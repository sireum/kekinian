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

object Sireum {

  def main(args: Array[Predef.String]): Unit = {
    args match {
      case Array("-v") | Array("--version") =>
        println(s"Sireum v${$internal.Macro.version}${if (isNative) " (native)" else ""}")
      case _ =>
        System.exit(Cli(File.pathSeparatorChar).parseSireum(ISZ(args.toSeq.map(s => s: String): _*), 0) match {
          case Some(o: Cli.SlangTipeOption) => cli.SlangTipe.run(o, Reporter.create).toInt
          case Some(o: Cli.SlangRunOption) => cli.SlangRunner.run(o).toInt
          case Some(o: Cli.CligenOption) => cli.GenTools.cliGen(o).toInt
          case Some(o: Cli.IvegenOption) => cli.GenTools.iveGen(o).toInt
          case Some(o: Cli.SergenOption) => cli.GenTools.serGen(o).toInt
          case Some(o: Cli.TransgenOption) => cli.GenTools.transGen(o).toInt
          case Some(_: Cli.HelpOption) => 0
          case _ => -1
        })
    }
  }

  def paths2files(pathFor: String, paths: ISZ[String], checkExist: B): Seq[Os.Path] = {
    var r = Vector[Os.Path]()
    for (p <- paths) {
      r = r :+ paths2fileOpt(pathFor, ISZ(p), checkExist).get
    }
    r
  }

  def paths2fileOpt(pathFor: String, path: ISZ[String], checkExist: B): Option[Os.Path] = {
    path.size match {
      case z"0" => None()
      case z"1" =>
        val f = Os.path(path(0).value).canon
        if (checkExist && !f.exists) error(s"File ${path(0)} does not exist.")
        return Some(f)
      case _ =>
        error(s"Expecting a path for $pathFor, but found multiple.")
    }
  }

  def path2fileOpt(pathFor: String, path: Option[String], checkExist: B): Option[Os.Path] = {
    if (path.isEmpty) return None()
    val f = Os.path(path.get.value).canon
    if (checkExist && !f.exists) error(s"File '${path.get}' does not exist.")
    return Some(f)
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

  lazy val isNative: B = Os_Ext.isNative

  lazy val info: Init.Info = Init.info($internal.Macro.version, versions)

  lazy val homeOpt: Option[Os.Path] = {
    var r = scala.Option(System.getenv("SIREUM_HOME")).map(envVar => Os.path(envVar).canon)
    if (r.isEmpty) {
      r = scala.Option(System.getProperty("org.sireum.home")).map(p => Os.path(p).canon)
    }
    if (r.nonEmpty) Some(r.get)
    else try {
      val cs = getClass.getProtectionDomain.getCodeSource
      var path =
        if (cs != null) Os.uriToPath(cs.getLocation.toURI.toASCIIString).up
        else Os.slashDir.up
      if (path.name.value == "bin") path = path.up
      if ((path / "bin" / "sireum.jar").exists && (path / "lib").exists) Some(path) else None()
    } catch {
      case _: Throwable => None()
    }
  }

  lazy val javaHomeOpt: Option[Os.Path] = {
    var rOpt = Os.env("JAVA_HOME").map(Os.path(_))
    if (rOpt.isEmpty) {
      rOpt = homeOpt.map(_ / "bin" / platform / "java")
      rOpt match {
        case Some(r) if r.exists =>
        case _ if isNative => rOpt = Some(info.javaHome)
        case _ => rOpt = None()
      }
    }
    rOpt
  }

  lazy val scalaHomeOpt: Option[Os.Path] = {
    var rOpt: Option[Os.Path] = Os.env("SCALA_HOME").map(Os.path(_))
    if (rOpt.isEmpty) {
      rOpt = homeOpt.map(_ / "bin" / "scala")
      rOpt match {
        case Some(r) if r.exists =>
        case _ if isNative => rOpt = Some(info.scalaHome)
        case _ => rOpt = None()
      }
    }
    rOpt
  }

  lazy val scalacPluginJar: Os.Path = homeOpt match {
    case Some(home) => home / "lib" / s"scalac-plugin-$scalacPluginVer.jar"
    case _ if isNative => info.scalacPlugin
    case _ => homeNotFound()
  }

  lazy val sireumJar: Os.Path = homeOpt match {
    case Some(home) => home / "bin" / "sireum.jar"
    case _ if isNative => info.sireumJar
    case _ => homeNotFound()
  }

  lazy val ideaDir: Os.Path =
    if (platform == "mac") (homeOpt.get / "bin" / platform / "idea").list.elements.
      find(_.string.value.endsWith(".app")).get / "Contents"
    else homeOpt.get / "bin" / platform / "idea"
  lazy val ideaLibDir: Os.Path = ideaDir / "lib"
  lazy val ideaPluginsDir: Os.Path = ideaDir / "plugins"
  lazy val versions: Map[String, String] = {
    val p = new java.util.Properties
    p.load(new java.io.StringReader(
      org.sireum.$internal.RC
        .text(Seq("../../../../../../..")) { (p, _) =>
          p == Seq("versions.properties")
        }
        .head
        ._2))
    var r = Map.empty[String, String]
    import scala.collection.JavaConverters._
    for (key <- p.keys().asScala) {
      r = r + key.toString ~> p.get(key).toString
    }
    r
  }

  lazy val (isDev, javaVer, scalaVer, scalacPluginVer): (B, String, String, String) = {
    (Some("false") == versions.get("org.sireum.version.dev"),
      if (Os.kind == Os.Kind.Linux) s"1.8-GraalVM-${versions.get("org.sireum.version.graal").get}"
      else s"1.8-Zulu-${versions.get("org.sireum.version.zulu").get}",
      versions.get("org.sireum.version.scala").get,
      versions.get("org.sireum.version.scalac-plugin").get)
  }

  def homeFound: B = {
    if (homeOpt.isEmpty) {
      eprintln("Could not detect Sireum's home!")
      eprintln("Please either specify SIREUM_HOME env var or org.sireum.home property in JAVA_OPTS env var.")
      F
    } else T
  }

  def homeNotFound(): Nothing = {
    homeFound
    Os.exit(-1)
    halt("")
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
      eprintln("Could not detect Scala home directory!")
      eprintln("Please specify SCALA_HOME env var.")
      F
    } else T
  }

}
