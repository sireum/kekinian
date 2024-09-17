/*
 Copyright (c) 2017-2024, Robby, Kansas State University
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

import org.sireum.logika.Logika.Reporter.Info.Kind
import org.sireum.logika.{Logika, Smt2Query}
import org.sireum.message.{Position, Reporter}
import org.sireum.project.DependencyManager

import java.io.{FileWriter, OutputStream, PrintStream}
import java.security.MessageDigest
import java.util.concurrent.atomic.AtomicLong

object Sireum {

  private lazy val commitSha: String = {
    val commitHashOps = ops.StringOps(commitHash)
    if (commitHashOps.endsWith("*")) commitHashOps.substring(0, commitHashOps.size - 1) else commitHashOps.s
  }

  lazy val init: Init = {
    var r = Os.sireumHomeOpt match {
      case Some(d) => Init(d, Os.kind, Map.empty)
      case _ =>
        val home: Os.Path = Os.home / "Applications" / "Sireum"
        if (!(home / "bin" / (if (Os.isWin) "sireum.bat" else "sireum")).exists) {
          halt("Please set SIREUM_HOME environment variable")
        }
        System.setProperty("org.sireum.home", home.string.value)
        Init(home, Os.kind, Map.empty)
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

  def homePathString: String = homeOpt.get.string

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

  def javaHomePathString: String = javaHomeOpt.get.string

  lazy val scalaHomeOpt: Option[Os.Path] = Some(init.scalaHome)

  def scalaHomePathString: String = scalaHomeOpt.get.string

  lazy val scalacPluginJar: Os.Path = init.scalacPlugin

  def scalacPluginJarPathString: String = scalacPluginJar.string

  lazy val sireumJar: Os.Path = init.sireumJar

  def sireumJarPathString: String = sireumJar.string

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
    ( //!(Some("false") == versions.get("org.sireum.version.dev")),
      F,
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
                  shouldWriteOutResources: B,
                  options: hamr.codegen.common.util.HamrCli.CodegenOption,
                  plugins: MSZ[hamr.codegen.common.plugin.Plugin],
                  reporter: message.Reporter,
                  transpilerCallback: (hamr.codegen.common.containers.SireumSlangTranspilersCOption, message.Reporter) => Z,
                  proyekIveCallback: hamr.codegen.common.containers.SireumProyekIveOption => Z,
                  sergenCallback: (hamr.codegen.common.containers.SireumToolsSergenOption, message.Reporter) => Z,
                  slangCheckCallback: (hamr.codegen.common.containers.SireumToolsSlangcheckGeneratorOption, message.Reporter) => Z): hamr.codegen.common.util.CodegenResults =
    hamr.codegen.CodeGen.codeGen(model, shouldWriteOutResources, options, plugins, reporter, transpilerCallback, proyekIveCallback, sergenCallback, slangCheckCallback)

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
      case ISZ(first, _*) =>
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
           |    --init               Setup dependencies
           |    --native             Build native executable
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
      case ISZ(string"--init") =>
        init.deps()
        return 0
      case ISZ(string"--native") =>
        init.deps()
        nativ()
        return 0
      case ISZ(string"--setup") =>
        init.deps()
        init.distro(isDev = F, buildSfx = F, isUltimate = F, isServer = F)
        if ((init.home / "bin" / "project.cmd").exists) {
          run(ISZ("proyek", "ive", init.home.string), reporter)
        }
        proyek.Ive.IVE.writeApplicationConfigs(T, init.home, init.ideaDirPath(F, F),
          Os.javaHomeOpt(init.kind, Some(init.home)).get, init.javaVersion, F,
          init.ideaConfig(T, F, F, None()), init.ideaSandbox(F))
        println()
        println(s"Sireum IVE v$version is available at ${init.ideaDirPath(isUltimate = F, isServer = F)}")
        println(s"Java Development Kit (JDK) ${init.javaVersion} is available at ${Os.javaHomeOpt(init.kind, Some(init.home)).get}")
        return 0
      case ISZ(string"--setup-server") =>
        init.deps()
        init.distro(isDev = F, buildSfx = F, isUltimate = F, isServer = T)
        if ((init.home / "bin" / "project.cmd").exists) {
          run(ISZ("proyek", "ive", "--edition", "server", init.home.string), reporter)
        }
        proyek.Ive.IVE.writeApplicationConfigs(T, init.home, init.ideaDirPath(F, T),
          Os.javaHomeOpt(init.kind, Some(init.home)).get, init.javaVersion, F,
          init.ideaConfig(T, F, F, None()), init.ideaSandbox(F))
        println()
        println(s"Sireum IVE v$version is available at ${init.ideaDirPath(isUltimate = F, isServer = T)}")
        println(s"Java Development Kit (JDK) ${init.javaVersion} is available at ${Os.javaHomeOpt(init.kind, Some(init.home)).get}")
        return 0
      case ISZ(string"--setup-ultimate") =>
        init.deps()
        init.distro(isDev = F, buildSfx = F, isUltimate = T, isServer = F)
        if ((init.home / "bin" / "project.cmd").exists) {
          run(ISZ("proyek", "ive", "--edition", "ultimate", init.home.string), reporter)
        }
        proyek.Ive.IVE.writeApplicationConfigs(T, init.home, init.ideaDirPath(T, F),
          Os.javaHomeOpt(init.kind, Some(init.home)).get, init.javaVersion, F,
          init.ideaConfig(T, F, T, None()), init.ideaSandbox(F))
        println()
        println(s"Sireum IVE v$version is available at ${init.ideaDirPath(isUltimate = T, isServer = F)}")
        println(s"Java Development Kit (JDK) ${init.javaVersion} is available at ${Os.javaHomeOpt(init.kind, Some(init.home)).get}")
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
          case Some(o: Cli.SireumToolsSlangcheckGeneratorOption) =>
            init.basicDeps()
            val result = cli.SlangCheck.generate(o, reporter)
            reporter.printMessages()
            return result
          case Some(o: Cli.SireumToolsSlangcheckRunnerOption) =>
            init.basicDeps()
            val code = NativeUtil.nonNative(Z(-1), () => {
              cli.SlangCheck.run(o, reporter)
            })
            reporter.printMessages()
            return code
          case Some(o: Cli.SireumToolsSlangcheckTesterOption) =>
            init.basicDeps()
            init.installJacoco()
            val code = NativeUtil.nonNative(Z(-1), () => {
              cli.SlangCheck.test(args, o, reporter)
            })
            reporter.printMessages()
            return code
          case Some(o: Cli.SireumHamrCodegenOption) =>
            init.deps()
            return cli.HAMR.codeGen(o, reporter)
          case Some(o: Cli.SireumHamrSysmlCodegenOption) =>
            init.deps()
            return cli.HAMR.codeGenS(o, reporter)
          case Some(o: Cli.SireumHamrPhantomOption) =>
            init.basicDeps()
            return cli.Phantom.run(o)
          case Some(o: Cli.SireumHamrSysmlLogikaOption) =>
            init.basicDeps()
            reporter match {
              case reporter: logika.Logika.Reporter => return cli.HAMR.sysmlLogika(o, reporter)
              case _ =>
                val feedbackDirOpt: Option[Os.Path] = o.feedback match {
                  case Some(d) => Some(Os.path(d))
                  case _ => None()
                }
                val sha3 = MessageDigest.getInstance("SHA3-512")

                def write(d: Os.Path, content: String): Unit = {
                  val f = d / st"${(ISZ(sha3.digest(content.value.getBytes("UTF-8")).take(8).map(U8(_)).toSeq: _*), "")}.json".render
                  f.writeOver(content)
                }
                class Rep extends logika.ReporterImpl(o.logPc, o.logRawPc, o.logVc, o.logDetailedInfo, F, ISZ(), o.stats,
                  new AtomicLong(0), new AtomicLong(0), new AtomicLong(0), new AtomicLong(0)) {
                  override def empty: logika.Logika.Reporter = {
                    new Rep()
                  }
                  override def query(pos: Position, title: String, isSat: B, time: Z, forceReport: B, detailElided: B, r: Smt2Query.Result): Unit = {
                    super.query(pos, title, isSat, time, forceReport, detailElided, r)
                    println(s"query: $pos")
                    feedbackDirOpt match {
                      case Some(d) =>
                        val content = server.protocol.JSON.fromLogikaVerifySmt2Query(
                          server.protocol.Logika.Verify.Smt2Query(ISZ(), pos, isSat, time, title, r.kind, r.solverName,
                            r.query, r.info, r.output), T)
                        write(d, content)
                      case _ =>
                    }
                  }
                  override def coverage(setCache: B, cached: U64, pos: Position): Unit = {
                    println(s"coverage: $pos")
                    feedbackDirOpt match {
                      case Some(d) =>
                        val content = server.protocol.JSON.fromAnalysisCoverage(
                          server.protocol.Analysis.Coverage(ISZ(), setCache, cached, pos), T)
                        write(d, content)
                      case _ =>
                    }
                  }
                  override def inform(pos: Position, kind: Kind.Type, message: String): Unit = {
                    super.inform(pos, kind, message)
                    println(s"inform: $pos")
                    feedbackDirOpt match {
                      case Some(d) =>
                        val k: server.protocol.Logika.Verify.Info.Kind.Type = kind match {
                          case logika.Logika.Reporter.Info.Kind.Verified => server.protocol.Logika.Verify.Info.Kind.Verified
                          case logika.Logika.Reporter.Info.Kind.Error => server.protocol.Logika.Verify.Info.Kind.Error
                        }
                        val content = server.protocol.JSON.fromLogikaVerifyInfo(server.protocol.Logika.Verify.Info(ISZ(),
                          pos, k, message), T)
                        write(d, content)
                      case _ =>
                    }
                  }
                }
                val rep = new Rep
                rep.collectStats = o.stats
                val exitCode = cli.HAMR.sysmlLogika(o, rep)
                reporter.reports(rep.messages)
                return exitCode
            }
          case Some(o: Cli.SireumHamrSysmlTipeOption) =>
            init.basicDeps()
            cli.HAMR.sysmlRun(o, reporter) match {
              case Either.Right(code) => return code
              case Either.Left((_, _, _, hasError)) =>
                if (!hasError) {
                  println("Well-formed!")
                }
                return 0
            }
          case Some(o: Cli.SireumHamrSysmlTranslatorOption) =>
            init.basicDeps()
            return cli.HAMR.sysmlTranslator(o)
          case Some(o: Cli.SireumHamrSysmlConfigOption) =>
            init.basicDeps()
            return cli.HAMR.sysmlConfig(o)
          case Some(o: Cli.SireumLogikaVerifierOption) =>
            init.basicDeps()
            init.logikaDeps()
            reporter match {
              case reporter: logika.Logika.Reporter => return cli.Logika.run(o, reporter)
              case _ =>
                val rep = logika.ReporterImpl.create(o.logPc, o.logRawPc, o.logVc, o.logDetailedInfo)
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
          case Some(o: Cli.SireumProyekExportOption) =>
            init.basicDeps()
            return cli.Proyek.exprt(o)
          case Some(o: Cli.SireumProyekDepOption) =>
            init.basicDeps()
            init.proyekCompileDeps()
            return cli.Proyek.dep(o)
          case Some(o: Cli.SireumProyekIveOption) =>
            val isUltimate = o.edition == Cli.SireumProyekIveEdition.Ultimate
            val isServer = o.edition == Cli.SireumProyekIveEdition.Server
            if (o.rebuildIve || !init.ideaDirPath(isUltimate, isServer).exists) {
              init.distro(isDev = F, buildSfx = F, isUltimate = o.edition == Cli.SireumProyekIveEdition.Ultimate,
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
                val rep = logika.ReporterImpl.create(o.logPc, o.logRawPc, o.logVc, o.logDetailedInfo)
                rep.collectStats = o.stats
                val exitCode = cli.Proyek.logika(o, rep)
                reporter.reports(rep.messages)
                return exitCode
            }
          case Some(o: Cli.SireumProyekSlangcheckOption) =>
            init.basicDeps()
            init.logikaDeps()
            reporter match {
              case reporter: logika.Logika.Reporter => return cli.Proyek.slangCheck(o, reporter)
              case _ =>
                val rep = logika.ReporterImpl.create(F, F, F, F)
                rep.collectStats = F
                val exitCode = cli.Proyek.slangCheck(o, rep)
                reporter.reports(rep.messages)
                return exitCode
            }
          case Some(o: Cli.SireumProyekPublishOption) =>
            init.basicDeps()
            init.proyekCompileDeps()
            return cli.Proyek.publish(o)
          case Some(o: Cli.SireumProyekRunOption) =>
            init.deps()
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
                val rep = logika.ReporterImpl.create(F, F, F, F)
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
                val buffer = new Array[Char](1024)
                var i = 0
                val log = (home / ".server.log").string.value

                def flushLog(): Unit = {
                  val fs = new FileWriter(log, true)
                  try {
                    fs.write(buffer, 0, i)
                  } finally {
                    i = 0
                    fs.close()
                  }
                }

                val ps = new PrintStream(new OutputStream {
                  def w(b: Int): Unit = {
                    buffer(i) = (b & 0xFF).toChar
                    i += 1
                    if (i >= buffer.length || b == '\n') {
                      flushLog()
                    }
                  }

                  override def write(b: Int): Unit = buffer.synchronized(w(b))

                  override def write(b: Array[Byte], off: Int, len: Int): Unit = buffer.synchronized {
                    for (i <- 0 until len) {
                      w(b(off + i))
                    }
                  }

                  override def flush(): Unit = flushLog()
                })
                try {
                  System.setOut(ps)
                  System.setErr(ps)
                  return server.Server.run(version, o.message == Cli.SireumServerServerMessage.Msgpack, o.workers,
                    !o.noInputCache, !o.noTypeCache, o.log, o.verbose, javaHomeOpt.get, scalaHomeOpt.get,
                    home, versions.entries)
                } finally {
                  flushLog()
                }
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

  def nativ(): Unit = {
    val homeBin = homeOpt.get / "bin"
    val exePath: Os.Path = Os.kind match {
      case Os.Kind.Win => homeBin / "win" / "sireum.exe"
      case Os.Kind.Linux => homeBin / "linux" / "sireum"
      case Os.Kind.LinuxArm => homeBin / "linux" / "arm" / "sireum"
      case Os.Kind.Mac => homeBin / "mac" / "sireum"
      case _ => halt("Infeasible")
    }
    val d = Os.tempDir()
    val f = d / "sireum.jar"
    (homeBin / "sireum.jar").copyOverTo(f)
    proyek.Assemble.nativ(homeOpt.get, f)
    (f.up / exePath.name).canon.moveOverTo(exePath)
    d.removeAll()
  }
}