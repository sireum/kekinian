/*
 Copyright (c) 2017-2022, Robby, Kansas State University
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

import org.sireum.message.Reporter
import org.sireum.project.DependencyManager

object Sireum {

  def main(args: Array[Predef.String]): Unit = {
    System.exit(run(ISZ(args.toSeq.map(s => s: String): _*)).toInt)
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
    val scalacPluginJarOpt = rOpt match {
      case Some(home) => Some(home / "lib" / s"scalac-plugin-$scalacPluginVer.jar")
      case _ if isNative => Some(initInfo.scalacPlugin)
      case _ => None()
    }
    scalacPluginJarOpt match {
      case Some(scalacPluginJar) =>
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
      case _ =>
    }
    rOpt
  }

  lazy val javaHomeOpt: Option[Os.Path] = {
    var rOpt: Option[Os.Path] =
      if (Os.env("SIREUM_PROVIDED_JAVA") == Some("true")) None()
      else homeOpt.map(_ / "bin" / platform / "java")
    if (rOpt.isEmpty || !rOpt.get.exists) {
      rOpt = Os.env("JAVA_HOME").map(Os.path(_))
      rOpt match {
        case Some(r) if r.exists =>
        case _ if isNative => rOpt = Some(initInfo.javaHome)
        case _ => rOpt = None()
      }
    }
    rOpt
  }

  lazy val scalaHomeOpt: Option[Os.Path] = {
    var rOpt: Option[Os.Path] =
      if (Os.env("SIREUM_PROVIDED_SCALA") == Some("true")) None()
      else homeOpt.map(_ / "bin" / "scala")
    if (rOpt.isEmpty || !rOpt.get.exists) {
      rOpt = Os.env("SCALA_HOME").map(Os.path(_))
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
    if (platform == "mac") homeOpt.get / "bin" / platform / "idea" / "IVE.app" / "Contents"
    else homeOpt.get / "bin" / platform / "idea"
  lazy val ideaUltimateDir: Os.Path =
    if (platform == "mac") homeOpt.get / "bin" / platform / "idea-ultimate" / "IVE.app" / "Contents"
    else homeOpt.get / "bin" / platform / "idea-ultimate"
  lazy val ideaServerDir: Option[Os.Path] =
    if (platform == "mac") None()
    else Some(homeOpt.get / "bin" / platform / "idea-server")
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
    val hash = commitHash.value.substring(0, 10)
    r = r + DependencyManager.macrosKey ~> hash
    r = r + DependencyManager.testKey ~> hash
    r = r + DependencyManager.librarySharedKey ~> hash
    r = r + DependencyManager.libraryKey ~> hash
    r
  }

  lazy val (isDev, javaVer, jbrVer, scalaVer, scalacPluginVer): (B, String, String, String, String) = {
    import project.DependencyManager._
    (!(Some("false") == versions.get("org.sireum.version.dev")),
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
                  transpilerCallback: (hamr.codegen.common.containers.TranspilerConfig, message.Reporter) => Z,
                  proyekIveCallback: hamr.codegen.common.containers.ProyekIveConfig => Z): hamr.codegen.common.util.CodeGenResults =
    hamr.codegen.CodeGen.codeGen(model, options, reporter, transpilerCallback, proyekIveCallback)

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

  def procCheck(p: OsProto.Proc, reporter: Reporter): OsProto.Proc.Result = {
    val r = proc(p, reporter)
    if (!r.ok) {
      halt(
        st"""Error encountered when running: ${(p.cmds, " ")}, exit code: ${r.exitCode}
            |${if (p.isErrAsOut) r.out else r.err}""".render)
    }
    r
  }

  def proc(p: OsProto.Proc, reporter: Reporter): OsProto.Proc.Result = this.synchronized {
    def firstErr(): ISZ[String] = {
      halt(s"The first path command should be 'sireum${if (Os.isWin) ".bat" else ""}'")
    }
    p match {
      case p: Os.Proc if p.envMap.nonEmpty || p.shouldPrintEnv || p.timeoutInMillis > 0 || p.outLineActionOpt.nonEmpty || p.errLineActionOpt.nonEmpty =>
        println("Some proc options are ignored (e.g., env, timeout, line action, etc.)")
        println()
      case _ =>
    }

    val args: ISZ[String] = p.cmds match {
      case ISZ(first,  _*) =>
        val firstName = Os.path(first).name
        if (firstName === "sireum" || firstName === "sireum.bat") ops.ISZOps(p.cmds).drop(1)
        else firstErr()
      case _ => firstErr()
    }

    val oldOut = System.out
    val oldErr = System.err
    val oldIn = System.in
    val bout = new java.io.ByteArrayOutputStream() {
      override def write(b: Int): Unit = {
        super.write(b)
        if (p.shouldOutputConsole) {
          oldOut.write(b)
          oldOut.flush()
        }
      }
      override def write(b: Array[Byte], off: Int, len: Int): Unit = {
        super.write(b, off, len)
        if (p.shouldOutputConsole) {
          oldOut.write(b, off, len)
          oldOut.flush()
        }
      }
    }
    val berr = if (p.isErrAsOut) bout else new java.io.ByteArrayOutputStream() {
      override def write(b: Int): Unit = {
        super.write(b)
        if (p.shouldOutputConsole) {
          oldErr.write(b)
          oldErr.flush()
        }
      }
      override def write(b: Array[Byte], off: Int, len: Int): Unit = {
        super.write(b, off, len)
        if (p.shouldOutputConsole) {
          oldErr.write(b, off, len)
          oldErr.flush()
        }
      }
    }
    val out = new java.io.PrintStream(bout)
    val err = new java.io.PrintStream(berr)
    p.in match {
      case Some(input) => System.setIn(new java.io.ByteArrayInputStream(input.value.getBytes("UTF-8")))
      case _ =>
    }
    System.setOut(out)
    System.setErr(err)
    if (p.shouldPrintCommands) {
      println(st"${(p.cmds, " ")}".render)
    }
    try {
      val exitCode = run(args, reporter)
      System.out.flush()
      System.err.flush()
      Os.Proc.Result.Normal(exitCode, bout.toString("UTF-8"), if (p.isErrAsOut) "" else berr.toString("UTF-8"))
    } catch {
      case t: Throwable =>
        val sw = new java.io.PrintWriter(new java.io.StringWriter)
        t.printStackTrace(sw)
        sw.flush()
        reporter.internalError(None(), "Sireum", sw.toString)
        System.out.flush()
        System.err.flush()
        Os.Proc.Result.Normal(42, bout.toString("UTF-8"), if (p.isErrAsOut) "" else berr.toString("UTF-8"))
    } finally {
      System.setErr(oldErr)
      System.setOut(oldOut)
      if (p.in.nonEmpty) System.setIn(oldIn)
    }
  }

  def runWithInputAndReporter(args: ISZ[String], input: String, reporter: Reporter): (Z, String, String) =
    runWithReporter(args, reporter, Some(input))

  def runWithReporter(args: ISZ[String], reporter: Reporter, inputOpt: Option[String] = None()): (Z, String, String) = {
    val r = proc(Os.proc("sireum" +: args)(in = inputOpt), reporter)
    (r.exitCode, r.out, r.err)
  }

  def run(args: ISZ[String], reporter: Reporter = Reporter.create): Z = {
    args match {
      case ISZ(string"-v") =>
        println(s"Sireum v$version${if (isNative) " (native)" else ""}")
        return 0
      case ISZ(string"--version") =>
        println(s"Sireum v$version${if (isNative) " (native)" else ""}")
        println()
        println(versions)
        return 0
      case ISZ(string"--test-cli", _*) =>
        return if (Cli(Os.pathSepChar).parseSireum(ops.ISZOps(args).drop(1), 0).nonEmpty) 0 else -1
      case _ =>
        Cli(Os.pathSepChar).parseSireum(args, 0) match {
          case Some(o: Cli.SireumSlangTipeOption) => cli.SlangTipe.run(o, Reporter.create) match {
            case Either.Right(code) => return code
            case _ => return 0
          }
          case Some(o: Cli.SireumSlangRunOption) => return cli.SlangRunner.run(o)
          case Some(o: Cli.SireumSlangTranspilersCOption) => return cli.CTranspiler.run(o, reporter)
          case Some(o: Cli.SireumToolsBcgenOption) => return cli.GenTools.bcGen(o, reporter)
          case Some(o: Cli.SireumToolsCheckstackOption) => return cli.CheckStack.run(o)
          case Some(o: Cli.SireumToolsCligenOption) => return cli.GenTools.cliGen(o)
          case Some(o: Cli.SireumToolsIvegenOption) => return cli.GenTools.iveGen(o)
          case Some(o: Cli.SireumToolsOpgenOption) => return cli.GenTools.opGen(o, reporter)
          case Some(o: Cli.SireumToolsSergenOption) => return cli.GenTools.serGen(o, reporter)
          case Some(o: Cli.SireumToolsTransgenOption) => return cli.GenTools.transGen(o, reporter)
          case Some(o: Cli.SireumHamrCodegenOption) => return cli.HAMR.codeGen(o, reporter)
          case Some(o: Cli.SireumHamrPhantomOption) => return cli.Phantom.run(o)
          case Some(o: Cli.SireumLogikaVerifierOption) =>
            reporter match {
              case reporter: logika.Logika.Reporter => return cli.Logika.run(o, reporter)
              case _ =>
                val rep = logika.Logika.Reporter.create
                val exitCode = cli.Logika.run(o, rep)
                reporter.reports(rep.messages)
                return exitCode
            }
          case Some(o: Cli.SireumParserGenOption) => return cli.Parser.gen(o, reporter)
          case Some(o: Cli.SireumPresentasiText2speechOption) => return cli.Presentasi.text2speech(o)
          case Some(o: Cli.SireumPresentasiGenOption) =>
            val r = NativeUtil.nonNative[Z](-1, () => try cli.Presentasi.gen(o, reporter) finally cli.Presentasi.Ext.shutdown())
            if (r == -1) {
              eprintln("The tool is not available in native mode")
            }
            return r
          case Some(o: Cli.SireumProyekIveOption) => return cli.Proyek.ive(o)
          case Some(o: Cli.SireumProyekAssembleOption) => return cli.Proyek.assemble(o)
          case Some(o: Cli.SireumProyekCompileOption) => return cli.Proyek.compile(o)
          case Some(o: Cli.SireumProyekLogikaOption) =>
            reporter match {
              case reporter: logika.Logika.Reporter => return cli.Proyek.logika(o, reporter)
              case _ =>
                val rep = logika.Logika.Reporter.create
                val exitCode = cli.Proyek.logika(o, rep)
                reporter.reports(rep.messages)
                return exitCode
            }
          case Some(o: Cli.SireumProyekPublishOption) => return cli.Proyek.publish(o)
          case Some(o: Cli.SireumProyekRunOption) => return cli.Proyek.run(o)
          case Some(o: Cli.SireumProyekStatsOption) => return cli.Proyek.stats(o, reporter)
          case Some(o: Cli.SireumProyekTestOption) => return cli.Proyek.test(o)
          case Some(o: Cli.SireumProyekTipeOption) =>
            reporter match {
              case reporter: logika.Logika.Reporter => return cli.Proyek.tipe(o, reporter)
              case _ =>
                val rep = logika.Logika.Reporter.create
                val exitCode = cli.Proyek.tipe(o, rep)
                reporter.reports(rep.messages)
                return exitCode
            }
          case Some(_: Cli.HelpOption) => return 0
          case Some(o: Cli.SireumServerOption) =>
            homeOpt match {
              case Some(home) =>
                return server.Server.run(version, o.message == Cli.SireumServerServerMessage.Msgpack, o.workers,
                  !o.noInputCache, !o.noTypeCache, o.log, o.verbose, javaHomeOpt.get, scalaHomeOpt.get,
                  home, versions.entries)
              case _ =>
                eprintln("Please set SIREUM_HOME env var")
                return -1
            }
          case _ => return -1
        }
    }
  }

  def initRuntimeLibrary(): Unit = {
    org.sireum.lang.FrontEnd.checkedLibraryReporter
  }

  def parCores(percentage: Z): Z = {
    val maxCores = Os.numOfProcessors
    val r = percentage * maxCores / 100
    return if (r <= 1) 1 else if (r >= maxCores) maxCores else r
  }

  def parCoresOpt(percentageOpt: Option[Z]): Z = {
    val r: Z = percentageOpt match {
      case Some(v) => parCores(v)
      case _ => 1
    }
    return r
  }

  def parseGrammar(uriOpt: Option[String],
                   input: String,
                   reporter: message.Reporter): Option[parser.ParseTree] =
    parser.SireumAntlr3ParserUtil.parseGrammar(uriOpt, input, reporter)

}