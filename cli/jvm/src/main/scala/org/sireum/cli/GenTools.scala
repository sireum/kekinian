// #Sireum
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

package org.sireum.cli

import org.sireum._
import org.sireum.bitcodec.Spec
import org.sireum.lang._
import org.sireum.lang.parser.Parser
import org.sireum.message._
import org.sireum.tools._
import org.sireum.SireumApi._
import org.sireum.lang.tipe.TypeHierarchy
import org.sireum.parser.json.{AST, ParserUtil}

object GenTools {


  def bcGen(o: Cli.SireumToolsBcgenOption, reporter: Reporter): Z = {
    o.args.size match {
      case z"0" => println(o.help); return 0
      case z"1" =>
      case _ => println(s"Expecting one argument, but found ${o.args.size}."); return -1
    }

    val lOpt = path2fileOpt("license file", o.license, T)
    val src = paths2fileOpt("config file", o.args, T).get
    val destDir = path2fileOpt("output directory", o.outputDir, T).get
    if (!destDir.isDir) {
      eprintln(s"Path $destDir is not a directory")
      return -1
    }
    val outTemp = Os.temp()
    val r = SlangRunner.run(Cli.SireumSlangRunOption("", ISZ(src.string), F, None(),
      Some(outTemp.string), F, F))
    if (r != 0) {
      eprintln(outTemp.read)
      return r
    }
    val text = src.read
    Parser.parseTopUnit[ast.TopUnit.Program](text, T, F, Some(src.string), reporter) match {
      case Some(p) if !reporter.hasError =>
        val (_, program) = FrontEnd.checkWorksheet(0, None(), p, reporter)
        if (reporter.hasError) {
          reporter.printMessages()
          return -1
        }
        val specText = outTemp.read
        outTemp.removeAll()
        Spec.fromJSON(specText) match {
          case Either.Left(spec) =>
            for (mode <- o.mode) {
              val ext: String = mode match {
                case Cli.SireumToolsBcgenBitCodecMode.Program => "scala"
                case Cli.SireumToolsBcgenBitCodecMode.Script => "sc"
                case Cli.SireumToolsBcgenBitCodecMode.Json => "json"
                case Cli.SireumToolsBcgenBitCodecMode.Dot => "dot"
              }
              val dest = destDir / s"${o.name.get}.$ext"
              val prev: String = if (dest.isFile) dest.read else ""
              val output: BitCodecGen.Output.Type = mode match {
                case Cli.SireumToolsBcgenBitCodecMode.Program => BitCodecGen.Output.Program
                case Cli.SireumToolsBcgenBitCodecMode.Script => BitCodecGen.Output.Script
                case Cli.SireumToolsBcgenBitCodecMode.Json => BitCodecGen.Output.Json
                case Cli.SireumToolsBcgenBitCodecMode.Dot => BitCodecGen.Output.Dot
              }
              val out = BitCodecGen.gen(output, !o.isLittleEndian, o.isMutable, lOpt.map((l: Os.Path) => l.read), src.name,
                o.packageName, o.name.get, text, o.traits, spec, bitcodecPrint(spec), program, prev, reporter)
              if (reporter.hasError) {
                reporter.printMessages()
                return -1
              }
              dest.writeOver(out.render)
              println(s"Wrote $dest")
            }
            if (reporter.hasIssue) {
              println()
              reporter.printMessages()
            }
            return 0
          case _ =>
            eprintln(s"Invalid config produced by running ${o.args(0)}")
            eprintln(specText)
            return -1
        }
      case _ =>
        reporter.printMessages()
        return -1
    }
  }

