package org.sireum.cli.slangcheck

import org.sireum._
import org.sireum.message.Reporter
import org.sireum.test.TestSuite
import org.sireum.tools.SlangCheckJvm
import org.sireum.tools.slangcheck.TestUtil

class SlangCheckHAMRIntegrationTest extends TestSuite with TestUtil {
  val resourceDir: Os.Path = Os.path(implicitly[sourcecode.File].value).up.up.up.up.up.up.up.up.up.up / "tools" / "jvm" / "src" / "test" / "resources" / "org" / "sireum" / "tools" / "slangcheck" / "hamr"

  "isolette" in {
    test("isolette", "isolette")
  }

  "temp_control" in {
    test("temp_control_periodic", "tc")
  }

  def test(projName: String, packageName: String): Unit = {

    if (!willingToWait) {
      return
    }

    val resultsDir = copy(projName, "hamr_results")

    println(s"Results: ${resultsDir.toUri}")

    val json = (resourceDir / projName / "aadl" / ".slang").list.filter(p => p.ext == string"json")
    assert(json.size == 1)

    val outDir = resultsDir / "hamr" / "slang"

    val reporter = Reporter.create

    println("Running codegen ...")
    val cmd = ISZ[String]("hamr", "codegen", "--no-proyek-ive", "--package-name", packageName, "--output-dir", outDir.value, json(0).value)
    Sireum.run(cmd, reporter)

    assert(!reporter.hasError, reporter.errors)

    val dataFiles = {
      val slangcheckBin = ops.StringOps((outDir / "bin" / "slangcheck.cmd").read)
      val searchString = "val files: ISZ[String] = ISZ("
      val start = slangcheckBin.stringIndexOf(searchString)

      def toFile(s: String): Os.Path = {
        val ss = ops.StringOps(ops.StringOps(s).trim)
        return outDir / ss.substring(4, ss.stringIndexOf("scala") + 5)
      }

      for (file <- ops.StringOps(ops.StringOps(slangcheckBin.substring(start + searchString.length, slangcheckBin.stringIndexOfFrom(")", start))).replaceAllLiterally("\r\n", "|")).split(c => c == C('|'))) yield toFile(file)
    }

    val results = SlangCheckJvm.run(ISZ(packageName), dataFiles, reporter)

    assert(!reporter.hasError, reporter.errors)

    val dataDir = outDir / "src" / "main" / "data" / packageName
    for (r <- results._1) {
      val destFile = dataDir /+ r._1
      assert(destFile.exists)
      destFile.writeOver(ops.StringOps(r._2.render).replaceAllLiterally("\n\n", "\n"))
      println(s"Replaced: $destFile")
    }

    println("Running tipe ...")
    Sireum.run(ISZ("proyek", "tipe", outDir.value), reporter)
    assert (!reporter.hasError, reporter.errors)

    val vprops = outDir / "version.properties"
    vprops.removeAll()
    println("Removed codegen generated version.properties before compiling")

    println("Compiling ...")
    Sireum.run(ISZ("proyek", "compile", outDir.value), reporter)
    assert (!reporter.hasError, reporter.errors)
  }
}
