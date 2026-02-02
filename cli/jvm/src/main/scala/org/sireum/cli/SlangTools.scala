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

object SlangTools {
  def report(feedbackOpt: Option[String], isError: B, msg: String): Unit = {
    feedbackOpt match {
      case Some(d) =>
        val content = server.protocol.JSON.fromReport(server.protocol.Report(ISZ(),
          message.Message(if (isError) message.Level.Error else message.Level.Info, None(), "Slang", msg)), T)
        (Os.path(d) / "result.json").writeOver(content)
      case _ =>
        eprintln(msg)
    }
  }
  def refactor(o: Cli.SireumSlangRefactorOption, file: Os.Path): Z = {
    o.mode match {
      case Cli.SireumSlangRefactorMode.EnumSymbol =>
        val rep = message.Reporter.create
        lang.FrontEnd.rewrite(lang.FrontEnd.Rewrite.ReplaceEnumSymbols, T, Some(file.toUri), file.read, rep) match {
          case Some((r, n)) =>
            file.writeOver(r)
            if (n == 0) {
              report(o.feedback, F, "All enum elements have already in string form")
            } else {
              report(o.feedback, F, s"Successfully replaced $n enum element symbol(s) with strings")
            }
            return 0
          case _ =>
        }
      case Cli.SireumSlangRefactorMode.InsertVal =>
        val rep = message.Reporter.create
        lang.FrontEnd.rewrite(lang.FrontEnd.Rewrite.InsertConstructorVals, T, Some(file.toUri), file.read, rep) match {
          case Some((r, n)) =>
            file.writeOver(r)
            if (n == 0) {
              report(o.feedback, F, "All constructor parameters have already been annotated with val or var")
            } else {
              report(o.feedback, F, s"Successfully inserted $n constructor parameter val modifier(s)")
            }
            return 0
          case _ =>
        }
      case Cli.SireumSlangRefactorMode.ReformatProof =>
        lang.FrontEnd.reformatProof(T, Some(file.toUri), file.read) match {
          case Some((r, n)) =>
            file.writeOver(r)
            if (n == 0) {
              report(o.feedback, F, s"Program proofs are already well-formatted")
            } else {
              report(o.feedback, F, s"Program proofs have been reformatted with $n number of edit(s)")
            }
            return 0
          case _ =>
        }
      case _ =>
        val rep = message.Reporter.create
        val text = file.read
        lang.parser.Parser.parseTopUnit[lang.ast.TopUnit.Program](text, T, F, Some(file.toUri), rep) match {
          case Some(p) if !rep.hasError =>
            val (th, p2) = lang.FrontEnd.checkWorksheet(0, Some(lang.FrontEnd.checkedLibraryReporter._1.typeHierarchy), p, rep)
            if (!rep.hasError) {
              if (o.mode == Cli.SireumSlangRefactorMode.ExpandInduct) {
                val trans = lang.FrontEnd.InductExpander(th, lang.ast.MethodContract.Simple.empty, None(), HashMap.empty,
                  message.Reporter.create)
                trans.transformTopUnit(p2)
                rep.reports(trans.reporter.messages)
                if (!rep.hasError) {
                  val n = trans.map.size
                  val content = conversions.String.toCis(text)
                  ops.StringOps.replace(content, trans.map) match {
                    case Either.Left(value) =>
                      file.writeOver(value)
                      if (n == 0) {
                        report(o.feedback, F, "All @induct have been expanded")
                      } else {
                        if (rep.hasWarning) {
                          var warnings = 0
                          for (m <- rep.messages) {
                            if (m.level == message.Level.Warning) {
                              warnings = warnings + 1
                            }
                          }
                          Os.printParseableMessages(rep)
                          report(o.feedback, F, s"Expanded $n @induct with $warnings warning(s)")
                        } else {
                          report(o.feedback, F, s"Successfully expanded $n @induct")
                        }
                      }
                      return 0
                    case Either.Right(message) => halt(s"Internal error: $message")
                  }
                }
              } else {
                assert(o.mode == Cli.SireumSlangRefactorMode.RenumberProof)
                val trans = lang.ast.Util.ProofStepsNumberMapper(1, HashMap.empty, HashMap.empty, message.Reporter.create)
                trans.transformTopUnit(p2)
                rep.reports(trans.reporter.messages)
                if (!rep.hasError) {
                  val n = trans.map.size
                  val content = conversions.String.toCis(text)
                  ops.StringOps.replace(content, trans.map) match {
                    case Either.Left(value) =>
                      file.writeOver(value)
                      if (n == 0) {
                        report(o.feedback, F, "All proof steps have already been numbered in order")
                      } else {
                        if (rep.hasWarning) {
                          var warnings = 0
                          for (m <- rep.messages) {
                            if (m.level == message.Level.Warning) {
                              warnings = warnings + 1
                            }
                          }
                          Os.printParseableMessages(rep)
                          report(o.feedback, F, s"Renumbered $n proof step(s) with $warnings warning(s)")
                        } else {
                          report(o.feedback, F, s"Successfully renumbered $n proof step(s)")
                        }
                      }
                      return 0
                    case Either.Right(message) => halt(s"Internal error: $message")
                  }
                }
              }
            }
          case _ =>
        }
    }
    report(o.feedback, F, s"Cannot refactor an ill-formed program")
    return if (o.feedback.isEmpty) -1 else 0
  }

