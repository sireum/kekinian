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
import org.sireum.message.Reporter
import org.sireum.tools.{SlangCheckJvm => SCJ}

object SlangCheck {

  val kind: String = "SlangCheck"

  val ARGS_ERROR: Z = -2
  val OUTPUT_REQUIRED: Z = -3
  val OUTPUT_NOT_FILE: Z = -4
  val INPUT_MISSING: Z = -4
  val EXEC_MISSING: Z = -5
  val DUMP_MISSING: Z = -6

  def generate(o: Cli.SireumToolsSlangcheckGeneratorOption, reporter: Reporter): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      return 0
    }

    val outputDir = Os.path(if (o.outputDir.nonEmpty) o.outputDir.get else ".")

    val files: ISZ[Os.Path] = for (arg <- o.args) yield Os.path(arg)
    for (f <- files if !f.exists || !f.isFile) {
      reporter.error(None(), kind, s"$f is not a file")
      return ARGS_ERROR
    }

    val results = SCJ.run(o.packageName, files, reporter)

    if (!reporter.hasError) {
      for (r <- results._1) {
        val destFile = outputDir /+ r._1
        destFile.writeOver(r._2.render)
        println(s"Wrote $destFile")
      }

      if (o.testDir.nonEmpty) {
        val testDir = Os.path(o.testDir.get)
        for (r <- results._2) {
          val destFile = testDir /+ r._1
          destFile.writeOver(r._2.render)
          println(s"Wrote $destFile")
        }
      }
    }

    return if (reporter.hasError) 1 else 0
  }

  def run(o: Cli.SireumToolsSlangcheckRunnerOption, reporter: Reporter): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      return 0
    }
    return Ext.run(o, reporter)
  }

  def test(args: ISZ[String], o: Cli.SireumToolsSlangcheckTesterOption, reporter: Reporter): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      return 0
    }
    return Ext.test(args, o, reporter)
  }

  @ext(name = "SlangCheck_Ext") object Ext {
    def run(o: Cli.SireumToolsSlangcheckRunnerOption, reporter: Reporter): Z = $

    def test(args: ISZ[String], o: Cli.SireumToolsSlangcheckTesterOption, reporter: Reporter): Z = $
  }
}
