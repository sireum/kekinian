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

package org.sireum

import org.sireum.message.Reporter
import org.sireum.project.DependencyManager

object Sireum {

  private lazy val commitSha: String = {
    val commitHashOps = ops.StringOps(commitHash)
    if (commitHashOps.endsWith("*")) commitHashOps.substring(0, commitHashOps.size - 1) else commitHashOps.s
  }

  lazy val init: Init = {
    var r = Os.sireumHomeOpt match {
      case Some(d) => Init(d, Os.kind, Map.empty)
      case _ =>
        val home: Os.Path = Os.kind match {
          case Os.Kind.Win => Os.home / "AppData" / "Local" / "Sireum"
          case Os.Kind.Mac => Os.home / "Library" / "Application Support" / "org.sireum"
          case _ => Os.home / ".sireum"
        }
        System.setProperty("org.sireum.home", home.string.value)
        Init(Os.home / "Applications" / "Sireum", Os.kind, Map.empty)
    }
    val vs: Map[String, String] = {
      val f = r.home / "versions.properties"
      if (f.exists) {
        f.properties
      } else {
        Map.empty ++ (for (p <- SireumApi.versions.entries) yield (ops.StringOps(p._1).replaceAllChars(':', '%'), p._2))
      }
    }
    r = r(versions = vs)
    r
  }

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

  lazy val homeOpt: Option[Os.Path] = Some(init.home)

  lazy val javaHomeOpt: Option[Os.Path] = {
    var rOpt: Option[Os.Path] =
      if (Os.env("SIREUM_PROVIDED_JAVA") == Some("true")) None()
      else homeOpt.map(_ / "bin" / platform / "java")
    if (rOpt.isEmpty || !rOpt.get.exists) {
      rOpt = Os.env("JAVA_HOME").map(Os.path(_))
      rOpt match {
        case Some(r) if r.exists =>
        case _ => rOpt = None()
      }
    }
    rOpt
  }

  lazy val scalaHomeOpt: Option[Os.Path] = Some(init.scalaHome)

  lazy val scalacPluginJar: Os.Path = init.scalacPlugin

