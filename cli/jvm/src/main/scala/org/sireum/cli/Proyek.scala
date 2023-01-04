// #Sireum
/*
 Copyright (c) 2017-2023, Robby, Kansas State University
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.sireum.cli

import org.sireum._
import org.sireum.logika.{Smt2, Smt2Invoke}
import org.sireum.project.DependencyManager
import org.sireum.proyek.Analysis

object Proyek {
  val HOME_NOT_FOUND: Z = -1
  val JAVA_OR_SCALA_NOT_FOUND: Z = -2
  val IDEA_NOT_FOUND: Z = -3
  val INVALID_ARGS: Z = -4
  val INVALID_PATH_ARG: Z = -5
  val INVALID_PROJECT: Z = -6
  val INVALID_VERSIONS: Z = -7
  val INVALID_PUBLISH_DEP: Z = -8
  val INVALID_NATIVE_DEP: Z = -9
  val INVALID_UBER: Z = -9
  val INVALID_LINE: Z = -10
  val INVALID_SOURCE_FILE: Z = -11
  val INVALID_CHAR_WIDTH: Z = -12
  val INVALID_INT_WIDTH: Z = -13
  val ILL_FORMED_PROGRAMS: Z = -14

  def check(jsonOpt: Option[String],
            projectOpt: Option[String],
            minArgsOpt: Option[Z],
            maxArgsOpt: Option[Z],
            args: ISZ[String],
            versionFiles: ISZ[String],
            slice: ISZ[String]): (B, Z, Os.Path, project.Project, HashSMap[String, String]) = {
    var path = Os.cwd
    var prj = project.Project.empty
    var versions = HashSMap.empty[String, String]

    def ret(code: Z): (B, Z, Os.Path, project.Project, HashSMap[String, String]) = {
      return (F, code, path, prj, versions)
    }

    def ret2(h: B, code: Z): (B, Z, Os.Path, project.Project, HashSMap[String, String]) = {
      return (h, code, path, prj, versions)
    }

    if (args.size == 0) {
      return ret2(T, 0)
    }

    if (!SireumApi.homeFound) {
      return ret(HOME_NOT_FOUND)
    }

    if (!(SireumApi.javaFound && SireumApi.scalaFound)) {
      return ret(JAVA_OR_SCALA_NOT_FOUND)
    }

    if (jsonOpt.nonEmpty && projectOpt.nonEmpty) {
      eprintln("Cannot specify both the 'json' and the 'project' options")
      return ret(INVALID_PROJECT)
    }

    if (minArgsOpt.nonEmpty && minArgsOpt == maxArgsOpt) {
      val size = minArgsOpt.get
      if (args.size != size) {
        eprintln(s"Expecting $size argument(s), but found ${args.size}")
        eprintln()
        return ret2(T, INVALID_ARGS)
      }
    } else {
      minArgsOpt match {
        case Some(min) if args.size < min =>
          eprintln(s"Expecting at least $min argument(s), but found ${args.size}")
          eprintln()
          return ret2(T, INVALID_ARGS)
        case _ =>
      }
      maxArgsOpt match {
        case Some(max) if args.size > max =>
          eprintln(s"Expecting at most $max argument(s), but found ${args.size}")
          eprintln()
          return ret2(T, INVALID_ARGS)
        case _ =>
      }
    }

    path = getPath(args) match {
      case Some(p) => p
      case _ => return ret(INVALID_PATH_ARG)
    }

    println("Loading project ...")

    prj = org.sireum.proyek.Proyek.getProject(SireumApi.homeOpt.get, path, jsonOpt, projectOpt) match {
      case Some(pr) => pr.slice(slice)
      case _ => return ret(INVALID_PROJECT)
    }

    if (versionFiles.nonEmpty) {
      println()
      println("Loading version dependencies ...")
    }

    versions = org.sireum.proyek.Proyek.getVersions(prj, path, versionFiles, SireumApi.versions.entries) match {
      case Some(vs) => vs
      case _ => return ret(INVALID_VERSIONS)
    }

    val sireumHome = SireumApi.homeOpt.get
    val buildCmd = sireumHome / "bin" / "build.cmd"
    val runtimeVerOpt = versions.get(DependencyManager.libraryKey)
    assert(SireumApi.versions.get(DependencyManager.libraryKey).nonEmpty)
    if ((sireumHome / "cli" / "jvm" / "src" / "main" / "scala" / "org" / "sireum" / "Sireum.scala").exists &&
      runtimeVerOpt.nonEmpty && runtimeVerOpt == SireumApi.versions.get(DependencyManager.libraryKey)) {
      if (!Coursier.isRuntimePublishedLocally(SireumApi.scalaVer, runtimeVerOpt.get)) {
        println()
        println("Publishing Slang runtime library locally ...")
        proc"$buildCmd m2-lib".console.runCheck()
      }
    }

    return ret(0)
  }

  def assemble(o: Cli.SireumProyekAssembleOption): Z = {
    val (help, code, path, prj, versions) = check(o.json, o.project, Some(1), Some(1), o.args, o.versions, o.slice)
    if (help) {
      println(o.help)
      return code
    } else if (code != 0) {
      return code
    }

    if (o.mainClass.isEmpty && o.uber) {
      eprintln("Assembling an uber jar requires the main class name to be specified")
      return INVALID_UBER
    }

    val sireumHome = SireumApi.homeOpt.get

    if (o.isNative) {
      if (Os.kind == Os.Kind.Unsupported) {
        eprintln("Unsupported operating system for native image")
        return INVALID_NATIVE_DEP
      }
      if (!(sireumHome / "bin" / "install" / "graal.cmd").exists) {
        eprintln("Could not locate GraalVM installation Slash script")
        return INVALID_NATIVE_DEP
      }
    }

    println()

    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = F,
      withSource = F,
      withDoc = F,
      javaHome = SireumApi.javaHomeOpt.get,
      scalaHome = SireumApi.scalaHomeOpt.get,
      sireumHome = sireumHome,
      cacheOpt = o.cache.map((p: String) => Os.path(p))
    )

    var r: Z = 0

    if (!o.skipCompile) {
      r = proyek.Compile.run(
        path = path,
        outDirName = o.outputDirName.get,
        project = prj,
        projectName = o.name.getOrElse(path.canon.name),
        dm = dm,
        javacOptions = o.javac,
        scalacOptions = o.scalac,
        isJs = F,
        followSymLink = o.symlink,
        fresh = o.fresh,
        par = SireumApi.parCoresOpt(o.par),
        sha3 = o.sha3,
        ignoreRuntime = o.ignoreRuntime,
        recompileModuleIds = o.recompile
      )
    }

    if (r != 0) {
      return r
    }

    val projectName = o.name.getOrElse(path.canon.name)

    r = proyek.Assemble.run(
      path = path,
      outDirName = o.outputDirName.get,
      project = prj,
      projectName = projectName,
      jarName = o.jar.getOrElse(projectName),
      dm = dm,
      mainClassNameOpt = o.mainClass,
      isNative = o.isNative,
      isUber = o.uber
    )

    return r
  }

  def compile(o: Cli.SireumProyekCompileOption): Z = {
    val (help, code, path, prj, versions) = check(o.json, o.project, Some(1), Some(1), o.args, o.versions, o.slice)
    if (help) {
      println(o.help)
      return code
    } else if (code != 0) {
      return code
    }

    println()

    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = o.js,
      withSource = F,
      withDoc = F,
      javaHome = SireumApi.javaHomeOpt.get,
      scalaHome = SireumApi.scalaHomeOpt.get,
      sireumHome = SireumApi.homeOpt.get,
      cacheOpt = o.cache.map((p: String) => Os.path(p))
    )

    val r = proyek.Compile.run(
      path = path,
      outDirName = o.outputDirName.get,
      project = prj,
      projectName = o.name.getOrElse(path.canon.name),
      dm = dm,
      javacOptions = o.javac,
      scalacOptions = o.scalac,
      isJs = o.js,
      followSymLink = o.symlink,
      fresh = o.fresh,
      par = SireumApi.parCoresOpt(o.par),
      sha3 = o.sha3,
      ignoreRuntime = o.ignoreRuntime,
      recompileModuleIds = o.recompile
    )

    return r
  }

  def dep(o: Cli.SireumProyekDepOption): Z = {
    val (help, code, _, prj, versions) = check(o.json, o.project, Some(1), Some(1), o.args, o.versions, o.slice)
    if (help) {
      println(o.help)
      return code
    } else if (code != 0) {
      return code
    }

    println()

    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = o.js,
      withSource = F,
      withDoc = F,
      javaHome = SireumApi.javaHomeOpt.get,
      scalaHome = SireumApi.scalaHomeOpt.get,
      sireumHome = SireumApi.homeOpt.get,
      cacheOpt = o.cache.map((p: String) => Os.path(p))
    )

    var deps = ISZ[String]()
    for (m <- prj.modules.values if prj.poset.childrenOf(m.id).isEmpty) {
      deps = deps ++ dm.computeTransitiveIvyDeps(m)
    }

    Coursier.resolve(dm.scalaVersion, dm.cacheOpt, o.repositories, deps, T)

    return 0
  }

  def ive(o: Cli.SireumProyekIveOption): Z = {
    if (o.empty) {
      val p: Os.Path = o.project match {
        case Some(f) => Os.path(f)
        case _ => Os.cwd / "bin" / "project.cmd"
      }
      p.up.mkdirAll()
      if (p.exists) {
        eprintln(s"$p already exists")
        return INVALID_PROJECT
      }
      val bs = "\\"
      p.writeOver(
        st"""::#! 2> /dev/null                                   #
            |@ 2>/dev/null # 2>nul & echo off & goto BOF         #
            |if [ -z $${SIREUM_HOME} ]; then                      #
            |  echo "Please set SIREUM_HOME env var"             #
            |  exit -1                                           #
            |fi                                                  #
            |exec $${SIREUM_HOME}/bin/sireum slang run "$$0" "$$@"  #
            |:BOF
            |setlocal
            |if not defined SIREUM_HOME (
            |  echo Please set SIREUM_HOME env var
            |  exit /B -1
            |)
            |%SIREUM_HOME%${bs}bin${bs}sireum.bat slang run "%0" %*
            |exit /B %errorlevel%
            |::!#
            |// #Sireum
            |
            |import org.sireum._
            |import org.sireum.project.ProjectUtil._
            |import org.sireum.project.Project
            |
            |val project = Project.empty
            |projectCli(Os.cliArgs, project)""".render
      )
      p.chmod("+x")
    }

    val (help, code, path, prj, versions) = check(o.json, o.project, Some(1), Some(1), o.args, o.versions, o.slice)
    if (help) {
      println(o.help)
      return code
    } else if (code != 0) {
      return code
    }

    var projectName = o.name.getOrElse(path.canon.name)
    if (prj.modules.contains(projectName)) {
      projectName = s"$projectName-proyek"
      if (prj.modules.contains(projectName)) {
        eprintln(s"Could not use project name ${o.name.getOrElse(path.canon.name)} or $projectName as they are used as module names")
        return INVALID_PROJECT
      }
    }

    val ideaDir: Os.Path = (SireumApi.ideaDir.exists, SireumApi.ideaUltimateDir.exists,
      SireumApi.ideaServerDir.map((p: Os.Path) => p.exists).getOrElseEager(F)) match {
      case (T, F, F) => SireumApi.ideaDir
      case (F, T, T) => SireumApi.ideaUltimateDir
      case (F, F, T) => SireumApi.ideaServerDir.get
      case _ =>
        o.edition match {
          case Cli.SireumProyekIveEdition.Community =>
            if (!SireumApi.ideaDir.exists) {
              eprintln("Sireum IVE is not installed")
              return IDEA_NOT_FOUND
            }
            SireumApi.ideaDir
          case Cli.SireumProyekIveEdition.Ultimate =>
            if (!SireumApi.ideaUltimateDir.exists) {
              eprintln("Sireum IVE Ultimate is not installed")
              return IDEA_NOT_FOUND
            }
            SireumApi.ideaUltimateDir
          case Cli.SireumProyekIveEdition.Server =>
            SireumApi.ideaServerDir match {
              case Some(p) if p.exists =>
              case _ =>
                eprintln("Sireum IVE Server is not installed")
                return IDEA_NOT_FOUND
            }
            SireumApi.ideaServerDir.get
        }
    }

    println()

    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = F,
      withSource = o.sources,
      withDoc = o.docs,
      javaHome = SireumApi.javaHomeOpt.get,
      scalaHome = SireumApi.scalaHomeOpt.get,
      sireumHome = SireumApi.homeOpt.get,
      cacheOpt = o.cache.map((p: String) => Os.path(p))
    )

    val r = proyek.Ive.run(
      sireumHome = SireumApi.homeOpt.get,
      path = path,
      project = prj,
      projectName = projectName,
      dm = dm,
      outDirName = o.outputDirName.get,
      ideaDir = ideaDir.canon,
      isDev = SireumApi.isDev,
      force = o.force,
      javacOptions = o.javac,
      scalacOptions = o.scalac,
      configPath = SireumApi.init.ideaConfig(SireumApi.isDev, o.edition == Cli.SireumProyekIveEdition.Ultimate,
        if (o.edition == Cli.SireumProyekIveEdition.Server) Some(path) else None()),
      sandboxPath = SireumApi.init.ideaSandbox(SireumApi.isDev)
    )

    return r
  }

  def logika(o: Cli.SireumProyekLogikaOption, reporter: org.sireum.logika.Logika.Reporter): Z = {
    val (help, code, path, prj, versions) = check(o.json, o.project, Some(1), None(), o.args, o.versions, o.slice)
    if (help) {
      println(o.help)
      return code
    } else if (code != 0) {
      return code
    }

    if (o.line != 0 && (o.all || o.args.size != 2)) {
      eprintln("Line focused verification can only be used for checking a single file")
      return INVALID_LINE
    }

    if (o.args.size == 1 && !o.all) {
      eprintln("Either specify file(s) to check or supply the --all option")
      return INVALID_ARGS
    }

    if (o.all && o.args.size > 1) {
      eprintln("Cannot specify file arguments if --all is supplied")
      return INVALID_ARGS
    }

    var files = HashSMap.empty[String, String]
    for (i <- 1 until o.args.size) {
      val arg = o.args(i)
      val p = Os.path(arg)
      var ok = F
      if (p.isFile && (p.ext == "slang" || p.ext == "scala")) {
        val content = p.read
        if (org.sireum.lang.parser.Parser.detectSlang(Some(p.toUri), content)._1) {
          ok = T
          files = files + p.canon.string ~> content
        }
      }
      if (!ok) {
        eprintln(s"$arg is not a Slang file")
        return INVALID_SOURCE_FILE
      }
    }

    o.charBitWidth match {
      case z"8" =>
      case z"16" =>
      case z"32" =>
      case _ =>
        eprintln(s"C (character) bit-width has to be 8, 16, or 32 (instead of ${o.charBitWidth})")
        return INVALID_CHAR_WIDTH
    }

    o.intBitWidth match {
      case z"0" =>
      case z"8" =>
      case z"16" =>
      case z"32" =>
      case z"64" =>
      case _ =>
        eprintln(s"Z (integer) bit-width has to be 0 (arbitrary-precision), 8, 16, 32, or 64 (instead of ${o.intBitWidth})")
        return INVALID_INT_WIDTH
    }

    val nameExePathMap: HashMap[String, String] = SireumApi.homeOpt match {
      case Some(sireumHome) => Smt2Invoke.nameExePathMap(sireumHome)
      case _ =>
        val exeOpt: Option[String] = if (Os.isWin) Some(".exe") else None()
        HashMap.empty[String, String] ++ ISZ[(String, String)](
          "cvc4" ~> st"cvc4$exeOpt".render,
          "cvc5" ~> st"cvc5$exeOpt".render,
          "z3" ~> st"z3$exeOpt".render,
        )
    }

    val smt2Configs =
      Smt2.parseConfigs(nameExePathMap, F, o.smt2ValidConfigs.get, o.timeout * 1000, o.rlimit).left ++
        Smt2.parseConfigs(nameExePathMap, T, o.smt2SatConfigs.get, Smt2.satTimeoutInMs, o.rlimit).left

    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = F,
      withSource = F,
      withDoc = F,
      javaHome = SireumApi.javaHomeOpt.get,
      scalaHome = SireumApi.scalaHomeOpt.get,
      sireumHome = SireumApi.homeOpt.get,
      cacheOpt = o.cache.map((p: String) => Os.path(p))
    )

    val libV = ops.StringOps(SireumApi.commitHash).substring(0, 10)
    versions.get(DependencyManager.libraryKey) match {
      case Some(v) if libV != v =>
        println(s"Verification is done using the internal library v.$libV")
        println(s"instead of using v.$v as specified in the given version property file(s)")
        println()
      case _ =>
    }

    val fpRoundingMode: String = o.fpRounding match {
      case Cli.SireumProyekLogikaFPRoundingMode.NearestTiesToEven => "RNE"
      case Cli.SireumProyekLogikaFPRoundingMode.NearestTiesToAway => "RNA"
      case Cli.SireumProyekLogikaFPRoundingMode.TowardPositive => "RTP"
      case Cli.SireumProyekLogikaFPRoundingMode.TowardNegative => "RTN"
      case Cli.SireumProyekLogikaFPRoundingMode.TowardZero => "RTZ"
    }

    val parCores = SireumApi.parCoresOpt(o.par)
    val branchParCores = SireumApi.parCoresOpt(o.branchPar)

    val branchParMode: org.sireum.logika.Config.BranchPar.Type = o.branchParMode match {
      case Cli.SireumProyekLogikaBranchPar.All => org.sireum.logika.Config.BranchPar.All
      case Cli.SireumProyekLogikaBranchPar.Returns => org.sireum.logika.Config.BranchPar.OnlyAllReturns
      case Cli.SireumProyekLogikaBranchPar.Disabled => org.sireum.logika.Config.BranchPar.Disabled
    }

    val config = org.sireum.logika.Config(smt2Configs, parCores, o.sat, o.rlimit, o.timeout * 1000, o.charBitWidth,
      o.intBitWidth, o.useReal, o.logPc, o.logRawPc, o.logVc, o.logVcDir, o.dontSplitFunQuant,
      o.splitAll, o.splitIf, o.splitMatch, o.splitContract, o.simplify, T, fpRoundingMode, F,
      o.sequential, branchParMode, branchParCores, o.logPcLines, o.interprocedural, o.loopBound, o.callBound,
      o.interproceduralContracts)

    val lcode = Analysis.run(
      root = path,
      outDirName = o.outputDirName.get,
      project = prj,
      dm = dm,
      cacheInput = F,
      cacheTypeHierarchy = F,
      mapBox = MBox2(HashMap.empty, HashMap.empty),
      config = config,
      cache = Smt2.NoCache(),
      files = files,
      filesWatched = F,
      vfiles = files.keys,
      line = o.line,
      par = SireumApi.parCoresOpt(o.par),
      strictAliasing = o.strictAliasing,
      followSymLink = o.symlink,
      all = o.all,
      verify = T,
      disableOutput = F,
      verbose = o.verbose,
      sanityCheck = F,
      plugins = org.sireum.logika.Logika.defaultPlugins,
      skipMethods = o.skipMethods,
      skipTypes = o.skipTypes,
      reporter = reporter
    )
    reporter.printMessages()
    if (lcode == 0) {
      println()
      println("Logika verified!")
      return 0
    } else {
      println()
      return ILL_FORMED_PROGRAMS
    }
  }

  def publish(o: Cli.SireumProyekPublishOption): Z = {
    val (help, code, path, prj, versions) = check(o.json, o.project, Some(2), Some(2), o.args, o.versions, o.slice)
    if (help) {
      println(o.help)
      return code
    } else if (code != 0) {
      return code
    }

    val m2Repo: Os.Path = o.m2 match {
      case Some(m) => Os.path(m) / "repository"
      case _ => Os.home / ".m2" / "repository"
    }

    val version: String = o.version match {
      case Some(v) => v
      case _ => ops.StringOps(proc"git log -1 --date=format:%Y%m%d.%H%M --pretty=format:%cd.%h".runCheck().out).trim
    }

    val orgName = ops.StringOps(o.args(1)).split((c: C) => c == '.')

    val publishModuleIds: ISZ[String] = for (m <- prj.modules.values if m.publishInfoOpt.nonEmpty) yield m.id
    for (m <- prj.modules.values) {
      val diff = m.deps -- publishModuleIds
      if (diff.nonEmpty) {
        eprintln(st"Publishable module ${m.id} depends on non-publishable module(s): ${(diff, ", ")}".render)
        return INVALID_PUBLISH_DEP
      }
    }

    println()

    val targets: MSZ[B] = {
      var ts = HashSSet.empty[B]
      for (t <- o.target) {
        t match {
          case Cli.SireumProyekPublishTarget.All => ts = ts + F + T
          case Cli.SireumProyekPublishTarget.Jvm => ts = ts + F
          case Cli.SireumProyekPublishTarget.Js => ts = ts + T
        }
      }
      ts.elements.toMS
    }
    val dms: MSZ[project.DependencyManager] = for (isJs <-targets) yield project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = isJs,
      withSource = F,
      withDoc = F,
      javaHome = SireumApi.javaHomeOpt.get,
      scalaHome = SireumApi.scalaHomeOpt.get,
      sireumHome = SireumApi.homeOpt.get,
      cacheOpt = o.cache.map((p: String) => Os.path(p))
    )

    var r: Z = 0

    if (!o.skipCompile) {
      for (i <- 0 until dms.size if r == 0) {
        println(s"Compiling for the ${if (dms(i).isJs) "Javascript" else "JVM"} target ...")
        println()
        r = proyek.Compile.run(
          path = path,
          outDirName = o.outputDirName.get,
          project = prj,
          projectName = o.name.getOrElse(path.canon.name),
          dm = dms(i),
          javacOptions = o.javac,
          scalacOptions = o.scalac,
          isJs = dms(i).isJs,
          followSymLink = o.symlink,
          fresh = o.fresh,
          par = SireumApi.parCoresOpt(o.par),
          sha3 = o.sha3,
          ignoreRuntime = o.ignoreRuntime,
          recompileModuleIds = o.recompile
        )
      }
    }

    for (i <- 0 until dms.size if r == 0) {
      println(s"Publishing for the ${if (dms(i).isJs) "Javascript" else "JVM"} target ...")
      println()
      r = proyek.Publish.run(
        path = path,
        outDirName = o.outputDirName.get,
        project = prj,
        projectName = o.name.getOrElse(path.canon.name),
        dm = dms(i),
        isJs = dms(i).isJs,
        orgName = orgName,
        m2Repo = m2Repo,
        version = version,
        symlink = o.symlink
      )
      println()
    }

    return r
  }

  def run(o: Cli.SireumProyekRunOption): Z = {
    val (help, code, path, prj, versions) = check(o.json, o.project, Some(2), None(), o.args, o.versions, o.slice)
    if (help) {
      println(o.help)
      return code
    } else if (code != 0) {
      return code
    }

    println()

    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = F,
      withSource = F,
      withDoc = F,
      javaHome = SireumApi.javaHomeOpt.get,
      scalaHome = SireumApi.scalaHomeOpt.get,
      sireumHome = SireumApi.homeOpt.get,
      cacheOpt = o.cache.map((p: String) => Os.path(p))
    )

    var r: Z = 0

    if (!o.skipCompile) {
      r = proyek.Compile.run(
        path = path,
        outDirName = o.outputDirName.get,
        project = prj,
        projectName = o.name.getOrElse(path.canon.name),
        dm = dm,
        javacOptions = o.javac,
        scalacOptions = o.scalac,
        isJs = F,
        followSymLink = o.symlink,
        fresh = o.fresh,
        par = SireumApi.parCoresOpt(o.par),
        sha3 = o.sha3,
        ignoreRuntime = o.ignoreRuntime,
        recompileModuleIds = o.recompile
      )
    }

    if (r != 0) {
      return r
    }

    r = proyek.Run.run(
      path = path,
      outDirName = o.outputDirName.get,
      project = prj,
      projectName = o.name.getOrElse(path.canon.name),
      dm = dm,
      javaOptions = o.java,
      dir = Os.path(o.dir.getOrElseEager(Os.cwd.string)),
      className = o.args(1),
      args = ops.ISZOps(o.args).drop(2)
    )

    return r
  }

  def stats(o: Cli.SireumProyekStatsOption, reporter: message.Reporter): Z = {
    val (help, code, path, prj, versions) = check(o.json, o.project, Some(1), Some(2), o.args, o.versions, o.slice)
    if (help) {
      println(o.help)
      return code
    } else if (code != 0) {
      return code
    }

    val projectName = o.name.getOrElse(path.canon.name)
    if (prj.modules.contains(projectName)) {
      eprintln("Project name cannot be the same as a module name")
      return INVALID_PROJECT
    }

    println()

    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = F,
      withSource = o.sources,
      withDoc = o.docs,
      javaHome = SireumApi.javaHomeOpt.get,
      scalaHome = SireumApi.scalaHomeOpt.get,
      sireumHome = SireumApi.homeOpt.get,
      cacheOpt = o.cache.map((p: String) => Os.path(p))
    )

    val output: Os.Path = o.args match {
      case ISZ(_) => o.name match {
        case Some(name) => Os.path(s"$name.csv").canon
        case _ => Os.path("project.csv").canon
      }
      case ISZ(_, file) => Os.path(file).canon
    }

    output.up.mkdirAll()

    val r = proyek.Stats.run(
      root = path,
      project = prj,
      dm = dm,
      par = 0,
      strictAliasing = T,
      followSymLink = F,
      output = output,
      reporter = reporter
    )

    return r
  }

  def test(o: Cli.SireumProyekTestOption): Z = {
    val (help, code, path, prj, versions) = check(o.json, o.project, Some(1), None(), o.args, o.versions, o.slice)
    if (help) {
      println(o.help)
      return code
    } else if (code != 0) {
      return code
    }

    println()

    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = F,
      withSource = F,
      withDoc = F,
      javaHome = SireumApi.javaHomeOpt.get,
      scalaHome = SireumApi.scalaHomeOpt.get,
      sireumHome = SireumApi.homeOpt.get,
      cacheOpt = o.cache.map((p: String) => Os.path(p))
    )

    var r: Z = 0

    if (!o.skipCompile) {
      r = proyek.Compile.run(
        path = path,
        outDirName = o.outputDirName.get,
        project = prj,
        projectName = o.name.getOrElse(path.canon.name),
        dm = dm,
        javacOptions = o.javac,
        scalacOptions = o.scalac,
        isJs = F,
        followSymLink = o.symlink,
        fresh = o.fresh,
        par = SireumApi.parCoresOpt(o.par),
        sha3 = o.sha3,
        ignoreRuntime = o.ignoreRuntime,
        recompileModuleIds = o.recompile
      )
    }

    if (r != 0) {
      return r
    }

    r = proyek.Test.run(
      path = path,
      outDirName = o.outputDirName.get,
      project = prj,
      projectName = o.name.getOrElse(path.canon.name),
      dm = dm,
      javaOptions = o.java,
      classNames = o.classes,
      suffixes = o.suffixes,
      packageNames = o.packages,
      names = ops.ISZOps(o.args).drop(1)
    )

    return r
  }

  def tipe(o: Cli.SireumProyekTipeOption, reporter: org.sireum.logika.Logika.Reporter): Z = {
    val (help, code, path, prj, versions) = check(o.json, o.project, Some(1), None(), o.args, o.versions, o.slice)
    if (help) {
      println(o.help)
      return code
    } else if (code != 0) {
      return code
    }

    if (o.args.size > 1) {
      eprintln(st"Unexpected command line arguments: ${(ops.ISZOps(o.args).drop(1), " ")}".render)
      return INVALID_ARGS
    }

    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = F,
      withSource = F,
      withDoc = F,
      javaHome = SireumApi.javaHomeOpt.get,
      scalaHome = SireumApi.scalaHomeOpt.get,
      sireumHome = SireumApi.homeOpt.get,
      cacheOpt = o.cache.map((p: String) => Os.path(p))
    )

    val config = org.sireum.logika.Config(ISZ(), 0, F, 0, 0, 8, 32, F, F, F, F, None(),
      F, F, F, F, F, F, F, "RNE", F, F, org.sireum.logika.Config.BranchPar.Disabled, 0, F, F, 3, 3, F)
    val lcode = Analysis.run(
      root = path,
      outDirName = "out",
      project = prj,
      dm = dm,
      cacheInput = F,
      cacheTypeHierarchy = F,
      mapBox = MBox2(HashMap.empty, HashMap.empty),
      config = config,
      cache = Smt2.NoCache(),
      files = HashSMap.empty,
      filesWatched = F,
      vfiles = ISZ(),
      line = 0,
      par = SireumApi.parCoresOpt(o.par),
      strictAliasing = o.strictAliasing,
      followSymLink = o.symlink,
      all = T,
      disableOutput = F,
      verify = F,
      verbose = o.verbose,
      sanityCheck = T,
      plugins = ISZ(),
      skipMethods = ISZ(),
      skipTypes = ISZ(),
      reporter = reporter
    )

    if (reporter.hasIssue) {
      println()
      reporter.printMessages()
    } else if (lcode == 0) {
      println()
      println("Programs are well-typed!")
    }

    return if (lcode === 0) 0 else ILL_FORMED_PROGRAMS
  }

  def getPath(args: ISZ[String]): Option[Os.Path] = {
    val p = args(0)
    val d = Os.path(p)
    if (d.exists && !d.isDir) {
      eprintln(s"$p is not a directory")
      return None()
    }
    return Some(d.canon)
  }

}
