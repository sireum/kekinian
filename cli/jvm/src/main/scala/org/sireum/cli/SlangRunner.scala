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
import org.sireum.Cli._
import org.sireum.Sireum._
import org.sireum.eprintln

object SlangRunner {

  val HomeNotFound: Z = -1
  val NativeUnavailable: Z = -2
  val InvalidOutput: Z = -3
  val GraalError: Z = -4

  def run(o: SlangRunOption): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      println()
      return 0
    }
    if (!homeFound) {
      return HomeNotFound
    }
    val scalaExe: Os.Path = scalaHomeOpt match {
      case Some(scalaHome) => scalaHome / "bin" / (if (Os.isWin) "scala.bat" else "scala")
      case _ => Os.path(if (Os.isWin) "scala.bat" else "scala")
    }
    val inputOpt = path2fileOpt("input", o.input, T)
    val isConsole: B =
      path2fileOpt("output", o.output, F) match {
        case Some(p) =>
          if (p == Os.Path.Kind.Dir) {
            eprintln(s"Output $p cannot be a directory")
            eprintln()
            return InvalidOutput
          } else {
            val d = p.up
            if (!d.exists) {
              d.mkdirAll()
            }
            if (!d.exists) {
              eprintln(s"Could not create parent directory of $p")
              eprintln()
              return InvalidOutput
            } else {
              F
            }
          }
        case _ => T
      }
    val script = path2fileOpt("Slang script", Some(o.args(0).value), T).get
    val wd = script.up
    var command: ISZ[String] = ISZ(
      scalaExe.string,
      "-bootclasspath",
      sireumJar.string,
      s"-Xplugin:$scalacPluginJar",
      "-classpath",
      wd.string,
      "-sourcepath",
      wd.string,
      "-unchecked",
      "-feature"
    )
    if (o.nativ) {
      command = command :+ "-save"
    }
    if (o.transformed) {
      command = command :+ "-Xprint:sireum"
    }
    if (o.server) {
      command = command :+ "-nc"
    }
    command = command :+ script.name
    command = command :+ wd.string
    for (i <- 1 until o.args.size) {
      command :+= o.args(i)
    }
    val inOpt: Option[String] = inputOpt match {
      case Some(f) => Some(f.read)
      case _ =>
        val p = wd / s"${script.name}.txt"
        if (p.exists) Some(p.read) else None()
    }
    val jarFile = wd / s"${script.name}.jar"
    var env = ISZ[(String, String)]()
    javaHomeOpt match {
      case Some(javaHome) =>
        env :+= "JAVA_HOME" ~> javaHome.string
        env :+= "PATH" ~> s"$javaHome${Os.fileSep}bin${Os.pathSep}${Os.env("PATH").get}"
      case _ =>
    }
    scalaHomeOpt match {
      case Some(scalaHome) => env = env :+ "SCALA_HOME" ~> scalaHome.string
      case _ =>
    }
    var p = Os.proc(command).at(wd).env(env)
    if (jarFile.exists) {
      jarFile.removeOnExit()
    }
    if (isConsole) {
      p = p.console
    } else {
      p = p.redirectErr
    }
    inOpt match {
      case Some(in) => p.input(in)
      case _ =>
    }
    var r = p.runCheck()
    if (o.nativ) {
      r = Os.proc(ISZ("native-image", "--version")).run()
      if (!r.ok || ops.StringOps(r.out).contains("GraalVM")) {
        return 0
      }
      command = ISZ("native-image", "--no-server", "-cp", sireumJar.string, "-jar", jarFile.name)
      if (Os.kind != Os.Kind.Mac) {
        command = command :+ "--static"
      }
      val nativeName = s"${script.name}.native"
      command = command :+ nativeName
      r = Os.proc(command).at(jarFile.up).run()
      if (r.ok) {
        (wd / s"$nativeName.o").removeAll()
        println(s"Generated native executable ${wd / nativeName}")
        return 0
      } else {
        eprintln(s"Failed to generate native executable ${wd / nativeName}, exit code: ${r.exitCode}")
        eprint(r.err)
        return GraalError
      }
    } else {
      return 0
    }
  }
}
