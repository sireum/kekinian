// #Sireum
// @formatter:off

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

// This file is auto-generated from cli.sc

package org.sireum

import org.sireum._

object Cli {

  @datatype trait SireumTopOption

  @datatype class HelpOption extends SireumTopOption

  @enum object ActFormat {
    'Air
    'Camkesir
    'Aadl
  }

  @enum object ActMode {
    'Json
    'Msgpack
  }

  @datatype class ActOption(
    help: String,
    args: ISZ[String],
    input: ActFormat.Type,
    mode: ActMode.Type,
    outputDir: Option[String],
    auxDirs: ISZ[String],
    aadlRootDir: Option[String]
  ) extends SireumTopOption

  @enum object PhantomMode {
    'Json
    'Msgpack
  }

  @datatype class PhantomOption(
    help: String,
    args: ISZ[String],
    mode: PhantomMode.Type,
    osate: Option[String],
    projects: ISZ[String],
    main: Option[String],
    output: Option[String]
  ) extends SireumTopOption

  @datatype class SlangRunOption(
    help: String,
    args: ISZ[String],
    input: Option[String],
    output: Option[String],
    server: B,
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
    projectName: Option[String],
    apps: ISZ[String],
    unroll: B,
    fingerprint: Z,
    bitWidth: Z,
    maxStringSize: Z,
    maxArraySize: Z,
    customArraySizes: ISZ[String],
    customConstants: ISZ[String],
    plugins: ISZ[String],
    exts: ISZ[String],
    forwarding: ISZ[String],
    stackSize: Option[String],
    excludeBuild: ISZ[String],
    save: Option[String],
    load: Option[String]
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
    packageName: ISZ[String],
    name: Option[String],
    license: Option[String],
    outputDir: Option[String]
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
}

import Cli._

@record class Cli(pathSep: C) {

