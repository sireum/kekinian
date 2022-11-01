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

package org.sireum.cli

import org.sireum._
import org.sireum.lang.tipe.TypeHierarchy
import org.sireum.logika.{Smt2, Smt2Invoke}

object Logika {

  val INVALID_MODE: Z = -1
  val INVALID_SCRIPT_FILE: Z = -2
  val ILL_FORMED_SCRIPT_FILE: Z = -3
  val INVALID_VC_DIR: Z = -4
  val INVALID_SOURCE_PATH: Z = -5

  def run(o: Cli.SireumLogikaVerifierOption, reporter: logika.Logika.Reporter): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      println()
      return 0
    }

    if (o.line != 0 && o.args.size != 1) {
      eprintln("Line focused verification can only be used for checking a single file")
      return Proyek.INVALID_LINE
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
        return Proyek.INVALID_CHAR_WIDTH
    }

    o.intBitWidth match {
      case z"0" =>
      case z"8" =>
      case z"16" =>
      case z"32" =>
      case z"64" =>
      case _ =>
        eprintln(s"Z (integer) bit-width has to be 0 (arbitrary-precision), 8, 16, 32, or 64 (instead of ${o.intBitWidth})")
        return Proyek.INVALID_INT_WIDTH
    }

    val nameExePathMap: HashMap[String, String] = SireumApi.homeOpt match {
      case Some(sireumHome) => Smt2Invoke.nameExePathMap(sireumHome)
      case _ =>
        val exeOpt: Option[String] = if (Os.isWin) Some(".exe") else None()
        HashMap.empty[String, String] ++ ISZ[(String, String)](
          "cvc4" ~> st"cvc4$exeOpt".render,
          "cvc5" ~> st"cvc5$exeOpt".render,
          "z3" ~> st"z3$exeOpt".render,
        )
    }

    val smt2Configs =
      Smt2.parseConfigs(nameExePathMap, F, o.smt2ValidConfigs.get, o.timeout * 1000, o.rlimit).left ++
        Smt2.parseConfigs(nameExePathMap, T, o.smt2SatConfigs.get, Smt2.satTimeoutInMs, o.rlimit).left

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
        val fpRoundingMode: String = o.fpRounding match {
          case Cli.SireumLogikaVerifierFPRoundingMode.NearestTiesToEven => "RNE"
          case Cli.SireumLogikaVerifierFPRoundingMode.NearestTiesToAway => "RNA"
          case Cli.SireumLogikaVerifierFPRoundingMode.TowardPositive => "RTP"
          case Cli.SireumLogikaVerifierFPRoundingMode.TowardNegative => "RTN"
          case Cli.SireumLogikaVerifierFPRoundingMode.TowardZero => "RTZ"
        }
        val parCores = SireumApi.parCoresOpt(o.par)
        val branchParCores = SireumApi.parCoresOpt(o.branchPar)
        val branchParMode: org.sireum.logika.Config.BranchPar.Type = o.branchParMode match {
          case Cli.SireumLogikaVerifierBranchPar.All => org.sireum.logika.Config.BranchPar.All
          case Cli.SireumLogikaVerifierBranchPar.Returns => org.sireum.logika.Config.BranchPar.OnlyAllReturns
          case Cli.SireumLogikaVerifierBranchPar.Disabled => org.sireum.logika.Config.BranchPar.Disabled
        }
        val config = logika.Config(smt2Configs, parCores, o.sat, o.rlimit, o.timeout * 1000, 3, HashMap.empty, o.unroll,
          o.charBitWidth, o.intBitWidth, o.useReal, o.logPc, o.logRawPc, o.logVc, outputDir, o.dontSplitFunQuant,
          o.splitAll, o.splitIf, o.splitMatch, o.splitContract, o.simplify, T, fpRoundingMode, F, o.sequential,
          branchParMode, branchParCores, o.logPcLines)
        val f = Os.path(arg)
        val ext = f.ext
        val plugins = logika.Logika.defaultPlugins
        if (f.isFile && (ext == "sc" || ext == "cmd")) {
          val content = f.read
          logika.Logika.checkScript(Some(f.value), content, config,
            (th: lang.tipe.TypeHierarchy) => logika.Smt2Impl.create(smt2Configs,
              logika.plugin.Plugin.claimPlugins(plugins), th, config.timeoutInMs, fpRoundingMode, config.charBitWidth,
              config.intBitWidth, config.useReal, config.simplifiedQuery, config.smt2Seq, reporter),
            logika.Smt2.NoCache(), reporter, T, plugins, o.line, o.skipMethods, o.skipTypes)
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
            var isSlang = path.ext == "slang"
            if (path.ext == "scala" || isSlang) {
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
          return Proyek.INVALID_SOURCE_FILE
        }
        files = files :+ p.canon.toUri
      }
      val fpRoundingMode: String = o.fpRounding match {
        case Cli.SireumLogikaVerifierFPRoundingMode.NearestTiesToEven => "RNE"
        case Cli.SireumLogikaVerifierFPRoundingMode.NearestTiesToAway => "RNA"
        case Cli.SireumLogikaVerifierFPRoundingMode.TowardPositive => "RTP"
        case Cli.SireumLogikaVerifierFPRoundingMode.TowardNegative => "RTN"
        case Cli.SireumLogikaVerifierFPRoundingMode.TowardZero => "RTZ"
      }
      val parCores = SireumApi.parCoresOpt(o.par)
      val config = logika.Config(smt2Configs, parCores, o.sat, o.rlimit, o.timeout * 1000, 3, HashMap.empty, o.unroll,
        o.charBitWidth, o.intBitWidth, o.useReal, o.logPc, o.logRawPc, o.logVc,  o.logVcDir, o.dontSplitFunQuant,
        o.splitAll, o.splitIf, o.splitMatch, o.splitContract, o.simplify, T, fpRoundingMode, F, o.sequential,
        logika.Config.BranchPar.All, parCores, o.logPcLines)
      val plugins = logika.Logika.defaultPlugins
      val th: TypeHierarchy =
        if (o.noRuntime) TypeHierarchy.empty
        else lang.FrontEnd.checkedLibraryReporter._1.typeHierarchy
      logika.Logika.checkPrograms(sources, files, config, th,
        (th: lang.tipe.TypeHierarchy) => logika.Smt2Impl.create(smt2Configs,
          logika.plugin.Plugin.claimPlugins(plugins), th, config.timeoutInMs, config.fpRoundingMode,
          config.charBitWidth, config.intBitWidth, config.useReal, config.simplifiedQuery, config.smt2Seq, reporter),
        logika.Smt2.NoCache(), reporter, T, T, plugins, o.line, o.skipMethods, o.skipTypes)
      reporter.printMessages()
      return if (reporter.hasError) Proyek.ILL_FORMED_PROGRAMS else 0
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
