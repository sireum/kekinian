/*
 Copyright (c) 2018, Robby, Kansas State University
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

import mill._
import ammonite.ops._
import $file.runtime.Runtime
import $file.slang.Slang
import $file.tools.Tools
import $file.cli.Cli

object runtime extends mill.Module {

  object macros extends Runtime.Module.Macros

  object library extends Runtime.Module.Library {

    final override def macrosObject = macros

  }

}

object slang extends mill.Module {

  object ast extends Slang.Module.Ast {

    final override def libraryObject = runtime.library

  }

  object parser extends Slang.Module.Parser {

    final override def astObject = ast

  }


  object tipe extends Slang.Module.Tipe {

    final override def astObject = ast

  }

  object frontend extends Slang.Module.FrontEnd {

    final override def parserObject = parser

    final override def tipeObject = tipe

  }

}

object tools extends Tools.Module {

  final override def frontEndObject = slang.frontend

}

object cli extends Cli.Module {

  final override def frontEndObject = slang.frontend

  final override def toolsObject = tools
}

def regenCli = T {
  val out = cli.assembly()
  val sireumPackagePath = pwd / 'cli / 'jvm / 'src / 'main / 'scala / 'org / 'sireum
  %(out.path, 'tools, 'cligen, "-p", "org.sireum", "-l", pwd / "license.txt", sireumPackagePath / "cli.sc")(sireumPackagePath)
  PathRef(sireumPackagePath / "Cli.scala")
}