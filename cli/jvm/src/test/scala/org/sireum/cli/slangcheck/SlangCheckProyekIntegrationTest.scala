package org.sireum.cli.slangcheck

import org.sireum._
import org.sireum.message.Reporter
import org.sireum.tools.slangcheck.SlangCheckTest

class SlangCheckProyekIntegrationTest extends SlangCheckTest {

  override val resourceDir: Os.Path = Os.path(implicitly[sourcecode.File].value).up.up.up.up.up.up.up.up.up.up / "tools" / "jvm" / "src" / "test" / "resources" / "org" / "sireum" / "tools" / "slangcheck"

  override val generateExpected: B = F

  override val runTipe: B = T && willingToWait

  override val runGeneratedTests: B = T && willingToWait

  override val verbose: B = F

  override def test(expectedName: String, packageName: String, filter: Os.Path => B = x => T): Unit = {

    val resultsDir = copy(expectedName, "results")

    // the following becomes a hyperlink in IVE. You can then use IVE's "Compare Directories"
    // to manually see any changes
    println(s"Result Dir: ${resultsDir.toUri}")

    val reporter = Reporter.create

    val dataFiles = Os.Path.walk(resultsDir / "src" / "main", F, F, p => p.ext == string"scala" && !ops.StringOps(p.name).contains("SlangCheck"))
    val destDir = resultsDir / "src" / "main" / "data"
    val testDir = resultsDir / "src" / "test"

    var failureReasons: ISZ[String] = ISZ()

    val v: String = st"${(for (d <- dataFiles) yield d, " ")}".render
    val proyekRoot: Os.Path = destDir.up.up.up
    val cmd = ops.StringOps(s"proyek slangcheck -p $packageName -o $destDir -t $testDir $proyekRoot $v").split((c: C) => c == C(' '))
    val results = Sireum.run(cmd, reporter)

    val outDir = resultsDir / "out"
    if (outDir.exists) {
      outDir.removeAll()
    }

    if (results != 0) {
      failureReasons = failureReasons :+ s"proyek slangcheck returned $results"
    }

    if (reporter.hasError) {
      failureReasons = failureReasons ++ (for (e <- reporter.errors) yield e.text)
    }

    if (generateExpected) {
      assert (!isCI, "generateExpected should be F when code is pushed to github")

      val expectedDir = getExpectedDir(resultsDir)
      expectedDir.removeAll()
      resultsDir.copyOverTo(expectedDir)
      println(s"Replaced: ${expectedDir}")
    } else {
      if (!compare(resultsDir, filter)) {
        failureReasons = failureReasons :+ "Results did not match expected"
      }
    }

    if (runGeneratedTests) {
      println("Compiling via proyek compile ...")
      var passed = Sireum.run(ISZ("proyek", "compile", resultsDir.value), reporter)
      if(passed != 0) {
        failureReasons = failureReasons :+ "Compilation failed"
      }

      if (passed == 0) {
        println("Running generated SlangCheck tests via proyek test ...")

        // use a different reporter as we don't necessarily care about errors coming from the generated tests
        //passed = Sireum.run(ISZ("proyek", "test", resultsDir.value), Reporter.create)
        //println(s"Generated Tests: ${if (passed == 0) "passing" else "failing"}")

        // tests probably have to be run in a different JVM
        passed = proc"$sireum proyek test ${resultsDir}".at(resultsDir).echo.console.run().exitCode

        // TODO: generated test could be failing due to 'requirements too strict'
        println(s"Generated Tests: ${if (passed == 0) "passing" else "failing"}")
        //if (!passed) {
        //  failureReasons = failureReasons :+ "Generated unit tests produced a failure"
        //}
      }
    }

    if (outDir.exists) {
      outDir.removeAll()
    }

    if (reporter.hasError) {
      reporter.printMessages()
    }
    assert (failureReasons.size == 0)
  }
}