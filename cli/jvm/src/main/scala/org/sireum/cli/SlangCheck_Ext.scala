package org.sireum.cli

import org.sireum._
import org.sireum.message.Reporter

import java.io.File
import java.net.URLClassLoader
import java.util.concurrent.ConcurrentLinkedQueue

object SlangCheck_Ext {

  def run(o: Cli.SireumToolsSlangcheckRunnerOption, reporter: Reporter): Z = {
    val urls = for (p <- o.classpath.elements) yield new File(Os.path(p).canon.string.value).toURI.toURL
    val cl = new URLClassLoader(urls.toArray, getClass.getClassLoader)
    val testRunner: Random.Gen.TestRunner[AnyRef] = o.args match {
      case ISZ(className) => cl.loadClass(className.value).getDeclaredConstructor().newInstance().asInstanceOf[Random.Gen.TestRunner[AnyRef]]
      case _ =>
        reporter.error(None(), SlangCheck.kind, s"Expecting a single fully-qualified class name argument")
        return SlangCheck.ARGS_ERROR
    }
    val maxCores = Runtime.getRuntime.availableProcessors
    val cores = o.par match {
      case Some(n) =>
        if (0 < n & n <= maxCores) n.toInt
        else if (n > maxCores) maxCores
        else 1
      case _ => 1
    }
    val output = o.output match {
      case Some(o) => Os.path(o)
      case _ =>
        reporter.error(None(), SlangCheck.kind, s"The output option has to be specified")
        return SlangCheck.OUTPUT_REQUIRED
    }
    if (output.exists && !output.isFile) {
      reporter.error(None(), SlangCheck.kind, s"The output path is not a file")
      return SlangCheck.OUTPUT_NOT_FILE
    }
    output.removeAll()
    output.up.mkdirAll()

    var numOfTestObjects: Z = 0

    def shouldContinue: B = o.max <= 0 || numOfTestObjects < o.max

    var finishedThreads = 0
    val threads = for (_ <- 0 until cores) yield new Thread {
      override def run(): Unit = {
        try {
          val t = Thread.currentThread
          while (!t.isInterrupted && shouldContinue) {
            val obj = testRunner.next()
            val json = testRunner.toCompactJson(obj).value
            testRunner.synchronized {
              if (shouldContinue) {
                numOfTestObjects = numOfTestObjects + 1
                output.writeAppend(s"$json\n")
                println(s"Generated test object #$numOfTestObjects")
              }
            }
          }
        } catch {
          case _: InterruptedException =>
        }
        testRunner.synchronized {
          finishedThreads = finishedThreads + 1
        }
      }
    }
    for (t <- threads) t.start()
    try {
      val t = if (o.max == 0 && o.timeout == 0) 1000L else o.timeout.toLong * 1000L
      Thread.sleep(t)
    } finally {
      for (t <- threads) t.interrupt()
    }
    while (cores != finishedThreads) {
      try {
        Thread.sleep(100)
      } catch {
        case _: Throwable =>
      }
    }
    println(s"Generated $numOfTestObjects number of test objects")
    println()

    if (numOfTestObjects <= 0) return 0

    def file(): Os.Path = {
      val p7za = Os.kind match {
        case Os.Kind.Mac => Sireum.homeOpt.get / "bin" / "mac" / "7za"
        case Os.Kind.Linux => Sireum.homeOpt.get / "bin" / "linux" / "7za"
        case Os.Kind.LinuxArm => Sireum.homeOpt.get / "bin" / "linux" / "arm" / "7za"
        case Os.Kind.Win => Sireum.homeOpt.get / "bin" / "win" / "7za.exe"
        case Os.Kind.Unsupported =>
          println(s"Compressing $output ...")
          proc"gzip $output.dsc.gz $output".echo.console.runCheck()
          return (output.up / s"${output.name}.dsc.gz").canon
      }
      println(s"Compressing $output ...")
      proc"$p7za a $output.dsc.7z $output".echo.console.runCheck()
      println()
      (output.up / s"${output.name}.dsc.7z").canon
    }

    val p = file()

    o.scp match {
      case Some(server) =>
        println(s"Copying to $server ...")
        proc"scp $p $server".echo.console.runCheck()
        println()
      case _ =>
    }
    return 0
  }

