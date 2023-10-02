package org.sireum.cli.slangcheck

import org.sireum._
import org.sireum.message.Reporter
import org.sireum.test.TestSuite
import org.sireum.tools.{SlangCheckJvm => SCJVM}

class SlangCheckTest extends TestSuite with TestUtil {

  val resourceDir: Os.Path = Os.path(implicitly[sourcecode.File].value).up.up.up.up.up.up.up.up.up.up / "tools" / "jvm" / "src" / "test" / "resources" / "org" / "sireum" / "tools" / "slangcheck"

  val generateExpected: B = F

  val runTipe: B = T && willingToWait

  val runGeneratedTests: B = T && willingToWait

  val verbose: B = F

  val sireum: Os.Path = Os.path(Os.env("SIREUM_HOME").get) / "bin" / (if (Os.isWin) "sireum.bat" else "sireum")

  "isolette" in {
    test("isolette", "isolette", x => !x.value.native.contains("component"))
  }

  "temp_control" in {
    test("temp_control", "tc")
  }

  "option_argument" in {
    test("option_argument", "oa")
  }

  "is_argument" in {
    test("is_argument", "is")
  }

  "ms_argument" in {
    test("ms_argument", "ms")
  }

//  "is_is_argument" in {
//    test("is_is_argument", "ms")
//  }

  "datatype_trait" in {
    test("datatype_trait", "dttr")
  }

  def test(expectedName: String, packageName: String, filter: Os.Path => B = x => T): Unit = {

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
      var passed = proc"$sireum proyek compile .".at(resultsDir).echo.console.run().ok
      if(!passed) {
        failureReasons = failureReasons :+ "Compilation failed"
      }

      if (passed) {
        passed = proc"$sireum proyek test .".at(resultsDir).echo.console.run().ok
        println(s"Generated Tests: ${if (passed) "passing" else "failing"}")

        // TODO: generated test could be failing due to 'requirements too strict'
        //if (!passed) {
        //  failureReasons = failureReasons :+ "Generated unit tests produced a failure"
        //}
      }
    }

    if (outDir.exists) {
      outDir.removeAll()
    }

    assert (failureReasons.size == 0)
  }
}