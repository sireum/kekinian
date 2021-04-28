// #Sireum
/*
 Copyright (c) 2021, Robby, Kansas State University
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
import org.sireum.project.DependencyManager

object Proyek {
  val HOME_NOT_FOUND: Z = -1
  val JAVA_OR_SCALA_NOT_FOUND: Z = -2
  val IDEA_NOT_FOUND: Z = -3
  val INVALID_ARGS: Z = -4
  val INVALID_PATH_ARG: Z = -5
  val INVALID_PROJECT: Z = -6
  val INVALID_VERSIONS: Z = -7
  val INVALID_PUBLISH_DEP: Z = -8

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

    prj = getProject(path, jsonOpt, projectOpt) match {
      case Some(pr) => pr.slice(slice)
      case _ => return ret(INVALID_PROJECT)
    }

    if (versionFiles.nonEmpty) {
      println()
      println("Loading version dependencies ...")
    }

    versions = getVersions(prj, path, versionFiles) match {
      case Some(vs) => vs
      case _ => return ret(INVALID_VERSIONS)
    }

    val buildCmd = SireumApi.homeOpt.get / "bin" / "build.cmd"
    val runtimeVerOpt = versions.get(DependencyManager.libraryKey)
    if (buildCmd.exists && runtimeVerOpt.nonEmpty && runtimeVerOpt == SireumApi.versions.get(DependencyManager.libraryKey)) {
      if (!Coursier.isRuntimePublishedLocally(runtimeVerOpt.get)) {
        println()
        println("Publishing Slang runtime library locally ...")
        proc"$buildCmd m2-lib".console.runCheck()
      }
    }

    return ret(0)
  }

  def assemble(o: Cli.AssembleOption): Z = {
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
      r = proyek.Proyek.compile(
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
        par = o.par,
        sha3 = o.sha3,
        ignoreRuntime = o.ignoreRuntime,
        recompileModuleIds = o.recompile
      )
    }

    if (r != 0) {
      return r
    }

    val projectName = o.name.getOrElse(path.canon.name)

    r = proyek.Proyek.assemble(
      path = path,
      outDirName = o.outputDirName.get,
      project = prj,
      projectName = projectName,
      jarName = o.jar.getOrElse(projectName),
      dm = dm,
      mainClassNameOpt = o.mainClass
    )

    return r
  }

  def compile(o: Cli.CompileOption): Z = {
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

    val r = proyek.Proyek.compile(
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
      par = o.par,
      sha3 = o.sha3,
      ignoreRuntime = o.ignoreRuntime,
      recompileModuleIds = o.recompile
    )

    return r
  }

  def ive(o: Cli.IveOption): Z = {
    val (help, code, path, prj, versions) = check(o.json, o.project, Some(1), Some(1), o.args, o.versions, o.slice)
    if (help) {
      println(o.help)
      return code
    } else if (code != 0) {
      return code
    }

    if (o.ultimate) {
      if (!SireumApi.ideaUltimateDir.exists) {
        eprintln("Sireum IVE Ultimate is not installed")
        return IDEA_NOT_FOUND
      }
    } else {
      if (!SireumApi.ideaDir.exists) {
        eprintln("Sireum IVE is not installed")
        return IDEA_NOT_FOUND
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

    val r = proyek.Proyek.ive(
      path = path,
      project = prj,
      projectName = o.name.getOrElse(path.canon.name),
      dm = dm,
      outDirName = o.outputDirName.get,
      jbrVersion = SireumApi.jbrVer,
      ideaDir = if (o.ultimate) SireumApi.ideaUltimateDir else SireumApi.ideaDir,
      isUltimate = o.ultimate,
      isDev = o.ultimate || SireumApi.isDev,
      force = o.force
    )

    return r
  }

  def publish(o: Cli.PublishOption): Z = {
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

    val orgName = ops.StringOps(o.args(1)).split((c: C) => c === '.')

    val publishModuleIds: ISZ[String] = for (m <- prj.modules.values if m.publishInfoOpt.nonEmpty) yield m.id
    for (m <- prj.modules.values) {
      val diff = m.deps -- publishModuleIds
      if (diff.nonEmpty) {
        eprintln(st"Publishable module ${m.id} depends on non-publishable module(s): ${(diff, ", ")}".render)
        return INVALID_PUBLISH_DEP
      }
    }

    println()

    val dms: MSZ[project.DependencyManager] = for (isJs <- MSZ(F, T)) yield project.DependencyManager(
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
        r = proyek.Proyek.compile(
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
          par = o.par,
          sha3 = o.sha3,
          ignoreRuntime = o.ignoreRuntime,
          recompileModuleIds = o.recompile
        )
      }
    }

    for (i <- 0 until dms.size if r == 0) {
      println(s"Publishing for the ${if (dms(i).isJs) "Javascript" else "JVM"} target ...")
      println()
      r = proyek.Proyek.publish(
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

  def run(o: Cli.RunOption): Z = {
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
      r = proyek.Proyek.compile(
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
        par = o.par,
        sha3 = o.sha3,
        ignoreRuntime = o.ignoreRuntime,
        recompileModuleIds = o.recompile
      )
    }

    if (r != 0) {
      return r
    }

    r = proyek.Proyek.run(
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

  def test(o: Cli.TestOption): Z = {
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
      r = proyek.Proyek.compile(
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
        par = o.par,
        sha3 = o.sha3,
        ignoreRuntime = o.ignoreRuntime,
        recompileModuleIds = o.recompile
      )
    }

    if (r != 0) {
      return r
    }

    r = proyek.Proyek.test(
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

  def getPath(args: ISZ[String]): Option[Os.Path] = {
    val p = args(0)
    val d = Os.path(p)
    if (d.exists && !d.isDir) {
      eprintln(s"$p is not a directory")
      return None()
    }
    return Some(d.canon)
  }

  def getProject(path: Os.Path, jsonOpt: Option[String], projectOpt: Option[String]): Option[project.Project] = {
    var prj = project.Project.empty
    var loaded = F

    {
      jsonOpt match {
        case Some(p) =>
          val f = Os.path(p)
          if (!f.isFile) {
            eprintln(s"$p is not a file")
            return None()
          }
          project.JSON.toProject(f.read) match {
            case Either.Left(pr) =>
              prj = pr
              loaded = T
            case _ =>
              eprintln(s"Ill-formed JSON project file $p")
              return None()
          }
          println()
        case _ =>
      }
    }

    if (!loaded) {
      val f: Os.Path = projectOpt match {
        case Some(p) => Os.path(p)
        case _ => path / "bin" / "project.cmd"
      }
      if (!f.isFile) {
        eprintln(s"$f is not a file")
        return None()
      }
      if (f.ext =!= "cmd") {
        eprintln(s"$f is not a .cmd Slash script file")
        return None()
      }
      val r = proc"$f json".env(ISZ("SIREUM_HOME" ~> SireumApi.homeOpt.get.string)).
        console.outLineAction((s: String) => project.ProjectUtil.projectJsonLine(s).isEmpty).run()
      if (r.ok) {
        project.ProjectUtil.projectJsonLine(r.out) match {
          case Some(line) =>
            project.JSON.toProject(line) match {
              case Either.Left(pr) =>
                prj = pr
              case _ =>
                eprintln(s"Ill-defined project file $f")
                return None()
            }
          case _ =>
            eprintln(s"Failed to load project from $f")
            return None()
        }
      } else {
        eprintln(s"Failed to load project from $f")
        return None()
      }
    }

    val openDeps = prj.openDeps
    if (openDeps.nonEmpty) {
      for (openDep <- openDeps.entries) {
        val (mid, deps) = openDep
        eprintln(st"Module $mid depends on undefined modules: ${(deps, ", ")}".render)
      }
      return None()
    }

    val illTargets = prj.illTargets
    if (illTargets.nonEmpty) {
      for (illTarget <- illTargets.entries) {
        val mid = illTarget._1
        for (illTargetDep <- illTarget._2.entries) {
          val (mDep, targets) = illTargetDep
          eprintln(st"Module $mid depends on undefined target(s) of module $mDep: ${(targets, ", ")}".render)
        }
      }
      return None()
    }

    return Some(prj)
  }

  def getVersions(prj: project.Project, path: Os.Path, versions: ISZ[String]): Option[HashSMap[String, String]] = {
    val files: ISZ[Os.Path] = if (versions.nonEmpty) {
      for (v <- versions) yield Os.path(v)
    } else {
      val f = path / "versions.properties"
      if (f.exists) ISZ(f) else ISZ()
    }

    var props = HashSMap.empty[String, String]
    props = props ++ SireumApi.versions.entries
    for (f <- files) {
      if (!f.isFile) {
        eprintln(s"$f is not a file")
        return None()
      } else {
        props = props ++ (for (p <- f.properties.entries) yield (ops.StringOps(p._1).replaceAllChars('%', ':'), p._2))
      }
    }

    var useRuntimeLibrary = F
    for (m <- prj.modules.values; ivyDep <- m.ivyDeps if ivyDep == DependencyManager.libraryKey) {
      useRuntimeLibrary = T
    }
    if (!useRuntimeLibrary) {
      props = props -- ISZ(DependencyManager.libraryKey)
    }

    return Some(props)
  }

}
