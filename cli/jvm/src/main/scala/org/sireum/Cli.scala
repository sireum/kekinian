// #Sireum
// @formatter:off

/*
 Copyright (c) 2021, Robby, Kansas State University
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

  @enum object HamrPlatform {
    'JVM
    'Linux
    'Cygwin
    'MacOS
    'SeL4
    'SeL4_Only
    'SeL4_TB
  }

  @datatype class HamrCodeGenOption(
    help: String,
    args: ISZ[String],
    msgpack: B,
    verbose: B,
    platform: HamrPlatform.Type,
    outputDir: Option[String],
    packageName: Option[String],
    noEmbedArt: B,
    devicesAsThreads: B,
    slangAuxCodeDirs: ISZ[String],
    slangOutputCDir: Option[String],
    excludeComponentImpl: B,
    bitWidth: Z,
    maxStringSize: Z,
    maxArraySize: Z,
    runTranspiler: B,
    camkesOutputDir: Option[String],
    camkesAuxCodeDirs: ISZ[String],
    aadlRootDir: Option[String],
    experimentalOptions: ISZ[String]
  ) extends SireumTopOption

  @enum object PhantomMode {
    'Json
    'Msgpack
  }

  @datatype class PhantomOption(
    help: String,
    args: ISZ[String],
    update: B,
    osate: Option[String],
    mode: PhantomMode.Type,
    projects: ISZ[String],
    main: Option[String],
    impl: Option[String],
    output: Option[String]
  ) extends SireumTopOption

  @enum object LogikaSolver {
    'All
    'Cvc4
    'Z3
  }

  @datatype class LogikaVerifierOption(
    help: String,
    args: ISZ[String],
    sat: B,
    sourcepath: ISZ[String],
    unroll: B,
    charBitWidth: Z,
    intBitWidth: Z,
    simplify: B,
    solver: LogikaSolver.Type,
    timeout: Z,
    par: B,
    ramFolder: Option[String],
    dontSplitFunQuant: B,
    splitAll: B,
    splitContract: B,
    splitIf: B,
    splitMatch: B,
    logPc: B,
    logRawPc: B,
    logVc: B,
    logVcDir: Option[String]
  ) extends SireumTopOption

  @datatype class SlangRunOption(
    help: String,
    args: ISZ[String],
    input: Option[String],
    output: Option[String],
    transformed: B,
    nativ: B
  ) extends SireumTopOption

  @datatype class SlangTipeOption(
    help: String,
    args: ISZ[String],
    sourcepath: ISZ[String],
    outline: B,
    force: ISZ[String],
    verbose: B,
    noRuntime: B,
    exclude: ISZ[String],
    save: Option[String],
    load: Option[String],
    gzip: B
  ) extends SireumTopOption

  @datatype class CTranspilerOption(
    help: String,
    args: ISZ[String],
    sourcepath: ISZ[String],
    output: Option[String],
    verbose: B,
    apps: ISZ[String],
    bitWidth: Z,
    projectName: Option[String],
    stackSize: Option[String],
    customArraySizes: ISZ[String],
    maxArraySize: Z,
    maxStringSize: Z,
    cmakeIncludes: ISZ[String],
    exts: ISZ[String],
    libOnly: B,
    excludeBuild: ISZ[String],
    plugins: ISZ[String],
    fingerprint: Z,
    stableTypeId: B,
    unroll: B,
    save: Option[String],
    load: Option[String],
    customConstants: ISZ[String],
    forwarding: ISZ[String]
  ) extends SireumTopOption

  @enum object BitCodecMode {
    'Program
    'Script
    'Json
    'Dot
  }

  @datatype class BcgenOption(
    help: String,
    args: ISZ[String],
    mode: ISZ[BitCodecMode.Type],
    isLittleEndian: B,
    isMutable: B,
    packageName: ISZ[String],
    name: Option[String],
    license: Option[String],
    outputDir: Option[String],
    traits: ISZ[String]
  ) extends SireumTopOption

  @enum object CheckStackMode {
    'Dotsu
    'Bin
  }

  @enum object CheckStackArch {
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

  @enum object CheckStackFormat {
    'Plain
    'Csv
    'Html
    'Md
    'Rst
  }

  @datatype class CheckstackOption(
    help: String,
    args: ISZ[String],
    mode: CheckStackMode.Type,
    objdump: Option[String],
    arch: CheckStackArch.Type,
    format: CheckStackFormat.Type
  ) extends SireumTopOption

  @datatype class CligenOption(
    help: String,
    args: ISZ[String],
    packageName: ISZ[String],
    name: Option[String],
    width: ISZ[Z],
    license: Option[String],
    outputDir: Option[String]
  ) extends SireumTopOption

  @enum object IveMode {
    'Idea
    'Mill
  }

  @datatype class IvegenOption(
    help: String,
    args: ISZ[String],
    jdk: Option[String],
    mode: IveMode.Type,
    projectName: Option[String],
    moduleName: Option[String],
    packageName: ISZ[String],
    appName: Option[String],
    millPath: B,
    force: B,
    compile: B
  ) extends SireumTopOption

  @enum object SerializerMode {
    'Json
    'Msgpack
  }

  @datatype class SergenOption(
    help: String,
    args: ISZ[String],
    modes: ISZ[SerializerMode.Type],
    packageName: ISZ[String],
    name: Option[String],
    license: Option[String],
    outputDir: Option[String]
  ) extends SireumTopOption

  @enum object TransformerMode {
    'Immutable
    'Mutable
  }

  @datatype class TransgenOption(
    help: String,
    args: ISZ[String],
    modes: ISZ[TransformerMode.Type],
    name: Option[String],
    license: Option[String],
    outputDir: Option[String]
  ) extends SireumTopOption

  @enum object ServerMessage {
    'Msgpack
    'Json
  }

  @datatype class ServerOption(
    help: String,
    args: ISZ[String],
    message: ServerMessage.Type,
    logika: Z
  ) extends SireumTopOption
}

import Cli._

@record class Cli(pathSep: C) {

  def parseSireum(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum: A High-Assurance System Engineering Platform
            |(c) SAnToS Laboratory, Kansas State University
            |Build yyyymmdd.sha
            |
            |Available modes:
            |hamr                     HAMR Tools
            |slang                    Slang tools
            |tools                    Utility tools""".render
      )
      return Some(HelpOption())
    }
    val opt = select("sireum", args, i, ISZ("hamr", "logika", "slang", "tools", "x"))
    opt match {
      case Some(string"hamr") => parseHamr(args, i + 1)
      case Some(string"logika") => parseLogika(args, i + 1)
      case Some(string"slang") => parseSlang(args, i + 1)
      case Some(string"tools") => parseTools(args, i + 1)
      case Some(string"x") => parseX(args, i + 1)
      case _ => return None()
    }
  }

  def parseHamr(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""HAMR: High-Assurance Model-based Rapid-engineering tools for embedded systems
            |
            |Available modes:
            |codegen                 
            |phantom                 """.render
      )
      return Some(HelpOption())
    }
    val opt = select("hamr", args, i, ISZ("codegen", "phantom"))
    opt match {
      case Some(string"codegen") => parseHamrCodeGen(args, i + 1)
      case Some(string"phantom") => parsePhantom(args, i + 1)
      case _ => return None()
    }
  }

  def parseHamrPlatformH(arg: String): Option[HamrPlatform.Type] = {
    arg.native match {
      case "JVM" => return Some(HamrPlatform.JVM)
      case "Linux" => return Some(HamrPlatform.Linux)
      case "Cygwin" => return Some(HamrPlatform.Cygwin)
      case "MacOS" => return Some(HamrPlatform.MacOS)
      case "seL4" => return Some(HamrPlatform.SeL4)
      case "seL4_Only" => return Some(HamrPlatform.SeL4_Only)
      case "seL4_TB" => return Some(HamrPlatform.SeL4_TB)
      case s =>
        eprintln(s"Expecting one of the following: { JVM, Linux, Cygwin, MacOS, seL4, seL4_Only, seL4_TB }, but found '$s'.")
        return None()
    }
  }

  def parseHamrPlatform(args: ISZ[String], i: Z): Option[HamrPlatform.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { JVM, Linux, Cygwin, MacOS, seL4, seL4_Only, seL4_TB }, but none found.")
      return None()
    }
    val r = parseHamrPlatformH(args(i))
    return r
  }

  def parseHamrCodeGen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
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
          |    --no-embed-art       Do not embed ART project files
          |    --devices-as-thread  Treat AADL devices as threads
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
    var platform: HamrPlatform.Type = HamrPlatform.JVM
    var outputDir: Option[String] = Some(".")
    var packageName: Option[String] = None[String]()
    var noEmbedArt: B = false
    var devicesAsThreads: B = false
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
           val o: Option[HamrPlatform.Type] = parseHamrPlatform(args, j + 1)
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
    return Some(HamrCodeGenOption(help, parseArguments(args, j), msgpack, verbose, platform, outputDir, packageName, noEmbedArt, devicesAsThreads, slangAuxCodeDirs, slangOutputCDir, excludeComponentImpl, bitWidth, maxStringSize, maxArraySize, runTranspiler, camkesOutputDir, camkesAuxCodeDirs, aadlRootDir, experimentalOptions))
  }

  def parsePhantomModeH(arg: String): Option[PhantomMode.Type] = {
    arg.native match {
      case "json" => return Some(PhantomMode.Json)
      case "msgpack" => return Some(PhantomMode.Msgpack)
      case s =>
        eprintln(s"Expecting one of the following: { json, msgpack }, but found '$s'.")
        return None()
    }
  }

  def parsePhantomMode(args: ISZ[String], i: Z): Option[PhantomMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { json, msgpack }, but none found.")
      return None()
    }
    val r = parsePhantomModeH(args(i))
    return r
  }

  def parsePhantom(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Phantom: Headless OSATE AADL to AIR Translator
          |
          |Usage: ${st"""
                  |    phantom --update [--osate <path>]
                  |
                  |      Just update/install Sireum OSATE plugins
                  |
                  |or: phantom [<options>] [<project-directory>]
                  |
                  |      Generate AIR.  Either:
                  |        - point to a directory containing a .project or .system file, or
                  |        - populate the 'projects', 'main-package', and 'sys-impl' options""".render}
          |
          |Available Options:
          |-u, --update             Update (or install) Sireum OSATE plugins
          |-o, --osate              Existing OSATE installation path, otherwise an
          |                           internal version of OSATE will be used (expects a
          |                           path)
          |-m, --mode               Serialization method (expects one of { json, msgpack
          |                           }; default: json)
          |-p, --projects           OSATE project directories, each must contain an OSATE
          |                           '.project' file (expects path strings)
          |-a, --main-package       AADL main package file that contains a system
          |                           implementation. (expects a path)
          |-s, --sys-impl           Name of the system implementation. (expects a string)
          |-f, --output-file        AIR output file path (expects a path)
          |-h, --help               Display this information""".render

    var update: B = false
    var osate: Option[String] = None[String]()
    var mode: PhantomMode.Type = PhantomMode.Json
    var projects: ISZ[String] = ISZ[String]()
    var main: Option[String] = None[String]()
    var impl: Option[String] = None[String]()
    var output: Option[String] = None[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-u" || arg == "--update") {
           val o: Option[B] = { j = j - 1; Some(!update) }
           o match {
             case Some(v) => update = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--osate") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => osate = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--mode") {
           val o: Option[PhantomMode.Type] = parsePhantomMode(args, j + 1)
           o match {
             case Some(v) => mode = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--projects") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => projects = v
             case _ => return None()
           }
         } else if (arg == "-a" || arg == "--main-package") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => main = v
             case _ => return None()
           }
         } else if (arg == "-s" || arg == "--sys-impl") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => impl = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--output-file") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => output = v
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
    return Some(PhantomOption(help, parseArguments(args, j), update, osate, mode, projects, main, impl, output))
  }

  def parseLogika(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Logika Toolset for Slang
            |
            |Available modes:
            |verifier                 Logika verifier""".render
      )
      return Some(HelpOption())
    }
    val opt = select("logika", args, i, ISZ("verifier"))
    opt match {
      case Some(string"verifier") => parseLogikaVerifier(args, i + 1)
      case _ => return None()
    }
  }

  def parseLogikaSolverH(arg: String): Option[LogikaSolver.Type] = {
    arg.native match {
      case "all" => return Some(LogikaSolver.All)
      case "cvc4" => return Some(LogikaSolver.Cvc4)
      case "z3" => return Some(LogikaSolver.Z3)
      case s =>
        eprintln(s"Expecting one of the following: { all, cvc4, z3 }, but found '$s'.")
        return None()
    }
  }

  def parseLogikaSolver(args: ISZ[String], i: Z): Option[LogikaSolver.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { all, cvc4, z3 }, but none found.")
      return None()
    }
    val r = parseLogikaSolverH(args(i))
    return r
  }

  def parseLogikaVerifier(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Logika Verifier for Slang
          |
          |Usage: <option>* [<slang-file>]
          |
          |Available Options:
          |    --sat                Enable assumption satisfiability checking
          |-s, --sourcepath         Sourcepath of Slang .scala files (expects path
          |                           strings)
          |    --unroll             Enable loop unrolling when loop modifies clause is
          |                           unspecified
          |-h, --help               Display this information
          |
          |Bit-width Options:
          |    --c-bitwidth         Bit-width representation for C (character) values
          |                           (expected 8, 16, or 32) (expects an integer; default
          |                           is 32)
          |    --z-bitwidth         Bit-width representation for Z (integer) values
          |                           (expected 0, 8, 16, 32, 64) (expects an integer;
          |                           default is 0)
          |
          |SMT2 Options:
          |    --simplify           Simplify SMT2 query
          |-m, --solver             Smt2 solver (expects one of { all, cvc4, z3 };
          |                           default: all)
          |-t, --timeout            Timeout (seconds) for SMT2 solver (expects an integer;
          |                           default is 2)
          |
          |Optimizations Options:
          |-p, --par                Enable parallelization
          |    --ram-folder         RAM folder to temporarily store various artifacts
          |                           (e.g., SMT2 solvers) (expects a path)
          |
          |Path Splitting Options:
          |    --dont-split-pfq     Do not force splitting in quantifiers and proof
          |                           functions derived from @strictpure methods
          |    --split-all          Split all
          |    --split-contract     Split on contract cases
          |    --split-if           Split on if-conditional expressions and statements
          |    --split-match        Split on match expressions and statements
          |
          |Logging Options:
          |    --log-pc             Display path conditions before each statement
          |    --log-raw-pc         Display raw path conditions before each statement
          |    --log-vc             Display all verification conditions
          |    --log-vc-dir         Write all verification conditions in a directory
          |                           (expects a path)""".render

    var sat: B = false
    var sourcepath: ISZ[String] = ISZ[String]()
    var unroll: B = false
    var charBitWidth: Z = 32
    var intBitWidth: Z = 0
    var simplify: B = false
    var solver: LogikaSolver.Type = LogikaSolver.All
    var timeout: Z = 2
    var par: B = false
    var ramFolder: Option[String] = None[String]()
    var dontSplitFunQuant: B = false
    var splitAll: B = false
    var splitContract: B = false
    var splitIf: B = false
    var splitMatch: B = false
    var logPc: B = false
    var logRawPc: B = false
    var logVc: B = false
    var logVcDir: Option[String] = None[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--sat") {
           val o: Option[B] = { j = j - 1; Some(!sat) }
           o match {
             case Some(v) => sat = v
             case _ => return None()
           }
         } else if (arg == "-s" || arg == "--sourcepath") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => sourcepath = v
             case _ => return None()
           }
         } else if (arg == "--unroll") {
           val o: Option[B] = { j = j - 1; Some(!unroll) }
           o match {
             case Some(v) => unroll = v
             case _ => return None()
           }
         } else if (arg == "--c-bitwidth") {
           val o: Option[Z] = parseNum(args, j + 1, None(), None())
           o match {
             case Some(v) => charBitWidth = v
             case _ => return None()
           }
         } else if (arg == "--z-bitwidth") {
           val o: Option[Z] = parseNum(args, j + 1, None(), None())
           o match {
             case Some(v) => intBitWidth = v
             case _ => return None()
           }
         } else if (arg == "--simplify") {
           val o: Option[B] = { j = j - 1; Some(!simplify) }
           o match {
             case Some(v) => simplify = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--solver") {
           val o: Option[LogikaSolver.Type] = parseLogikaSolver(args, j + 1)
           o match {
             case Some(v) => solver = v
             case _ => return None()
           }
         } else if (arg == "-t" || arg == "--timeout") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => timeout = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--par") {
           val o: Option[B] = { j = j - 1; Some(!par) }
           o match {
             case Some(v) => par = v
             case _ => return None()
           }
         } else if (arg == "--ram-folder") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => ramFolder = v
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
         } else if (arg == "--log-pc") {
           val o: Option[B] = { j = j - 1; Some(!logPc) }
           o match {
             case Some(v) => logPc = v
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
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(LogikaVerifierOption(help, parseArguments(args, j), sat, sourcepath, unroll, charBitWidth, intBitWidth, simplify, solver, timeout, par, ramFolder, dontSplitFunQuant, splitAll, splitContract, splitIf, splitMatch, logPc, logRawPc, logVc, logVcDir))
  }

  def parseSlang(args: ISZ[String], i: Z): Option[SireumTopOption] = {
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
      case Some(string"run") => parseSlangRun(args, i + 1)
      case Some(string"tipe") => parseSlangTipe(args, i + 1)
      case Some(string"transpilers") => parseTranspilers(args, i + 1)
      case _ => return None()
    }
  }

  def parseSlangRun(args: ISZ[String], i: Z): Option[SireumTopOption] = {
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
    return Some(SlangRunOption(help, parseArguments(args, j), input, output, transformed, nativ))
  }

  def parseSlangTipe(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Slang Type Checker
          |
          |Usage: <option>* [<slang-file>]
          |
          |Available Options:
          |-s, --sourcepath         Sourcepath of Slang .scala files (expects path
          |                           strings)
          |-o, --outline            Perform type outlining only for files in the
          |                           sourcepath
          |-f, --force              Fully qualified names of traits, classes, and objects
          |                           to force full type checking on when type outlining
          |                           is enabled (expects a string separated by ",")
          |    --verbose            Enable verbose mode
          |-r, --no-runtime         Do not use built-in runtime (use runtime in
          |                           sourcepath)
          |-x, --exclude            Sourcepath exclusion as URI segment (expects a string
          |                           separated by ",")
          |-h, --help               Display this information
          |
          |Persistence Options:
          |    --save               Path to save type information to (outline should not
          |                           be enabled) (expects a path)
          |    --load               Path to load type information from (expects a path)
          |-z, --no-gzip            Disable gzip compression when saving and/or loading""".render

    var sourcepath: ISZ[String] = ISZ[String]()
    var outline: B = false
    var force: ISZ[String] = ISZ[String]()
    var verbose: B = false
    var noRuntime: B = false
    var exclude: ISZ[String] = ISZ[String]()
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
        } else if (arg == "-s" || arg == "--sourcepath") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => sourcepath = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--outline") {
           val o: Option[B] = { j = j - 1; Some(!outline) }
           o match {
             case Some(v) => outline = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--force") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => force = v
             case _ => return None()
           }
         } else if (arg == "--verbose") {
           val o: Option[B] = { j = j - 1; Some(!verbose) }
           o match {
             case Some(v) => verbose = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--no-runtime") {
           val o: Option[B] = { j = j - 1; Some(!noRuntime) }
           o match {
             case Some(v) => noRuntime = v
             case _ => return None()
           }
         } else if (arg == "-x" || arg == "--exclude") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => exclude = v
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
    return Some(SlangTipeOption(help, parseArguments(args, j), sourcepath, outline, force, verbose, noRuntime, exclude, save, load, gzip))
  }

  def parseTranspilers(args: ISZ[String], i: Z): Option[SireumTopOption] = {
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
      case Some(string"c") => parseCTranspiler(args, i + 1)
      case _ => return None()
    }
  }

  def parseCTranspiler(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Slang Embedded To C Transpiler
          |
          |Usage: <option>* ( <slang-file> )*
          |
          |Available Options:
          |-s, --sourcepath         Sourcepath of Slang .scala files (expects path
          |                           strings)
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
          |                           integer; default is 3)
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
    return Some(CTranspilerOption(help, parseArguments(args, j), sourcepath, output, verbose, apps, bitWidth, projectName, stackSize, customArraySizes, maxArraySize, maxStringSize, cmakeIncludes, exts, libOnly, excludeBuild, plugins, fingerprint, stableTypeId, unroll, save, load, customConstants, forwarding))
  }

  def parseTools(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum Utility Tools
            |
            |Available modes:
            |bcgen                    Bit encoder/decoder generator
            |checkstack               Native function stack size check tool
            |cligen                   Command-line interface (CLI) generator
            |ivegen                   Sireum IVE project generator
            |sergen                   De/Serializer generator
            |transgen                 Transformer (visitor/rewriter) generator""".render
      )
      return Some(HelpOption())
    }
    val opt = select("tools", args, i, ISZ("bcgen", "checkstack", "cligen", "ivegen", "sergen", "transgen"))
    opt match {
      case Some(string"bcgen") => parseBcgen(args, i + 1)
      case Some(string"checkstack") => parseCheckstack(args, i + 1)
      case Some(string"cligen") => parseCligen(args, i + 1)
      case Some(string"ivegen") => parseIvegen(args, i + 1)
      case Some(string"sergen") => parseSergen(args, i + 1)
      case Some(string"transgen") => parseTransgen(args, i + 1)
      case _ => return None()
    }
  }

  def parseBitCodecModeH(arg: String): Option[BitCodecMode.Type] = {
    arg.native match {
      case "program" => return Some(BitCodecMode.Program)
      case "script" => return Some(BitCodecMode.Script)
      case "json" => return Some(BitCodecMode.Json)
      case "dot" => return Some(BitCodecMode.Dot)
      case s =>
        eprintln(s"Expecting one of the following: { program, script, json, dot }, but found '$s'.")
        return None()
    }
  }

  def parseBitCodecMode(args: ISZ[String], i: Z): Option[BitCodecMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { program, script, json, dot }, but none found.")
      return None()
    }
    val r = parseBitCodecModeH(args(i))
    return r
  }

  def parseBitCodecModes(args: ISZ[String], i: Z): Option[ISZ[BitCodecMode.Type]] = {
    val tokensOpt = tokenize(args, i, "BitCodecMode", ',', T)
    if (tokensOpt.isEmpty) {
      return None()
    }
    var r = ISZ[BitCodecMode.Type]()
    for (token <- tokensOpt.get) {
      val e = parseBitCodecModeH(token)
      e match {
        case Some(v) => r = r :+ v
        case _ => return None()
      }
    }
    return Some(r)
  }

  def parseBcgen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
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

    var mode: ISZ[BitCodecMode.Type] = ISZ(BitCodecMode.Program)
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
           val o: Option[ISZ[BitCodecMode.Type]] = parseBitCodecModes(args, j + 1)
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
    return Some(BcgenOption(help, parseArguments(args, j), mode, isLittleEndian, isMutable, packageName, name, license, outputDir, traits))
  }

  def parseCheckStackModeH(arg: String): Option[CheckStackMode.Type] = {
    arg.native match {
      case "dotsu" => return Some(CheckStackMode.Dotsu)
      case "bin" => return Some(CheckStackMode.Bin)
      case s =>
        eprintln(s"Expecting one of the following: { dotsu, bin }, but found '$s'.")
        return None()
    }
  }

  def parseCheckStackMode(args: ISZ[String], i: Z): Option[CheckStackMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { dotsu, bin }, but none found.")
      return None()
    }
    val r = parseCheckStackModeH(args(i))
    return r
  }

  def parseCheckStackArchH(arg: String): Option[CheckStackArch.Type] = {
    arg.native match {
      case "amd64" => return Some(CheckStackArch.Amd64)
      case "x86" => return Some(CheckStackArch.X86)
      case "aarch64" => return Some(CheckStackArch.Aarch64)
      case "arm" => return Some(CheckStackArch.Arm)
      case "powerpc" => return Some(CheckStackArch.Powerpc)
      case "openrisc" => return Some(CheckStackArch.Openrisc)
      case "mips" => return Some(CheckStackArch.Mips)
      case "mips64" => return Some(CheckStackArch.Mips64)
      case "m68k" => return Some(CheckStackArch.M68k)
      case "ia64" => return Some(CheckStackArch.Ia64)
      case "nios2" => return Some(CheckStackArch.Nios2)
      case "parisc" => return Some(CheckStackArch.Parisc)
      case "s390x" => return Some(CheckStackArch.S390x)
      case "sh64" => return Some(CheckStackArch.Sh64)
      case "sparc" => return Some(CheckStackArch.Sparc)
      case s =>
        eprintln(s"Expecting one of the following: { amd64, x86, aarch64, arm, powerpc, openrisc, mips, mips64, m68k, ia64, nios2, parisc, s390x, sh64, sparc }, but found '$s'.")
        return None()
    }
  }

  def parseCheckStackArch(args: ISZ[String], i: Z): Option[CheckStackArch.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { amd64, x86, aarch64, arm, powerpc, openrisc, mips, mips64, m68k, ia64, nios2, parisc, s390x, sh64, sparc }, but none found.")
      return None()
    }
    val r = parseCheckStackArchH(args(i))
    return r
  }

  def parseCheckStackFormatH(arg: String): Option[CheckStackFormat.Type] = {
    arg.native match {
      case "plain" => return Some(CheckStackFormat.Plain)
      case "csv" => return Some(CheckStackFormat.Csv)
      case "html" => return Some(CheckStackFormat.Html)
      case "md" => return Some(CheckStackFormat.Md)
      case "rst" => return Some(CheckStackFormat.Rst)
      case s =>
        eprintln(s"Expecting one of the following: { plain, csv, html, md, rst }, but found '$s'.")
        return None()
    }
  }

  def parseCheckStackFormat(args: ISZ[String], i: Z): Option[CheckStackFormat.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { plain, csv, html, md, rst }, but none found.")
      return None()
    }
    val r = parseCheckStackFormatH(args(i))
    return r
  }

  def parseCheckstack(args: ISZ[String], i: Z): Option[SireumTopOption] = {
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

    var mode: CheckStackMode.Type = CheckStackMode.Dotsu
    var objdump: Option[String] = Some("objdump")
    var arch: CheckStackArch.Type = CheckStackArch.Amd64
    var format: CheckStackFormat.Type = CheckStackFormat.Plain
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-m" || arg == "--mode") {
           val o: Option[CheckStackMode.Type] = parseCheckStackMode(args, j + 1)
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
           val o: Option[CheckStackArch.Type] = parseCheckStackArch(args, j + 1)
           o match {
             case Some(v) => arch = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--format") {
           val o: Option[CheckStackFormat.Type] = parseCheckStackFormat(args, j + 1)
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
    return Some(CheckstackOption(help, parseArguments(args, j), mode, objdump, arch, format))
  }

  def parseCligen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum CLI Generator
          |
          |Usage: <option>* <config-file>
          |
          |Available Options:
          |-p, --package            Package name for the CLI processor (expects a string
          |                           separated by "."; default is "cli")
          |-n, --name               Type simple name for the CLI @record class processor
          |                           (expects a string; default is "Cli")
          |-w, --width              First (key) column (default: 25) and second column
          |                           (default: 55) max width (expects an int-list
          |                           separated by ',')
          |-l, --license            License file to be inserted in the file header
          |                           (expects a path)
          |-o, --output-dir         Output directory for the generated CLI Slang file
          |                           (expects a path; default is ".")
          |-h, --help               Display this information""".render

    var packageName: ISZ[String] = ISZ("cli")
    var name: Option[String] = Some("Cli")
    var width: ISZ[Z] = ISZ()
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
         } else if (arg == "-w" || arg == "--width") {
           val o: Option[ISZ[Z]] = parseNums(args, j + 1, ',', Some(0), None())
           o match {
             case Some(v) => width = v
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
    return Some(CligenOption(help, parseArguments(args, j), packageName, name, width, license, outputDir))
  }

  def parseIveModeH(arg: String): Option[IveMode.Type] = {
    arg.native match {
      case "idea" => return Some(IveMode.Idea)
      case "mill" => return Some(IveMode.Mill)
      case s =>
        eprintln(s"Expecting one of the following: { idea, mill }, but found '$s'.")
        return None()
    }
  }

  def parseIveMode(args: ISZ[String], i: Z): Option[IveMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { idea, mill }, but none found.")
      return None()
    }
    val r = parseIveModeH(args(i))
    return r
  }

  def parseIvegen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
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
    var mode: IveMode.Type = IveMode.Idea
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
           val o: Option[IveMode.Type] = parseIveMode(args, j + 1)
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
    return Some(IvegenOption(help, parseArguments(args, j), jdk, mode, projectName, moduleName, packageName, appName, millPath, force, compile))
  }

  def parseSerializerModeH(arg: String): Option[SerializerMode.Type] = {
    arg.native match {
      case "json" => return Some(SerializerMode.Json)
      case "msgpack" => return Some(SerializerMode.Msgpack)
      case s =>
        eprintln(s"Expecting one of the following: { json, msgpack }, but found '$s'.")
        return None()
    }
  }

  def parseSerializerMode(args: ISZ[String], i: Z): Option[SerializerMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { json, msgpack }, but none found.")
      return None()
    }
    val r = parseSerializerModeH(args(i))
    return r
  }

  def parseSerializerModes(args: ISZ[String], i: Z): Option[ISZ[SerializerMode.Type]] = {
    val tokensOpt = tokenize(args, i, "SerializerMode", ',', T)
    if (tokensOpt.isEmpty) {
      return None()
    }
    var r = ISZ[SerializerMode.Type]()
    for (token <- tokensOpt.get) {
      val e = parseSerializerModeH(token)
      e match {
        case Some(v) => r = r :+ v
        case _ => return None()
      }
    }
    return Some(r)
  }

  def parseSergen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
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

    var modes: ISZ[SerializerMode.Type] = ISZ(SerializerMode.Json)
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
           val o: Option[ISZ[SerializerMode.Type]] = parseSerializerModes(args, j + 1)
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
    return Some(SergenOption(help, parseArguments(args, j), modes, packageName, name, license, outputDir))
  }

  def parseTransformerModeH(arg: String): Option[TransformerMode.Type] = {
    arg.native match {
      case "immutable" => return Some(TransformerMode.Immutable)
      case "mutable" => return Some(TransformerMode.Mutable)
      case s =>
        eprintln(s"Expecting one of the following: { immutable, mutable }, but found '$s'.")
        return None()
    }
  }

  def parseTransformerMode(args: ISZ[String], i: Z): Option[TransformerMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { immutable, mutable }, but none found.")
      return None()
    }
    val r = parseTransformerModeH(args(i))
    return r
  }

  def parseTransformerModes(args: ISZ[String], i: Z): Option[ISZ[TransformerMode.Type]] = {
    val tokensOpt = tokenize(args, i, "TransformerMode", ',', T)
    if (tokensOpt.isEmpty) {
      return None()
    }
    var r = ISZ[TransformerMode.Type]()
    for (token <- tokensOpt.get) {
      val e = parseTransformerModeH(token)
      e match {
        case Some(v) => r = r :+ v
        case _ => return None()
      }
    }
    return Some(r)
  }

  def parseTransgen(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Transformer Generator
          |
          |Usage: <option>* <slang-file>+
          |
          |Available Options:
          |-m, --modes              Transformer mode (expects one or more of { immutable,
          |                           mutable }; default: immutable)
          |-n, --name               Type simple name for the transformers (default:
          |                           "Transformer" or "MTransformer") (expects a string)
          |-l, --license            License file to be inserted in the file header
          |                           (expects a path)
          |-o, --output-dir         Output directory for the generated transformer Slang
          |                           files (expects a path; default is ".")
          |-h, --help               Display this information""".render

    var modes: ISZ[TransformerMode.Type] = ISZ(TransformerMode.Immutable)
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
           val o: Option[ISZ[TransformerMode.Type]] = parseTransformerModes(args, j + 1)
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
    return Some(TransgenOption(help, parseArguments(args, j), modes, name, license, outputDir))
  }

  def parseX(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum eXperimental
            |
            |Available modes:
            |server                   Sireum server""".render
      )
      return Some(HelpOption())
    }
    val opt = select("x", args, i, ISZ("server"))
    opt match {
      case Some(string"server") => parseServer(args, i + 1)
      case _ => return None()
    }
  }

  def parseServerMessageH(arg: String): Option[ServerMessage.Type] = {
    arg.native match {
      case "msgpack" => return Some(ServerMessage.Msgpack)
      case "json" => return Some(ServerMessage.Json)
      case s =>
        eprintln(s"Expecting one of the following: { msgpack, json }, but found '$s'.")
        return None()
    }
  }

  def parseServerMessage(args: ISZ[String], i: Z): Option[ServerMessage.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { msgpack, json }, but none found.")
      return None()
    }
    val r = parseServerMessageH(args(i))
    return r
  }

  def parseServer(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum Server
          |
          |Usage: <option>*
          |
          |Available Options:
          |-m, --message            Message format (expects one of { msgpack, json };
          |                           default: msgpack)
          |-l, --logika             Number of Logika workers (expects an integer; default
          |                           is 1)
          |-h, --help               Display this information""".render

    var message: ServerMessage.Type = ServerMessage.Msgpack
    var logika: Z = 1
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-m" || arg == "--message") {
           val o: Option[ServerMessage.Type] = parseServerMessage(args, j + 1)
           o match {
             case Some(v) => message = v
             case _ => return None()
           }
         } else if (arg == "-l" || arg == "--logika") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), None())
           o match {
             case Some(v) => logika = v
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
    return Some(ServerOption(help, parseArguments(args, j), message, logika))
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
          parseNumH(arg, minOpt, maxOpt) match {
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
    return parseNumH(args(i), minOpt, maxOpt)
  }

  def parseNumH(arg: String, minOpt: Option[Z], maxOpt: Option[Z]): Option[Z] = {
    Z(arg) match {
      case Some(n) =>
        minOpt match {
          case Some(min) =>
            if (n < min) {
              eprintln(s"Expecting an integer at least $min, but found $n.")
              return None()
            }
          case _ =>
        }
        maxOpt match {
          case Some(max) =>
            if (n > max) {
              eprintln(s"Expecting an integer at most $max, but found $n.")
              return None()
            }
            return Some(n)
          case _ =>
        }
        return Some(n)
      case _ =>
        eprintln(s"Expecting an integer, but found '$arg'.")
        return None()
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