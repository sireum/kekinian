/*
 Copyright (c) 2017-2026,Robby, Kansas State University
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
import org.sireum.test._

import java.io.{ByteArrayOutputStream, PrintStream}
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, LinkOption, Path, Paths}
import _root_.scala.jdk.CollectionConverters._

class ProyekSagaReportCliTest extends TestSuite {

  private def tempRoot[A](f: Path => A): A = {
    val home = Paths.get(System.getenv("SIREUM_HOME")).toAbsolutePath.normalize
    val base = home.resolve("out").resolve("saga-report-cli-test-temp")
    Files.createDirectories(base)
    val root = Files.createTempDirectory(base, "case-")
    try f(root)
    finally deleteTree(root)
  }

  private def deleteTree(root: Path): Unit = {
    if (Files.exists(root, LinkOption.NOFOLLOW_LINKS)) {
      val stream = Files.walk(root)
      try {
        stream.iterator.asScala.toVector.sortBy(_.getNameCount).reverse.foreach(Files.deleteIfExists)
      } finally {
        stream.close()
      }
    }
  }

  private def captureRun(args: ISZ[String]): (Z, Predef.String, Predef.String) = {
    val oldOut = System.out
    val oldErr = System.err
    val out = new ByteArrayOutputStream
    val err = new ByteArrayOutputStream
    try {
      System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8))
      System.setErr(new PrintStream(err, true, StandardCharsets.UTF_8))
      val code = Sireum.run(args)
      (code, out.toString(StandardCharsets.UTF_8), err.toString(StandardCharsets.UTF_8))
    } finally {
      System.setOut(oldOut)
      System.setErr(oldErr)
    }
  }

  private def assertEmpty(path: Path): Unit = {
    val stream = Files.list(path)
    try assert(!stream.iterator.hasNext)
    finally stream.close()
  }

  "one saga report option parses and drives the ScalaTest report seam" in tempRoot { root =>
    val report = root.resolve("gate.json")
    val parsed = Cli(Os.pathSepChar).parseSireum(
      ISZ("proyek", "test", "--saga-report", report.toString, root.toString),
      0)
    parsed match {
      case Some(option: Cli.SireumProyekTestOption) =>
        assert(option.sagaReport == Some(String(report.toString)))
      case _ => fail("Expected a parsed Proyek test option")
    }
    assertEmpty(root)

    val home = Paths.get(System.getenv("SIREUM_HOME")).toAbsolutePath.normalize
    val (code, _, _) = captureRun(
      ISZ(
        "proyek",
        "test",
        "--skip-compile",
        "--name",
        "sireum-proyek",
        "--slice",
        "proyek",
        "--classes",
        "org.sireum.proyek.SagaReportTest",
        "--tests",
        "wire tree matches the frozen cross-repository golden",
        "--saga-report",
        report.toString,
        home.toString))
    assert(code == 0)
    val tree = new Predef.String(Files.readAllBytes(report), StandardCharsets.UTF_8)
    assert(tree.startsWith("""{"type":"org.sireum.gate.GateReport","producerId":"sireum-proyek-scalatest""""))
    assert(tree.contains(
      """"namespace":"org.sireum.proyek.SagaReportTest","id":"wire tree matches the frozen cross-repository golden"}"""))
    assert(tree.contains(
      """"outcome":{"type":"org.sireum.gate.GateOutcome.Type","value":"Passed"}"""))
    val proyekOut = home.resolve("out").resolve("sireum-proyek")
    val entries = Files.list(proyekOut)
    try {
      assert(!entries.iterator.asScala.exists(_.getFileName.toString.startsWith(".saga-report-xml-")))
    } finally {
      entries.close()
    }
  }

  "help and checked-in CLI spec each expose saga report exactly once" in {
    val (_, out, err) = captureRun(ISZ("proyek", "test", "--help"))
    assert(err.isEmpty)
    assert(out.sliding("--saga-report".length).count(_ == "--saga-report") == 1)
    val home = Paths.get(System.getenv("SIREUM_HOME")).toAbsolutePath.normalize
    val spec = new Predef.String(
      Files.readAllBytes(
        home.resolve("cli/jvm/src/main/scala/org/sireum/sireum-cli-spec.json")),
      StandardCharsets.UTF_8)
    val seam = """"longKey" : "saga-report""""
    assert(spec.sliding(seam.length).count(_ == seam) == 1)
  }

  "missing saga report value fails before project or test launch and creates nothing" in tempRoot { root =>
    val (code, _, err) = captureRun(ISZ("proyek", "test", "--saga-report"))
    assert(code != 0)
    assert(err.contains("--saga-report requires a path"))
    assertEmpty(root)
  }

  "repeated saga report option fails before project or test launch and creates nothing" in tempRoot { root =>
    val first = root.resolve("first.json")
    val second = root.resolve("second.json")
    val (code, _, err) = captureRun(
      ISZ(
        "proyek",
        "test",
        "--saga-report",
        first.toString,
        "--saga-report",
        second.toString,
        root.resolve("project-that-must-not-launch").toString))
    assert(code != 0)
    assert(err.contains("--saga-report may be supplied exactly once"))
    assertEmpty(root)
  }

  "unsupported backend and selection combinations fail before project or test launch" in tempRoot { root =>
    val report = root.resolve("gate.json")
    val project = root.resolve("project-that-must-not-launch")
    val (code, _, err) = captureRun(
      ISZ(
        "proyek",
        "test",
        "--junit5",
        "--saga-report",
        report.toString,
        project.toString))
    assert(code != 0)
    assert(err.contains("cannot be combined with --junit5"))

    def assertSelectionRefused(options: ISZ[String], names: ISZ[String] = ISZ()): Unit = {
      val (selectionCode, _, selectionErr) = captureRun(
        ISZ[String]("proyek", "test", "--saga-report", String(report.toString)) ++
          options ++ ISZ[String](String(project.toString)) ++ names)
      assert(selectionCode != 0)
      assert(selectionErr.contains("--classes"))
    }

    assertSelectionRefused(ISZ("--suffixes", "Suite"))
    assertSelectionRefused(ISZ("--packages", "probe"))
    assertSelectionRefused(ISZ(), ISZ("probe"))
    assertSelectionRefused(ISZ())
    assertSelectionRefused(ISZ("--classes", "probe.FailSuite", "--suffixes", "Suite"))
    assertEmpty(root)
  }
}
