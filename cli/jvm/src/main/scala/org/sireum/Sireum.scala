/*
 Copyright (c) 2018, Robby, Kansas State University
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

package org.sireum

import java.io.File

import org.sireum.message.Reporter

object Sireum extends scala.App {

  System.exit(Cli(File.pathSeparatorChar).parseSireum(ISZ(args.toSeq.map(s => s: String): _*), 0) match {
    case Some(o: Cli.SlangTipeOption) => cli.SlangTipe.run(o, Reporter.create)
    case Some(o: Cli.SlangRunOption) => cli.SlangRunner.run(o)
    case Some(o: Cli.CligenOption) => cli.GenTools.cliGen(o)
    case Some(o: Cli.IvegenOption) => cli.GenTools.iveGen(o)
    case Some(o: Cli.SergenOption) => cli.GenTools.serGen(o)
    case Some(o: Cli.TransgenOption) => cli.GenTools.transGen(o)
    case Some(_: Cli.HelpOption) => 0
    case _ => -1
  })

  def paths2files(pathFor: String, paths: ISZ[String], checkExist: B): Seq[Os.Path] = {
    var r = Vector[Os.Path]()
    for (p <- paths) {
      r = r :+ paths2fileOpt(pathFor, ISZ(p), checkExist).get
    }
    r
  }

  def paths2fileOpt(pathFor: String, path: ISZ[String], checkExist: B): scala.Option[Os.Path] = {
    path.size match {
      case z"0" => scala.None
      case z"1" =>
        val f = Os.path(path(0).value).canon
        if (checkExist && !f.exists) error(s"File ${path(0)} does not exist.")
        return scala.Some(f)
      case _ =>
        error(s"Expecting a path for $pathFor, but found multiple.")
    }
  }

  def path2fileOpt(pathFor: String, path: Option[String], checkExist: B): scala.Option[Os.Path] = {
    if (path.isEmpty) return scala.None
    val f = Os.path(path.get.value).canon
    if (checkExist && !f.exists) error(s"File '${path.get}' does not exist.")
    return scala.Some(f)
  }

  def error(msg: Predef.String): Nothing = {
    throw new RuntimeException(msg)
  }

  lazy val platform: Predef.String =
    Os.kind match {
      case Os.Kind.Win => "win"
      case Os.Kind.Linux => "linux"
      case Os.Kind.Mac => "mac"
      case Os.Kind.Unsupported => "unsupported"
    }

  lazy val homeOpt: scala.Option[Os.Path] = {
    var r = scala.Option(System.getenv("SIREUM_HOME")).map(envVar => Os.path(envVar).canon)
    if (r.isEmpty) {
      r = scala.Option(System.getProperty("org.sireum.home")).map(p => Os.path(p).canon)
    }
    if (r.nonEmpty) r
    else try {
      val uri = getClass.getProtectionDomain.getCodeSource.getLocation.toURI.toASCIIString
      val i = uri.indexOf(".jar")
      if (i >= 0) {
        val path = Os.path(
          new java.io.File(new java.net.URI(uri.substring(0, uri.lastIndexOf('/', i)))).getCanonicalPath
        ).up
        if ((path / "bin" / "sireum.jar").exists && (path / "lib").exists) scala.Some(path)
        else scala.None
      } else scala.None
    } catch {
      case _: Throwable => scala.None
    }
  }

  lazy val javaHomeOpt: scala.Option[Os.Path] = homeOpt.map(_ / "bin" / platform / "java") match {
    case scala.None => scala.Option(System.getenv("JAVA_HOME")).map(Os.path(_))
    case r => r
  }
  lazy val scalaHomeOpt: scala.Option[Os.Path] = homeOpt.map(_ / "bin" / "scala") match {
    case scala.None => scala.Option(System.getenv("SCALA_HOME")).map(Os.path(_))
    case r => r
  }
  lazy val scalacPluginJar: Os.Path = homeOpt.get / "lib" / s"scalac-plugin-$scalacPluginVer.jar"
  lazy val sireumJar: Os.Path = homeOpt.get / "bin" / "sireum.jar"
  lazy val ideaDir: Os.Path =
    if (platform == "mac") (homeOpt.get / "bin" / platform / "idea").list.elements.
      find(_.string.value.endsWith(".app")).get / "Contents"
    else homeOpt.get / "bin" / platform / "idea"
  lazy val ideaLibDir: Os.Path = ideaDir / "lib"
  lazy val ideaPluginsDir: Os.Path = ideaDir / "plugins"

  lazy val (isDev, javaVer, scalaVer, scalacPluginVer) = {
    val p = new java.util.Properties
    p.load(new java.io.StringReader(
      org.sireum.$internal.RC
        .text(Seq("../../../../../../..")) { (p, _) =>
          p == Seq("versions.properties")
        }
        .head
        ._2))
    ("false" == p.get("org.sireum.version.dev"),
      if (Os.kind == Os.Kind.Linux) String(s"1.8-GraalVM-${p.get("org.sireum.version.graal")}")
      else String(s"1.8-Zulu-${p.get("org.sireum.version.zulu")}"),
      String(p.get("org.sireum.version.scala").toString),
      String(p.get("org.sireum.version.scalac-plugin").toString))
  }

  def homeFound: B = {
    if (homeOpt.isEmpty) {
      eprintln("Could not detect Sireum's home!")
      eprintln("Please either specify SIREUM_HOME env var or org.sireum.home property in JAVA_OPTS env var.")
      F
    } else T
  }

  def javaFound: B = {
    if (javaHomeOpt.isEmpty) {
      eprintln("Could not detect Java home directory!")
      eprintln("Please specify JAVA_HOME env var.")
      F
    } else T
  }

  def scalaFound: B = {
    if (scalaHomeOpt.isEmpty) {
      eprintln("Could not detect Java home directory!")
      eprintln("Please specify JAVA_HOME env var.")
      F
    } else T
  }

}
