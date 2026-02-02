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

object CheckStack {
  def run(o: Cli.SireumToolsCheckstackOption, checkstack: Os.Path): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      return 0
    }

    if (!SireumApi.homeFound) {
      return tools.CheckStack.NO_SIREUM_HOME
    }

    val arch: String = o.arch match {
      case Cli.SireumToolsCheckstackCheckStackArch.Amd64 => "x86_64"
      case _ => ops.StringOps(o.arch.name).firstToLower
    }

    val format: tools.CheckStack.Format.Type = o.format match {
      case Cli.SireumToolsCheckstackCheckStackFormat.Plain => tools.CheckStack.Format.Plain
      case Cli.SireumToolsCheckstackCheckStackFormat.Html => tools.CheckStack.Format.Html
      case Cli.SireumToolsCheckstackCheckStackFormat.Md => tools.CheckStack.Format.Md
      case Cli.SireumToolsCheckstackCheckStackFormat.Rst => tools.CheckStack.Format.Rst
      case Cli.SireumToolsCheckstackCheckStackFormat.Csv => tools.CheckStack.Format.Csv
    }

    return tools.CheckStack.run(SireumApi.homeOpt.get, checkstack, o.args.map((s: String) => Os.path(s)),
      o.mode == Cli.SireumToolsCheckstackCheckStackMode.Bin, o.objdump.get, arch, format)
  }
}
