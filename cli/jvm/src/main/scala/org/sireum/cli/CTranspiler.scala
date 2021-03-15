/*
 Copyright (c) 2020, Robby, Kansas State University
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

import _root_.java.io._
import _root_.java.util.zip._

import org.sireum._
import org.sireum.lang.FrontEnd
import org.sireum.lang.{ast => AST}
import org.sireum.lang.parser.Parser
import org.sireum.lang.tipe._
import org.sireum.message._

import Cli._
import org.sireum.transpilers.c.StaticTranspiler
import org.sireum.transpilers.common.TypeSpecializer

object CTranspiler {

  val InvalidLibrary: Int = -1
  val InvalidMode: Int = -2
  val InvalidPath: Int = -3
  val InvalidFile: Int = -4
  val InvalidSources: Int = -5
  val InvalidSlangFiles: Int = -6
  val InvalidForceNames: Int = -7
  val InternalError: Int = -8
  val SavingError: Int = -9
  val LoadingError: Int = -10
  val PluginError: Int = -11
  val TranspilingError: Int = -12

  def run(o: CTranspilerOption): Z = {
    def readFile(f: Os.Path): (Option[String], String) = {
      (Some(f.toUri), f.read)
    }

    if (o.args.isEmpty && o.sourcepath.isEmpty) {
      println(o.help)
      println()
      println("Please either specify sourcepath or Slang files as argument")
      return 0
    }

    val loadFileOpt: Option[File] = if (o.load.nonEmpty) {
      val f = new File(o.load.get.value)
      if (!f.isFile) {
        eprintln("Invalid file to load type information from")
        return InvalidFile
      }
      Some(f)
    } else None()

    val saveFileOpt: Option[File] = if (o.save.nonEmpty) {
      val f = new File(o.save.get.value)
      if (f.exists && !f.isFile) {
        eprintln("Invalid file to save information to")
        return InvalidFile
      }
      Some(f)
    } else None()

    var start = 0L
    var used = 0L
    val rt = Runtime.getRuntime

    def startTime(): Unit = {
      start = System.currentTimeMillis
    }

    def stopTime(): Unit = {
      if (o.verbose) {
        val end = System.currentTimeMillis
        val newUsed = rt.totalMemory - rt.freeMemory
        if (newUsed > used) {
          used = newUsed
        }
        println(f"Time: ${end - start} ms, Memory: ${newUsed / 1024d / 1024d}%.2f MB")
      }
    }

    val begin = System.currentTimeMillis

    if (o.verbose && o.plugins.nonEmpty) {
      println("Loading plugins ...")
      startTime()
    }

    var plugins = ISZ[StaticTranspiler.Plugin]()

    for (p <- o.plugins) {
      try {
        val c = Class.forName(p.value)
        plugins = plugins :+ c.getDeclaredConstructor().newInstance().asInstanceOf[StaticTranspiler.Plugin]
      } catch {
        case _: Throwable =>
          eprintln(s"Could not load plugin: $p")
          return PluginError
      }
    }

    plugins = plugins ++ ISZ(
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
      val f = Os.path(arg.value)
      if (!f.exists) {
        eprintln(s"File $arg does not exist.")
        return InvalidFile
      } else if (!f.isFile) {
        eprintln(s"Path $arg is not a file.")
        return InvalidFile
      } else if (!(ops.StringOps(f.name).endsWith(".sc") || ops.StringOps(f.name).endsWith(".slang"))) {
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
      }
      if ((force && p.isFile) || p.ext === "c" || p.ext === "h") {
        val (uriOpt, content) = readFile(p)
        exts = exts :+ StaticTranspiler.ExtFile(rel, uriOpt.get, content)
      }
      return T
    }

    for (ext <- o.exts) {
      val f = Os.path(ext.value)
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
      val ciOps = ops.StringOps(ci.value)
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
      val f = Os.path(p.value)
      if (!f.exists) {
        eprintln(s"Source path '$p' does not exist.")
        return InvalidPath
      } else {
        var seen = HashSet.empty[Os.Path]
        for (p <- Os.Path.walk(f, F, T, { path =>
          var isSlang = path.string.value.endsWith(".slang")
          if (path.string.value.endsWith(".scala") || isSlang) {
            if (!isSlang) {
              for (firstLine <- path.readLineStream.take(1).toISZ.elements) {
                isSlang = firstLine.value
                  .replace(" ", "")
                  .replace("\t", "")
                  .replace("\r", "")
                  .contains("#Sireum")
              }
            }
          }
          isSlang
        })) {
          sources = sources :+ readFile(p)
          if (o.verbose) println(s"Read $p")
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
          println(s"Loading type information from ${loadFile.getPath} ...")
          startTime()
        }
        val data: ISZ[U8] = {
          val gis = new GZIPInputStream(new FileInputStream(loadFile))
          try {
            toIS(gis.bytes)
          } catch {
            case e: IOException =>
              eprintln(s"Could not load file: ${e.getMessage}")
              return LoadingError
          } finally gis.close()
        }
        CustomMessagePack.toTypeHierarchy(data) match {
          case Either.Left(thl) => thl
          case Either.Right(errorMsg) =>
            eprintln(s"Loading error at offset ${errorMsg.offset}: ${errorMsg.message}")
            return LoadingError
        }
      case _ =>
        if (o.verbose) {
          println()
          println(s"Parsing, resolving, type outlining, and type checking Slang library files ...")
          startTime()
        }

        val (thl, rep) = {
          val p = FrontEnd.checkedLibraryReporter
          (p._1.typeHierarchy, p._2)
        }
        if (rep.hasIssue) {
          rep.printMessages()
          return InvalidLibrary
        }
        thl
    }
    stopTime()

    val reporter = Reporter.create

    if (o.verbose) {
      println()
      println("Parsing and resolving Slang sourcepath programs ...")
      startTime()
    }

    val t = FrontEnd.parseProgramAndGloballyResolve(T, sources, th.nameMap, th.typeMap)
    if (t._1.hasIssue) {
      t._1.printMessages()
      return InvalidSources
    }
    stopTime()

    if (o.verbose) {
      println()
      println("Building type hierarchy of Slang sourcepath programs ...")
      startTime()
    }

    th = TypeHierarchy.build(th(nameMap = t._3, typeMap = t._4), reporter)
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

    th = TypeOutliner.checkOutline(T, th, reporter)
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

    th = TypeChecker.checkComponents(T, th, th.nameMap, th.typeMap, reporter)

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
          println(s"Saving type information to ${saveFile.getPath} ...")
          startTime()
        }

        val (buf, length) = fromIS(CustomMessagePack.fromTypeHierarchy(th))
        val gos = new GZIPOutputStream(new FileOutputStream(saveFile))
        try gos.write(buf, 0, length)
        catch {
          case e: IOException =>
            eprintln(s"Could not save file: ${e.getMessage}")
            return SavingError
        } finally gos.close()

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
        case Some(p: AST.TopUnit.Program) =>
          val p2 = FrontEnd.checkWorksheet(T, thOpt, p, reporter)
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

        case Some(_) => eprintln(s"File '${slangFile._1}' does not contain a Slang program")
        case _ =>
      }
      stopTime()
    }

    if (o.verbose) {
      println()
      println(s"Specializing programs ...")
      startTime()
    }

    var forwardingMap = HashMap.empty[ISZ[String], ISZ[String]]
    for (p <- o.forwarding) {
      val Array(key, value) = p.value.split('=')
      forwardingMap = forwardingMap + ISZ(key.split('.').toIndexedSeq.map(s => String(s.trim)): _*) ~> ISZ(
        value.split('.').toIndexedSeq.map(s => String(s.trim)): _*
      )
    }

    for (app <- o.apps) {
      entryPoints = entryPoints :+ TypeSpecializer.EntryPoint.App(ISZ(app.value.split('.').toIndexedSeq.map(String(_)): _*))
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
      try {
        val Array(key, value) = p.value.split('=')
        val num = Z(value).get
        if (num <= 0) {
          eprintln(s"Custom sequence size should be positive: $p")
          return TranspilingError
        }
        val e = Parser.parseExp[AST.Exp.Select](s"o[$key]")
        e.targs(0) match {
          case t: AST.Type.Named =>
            def addS(name: ISZ[String], otherName: ISZ[String], it: AST.Typed, et: AST.Typed) = {
              val t1 = AST.Typed.Name(name, ISZ(it, et))
              val t2 = AST.Typed.Name(otherName, ISZ(it, et))
              customArraySizes = customArraySizes + t1 ~> num
            }

            t.name.ids.map(_.value.value) match {
              case ISZ("MS") if t.typeArgs.size.toInt == 2 =>
                val it = toTyped(tsr.typeHierarchy, t.typeArgs(0))
                val et = toTyped(tsr.typeHierarchy, t.typeArgs(1))
                addS(AST.Typed.msName, AST.Typed.isName, it, et)
              case ISZ("IS") if t.typeArgs.size.toInt == 2 =>
                val it = toTyped(tsr.typeHierarchy, t.typeArgs(0))
                val et = toTyped(tsr.typeHierarchy, t.typeArgs(1))
                addS(AST.Typed.isName, AST.Typed.msName, it, et)
              case ISZ("ISZ") if t.typeArgs.size.toInt == 1 =>
                val et = toTyped(tsr.typeHierarchy, t.typeArgs(0))
                addS(AST.Typed.isName, AST.Typed.msName, AST.Typed.z, et)
              case ISZ("MSZ") if t.typeArgs.size.toInt == 1 =>
                val et = toTyped(tsr.typeHierarchy, t.typeArgs(0))
                addS(AST.Typed.msName, AST.Typed.isName, AST.Typed.z, et)
              case ISZ("ZS") if t.typeArgs.size.toInt == 0 =>
                addS(AST.Typed.msName, AST.Typed.isName, AST.Typed.z, AST.Typed.z)
              case _ => throw new Exception
            }
          case _ => throw new Exception
        }
      } catch {
        case _: Throwable =>
          eprintln(s"Could not recognize custom sequence size configuration: $p")
          return TranspilingError
      }
    }

    var excludedNames: HashSet[ISZ[String]] = HashSet.empty
    for (s <- o.excludeBuild) {
      val name = for (id <- ops.StringOps(s).split((c: C) => c.value == '.')) yield ops.StringOps(id).trim
      excludedNames = excludedNames + name
    }

    var constants = HashMap.empty[ISZ[String], AST.Exp]
    for (p <- o.customConstants) {
      try {
        val Array(key, value) = p.value.split('=')
        val e = Parser.parseExp[AST.Exp](value)
        e match {
          case e: AST.Lit => constants = constants + ISZ(key.split('.').toIndexedSeq.map(x => String(x.trim)): _*) ~> e
          case _ => throw new Exception
        }
      } catch {
        case _: Throwable =>
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
      var f = resultDir
      for (segment <- path) {
        f = f / segment.value
      }
      f.up.mkdirAll()
      f = f.canon
      f.writeOver(e._2.render)
      println(s"Wrote $f")
    }
    for (e <- r.extFiles.entries) {
      val path = e._1
      val extFile = e._2
      var f = resultDir
      for (segment <- path) {
        f = f / segment.value
      }
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
      val newUsed = rt.totalMemory - rt.freeMemory
      if (newUsed > used) {
        used = newUsed
      }
      println()
      println(
        f"Ok! Total time: ${(System.currentTimeMillis - begin) / 1000d}%.2f s, Max memory: ${used / 1024d / 1024d}%.2f MB"
      )
    }

    return 0
  }

  def toIS(data: Array[Byte]): ISZ[U8] = {
    new IS(Z, data, data.length, U8.Boxer)
  }

  def fromIS(data: ISZ[U8]): (Array[Byte], Int) = {
    return (data.data.asInstanceOf[Array[Byte]], data.size.toInt)
  }

  implicit class GZIS(val gzis: GZIPInputStream) extends AnyVal {

    def bytes: Array[Byte] = {
      val bos = new ByteArrayOutputStream
      val buffer = new Array[Byte](16384)
      var n = gzis.read(buffer)
      while (n > -1) {
        bos.write(buffer, 0, n)
        n = gzis.read(buffer)
      }
      gzis.close()
      bos.toByteArray
    }
  }

  def toTyped(th: TypeHierarchy, tpe: AST.Type): AST.Typed = {
    def rec(t: AST.Type): AST.Typed = {
      t match {
        case t: AST.Type.Named =>
          val ids = t.name.ids.map(_.value)
          val tids = AST.Typed.sireumName ++ ids
          if (!th.typeMap.contains(ids) && th.typeMap.contains(tids)) AST.Typed.Name(tids, t.typeArgs.map(rec))
          else AST.Typed.Name(ids, t.typeArgs.map(rec))
        case t: AST.Type.Tuple => AST.Typed.Tuple(t.args.map(rec))
        case t: AST.Type.Fun => AST.Typed.Fun(t.isPure, t.isByName, t.args.map(rec), rec(t.ret))
      }
    }
    rec(tpe)
  }
}
