// #Sireum
/*
 Copyright (c) 2017-2022, Robby, Kansas State University
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
import org.sireum.proyek
import org.sireum.tools

val hamr = Group(
  name = "hamr",
  description = "HAMR tools",
  header =
    st"""HAMR: High Assurance Model-based Rapid-engineering tools for embedded systems""".render,
  unlisted = F,
  subs = ISZ(codegen.hamrCodeGenCli.codeGenTool, phantom.cli.phantomTool)
)

val x = Group(
  name = "x",
  description = "Experimental",
  header = "Sireum eXperimental",
  unlisted = T,
  subs = ISZ()
)

val text2speechTool: Tool = Tool(
  name = "text2speech",
  command = "text2speech",
  description = "Text-to-speech tool",
  header = "Sireum Presentasi Text-to-Speech Tool",
  usage = "<option>* <file.txt>",
  usageDescOpt = None(),
  opts = ISZ(
    Opt(name = "force", longKey = "force", shortKey = None(),
      tpe = Type.Flag(F),
      description = "Overwrite output file(s)"),
    Opt(name = "lang", longKey = "lang", shortKey = Some('l'),
      tpe = Type.Str(sep = None(), default = Some("en-US")), description = "Speech language (for AWS or Azure)"),
    Opt(name = "output", longKey = "output", shortKey = Some('o'),
      tpe = Type.Path(F, None()),
      description = "Output filename (defaults to <line>.<ext>)"),
    Opt(name = "outputFormat", longKey = "output-format", shortKey = Some('f'),
      tpe = Type.Choice(name = "OutputFormat", sep = None(), elements = ISZ(
        "mp3", "webm", "ogg", "wav")),
      description = "Audio output format (for AWS or Azure)"),
    Opt(name = "service", longKey = "service", shortKey = Some('s'),
      tpe = Type.Choice(name = "service", sep = None(), elements = ISZ("mary", "aws", "azure")),
      description = "Text-to-speech service"),
    Opt(name = "voice", longKey = "voice", shortKey = Some('v'),
      tpe = Type.Str(sep = None(), default = None()),
      description = "Voice (defaults to \"dfki-spike-hsmm\" for MaryTTS, \"Amy\" for AWS, \"en-GB-RyanNeural\" for Azure)"),
  ),
  groups = ISZ(
    OptGroup(
      name = "AWS", opts = ISZ(
        Opt(name = "awsPath", longKey = "aws-path", shortKey = Some('a'),
          tpe = Type.Path(F, Some("aws")),
          description = "Path to AWS command-line interface (CLI)"),
        Opt(name = "engine", longKey = "engine", shortKey = Some('e'),
          tpe = Type.Choice(name = "engine", sep = None(), elements = ISZ("neural", "standard")),
          description = "Voice engine"),
      ),
    ),
    OptGroup(
      name = "Azure", opts = ISZ(
        Opt(name = "gender", longKey = "gender", shortKey = Some('g'),
          tpe = Type.Str(sep = None(), default = Some("Male")), description = "Voice gender"),
        Opt(name = "key", longKey = "key", shortKey = Some('k'),
          tpe = Type.Str(sep = None(), default = None()), description = "Azure subscription key"),
        Opt(name = "region", longKey = "region", shortKey = Some('r'),
          tpe = Type.Str(sep = None(), default = Some("centralus")), description = "Azure region"),
        Opt(name = "voiceLang", longKey = "voice-lang", shortKey = Some('d'),
          tpe = Type.Str(sep = None(), default = Some("en-GB")), description = "Voice language"),
      )
    )
  )
)


val pgenTool: Tool = Tool(
  name = "gen",
  command = "gen",
  description = "Presentation generator",
  header = "Sireum Presentasi Generator",
  usage = "<option>* <path>",
  usageDescOpt = None(),
  opts = (text2speechTool.opts - text2speechTool.opts(2))(2 ~>
    Opt(name = "outputFormat", longKey = "output-format", shortKey = Some('f'),
      tpe = Type.Choice(name = "OutputFormat", sep = None(), elements = ISZ(
        "mp3", "wav")),
      description = "Audio output format (for AWS or Azure)")),
  groups = text2speechTool.groups
)


val presentasiGroup = Group(
  name = "presentasi",
  description = "Presentation tools",
  header =
    st"""Sireum Presentasi""".render,
  unlisted = F,
  subs = ISZ(pgenTool, text2speechTool)
)

val main = Group(
  name = "sireum",
  description = "",
  header =
    st"""Sireum: A High Assurance System Engineering Platform
        |(c) SAnToS Laboratory, Kansas State University
        |Build $${SireumApi.version}""".render,
  unlisted = F,
  subs = ISZ(
    anvil.cli.group,
    hamr,
    logika.cli.group,
    parser.cli.group,
    proyek.cli.group,
    lang.cli.group(subs = lang.cli.group.subs :+ transpilers.cli.group),
    presentasiGroup,
    server.cli.serverTool,
    tools.cli.group,
    x
  )
)
println(org.sireum.cli.JSON.fromCliOpt(main, T))