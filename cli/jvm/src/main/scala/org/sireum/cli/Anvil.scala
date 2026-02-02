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
import org.sireum.lang.{IRTranslatorFreshAtomic, ast => AST}
import org.sireum.lang.tipe.TypeHierarchy

object Anvil {

  val InvalidArgs: Z = -6
  val AnvilError: Z = -12

  def run(o: Cli.SireumXAnvilOption, reporter: message.Reporter): Z = {
    @pure def split(text: String, char: C): ISZ[String] = {
      return for (s <- ops.StringOps(text).split((c: C) => c == char)) yield ops.StringOps(s).trim
    }

    val (mfqname, args): (ISZ[String], ISZ[String]) = o.args match {
      case ISZ(fqn) => (split(fqn, '.'), ISZ())
      case ISZ(fqn, f) => (split(fqn, '.'), ISZ(f))
      case ISZ() =>
        println(o.help)
        return 0
      case _ =>
        eprintln(s"Invalid arguments: ${o.args}")
        return InvalidArgs
    }

    val tipeCliOpt = Cli.SireumSlangTipeOption(
      help = o.help,
      args = args,
      exclude = ISZ(),
      force = ISZ(),
      noRuntime = F,
      outline = F,
      parseableMessages = F,
      sourcepath = o.sourcepath,
      strictAliasing = o.strictAliasing,
      verbose = o.verbose,
      save = o.save,
      load = o.load,
      gzip = T
    )
    val th: TypeHierarchy = SlangTipe.run(tipeCliOpt, reporter) match {
      case Either.Left(t) => t
      case Either.Right(exitCode) =>
        reporter.printMessages()
        return exitCode
    }

    def toTyped(tpe: AST.Type): AST.Typed = {
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

    var customArraySizes = HashMap.empty[AST.Typed, Z]
    for (p <- o.customArraySizes) {
      split(p, '=') match {
        case ISZ(key, value) =>
          val num: Z = Z(value) match {
            case Some(n) if n > 0 => n
            case _ =>
              eprintln(s"Custom sequence size should be positive: $p")
              return AnvilError
          }
          val e = lang.parser.Parser.parseExp[AST.Exp.Select](s"o[$key]")
          e.targs(0) match {
            case t: AST.Type.Named =>
              def addS(name: ISZ[String], otherName: ISZ[String], it: AST.Typed, et: AST.Typed): Unit = {
                val t1 = AST.Typed.Name(name, ISZ(it, et))
                customArraySizes = customArraySizes + t1 ~> num
              }

              t.name.ids.map((id: AST.Id) => id.value) match {
                case ISZ("MS") if t.typeArgs.size == 2 =>
                  val it = toTyped(t.typeArgs(0))
                  val et = toTyped(t.typeArgs(1))
                  addS(AST.Typed.msName, AST.Typed.isName, it, et)
                case ISZ("IS") if t.typeArgs.size == 2 =>
                  val it = toTyped(t.typeArgs(0))
                  val et = toTyped(t.typeArgs(1))
                  addS(AST.Typed.isName, AST.Typed.msName, it, et)
                case ISZ("ISZ") if t.typeArgs.size == 1 =>
                  val et = toTyped(t.typeArgs(0))
                  addS(AST.Typed.isName, AST.Typed.msName, AST.Typed.z, et)
                case ISZ("MSZ") if t.typeArgs.size == 1 =>
                  val et = toTyped(t.typeArgs(0))
                  addS(AST.Typed.msName, AST.Typed.isName, AST.Typed.z, et)
                case ISZ("ZS") if t.typeArgs.size == 0 =>
                  addS(AST.Typed.msName, AST.Typed.isName, AST.Typed.z, AST.Typed.z)
                case _ =>
                  eprintln(s"Could not recognize custom sequence size configuration: $p")
                  return AnvilError
              }
            case _ =>
              eprintln(s"Could not recognize custom sequence size configuration: $p")
              return AnvilError
          }
        case _ =>
          eprintln(s"Could not recognize custom sequence size configuration: $p")
          return AnvilError
      }
    }

    var customConstants = HashMap.empty[ISZ[String], AST.Exp]
    for (p <- o.customConstants) {
      split(p, '=') match {
        case ISZ(key, value) =>
          val e = lang.parser.Parser.parseExp[AST.Exp](value)
          e match {
            case _: AST.Lit => customConstants = customConstants + split(key, '.') ~> e
            case _ =>
              eprintln(s"Could not recognize custom object var constant configuration: $p")
              return AnvilError
          }
        case _ =>
          eprintln(s"Could not recognize custom object var constant configuration: $p")
          return AnvilError
      }
    }

    val config = anvil.Anvil.Config(
      nameOpt = o.projectName,
      isFirstGen = T,
      memory = o.memory * 1024,
      defaultBitWidth = o.bitWidth,
      maxStringSize = o.maxStringSize,
      maxArraySize = o.maxArraySize,
      customArraySizes = customArraySizes,
      customConstants = customConstants,
      stackTrace = o.stackTrace,
      runtimeCheck = o.runtimeCheck,
      printSize = o.printSize.getOrElse(0) * 1024,
      copySize = o.copy,
      erase = o.erase,
      noXilinxIp = o.customDivRem,
      splitTempSizes = T,
      tempGlobal = T,
      tempLocal = T,
      memoryAccess = anvil.Anvil.Config.MemoryAccess.Default,
      baseAddress = 0,
      alignAxi4 = F,
      ipMax = 0,
      ipSubroutine = F,
      cpMax = 0,
      genVerilog = F,
      simOpt = None(),
      recursiveDepthMax = 8
    )


    val dir: Os.Path = o.output match {
      case Some(d) => Os.path(d)
      case _ => Os.cwd
    }

    anvil.Anvil.synthesize(F, lang.IRTranslator.createFresh, th, mfqname, config,
      anvil.AnvilOutput(T, SireumApi.versions.get("org.sireum.version.sbt").get, dir), reporter)

    if (reporter.hasError) {
      reporter.printMessages()
      return AnvilError
    }

    reporter.printMessages()

    return 0
  }
}
