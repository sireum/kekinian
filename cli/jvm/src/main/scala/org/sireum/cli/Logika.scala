/*
 Copyright (c) 2019, Robby, Kansas State University
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

object Logika {

  def run(o: Cli.LogikaVerifierOption): Z = {
    if (o.sourcepath.nonEmpty) {
      eprintln("Logika sourcepath support is coming soon")
      return -1
    }

    if (o.args.isEmpty) {
      println(o.help)
      println()
      return 0
    }

    val config = logika.Logika.Config(3, HashMap.empty, o.timeout, o.logPc, o.logVc)

    for (arg <- o.args) {
      val f = Os.path(arg)
      if (f.isFile && f.ext.value != ".sc") {
        val z3Exe: String = Sireum.homeOpt match {
          case Some(home) => (home / "bin" / Sireum.platform / "z3" / "bin" / (if (Os.isWin) "z3.exe" else "z3")).value
          case _ => "z3"
        }
        val reporter = message.Reporter.create
        logika.Logika.checkWorksheet(Some(f.value), f.read, config,
          (th: lang.tipe.TypeHierarchy) => logika.Z3(z3Exe , th), reporter)
        reporter.printMessages()
        if (reporter.hasIssue) {
          return -1
        } else {
          println("Logika verified!")
          println()
          return 0
        }
      } else {
        eprintln(s"$arg is not a Slang script file")
        return -1
      }
    }

    return 0
  }
}
