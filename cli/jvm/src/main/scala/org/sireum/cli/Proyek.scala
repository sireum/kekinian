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

object Proyek {
  val HOME_NOT_FOUND: Z = -1
  val JAVA_OR_SCALA_NOT_FOUND: Z = -2
  val IDEA_NOT_FOUND: Z = -3
  val INVALID_PATH_ARG: Z = -4
  val INVALID_PROJECT: Z = -5
  val INVALID_VERSIONS: Z = -6
  val INVALID_PUBLISH_DEP: Z = -7

  def assemble(o: Cli.AssembleOption): Z = {
    val code = checkRequirements(o.json, o.project)
    if (code != 0) {
      return code
    }

    if (o.args.size > 1) {
      eprintln(s"Expecting only at most one argument, but found ${o.args.size}")
      return INVALID_PATH_ARG
    }

    val path: Os.Path = getPath(o.args, o.help) match {
      case (T, Some(p)) => p
      case (T, None()) => return 0
      case (_, _) => return INVALID_PATH_ARG
    }

    val prj: project.Project = getProject(path, o.json, o.project) match {
      case Some(pr) => pr
      case _ => return INVALID_PROJECT
    }

    val versions: HashSMap[String, String] = getVersions(path, o.versions) match {
      case Some(vs) => vs
      case _ => return INVALID_VERSIONS
    }

    println()

    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = F,
      withSource = F,
      withDoc = F,
      javaHome = Sireum.javaHomeOpt.get,
      scalaHome = Sireum.scalaHomeOpt.get,
      sireumJar = Sireum.sireumJar,
      scalacPlugin = Sireum.scalacPluginJar,
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
        isJs = F,
        followSymLink = o.symlink,
        fresh = o.fresh,
        par = o.par,
        sha3 = o.sha3
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
    val code = checkRequirements(o.json, o.project)
    if (code != 0) {
      return code
    }

    if (o.args.size > 1) {
      eprintln(s"Expecting only at most one argument, but found ${o.args.size}")
      return INVALID_PATH_ARG
    }

    val path: Os.Path = getPath(o.args, o.help) match {
      case (T, Some(p)) => p
      case (T, None()) => return 0
      case (_, _) => return INVALID_PATH_ARG
    }

    val prj: project.Project = getProject(path, o.json, o.project) match {
      case Some(pr) => pr
      case _ => return INVALID_PROJECT
    }

    val versions: HashSMap[String, String] = getVersions(path, o.versions) match {
      case Some(vs) => vs
      case _ => return INVALID_VERSIONS
    }

    println()

    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = o.js,
      withSource = F,
      withDoc = F,
      javaHome = Sireum.javaHomeOpt.get,
      scalaHome = Sireum.scalaHomeOpt.get,
      sireumJar = Sireum.sireumJar,
      scalacPlugin = Sireum.scalacPluginJar,
      cacheOpt = o.cache.map((p: String) => Os.path(p))
    )

    val r =  proyek.Proyek.compile(
      path = path,
      outDirName = o.outputDirName.get,
      project = prj,
      projectName = o.name.getOrElse(path.canon.name),
      dm = dm,
      isJs = o.js,
      followSymLink = o.symlink,
      fresh = o.fresh,
      par = o.par,
      sha3 = o.sha3
    )

