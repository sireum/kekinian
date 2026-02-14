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
import org.sireum.SireumApi._

object Parser {

  val INVALID_OPTIONS: Z = -1
  val INVALID_OUTDIR: Z = -2
  val INVALID_INPUT: Z = -3
  val INVALID_GRAMMAR: Z = -4

  def gen(o: Cli.SireumParserGenOption, reporter: message.Reporter): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      println()
      println("The LL(k) input grammar specification is a subset of ANTLR3's grammar (processed as a codepoint stream)")
      println("extended with the following full unicode input form '\\\\u{<hex>}':")
      println("https://github.com/sireum/parser/blob/master/jvm/src/main/resources/SireumAntlr3.g")
      println()
      println("If not using '\\\\u{<hex>}', please first check the input grammar using ANTLRWorks available at:")
      println("https://github.com/sireum/antlrworks/releases")
      return 0
    }

    if (!o.predictive && !o.backtracking) {
      eprintln("Cannot generate non-predictive parsers without backtracking")
      return INVALID_OPTIONS
    }

    val licenseOpt: Option[ST] = path2fileOpt("license file", o.license, T) match {
      case Some(f) => Some(
        st"""/*
            | ${ops.StringOps(f.read).trim}
            | */
            |""")
      case _ => None()
    }
    val src = paths2fileOpt("grammar file", o.args, T).get
    val destDir = path2fileOpt("output directory", o.outputDir, T).get
    if (!destDir.isDir) {
      eprintln(s"Path $destDir is not a directory")
      return INVALID_OUTDIR
    }

    val name: String = o.name match {
      case Some(n) => n
      case _ => ops.StringOps(ops.StringOps(src.name).substring(0, src.name.size - (src.ext.size + 1))).firstToUpper
    }
    val dest = destDir / s"${name}Parser.scala"
    val packageOpt: Option[ST] =
      if (o.packageName.isEmpty) None() else Some(
        st"""package ${(o.packageName, ".")}
            |""")
    val fileInfo = st"""// This file is auto-generated from ${src.name}
                       |"""

    val isLLk = o.predictive && !o.memoize && !o.backtracking && src.name != "SlangTruthTable.g"
    val rOpt: Option[parser.ParseTree] = o.mode match {
      case Cli.SireumParserGenParserGenMode.Slang =>
        if (isLLk) parser.SireumGrammarParser.parse(Some(src.toUri), src.read, reporter)
        else parser.SireumGrammarParserOld.parse(Some(src.toUri), src.read, reporter)
      case _ => SireumApi.parseGrammar(Some(src.toUri), src.read, reporter)
    }
    if (reporter.hasError) {
      reporter.printMessages()
      return INVALID_INPUT
    }
    val ast: parser.GrammarAst.Grammar =
      if (isLLk) parser.LLkGrammarAstBuilder(rOpt.get).build(reporter)
      else parser.GrammarAstBuilder(rOpt.get).build(reporter)
    if (reporter.hasError) {
      reporter.printMessages()
      return INVALID_INPUT
    }
    val parserOpt: Option[ST] =
      if (isLLk) parser.LLkParserGenerator.gen(licenseOpt, fileInfo, packageOpt, name, ast, reporter)
      else parser.ParserGenerator().gen(licenseOpt, fileInfo, packageOpt, name,  ast, o.memoize, o.predictive, o.backtracking, reporter)
    parserOpt match {
      case Some(out) =>
        dest.writeOver(out.render)
        println(s"Wrote $dest")
        return 0
      case _ =>
        reporter.printMessages()
        if (isLLk) {
          eprintln(s"The grammar is not LL(${ast.k})")
        }
        return INVALID_GRAMMAR
    }
  }

}
