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
import org.sireum.lang.FrontEnd
import org.sireum.lang.{ast => AST}
import org.sireum.lang.parser.Parser
import org.sireum.lang.tipe._
import org.sireum.message._

import org.sireum.transpilers.c.StaticTranspiler
import org.sireum.alir.TypeSpecializer

object CTranspiler {

  val InvalidLibrary: Z = -1
  val InvalidMode: Z = -2
  val InvalidPath: Z = -3
  val InvalidFile: Z = -4
  val InvalidSources: Z = -5
  val InvalidSlangFiles: Z = -6
  val InvalidForceNames: Z = -7
  val InternalError: Z = -8
  val SavingError: Z = -9
  val LoadingError: Z = -10
  val PluginError: Z = -11
  val TranspilingError: Z = -12

  def run(o: Cli.SireumSlangTranspilersCOption, reporter: Reporter): Z = {
    def readFile(f: Os.Path): (Option[String], String) = {
      return (Some(f.toUri), f.read)
    }

    if (o.args.isEmpty && o.sourcepath.isEmpty) {
      println(o.help)
      println()
      println("Please either specify sourcepath or Slang files as argument")
      return 0
    }

    val loadFileOpt: Option[Os.Path] = if (o.load.nonEmpty) {
      val f = Os.path(o.load.get)
      if (!f.isFile) {
        eprintln("Invalid file to load type information from")
        return InvalidFile
      }
      Some(f)
    } else {
      None()
    }

    val saveFileOpt: Option[Os.Path] = if (o.save.nonEmpty) {
      val f = Os.path(o.save.get)
      if (f.exists && !f.isFile) {
        eprintln("Invalid file to save information to")
        return InvalidFile
      }
      Some(f)
    } else {
      None()
    }

    var start: Z = 0
    var used: Z = 0

    def startTime(): Unit = {
      start = extension.Time.currentMillis
    }

    def stopTime(): Unit = {
      if (o.verbose) {
        val end = extension.Time.currentMillis
        val newUsed = Os.totalMemory - Os.freeMemory
        if (newUsed > used) {
          used = newUsed
        }
        println(s"Time: ${end - start} ms, Memory: ${SireumApi.formatMb(newUsed)} MB")
      }
    }

    val begin = extension.Time.currentMillis

    if (o.verbose && o.plugins.nonEmpty) {
      println("Loading plugins ...")
      startTime()
    }

    var plugins = ISZ[StaticTranspiler.Plugin]()

    for (p <- o.plugins) {
      SireumApi.instantiate[StaticTranspiler.Plugin](p) match {
        case Some(plugin) => plugins = plugins :+ plugin
        case _ =>
          eprintln(s"Could not load plugin: $p")
          return PluginError
      }
    }

    plugins = plugins ++ ISZ[StaticTranspiler.Plugin](
      StaticTranspiler.StringConversionsExtMethodPlugin(),
      StaticTranspiler.NumberConversionsExtMethodPlugin()
    )

    if (o.verbose) {
      if (o.plugins.nonEmpty) {
        stopTime()
        println()
      }

      if (o.args.nonEmpty) {
        println("Reading Slang file arguments ...")
        startTime()
      }
    }

    var slangFiles: ISZ[(String, (Option[String], String))] = ISZ()
    for (arg <- o.args) {
      val f = Os.path(arg)
      if (!f.exists) {
        eprintln(s"File $arg does not exist.")
        return InvalidFile
      } else if (!f.isFile) {
        eprintln(s"Path $arg is not a file.")
        return InvalidFile
      } else if (!(f.ext == "sc" || f.ext == "slang")) {
        eprintln(s"Can only accept .sc/.slang files as arguments")
        return InvalidFile
      }
      slangFiles = slangFiles :+ ((arg, readFile(f)))
    }

    if (o.verbose) {
      if (o.args.nonEmpty) {
        stopTime()
        println()
      }

      if (o.exts.nonEmpty) {
        println("Reading extension files ...")
        startTime()
      }
    }

    var exts: ISZ[StaticTranspiler.ExtFile] = ISZ()

    def extRec(rel: ISZ[String], p: Os.Path, force: B): B = {
      if (!p.exists) {
        eprintln(s"File $p does not exist.")
        return F
      } else if (p.isDir) {
        for (f <- p.list) {
          extRec(rel :+ p.name, f, F)
        }
      } else if ((force || p.ext == "c" || p.ext == "h") && p.isFile) {
        val (uriOpt, content) = readFile(p)
        exts = exts :+ StaticTranspiler.ExtFile(rel, uriOpt.get, content)
      }
      return T
    }

    for (ext <- o.exts) {
      val f = Os.path(ext)
      if (!extRec(ISZ(), f, T)) {
        return InvalidFile
      }
    }

    if (o.verbose) {
      if (o.exts.nonEmpty) {
        stopTime()
        println()
      }
      println("Reading cmake include files ...")
      startTime()
    }

    var cmakeIncludes: ISZ[String] = ISZ()
    var cmakePlusIncludes: ISZ[String] = ISZ()
    for (ci <- o.cmakeIncludes) {
      val ciOps = ops.StringOps(ci)
      val (plus, path): (B, String) = if (ciOps.startsWith("+")) {
        (T, ciOps.substring(1, ciOps.size))
      } else if (ciOps.startsWith("-")) {
        (F, ciOps.substring(1, ciOps.size))
      } else {
        (F, ciOps.s)
      }
      val f = Os.path(path)
      if (!f.exists) {
        eprintln(s"File $path does not exist.")
        return InvalidFile
      } else if (!f.isFile) {
        eprintln(s"Path $path is not a file.")
        return InvalidFile
      }
      val content = readFile(f)._2
      if (plus) {
        cmakePlusIncludes = cmakePlusIncludes :+ content
      } else {
        cmakeIncludes = cmakeIncludes :+ content
      }
    }

    if (o.verbose) {
      if (o.cmakeIncludes.nonEmpty) {
        stopTime()
        println()
      }
      println("Reading sourcepath files ...")
      startTime()
    }

    var sources = ISZ[(Option[String], String)]()
    for (p <- o.sourcepath) {
      val f = Os.path(p)
      if (!f.exists) {
        eprintln(s"Source path '$p' does not exist.")
        return InvalidPath
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
          if (o.verbose) {
            println(s"Read $p")
          }
        }
      }
    }