    return r
  }

  def ive(o: Cli.IveOption): Z = {
    val code = checkRequirements(o.json, o.project)
    if (code != 0) {
      return code
    }

    if (o.ultimate) {
      if (!Sireum.ideaUltimateDir.exists) {
        eprintln("Sireum IVE Ultimate is not installed")
        return IDEA_NOT_FOUND
      }
    } else {
      if (!Sireum.ideaDir.exists) {
        eprintln("Sireum IVE is not installed")
        return IDEA_NOT_FOUND
      }
    }

    if (o.args.size > 1) {
      eprintln(s"Expecting only at most one argument, but found ${o.args.size}")
      return INVALID_PATH_ARG
    }

    val path: Os.Path = getPath(o.args, o.help) match {
      case (T, Some(p)) => p
      case (T, None()) => return 0
      case (_, _) => return INVALID_PATH_ARG
    }

    val prj: project.Project = getProject(path, o.json, o.project) match {
      case Some(pr) => pr
      case _ => return INVALID_PROJECT
    }

    val versions: HashSMap[String, String] = getVersions(path, o.versions) match {
      case Some(vs) => vs
      case _ => return INVALID_VERSIONS
    }

    println()

    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = F,
      withSource = o.sources,
      withDoc = o.docs,
      javaHome = Sireum.javaHomeOpt.get,
      scalaHome = Sireum.scalaHomeOpt.get,
      sireumJar = Sireum.sireumJar,
      scalacPlugin = Sireum.scalacPluginJar,
      cacheOpt = o.cache.map((p: String) => Os.path(p))
    )

    val r = proyek.Proyek.ive(
      path = path,
      project = prj,
      projectName = o.name.getOrElse(path.canon.name),
      dm = dm,
      outDirName = o.outputDirName.get,
      jbrVersion = Sireum.jbrVer,
      ideaDir = if (o.ultimate) Sireum.ideaUltimateDir else Sireum.ideaDir,
      isUltimate = o.ultimate,
      isDev = o.ultimate || Sireum.isDev,
      force = o.force
    )

    return r
  }

  def publish(o: Cli.PublishOption): Z = {
    val code = checkRequirements(o.json, o.project)
    if (code != 0) {
      return code
    }

    if (o.args.size != 2) {
      eprintln(s"Expecting 2 arguments, but found ${o.args.size}")
    }

    val path: Os.Path = getPath(o.args, o.help) match {
      case (T, Some(p)) => p
      case (T, None()) => return 0
      case (_, _) => return INVALID_PATH_ARG
    }

    val prj: project.Project = getProject(path, o.json, o.project) match {
      case Some(pr) => pr
      case _ => return INVALID_PROJECT
    }

    val versions: HashSMap[String, String] = getVersions(path, o.versions) match {
      case Some(vs) => vs
      case _ => return INVALID_VERSIONS
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

    val dms: ISZ[project.DependencyManager] = for (isJs <- ISZ(F, T)) yield project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = isJs,
      withSource = F,
      withDoc = F,
      javaHome = Sireum.javaHomeOpt.get,
      scalaHome = Sireum.scalaHomeOpt.get,
      sireumJar = Sireum.sireumJar,
      scalacPlugin = Sireum.scalacPluginJar,
      cacheOpt = o.cache.map((p: String) => Os.path(p))
    )

    var r: Z = 0

    if (!o.skipCompile) {
      for (dm<- dms if r == 0) {
        println(s"Compiling for ${if (dm.isJs) "Javascript" else "JVM"} target ...")
        println()
        r = proyek.Proyek.compile(
          path = path,
          outDirName = o.outputDirName.get,
          project = prj,
          projectName = o.name.getOrElse(path.canon.name),
          dm = dm,
          isJs = dm.isJs,
          followSymLink = o.symlink,
          fresh = o.fresh,
          par = o.par,
          sha3 = o.sha3
        )
      }
    }

    for (dm <- dms if r == 0) {
      println(s"Publishing for ${if (dm.isJs) "Javascript" else "JVM"} target ...")
      println()
      r = proyek.Proyek.publish(
        path = path,
        outDirName = o.outputDirName.get,
        project = prj,
        projectName = o.name.getOrElse(path.canon.name),
        dm = dm,
        isJs = dm.isJs,
        orgName = orgName,
        m2Repo = m2Repo,
        version = version,
        symlink = o.symlink
      )
    }

    return r
  }

  def test(o: Cli.TestOption): Z = {
    val code = checkRequirements(o.json, o.project)
    if (code != 0) {
      return code
    }

    val path: Os.Path = getPath(o.args, o.help) match {
      case (T, Some(p)) => p
      case (T, None()) => return 0
      case (_, _) => return INVALID_PATH_ARG
    }

    val prj: project.Project = getProject(path, o.json, o.project) match {
      case Some(pr) => pr
      case _ => return INVALID_PROJECT
    }

    val versions: HashSMap[String, String] = getVersions(path, o.versions) match {
      case Some(vs) => vs
      case _ => return INVALID_VERSIONS
    }

    println()

    val dm = project.DependencyManager(
      project = prj,
      versions = versions,
      isJs = F,
      withSource = F,
      withDoc = F,
      javaHome = Sireum.javaHomeOpt.get,
      scalaHome = Sireum.scalaHomeOpt.get,
      sireumJar = Sireum.sireumJar,
      scalacPlugin = Sireum.scalacPluginJar,
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
        isJs = F,
        followSymLink = o.symlink,
        fresh = o.fresh,
        par = o.par,
        sha3 = o.sha3
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
      classNames = o.classes,
      suffixes = o.suffixes,
      packageNames = o.packages,
      names = ops.ISZOps(o.args).drop(1)
    )

    return r
  }

  def checkRequirements(jsonOpt: Option[String], projectOpt: Option[String]): Z = {
    if (!Sireum.homeFound) return HOME_NOT_FOUND
    if (!(Sireum.javaFound && Sireum.scalaFound)) return JAVA_OR_SCALA_NOT_FOUND

    if (jsonOpt.nonEmpty && projectOpt.nonEmpty) {
      eprintln("Cannot specify both the 'json' and the 'project' options")
      return INVALID_PROJECT
    }

    return 0
  }

  def getPath(args: ISZ[String], help: String): (B, Option[Os.Path]) = {
    if (args.size >= 1) {
      val p = args(0)
      val d = Os.path(p)
      if (d.exists && !d.isDir) {
        eprintln(s"$p is not a directory")
        return (F, None())
      }
      return (T, Some(d.canon))
    } else {
      println(help)
      return (T, None())
    }
  }

  def getProject(path: Os.Path, jsonOpt: Option[String], projectOpt: Option[String]): Option[project.Project] = {
    var prj = project.Project.empty
    var loaded = F
    ;{
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
      val r = proc"$f json".console.outLineAction((s: String) => project.ProjectUtil.projectJsonLine(s).isEmpty).run()
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
    val definedModuleIds: ISZ[String] = for (m <- prj.modules.values) yield m.id
    for (m <- prj.modules.values) {
      val diff = m.deps -- definedModuleIds
      if (diff.nonEmpty) {
        eprintln(st"Module ${m.id} depends on undefined modules: ${(diff, ", ")}".render)
        return None()
      }
    }
    return Some(prj)
  }

  def getVersions(path: Os.Path, versions: ISZ[String]): Option[HashSMap[String, String]] = {
    val files: ISZ[Os.Path] = if (versions.nonEmpty) {
      for (v <- versions) yield Os.path(v)
    } else {
      ISZ(path / "versions.properties")
    }

    var props = HashSMap.empty[String, String]
    for (f <- files) {
      if (!f.isFile) {
        eprintln(s"$f is not a file")
        return None()
      } else {
        props = props ++ f.properties.entries
      }
    }
    return Some(props)
  }
}
