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

      def check(tool: String, exitCode: Z): Unit = {
        if (verbose || exitCode != 0 || reporter.hasError) {
          reporter.printMessages()
          oldOut.println(s"Out: \n${outCapture.toString}");
          oldOut.flush()
          oldErr.println(s"Err: \n${errCapture.toString}");
          oldErr.flush()
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
      val hcmd = ISZ[String]("hamr", "codegen", "--no-proyek-ive", "--package-name", packageName, "--slang-output-dir", proyekRootDir.value, json(0).value)
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

  var osateHome: Option[Os.Path] = Os.env("OSATE_HOME") match {
    case Some(p) => Some(Os.path(p))
    case _ => None()
  }

  override def beforeAll(): Unit = {
    if (!willingToWait || osateHome.nonEmpty) {
      return
    }
    Os.env("SIREUM_HOME") match {
      case Some(h) =>
        val sireumHome = Os.path(h)
        val pv = "phantom_versions.properties"
        val loc: (Option[Os.Path], ISZ[String]) = {
          // jenkins Sireum-Kekinian is setup to delete the workspace before building
          // so setup an osate-cache in the parent directory to speed up builds
          val cacheLoc =
            if (Os.env("JENKINS_HOME").nonEmpty) sireumHome.up / "osate-cache"
            else sireumHome
          if (Os.isWin) (Some(cacheLoc / "bin" / "win" / "osate"), ISZ(pv))
          else if (Os.isMac) (Some(cacheLoc / "bin" / "mac" / "osate.app"), ISZ("Contents", "Eclipse", pv))
          else if (Os.isLinux) (Some(cacheLoc / "bin" / "linux" / "osate"), ISZ(pv))
          else (None(), ISZ())
        }


        if (loc._1.isEmpty) {
          assert(F, s"Unsupported operating system: ${Os.kind}")
          halt("Infeasible assuming -ea")
        }
        osateHome = loc._1

        val phantomVer = Os.path(implicitly[sourcecode.File].value).up.up.up.up.up.up.up.up.up.up / "hamr" / "codegen" / "jvm" / "src" / "main" / "resources" / pv
        val cachedVer = osateHome.get /+ loc._2

        def iu(installing: B): Unit = {
          println(s"${if (installing) "Installing" else "Updating"} OSATE ${if(installing) "to" else "at" } ${osateHome.get} (this will take a while) ...")
          val sireum = sireumHome / "bin" / (if (Os.isWin) "sireum.bat" else "sireum")
          val oresults = proc"$sireum hamr phantom -u -o ${osateHome.get}".at(sireumHome).run()
          if (oresults.ok) {
            cachedVer.writeOver(phantomVer.read)
          } else {
            println(oresults.out)
            println(oresults.err)
            assert(F, s"OSATE ${if (installing) "installation" else "update"} failed")
          }
        }

        if (!osateHome.get.exists) {
          iu(T)
        } else if (phantomVer.read != cachedVer.read) {
          iu(F)
        } else {
          println("OSATE up-to-date")
        }

      case _ =>
        assert(F, "Please set the SIREUM_HOME environment variable")
        halt("Infeasible assuming -ea")
    }
  }
}
