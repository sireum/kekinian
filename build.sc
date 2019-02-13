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
import $file.cli.Cli
import $file.distro

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
  final override def frontEndObject = slang.frontend
}

object tools extends Tools.Module with runtime.testProvider {
  final override def frontEndObject = slang.frontend
}

object cli extends Cli.Module {
  final override def alirObject = alir
  final override def toolsObject = tools
}

val libFiles = os.pwd / 'runtime / 'library / 'shared / 'src / 'main / 'scala / 'org / 'sireum / "Library_Ext.scala"

val slangFiles = os.pwd / 'slang / 'frontend / 'shared / 'src / 'main / 'scala / 'org / 'sireum / 'lang / "$SlangFiles.scala"

def toucheSlang() = T.command {
  touchePath(slangFiles)
}

def toucheLib() = T.command {
  touchePath(libFiles)
}

def toucheMacro() = T.command {
  touchePath(os.pwd / 'runtime / 'macros / 'shared / 'src / 'main / 'scala / 'org / 'sireum / '$internal / "Macro.scala")
}

def touche() = T.command {
  touchePath(libFiles)
  touchePath(slangFiles)
}

def build() = T.command {
  val jar = os.pwd / 'bin / "sireum.jar"
  os.copy(cli.assembly().path, jar, replaceExisting = true, copyAttributes = true)
}


def regenCliOpt() = T.command {
  val out = os.pwd / 'bin / "sireum.jar"
  if (os.exists(out)) {
    val cliPackagePath = pwd / 'runtime / 'library / 'shared / 'src / 'main / 'scala / 'org / 'sireum / 'cli
    log(%%('java, "-jar", out, 'tools, 'sergen, "-p", "org.sireum.cli", "-l", pwd / "license.txt",
      "-m", "json", cliPackagePath / "CliOpt.scala")(
      cliPackagePath))
  } else {
    println(s"Please first run ${pwd / 'bin / "build.sh"}")
  }
}


def regenSlang() = T.command {
  val out = os.pwd / 'bin / "sireum.jar"
  if (os.exists(out)) {
    val astPackagePath = pwd / 'slang / 'ast / 'shared / 'src / 'main / 'scala / 'org / 'sireum / 'lang / 'ast
    val slangPackagePath = pwd / 'slang / 'tipe / 'shared / 'src / 'main / 'scala / 'org / 'sireum / 'lang
    log(%%('java, "-jar", out, 'tools, 'transgen, "-l", pwd / "license.txt", "-m", "immutable,mutable",
      astPackagePath / "AST.scala")(astPackagePath))
    log(%%('java, "-jar", out, 'tools, 'sergen, "-p", "org.sireum.lang.tipe", "-l", pwd / "license.txt",
      "-m", "json,msgpack", slangPackagePath / 'symbol / "Info.scala", astPackagePath / "AST.scala")(
      slangPackagePath / 'tipe))
  } else {
    println(s"Please first run ${pwd / 'bin / "build.sh"}")
  }
}


def regenCli() = T.command {
  val out = os.pwd / 'bin / "sireum.jar"
  if (os.exists(out)) {
    val sireumPackagePath = pwd / 'cli / 'jvm / 'src / 'main / 'scala / 'org / 'sireum
    log(%%('java, "-jar", out, 'tools, 'cligen, "-p", "org.sireum", "-l", pwd / "license.txt",
      sireumPackagePath / "cli.sc")(sireumPackagePath))
  } else {
    println(s"Please first run ${pwd / 'bin / "build.sh"}")
  }
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

private def touchePath(path: Path): Unit = {
  val content = os.read(path)
  val lineSep = System.lineSeparator
  if (content.endsWith(lineSep)) {
    os.write.over(path, content.trim)
  } else {
    os.write.over(path, content + lineSep)
  }
}
