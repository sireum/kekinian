// #Sireum
/*
 Copyright (c) 2017-2026,Robby, Kansas State University
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
import org.sireum.U8._
import org.sireum.lang.symbol.Resolver.{NameMap, TypeMap}
import org.sireum.lang.tipe.TypeHierarchy
import org.sireum.logika.Config.StrictPureMode
import org.sireum.logika.{Config, Smt2, Smt2Formatter, Smt2Invoke}
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
  val FAILED_REFLECT_GEN: Z = -15
  val INVALID_IVY_FORM: Z = -16

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
      case Some(pr) =>
        if (slice.nonEmpty) {
          val mids = pr.modules.keySet
          val invalidIds: ISZ[String] = for (s <- slice if !mids.contains(s)) yield s
          if (invalidIds.nonEmpty) {
            eprintln(st"Invalid module id(s): ${(invalidIds, ", ")}".render)
            return ret(INVALID_PROJECT)
          }
        }
        pr.slice(slice)
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
      withSource = o.includeSources,
      withDoc = F,
      javaHome = SireumApi.javaHomeOpt.get,
      scalaHome = SireumApi.scalaHomeOpt.get,
      sireumHome = sireumHome,
      cacheOpt = o.cache.map((p: String) => Os.path(p)),
      proxy = Coursier.Proxy(hostOpt = o.proxyHost, nonHostsOpt = o.proxyNonHosts, portOpt = o.proxyPort,
        protocolOpt = o.proxyProtocol, protocolUserEnvKeyOpt = o.proxyUser, protocolPasswordEnvKeyOpt = o.proxyPassword)
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
        genSemanticsDB = o.meta,
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

    var excludedDeps = ISZ[(String, String)]()

    for (ivy <- o.excludeJarDeps) {
      ops.StringOps(ivy).split((c: C) => c == ':') match {
        case ISZ(org) => excludedDeps = excludedDeps :+ (ops.StringOps(org).trim,  "")
        case ISZ(org, module) => excludedDeps :+ (ops.StringOps(org).trim,  ops.StringOps(module).trim)
        case _ =>
          eprintln(s"Unsupported ivy form: $ivy")
          return INVALID_IVY_FORM
      }
    }

    r = proyek.Assemble.run(
      path = path,
      outDirName = o.outputDirName.get,
      project = prj,
      projectName = projectName,
      jarName = o.jar.getOrElse(projectName),
      noDeps = o.noDeps,
      dm = dm,
      mainClassNameOpt = o.mainClass,
      isNative = o.isNative,
      isNativeScript = o.isNativeScript,
      isUber = o.uber,
      includeSources = o.includeSources,
      includeTests = o.includeTests,
      excludedDeps = excludedDeps
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
      cacheOpt = o.cache.map((p: String) => Os.path(p)),
      proxy = Coursier.Proxy(hostOpt = o.proxyHost, nonHostsOpt = o.proxyNonHosts, portOpt = o.proxyPort,
        protocolOpt = o.proxyProtocol, protocolUserEnvKeyOpt = o.proxyUser, protocolPasswordEnvKeyOpt = o.proxyPassword)
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
      genSemanticsDB = o.meta,
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
      cacheOpt = o.cache.map((p: String) => Os.path(p)),
      proxy = Coursier.Proxy(hostOpt = o.proxyHost, nonHostsOpt = o.proxyNonHosts, portOpt = o.proxyPort,
        protocolOpt = o.proxyProtocol, protocolUserEnvKeyOpt = o.proxyUser, protocolPasswordEnvKeyOpt = o.proxyPassword)
    )

    var deps = ISZ[String]()
    var unmanaged = HashSSet.empty[String]
    for (m <- prj.modules.values if prj.poset.childrenOf(m.id).isEmpty) {
      for (d <- dm.computeTransitiveIvyDeps(m)) {
        dm.depKind(d) match {
          case DependencyManager.DepKind.IVY => deps = deps :+ d
          case DependencyManager.DepKind.File => unmanaged = unmanaged + Os.Path.fromUri(d).string
          case _ => unmanaged = unmanaged + d
        }
      }
    }

    Coursier.resolve(dm.scalaVersion, dm.cacheOpt, o.repositories, deps, T, Coursier.Proxy.empty)

    if (unmanaged.nonEmpty) {
      println()
      println("Unmanaged:")
      for (f <- unmanaged.elements) {
        println(s"* ${Os.Path.fromUri(f)}")
      }
    }
    return 0
  }

  @datatype class Files {
    @memoize def hasFiles(paths: ISZ[Os.Path]): B = {
      for (path <- paths; _ <- Os.Path.walk(path, F, F, (p: Os.Path) => p.ext == "scala" || p.ext == "java")) {
        return T
      }
      return F
    }
  }

  def exprt(o: Cli.SireumProyekExportOption): Z = {
    if (o.args.size != 1) {
      println(o.help)
      return 0
    }

    var genMill = F
    var genBloop = F
    val files = Files()

    for (t <- o.target) {
      t match {
        case Cli.SireumProyekExportBuildTool.Mill => genMill = T
        case Cli.SireumProyekExportBuildTool.Bloop => genBloop = T
      }
    }

    val init = Init(SireumApi.homeOpt.get, Os.kind, SireumApi.versions)
    init.installMill(genMill)

    val root: Os.Path = getPath(o.args) match {
      case Some(p) => p
      case _ => return INVALID_PATH_ARG
    }

    val rootName = root.canon.name

    var millGenerated = F

    @pure def quote(name: String): String = {
      if (ops.StringOps.scalaKeywords.contains(name) || !ops.StringOps(name).isJavaId) {
        return s"`$name`"
      } else {
        return name
      }
    }

    def millGen(prj: project.Project, versions: HashSMap[String, String]): Unit = {
      if (millGenerated) {
        return
      }
      millGenerated = T
      @pure def module(m: project.Module): ST = {
        var supers = ISZ[String]()
        val hasJs = ops.ISZOps(m.targets).exists((t: project.Target.Type) => t == project.Target.Js)
        val hasJvm = ops.ISZOps(m.targets).exists((t: project.Target.Type) => t == project.Target.Js)
        var hasScala = F
        var base = root.relativize(Os.path(m.basePath))
        m.subPathOpt match {
          case Some(sub) => base = base / sub
          case _ =>
        }
        for (src <- m.sources ++ m.testSources; _ <- Os.Path.walk(root / base.string / src, F, F, (p: Os.Path) => p.ext == "scala" || p.ext == "sc")) {
          hasScala = T
        }
        supers = supers :+ (
          if (hasJs && !hasJvm /* Workaround: broken Bloop Scala.js support */ && !genBloop ) "ScalaJSModule"
          else if (hasScala) "ScalaModule"
          else "JavaModule"
        )
        val fileSep: C = if (Os.isWin) '\\' else '/'
        @strictpure def split(s: String): ISZ[String] = for (p <- ops.StringOps(s).split((c: C) => c == fileSep)) yield s"\"$p\""
        val bases = split(base.string)
        val mid = quote(s"m-${m.id}")
        val testsOpt: Option[ST] = if (!files.hasFiles(project.ProjectUtil.moduleTestSources(m))) None() else Some(
          st"""object tests extends TestSuite {
              |  def millSourcePath = $mid.millSourcePath
              |  def moduleDeps = Seq($mid${(for (parent <- prj.poset.ancestorsOf(m.id).elements if files.hasFiles(project.ProjectUtil.moduleTestSources(prj.modules.get(parent).get))) yield s", `m-$parent`.tests", "")})
              |  def sources = Task.Sources {
              |    Seq(${(for (src <- m.testSources) yield st"PathRef(millSourcePath / ${(split(src), " / ")})", ", ")})
              |  }
              |  def resources = Task.Sources {
              |    Seq(${(for (src <- m.testResources) yield st"PathRef(millSourcePath / ${(split(src), " / ")})", ", ")})
              |  }
              |}"""
        )
        val ivyDepOpt: Option[ST] = if (prj.poset.parentsOf(m.id).isEmpty) Some(st"ivy\"org.scala-lang:scala-reflect:$$scalaVer\",") else None()
        val ivyDeps: ST = if (ivyDepOpt.isEmpty && m.ivyDeps.isEmpty) st"def ivyDeps = Task { Agg.empty }" else
          st"""def ivyDeps = Task { Agg(
              |  $ivyDepOpt
              |  ${(for (d <- m.ivyDeps) yield st"""ivy"$d$${`$d`}"""", ",\n")}
              |)}"""
        val r =
          st"""object ${quote(m.id)} extends ${(supers, " with ")} {
              |  def millSourcePath = super.millSourcePath / os.up${(for (b <- bases) yield st" / $b", "")}
              |  def moduleDeps = Seq(${(for (parent <- prj.poset.parentsOf(m.id).elements) yield s"`m-$parent`", ", ")})
              |  def sources = Task.Sources {
              |    Seq(${(for (src <- m.sources) yield st"PathRef(millSourcePath / ${(split(src), " / ")})", ", ")})
              |  }
              |  def resources = Task.Sources {
              |    Seq(${(for (src <- m.resources) yield st"PathRef(millSourcePath / ${(split(src), " / ")})", ", ")})
              |  }
              |  $ivyDeps
              |  $testsOpt
              |}"""
        return r
      }
      var modules: ISZ[ST] = if (prj.modules.size == 1 && Os.path(prj.modules.values(0).basePath).canon.string == root.canon.string) ISZ() else ISZ[ST](
        st"""object ${quote(rootName)} extends ScalaModule {
            |  def millSourcePath = super.millSourcePath / os.up
            |  def sources = Task.Sources { Seq(${if (genBloop) "PathRef(millSourcePath / \".BIN\")" else "" }) }
            |  def resources = Task.Sources { Seq() }
            |  def unmanagedClasspath = Task {
            |    val sireumHome = Option(System.getenv("SIREUM_HOME")) match {
            |      case Some(p) => os.Path(p)
            |      case _ => os.Path("${ops.StringOps(SireumApi.homeOpt.get.string).escapeST}")
            |    }
            |    Agg(PathRef(sireumHome / "bin" / "sireum.jar"))
            |  }
            |}"""
      )
      var depVersions = HashSMap.empty[String, String]
      var modDefs = ISZ[ST]()
      var seen = HashSet.empty[String]
      var works = HashSSet ++ prj.poset.rootNodes
      while (works.nonEmpty) {
        var next = ISZ[String]()
        for (mid <- works.elements) {
          val m = prj.modules.get(mid).get
          for (ivyDep <- m.ivyDeps) {
            depVersions = depVersions + ivyDep ~> versions.get(ivyDep).get
          }
          modDefs = modDefs :+ st"def `m-${m.id}` = ${quote(m.id)}"
          modules = modules :+ module(m)
          seen = seen + mid
          for (child <- prj.poset.childrenOf(mid).elements) {
            if (ops.ISZOps(prj.poset.parentsOf(child).elements).forall((p: String) => seen.contains(p))) {
              next = next :+ child
            }
          }
        }
        works = HashSSet ++ next
      }
      val build = (root / "build.mill").canon
      val javaST =
        st"""def javacOptions = javacOpts
            |def repositoriesTask = Task.Anon { super.repositoriesTask() ++ repos }"""
      val scalaST =
        st"""def scalaVersion = scalaVer
            |def scalacOptions = scalacOpts
            |def scalacPluginIvyDeps = Agg(ivy"org.sireum::scalac-plugin:$$scalacPluginVer")
            |def scalaDocOptions = scalaDocOpts
            |$javaST"""
      @strictpure def testsST(id: String): ST =
        st"""trait TestSuite extends ${id}Tests with scalalib.TestModule.ScalaTest {
            |  def ivyDeps = Agg(ivy"org.scalatest::scalatest::$$scalaTestVer")
            |}"""

      @pure def filterOptions(opts: ISZ[String]): ISZ[String] = {
        val ignoreOptions = HashSet ++ ISZ[String]("-Xfatal-warnings", "-Werror")
        return for (opt <- opts if !ignoreOptions.contains(opt)) yield opt
      }

      build.writeOver(
        st"""// Auto-generated
            |package build
            |
            |import mill._, scalalib._
            |
            |val scalaVer = "${SireumApi.scalaVer}"
            |val scalaJSVer = "${SireumApi.versions.get("org.scala-js:::scalajs-compiler:")}"
            |val scalaTestVer = "${SireumApi.versions.get("org.scalatest::scalatest::").get}"
            |val scalacPluginVer = "${SireumApi.versions.get("org.sireum::scalac-plugin:").get}"
            |
            |val scalacOpts = Seq(${(for (opt <- filterOptions(o.scalac)) yield s"\"$opt\"", ", ")})
            |
            |val scalaDocOpts = Seq("-siteroot", "mydocs", "-no-link-warnings")
            |
            |val javacOpts = Seq(${(for (opt <- o.javac) yield s"\"$opt\"", ", ")})
            |
            |val repos = Seq(
            |  coursier.maven.MavenRepository((os.home / ".m2" / "repository").toIO.toURI.toASCIIString),
            |  coursier.maven.MavenRepository("https://oss.sonatype.org/content/repositories/releases"),
            |  coursier.maven.MavenRepository("https://jitpack.io")
            |)
            |
            |${(for (d <- depVersions.entries) yield st"""val `${d._1}` = "${d._2}"""", "\n")}
            |
            |${(modDefs, "\n")}
            |
            |
            |trait JavaModule extends javalib.JavaModule {
            |  $javaST
            |  ${testsST("Java")}
            |}
            |
            |trait ScalaModule extends scalalib.ScalaModule {
            |  $scalaST
            |  ${testsST("Scala")}
            |}
            |
            |trait ScalaJSModule extends scalajslib.ScalaJSModule {
            |  $scalaST
            |  def scalaJSVersion = scalaJSVer
            |  ${testsST("ScalaJS")}
            |}
            |
            |${(modules, "\n\n")}""".render
      )
      if (genMill) {
        println(s"Wrote $build")
      }
    }

    def bloopGen(): Unit = {
      println("Generating Bloop configuration ...")
      val pr: OsProto.Proc = if (Os.isWin) {
        proc"cmd /C ${SireumApi.homeOpt.get / "bin" / "mill.bat"} --ticker false --color false --import ivy:com.lihaoyi::mill-contrib-bloop: mill.contrib.bloop.Bloop/install"
      } else {
        Os.proc(ISZ("bash", "-c", s"\"${SireumApi.homeOpt.get / "bin" / "mill"}\" --ticker false --color false --import ivy:com.lihaoyi::mill-contrib-bloop: mill.contrib.bloop.Bloop/install"))
      }
      (root / ".bloop").removeAll()
      val env = ISZ(
        "JAVA_HOME" ~> SireumApi.javaHomeOpt.get.string,
        "PATH" ~> s"${SireumApi.javaHomeOpt.get / "bin"}${Os.pathSep}${Os.env("PATH").get}",
        "COURSIER_CACHE" ~> Coursier.defaultCacheDir.string
      )
      pr.console.at(root).env(env).runCheck()
      val top = root / ".bloop" / s"$rootName.json"
      if (top.exists) {
        top.writeOver(ops.StringOps(top.read).replaceAllLiterally(s".BIN\"", s"bin\""))
      }
      (root / ".bloop" / "mill-build-.json").removeAll()
      if (!genMill) {
        (root / "build.mill").removeAll()
        (root / ".mill-version").removeAll()
      }
      println()
    }

    val dotSireum = root / ".sireum"
    (root / ".mill-version").writeOver(SireumApi.versions.get("org.sireum.version.mill").get)
    if (dotSireum.exists) {
      val sireumHome = SireumApi.homeOpt.get
      val buildCmd = sireumHome / "bin" / "build.cmd"
      if ((SireumApi.homeOpt.get / "cli" / "jvm" / "src" / "main" / "scala" / "org" / "sireum" / "Sireum.scala").exists) {
        if (!Coursier.isRuntimePublishedLocally(SireumApi.scalaVer,
          ops.StringOps(SireumApi.commitHash).substring(0, 10))) {
          println()
          println("Publishing Slang runtime library locally ...")
          proc"$buildCmd m2-lib".console.runCheck()
        }
      }
      (root / "build.mill").writeOver(
        st"""package build
            |
            |import mill._, scalalib._
            |
            |object build extends ScalaModule {
            |  def scalaVersion = "${SireumApi.versions.get("org.scala-lang:scala-library:").get}"
            |  def ivyDeps = Agg(
            |    ivy"org.sireum.kekinian::library:${ops.StringOps(Sireum.commitSha).substring(0, 10)}"
            |  )
            |  def repositoriesTask = T.task { super.repositoriesTask() :+
            |    coursier.maven.MavenRepository("${(Os.home / ".m2" / "repository").toUri}") :+
            |    coursier.maven.MavenRepository("https://jitpack.io")
            |  }
            |}""".render
      )
      bloopGen()
      (root / ".sireum.ver").writeOver(Sireum.commitSha)
      return 0
    }

    val (help, code, _, prj, versions) = check(o.json, o.project, Some(1), Some(1), o.args, o.versions, o.slice)
    if (help) {
      println(o.help)
      return code
    } else if (code != 0) {
      return code
    }

    println()

    if (genBloop) {
      millGen(prj, versions)
      bloopGen()
    } else if (genMill) {
      millGen(prj, versions)
    }
    println("Done!")
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
        st"""::#! 2> /dev/null                                     #
            |@ 2>/dev/null # 2>nul & echo off & goto BOF           #
            |if [ -z "$${SIREUM_HOME}" ]; then                      #
            |  echo "Please set SIREUM_HOME env var"               #
            |  exit -1                                             #
            |fi                                                    #
            |exec "$${SIREUM_HOME}/bin/sireum" slang run "$$0" "$$@"  #
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
      cacheOpt = o.cache.map((p: String) => Os.path(p)),
      proxy = Coursier.Proxy(hostOpt = o.proxyHost, nonHostsOpt = o.proxyNonHosts, portOpt = o.proxyPort,
        protocolOpt = o.proxyProtocol, protocolUserEnvKeyOpt = o.proxyUser, protocolPasswordEnvKeyOpt = o.proxyPassword)
    )

    val configPath: Os.Path = o.edition match {
      case Cli.SireumProyekIveEdition.Server =>
        SireumApi.init.ideaConfig(F, SireumApi.isDev, F, Some(path))
      case _ =>
        var ideaConfigPath = (ideaDir / "bin" / "idea.properties").properties.get("idea.config.path").get
        ideaConfigPath = ops.StringOps(ideaConfigPath).replaceAllLiterally("${idea.home.path}", ideaDir.canon.toUri)
        ideaConfigPath = ops.StringOps(ideaConfigPath).replaceAllLiterally("${user.home}", Os.home.toUri)
        Os.Path.fromUri(ideaConfigPath).canon
    }

    val r = proyek.Ive.run(
      path = path,
      project = prj,
      projectName = projectName,
      dm = dm,
      outDirName = o.outputDirName.get,
      ideaDir = ideaDir.canon,
      isDev = SireumApi.isDev,
      force = o.force,
      isCommunity = o.edition == Cli.SireumProyekIveEdition.Community,
      javacOptions = o.javac,
      scalacOptions = o.scalac,
      configPath = configPath,
      sandboxPath = SireumApi.init.ideaSandbox(SireumApi.isDev)
    )

    return r
  }

  def slangCheck(o: Cli.SireumProyekSlangcheckOption, reporter: org.sireum.logika.Logika.Reporter): Z = {

    val (help, code, path, prj, versions) = org.sireum.cli.Proyek.check(o.json, o.project, Some(1), None(), o.args, o.versions, o.slice)
    if (help) {
      println(o.help)
      return code
    } else if (code != 0) {
      return code
    }

    if (o.args.size < 2) {
      eprintln(st"Unexpected command line arguments: ${(ops.ISZOps(o.args).drop(1), " ")}".render)
      return org.sireum.cli.Proyek.INVALID_ARGS
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
      cacheOpt = o.cache.map((p: String) => Os.path(p)),
      proxy = Coursier.Proxy(hostOpt = o.proxyHost, nonHostsOpt = o.proxyNonHosts, portOpt = o.proxyPort,
        protocolOpt = o.proxyProtocol, protocolUserEnvKeyOpt = o.proxyUser, protocolPasswordEnvKeyOpt = o.proxyPassword)
    )

    val config = org.sireum.logika.Config(
      smt2Configs = ISZ(),
      parCores = 1,
      sat = F,
      rlimit = 1000000,
      timeoutInMs = 2000,
      charBitWidth = 32,
      intBitWidth = 0,
      useReal = F,
      logPc = F,
      logRawPc = F,
      logVc = F,
      logVcDirOpt = None(),
      dontSplitPfq = F,
      splitAll = F,
      splitContract = F,
      splitIf = F,
      splitMatch = F,
      simplifiedQuery = F,
      checkInfeasiblePatternMatch = T,
      fpRoundingMode = "RNE",
      smt2Seq = F,
      branchPar = org.sireum.logika.Config.BranchPar.All,
      atLinesFresh = F,
      interp = F,
      loopBound = 3,
      callBound = 3,
      interpContracts = F,
      elideEncoding = F,
      rawInscription = F,
      smt2Caching = F,
      strictPureMode = StrictPureMode.Default,
      transitionCache = F,
      patternExhaustive = F,
      pureFun = F,
      detailedInfo = F,
      satTimeout = F,
      isAuto = F,
      background = Config.BackgroundMode.Disabled,
      atRewrite = F,
      searchPc = F,
      rwTrace = T,
      rwMax = 100,
      rwPar = T,
      rwEvalTrace = T,
      branchParPredNum = 3,
      branchParPredComp = 16
//      undefined = F,
//      useInt = F,
//      branchSat = F
    )
    val mbox: MBox2[HashMap[String, HashMap[String, org.sireum.lang.FrontEnd.Input]], HashMap[String, TypeHierarchy]] = MBox2(HashMap.empty, HashMap.empty)
    val lcode = org.sireum.proyek.Analysis.run(
      root = path,
      outDirName = "out",
      project = prj,
      dm = dm,
      cacheInput = F,
      cacheTypeHierarchy = F,
      mapBox = mbox,
      config = config,
      cache = org.sireum.logika.NoTransitionSmt2Cache.create,
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
    )._1

    if (reporter.hasIssue) {
      println()
      reporter.printMessages()
    } else if (lcode == 0) {
      println()
      println("Programs are well-typed!")

      val outputDir = Os.path(o.outputDir.get) // outputDir has a cli default of '.'

      val files: ISZ[Os.Path] = for (arg <- ops.ISZOps(o.args).drop(1)) yield Os.path(arg)
      for (f <- files if !f.exists || !f.isFile) {
        // TODO convert to an error report
        halt(s"$f is not a file")
      }

      // TODO convert this into an error report
      assert(mbox.value2.values.size == 1)


      val packageName: ISZ[String] =
        if (o.packageName.nonEmpty) ops.StringOps(o.packageName.get).split((c: C) => c == '.')
        else ISZ()
      val srcFiles: ISZ[String] = for (source <- files) yield source.toUri
      val genFiles = org.sireum.tools.SlangCheck.gen(packageName, srcFiles, ISZ(), reporter, mbox.value2.values(0))

      if (!reporter.hasError) {
        for (f <- genFiles) {
          val output = outputDir /+ f._1
          output.writeOver(f._2.render)
          println(s"Wrote: $output")
        }

        /* TODO complete this
        if (o.testDir.nonEmpty) { // testDir does not have a cli default value so need to check for empty
          val testDir = Os.path(o.testDir.get)
          val testFiles = SlangCheckTest.gen(packageName, srcFiles, reporter, mbox.value2.values(0))
          for (f <- testFiles) {
            val output = testDir /+ packageName /+ f._1
            output.writeOver(f._2.render)
            println(s"Wrote: $output")
          }
        }
        */
      }
      return if (reporter.hasError) 1 else 0
    }

    return if (lcode === 0) 0 else org.sireum.cli.Proyek.ILL_FORMED_PROGRAMS
  }

  def logika(o: Cli.SireumProyekLogikaOption, reporter: org.sireum.logika.Logika.Reporter): Z = {
    val start = extension.Time.currentMillis
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
          "z3" ~> st"z3$exeOpt".render
        )
    }

    val smt2Configs =
      Smt2.parseConfigs(nameExePathMap, F, o.smt2ValidConfigs.get).left ++
        Smt2.parseConfigs(nameExePathMap, T, o.smt2SatConfigs.get).left

    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = F,
      withSource = F,
      withDoc = F,
      javaHome = SireumApi.javaHomeOpt.get,
      scalaHome = SireumApi.scalaHomeOpt.get,
      sireumHome = SireumApi.homeOpt.get,
      cacheOpt = o.cache.map((p: String) => Os.path(p)),
      proxy = Coursier.Proxy(hostOpt = o.proxyHost, nonHostsOpt = o.proxyNonHosts, portOpt = o.proxyPort,
        protocolOpt = o.proxyProtocol, protocolUserEnvKeyOpt = o.proxyUser, protocolPasswordEnvKeyOpt = o.proxyPassword)
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

    val branchPar: org.sireum.logika.Config.BranchPar.Type = (o.branchPar, o.branchParReturn) match {
      case (T, F) => org.sireum.logika.Config.BranchPar.All
      case (T, T) => org.sireum.logika.Config.BranchPar.OnlyAllReturns
      case (F, F) => org.sireum.logika.Config.BranchPar.Disabled
      case (F, T) => org.sireum.logika.Config.BranchPar.Disabled
    }
    val spMode: org.sireum.logika.Config.StrictPureMode.Type = o.strictPureMode match {
      case Cli.SireumProyekLogikaStrictPureMode.Default => org.sireum.logika.Config.StrictPureMode.Default
      case Cli.SireumProyekLogikaStrictPureMode.Flip => org.sireum.logika.Config.StrictPureMode.Flip
      case Cli.SireumProyekLogikaStrictPureMode.Uninterpreted => org.sireum.logika.Config.StrictPureMode.Uninterpreted
    }

    val config = org.sireum.logika.Config(
      smt2Configs = smt2Configs,
      parCores = parCores,
      sat = o.sat,
      rlimit = o.rlimit,
      timeoutInMs = o.timeout * 1000,
      charBitWidth = o.charBitWidth,
      intBitWidth = o.intBitWidth,
      useReal = o.useReal,
      logPc = o.logPc,
      logRawPc = o.logRawPc,
      logVc = o.logVc,
      logVcDirOpt = o.logVcDir,
      dontSplitPfq = o.dontSplitFunQuant,
      splitAll = o.splitAll,
      splitIf = o.splitIf,
      splitMatch = o.splitMatch,
      splitContract = o.splitContract,
      simplifiedQuery = o.simplify,
      checkInfeasiblePatternMatch = T,
      fpRoundingMode = fpRoundingMode,
      smt2Caching = F,
      smt2Seq = o.sequential,
      branchPar = branchPar,
      atLinesFresh = o.logPcLines,
      interp = o.interprocedural,
      loopBound = o.loopBound,
      callBound = o.callBound,
      interpContracts = o.interproceduralContracts,
      elideEncoding = o.elideEncoding,
      rawInscription = o.rawInscription,
      strictPureMode = spMode,
      transitionCache = F,
      patternExhaustive = o.patternExhaustive,
      pureFun = o.pureFun,
      detailedInfo = o.logDetailedInfo,
      satTimeout = o.satTimeout,
      isAuto = T,
      background = org.sireum.logika.Config.BackgroundMode.Disabled,
      atRewrite = o.logAtRewrite,
      searchPc = o.searchPC,
      rwTrace = o.rwTrace,
      rwMax = o.rwMax,
      rwPar = o.rwPar,
      rwEvalTrace = o.rwEvalTrace,
      branchParPredNum = o.branchPredNum,
      branchParPredComp = o.branchPredComplexity
//      undefined = F,
//      useInt = F,
//      branchSat = F
    )

    val (lcode, vtime) = Analysis.run(
      root = path,
      outDirName = o.outputDirName.get,
      project = prj,
      dm = dm,
      cacheInput = F,
      cacheTypeHierarchy = F,
      mapBox = MBox2(HashMap.empty, HashMap.empty),
      config = config,
      cache = org.sireum.logika.NoTransitionSmt2Cache.create,
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
      plugins = org.sireum.logika.Logika.defaultPlugins ++
        (if (o.infoFlow) org.sireum.logika.infoflow.InfoFlowPlugins.defaultPlugins else ISZ[org.sireum.logika.plugin.Plugin]()),
      skipMethods = o.skipMethods,
      skipTypes = o.skipTypes,
      reporter = reporter
    )
    reporter.printMessages()

    if (!(reporter.numOfSats == 0 && reporter.numOfVCs == 0)) {
      println()
      println(st"Number of SMT2 verification condition checking: ${reporter.numOfVCs} (time: ${Smt2Formatter.formatTime(reporter.vcMillis)})".render)
      println(st"Number of SMT2 satisfiability checking: ${reporter.numOfSats} (time: ${Smt2Formatter.formatTime(reporter.satMillis)})".render)
    }

    println()
    if (lcode == 0) {
      println(st"Logika verified! Verification time: ${Smt2Formatter.formatTime(vtime)}, Elapsed time: ${Smt2Formatter.formatTime(extension.Time.currentMillis - start)}".render)
      return 0
    } else {
      println()
      println(st"Elapsed time: ${Smt2Formatter.formatTime(extension.Time.currentMillis - start)}".render)
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
    val dms: MSZ[project.DependencyManager] = for (isJs <- targets) yield project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = isJs,
      withSource = F,
      withDoc = F,
      javaHome = SireumApi.javaHomeOpt.get,
      scalaHome = SireumApi.scalaHomeOpt.get,
      sireumHome = SireumApi.homeOpt.get,
      cacheOpt = o.cache.map((p: String) => Os.path(p)),
      proxy = Coursier.Proxy(hostOpt = o.proxyHost, nonHostsOpt = o.proxyNonHosts, portOpt = o.proxyPort,
        protocolOpt = o.proxyProtocol, protocolUserEnvKeyOpt = o.proxyUser, protocolPasswordEnvKeyOpt = o.proxyPassword)
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
          genSemanticsDB = F,
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

  def reflectGen(o: Cli.SireumProyekReflectOption, reporter: org.sireum.logika.Logika.Reporter): Z = {
    val (help, code, path, prj, versions) = check(o.json, o.project, Some(1), None(), o.args, o.versions, o.slice)
    if (help) {
      println(o.help)
      return code
    } else if (code != 0) {
      return code
    }

    val mbox2 = MBox2(HashMap.empty[String, HashMap[String, lang.FrontEnd.Input]], HashMap.empty[String, TypeHierarchy])
    val exitCode = tipeH(Cli.SireumProyekTipeOption(
     help = o.help,
     args = o.args,
     par = o.par,
     strictAliasing = o.strictAliasing,
     verbose = F,
     ignoreRuntime = o.ignoreRuntime,
     json = None(),
     name = None(),
     outputDirName = None(),
     project = o.project,
     slice = o.slice,
     symlink = o.symlink,
     versions = o.versions), path, prj, versions, mbox2, reporter)
    if (exitCode != 0) {
      return exitCode
    }

    println()
    
    val mth = mbox2.value2
    var nameMap: NameMap = HashSMap.empty
    var typeMap: TypeMap = HashSMap.empty
    for (m <- prj.modules.values if prj.poset.childrenOf(m.id).isEmpty) {
      val th = mth.get(m.id).get
      if (nameMap.isEmpty) {
        nameMap = th.nameMap
      } else {
        nameMap = nameMap ++ th.nameMap.entries
      }
      if (typeMap.isEmpty) {
        typeMap = th.typeMap
      } else {
        typeMap = typeMap ++ th.typeMap.entries
      }
    }

    proyek.Reflect.gen(o.packageName, o.className.get, o.output.map((p: String) => Os.path(p)), nameMap, typeMap,
      HashSet ++ (for (p <- o.includePackages) yield ops.StringOps(p).split((c: C) => c == '.')),
      HashSet ++ (for (p <- o.excludePackages) yield ops.StringOps(p).split((c: C) => c == '.')),
      HashSet ++ (for (p <- o.include) yield ops.StringOps(p).split((c: C) => c == '.')),
      HashSet ++ (for (p <- o.exclude) yield ops.StringOps(p).split((c: C) => c == '.')),
      o.license.map((l: String) => Os.path(l).read))
    reporter.printMessages()

    return if (reporter.hasError) FAILED_REFLECT_GEN else 0
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
      cacheOpt = o.cache.map((p: String) => Os.path(p)),
      proxy = Coursier.Proxy(hostOpt = o.proxyHost, nonHostsOpt = o.proxyNonHosts, portOpt = o.proxyPort,
        protocolOpt = o.proxyProtocol, protocolUserEnvKeyOpt = o.proxyUser, protocolPasswordEnvKeyOpt = o.proxyPassword)
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
        genSemanticsDB = F,
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
      cacheOpt = o.cache.map((p: String) => Os.path(p)),
      proxy = Coursier.Proxy(hostOpt = o.proxyHost, nonHostsOpt = o.proxyNonHosts, portOpt = o.proxyPort,
        protocolOpt = o.proxyProtocol, protocolUserEnvKeyOpt = o.proxyUser, protocolPasswordEnvKeyOpt = o.proxyPassword)
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
      cacheOpt = o.cache.map((p: String) => Os.path(p)),
      proxy = Coursier.Proxy(hostOpt = o.proxyHost, nonHostsOpt = o.proxyNonHosts, portOpt = o.proxyPort,
        protocolOpt = o.proxyProtocol, protocolUserEnvKeyOpt = o.proxyUser, protocolPasswordEnvKeyOpt = o.proxyPassword)
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
        genSemanticsDB = F,
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
      names = ops.ISZOps(o.args).drop(1),
      coverageOpt = o.coverage
    )

    return r
  }

  def tipeH(o: Cli.SireumProyekTipeOption, path: Os.Path, prj: project.Project, versions: HashSMap[String, String],
            mapBox: MBox2[HashMap[String, HashMap[String, lang.FrontEnd.Input]], HashMap[String, TypeHierarchy]],
            reporter: org.sireum.logika.Logika.Reporter): Z = {
    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = F,
      withSource = F,
      withDoc = F,
      javaHome = SireumApi.javaHomeOpt.get,
      scalaHome = SireumApi.scalaHomeOpt.get,
      sireumHome = SireumApi.homeOpt.get,
      cacheOpt = None(),
      proxy = Coursier.Proxy.empty
    )

    val config = org.sireum.logika.Config(
      smt2Configs = ISZ(),
      parCores = 1,
      sat = F,
      rlimit = 1000000,
      timeoutInMs = 2000,
      charBitWidth = 32,
      intBitWidth = 0,
      useReal = F,
      logPc = F,
      logRawPc = F,
      logVc = F,
      logVcDirOpt = None(),
      dontSplitPfq = F,
      splitAll = F,
      splitContract = F,
      splitIf = F,
      splitMatch = F,
      simplifiedQuery = F,
      checkInfeasiblePatternMatch = T,
      fpRoundingMode = "RNE",
      smt2Caching = F,
      smt2Seq = F,
      branchPar = org.sireum.logika.Config.BranchPar.All,
      atLinesFresh = F,
      interp = F,
      loopBound = 3,
      callBound = 3,
      interpContracts = F,
      elideEncoding = F,
      rawInscription = F,
      strictPureMode = org.sireum.logika.Config.StrictPureMode.Default,
      transitionCache = F,
      patternExhaustive = F,
      pureFun = F,
      detailedInfo = F,
      satTimeout = F,
      isAuto = T,
      background = org.sireum.logika.Config.BackgroundMode.Disabled,
      atRewrite = T,
      searchPc = F,
      rwTrace = T,
      rwMax = 100,
      rwPar = T,
      rwEvalTrace = T,
      branchParPredNum = 3,
      branchParPredComp = 16
//      undefined = F,
//      useInt = F,
//      branchSat = F
    )
    val lcode = Analysis.run(
      root = path,
      outDirName = "out",
      project = prj,
      dm = dm,
      cacheInput = F,
      cacheTypeHierarchy = F,
      mapBox = mapBox,
      config = config,
      cache = org.sireum.logika.NoTransitionSmt2Cache.create,
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
    )._1

    if (reporter.hasIssue) {
      println()
      reporter.printMessages()
    } else if (lcode == 0) {
      println()
      println("Programs are well-typed!")
    }

    return if (lcode === 0) 0 else ILL_FORMED_PROGRAMS

  }

  def tipe(o: Cli.SireumProyekTipeOption,
           mapBox: MBox2[HashMap[String, HashMap[String, lang.FrontEnd.Input]], HashMap[String, TypeHierarchy]],
           reporter: org.sireum.logika.Logika.Reporter): Z = {
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

    return tipeH(o, path, prj, versions, mapBox, reporter)
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
