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
import java.nio.file.Files

class SlangMigrateTest extends TestSuite {
  "migration preserves interpolator escapes and raw multiline templates" in {
    val root = Os.path(Files.createTempDirectory("slang-migrate-escapes-").toString)
    try {
      val triple = "\"" * 3
      val source = "// #Sireum\n// \uD83D\uDE00 Unicode precedes source-positioned interpolators.\nimport org.sireum._\n" +
        "def message(n: Z): String = { return s\"pid=$n\\nscope=session\\t\\\"quoted\\\"\\\\tail$$\" }\n" +
        "def template(n: Z): ST = { return st\"pid=$n\\nscope=session\\t\\\"quoted\\\"\\\\tail$$\" }\n" +
        "def multiline(n: Z): String = { return s" + triple + "pid=$n\\nscope=session" + triple + " }\n" +
        "def raw(n: Z): ST = { return st" + triple + "pid=$n\\nscope=session" + triple + " }\n" +
        "def literal(n: Z): String = { return s\"pid=$n\\\\nscope=session\" }\n"
      val path = root / "escapes.sc"
      path.writeOver(String(source))
      val reporter = message.Reporter.create
      assert(SlangMigrate.run(Cli.SireumSlangMigrateOption("", ISZ(path.string)), reporter) == 0)
      assert(!reporter.hasError)
      val output = root / "escapes.slang"
      val tree = lang.parser.SlangLl2Parser.parse(Some(output.string), output.read, reporter).get
      val program = lang.ast.SlangLl2AstBuilder.build(Some(output.string), tree, reporter).get
      assert(!reporter.hasError)
      val methods = program.body.stmts.elements.collect { case method: lang.ast.Stmt.Method => method }
      val expected = scala.Seq("\nscope=session\t\"quoted\"\\tail$", "\nscope=session\t\"quoted\"\\tail$",
        "\nscope=session", "\\nscope=session", "\\nscope=session")
      for ((method, suffix) <- methods.zip(expected)) {
        val expression = method.bodyOpt.get.stmts(0).asInstanceOf[lang.ast.Stmt.Return].expOpt.get
          .asInstanceOf[lang.ast.Exp.StringInterpolate]
        assert(expression.lits.map(_.value) == ISZ(String("pid="), String(suffix)))
        assert(expression.args.size == 1)
      }
      assert(methods.size == expected.size)
    } finally {
      root.removeAll()
    }
  }

  "invalid sources preserve existing output and do not block valid files" in {
    val root = Os.path(Files.createTempDirectory("slang-migrate-").toString)
    try {
      val invalid = "// #Sireum\nimport org.sireum._\nval invalid: Z = ()\n"
      (root / "existing.sc").writeOver(invalid)
      (root / "missing.sc").writeOver(invalid)
      (root / "existing.slang").writeOver("preserve this output\n")
      val unsupported = "// #Sireum\nimport org.sireum._\ndef keep(xs: ISZ[Z]): ISZ[Z] = { return xs.filter(x => x > 0) }\n"
      (root / "unsupported.sc").writeOver(unsupported)
      (root / "unsupported.slang").writeOver("preserve this target output\n")
      (root / "valid.sc").writeOver("// #Sireum\nimport org.sireum._\nval valid: Z = 1\n")
      val reporter = message.Reporter.create
      val option = Cli.SireumSlangMigrateOption("", ISZ(
        (root / "existing.sc").string, (root / "missing.sc").string,
        (root / "unsupported.sc").string, (root / "valid.sc").string))
      assert(SlangMigrate.run(option, reporter) != 0)
      assert(reporter.hasError)
      assert((root / "existing.slang").read == String("preserve this output\n"))
      assert(!(root / "missing.slang").exists)
      assert((root / "unsupported.slang").read == String("preserve this target output\n"))
      assert((root / "valid.slang").isFile)
    } finally {
      root.removeAll()
    }
  }
}
