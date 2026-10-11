// #Sireum
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
import org.sireum.lang.{ast => AST}

object SlangMigrate {

  @record class Interpolators(val source: ISZ[Z]) extends AST.MTransformer {
    override def postExpStringInterpolate(o: AST.Exp.StringInterpolate): MOption[AST.Exp] = {
      if (o.prefix != "s" && o.prefix != "st" && o.prefix != "proc") {
        return MNone()
      }
      if (o.prefix != "s" && o.posOpt.nonEmpty) {
        val quote = o.posOpt.get.offset + o.prefix.size
        if (quote + 2 < source.size && source(quote) == 34 &&
            source(quote + 1) == 34 && source(quote + 2) == 34) {
          return MNone()
        }
      }
      val literals = Buffer.create[AST.Exp.LitString]()
      for (literal <- o.lits) {
        literals.append(literal(value = lang.ast.SlangLl2AstBuilder.unescapeString(literal.value)))
      }
      return MSome(o(lits = literals.toIS))
    }
  }

  def normalizeInterpolators(program: AST.TopUnit.Program, content: String): AST.TopUnit.Program = {
    // Scala source positions count UTF-16 code units.
    val units = Buffer.create[Z]()
    for (c <- conversions.String.toCis(content)) {
      val code = c.toZ
      if (code <= 65535) {
        units.append(code)
      } else {
        units.append(55296 + (code - 65536) / 1024)
        units.append(56320 + (code - 65536) % 1024)
      }
    }
    return Interpolators(units.toIS).transformTopUnit(program).getOrElse(program)
      .asInstanceOf[AST.TopUnit.Program]
  }

  def run(o: Cli.SireumSlangMigrateOption, reporter: message.Reporter): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      return 0
    }
    for (arg <- o.args) {
      val path = Os.path(arg).canon
      if (path.ext != "scala" && path.ext != "sc") {
        reporter.error(None(), "Slang Migrator", s"$path is not a Slang file")
      } else {
        val content = path.read
        val isWorksheet = path.ext == "sc"
        val pathString = path.string
        val name = ops.StringOps(path.name).substring(0, path.name.size - path.ext.size - 1)
        val fileReporter = message.Reporter.create
        lang.parser.Parser.parseTopUnit[AST.TopUnit.Program](content, isWorksheet, F, Some(pathString), fileReporter) match {
          case Some(program) if !fileReporter.hasError =>
            val newContent = lang.ast.SlangLl2PrettyPrinter.prettyPrint(normalizeInterpolators(program, content)).render
            val p = path.up / s"$name.slang"
            val target: Option[AST.TopUnit.Program] = lang.parser.SlangLl2Parser.parse(Some(p.string), newContent, fileReporter) match {
              case Some(tree) => lang.ast.SlangLl2AstBuilder.build(Some(p.string), tree, fileReporter)
              case _ => None[AST.TopUnit.Program]()
            }
            if (target.nonEmpty && !fileReporter.hasError) {
              p.writeOver(newContent)
              println(s"Wrote $p")
            } else if (!fileReporter.hasError) {
              fileReporter.error(None(), "Slang Migrator", s"Could not parse migrated output for $path")
            }
          case _ =>
        }
        reporter.reports(fileReporter.messages)
      }
    }
    reporter.printMessages()
    return if (reporter.hasIssue) -1 else 0
  }
}