  def template(o: Cli.SireumSlangTemplateOption, file: Os.Path): Z = {
    if (o.line == 0) {
      eprintln("Please specify the line location")
      return -1
    }

    var lineColumn: B = F

    val tmpl: String = o.mode match {
      case Cli.SireumSlangTemplateMode.Step => lang.ast.Util.ProofStepTemplate.regular
      case Cli.SireumSlangTemplateMode.Assume => lang.ast.Util.ProofStepTemplate.assum
      case Cli.SireumSlangTemplateMode.Assert => lang.ast.Util.ProofStepTemplate.asser
      case Cli.SireumSlangTemplateMode.Subproof => lang.ast.Util.ProofStepTemplate.subProof
      case Cli.SireumSlangTemplateMode.SubproofFresh => lang.ast.Util.ProofStepTemplate.let
      case Cli.SireumSlangTemplateMode.Forall =>
        lineColumn = T
        lang.ast.Util.ProofStepTemplate.all
      case Cli.SireumSlangTemplateMode.Exists =>
        lineColumn = T
        lang.ast.Util.ProofStepTemplate.exists
      case Cli.SireumSlangTemplateMode.ForallRange =>
        lineColumn = T
        lang.ast.Util.ProofStepTemplate.allRange
      case Cli.SireumSlangTemplateMode.ExistsRange =>
        lineColumn = T
        lang.ast.Util.ProofStepTemplate.existsRange
      case Cli.SireumSlangTemplateMode.ForallElements =>
        lineColumn = T
        lang.ast.Util.ProofStepTemplate.allEach
      case Cli.SireumSlangTemplateMode.ExistsElements =>
        lineColumn = T
        lang.ast.Util.ProofStepTemplate.existsEach
      case Cli.SireumSlangTemplateMode.ForallIndices =>
        lineColumn = T
        lang.ast.Util.ProofStepTemplate.allEachIndex
      case Cli.SireumSlangTemplateMode.ExistsIndices =>
        lineColumn = T
        lang.ast.Util.ProofStepTemplate.existsEachIndex
    }

    if (lineColumn && o.column == 0) {
      eprintln("Please specify the column location")
      return -1
    }


    if (lineColumn) {
      val content = conversions.String.toCis(file.read)
      val docInfo = message.DocInfo.createFromCis(Some(file.toUri), content)
      val offset = docInfo.lineOffsets(o.line - 1).toZ + o.column - 1
      file.writeOver(s"${ops.StringOps.substring(content, 0, offset)}$tmpl${ops.StringOps.substring(content, offset, content.size)}")
      return 0
    } else {
      lang.FrontEnd.insertProofStep(T, Some(file.toUri), file.read, tmpl, o.line) match {
        case Some(r) =>
          file.writeOver(r)
          return 0
        case _ =>
      }
    }

    if (o.feedback.isEmpty) {
      report(o.feedback, T, "Please specify a suitable line for proof step insertion")
      return -1
    } else {
      report(o.feedback, T, "Please navigate caret to a suitable place for proof step insertion")
      return 0
    }
  }
}