  def cliGen(o: Cli.SireumToolsCligenOption): Z = {
    o.args.size match {
      case z"0" => println(o.help); return 0
      case z"1" =>
      case _ =>
        eprintln(s"Expecting one argument, but found ${o.args.size}.")
        return -1
    }

    if (o.packageName.nonEmpty && o.script.nonEmpty) {
      eprintln("Package name cannot be used along with script generation")
      return -1
    }

    val lOpt = path2fileOpt("license file", o.license, T)
    val src = paths2fileOpt("config file", o.args, T).get
    val destDir = path2fileOpt("output directory", o.outputDir, T).get
    if (!destDir.isDir) {
      eprintln(s"Path $destDir is not a directory")
    }
    val dest: Os.Path = o.script match {
      case Some(script) => destDir / script
      case _ => destDir / s"${o.name.get}.scala"
    }
    val (first, second): (Z, Z) = o.width.size match {
      case z"2" => (o.width(0), o.width(1))
      case _ => (z"25", z"55")
    }
    val outTemp = Os.temp()
    val r = SlangRunner.run(Cli.SireumSlangRunOption("", ISZ(src.string), F, None(),
      Some(outTemp.string), F, F))
    if (r != 0) {
      eprintln(s"Could not run ${o.args(0)}")
      return r
    }
    val configText = outTemp.read
    outTemp.removeAll()
    JSON.toCliOpt(configText) match {
      case Either.Left(config) =>
        val out = CliGenJvm.run(lOpt, config, src, dest, o.packageName, o.name, first, second, o.reporter)
        dest.writeOver(out)
        if (dest.ext == "cmd") {
          dest.chmod("+x")
        }
        println(s"Wrote $dest")
        return 0
      case _ =>
        eprintln(s"Invalid config produced by running ${o.args(0)}")
        eprintln(configText)
        return -1
    }
  }

  def opGen(o: Cli.SireumToolsOpgenOption, reporter: Reporter): Z = {
    if (o.args.size == 0) {
      println(o.help)
      return 0
    }
    if (o.args.size != 1) {
      eprintln("Expecting a single fully-qualified type name argument")
      return -1
    }

    val dir: Os.Path = o.output match {
      case Some(v) =>
        val d = Os.path(v)
        d.mkdirAll()
        d
      case _ => Os.cwd
    }

    val to = Cli.SireumSlangTipeOption(
      help = o.help,
      args = ISZ(),
      exclude = o.exclude,
      force = o.force,
      noRuntime = o.noRuntime,
      outline = T,
      sourcepath = o.sourcepath,
      strictAliasing = o.strictAliasing,
      verbose = o.verbose,
      save = o.save,
      load = o.load,
      gzip = o.gzip,
      parseableMessages = F
    )
    val th: TypeHierarchy = SlangTipe.run(to, reporter) match {
      case Either.Left(v) => v
      case Either.Right(code) => return code
    }
    val lOpt: Option[String] = o.license match {
      case Some(v) =>
        val f = Os.path(v)
        if (f.exists && f.isFile) {
          Some(
            st"""/*
                | ${ops.StringOps(f.read).trim}
                | */""".render)
        } else {
          eprintln(s"$f is not a file")
          return -1
        }
      case _ => None()
    }
    val pOpt: Option[ISZ[String]] = if (o.packageName.nonEmpty) Some(o.packageName) else None()
    val topClassName: ISZ[String] = for (e <- ops.StringOps(o.args(0)).split((c: C) => c == '.')) yield ops.StringOps(e).trim
    val ocgen = ObjectPrinterGen(lOpt, pOpt, o.name.get, topClassName, th)
    val r = ocgen.gen(reporter)
    if (reporter.hasError) {
      reporter.printMessages()
      return -1
    }

    val f = dir / s"${ocgen.name}.scala"
    f.writeOver(r.render)
    println(s"Wrote $f")

    if (reporter.hasIssue) {
      println()
      reporter.printMessages()
    }

    return 0
  }


