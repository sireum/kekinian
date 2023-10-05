package org.sireum.cli.slangcheck

import org.scalatest.BeforeAndAfterAll
import org.sireum._
import org.sireum.message.Reporter
import org.sireum.tools.slangcheck.SlangCheckTest

import java.io.{ByteArrayOutputStream, PrintStream}

class SlangCheckProyekIntegrationTest extends SlangCheckTest with BeforeAndAfterAll {

  override val resourceDir: Os.Path = Os.path(implicitly[sourcecode.File].value).up.up.up.up.up.up.up.up.up.up / "tools" / "jvm" / "src" / "test" / "resources" / "org" / "sireum" / "tools" / "slangcheck"

  override val generateExpected: B = F

  override val verbose: B = F

  override val runTipe: B = T && willingToWait

  override val runGeneratedTests: B = T && willingToWait

  override def test(expectedName: String, packageName: String, filter: Os.Path => B = x => T): Unit = {

    val reporter = Reporter.create
    var failureReasons: ISZ[String] = ISZ()

    val proyekRootDir = copy(expectedName, "results")

    // the following becomes a hyperlink in IVE. You can then use IVE's "Compare Directories"
    // to manually see any changes
    println(s"Result Dir: ${proyekRootDir.toUri}")

    // use separate out/err streams when verbose is false
    val oldOut = System.out
    val oldErr = System.err

    val outCapture = if (verbose) Console.out else new ByteArrayOutputStream
    val errCapture = if (verbose) Console.err else new ByteArrayOutputStream
    try {
      System.setOut(if (verbose) oldOut else new PrintStream(outCapture))
      System.setErr(if (verbose) oldErr else new PrintStream(errCapture))

      val dataDir = proyekRootDir / "src" / "main" // pointing at main in order to pickup art.art.DataContent
      val dataFiles = Os.Path.walk(dataDir, F, F, p => p.ext == string"scala" && !ops.StringOps(p.name).contains("SlangCheck"))
      val outputDir = dataDir / "data" /+ ops.StringOps(packageName).split(c => c == c".")
      val testDir = proyekRootDir / "src" / "test"
      val dataFilesArg: ISZ[String] = for (f <- dataFiles) yield f.value

      oldOut.println("Generating slangcheck artifacts ...")
      val cmd = ISZ[String]("proyek", "slangcheck", "-p", packageName, "-o", outputDir.value, "-t", testDir.value, proyekRootDir.value) ++ dataFilesArg
      var passing = Sireum.run(cmd, reporter)

      val proyekOutDir = proyekRootDir / "out"
      if (proyekOutDir.exists) {
        proyekOutDir.removeAll()
      }

      if (passing != 0) {
        failureReasons = failureReasons :+ s"proyek slangcheck returned $passing"
      } else {
        if (generateExpected) {
          assert(!isCI, "generateExpected should be F when code is pushed to github")

          val expectedDir = getExpectedDir(proyekRootDir)
          expectedDir.removeAll()
          proyekRootDir.copyOverTo(expectedDir)
          oldOut.println(s"Replaced: ${expectedDir}")
        } else {
          if (!compare(proyekRootDir, filter)) {
            failureReasons = failureReasons :+ "Results did not match expected"
          }
        }
      }

      if (runGeneratedTests) {
        oldOut.println("Compiling generated artifacts via proyek compile ...")
        passing = Sireum.run(ISZ("proyek", "compile", proyekRootDir.value), reporter)
        if (passing != 0) {
          failureReasons = failureReasons :+ "Compilation failed"
        } else {
          oldOut.println("Running generated SlangCheck tests via proyek test ...")

          val genTestsPassing = Sireum.run(ISZ("proyek", "test", proyekRootDir.value), reporter)

          oldOut.println(s"Generated Tests: ${if (genTestsPassing == 0) "passing" else "failing"}")
          //if (!genTestsPassing) {
          //  failureReasons = failureReasons :+ "Generated unit tests produced a failure"
          //}
        }
      }

      if (proyekOutDir.exists) {
        proyekOutDir.removeAll()
      }

      if (reporter.hasError) {
        reporter.printMessages()
        failureReasons = failureReasons ++ (for (e <- reporter.errors) yield e.text)
      }

      assert(failureReasons.size == 0)
    } finally {
      System.setOut(oldOut)
      System.setErr(oldErr)
    }
  }
}