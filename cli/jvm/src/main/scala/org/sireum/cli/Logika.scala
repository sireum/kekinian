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

package org.sireum.cli

import org.sireum._
import org.sireum.lang.tipe.TypeHierarchy

object Logika {

  val INVALID_MODE: Z = -1
  val INVALID_SCRIPT_FILE: Z = -2
  val ILL_FORMED_SCRIPT_FILE: Z = -3
  val INVALID_CHAR_WIDTH: Z = -4
  val INVALID_INT_WIDTH: Z = -5
  val INVALID_VC_DIR: Z = -6
  val INVALID_RAM_DIR: Z = -7
  val INVALID_SOURCE_PATH: Z = -8
  val INVALID_SOURCE_FILE: Z = -9
  val ILL_FORMED_PROGRAMS: Z = -10

  def run(o: Cli.LogikaVerifierOption): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      println()
      return 0
    }

    if (o.noRuntime && o.sourcepath.isEmpty) {
      eprintln("Please specify sourcepath when not using built-in runtime")
      return INVALID_MODE
    }

    o.charBitWidth match {
      case z"8" =>
      case z"16" =>
      case z"32" =>
      case _ =>
        eprintln(s"C (character) bit-width has to be 8, 16, or 32 (instead of ${o.charBitWidth})")
        return INVALID_CHAR_WIDTH
    }

    o.intBitWidth match {
      case z"0" =>
      case z"8" =>
      case z"16" =>
      case z"32" =>
      case z"64" =>
      case _ =>
        eprintln(s"Z (integer) bit-width has to be 0 (arbitrary-precision), 8, 16, 32, or 64 (instead of ${o.intBitWidth})")
        return INVALID_INT_WIDTH
    }

    var smt2Configs = ISZ[logika.Smt2Config]()
    val rfOpt: Option[Os.Path] = o.ramFolder match {
      case Some(rf) =>
        val rfp = Os.path(rf)
        if (rfp.isDir) {
          rfp.removeAll()
          Some(rfp)
        } else {
          eprintln(s"$rf is not a directory")
          return INVALID_RAM_DIR
        }
      case _ => None()
    }
    if (o.solver == Cli.LogikaSolver.All || o.solver == Cli.LogikaSolver.Cvc4) {
      val exeFilename: String = if (Os.isWin) s"cvc4.exe" else "cvc4"
      SireumApi.homeOpt match {
        case Some(home) =>
          val p: Os.Path = home / "bin" / SireumApi.platform / exeFilename
          val exe: String = rfOpt match {
            case Some(rfp) =>
              val d = rfp / "sireum"
              d.mkdirAll()
              val f = d / p.name
              if (!(f.isFile && f.length == p.length)) {
                f.removeAll()
                p.copyOverTo(f)
              }
              f.string
            case _ => p.string
          }
          smt2Configs = smt2Configs :+ logika.Cvc4Config(exe)
        case _ =>
          smt2Configs = smt2Configs :+ logika.Cvc4Config(exeFilename)
      }
    }
    if (o.solver == Cli.LogikaSolver.All || o.solver == Cli.LogikaSolver.Z3) {
      val exeFilename: String = if (Os.isWin) s"z3.exe" else "z3"
      SireumApi.homeOpt match {
        case Some(home) =>
          val p: Os.Path = home / "bin" / SireumApi.platform / "z3" / "bin" / exeFilename
          val exe: String = rfOpt match {
            case Some(rfp) =>
              val d = rfp / "sireum"
              d.mkdirAll()
              val f = d / p.name
              if (!(f.isFile && f.length == p.length)) {
                f.removeAll()
                p.copyOverTo(f)
              }
              f.string
            case _ => p.string
          }
          smt2Configs = smt2Configs :+ logika.Z3Config(exe)
        case _ =>
          smt2Configs = smt2Configs :+ logika.Z3Config(exeFilename)
      }
    }

    def verifyScripts(): Z = {
      if (o.noRuntime) {
        eprintln("Checking scripts have to use built-in runtime")
        return INVALID_MODE
      }

      if (o.sourcepath.nonEmpty) {
        eprintln("Checking scripts cannot use custom sourcepath")
      }

      var code: Z = 0
      for (arg <- o.args) {
        if (o.args.size > 1) {
          println(s"Verifying $arg ...")
        }
        val outputDir: Option[String] =
          o.logVcDir match {
            case Some(d) => Some(s"$d${Os.fileSep}${Os.path(arg).name}")
            case _ => None()
          }
        outputDir match {
          case Some(p) =>
            val path = Os.path(p)
            if (path.exists && !path.isDir) {
              eprintln(s"$p is not a directory")
              return INVALID_VC_DIR
            } else {
              path.removeAll()
            }
          case _ =>
        }
        val config = logika.Config(smt2Configs, o.sat, o.timeout * 1000, 3, HashMap.empty, o.unroll, o.charBitWidth,
          o.intBitWidth, o.logPc, o.logRawPc, o.logVc, outputDir, o.dontSplitFunQuant, o.splitAll, o.splitIf,
          o.splitMatch, o.splitContract, o.simplify)
        val f = Os.path(arg)
        val ext = f.ext
        val plugins = logika.Logika.defaultPlugins
        if (f.isFile && (ext == "sc" || ext == "cmd")) {
          val reporter = logika.Logika.Reporter.create
          val content = f.read
          logika.Logika.checkScript(Some(f.value), content, config, (th: lang.tipe.TypeHierarchy) =>
            logika.Smt2Impl.create(smt2Configs, th, logika.Smt2Impl.NoCache(), config.timeoutInMs, config.charBitWidth,
              config.intBitWidth, config.simplifiedQuery, reporter), reporter, o.par, T, plugins, o.line, o.skipMethods,
              o.skipTypes)
          reporter.printMessages()
          if (reporter.hasError) {
            code = if (code == 0) ILL_FORMED_SCRIPT_FILE else code
          }
        } else {
          eprintln(s"$arg is not a Slang script file")
          return INVALID_SCRIPT_FILE
        }
        if (o.args.size > 1) {
          println()
        }
      }
      return code
    }

    def verifyPrograms(): Z = {
      o.logVcDir match {
        case Some(p) =>
          val path = Os.path(p)
          if (path.exists && !path.isDir) {
            eprintln(s"$p is not a directory")
            return INVALID_VC_DIR
          }
        case _ =>
      }

      def readFile(f: Os.Path): (Option[String], String) = {
        return (Some(f.toUri), f.read)
      }

      var sources = ISZ[(Option[String], String)]()
      for (p <- o.sourcepath) {
        val f = Os.path(p)
        if (!f.exists) {
          eprintln(s"Source path '$p' does not exist.")
          return INVALID_SOURCE_PATH
        } else {
          for (p <- Os.Path.walk(f, F, T, { path: Os.Path =>
            var isSlang = path.ext === "slang"
            if (path.ext === "scala" || isSlang) {
              if (!isSlang) {
                val line = conversions.String.fromCis(path.readCStream.takeWhile((c : C) => c != '\n').
                  filter((c : C) => c != ' ' && c != '\t' && c != '\r').toISZ)
                isSlang = ops.StringOps(line).contains("#Sireum")
              }
            }
            isSlang
          })) {
            sources = sources :+ readFile(p)
          }
        }
      }

      if (o.sourcepath.nonEmpty && sources.isEmpty) {
        eprintln("Did not find any sources in the specified sourcepath")
        return INVALID_SOURCE_PATH
      }

      var files = ISZ[String]()
      for (f <- o.args) {
        val p = Os.path(f)
        if (!p.exists) {
          eprintln(s"$p is not a source file")
          return INVALID_SOURCE_FILE
        }
        files = files :+ p.canon.toUri
      }
      val config = logika.Config(smt2Configs, o.sat, o.timeout * 1000, 3, HashMap.empty, o.unroll, o.charBitWidth,
        o.intBitWidth, o.logPc, o.logRawPc, o.logVc,  o.logVcDir, o.dontSplitFunQuant, o.splitAll, o.splitIf,
        o.splitMatch, o.splitContract, o.simplify)
      val plugins = logika.Logika.defaultPlugins
      val reporter = logika.Logika.Reporter.create
      val th: TypeHierarchy =
        if (o.noRuntime) TypeHierarchy.empty
        else lang.FrontEnd.checkedLibraryReporter._1.typeHierarchy
      logika.Logika.checkPrograms(sources, files, config, th,
        (th: lang.tipe.TypeHierarchy) => logika.Smt2Impl.create(smt2Configs, th, logika.Smt2Impl.NoCache(),
          config.timeoutInMs, config.charBitWidth, config.intBitWidth, config.simplifiedQuery, reporter), reporter,
          o.par, T, T, plugins, o.line, o.skipMethods, o.skipTypes)
      reporter.printMessages()
      return if (reporter.hasError) ILL_FORMED_PROGRAMS else 0
    }

    val code: Z =
      if (ops.ISZOps(o.args).forall((s: String) => Os.path(s).ext == "scala")) verifyPrograms()
      else verifyScripts()

    if (code == 0) {
      println("Logika verified!")
    }

    return code
  }
}
