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
        lang.parser.Parser.parseTopUnit[AST.TopUnit.Program](content, isWorksheet, F, Some(pathString), reporter) match {
          case Some(program) =>
            val newContent = lang.ast.SlangLl2PrettyPrinter.prettyPrint(program)
            val p = path.up / s"$name.slang"
            p.writeOver(newContent.render)
            println(s"Wrote $p")
          case _ =>
        }
      }
    }
    reporter.printMessages()
    return if (reporter.hasIssue) -1 else 0
  }
}