    if (o.sourcepath.nonEmpty && sources.isEmpty) {
      eprintln("Did not find any sources in the specified sourcepath")
      return InvalidSources
    }
    stopTime()

    var th: TypeHierarchy = loadFileOpt match {
      case Some(loadFile) =>
        if (o.verbose) {
          println()
          println(s"Loading type information from $loadFile ...")
          startTime()
        }
        SireumApi.readGzipContent(loadFile) match {
          case Some(data) =>
            CustomMessagePack.toTypeHierarchy(data) match {
              case Either.Left(thl) => thl
              case Either.Right(errorMsg) =>
                eprintln(s"Loading error at offset ${errorMsg.offset}: ${errorMsg.message}")
                return LoadingError
            }
          case _ =>
            eprintln(s"Could not load from $loadFile")
            return LoadingError
        }
      case _ =>
        if (o.verbose) {
          println()
          println(s"Parsing, resolving, type outlining, and type checking Slang library files ...")
          startTime()
        }

        val (thl, rep): (TypeHierarchy, Reporter) = {
          val p = FrontEnd.checkedLibraryReporter
          (p._1.typeHierarchy, p._2)
        }
        if (rep.hasIssue) {
          reporter.reports(rep.messages)
          reporter.printMessages()
          return InvalidLibrary
        }
        thl
    }
    stopTime()

    if (o.verbose) {
      println()
      println("Parsing and resolving Slang sourcepath programs ...")
      startTime()
    }

    val t = FrontEnd.parseProgramAndGloballyResolve(0, for (p <- sources) yield FrontEnd.Input(p._2, p._1),
      th.nameMap, th.typeMap)
    if (t._1.hasIssue) {
      reporter.reports(t._1.messages)
      reporter.printMessages()
      return InvalidSources
    }
    stopTime()

    if (o.verbose) {
      println()
      println("Building type hierarchy of Slang sourcepath programs ...")
      startTime()
    }

    th = TypeHierarchy.build(F, th(nameMap = t._3, typeMap = t._4), reporter)
    if (reporter.hasIssue) {
      reporter.printMessages()
      return InvalidSources
    }
    stopTime()

    if (o.verbose) {
      println()
      println("Type outlining Slang sourcepath programs ...")
      startTime()
    }

    th = TypeOutliner.checkOutline(0, T, th, reporter)
    if (reporter.hasIssue) {
      reporter.printMessages()
      return InvalidSources
    }
    stopTime()

    if (o.verbose) {
      println()
      println("Type checking Slang sourcepath programs ...")
      startTime()
    }

    th = TypeChecker.checkComponents(0, o.strictAliasing, th, th.nameMap, th.typeMap, reporter)

    if (reporter.hasIssue) {
      reporter.printMessages()
      return InvalidSources
    }
    stopTime()

    if (o.verbose) {
      println()
      println("Sanity checking computed symbol and type information of Slang library files and sourcepath programs ...")
      startTime()
    }

