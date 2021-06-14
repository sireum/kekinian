/*
 Copyright (c) 2017-2021, Robby, Kansas State University
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
import org.sireum.project.DependencyManager

object Sireum {

  def main(args: Array[Predef.String]): Unit = {
    args match {
      case Array("-v") =>
        println(s"Sireum v$version${if (isNative) " (native)" else ""}")
      case Array("--version") =>
        println(s"Sireum v$version${if (isNative) " (native)" else ""}")
        println()
        println(versions)
      case _ =>
        System.exit(Cli(File.pathSeparatorChar).parseSireum(ISZ(args.toSeq.map(s => s: String): _*), 0) match {
          case Some(o: Cli.SlangTipeOption) => cli.SlangTipe.run(o, Reporter.create).toInt
          case Some(o: Cli.SlangRunOption) => cli.SlangRunner.run(o).toInt
          case Some(o: Cli.CTranspilerOption) => cli.CTranspiler.run(o).toInt
          case Some(o: Cli.BcgenOption) => cli.GenTools.bcGen(o).toInt
          case Some(o: Cli.CheckstackOption) => cli.CheckStack.run(o).toInt
          case Some(o: Cli.CligenOption) => cli.GenTools.cliGen(o).toInt
          case Some(o: Cli.IvegenOption) => cli.GenTools.iveGen(o).toInt
          case Some(o: Cli.SergenOption) => cli.GenTools.serGen(o).toInt
          case Some(o: Cli.TransgenOption) => cli.GenTools.transGen(o).toInt
          case Some(o: Cli.HamrCodeGenOption) => cli.HAMR.codeGen(o).toInt
          case Some(o: Cli.PhantomOption) => cli.Phantom.run(o).toInt
          case Some(o: Cli.LogikaVerifierOption) => cli.Logika.run(o).toInt
          case Some(o: Cli.ServerOption) => server.Server.run(version, o.message == Cli.ServerMessage.Msgpack, o.logika).toInt
          case Some(o: Cli.IveOption) => cli.Proyek.ive(o).toInt
          case Some(o: Cli.AssembleOption) => cli.Proyek.assemble(o).toInt
          case Some(o: Cli.CompileOption) => cli.Proyek.compile(o).toInt
          case Some(o: Cli.PublishOption) => cli.Proyek.publish(o).toInt
          case Some(o: Cli.RunOption) => cli.Proyek.run(o).toInt
          case Some(o: Cli.TestOption) => cli.Proyek.test(o).toInt
          case Some(_: Cli.HelpOption) => 0
          case _ => -1
        })
    }
  }

  def paths2files(pathFor: String, paths: ISZ[String], checkExist: B): ISZ[Os.Path] = {
    var r = ISZ[Os.Path]()
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
      case Os.Kind.LinuxArm => "linux/arm"
      case Os.Kind.Mac => "mac"
      case Os.Kind.Unsupported => "unsupported"
    }

  lazy val isNative: B = Os_Ext.isNative

  lazy val version: String = $internal.Macro.version

  lazy val commitHash: String = $internal.Macro.commitHash

  lazy val initInfo: Init.Info = Init.info(version, versions)

  lazy val homeOpt: Option[Os.Path] = {
    val rOpt: Option[Os.Path] = {
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
    val scalacPluginJar = rOpt match {
      case Some(home) => home / "lib" / s"scalac-plugin-$scalacPluginVer.jar"
      case _ if isNative => initInfo.scalacPlugin
      case _ => homeNotFound()
    }
    if (!scalacPluginJar.exists && !scalacPluginVer.value.contains("SNAPSHOT")) {
      val scalacPluginCache = Os.home / "Downloads" / "sireum" / s"scalac-plugin-$scalacPluginVer.jar"
      if (!scalacPluginCache.exists) {
        scalacPluginCache.up.mkdirAll()
        println(s"Please wait while downloading Slang scalac-plugin $scalacPluginVer ...")
        scalacPluginCache.downloadFrom(s"https://github.com/sireum/scalac-plugin/releases/download/$scalacPluginVer/scalac-plugin-$scalacPluginVer.jar")
        println()
      }
      scalacPluginJar.up.mkdirAll()
      scalacPluginCache.copyOverTo(scalacPluginJar)
    }
    rOpt
  }

  lazy val javaHomeOpt: Option[Os.Path] = {
    var rOpt = Os.env("JAVA_HOME").map(Os.path(_))
    if (rOpt.isEmpty) {
      rOpt = homeOpt.map(_ / "bin" / platform / "java")
      rOpt match {
        case Some(r) if r.exists =>
        case _ if isNative => rOpt = Some(initInfo.javaHome)
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
        case _ if isNative => rOpt = Some(initInfo.scalaHome)
        case _ => rOpt = None()
      }
    }
    rOpt
  }

  lazy val scalacPluginJar: Os.Path = homeOpt match {
    case Some(home) => home / "lib" / s"scalac-plugin-$scalacPluginVer.jar"
    case _ => homeNotFound()
  }

  lazy val sireumJar: Os.Path = homeOpt match {
    case Some(home) => home / "bin" / "sireum.jar"
    case _ if isNative => initInfo.sireumJar
    case _ => homeNotFound()
  }

  lazy val ideaDir: Os.Path =
    if (platform == "mac") (homeOpt.get / "bin" / platform / "idea").list.elements.
      find(_.string.value.endsWith(".app")).get / "Contents"
    else homeOpt.get / "bin" / platform / "idea"
  lazy val ideaUltimateDir: Os.Path =
    if (platform == "mac") (homeOpt.get / "bin" / platform / "idea-ultimate").list.elements.
      find(_.string.value.endsWith(".app")).get / "Contents"
    else homeOpt.get / "bin" / platform / "idea-ultimate"
  lazy val ideaLibDir: Os.Path = ideaDir / "lib"
  lazy val ideaPluginsDir: Os.Path = ideaDir / "plugins"
  lazy val versions: Map[String, String] = {
    val p = new java.util.Properties
    p.load(new java.io.StringReader(
      org.sireum.$internal.RC
        .text(Vector("../../../../../../..")) { (p, _) =>
          p == Vector("versions.properties")
        }
        .head
        ._2))
    var r = Map.empty[String, String]
    import org.sireum.$internal.CollectionCompat.Converters._
    for (key <- p.keys().asScala) {
      r = r + key.toString.replace('%', ':') ~> p.get(key).toString
    }
    r = r + DependencyManager.libraryKey ~> commitHash.value.substring(0, 10)
    r
  }

  lazy val (isDev, javaVer, jbrVer, scalaVer, scalacPluginVer): (B, String, String, String, String) = {
    import project.DependencyManager._
    (Some("false") == versions.get("org.sireum.version.dev"),
      versions.get(javaKey).get,
      versions.get(jbrKey).get,
      versions.get(scalaKey).get,
      versions.get(scalacPluginKey).get)
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

  def currentTimeMillis: Z = System.currentTimeMillis()

  def readGzipContent(path: Os.Path): Option[ISZ[U8]] = {
    import _root_.java.io.{File, FileInputStream}
    import _root_.java.util.zip.GZIPInputStream

    def toIS(data: Array[Byte]): ISZ[U8] = {
      new IS(Z, data, data.length, U8.Boxer)
    }

    val gis = new GZIPInputStream(new FileInputStream(new File(path.string.value)))
    try {
      return Some(toIS(gis.bytes))
    } catch {
      case e: _root_.java.io.IOException =>
        eprintln(s"Could not load file: ${e.getMessage}")
        return None()
    } finally gis.close()
  }

  def writeGzipContent(path: Os.Path, content: ISZ[U8]): B = {
    def fromIS(data: ISZ[U8]): (Array[Byte], Int) = {
      return (data.data.asInstanceOf[Array[Byte]], data.size.toInt)
    }

    import _root_.java.io.{File, FileOutputStream}
    import _root_.java.util.zip.GZIPOutputStream

    val (buf, length) = fromIS(content)
    val gos = new GZIPOutputStream(new FileOutputStream(new File(path.string.value)))
    try {
      gos.write(buf, 0, length)
      return T
    } catch {
      case e: _root_.java.io.IOException =>
        eprintln(s"Could not save file: ${e.getMessage}")
        return F
    } finally gos.close()
  }

  def totalMemory: Z = Runtime.getRuntime.totalMemory()

  def freeMemory: Z = Runtime.getRuntime.freeMemory()

  def formatMb(bytes: Z): String = f"${bytes.toLong / 1024d / 1024d}%.2f"

  def formatSecond(millis: Z): String = f"${millis.toLong / 1000d}%.2f"

  def instantiate[T](className: String): Option[T] = {
    try {
      val c = Class.forName(className.value)
      return Some(c.getDeclaredConstructor().newInstance().asInstanceOf[T])
    } catch {
      case _: Throwable => return None()
    }
  }

  def bitcodecPrint(spec: bitcodec.Spec): ST = org.sireum.bitcodec.JSON.Printer.printSpec(spec)

  def hamrCodeGen(model: hamr.ir.Aadl,
                  options: hamr.codegen.common.util.CodeGenConfig,
                  reporter: message.Reporter,
                  transpilerCallback: hamr.codegen.common.containers.TranspilerConfig => Z): hamr.codegen.common.util.CodeGenResults =
    hamr.codegen.CodeGen.codeGen(model, options, reporter, transpilerCallback)

  implicit class GZIS(val gzis: _root_.java.util.zip.GZIPInputStream) extends AnyVal {

    def bytes: Array[Byte] = {
      val bos = new _root_.java.io.ByteArrayOutputStream
      val buffer = new Array[Byte](16384)
      var n = gzis.read(buffer)
      while (n > -1) {
        bos.write(buffer, 0, n)
        n = gzis.read(buffer)
      }
      gzis.close()
      bos.toByteArray
    }
  }

}