  def parseSireum(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum: A High-Assurance System Engineering Platform
            |(c) 2019, SAnToS Laboratory, Kansas State University
            |
            |Available modes:
            |hamr                     HAMR Tools
            |slang                    Slang tools
            |tools                    Utility tools""".render
      )
      return Some(HelpOption())
    }
    val opt = select("sireum", args, i, ISZ("hamr", "slang", "tools"))
    opt match {
      case Some(string"hamr") => parseHamr(args, i + 1)
      case Some(string"slang") => parseSlang(args, i + 1)
      case Some(string"tools") => parseTools(args, i + 1)
      case _ => return None()
    }
  }

  def parseHamr(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""HAMR: High-Assurance Model-based Rapid-engineering tools for embedded systems
            |
            |Available modes:
            |act                      AADL to CAmkES translator
            |phantom                 """.render
      )
      return Some(HelpOption())
    }
    val opt = select("hamr", args, i, ISZ("act", "phantom"))
    opt match {
      case Some(string"act") => parseAct(args, i + 1)
      case Some(string"phantom") => parsePhantom(args, i + 1)
      case _ => return None()
    }
  }

  def parseActFormatH(arg: String): Option[ActFormat.Type] = {
    arg.native match {
      case "air" => return Some(ActFormat.Air)
      case "camkesir" => return Some(ActFormat.Camkesir)
      case "aadl" => return Some(ActFormat.Aadl)
      case s =>
        eprintln(s"Expecting one of the following: { air, camkesir, aadl }, but found '$s'.")
        return None()
    }
  }

  def parseActFormat(args: ISZ[String], i: Z): Option[ActFormat.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { air, camkesir, aadl }, but none found.")
      return None()
    }
    val r = parseActFormatH(args(i))
    return r
  }

  def parseActModeH(arg: String): Option[ActMode.Type] = {
    arg.native match {
      case "json" => return Some(ActMode.Json)
      case "msgpack" => return Some(ActMode.Msgpack)
      case s =>
        eprintln(s"Expecting one of the following: { json, msgpack }, but found '$s'.")
        return None()
    }
  }

  def parseActMode(args: ISZ[String], i: Z): Option[ActMode.Type] = {
    if (i >= args.size) {
      eprintln("Expecting one of the following: { json, msgpack }, but none found.")
      return None()
    }
    val r = parseActModeH(args(i))
    return r
  }

  def parseAct(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    val help =
      st"""Sireum ACT: An AADL-to-CAmkES Translator
          |
          |Usage: <option>* <file>+
          |
          |Available Options:
          |-i, --input              Input format (expects one of { air, camkesir, aadl };
          |                           default: air)
          |-m, --mode               Serialization method (only valid for air/camkesir
          |                           input (expects one of { json, msgpack }; default:
          |                           json)
          |-o, --output-dir         Output directory for the generated project files
          |                           (expects a path; default is ".")
          |-a, --aux-directories    
          |                          Directories containing C files to be included in
          |                           build (expects path strings)
          |-r, --root-dir            (expects a path)
          |-h, --help               Display this information""".render

    var input: ActFormat.Type = ActFormat.Air
    var mode: ActMode.Type = ActMode.Json
    var outputDir: Option[String] = Some(".")
    var auxDirs: ISZ[String] = ISZ[String]()
    var aadlRootDir: Option[String] = None[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-i" || arg == "--input") {
           val o: Option[ActFormat.Type] = parseActFormat(args, j + 1)
           o match {
             case Some(v) => input = v
             case _ => return None()
           }
         } else if (arg == "-m" || arg == "--mode") {
           val o: Option[ActMode.Type] = parseActMode(args, j + 1)
           o match {
             case Some(v) => mode = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => outputDir = v
             case _ => return None()
           }
         } else if (arg == "-a" || arg == "--aux-directories") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => auxDirs = v
             case _ => return None()
           }
         } else if (arg == "-r" || arg == "--root-dir") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => aadlRootDir = v
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
    return Some(ActOption(help, parseArguments(args, j), input, mode, outputDir, auxDirs, aadlRootDir))
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
          |Usage: <option>* <system-name>
          |
          |Available Options:
          |-m, --mode               Serialization method (expects one of { json, msgpack
          |                           }; default: json)
          |-e, --osate              OSATE installation path (expects a path)
          |-p, --projects           OSATE project folders (expects path strings; default
          |                           is ".")
          |-a, --main-package       AADL main package file (expects a string)
          |-o, --output             AIR output file path (expects a path)
          |-h, --help               Display this information""".render

    var mode: PhantomMode.Type = PhantomMode.Json
    var osate: Option[String] = None[String]()
    var projects: ISZ[String] = ISZ(".")
    var main: Option[String] = None[String]()
    var output: Option[String] = None[String]()
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "-m" || arg == "--mode") {
           val o: Option[PhantomMode.Type] = parsePhantomMode(args, j + 1)
           o match {
             case Some(v) => mode = v
             case _ => return None()
           }
         } else if (arg == "-e" || arg == "--osate") {
           val o: Option[Option[String]] = parsePath(args, j + 1)
           o match {
             case Some(v) => osate = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--projects") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => projects = v
             case _ => return None()
           }
         } else if (arg == "-a" || arg == "--main-package") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => main = v
             case _ => return None()
           }
         } else if (arg == "-o" || arg == "--output") {
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
    return Some(PhantomOption(help, parseArguments(args, j), mode, osate, projects, main, output))
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
          |-s, --no-server          Disable Scala compile server
          |-t, --transformed        Show Scala transformed tree
          |-n, --native             Generate native executable
          |-h, --help               Display this information""".render

    var input: Option[String] = None[String]()
    var output: Option[String] = None[String]()
    var server: B = false
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
         } else if (arg == "-s" || arg == "--no-server") {
           val o: Option[B] = { j = j - 1; Some(!server) }
           o match {
             case Some(v) => server = v
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
    return Some(SlangRunOption(help, parseArguments(args, j), input, output, server, transformed, nativ))
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
    return Some(SlangTipeOption(help, parseArguments(args, j), sourcepath, outline, force, verbose, noRuntime, save, load, gzip))
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
          |-n, --name               Project name (expects a string; default is "main")
          |-a, --apps               @app fully qualified names (expects a string separated
          |                           by ",")
          |-u, --unroll             Enable for-loop unrolling
          |-f, --fingerprint        Generic entity fingerprinting size (expects an
          |                           integer; default is 3)
          |-b, --bits               Default bit-width for unbounded integer types (e.g.,
          |                           Z) (expects one of { 64, 32, 16, 8 })
          |    --string-size        Maximum string size (expects an integer; default is
          |                           100)
          |    --sequence-size      Default maximum sequence size (expects an integer;
          |                           default is 100)
          |-q, --sequence           Custom maximum sequence sizes, each in the form of
          |                           <type>=<size>, where <type> is either IS[,], MS[,],
          |                           ISZ[], MSZ[], or ZS with fully qualified index and
          |                           element types where applicable (expects a string
          |                           separated by ";")
          |-c, --constants          Custom constant for object variables, each in the form
          |                           of <name>=<lit>, where <name> is a qualified name of
          |                           an object var and <lit> is a Slang literal
          |                           expression (expects a string separated by ";")
          |-p, --plugins            Plugin fully qualified names (expects a string
          |                           separated by ",")
          |-e, --exts               Extension file paths (expects path strings)
          |-w, --forward            Object forwarding, each in form of <name>=<name>,
          |                           where <name> is a fully qualified name of an object
          |                           (expects a string separated by ",")
          |-z, --stack-size         Maximum stack size in bytes (expects a string; default
          |                           is "16 * 1024 * 1024")
          |-x, --exclude-build      Type/method fully qualified names to exclude in the
          |                           generated CMake file (expects a string separated by
          |                           ",")
          |
          |Persistence Options:
          |    --save               Path to save type information to (outline should not
          |                           be enabled) (expects a path)
          |    --load               Path to load type information from (expects a path)""".render

    var sourcepath: ISZ[String] = ISZ[String]()
    var output: Option[String] = Some("out")
    var verbose: B = false
    var projectName: Option[String] = Some("main")
    var apps: ISZ[String] = ISZ[String]()
    var unroll: B = false
    var fingerprint: Z = 3
    var bitWidth: Z = 64
    var maxStringSize: Z = 100
    var maxArraySize: Z = 100
    var customArraySizes: ISZ[String] = ISZ[String]()
    var customConstants: ISZ[String] = ISZ[String]()
    var plugins: ISZ[String] = ISZ[String]()
    var exts: ISZ[String] = ISZ[String]()
    var forwarding: ISZ[String] = ISZ[String]()
    var stackSize: Option[String] = Some("16 * 1024 * 1024")
    var excludeBuild: ISZ[String] = ISZ[String]()
    var save: Option[String] = None[String]()
    var load: Option[String] = None[String]()
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
         } else if (arg == "-n" || arg == "--name") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => projectName = v
             case _ => return None()
           }
         } else if (arg == "-a" || arg == "--apps") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => apps = v
             case _ => return None()
           }
         } else if (arg == "-u" || arg == "--unroll") {
           val o: Option[B] = { j = j - 1; Some(!unroll) }
           o match {
             case Some(v) => unroll = v
             case _ => return None()
           }
         } else if (arg == "-f" || arg == "--fingerprint") {
           val o: Option[Z] = parseNum(args, j + 1, Some(1), Some(64))
           o match {
             case Some(v) => fingerprint = v
             case _ => return None()
           }
         } else if (arg == "-b" || arg == "--bits") {
           val o: Option[Z] = parseNumChoice(args, j + 1, ISZ(z"64", z"32", z"16", z"8"))
           o match {
             case Some(v) => bitWidth = v
             case _ => return None()
           }
         } else if (arg == "--string-size") {
           val o: Option[Z] = parseNum(args, j + 1, None(), None())
           o match {
             case Some(v) => maxStringSize = v
             case _ => return None()
           }
         } else if (arg == "--sequence-size") {
           val o: Option[Z] = parseNum(args, j + 1, None(), None())
           o match {
             case Some(v) => maxArraySize = v
             case _ => return None()
           }
         } else if (arg == "-q" || arg == "--sequence") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ';')
           o match {
             case Some(v) => customArraySizes = v
             case _ => return None()
           }
         } else if (arg == "-c" || arg == "--constants") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ';')
           o match {
             case Some(v) => customConstants = v
             case _ => return None()
           }
         } else if (arg == "-p" || arg == "--plugins") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => plugins = v
             case _ => return None()
           }
         } else if (arg == "-e" || arg == "--exts") {
           val o: Option[ISZ[String]] = parsePaths(args, j + 1)
           o match {
             case Some(v) => exts = v
             case _ => return None()
           }
         } else if (arg == "-w" || arg == "--forward") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => forwarding = v
             case _ => return None()
           }
         } else if (arg == "-z" || arg == "--stack-size") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => stackSize = v
             case _ => return None()
           }
         } else if (arg == "-x" || arg == "--exclude-build") {
           val o: Option[ISZ[String]] = parseStrings(args, j + 1, ',')
           o match {
             case Some(v) => excludeBuild = v
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
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(CTranspilerOption(help, parseArguments(args, j), sourcepath, output, verbose, projectName, apps, unroll, fingerprint, bitWidth, maxStringSize, maxArraySize, customArraySizes, customConstants, plugins, exts, forwarding, stackSize, excludeBuild, save, load))
  }

  def parseTools(args: ISZ[String], i: Z): Option[SireumTopOption] = {
    if (i >= args.size) {
      println(
        st"""Sireum Utility Tools
            |
            |Available modes:
            |bcgen                    Bit encoder/decoder generator
            |cligen                   Command-line interface (CLI) generator
            |ivegen                   Sireum IVE project generator
            |sergen                   De/Serializer generator
            |transgen                 Transformer (visitor/rewriter) generator""".render
      )
      return Some(HelpOption())
    }
    val opt = select("tools", args, i, ISZ("bcgen", "cligen", "ivegen", "sergen", "transgen"))
    opt match {
      case Some(string"bcgen") => parseBcgen(args, i + 1)
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
          |-p, --package            Package name for the codec (expects a string separated
          |                           by ".")
          |-n, --name               Object and filename for the codec (script always uses
          |                           BitCodec as the object name) (expects a string;
          |                           default is "BitCodec")
          |-l, --license            License file to be inserted in the file header
          |                           (expects a path)
          |-o, --output-dir         Output directory for the generated codec files
          |                           (expects a path; default is ".")
          |-h, --help               Display this information""".render

    var mode: ISZ[BitCodecMode.Type] = ISZ(BitCodecMode.Program)
    var isLittleEndian: B = false
    var packageName: ISZ[String] = ISZ[String]()
    var name: Option[String] = Some("BitCodec")
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
    return Some(BcgenOption(help, parseArguments(args, j), mode, isLittleEndian, packageName, name, license, outputDir))
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