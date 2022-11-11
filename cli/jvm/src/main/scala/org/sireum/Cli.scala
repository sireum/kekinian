// #Sireum
// @formatter:off

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

// This file is auto-generated from cli.sc

package org.sireum

import org.sireum._

object Cli {

  @datatype trait SireumTopOption

  @datatype class HelpOption extends SireumTopOption

  @enum object SireumAnvilCompileStage {
    'All
    'Hls
    'Hw
    'Sw
    'Os
  }

  @datatype class SireumAnvilCompileOption(
    val help: String,
    val args: ISZ[String],
    val stage: ISZ[SireumAnvilCompileStage.Type],
    val transpilerArgs: Option[String],
    val sandboxPath: Option[String]
  ) extends SireumTopOption

  @datatype class SireumAnvilSandboxOption(
    val help: String,
    val args: ISZ[String],
    val excludeSireum: B,
    val xilinxUnifiedPath: Option[String],
    val petalinuxInstallerPath: Option[String]
  ) extends SireumTopOption

  @enum object SireumHamrCodegenHamrPlatform {
    'JVM
    'Linux
    'Cygwin
    'MacOS
    'SeL4
    'SeL4_Only
    'SeL4_TB
  }

  @datatype class SireumHamrCodegenOption(
    val help: String,
    val args: ISZ[String],
    val msgpack: B,
    val verbose: B,
    val platform: SireumHamrCodegenHamrPlatform.Type,
    val outputDir: Option[String],
    val packageName: Option[String],
    val noProyekIve: B,
    val noEmbedArt: B,
    val devicesAsThreads: B,
    val genSbtMill: B,
    val slangAuxCodeDirs: ISZ[String],
    val slangOutputCDir: Option[String],
    val excludeComponentImpl: B,
    val bitWidth: Z,
    val maxStringSize: Z,
    val maxArraySize: Z,
    val runTranspiler: B,
    val camkesOutputDir: Option[String],
    val camkesAuxCodeDirs: ISZ[String],
    val aadlRootDir: Option[String],
    val experimentalOptions: ISZ[String]
  ) extends SireumTopOption

  @enum object SireumHamrPhantomPhantomMode {
    'Json
    'Json_compact
    'Msgpack
  }

  @datatype class SireumHamrPhantomOption(
    val help: String,
    val args: ISZ[String],
    val impl: Option[String],
    val main: Option[String],
    val mode: SireumHamrPhantomPhantomMode.Type,
    val output: Option[String],
    val projects: ISZ[String],
    val verbose: B,
    val verbosePlus: B,
    val osate: Option[String],
    val update: B,
    val features: ISZ[String],
    val version: Option[String]
  ) extends SireumTopOption

  @enum object SireumLogikaVerifierFPRoundingMode {
    'NearestTiesToEven
    'NearestTiesToAway
    'TowardPositive
    'TowardNegative
    'TowardZero
  }

  @enum object SireumLogikaVerifierBranchPar {
    'All
    'Returns
    'Disabled
  }

  @datatype class SireumLogikaVerifierOption(
    val help: String,
    val args: ISZ[String],
    val noRuntime: B,
    val sourcepath: ISZ[String],
    val infoFlow: B,
    val charBitWidth: Z,
    val fpRounding: SireumLogikaVerifierFPRoundingMode.Type,
    val useReal: B,
    val intBitWidth: Z,
    val interprocedural: B,
    val line: Z,
    val sat: B,
    val skipMethods: ISZ[String],
    val skipTypes: ISZ[String],
    val unroll: B,
    val logPc: B,
    val logPcLines: B,
    val logRawPc: B,
    val logVc: B,
    val logVcDir: Option[String],
    val par: Option[Z],
    val branchParMode: SireumLogikaVerifierBranchPar.Type,
    val branchPar: Option[Z],
    val dontSplitFunQuant: B,
    val splitAll: B,
    val splitContract: B,
    val splitIf: B,
    val splitMatch: B,
    val rlimit: Z,
    val sequential: B,
    val simplify: B,
    val smt2SatConfigs: Option[String],
    val smt2ValidConfigs: Option[String],
    val timeout: Z
  ) extends SireumTopOption

  @enum object SireumParserGenParserGenMode {
    'Slang
    'Antlr3
  }

  @datatype class SireumParserGenOption(
    val help: String,
    val args: ISZ[String],
    val memoize: B,
    val mode: SireumParserGenParserGenMode.Type,
    val name: Option[String],
    val backtracking: B,
    val predictive: B,
    val license: Option[String],
    val outputDir: Option[String],
    val packageName: ISZ[String]
  ) extends SireumTopOption

  @datatype class SireumProyekAssembleOption(
    val help: String,
    val args: ISZ[String],
    val jar: Option[String],
    val mainClass: Option[String],
    val isNative: B,
    val uber: B,
    val ignoreRuntime: B,
    val json: Option[String],
    val name: Option[String],
    val outputDirName: Option[String],
    val project: Option[String],
    val slice: ISZ[String],
    val symlink: B,
    val versions: ISZ[String],
    val javac: ISZ[String],
    val fresh: B,
    val par: Option[Z],
    val recompile: ISZ[String],
    val scalac: ISZ[String],
    val sha3: B,
    val skipCompile: B,
    val cache: Option[String],
    val docs: B,
    val sources: B,
    val repositories: ISZ[String]
  ) extends SireumTopOption

  @datatype class SireumProyekCompileOption(
    val help: String,
    val args: ISZ[String],
    val javac: ISZ[String],
    val fresh: B,
    val par: Option[Z],
    val recompile: ISZ[String],
    val scalac: ISZ[String],
    val sha3: B,
    val js: B,
    val ignoreRuntime: B,
    val json: Option[String],
    val name: Option[String],
    val outputDirName: Option[String],
    val project: Option[String],
    val slice: ISZ[String],
    val symlink: B,
    val versions: ISZ[String],
    val cache: Option[String],
    val docs: B,
    val sources: B,
    val repositories: ISZ[String]
  ) extends SireumTopOption

  @enum object SireumProyekIveEdition {
    'Community
    'Ultimate
    'Server
  }

  @datatype class SireumProyekIveOption(
    val help: String,
    val args: ISZ[String],
    val empty: B,
    val force: B,
    val edition: SireumProyekIveEdition.Type,
    val javac: ISZ[String],
    val scalac: ISZ[String],
    val ignoreRuntime: B,
    val json: Option[String],
    val name: Option[String],
    val outputDirName: Option[String],
    val project: Option[String],
    val slice: ISZ[String],
    val symlink: B,
    val versions: ISZ[String],
    val cache: Option[String],
    val docs: B,
    val sources: B,
    val repositories: ISZ[String]
  ) extends SireumTopOption

  @enum object SireumProyekLogikaFPRoundingMode {
    'NearestTiesToEven
    'NearestTiesToAway
    'TowardPositive
    'TowardNegative
    'TowardZero
  }

  @enum object SireumProyekLogikaBranchPar {
    'All
    'Returns
    'Disabled
  }

  @datatype class SireumProyekLogikaOption(
    val help: String,
    val args: ISZ[String],
    val all: B,
    val strictAliasing: B,
    val verbose: B,
    val ignoreRuntime: B,
    val json: Option[String],
    val name: Option[String],
    val outputDirName: Option[String],
    val project: Option[String],
    val slice: ISZ[String],
    val symlink: B,
    val versions: ISZ[String],
    val cache: Option[String],
    val docs: B,
    val sources: B,
    val repositories: ISZ[String],
    val infoFlow: B,
    val charBitWidth: Z,
    val fpRounding: SireumProyekLogikaFPRoundingMode.Type,
    val useReal: B,
    val intBitWidth: Z,
    val interprocedural: B,
    val line: Z,
    val sat: B,
    val skipMethods: ISZ[String],
    val skipTypes: ISZ[String],
    val unroll: B,
    val logPc: B,
    val logPcLines: B,
    val logRawPc: B,
    val logVc: B,
    val logVcDir: Option[String],
    val par: Option[Z],
    val branchParMode: SireumProyekLogikaBranchPar.Type,
    val branchPar: Option[Z],
    val dontSplitFunQuant: B,
    val splitAll: B,
    val splitContract: B,
    val splitIf: B,
    val splitMatch: B,
    val rlimit: Z,
    val sequential: B,
    val simplify: B,
    val smt2SatConfigs: Option[String],
    val smt2ValidConfigs: Option[String],
    val timeout: Z
  ) extends SireumTopOption

  @enum object SireumProyekPublishTarget {
    'All
    'Jvm
    'Js
  }

  @datatype class SireumProyekPublishOption(
    val help: String,
    val args: ISZ[String],
    val m2: Option[String],
    val target: ISZ[SireumProyekPublishTarget.Type],
    val version: Option[String],
    val ignoreRuntime: B,
    val json: Option[String],
    val name: Option[String],
    val outputDirName: Option[String],
    val project: Option[String],
    val slice: ISZ[String],
    val symlink: B,
    val versions: ISZ[String],
    val javac: ISZ[String],
    val fresh: B,
    val par: Option[Z],
    val recompile: ISZ[String],
    val scalac: ISZ[String],
    val sha3: B,
    val skipCompile: B,
    val cache: Option[String],
    val docs: B,
    val sources: B,
    val repositories: ISZ[String]
  ) extends SireumTopOption

  @datatype class SireumProyekRunOption(
    val help: String,
    val args: ISZ[String],
    val dir: Option[String],
    val java: ISZ[String],
    val ignoreRuntime: B,
    val json: Option[String],
    val name: Option[String],
    val outputDirName: Option[String],
    val project: Option[String],
    val slice: ISZ[String],
    val symlink: B,
    val versions: ISZ[String],
    val javac: ISZ[String],
    val fresh: B,
    val par: Option[Z],
    val recompile: ISZ[String],
    val scalac: ISZ[String],
    val sha3: B,
    val skipCompile: B,
    val cache: Option[String],
    val docs: B,
    val sources: B,
    val repositories: ISZ[String]
  ) extends SireumTopOption

  @datatype class SireumProyekStatsOption(
    val help: String,
    val args: ISZ[String],
    val ignoreRuntime: B,
    val json: Option[String],
    val name: Option[String],
    val outputDirName: Option[String],
    val project: Option[String],
    val slice: ISZ[String],
    val symlink: B,
    val versions: ISZ[String],
    val cache: Option[String],
    val docs: B,
    val sources: B,
    val repositories: ISZ[String]
  ) extends SireumTopOption

  @datatype class SireumProyekTestOption(
    val help: String,
    val args: ISZ[String],
    val classes: ISZ[String],
    val java: ISZ[String],
    val packages: ISZ[String],
    val suffixes: ISZ[String],
    val ignoreRuntime: B,
    val json: Option[String],
    val name: Option[String],
    val outputDirName: Option[String],
    val project: Option[String],
    val slice: ISZ[String],
    val symlink: B,
    val versions: ISZ[String],
    val javac: ISZ[String],
    val fresh: B,
    val par: Option[Z],
    val recompile: ISZ[String],
    val scalac: ISZ[String],
    val sha3: B,
    val skipCompile: B,
    val cache: Option[String],
    val docs: B,
    val sources: B,
    val repositories: ISZ[String]
  ) extends SireumTopOption

  @datatype class SireumProyekTipeOption(
    val help: String,
    val args: ISZ[String],
    val par: Option[Z],
    val strictAliasing: B,
    val verbose: B,
    val ignoreRuntime: B,
    val json: Option[String],
    val name: Option[String],
    val outputDirName: Option[String],
    val project: Option[String],
    val slice: ISZ[String],
    val symlink: B,
    val versions: ISZ[String],
    val cache: Option[String],
    val docs: B,
    val sources: B,
    val repositories: ISZ[String]
  ) extends SireumTopOption

  @datatype class SireumSlangRunOption(
    val help: String,
    val args: ISZ[String],
    val input: Option[String],
    val output: Option[String],
    val transformed: B,
    val nativ: B
  ) extends SireumTopOption

  @datatype class SireumSlangTipeOption(
    val help: String,
    val args: ISZ[String],
    val exclude: ISZ[String],
    val force: ISZ[String],
    val noRuntime: B,
    val outline: B,
    val sourcepath: ISZ[String],
    val strictAliasing: B,
    val verbose: B,
    val save: Option[String],
    val load: Option[String],
    val gzip: B
  ) extends SireumTopOption

  @datatype class SireumSlangTranspilersCOption(
    val help: String,
    val args: ISZ[String],
    val sourcepath: ISZ[String],
    val strictAliasing: B,
    val output: Option[String],
    val verbose: B,
    val apps: ISZ[String],
    val bitWidth: Z,
    val projectName: Option[String],
    val stackSize: Option[String],
    val customArraySizes: ISZ[String],
    val maxArraySize: Z,
    val maxStringSize: Z,
    val cmakeIncludes: ISZ[String],
    val exts: ISZ[String],
    val libOnly: B,
    val excludeBuild: ISZ[String],
    val plugins: ISZ[String],
    val fingerprint: Z,
    val stableTypeId: B,
    val unroll: B,
    val save: Option[String],
    val load: Option[String],
    val customConstants: ISZ[String],
    val forwarding: ISZ[String]
  ) extends SireumTopOption

  @enum object SireumPresentasiGenOutputFormat {
    'Mp3
    'Wav
  }

  @enum object SireumPresentasiGenService {
    'Mary
    'Aws
    'Azure
  }

  @enum object SireumPresentasiGenEngine {
    'Neural
    'Standard
  }

  @datatype class SireumPresentasiGenOption(
    val help: String,
    val args: ISZ[String],
    val force: B,
    val lang: Option[String],
    val outputFormat: SireumPresentasiGenOutputFormat.Type,
    val service: SireumPresentasiGenService.Type,
    val voice: Option[String],
    val awsPath: Option[String],
    val engine: SireumPresentasiGenEngine.Type,
    val gender: Option[String],
    val key: Option[String],
    val region: Option[String],
    val voiceLang: Option[String]
  ) extends SireumTopOption

  @enum object SireumPresentasiText2speechOutputFormat {
    'Mp3
    'Webm
    'Ogg
    'Wav
  }

  @enum object SireumPresentasiText2speechService {
    'Mary
    'Aws
    'Azure
  }

  @enum object SireumPresentasiText2speechEngine {
    'Neural
    'Standard
  }

  @datatype class SireumPresentasiText2speechOption(
    val help: String,
    val args: ISZ[String],
    val force: B,
    val lang: Option[String],
    val output: Option[String],
    val outputFormat: SireumPresentasiText2speechOutputFormat.Type,
    val service: SireumPresentasiText2speechService.Type,
    val voice: Option[String],
    val awsPath: Option[String],
    val engine: SireumPresentasiText2speechEngine.Type,
    val gender: Option[String],
    val key: Option[String],
    val region: Option[String],
    val voiceLang: Option[String]
  ) extends SireumTopOption

  @enum object SireumServerServerMessage {
    'Msgpack
    'Json
  }

  @datatype class SireumServerOption(
    val help: String,
    val args: ISZ[String],
    val message: SireumServerServerMessage.Type,
    val log: B,
    val noInputCache: B,
    val noTypeCache: B,
    val verbose: B,
    val workers: Z
  ) extends SireumTopOption

  @enum object SireumToolsBcgenBitCodecMode {
    'Program
    'Script
    'Json
    'Dot
  }

  @datatype class SireumToolsBcgenOption(
    val help: String,
    val args: ISZ[String],
    val mode: ISZ[SireumToolsBcgenBitCodecMode.Type],
    val isLittleEndian: B,
    val isMutable: B,
    val packageName: ISZ[String],
    val name: Option[String],
    val license: Option[String],
    val outputDir: Option[String],
    val traits: ISZ[String]
  ) extends SireumTopOption

  @enum object SireumToolsCheckstackCheckStackMode {
    'Dotsu
    'Bin
  }

  @enum object SireumToolsCheckstackCheckStackArch {
    'Amd64
    'X86
    'Aarch64
    'Arm
    'Powerpc
    'Openrisc
    'Mips
    'Mips64
    'M68k
    'Ia64
    'Nios2
    'Parisc
    'S390x
    'Sh64
    'Sparc
  }

  @enum object SireumToolsCheckstackCheckStackFormat {
    'Plain
    'Csv
    'Html
    'Md
    'Rst
  }

  @datatype class SireumToolsCheckstackOption(
    val help: String,
    val args: ISZ[String],
    val mode: SireumToolsCheckstackCheckStackMode.Type,
    val objdump: Option[String],
    val arch: SireumToolsCheckstackCheckStackArch.Type,
    val format: SireumToolsCheckstackCheckStackFormat.Type
  ) extends SireumTopOption

  @datatype class SireumToolsCligenOption(
    val help: String,
    val args: ISZ[String],
    val license: Option[String],
    val name: Option[String],
    val outputDir: Option[String],
    val packageName: ISZ[String],
    val script: Option[String],
    val width: ISZ[Z]
  ) extends SireumTopOption

  @enum object SireumToolsIvegenIveMode {
    'Idea
    'Mill
  }

  @datatype class SireumToolsIvegenOption(
    val help: String,
    val args: ISZ[String],
    val jdk: Option[String],
    val mode: SireumToolsIvegenIveMode.Type,
    val projectName: Option[String],
    val moduleName: Option[String],
    val packageName: ISZ[String],
    val appName: Option[String],
    val millPath: B,
    val force: B,
    val compile: B
  ) extends SireumTopOption

  @datatype class SireumToolsOpgenOption(
    val help: String,
    val args: ISZ[String],
    val license: Option[String],
    val name: Option[String],
    val output: Option[String],
    val packageName: ISZ[String],
    val exclude: ISZ[String],
    val force: ISZ[String],
    val noRuntime: B,
    val sourcepath: ISZ[String],
    val strictAliasing: B,
    val verbose: B,
    val save: Option[String],
    val load: Option[String],
    val gzip: B
  ) extends SireumTopOption

  @enum object SireumToolsSergenSerializerMode {
    'Json
    'Msgpack
  }

  @datatype class SireumToolsSergenOption(
    val help: String,
    val args: ISZ[String],
    val modes: ISZ[SireumToolsSergenSerializerMode.Type],
    val packageName: ISZ[String],
    val name: Option[String],
    val license: Option[String],
    val outputDir: Option[String]
  ) extends SireumTopOption

  @enum object SireumToolsTransgenTransformerMode {
    'Immutable
    'Mutable
  }

  @datatype class SireumToolsTransgenOption(
    val help: String,
    val args: ISZ[String],
    val exclude: ISZ[String],
    val modes: ISZ[SireumToolsTransgenTransformerMode.Type],
    val name: Option[String],
    val license: Option[String],
    val outputDir: Option[String]
  ) extends SireumTopOption
}

import Cli._

@record class Cli(val pathSep: C) {

