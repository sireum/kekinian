package org.sireum.cli.slangcheck

import org.scalatest.BeforeAndAfterAll
import org.sireum._
import org.sireum.message.Reporter
import org.sireum.test.TestSuite
import org.sireum.tools.slangcheck.TestUtil

import java.io.{ByteArrayOutputStream, PrintStream}

class SlangCheckHAMRIntegrationTest extends TestSuite with TestUtil with BeforeAndAfterAll {
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

      val aadlDir = resourceDir / projName / "aadl"

      oldOut.println(s"Regenerating AIR/JSON via Phantom using ${osateHome.get} ...")
      (aadlDir / ".slang").removeAll()
      val ocmd = ISZ[String]("hamr", "phantom", "-o", osateHome.get.value, aadlDir.value)
      val oresult = Sireum.run(ocmd, reporter)

      check("Phantom", oresult)

      val json = (aadlDir / ".slang").list.filter(p => p.ext == string"json")
      assert(json.size == 1, s"Expecting a single AIR/JSON file but found ${json.size}")

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

  var osateHome: Option[Os.Path] = None()

  override def beforeAll(): Unit = {
    val cand: Option[Os.Path] = Os.env("OSATE_HOME") match {
      case Some(h) => Some(Os.path(h))
      case _ =>
        if (Os.kind == Os.Kind.LinuxArm || (Os.isMac && Os.prop("os.arch").get == string"aarch64")) {
          None()
        } else {
          Os.env("SIREUM_HOME") match {
            case Some(h) =>
              val sireumHome = Os.path(h)
              println("Installing/Updating FMIDE ...")
              val results = proc"${sireumHome / "bin" / "install" / "fmide.cmd"}".at(sireumHome).run()
              if (!results.ok) {
                println(results.out)
                println(results.err)
                assert(F, "FMIDE install failed")
              }
              if (Os.isWin) Some(sireumHome / "bin" / "win" / "fmide")
              else if (Os.isMac) Some(sireumHome / "bin" / "mac" / "fmide.app")
              else if (Os.isLinux) Some(sireumHome / "bin" / "linux" / "fmide")
              else None()
            case _ =>
              assert(F, "Please set the SIREUM_HOME environment variable")
              halt("Infeasible assuming -ea")
          }
        }
    }

    if (cand.isEmpty || !cand.get.exists) {
      assert(F, "Please install FMIDE or point to an OSATE installation via the OSATE_HOME environment variable")
    } else {
      osateHome = cand
    }
  }
}