  def test(args: ISZ[String], o: Cli.SireumToolsSlangcheckTesterOption, reporter: Reporter): Z = {
    val input: Os.Path = o.input match {
      case Some(i) => Os.path(i).canon
      case _ =>
        reporter.error(None(), SlangCheck.kind, s"No input is specified")
        return SlangCheck.INPUT_MISSING
    }

    if (!input.exists) {
      reporter.error(None(), SlangCheck.kind, s"The specified input does not exist")
      return SlangCheck.INPUT_MISSING
    }
    val (outputPassing, outputFailing): (Os.Path, Os.Path) = o.output match {
      case Some(out) =>
        val op = Os.path(out).canon
        val oParent = op.up.canon
        (oParent / s"${op.name}.passing", oParent / s"${op.name}.failing")
      case _ =>
        reporter.error(None(), SlangCheck.kind, s"The output option has to be specified")
        return SlangCheck.OUTPUT_REQUIRED
    }
    if (outputPassing.exists && !outputPassing.isFile) {
      reporter.error(None(), SlangCheck.kind, s"The output passing path is not a file")
      return SlangCheck.OUTPUT_NOT_FILE
    }
    if (outputFailing.exists && !outputFailing.isFile) {
      reporter.error(None(), SlangCheck.kind, s"The output failing path is not a file")
      return SlangCheck.OUTPUT_NOT_FILE
    }
    outputPassing.removeAll()
    outputFailing.removeAll()
    outputPassing.up.mkdirAll()
    outputFailing.up.mkdirAll()
    outputPassing.write("")
    outputFailing.write("")

    val sireumHome = Sireum.homeOpt.get
    val javaExe = Sireum.javaHomeOpt.get / "bin" / (if (Os.isWin) "java.exe" else "java")
    val jacocoCli = sireumHome / "lib" / "jacococli.jar"
    val p7za = Os.kind match {
      case Os.Kind.Mac => sireumHome / "bin" / "mac" / "7za"
      case Os.Kind.Linux => sireumHome / "bin" / "linux" / "7za"
      case Os.Kind.LinuxArm => sireumHome / "bin" / "linux" / "arm" / "7za"
      case Os.Kind.Win => sireumHome / "bin" / "win" / "7za.exe"
      case Os.Kind.Unsupported => halt("Unsupported platform")
    }
    o.coverage match {
      case Some(p) =>
        val prefix = Os.path(p)
        val exec = (prefix.up / s"${prefix.name}.exec").canon
        val dump = (prefix.up / s"${prefix.name}.dump").canon
        exec.removeAll()
        dump.removeAll()
        dump.mkdirAll()
        val i = ops.ISZOps(args).indexOf("--coverage")
        var newArgs = ISZ[String]()
        for (j <- 0 until args.size) {
          if (j != i && j != i + 1) {
            newArgs = newArgs :+ args(j)
          }
        }
        val jacocoAgent = sireumHome / "lib" / "jacocoagent.jar"
        val commands = ISZ[String](javaExe.string, s"-javaagent:$jacocoAgent=destfile=$exec,classdumpdir=$dump",
          "-jar", (sireumHome / "bin" / "sireum.jar").string) ++ newArgs
        val exitCode = Os.proc(commands).console.run().exitCode
        if (exitCode != 0) {
          return exitCode
        }
      case _ =>
        val paths = for (p <- o.classpath) yield Os.path(p).canon
        val urls = for (p <- paths) yield new File(p.string.value).toURI.toURL
        val cl = new URLClassLoader(urls.elements.toArray, getClass.getClassLoader)
        val testRunner: Random.Gen.TestRunner[AnyRef] = o.args match {
          case ISZ(className) => cl.loadClass(className.value).getDeclaredConstructor().newInstance().asInstanceOf[Random.Gen.TestRunner[AnyRef]]
          case _ =>
            reporter.error(None(), SlangCheck.kind, s"Expecting a single fully-qualified class name argument")
            return SlangCheck.ARGS_ERROR
        }

        val maxCores = Runtime.getRuntime.availableProcessors
        val cores = o.par match {
          case Some(n) =>
            if (0 < n & n <= maxCores) n.toInt
            else if (n > maxCores) maxCores
            else 1
          case _ => 1
        }
        System.setProperty("org.sireum.silenthalt", "true")
        val queue = new ConcurrentLinkedQueue[Predef.String]
        var finishedThreads = 0
        var numOfPassingObjects = 0
        var numOfFailingObjects = 0
        val threads = for (_ <- 0 until cores) yield new Thread {
          override def run(): Unit = {
            try {
              val t = Thread.currentThread
              while (!t.isInterrupted) {
                val json = queue.poll
                if (json != null) {
                  val obj = testRunner.fromJson(json)
                  try {
                    if (testRunner.test(obj)) {
                      testRunner.synchronized {
                        numOfPassingObjects = numOfPassingObjects + 1
                        outputPassing.writeAppend(s"$json\n")
                      }
                    } else {
                      testRunner.synchronized {
                        numOfFailingObjects = numOfFailingObjects + 1
                        outputFailing.writeAppend(s"$json\n")
                      }
                    }
                  } catch {
                    case e: InterruptedException => throw e
                    case _: Throwable =>
                      testRunner.synchronized {
                        numOfFailingObjects = numOfFailingObjects + 1
                        outputFailing.writeAppend(s"$json\n")
                      }
                  }
                } else {
                  Thread.sleep(100)
                }
              }
            } catch {
              case _: InterruptedException =>
            }
            testRunner.synchronized {
              finishedThreads = finishedThreads + 1
            }
          }
        }
        for (t <- threads) t.start()

        def readLines(f: Os.Path): Unit = {
          for (l <- f.readLineStream) {
            queue.add(l.value)
          }
        }

        def process(p: Os.Path): Unit = {
          if (p.isFile) {
            val pNameOps = ops.StringOps(p.name)
            if (pNameOps.endsWith(".dsc.7z")) {
              val d = Os.tempDir()
              proc"$p7za x $p".at(d).runCheck()
              for (path <- d.list if path.isFile) readLines(path)
              d.removeAll()
            } else if (pNameOps.endsWith(".dsc.gz")) {
              val d = Os.tempDir()
              proc"gunzip --keep $p".at(d).runCheck()
              for (path <- d.list if path.isFile) readLines(path)
              d.removeAll()
            }
          } else if (p.isDir) {
            for (e <- p.list) process(e)
          }
        }

        process(input)
        while (!queue.isEmpty) {
          try {
            Thread.sleep(100)
          } catch {
            case _: Throwable =>
          }
        }
        for (t <- threads) t.interrupt()
        while (cores != finishedThreads) {
          try {
            Thread.sleep(100)
          } catch {
            case _: Throwable =>
          }
        }

        def file(): (Os.Path, Os.Path) = {
          println(s"Compressing $outputPassing ...")
          proc"$p7za a $outputPassing.dsc.7z $outputPassing".echo.console.runCheck()
          println(s"Compressing $outputFailing ...")
          proc"$p7za a $outputFailing.dsc.7z $outputFailing".echo.console.runCheck()
          println()
          ((outputPassing.up / s"${outputPassing.name}.dsc.7z").canon, (outputFailing.up / s"${outputFailing.name}.dsc.7z").canon)
        }

        val p = file()

        o.scp match {
          case Some(server) =>
            println(s"Copying to $server ...")
            proc"scp ${p._1} ${p._2} $server".echo.console.runCheck()
            println()
          case _ =>
        }

        println(s"Passing: $numOfPassingObjects, Failing: $numOfFailingObjects")
        println()
    }

    o.coverage match {
      case Some(p) =>
        val prefix = Os.path(p)
        val exec = (prefix.up / s"${prefix.name}.exec").canon
        val dump = (prefix.up / s"${prefix.name}.dump").canon

        if (!exec.exists) {
          eprintln(s"$exec was not generated")
          return SlangCheck.EXEC_MISSING
        }
        if (dump.list.isEmpty) {
          eprintln(s"$dump was not generated")
          return SlangCheck.DUMP_MISSING
        }
        val csv = (prefix.up / s"${prefix.name}.coverage.csv").canon
        val html = (prefix.up / s"${prefix.name}.coverage").canon
        csv.removeAll()
        html.removeAll()
        println("Generating coverage report ...")
        println(s"* $csv")
        println(s"* $html")
        val srcDir = Os.tempDir()
        val pred: Os.Path => B = (p: Os.Path) => p.ext.value == "scala" || p.ext.value == "java"
        for (p <- o.sourcepath) {
          val path = Os.path(p).canon
          if (path.exists) {
            if (path.ext.value == "jar") {
              path.unzipTo(srcDir)
            } else {
              path.overlayCopy(dump, F, F, pred, F)
            }
          }
        }
        srcDir.overlayCopy(dump, F, F, pred, F)
        srcDir.removeAll()
        val commands = ISZ[String](javaExe.string, "-jar", jacocoCli.string, "report", exec.string, "--encoding",
          "UTF-8", "--classfiles", dump.string, "--csv", csv.string, "--html", html.string, "--sourcefiles", dump.string)
        Os.proc(commands).runCheck()
        println()
      case _ =>
    }

    return 0
  }
}
