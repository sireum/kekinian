// #Sireum
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

import org.sireum._
import org.sireum.cli.CliOpt._
import org.sireum.hamr.codegen
import org.sireum.hamr.phantom
import org.sireum.lang
import org.sireum.transpilers
import org.sireum.tools

val hamr = Group(
  name = "hamr",
  description = "HAMR Tools",
  header =
    st"""HAMR: High-Assurance Model-based Rapid-engineering tools for embedded systems""".render,
  unlisted = F,
  subs = ISZ(codegen.hamrCodeGenCli.codeGenTool, phantom.cli.phantomTool)
)

val main = Group(
  name = "sireum",
  description = "",
  header =
    st"""Sireum: A High-Assurance System Engineering Platform
        |(c) 2019, SAnToS Laboratory, Kansas State University
        |Build yyyymmdd.sha""".render,
  unlisted = F,
  subs = ISZ(
    hamr,
    logika.cli.group,
    lang.cli.group(subs = lang.cli.group.subs :+ transpilers.cli.group),
    tools.cli.group)
)
println(org.sireum.cli.JSON.fromCliOpt(main, T))