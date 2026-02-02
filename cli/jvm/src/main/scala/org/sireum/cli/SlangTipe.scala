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

  def run(o: SireumSlangTipeOption, reporter: Reporter): Either[TypeHierarchy, Z] = {
    def readFile(f: Os.Path): (Option[String], String) = {
      return (Some(f.toUri), f.read)
    }

    if (o.args.isEmpty && o.sourcepath.isEmpty) {
      println(o.help)
      println()
      println("Please either specify sourcepath or Slang files as argument")
      return Either.right(InvalidSources)
    }

    if (o.noRuntime && o.sourcepath.isEmpty && o.load.isEmpty) {
      eprintln("Please specify sourcepath or load type information from a file when not using built-in runtime")
      return Either.right(InvalidMode)
    }

    if (o.force.nonEmpty && !o.outline) {
      eprintln("Forcing type checking can only be used when outline is enabled")
      return Either.right(InvalidMode)
    }

    if (o.save.nonEmpty && o.outline) {
      eprintln("Saving type information can only be used when outline is disabled")
      return Either.right(InvalidMode)
    }

    val loadFileOpt: Option[Os.Path] = if (o.load.nonEmpty) {
      val f = Os.path(o.load.get)
      if (!f.exists || !f.isFile) {
        eprintln("Invalid file to load type information from")
        return Either.right(InvalidFile)
      }
      Some(f)
    } else {
      None()
    }

    val saveFileOpt: Option[Os.Path] = if (o.save.nonEmpty) {
      val f = Os.path(o.save.get)
      if (f.exists && !f.isFile) {
        eprintln("Invalid file to save information to")
        return Either.right(InvalidFile)
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

    if (o.verbose && o.args.nonEmpty) {
      println("Reading Slang file arguments ...")
      startTime()
    }

    var slangFiles = ISZ[(String, (Option[String], String))]()
    for (arg <- o.args) {
      val f = Os.path(arg)
      if (!f.exists) {
        eprintln(s"File $arg does not exist.")
        return Either.right(InvalidFile)
      } else if (!f.isFile) {
        eprintln(s"Path $arg is not a file.")
        return Either.right(InvalidFile)
      } else if (f.ext != "sc" && f.ext != "cmd" &&
        f.ext != "slang" && f.ext != "logika") {
        eprintln(s"Can only accept .sc, .cmd, .slang, or .logika files as arguments")
        return Either.right(InvalidFile)
      }
      slangFiles = slangFiles :+ ((arg, readFile(f)))
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
      val f = Os.path(p)
      if (!f.exists) {
        eprintln(s"Source path '$p' does not exist.")
        return Either.right(InvalidPath)
      } else {
        for (p <- Os.Path.walk(f, F, T, { path: Os.Path =>
          val excluded = ops.ISZOps(o.exclude).
            exists((segment: String) => ops.StringOps(path.toUri).contains(segment))
          var isSlang = !excluded && path.ext == "slang"
          if (!excluded && (path.ext == "scala" || isSlang)) {
            if (!isSlang) {
              val line = conversions.String.fromCis(path.readCStream.takeWhile((c: C) => c != '\n').
                filter((c: C) => c != ' ' && c != '\t' && c != '\r').toISZ)
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
      return Either.right(InvalidSources)
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
          SireumApi.readGzipContent(loadFile) match {
            case Some(d) => d
            case _ =>
              eprintln(s"Could not load from $loadFile")
              return Either.right(LoadingError)
          }
        } else {
          loadFile.readU8s
        }
        CustomMessagePack.toTypeHierarchy(data) match {
          case Either.Left(thl) =>
            stopTime()
            thl
          case Either.Right(errorMsg) =>
            eprintln(s"Loading error at offset ${errorMsg.offset}: ${errorMsg.message}")
            return Either.right(LoadingError)
        }
      case _ =>
        if (o.noRuntime) {
          TypeHierarchy.empty
        } else {
          if (o.verbose) {
            println()
            println(s"Parsing, resolving, ${if (o.outline) "and type outlining" else "type outlining, and type checking"} Slang library files ...")
            startTime()
          }

          val (thl, rep): (TypeHierarchy, Reporter) = {
            val p = FrontEnd.checkedLibraryReporter
            (p._1.typeHierarchy, p._2)
          }
          if (rep.hasError) {
            if (o.parseableMessages) {
              Os.printParseableMessages(rep)
            } else {
              rep.printMessages()
            }
            return Either.right(InvalidLibrary)
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

    val t = FrontEnd.parseProgramAndGloballyResolve(0, for (p <- sources) yield FrontEnd.Input(p._2, p._1),
      th.nameMap, th.typeMap)
    if (t._1.hasError) {
      if (o.parseableMessages) {
        Os.printParseableMessages(t._1)
      } else {
        t._1.printMessages()
      }
      return Either.right(InvalidSources)
    }
    stopTime()

    if (o.verbose) {
      println()
      println("Building type hierarchy of Slang sourcepath programs ...")
      startTime()
    }

    th = TypeHierarchy.build(F, th(nameMap = t._3, typeMap = t._4), reporter)
    if (reporter.hasError) {
      if (o.parseableMessages) {
        Os.printParseableMessages(reporter)
      } else {
        reporter.printMessages()
      }
      return Either.right(InvalidSources)
    }
    stopTime()

    if (o.verbose) {
      println()
      println("Type outlining Slang sourcepath programs ...")
      startTime()
    }

    th = TypeOutliner.checkOutline(0, o.strictAliasing, th, reporter)
    if (reporter.hasError) {
      if (o.parseableMessages) {
        Os.printParseableMessages(reporter)
      } else {
        reporter.printMessages()
      }
      return Either.right(InvalidSources)
    }
    stopTime()

    @pure def split(text: String, char: C): ISZ[String] = {
      return for (s <- ops.StringOps(text).split((c: C) => c == char)) yield ops.StringOps(s).trim
    }

    if (o.outline) {
      var nameMap: Resolver.NameMap = HashSMap.empty
      var typeMap: Resolver.TypeMap = HashSMap.empty

      if (o.verbose && o.force.nonEmpty) {
        println()
        println("Type checking Slang entities in the force list ...")
        startTime()
      }

      var ok = T
      for (name <- o.force) {
        val ids = split(name, '.')
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
        return Either.right(InvalidForceNames)
      }

      th = TypeChecker.checkComponents(0, o.strictAliasing, th, nameMap, typeMap, reporter)

      if (reporter.hasError) {
        if (o.parseableMessages) {
          Os.printParseableMessages(reporter)
        } else {
          reporter.printMessages()
        }
        return Either.right(InvalidSources)
      }
      if (o.force.nonEmpty) {
        stopTime()
      }

    } else {

      if (o.verbose) {
        println()
        println("Type checking Slang sourcepath programs ...")
        startTime()
      }

      th = TypeChecker.checkComponents(0, o.strictAliasing, th, th.nameMap, th.typeMap, reporter)

      if (reporter.hasError) {
        if (o.parseableMessages) {
          Os.printParseableMessages(reporter)
        } else {
          reporter.printMessages()
        }
        return Either.right(InvalidSources)
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

      if (reporter.hasError) {
        if (o.parseableMessages) {
          Os.printParseableMessages(reporter)
        } else {
          reporter.printMessages()
        }
        return Either.right(InternalError)
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
          if (!SireumApi.writeGzipContent(saveFile, data)) {
            return Either.right(SavingError)
          }
        } else {
          saveFile.writeOverU8s(data)
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
          val p2 = FrontEnd.checkWorksheet(0, thOpt, p, reporter)
          th = p2._1
          if (reporter.hasError) {
            if (o.parseableMessages) {
              Os.printParseableMessages(reporter)
            } else {
              reporter.printMessages()
            }
            return Either.right(InvalidSlangFiles)
          }
          stopTime()

          if (o.verbose) {
            println()
            println(s"Sanity checking computed symbol and type information of ${slangFile._1} ...")
            startTime()
          }

          PostTipeAttrChecker.checkProgram(p2._2, reporter)

          if (reporter.hasError) {
            if (o.parseableMessages) {
              Os.printParseableMessages(reporter)
            } else {
              reporter.printMessages()
            }
            return Either.right(InternalError)
          }
          stopTime()

        case Some(_) => eprintln(s"File '${slangFile._1}' does not contain a Slang program")
        case _ =>
      }
    }

    if (reporter.hasIssue) {
      println()
      if (o.parseableMessages) {
        Os.printParseableMessages(reporter)
      } else {
        reporter.printMessages()
      }
    }

    if (o.verbose) {
      val newUsed = Os.totalMemory - Os.freeMemory
      if (newUsed > used) {
        used = newUsed
      }
      println()
      println(s"Ok! Total time: ${SireumApi.formatSecond(extension.Time.currentMillis - begin)} s, Max memory: ${SireumApi.formatMb(used)} MB")
    }

    return Either.Left(th)
  }
}