    PostTipeAttrChecker.checkNameTypeMaps(th.nameMap, th.typeMap, reporter)

    if (reporter.hasIssue) {
      reporter.printMessages()
      return InternalError
    }
    stopTime()

    saveFileOpt match {
      case Some(saveFile) =>
        if (o.verbose) {
          println()
          println(s"Saving type information to $saveFile ...")
          startTime()
        }
        if (!SireumApi.writeGzipContent(saveFile, CustomMessagePack.fromTypeHierarchy(th))) {
          return SavingError
        }
        stopTime()
      case _ =>
    }

    var thOpt: Option[TypeHierarchy] = Some(th)
    var entryPoints = ISZ[TypeSpecializer.EntryPoint]()
    for (slangFile <- slangFiles) {
      if (o.verbose) {
        println()
        println(s"Parsing, resolving, type outlining, and type checking ${slangFile._1} ...")
        startTime()
      }

      Parser.parseTopUnit[AST.TopUnit.Program](slangFile._2._2, T, F, slangFile._2._1, reporter) match {
        case Some(p) =>
          val p2 = FrontEnd.checkWorksheet(0, thOpt, p, reporter)
          if (reporter.hasIssue) {
            reporter.printMessages()
            return InvalidSlangFiles
          }
          stopTime()

          if (o.verbose) {
            println()
            println(s"Sanity checking computed symbol and type information of ${slangFile._1} ...")
            startTime()
          }

          PostTipeAttrChecker.checkProgram(p2._2, reporter)

          if (reporter.hasIssue) {
            reporter.printMessages()
            return InternalError
          }

          entryPoints = entryPoints :+ TypeSpecializer.EntryPoint.Worksheet(p2._2)
          thOpt = Some(p2._1)
        case _ =>
      }
      stopTime()
    }

    if (o.verbose) {
      println()
      println(s"Specializing programs ...")
      startTime()
    }

    @pure def split(text: String, char: C): ISZ[String] = {
      return for (s <- ops.StringOps(text).split((c: C) => c == char)) yield ops.StringOps(s).trim
    }

    var forwardingMap = HashMap.empty[ISZ[String], ISZ[String]]
    for (p <- o.forwarding) {
      split(p, '=') match {
        case ISZ(key, value) =>
          forwardingMap = forwardingMap + split(key, '.') ~> split(value, '.')
        case _ =>
          eprintln(s"Could not parse forwarding config $p")
          return TranspilingError
      }
    }

    for (app <- o.apps) {
      entryPoints = entryPoints :+ TypeSpecializer.EntryPoint.App(split(app, '.'))
    }

    val tsr = TypeSpecializer.specialize(thOpt.get, entryPoints, forwardingMap, reporter)

    reporter.printMessages()
    if (reporter.hasIssue) {
      return InternalError
    }
    stopTime()

    if (o.verbose) {
      println()
      println("Transpiling to C ...")
      startTime()
    }

    var customArraySizes = HashMap.empty[AST.Typed, Z]
    for (p <- o.customArraySizes) {
      split(p, '=') match {
        case ISZ(key, value) =>
          val num: Z = Z(value) match {
            case Some(n) if n > 0 => n
            case _ =>
              eprintln(s"Custom sequence size should be positive: $p")
              return TranspilingError
          }
          val e = Parser.parseExp[AST.Exp.Select](s"o[$key]")
          e.targs(0) match {
            case t: AST.Type.Named =>
              def addS(name: ISZ[String], otherName: ISZ[String], it: AST.Typed, et: AST.Typed): Unit = {
                val t1 = AST.Typed.Name(name, ISZ(it, et))
                customArraySizes = customArraySizes + t1 ~> num
              }

              t.name.ids.map((id: AST.Id) => id.value) match {
                case ISZ("MS") if t.typeArgs.size == 2 =>
                  val it = toTyped(tsr.typeHierarchy, t.typeArgs(0))
                  val et = toTyped(tsr.typeHierarchy, t.typeArgs(1))
                  addS(AST.Typed.msName, AST.Typed.isName, it, et)
                case ISZ("IS") if t.typeArgs.size == 2 =>
                  val it = toTyped(tsr.typeHierarchy, t.typeArgs(0))
                  val et = toTyped(tsr.typeHierarchy, t.typeArgs(1))
                  addS(AST.Typed.isName, AST.Typed.msName, it, et)
                case ISZ("ISZ") if t.typeArgs.size == 1 =>
                  val et = toTyped(tsr.typeHierarchy, t.typeArgs(0))
                  addS(AST.Typed.isName, AST.Typed.msName, AST.Typed.z, et)
                case ISZ("MSZ") if t.typeArgs.size == 1 =>
                  val et = toTyped(tsr.typeHierarchy, t.typeArgs(0))
                  addS(AST.Typed.msName, AST.Typed.isName, AST.Typed.z, et)
                case ISZ("ZS") if t.typeArgs.size == 0 =>
                  addS(AST.Typed.msName, AST.Typed.isName, AST.Typed.z, AST.Typed.z)
                case _ =>
                  eprintln(s"Could not recognize custom sequence size configuration: $p")
                  return TranspilingError
              }
            case _ =>
              eprintln(s"Could not recognize custom sequence size configuration: $p")
              return TranspilingError
          }
        case _ =>
          eprintln(s"Could not recognize custom sequence size configuration: $p")
          return TranspilingError
      }
    }

