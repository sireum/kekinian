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

  def runCompile(o: Cli.CompileOption): Z = {
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

    val (scalaVersion, versions): (String, Map[String, String]) = getVersions(path, o.versions) match {
      case Some((v, vs)) => (v, vs)
      case _ => return INVALID_VERSIONS
    }

    println()

    val oldScalaVersion = Coursier.scalaVersion
    Coursier.setScalaVersion(scalaVersion)

    val r =  proyek.Proyek.compile(
      path = path,
      outDirName = o.outputDirName.get,
      project = prj,
      versions = versions,
      projectName = o.name.getOrElse(path.canon.name),
      javaHome = Sireum.javaHomeOpt.get,
      scalaHome = Sireum.scalaHomeOpt.get,
      scalacPlugin = Sireum.scalacPluginJar,
      followSymLink = o.symlink,
      fresh = o.fresh,
      par = o.par,
      sha3 = o.sha3
    )

    Coursier.setScalaVersion(oldScalaVersion)

    return r
  }

  def runIve(o: Cli.IveOption): Z = {
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


    val (scalaVersion, versions): (String, Map[String, String]) = getVersions(path, o.versions) match {
      case Some((v, vs)) => (v, vs)
      case _ => return INVALID_VERSIONS
    }

    println()

    val oldScalaVersion = Coursier.scalaVersion
    Coursier.setScalaVersion(scalaVersion)

    val r = proyek.Proyek.ive(
      path = path,
      project = prj,
      versions = versions,
      projectName = o.name.getOrElse(path.canon.name),
      withSource = o.sources,
      withDoc = o.docs,
      outDirName = o.outputDirName.get,
      scalacPlugin = Sireum.scalacPluginJar,
      scalaVersion = scalaVersion,
      scalaHome = Sireum.scalaHomeOpt.get,
      sireumJar = Sireum.sireumJar,
      javaHome = Sireum.javaHomeOpt.get,
      javaVersion = Sireum.javaVer,
      jbrVersion = Sireum.jbrVer,
      ideaDir = Sireum.ideaDir,
      isDev = Sireum.isDev,
      force = o.force
    )

    Coursier.setScalaVersion(oldScalaVersion)

    return r
  }

  def checkRequirements(jsonOpt: Option[String], projectOpt: Option[String]): Z = {
    if (!Sireum.homeFound) return HOME_NOT_FOUND
    if (!(Sireum.javaFound && Sireum.scalaFound)) return JAVA_OR_SCALA_NOT_FOUND
    if (!Sireum.ideaDir.exists) {
      eprintln("Sireum IVE is not installed")
      return IDEA_NOT_FOUND
    }

    if (jsonOpt.nonEmpty && projectOpt.nonEmpty) {
      eprintln("Cannot specify both the 'json' and the 'project' options")
      return INVALID_PROJECT
    }

    return 0
  }

  def getPath(args: ISZ[String], help: String): (B, Option[Os.Path]) = {
    args match {
      case ISZ(p) =>
        val d = Os.path(p)
        if (d.exists && !d.isDir) {
          eprintln(s"$p is not a directory")
          return (F, None())
        }
        return (T, Some(d.canon))
      case ISZ() =>
        println(help)
        return (T, None())
      case _ =>
        eprintln(s"Expecting at most one argument, but found $args")
        println(help)
        return (F, None())
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
    return Some(prj)
  }

  def getVersions(path: Os.Path, versionsOpt: Option[String]): Option[(String, Map[String, String])] = {
    val versionsFile: Os.Path = versionsOpt match {
      case Some(p) => Os.path(p)
      case _ => (path / "versions.properties")
    }

    if (!versionsFile.isFile) {
      eprintln(s"$versionsFile is not a file")
      return None()
    }

    val props = versionsFile.properties
    props.get("org.scala-lang%scala-library%") match {
      case Some(v) =>
        return Some((v, props))
      case _ =>
        eprintln(s"Could not find Scala library version in $versionsFile")
        return None()
    }
  }
}