  def jsonSchema2SlangGen(o: Cli.SireumToolsJsonsOption, reporter: Reporter): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      return 0
    }
    val lOpt: Option[String] = path2fileOpt("license file", o.license, T) match {
      case Some(f) => Some(
        st"""/*
            | ${ops.StringOps(f.read).trim}
            | */""".render
      )
      case _ => None()
    }
    val srcs = paths2files("JSON schema file", o.args, T)
    val destDir = path2fileOpt("output directory", o.outputDir, T).get
    if (!destDir.isDir) {
      eprintln(s"Path $destDir is not a directory")
      return -1
    }
    val packageName: ISZ[String] = if (o.packageName.isEmpty) ISZ() else o.packageName
    for (src <- srcs) {
      val name: String = o.name match {
        case Some(n) => n
        case _ =>
          val fn = src.name
          val j = ops.StringOps(fn).lastIndexOf('.')
          s"${ops.StringOps(if (j >= 0) ops.StringOps(fn).substring(0, j) else fn).firstToUpper}Binding"
      }
      val uriOpt = Option.some(src.toUri)
      val tree: AST.Obj = ParserUtil.build(uriOpt, src.read, reporter) match {
        case Some(t: AST.Obj) => t
        case _ =>
          return -1
      }
      JsonSchema2SlangGen.gen(uriOpt, lOpt, packageName, name, tree, reporter) match {
        case Some(r) =>
          val f = destDir / s"$name.scala"
          f.up.mkdirAll()
          f.writeOver(r.render)
          println(s"Wrote $f")
        case _ =>
      }
    }
    return 0
  }

  def serGen(o: Cli.SireumToolsSergenOption, reporter: Reporter): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      return 0
    }
    val lOpt = path2fileOpt("license file", o.license, T)
    val srcs = paths2files("Slang file", o.args, T)
    val destDir = path2fileOpt("output directory", o.outputDir, T).get
    if (!destDir.isDir) {
      eprintln(s"Path $destDir is not a directory")
      return -1
    }
    for (m <- o.modes) {
      val (name, mode): (String, SerializerGen.Mode.Type) = m match {
        case Cli.SireumToolsSergenSerializerMode.Json =>
          (
            if (o.modes.size > 1)
              if (o.name.isEmpty) "JSON" else s"${o.name.get}JSON"
            else if (o.name.isEmpty) "JSON"
            else o.name.get,
            SerializerGen.Mode.JSON
          )
        case Cli.SireumToolsSergenSerializerMode.Msgpack =>
          (
            if (o.modes.size > 1)
              if (o.name.isEmpty) "MsgPack" else s"${o.name.get}MsgPack"
            else if (o.name.isEmpty) "MsgPack"
            else o.name.get,
            SerializerGen.Mode.MessagePack
          )
      }
      val dest = destDir / s"$name.scala"
      val packageNameOpt: Option[ISZ[String]] = if (o.packageName.isEmpty) None() else Some(o.packageName)
      SerializerGenJvm.run(mode, lOpt, srcs, packageNameOpt, Some(name), reporter) match {
        case Some(out) =>
          dest.writeOver(out)
          println(s"Wrote $dest")
        case _ =>
          reporter.printMessages()
      }
    }
    return 0
  }

  def transGen(o: Cli.SireumToolsTrafoOption, reporter: Reporter): Z = {
    o.args.size match {
      case z"0" => println(o.help); return 0
      case _ =>
    }
    val lOpt = path2fileOpt("license file", o.license, T)
    val sources = paths2files("Slang file", o.args, T)
    val destDir = path2fileOpt("output directory", o.outputDir, T).get
    if (!destDir.isDir) {
      eprintln(s"Path $destDir is not a directory")
      return -1
    }
    for (m <- o.modes) {
      val (name, isImmutable, isReversed): (String, B, B) = m match {
        case Cli.SireumToolsTrafoTransformerMode.Immutable =>
          (
            if (o.modes.size > 1)
              if (o.name.isEmpty) "Transformer" else s"${o.name.get}Transformer"
            else if (o.name.isEmpty) "Transformer"
            else o.name.get,
            T,
            F
          )
        case Cli.SireumToolsTrafoTransformerMode.Mutable =>
          (
            if (o.modes.size > 1)
              if (o.name.isEmpty) "MTransformer" else s"M${o.name.get}Transformer"
            else if (o.name.isEmpty) "MTransformer"
            else o.name.get,
            F,
            F
          )
        case Cli.SireumToolsTrafoTransformerMode.Rimmutable =>
          (
            if (o.modes.size > 1)
              if (o.name.isEmpty) "RTransformer" else s"R${o.name.get}Transformer"
            else if (o.name.isEmpty) "RTransformer"
            else o.name.get,
            T,
            T
          )
        case Cli.SireumToolsTrafoTransformerMode.Rmutable =>
          (
            if (o.modes.size > 1)
              if (o.name.isEmpty) "RMTransformer" else s"RM${o.name.get}Transformer"
            else if (o.name.isEmpty) "RMTransformer"
            else o.name.get,
            F,
            T
          )
      }
      val dest = destDir / s"$name.scala"
      TransformerGenJvm.run(isImmutable, isReversed, lOpt, sources, Some(name), o.exclude, reporter) match {
        case Some(out) =>
          dest.writeOver(out)
          println(s"Wrote $dest")
        case _ =>
          reporter.printMessages()
      }
    }
    return 0
  }

}
