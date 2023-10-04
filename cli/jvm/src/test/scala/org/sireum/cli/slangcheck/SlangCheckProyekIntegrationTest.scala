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

    val proyekRootDir = copy(expectedName, "results")

    // the following becomes a hyperlink in IVE. You can then use IVE's "Compare Directories"
    // to manually see any changes
    println(s"Result Dir: ${proyekRootDir.toUri}")

    val reporter = Reporter.create
    var failureReasons: ISZ[String] = ISZ()

    val dataDir = proyekRootDir / "src" / "main" // pointing at main in order to pickup art.art.DataContent
    val dataFiles = Os.Path.walk(dataDir, F, F, p => p.ext == string"scala" && !ops.StringOps(p.name).contains("SlangCheck"))
    val outputDir = dataDir / "data" /+ ops.StringOps(packageName).split(c => c == c".")
    val testDir = proyekRootDir / "src" / "test"
    val dataFilesArg: ISZ[String] = for(f <- dataFiles) yield f.value


    val cmd = ISZ[String]("proyek", "slangcheck", "-p", packageName, "-o", outputDir.value, "-t", testDir.value, proyekRootDir.value) ++ dataFilesArg
    val results = Sireum.run(cmd, reporter)

    val outDir = proyekRootDir / "out"
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

      val expectedDir = getExpectedDir(proyekRootDir)
      expectedDir.removeAll()
      proyekRootDir.copyOverTo(expectedDir)
      println(s"Replaced: ${expectedDir}")
    } else {
      if (!compare(proyekRootDir, filter)) {
        failureReasons = failureReasons :+ "Results did not match expected"
      }
    }

    if (runGeneratedTests) {
      println("Compiling via proyek compile ...")
      var passed = Sireum.run(ISZ("proyek", "compile", proyekRootDir.value), reporter)
      if(passed != 0) {
        failureReasons = failureReasons :+ "Compilation failed"
      }

      if (passed == 0) {
        println("Running generated SlangCheck tests via proyek test ...")

        // use a different reporter as we don't necessarily care about errors coming from the generated tests
        // as they may relate to 'requirements too strict'
        passed = Sireum.run(ISZ("proyek", "test", proyekRootDir.value), Reporter.create)
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