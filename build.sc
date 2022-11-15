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

import mill._
import $file.runtime.Runtime
import $file.slang.Slang
import $file.tools.Tools
import $file.alir.Alir
import $file.transpilers.Transpilers
import $file.logika.Logika
import $file.infoflow.InfoFlow
import $file.parser.Parser
import $file.hamr.air.Air
import $file.hamr.codegen.art.Art
import $file.hamr.codegen.Codegen
import $file.hamr.codegen.act.Act
import $file.hamr.codegen.arsit.Arsit
import $file.hamr.phantom.Phantom
import $file.proyek.Proyek
import $file.anvil.Anvil
import $file.server.Server
import $file.cli.Cli
import org.sireum.mill.SireumModule

object runtime extends mill.Module {

  object macros extends Runtime.Module.Macros

  object test extends Runtime.Module.Test {
    override def macrosObject = macros
  }

  trait testProvider extends Runtime.Module.TestProvider {
    override def testObject = test
  }

  object library extends Runtime.Module.Library with testProvider

}

object slang extends mill.Module {

  object ast extends Slang.Module.Ast with runtime.testProvider {
    final override def libraryObject = runtime.library
  }

  object parser extends Slang.Module.Parser with runtime.testProvider {
    final override def astObject = ast
  }

  object tipe extends Slang.Module.Tipe with runtime.testProvider {
    final override def astObject = ast
    final override def testObject = runtime.test
  }

  object frontend extends Slang.Module.FrontEnd with runtime.testProvider {
    final override def parserObject = parser
    final override def tipeObject = tipe
  }

}

object alir extends Alir.Module with runtime.testProvider {
  final override def tipeObject = slang.tipe
}

object transpilers extends mill.Module {

  object common extends Transpilers.Module.Common {
    override val alirObject = alir
  }

  object c extends Transpilers.Module.C {
    final override def frontEndObject = slang.frontend

    override val commonObject = common
  }
}

object logika extends Logika.Module with runtime.testProvider {
  final override def frontEndObject = slang.frontend
}

object infoflow extends InfoFlow.Module with runtime.testProvider {
  final override def logikaObject = logika
}

object tools extends Tools.Module with runtime.testProvider {
  final override def frontEndObject = slang.frontend
}

object parser extends Parser.Module with runtime.testProvider {
  final override def libraryObject = runtime.library
}

object hamr extends mill.Module {

  object air extends Air.Module {
    final override def slangTipeObject = slang.tipe
    final override def libraryObject = runtime.library
    final override def testObject = runtime.test
  }
  
  object phantom extends Phantom.Module {
    final override def libraryObject = runtime.library
  }
  
  object codegen extends Codegen.Module.Codegen with runtime.testProvider {

    object common extends Codegen.Module.Common {
      final override def airObject = air

      final override def slangFrontendObject = slang.frontend
    }

    object act extends Act.Module {
      final override def airObject = air

      final override def commonObject = common
    }

    object arsit extends Arsit.Module {
      final override def airObject = air

      final override def commonObject = common
    }
    
    final override def actObject = act    
    final override def airObject = air
    final override def arsitObject = arsit

    object art extends Art.Module {
      final override def libraryObject = runtime.library
      final override def testObject = runtime.test
    }
  }
}

object proyek extends Proyek.Module {
  final override def infoflowObject = infoflow
}

object anvil extends Anvil.Module {
 final override def transpilersObject = transpilers.c
}


object server extends Server.Module {
  final override def alirObject = alir
  final override def anvilObject = anvil
  final override def toolsObject = tools
  final override def infoflowObject = infoflow
  final override def phantomObject = hamr.phantom
  final override def hamrCodegenObject = hamr.codegen
  final override def proyekObject = proyek
  final override def testObject = runtime.test
  final override def parserObject = parser
}


object cli extends Cli.Module {
  final override def serverObject = server
}


object buildModule extends mill.Module {
  override def millSourcePath = super.millSourcePath / os.up / "build"
}

def build() = T.command {
  val jar = os.pwd / 'bin / "sireum.jar"
  val p = cli.assembly().path
  os.proc("java", "-cp", jar, "org.sireum.tools.ScalaGraal", p).call()
  os.copy(p, jar, replaceExisting = true, copyAttributes = true)
}


def jitPack(owner: String, repo: String, lib: String = "", hash: String = "") = T.command {
  if ("" == hash) org.sireum.mill.SireumModule.jitPack(owner, repo, if ("" == lib) repo else lib)
  else org.sireum.mill.SireumModule.jitPack(owner, repo, if ("" == lib) repo else lib, hash)
}


private def currPlatform: String = {
  import scala.util.{Properties => ps}
  if (ps.isMac) "mac"
  else if (ps.isLinux) {
    val arch = os.proc("uname", "-m", os.pwd).call().out.trim
    if (arch == "aarch64") "linux/arm" else "linux"
  } else if (ps.isWin) "win"
  else throw new UnsupportedOperationException("Unsupported platform")
}

private def log(r: os.CommandResult)(implicit ctx: mill.api.Ctx.Log): Unit = {
  val logger = ctx.log
  val out = r.out.string
  val err = r.err.string
  if (out.trim.nonEmpty) logger.info(out)
  if (err.trim.nonEmpty) logger.error(err)
  if (r.exitCode != 0) System.exit(r.exitCode)
}
