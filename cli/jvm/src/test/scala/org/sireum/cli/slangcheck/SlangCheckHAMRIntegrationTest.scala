package org.sireum.cli.slangcheck

import org.sireum._
import org.sireum.message.Reporter
import org.sireum.test.TestSuite
import org.sireum.tools.slangcheck.TestUtil

import java.io.{ByteArrayOutputStream, PrintStream}

class SlangCheckHAMRIntegrationTest extends TestSuite with TestUtil {
  val resourceDir: Os.Path = Os.path(implicitly[sourcecode.File].value).up.up.up.up.up.up.up.up.up.up / "tools" / "jvm" / "src" / "test" / "resources" / "org" / "sireum" / "tools" / "slangcheck" / "hamr"

  "isolette" in {
    test("isolette", "isolette")
  }

  "temp_control" in {
    test("temp_control_periodic", "tc")
  }

  val verbose: B = F

  def test(projName: String, packageName: String): Unit = {

    if (!willingToWait) {
      return
    }

    val resultsDir = copy(projName, "hamr_results")
    println(s"Results: ${resultsDir.toUri}")

    val oldOut = System.out
    val oldErr = System.err
    try {
      val outCapture = new ByteArrayOutputStream
      val errCapture = new ByteArrayOutputStream
      System.setOut(new PrintStream(outCapture))
      System.setErr(new PrintStream(errCapture))

      val json = (resourceDir / projName / "aadl" / ".slang").list.filter(p => p.ext == string"json")
      assert(json.size == 1)

      val proyekRootDir = resultsDir / "hamr" / "slang"

      var reporter = Reporter.create

      def check(tool:String, exitCode: Z): Unit = {
        if (verbose || exitCode != 0 || reporter.hasError) {
          reporter.printMessages()
          oldOut.println(s"Out: \n${outCapture.toString}"); oldOut.flush()
          oldErr.println(s"Err: \n${errCapture.toString}"); oldErr.flush()
        }
        assert(exitCode == 0 && !reporter.hasError, s"$tool failed")

        reporter = Reporter.create
        outCapture.reset()
        errCapture.reset()
      }

      oldOut.println("Running codegen which invokes SlangCheck via a callback ...")
      val hcmd = ISZ[String]("hamr", "codegen", "--no-proyek-ive", "--package-name", packageName, "--output-dir", proyekRootDir.value, json(0).value)
      val hresult = Sireum.run(hcmd, reporter)

      check("Codegen + SlangCheck", hresult)

      oldOut.println("Running proyek tipe ...")
      val tresults = Sireum.run(ISZ("proyek", "tipe", proyekRootDir.value), reporter)

      check("Tipe", tresults)

      val vprops = proyekRootDir / "version.properties"
      vprops.removeAll()
      println("Removed codegen generated version.properties before compiling")

      oldOut.println("Compiling via proyek ...")
      val cresults = Sireum.run(ISZ("proyek", "compile", proyekRootDir.value), reporter)

      check("Compiling", cresults)
    }
    finally {
      System.setErr(oldErr)
      System.setOut(oldOut)
    }
  }
}