  def parseSireum(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum: A High Assurance System Engineering Platform
            |(c) SAnToS Laboratory, Kansas State University
            |Build ${SireumApi.version}
            |
            |Available modes:
            |anvil                    Anvil tool
            |hamr                     HAMR tools
            |logika                   Logika tools
            |parser                   Parser tools
            |proyek                   Build tools
            |slang                    Slang tools
            |presentasi               Presentation tools
            |server                   Sireum server
            |tools                    Utility tools""".render
      )
      return Some(HelpOption())
    }
    val opt = select("sireum", args, i, ISZ("anvil", "hamr", "logika", "parser", "proyek", "slang", "presentasi", "server", "tools", "x"))
    opt match {
      case Some(string"anvil") => parseSireumAnvil(args, i + 1)
      case Some(string"hamr") => parseSireumHamr(args, i + 1)
      case Some(string"logika") => parseSireumLogika(args, i + 1)
      case Some(string"parser") => parseSireumParser(args, i + 1)
      case Some(string"proyek") => parseSireumProyek(args, i + 1)
      case Some(string"slang") => parseSireumSlang(args, i + 1)
      case Some(string"presentasi") => parseSireumPresentasi(args, i + 1)
      case Some(string"server") => parseSireumServer(args, i + 1)
      case Some(string"tools") => parseSireumTools(args, i + 1)
      case Some(string"x") => parseSireumX(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumAnvil(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum Anvil
            |
            |Available modes:
            |compile                  Compile one or more stages
            |sandbox                  Create a premade anvil execution environment.""".render
      )
      return Some(HelpOption())
    }
    val opt = select("anvil", args, i, ISZ("compile", "sandbox"))
    opt match {
      case Some(string"compile") => parseSireumAnvilCompile(args, i + 1)
      case Some(string"sandbox") => parseSireumAnvilSandbox(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumAnvilCompileStageH(arg: String): Option[SireumAnvilCompileStage.Type] = {
    arg.native match {
      case "all" => return Some(SireumAnvilCompileStage.All)
      case "hls" => return Some(SireumAnvilCompileStage.Hls)
      case "hw" => return Some(SireumAnvilCompileStage.Hw)
      case "sw" => return Some(SireumAnvilCompileStage.Sw)
      case "os" => return Some(SireumAnvilCompileStage.Os)
      case s =>
        eprintln(s"Expecting one of the following: { all, hls, hw, sw, os }, but found '$s'.")
        return None()
    }
  }

  def parseSireumAnvilCompileStage(args: ISZ[String], i: Z): Option[SireumAnvilCompileStage.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { all, hls, hw, sw, os }, but none found.")
      return None()
    }
    val r = parseSireumAnvilCompileStageH(args(i))
    return r
  }

  def parseSireumAnvilCompileStages(args: ISZ[String], i: Z): Option[ISZ[SireumAnvilCompileStage.Type]] = {
    val tokensOpt = tokenize(args, i, "SireumAnvilCompileStage", ',', T)
    if (tokensOpt.isEmpty) {
      return None()
    }
    var r = ISZ[SireumAnvilCompileStage.Type]()
    for (token <- tokensOpt.get) {
      val e = parseSireumAnvilCompileStageH(token)
      e match {
        case Some(v) => r = r :+ v
        case _ => return None()
      }
    }
    return Some(r)
  }

  def parseSireumAnvilCompile(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Compile one or more stages
          |
          |Usage: <option>* ( <slang-file> )* <slang-file#method-to-accel>
          |
          |Available Options:
          |    --stage              Run the selected stages. Note that "all" is just
          |                           shortcut for "hls,hw,sw,os". (expects one or more of
          |                           { all, hls, hw, sw, os }; default: all)
          |    --transpiler-args-file
          |                          [File containing args to be forwarded to the
          |                           transpiler.Anvil will intercept the transpiler's
          |                           "--output" flag and use it to create a
          |                           workspace.Each flag/value should be on its own line.
          |                           For example:
           --sourcepath
           path/to/src
           --name

          |                           my_project
           --stable-type-id
           --unroll
           ...etc]
          |                           (expects a path)
          |    --sandbox-path       [Optional path to a sandbox that execution will be
          |                           delegated to.Type "anvil sandbox help" for more
          |                           info.] (expects a path)
          |-h, --help               Display this information""".render

    var stage: ISZ[SireumAnvilCompileStage.Type] = ISZ(SireumAnvilCompileStage.All)
    var transpilerArgs: Option[String] = None[String]()
    var sandboxPath: Option[String] = None[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--stage") {
           val o: Option[ISZ[SireumAnvilCompileStage.Type]] = parseSireumAnvilCompileStages(args, j + 1)
           o match {
             case Some(v) => stage = v
             case _ => return None()
           }
         } else if (arg == "--transpiler-args-file") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => transpilerArgs = v
             case _ => return None()
           }
         } else if (arg == "--sandbox-path") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => sandboxPath = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumAnvilCompileOption(help, parseArguments(args, j), stage, transpilerArgs, sandboxPath))
  }

  def parseSireumAnvilSandbox(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Create a linux sandbox that may optionally be hooked into Anvil or used as a debugging workspace.
          |
          |Usage: "sandbox" (args)* <output-path>
          |
          |Available Options:
          |-s, --exclude-sireum     [Indicates that Sireum should NOT be included in the
          |                           sandbox.Sireum enables sandboxing for Anvil's
          |                           "hls#transpiler_pass" and "sw" compilation
          |                           stages.This flag only exists for convenience.]
          |-x, --xilinx-unified-path    
          |                          [Path to Xilinx_Unified_2020.1_0602_1208.tar.gz.
          |                           Enables sandboxing for Anvil's "hls#vivado_hls" and
          |                           "hw" compilation stages.Download from
          |                           https://www.xilinx.com/member/forms/download/xef.html?filename=Xilinx_Unified_2020.1_0602_1208.tar.gz
          |                           (login required)] (expects a path)
          |-p, --petalinux-installer-path    
          |                          [Path to petalinux-v2020.1-final-installer.run.
          |                           Enables sandboxing for Anvil's "os" compilation
          |                           stages.Download from
          |                           https://www.xilinx.com/member/forms/download/xef.html?filename=petalinux-v2020.1-final-installer.run
          |                           (login required)] (expects a path)
          |-h, --help               Display this information""".render

    var excludeSireum: B = false
    var xilinxUnifiedPath: Option[String] = None[String]()
    var petalinuxInstallerPath: Option[String] = None[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-s" || arg == "--exclude-sireum") {
           val o: Option[B] = { j = j - 1; Some(!excludeSireum) }
           o match {
             case Some(v) => excludeSireum = v
             case _ => return None()
           }
         } else if (arg == "-x" || arg == "--xilinx-unified-path") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => xilinxUnifiedPath = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--petalinux-installer-path") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => petalinuxInstallerPath = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumAnvilSandboxOption(help, parseArguments(args, j), excludeSireum, xilinxUnifiedPath, petalinuxInstallerPath))
  }

  def parseSireumHamr(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""HAMR: High Assurance Model-based Rapid-engineering tools for embedded systems
            |
            |Available modes:
            |codegen                 
            |phantom                 """.render
      )
      return Some(HelpOption())
    }
    val opt = select("hamr", args, i, ISZ("codegen", "phantom"))
    opt match {
      case Some(string"codegen") => parseSireumHamrCodegen(args, i + 1)
      case Some(string"phantom") => parseSireumHamrPhantom(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumHamrCodegenHamrPlatformH(arg: String): Option[SireumHamrCodegenHamrPlatform.Type] = {
    arg.native match {
      case "JVM" => return Some(SireumHamrCodegenHamrPlatform.JVM)
      case "Linux" => return Some(SireumHamrCodegenHamrPlatform.Linux)
      case "Cygwin" => return Some(SireumHamrCodegenHamrPlatform.Cygwin)
      case "MacOS" => return Some(SireumHamrCodegenHamrPlatform.MacOS)
      case "seL4" => return Some(SireumHamrCodegenHamrPlatform.SeL4)
      case "seL4_Only" => return Some(SireumHamrCodegenHamrPlatform.SeL4_Only)
      case "seL4_TB" => return Some(SireumHamrCodegenHamrPlatform.SeL4_TB)
      case s =>
        eprintln(s"Expecting one of the following: { JVM, Linux, Cygwin, MacOS, seL4, seL4_Only, seL4_TB }, but found '$s'.")
        return None()
    }
  }

  def parseSireumHamrCodegenHamrPlatform(args: ISZ[String], i: Z): Option[SireumHamrCodegenHamrPlatform.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { JVM, Linux, Cygwin, MacOS, seL4, seL4_Only, seL4_TB }, but none found.")
      return None()
    }
    val r = parseSireumHamrCodegenHamrPlatformH(args(i))
    return r
  }

  def parseSireumHamrCodegen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Generate code from AADL IR (AIR)
          |
          |Usage: <option>* air-file
          |
          |Available Options:
          |    --msgpack            Input serialized using Msgpack (otherwise JSON
          |                           assumed)
          |-v, --verbose            Enable verbose mode
          |-p, --platform           Target platform (expects one of { JVM, Linux, Cygwin,
          |                           MacOS, seL4, seL4_Only, seL4_TB }; default: JVM)
          |-h, --help               Display this information
          |
          |Slang Options:
          |-o, --output-dir         Output directory for the generated project files
          |                           (expects a path; default is ".")
          |-n, --package-name       Base package name for Slang project (output-dir's
          |                           simple name used if not provided) (expects a string)
          |    --no-proyek-ive      Do not run Proyek IVE
          |    --no-embed-art       Do not embed ART project files
          |    --devices-as-thread  Treat AADL devices as threads
          |    --sbt-mill           Generate SBT and Mill projects in addition to Proyek
          |
          |Transpiler Options:
          |    --aux-code-dirs      Auxiliary C source code directories (expects path
          |                           strings)
          |    --output-c-dir       Output directory for C artifacts (expects a path)
          |-e, --exclude-component-impl    
          |                          Exclude Slang component implementations, behavior
          |                           code written in C
          |-b, --bit-width          Default bit-width for unbounded integer types (e.g.,
          |                           Z) (expects one of { 64, 32, 16, 8 })
          |-s, --max-string-size    
          |                          Size for statically allocated strings (expects an
          |                           integer; default is 100)
          |-a, --max-array-size     Default sequence size (e.g., for ISZ, MSZ (expects an
          |                           integer; default is 100)
          |-t, --run-transpiler     Run Transpiler during HAMR Codegen
          |
          |CAmkES Options:
          |    --camkes-output-dir  Output directory for the generated CAmkES project
          |                           files (expects a path)
          |    --camkes-aux-code-dirs
          |                          Directories containing C files to be included in
          |                           CAmkES build (expects path strings)
          |-r, --aadl-root-dir      Root directory containing the AADL project (expects a
          |                           path)
          |
          |Experimental Options:
          |-x, --experimental-options    
          |                           (expects a string separated by ";")""".render

    var msgpack: B = false
    var verbose: B = false
    var platform: SireumHamrCodegenHamrPlatform.Type = SireumHamrCodegenHamrPlatform.JVM
    var outputDir: Option[String] = Some(".")
    var packageName: Option[String] = None[String]()
    var noProyekIve: B = false
    var noEmbedArt: B = false
    var devicesAsThreads: B = false
    var genSbtMill: B = false
    var slangAuxCodeDirs: ISZ[String] = ISZ[String]()
    var slangOutputCDir: Option[String] = None[String]()
    var excludeComponentImpl: B = false
    var bitWidth: Z = 64
    var maxStringSize: Z = 100
    var maxArraySize: Z = 100
    var runTranspiler: B = false
    var camkesOutputDir: Option[String] = None[String]()
    var camkesAuxCodeDirs: ISZ[String] = ISZ[String]()
    var aadlRootDir: Option[String] = None[String]()
    var experimentalOptions: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--msgpack") {
           val o: Option[B] = { j = j - 1; Some(!msgpack) }
           o match {
             case Some(v) => msgpack = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--verbose") {
           val o: Option[B] = { j = j - 1; Some(!verbose) }
           o match {
             case Some(v) => verbose = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--platform") {
           val o: Option[SireumHamrCodegenHamrPlatform.Type] = parseSireumHamrCodegenHamrPlatform(args, j + 1)
           o match {
             case Some(v) => platform = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => outputDir = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--package-name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => packageName = v
             case _ => return None()
           }
         } else if (arg == "--no-proyek-ive") {
           val o: Option[B] = { j = j - 1; Some(!noProyekIve) }
           o match {
             case Some(v) => noProyekIve = v
             case _ => return None()
           }
         } else if (arg == "--no-embed-art") {
           val o: Option[B] = { j = j - 1; Some(!noEmbedArt) }
           o match {
             case Some(v) => noEmbedArt = v
             case _ => return None()
           }
         } else if (arg == "--devices-as-thread") {
           val o: Option[B] = { j = j - 1; Some(!devicesAsThreads) }
           o match {
             case Some(v) => devicesAsThreads = v
             case _ => return None()
           }
         } else if (arg == "--sbt-mill") {
           val o: Option[B] = { j = j - 1; Some(!genSbtMill) }
           o match {
             case Some(v) => genSbtMill = v
             case _ => return None()
           }
         } else if (arg == "--aux-code-dirs") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => slangAuxCodeDirs = v
             case _ => return None()
           }
         } else if (arg == "--output-c-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => slangOutputCDir = v
             case _ => return None()
           }
         } else if (arg == "-e" || arg == "--exclude-component-impl") {
           val o: Option[B] = { j = j - 1; Some(!excludeComponentImpl) }
           o match {
             case Some(v) => excludeComponentImpl = v
             case _ => return None()
           }
         } else if (arg == "-b" || arg == "--bit-width") {
           val o: Option[Z] = parseNumChoice(args, j + 1, ISZ(z"64", z"32", z"16", z"8"))
           o match {
             case Some(v) => bitWidth = v
             case _ => return None()
           }
         } else if (arg == "-s" || arg == "--max-string-size") {
           val o: Option[Z] = parseNum(args, j + 1, None(), None())
           o match {
             case Some(v) => maxStringSize = v
             case _ => return None()
           }
         } else if (arg == "-a" || arg == "--max-array-size") {
           val o: Option[Z] = parseNum(args, j + 1, None(), None())
           o match {
             case Some(v) => maxArraySize = v
             case _ => return None()
           }
         } else if (arg == "-t" || arg == "--run-transpiler") {
           val o: Option[B] = { j = j - 1; Some(!runTranspiler) }
           o match {
             case Some(v) => runTranspiler = v
             case _ => return None()
           }
         } else if (arg == "--camkes-output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => camkesOutputDir = v
             case _ => return None()
           }
         } else if (arg == "--camkes-aux-code-dirs") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => camkesAuxCodeDirs = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--aadl-root-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => aadlRootDir = v
             case _ => return None()
           }
         } else if (arg == "-x" || arg == "--experimental-options") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ';')
           o match {
             case Some(v) => experimentalOptions = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumHamrCodegenOption(help, parseArguments(args, j), msgpack, verbose, platform, outputDir, packageName, noProyekIve, noEmbedArt, devicesAsThreads, genSbtMill, slangAuxCodeDirs, slangOutputCDir, excludeComponentImpl, bitWidth, maxStringSize, maxArraySize, runTranspiler, camkesOutputDir, camkesAuxCodeDirs, aadlRootDir, experimentalOptions))
  }

  def parseSireumHamrPhantomPhantomModeH(arg: String): Option[SireumHamrPhantomPhantomMode.Type] = {
    arg.native match {
      case "json" => return Some(SireumHamrPhantomPhantomMode.Json)
      case "json_compact" => return Some(SireumHamrPhantomPhantomMode.Json_compact)
      case "msgpack" => return Some(SireumHamrPhantomPhantomMode.Msgpack)
      case s =>
        eprintln(s"Expecting one of the following: { json, json_compact, msgpack }, but found '$s'.")
        return None()
    }
  }

  def parseSireumHamrPhantomPhantomMode(args: ISZ[String], i: Z): Option[SireumHamrPhantomPhantomMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { json, json_compact, msgpack }, but none found.")
      return None()
    }
    val r = parseSireumHamrPhantomPhantomModeH(args(i))
    return r
  }

  def parseSireumHamrPhantom(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Phantom: Headless OSATE AADL to AIR Translator
          |
          |Usage: ${st"""
                  |    phantom --update [--osate <path>] [--features <config>]
                  |
                  |      Just update/install features
                  |
                  |or: phantom [<options>] [<project-directory>]
                  |
                  |      Generate AIR.  Either:
                  |        - point to a directory containing a .project or .system file, or
                  |        - populate the 'projects', 'main-package', and 'sys-impl' options""".render}
          |
          |Available Options:
          |-s, --sys-impl           Name of the system implementation. (expects a string)
          |-a, --main-package       AADL main package file that contains a system
          |                           implementation. (expects a path)
          |-m, --mode               Serialization method (expects one of { json,
          |                           json_compact, msgpack }; default: json)
          |-f, --output-file        AIR output file path (expects a path)
          |-p, --projects           OSATE project directories, each must contain an OSATE
          |                           '.project' file (expects path strings)
          |-v, --verbose            Verbose output
          |    --verbose+           Increased verbose output
          |-h, --help               Display this information
          |
          |OSATE Options:
          |-o, --osate              Either the path to an existing installation of OSATE,
          |                           or the path where OSATE should be installed (expects
          |                           a path)
          |-u, --update             Update (or install) features
          |    --features           Plugin features to update/install, each of the form
          |                           <feature-id>=<repo-url-1>,...,<repo-url-N>. Latest
          |                           Sireum plugins installed if not provided (expects a
          |                           string separated by ";")
          |-v, --version            OSATE version (expects a string; default is
          |                           "2.11.0-vfinal")""".render

    var impl: Option[String] = None[String]()
    var main: Option[String] = None[String]()
    var mode: SireumHamrPhantomPhantomMode.Type = SireumHamrPhantomPhantomMode.Json
    var output: Option[String] = None[String]()
    var projects: ISZ[String] = ISZ[String]()
    var verbose: B = false
    var verbosePlus: B = false
    var osate: Option[String] = None[String]()
    var update: B = false
    var features: ISZ[String] = ISZ[String]()
    var version: Option[String] = Some("2.11.0-vfinal")
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-s" || arg == "--sys-impl") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => impl = v
             case _ => return None()
           }
         } else if (arg == "-a" || arg == "--main-package") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => main = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--mode") {
           val o: Option[SireumHamrPhantomPhantomMode.Type] = parseSireumHamrPhantomPhantomMode(args, j + 1)
           o match {
             case Some(v) => mode = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--output-file") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => output = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--projects") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => projects = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--verbose") {
           val o: Option[B] = { j = j - 1; Some(!verbose) }
           o match {
             case Some(v) => verbose = v
             case _ => return None()
           }
         } else if (arg == "--verbose+") {
           val o: Option[B] = { j = j - 1; Some(!verbosePlus) }
           o match {
             case Some(v) => verbosePlus = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--osate") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => osate = v
             case _ => return None()
           }
         } else if (arg == "-u" || arg == "--update") {
           val o: Option[B] = { j = j - 1; Some(!update) }
           o match {
             case Some(v) => update = v
             case _ => return None()
           }
         } else if (arg == "--features") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ';')
           o match {
             case Some(v) => features = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--version") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => version = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumHamrPhantomOption(help, parseArguments(args, j), impl, main, mode, output, projects, verbose, verbosePlus, osate, update, features, version))
  }

  def parseSireumLogika(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Logika Tools for Slang
            |
            |Available modes:
            |verifier                 Logika verifier""".render
      )
      return Some(HelpOption())
    }
    val opt = select("logika", args, i, ISZ("verifier"))
    opt match {
      case Some(string"verifier") => parseSireumLogikaVerifier(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumLogikaVerifierFPRoundingModeH(arg: String): Option[SireumLogikaVerifierFPRoundingMode.Type] = {
    arg.native match {
      case "NearestTiesToEven" => return Some(SireumLogikaVerifierFPRoundingMode.NearestTiesToEven)
      case "NearestTiesToAway" => return Some(SireumLogikaVerifierFPRoundingMode.NearestTiesToAway)
      case "TowardPositive" => return Some(SireumLogikaVerifierFPRoundingMode.TowardPositive)
      case "TowardNegative" => return Some(SireumLogikaVerifierFPRoundingMode.TowardNegative)
      case "TowardZero" => return Some(SireumLogikaVerifierFPRoundingMode.TowardZero)
      case s =>
        eprintln(s"Expecting one of the following: { NearestTiesToEven, NearestTiesToAway, TowardPositive, TowardNegative, TowardZero }, but found '$s'.")
        return None()
    }
  }

  def parseSireumLogikaVerifierFPRoundingMode(args: ISZ[String], i: Z): Option[SireumLogikaVerifierFPRoundingMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { NearestTiesToEven, NearestTiesToAway, TowardPositive, TowardNegative, TowardZero }, but none found.")
      return None()
    }
    val r = parseSireumLogikaVerifierFPRoundingModeH(args(i))
    return r
  }

  def parseSireumLogikaVerifierBranchParH(arg: String): Option[SireumLogikaVerifierBranchPar.Type] = {
    arg.native match {
      case "all" => return Some(SireumLogikaVerifierBranchPar.All)
      case "returns" => return Some(SireumLogikaVerifierBranchPar.Returns)
      case "disabled" => return Some(SireumLogikaVerifierBranchPar.Disabled)
      case s =>
        eprintln(s"Expecting one of the following: { all, returns, disabled }, but found '$s'.")
        return None()
    }
  }

  def parseSireumLogikaVerifierBranchPar(args: ISZ[String], i: Z): Option[SireumLogikaVerifierBranchPar.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { all, returns, disabled }, but none found.")
      return None()
    }
    val r = parseSireumLogikaVerifierBranchParH(args(i))
    return r
  }

  def parseSireumLogikaVerifier(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Logika Verifier for Slang
          |
          |Usage: <option>* <slang-file>+
          |
          |Available Options:
          |-r, --no-runtime         Do not use built-in runtime (use runtime in
          |                           sourcepath)
          |-s, --sourcepath         Sourcepath of Slang .scala files (expects path
          |                           strings)
          |-h, --help               Display this information
          |
          |Additional Verifications Options:
          |    --info-flow          Enable information flow verification
          |
          |Approximation Options:
          |    --c-bitwidth         Bit-width representation for C (character) values
          |                           (expected 8, 16, or 32) (expects an integer; default
          |                           is 32)
          |    --fp-rounding        Rounding mode for floating point numbers (expects one
          |                           of { NearestTiesToEven, NearestTiesToAway,
          |                           TowardPositive, TowardNegative, TowardZero };
          |                           default: NearestTiesToEven)
          |    --use-real           Use reals to approximate floating-point numbers
          |    --z-bitwidth         Bit-width representation for Z (integer) values
          |                           (expected 0, 8, 16, 32, 64) (expects an integer;
          |                           default is 0)
          |
          |Control Options:
          |    --interprocedural    Enable inter-procedural verification for invoked
          |                           methods without contracts
          |    --line               Focus verification to the specified program line
          |                           number (expects an integer; min is 0; default is 0)
          |    --sat                Enable assumption satisfiability checking
          |    --skip-methods       Skip checking methods with the specified
          |                           fully-qualified names or identifiers (expects a
          |                           string separated by ",")
          |    --skip-types         Skip checking traits, classes, and objects with the
          |                           specified fully-qualified names or identifiers
          |                           (expects a string separated by ",")
          |    --unroll             Enable loop unrolling when loop modifies clause is
          |                           unspecified
          |
          |Logging Options:
          |    --log-pc             Display path conditions before each statement
          |    --log-pc-lines       Display At(...) path condition line numbers and unique
          |                           symbolic value numbering
          |    --log-raw-pc         Display raw path conditions before each statement
          |    --log-vc             Display all verification conditions
          |    --log-vc-dir         Write all verification conditions in a directory
          |                           (expects a path)
          |
          |Optimizations Options:
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --par-branch-mode    Branch parallelization mode (expects one of { all,
          |                           returns, disabled }; default: all)
          |    --par-branch         Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |
          |Path Splitting Options:
          |    --dont-split-pfq     Do not force splitting in quantifiers and proof
          |                           functions derived from @strictpure methods
          |    --split-all          Split all
          |    --split-contract     Split on contract cases
          |    --split-if           Split on if-conditional expressions and statements
          |    --split-match        Split on match expressions and statements
          |
          |SMT2 Options:
          |    --rlimit             SMT2 solver resource limit (expects an integer; min is
          |                           0; default is 2000000)
          |    --smt2-seq           Disable SMT2 solvers parallelization
          |    --simplify           Simplify SMT2 query (experimental)
          |    --solver-sat         SMT2 configurations for satisfiability queries
          |                           (expects a string; default is "cvc4; z3;
          |                           cvc5,--finite-model-find")
          |    --solver-valid       SMT2 configurations for validity queries (expects a
          |                           string; default is "cvc4,--full-saturate-quant; z3;
          |                           cvc5,--full-saturate-quant")
          |-t, --timeout            Timeout (seconds) for SMT2 solver (expects an integer;
          |                           min is 1; default is 2)""".render

    var noRuntime: B = false
    var sourcepath: ISZ[String] = ISZ[String]()
    var infoFlow: B = false
    var charBitWidth: Z = 32
    var fpRounding: SireumLogikaVerifierFPRoundingMode.Type = SireumLogikaVerifierFPRoundingMode.NearestTiesToEven
    var useReal: B = false
    var intBitWidth: Z = 0
    var interprocedural: B = false
    var line: Z = 0
    var sat: B = false
    var skipMethods: ISZ[String] = ISZ[String]()
    var skipTypes: ISZ[String] = ISZ[String]()
    var unroll: B = false
    var logPc: B = false
    var logPcLines: B = false
    var logRawPc: B = false
    var logVc: B = false
    var logVcDir: Option[String] = None[String]()
    var par: Option[Z] = None()
    var branchParMode: SireumLogikaVerifierBranchPar.Type = SireumLogikaVerifierBranchPar.All
    var branchPar: Option[Z] = None()
    var dontSplitFunQuant: B = false
    var splitAll: B = false
    var splitContract: B = false
    var splitIf: B = false
    var splitMatch: B = false
    var rlimit: Z = 2000000
    var sequential: B = false
    var simplify: B = false
    var smt2SatConfigs: Option[String] = Some("cvc4; z3; cvc5,--finite-model-find")
    var smt2ValidConfigs: Option[String] = Some("cvc4,--full-saturate-quant; z3; cvc5,--full-saturate-quant")
    var timeout: Z = 2
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-r" || arg == "--no-runtime") {
           val o: Option[B] = { j = j - 1; Some(!noRuntime) }
           o match {
             case Some(v) => noRuntime = v
             case _ => return None()
           }
         } else if (arg == "-s" || arg == "--sourcepath") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => sourcepath = v
             case _ => return None()
           }
         } else if (arg == "--info-flow") {
           val o: Option[B] = { j = j - 1; Some(!infoFlow) }
           o match {
             case Some(v) => infoFlow = v
             case _ => return None()
           }
         } else if (arg == "--c-bitwidth") {
           val o: Option[Z] = parseNum(args, j + 1, None(), None())
           o match {
             case Some(v) => charBitWidth = v
             case _ => return None()
           }
         } else if (arg == "--fp-rounding") {
           val o: Option[SireumLogikaVerifierFPRoundingMode.Type] = parseSireumLogikaVerifierFPRoundingMode(args, j + 1)
           o match {
             case Some(v) => fpRounding = v
             case _ => return None()
           }
         } else if (arg == "--use-real") {
           val o: Option[B] = { j = j - 1; Some(!useReal) }
           o match {
             case Some(v) => useReal = v
             case _ => return None()
           }
         } else if (arg == "--z-bitwidth") {
           val o: Option[Z] = parseNum(args, j + 1, None(), None())
           o match {
             case Some(v) => intBitWidth = v
             case _ => return None()
           }
         } else if (arg == "--interprocedural") {
           val o: Option[B] = { j = j - 1; Some(!interprocedural) }
           o match {
             case Some(v) => interprocedural = v
             case _ => return None()
           }
         } else if (arg == "--line") {
           val o: Option[Z] = parseNum(args, j + 1, Some(0), None())
           o match {
             case Some(v) => line = v
             case _ => return None()
           }
         } else if (arg == "--sat") {
           val o: Option[B] = { j = j - 1; Some(!sat) }
           o match {
             case Some(v) => sat = v
             case _ => return None()
           }
         } else if (arg == "--skip-methods") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => skipMethods = v
             case _ => return None()
           }
         } else if (arg == "--skip-types") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => skipTypes = v
             case _ => return None()
           }
         } else if (arg == "--unroll") {
           val o: Option[B] = { j = j - 1; Some(!unroll) }
           o match {
             case Some(v) => unroll = v
             case _ => return None()
           }
         } else if (arg == "--log-pc") {
           val o: Option[B] = { j = j - 1; Some(!logPc) }
           o match {
             case Some(v) => logPc = v
             case _ => return None()
           }
         } else if (arg == "--log-pc-lines") {
           val o: Option[B] = { j = j - 1; Some(!logPcLines) }
           o match {
             case Some(v) => logPcLines = v
             case _ => return None()
           }
         } else if (arg == "--log-raw-pc") {
           val o: Option[B] = { j = j - 1; Some(!logRawPc) }
           o match {
             case Some(v) => logRawPc = v
             case _ => return None()
           }
         } else if (arg == "--log-vc") {
           val o: Option[B] = { j = j - 1; Some(!logVc) }
           o match {
             case Some(v) => logVc = v
             case _ => return None()
           }
         } else if (arg == "--log-vc-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => logVcDir = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--par") {
           val o: Option[Option[Z]] = parseNumFlag(args, j + 1, Some(1), Some(100)) match {
             case o@Some(None()) => j = j - 1; Some(Some(100))
             case o => o
           }
           o match {
             case Some(v) => par = v
             case _ => return None()
           }
         } else if (arg == "--par-branch-mode") {
           val o: Option[SireumLogikaVerifierBranchPar.Type] = parseSireumLogikaVerifierBranchPar(args, j + 1)
           o match {
             case Some(v) => branchParMode = v
             case _ => return None()
           }
         } else if (arg == "--par-branch") {
           val o: Option[Option[Z]] = parseNumFlag(args, j + 1, Some(1), Some(100)) match {
             case o@Some(None()) => j = j - 1; Some(Some(100))
             case o => o
           }
           o match {
             case Some(v) => branchPar = v
             case _ => return None()
           }
         } else if (arg == "--dont-split-pfq") {
           val o: Option[B] = { j = j - 1; Some(!dontSplitFunQuant) }
           o match {
             case Some(v) => dontSplitFunQuant = v
             case _ => return None()
           }
         } else if (arg == "--split-all") {
           val o: Option[B] = { j = j - 1; Some(!splitAll) }
           o match {
             case Some(v) => splitAll = v
             case _ => return None()
           }
         } else if (arg == "--split-contract") {
           val o: Option[B] = { j = j - 1; Some(!splitContract) }
           o match {
             case Some(v) => splitContract = v
             case _ => return None()
           }
         } else if (arg == "--split-if") {
           val o: Option[B] = { j = j - 1; Some(!splitIf) }
           o match {
             case Some(v) => splitIf = v
             case _ => return None()
           }
         } else if (arg == "--split-match") {
           val o: Option[B] = { j = j - 1; Some(!splitMatch) }
           o match {
             case Some(v) => splitMatch = v
             case _ => return None()
           }
         } else if (arg == "--rlimit") {
           val o: Option[Z] = parseNum(args, j + 1, Some(0), None())
           o match {
             case Some(v) => rlimit = v
             case _ => return None()
           }
         } else if (arg == "--smt2-seq") {
           val o: Option[B] = { j = j - 1; Some(!sequential) }
           o match {
             case Some(v) => sequential = v
             case _ => return None()
           }
         } else if (arg == "--simplify") {
           val o: Option[B] = { j = j - 1; Some(!simplify) }
           o match {
             case Some(v) => simplify = v
             case _ => return None()
           }
         } else if (arg == "--solver-sat") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => smt2SatConfigs = v
             case _ => return None()
           }
         } else if (arg == "--solver-valid") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => smt2ValidConfigs = v
             case _ => return None()
           }
         } else if (arg == "-t" || arg == "--timeout") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => timeout = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumLogikaVerifierOption(help, parseArguments(args, j), noRuntime, sourcepath, infoFlow, charBitWidth, fpRounding, useReal, intBitWidth, interprocedural, line, sat, skipMethods, skipTypes, unroll, logPc, logPcLines, logRawPc, logVc, logVcDir, par, branchParMode, branchPar, dontSplitFunQuant, splitAll, splitContract, splitIf, splitMatch, rlimit, sequential, simplify, smt2SatConfigs, smt2ValidConfigs, timeout))
  }

  def parseSireumParser(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum Parser Tools
            |
            |Available modes:
            |gen                      Parser generator""".render
      )
      return Some(HelpOption())
    }
    val opt = select("parser", args, i, ISZ("gen"))
    opt match {
      case Some(string"gen") => parseSireumParserGen(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumParserGenParserGenModeH(arg: String): Option[SireumParserGenParserGenMode.Type] = {
    arg.native match {
      case "slang" => return Some(SireumParserGenParserGenMode.Slang)
      case "antlr3" => return Some(SireumParserGenParserGenMode.Antlr3)
      case s =>
        eprintln(s"Expecting one of the following: { slang, antlr3 }, but found '$s'.")
        return None()
    }
  }

  def parseSireumParserGenParserGenMode(args: ISZ[String], i: Z): Option[SireumParserGenParserGenMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { slang, antlr3 }, but none found.")
      return None()
    }
    val r = parseSireumParserGenParserGenModeH(args(i))
    return r
  }

  def parseSireumParserGen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Parser Generator
          |
          |Usage: <option>* <file.g>
          |
          |Available Options:
          |-z, --memoize            Use memoization in the generated parser
          |-m, --mode               Grammar input parsing/lexing engines (expects one of {
          |                           slang, antlr3 }; default: slang)
          |-n, --name               Type simple name for the generated parser/lexer prefix
          |                           (default: the grammar file name without extension)
          |                           (expects a string)
          |    --no-backtracking    Disable backtracking in the generated parser
          |    --non-predictive     Make the generated parser non-predictive
          |-l, --license            License file to be inserted in the file header
          |                           (expects a path)
          |-o, --output-dir         Output directory for the generated transformer Slang
          |                           files (expects a path; default is ".")
          |-p, --package            Package name for the generated parser/lexer (expects a
          |                           string separated by ".")
          |-h, --help               Display this information""".render

    var memoize: B = false
    var mode: SireumParserGenParserGenMode.Type = SireumParserGenParserGenMode.Slang
    var name: Option[String] = None[String]()
    var backtracking: B = true
    var predictive: B = true
    var license: Option[String] = None[String]()
    var outputDir: Option[String] = Some(".")
    var packageName: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-z" || arg == "--memoize") {
           val o: Option[B] = { j = j - 1; Some(!memoize) }
           o match {
             case Some(v) => memoize = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--mode") {
           val o: Option[SireumParserGenParserGenMode.Type] = parseSireumParserGenParserGenMode(args, j + 1)
           o match {
             case Some(v) => mode = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "--no-backtracking") {
           val o: Option[B] = { j = j - 1; Some(!backtracking) }
           o match {
             case Some(v) => backtracking = v
             case _ => return None()
           }
         } else if (arg == "--non-predictive") {
           val o: Option[B] = { j = j - 1; Some(!predictive) }
           o match {
             case Some(v) => predictive = v
             case _ => return None()
           }
         } else if (arg == "-l" || arg == "--license") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => license = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => outputDir = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--package") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, '.')
           o match {
             case Some(v) => packageName = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumParserGenOption(help, parseArguments(args, j), memoize, mode, name, backtracking, predictive, license, outputDir, packageName))
  }

  def parseSireumProyek(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum Proyek: Build Tools for Slang Projects
            |
            |Available modes:
            |assemble                 Proyek jar assembler
            |compile                  Proyek compiler
            |ive                      Sireum IVE proyek generator
            |logika                   Sireum Logika for Proyek
            |publish                  Proyek publisher
            |run                      Proyek program runner
            |stats                    Proyek statistics reporter
            |test                     Proyek test runner
            |tipe                     Slang proyek type checker""".render
      )
      return Some(HelpOption())
    }
    val opt = select("proyek", args, i, ISZ("assemble", "compile", "ive", "logika", "publish", "run", "stats", "test", "tipe"))
    opt match {
      case Some(string"assemble") => parseSireumProyekAssemble(args, i + 1)
      case Some(string"compile") => parseSireumProyekCompile(args, i + 1)
      case Some(string"ive") => parseSireumProyekIve(args, i + 1)
      case Some(string"logika") => parseSireumProyekLogika(args, i + 1)
      case Some(string"publish") => parseSireumProyekPublish(args, i + 1)
      case Some(string"run") => parseSireumProyekRun(args, i + 1)
      case Some(string"stats") => parseSireumProyekStats(args, i + 1)
      case Some(string"test") => parseSireumProyekTest(args, i + 1)
      case Some(string"tipe") => parseSireumProyekTipe(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumProyekAssemble(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Proyek Jar Assembler
          |
          |Usage: <options>* <dir>
          |
          |Available Options:
          |-j, --jar                The assembled jar filename (defaults to the project
          |                           name) (expects a string)
          |-m, --main               The main class fully qualified name (expects a string)
          |    --native             Generates native image
          |    --uber               Generates uber jar
          |-h, --help               Display this information
          |
          |Project Options:
          |    --ignore-runtime     Ignore runtime library dependency version when
          |                           detecting changes
          |    --json               The JSON file to load project definitions from
          |                           (mutually exclusive with the 'project' option)
          |                           (expects a path)
          |-n, --name               Project name (defaults to the directory name of <dir>)
          |                           (expects a string)
          |-o, --out                Output directory name under <dir> (expects a string;
          |                           default is "out")
          |    --project            The project.cmd file accepting the 'json' argument
          |                           (defaults to
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
          |                           mutually exclusive with the 'json' option) (expects
          |                           a path)
          |    --slice              Slice the project starting from the given module IDs
          |                           and their dependencies (expects a string separated
          |                           by ",")
          |    --symlink            Follow symbolic link when searching for files
          |-v, --versions           The properties file(s) containing version information
          |                           (defaults to <dir>${Os.fileSep}versions.properties)
          |                           (expects path strings)
          |
          |Compilation Options:
          |    --javac              Javac options (expects a string separated by ",";
          |                           default is "-source, 1.8, -target, 1.8, -encoding,
          |                           utf8, -XDignore.symbol.file, -Xlint:-options,
          |                           -Xlint:deprecation")
          |-f, --fresh              Fresh compilation from a clean slate
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --recompile          Module IDs to force recompilation on (expects a string
          |                           separated by ",")
          |    --scalac             Scalac options (expects a string separated by ",";
          |                           default is "-release, 8, -deprecation, -Yrangepos,
          |                           -Ydelambdafy:method, -feature, -unchecked,
          |                           -Xfatal-warnings, -language:postfixOps")
          |    --sha3               Use SHA3 instead of time stamp for detecting file
          |                           changes
          |    --skip-compile       Skip compilation
          |
          |Ivy Dependencies Options:
          |-c, --cache              Ivy cache directory (defaults to Coursier's default
          |                           cache directory) (expects a path)
          |    --no-docs            Disable retrieval of javadoc files from Ivy
          |                           dependencies
          |    --no-sources         Disable retrieval of source files from Ivy
          |                           dependencies
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")""".render

    var jar: Option[String] = None[String]()
    var mainClass: Option[String] = None[String]()
    var isNative: B = false
    var uber: B = false
    var ignoreRuntime: B = false
    var json: Option[String] = None[String]()
    var name: Option[String] = None[String]()
    var outputDirName: Option[String] = Some("out")
    var project: Option[String] = None[String]()
    var slice: ISZ[String] = ISZ[String]()
    var symlink: B = false
    var versions: ISZ[String] = ISZ[String]()
    var javac: ISZ[String] = ISZ("-source", "1.8", "-target", "1.8", "-encoding", "utf8", "-XDignore.symbol.file", "-Xlint:-options", "-Xlint:deprecation")
    var fresh: B = false
    var par: Option[Z] = None()
    var recompile: ISZ[String] = ISZ[String]()
    var scalac: ISZ[String] = ISZ("-release", "8", "-deprecation", "-Yrangepos", "-Ydelambdafy:method", "-feature", "-unchecked", "-Xfatal-warnings", "-language:postfixOps")
    var sha3: B = false
    var skipCompile: B = false
    var cache: Option[String] = None[String]()
    var docs: B = true
    var sources: B = true
    var repositories: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-j" || arg == "--jar") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => jar = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--main") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => mainClass = v
             case _ => return None()
           }
         } else if (arg == "--native") {
           val o: Option[B] = { j = j - 1; Some(!isNative) }
           o match {
             case Some(v) => isNative = v
             case _ => return None()
           }
         } else if (arg == "--uber") {
           val o: Option[B] = { j = j - 1; Some(!uber) }
           o match {
             case Some(v) => uber = v
             case _ => return None()
           }
         } else if (arg == "--ignore-runtime") {
           val o: Option[B] = { j = j - 1; Some(!ignoreRuntime) }
           o match {
             case Some(v) => ignoreRuntime = v
             case _ => return None()
           }
         } else if (arg == "--json") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => json = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--out") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => outputDirName = v
             case _ => return None()
           }
         } else if (arg == "--project") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => project = v
             case _ => return None()
           }
         } else if (arg == "--slice") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => slice = v
             case _ => return None()
           }
         } else if (arg == "--symlink") {
           val o: Option[B] = { j = j - 1; Some(!symlink) }
           o match {
             case Some(v) => symlink = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--versions") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => versions = v
             case _ => return None()
           }
         } else if (arg == "--javac") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => javac = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--fresh") {
           val o: Option[B] = { j = j - 1; Some(!fresh) }
           o match {
             case Some(v) => fresh = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--par") {
           val o: Option[Option[Z]] = parseNumFlag(args, j + 1, Some(1), Some(100)) match {
             case o@Some(None()) => j = j - 1; Some(Some(100))
             case o => o
           }
           o match {
             case Some(v) => par = v
             case _ => return None()
           }
         } else if (arg == "--recompile") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => recompile = v
             case _ => return None()
           }
         } else if (arg == "--scalac") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => scalac = v
             case _ => return None()
           }
         } else if (arg == "--sha3") {
           val o: Option[B] = { j = j - 1; Some(!sha3) }
           o match {
             case Some(v) => sha3 = v
             case _ => return None()
           }
         } else if (arg == "--skip-compile") {
           val o: Option[B] = { j = j - 1; Some(!skipCompile) }
           o match {
             case Some(v) => skipCompile = v
             case _ => return None()
           }
         } else if (arg == "-c" || arg == "--cache") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => cache = v
             case _ => return None()
           }
         } else if (arg == "--no-docs") {
           val o: Option[B] = { j = j - 1; Some(!docs) }
           o match {
             case Some(v) => docs = v
             case _ => return None()
           }
         } else if (arg == "--no-sources") {
           val o: Option[B] = { j = j - 1; Some(!sources) }
           o match {
             case Some(v) => sources = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--repositories") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => repositories = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumProyekAssembleOption(help, parseArguments(args, j), jar, mainClass, isNative, uber, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, javac, fresh, par, recompile, scalac, sha3, skipCompile, cache, docs, sources, repositories))
  }

  def parseSireumProyekCompile(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Proyek Compiler
          |
          |Usage: <options>* <dir>
          |
          |Available Options:
          |    --javac              Javac options (expects a string separated by ",";
          |                           default is "-source, 1.8, -target, 1.8, -encoding,
          |                           utf8, -XDignore.symbol.file, -Xlint:-options,
          |                           -Xlint:deprecation")
          |-f, --fresh              Fresh compilation from a clean slate
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --recompile          Module IDs to force recompilation on (expects a string
          |                           separated by ",")
          |    --scalac             Scalac options (expects a string separated by ",";
          |                           default is "-release, 8, -deprecation, -Yrangepos,
          |                           -Ydelambdafy:method, -feature, -unchecked,
          |                           -Xfatal-warnings, -language:postfixOps")
          |    --sha3               Use SHA3 instead of time stamp for detecting file
          |                           changes
          |    --js                 Compile using Scala.js
          |-h, --help               Display this information
          |
          |Project Options:
          |    --ignore-runtime     Ignore runtime library dependency version when
          |                           detecting changes
          |    --json               The JSON file to load project definitions from
          |                           (mutually exclusive with the 'project' option)
          |                           (expects a path)
          |-n, --name               Project name (defaults to the directory name of <dir>)
          |                           (expects a string)
          |-o, --out                Output directory name under <dir> (expects a string;
          |                           default is "out")
          |    --project            The project.cmd file accepting the 'json' argument
          |                           (defaults to
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
          |                           mutually exclusive with the 'json' option) (expects
          |                           a path)
          |    --slice              Slice the project starting from the given module IDs
          |                           and their dependencies (expects a string separated
          |                           by ",")
          |    --symlink            Follow symbolic link when searching for files
          |-v, --versions           The properties file(s) containing version information
          |                           (defaults to <dir>${Os.fileSep}versions.properties)
          |                           (expects path strings)
          |
          |Ivy Dependencies Options:
          |-c, --cache              Ivy cache directory (defaults to Coursier's default
          |                           cache directory) (expects a path)
          |    --no-docs            Disable retrieval of javadoc files from Ivy
          |                           dependencies
          |    --no-sources         Disable retrieval of source files from Ivy
          |                           dependencies
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")""".render

    var javac: ISZ[String] = ISZ("-source", "1.8", "-target", "1.8", "-encoding", "utf8", "-XDignore.symbol.file", "-Xlint:-options", "-Xlint:deprecation")
    var fresh: B = false
    var par: Option[Z] = None()
    var recompile: ISZ[String] = ISZ[String]()
    var scalac: ISZ[String] = ISZ("-release", "8", "-deprecation", "-Yrangepos", "-Ydelambdafy:method", "-feature", "-unchecked", "-Xfatal-warnings", "-language:postfixOps")
    var sha3: B = false
    var js: B = false
    var ignoreRuntime: B = false
    var json: Option[String] = None[String]()
    var name: Option[String] = None[String]()
    var outputDirName: Option[String] = Some("out")
    var project: Option[String] = None[String]()
    var slice: ISZ[String] = ISZ[String]()
    var symlink: B = false
    var versions: ISZ[String] = ISZ[String]()
    var cache: Option[String] = None[String]()
    var docs: B = true
    var sources: B = true
    var repositories: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--javac") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => javac = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--fresh") {
           val o: Option[B] = { j = j - 1; Some(!fresh) }
           o match {
             case Some(v) => fresh = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--par") {
           val o: Option[Option[Z]] = parseNumFlag(args, j + 1, Some(1), Some(100)) match {
             case o@Some(None()) => j = j - 1; Some(Some(100))
             case o => o
           }
           o match {
             case Some(v) => par = v
             case _ => return None()
           }
         } else if (arg == "--recompile") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => recompile = v
             case _ => return None()
           }
         } else if (arg == "--scalac") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => scalac = v
             case _ => return None()
           }
         } else if (arg == "--sha3") {
           val o: Option[B] = { j = j - 1; Some(!sha3) }
           o match {
             case Some(v) => sha3 = v
             case _ => return None()
           }
         } else if (arg == "--js") {
           val o: Option[B] = { j = j - 1; Some(!js) }
           o match {
             case Some(v) => js = v
             case _ => return None()
           }
         } else if (arg == "--ignore-runtime") {
           val o: Option[B] = { j = j - 1; Some(!ignoreRuntime) }
           o match {
             case Some(v) => ignoreRuntime = v
             case _ => return None()
           }
         } else if (arg == "--json") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => json = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--out") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => outputDirName = v
             case _ => return None()
           }
         } else if (arg == "--project") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => project = v
             case _ => return None()
           }
         } else if (arg == "--slice") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => slice = v
             case _ => return None()
           }
         } else if (arg == "--symlink") {
           val o: Option[B] = { j = j - 1; Some(!symlink) }
           o match {
             case Some(v) => symlink = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--versions") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => versions = v
             case _ => return None()
           }
         } else if (arg == "-c" || arg == "--cache") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => cache = v
             case _ => return None()
           }
         } else if (arg == "--no-docs") {
           val o: Option[B] = { j = j - 1; Some(!docs) }
           o match {
             case Some(v) => docs = v
             case _ => return None()
           }
         } else if (arg == "--no-sources") {
           val o: Option[B] = { j = j - 1; Some(!sources) }
           o match {
             case Some(v) => sources = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--repositories") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => repositories = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumProyekCompileOption(help, parseArguments(args, j), javac, fresh, par, recompile, scalac, sha3, js, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, cache, docs, sources, repositories))
  }

  def parseSireumProyekIveEditionH(arg: String): Option[SireumProyekIveEdition.Type] = {
    arg.native match {
      case "community" => return Some(SireumProyekIveEdition.Community)
      case "ultimate" => return Some(SireumProyekIveEdition.Ultimate)
      case "server" => return Some(SireumProyekIveEdition.Server)
      case s =>
        eprintln(s"Expecting one of the following: { community, ultimate, server }, but found '$s'.")
        return None()
    }
  }

  def parseSireumProyekIveEdition(args: ISZ[String], i: Z): Option[SireumProyekIveEdition.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { community, ultimate, server }, but none found.")
      return None()
    }
    val r = parseSireumProyekIveEditionH(args(i))
    return r
  }

  def parseSireumProyekIve(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum IVE Proyek Generator
          |
          |Usage: <options>* <dir>
          |
          |Available Options:
          |    --empty              Create an empty project definition
          |-f, --force              Force generation of application-wide configurations
          |                           (e.g., JDK info, etc.)
          |-e, --edition            IntelliJ edition (auto-detected if there is only one
          |                           installed) (expects one of { community, ultimate,
          |                           server }; default: community)
          |    --javac              Javac options (expects a string separated by ",";
          |                           default is "-source, 1.8, -target, 1.8, -encoding,
          |                           utf8, -XDignore.symbol.file, -Xlint:-options,
          |                           -Xlint:deprecation")
          |    --scalac             Scalac options (expects a string separated by ",";
          |                           default is "-release, 8, -deprecation, -Yrangepos,
          |                           -Ydelambdafy:method, -feature, -unchecked,
          |                           -Xfatal-warnings, -language:postfixOps")
          |-h, --help               Display this information
          |
          |Project Options:
          |    --ignore-runtime     Ignore runtime library dependency version when
          |                           detecting changes
          |    --json               The JSON file to load project definitions from
          |                           (mutually exclusive with the 'project' option)
          |                           (expects a path)
          |-n, --name               Project name (defaults to the directory name of <dir>)
          |                           (expects a string)
          |-o, --out                Output directory name under <dir> (expects a string;
          |                           default is "out")
          |    --project            The project.cmd file accepting the 'json' argument
          |                           (defaults to
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
          |                           mutually exclusive with the 'json' option) (expects
          |                           a path)
          |    --slice              Slice the project starting from the given module IDs
          |                           and their dependencies (expects a string separated
          |                           by ",")
          |    --symlink            Follow symbolic link when searching for files
          |-v, --versions           The properties file(s) containing version information
          |                           (defaults to <dir>${Os.fileSep}versions.properties)
          |                           (expects path strings)
          |
          |Ivy Dependencies Options:
          |-c, --cache              Ivy cache directory (defaults to Coursier's default
          |                           cache directory) (expects a path)
          |    --no-docs            Disable retrieval of javadoc files from Ivy
          |                           dependencies
          |    --no-sources         Disable retrieval of source files from Ivy
          |                           dependencies
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")""".render

    var empty: B = false
    var force: B = false
    var edition: SireumProyekIveEdition.Type = SireumProyekIveEdition.Community
    var javac: ISZ[String] = ISZ("-source", "1.8", "-target", "1.8", "-encoding", "utf8", "-XDignore.symbol.file", "-Xlint:-options", "-Xlint:deprecation")
    var scalac: ISZ[String] = ISZ("-release", "8", "-deprecation", "-Yrangepos", "-Ydelambdafy:method", "-feature", "-unchecked", "-Xfatal-warnings", "-language:postfixOps")
    var ignoreRuntime: B = false
    var json: Option[String] = None[String]()
    var name: Option[String] = None[String]()
    var outputDirName: Option[String] = Some("out")
    var project: Option[String] = None[String]()
    var slice: ISZ[String] = ISZ[String]()
    var symlink: B = false
    var versions: ISZ[String] = ISZ[String]()
    var cache: Option[String] = None[String]()
    var docs: B = true
    var sources: B = true
    var repositories: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--empty") {
           val o: Option[B] = { j = j - 1; Some(!empty) }
           o match {
             case Some(v) => empty = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--force") {
           val o: Option[B] = { j = j - 1; Some(!force) }
           o match {
             case Some(v) => force = v
             case _ => return None()
           }
         } else if (arg == "-e" || arg == "--edition") {
           val o: Option[SireumProyekIveEdition.Type] = parseSireumProyekIveEdition(args, j + 1)
           o match {
             case Some(v) => edition = v
             case _ => return None()
           }
         } else if (arg == "--javac") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => javac = v
             case _ => return None()
           }
         } else if (arg == "--scalac") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => scalac = v
             case _ => return None()
           }
         } else if (arg == "--ignore-runtime") {
           val o: Option[B] = { j = j - 1; Some(!ignoreRuntime) }
           o match {
             case Some(v) => ignoreRuntime = v
             case _ => return None()
           }
         } else if (arg == "--json") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => json = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--out") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => outputDirName = v
             case _ => return None()
           }
         } else if (arg == "--project") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => project = v
             case _ => return None()
           }
         } else if (arg == "--slice") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => slice = v
             case _ => return None()
           }
         } else if (arg == "--symlink") {
           val o: Option[B] = { j = j - 1; Some(!symlink) }
           o match {
             case Some(v) => symlink = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--versions") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => versions = v
             case _ => return None()
           }
         } else if (arg == "-c" || arg == "--cache") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => cache = v
             case _ => return None()
           }
         } else if (arg == "--no-docs") {
           val o: Option[B] = { j = j - 1; Some(!docs) }
           o match {
             case Some(v) => docs = v
             case _ => return None()
           }
         } else if (arg == "--no-sources") {
           val o: Option[B] = { j = j - 1; Some(!sources) }
           o match {
             case Some(v) => sources = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--repositories") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => repositories = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumProyekIveOption(help, parseArguments(args, j), empty, force, edition, javac, scalac, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, cache, docs, sources, repositories))
  }

  def parseSireumProyekLogikaFPRoundingModeH(arg: String): Option[SireumProyekLogikaFPRoundingMode.Type] = {
    arg.native match {
      case "NearestTiesToEven" => return Some(SireumProyekLogikaFPRoundingMode.NearestTiesToEven)
      case "NearestTiesToAway" => return Some(SireumProyekLogikaFPRoundingMode.NearestTiesToAway)
      case "TowardPositive" => return Some(SireumProyekLogikaFPRoundingMode.TowardPositive)
      case "TowardNegative" => return Some(SireumProyekLogikaFPRoundingMode.TowardNegative)
      case "TowardZero" => return Some(SireumProyekLogikaFPRoundingMode.TowardZero)
      case s =>
        eprintln(s"Expecting one of the following: { NearestTiesToEven, NearestTiesToAway, TowardPositive, TowardNegative, TowardZero }, but found '$s'.")
        return None()
    }
  }

  def parseSireumProyekLogikaFPRoundingMode(args: ISZ[String], i: Z): Option[SireumProyekLogikaFPRoundingMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { NearestTiesToEven, NearestTiesToAway, TowardPositive, TowardNegative, TowardZero }, but none found.")
      return None()
    }
    val r = parseSireumProyekLogikaFPRoundingModeH(args(i))
    return r
  }

  def parseSireumProyekLogikaBranchParH(arg: String): Option[SireumProyekLogikaBranchPar.Type] = {
    arg.native match {
      case "all" => return Some(SireumProyekLogikaBranchPar.All)
      case "returns" => return Some(SireumProyekLogikaBranchPar.Returns)
      case "disabled" => return Some(SireumProyekLogikaBranchPar.Disabled)
      case s =>
        eprintln(s"Expecting one of the following: { all, returns, disabled }, but found '$s'.")
        return None()
    }
  }

  def parseSireumProyekLogikaBranchPar(args: ISZ[String], i: Z): Option[SireumProyekLogikaBranchPar.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { all, returns, disabled }, but none found.")
      return None()
    }
    val r = parseSireumProyekLogikaBranchParH(args(i))
    return r
  }

  def parseSireumProyekLogika(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Logika for Proyek
          |
          |Usage: <options>* <dir> <file>*
          |
          |Available Options:
          |    --all                Check all Slang files
          |    --strict-aliasing    Enable strict aliasing check
          |    --verbose            Enable verbose mode
          |-h, --help               Display this information
          |
          |Project Options:
          |    --ignore-runtime     Ignore runtime library dependency version when
          |                           detecting changes
          |    --json               The JSON file to load project definitions from
          |                           (mutually exclusive with the 'project' option)
          |                           (expects a path)
          |-n, --name               Project name (defaults to the directory name of <dir>)
          |                           (expects a string)
          |-o, --out                Output directory name under <dir> (expects a string;
          |                           default is "out")
          |    --project            The project.cmd file accepting the 'json' argument
          |                           (defaults to
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
          |                           mutually exclusive with the 'json' option) (expects
          |                           a path)
          |    --slice              Slice the project starting from the given module IDs
          |                           and their dependencies (expects a string separated
          |                           by ",")
          |    --symlink            Follow symbolic link when searching for files
          |-v, --versions           The properties file(s) containing version information
          |                           (defaults to <dir>${Os.fileSep}versions.properties)
          |                           (expects path strings)
          |
          |Ivy Dependencies Options:
          |-c, --cache              Ivy cache directory (defaults to Coursier's default
          |                           cache directory) (expects a path)
          |    --no-docs            Disable retrieval of javadoc files from Ivy
          |                           dependencies
          |    --no-sources         Disable retrieval of source files from Ivy
          |                           dependencies
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")
          |
          |Additional Verifications Options:
          |    --info-flow          Enable information flow verification
          |
          |Approximation Options:
          |    --c-bitwidth         Bit-width representation for C (character) values
          |                           (expected 8, 16, or 32) (expects an integer; default
          |                           is 32)
          |    --fp-rounding        Rounding mode for floating point numbers (expects one
          |                           of { NearestTiesToEven, NearestTiesToAway,
          |                           TowardPositive, TowardNegative, TowardZero };
          |                           default: NearestTiesToEven)
          |    --use-real           Use reals to approximate floating-point numbers
          |    --z-bitwidth         Bit-width representation for Z (integer) values
          |                           (expected 0, 8, 16, 32, 64) (expects an integer;
          |                           default is 0)
          |
          |Control Options:
          |    --interprocedural    Enable inter-procedural verification for invoked
          |                           methods without contracts
          |    --line               Focus verification to the specified program line
          |                           number (expects an integer; min is 0; default is 0)
          |    --sat                Enable assumption satisfiability checking
          |    --skip-methods       Skip checking methods with the specified
          |                           fully-qualified names or identifiers (expects a
          |                           string separated by ",")
          |    --skip-types         Skip checking traits, classes, and objects with the
          |                           specified fully-qualified names or identifiers
          |                           (expects a string separated by ",")
          |    --unroll             Enable loop unrolling when loop modifies clause is
          |                           unspecified
          |
          |Logging Options:
          |    --log-pc             Display path conditions before each statement
          |    --log-pc-lines       Display At(...) path condition line numbers and unique
          |                           symbolic value numbering
          |    --log-raw-pc         Display raw path conditions before each statement
          |    --log-vc             Display all verification conditions
          |    --log-vc-dir         Write all verification conditions in a directory
          |                           (expects a path)
          |
          |Optimizations Options:
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --par-branch-mode    Branch parallelization mode (expects one of { all,
          |                           returns, disabled }; default: all)
          |    --par-branch         Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |
          |Path Splitting Options:
          |    --dont-split-pfq     Do not force splitting in quantifiers and proof
          |                           functions derived from @strictpure methods
          |    --split-all          Split all
          |    --split-contract     Split on contract cases
          |    --split-if           Split on if-conditional expressions and statements
          |    --split-match        Split on match expressions and statements
          |
          |SMT2 Options:
          |    --rlimit             SMT2 solver resource limit (expects an integer; min is
          |                           0; default is 2000000)
          |    --smt2-seq           Disable SMT2 solvers parallelization
          |    --simplify           Simplify SMT2 query (experimental)
          |    --solver-sat         SMT2 configurations for satisfiability queries
          |                           (expects a string; default is "cvc4; z3;
          |                           cvc5,--finite-model-find")
          |    --solver-valid       SMT2 configurations for validity queries (expects a
          |                           string; default is "cvc4,--full-saturate-quant; z3;
          |                           cvc5,--full-saturate-quant")
          |-t, --timeout            Timeout (seconds) for SMT2 solver (expects an integer;
          |                           min is 1; default is 2)""".render

    var all: B = false
    var strictAliasing: B = false
    var verbose: B = false
    var ignoreRuntime: B = false
    var json: Option[String] = None[String]()
    var name: Option[String] = None[String]()
    var outputDirName: Option[String] = Some("out")
    var project: Option[String] = None[String]()
    var slice: ISZ[String] = ISZ[String]()
    var symlink: B = false
    var versions: ISZ[String] = ISZ[String]()
    var cache: Option[String] = None[String]()
    var docs: B = true
    var sources: B = true
    var repositories: ISZ[String] = ISZ[String]()
    var infoFlow: B = false
    var charBitWidth: Z = 32
    var fpRounding: SireumProyekLogikaFPRoundingMode.Type = SireumProyekLogikaFPRoundingMode.NearestTiesToEven
    var useReal: B = false
    var intBitWidth: Z = 0
    var interprocedural: B = false
    var line: Z = 0
    var sat: B = false
    var skipMethods: ISZ[String] = ISZ[String]()
    var skipTypes: ISZ[String] = ISZ[String]()
    var unroll: B = false
    var logPc: B = false
    var logPcLines: B = false
    var logRawPc: B = false
    var logVc: B = false
    var logVcDir: Option[String] = None[String]()
    var par: Option[Z] = None()
    var branchParMode: SireumProyekLogikaBranchPar.Type = SireumProyekLogikaBranchPar.All
    var branchPar: Option[Z] = None()
    var dontSplitFunQuant: B = false
    var splitAll: B = false
    var splitContract: B = false
    var splitIf: B = false
    var splitMatch: B = false
    var rlimit: Z = 2000000
    var sequential: B = false
    var simplify: B = false
    var smt2SatConfigs: Option[String] = Some("cvc4; z3; cvc5,--finite-model-find")
    var smt2ValidConfigs: Option[String] = Some("cvc4,--full-saturate-quant; z3; cvc5,--full-saturate-quant")
    var timeout: Z = 2
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--all") {
           val o: Option[B] = { j = j - 1; Some(!all) }
           o match {
             case Some(v) => all = v
             case _ => return None()
           }
         } else if (arg == "--strict-aliasing") {
           val o: Option[B] = { j = j - 1; Some(!strictAliasing) }
           o match {
             case Some(v) => strictAliasing = v
             case _ => return None()
           }
         } else if (arg == "--verbose") {
           val o: Option[B] = { j = j - 1; Some(!verbose) }
           o match {
             case Some(v) => verbose = v
             case _ => return None()
           }
         } else if (arg == "--ignore-runtime") {
           val o: Option[B] = { j = j - 1; Some(!ignoreRuntime) }
           o match {
             case Some(v) => ignoreRuntime = v
             case _ => return None()
           }
         } else if (arg == "--json") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => json = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--out") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => outputDirName = v
             case _ => return None()
           }
         } else if (arg == "--project") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => project = v
             case _ => return None()
           }
         } else if (arg == "--slice") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => slice = v
             case _ => return None()
           }
         } else if (arg == "--symlink") {
           val o: Option[B] = { j = j - 1; Some(!symlink) }
           o match {
             case Some(v) => symlink = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--versions") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => versions = v
             case _ => return None()
           }
         } else if (arg == "-c" || arg == "--cache") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => cache = v
             case _ => return None()
           }
         } else if (arg == "--no-docs") {
           val o: Option[B] = { j = j - 1; Some(!docs) }
           o match {
             case Some(v) => docs = v
             case _ => return None()
           }
         } else if (arg == "--no-sources") {
           val o: Option[B] = { j = j - 1; Some(!sources) }
           o match {
             case Some(v) => sources = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--repositories") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => repositories = v
             case _ => return None()
           }
         } else if (arg == "--info-flow") {
           val o: Option[B] = { j = j - 1; Some(!infoFlow) }
           o match {
             case Some(v) => infoFlow = v
             case _ => return None()
           }
         } else if (arg == "--c-bitwidth") {
           val o: Option[Z] = parseNum(args, j + 1, None(), None())
           o match {
             case Some(v) => charBitWidth = v
             case _ => return None()
           }
         } else if (arg == "--fp-rounding") {
           val o: Option[SireumProyekLogikaFPRoundingMode.Type] = parseSireumProyekLogikaFPRoundingMode(args, j + 1)
           o match {
             case Some(v) => fpRounding = v
             case _ => return None()
           }
         } else if (arg == "--use-real") {
           val o: Option[B] = { j = j - 1; Some(!useReal) }
           o match {
             case Some(v) => useReal = v
             case _ => return None()
           }
         } else if (arg == "--z-bitwidth") {
           val o: Option[Z] = parseNum(args, j + 1, None(), None())
           o match {
             case Some(v) => intBitWidth = v
             case _ => return None()
           }
         } else if (arg == "--interprocedural") {
           val o: Option[B] = { j = j - 1; Some(!interprocedural) }
           o match {
             case Some(v) => interprocedural = v
             case _ => return None()
           }
         } else if (arg == "--line") {
           val o: Option[Z] = parseNum(args, j + 1, Some(0), None())
           o match {
             case Some(v) => line = v
             case _ => return None()
           }
         } else if (arg == "--sat") {
           val o: Option[B] = { j = j - 1; Some(!sat) }
           o match {
             case Some(v) => sat = v
             case _ => return None()
           }
         } else if (arg == "--skip-methods") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => skipMethods = v
             case _ => return None()
           }
         } else if (arg == "--skip-types") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => skipTypes = v
             case _ => return None()
           }
         } else if (arg == "--unroll") {
           val o: Option[B] = { j = j - 1; Some(!unroll) }
           o match {
             case Some(v) => unroll = v
             case _ => return None()
           }
         } else if (arg == "--log-pc") {
           val o: Option[B] = { j = j - 1; Some(!logPc) }
           o match {
             case Some(v) => logPc = v
             case _ => return None()
           }
         } else if (arg == "--log-pc-lines") {
           val o: Option[B] = { j = j - 1; Some(!logPcLines) }
           o match {
             case Some(v) => logPcLines = v
             case _ => return None()
           }
         } else if (arg == "--log-raw-pc") {
           val o: Option[B] = { j = j - 1; Some(!logRawPc) }
           o match {
             case Some(v) => logRawPc = v
             case _ => return None()
           }
         } else if (arg == "--log-vc") {
           val o: Option[B] = { j = j - 1; Some(!logVc) }
           o match {
             case Some(v) => logVc = v
             case _ => return None()
           }
         } else if (arg == "--log-vc-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => logVcDir = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--par") {
           val o: Option[Option[Z]] = parseNumFlag(args, j + 1, Some(1), Some(100)) match {
             case o@Some(None()) => j = j - 1; Some(Some(100))
             case o => o
           }
           o match {
             case Some(v) => par = v
             case _ => return None()
           }
         } else if (arg == "--par-branch-mode") {
           val o: Option[SireumProyekLogikaBranchPar.Type] = parseSireumProyekLogikaBranchPar(args, j + 1)
           o match {
             case Some(v) => branchParMode = v
             case _ => return None()
           }
         } else if (arg == "--par-branch") {
           val o: Option[Option[Z]] = parseNumFlag(args, j + 1, Some(1), Some(100)) match {
             case o@Some(None()) => j = j - 1; Some(Some(100))
             case o => o
           }
           o match {
             case Some(v) => branchPar = v
             case _ => return None()
           }
         } else if (arg == "--dont-split-pfq") {
           val o: Option[B] = { j = j - 1; Some(!dontSplitFunQuant) }
           o match {
             case Some(v) => dontSplitFunQuant = v
             case _ => return None()
           }
         } else if (arg == "--split-all") {
           val o: Option[B] = { j = j - 1; Some(!splitAll) }
           o match {
             case Some(v) => splitAll = v
             case _ => return None()
           }
         } else if (arg == "--split-contract") {
           val o: Option[B] = { j = j - 1; Some(!splitContract) }
           o match {
             case Some(v) => splitContract = v
             case _ => return None()
           }
         } else if (arg == "--split-if") {
           val o: Option[B] = { j = j - 1; Some(!splitIf) }
           o match {
             case Some(v) => splitIf = v
             case _ => return None()
           }
         } else if (arg == "--split-match") {
           val o: Option[B] = { j = j - 1; Some(!splitMatch) }
           o match {
             case Some(v) => splitMatch = v
             case _ => return None()
           }
         } else if (arg == "--rlimit") {
           val o: Option[Z] = parseNum(args, j + 1, Some(0), None())
           o match {
             case Some(v) => rlimit = v
             case _ => return None()
           }
         } else if (arg == "--smt2-seq") {
           val o: Option[B] = { j = j - 1; Some(!sequential) }
           o match {
             case Some(v) => sequential = v
             case _ => return None()
           }
         } else if (arg == "--simplify") {
           val o: Option[B] = { j = j - 1; Some(!simplify) }
           o match {
             case Some(v) => simplify = v
             case _ => return None()
           }
         } else if (arg == "--solver-sat") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => smt2SatConfigs = v
             case _ => return None()
           }
         } else if (arg == "--solver-valid") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => smt2ValidConfigs = v
             case _ => return None()
           }
         } else if (arg == "-t" || arg == "--timeout") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => timeout = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumProyekLogikaOption(help, parseArguments(args, j), all, strictAliasing, verbose, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, cache, docs, sources, repositories, infoFlow, charBitWidth, fpRounding, useReal, intBitWidth, interprocedural, line, sat, skipMethods, skipTypes, unroll, logPc, logPcLines, logRawPc, logVc, logVcDir, par, branchParMode, branchPar, dontSplitFunQuant, splitAll, splitContract, splitIf, splitMatch, rlimit, sequential, simplify, smt2SatConfigs, smt2ValidConfigs, timeout))
  }

  def parseSireumProyekPublishTargetH(arg: String): Option[SireumProyekPublishTarget.Type] = {
    arg.native match {
      case "all" => return Some(SireumProyekPublishTarget.All)
      case "jvm" => return Some(SireumProyekPublishTarget.Jvm)
      case "js" => return Some(SireumProyekPublishTarget.Js)
      case s =>
        eprintln(s"Expecting one of the following: { all, jvm, js }, but found '$s'.")
        return None()
    }
  }

  def parseSireumProyekPublishTarget(args: ISZ[String], i: Z): Option[SireumProyekPublishTarget.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { all, jvm, js }, but none found.")
      return None()
    }
    val r = parseSireumProyekPublishTargetH(args(i))
    return r
  }

  def parseSireumProyekPublishTargets(args: ISZ[String], i: Z): Option[ISZ[SireumProyekPublishTarget.Type]] = {
    val tokensOpt = tokenize(args, i, "SireumProyekPublishTarget", ',', T)
    if (tokensOpt.isEmpty) {
      return None()
    }
    var r = ISZ[SireumProyekPublishTarget.Type]()
    for (token <- tokensOpt.get) {
      val e = parseSireumProyekPublishTargetH(token)
      e match {
        case Some(v) => r = r :+ v
        case _ => return None()
      }
    }
    return Some(r)
  }

  def parseSireumProyekPublish(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Proyek Publisher
          |
          |Usage: <options>* <dir> <org.name>
          |
          |Available Options:
          |    --m2                 Local m2 repository (defaults to the user home's .m2
          |                           directory) (expects a path)
          |    --target             Publication target (expects one or more of { all, jvm,
          |                           js }; default: all)
          |    --version            Publication version (defaults to using git commit
          |                           date, time, and abbreviated hash) (expects a string)
          |-h, --help               Display this information
          |
          |Project Options:
          |    --ignore-runtime     Ignore runtime library dependency version when
          |                           detecting changes
          |    --json               The JSON file to load project definitions from
          |                           (mutually exclusive with the 'project' option)
          |                           (expects a path)
          |-n, --name               Project name (defaults to the directory name of <dir>)
          |                           (expects a string)
          |-o, --out                Output directory name under <dir> (expects a string;
          |                           default is "out")
          |    --project            The project.cmd file accepting the 'json' argument
          |                           (defaults to
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
          |                           mutually exclusive with the 'json' option) (expects
          |                           a path)
          |    --slice              Slice the project starting from the given module IDs
          |                           and their dependencies (expects a string separated
          |                           by ",")
          |    --symlink            Follow symbolic link when searching for files
          |-v, --versions           The properties file(s) containing version information
          |                           (defaults to <dir>${Os.fileSep}versions.properties)
          |                           (expects path strings)
          |
          |Compilation Options:
          |    --javac              Javac options (expects a string separated by ",";
          |                           default is "-source, 1.8, -target, 1.8, -encoding,
          |                           utf8, -XDignore.symbol.file, -Xlint:-options,
          |                           -Xlint:deprecation")
          |-f, --fresh              Fresh compilation from a clean slate
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --recompile          Module IDs to force recompilation on (expects a string
          |                           separated by ",")
          |    --scalac             Scalac options (expects a string separated by ",";
          |                           default is "-release, 8, -deprecation, -Yrangepos,
          |                           -Ydelambdafy:method, -feature, -unchecked,
          |                           -Xfatal-warnings, -language:postfixOps")
          |    --sha3               Use SHA3 instead of time stamp for detecting file
          |                           changes
          |    --skip-compile       Skip compilation
          |
          |Ivy Dependencies Options:
          |-c, --cache              Ivy cache directory (defaults to Coursier's default
          |                           cache directory) (expects a path)
          |    --no-docs            Disable retrieval of javadoc files from Ivy
          |                           dependencies
          |    --no-sources         Disable retrieval of source files from Ivy
          |                           dependencies
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")""".render

    var m2: Option[String] = None[String]()
    var target: ISZ[SireumProyekPublishTarget.Type] = ISZ(SireumProyekPublishTarget.All)
    var version: Option[String] = None[String]()
    var ignoreRuntime: B = false
    var json: Option[String] = None[String]()
    var name: Option[String] = None[String]()
    var outputDirName: Option[String] = Some("out")
    var project: Option[String] = None[String]()
    var slice: ISZ[String] = ISZ[String]()
    var symlink: B = false
    var versions: ISZ[String] = ISZ[String]()
    var javac: ISZ[String] = ISZ("-source", "1.8", "-target", "1.8", "-encoding", "utf8", "-XDignore.symbol.file", "-Xlint:-options", "-Xlint:deprecation")
    var fresh: B = false
    var par: Option[Z] = None()
    var recompile: ISZ[String] = ISZ[String]()
    var scalac: ISZ[String] = ISZ("-release", "8", "-deprecation", "-Yrangepos", "-Ydelambdafy:method", "-feature", "-unchecked", "-Xfatal-warnings", "-language:postfixOps")
    var sha3: B = false
    var skipCompile: B = false
    var cache: Option[String] = None[String]()
    var docs: B = true
    var sources: B = true
    var repositories: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--m2") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => m2 = v
             case _ => return None()
           }
         } else if (arg == "--target") {
           val o: Option[ISZ[SireumProyekPublishTarget.Type]] = parseSireumProyekPublishTargets(args, j + 1)
           o match {
             case Some(v) => target = v
             case _ => return None()
           }
         } else if (arg == "--version") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => version = v
             case _ => return None()
           }
         } else if (arg == "--ignore-runtime") {
           val o: Option[B] = { j = j - 1; Some(!ignoreRuntime) }
           o match {
             case Some(v) => ignoreRuntime = v
             case _ => return None()
           }
         } else if (arg == "--json") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => json = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--out") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => outputDirName = v
             case _ => return None()
           }
         } else if (arg == "--project") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => project = v
             case _ => return None()
           }
         } else if (arg == "--slice") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => slice = v
             case _ => return None()
           }
         } else if (arg == "--symlink") {
           val o: Option[B] = { j = j - 1; Some(!symlink) }
           o match {
             case Some(v) => symlink = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--versions") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => versions = v
             case _ => return None()
           }
         } else if (arg == "--javac") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => javac = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--fresh") {
           val o: Option[B] = { j = j - 1; Some(!fresh) }
           o match {
             case Some(v) => fresh = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--par") {
           val o: Option[Option[Z]] = parseNumFlag(args, j + 1, Some(1), Some(100)) match {
             case o@Some(None()) => j = j - 1; Some(Some(100))
             case o => o
           }
           o match {
             case Some(v) => par = v
             case _ => return None()
           }
         } else if (arg == "--recompile") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => recompile = v
             case _ => return None()
           }
         } else if (arg == "--scalac") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => scalac = v
             case _ => return None()
           }
         } else if (arg == "--sha3") {
           val o: Option[B] = { j = j - 1; Some(!sha3) }
           o match {
             case Some(v) => sha3 = v
             case _ => return None()
           }
         } else if (arg == "--skip-compile") {
           val o: Option[B] = { j = j - 1; Some(!skipCompile) }
           o match {
             case Some(v) => skipCompile = v
             case _ => return None()
           }
         } else if (arg == "-c" || arg == "--cache") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => cache = v
             case _ => return None()
           }
         } else if (arg == "--no-docs") {
           val o: Option[B] = { j = j - 1; Some(!docs) }
           o match {
             case Some(v) => docs = v
             case _ => return None()
           }
         } else if (arg == "--no-sources") {
           val o: Option[B] = { j = j - 1; Some(!sources) }
           o match {
             case Some(v) => sources = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--repositories") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => repositories = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumProyekPublishOption(help, parseArguments(args, j), m2, target, version, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, javac, fresh, par, recompile, scalac, sha3, skipCompile, cache, docs, sources, repositories))
  }

  def parseSireumProyekRun(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Proyek Program Runner
          |
          |Usage: <options>* <dir> <class-name> <arg>*
          |
          |Available Options:
          |-d, --dir                Working directory (defaults to current working
          |                           directory) (expects a path)
          |    --java               Java options (expects a string separated by ",")
          |-h, --help               Display this information
          |
          |Project Options:
          |    --ignore-runtime     Ignore runtime library dependency version when
          |                           detecting changes
          |    --json               The JSON file to load project definitions from
          |                           (mutually exclusive with the 'project' option)
          |                           (expects a path)
          |-n, --name               Project name (defaults to the directory name of <dir>)
          |                           (expects a string)
          |-o, --out                Output directory name under <dir> (expects a string;
          |                           default is "out")
          |    --project            The project.cmd file accepting the 'json' argument
          |                           (defaults to
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
          |                           mutually exclusive with the 'json' option) (expects
          |                           a path)
          |    --slice              Slice the project starting from the given module IDs
          |                           and their dependencies (expects a string separated
          |                           by ",")
          |    --symlink            Follow symbolic link when searching for files
          |-v, --versions           The properties file(s) containing version information
          |                           (defaults to <dir>${Os.fileSep}versions.properties)
          |                           (expects path strings)
          |
          |Compilation Options:
          |    --javac              Javac options (expects a string separated by ",";
          |                           default is "-source, 1.8, -target, 1.8, -encoding,
          |                           utf8, -XDignore.symbol.file, -Xlint:-options,
          |                           -Xlint:deprecation")
          |-f, --fresh              Fresh compilation from a clean slate
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --recompile          Module IDs to force recompilation on (expects a string
          |                           separated by ",")
          |    --scalac             Scalac options (expects a string separated by ",";
          |                           default is "-release, 8, -deprecation, -Yrangepos,
          |                           -Ydelambdafy:method, -feature, -unchecked,
          |                           -Xfatal-warnings, -language:postfixOps")
          |    --sha3               Use SHA3 instead of time stamp for detecting file
          |                           changes
          |    --skip-compile       Skip compilation
          |
          |Ivy Dependencies Options:
          |-c, --cache              Ivy cache directory (defaults to Coursier's default
          |                           cache directory) (expects a path)
          |    --no-docs            Disable retrieval of javadoc files from Ivy
          |                           dependencies
          |    --no-sources         Disable retrieval of source files from Ivy
          |                           dependencies
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")""".render

    var dir: Option[String] = None[String]()
    var java: ISZ[String] = ISZ[String]()
    var ignoreRuntime: B = false
    var json: Option[String] = None[String]()
    var name: Option[String] = None[String]()
    var outputDirName: Option[String] = Some("out")
    var project: Option[String] = None[String]()
    var slice: ISZ[String] = ISZ[String]()
    var symlink: B = false
    var versions: ISZ[String] = ISZ[String]()
    var javac: ISZ[String] = ISZ("-source", "1.8", "-target", "1.8", "-encoding", "utf8", "-XDignore.symbol.file", "-Xlint:-options", "-Xlint:deprecation")
    var fresh: B = false
    var par: Option[Z] = None()
    var recompile: ISZ[String] = ISZ[String]()
    var scalac: ISZ[String] = ISZ("-release", "8", "-deprecation", "-Yrangepos", "-Ydelambdafy:method", "-feature", "-unchecked", "-Xfatal-warnings", "-language:postfixOps")
    var sha3: B = false
    var skipCompile: B = false
    var cache: Option[String] = None[String]()
    var docs: B = true
    var sources: B = true
    var repositories: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-d" || arg == "--dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => dir = v
             case _ => return None()
           }
         } else if (arg == "--java") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => java = v
             case _ => return None()
           }
         } else if (arg == "--ignore-runtime") {
           val o: Option[B] = { j = j - 1; Some(!ignoreRuntime) }
           o match {
             case Some(v) => ignoreRuntime = v
             case _ => return None()
           }
         } else if (arg == "--json") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => json = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--out") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => outputDirName = v
             case _ => return None()
           }
         } else if (arg == "--project") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => project = v
             case _ => return None()
           }
         } else if (arg == "--slice") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => slice = v
             case _ => return None()
           }
         } else if (arg == "--symlink") {
           val o: Option[B] = { j = j - 1; Some(!symlink) }
           o match {
             case Some(v) => symlink = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--versions") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => versions = v
             case _ => return None()
           }
         } else if (arg == "--javac") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => javac = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--fresh") {
           val o: Option[B] = { j = j - 1; Some(!fresh) }
           o match {
             case Some(v) => fresh = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--par") {
           val o: Option[Option[Z]] = parseNumFlag(args, j + 1, Some(1), Some(100)) match {
             case o@Some(None()) => j = j - 1; Some(Some(100))
             case o => o
           }
           o match {
             case Some(v) => par = v
             case _ => return None()
           }
         } else if (arg == "--recompile") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => recompile = v
             case _ => return None()
           }
         } else if (arg == "--scalac") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => scalac = v
             case _ => return None()
           }
         } else if (arg == "--sha3") {
           val o: Option[B] = { j = j - 1; Some(!sha3) }
           o match {
             case Some(v) => sha3 = v
             case _ => return None()
           }
         } else if (arg == "--skip-compile") {
           val o: Option[B] = { j = j - 1; Some(!skipCompile) }
           o match {
             case Some(v) => skipCompile = v
             case _ => return None()
           }
         } else if (arg == "-c" || arg == "--cache") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => cache = v
             case _ => return None()
           }
         } else if (arg == "--no-docs") {
           val o: Option[B] = { j = j - 1; Some(!docs) }
           o match {
             case Some(v) => docs = v
             case _ => return None()
           }
         } else if (arg == "--no-sources") {
           val o: Option[B] = { j = j - 1; Some(!sources) }
           o match {
             case Some(v) => sources = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--repositories") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => repositories = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumProyekRunOption(help, parseArguments(args, j), dir, java, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, javac, fresh, par, recompile, scalac, sha3, skipCompile, cache, docs, sources, repositories))
  }

  def parseSireumProyekStats(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Proyek Statistics Reporter
          |
          |Usage: <options>* <dir> [ <file.csv> ]
          |
          |Available Options:
          |-h, --help               Display this information
          |
          |Project Options:
          |    --ignore-runtime     Ignore runtime library dependency version when
          |                           detecting changes
          |    --json               The JSON file to load project definitions from
          |                           (mutually exclusive with the 'project' option)
          |                           (expects a path)
          |-n, --name               Project name (defaults to the directory name of <dir>)
          |                           (expects a string)
          |-o, --out                Output directory name under <dir> (expects a string;
          |                           default is "out")
          |    --project            The project.cmd file accepting the 'json' argument
          |                           (defaults to
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
          |                           mutually exclusive with the 'json' option) (expects
          |                           a path)
          |    --slice              Slice the project starting from the given module IDs
          |                           and their dependencies (expects a string separated
          |                           by ",")
          |    --symlink            Follow symbolic link when searching for files
          |-v, --versions           The properties file(s) containing version information
          |                           (defaults to <dir>${Os.fileSep}versions.properties)
          |                           (expects path strings)
          |
          |Ivy Dependencies Options:
          |-c, --cache              Ivy cache directory (defaults to Coursier's default
          |                           cache directory) (expects a path)
          |    --no-docs            Disable retrieval of javadoc files from Ivy
          |                           dependencies
          |    --no-sources         Disable retrieval of source files from Ivy
          |                           dependencies
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")""".render

    var ignoreRuntime: B = false
    var json: Option[String] = None[String]()
    var name: Option[String] = None[String]()
    var outputDirName: Option[String] = Some("out")
    var project: Option[String] = None[String]()
    var slice: ISZ[String] = ISZ[String]()
    var symlink: B = false
    var versions: ISZ[String] = ISZ[String]()
    var cache: Option[String] = None[String]()
    var docs: B = true
    var sources: B = true
    var repositories: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--ignore-runtime") {
           val o: Option[B] = { j = j - 1; Some(!ignoreRuntime) }
           o match {
             case Some(v) => ignoreRuntime = v
             case _ => return None()
           }
         } else if (arg == "--json") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => json = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--out") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => outputDirName = v
             case _ => return None()
           }
         } else if (arg == "--project") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => project = v
             case _ => return None()
           }
         } else if (arg == "--slice") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => slice = v
             case _ => return None()
           }
         } else if (arg == "--symlink") {
           val o: Option[B] = { j = j - 1; Some(!symlink) }
           o match {
             case Some(v) => symlink = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--versions") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => versions = v
             case _ => return None()
           }
         } else if (arg == "-c" || arg == "--cache") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => cache = v
             case _ => return None()
           }
         } else if (arg == "--no-docs") {
           val o: Option[B] = { j = j - 1; Some(!docs) }
           o match {
             case Some(v) => docs = v
             case _ => return None()
           }
         } else if (arg == "--no-sources") {
           val o: Option[B] = { j = j - 1; Some(!sources) }
           o match {
             case Some(v) => sources = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--repositories") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => repositories = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumProyekStatsOption(help, parseArguments(args, j), ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, cache, docs, sources, repositories))
  }

  def parseSireumProyekTest(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Proyek Test Runner
          |
          |Usage: <options>* <dir> <root-package-name>*
          |
          |Available Options:
          |    --classes            Specific fully-qualified test class names to run
          |                           (expects a string separated by ",")
          |    --java               Java options (expects a string separated by ",")
          |    --packages           Specific fully-qualified test package names to run
          |                           (expects a string separated by ",")
          |    --suffixes           Specific test class name suffixes to run (expects a
          |                           string separated by ",")
          |-h, --help               Display this information
          |
          |Project Options:
          |    --ignore-runtime     Ignore runtime library dependency version when
          |                           detecting changes
          |    --json               The JSON file to load project definitions from
          |                           (mutually exclusive with the 'project' option)
          |                           (expects a path)
          |-n, --name               Project name (defaults to the directory name of <dir>)
          |                           (expects a string)
          |-o, --out                Output directory name under <dir> (expects a string;
          |                           default is "out")
          |    --project            The project.cmd file accepting the 'json' argument
          |                           (defaults to
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
          |                           mutually exclusive with the 'json' option) (expects
          |                           a path)
          |    --slice              Slice the project starting from the given module IDs
          |                           and their dependencies (expects a string separated
          |                           by ",")
          |    --symlink            Follow symbolic link when searching for files
          |-v, --versions           The properties file(s) containing version information
          |                           (defaults to <dir>${Os.fileSep}versions.properties)
          |                           (expects path strings)
          |
          |Compilation Options:
          |    --javac              Javac options (expects a string separated by ",";
          |                           default is "-source, 1.8, -target, 1.8, -encoding,
          |                           utf8, -XDignore.symbol.file, -Xlint:-options,
          |                           -Xlint:deprecation")
          |-f, --fresh              Fresh compilation from a clean slate
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --recompile          Module IDs to force recompilation on (expects a string
          |                           separated by ",")
          |    --scalac             Scalac options (expects a string separated by ",";
          |                           default is "-release, 8, -deprecation, -Yrangepos,
          |                           -Ydelambdafy:method, -feature, -unchecked,
          |                           -Xfatal-warnings, -language:postfixOps")
          |    --sha3               Use SHA3 instead of time stamp for detecting file
          |                           changes
          |    --skip-compile       Skip compilation
          |
          |Ivy Dependencies Options:
          |-c, --cache              Ivy cache directory (defaults to Coursier's default
          |                           cache directory) (expects a path)
          |    --no-docs            Disable retrieval of javadoc files from Ivy
          |                           dependencies
          |    --no-sources         Disable retrieval of source files from Ivy
          |                           dependencies
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")""".render

    var classes: ISZ[String] = ISZ[String]()
    var java: ISZ[String] = ISZ[String]()
    var packages: ISZ[String] = ISZ[String]()
    var suffixes: ISZ[String] = ISZ[String]()
    var ignoreRuntime: B = false
    var json: Option[String] = None[String]()
    var name: Option[String] = None[String]()
    var outputDirName: Option[String] = Some("out")
    var project: Option[String] = None[String]()
    var slice: ISZ[String] = ISZ[String]()
    var symlink: B = false
    var versions: ISZ[String] = ISZ[String]()
    var javac: ISZ[String] = ISZ("-source", "1.8", "-target", "1.8", "-encoding", "utf8", "-XDignore.symbol.file", "-Xlint:-options", "-Xlint:deprecation")
    var fresh: B = false
    var par: Option[Z] = None()
    var recompile: ISZ[String] = ISZ[String]()
    var scalac: ISZ[String] = ISZ("-release", "8", "-deprecation", "-Yrangepos", "-Ydelambdafy:method", "-feature", "-unchecked", "-Xfatal-warnings", "-language:postfixOps")
    var sha3: B = false
    var skipCompile: B = false
    var cache: Option[String] = None[String]()
    var docs: B = true
    var sources: B = true
    var repositories: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--classes") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => classes = v
             case _ => return None()
           }
         } else if (arg == "--java") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => java = v
             case _ => return None()
           }
         } else if (arg == "--packages") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => packages = v
             case _ => return None()
           }
         } else if (arg == "--suffixes") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => suffixes = v
             case _ => return None()
           }
         } else if (arg == "--ignore-runtime") {
           val o: Option[B] = { j = j - 1; Some(!ignoreRuntime) }
           o match {
             case Some(v) => ignoreRuntime = v
             case _ => return None()
           }
         } else if (arg == "--json") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => json = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--out") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => outputDirName = v
             case _ => return None()
           }
         } else if (arg == "--project") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => project = v
             case _ => return None()
           }
         } else if (arg == "--slice") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => slice = v
             case _ => return None()
           }
         } else if (arg == "--symlink") {
           val o: Option[B] = { j = j - 1; Some(!symlink) }
           o match {
             case Some(v) => symlink = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--versions") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => versions = v
             case _ => return None()
           }
         } else if (arg == "--javac") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => javac = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--fresh") {
           val o: Option[B] = { j = j - 1; Some(!fresh) }
           o match {
             case Some(v) => fresh = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--par") {
           val o: Option[Option[Z]] = parseNumFlag(args, j + 1, Some(1), Some(100)) match {
             case o@Some(None()) => j = j - 1; Some(Some(100))
             case o => o
           }
           o match {
             case Some(v) => par = v
             case _ => return None()
           }
         } else if (arg == "--recompile") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => recompile = v
             case _ => return None()
           }
         } else if (arg == "--scalac") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => scalac = v
             case _ => return None()
           }
         } else if (arg == "--sha3") {
           val o: Option[B] = { j = j - 1; Some(!sha3) }
           o match {
             case Some(v) => sha3 = v
             case _ => return None()
           }
         } else if (arg == "--skip-compile") {
           val o: Option[B] = { j = j - 1; Some(!skipCompile) }
           o match {
             case Some(v) => skipCompile = v
             case _ => return None()
           }
         } else if (arg == "-c" || arg == "--cache") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => cache = v
             case _ => return None()
           }
         } else if (arg == "--no-docs") {
           val o: Option[B] = { j = j - 1; Some(!docs) }
           o match {
             case Some(v) => docs = v
             case _ => return None()
           }
         } else if (arg == "--no-sources") {
           val o: Option[B] = { j = j - 1; Some(!sources) }
           o match {
             case Some(v) => sources = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--repositories") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => repositories = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumProyekTestOption(help, parseArguments(args, j), classes, java, packages, suffixes, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, javac, fresh, par, recompile, scalac, sha3, skipCompile, cache, docs, sources, repositories))
  }

  def parseSireumProyekTipe(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Proyek Type Checker
          |
          |Usage: <options>* <dir>
          |
          |Available Options:
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --strict-aliasing    Enable strict aliasing check
          |    --verbose            Enable verbose mode
          |-h, --help               Display this information
          |
          |Project Options:
          |    --ignore-runtime     Ignore runtime library dependency version when
          |                           detecting changes
          |    --json               The JSON file to load project definitions from
          |                           (mutually exclusive with the 'project' option)
          |                           (expects a path)
          |-n, --name               Project name (defaults to the directory name of <dir>)
          |                           (expects a string)
          |-o, --out                Output directory name under <dir> (expects a string;
          |                           default is "out")
          |    --project            The project.cmd file accepting the 'json' argument
          |                           (defaults to
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
          |                           mutually exclusive with the 'json' option) (expects
          |                           a path)
          |    --slice              Slice the project starting from the given module IDs
          |                           and their dependencies (expects a string separated
          |                           by ",")
          |    --symlink            Follow symbolic link when searching for files
          |-v, --versions           The properties file(s) containing version information
          |                           (defaults to <dir>${Os.fileSep}versions.properties)
          |                           (expects path strings)
          |
          |Ivy Dependencies Options:
          |-c, --cache              Ivy cache directory (defaults to Coursier's default
          |                           cache directory) (expects a path)
          |    --no-docs            Disable retrieval of javadoc files from Ivy
          |                           dependencies
          |    --no-sources         Disable retrieval of source files from Ivy
          |                           dependencies
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")""".render

    var par: Option[Z] = None()
    var strictAliasing: B = false
    var verbose: B = false
    var ignoreRuntime: B = false
    var json: Option[String] = None[String]()
    var name: Option[String] = None[String]()
    var outputDirName: Option[String] = Some("out")
    var project: Option[String] = None[String]()
    var slice: ISZ[String] = ISZ[String]()
    var symlink: B = false
    var versions: ISZ[String] = ISZ[String]()
    var cache: Option[String] = None[String]()
    var docs: B = true
    var sources: B = true
    var repositories: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-p" || arg == "--par") {
           val o: Option[Option[Z]] = parseNumFlag(args, j + 1, Some(1), Some(100)) match {
             case o@Some(None()) => j = j - 1; Some(Some(100))
             case o => o
           }
           o match {
             case Some(v) => par = v
             case _ => return None()
           }
         } else if (arg == "--strict-aliasing") {
           val o: Option[B] = { j = j - 1; Some(!strictAliasing) }
           o match {
             case Some(v) => strictAliasing = v
             case _ => return None()
           }
         } else if (arg == "--verbose") {
           val o: Option[B] = { j = j - 1; Some(!verbose) }
           o match {
             case Some(v) => verbose = v
             case _ => return None()
           }
         } else if (arg == "--ignore-runtime") {
           val o: Option[B] = { j = j - 1; Some(!ignoreRuntime) }
           o match {
             case Some(v) => ignoreRuntime = v
             case _ => return None()
           }
         } else if (arg == "--json") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => json = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--out") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => outputDirName = v
             case _ => return None()
           }
         } else if (arg == "--project") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => project = v
             case _ => return None()
           }
         } else if (arg == "--slice") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => slice = v
             case _ => return None()
           }
         } else if (arg == "--symlink") {
           val o: Option[B] = { j = j - 1; Some(!symlink) }
           o match {
             case Some(v) => symlink = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--versions") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => versions = v
             case _ => return None()
           }
         } else if (arg == "-c" || arg == "--cache") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => cache = v
             case _ => return None()
           }
         } else if (arg == "--no-docs") {
           val o: Option[B] = { j = j - 1; Some(!docs) }
           o match {
             case Some(v) => docs = v
             case _ => return None()
           }
         } else if (arg == "--no-sources") {
           val o: Option[B] = { j = j - 1; Some(!sources) }
           o match {
             case Some(v) => sources = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--repositories") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => repositories = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumProyekTipeOption(help, parseArguments(args, j), par, strictAliasing, verbose, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, cache, docs, sources, repositories))
  }

  def parseSireumSlang(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""The Sireum Language (Slang) Tools
            |
            |Available modes:
            |run                      Script runner
            |tipe                     Type checker
            |transpilers              Slang transpilers""".render
      )
      return Some(HelpOption())
    }
    val opt = select("slang", args, i, ISZ("run", "tipe", "transpilers"))
    opt match {
      case Some(string"run") => parseSireumSlangRun(args, i + 1)
      case Some(string"tipe") => parseSireumSlangTipe(args, i + 1)
      case Some(string"transpilers") => parseSireumSlangTranspilers(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumSlangRun(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Slang Script Runner
          |
          |Usage: <option>* <slang-file> <arg>*
          |
          |Available Options:
          |-i, --input              Input file for stdin (default: <slang-file>.txt, if
          |                           any) (expects a path)
          |-o, --output             Output file for stdin & stderr (expects a path)
          |-t, --transformed        Show Scala transformed tree
          |-n, --native             Generate native executable
          |-h, --help               Display this information""".render

    var input: Option[String] = None[String]()
    var output: Option[String] = None[String]()
    var transformed: B = false
    var nativ: B = false
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-i" || arg == "--input") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => input = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => output = v
             case _ => return None()
           }
         } else if (arg == "-t" || arg == "--transformed") {
           val o: Option[B] = { j = j - 1; Some(!transformed) }
           o match {
             case Some(v) => transformed = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--native") {
           val o: Option[B] = { j = j - 1; Some(!nativ) }
           o match {
             case Some(v) => nativ = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumSlangRunOption(help, parseArguments(args, j), input, output, transformed, nativ))
  }

  def parseSireumSlangTipe(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Slang Type Checker
          |
          |Usage: <option>* [<slang-file>]
          |
          |Available Options:
          |-x, --exclude            Sourcepath exclusion as URI segment (expects a string
          |                           separated by ",")
          |-f, --force              Fully qualified names of traits, classes, and objects
          |                           to force full type checking on when type outlining
          |                           is enabled (expects a string separated by ",")
          |-r, --no-runtime         Do not use built-in runtime (use runtime in
          |                           sourcepath)
          |-o, --outline            Perform type outlining only for files in the
          |                           sourcepath
          |-s, --sourcepath         Sourcepath of Slang .scala files (expects path
          |                           strings)
          |    --strict-aliasing    Enable strict aliasing check
          |    --verbose            Enable verbose mode
          |-h, --help               Display this information
          |
          |Persistence Options:
          |    --save               Path to save type information to (outline should not
          |                           be enabled) (expects a path)
          |    --load               Path to load type information from (expects a path)
          |-z, --no-gzip            Disable gzip compression when saving and/or loading""".render

    var exclude: ISZ[String] = ISZ[String]()
    var force: ISZ[String] = ISZ[String]()
    var noRuntime: B = false
    var outline: B = false
    var sourcepath: ISZ[String] = ISZ[String]()
    var strictAliasing: B = false
    var verbose: B = false
    var save: Option[String] = None[String]()
    var load: Option[String] = None[String]()
    var gzip: B = true
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-x" || arg == "--exclude") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => exclude = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--force") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => force = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--no-runtime") {
           val o: Option[B] = { j = j - 1; Some(!noRuntime) }
           o match {
             case Some(v) => noRuntime = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--outline") {
           val o: Option[B] = { j = j - 1; Some(!outline) }
           o match {
             case Some(v) => outline = v
             case _ => return None()
           }
         } else if (arg == "-s" || arg == "--sourcepath") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => sourcepath = v
             case _ => return None()
           }
         } else if (arg == "--strict-aliasing") {
           val o: Option[B] = { j = j - 1; Some(!strictAliasing) }
           o match {
             case Some(v) => strictAliasing = v
             case _ => return None()
           }
         } else if (arg == "--verbose") {
           val o: Option[B] = { j = j - 1; Some(!verbose) }
           o match {
             case Some(v) => verbose = v
             case _ => return None()
           }
         } else if (arg == "--save") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => save = v
             case _ => return None()
           }
         } else if (arg == "--load") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => load = v
             case _ => return None()
           }
         } else if (arg == "-z" || arg == "--no-gzip") {
           val o: Option[B] = { j = j - 1; Some(!gzip) }
           o match {
             case Some(v) => gzip = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumSlangTipeOption(help, parseArguments(args, j), exclude, force, noRuntime, outline, sourcepath, strictAliasing, verbose, save, load, gzip))
  }

  def parseSireumSlangTranspilers(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Slang Transpilers
            |
            |Available modes:
            |c                        Slang Embedded to C transpiler""".render
      )
      return Some(HelpOption())
    }
    val opt = select("transpilers", args, i, ISZ("c"))
    opt match {
      case Some(string"c") => parseSireumSlangTranspilersC(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumSlangTranspilersC(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Slang Embedded To C Transpiler
          |
          |Usage: <option>* ( <slang-file> )*
          |
          |Available Options:
          |-s, --sourcepath         Sourcepath of Slang .scala files (expects path
          |                           strings)
          |    --strict-aliasing    Enable strict aliasing check
          |-o, --output-dir         Output directory for transpiled files (expects a path;
          |                           default is "out")
          |    --verbose            Enable verbose mode
          |-h, --help               Display this information
          |
          |Configuration Options:
          |-a, --apps               @app fully qualified names (expects a string separated
          |                           by ",")
          |-b, --bits               Default bit-width for unbounded integer types (e.g.,
          |                           Z) (expects one of { 64, 32, 16, 8 })
          |-n, --name               Project name (expects a string; default is "main")
          |-z, --stack-size         Maximum stack size in bytes (expects a string; default
          |                           is "16 * 1024 * 1024")
          |
          |Array Bounds Options:
          |-q, --sequence           Custom maximum sequence sizes, each in the form of
          |                           <type>=<size>, where <type> is either IS[,], MS[,],
          |                           ISZ[], MSZ[], or ZS with fully qualified index and
          |                           element types where applicable (expects a string
          |                           separated by ";")
          |    --sequence-size      Default maximum sequence size (expects an integer;
          |                           default is 100)
          |    --string-size        Maximum string size (expects an integer; default is
          |                           100)
          |
          |CMake Options:
          |    --cmake-includes     Files to embed in generated CMakeLists.txt (can
          |                           optionally use a preceeding - or + to indicate
          |                           before/after library/app definitions; defaults to -)
          |                           (expects path strings)
          |-e, --exts               Extension directory or file paths (expects path
          |                           strings)
          |-l, --lib-only           Only generate library definition in CMake file
          |-x, --exclude-build      Type/method fully qualified names to exclude in the
          |                           generated CMake file (expects a string separated by
          |                           ",")
          |
          |Extensibility Options:
          |-p, --plugins            Plugin fully qualified names (expects a string
          |                           separated by ",")
          |
          |Name Mangling Options:
          |-f, --fingerprint        Generic entity fingerprinting size (expects an
          |                           integer; min is 1; max is 64; default is 3)
          |-i, --stable-type-id     Enable stable type id
          |
          |Optimizations Options:
          |-u, --unroll             Enable for-loop unrolling on constant bounds
          |
          |Persistence Options:
          |    --save               Path to save type information to (outline should not
          |                           be enabled) (expects a path)
          |    --load               Path to load type information from (expects a path)
          |
          |Substitutions Options:
          |-c, --constants          Custom constant for object variables, each in the form
          |                           of <name>=<lit>, where <name> is a qualified name of
          |                           an object var and <lit> is a Slang literal
          |                           expression (expects a string separated by ";")
          |-w, --forward            Object forwarding, each in form of <name>=<name>,
          |                           where <name> is a fully qualified name of an object
          |                           (expects a string separated by ",")""".render

    var sourcepath: ISZ[String] = ISZ[String]()
    var strictAliasing: B = false
    var output: Option[String] = Some("out")
    var verbose: B = false
    var apps: ISZ[String] = ISZ[String]()
    var bitWidth: Z = 64
    var projectName: Option[String] = Some("main")
    var stackSize: Option[String] = Some("16 * 1024 * 1024")
    var customArraySizes: ISZ[String] = ISZ[String]()
    var maxArraySize: Z = 100
    var maxStringSize: Z = 100
    var cmakeIncludes: ISZ[String] = ISZ[String]()
    var exts: ISZ[String] = ISZ[String]()
    var libOnly: B = false
    var excludeBuild: ISZ[String] = ISZ[String]()
    var plugins: ISZ[String] = ISZ[String]()
    var fingerprint: Z = 3
    var stableTypeId: B = false
    var unroll: B = false
    var save: Option[String] = None[String]()
    var load: Option[String] = None[String]()
    var customConstants: ISZ[String] = ISZ[String]()
    var forwarding: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-s" || arg == "--sourcepath") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => sourcepath = v
             case _ => return None()
           }
         } else if (arg == "--strict-aliasing") {
           val o: Option[B] = { j = j - 1; Some(!strictAliasing) }
           o match {
             case Some(v) => strictAliasing = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => output = v
             case _ => return None()
           }
         } else if (arg == "--verbose") {
           val o: Option[B] = { j = j - 1; Some(!verbose) }
           o match {
             case Some(v) => verbose = v
             case _ => return None()
           }
         } else if (arg == "-a" || arg == "--apps") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => apps = v
             case _ => return None()
           }
         } else if (arg == "-b" || arg == "--bits") {
           val o: Option[Z] = parseNumChoice(args, j + 1, ISZ(z"64", z"32", z"16", z"8"))
           o match {
             case Some(v) => bitWidth = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => projectName = v
             case _ => return None()
           }
         } else if (arg == "-z" || arg == "--stack-size") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => stackSize = v
             case _ => return None()
           }
         } else if (arg == "-q" || arg == "--sequence") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ';')
           o match {
             case Some(v) => customArraySizes = v
             case _ => return None()
           }
         } else if (arg == "--sequence-size") {
           val o: Option[Z] = parseNum(args, j + 1, None(), None())
           o match {
             case Some(v) => maxArraySize = v
             case _ => return None()
           }
         } else if (arg == "--string-size") {
           val o: Option[Z] = parseNum(args, j + 1, None(), None())
           o match {
             case Some(v) => maxStringSize = v
             case _ => return None()
           }
         } else if (arg == "--cmake-includes") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => cmakeIncludes = v
             case _ => return None()
           }
         } else if (arg == "-e" || arg == "--exts") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => exts = v
             case _ => return None()
           }
         } else if (arg == "-l" || arg == "--lib-only") {
           val o: Option[B] = { j = j - 1; Some(!libOnly) }
           o match {
             case Some(v) => libOnly = v
             case _ => return None()
           }
         } else if (arg == "-x" || arg == "--exclude-build") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => excludeBuild = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--plugins") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => plugins = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--fingerprint") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), Some(64))
           o match {
             case Some(v) => fingerprint = v
             case _ => return None()
           }
         } else if (arg == "-i" || arg == "--stable-type-id") {
           val o: Option[B] = { j = j - 1; Some(!stableTypeId) }
           o match {
             case Some(v) => stableTypeId = v
             case _ => return None()
           }
         } else if (arg == "-u" || arg == "--unroll") {
           val o: Option[B] = { j = j - 1; Some(!unroll) }
           o match {
             case Some(v) => unroll = v
             case _ => return None()
           }
         } else if (arg == "--save") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => save = v
             case _ => return None()
           }
         } else if (arg == "--load") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => load = v
             case _ => return None()
           }
         } else if (arg == "-c" || arg == "--constants") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ';')
           o match {
             case Some(v) => customConstants = v
             case _ => return None()
           }
         } else if (arg == "-w" || arg == "--forward") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => forwarding = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumSlangTranspilersCOption(help, parseArguments(args, j), sourcepath, strictAliasing, output, verbose, apps, bitWidth, projectName, stackSize, customArraySizes, maxArraySize, maxStringSize, cmakeIncludes, exts, libOnly, excludeBuild, plugins, fingerprint, stableTypeId, unroll, save, load, customConstants, forwarding))
  }

  def parseSireumPresentasi(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum Presentasi
            |
            |Available modes:
            |gen                      Presentation generator
            |text2speech              Text-to-speech tool""".render
      )
      return Some(HelpOption())
    }
    val opt = select("presentasi", args, i, ISZ("gen", "text2speech"))
    opt match {
      case Some(string"gen") => parseSireumPresentasiGen(args, i + 1)
      case Some(string"text2speech") => parseSireumPresentasiText2speech(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumPresentasiGenOutputFormatH(arg: String): Option[SireumPresentasiGenOutputFormat.Type] = {
    arg.native match {
      case "mp3" => return Some(SireumPresentasiGenOutputFormat.Mp3)
      case "wav" => return Some(SireumPresentasiGenOutputFormat.Wav)
      case s =>
        eprintln(s"Expecting one of the following: { mp3, wav }, but found '$s'.")
        return None()
    }
  }

  def parseSireumPresentasiGenOutputFormat(args: ISZ[String], i: Z): Option[SireumPresentasiGenOutputFormat.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { mp3, wav }, but none found.")
      return None()
    }
    val r = parseSireumPresentasiGenOutputFormatH(args(i))
    return r
  }

  def parseSireumPresentasiGenServiceH(arg: String): Option[SireumPresentasiGenService.Type] = {
    arg.native match {
      case "mary" => return Some(SireumPresentasiGenService.Mary)
      case "aws" => return Some(SireumPresentasiGenService.Aws)
      case "azure" => return Some(SireumPresentasiGenService.Azure)
      case s =>
        eprintln(s"Expecting one of the following: { mary, aws, azure }, but found '$s'.")
        return None()
    }
  }

  def parseSireumPresentasiGenService(args: ISZ[String], i: Z): Option[SireumPresentasiGenService.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { mary, aws, azure }, but none found.")
      return None()
    }
    val r = parseSireumPresentasiGenServiceH(args(i))
    return r
  }

  def parseSireumPresentasiGenEngineH(arg: String): Option[SireumPresentasiGenEngine.Type] = {
    arg.native match {
      case "neural" => return Some(SireumPresentasiGenEngine.Neural)
      case "standard" => return Some(SireumPresentasiGenEngine.Standard)
      case s =>
        eprintln(s"Expecting one of the following: { neural, standard }, but found '$s'.")
        return None()
    }
  }

  def parseSireumPresentasiGenEngine(args: ISZ[String], i: Z): Option[SireumPresentasiGenEngine.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { neural, standard }, but none found.")
      return None()
    }
    val r = parseSireumPresentasiGenEngineH(args(i))
    return r
  }

  def parseSireumPresentasiGen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Presentasi Generator
          |
          |Usage: <option>* <path> <arg>*
          |
          |Available Options:
          |    --force              Overwrite output file(s)
          |-l, --lang               Speech language (for AWS or Azure) (expects a string;
          |                           default is "en-US")
          |-f, --output-format      Audio output format (for AWS or Azure) (expects one of
          |                           { mp3, wav }; default: mp3)
          |-s, --service            Text-to-speech service (expects one of { mary, aws,
          |                           azure }; default: mary)
          |-v, --voice              Voice (defaults to "dfki-spike-hsmm" for MaryTTS,
          |                           "Amy" for AWS, "en-GB-RyanNeural" for Azure)
          |                           (expects a string)
          |-h, --help               Display this information
          |
          |AWS Options:
          |-a, --aws-path           Path to AWS command-line interface (CLI) (expects a
          |                           path; default is "aws")
          |-e, --engine             Voice engine (expects one of { neural, standard };
          |                           default: neural)
          |
          |Azure Options:
          |-g, --gender             Voice gender (expects a string; default is "Male")
          |-k, --key                Azure subscription key (expects a string)
          |-r, --region             Azure region (expects a string; default is
          |                           "centralus")
          |-d, --voice-lang         Voice language (expects a string; default is "en-GB")""".render

    var force: B = false
    var lang: Option[String] = Some("en-US")
    var outputFormat: SireumPresentasiGenOutputFormat.Type = SireumPresentasiGenOutputFormat.Mp3
    var service: SireumPresentasiGenService.Type = SireumPresentasiGenService.Mary
    var voice: Option[String] = None[String]()
    var awsPath: Option[String] = Some("aws")
    var engine: SireumPresentasiGenEngine.Type = SireumPresentasiGenEngine.Neural
    var gender: Option[String] = Some("Male")
    var key: Option[String] = None[String]()
    var region: Option[String] = Some("centralus")
    var voiceLang: Option[String] = Some("en-GB")
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--force") {
           val o: Option[B] = { j = j - 1; Some(!force) }
           o match {
             case Some(v) => force = v
             case _ => return None()
           }
         } else if (arg == "-l" || arg == "--lang") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => lang = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--output-format") {
           val o: Option[SireumPresentasiGenOutputFormat.Type] = parseSireumPresentasiGenOutputFormat(args, j + 1)
           o match {
             case Some(v) => outputFormat = v
             case _ => return None()
           }
         } else if (arg == "-s" || arg == "--service") {
           val o: Option[SireumPresentasiGenService.Type] = parseSireumPresentasiGenService(args, j + 1)
           o match {
             case Some(v) => service = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--voice") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => voice = v
             case _ => return None()
           }
         } else if (arg == "-a" || arg == "--aws-path") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => awsPath = v
             case _ => return None()
           }
         } else if (arg == "-e" || arg == "--engine") {
           val o: Option[SireumPresentasiGenEngine.Type] = parseSireumPresentasiGenEngine(args, j + 1)
           o match {
             case Some(v) => engine = v
             case _ => return None()
           }
         } else if (arg == "-g" || arg == "--gender") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => gender = v
             case _ => return None()
           }
         } else if (arg == "-k" || arg == "--key") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => key = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--region") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => region = v
             case _ => return None()
           }
         } else if (arg == "-d" || arg == "--voice-lang") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => voiceLang = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumPresentasiGenOption(help, parseArguments(args, j), force, lang, outputFormat, service, voice, awsPath, engine, gender, key, region, voiceLang))
  }

  def parseSireumPresentasiText2speechOutputFormatH(arg: String): Option[SireumPresentasiText2speechOutputFormat.Type] = {
    arg.native match {
      case "mp3" => return Some(SireumPresentasiText2speechOutputFormat.Mp3)
      case "webm" => return Some(SireumPresentasiText2speechOutputFormat.Webm)
      case "ogg" => return Some(SireumPresentasiText2speechOutputFormat.Ogg)
      case "wav" => return Some(SireumPresentasiText2speechOutputFormat.Wav)
      case s =>
        eprintln(s"Expecting one of the following: { mp3, webm, ogg, wav }, but found '$s'.")
        return None()
    }
  }

  def parseSireumPresentasiText2speechOutputFormat(args: ISZ[String], i: Z): Option[SireumPresentasiText2speechOutputFormat.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { mp3, webm, ogg, wav }, but none found.")
      return None()
    }
    val r = parseSireumPresentasiText2speechOutputFormatH(args(i))
    return r
  }

  def parseSireumPresentasiText2speechServiceH(arg: String): Option[SireumPresentasiText2speechService.Type] = {
    arg.native match {
      case "mary" => return Some(SireumPresentasiText2speechService.Mary)
      case "aws" => return Some(SireumPresentasiText2speechService.Aws)
      case "azure" => return Some(SireumPresentasiText2speechService.Azure)
      case s =>
        eprintln(s"Expecting one of the following: { mary, aws, azure }, but found '$s'.")
        return None()
    }
  }

  def parseSireumPresentasiText2speechService(args: ISZ[String], i: Z): Option[SireumPresentasiText2speechService.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { mary, aws, azure }, but none found.")
      return None()
    }
    val r = parseSireumPresentasiText2speechServiceH(args(i))
    return r
  }

  def parseSireumPresentasiText2speechEngineH(arg: String): Option[SireumPresentasiText2speechEngine.Type] = {
    arg.native match {
      case "neural" => return Some(SireumPresentasiText2speechEngine.Neural)
      case "standard" => return Some(SireumPresentasiText2speechEngine.Standard)
      case s =>
        eprintln(s"Expecting one of the following: { neural, standard }, but found '$s'.")
        return None()
    }
  }

  def parseSireumPresentasiText2speechEngine(args: ISZ[String], i: Z): Option[SireumPresentasiText2speechEngine.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { neural, standard }, but none found.")
      return None()
    }
    val r = parseSireumPresentasiText2speechEngineH(args(i))
    return r
  }

  def parseSireumPresentasiText2speech(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Presentasi Text-to-Speech Tool
          |
          |Usage: <option>* <file.txt>
          |
          |Available Options:
          |    --force              Overwrite output file(s)
          |-l, --lang               Speech language (for AWS or Azure) (expects a string;
          |                           default is "en-US")
          |-o, --output             Output filename (defaults to <line>.<ext>) (expects a
          |                           path)
          |-f, --output-format      Audio output format (for AWS or Azure) (expects one of
          |                           { mp3, webm, ogg, wav }; default: mp3)
          |-s, --service            Text-to-speech service (expects one of { mary, aws,
          |                           azure }; default: mary)
          |-v, --voice              Voice (defaults to "dfki-spike-hsmm" for MaryTTS,
          |                           "Amy" for AWS, "en-GB-RyanNeural" for Azure)
          |                           (expects a string)
          |-h, --help               Display this information
          |
          |AWS Options:
          |-a, --aws-path           Path to AWS command-line interface (CLI) (expects a
          |                           path; default is "aws")
          |-e, --engine             Voice engine (expects one of { neural, standard };
          |                           default: neural)
          |
          |Azure Options:
          |-g, --gender             Voice gender (expects a string; default is "Male")
          |-k, --key                Azure subscription key (expects a string)
          |-r, --region             Azure region (expects a string; default is
          |                           "centralus")
          |-d, --voice-lang         Voice language (expects a string; default is "en-GB")""".render

    var force: B = false
    var lang: Option[String] = Some("en-US")
    var output: Option[String] = None[String]()
    var outputFormat: SireumPresentasiText2speechOutputFormat.Type = SireumPresentasiText2speechOutputFormat.Mp3
    var service: SireumPresentasiText2speechService.Type = SireumPresentasiText2speechService.Mary
    var voice: Option[String] = None[String]()
    var awsPath: Option[String] = Some("aws")
    var engine: SireumPresentasiText2speechEngine.Type = SireumPresentasiText2speechEngine.Neural
    var gender: Option[String] = Some("Male")
    var key: Option[String] = None[String]()
    var region: Option[String] = Some("centralus")
    var voiceLang: Option[String] = Some("en-GB")
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--force") {
           val o: Option[B] = { j = j - 1; Some(!force) }
           o match {
             case Some(v) => force = v
             case _ => return None()
           }
         } else if (arg == "-l" || arg == "--lang") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => lang = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => output = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--output-format") {
           val o: Option[SireumPresentasiText2speechOutputFormat.Type] = parseSireumPresentasiText2speechOutputFormat(args, j + 1)
           o match {
             case Some(v) => outputFormat = v
             case _ => return None()
           }
         } else if (arg == "-s" || arg == "--service") {
           val o: Option[SireumPresentasiText2speechService.Type] = parseSireumPresentasiText2speechService(args, j + 1)
           o match {
             case Some(v) => service = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--voice") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => voice = v
             case _ => return None()
           }
         } else if (arg == "-a" || arg == "--aws-path") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => awsPath = v
             case _ => return None()
           }
         } else if (arg == "-e" || arg == "--engine") {
           val o: Option[SireumPresentasiText2speechEngine.Type] = parseSireumPresentasiText2speechEngine(args, j + 1)
           o match {
             case Some(v) => engine = v
             case _ => return None()
           }
         } else if (arg == "-g" || arg == "--gender") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => gender = v
             case _ => return None()
           }
         } else if (arg == "-k" || arg == "--key") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => key = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--region") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => region = v
             case _ => return None()
           }
         } else if (arg == "-d" || arg == "--voice-lang") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => voiceLang = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumPresentasiText2speechOption(help, parseArguments(args, j), force, lang, output, outputFormat, service, voice, awsPath, engine, gender, key, region, voiceLang))
  }

  def parseSireumServerServerMessageH(arg: String): Option[SireumServerServerMessage.Type] = {
    arg.native match {
      case "msgpack" => return Some(SireumServerServerMessage.Msgpack)
      case "json" => return Some(SireumServerServerMessage.Json)
      case s =>
        eprintln(s"Expecting one of the following: { msgpack, json }, but found '$s'.")
        return None()
    }
  }

  def parseSireumServerServerMessage(args: ISZ[String], i: Z): Option[SireumServerServerMessage.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { msgpack, json }, but none found.")
      return None()
    }
    val r = parseSireumServerServerMessageH(args(i))
    return r
  }

  def parseSireumServer(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Server
          |
          |Usage: <option>*
          |
          |Available Options:
          |-m, --message            Message format (expects one of { msgpack, json };
          |                           default: msgpack)
          |-l, --log                Enable logging
          |-i, --no-input-cache     Disable file input caching
          |-t, --no-type-cache      Disable type information caching
          |-v, --verbose            Enable verbose logging
          |-w, --workers            Number of analysis thread workers (expects an integer;
          |                           min is 1; default is 1)
          |-h, --help               Display this information""".render

    var message: SireumServerServerMessage.Type = SireumServerServerMessage.Msgpack
    var log: B = false
    var noInputCache: B = false
    var noTypeCache: B = false
    var verbose: B = false
    var workers: Z = 1
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-m" || arg == "--message") {
           val o: Option[SireumServerServerMessage.Type] = parseSireumServerServerMessage(args, j + 1)
           o match {
             case Some(v) => message = v
             case _ => return None()
           }
         } else if (arg == "-l" || arg == "--log") {
           val o: Option[B] = { j = j - 1; Some(!log) }
           o match {
             case Some(v) => log = v
             case _ => return None()
           }
         } else if (arg == "-i" || arg == "--no-input-cache") {
           val o: Option[B] = { j = j - 1; Some(!noInputCache) }
           o match {
             case Some(v) => noInputCache = v
             case _ => return None()
           }
         } else if (arg == "-t" || arg == "--no-type-cache") {
           val o: Option[B] = { j = j - 1; Some(!noTypeCache) }
           o match {
             case Some(v) => noTypeCache = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--verbose") {
           val o: Option[B] = { j = j - 1; Some(!verbose) }
           o match {
             case Some(v) => verbose = v
             case _ => return None()
           }
         } else if (arg == "-w" || arg == "--workers") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => workers = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumServerOption(help, parseArguments(args, j), message, log, noInputCache, noTypeCache, verbose, workers))
  }

  def parseSireumTools(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum Utility Tools
            |
            |Available modes:
            |bcgen                    Bit encoder/decoder generator
            |checkstack               Native function stack size check tool
            |cligen                   Command-line interface (CLI) generator
            |ivegen                   Sireum IVE project generator
            |opgen                    Object printer meta-generator
            |sergen                   De/Serializer generator
            |transgen                 Transformer (visitor/rewriter) generator""".render
      )
      return Some(HelpOption())
    }
    val opt = select("tools", args, i, ISZ("bcgen", "checkstack", "cligen", "ivegen", "opgen", "sergen", "transgen"))
    opt match {
      case Some(string"bcgen") => parseSireumToolsBcgen(args, i + 1)
      case Some(string"checkstack") => parseSireumToolsCheckstack(args, i + 1)
      case Some(string"cligen") => parseSireumToolsCligen(args, i + 1)
      case Some(string"ivegen") => parseSireumToolsIvegen(args, i + 1)
      case Some(string"opgen") => parseSireumToolsOpgen(args, i + 1)
      case Some(string"sergen") => parseSireumToolsSergen(args, i + 1)
      case Some(string"transgen") => parseSireumToolsTransgen(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumToolsBcgenBitCodecModeH(arg: String): Option[SireumToolsBcgenBitCodecMode.Type] = {
    arg.native match {
      case "program" => return Some(SireumToolsBcgenBitCodecMode.Program)
      case "script" => return Some(SireumToolsBcgenBitCodecMode.Script)
      case "json" => return Some(SireumToolsBcgenBitCodecMode.Json)
      case "dot" => return Some(SireumToolsBcgenBitCodecMode.Dot)
      case s =>
        eprintln(s"Expecting one of the following: { program, script, json, dot }, but found '$s'.")
        return None()
    }
  }

  def parseSireumToolsBcgenBitCodecMode(args: ISZ[String], i: Z): Option[SireumToolsBcgenBitCodecMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { program, script, json, dot }, but none found.")
      return None()
    }
    val r = parseSireumToolsBcgenBitCodecModeH(args(i))
    return r
  }

  def parseSireumToolsBcgenBitCodecModes(args: ISZ[String], i: Z): Option[ISZ[SireumToolsBcgenBitCodecMode.Type]] = {
    val tokensOpt = tokenize(args, i, "SireumToolsBcgenBitCodecMode", ',', T)
    if (tokensOpt.isEmpty) {
      return None()
    }
    var r = ISZ[SireumToolsBcgenBitCodecMode.Type]()
    for (token <- tokensOpt.get) {
      val e = parseSireumToolsBcgenBitCodecModeH(token)
      e match {
        case Some(v) => r = r :+ v
        case _ => return None()
      }
    }
    return Some(r)
  }

  def parseSireumToolsBcgen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum BitCodec Generator
          |
          |Usage: <option>* <spec-file>
          |
          |Available Options:
          |-m, --mode               Generated codec unit mode (expects one or more of {
          |                           program, script, json, dot }; default: program)
          |    --little             Generate little-endian bitcodec instead of big-endian
          |    --mutable            Use MS instead of IS on decode methods
          |-p, --package            Package name for the codec (expects a string separated
          |                           by ".")
          |-n, --name               Object and filename for the codec (script always uses
          |                           BitCodec as the object name) (expects a string;
          |                           default is "BitCodec")
          |-l, --license            License file to be inserted in the file header
          |                           (expects a path)
          |-o, --output-dir         Output directory for the generated codec files
          |                           (expects a path; default is ".")
          |-t, --traits             Fully-qualified name of @sig traits for all bitcodec
          |                           types to extend (expects a string separated by ";")
          |-h, --help               Display this information""".render

    var mode: ISZ[SireumToolsBcgenBitCodecMode.Type] = ISZ(SireumToolsBcgenBitCodecMode.Program)
    var isLittleEndian: B = false
    var isMutable: B = false
    var packageName: ISZ[String] = ISZ[String]()
    var name: Option[String] = Some("BitCodec")
    var license: Option[String] = None[String]()
    var outputDir: Option[String] = Some(".")
    var traits: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-m" || arg == "--mode") {
           val o: Option[ISZ[SireumToolsBcgenBitCodecMode.Type]] = parseSireumToolsBcgenBitCodecModes(args, j + 1)
           o match {
             case Some(v) => mode = v
             case _ => return None()
           }
         } else if (arg == "--little") {
           val o: Option[B] = { j = j - 1; Some(!isLittleEndian) }
           o match {
             case Some(v) => isLittleEndian = v
             case _ => return None()
           }
         } else if (arg == "--mutable") {
           val o: Option[B] = { j = j - 1; Some(!isMutable) }
           o match {
             case Some(v) => isMutable = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--package") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, '.')
           o match {
             case Some(v) => packageName = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "-l" || arg == "--license") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => license = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => outputDir = v
             case _ => return None()
           }
         } else if (arg == "-t" || arg == "--traits") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ';')
           o match {
             case Some(v) => traits = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumToolsBcgenOption(help, parseArguments(args, j), mode, isLittleEndian, isMutable, packageName, name, license, outputDir, traits))
  }

  def parseSireumToolsCheckstackCheckStackModeH(arg: String): Option[SireumToolsCheckstackCheckStackMode.Type] = {
    arg.native match {
      case "dotsu" => return Some(SireumToolsCheckstackCheckStackMode.Dotsu)
      case "bin" => return Some(SireumToolsCheckstackCheckStackMode.Bin)
      case s =>
        eprintln(s"Expecting one of the following: { dotsu, bin }, but found '$s'.")
        return None()
    }
  }

  def parseSireumToolsCheckstackCheckStackMode(args: ISZ[String], i: Z): Option[SireumToolsCheckstackCheckStackMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { dotsu, bin }, but none found.")
      return None()
    }
    val r = parseSireumToolsCheckstackCheckStackModeH(args(i))
    return r
  }

  def parseSireumToolsCheckstackCheckStackArchH(arg: String): Option[SireumToolsCheckstackCheckStackArch.Type] = {
    arg.native match {
      case "amd64" => return Some(SireumToolsCheckstackCheckStackArch.Amd64)
      case "x86" => return Some(SireumToolsCheckstackCheckStackArch.X86)
      case "aarch64" => return Some(SireumToolsCheckstackCheckStackArch.Aarch64)
      case "arm" => return Some(SireumToolsCheckstackCheckStackArch.Arm)
      case "powerpc" => return Some(SireumToolsCheckstackCheckStackArch.Powerpc)
      case "openrisc" => return Some(SireumToolsCheckstackCheckStackArch.Openrisc)
      case "mips" => return Some(SireumToolsCheckstackCheckStackArch.Mips)
      case "mips64" => return Some(SireumToolsCheckstackCheckStackArch.Mips64)
      case "m68k" => return Some(SireumToolsCheckstackCheckStackArch.M68k)
      case "ia64" => return Some(SireumToolsCheckstackCheckStackArch.Ia64)
      case "nios2" => return Some(SireumToolsCheckstackCheckStackArch.Nios2)
      case "parisc" => return Some(SireumToolsCheckstackCheckStackArch.Parisc)
      case "s390x" => return Some(SireumToolsCheckstackCheckStackArch.S390x)
      case "sh64" => return Some(SireumToolsCheckstackCheckStackArch.Sh64)
      case "sparc" => return Some(SireumToolsCheckstackCheckStackArch.Sparc)
      case s =>
        eprintln(s"Expecting one of the following: { amd64, x86, aarch64, arm, powerpc, openrisc, mips, mips64, m68k, ia64, nios2, parisc, s390x, sh64, sparc }, but found '$s'.")
        return None()
    }
  }

  def parseSireumToolsCheckstackCheckStackArch(args: ISZ[String], i: Z): Option[SireumToolsCheckstackCheckStackArch.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { amd64, x86, aarch64, arm, powerpc, openrisc, mips, mips64, m68k, ia64, nios2, parisc, s390x, sh64, sparc }, but none found.")
      return None()
    }
    val r = parseSireumToolsCheckstackCheckStackArchH(args(i))
    return r
  }

  def parseSireumToolsCheckstackCheckStackFormatH(arg: String): Option[SireumToolsCheckstackCheckStackFormat.Type] = {
    arg.native match {
      case "plain" => return Some(SireumToolsCheckstackCheckStackFormat.Plain)
      case "csv" => return Some(SireumToolsCheckstackCheckStackFormat.Csv)
      case "html" => return Some(SireumToolsCheckstackCheckStackFormat.Html)
      case "md" => return Some(SireumToolsCheckstackCheckStackFormat.Md)
      case "rst" => return Some(SireumToolsCheckstackCheckStackFormat.Rst)
      case s =>
        eprintln(s"Expecting one of the following: { plain, csv, html, md, rst }, but found '$s'.")
        return None()
    }
  }

  def parseSireumToolsCheckstackCheckStackFormat(args: ISZ[String], i: Z): Option[SireumToolsCheckstackCheckStackFormat.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { plain, csv, html, md, rst }, but none found.")
      return None()
    }
    val r = parseSireumToolsCheckstackCheckStackFormatH(args(i))
    return r
  }

  def parseSireumToolsCheckstack(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum CheckStack
          |
          |Usage: <option>* ( <file> | <dir> )*
          |
          |Available Options:
          |-m, --mode               Analysis mode (expects one of { dotsu, bin }; default:
          |                           dotsu)
          |-h, --help               Display this information
          |
          |Binary Mode Options:
          |-o, --objdump            Name of object file dumper (expects a string; default
          |                           is "objdump")
          |-a, --arch               Target architecture (expects one of { amd64, x86,
          |                           aarch64, arm, powerpc, openrisc, mips, mips64, m68k,
          |                           ia64, nios2, parisc, s390x, sh64, sparc }; default:
          |                           amd64)
          |
          |Output Mode Options:
          |-f, --format             Output format (expects one of { plain, csv, html, md,
          |                           rst }; default: plain)""".render

    var mode: SireumToolsCheckstackCheckStackMode.Type = SireumToolsCheckstackCheckStackMode.Dotsu
    var objdump: Option[String] = Some("objdump")
    var arch: SireumToolsCheckstackCheckStackArch.Type = SireumToolsCheckstackCheckStackArch.Amd64
    var format: SireumToolsCheckstackCheckStackFormat.Type = SireumToolsCheckstackCheckStackFormat.Plain
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-m" || arg == "--mode") {
           val o: Option[SireumToolsCheckstackCheckStackMode.Type] = parseSireumToolsCheckstackCheckStackMode(args, j + 1)
           o match {
             case Some(v) => mode = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--objdump") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => objdump = v
             case _ => return None()
           }
         } else if (arg == "-a" || arg == "--arch") {
           val o: Option[SireumToolsCheckstackCheckStackArch.Type] = parseSireumToolsCheckstackCheckStackArch(args, j + 1)
           o match {
             case Some(v) => arch = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--format") {
           val o: Option[SireumToolsCheckstackCheckStackFormat.Type] = parseSireumToolsCheckstackCheckStackFormat(args, j + 1)
           o match {
             case Some(v) => format = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumToolsCheckstackOption(help, parseArguments(args, j), mode, objdump, arch, format))
  }

  def parseSireumToolsCligen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum CLI Generator
          |
          |Usage: <option>* <config-file>
          |
          |Available Options:
          |-l, --license            License file to be inserted in the file header
          |                           (expects a path)
          |-n, --name               Type simple name for the CLI @record class processor
          |                           (expects a string; default is "Cli")
          |-o, --output-dir         Output directory for the generated CLI file (expects a
          |                           path; default is ".")
          |-p, --package            Package name for the CLI processor (expects a string
          |                           separated by ".")
          |-s, --script             Generate a script file with the provided name instead
          |                           of a Slang program (expects a string)
          |-w, --width              First (key) column (default: 25) and second column
          |                           (default: 55) max width (expects an int-list
          |                           separated by ','; min is 0)
          |-h, --help               Display this information""".render

    var license: Option[String] = None[String]()
    var name: Option[String] = Some("Cli")
    var outputDir: Option[String] = Some(".")
    var packageName: ISZ[String] = ISZ[String]()
    var script: Option[String] = None[String]()
    var width: ISZ[Z] = ISZ()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-l" || arg == "--license") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => license = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => outputDir = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--package") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, '.')
           o match {
             case Some(v) => packageName = v
             case _ => return None()
           }
         } else if (arg == "-s" || arg == "--script") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => script = v
             case _ => return None()
           }
         } else if (arg == "-w" || arg == "--width") {
           val o: Option[ISZ[Z]] = parseNums(args, j + 1, ',', Some(0), None())
           o match {
             case Some(v) => width = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumToolsCligenOption(help, parseArguments(args, j), license, name, outputDir, packageName, script, width))
  }

  def parseSireumToolsIvegenIveModeH(arg: String): Option[SireumToolsIvegenIveMode.Type] = {
    arg.native match {
      case "idea" => return Some(SireumToolsIvegenIveMode.Idea)
      case "mill" => return Some(SireumToolsIvegenIveMode.Mill)
      case s =>
        eprintln(s"Expecting one of the following: { idea, mill }, but found '$s'.")
        return None()
    }
  }

  def parseSireumToolsIvegenIveMode(args: ISZ[String], i: Z): Option[SireumToolsIvegenIveMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { idea, mill }, but none found.")
      return None()
    }
    val r = parseSireumToolsIvegenIveModeH(args(i))
    return r
  }

  def parseSireumToolsIvegen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum IVE Project Generator
          |
          |Usage: <option>* <project-parent-directory>
          |
          |Available Options:
          |-j, --jdk                JDK name (expects a string; default is "Java")
          |-m, --mode               Project format (use idea for Slang script project and
          |                           mill for full Slang development) (expects one of {
          |                           idea, mill }; default: idea)
          |-n, --name               Project name (expects a string; default is "hello")
          |    --module             Module name (default: project name) (expects a string)
          |-p, --package            Fully qualified app package name (expects a string
          |                           separated by ".")
          |    --app                App/script name (default: "app" in mill mode;
          |                           otherwise, "script") (expects a string)
          |    --mill-path          Use mill available in the PATH environment variable
          |                           (only in mill mode)
          |-f, --force              Force regeneration of JDK and library tables
          |-c, --no-compile         Only generate mill project without code compilation
          |-h, --help               Display this information""".render

    var jdk: Option[String] = Some("Java")
    var mode: SireumToolsIvegenIveMode.Type = SireumToolsIvegenIveMode.Idea
    var projectName: Option[String] = Some("hello")
    var moduleName: Option[String] = None[String]()
    var packageName: ISZ[String] = ISZ[String]()
    var appName: Option[String] = None[String]()
    var millPath: B = false
    var force: B = false
    var compile: B = true
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-j" || arg == "--jdk") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => jdk = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--mode") {
           val o: Option[SireumToolsIvegenIveMode.Type] = parseSireumToolsIvegenIveMode(args, j + 1)
           o match {
             case Some(v) => mode = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => projectName = v
             case _ => return None()
           }
         } else if (arg == "--module") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => moduleName = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--package") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, '.')
           o match {
             case Some(v) => packageName = v
             case _ => return None()
           }
         } else if (arg == "--app") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => appName = v
             case _ => return None()
           }
         } else if (arg == "--mill-path") {
           val o: Option[B] = { j = j - 1; Some(!millPath) }
           o match {
             case Some(v) => millPath = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--force") {
           val o: Option[B] = { j = j - 1; Some(!force) }
           o match {
             case Some(v) => force = v
             case _ => return None()
           }
         } else if (arg == "-c" || arg == "--no-compile") {
           val o: Option[B] = { j = j - 1; Some(!compile) }
           o match {
             case Some(v) => compile = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumToolsIvegenOption(help, parseArguments(args, j), jdk, mode, projectName, moduleName, packageName, appName, millPath, force, compile))
  }

  def parseSireumToolsOpgen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Object Printer Meta-generator
          |
          |Usage: <option>* <fully-qualified-name>
          |
          |Available Options:
          |-l, --license             (expects a path)
          |-n, --name               Name of the generated object printer generator
          |                           (expects a string; default is "ObjectPrinter")
          |-o, --output-dir          (expects a path)
          |-p, --package            Package name of the generated object printer generator
          |                           (expects a string separated by ".")
          |-x, --exclude            Sourcepath exclusion as URI segment (expects a string
          |                           separated by ",")
          |-f, --force              Fully qualified names of traits, classes, and objects
          |                           to force full type checking on when type outlining
          |                           is enabled (expects a string separated by ",")
          |-r, --no-runtime         Do not use built-in runtime (use runtime in
          |                           sourcepath)
          |-s, --sourcepath         Sourcepath of Slang .scala files (expects path
          |                           strings)
          |    --strict-aliasing    Enable strict aliasing check
          |    --verbose            Enable verbose mode
          |-h, --help               Display this information
          |
          |Persistence Options:
          |    --save               Path to save type information to (outline should not
          |                           be enabled) (expects a path)
          |    --load               Path to load type information from (expects a path)
          |-z, --no-gzip            Disable gzip compression when saving and/or loading""".render

    var license: Option[String] = None[String]()
    var name: Option[String] = Some("ObjectPrinter")
    var output: Option[String] = None[String]()
    var packageName: ISZ[String] = ISZ[String]()
    var exclude: ISZ[String] = ISZ[String]()
    var force: ISZ[String] = ISZ[String]()
    var noRuntime: B = false
    var sourcepath: ISZ[String] = ISZ[String]()
    var strictAliasing: B = false
    var verbose: B = false
    var save: Option[String] = None[String]()
    var load: Option[String] = None[String]()
    var gzip: B = true
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-l" || arg == "--license") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => license = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => output = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--package") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, '.')
           o match {
             case Some(v) => packageName = v
             case _ => return None()
           }
         } else if (arg == "-x" || arg == "--exclude") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => exclude = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--force") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => force = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--no-runtime") {
           val o: Option[B] = { j = j - 1; Some(!noRuntime) }
           o match {
             case Some(v) => noRuntime = v
             case _ => return None()
           }
         } else if (arg == "-s" || arg == "--sourcepath") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => sourcepath = v
             case _ => return None()
           }
         } else if (arg == "--strict-aliasing") {
           val o: Option[B] = { j = j - 1; Some(!strictAliasing) }
           o match {
             case Some(v) => strictAliasing = v
             case _ => return None()
           }
         } else if (arg == "--verbose") {
           val o: Option[B] = { j = j - 1; Some(!verbose) }
           o match {
             case Some(v) => verbose = v
             case _ => return None()
           }
         } else if (arg == "--save") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => save = v
             case _ => return None()
           }
         } else if (arg == "--load") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => load = v
             case _ => return None()
           }
         } else if (arg == "-z" || arg == "--no-gzip") {
           val o: Option[B] = { j = j - 1; Some(!gzip) }
           o match {
             case Some(v) => gzip = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumToolsOpgenOption(help, parseArguments(args, j), license, name, output, packageName, exclude, force, noRuntime, sourcepath, strictAliasing, verbose, save, load, gzip))
  }

  def parseSireumToolsSergenSerializerModeH(arg: String): Option[SireumToolsSergenSerializerMode.Type] = {
    arg.native match {
      case "json" => return Some(SireumToolsSergenSerializerMode.Json)
      case "msgpack" => return Some(SireumToolsSergenSerializerMode.Msgpack)
      case s =>
        eprintln(s"Expecting one of the following: { json, msgpack }, but found '$s'.")
        return None()
    }
  }

  def parseSireumToolsSergenSerializerMode(args: ISZ[String], i: Z): Option[SireumToolsSergenSerializerMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { json, msgpack }, but none found.")
      return None()
    }
    val r = parseSireumToolsSergenSerializerModeH(args(i))
    return r
  }

  def parseSireumToolsSergenSerializerModes(args: ISZ[String], i: Z): Option[ISZ[SireumToolsSergenSerializerMode.Type]] = {
    val tokensOpt = tokenize(args, i, "SireumToolsSergenSerializerMode", ',', T)
    if (tokensOpt.isEmpty) {
      return None()
    }
    var r = ISZ[SireumToolsSergenSerializerMode.Type]()
    for (token <- tokensOpt.get) {
      val e = parseSireumToolsSergenSerializerModeH(token)
      e match {
        case Some(v) => r = r :+ v
        case _ => return None()
      }
    }
    return Some(r)
  }

  def parseSireumToolsSergen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum De/Serializer Generator
          |
          |Usage: <option>* <slang-file>
          |
          |Available Options:
          |-m, --modes              De/serializer mode (expects one or more of { json,
          |                           msgpack }; default: json)
          |-p, --package            Package name for the de/serializers (expects a string
          |                           separated by ".")
          |-n, --name               Type simple name for the de/serializers (default:
          |                           "Json" or "MsgPack") (expects a string)
          |-l, --license            License file to be inserted in the file header
          |                           (expects a path)
          |-o, --output-dir         Output directory for the generated de/serializer Slang
          |                           files (expects a path; default is ".")
          |-h, --help               Display this information""".render

    var modes: ISZ[SireumToolsSergenSerializerMode.Type] = ISZ(SireumToolsSergenSerializerMode.Json)
    var packageName: ISZ[String] = ISZ[String]()
    var name: Option[String] = None[String]()
    var license: Option[String] = None[String]()
    var outputDir: Option[String] = Some(".")
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-m" || arg == "--modes") {
           val o: Option[ISZ[SireumToolsSergenSerializerMode.Type]] = parseSireumToolsSergenSerializerModes(args, j + 1)
           o match {
             case Some(v) => modes = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--package") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, '.')
           o match {
             case Some(v) => packageName = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "-l" || arg == "--license") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => license = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => outputDir = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumToolsSergenOption(help, parseArguments(args, j), modes, packageName, name, license, outputDir))
  }

  def parseSireumToolsTransgenTransformerModeH(arg: String): Option[SireumToolsTransgenTransformerMode.Type] = {
    arg.native match {
      case "immutable" => return Some(SireumToolsTransgenTransformerMode.Immutable)
      case "mutable" => return Some(SireumToolsTransgenTransformerMode.Mutable)
      case s =>
        eprintln(s"Expecting one of the following: { immutable, mutable }, but found '$s'.")
        return None()
    }
  }

  def parseSireumToolsTransgenTransformerMode(args: ISZ[String], i: Z): Option[SireumToolsTransgenTransformerMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { immutable, mutable }, but none found.")
      return None()
    }
    val r = parseSireumToolsTransgenTransformerModeH(args(i))
    return r
  }

  def parseSireumToolsTransgenTransformerModes(args: ISZ[String], i: Z): Option[ISZ[SireumToolsTransgenTransformerMode.Type]] = {
    val tokensOpt = tokenize(args, i, "SireumToolsTransgenTransformerMode", ',', T)
    if (tokensOpt.isEmpty) {
      return None()
    }
    var r = ISZ[SireumToolsTransgenTransformerMode.Type]()
    for (token <- tokensOpt.get) {
      val e = parseSireumToolsTransgenTransformerModeH(token)
      e match {
        case Some(v) => r = r :+ v
        case _ => return None()
      }
    }
    return Some(r)
  }

  def parseSireumToolsTransgen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Transformer Generator
          |
          |Usage: <option>* <slang-file>+
          |
          |Available Options:
          |-e, --exclude            Exclude generating top-level transform for the
          |                           specified type identifiers (expects a string
          |                           separated by ",")
          |-m, --modes              Transformer mode (expects one or more of { immutable,
          |                           mutable }; default: immutable)
          |-n, --name               Type simple name for the transformers (default:
          |                           "Transformer" or "MTransformer") (expects a string)
          |-l, --license            License file to be inserted in the file header
          |                           (expects a path)
          |-o, --output-dir         Output directory for the generated transformer Slang
          |                           files (expects a path; default is ".")
          |-h, --help               Display this information""".render

    var exclude: ISZ[String] = ISZ[String]()
    var modes: ISZ[SireumToolsTransgenTransformerMode.Type] = ISZ(SireumToolsTransgenTransformerMode.Immutable)
    var name: Option[String] = None[String]()
    var license: Option[String] = None[String]()
    var outputDir: Option[String] = Some(".")
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-e" || arg == "--exclude") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => exclude = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--modes") {
           val o: Option[ISZ[SireumToolsTransgenTransformerMode.Type]] = parseSireumToolsTransgenTransformerModes(args, j + 1)
           o match {
             case Some(v) => modes = v
             case _ => return None()
           }
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => name = v
             case _ => return None()
           }
         } else if (arg == "-l" || arg == "--license") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => license = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => outputDir = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumToolsTransgenOption(help, parseArguments(args, j), exclude, modes, name, license, outputDir))
  }

  def parseSireumX(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum eXperimental
            |
            |Available modes:
            """.render
      )
      return Some(HelpOption())
    }
    val opt = select("x", args, i, ISZ(""))
    opt match {
      case _ => return None()
    }
  }

  def parseArguments(args: ISZ[String], i: Z): ISZ[String] = {
    var r = ISZ[String]()
    var j = i
    while (j < args.size) {
      r = r :+ args(j)
      j = j + 1
    }
    return r
  }

  def parsePaths(args: ISZ[String], i: Z): Option[ISZ[String]] = {
    return tokenize(args, i, "path", pathSep, F)
  }

  def parsePath(args: ISZ[String], i: Z): Option[Option[String]] = {
    if (i >= args.size) {
      eprintln("Expecting a path, but none found.")
    }
    return Some(Some(args(i)))
  }

  def parseStrings(args: ISZ[String], i: Z, sep: C): Option[ISZ[String]] = {
    tokenize(args, i, "string", sep, F) match {
      case r@Some(_) => return r
      case _ => return None()
    }
  }

  def parseString(args: ISZ[String], i: Z): Option[Option[String]] = {
    if (i >= args.size) {
      eprintln("Expecting a string, but none found.")
      return None()
    }
    return Some(Some(args(i)))
  }

  def parseNums(args: ISZ[String], i: Z, sep: C, minOpt: Option[Z], maxOpt: Option[Z]): Option[ISZ[Z]] = {
    tokenize(args, i, "integer", sep, T) match {
      case Some(sargs) =>
        var r = ISZ[Z]()
        for (arg <- sargs) {
          parseNumH(F, arg, minOpt, maxOpt)._2 match {
            case Some(n) => r = r :+ n
            case _ => return None()
          }
        }
        return Some(r)
      case _ => return None()
    }
  }

  def tokenize(args: ISZ[String], i: Z, tpe: String, sep: C, removeWhitespace: B): Option[ISZ[String]] = {
    if (i >= args.size) {
      eprintln(s"Expecting a sequence of $tpe separated by '$sep', but none found.")
      return None()
    }
    val arg = args(i)
    return Some(tokenizeH(arg, sep, removeWhitespace))
  }

  def tokenizeH(arg: String, sep: C, removeWhitespace: B): ISZ[String] = {
    val argCis = conversions.String.toCis(arg)
    var r = ISZ[String]()
    var cis = ISZ[C]()
    var j = 0
    while (j < argCis.size) {
      val c = argCis(j)
      if (c == sep) {
        r = r :+ conversions.String.fromCis(cis)
        cis = ISZ[C]()
      } else {
        val allowed: B = c match {
          case c"\n" => !removeWhitespace
          case c" " => !removeWhitespace
          case c"\r" => !removeWhitespace
          case c"\t" => !removeWhitespace
          case _ => T
        }
        if (allowed) {
          cis = cis :+ c
        }
      }
      j = j + 1
    }
    if (cis.size > 0) {
      r = r :+ conversions.String.fromCis(cis)
    }
    return r
  }

  def parseNumChoice(args: ISZ[String], i: Z, choices: ISZ[Z]): Option[Z] = {
    val set = HashSet.empty[Z] ++ choices
    parseNum(args, i, None(), None()) match {
      case r@Some(n) =>
        if (set.contains(n)) {
          return r
        } else {
          eprintln(s"Expecting one of the following: $set, but found $n.")
          return None()
        }
      case r => return r
    }
  }

  def parseNum(args: ISZ[String], i: Z, minOpt: Option[Z], maxOpt: Option[Z]): Option[Z] = {
    if (i >= args.size) {
      eprintln(s"Expecting an integer, but none found.")
      return None()
    }
    return parseNumH(F, args(i), minOpt, maxOpt)._2
  }

  def parseNumFlag(args: ISZ[String], i: Z, minOpt: Option[Z], maxOpt: Option[Z]): Option[Option[Z]] = {
    if (i >= args.size) {
      return Some(None())
    }
    parseNumH(T, args(i), minOpt, maxOpt) match {
      case (T, vOpt) => return Some(vOpt)
      case _ => return None()
    }
  }

  def parseNumH(optArg: B, arg: String, minOpt: Option[Z], maxOpt: Option[Z]): (B, Option[Z]) = {
    Z(arg) match {
      case Some(n) =>
        minOpt match {
          case Some(min) =>
            if (n < min) {
              eprintln(s"Expecting an integer at least $min, but found $n.")
              return (F, None())
            }
          case _ =>
        }
        maxOpt match {
          case Some(max) =>
            if (n > max) {
              eprintln(s"Expecting an integer at most $max, but found $n.")
              return (F, None())
            }
          case _ =>
        }
        return (T, Some(n))
      case _ =>
        if (!optArg) {
          eprintln(s"Expecting an integer, but found '$arg'.")
          return (F, None())
        } else {
          return (T, None())
       }
    }
  }

  def select(mode: String, args: ISZ[String], i: Z, choices: ISZ[String]): Option[String] = {
    val arg = args(i)
    var cs = ISZ[String]()
    for (c <- choices) {
      if (ops.StringOps(c).startsWith(arg)) {
        cs = cs :+ c
      }
    }
    cs.size match {
      case z"0" =>
        eprintln(s"$arg is not a mode of $mode.")
        return None()
      case z"1" => return Some(cs(0))
      case _ =>
        eprintln(
          st"""Which one of the following modes did you mean by '$arg'?
              |${(cs, "\n")}""".render)
        return None()
    }
  }
}
// @formatter:on

// BEGIN USER CODE

// END USER CODE
