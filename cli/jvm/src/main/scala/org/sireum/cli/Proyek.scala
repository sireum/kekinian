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

  def run(o: Cli.IveOption): Z = {
    if (!Sireum.homeFound) return HOME_NOT_FOUND
    if (!(Sireum.javaFound && Sireum.scalaFound)) return JAVA_OR_SCALA_NOT_FOUND
    if (!Sireum.ideaDir.exists) {
      eprintln("Sireum IVE is not installed")
      return IDEA_NOT_FOUND
    }

    val path: Os.Path = o.args match {
      case ISZ(p) =>
        val d = Os.path(p)
        if (d.exists && !d.isDir) {
          eprintln(s"$p is not a directory")
          return INVALID_PATH_ARG
        }
        d
      case ISZ() =>
        println(o.help)
        return 0
      case _ =>
        eprintln(s"Expecting at most one argument, but found ${o.args}")
        println(o.help)
        return INVALID_PATH_ARG
    }

    if (o.json.nonEmpty && o.project.nonEmpty) {
      eprintln("Cannot specify both the 'json' and the 'project' options")
      return INVALID_PROJECT
    }

    var prj = project.Project.empty
    var loaded = F
    ;{
      o.json match {
        case Some(p) =>
          val f = Os.path(p)
          if (!f.isFile) {
            eprintln(s"$p is not a file")
            return INVALID_PROJECT
          }
          project.JSON.toProject(f.read) match {
            case Either.Left(pr) =>
              prj = pr
              loaded = T
            case _ =>
              eprintln(s"Ill-formed JSON project file $p")
              return INVALID_PROJECT
          }
          println()
        case _ =>
      }
    }
    if (!loaded) {
      val f: Os.Path = o.project match {
        case Some(p) => Os.path(p)
        case _ => path / "bin" / "project.cmd"
      }
      if (!f.isFile) {
        eprintln(s"$f is not a file")
        return INVALID_PROJECT
      }
      if (f.ext =!= "cmd") {
        eprintln(s"$f is not a .cmd Slash script file")
        return INVALID_PROJECT
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
                return INVALID_PROJECT
            }
          case _ =>
            eprintln(s"Failed to load project from $f")
            return INVALID_PROJECT
        }
      } else {
        eprintln(s"Failed to load project from $f")
        return INVALID_PROJECT
      }
    }

    val versionsFile: Os.Path = o.versions match {
      case Some(p) => Os.path(p)
      case _ => path / "versions.properties"
    }

    if (!versionsFile.isFile) {
      eprintln(s"$versionsFile is not a file")
      return INVALID_VERSIONS
    }

    val versions = versionsFile.properties

    return org.sireum.proyek.Proyek.ive(
      path = path,
      project = prj,
      versions = versions,
      projectName = o.name.getOrElse(path.canon.name),
      withSource = o.sources,
      withDoc = o.docs,
      outDirName = o.outputDirName.get,
      scalacPlugin = Sireum.scalacPluginJar,
      scalaVersion = Sireum.scalaVer,
      scalaHome = Sireum.scalaHomeOpt.get,
      sireumJar = Sireum.sireumJar,
      javaHome = Sireum.javaHomeOpt.get,
      javaVersion = Sireum.javaVer,
      jbrVersion = Sireum.jbrVer,
      ideaDir = Sireum.ideaDir,
      isDev = Sireum.isDev,
      force = o.force
    )
  }
}
