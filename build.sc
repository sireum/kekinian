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

import mill._
import ammonite.ops._
import $file.runtime.Runtime
import $file.slang.Slang
import $file.tools.Tools
import $file.alir.Alir
import $file.transpilers.Transpilers
import $file.logika.Logika
import $file.aadl.air.Air
import $file.aadl.act.Act
import $file.aadl.phantom.Phantom
import $file.cli.Cli
import $file.distro
import mill.scalalib.ScalaModule
import org.sireum.mill.SireumModule

object runtime extends mill.Module {

  object macros extends Runtime.Module.Macros

  object test extends Runtime.Module.Test {
    override def macrosObject = macros
  }

  trait testProvider extends Runtime.Module.TestProvider {
    override def testObject = test
  }

  object library extends Runtime.Module.Library with testProvider {
    override def macrosObject = macros
  }

  object bin extends ScalaModule {
    final override def scalaVersion = SireumModule.scalaVersion
    final override def moduleDeps = Seq(runtime.library.jvm)
  }
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

  object bin extends ScalaModule {
    final override def scalaVersion = SireumModule.scalaVersion
    final override def moduleDeps = Seq(runtime.library.jvm)
  }
}

object alir extends Alir.Module with runtime.testProvider {
  final override def frontEndObject = slang.frontend

  object bin extends ScalaModule {
    final override def scalaVersion = SireumModule.scalaVersion
    final override def moduleDeps = Seq(runtime.library.jvm)
  }
}

object transpilers extends mill.Module {

  object common extends Transpilers.Module.Common {
    override val alirObject = alir
  }

  object c extends Transpilers.Module.C {
    override val commonObject = common
  }

  object bin extends ScalaModule {
    final override def scalaVersion = SireumModule.scalaVersion
    final override def moduleDeps = Seq(runtime.library.jvm)
  }
}

object logika extends Logika.Module with runtime.testProvider {
  final override def frontEndObject = slang.frontend

  object bin extends ScalaModule {
    final override def scalaVersion = SireumModule.scalaVersion
    final override def moduleDeps = Seq(runtime.library.jvm)
  }
}

object tools extends Tools.Module with runtime.testProvider {
  final override def frontEndObject = slang.frontend

  object bin extends ScalaModule {
    final override def scalaVersion = SireumModule.scalaVersion
    final override def moduleDeps = Seq(runtime.library.jvm)
  }
}

object aadl extends mill.Module {

  object air extends Air.Module {
    final override def libraryObject = runtime.library
    final override def testObject = runtime.test
  }

  object act extends Act.Module {
    final override def airObject = air

    object bin extends ScalaModule {
      final override def scalaVersion = SireumModule.scalaVersion
      final override def moduleDeps = Seq(runtime.library.jvm)
    }
  }

  object phantom extends Phantom.Module {
    final override def libraryObject = runtime.library
  }
}


object cli extends Cli.Module {
  final override def alirObject = alir
  final override def transpilersCObject = transpilers.c
  final override def toolsObject = tools
  final override def logikaObject = logika
  final override def actObject = aadl.act
  final override def phantomObject = aadl.phantom
}

object bin extends ScalaModule {
  final override def scalaVersion = SireumModule.scalaVersion
  final override def moduleDeps = Seq(runtime.library.jvm)

  object `mill-build` extends mill.Module {
    object bin extends ScalaModule {
      final override def scalaVersion = SireumModule.scalaVersion
      final override def moduleDeps = Seq(runtime.library.jvm)
    }
  }
}


def build() = T.command {
  val jar = os.pwd / 'bin / "sireum.jar"
  os.copy(cli.assembly().path, jar, replaceExisting = true, copyAttributes = true)
}

def IVE(platforms: String = currPlatform, isDev: Boolean = true) = T.command {
  build()()
  println(s"Using cache at ${distro.distro.cacheDir}")
  for (platform <- platforms.split(',')) {
    require((platform == "mac") == scala.util.Properties.isMac, "Cannot setup macOS IVE on non-mac")
    distro.build(platform.trim, isDev, sfx = false, clone = false)
  }
}


def Distro(isDev: Boolean = true, platforms: String = currPlatform, clone: Boolean = true) = T.command {
  build()()
  println(s"Using cache at ${distro.distro.cacheDir}")
  for (platform <- platforms.split(',')) {
    require((platform == "mac") == scala.util.Properties.isMac, "Cannot build macOS distro on non-mac")
    val shouldClone = platform match {
      case "mac" => false
      case _ => if (scala.util.Properties.isWin) true else clone
    }
    distro.build(platform.trim, isDev, sfx = true, clone = shouldClone)
  }
}


def jitPack(owner: String, repo: String, hash: String, lib: String = "") = T.command {
  org.sireum.mill.SireumModule.jitPack(owner, repo, if ("" == lib) repo else lib, hash)
}


private def currPlatform: String = {
  import scala.util.{Properties => ps}
  if (ps.isMac) "mac"
  else if (ps.isLinux) "linux"
  else if (ps.isWin) "win"
  else throw new UnsupportedOperationException("Unsupported platform")
}

private def log(r: CommandResult)(implicit ctx: mill.api.Ctx.Log): Unit = {
  val logger = ctx.log
  val out = r.out.string
  val err = r.err.string
  if (out.trim.nonEmpty) logger.info(out)
  if (err.trim.nonEmpty) logger.error(err)
  if (r.exitCode != 0) System.exit(r.exitCode)
}
