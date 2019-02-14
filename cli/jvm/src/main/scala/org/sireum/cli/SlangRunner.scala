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

  val HomeNotFound: Int = -1
  val NativeUnavailable: Int = -2
  val InvalidOutput: Int = -3
  val GraalError: Int = -4

  def run(o: SlangRunOption): Int = {
    if (o.args.isEmpty) {
      println(o.help)
      println()
      return 0
    }
    if (!homeFound) return HomeNotFound
    if (o.nativ && isWin) {
      eprintln("Native code generation is not currently available for Windows")
      return NativeUnavailable
    }
    val scalaExe = scalaHome / 'bin / (if (isWin) "scala.bat" else 'scala)
    val inputOpt = path2fileOpt("input", o.input, T)
    val (stdout, stderr) =
      path2fileOpt("output", o.output, F) match {
        case scala.Some(f) =>
          val p = os.Path(f.getCanonicalFile)
          if (os.isDir(p)) {
            eprintln(s"Output $p cannot be a directory")
            eprintln()
            return InvalidOutput
          } else {
            val d = p / os.up
            if (!os.exists(d)) {
              os.makeDir.all(d)
            }
            if (!os.exists(d)) {
              eprintln(s"Could not create parent directory of $p")
              eprintln()
              return InvalidOutput
            } else (p: os.ProcessOutput, p: os.ProcessOutput)
          }
        case _ => (os.Inherit: os.ProcessOutput, os.Inherit: os.ProcessOutput)
      }
    val script = os.Path(path2fileOpt("Slang script", Some(o.args(0).value), checkExist = T).get)
    val wd = script / os.up
    var command =
      Vector[os.Shellable](
        scalaExe,
        "-bootclasspath",
        sireumJar,
        s"-Xplugin:$scalacPluginJar",
        "-classpath",
        wd,
        "-sourcepath",
        wd,
        "-unchecked",
        "-feature"
      )
    if (o.nativ) command :+= ("-save": os.Shellable)
    if (o.transformed) command :+= ("-Xprint:sireum": os.Shellable)
    if (o.server) command :+= ("-nc": os.Shellable)
    command :+= (script.last: os.Shellable)
    command :+= (wd: os.Shellable)
    for (i <- 1 until o.args.size) {
      command :+= (o.args(i).value: os.Shellable)
    }
    val stdin: os.ProcessInput = inputOpt match {
      case scala.Some(f) => os.Path(f.getCanonicalFile)
      case _ =>
        val p = wd / s"${script.last}.txt"
        if (os.exists(p)) p else os.Inherit
    }
    val jarFile = wd / s"${script.last}.jar"
    val env = _root_.scala.Predef.Map[Predef.String, Predef.String](
      "JAVA_HOME" -> javaHome.toString,
      "SCALA_HOME" -> scalaHome.toString,
      "PATH" -> s"$javaHome/bin${java.io.File.pathSeparatorChar}${System.getenv("PATH")}"
    )
    try {
      val r = os.proc(command: _*)
        .call(
          cwd = wd,
          env = env,
          stdin = stdin,
          stdout = stdout,
          stderr = stderr,
          check = false
        )
      if (r.exitCode != 0) {
        eprintln(s"Error encountered when running $script")
        return r.exitCode
      }
      if (o.nativ) {
        val nativeName = s"${script.last}.native"
        command = Vector(javaHome / 'bin / "native-image", "--no-server", "-cp", sireumJar, "-jar", jarFile.last)
        if (!scala.util.Properties.isMac) command :+= ("--static" : os.Shellable)
        command :+= (nativeName : os.Shellable)
        val graal = os.proc(command: _*).
          call(cwd = os.pwd, env = env, stdin = os.Inherit, stdout = os.Inherit, stderr = os.Inherit, check = false)
        if (graal.exitCode == 0) {
          os.remove.all(wd / s"$nativeName.o")
          println(s"Generated native executable ${wd / nativeName}")
        } else {
          eprintln(s"Failed to generate native executable ${wd / nativeName}")
          return GraalError
        }
      }
    } finally os.remove.all(jarFile)
    0
  }
}