    var excludedNames: HashSet[ISZ[String]] = HashSet.empty
    for (s <- o.excludeBuild) {
      val name = split(s, '.')
      excludedNames = excludedNames + name
    }

    var constants = HashMap.empty[ISZ[String], AST.Exp]
    for (p <- o.customConstants) {
      split(p, '=') match {
        case ISZ(key, value) =>
          val e = Parser.parseExp[AST.Exp](value)
          e match {
            case _: AST.Lit => constants = constants + split(key, '.') ~> e
            case _ =>
              eprintln(s"Could not recognize custom object var constant configuration: $p")
              return TranspilingError
          }
        case _ =>
          eprintln(s"Could not recognize custom object var constant configuration: $p")
          return TranspilingError
      }
    }

    val config = StaticTranspiler.Config(
      projectName = o.projectName.getOrElse("main"),
      fprintWidth = o.fingerprint,
      defaultBitWidth = o.bitWidth,
      maxStringSize = o.maxStringSize,
      maxArraySize = o.maxArraySize,
      customArraySizes = customArraySizes,
      customConstants = constants,
      plugins = plugins,
      exts = exts,
      excludedNames = excludedNames,
      forLoopOpt = o.unroll,
      stackSize = o.stackSize.get,
      libOnly = o.libOnly,
      stableTypeId = o.stableTypeId,
      cmakeIncludes = cmakeIncludes,
      cmakePlusIncludes = cmakePlusIncludes
    )

    val trans = StaticTranspiler(config, tsr)
    val r = trans.transpile(reporter)

    reporter.printMessages()
    if (reporter.hasIssue) {
      return TranspilingError
    }
    stopTime()

    if (o.verbose) {
      println()
      println("Writing generated files ...")
      startTime()
    }

    val resultDir = Os.path(o.output.get)
    resultDir.removeAll()
    resultDir.mkdirAll()

    for (e <- r.files.entries) {
      val path = e._1
      var f = resultDir /+ path
      f.up.mkdirAll()
      f = f.canon
      f.writeOver(e._2.render)
      println(s"Wrote $f")
    }
    for (e <- r.extFiles.entries) {
      val path = e._1
      val extFile = e._2
      var f = resultDir /+ path
      val p = Os.uriToPath(extFile.uri)
      f.up.mkdirAll()
      f = f.canon
      if (Os.isWin) {
        f.writeOver(extFile.content)
        println(s"Wrote $f")
      } else {
        f.mklink(p)
        println(s"Created symlink from $f to $p")
      }
    }
    stopTime()

    if (o.verbose) {
      val newUsed = Os.totalMemory - Os.freeMemory
      if (newUsed > used) {
        used = newUsed
      }
      println()
      println(s"Ok! Total time: ${SireumApi.formatSecond(extension.Time.currentMillis - begin)} s, Max memory: ${SireumApi.formatMb(used)} MB")
    }

    return 0
  }

  def toTyped(th: TypeHierarchy, tpe: AST.Type): AST.Typed = {
    def rec(t: AST.Type): AST.Typed = {
      t match {
        case t: AST.Type.Named =>
          val ids = t.name.ids.map((id: AST.Id) => id.value)
          val tids = AST.Typed.sireumName ++ ids
          if (!th.typeMap.contains(ids) && th.typeMap.contains(tids)) {
            return AST.Typed.Name(tids, for (ta <- t.typeArgs) yield rec(ta))
          } else {
            return AST.Typed.Name(ids, for (ta <- t.typeArgs) yield rec(ta))
          }
        case t: AST.Type.Tuple => return AST.Typed.Tuple(for (ta <- t.args) yield rec(ta))
        case t: AST.Type.Fun => return AST.Typed.Fun(if (t.isPure) AST.Purity.Pure else AST.Purity.Impure, t.isByName, for (ta <- t.args) yield rec(ta), rec(t.ret))
      }
    }
    return rec(tpe)
  }
}