  lazy val sireumJar: Os.Path = init.sireumJar

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
    val hash = if (commitHash.size >= 10) commitHash.value.substring(0, 10) else "-SNAPSHOT"
    r = r + DependencyManager.macrosKey ~> hash
    r = r + DependencyManager.testKey ~> hash
    r = r + DependencyManager.librarySharedKey ~> hash
    r = r + DependencyManager.libraryKey ~> hash
    r
  }

  lazy val (isDev, javaVer, scalaVer, scalacPluginVer): (B, String, String, String) = {
    import project.DependencyManager._
    (!(Some("false") == versions.get("org.sireum.version.dev")),
      versions.get(javaKey).get,
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
                  plugins: MSZ[hamr.codegen.common.plugin.Plugin],
                  reporter: message.Reporter,
                  transpilerCallback: (hamr.codegen.common.containers.TranspilerConfig, message.Reporter) => Z,
                  proyekIveCallback: hamr.codegen.common.containers.ProyekIveConfig => Z): hamr.codegen.common.util.CodeGenResults =
    hamr.codegen.CodeGen.codeGen(model, options, plugins, reporter, transpilerCallback, proyekIveCallback)

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
    def printAdditionalHelp(): Unit = {
      println(
        s"""
           |Available Standalone Options:
           |    --setup              Setup IVE and dependencies
           |    --setup-server       Setup IVE (Server) and dependencies
           |    --setup-ultimate     Setup IVE (Ultimate) and dependencies
           |    --sha                Print Sireum build SHA commit tip
           |    --test-cli           Test CLI arguments (expects strings)
           |-v, --version            Print version information""".stripMargin)
    }
    args match {
      case ISZ(string"-h") =>
        Cli(Os.pathSepChar).parseSireum(ISZ(), 0)
        printAdditionalHelp()
        return 0
      case ISZ(string"--help") =>
        Cli(Os.pathSepChar).parseSireum(ISZ(), 0)
        printAdditionalHelp()
        return 0
      case ISZ(string"--setup") =>
        init.deps()
        init.installMaryTTS()
        init.installCheckStack()
        init.installScripts()
        init.distro(isDev = T, buildSfx = F, isUltimate = F, isServer = F)
        if ((init.home / "bin" / "project.cmd").exists) {
          run(ISZ("proyek", "ive", init.home.string), reporter)
        }
        return 0
      case ISZ(string"--setup-server") =>
        init.deps()
        init.installMaryTTS()
        init.installCheckStack()
        init.installScripts()
        init.distro(isDev = T, buildSfx = F, isUltimate = F, isServer = T)
        if ((init.home / "bin" / "project.cmd").exists) {
          run(ISZ("proyek", "ive", "--edition", "server", init.home.string), reporter)
        }
        return 0
      case ISZ(string"--setup-ultimate") =>
        init.deps()
        init.installMaryTTS()
        init.installCheckStack()
        init.installScripts()
        init.distro(isDev = T, buildSfx = F, isUltimate = T, isServer = F)
        if ((init.home / "bin" / "project.cmd").exists) {
          run(ISZ("proyek", "ive", "--edition", "ultimate", init.home.string), reporter)
        }
        return 0
      case ISZ(string"--sha") =>
        println(commitSha)
        return 0
      case ISZ(string"--test-cli", _*) =>
        return if (Cli(Os.pathSepChar).parseSireum(ops.ISZOps(args).drop(1), 0).nonEmpty) 0 else -1
      case ISZ(string"-v") =>
        println(s"Sireum v$version${if (isNative) " (native)" else ""}")
        return 0
      case ISZ(string"--version") =>
        println(s"Sireum v$version${if (isNative) " (native)" else ""}")
        println()
        println(versions)
        return 0
      case _ =>
        Cli(Os.pathSepChar).parseSireum(args, 0) match {
          case Some(o: Cli.SireumSlangTipeOption) =>
            cli.SlangTipe.run(o, Reporter.create) match {
              case Either.Right(code) => return code
              case _ => return 0
            }
          case Some(o: Cli.SireumSlangRunOption) =>
            init.basicDeps()
            return cli.SlangRunner.run(o)
          case Some(o: Cli.SireumSlangTranspilersCOption) =>
            return cli.CTranspiler.run(o, reporter)
          case Some(o: Cli.SireumToolsBcgenOption) =>
            init.basicDeps()
            return cli.GenTools.bcGen(o, reporter)
          case Some(o: Cli.SireumToolsCheckstackOption) =>
            init.installCheckStack()
            return cli.CheckStack.run(o, init.checkstack)
          case Some(o: Cli.SireumToolsCligenOption) =>
            init.basicDeps()
            return cli.GenTools.cliGen(o)
          case Some(o: Cli.SireumToolsOpgenOption) =>
            init.basicDeps()
            return cli.GenTools.opGen(o, reporter)
          case Some(o: Cli.SireumToolsSergenOption) =>
            init.basicDeps()
            return cli.GenTools.serGen(o, reporter)
          case Some(o: Cli.SireumToolsTrafoOption) =>
            init.basicDeps()
            return cli.GenTools.transGen(o, reporter)
          case Some(o: Cli.SireumToolsSlangcheckRunnerOption) =>
            val code = NativeUtil.nonNative(Z(-1), () => {
              cli.SlangCheck.run(o, reporter)
            })
            reporter.printMessages()
            return code
          case Some(o: Cli.SireumToolsSlangcheckTesterOption) =>
            val code = NativeUtil.nonNative(Z(-1), () => {
              cli.SlangCheck.test(o, reporter)
            })
            reporter.printMessages()
            return code
          case Some(o: Cli.SireumHamrCodegenOption) =>
            init.deps()
            return cli.HAMR.codeGen(o, reporter)
          case Some(o: Cli.SireumHamrPhantomOption) =>
            init.basicDeps()
            return cli.Phantom.run(o)
          case Some(o: Cli.SireumLogikaVerifierOption) =>
            init.basicDeps()
            init.logikaDeps()
            reporter match {
              case reporter: logika.Logika.Reporter => return cli.Logika.run(o, reporter)
              case _ =>
                val rep = logika.ReporterImpl.create(o.logPc, o.logRawPc, o.logVc)
                rep.collectStats = o.stats
                val exitCode = cli.Logika.run(o, rep)
                reporter.reports(rep.messages)
                return exitCode
            }
          case Some(o: Cli.SireumParserGenOption) =>
            return cli.Parser.gen(o, reporter)
          case Some(o: Cli.SireumPresentasiText2speechOption) =>
            init.installJava()
            return cli.Presentasi.text2speech(o)
          case Some(o: Cli.SireumPresentasiGenOption) =>
            init.basicDeps()
            init.installMaryTTS()
            val r = NativeUtil.nonNative[Z](-1, () => try cli.Presentasi.gen(o, reporter) finally cli.Presentasi.Ext.shutdown())
            if (r == -1) {
              eprintln("The tool is not available in native mode")
            }
            return r
          case Some(o: Cli.SireumProyekDepOption) =>
            init.basicDeps()
            init.proyekCompileDeps()
            return cli.Proyek.dep(o)
          case Some(o: Cli.SireumProyekIveOption) =>
            val isUltimate = o.edition == Cli.SireumProyekIveEdition.Ultimate
            val isServer = o.edition == Cli.SireumProyekIveEdition.Server
            if (o.rebuildIve || !init.ideaDirPath(isUltimate, isServer).exists) {
              init.distro(isDev = T, buildSfx = F, isUltimate = o.edition == Cli.SireumProyekIveEdition.Ultimate,
                isServer = o.edition == Cli.SireumProyekIveEdition.Server)
            }
            return cli.Proyek.ive(o)
          case Some(o: Cli.SireumProyekAssembleOption) =>
            init.basicDeps()
            init.proyekCompileDeps()
            return cli.Proyek.assemble(o)
          case Some(o: Cli.SireumProyekCompileOption) =>
            init.basicDeps()
            init.proyekCompileDeps()
            return cli.Proyek.compile(o)
          case Some(o: Cli.SireumProyekLogikaOption) =>
            init.basicDeps()
            init.logikaDeps()
            reporter match {
              case reporter: logika.Logika.Reporter => return cli.Proyek.logika(o, reporter)
              case _ =>
                val rep = logika.ReporterImpl.create(o.logPc, o.logRawPc, o.logVc)
                rep.collectStats = o.stats
                val exitCode = cli.Proyek.logika(o, rep)
                reporter.reports(rep.messages)
                return exitCode
            }
          case Some(o: Cli.SireumProyekPublishOption) =>
            init.basicDeps()
            init.proyekCompileDeps()
            return cli.Proyek.publish(o)
          case Some(o: Cli.SireumProyekRunOption) =>
            init.basicDeps()
            init.proyekCompileDeps()
            return cli.Proyek.run(o)
          case Some(o: Cli.SireumProyekStatsOption) =>
            init.basicDeps()
            return cli.Proyek.stats(o, reporter)
          case Some(o: Cli.SireumProyekTestOption) =>
            init.deps()
            return cli.Proyek.test(o)
          case Some(o: Cli.SireumProyekTipeOption) =>
            init.basicDeps()
            reporter match {
              case reporter: logika.Logika.Reporter => return cli.Proyek.tipe(o, reporter)
              case _ =>
                val rep = logika.ReporterImpl.create(F, F, F)
                val exitCode = cli.Proyek.tipe(o, rep)
                reporter.reports(rep.messages)
                return exitCode
            }
          case Some(_: Cli.HelpOption) =>
            if (args.isEmpty) {
              printAdditionalHelp()
            }
            return 0
          case Some(o: Cli.SireumServerOption) =>
            init.deps()
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

  def parCores(percentage: Z): Z = LibUtil.parCores(Os.numOfProcessors, percentage)

  def parCoresOpt(percentageOpt: Option[Z]): Z = LibUtil.parCoresOpt(Os.numOfProcessors, percentageOpt)

  def parseGrammar(uriOpt: Option[String],
                   input: String,
                   reporter: message.Reporter): Option[parser.ParseTree] =
    parser.SireumAntlr3ParserUtil.parseGrammar(uriOpt, input, reporter)
}