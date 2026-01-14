// #Sireum
/*
 Copyright (c) 2017-2025, Robby, Kansas State University
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
import org.sireum.hamr.sysml
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
  subs = ISZ(codegen.HamrCodegenCli.codeGenTool, phantom.cli.phantomTool, sysml.cli.group(subs =
    for (sub <- sysml.cli.group.subs) yield
      if (sub.name == "sysmlLogika") sub.asInstanceOf[Tool](groups = ops.ISZOps(logika.cli.logikaVerifier.groups).drop(1))
      else sub)
  )
)

val x = Group(
  name = "x",
  description = "Experimental",
  header = "Sireum eXperimental",
  unlisted = T,
  subs = ISZ(anvil.cli.anvil)
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
  usage = "<option>* <path> <arg>*",
  usageDescOpt = None(),
  opts = ISZ[Opt](
    Opt(name = "cc", longKey = "cc", shortKey = None(),
      tpe = Type.Num(sep = None(), default = 0, min = Some(0), max = None()),
      description = "Additional time shift (ms) for close-captioning subtitle"),
    Opt(name = "slice", longKey = "slice", shortKey = None(),
      tpe = Type.Str(sep = Some(','), default = None()), description = "Slide indices to keep"),
    Opt(name = "slides", longKey = "slides", shortKey = None(),
      tpe = Type.Flag(default = F), description = "Generate markdown slides"),
    Opt(name = "srt", longKey = "srt", shortKey = None(),
      tpe = Type.Flag(default = F), description = "Generate .srt instead of .vtt subtitle file"),
    Opt(name = "videoFps", longKey = "video-fps", shortKey = None(),
      tpe = Type.Num(sep = None(), default = 5, min = Some(5), max = None()),
      description = "Animated video frames-per-second when generating markdown slides"),
    Opt(name = "videoHeight", longKey = "video-height", shortKey = None(),
      tpe = Type.Num(sep = None(), default = 1080, min = Some(240), max = None()),
      description = "Animated video height when generating markdown slides")
  ) ++ (text2speechTool.opts - text2speechTool.opts(2))(2 ~>
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


val iveSetup: Tool = Tool(
  name = "ive",
  command = "ive",
  description = "IVE setup",
  header = "Sireum IVE Setup",
  usage = "<options>*",
  usageDescOpt = None(),
  opts = ISZ(),
  groups = ISZ()
)

val vscodiumExtensions: ISZ[String] = ISZ(
  "llvm-vs-code-extensions.vscode-clangd",
  "mike-lischke.vscode-antlr4",
  "mads-hartmann.bash-ide-vscode",
  "dbaeumer.vscode-eslint",
  "mhutchie.git-graph",
  "ecmel.vscode-html-css",
  "kofuk.hugo-utils",
  "redhat.java",
  "langium.langium-vscode",
  "James-Yu.latex-workshop",
  "jebbs.plantuml",
  "esbenp.prettier-vscode",
  "ms-python.python",
  "rust-lang.rust-analyzer",
  "scalameta.metals",
  "mshr-h.veriloghdl",
  "redhat.vscode-xml",
  "redhat.vscode-yaml",
  "adamraichu.zip-viewer",
  "hediet.vscode-drawio"
)

val vscodeSetup: Tool = Tool(
  name = "vscode",
  command = "vscode",
  description = "VSCode setup",
  header = "Sireum VSCode Setup",
  usage = "<options>*",
  usageDescOpt = None(),
  opts = ISZ(
    Opt(name = "existingInstall", longKey = "existing-install", shortKey = None(),
      tpe = Type.Path(multiple = F, default = None()),
      description = "Existing VSCodium/VSCode installation path"),
    Opt(name = "extensions", longKey = "extensions", shortKey = None(),
      tpe = Type.Str(sep = Some(','), default = Some(st"${(vscodiumExtensions, ", ")}".render)),
      description = "List of extensions to be installed (excluding Sireum and SysIDE)"),
    Opt(name = "extensionsDir", longKey = "extensions-dir", shortKey = None(),
      tpe = Type.Path(multiple = F, default = None()),
      description = "Custom VSCodium/VSCode extensions directory"),
    Opt(name = "vscode", longKey = "vscode-marketplace", shortKey = None(),
      tpe = Type.Flag(default = F),
      description = "Use VSCode Marketplace")
  ),
  groups = ISZ()
)

val setup = Group(
  name = "setup",
  description = "Setup",
  header =
    st"""Sireum Setup""".render,
  unlisted = F,
  subs = ISZ(iveSetup, vscodeSetup)
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
    hamr,
    logika.cli.group,
    parser.cli.group,
    proyek.cli.group,
    lang.cli.group(subs = lang.cli.group.subs :+ transpilers.cli.group),
    presentasiGroup,
    server.cli.serverTool,
    setup,
    tools.cli.group,
    x
  )
)
println(org.sireum.cli.JSON.fromCliOpt(main, T))