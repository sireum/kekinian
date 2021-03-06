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

import _root_.java.util.zip._

import org.sireum._
import org.sireum.lang.FrontEnd
import org.sireum.lang.ast.TopUnit
import org.sireum.lang.parser.Parser
import org.sireum.lang.symbol._
import org.sireum.lang.tipe._
import org.sireum.message._
import org.sireum.Cli._

object SlangTipe {

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

  def run(o: SlangTipeOption, reporter: Reporter): Z = {

    def readFile(f: Os.Path): (Option[String], String) = {
      (Some(f.toUri), f.read)
    }

    if (o.args.isEmpty && o.sourcepath.isEmpty) {
      println(o.help)
      println()
      println("Please either specify sourcepath or Slang files as arguments")
      return 0
    }

    if (o.load.nonEmpty && o.noRuntime) {
      eprintln("Have to use built-in runtime together with loading type information from file")
      return InvalidMode
    }

    if (o.noRuntime && o.sourcepath.isEmpty) {
      eprintln("Please specify sourcepath when not using built-in runtime")
      return InvalidMode
    }

    if (o.force.nonEmpty && !o.outline) {
      eprintln("Forcing type checking can only be used when outline is enabled")
      return InvalidMode
    }

    if (o.save.nonEmpty && o.outline) {
      eprintln("Saving type information can only be used when outline is disabled")
      return InvalidMode
    }

    val loadFileOpt: Option[Os.Path] = if (o.load.nonEmpty) {
      val f = Os.path(o.load.get)
      if (!f.exists || !f.isFile) {
        eprintln("Invalid file to load type information from")
        return InvalidFile
      }
      Some(f)
    } else None()

    val saveFileOpt: Option[Os.Path] = if (o.save.nonEmpty) {
      val f = Os.path(o.save.get)
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

    if (o.verbose && o.args.nonEmpty) {
      println("Reading Slang file arguments ...")
      startTime()
    }

    var slangFiles = ISZ[(String, (Option[String], String))]()
    for (arg <- o.args) {
      val f = Os.path(arg.value)
      if (!f.exists) {
        eprintln(s"File $arg does not exist.")
        return InvalidFile
      } else if (!f.isFile) {
        eprintln(s"Path $arg is not a file.")
        return InvalidFile
      } else if (!f.string.value.endsWith(".sc") && !f.string.value.endsWith(".cmd") &&
        !f.string.value.endsWith(".slang") && !f.string.value.endsWith(".logika")) {
        eprintln(s"Can only accept .sc, .cmd, .slang, or .logika files as arguments")
        return InvalidFile
      }
      slangFiles = slangFiles :+ (arg, readFile(f))
    }

    if (o.verbose) {
      if (o.args.nonEmpty) {
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
        for (p <- Os.Path.walk(f, F, T, { path =>
          val excluded = ops.ISZOps(o.exclude).exists(segment => ops.StringOps(path.toUri).contains(segment))
          var isSlang = !excluded && path.string.value.endsWith(".slang")
          if (!excluded && (path.string.value.endsWith(".scala") || isSlang)) {
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
          println(s"Loading type information from $loadFile ...")
          startTime()
        }
        val data: ISZ[U8] = if (o.gzip) {
          val gis = new GZIPInputStream(new java.io.FileInputStream(new java.io.File(loadFile.string.value)))
          try {
            toIS(gis.bytes)
          } catch {
            case e: java.io.IOException =>
              eprintln(s"Could not load file: ${e.getMessage}")
              return LoadingError
          } finally gis.close()
        } else {
          loadFile.readU8s
        }
        CustomMessagePack.toTypeHierarchy(data) match {
          case Either.Left(thl) =>
            stopTime()
            thl
          case Either.Right(errorMsg) =>
            eprintln(s"Loading error at offset ${errorMsg.offset}: ${errorMsg.message}")
            return LoadingError
        }
      case _ =>
        if (o.noRuntime) {
          TypeHierarchy(HashMap.empty, HashMap.empty, Poset.empty, HashMap.empty)
        } else {
          if (o.verbose) {
            println()
            println(
              s"Parsing, resolving, ${if (o.outline) "and type outlining" else "type outlining, and type checking"} Slang library files ..."
            )
            startTime()
          }

          val (thl, rep) = if (o.outline) {
            val p = FrontEnd.libraryReporter
            (p._1.typeHierarchy, p._2)
          } else {
            val p = FrontEnd.checkedLibraryReporter
            (p._1.typeHierarchy, p._2)
          }
          if (rep.hasIssue) {
            rep.printMessages()
            return InvalidLibrary
          }
          stopTime()
          thl
        }
    }

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

    if (o.outline) {
      var nameMap: Resolver.NameMap = HashMap.empty
      var typeMap: Resolver.TypeMap = HashMap.empty

      if (o.verbose && o.force.nonEmpty) {
        println()
        println("Type checking Slang entities in the force list ...")
        startTime()
      }

      var ok = T
      for (name <- o.force) {
        val ids = ISZ[String](name.value.split('.').toIndexedSeq.map(id => String(id)): _*)
        var found = F
        th.nameMap.get(ids) match {
          case Some(info: Info.Object) =>
            nameMap = nameMap + ids ~> info
            found = T
          case _ =>
        }
        th.typeMap.get(ids) match {
          case Some(info: TypeInfo.Sig) =>
            typeMap = typeMap + ids ~> info
            found = T
          case Some(info: TypeInfo.Adt) =>
            typeMap = typeMap + ids ~> info
            found = T
          case _ =>
        }
        if (!found) {
          eprintln(st"No object, trait, or class with name '${(ids, ".")}' was found".render)
          ok = F
        }
      }
      if (!ok) {
        return InvalidForceNames
      }

      th = TypeChecker.checkComponents(T, th, nameMap, typeMap, reporter)

      if (reporter.hasIssue) {
        reporter.printMessages()
        return InvalidSources
      }
      if (o.force.nonEmpty) stopTime()

    } else {

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
        println(
          "Sanity checking computed symbol and type information of Slang library files and sourcepath programs ..."
        )
        startTime()
      }

      PostTipeAttrChecker.checkNameTypeMaps(th.nameMap, th.typeMap, reporter)

      if (reporter.hasIssue) {
        reporter.printMessages()
        return InternalError
      }
      stopTime()
    }

    saveFileOpt match {
      case Some(saveFile) =>
        if (o.verbose) {
          println()
          println(s"Saving type information to $saveFile ...")
          startTime()
        }

        val data = CustomMessagePack.fromTypeHierarchy(th)
        if (o.gzip) {
          val gos = new GZIPOutputStream(new java.io.FileOutputStream(new java.io.File(saveFile.string.value)))
          val (buf, length) = fromIS(data)
          try gos.write(buf, 0, length)
          catch {
            case e: java.io.IOException =>
              eprintln(s"Could not save file: ${e.getMessage}")
              return SavingError
          } finally gos.close()
        } else {
          try saveFile.writeOverU8s(data)
          catch {
            case e: java.io.IOException =>
              eprintln(s"Could not save file: ${e.getMessage}")
              return SavingError
          }
        }

        stopTime()
      case _ =>
    }

    val thOpt: Option[TypeHierarchy] = Some(th)

    for (slangFile <- slangFiles) {

      if (o.verbose) {
        println()
        println(s"Parsing, resolving, type outlining, and type checking ${slangFile._1} ...")
        startTime()
      }

      Parser.parseTopUnit[TopUnit](slangFile._2._2, T, F, slangFile._2._1, reporter) match {
        case Some(p: TopUnit.Program) =>
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
          stopTime()

        case Some(_) => eprintln(s"File '${slangFile._1}' does not contain a Slang program")
        case _ =>
      }
    }

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
      val bos = new java.io.ByteArrayOutputStream
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

}
