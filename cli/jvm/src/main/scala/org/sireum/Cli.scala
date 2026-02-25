// #Sireum
// @formatter:off

/*
 Copyright (c) 2017-2026,Robby, Kansas State University
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

  @enum object SireumHamrCodegenHamrPlatform {
    "JVM"
    "Linux"
    "Cygwin"
    "MacOS"
    "SeL4"
    "SeL4_Only"
    "SeL4_TB"
    "Microkit"
    "Ros2"
  }

  @enum object SireumHamrCodegenNodesCodeLanguage {
    "Python"
    "Cpp"
  }

  @enum object SireumHamrCodegenLaunchCodeLanguage {
    "Python"
    "Xml"
  }

  @datatype class SireumHamrCodegenOption(
    val help: String,
    val args: ISZ[String],
    val msgpack: B,
    val verbose: B,
    val runtimeMonitoring: B,
    val platform: SireumHamrCodegenHamrPlatform.Type,
    val outputDir: Option[String],
    val parseableMessages: B,
    val slangOutputDir: Option[String],
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
    val sel4OutputDir: Option[String],
    val sel4AuxCodeDirs: ISZ[String],
    val workspaceRootDir: Option[String],
    val strictAadlMode: B,
    val ros2OutputWorkspaceDir: Option[String],
    val ros2Dir: Option[String],
    val ros2NodesLanguage: SireumHamrCodegenNodesCodeLanguage.Type,
    val ros2LaunchLanguage: SireumHamrCodegenLaunchCodeLanguage.Type,
    val invertTopicBinding: B,
    val experimentalOptions: ISZ[String]
  ) extends SireumTopOption

  @enum object SireumHamrPhantomPhantomMode {
    "Json"
    "Json_compact"
    "Msgpack"
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
    val version: Option[String],
    val sireum: Option[String]
  ) extends SireumTopOption

  @enum object SireumHamrSysmlCodegenHamrPlatform {
    "JVM"
    "Linux"
    "Cygwin"
    "MacOS"
    "SeL4"
    "SeL4_Only"
    "SeL4_TB"
    "Microkit"
    "Ros2"
  }

  @enum object SireumHamrSysmlCodegenNodesCodeLanguage {
    "Python"
    "Cpp"
  }

  @enum object SireumHamrSysmlCodegenLaunchCodeLanguage {
    "Python"
    "Xml"
  }

  @datatype class SireumHamrSysmlCodegenOption(
    val help: String,
    val args: ISZ[String],
    val sourcepath: ISZ[String],
    val line: Z,
    val system: Option[String],
    val verbose: B,
    val runtimeMonitoring: B,
    val platform: SireumHamrSysmlCodegenHamrPlatform.Type,
    val outputDir: Option[String],
    val parseableMessages: B,
    val slangOutputDir: Option[String],
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
    val sel4OutputDir: Option[String],
    val sel4AuxCodeDirs: ISZ[String],
    val workspaceRootDir: Option[String],
    val strictAadlMode: B,
    val ros2OutputWorkspaceDir: Option[String],
    val ros2Dir: Option[String],
    val ros2NodesLanguage: SireumHamrSysmlCodegenNodesCodeLanguage.Type,
    val ros2LaunchLanguage: SireumHamrSysmlCodegenLaunchCodeLanguage.Type,
    val invertTopicBinding: B,
    val experimentalOptions: ISZ[String]
  ) extends SireumTopOption

  @enum object SireumHamrSysmlConfigTheme {
    "Dark"
    "Light"
  }

  @datatype class SireumHamrSysmlConfigOption(
    val help: String,
    val args: ISZ[String],
    val theme: SireumHamrSysmlConfigTheme.Type
  ) extends SireumTopOption

  @enum object SireumHamrSysmlLogikaFPRoundingMode {
    "NearestTiesToEven"
    "NearestTiesToAway"
    "TowardPositive"
    "TowardNegative"
    "TowardZero"
  }

  @enum object SireumHamrSysmlLogikaStrictPureMode {
    "Default"
    "Flip"
    "Uninterpreted"
  }

  @datatype class SireumHamrSysmlLogikaOption(
    val help: String,
    val args: ISZ[String],
    val integrationOnly: B,
    val exclude: ISZ[String],
    val feedback: Option[String],
    val sourcepath: ISZ[String],
    val parseableMessages: B,
    val charBitWidth: Z,
    val fpRounding: SireumHamrSysmlLogikaFPRoundingMode.Type,
    val useReal: B,
    val intBitWidth: Z,
    val interprocedural: B,
    val interproceduralContracts: B,
    val strictPureMode: SireumHamrSysmlLogikaStrictPureMode.Type,
    val line: Z,
    val loopBound: Z,
    val callBound: Z,
    val patternExhaustive: B,
    val pureFun: B,
    val sat: B,
    val skipMethods: ISZ[String],
    val skipTypes: ISZ[String],
    val logPc: B,
    val logPcLines: B,
    val logRawPc: B,
    val logVc: B,
    val logVcDir: Option[String],
    val logDetailedInfo: B,
    val logAtRewrite: B,
    val stats: B,
    val par: Option[Z],
    val branchPar: B,
    val branchParReturn: B,
    val branchPredNum: Z,
    val branchPredComplexity: Z,
    val rwPar: B,
    val dontSplitFunQuant: B,
    val splitAll: B,
    val splitContract: B,
    val splitIf: B,
    val splitMatch: B,
    val rwMax: Z,
    val rwTrace: B,
    val rwEvalTrace: B,
    val elideEncoding: B,
    val rawInscription: B,
    val rlimit: Z,
    val sequential: B,
    val simplify: B,
    val smt2SatConfigs: Option[String],
    val smt2ValidConfigs: Option[String],
    val satTimeout: B,
    val timeout: Z,
    val searchPC: B
  ) extends SireumTopOption

  @datatype class SireumHamrSysmlTipeOption(
    val help: String,
    val args: ISZ[String],
    val exclude: ISZ[String],
    val sourcepath: ISZ[String],
    val parseableMessages: B
  ) extends SireumTopOption

  @datatype class SireumHamrSysmlTranslatorOption(
    val help: String,
    val args: ISZ[String],
    val version: Option[String],
    val grammar: Option[String],
    val url: Option[String],
    val keywords: ISZ[String]
  ) extends SireumTopOption

  @enum object SireumLogikaConfigTheme {
    "Dark"
    "Light"
  }

  @datatype class SireumLogikaConfigOption(
    val help: String,
    val args: ISZ[String],
    val theme: SireumLogikaConfigTheme.Type
  ) extends SireumTopOption

  @enum object SireumLogikaVerifierFPRoundingMode {
    "NearestTiesToEven"
    "NearestTiesToAway"
    "TowardPositive"
    "TowardNegative"
    "TowardZero"
  }

  @enum object SireumLogikaVerifierStrictPureMode {
    "Default"
    "Flip"
    "Uninterpreted"
  }

  @datatype class SireumLogikaVerifierOption(
    val help: String,
    val args: ISZ[String],
    val feedback: Option[String],
    val manual: B,
    val noRuntime: B,
    val parseableMessages: B,
    val sourcepath: ISZ[String],
    val infoFlow: B,
    val charBitWidth: Z,
    val fpRounding: SireumLogikaVerifierFPRoundingMode.Type,
    val useReal: B,
    val intBitWidth: Z,
    val interprocedural: B,
    val interproceduralContracts: B,
    val strictPureMode: SireumLogikaVerifierStrictPureMode.Type,
    val line: Z,
    val loopBound: Z,
    val callBound: Z,
    val patternExhaustive: B,
    val pureFun: B,
    val sat: B,
    val skipMethods: ISZ[String],
    val skipTypes: ISZ[String],
    val logPc: B,
    val logPcLines: B,
    val logRawPc: B,
    val logVc: B,
    val logVcDir: Option[String],
    val logDetailedInfo: B,
    val logAtRewrite: B,
    val stats: B,
    val par: Option[Z],
    val branchPar: B,
    val branchParReturn: B,
    val branchPredNum: Z,
    val branchPredComplexity: Z,
    val rwPar: B,
    val dontSplitFunQuant: B,
    val splitAll: B,
    val splitContract: B,
    val splitIf: B,
    val splitMatch: B,
    val rwMax: Z,
    val rwTrace: B,
    val rwEvalTrace: B,
    val elideEncoding: B,
    val rawInscription: B,
    val rlimit: Z,
    val sequential: B,
    val simplify: B,
    val smt2SatConfigs: Option[String],
    val smt2ValidConfigs: Option[String],
    val satTimeout: B,
    val timeout: Z,
    val searchPC: B
  ) extends SireumTopOption

  @enum object SireumParserGenParserGenMode {
    "Slang"
    "Antlr3"
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
    val excludeJarDeps: ISZ[String],
    val includeSources: B,
    val includeTests: B,
    val jar: Option[String],
    val noDeps: B,
    val mainClass: Option[String],
    val meta: B,
    val isNative: B,
    val isNativeScript: B,
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
    val proxyHost: Option[String],
    val proxyNonHosts: Option[String],
    val proxyPort: Option[String],
    val proxyProtocol: Option[String],
    val proxyUser: Option[String],
    val proxyPassword: Option[String],
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
    val meta: B,
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
    val proxyHost: Option[String],
    val proxyNonHosts: Option[String],
    val proxyPort: Option[String],
    val proxyProtocol: Option[String],
    val proxyUser: Option[String],
    val proxyPassword: Option[String],
    val repositories: ISZ[String]
  ) extends SireumTopOption

  @datatype class SireumProyekDepOption(
    val help: String,
    val args: ISZ[String],
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
    val proxyHost: Option[String],
    val proxyNonHosts: Option[String],
    val proxyPort: Option[String],
    val proxyProtocol: Option[String],
    val proxyUser: Option[String],
    val proxyPassword: Option[String],
    val repositories: ISZ[String]
  ) extends SireumTopOption

  @enum object SireumProyekExportBuildTool {
    "Bloop"
    "Mill"
  }

  @datatype class SireumProyekExportOption(
    val help: String,
    val args: ISZ[String],
    val javac: ISZ[String],
    val fresh: B,
    val par: Option[Z],
    val recompile: ISZ[String],
    val scalac: ISZ[String],
    val sha3: B,
    val target: ISZ[SireumProyekExportBuildTool.Type],
    val ignoreRuntime: B,
    val json: Option[String],
    val name: Option[String],
    val outputDirName: Option[String],
    val project: Option[String],
    val slice: ISZ[String],
    val symlink: B,
    val versions: ISZ[String]
  ) extends SireumTopOption

  @enum object SireumProyekIveEdition {
    "Community"
    "Ultimate"
    "Server"
  }

  @datatype class SireumProyekIveOption(
    val help: String,
    val args: ISZ[String],
    val empty: B,
    val edition: SireumProyekIveEdition.Type,
    val force: B,
    val rebuildIve: B,
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
    val proxyHost: Option[String],
    val proxyNonHosts: Option[String],
    val proxyPort: Option[String],
    val proxyProtocol: Option[String],
    val proxyUser: Option[String],
    val proxyPassword: Option[String],
    val repositories: ISZ[String]
  ) extends SireumTopOption

  @enum object SireumProyekLogikaFPRoundingMode {
    "NearestTiesToEven"
    "NearestTiesToAway"
    "TowardPositive"
    "TowardNegative"
    "TowardZero"
  }

  @enum object SireumProyekLogikaStrictPureMode {
    "Default"
    "Flip"
    "Uninterpreted"
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
    val proxyHost: Option[String],
    val proxyNonHosts: Option[String],
    val proxyPort: Option[String],
    val proxyProtocol: Option[String],
    val proxyUser: Option[String],
    val proxyPassword: Option[String],
    val repositories: ISZ[String],
    val infoFlow: B,
    val charBitWidth: Z,
    val fpRounding: SireumProyekLogikaFPRoundingMode.Type,
    val useReal: B,
    val intBitWidth: Z,
    val interprocedural: B,
    val interproceduralContracts: B,
    val strictPureMode: SireumProyekLogikaStrictPureMode.Type,
    val line: Z,
    val loopBound: Z,
    val callBound: Z,
    val patternExhaustive: B,
    val pureFun: B,
    val sat: B,
    val skipMethods: ISZ[String],
    val skipTypes: ISZ[String],
    val logPc: B,
    val logPcLines: B,
    val logRawPc: B,
    val logVc: B,
    val logVcDir: Option[String],
    val logDetailedInfo: B,
    val logAtRewrite: B,
    val stats: B,
    val par: Option[Z],
    val branchPar: B,
    val branchParReturn: B,
    val branchPredNum: Z,
    val branchPredComplexity: Z,
    val rwPar: B,
    val dontSplitFunQuant: B,
    val splitAll: B,
    val splitContract: B,
    val splitIf: B,
    val splitMatch: B,
    val rwMax: Z,
    val rwTrace: B,
    val rwEvalTrace: B,
    val elideEncoding: B,
    val rawInscription: B,
    val rlimit: Z,
    val sequential: B,
    val simplify: B,
    val smt2SatConfigs: Option[String],
    val smt2ValidConfigs: Option[String],
    val satTimeout: B,
    val timeout: Z,
    val searchPC: B
  ) extends SireumTopOption

  @enum object SireumProyekPublishTarget {
    "All"
    "Jvm"
    "Js"
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
    val proxyHost: Option[String],
    val proxyNonHosts: Option[String],
    val proxyPort: Option[String],
    val proxyProtocol: Option[String],
    val proxyUser: Option[String],
    val proxyPassword: Option[String],
    val repositories: ISZ[String]
  ) extends SireumTopOption

  @datatype class SireumProyekReflectOption(
    val help: String,
    val args: ISZ[String],
    val className: Option[String],
    val includePackages: ISZ[String],
    val include: ISZ[String],
    val excludePackages: ISZ[String],
    val exclude: ISZ[String],
    val license: Option[String],
    val packageName: Option[String],
    val output: Option[String],
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
    val versions: ISZ[String]
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
    val proxyHost: Option[String],
    val proxyNonHosts: Option[String],
    val proxyPort: Option[String],
    val proxyProtocol: Option[String],
    val proxyUser: Option[String],
    val proxyPassword: Option[String],
    val repositories: ISZ[String]
  ) extends SireumTopOption

  @datatype class SireumProyekSlangcheckOption(
    val help: String,
    val args: ISZ[String],
    val license: Option[String],
    val packageName: Option[String],
    val outputDir: Option[String],
    val testDir: Option[String],
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
    val proxyHost: Option[String],
    val proxyNonHosts: Option[String],
    val proxyPort: Option[String],
    val proxyProtocol: Option[String],
    val proxyUser: Option[String],
    val proxyPassword: Option[String],
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
    val proxyHost: Option[String],
    val proxyNonHosts: Option[String],
    val proxyPort: Option[String],
    val proxyProtocol: Option[String],
    val proxyUser: Option[String],
    val proxyPassword: Option[String],
    val repositories: ISZ[String]
  ) extends SireumTopOption

  @datatype class SireumProyekTestOption(
    val help: String,
    val args: ISZ[String],
    val classes: ISZ[String],
    val coverage: Option[String],
    val java: ISZ[String],
    val junit5: B,
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
    val proxyHost: Option[String],
    val proxyNonHosts: Option[String],
    val proxyPort: Option[String],
    val proxyProtocol: Option[String],
    val proxyUser: Option[String],
    val proxyPassword: Option[String],
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
    val versions: ISZ[String]
  ) extends SireumTopOption

  @enum object SireumSlangRefactorMode {
    "EnumSymbol"
    "InsertVal"
    "RenumberProof"
    "ReformatProof"
    "ExpandInduct"
  }

  @datatype class SireumSlangRefactorOption(
    val help: String,
    val args: ISZ[String],
    val feedback: Option[String],
    val mode: SireumSlangRefactorMode.Type
  ) extends SireumTopOption

  @datatype class SireumSlangRunOption(
    val help: String,
    val args: ISZ[String],
    val eval: B,
    val input: Option[String],
    val output: Option[String],
    val transformed: B,
    val nativ: B
  ) extends SireumTopOption

  @enum object SireumSlangTemplateMode {
    "Step"
    "Assume"
    "Assert"
    "Subproof"
    "SubproofFresh"
    "Forall"
    "Exists"
    "ForallRange"
    "ExistsRange"
    "ForallElements"
    "ExistsElements"
    "ForallIndices"
    "ExistsIndices"
  }

  @datatype class SireumSlangTemplateOption(
    val help: String,
    val args: ISZ[String],
    val feedback: Option[String],
    val line: Z,
    val column: Z,
    val mode: SireumSlangTemplateMode.Type
  ) extends SireumTopOption

  @datatype class SireumSlangTipeOption(
    val help: String,
    val args: ISZ[String],
    val exclude: ISZ[String],
    val force: ISZ[String],
    val noRuntime: B,
    val outline: B,
    val parseableMessages: B,
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
    "Mp3"
    "Wav"
  }

  @enum object SireumPresentasiGenService {
    "Mary"
    "Aws"
    "Azure"
  }

  @enum object SireumPresentasiGenEngine {
    "Neural"
    "Standard"
  }

  @datatype class SireumPresentasiGenOption(
    val help: String,
    val args: ISZ[String],
    val cc: Z,
    val clean: B,
    val record: B,
    val slice: ISZ[String],
    val slides: B,
    val srt: B,
    val videoFps: Z,
    val videoHeight: Z,
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
    "Mp3"
    "Webm"
    "Ogg"
    "Wav"
  }

  @enum object SireumPresentasiText2speechService {
    "Mary"
    "Aws"
    "Azure"
  }

  @enum object SireumPresentasiText2speechEngine {
    "Neural"
    "Standard"
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
    "Json"
    "Msgpack"
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

  @datatype class SireumSetupIveOption(
    val help: String,
    val args: ISZ[String]
  ) extends SireumTopOption

  @datatype class SireumSetupVscodeOption(
    val help: String,
    val args: ISZ[String],
    val existingInstall: Option[String],
    val extensions: ISZ[String],
    val extensionsDir: Option[String],
    val vscode: B
  ) extends SireumTopOption

  @enum object SireumToolsBcgenBitCodecMode {
    "Program"
    "Script"
    "Json"
    "Dot"
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
    "Dotsu"
    "Bin"
  }

  @enum object SireumToolsCheckstackCheckStackArch {
    "Amd64"
    "X86"
    "Aarch64"
    "Arm"
    "Powerpc"
    "Openrisc"
    "Mips"
    "Mips64"
    "M68k"
    "Ia64"
    "Nios2"
    "Parisc"
    "S390x"
    "Sh64"
    "Sparc"
  }

  @enum object SireumToolsCheckstackCheckStackFormat {
    "Plain"
    "Csv"
    "Html"
    "Md"
    "Rst"
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
    val reporter: B,
    val script: Option[String],
    val width: ISZ[Z]
  ) extends SireumTopOption

  @datatype class SireumToolsJsonsOption(
    val help: String,
    val args: ISZ[String],
    val packageName: ISZ[String],
    val name: Option[String],
    val license: Option[String],
    val outputDir: Option[String]
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
    val parseableMessages: B,
    val sourcepath: ISZ[String],
    val strictAliasing: B,
    val verbose: B,
    val save: Option[String],
    val load: Option[String],
    val gzip: B
  ) extends SireumTopOption

  @enum object SireumToolsSergenSerializerMode {
    "Json"
    "Msgpack"
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

  @datatype class SireumToolsSlangcheckRunnerOption(
    val help: String,
    val args: ISZ[String],
    val classpath: ISZ[String],
    val max: Z,
    val output: Option[String],
    val par: Option[Z],
    val scp: Option[String],
    val timeout: Z
  ) extends SireumTopOption

  @datatype class SireumToolsSlangcheckTesterOption(
    val help: String,
    val args: ISZ[String],
    val classpath: ISZ[String],
    val coverage: Option[String],
    val input: Option[String],
    val output: Option[String],
    val par: Option[Z],
    val scp: Option[String],
    val sourcepath: ISZ[String]
  ) extends SireumTopOption

  @datatype class SireumToolsSlangcheckGeneratorOption(
    val help: String,
    val args: ISZ[String],
    val license: Option[String],
    val packageName: ISZ[String],
    val outputDir: Option[String],
    val testDir: Option[String]
  ) extends SireumTopOption

  @enum object SireumToolsTrafoTransformerMode {
    "Immutable"
    "Mutable"
    "Rimmutable"
    "Rmutable"
  }

  @datatype class SireumToolsTrafoOption(
    val help: String,
    val args: ISZ[String],
    val exclude: ISZ[String],
    val license: Option[String],
    val modes: ISZ[SireumToolsTrafoTransformerMode.Type],
    val name: Option[String],
    val outputDir: Option[String]
  ) extends SireumTopOption

  @datatype class SireumXAnvilOption(
    val help: String,
    val args: ISZ[String],
    val sourcepath: ISZ[String],
    val strictAliasing: B,
    val memory: Z,
    val erase: B,
    val depth: Z,
    val runtimeCheck: B,
    val stackTrace: B,
    val copy: Z,
    val printSize: Option[Z],
    val output: Option[String],
    val axi4: B,
    val customDivRem: B,
    val verbose: B,
    val bitWidth: Z,
    val projectName: Option[String],
    val customArraySizes: ISZ[String],
    val maxArraySize: Z,
    val maxStringSize: Z,
    val save: Option[String],
    val load: Option[String],
    val customConstants: ISZ[String]
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
            |hamr                     HAMR tools
            |logika                   Logika tools
            |parser                   Parser tools
            |proyek                   Build tools
            |slang                    Slang tools
            |presentasi               Presentation tools
            |server                   Sireum server
            |setup                    Setup
            |tools                    Utility tools""".render
      )
      return Some(HelpOption())
    }
    val opt = select("sireum", args, i, ISZ("hamr", "logika", "parser", "proyek", "slang", "presentasi", "server", "setup", "tools", "x"))
    opt match {
      case Some(string"hamr") => return parseSireumHamr(args, i + 1)
      case Some(string"logika") => return parseSireumLogika(args, i + 1)
      case Some(string"parser") => return parseSireumParser(args, i + 1)
      case Some(string"proyek") => return parseSireumProyek(args, i + 1)
      case Some(string"slang") => return parseSireumSlang(args, i + 1)
      case Some(string"presentasi") => return parseSireumPresentasi(args, i + 1)
      case Some(string"server") => return parseSireumServer(args, i + 1)
      case Some(string"setup") => return parseSireumSetup(args, i + 1)
      case Some(string"tools") => return parseSireumTools(args, i + 1)
      case Some(string"x") => return parseSireumX(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumHamr(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""HAMR: High Assurance Model-based Rapid-engineering tools for embedded systems
            |
            |Available modes:
            |codegen                 
            |phantom                 
            |sysml                    SysML v2 Tools""".render
      )
      return Some(HelpOption())
    }
    val opt = select("hamr", args, i, ISZ("codegen", "phantom", "sysml"))
    opt match {
      case Some(string"codegen") => return parseSireumHamrCodegen(args, i + 1)
      case Some(string"phantom") => return parseSireumHamrPhantom(args, i + 1)
      case Some(string"sysml") => return parseSireumHamrSysml(args, i + 1)
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
      case "Microkit" => return Some(SireumHamrCodegenHamrPlatform.Microkit)
      case "ros2" => return Some(SireumHamrCodegenHamrPlatform.Ros2)
      case s =>
        eprintln(s"Expecting one of the following: { JVM, Linux, Cygwin, MacOS, seL4, seL4_Only, seL4_TB, Microkit, ros2 }, but found '$s'.")
        return None()
    }
  }

  def parseSireumHamrCodegenHamrPlatform(args: ISZ[String], i: Z): Option[SireumHamrCodegenHamrPlatform.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { JVM, Linux, Cygwin, MacOS, seL4, seL4_Only, seL4_TB, Microkit, ros2 }, but none found.")
      return None()
    }
    val r = parseSireumHamrCodegenHamrPlatformH(args(i))
    return r
  }

  def parseSireumHamrCodegenNodesCodeLanguageH(arg: String): Option[SireumHamrCodegenNodesCodeLanguage.Type] = {
    arg.native match {
      case "Python" => return Some(SireumHamrCodegenNodesCodeLanguage.Python)
      case "Cpp" => return Some(SireumHamrCodegenNodesCodeLanguage.Cpp)
      case s =>
        eprintln(s"Expecting one of the following: { Python, Cpp }, but found '$s'.")
        return None()
    }
  }

  def parseSireumHamrCodegenNodesCodeLanguage(args: ISZ[String], i: Z): Option[SireumHamrCodegenNodesCodeLanguage.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { Python, Cpp }, but none found.")
      return None()
    }
    val r = parseSireumHamrCodegenNodesCodeLanguageH(args(i))
    return r
  }

  def parseSireumHamrCodegenLaunchCodeLanguageH(arg: String): Option[SireumHamrCodegenLaunchCodeLanguage.Type] = {
    arg.native match {
      case "Python" => return Some(SireumHamrCodegenLaunchCodeLanguage.Python)
      case "Xml" => return Some(SireumHamrCodegenLaunchCodeLanguage.Xml)
      case s =>
        eprintln(s"Expecting one of the following: { Python, Xml }, but found '$s'.")
        return None()
    }
  }

  def parseSireumHamrCodegenLaunchCodeLanguage(args: ISZ[String], i: Z): Option[SireumHamrCodegenLaunchCodeLanguage.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { Python, Xml }, but none found.")
      return None()
    }
    val r = parseSireumHamrCodegenLaunchCodeLanguageH(args(i))
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
          |-m, --runtime-monitoring    
          |                          Enable runtime monitoring
          |-p, --platform           Target platform (expects one of { JVM, Linux, Cygwin,
          |                           MacOS, seL4, seL4_Only, seL4_TB, Microkit, ros2 };
          |                           default: JVM)
          |-o, --output-dir         Default output directory (expects a path)
          |    --parseable-messages Print parseable file messages
          |-h, --help               Display this information
          |
          |Slang Options:
          |    --slang-output-dir   Output directory for the generated Slang project files
          |                           (expects a path)
          |-n, --package-name       Base package name for Slang project (expects a string;
          |                           default is "base")
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
          |CAmkES/Microkit Options:
          |    --sel4-output-dir    Output directory for the generated CAmkES/Microkit
          |                           project files (expects a path)
          |    --sel4-aux-code-dirs Directories containing C files to be included in
          |                           CAmkES/Microkit build (expects path strings)
          |-r, --workspace-root-dir    
          |                          Root directory containing the architectural model
          |                           project (expects a path)
          |
          |ROS2 Options:
          |    --strict-aadl-mode   Whether to generate strictly AADL-compliant code or
          |                           not (will probably become obsolete soon)
          |    --ros2-output-workspace-dir
          |                          The path to the ROS2 workspace to generate the
          |                           packages into (expects a path)
          |-r, --ros2-dir           The path to your ROS2 installation, including the
          |                           version (../ros/humble) (expects a path)
          |-p, --ros2-nodes-language    
          |                          The programming language for the generated node files
          |                           (expects one of { Python, Cpp }; default: Python)
          |-p, --ros2-launch-language    
          |                          The programming language for the launch file (expects
          |                           one of { Python, Xml }; default: Python)
          |    --invert-topic-binding
          |                          By default, topic names are based on in ports, and
          |                           fan out ports would have multiple publishers.  This
          |                           option inverts that behavior.
          |
          |Experimental Options:
          |-x, --experimental-options    
          |                           (expects a string separated by ";")""".render

    var msgpack: B = false
    var verbose: B = false
    var runtimeMonitoring: B = false
    var platform: SireumHamrCodegenHamrPlatform.Type = SireumHamrCodegenHamrPlatform.JVM
    var outputDir: Option[String] = None[String]()
    var parseableMessages: B = false
    var slangOutputDir: Option[String] = None[String]()
    var packageName: Option[String] = Some("base")
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
    var sel4OutputDir: Option[String] = None[String]()
    var sel4AuxCodeDirs: ISZ[String] = ISZ[String]()
    var workspaceRootDir: Option[String] = None[String]()
    var strictAadlMode: B = false
    var ros2OutputWorkspaceDir: Option[String] = None[String]()
    var ros2Dir: Option[String] = None[String]()
    var ros2NodesLanguage: SireumHamrCodegenNodesCodeLanguage.Type = SireumHamrCodegenNodesCodeLanguage.Python
    var ros2LaunchLanguage: SireumHamrCodegenLaunchCodeLanguage.Type = SireumHamrCodegenLaunchCodeLanguage.Python
    var invertTopicBinding: B = false
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
         } else if (arg == "-m" || arg == "--runtime-monitoring") {
           val o: Option[B] = { j = j - 1; Some(!runtimeMonitoring) }
           o match {
             case Some(v) => runtimeMonitoring = v
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
         } else if (arg == "--parseable-messages") {
           val o: Option[B] = { j = j - 1; Some(!parseableMessages) }
           o match {
             case Some(v) => parseableMessages = v
             case _ => return None()
           }
         } else if (arg == "--slang-output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => slangOutputDir = v
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
         } else if (arg == "--sel4-output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => sel4OutputDir = v
             case _ => return None()
           }
         } else if (arg == "--sel4-aux-code-dirs") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => sel4AuxCodeDirs = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--workspace-root-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => workspaceRootDir = v
             case _ => return None()
           }
         } else if (arg == "--strict-aadl-mode") {
           val o: Option[B] = { j = j - 1; Some(!strictAadlMode) }
           o match {
             case Some(v) => strictAadlMode = v
             case _ => return None()
           }
         } else if (arg == "--ros2-output-workspace-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => ros2OutputWorkspaceDir = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--ros2-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => ros2Dir = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--ros2-nodes-language") {
           val o: Option[SireumHamrCodegenNodesCodeLanguage.Type] = parseSireumHamrCodegenNodesCodeLanguage(args, j + 1)
           o match {
             case Some(v) => ros2NodesLanguage = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--ros2-launch-language") {
           val o: Option[SireumHamrCodegenLaunchCodeLanguage.Type] = parseSireumHamrCodegenLaunchCodeLanguage(args, j + 1)
           o match {
             case Some(v) => ros2LaunchLanguage = v
             case _ => return None()
           }
         } else if (arg == "--invert-topic-binding") {
           val o: Option[B] = { j = j - 1; Some(!invertTopicBinding) }
           o match {
             case Some(v) => invertTopicBinding = v
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
    return Some(SireumHamrCodegenOption(help, parseArguments(args, j), msgpack, verbose, runtimeMonitoring, platform, outputDir, parseableMessages, slangOutputDir, packageName, noProyekIve, noEmbedArt, devicesAsThreads, genSbtMill, slangAuxCodeDirs, slangOutputCDir, excludeComponentImpl, bitWidth, maxStringSize, maxArraySize, runTranspiler, sel4OutputDir, sel4AuxCodeDirs, workspaceRootDir, strictAadlMode, ros2OutputWorkspaceDir, ros2Dir, ros2NodesLanguage, ros2LaunchLanguage, invertTopicBinding, experimentalOptions))
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
                  |    phantom --update [--osate <path>] [--features <config>] [--sireum-home <path>]
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
          |-m, --mode               AADL model serialization method (expects one of {
          |                           json, json_compact, msgpack }; default: json)
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
          |    --version            OSATE version (expects a string; default is
          |                           "2.17.0-vfinal")
          |    --sireum-home        Change the location of the Sireum home installation
          |                           directory that OSATE uses (expects a path)""".render

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
    var version: Option[String] = Some("2.17.0-vfinal")
    var sireum: Option[String] = None[String]()
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
         } else if (arg == "--version") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => version = v
             case _ => return None()
           }
         } else if (arg == "--sireum-home") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => sireum = v
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
    return Some(SireumHamrPhantomOption(help, parseArguments(args, j), impl, main, mode, output, projects, verbose, verbosePlus, osate, update, features, version, sireum))
  }

  def parseSireumHamrSysml(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum HAMR SysML v2 Tools
            |
            |Available modes:
            |codegen                  SysML v2 Codegen
            |config                   SysML v2 CodeGen Config
            |logika                   SysML v2 Verifier
            |tipe                     SysML v2 Type Checker
            |translator               SysML v2 Grammar Translator to ANTLR v4""".render
      )
      return Some(HelpOption())
    }
    val opt = select("sysml", args, i, ISZ("codegen", "config", "logika", "tipe", "translator"))
    opt match {
      case Some(string"codegen") => return parseSireumHamrSysmlCodegen(args, i + 1)
      case Some(string"config") => return parseSireumHamrSysmlConfig(args, i + 1)
      case Some(string"logika") => return parseSireumHamrSysmlLogika(args, i + 1)
      case Some(string"tipe") => return parseSireumHamrSysmlTipe(args, i + 1)
      case Some(string"translator") => return parseSireumHamrSysmlTranslator(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumHamrSysmlCodegenHamrPlatformH(arg: String): Option[SireumHamrSysmlCodegenHamrPlatform.Type] = {
    arg.native match {
      case "JVM" => return Some(SireumHamrSysmlCodegenHamrPlatform.JVM)
      case "Linux" => return Some(SireumHamrSysmlCodegenHamrPlatform.Linux)
      case "Cygwin" => return Some(SireumHamrSysmlCodegenHamrPlatform.Cygwin)
      case "MacOS" => return Some(SireumHamrSysmlCodegenHamrPlatform.MacOS)
      case "seL4" => return Some(SireumHamrSysmlCodegenHamrPlatform.SeL4)
      case "seL4_Only" => return Some(SireumHamrSysmlCodegenHamrPlatform.SeL4_Only)
      case "seL4_TB" => return Some(SireumHamrSysmlCodegenHamrPlatform.SeL4_TB)
      case "Microkit" => return Some(SireumHamrSysmlCodegenHamrPlatform.Microkit)
      case "ros2" => return Some(SireumHamrSysmlCodegenHamrPlatform.Ros2)
      case s =>
        eprintln(s"Expecting one of the following: { JVM, Linux, Cygwin, MacOS, seL4, seL4_Only, seL4_TB, Microkit, ros2 }, but found '$s'.")
        return None()
    }
  }

  def parseSireumHamrSysmlCodegenHamrPlatform(args: ISZ[String], i: Z): Option[SireumHamrSysmlCodegenHamrPlatform.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { JVM, Linux, Cygwin, MacOS, seL4, seL4_Only, seL4_TB, Microkit, ros2 }, but none found.")
      return None()
    }
    val r = parseSireumHamrSysmlCodegenHamrPlatformH(args(i))
    return r
  }

  def parseSireumHamrSysmlCodegenNodesCodeLanguageH(arg: String): Option[SireumHamrSysmlCodegenNodesCodeLanguage.Type] = {
    arg.native match {
      case "Python" => return Some(SireumHamrSysmlCodegenNodesCodeLanguage.Python)
      case "Cpp" => return Some(SireumHamrSysmlCodegenNodesCodeLanguage.Cpp)
      case s =>
        eprintln(s"Expecting one of the following: { Python, Cpp }, but found '$s'.")
        return None()
    }
  }

  def parseSireumHamrSysmlCodegenNodesCodeLanguage(args: ISZ[String], i: Z): Option[SireumHamrSysmlCodegenNodesCodeLanguage.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { Python, Cpp }, but none found.")
      return None()
    }
    val r = parseSireumHamrSysmlCodegenNodesCodeLanguageH(args(i))
    return r
  }

  def parseSireumHamrSysmlCodegenLaunchCodeLanguageH(arg: String): Option[SireumHamrSysmlCodegenLaunchCodeLanguage.Type] = {
    arg.native match {
      case "Python" => return Some(SireumHamrSysmlCodegenLaunchCodeLanguage.Python)
      case "Xml" => return Some(SireumHamrSysmlCodegenLaunchCodeLanguage.Xml)
      case s =>
        eprintln(s"Expecting one of the following: { Python, Xml }, but found '$s'.")
        return None()
    }
  }

  def parseSireumHamrSysmlCodegenLaunchCodeLanguage(args: ISZ[String], i: Z): Option[SireumHamrSysmlCodegenLaunchCodeLanguage.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { Python, Xml }, but none found.")
      return None()
    }
    val r = parseSireumHamrSysmlCodegenLaunchCodeLanguageH(args(i))
    return r
  }

  def parseSireumHamrSysmlCodegen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum HAMR SysML v2 Code Generator
          |
          |Usage: <option>* [<sysmlv2-file>]
          |
          |Available Options:
          |    --sourcepath         Source paths of SysML v2 .sysml files (expects path
          |                           strings)
          |    --line               Line number containing the system to instantiate in
          |                           the <sysmlv2-file> argument (expects an integer; min
          |                           is 0; default is 0)
          |    --system-name        Fully qualified name of the system to instantiate
          |                           (expects a string)
          |-v, --verbose            Enable verbose mode
          |-m, --runtime-monitoring    
          |                          Enable runtime monitoring
          |-p, --platform           Target platform (expects one of { JVM, Linux, Cygwin,
          |                           MacOS, seL4, seL4_Only, seL4_TB, Microkit, ros2 };
          |                           default: JVM)
          |-o, --output-dir         Default output directory (expects a path)
          |    --parseable-messages Print parseable file messages
          |-h, --help               Display this information
          |
          |Slang Options:
          |    --slang-output-dir   Output directory for the generated Slang project files
          |                           (expects a path)
          |-n, --package-name       Base package name for Slang project (expects a string;
          |                           default is "base")
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
          |CAmkES/Microkit Options:
          |    --sel4-output-dir    Output directory for the generated CAmkES/Microkit
          |                           project files (expects a path)
          |    --sel4-aux-code-dirs Directories containing C files to be included in
          |                           CAmkES/Microkit build (expects path strings)
          |-r, --workspace-root-dir    
          |                          Root directory containing the architectural model
          |                           project (expects a path)
          |
          |ROS2 Options:
          |    --strict-aadl-mode   Whether to generate strictly AADL-compliant code or
          |                           not (will probably become obsolete soon)
          |    --ros2-output-workspace-dir
          |                          The path to the ROS2 workspace to generate the
          |                           packages into (expects a path)
          |-r, --ros2-dir           The path to your ROS2 installation, including the
          |                           version (../ros/humble) (expects a path)
          |-p, --ros2-nodes-language    
          |                          The programming language for the generated node files
          |                           (expects one of { Python, Cpp }; default: Python)
          |-p, --ros2-launch-language    
          |                          The programming language for the launch file (expects
          |                           one of { Python, Xml }; default: Python)
          |    --invert-topic-binding
          |                          By default, topic names are based on in ports, and
          |                           fan out ports would have multiple publishers.  This
          |                           option inverts that behavior.
          |
          |Experimental Options:
          |-x, --experimental-options    
          |                           (expects a string separated by ";")""".render

    var sourcepath: ISZ[String] = ISZ[String]()
    var line: Z = 0
    var system: Option[String] = None[String]()
    var verbose: B = false
    var runtimeMonitoring: B = false
    var platform: SireumHamrSysmlCodegenHamrPlatform.Type = SireumHamrSysmlCodegenHamrPlatform.JVM
    var outputDir: Option[String] = None[String]()
    var parseableMessages: B = false
    var slangOutputDir: Option[String] = None[String]()
    var packageName: Option[String] = Some("base")
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
    var sel4OutputDir: Option[String] = None[String]()
    var sel4AuxCodeDirs: ISZ[String] = ISZ[String]()
    var workspaceRootDir: Option[String] = None[String]()
    var strictAadlMode: B = false
    var ros2OutputWorkspaceDir: Option[String] = None[String]()
    var ros2Dir: Option[String] = None[String]()
    var ros2NodesLanguage: SireumHamrSysmlCodegenNodesCodeLanguage.Type = SireumHamrSysmlCodegenNodesCodeLanguage.Python
    var ros2LaunchLanguage: SireumHamrSysmlCodegenLaunchCodeLanguage.Type = SireumHamrSysmlCodegenLaunchCodeLanguage.Python
    var invertTopicBinding: B = false
    var experimentalOptions: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--sourcepath") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => sourcepath = v
             case _ => return None()
           }
         } else if (arg == "--line") {
           val o: Option[Z] = parseNum(args, j + 1, Some(0), None())
           o match {
             case Some(v) => line = v
             case _ => return None()
           }
         } else if (arg == "--system-name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => system = v
             case _ => return None()
           }
         } else if (arg == "-v" || arg == "--verbose") {
           val o: Option[B] = { j = j - 1; Some(!verbose) }
           o match {
             case Some(v) => verbose = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--runtime-monitoring") {
           val o: Option[B] = { j = j - 1; Some(!runtimeMonitoring) }
           o match {
             case Some(v) => runtimeMonitoring = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--platform") {
           val o: Option[SireumHamrSysmlCodegenHamrPlatform.Type] = parseSireumHamrSysmlCodegenHamrPlatform(args, j + 1)
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
         } else if (arg == "--parseable-messages") {
           val o: Option[B] = { j = j - 1; Some(!parseableMessages) }
           o match {
             case Some(v) => parseableMessages = v
             case _ => return None()
           }
         } else if (arg == "--slang-output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => slangOutputDir = v
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
         } else if (arg == "--sel4-output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => sel4OutputDir = v
             case _ => return None()
           }
         } else if (arg == "--sel4-aux-code-dirs") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => sel4AuxCodeDirs = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--workspace-root-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => workspaceRootDir = v
             case _ => return None()
           }
         } else if (arg == "--strict-aadl-mode") {
           val o: Option[B] = { j = j - 1; Some(!strictAadlMode) }
           o match {
             case Some(v) => strictAadlMode = v
             case _ => return None()
           }
         } else if (arg == "--ros2-output-workspace-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => ros2OutputWorkspaceDir = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--ros2-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => ros2Dir = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--ros2-nodes-language") {
           val o: Option[SireumHamrSysmlCodegenNodesCodeLanguage.Type] = parseSireumHamrSysmlCodegenNodesCodeLanguage(args, j + 1)
           o match {
             case Some(v) => ros2NodesLanguage = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--ros2-launch-language") {
           val o: Option[SireumHamrSysmlCodegenLaunchCodeLanguage.Type] = parseSireumHamrSysmlCodegenLaunchCodeLanguage(args, j + 1)
           o match {
             case Some(v) => ros2LaunchLanguage = v
             case _ => return None()
           }
         } else if (arg == "--invert-topic-binding") {
           val o: Option[B] = { j = j - 1; Some(!invertTopicBinding) }
           o match {
             case Some(v) => invertTopicBinding = v
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
    return Some(SireumHamrSysmlCodegenOption(help, parseArguments(args, j), sourcepath, line, system, verbose, runtimeMonitoring, platform, outputDir, parseableMessages, slangOutputDir, packageName, noProyekIve, noEmbedArt, devicesAsThreads, genSbtMill, slangAuxCodeDirs, slangOutputCDir, excludeComponentImpl, bitWidth, maxStringSize, maxArraySize, runTranspiler, sel4OutputDir, sel4AuxCodeDirs, workspaceRootDir, strictAadlMode, ros2OutputWorkspaceDir, ros2Dir, ros2NodesLanguage, ros2LaunchLanguage, invertTopicBinding, experimentalOptions))
  }

  def parseSireumHamrSysmlConfigThemeH(arg: String): Option[SireumHamrSysmlConfigTheme.Type] = {
    arg.native match {
      case "dark" => return Some(SireumHamrSysmlConfigTheme.Dark)
      case "light" => return Some(SireumHamrSysmlConfigTheme.Light)
      case s =>
        eprintln(s"Expecting one of the following: { dark, light }, but found '$s'.")
        return None()
    }
  }

  def parseSireumHamrSysmlConfigTheme(args: ISZ[String], i: Z): Option[SireumHamrSysmlConfigTheme.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { dark, light }, but none found.")
      return None()
    }
    val r = parseSireumHamrSysmlConfigThemeH(args(i))
    return r
  }

  def parseSireumHamrSysmlConfig(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum HAMR SysML v2 CodeGen Config
          |
          |Usage: <option>* <sysmlv2-file>
          |
          |Available Options:
          |-t, --theme              Form color theme (expects one of { dark, light };
          |                           default: dark)
          |-h, --help               Display this information""".render

    var theme: SireumHamrSysmlConfigTheme.Type = SireumHamrSysmlConfigTheme.Dark
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-t" || arg == "--theme") {
           val o: Option[SireumHamrSysmlConfigTheme.Type] = parseSireumHamrSysmlConfigTheme(args, j + 1)
           o match {
             case Some(v) => theme = v
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
    return Some(SireumHamrSysmlConfigOption(help, parseArguments(args, j), theme))
  }

  def parseSireumHamrSysmlLogikaFPRoundingModeH(arg: String): Option[SireumHamrSysmlLogikaFPRoundingMode.Type] = {
    arg.native match {
      case "NearestTiesToEven" => return Some(SireumHamrSysmlLogikaFPRoundingMode.NearestTiesToEven)
      case "NearestTiesToAway" => return Some(SireumHamrSysmlLogikaFPRoundingMode.NearestTiesToAway)
      case "TowardPositive" => return Some(SireumHamrSysmlLogikaFPRoundingMode.TowardPositive)
      case "TowardNegative" => return Some(SireumHamrSysmlLogikaFPRoundingMode.TowardNegative)
      case "TowardZero" => return Some(SireumHamrSysmlLogikaFPRoundingMode.TowardZero)
      case s =>
        eprintln(s"Expecting one of the following: { NearestTiesToEven, NearestTiesToAway, TowardPositive, TowardNegative, TowardZero }, but found '$s'.")
        return None()
    }
  }

  def parseSireumHamrSysmlLogikaFPRoundingMode(args: ISZ[String], i: Z): Option[SireumHamrSysmlLogikaFPRoundingMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { NearestTiesToEven, NearestTiesToAway, TowardPositive, TowardNegative, TowardZero }, but none found.")
      return None()
    }
    val r = parseSireumHamrSysmlLogikaFPRoundingModeH(args(i))
    return r
  }

  def parseSireumHamrSysmlLogikaStrictPureModeH(arg: String): Option[SireumHamrSysmlLogikaStrictPureMode.Type] = {
    arg.native match {
      case "default" => return Some(SireumHamrSysmlLogikaStrictPureMode.Default)
      case "flip" => return Some(SireumHamrSysmlLogikaStrictPureMode.Flip)
      case "uninterpreted" => return Some(SireumHamrSysmlLogikaStrictPureMode.Uninterpreted)
      case s =>
        eprintln(s"Expecting one of the following: { default, flip, uninterpreted }, but found '$s'.")
        return None()
    }
  }

  def parseSireumHamrSysmlLogikaStrictPureMode(args: ISZ[String], i: Z): Option[SireumHamrSysmlLogikaStrictPureMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { default, flip, uninterpreted }, but none found.")
      return None()
    }
    val r = parseSireumHamrSysmlLogikaStrictPureModeH(args(i))
    return r
  }

  def parseSireumHamrSysmlLogika(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum HAMR SysML v2 Logika Verifier
          |
          |Usage: <option>* <sysmlv2-file>*
          |
          |Available Options:
          |    --integration-feedback-only
          |                          Only output integration feedback
          |    --exclude            Sourcepath exclusion as URI segment (expects a string
          |                           separated by ",")
          |    --feedback           Feedback output directory (expects a path)
          |    --sourcepath         Source paths of SysML v2 .sysml files (expects path
          |                           strings)
          |    --parseable-messages Print parseable file messages
          |-h, --help               Display this information
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
          |    --interprocedural    Enable inter-procedural verification on
          |                           non-strict-pure methods
          |    --interprocedural-contracts
          |                          Use contracts in inter-procedural verification
          |    --strictpure-mode    Strict-pure method treatment mode in
          |                           compositional/interprocedural verification (expects
          |                           one of { default, flip, uninterpreted }; default:
          |                           default)
          |    --line               Focus verification to the specified program line
          |                           number (expects an integer; min is 0; default is 0)
          |    --loop-bound         Loop bound for inter-procedural verification (expects
          |                           an integer; min is 1; default is 3)
          |    --recursive-bound    Recursive call-chain bound for inter-procedural
          |                           verification (expects an integer; min is 1; default
          |                           is 3)
          |    --pattern-inexhaustive
          |                          Disable pattern exhaustiveness checking
          |    --pure-proof-fun     Always add proof functions for pure methods
          |    --sat                Enable assumption satisfiability checking
          |    --skip-methods       Skip checking methods with the specified
          |                           fully-qualified names or identifiers (expects a
          |                           string separated by ",")
          |    --skip-types         Skip checking traits, classes, and objects with the
          |                           specified fully-qualified names or identifiers
          |                           (expects a string separated by ",")
          |
          |Logging Options:
          |    --log-pc             Display path conditions before each statement
          |    --log-pc-lines       Display At(...) path condition line numbers and unique
          |                           symbolic value numbering
          |    --log-raw-pc         Display raw path conditions before each statement
          |    --log-vc             Display all verification conditions
          |    --log-vc-dir         Write all verification conditions in a directory
          |                           (expects a path)
          |    --log-detailed-info  Display detailed feedback information
          |    --log-rewrite-at     Disable At(...) rewriting as In(...)/Old(...) in
          |                           symexe mode
          |    --stats              Collect verification statistics
          |
          |Optimizations Options:
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --par-branch         Enable branch parallelization
          |    --par-branch-return  Only use branch parallelization if all branches return
          |    --par-branch-pred-num
          |                          Branch parallelization prediction minimum number of
          |                           branches (expects an integer; min is 3; default is
          |                           2)
          |    --par-branch-pred-complexity
          |                          Branch parallelization prediction statement
          |                           complexity (expects an integer; min is 0; default is
          |                           16)
          |    --par-rw             Enable rewriting parallelization
          |
          |Path Splitting Options:
          |    --dont-split-pfq     Do not force splitting in quantifiers and proof
          |                           functions derived from @strictpure methods
          |    --split-all          Split all
          |    --split-contract     Split on contract cases
          |    --split-if           Split on if-conditional expressions and statements
          |    --split-match        Split on match expressions and statements
          |
          |Rewriting Options:
          |    --rw-max             Maximum number of rewriting (expects an integer; min
          |                           is 1; default is 100)
          |    --rw-trace           Disable rewriting trace
          |    --rw-eval-trace      Disable evaluation rewriting trace
          |
          |SMT2 Options:
          |    --elide-encoding     Strip out SMT2 encoding in feedback
          |    --raw-inscription    Use raw sequent/sat preamble inscription
          |    --rlimit             SMT2 solver resource limit (expects an integer; min is
          |                           0; default is 2000000)
          |    --smt2-seq           Disable SMT2 solvers parallelization
          |    --simplify           Simplify SMT2 query (experimental)
          |    --solver-sat         SMT2 configurations for satisfiability queries
          |                           (expects a string; default is "z3")
          |    --solver-valid       SMT2 configurations for validity queries (expects a
          |                           string; default is "cvc4,--full-saturate-quant; z3;
          |                           cvc5,--full-saturate-quant")
          |    --sat-timeout        Use validity checking timeout for satisfiability
          |                           checking (otherwise: 500ms)
          |-t, --timeout            Timeout (seconds) for validity checking (expects an
          |                           integer; min is 1; default is 2)
          |    --search-pc          Search path conditions first before employing SMT2
          |                           solvers when discharging VCs""".render

    var integrationOnly: B = false
    var exclude: ISZ[String] = ISZ[String]()
    var feedback: Option[String] = None[String]()
    var sourcepath: ISZ[String] = ISZ[String]()
    var parseableMessages: B = false
    var charBitWidth: Z = 32
    var fpRounding: SireumHamrSysmlLogikaFPRoundingMode.Type = SireumHamrSysmlLogikaFPRoundingMode.NearestTiesToEven
    var useReal: B = false
    var intBitWidth: Z = 0
    var interprocedural: B = false
    var interproceduralContracts: B = false
    var strictPureMode: SireumHamrSysmlLogikaStrictPureMode.Type = SireumHamrSysmlLogikaStrictPureMode.Default
    var line: Z = 0
    var loopBound: Z = 3
    var callBound: Z = 3
    var patternExhaustive: B = true
    var pureFun: B = false
    var sat: B = false
    var skipMethods: ISZ[String] = ISZ[String]()
    var skipTypes: ISZ[String] = ISZ[String]()
    var logPc: B = false
    var logPcLines: B = false
    var logRawPc: B = false
    var logVc: B = false
    var logVcDir: Option[String] = None[String]()
    var logDetailedInfo: B = false
    var logAtRewrite: B = true
    var stats: B = false
    var par: Option[Z] = None()
    var branchPar: B = false
    var branchParReturn: B = false
    var branchPredNum: Z = 2
    var branchPredComplexity: Z = 16
    var rwPar: B = true
    var dontSplitFunQuant: B = false
    var splitAll: B = false
    var splitContract: B = false
    var splitIf: B = false
    var splitMatch: B = false
    var rwMax: Z = 100
    var rwTrace: B = true
    var rwEvalTrace: B = true
    var elideEncoding: B = false
    var rawInscription: B = false
    var rlimit: Z = 2000000
    var sequential: B = false
    var simplify: B = false
    var smt2SatConfigs: Option[String] = Some("z3")
    var smt2ValidConfigs: Option[String] = Some("cvc4,--full-saturate-quant; z3; cvc5,--full-saturate-quant")
    var satTimeout: B = false
    var timeout: Z = 2
    var searchPC: B = false
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--integration-feedback-only") {
           val o: Option[B] = { j = j - 1; Some(!integrationOnly) }
           o match {
             case Some(v) => integrationOnly = v
             case _ => return None()
           }
         } else if (arg == "--exclude") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => exclude = v
             case _ => return None()
           }
         } else if (arg == "--feedback") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => feedback = v
             case _ => return None()
           }
         } else if (arg == "--sourcepath") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => sourcepath = v
             case _ => return None()
           }
         } else if (arg == "--parseable-messages") {
           val o: Option[B] = { j = j - 1; Some(!parseableMessages) }
           o match {
             case Some(v) => parseableMessages = v
             case _ => return None()
           }
         } else if (arg == "--c-bitwidth") {
           val o: Option[Z] = parseNum(args, j + 1, None(), None())
           o match {
             case Some(v) => charBitWidth = v
             case _ => return None()
           }
         } else if (arg == "--fp-rounding") {
           val o: Option[SireumHamrSysmlLogikaFPRoundingMode.Type] = parseSireumHamrSysmlLogikaFPRoundingMode(args, j + 1)
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
         } else if (arg == "--interprocedural-contracts") {
           val o: Option[B] = { j = j - 1; Some(!interproceduralContracts) }
           o match {
             case Some(v) => interproceduralContracts = v
             case _ => return None()
           }
         } else if (arg == "--strictpure-mode") {
           val o: Option[SireumHamrSysmlLogikaStrictPureMode.Type] = parseSireumHamrSysmlLogikaStrictPureMode(args, j + 1)
           o match {
             case Some(v) => strictPureMode = v
             case _ => return None()
           }
         } else if (arg == "--line") {
           val o: Option[Z] = parseNum(args, j + 1, Some(0), None())
           o match {
             case Some(v) => line = v
             case _ => return None()
           }
         } else if (arg == "--loop-bound") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => loopBound = v
             case _ => return None()
           }
         } else if (arg == "--recursive-bound") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => callBound = v
             case _ => return None()
           }
         } else if (arg == "--pattern-inexhaustive") {
           val o: Option[B] = { j = j - 1; Some(!patternExhaustive) }
           o match {
             case Some(v) => patternExhaustive = v
             case _ => return None()
           }
         } else if (arg == "--pure-proof-fun") {
           val o: Option[B] = { j = j - 1; Some(!pureFun) }
           o match {
             case Some(v) => pureFun = v
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
         } else if (arg == "--log-detailed-info") {
           val o: Option[B] = { j = j - 1; Some(!logDetailedInfo) }
           o match {
             case Some(v) => logDetailedInfo = v
             case _ => return None()
           }
         } else if (arg == "--log-rewrite-at") {
           val o: Option[B] = { j = j - 1; Some(!logAtRewrite) }
           o match {
             case Some(v) => logAtRewrite = v
             case _ => return None()
           }
         } else if (arg == "--stats") {
           val o: Option[B] = { j = j - 1; Some(!stats) }
           o match {
             case Some(v) => stats = v
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
         } else if (arg == "--par-branch") {
           val o: Option[B] = { j = j - 1; Some(!branchPar) }
           o match {
             case Some(v) => branchPar = v
             case _ => return None()
           }
         } else if (arg == "--par-branch-return") {
           val o: Option[B] = { j = j - 1; Some(!branchParReturn) }
           o match {
             case Some(v) => branchParReturn = v
             case _ => return None()
           }
         } else if (arg == "--par-branch-pred-num") {
           val o: Option[Z] = parseNum(args, j + 1, Some(3), None())
           o match {
             case Some(v) => branchPredNum = v
             case _ => return None()
           }
         } else if (arg == "--par-branch-pred-complexity") {
           val o: Option[Z] = parseNum(args, j + 1, Some(0), None())
           o match {
             case Some(v) => branchPredComplexity = v
             case _ => return None()
           }
         } else if (arg == "--par-rw") {
           val o: Option[B] = { j = j - 1; Some(!rwPar) }
           o match {
             case Some(v) => rwPar = v
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
         } else if (arg == "--rw-max") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => rwMax = v
             case _ => return None()
           }
         } else if (arg == "--rw-trace") {
           val o: Option[B] = { j = j - 1; Some(!rwTrace) }
           o match {
             case Some(v) => rwTrace = v
             case _ => return None()
           }
         } else if (arg == "--rw-eval-trace") {
           val o: Option[B] = { j = j - 1; Some(!rwEvalTrace) }
           o match {
             case Some(v) => rwEvalTrace = v
             case _ => return None()
           }
         } else if (arg == "--elide-encoding") {
           val o: Option[B] = { j = j - 1; Some(!elideEncoding) }
           o match {
             case Some(v) => elideEncoding = v
             case _ => return None()
           }
         } else if (arg == "--raw-inscription") {
           val o: Option[B] = { j = j - 1; Some(!rawInscription) }
           o match {
             case Some(v) => rawInscription = v
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
         } else if (arg == "--sat-timeout") {
           val o: Option[B] = { j = j - 1; Some(!satTimeout) }
           o match {
             case Some(v) => satTimeout = v
             case _ => return None()
           }
         } else if (arg == "-t" || arg == "--timeout") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => timeout = v
             case _ => return None()
           }
         } else if (arg == "--search-pc") {
           val o: Option[B] = { j = j - 1; Some(!searchPC) }
           o match {
             case Some(v) => searchPC = v
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
    return Some(SireumHamrSysmlLogikaOption(help, parseArguments(args, j), integrationOnly, exclude, feedback, sourcepath, parseableMessages, charBitWidth, fpRounding, useReal, intBitWidth, interprocedural, interproceduralContracts, strictPureMode, line, loopBound, callBound, patternExhaustive, pureFun, sat, skipMethods, skipTypes, logPc, logPcLines, logRawPc, logVc, logVcDir, logDetailedInfo, logAtRewrite, stats, par, branchPar, branchParReturn, branchPredNum, branchPredComplexity, rwPar, dontSplitFunQuant, splitAll, splitContract, splitIf, splitMatch, rwMax, rwTrace, rwEvalTrace, elideEncoding, rawInscription, rlimit, sequential, simplify, smt2SatConfigs, smt2ValidConfigs, satTimeout, timeout, searchPC))
  }

  def parseSireumHamrSysmlTipe(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum HAMR SysML v2 Type Checker
          |
          |Usage: <option>* [<sysmlv2-file>]
          |
          |Available Options:
          |-x, --exclude            Sourcepath exclusion as URI segment (expects a string
          |                           separated by ",")
          |-s, --sourcepath         Source paths of SysML v2 .sysml files (expects path
          |                           strings)
          |    --parseable-messages Print parseable file messages
          |-h, --help               Display this information""".render

    var exclude: ISZ[String] = ISZ[String]()
    var sourcepath: ISZ[String] = ISZ[String]()
    var parseableMessages: B = false
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
         } else if (arg == "-s" || arg == "--sourcepath") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => sourcepath = v
             case _ => return None()
           }
         } else if (arg == "--parseable-messages") {
           val o: Option[B] = { j = j - 1; Some(!parseableMessages) }
           o match {
             case Some(v) => parseableMessages = v
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
    return Some(SireumHamrSysmlTipeOption(help, parseArguments(args, j), exclude, sourcepath, parseableMessages))
  }

  def parseSireumHamrSysmlTranslator(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum HAMR SysML v2 Grammar Translator
          |
          |Usage: <option>* <output>
          |
          |Available Options:
          |-v, --version            SysML v2 grammar version (expects a string; default is
          |                           "2025-12")
          |-g, --grammar            File containing an ANTLR v3 grammar (expects a string)
          |-u, --url                URL of an ANTLR v3 grammar (%version is replaced with
          |                           --version option, if any) (expects a string)
          |-k, --keywords           Strings that should be treated as keywords rather than
          |                           operators (expects a string separated by ",")
          |-h, --help               Display this information""".render

    var version: Option[String] = Some("2025-12")
    var grammar: Option[String] = None[String]()
    var url: Option[String] = None[String]()
    var keywords: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-v" || arg == "--version") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => version = v
             case _ => return None()
           }
         } else if (arg == "-g" || arg == "--grammar") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => grammar = v
             case _ => return None()
           }
         } else if (arg == "-u" || arg == "--url") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => url = v
             case _ => return None()
           }
         } else if (arg == "-k" || arg == "--keywords") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => keywords = v
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
    return Some(SireumHamrSysmlTranslatorOption(help, parseArguments(args, j), version, grammar, url, keywords))
  }

  def parseSireumLogika(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Logika Tools for Slang
            |
            |Available modes:
            |config                   Logika Config
            |verifier                 Logika verifier""".render
      )
      return Some(HelpOption())
    }
    val opt = select("logika", args, i, ISZ("config", "verifier"))
    opt match {
      case Some(string"config") => return parseSireumLogikaConfig(args, i + 1)
      case Some(string"verifier") => return parseSireumLogikaVerifier(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumLogikaConfigThemeH(arg: String): Option[SireumLogikaConfigTheme.Type] = {
    arg.native match {
      case "dark" => return Some(SireumLogikaConfigTheme.Dark)
      case "light" => return Some(SireumLogikaConfigTheme.Light)
      case s =>
        eprintln(s"Expecting one of the following: { dark, light }, but found '$s'.")
        return None()
    }
  }

  def parseSireumLogikaConfigTheme(args: ISZ[String], i: Z): Option[SireumLogikaConfigTheme.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { dark, light }, but none found.")
      return None()
    }
    val r = parseSireumLogikaConfigThemeH(args(i))
    return r
  }

  def parseSireumLogikaConfig(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Logika Config
          |
          |Usage: <option>* <file.sc>
          |
          |Available Options:
          |-t, --theme              Form color theme (expects one of { dark, light };
          |                           default: dark)
          |-h, --help               Display this information""".render

    var theme: SireumLogikaConfigTheme.Type = SireumLogikaConfigTheme.Dark
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-t" || arg == "--theme") {
           val o: Option[SireumLogikaConfigTheme.Type] = parseSireumLogikaConfigTheme(args, j + 1)
           o match {
             case Some(v) => theme = v
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
    return Some(SireumLogikaConfigOption(help, parseArguments(args, j), theme))
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

  def parseSireumLogikaVerifierStrictPureModeH(arg: String): Option[SireumLogikaVerifierStrictPureMode.Type] = {
    arg.native match {
      case "default" => return Some(SireumLogikaVerifierStrictPureMode.Default)
      case "flip" => return Some(SireumLogikaVerifierStrictPureMode.Flip)
      case "uninterpreted" => return Some(SireumLogikaVerifierStrictPureMode.Uninterpreted)
      case s =>
        eprintln(s"Expecting one of the following: { default, flip, uninterpreted }, but found '$s'.")
        return None()
    }
  }

  def parseSireumLogikaVerifierStrictPureMode(args: ISZ[String], i: Z): Option[SireumLogikaVerifierStrictPureMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { default, flip, uninterpreted }, but none found.")
      return None()
    }
    val r = parseSireumLogikaVerifierStrictPureModeH(args(i))
    return r
  }

  def parseSireumLogikaVerifier(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Logika Verifier for Slang
          |
          |Usage: <option>* <slang-file>+
          |
          |Available Options:
          |    --feedback           Feedback output directory (expects a path)
          |-m, --manual             Set verification mode to manual for Slang scripts
          |-r, --no-runtime         Do not use built-in runtime (use runtime in
          |                           sourcepath)
          |    --parseable-messages Print parseable file messages
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
          |    --interprocedural    Enable inter-procedural verification on
          |                           non-strict-pure methods
          |    --interprocedural-contracts
          |                          Use contracts in inter-procedural verification
          |    --strictpure-mode    Strict-pure method treatment mode in
          |                           compositional/interprocedural verification (expects
          |                           one of { default, flip, uninterpreted }; default:
          |                           default)
          |    --line               Focus verification to the specified program line
          |                           number (expects an integer; min is 0; default is 0)
          |    --loop-bound         Loop bound for inter-procedural verification (expects
          |                           an integer; min is 1; default is 3)
          |    --recursive-bound    Recursive call-chain bound for inter-procedural
          |                           verification (expects an integer; min is 1; default
          |                           is 3)
          |    --pattern-inexhaustive
          |                          Disable pattern exhaustiveness checking
          |    --pure-proof-fun     Always add proof functions for pure methods
          |    --sat                Enable assumption satisfiability checking
          |    --skip-methods       Skip checking methods with the specified
          |                           fully-qualified names or identifiers (expects a
          |                           string separated by ",")
          |    --skip-types         Skip checking traits, classes, and objects with the
          |                           specified fully-qualified names or identifiers
          |                           (expects a string separated by ",")
          |
          |Logging Options:
          |    --log-pc             Display path conditions before each statement
          |    --log-pc-lines       Display At(...) path condition line numbers and unique
          |                           symbolic value numbering
          |    --log-raw-pc         Display raw path conditions before each statement
          |    --log-vc             Display all verification conditions
          |    --log-vc-dir         Write all verification conditions in a directory
          |                           (expects a path)
          |    --log-detailed-info  Display detailed feedback information
          |    --log-rewrite-at     Disable At(...) rewriting as In(...)/Old(...) in
          |                           symexe mode
          |    --stats              Collect verification statistics
          |
          |Optimizations Options:
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --par-branch         Enable branch parallelization
          |    --par-branch-return  Only use branch parallelization if all branches return
          |    --par-branch-pred-num
          |                          Branch parallelization prediction minimum number of
          |                           branches (expects an integer; min is 3; default is
          |                           2)
          |    --par-branch-pred-complexity
          |                          Branch parallelization prediction statement
          |                           complexity (expects an integer; min is 0; default is
          |                           16)
          |    --par-rw             Enable rewriting parallelization
          |
          |Path Splitting Options:
          |    --dont-split-pfq     Do not force splitting in quantifiers and proof
          |                           functions derived from @strictpure methods
          |    --split-all          Split all
          |    --split-contract     Split on contract cases
          |    --split-if           Split on if-conditional expressions and statements
          |    --split-match        Split on match expressions and statements
          |
          |Rewriting Options:
          |    --rw-max             Maximum number of rewriting (expects an integer; min
          |                           is 1; default is 100)
          |    --rw-trace           Disable rewriting trace
          |    --rw-eval-trace      Disable evaluation rewriting trace
          |
          |SMT2 Options:
          |    --elide-encoding     Strip out SMT2 encoding in feedback
          |    --raw-inscription    Use raw sequent/sat preamble inscription
          |    --rlimit             SMT2 solver resource limit (expects an integer; min is
          |                           0; default is 2000000)
          |    --smt2-seq           Disable SMT2 solvers parallelization
          |    --simplify           Simplify SMT2 query (experimental)
          |    --solver-sat         SMT2 configurations for satisfiability queries
          |                           (expects a string; default is "z3")
          |    --solver-valid       SMT2 configurations for validity queries (expects a
          |                           string; default is "cvc4,--full-saturate-quant; z3;
          |                           cvc5,--full-saturate-quant")
          |    --sat-timeout        Use validity checking timeout for satisfiability
          |                           checking (otherwise: 500ms)
          |-t, --timeout            Timeout (seconds) for validity checking (expects an
          |                           integer; min is 1; default is 2)
          |    --search-pc          Search path conditions first before employing SMT2
          |                           solvers when discharging VCs""".render

    var feedback: Option[String] = None[String]()
    var manual: B = false
    var noRuntime: B = false
    var parseableMessages: B = false
    var sourcepath: ISZ[String] = ISZ[String]()
    var infoFlow: B = false
    var charBitWidth: Z = 32
    var fpRounding: SireumLogikaVerifierFPRoundingMode.Type = SireumLogikaVerifierFPRoundingMode.NearestTiesToEven
    var useReal: B = false
    var intBitWidth: Z = 0
    var interprocedural: B = false
    var interproceduralContracts: B = false
    var strictPureMode: SireumLogikaVerifierStrictPureMode.Type = SireumLogikaVerifierStrictPureMode.Default
    var line: Z = 0
    var loopBound: Z = 3
    var callBound: Z = 3
    var patternExhaustive: B = true
    var pureFun: B = false
    var sat: B = false
    var skipMethods: ISZ[String] = ISZ[String]()
    var skipTypes: ISZ[String] = ISZ[String]()
    var logPc: B = false
    var logPcLines: B = false
    var logRawPc: B = false
    var logVc: B = false
    var logVcDir: Option[String] = None[String]()
    var logDetailedInfo: B = false
    var logAtRewrite: B = true
    var stats: B = false
    var par: Option[Z] = None()
    var branchPar: B = false
    var branchParReturn: B = false
    var branchPredNum: Z = 2
    var branchPredComplexity: Z = 16
    var rwPar: B = true
    var dontSplitFunQuant: B = false
    var splitAll: B = false
    var splitContract: B = false
    var splitIf: B = false
    var splitMatch: B = false
    var rwMax: Z = 100
    var rwTrace: B = true
    var rwEvalTrace: B = true
    var elideEncoding: B = false
    var rawInscription: B = false
    var rlimit: Z = 2000000
    var sequential: B = false
    var simplify: B = false
    var smt2SatConfigs: Option[String] = Some("z3")
    var smt2ValidConfigs: Option[String] = Some("cvc4,--full-saturate-quant; z3; cvc5,--full-saturate-quant")
    var satTimeout: B = false
    var timeout: Z = 2
    var searchPC: B = false
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--feedback") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => feedback = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--manual") {
           val o: Option[B] = { j = j - 1; Some(!manual) }
           o match {
             case Some(v) => manual = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--no-runtime") {
           val o: Option[B] = { j = j - 1; Some(!noRuntime) }
           o match {
             case Some(v) => noRuntime = v
             case _ => return None()
           }
         } else if (arg == "--parseable-messages") {
           val o: Option[B] = { j = j - 1; Some(!parseableMessages) }
           o match {
             case Some(v) => parseableMessages = v
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
         } else if (arg == "--interprocedural-contracts") {
           val o: Option[B] = { j = j - 1; Some(!interproceduralContracts) }
           o match {
             case Some(v) => interproceduralContracts = v
             case _ => return None()
           }
         } else if (arg == "--strictpure-mode") {
           val o: Option[SireumLogikaVerifierStrictPureMode.Type] = parseSireumLogikaVerifierStrictPureMode(args, j + 1)
           o match {
             case Some(v) => strictPureMode = v
             case _ => return None()
           }
         } else if (arg == "--line") {
           val o: Option[Z] = parseNum(args, j + 1, Some(0), None())
           o match {
             case Some(v) => line = v
             case _ => return None()
           }
         } else if (arg == "--loop-bound") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => loopBound = v
             case _ => return None()
           }
         } else if (arg == "--recursive-bound") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => callBound = v
             case _ => return None()
           }
         } else if (arg == "--pattern-inexhaustive") {
           val o: Option[B] = { j = j - 1; Some(!patternExhaustive) }
           o match {
             case Some(v) => patternExhaustive = v
             case _ => return None()
           }
         } else if (arg == "--pure-proof-fun") {
           val o: Option[B] = { j = j - 1; Some(!pureFun) }
           o match {
             case Some(v) => pureFun = v
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
         } else if (arg == "--log-detailed-info") {
           val o: Option[B] = { j = j - 1; Some(!logDetailedInfo) }
           o match {
             case Some(v) => logDetailedInfo = v
             case _ => return None()
           }
         } else if (arg == "--log-rewrite-at") {
           val o: Option[B] = { j = j - 1; Some(!logAtRewrite) }
           o match {
             case Some(v) => logAtRewrite = v
             case _ => return None()
           }
         } else if (arg == "--stats") {
           val o: Option[B] = { j = j - 1; Some(!stats) }
           o match {
             case Some(v) => stats = v
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
         } else if (arg == "--par-branch") {
           val o: Option[B] = { j = j - 1; Some(!branchPar) }
           o match {
             case Some(v) => branchPar = v
             case _ => return None()
           }
         } else if (arg == "--par-branch-return") {
           val o: Option[B] = { j = j - 1; Some(!branchParReturn) }
           o match {
             case Some(v) => branchParReturn = v
             case _ => return None()
           }
         } else if (arg == "--par-branch-pred-num") {
           val o: Option[Z] = parseNum(args, j + 1, Some(3), None())
           o match {
             case Some(v) => branchPredNum = v
             case _ => return None()
           }
         } else if (arg == "--par-branch-pred-complexity") {
           val o: Option[Z] = parseNum(args, j + 1, Some(0), None())
           o match {
             case Some(v) => branchPredComplexity = v
             case _ => return None()
           }
         } else if (arg == "--par-rw") {
           val o: Option[B] = { j = j - 1; Some(!rwPar) }
           o match {
             case Some(v) => rwPar = v
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
         } else if (arg == "--rw-max") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => rwMax = v
             case _ => return None()
           }
         } else if (arg == "--rw-trace") {
           val o: Option[B] = { j = j - 1; Some(!rwTrace) }
           o match {
             case Some(v) => rwTrace = v
             case _ => return None()
           }
         } else if (arg == "--rw-eval-trace") {
           val o: Option[B] = { j = j - 1; Some(!rwEvalTrace) }
           o match {
             case Some(v) => rwEvalTrace = v
             case _ => return None()
           }
         } else if (arg == "--elide-encoding") {
           val o: Option[B] = { j = j - 1; Some(!elideEncoding) }
           o match {
             case Some(v) => elideEncoding = v
             case _ => return None()
           }
         } else if (arg == "--raw-inscription") {
           val o: Option[B] = { j = j - 1; Some(!rawInscription) }
           o match {
             case Some(v) => rawInscription = v
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
         } else if (arg == "--sat-timeout") {
           val o: Option[B] = { j = j - 1; Some(!satTimeout) }
           o match {
             case Some(v) => satTimeout = v
             case _ => return None()
           }
         } else if (arg == "-t" || arg == "--timeout") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => timeout = v
             case _ => return None()
           }
         } else if (arg == "--search-pc") {
           val o: Option[B] = { j = j - 1; Some(!searchPC) }
           o match {
             case Some(v) => searchPC = v
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
    return Some(SireumLogikaVerifierOption(help, parseArguments(args, j), feedback, manual, noRuntime, parseableMessages, sourcepath, infoFlow, charBitWidth, fpRounding, useReal, intBitWidth, interprocedural, interproceduralContracts, strictPureMode, line, loopBound, callBound, patternExhaustive, pureFun, sat, skipMethods, skipTypes, logPc, logPcLines, logRawPc, logVc, logVcDir, logDetailedInfo, logAtRewrite, stats, par, branchPar, branchParReturn, branchPredNum, branchPredComplexity, rwPar, dontSplitFunQuant, splitAll, splitContract, splitIf, splitMatch, rwMax, rwTrace, rwEvalTrace, elideEncoding, rawInscription, rlimit, sequential, simplify, smt2SatConfigs, smt2ValidConfigs, satTimeout, timeout, searchPC))
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
      case Some(string"gen") => return parseSireumParserGen(args, i + 1)
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
            |dep                      Sireum proyek Ivy dependency visualizer
            |export                   Sireum proyek exporter
            |ive                      Sireum IVE proyek generator
            |logika                   Sireum Logika for Proyek
            |publish                  Proyek publisher
            |reflect                  Slang proyek reflection generator
            |run                      Proyek program runner
            |slangcheck               Slang Check generator
            |stats                    Proyek statistics reporter
            |test                     Proyek test runner
            |tipe                     Slang proyek type checker""".render
      )
      return Some(HelpOption())
    }
    val opt = select("proyek", args, i, ISZ("assemble", "compile", "dep", "export", "ive", "logika", "publish", "reflect", "run", "slangcheck", "stats", "test", "tipe"))
    opt match {
      case Some(string"assemble") => return parseSireumProyekAssemble(args, i + 1)
      case Some(string"compile") => return parseSireumProyekCompile(args, i + 1)
      case Some(string"dep") => return parseSireumProyekDep(args, i + 1)
      case Some(string"export") => return parseSireumProyekExport(args, i + 1)
      case Some(string"ive") => return parseSireumProyekIve(args, i + 1)
      case Some(string"logika") => return parseSireumProyekLogika(args, i + 1)
      case Some(string"publish") => return parseSireumProyekPublish(args, i + 1)
      case Some(string"reflect") => return parseSireumProyekReflect(args, i + 1)
      case Some(string"run") => return parseSireumProyekRun(args, i + 1)
      case Some(string"slangcheck") => return parseSireumProyekSlangcheck(args, i + 1)
      case Some(string"stats") => return parseSireumProyekStats(args, i + 1)
      case Some(string"test") => return parseSireumProyekTest(args, i + 1)
      case Some(string"tipe") => return parseSireumProyekTipe(args, i + 1)
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
          |    --exclude-jar-deps   Exclude .jar Ivy (format:
          |                           <org-prefix>:<module-prefix>) dependencies (expects
          |                           a string separated by ",")
          |    --include-sources    Include source files
          |    --include-tests      Include test classes
          |-j, --jar                The assembled jar filename (defaults to the project
          |                           name) (expects a string)
          |    --no-deps            Exclude library dependencies
          |-m, --main               The main class fully qualified name (expects a string)
          |    --meta               Generate Scalameta semanticdb
          |    --native             Generates native image
          |    --native-script      Generates native image script
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
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project-standalone.cmd,
          |                           or <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
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
          |                           default is "-source, 17, -target, 17, -encoding,
          |                           utf8, -XDignore.symbol.file, -Xlint:-options,
          |                           -Xlint:deprecation, -proc:none")
          |-f, --fresh              Fresh compilation from a clean slate
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --recompile          Module IDs to force recompilation on (expects a string
          |                           separated by ",")
          |    --scalac             Scalac options (expects a string separated by ",";
          |                           default is "-release, 17, -deprecation, -Yrangepos,
          |                           -Ydelambdafy:method, -feature, -unchecked, -Werror,
          |                           -language:postfixOps")
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
          |    --proxy-host         Proxy host for ivy resolution (expects a string)
          |    --proxy-non-hosts    Proxy non-hosts for ivy resolution (expects a string)
          |    --proxy-port         Proxy port for ivy resolution (expects a string)
          |    --proxy-protocol     Proxy protocol for ivy resolution (expects a string)
          |    --proxy-user-env     Proxy user environment variable for ivy resolution
          |                           (expects a string)
          |    --proxy-passwd-env   Proxy password environment variable for ivy resolution
          |                           (expects a string)
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")""".render

    var excludeJarDeps: ISZ[String] = ISZ[String]()
    var includeSources: B = false
    var includeTests: B = false
    var jar: Option[String] = None[String]()
    var noDeps: B = false
    var mainClass: Option[String] = None[String]()
    var meta: B = false
    var isNative: B = false
    var isNativeScript: B = false
    var uber: B = false
    var ignoreRuntime: B = false
    var json: Option[String] = None[String]()
    var name: Option[String] = None[String]()
    var outputDirName: Option[String] = Some("out")
    var project: Option[String] = None[String]()
    var slice: ISZ[String] = ISZ[String]()
    var symlink: B = false
    var versions: ISZ[String] = ISZ[String]()
    var javac: ISZ[String] = ISZ("-source", "17", "-target", "17", "-encoding", "utf8", "-XDignore.symbol.file", "-Xlint:-options", "-Xlint:deprecation", "-proc:none")
    var fresh: B = false
    var par: Option[Z] = None()
    var recompile: ISZ[String] = ISZ[String]()
    var scalac: ISZ[String] = ISZ("-release", "17", "-deprecation", "-Yrangepos", "-Ydelambdafy:method", "-feature", "-unchecked", "-Werror", "-language:postfixOps")
    var sha3: B = false
    var skipCompile: B = false
    var cache: Option[String] = None[String]()
    var docs: B = true
    var sources: B = true
    var proxyHost: Option[String] = None[String]()
    var proxyNonHosts: Option[String] = None[String]()
    var proxyPort: Option[String] = None[String]()
    var proxyProtocol: Option[String] = None[String]()
    var proxyUser: Option[String] = None[String]()
    var proxyPassword: Option[String] = None[String]()
    var repositories: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--exclude-jar-deps") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => excludeJarDeps = v
             case _ => return None()
           }
         } else if (arg == "--include-sources") {
           val o: Option[B] = { j = j - 1; Some(!includeSources) }
           o match {
             case Some(v) => includeSources = v
             case _ => return None()
           }
         } else if (arg == "--include-tests") {
           val o: Option[B] = { j = j - 1; Some(!includeTests) }
           o match {
             case Some(v) => includeTests = v
             case _ => return None()
           }
         } else if (arg == "-j" || arg == "--jar") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => jar = v
             case _ => return None()
           }
         } else if (arg == "--no-deps") {
           val o: Option[B] = { j = j - 1; Some(!noDeps) }
           o match {
             case Some(v) => noDeps = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--main") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => mainClass = v
             case _ => return None()
           }
         } else if (arg == "--meta") {
           val o: Option[B] = { j = j - 1; Some(!meta) }
           o match {
             case Some(v) => meta = v
             case _ => return None()
           }
         } else if (arg == "--native") {
           val o: Option[B] = { j = j - 1; Some(!isNative) }
           o match {
             case Some(v) => isNative = v
             case _ => return None()
           }
         } else if (arg == "--native-script") {
           val o: Option[B] = { j = j - 1; Some(!isNativeScript) }
           o match {
             case Some(v) => isNativeScript = v
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
         } else if (arg == "--proxy-host") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyHost = v
             case _ => return None()
           }
         } else if (arg == "--proxy-non-hosts") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyNonHosts = v
             case _ => return None()
           }
         } else if (arg == "--proxy-port") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPort = v
             case _ => return None()
           }
         } else if (arg == "--proxy-protocol") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyProtocol = v
             case _ => return None()
           }
         } else if (arg == "--proxy-user-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyUser = v
             case _ => return None()
           }
         } else if (arg == "--proxy-passwd-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPassword = v
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
    return Some(SireumProyekAssembleOption(help, parseArguments(args, j), excludeJarDeps, includeSources, includeTests, jar, noDeps, mainClass, meta, isNative, isNativeScript, uber, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, javac, fresh, par, recompile, scalac, sha3, skipCompile, cache, docs, sources, proxyHost, proxyNonHosts, proxyPort, proxyProtocol, proxyUser, proxyPassword, repositories))
  }

  def parseSireumProyekCompile(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Proyek Compiler
          |
          |Usage: <options>* <dir>
          |
          |Available Options:
          |    --javac              Javac options (expects a string separated by ",";
          |                           default is "-source, 17, -target, 17, -encoding,
          |                           utf8, -XDignore.symbol.file, -Xlint:-options,
          |                           -Xlint:deprecation, -proc:none")
          |-f, --fresh              Fresh compilation from a clean slate
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --recompile          Module IDs to force recompilation on (expects a string
          |                           separated by ",")
          |    --scalac             Scalac options (expects a string separated by ",";
          |                           default is "-release, 17, -deprecation, -Yrangepos,
          |                           -Ydelambdafy:method, -feature, -unchecked, -Werror,
          |                           -language:postfixOps")
          |    --sha3               Use SHA3 instead of time stamp for detecting file
          |                           changes
          |    --js                 Compile using Scala.js
          |    --meta               Generate Scalameta semanticdb
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
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project-standalone.cmd,
          |                           or <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
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
          |    --proxy-host         Proxy host for ivy resolution (expects a string)
          |    --proxy-non-hosts    Proxy non-hosts for ivy resolution (expects a string)
          |    --proxy-port         Proxy port for ivy resolution (expects a string)
          |    --proxy-protocol     Proxy protocol for ivy resolution (expects a string)
          |    --proxy-user-env     Proxy user environment variable for ivy resolution
          |                           (expects a string)
          |    --proxy-passwd-env   Proxy password environment variable for ivy resolution
          |                           (expects a string)
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")""".render

    var javac: ISZ[String] = ISZ("-source", "17", "-target", "17", "-encoding", "utf8", "-XDignore.symbol.file", "-Xlint:-options", "-Xlint:deprecation", "-proc:none")
    var fresh: B = false
    var par: Option[Z] = None()
    var recompile: ISZ[String] = ISZ[String]()
    var scalac: ISZ[String] = ISZ("-release", "17", "-deprecation", "-Yrangepos", "-Ydelambdafy:method", "-feature", "-unchecked", "-Werror", "-language:postfixOps")
    var sha3: B = false
    var js: B = false
    var meta: B = false
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
    var proxyHost: Option[String] = None[String]()
    var proxyNonHosts: Option[String] = None[String]()
    var proxyPort: Option[String] = None[String]()
    var proxyProtocol: Option[String] = None[String]()
    var proxyUser: Option[String] = None[String]()
    var proxyPassword: Option[String] = None[String]()
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
         } else if (arg == "--meta") {
           val o: Option[B] = { j = j - 1; Some(!meta) }
           o match {
             case Some(v) => meta = v
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
         } else if (arg == "--proxy-host") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyHost = v
             case _ => return None()
           }
         } else if (arg == "--proxy-non-hosts") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyNonHosts = v
             case _ => return None()
           }
         } else if (arg == "--proxy-port") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPort = v
             case _ => return None()
           }
         } else if (arg == "--proxy-protocol") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyProtocol = v
             case _ => return None()
           }
         } else if (arg == "--proxy-user-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyUser = v
             case _ => return None()
           }
         } else if (arg == "--proxy-passwd-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPassword = v
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
    return Some(SireumProyekCompileOption(help, parseArguments(args, j), javac, fresh, par, recompile, scalac, sha3, js, meta, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, cache, docs, sources, proxyHost, proxyNonHosts, proxyPort, proxyProtocol, proxyUser, proxyPassword, repositories))
  }

  def parseSireumProyekDep(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Proyek Ivy Dependency Visualizer
          |
          |Usage: <options>* <dir>
          |
          |Available Options:
          |    --js                 Scala.js dependency
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
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project-standalone.cmd,
          |                           or <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
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
          |    --proxy-host         Proxy host for ivy resolution (expects a string)
          |    --proxy-non-hosts    Proxy non-hosts for ivy resolution (expects a string)
          |    --proxy-port         Proxy port for ivy resolution (expects a string)
          |    --proxy-protocol     Proxy protocol for ivy resolution (expects a string)
          |    --proxy-user-env     Proxy user environment variable for ivy resolution
          |                           (expects a string)
          |    --proxy-passwd-env   Proxy password environment variable for ivy resolution
          |                           (expects a string)
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")""".render

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
    var proxyHost: Option[String] = None[String]()
    var proxyNonHosts: Option[String] = None[String]()
    var proxyPort: Option[String] = None[String]()
    var proxyProtocol: Option[String] = None[String]()
    var proxyUser: Option[String] = None[String]()
    var proxyPassword: Option[String] = None[String]()
    var repositories: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
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
         } else if (arg == "--proxy-host") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyHost = v
             case _ => return None()
           }
         } else if (arg == "--proxy-non-hosts") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyNonHosts = v
             case _ => return None()
           }
         } else if (arg == "--proxy-port") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPort = v
             case _ => return None()
           }
         } else if (arg == "--proxy-protocol") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyProtocol = v
             case _ => return None()
           }
         } else if (arg == "--proxy-user-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyUser = v
             case _ => return None()
           }
         } else if (arg == "--proxy-passwd-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPassword = v
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
    return Some(SireumProyekDepOption(help, parseArguments(args, j), js, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, cache, docs, sources, proxyHost, proxyNonHosts, proxyPort, proxyProtocol, proxyUser, proxyPassword, repositories))
  }

  def parseSireumProyekExportBuildToolH(arg: String): Option[SireumProyekExportBuildTool.Type] = {
    arg.native match {
      case "bloop" => return Some(SireumProyekExportBuildTool.Bloop)
      case "mill" => return Some(SireumProyekExportBuildTool.Mill)
      case s =>
        eprintln(s"Expecting one of the following: { bloop, mill }, but found '$s'.")
        return None()
    }
  }

  def parseSireumProyekExportBuildTool(args: ISZ[String], i: Z): Option[SireumProyekExportBuildTool.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { bloop, mill }, but none found.")
      return None()
    }
    val r = parseSireumProyekExportBuildToolH(args(i))
    return r
  }

  def parseSireumProyekExportBuildTools(args: ISZ[String], i: Z): Option[ISZ[SireumProyekExportBuildTool.Type]] = {
    val tokensOpt = tokenize(args, i, "SireumProyekExportBuildTool", ',', T)
    if (tokensOpt.isEmpty) {
      return None()
    }
    var r = ISZ[SireumProyekExportBuildTool.Type]()
    for (token <- tokensOpt.get) {
      val e = parseSireumProyekExportBuildToolH(token)
      e match {
        case Some(v) => r = r :+ v
        case _ => return None()
      }
    }
    return Some(r)
  }

  def parseSireumProyekExport(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Proyek Exporter
          |
          |Usage: <options>* <dir>
          |
          |Available Options:
          |    --javac              Javac options (expects a string separated by ",";
          |                           default is "-source, 17, -target, 17, -encoding,
          |                           utf8, -XDignore.symbol.file, -Xlint:-options,
          |                           -Xlint:deprecation, -proc:none")
          |-f, --fresh              Fresh compilation from a clean slate
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --recompile          Module IDs to force recompilation on (expects a string
          |                           separated by ",")
          |    --scalac             Scalac options (expects a string separated by ",";
          |                           default is "-release, 17, -deprecation, -Yrangepos,
          |                           -Ydelambdafy:method, -feature, -unchecked, -Werror,
          |                           -language:postfixOps")
          |    --sha3               Use SHA3 instead of time stamp for detecting file
          |                           changes
          |    --target             Build tool target (expects one or more of { bloop,
          |                           mill }; default: bloop)
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
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project-standalone.cmd,
          |                           or <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
          |                           mutually exclusive with the 'json' option) (expects
          |                           a path)
          |    --slice              Slice the project starting from the given module IDs
          |                           and their dependencies (expects a string separated
          |                           by ",")
          |    --symlink            Follow symbolic link when searching for files
          |-v, --versions           The properties file(s) containing version information
          |                           (defaults to <dir>${Os.fileSep}versions.properties)
          |                           (expects path strings)""".render

    var javac: ISZ[String] = ISZ("-source", "17", "-target", "17", "-encoding", "utf8", "-XDignore.symbol.file", "-Xlint:-options", "-Xlint:deprecation", "-proc:none")
    var fresh: B = false
    var par: Option[Z] = None()
    var recompile: ISZ[String] = ISZ[String]()
    var scalac: ISZ[String] = ISZ("-release", "17", "-deprecation", "-Yrangepos", "-Ydelambdafy:method", "-feature", "-unchecked", "-Werror", "-language:postfixOps")
    var sha3: B = false
    var target: ISZ[SireumProyekExportBuildTool.Type] = ISZ(SireumProyekExportBuildTool.Bloop)
    var ignoreRuntime: B = false
    var json: Option[String] = None[String]()
    var name: Option[String] = None[String]()
    var outputDirName: Option[String] = Some("out")
    var project: Option[String] = None[String]()
    var slice: ISZ[String] = ISZ[String]()
    var symlink: B = false
    var versions: ISZ[String] = ISZ[String]()
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
         } else if (arg == "--target") {
           val o: Option[ISZ[SireumProyekExportBuildTool.Type]] = parseSireumProyekExportBuildTools(args, j + 1)
           o match {
             case Some(v) => target = v
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
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumProyekExportOption(help, parseArguments(args, j), javac, fresh, par, recompile, scalac, sha3, target, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions))
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
          |-e, --edition            IntelliJ edition (auto-detected if there is only one
          |                           installed) (expects one of { community, ultimate,
          |                           server }; default: community)
          |-f, --force              Force generation of application-wide configurations
          |                           (e.g., JDK info, etc.)
          |    --rebuild-ive        Rebuild IVE
          |    --javac              Javac options (expects a string separated by ",";
          |                           default is "-source, 17, -target, 17, -encoding,
          |                           utf8, -XDignore.symbol.file, -Xlint:-options,
          |                           -Xlint:deprecation, -proc:none")
          |    --scalac             Scalac options (expects a string separated by ",";
          |                           default is "-release, 17, -deprecation, -Yrangepos,
          |                           -Ydelambdafy:method, -feature, -unchecked, -Werror,
          |                           -language:postfixOps")
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
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project-standalone.cmd,
          |                           or <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
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
          |    --proxy-host         Proxy host for ivy resolution (expects a string)
          |    --proxy-non-hosts    Proxy non-hosts for ivy resolution (expects a string)
          |    --proxy-port         Proxy port for ivy resolution (expects a string)
          |    --proxy-protocol     Proxy protocol for ivy resolution (expects a string)
          |    --proxy-user-env     Proxy user environment variable for ivy resolution
          |                           (expects a string)
          |    --proxy-passwd-env   Proxy password environment variable for ivy resolution
          |                           (expects a string)
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")""".render

    var empty: B = false
    var edition: SireumProyekIveEdition.Type = SireumProyekIveEdition.Community
    var force: B = false
    var rebuildIve: B = false
    var javac: ISZ[String] = ISZ("-source", "17", "-target", "17", "-encoding", "utf8", "-XDignore.symbol.file", "-Xlint:-options", "-Xlint:deprecation", "-proc:none")
    var scalac: ISZ[String] = ISZ("-release", "17", "-deprecation", "-Yrangepos", "-Ydelambdafy:method", "-feature", "-unchecked", "-Werror", "-language:postfixOps")
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
    var proxyHost: Option[String] = None[String]()
    var proxyNonHosts: Option[String] = None[String]()
    var proxyPort: Option[String] = None[String]()
    var proxyProtocol: Option[String] = None[String]()
    var proxyUser: Option[String] = None[String]()
    var proxyPassword: Option[String] = None[String]()
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
         } else if (arg == "-e" || arg == "--edition") {
           val o: Option[SireumProyekIveEdition.Type] = parseSireumProyekIveEdition(args, j + 1)
           o match {
             case Some(v) => edition = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--force") {
           val o: Option[B] = { j = j - 1; Some(!force) }
           o match {
             case Some(v) => force = v
             case _ => return None()
           }
         } else if (arg == "--rebuild-ive") {
           val o: Option[B] = { j = j - 1; Some(!rebuildIve) }
           o match {
             case Some(v) => rebuildIve = v
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
         } else if (arg == "--proxy-host") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyHost = v
             case _ => return None()
           }
         } else if (arg == "--proxy-non-hosts") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyNonHosts = v
             case _ => return None()
           }
         } else if (arg == "--proxy-port") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPort = v
             case _ => return None()
           }
         } else if (arg == "--proxy-protocol") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyProtocol = v
             case _ => return None()
           }
         } else if (arg == "--proxy-user-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyUser = v
             case _ => return None()
           }
         } else if (arg == "--proxy-passwd-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPassword = v
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
    return Some(SireumProyekIveOption(help, parseArguments(args, j), empty, edition, force, rebuildIve, javac, scalac, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, cache, docs, sources, proxyHost, proxyNonHosts, proxyPort, proxyProtocol, proxyUser, proxyPassword, repositories))
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

  def parseSireumProyekLogikaStrictPureModeH(arg: String): Option[SireumProyekLogikaStrictPureMode.Type] = {
    arg.native match {
      case "default" => return Some(SireumProyekLogikaStrictPureMode.Default)
      case "flip" => return Some(SireumProyekLogikaStrictPureMode.Flip)
      case "uninterpreted" => return Some(SireumProyekLogikaStrictPureMode.Uninterpreted)
      case s =>
        eprintln(s"Expecting one of the following: { default, flip, uninterpreted }, but found '$s'.")
        return None()
    }
  }

  def parseSireumProyekLogikaStrictPureMode(args: ISZ[String], i: Z): Option[SireumProyekLogikaStrictPureMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { default, flip, uninterpreted }, but none found.")
      return None()
    }
    val r = parseSireumProyekLogikaStrictPureModeH(args(i))
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
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project-standalone.cmd,
          |                           or <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
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
          |    --proxy-host         Proxy host for ivy resolution (expects a string)
          |    --proxy-non-hosts    Proxy non-hosts for ivy resolution (expects a string)
          |    --proxy-port         Proxy port for ivy resolution (expects a string)
          |    --proxy-protocol     Proxy protocol for ivy resolution (expects a string)
          |    --proxy-user-env     Proxy user environment variable for ivy resolution
          |                           (expects a string)
          |    --proxy-passwd-env   Proxy password environment variable for ivy resolution
          |                           (expects a string)
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
          |    --interprocedural    Enable inter-procedural verification on
          |                           non-strict-pure methods
          |    --interprocedural-contracts
          |                          Use contracts in inter-procedural verification
          |    --strictpure-mode    Strict-pure method treatment mode in
          |                           compositional/interprocedural verification (expects
          |                           one of { default, flip, uninterpreted }; default:
          |                           default)
          |    --line               Focus verification to the specified program line
          |                           number (expects an integer; min is 0; default is 0)
          |    --loop-bound         Loop bound for inter-procedural verification (expects
          |                           an integer; min is 1; default is 3)
          |    --recursive-bound    Recursive call-chain bound for inter-procedural
          |                           verification (expects an integer; min is 1; default
          |                           is 3)
          |    --pattern-inexhaustive
          |                          Disable pattern exhaustiveness checking
          |    --pure-proof-fun     Always add proof functions for pure methods
          |    --sat                Enable assumption satisfiability checking
          |    --skip-methods       Skip checking methods with the specified
          |                           fully-qualified names or identifiers (expects a
          |                           string separated by ",")
          |    --skip-types         Skip checking traits, classes, and objects with the
          |                           specified fully-qualified names or identifiers
          |                           (expects a string separated by ",")
          |
          |Logging Options:
          |    --log-pc             Display path conditions before each statement
          |    --log-pc-lines       Display At(...) path condition line numbers and unique
          |                           symbolic value numbering
          |    --log-raw-pc         Display raw path conditions before each statement
          |    --log-vc             Display all verification conditions
          |    --log-vc-dir         Write all verification conditions in a directory
          |                           (expects a path)
          |    --log-detailed-info  Display detailed feedback information
          |    --log-rewrite-at     Disable At(...) rewriting as In(...)/Old(...) in
          |                           symexe mode
          |    --stats              Collect verification statistics
          |
          |Optimizations Options:
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --par-branch         Enable branch parallelization
          |    --par-branch-return  Only use branch parallelization if all branches return
          |    --par-branch-pred-num
          |                          Branch parallelization prediction minimum number of
          |                           branches (expects an integer; min is 3; default is
          |                           2)
          |    --par-branch-pred-complexity
          |                          Branch parallelization prediction statement
          |                           complexity (expects an integer; min is 0; default is
          |                           16)
          |    --par-rw             Enable rewriting parallelization
          |
          |Path Splitting Options:
          |    --dont-split-pfq     Do not force splitting in quantifiers and proof
          |                           functions derived from @strictpure methods
          |    --split-all          Split all
          |    --split-contract     Split on contract cases
          |    --split-if           Split on if-conditional expressions and statements
          |    --split-match        Split on match expressions and statements
          |
          |Rewriting Options:
          |    --rw-max             Maximum number of rewriting (expects an integer; min
          |                           is 1; default is 100)
          |    --rw-trace           Disable rewriting trace
          |    --rw-eval-trace      Disable evaluation rewriting trace
          |
          |SMT2 Options:
          |    --elide-encoding     Strip out SMT2 encoding in feedback
          |    --raw-inscription    Use raw sequent/sat preamble inscription
          |    --rlimit             SMT2 solver resource limit (expects an integer; min is
          |                           0; default is 2000000)
          |    --smt2-seq           Disable SMT2 solvers parallelization
          |    --simplify           Simplify SMT2 query (experimental)
          |    --solver-sat         SMT2 configurations for satisfiability queries
          |                           (expects a string; default is "z3")
          |    --solver-valid       SMT2 configurations for validity queries (expects a
          |                           string; default is "cvc4,--full-saturate-quant; z3;
          |                           cvc5,--full-saturate-quant")
          |    --sat-timeout        Use validity checking timeout for satisfiability
          |                           checking (otherwise: 500ms)
          |-t, --timeout            Timeout (seconds) for validity checking (expects an
          |                           integer; min is 1; default is 2)
          |    --search-pc          Search path conditions first before employing SMT2
          |                           solvers when discharging VCs""".render

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
    var proxyHost: Option[String] = None[String]()
    var proxyNonHosts: Option[String] = None[String]()
    var proxyPort: Option[String] = None[String]()
    var proxyProtocol: Option[String] = None[String]()
    var proxyUser: Option[String] = None[String]()
    var proxyPassword: Option[String] = None[String]()
    var repositories: ISZ[String] = ISZ[String]()
    var infoFlow: B = false
    var charBitWidth: Z = 32
    var fpRounding: SireumProyekLogikaFPRoundingMode.Type = SireumProyekLogikaFPRoundingMode.NearestTiesToEven
    var useReal: B = false
    var intBitWidth: Z = 0
    var interprocedural: B = false
    var interproceduralContracts: B = false
    var strictPureMode: SireumProyekLogikaStrictPureMode.Type = SireumProyekLogikaStrictPureMode.Default
    var line: Z = 0
    var loopBound: Z = 3
    var callBound: Z = 3
    var patternExhaustive: B = true
    var pureFun: B = false
    var sat: B = false
    var skipMethods: ISZ[String] = ISZ[String]()
    var skipTypes: ISZ[String] = ISZ[String]()
    var logPc: B = false
    var logPcLines: B = false
    var logRawPc: B = false
    var logVc: B = false
    var logVcDir: Option[String] = None[String]()
    var logDetailedInfo: B = false
    var logAtRewrite: B = true
    var stats: B = false
    var par: Option[Z] = None()
    var branchPar: B = false
    var branchParReturn: B = false
    var branchPredNum: Z = 2
    var branchPredComplexity: Z = 16
    var rwPar: B = true
    var dontSplitFunQuant: B = false
    var splitAll: B = false
    var splitContract: B = false
    var splitIf: B = false
    var splitMatch: B = false
    var rwMax: Z = 100
    var rwTrace: B = true
    var rwEvalTrace: B = true
    var elideEncoding: B = false
    var rawInscription: B = false
    var rlimit: Z = 2000000
    var sequential: B = false
    var simplify: B = false
    var smt2SatConfigs: Option[String] = Some("z3")
    var smt2ValidConfigs: Option[String] = Some("cvc4,--full-saturate-quant; z3; cvc5,--full-saturate-quant")
    var satTimeout: B = false
    var timeout: Z = 2
    var searchPC: B = false
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
         } else if (arg == "--proxy-host") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyHost = v
             case _ => return None()
           }
         } else if (arg == "--proxy-non-hosts") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyNonHosts = v
             case _ => return None()
           }
         } else if (arg == "--proxy-port") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPort = v
             case _ => return None()
           }
         } else if (arg == "--proxy-protocol") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyProtocol = v
             case _ => return None()
           }
         } else if (arg == "--proxy-user-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyUser = v
             case _ => return None()
           }
         } else if (arg == "--proxy-passwd-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPassword = v
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
         } else if (arg == "--interprocedural-contracts") {
           val o: Option[B] = { j = j - 1; Some(!interproceduralContracts) }
           o match {
             case Some(v) => interproceduralContracts = v
             case _ => return None()
           }
         } else if (arg == "--strictpure-mode") {
           val o: Option[SireumProyekLogikaStrictPureMode.Type] = parseSireumProyekLogikaStrictPureMode(args, j + 1)
           o match {
             case Some(v) => strictPureMode = v
             case _ => return None()
           }
         } else if (arg == "--line") {
           val o: Option[Z] = parseNum(args, j + 1, Some(0), None())
           o match {
             case Some(v) => line = v
             case _ => return None()
           }
         } else if (arg == "--loop-bound") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => loopBound = v
             case _ => return None()
           }
         } else if (arg == "--recursive-bound") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => callBound = v
             case _ => return None()
           }
         } else if (arg == "--pattern-inexhaustive") {
           val o: Option[B] = { j = j - 1; Some(!patternExhaustive) }
           o match {
             case Some(v) => patternExhaustive = v
             case _ => return None()
           }
         } else if (arg == "--pure-proof-fun") {
           val o: Option[B] = { j = j - 1; Some(!pureFun) }
           o match {
             case Some(v) => pureFun = v
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
         } else if (arg == "--log-detailed-info") {
           val o: Option[B] = { j = j - 1; Some(!logDetailedInfo) }
           o match {
             case Some(v) => logDetailedInfo = v
             case _ => return None()
           }
         } else if (arg == "--log-rewrite-at") {
           val o: Option[B] = { j = j - 1; Some(!logAtRewrite) }
           o match {
             case Some(v) => logAtRewrite = v
             case _ => return None()
           }
         } else if (arg == "--stats") {
           val o: Option[B] = { j = j - 1; Some(!stats) }
           o match {
             case Some(v) => stats = v
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
         } else if (arg == "--par-branch") {
           val o: Option[B] = { j = j - 1; Some(!branchPar) }
           o match {
             case Some(v) => branchPar = v
             case _ => return None()
           }
         } else if (arg == "--par-branch-return") {
           val o: Option[B] = { j = j - 1; Some(!branchParReturn) }
           o match {
             case Some(v) => branchParReturn = v
             case _ => return None()
           }
         } else if (arg == "--par-branch-pred-num") {
           val o: Option[Z] = parseNum(args, j + 1, Some(3), None())
           o match {
             case Some(v) => branchPredNum = v
             case _ => return None()
           }
         } else if (arg == "--par-branch-pred-complexity") {
           val o: Option[Z] = parseNum(args, j + 1, Some(0), None())
           o match {
             case Some(v) => branchPredComplexity = v
             case _ => return None()
           }
         } else if (arg == "--par-rw") {
           val o: Option[B] = { j = j - 1; Some(!rwPar) }
           o match {
             case Some(v) => rwPar = v
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
         } else if (arg == "--rw-max") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => rwMax = v
             case _ => return None()
           }
         } else if (arg == "--rw-trace") {
           val o: Option[B] = { j = j - 1; Some(!rwTrace) }
           o match {
             case Some(v) => rwTrace = v
             case _ => return None()
           }
         } else if (arg == "--rw-eval-trace") {
           val o: Option[B] = { j = j - 1; Some(!rwEvalTrace) }
           o match {
             case Some(v) => rwEvalTrace = v
             case _ => return None()
           }
         } else if (arg == "--elide-encoding") {
           val o: Option[B] = { j = j - 1; Some(!elideEncoding) }
           o match {
             case Some(v) => elideEncoding = v
             case _ => return None()
           }
         } else if (arg == "--raw-inscription") {
           val o: Option[B] = { j = j - 1; Some(!rawInscription) }
           o match {
             case Some(v) => rawInscription = v
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
         } else if (arg == "--sat-timeout") {
           val o: Option[B] = { j = j - 1; Some(!satTimeout) }
           o match {
             case Some(v) => satTimeout = v
             case _ => return None()
           }
         } else if (arg == "-t" || arg == "--timeout") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => timeout = v
             case _ => return None()
           }
         } else if (arg == "--search-pc") {
           val o: Option[B] = { j = j - 1; Some(!searchPC) }
           o match {
             case Some(v) => searchPC = v
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
    return Some(SireumProyekLogikaOption(help, parseArguments(args, j), all, strictAliasing, verbose, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, cache, docs, sources, proxyHost, proxyNonHosts, proxyPort, proxyProtocol, proxyUser, proxyPassword, repositories, infoFlow, charBitWidth, fpRounding, useReal, intBitWidth, interprocedural, interproceduralContracts, strictPureMode, line, loopBound, callBound, patternExhaustive, pureFun, sat, skipMethods, skipTypes, logPc, logPcLines, logRawPc, logVc, logVcDir, logDetailedInfo, logAtRewrite, stats, par, branchPar, branchParReturn, branchPredNum, branchPredComplexity, rwPar, dontSplitFunQuant, splitAll, splitContract, splitIf, splitMatch, rwMax, rwTrace, rwEvalTrace, elideEncoding, rawInscription, rlimit, sequential, simplify, smt2SatConfigs, smt2ValidConfigs, satTimeout, timeout, searchPC))
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
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project-standalone.cmd,
          |                           or <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
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
          |                           default is "-source, 17, -target, 17, -encoding,
          |                           utf8, -XDignore.symbol.file, -Xlint:-options,
          |                           -Xlint:deprecation, -proc:none")
          |-f, --fresh              Fresh compilation from a clean slate
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --recompile          Module IDs to force recompilation on (expects a string
          |                           separated by ",")
          |    --scalac             Scalac options (expects a string separated by ",";
          |                           default is "-release, 17, -deprecation, -Yrangepos,
          |                           -Ydelambdafy:method, -feature, -unchecked, -Werror,
          |                           -language:postfixOps")
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
          |    --proxy-host         Proxy host for ivy resolution (expects a string)
          |    --proxy-non-hosts    Proxy non-hosts for ivy resolution (expects a string)
          |    --proxy-port         Proxy port for ivy resolution (expects a string)
          |    --proxy-protocol     Proxy protocol for ivy resolution (expects a string)
          |    --proxy-user-env     Proxy user environment variable for ivy resolution
          |                           (expects a string)
          |    --proxy-passwd-env   Proxy password environment variable for ivy resolution
          |                           (expects a string)
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
    var javac: ISZ[String] = ISZ("-source", "17", "-target", "17", "-encoding", "utf8", "-XDignore.symbol.file", "-Xlint:-options", "-Xlint:deprecation", "-proc:none")
    var fresh: B = false
    var par: Option[Z] = None()
    var recompile: ISZ[String] = ISZ[String]()
    var scalac: ISZ[String] = ISZ("-release", "17", "-deprecation", "-Yrangepos", "-Ydelambdafy:method", "-feature", "-unchecked", "-Werror", "-language:postfixOps")
    var sha3: B = false
    var skipCompile: B = false
    var cache: Option[String] = None[String]()
    var docs: B = true
    var sources: B = true
    var proxyHost: Option[String] = None[String]()
    var proxyNonHosts: Option[String] = None[String]()
    var proxyPort: Option[String] = None[String]()
    var proxyProtocol: Option[String] = None[String]()
    var proxyUser: Option[String] = None[String]()
    var proxyPassword: Option[String] = None[String]()
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
         } else if (arg == "--proxy-host") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyHost = v
             case _ => return None()
           }
         } else if (arg == "--proxy-non-hosts") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyNonHosts = v
             case _ => return None()
           }
         } else if (arg == "--proxy-port") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPort = v
             case _ => return None()
           }
         } else if (arg == "--proxy-protocol") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyProtocol = v
             case _ => return None()
           }
         } else if (arg == "--proxy-user-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyUser = v
             case _ => return None()
           }
         } else if (arg == "--proxy-passwd-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPassword = v
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
    return Some(SireumProyekPublishOption(help, parseArguments(args, j), m2, target, version, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, javac, fresh, par, recompile, scalac, sha3, skipCompile, cache, docs, sources, proxyHost, proxyNonHosts, proxyPort, proxyProtocol, proxyUser, proxyPassword, repositories))
  }

  def parseSireumProyekReflect(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Proyek Reflection Generator
          |
          |Usage: <options>* <dir>
          |
          |Available Options:
          |    --class              Class name (expects a string; default is
          |                           "StaticReflection")
          |    --include-packages   Package names to include (includes all by default)
          |                           (expects a string separated by ",")
          |    --include            Object or type names to include (includes all in each
          |                           package by default) (expects a string separated by
          |                           ",")
          |    --exclude-packages   Package names to exclude (excludes none by default)
          |                           (expects a string separated by ",")
          |    --exclude            Object or type names to exclude (exclude none by
          |                           default) (expects a string separated by ",")
          |    --license            License file (expects a path)
          |    --package            Package name (expects a string)
          |    --output             Output directory (expects a path)
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
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project-standalone.cmd,
          |                           or <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
          |                           mutually exclusive with the 'json' option) (expects
          |                           a path)
          |    --slice              Slice the project starting from the given module IDs
          |                           and their dependencies (expects a string separated
          |                           by ",")
          |    --symlink            Follow symbolic link when searching for files
          |-v, --versions           The properties file(s) containing version information
          |                           (defaults to <dir>${Os.fileSep}versions.properties)
          |                           (expects path strings)""".render

    var className: Option[String] = Some("StaticReflection")
    var includePackages: ISZ[String] = ISZ[String]()
    var include: ISZ[String] = ISZ[String]()
    var excludePackages: ISZ[String] = ISZ[String]()
    var exclude: ISZ[String] = ISZ[String]()
    var license: Option[String] = None[String]()
    var packageName: Option[String] = None[String]()
    var output: Option[String] = None[String]()
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
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--class") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => className = v
             case _ => return None()
           }
         } else if (arg == "--include-packages") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => includePackages = v
             case _ => return None()
           }
         } else if (arg == "--include") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => include = v
             case _ => return None()
           }
         } else if (arg == "--exclude-packages") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => excludePackages = v
             case _ => return None()
           }
         } else if (arg == "--exclude") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => exclude = v
             case _ => return None()
           }
         } else if (arg == "--license") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => license = v
             case _ => return None()
           }
         } else if (arg == "--package") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => packageName = v
             case _ => return None()
           }
         } else if (arg == "--output") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => output = v
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
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumProyekReflectOption(help, parseArguments(args, j), className, includePackages, include, excludePackages, exclude, license, packageName, output, par, strictAliasing, verbose, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions))
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
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project-standalone.cmd,
          |                           or <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
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
          |                           default is "-source, 17, -target, 17, -encoding,
          |                           utf8, -XDignore.symbol.file, -Xlint:-options,
          |                           -Xlint:deprecation, -proc:none")
          |-f, --fresh              Fresh compilation from a clean slate
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --recompile          Module IDs to force recompilation on (expects a string
          |                           separated by ",")
          |    --scalac             Scalac options (expects a string separated by ",";
          |                           default is "-release, 17, -deprecation, -Yrangepos,
          |                           -Ydelambdafy:method, -feature, -unchecked, -Werror,
          |                           -language:postfixOps")
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
          |    --proxy-host         Proxy host for ivy resolution (expects a string)
          |    --proxy-non-hosts    Proxy non-hosts for ivy resolution (expects a string)
          |    --proxy-port         Proxy port for ivy resolution (expects a string)
          |    --proxy-protocol     Proxy protocol for ivy resolution (expects a string)
          |    --proxy-user-env     Proxy user environment variable for ivy resolution
          |                           (expects a string)
          |    --proxy-passwd-env   Proxy password environment variable for ivy resolution
          |                           (expects a string)
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
    var javac: ISZ[String] = ISZ("-source", "17", "-target", "17", "-encoding", "utf8", "-XDignore.symbol.file", "-Xlint:-options", "-Xlint:deprecation", "-proc:none")
    var fresh: B = false
    var par: Option[Z] = None()
    var recompile: ISZ[String] = ISZ[String]()
    var scalac: ISZ[String] = ISZ("-release", "17", "-deprecation", "-Yrangepos", "-Ydelambdafy:method", "-feature", "-unchecked", "-Werror", "-language:postfixOps")
    var sha3: B = false
    var skipCompile: B = false
    var cache: Option[String] = None[String]()
    var docs: B = true
    var sources: B = true
    var proxyHost: Option[String] = None[String]()
    var proxyNonHosts: Option[String] = None[String]()
    var proxyPort: Option[String] = None[String]()
    var proxyProtocol: Option[String] = None[String]()
    var proxyUser: Option[String] = None[String]()
    var proxyPassword: Option[String] = None[String]()
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
         } else if (arg == "--proxy-host") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyHost = v
             case _ => return None()
           }
         } else if (arg == "--proxy-non-hosts") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyNonHosts = v
             case _ => return None()
           }
         } else if (arg == "--proxy-port") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPort = v
             case _ => return None()
           }
         } else if (arg == "--proxy-protocol") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyProtocol = v
             case _ => return None()
           }
         } else if (arg == "--proxy-user-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyUser = v
             case _ => return None()
           }
         } else if (arg == "--proxy-passwd-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPassword = v
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
    return Some(SireumProyekRunOption(help, parseArguments(args, j), dir, java, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, javac, fresh, par, recompile, scalac, sha3, skipCompile, cache, docs, sources, proxyHost, proxyNonHosts, proxyPort, proxyProtocol, proxyUser, proxyPassword, repositories))
  }

  def parseSireumProyekSlangcheck(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Slang Check generator
          |
          |Usage: <option>* <dir> <slang-file>+
          |
          |Available Options:
          |-l, --license            License file to be inserted in the file header
          |                           (expects a path)
          |-p, --packageName        Package name for generators (expects a path)
          |-o, --output-dir         Output directory for the generated Slang Check files
          |                           (expects a path; default is ".")
          |-t, --test-dir           Output directory for the generated unit tests (expects
          |                           a path)
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
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project-standalone.cmd,
          |                           or <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
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
          |    --proxy-host         Proxy host for ivy resolution (expects a string)
          |    --proxy-non-hosts    Proxy non-hosts for ivy resolution (expects a string)
          |    --proxy-port         Proxy port for ivy resolution (expects a string)
          |    --proxy-protocol     Proxy protocol for ivy resolution (expects a string)
          |    --proxy-user-env     Proxy user environment variable for ivy resolution
          |                           (expects a string)
          |    --proxy-passwd-env   Proxy password environment variable for ivy resolution
          |                           (expects a string)
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")""".render

    var license: Option[String] = None[String]()
    var packageName: Option[String] = None[String]()
    var outputDir: Option[String] = Some(".")
    var testDir: Option[String] = None[String]()
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
    var proxyHost: Option[String] = None[String]()
    var proxyNonHosts: Option[String] = None[String]()
    var proxyPort: Option[String] = None[String]()
    var proxyProtocol: Option[String] = None[String]()
    var proxyUser: Option[String] = None[String]()
    var proxyPassword: Option[String] = None[String]()
    var repositories: ISZ[String] = ISZ[String]()
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
         } else if (arg == "-p" || arg == "--packageName") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => packageName = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => outputDir = v
             case _ => return None()
           }
         } else if (arg == "-t" || arg == "--test-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => testDir = v
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
         } else if (arg == "--proxy-host") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyHost = v
             case _ => return None()
           }
         } else if (arg == "--proxy-non-hosts") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyNonHosts = v
             case _ => return None()
           }
         } else if (arg == "--proxy-port") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPort = v
             case _ => return None()
           }
         } else if (arg == "--proxy-protocol") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyProtocol = v
             case _ => return None()
           }
         } else if (arg == "--proxy-user-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyUser = v
             case _ => return None()
           }
         } else if (arg == "--proxy-passwd-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPassword = v
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
    return Some(SireumProyekSlangcheckOption(help, parseArguments(args, j), license, packageName, outputDir, testDir, par, strictAliasing, verbose, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, cache, docs, sources, proxyHost, proxyNonHosts, proxyPort, proxyProtocol, proxyUser, proxyPassword, repositories))
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
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project-standalone.cmd,
          |                           or <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
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
          |    --proxy-host         Proxy host for ivy resolution (expects a string)
          |    --proxy-non-hosts    Proxy non-hosts for ivy resolution (expects a string)
          |    --proxy-port         Proxy port for ivy resolution (expects a string)
          |    --proxy-protocol     Proxy protocol for ivy resolution (expects a string)
          |    --proxy-user-env     Proxy user environment variable for ivy resolution
          |                           (expects a string)
          |    --proxy-passwd-env   Proxy password environment variable for ivy resolution
          |                           (expects a string)
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
    var proxyHost: Option[String] = None[String]()
    var proxyNonHosts: Option[String] = None[String]()
    var proxyPort: Option[String] = None[String]()
    var proxyProtocol: Option[String] = None[String]()
    var proxyUser: Option[String] = None[String]()
    var proxyPassword: Option[String] = None[String]()
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
         } else if (arg == "--proxy-host") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyHost = v
             case _ => return None()
           }
         } else if (arg == "--proxy-non-hosts") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyNonHosts = v
             case _ => return None()
           }
         } else if (arg == "--proxy-port") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPort = v
             case _ => return None()
           }
         } else if (arg == "--proxy-protocol") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyProtocol = v
             case _ => return None()
           }
         } else if (arg == "--proxy-user-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyUser = v
             case _ => return None()
           }
         } else if (arg == "--proxy-passwd-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPassword = v
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
    return Some(SireumProyekStatsOption(help, parseArguments(args, j), ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, cache, docs, sources, proxyHost, proxyNonHosts, proxyPort, proxyProtocol, proxyUser, proxyPassword, repositories))
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
          |    --coverage           JaCoCo exec, classdumpdir, report path prefix (without
          |                           .exec, .dump, .coverage) (expects a path)
          |    --java               Java options (expects a string separated by ",")
          |    --junit5             Use JUnit5 runner
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
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project-standalone.cmd,
          |                           or <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
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
          |                           default is "-source, 17, -target, 17, -encoding,
          |                           utf8, -XDignore.symbol.file, -Xlint:-options,
          |                           -Xlint:deprecation, -proc:none")
          |-f, --fresh              Fresh compilation from a clean slate
          |-p, --par                Enable parallelization (with CPU cores percentage to
          |                           use) (accepts an optional integer; min is 1; max is
          |                           100; default is 100)
          |    --recompile          Module IDs to force recompilation on (expects a string
          |                           separated by ",")
          |    --scalac             Scalac options (expects a string separated by ",";
          |                           default is "-release, 17, -deprecation, -Yrangepos,
          |                           -Ydelambdafy:method, -feature, -unchecked, -Werror,
          |                           -language:postfixOps")
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
          |    --proxy-host         Proxy host for ivy resolution (expects a string)
          |    --proxy-non-hosts    Proxy non-hosts for ivy resolution (expects a string)
          |    --proxy-port         Proxy port for ivy resolution (expects a string)
          |    --proxy-protocol     Proxy protocol for ivy resolution (expects a string)
          |    --proxy-user-env     Proxy user environment variable for ivy resolution
          |                           (expects a string)
          |    --proxy-passwd-env   Proxy password environment variable for ivy resolution
          |                           (expects a string)
          |-r, --repositories       Additional repository URLs to retrieve Ivy
          |                           dependencies from (expects a string separated by
          |                           ",")""".render

    var classes: ISZ[String] = ISZ[String]()
    var coverage: Option[String] = None[String]()
    var java: ISZ[String] = ISZ[String]()
    var junit5: B = false
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
    var javac: ISZ[String] = ISZ("-source", "17", "-target", "17", "-encoding", "utf8", "-XDignore.symbol.file", "-Xlint:-options", "-Xlint:deprecation", "-proc:none")
    var fresh: B = false
    var par: Option[Z] = None()
    var recompile: ISZ[String] = ISZ[String]()
    var scalac: ISZ[String] = ISZ("-release", "17", "-deprecation", "-Yrangepos", "-Ydelambdafy:method", "-feature", "-unchecked", "-Werror", "-language:postfixOps")
    var sha3: B = false
    var skipCompile: B = false
    var cache: Option[String] = None[String]()
    var docs: B = true
    var sources: B = true
    var proxyHost: Option[String] = None[String]()
    var proxyNonHosts: Option[String] = None[String]()
    var proxyPort: Option[String] = None[String]()
    var proxyProtocol: Option[String] = None[String]()
    var proxyUser: Option[String] = None[String]()
    var proxyPassword: Option[String] = None[String]()
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
         } else if (arg == "--coverage") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => coverage = v
             case _ => return None()
           }
         } else if (arg == "--java") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => java = v
             case _ => return None()
           }
         } else if (arg == "--junit5") {
           val o: Option[B] = { j = j - 1; Some(!junit5) }
           o match {
             case Some(v) => junit5 = v
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
         } else if (arg == "--proxy-host") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyHost = v
             case _ => return None()
           }
         } else if (arg == "--proxy-non-hosts") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyNonHosts = v
             case _ => return None()
           }
         } else if (arg == "--proxy-port") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPort = v
             case _ => return None()
           }
         } else if (arg == "--proxy-protocol") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyProtocol = v
             case _ => return None()
           }
         } else if (arg == "--proxy-user-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyUser = v
             case _ => return None()
           }
         } else if (arg == "--proxy-passwd-env") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => proxyPassword = v
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
    return Some(SireumProyekTestOption(help, parseArguments(args, j), classes, coverage, java, junit5, packages, suffixes, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions, javac, fresh, par, recompile, scalac, sha3, skipCompile, cache, docs, sources, proxyHost, proxyNonHosts, proxyPort, proxyProtocol, proxyUser, proxyPassword, repositories))
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
          |                           <dir>${Os.fileSep}bin${Os.fileSep}project-standalone.cmd,
          |                           or <dir>${Os.fileSep}bin${Os.fileSep}project.cmd;
          |                           mutually exclusive with the 'json' option) (expects
          |                           a path)
          |    --slice              Slice the project starting from the given module IDs
          |                           and their dependencies (expects a string separated
          |                           by ",")
          |    --symlink            Follow symbolic link when searching for files
          |-v, --versions           The properties file(s) containing version information
          |                           (defaults to <dir>${Os.fileSep}versions.properties)
          |                           (expects path strings)""".render

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
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumProyekTipeOption(help, parseArguments(args, j), par, strictAliasing, verbose, ignoreRuntime, json, name, outputDirName, project, slice, symlink, versions))
  }

  def parseSireumSlang(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""The Sireum Language (Slang) Tools
            |
            |Available modes:
            |refactor                 Refactor script
            |run                      Script runner
            |template                 Insert Slang template
            |tipe                     Type checker
            |transpilers              Slang transpilers""".render
      )
      return Some(HelpOption())
    }
    val opt = select("slang", args, i, ISZ("refactor", "run", "template", "tipe", "transpilers"))
    opt match {
      case Some(string"refactor") => return parseSireumSlangRefactor(args, i + 1)
      case Some(string"run") => return parseSireumSlangRun(args, i + 1)
      case Some(string"template") => return parseSireumSlangTemplate(args, i + 1)
      case Some(string"tipe") => return parseSireumSlangTipe(args, i + 1)
      case Some(string"transpilers") => return parseSireumSlangTranspilers(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumSlangRefactorModeH(arg: String): Option[SireumSlangRefactorMode.Type] = {
    arg.native match {
      case "enumSymbol" => return Some(SireumSlangRefactorMode.EnumSymbol)
      case "insertVal" => return Some(SireumSlangRefactorMode.InsertVal)
      case "renumberProof" => return Some(SireumSlangRefactorMode.RenumberProof)
      case "reformatProof" => return Some(SireumSlangRefactorMode.ReformatProof)
      case "expandInduct" => return Some(SireumSlangRefactorMode.ExpandInduct)
      case s =>
        eprintln(s"Expecting one of the following: { enumSymbol, insertVal, renumberProof, reformatProof, expandInduct }, but found '$s'.")
        return None()
    }
  }

  def parseSireumSlangRefactorMode(args: ISZ[String], i: Z): Option[SireumSlangRefactorMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { enumSymbol, insertVal, renumberProof, reformatProof, expandInduct }, but none found.")
      return None()
    }
    val r = parseSireumSlangRefactorModeH(args(i))
    return r
  }

  def parseSireumSlangRefactor(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Slang Script Refactoring
          |
          |Usage: <option>* <slang-file>
          |
          |Available Options:
          |    --feedback           Feedback output directory (expects a path)
          |-m, --mode               Refactoring mode (expects one of { enumSymbol,
          |                           insertVal, renumberProof, reformatProof,
          |                           expandInduct }; default: enumSymbol)
          |-h, --help               Display this information""".render

    var feedback: Option[String] = None[String]()
    var mode: SireumSlangRefactorMode.Type = SireumSlangRefactorMode.EnumSymbol
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--feedback") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => feedback = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--mode") {
           val o: Option[SireumSlangRefactorMode.Type] = parseSireumSlangRefactorMode(args, j + 1)
           o match {
             case Some(v) => mode = v
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
    return Some(SireumSlangRefactorOption(help, parseArguments(args, j), feedback, mode))
  }

  def parseSireumSlangRun(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Slang Script Runner
          |
          |Usage: <option>* <slang-file> <arg>*
          |
          |Available Options:
          |-e, --eval               Use Slang evaluator (all other options are ignored)
          |-i, --input              Input file for stdin (default: <slang-file>.txt, if
          |                           any) (expects a path)
          |-o, --output             Output file for stdin & stderr (expects a path)
          |-t, --transformed        Show Scala transformed tree
          |-n, --native             Generate native executable
          |-h, --help               Display this information""".render

    var eval: B = false
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
        } else if (arg == "-e" || arg == "--eval") {
           val o: Option[B] = { j = j - 1; Some(!eval) }
           o match {
             case Some(v) => eval = v
             case _ => return None()
           }
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
    return Some(SireumSlangRunOption(help, parseArguments(args, j), eval, input, output, transformed, nativ))
  }

  def parseSireumSlangTemplateModeH(arg: String): Option[SireumSlangTemplateMode.Type] = {
    arg.native match {
      case "step" => return Some(SireumSlangTemplateMode.Step)
      case "assume" => return Some(SireumSlangTemplateMode.Assume)
      case "assert" => return Some(SireumSlangTemplateMode.Assert)
      case "subproof" => return Some(SireumSlangTemplateMode.Subproof)
      case "subproofFresh" => return Some(SireumSlangTemplateMode.SubproofFresh)
      case "forall" => return Some(SireumSlangTemplateMode.Forall)
      case "exists" => return Some(SireumSlangTemplateMode.Exists)
      case "forallRange" => return Some(SireumSlangTemplateMode.ForallRange)
      case "existsRange" => return Some(SireumSlangTemplateMode.ExistsRange)
      case "forallElements" => return Some(SireumSlangTemplateMode.ForallElements)
      case "existsElements" => return Some(SireumSlangTemplateMode.ExistsElements)
      case "forallIndices" => return Some(SireumSlangTemplateMode.ForallIndices)
      case "existsIndices" => return Some(SireumSlangTemplateMode.ExistsIndices)
      case s =>
        eprintln(s"Expecting one of the following: { step, assume, assert, subproof, subproofFresh, forall, exists, forallRange, existsRange, forallElements, existsElements, forallIndices, existsIndices }, but found '$s'.")
        return None()
    }
  }

  def parseSireumSlangTemplateMode(args: ISZ[String], i: Z): Option[SireumSlangTemplateMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { step, assume, assert, subproof, subproofFresh, forall, exists, forallRange, existsRange, forallElements, existsElements, forallIndices, existsIndices }, but none found.")
      return None()
    }
    val r = parseSireumSlangTemplateModeH(args(i))
    return r
  }

  def parseSireumSlangTemplate(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Slang Script Template Inserter
          |
          |Usage: <option>* <slang-file>
          |
          |Available Options:
          |    --feedback           Feedback output directory (expects a path)
          |-l, --line               Line location (expects an integer; min is 1; default
          |                           is 0)
          |-c, --column             Column location (expects an integer; min is 1; default
          |                           is 0)
          |-m, --mode               Refactoring mode (expects one of { step, assume,
          |                           assert, subproof, subproofFresh, forall, exists,
          |                           forallRange, existsRange, forallElements,
          |                           existsElements, forallIndices, existsIndices };
          |                           default: step)
          |-h, --help               Display this information""".render

    var feedback: Option[String] = None[String]()
    var line: Z = 0
    var column: Z = 0
    var mode: SireumSlangTemplateMode.Type = SireumSlangTemplateMode.Step
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--feedback") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => feedback = v
             case _ => return None()
           }
         } else if (arg == "-l" || arg == "--line") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => line = v
             case _ => return None()
           }
         } else if (arg == "-c" || arg == "--column") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => column = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--mode") {
           val o: Option[SireumSlangTemplateMode.Type] = parseSireumSlangTemplateMode(args, j + 1)
           o match {
             case Some(v) => mode = v
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
    return Some(SireumSlangTemplateOption(help, parseArguments(args, j), feedback, line, column, mode))
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
          |    --parseable-messages Print parseable file messages
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
    var parseableMessages: B = false
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
         } else if (arg == "--parseable-messages") {
           val o: Option[B] = { j = j - 1; Some(!parseableMessages) }
           o match {
             case Some(v) => parseableMessages = v
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
    return Some(SireumSlangTipeOption(help, parseArguments(args, j), exclude, force, noRuntime, outline, parseableMessages, sourcepath, strictAliasing, verbose, save, load, gzip))
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
      case Some(string"c") => return parseSireumSlangTranspilersC(args, i + 1)
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
      case Some(string"gen") => return parseSireumPresentasiGen(args, i + 1)
      case Some(string"text2speech") => return parseSireumPresentasiText2speech(args, i + 1)
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
          |    --cc                 Additional time shift (ms) for close-captioning
          |                           subtitle (expects an integer; default is 0)
          |    --clean              Remove unused generated audio files
          |    --record             Generates subtitle and audio files to combine with
          |                           screen recording
          |    --slice              Slide indices to keep (expects a string separated by
          |                           ",")
          |    --slides             Generate markdown slides
          |    --srt                Generate .srt instead of .vtt subtitle file
          |    --video-fps          Animated video frames-per-second when generating
          |                           markdown slides (expects an integer; min is 5;
          |                           default is 5)
          |    --video-height       Animated video height when generating markdown slides
          |                           (expects an integer; min is 240; default is 1080)
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

    var cc: Z = 0
    var clean: B = false
    var record: B = false
    var slice: ISZ[String] = ISZ[String]()
    var slides: B = false
    var srt: B = false
    var videoFps: Z = 5
    var videoHeight: Z = 1080
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
        } else if (arg == "--cc") {
           val o: Option[Z] = parseNum(args, j + 1, None(), None())
           o match {
             case Some(v) => cc = v
             case _ => return None()
           }
         } else if (arg == "--clean") {
           val o: Option[B] = { j = j - 1; Some(!clean) }
           o match {
             case Some(v) => clean = v
             case _ => return None()
           }
         } else if (arg == "--record") {
           val o: Option[B] = { j = j - 1; Some(!record) }
           o match {
             case Some(v) => record = v
             case _ => return None()
           }
         } else if (arg == "--slice") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => slice = v
             case _ => return None()
           }
         } else if (arg == "--slides") {
           val o: Option[B] = { j = j - 1; Some(!slides) }
           o match {
             case Some(v) => slides = v
             case _ => return None()
           }
         } else if (arg == "--srt") {
           val o: Option[B] = { j = j - 1; Some(!srt) }
           o match {
             case Some(v) => srt = v
             case _ => return None()
           }
         } else if (arg == "--video-fps") {
           val o: Option[Z] = parseNum(args, j + 1, Some(5), None())
           o match {
             case Some(v) => videoFps = v
             case _ => return None()
           }
         } else if (arg == "--video-height") {
           val o: Option[Z] = parseNum(args, j + 1, Some(240), None())
           o match {
             case Some(v) => videoHeight = v
             case _ => return None()
           }
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
    return Some(SireumPresentasiGenOption(help, parseArguments(args, j), cc, clean, record, slice, slides, srt, videoFps, videoHeight, force, lang, outputFormat, service, voice, awsPath, engine, gender, key, region, voiceLang))
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
      case "json" => return Some(SireumServerServerMessage.Json)
      case "msgpack" => return Some(SireumServerServerMessage.Msgpack)
      case s =>
        eprintln(s"Expecting one of the following: { json, msgpack }, but found '$s'.")
        return None()
    }
  }

  def parseSireumServerServerMessage(args: ISZ[String], i: Z): Option[SireumServerServerMessage.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { json, msgpack }, but none found.")
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
          |-m, --message            Message format (expects one of { json, msgpack };
          |                           default: json)
          |-l, --log                Enable logging
          |-i, --no-input-cache     Disable file input caching
          |-t, --no-type-cache      Disable type information caching
          |-v, --verbose            Enable verbose logging
          |-w, --workers            Number of analysis thread workers (expects an integer;
          |                           min is 1; default is 1)
          |-h, --help               Display this information""".render

    var message: SireumServerServerMessage.Type = SireumServerServerMessage.Json
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

  def parseSireumSetup(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum Setup
            |
            |Available modes:
            |ive                      IVE setup
            |vscode                   VSCode setup""".render
      )
      return Some(HelpOption())
    }
    val opt = select("setup", args, i, ISZ("ive", "vscode"))
    opt match {
      case Some(string"ive") => return parseSireumSetupIve(args, i + 1)
      case Some(string"vscode") => return parseSireumSetupVscode(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumSetupIve(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum IVE Setup
          |
          |Usage: <options>*
          |
          |Available Options:
          |-h, --help               Display this information""".render

    val j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }

      } else {
        isOption = F
      }
    }
    return Some(SireumSetupIveOption(help, parseArguments(args, j)))
  }

  def parseSireumSetupVscode(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum VSCode Setup
          |
          |Usage: <options>*
          |
          |Available Options:
          |    --existing-install   Existing VSCodium/VSCode installation path (expects a
          |                           path)
          |    --extensions         List of extensions to be installed (excluding Sireum
          |                           and SysIDE) (expects a string separated by ",";
          |                           default is "llvm-vs-code-extensions.vscode-clangd,
          |                           mike-lischke.vscode-antlr4,
          |                           mads-hartmann.bash-ide-vscode,
          |                           dbaeumer.vscode-eslint, mhutchie.git-graph,
          |                           ecmel.vscode-html-css, kofuk.hugo-utils,
          |                           redhat.java, langium.langium-vscode,
          |                           James-Yu.latex-workshop, jebbs.plantuml,
          |                           esbenp.prettier-vscode, ms-python.python,
          |                           rust-lang.rust-analyzer, scalameta.metals,
          |                           mshr-h.veriloghdl, redhat.vscode-xml,
          |                           redhat.vscode-yaml, adamraichu.zip-viewer,
          |                           hediet.vscode-drawio")
          |    --extensions-dir     Custom VSCodium/VSCode extensions directory (expects a
          |                           path)
          |    --vscode-marketplace Use VSCode Marketplace
          |-h, --help               Display this information""".render

    var existingInstall: Option[String] = None[String]()
    var extensions: ISZ[String] = ISZ("llvm-vs-code-extensions.vscode-clangd", "mike-lischke.vscode-antlr4", "mads-hartmann.bash-ide-vscode", "dbaeumer.vscode-eslint", "mhutchie.git-graph", "ecmel.vscode-html-css", "kofuk.hugo-utils", "redhat.java", "langium.langium-vscode", "James-Yu.latex-workshop", "jebbs.plantuml", "esbenp.prettier-vscode", "ms-python.python", "rust-lang.rust-analyzer", "scalameta.metals", "mshr-h.veriloghdl", "redhat.vscode-xml", "redhat.vscode-yaml", "adamraichu.zip-viewer", "hediet.vscode-drawio")
    var extensionsDir: Option[String] = None[String]()
    var vscode: B = false
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--existing-install") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => existingInstall = v
             case _ => return None()
           }
         } else if (arg == "--extensions") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => extensions = v
             case _ => return None()
           }
         } else if (arg == "--extensions-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => extensionsDir = v
             case _ => return None()
           }
         } else if (arg == "--vscode-marketplace") {
           val o: Option[B] = { j = j - 1; Some(!vscode) }
           o match {
             case Some(v) => vscode = v
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
    return Some(SireumSetupVscodeOption(help, parseArguments(args, j), existingInstall, extensions, extensionsDir, vscode))
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
            |jsons                    JSON schema to slang binding generator
            |opgen                    Object printer meta-generator
            |sergen                   De/Serializer generator
            |slangcheck               SlangCheck tools
            |trafo                    Transformer (visitor/rewriter) generator""".render
      )
      return Some(HelpOption())
    }
    val opt = select("tools", args, i, ISZ("bcgen", "checkstack", "cligen", "jsons", "opgen", "sergen", "slangcheck", "trafo"))
    opt match {
      case Some(string"bcgen") => return parseSireumToolsBcgen(args, i + 1)
      case Some(string"checkstack") => return parseSireumToolsCheckstack(args, i + 1)
      case Some(string"cligen") => return parseSireumToolsCligen(args, i + 1)
      case Some(string"jsons") => return parseSireumToolsJsons(args, i + 1)
      case Some(string"opgen") => return parseSireumToolsOpgen(args, i + 1)
      case Some(string"sergen") => return parseSireumToolsSergen(args, i + 1)
      case Some(string"slangcheck") => return parseSireumToolsSlangcheck(args, i + 1)
      case Some(string"trafo") => return parseSireumToolsTrafo(args, i + 1)
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
          |-r, --reporter           Use message.Reporter for reporting error messages
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
    var reporter: B = false
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
         } else if (arg == "-r" || arg == "--reporter") {
           val o: Option[B] = { j = j - 1; Some(!reporter) }
           o match {
             case Some(v) => reporter = v
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
    return Some(SireumToolsCligenOption(help, parseArguments(args, j), license, name, outputDir, packageName, reporter, script, width))
  }

  def parseSireumToolsJsons(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum JSON Schema to Slang Binding Generator
          |
          |Usage: <option>* <json-schema-file>+
          |
          |Available Options:
          |-p, --package            Package name for the binding (expects a string
          |                           separated by ".")
          |-n, --name               Type simple name for the binding (default is based on
          |                           the JSON schema filename) (expects a string)
          |-l, --license            License file to be inserted in the file header
          |                           (expects a path)
          |-o, --output-dir         Output directory for the generated Slang files
          |                           (expects a path; default is ".")
          |-h, --help               Display this information""".render

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
    return Some(SireumToolsJsonsOption(help, parseArguments(args, j), packageName, name, license, outputDir))
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
          |    --parseable-messages Print parseable file messages
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
    var parseableMessages: B = false
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
         } else if (arg == "--parseable-messages") {
           val o: Option[B] = { j = j - 1; Some(!parseableMessages) }
           o match {
             case Some(v) => parseableMessages = v
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
    return Some(SireumToolsOpgenOption(help, parseArguments(args, j), license, name, output, packageName, exclude, force, noRuntime, parseableMessages, sourcepath, strictAliasing, verbose, save, load, gzip))
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

  def parseSireumToolsSlangcheck(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""SlangCheck Tools
            |
            |Available modes:
            |runner                   SlangCheck test generator runner
            |tester                   SlangCheck test case runner
            |generator                Slang Check generator""".render
      )
      return Some(HelpOption())
    }
    val opt = select("slangcheck", args, i, ISZ("runner", "tester", "generator"))
    opt match {
      case Some(string"runner") => return parseSireumToolsSlangcheckRunner(args, i + 1)
      case Some(string"tester") => return parseSireumToolsSlangcheckTester(args, i + 1)
      case Some(string"generator") => return parseSireumToolsSlangcheckGenerator(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumToolsSlangcheckRunner(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""SlangCheck Test Generator Runner
          |
          |Usage: <option>* <fully-qualified-name>
          |
          |Available Options:
          |-c, --classpath          Classpath to load test runner class from (expects path
          |                           strings)
          |-m, --max                Maximum number of test objects (expects an integer;
          |                           min is 1; default is 0)
          |-o, --output             Output file to store generated test case objects
          |                           (expects a path)
          |-p, --parallel           Enable parallelization (accepts an optional integer;
          |                           min is 1; default is 0)
          |-s, --scp                Server connection to scp compressed output file to
          |                           (expects a string)
          |-t, --timeout            Timeout (seconds) (expects an integer; min is 1;
          |                           default is 0)
          |-h, --help               Display this information""".render

    var classpath: ISZ[String] = ISZ[String]()
    var max: Z = 0
    var output: Option[String] = None[String]()
    var par: Option[Z] = None()
    var scp: Option[String] = None[String]()
    var timeout: Z = 0
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-c" || arg == "--classpath") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => classpath = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--max") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => max = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => output = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--parallel") {
           val o: Option[Option[Z]] = parseNumFlag(args, j + 1, Some(1), None()) match {
             case o@Some(None()) => j = j - 1; Some(Some(0))
             case o => o
           }
           o match {
             case Some(v) => par = v
             case _ => return None()
           }
         } else if (arg == "-s" || arg == "--scp") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => scp = v
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
    return Some(SireumToolsSlangcheckRunnerOption(help, parseArguments(args, j), classpath, max, output, par, scp, timeout))
  }

  def parseSireumToolsSlangcheckTester(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""SlangCheck Test Case Runner
          |
          |Usage: <option>* <fully-qualified-name>
          |
          |Available Options:
          |-c, --classpath          Classpath to load test runner class from (expects path
          |                           strings)
          |    --coverage           JaCoCo exec, classdumpdir, report path prefix (without
          |                           .exec, .dump, .coverage) (expects a path)
          |-i, --input              Input file or directory containing compressed test
          |                           case objects (expects a path)
          |-o, --output             Output file to store passing/failing test case objects
          |                           (expects a path)
          |-p, --parallel           Enable parallelization (accepts an optional integer;
          |                           min is 1; default is 0)
          |-s, --scp                Server connection to scp compressed output file to
          |                           (expects a string)
          |    --sourcepath         Sourcepath for coverage information (expects path
          |                           strings)
          |-h, --help               Display this information""".render

    var classpath: ISZ[String] = ISZ[String]()
    var coverage: Option[String] = None[String]()
    var input: Option[String] = None[String]()
    var output: Option[String] = None[String]()
    var par: Option[Z] = None()
    var scp: Option[String] = None[String]()
    var sourcepath: ISZ[String] = ISZ[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-c" || arg == "--classpath") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => classpath = v
             case _ => return None()
           }
         } else if (arg == "--coverage") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => coverage = v
             case _ => return None()
           }
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
         } else if (arg == "-p" || arg == "--parallel") {
           val o: Option[Option[Z]] = parseNumFlag(args, j + 1, Some(1), None()) match {
             case o@Some(None()) => j = j - 1; Some(Some(0))
             case o => o
           }
           o match {
             case Some(v) => par = v
             case _ => return None()
           }
         } else if (arg == "-s" || arg == "--scp") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => scp = v
             case _ => return None()
           }
         } else if (arg == "--sourcepath") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => sourcepath = v
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
    return Some(SireumToolsSlangcheckTesterOption(help, parseArguments(args, j), classpath, coverage, input, output, par, scp, sourcepath))
  }

  def parseSireumToolsSlangcheckGenerator(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Slang Check generator
          |
          |Usage: <option>* <slang-file>+
          |
          |Available Options:
          |-l, --license            License file to be inserted in the file header
          |                           (expects a path)
          |-p, --package            Package name for generators (expects a string
          |                           separated by ".")
          |-o, --output-dir         Output directory for the generated Slang Check files
          |                           (expects a path; default is ".")
          |-t, --test-dir           Output directory for the generated unit tests (expects
          |                           a path)
          |-h, --help               Display this information""".render

    var license: Option[String] = None[String]()
    var packageName: ISZ[String] = ISZ[String]()
    var outputDir: Option[String] = Some(".")
    var testDir: Option[String] = None[String]()
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
         } else if (arg == "-p" || arg == "--package") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, '.')
           o match {
             case Some(v) => packageName = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => outputDir = v
             case _ => return None()
           }
         } else if (arg == "-t" || arg == "--test-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => testDir = v
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
    return Some(SireumToolsSlangcheckGeneratorOption(help, parseArguments(args, j), license, packageName, outputDir, testDir))
  }

  def parseSireumToolsTrafoTransformerModeH(arg: String): Option[SireumToolsTrafoTransformerMode.Type] = {
    arg.native match {
      case "immutable" => return Some(SireumToolsTrafoTransformerMode.Immutable)
      case "mutable" => return Some(SireumToolsTrafoTransformerMode.Mutable)
      case "rimmutable" => return Some(SireumToolsTrafoTransformerMode.Rimmutable)
      case "rmutable" => return Some(SireumToolsTrafoTransformerMode.Rmutable)
      case s =>
        eprintln(s"Expecting one of the following: { immutable, mutable, rimmutable, rmutable }, but found '$s'.")
        return None()
    }
  }

  def parseSireumToolsTrafoTransformerMode(args: ISZ[String], i: Z): Option[SireumToolsTrafoTransformerMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { immutable, mutable, rimmutable, rmutable }, but none found.")
      return None()
    }
    val r = parseSireumToolsTrafoTransformerModeH(args(i))
    return r
  }

  def parseSireumToolsTrafoTransformerModes(args: ISZ[String], i: Z): Option[ISZ[SireumToolsTrafoTransformerMode.Type]] = {
    val tokensOpt = tokenize(args, i, "SireumToolsTrafoTransformerMode", ',', T)
    if (tokensOpt.isEmpty) {
      return None()
    }
    var r = ISZ[SireumToolsTrafoTransformerMode.Type]()
    for (token <- tokensOpt.get) {
      val e = parseSireumToolsTrafoTransformerModeH(token)
      e match {
        case Some(v) => r = r :+ v
        case _ => return None()
      }
    }
    return Some(r)
  }

  def parseSireumToolsTrafo(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Transformer Generator
          |
          |Usage: <option>* <slang-file>+
          |
          |Available Options:
          |-e, --exclude            Exclude generating top-level transform for the
          |                           specified type identifiers (expects a string
          |                           separated by ",")
          |-l, --license            License file to be inserted in the file header
          |                           (expects a path)
          |-m, --modes              Transformer mode (expects one or more of { immutable,
          |                           mutable, rimmutable, rmutable }; default: immutable)
          |-n, --name               Type simple name for the transformers (default:
          |                           "Transformer" or "MTransformer") (expects a string)
          |-o, --output-dir         Output directory for the generated transformer Slang
          |                           files (expects a path; default is ".")
          |-h, --help               Display this information""".render

    var exclude: ISZ[String] = ISZ[String]()
    var license: Option[String] = None[String]()
    var modes: ISZ[SireumToolsTrafoTransformerMode.Type] = ISZ(SireumToolsTrafoTransformerMode.Immutable)
    var name: Option[String] = None[String]()
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
         } else if (arg == "-l" || arg == "--license") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => license = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--modes") {
           val o: Option[ISZ[SireumToolsTrafoTransformerMode.Type]] = parseSireumToolsTrafoTransformerModes(args, j + 1)
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
    return Some(SireumToolsTrafoOption(help, parseArguments(args, j), exclude, license, modes, name, outputDir))
  }

  def parseSireumX(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum eXperimental
            |
            |Available modes:
            |anvil                    High-level hardware synthesizer""".render
      )
      return Some(HelpOption())
    }
    val opt = select("x", args, i, ISZ("anvil"))
    opt match {
      case Some(string"anvil") => return parseSireumXAnvil(args, i + 1)
      case _ => return None()
    }
  }

  def parseSireumXAnvil(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Anvil HLS
          |
          |Usage: <option>* <fully-qualified-method-name> [ <slang-file.sc> ]
          |
          |Available Options:
          |-s, --sourcepath         Sourcepath of Slang .scala files (expects path
          |                           strings)
          |    --strict-aliasing    Enable strict aliasing check
          |-m, --memory             Memory size in kilobytes (expects an integer; min is
          |                           64; default is 512)
          |-e, --erase              Securely erase memory chunks as they are reclaimed
          |-d, --depth              Maximum expression depth to coalesce (0 means
          |                           unbounded) (expects an integer; min is 0; default is
          |                           1)
          |-r, --runtime-check      Enable implicit and explicit runtime assertion
          |                           checking
          |-t, --stack-trace        Enable call stack tracing
          |    --copy               The maximum number of bytes per cycle for memory copy
          |                           operation (expects an integer; min is 8; default is
          |                           8)
          |-p, --print              Printing console buffer size in kilobytes (rounded up
          |                           to a power of 2) (accepts an optional integer; min
          |                           is 4; default is 0)
          |-o, --output-dir         Output directory synthesized files (expects a path;
          |                           default is "out")
          |    --axi4               Enable AXI4 interface
          |    --custom-div-rem     Enable custom division and remainder implementations
          |    --verbose            Enable verbose mode
          |-h, --help               Display this information
          |
          |Configuration Options:
          |-b, --bits               Default bit-width for unbounded integer types (e.g.,
          |                           Z) (expects one of { 64, 32, 16, 8 })
          |-n, --name               Project name (expects a string)
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
          |Persistence Options:
          |    --save               Path to save type information to (outline should not
          |                           be enabled) (expects a path)
          |    --load               Path to load type information from (expects a path)
          |
          |Substitutions Options:
          |-c, --constants          Custom constant for object variables, each in the form
          |                           of <name>=<lit>, where <name> is a qualified name of
          |                           an object var and <lit> is a Slang literal
          |                           expression (expects a string separated by ";")""".render

    var sourcepath: ISZ[String] = ISZ[String]()
    var strictAliasing: B = false
    var memory: Z = 512
    var erase: B = false
    var depth: Z = 1
    var runtimeCheck: B = false
    var stackTrace: B = false
    var copy: Z = 8
    var printSize: Option[Z] = None()
    var output: Option[String] = Some("out")
    var axi4: B = false
    var customDivRem: B = false
    var verbose: B = false
    var bitWidth: Z = 64
    var projectName: Option[String] = None[String]()
    var customArraySizes: ISZ[String] = ISZ[String]()
    var maxArraySize: Z = 100
    var maxStringSize: Z = 100
    var save: Option[String] = None[String]()
    var load: Option[String] = None[String]()
    var customConstants: ISZ[String] = ISZ[String]()
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
         } else if (arg == "-m" || arg == "--memory") {
           val o: Option[Z] = parseNum(args, j + 1, Some(64), None())
           o match {
             case Some(v) => memory = v
             case _ => return None()
           }
         } else if (arg == "-e" || arg == "--erase") {
           val o: Option[B] = { j = j - 1; Some(!erase) }
           o match {
             case Some(v) => erase = v
             case _ => return None()
           }
         } else if (arg == "-d" || arg == "--depth") {
           val o: Option[Z] = parseNum(args, j + 1, Some(0), None())
           o match {
             case Some(v) => depth = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--runtime-check") {
           val o: Option[B] = { j = j - 1; Some(!runtimeCheck) }
           o match {
             case Some(v) => runtimeCheck = v
             case _ => return None()
           }
         } else if (arg == "-t" || arg == "--stack-trace") {
           val o: Option[B] = { j = j - 1; Some(!stackTrace) }
           o match {
             case Some(v) => stackTrace = v
             case _ => return None()
           }
         } else if (arg == "--copy") {
           val o: Option[Z] = parseNum(args, j + 1, Some(8), None())
           o match {
             case Some(v) => copy = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--print") {
           val o: Option[Option[Z]] = parseNumFlag(args, j + 1, Some(4), None()) match {
             case o@Some(None()) => j = j - 1; Some(Some(0))
             case o => o
           }
           o match {
             case Some(v) => printSize = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => output = v
             case _ => return None()
           }
         } else if (arg == "--axi4") {
           val o: Option[B] = { j = j - 1; Some(!axi4) }
           o match {
             case Some(v) => axi4 = v
             case _ => return None()
           }
         } else if (arg == "--custom-div-rem") {
           val o: Option[B] = { j = j - 1; Some(!customDivRem) }
           o match {
             case Some(v) => customDivRem = v
             case _ => return None()
           }
         } else if (arg == "--verbose") {
           val o: Option[B] = { j = j - 1; Some(!verbose) }
           o match {
             case Some(v) => verbose = v
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
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(SireumXAnvilOption(help, parseArguments(args, j), sourcepath, strictAliasing, memory, erase, depth, runtimeCheck, stackTrace, copy, printSize, output, axi4, customDivRem, verbose, bitWidth, projectName, customArraySizes, maxArraySize, maxStringSize, save, load, customConstants))
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
