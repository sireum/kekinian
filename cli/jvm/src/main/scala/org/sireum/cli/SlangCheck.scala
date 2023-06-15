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
package org.sireum.cli

import org.sireum._
import org.sireum.message.Reporter
import org.sireum.lang.{FrontEnd, ast => AST}
import org.sireum.lang.tipe.{TypeChecker, TypeHierarchy, TypeOutliner}
import org.sireum.tools.{SlangCheck => SC}
import java.io.File
import java.net.URLClassLoader
import java.util.concurrent.ConcurrentLinkedQueue

object SlangCheck {

  val kind: String = "SlangCheck"
  val ARGS_ERROR: Z = -2
  val OUTPUT_REQUIRED: Z = -3
  val OUTPUT_NOT_FILE: Z = -4
  val INPUT_MISSING: Z = -4
  val EXEC_MISSING: Z = -5
  val DUMP_MISSING: Z = -6

  def run(o: Cli.SireumToolsSlangcheckRunnerOption, reporter: Reporter): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      return 0
    }
    val urls = for (p <- o.classpath.elements) yield new File(Os.path(p).canon.string.value).toURI.toURL
    val cl = new URLClassLoader(urls.toArray, getClass.getClassLoader)
    val testRunner: Random.Gen.TestRunner[AnyRef] = o.args match {
      case ISZ(className) => cl.loadClass(className.value).getDeclaredConstructor().newInstance().asInstanceOf[Random.Gen.TestRunner[AnyRef]]
      case _ =>
        reporter.error(None(), kind, s"Expecting a single fully-qualified class name argument")
        return ARGS_ERROR
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
        reporter.error(None(), kind, s"The output option has to be specified")
        return OUTPUT_REQUIRED
    }
    if (output.exists && !output.isFile) {
      reporter.error(None(), kind, s"The output path is not a file")
      return OUTPUT_NOT_FILE
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


  def generate(o: Cli.SireumToolsSlangcheckGeneratorOption, reporter: Reporter): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      return 0
    }

    val outputDir = Os.path(if (o.outputDir.nonEmpty) o.outputDir.get else ".")
    val testDir = Os.path(if (o.testDir.nonEmpty) o.testDir.get else ".")

    val files: ISZ[Os.Path] = for (arg <- o.args) yield Os.path(arg)
    for (f <- files if !f.exists || !f.isFile) {
      halt(s"$f is not a file")
    }

    val reporter = Reporter.create

    if (files.isEmpty) { //Checks if files are present
      reporter.error(None(), "Gen", "Expecting a program input")
      return -1
    }

    def readFile(f: Os.Path): (Option[String], String) = {
      return (Some(f.toUri), f.read)
    }

    var programs = ISZ[AST.TopUnit.Program]() //list of all slang programs
    for (src <- files) { //go through all folders
      val srcText = src.read //read files
      val r = lang.parser.Parser.parseTopUnit[AST.TopUnit](srcText, F, F, Some(src.toUri), reporter) //parse program
      r match { //adds all programs that are valid to programs
        case Some(p: AST.TopUnit.Program) =>
          programs = programs :+ p
        case _ =>
          reporter.error(None(), "Gen", s"$src is not a Slang program")
          return -1
      }
    }


    var sources2 = ISZ[FrontEnd.Input]()
    for (p <- files) {
      val x = readFile(p)
      sources2 = sources2 :+ FrontEnd.Input(x._2, x._1)
    }

    var th: TypeHierarchy = {
      val (thl, rep): (TypeHierarchy, Reporter) = {
        val p = FrontEnd.libraryReporter
        (p._1.typeHierarchy, p._2)
      }

      if (rep.hasError) {
        rep.printMessages()
      }

      thl
    }

    val t = FrontEnd.parseProgramAndGloballyResolve(0, sources2,
      th.nameMap, th.typeMap)

    th = TypeHierarchy.build(F, th(nameMap = t._3, typeMap = t._4), reporter)

    th = TypeOutliner.checkOutline(0, T, th, reporter)

    th = TypeChecker.checkComponents(0, T, th, th.nameMap, th.typeMap, reporter)


    val results: ISZ[(ISZ[String], ST)] = SC.gen(for (source <- files) yield source.toUri, programs, reporter, th)

    if (!reporter.hasError) {
      for (r <- results) {
        val destFile = outputDir /+ r._1
        destFile.writeOver(r._2.render)
        println(s"Wrote: $destFile")
      }
    }

    print()

    return if (reporter.hasError) 1 else 0
  }

  def test(args: ISZ[String], o: Cli.SireumToolsSlangcheckTesterOption, reporter: Reporter): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      return 0
    }
    val input: Os.Path = o.input match {
      case Some(i) => Os.path(i).canon
      case _ =>
        reporter.error(None(), kind, s"No input is specified")
        return INPUT_MISSING
    }

    if (!input.exists) {
      reporter.error(None(), kind, s"The specified input does not exist")
      return INPUT_MISSING
    }
    val (outputPassing, outputFailing): (Os.Path, Os.Path) = o.output match {
      case Some(out) =>
        val op = Os.path(out).canon
        val oParent = op.up.canon
        (oParent / s"${op.name}.passing", oParent / s"${op.name}.failing")
      case _ =>
        reporter.error(None(), kind, s"The output option has to be specified")
        return OUTPUT_REQUIRED
    }
    if (outputPassing.exists && !outputPassing.isFile) {
      reporter.error(None(), kind, s"The output passing path is not a file")
      return OUTPUT_NOT_FILE
    }
    if (outputFailing.exists && !outputFailing.isFile) {
      reporter.error(None(), kind, s"The output failing path is not a file")
      return OUTPUT_NOT_FILE
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
            reporter.error(None(), kind, s"Expecting a single fully-qualified class name argument")
            return ARGS_ERROR
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
          return EXEC_MISSING
        }
        if (dump.list.isEmpty) {
          eprintln(s"$dump was not generated")
          return DUMP_MISSING
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
