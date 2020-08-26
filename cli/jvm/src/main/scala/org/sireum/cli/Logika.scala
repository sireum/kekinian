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

package org.sireum.cli

import org.sireum._

object Logika {

  val TODO: Z = -1
  val INVALID_SCRIPT_FILE: Z = -2
  val ILL_FORMED_SCRIPT_FILE: Z = -3
  val INVALID_CHAR_WIDTH: Z = -4
  val INVALID_INT_WIDTH: Z = -5
  val INVALID_VC_DIR: Z = -6

  def run(o: Cli.LogikaVerifierOption): Z = {
    if (o.sourcepath.nonEmpty) {
      eprintln("Logika sourcepath support is coming soon")
      return TODO
    }

    if (o.args.isEmpty) {
      println(o.help)
      println()
      return 0
    }

    o.charBitWidth match {
      case z"8" =>
      case z"16" =>
      case z"32" =>
      case _ =>
        eprintln(s"C (character) bit-width has to be 8, 16, or 32 (instead of ${o.charBitWidth})")
        return INVALID_CHAR_WIDTH
    }

    o.intBitWidth match {
      case z"0" =>
      case z"8" =>
      case z"16" =>
      case z"32" =>
      case z"64" =>
      case _ =>
        eprintln(s"Z (integer) bit-width has to be 0 (arbitrary-precision), 8, 16, 32, or 64 (instead of ${o.intBitWidth})")
        return INVALID_INT_WIDTH
    }

    val (smt2, argsF): (String, Z => ISZ[String] @pure) =
      o.solver match {
        case Cli.LogikaSolver.Z3 => ("z3", logika.Smt2Impl.z3ArgF _)
        case Cli.LogikaSolver.Cvc4 => ("cvc4", logika.Smt2Impl.cvc4ArgF _)
      }
    val smt2Exe: String = Sireum.homeOpt match {
      case Some(home) =>
        val p: Os.Path =
          if (o.solver == Cli.LogikaSolver.Cvc4) home / "bin" / Sireum.platform / (if (Os.isWin) s"$smt2.exe" else smt2)
          else home / "bin" / Sireum.platform / smt2 / "bin" / (if (Os.isWin) s"$smt2.exe" else smt2)
        if (p.exists) p.value else smt2
      case _ => smt2
    }
    var status = T
    var code = z"0"

    for (arg <- o.args) {
      if (o.args.size > 1) {
        println(s"Verifying $arg ...")
      }

      val outputDir: Option[String] =
        o.logVcDir match {
          case Some(d) => Some(s"$d${Os.fileSep}${Os.path(arg).name}")
          case _ => None()
        }
      outputDir match {
        case Some(p) =>
          val path = Os.path(p)
          if (path.exists && !path.isDir) {
            eprintln(s"$p is not a directory")
            return INVALID_VC_DIR
          }
        case _ =>
      }
      val config = logika.Config(3, HashMap.empty, o.timeout, o.unroll, o.charBitWidth, o.intBitWidth, o.logPc,
        o.logRawPc, o.logVc, outputDir, o.splitAll, o.splitIf, o.splitMatch, o.splitContract)
      val f = Os.path(arg)
      if (f.isFile && f.ext.value != ".sc") {
        val reporter = logika.Logika.Reporter.create
        logika.Logika.checkWorksheet(Some(f.value), f.read, config, (th: lang.tipe.TypeHierarchy) =>
            logika.Smt2Impl(smt2Exe, argsF, th, config.charBitWidth, config.intBitWidth), reporter)
        reporter.printMessages()
        if (reporter.hasError) {
          code = if (code == 0) ILL_FORMED_SCRIPT_FILE else code
        }
        if (reporter.hasIssue) {
          status = F
        }
      } else {
        eprintln(s"$arg is not a Slang script file")
        return INVALID_SCRIPT_FILE
      }
      if (o.args.size > 1) {
        println()
      }
    }

    if (status) {
      println("Logika verified!")
    }

    return code
  }
}
