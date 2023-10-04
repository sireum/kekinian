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

    val proyekRootDir = resultsDir / "hamr" / "slang"

    val reporter = Reporter.create

    println("Running codegen ...")
    val hcmd = ISZ[String]("hamr", "codegen", "--no-proyek-ive", "--package-name", packageName, "--output-dir", proyekRootDir.value, json(0).value)
    val hresult = Sireum.run(hcmd, reporter)

    assert(hresult == 0 && !reporter.hasError, reporter.errors)

    val dataFilesArg: ISZ[String] = {
      val slangcheckBin = ops.StringOps((proyekRootDir / "bin" / "slangcheck.cmd").read)
      val searchString = "val files: ISZ[String] = ISZ("
      val start = slangcheckBin.stringIndexOf(searchString)

      def toFile(s: String): Os.Path = {
        val ss = ops.StringOps(ops.StringOps(s).trim)
        return proyekRootDir / ss.substring(4, ss.stringIndexOf("scala") + 5)
      }

      val raw = for (file <- ops.StringOps(ops.StringOps(slangcheckBin.substring(start + searchString.length, slangcheckBin.stringIndexOfFrom(")", start))).replaceAllLiterally("\r\n", "|")).split(c => c == C('|'))) yield toFile(file)
      for(r <- raw) yield r.value
    }

    val outputDir: Os.Path = (proyekRootDir / "src" / "main" / "data") /+ ops.StringOps(packageName).split(c => c == C('.'))

    val sccmd = ISZ[String]("proyek", "slangcheck", "-p", packageName, "-o", outputDir.value, proyekRootDir.value) ++ dataFilesArg
    val scresults = Sireum.run(sccmd, reporter)

    assert(scresults == 0 && !reporter.hasError, reporter.errors)

    println("Running tipe ...")
    Sireum.run(ISZ("proyek", "tipe", proyekRootDir.value), reporter)
    assert (!reporter.hasError, reporter.errors)

    val vprops = proyekRootDir / "version.properties"
    vprops.removeAll()
    println("Removed codegen generated version.properties before compiling")

    println("Compiling ...")
    Sireum.run(ISZ("proyek", "compile", proyekRootDir.value), reporter)
    assert (!reporter.hasError, reporter.errors)
  }
}
