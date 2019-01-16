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

  def run(o: SlangRunOption): Int = {
    if (o.args.isEmpty) {
      println(o.help)
      println()
      return 0
    }
    if (homeOpt.isEmpty) {
      eprintln("Could not detect Sireum's home!")
      eprintln("Please either specify SIREUM_HOME env var or org.sireum.home property in JAVA_OPTS env var.")
      return HomeNotFound
    }
    val scala = scalaHome / 'bin / (if (isWin) "scala.bat" else 'scala)
    for (arg <- o.args) {
      val script = os.Path(path2fileOpt("Slang script", Some(arg.value), checkExist = T).get)
      val p = os.proc(scala, "-bootclasspath", sireumJar, s"-Xplugin:$scalacPluginJar", "-Xscript", 'Slang, script)
      p.call(
        cwd = os.pwd,
        env = _root_.scala.Predef.Map[Predef.String, Predef.String](
          "JAVA_HOME" ~> javaHome.toString,
          "SCALA_HOME" ~> scalaHome.toString,
          "PATH" ~> s"$javaHome/bin${java.io.File.pathSeparatorChar}${System.getenv("PATH")}"),
        stdin = os.Inherit,
        stdout = os.Inherit,
        stderr = os.Inherit,
        check = false
      )
    }
    0
  }
}
