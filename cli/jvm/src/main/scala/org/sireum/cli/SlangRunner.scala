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
  val IllFormed: Z = -4
  val GraalError: Z = -5

  def run(o: SlangRunOption): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      println()
      return 0
    }
    val javaExe: Os.Path = javaHomeOpt match {
      case Some(javaHome) => javaHome / "bin" / (if (Os.isWin) "java.exe" else "java")
      case _ => Os.Path.Impl(if (Os.isWin) "java.exe" else "java")
    }
    val scalacExe: Os.Path = scalaHomeOpt match {
      case Some(scalaHome) => scalaHome / "bin" / (if (Os.isWin) "scalac.bat" else "scalac")
      case _ => Os.Path.Impl(if (Os.isWin) "scalac.bat" else "scalac")
    }
    val inputOpt = path2fileOpt("input", o.input, T)
    var outputOpt: Option[Os.Path] = None()
    val isConsole: B =
      path2fileOpt("output", o.output, F) match {
        case Some(path) =>
          outputOpt = Some(path)
          if (path == Os.Path.Kind.Dir) {
            eprintln(s"Output $path cannot be a directory")
            eprintln()
            return InvalidOutput
          } else {
            val d = path.up
            if (!d.exists) {
              d.mkdirAll()
            }
            if (!d.exists) {
              eprintln(s"Could not create parent directory of $path")
              eprintln()
              return InvalidOutput
            } else {
              F
            }
          }
        case _ => T
      }
    val script = path2fileOpt("Slang script", Some(o.args(0).value), T).get
    val wd = Os.tempDir()
    wd.removeOnExit()
    val scriptHome = script.up.canon
    val sc = wd / s"anon$$${script.name}"
    var content = script.read
    val contentOps = ops.StringOps(content)
    if (contentOps.startsWith("::#!")) {
      val i = contentOps.stringIndexOf("::!#")
      if (i >= 0) {
        content = contentOps.substring(i + 4, content.size)
      }
    }
    sc.writeOver(
      st"""// #Sireum
          |import org.sireum._
          |object Main {
          |  def main(args: scala.Array[Predef.String]): scala.Unit = {
          |    final class $$anon {
          |      _root_.org.sireum.App.args = _root_.org.sireum.ISZ(((args.map(((s) => _root_.org.sireum.String(s.trim)))).toIndexedSeq: _*));
          |      $content
          |    };
          |    new $$anon()
          |  }
          |}""".render)
    val sJar: Os.Path =
      if (Os.isWin && (sireumJar.up / ".sireum-win.jar").exists) sireumJar.up / ".sireum-win.jar"
      else sireumJar
    var command: ISZ[String] = ISZ(
      scalacExe.string,
      "-bootclasspath",
      sJar.string,
      s"-Xplugin:$scalacPluginJar",
      "-classpath",
      s"$scriptHome${Os.pathSep}$sJar",
      "-sourcepath",
      scriptHome.string,
      "-unchecked",
      "-feature",
      "-deprecation"
    )
    if (o.transformed) {
      command = command :+ "-Xprint:sireum"
    }
    command = command :+ sc.string
    val inOpt: Option[String] = inputOpt match {
      case Some(f) => Some(f.read)
      case _ =>
        val p = script.up / s"${script.name}.txt"
        if (p.exists) Some(p.read) else None()
    }
    var env = ISZ("SLASH_DIR" ~> script.up.string)
    val nativeImage: Os.Path = {
      val niName: String = if (Os.isWin) "native-image.cmd" else "native-image"
      homeOpt match {
        case Some(home) =>
          env = env :+ "SIREUM_HOME" ~> home.string
          val p = home / "bin" / platform / "graal" / "bin" / niName
          if (p.isFile) p else Os.path(niName)
        case _ => Os.path(niName)
      }
    }
    var p = Os.proc(command).at(wd).env(env).console
    var r = p.run()
    if (!r.ok) {
      return IllFormed
    }
    (wd / "META-INF").mkdir()
    (wd / "META-INF" / "MANIFEST.MF").writeOver(
      st"""Manifest-Version: 1.0
          |Created-By: Slang Runner
          |Main-Class: Main
          |""".render
    )
    val jarFile = script.up / s"${script.name}.jar"
    wd.zipTo(jarFile)
    jarFile.removeOnExit()
    p = Os.proc(ISZ[String](javaExe.string, "-classpath", s"$jarFile${Os.pathSep}$sJar", "Main") ++
      (for (i <- 1 until o.args.size) yield o.args(i))).env(env).at(Os.cwd)
    if (isConsole) {
      p = p.console
    } else {
      p = p.redirectErr
    }
    inOpt match {
      case Some(in) => p.input(in)
      case _ =>
    }
    r = p.run()
    outputOpt match {
      case Some(path) => path.writeOver(r.out)
      case _ =>
    }
    if (!r.ok) {
      return IllFormed
    }
    if (o.nativ) {
      val nativeName = s"${script.name}.com"
      val nativ = script.up / nativeName
      if (nativ.exists && nativ.lastModified > script.lastModified) {
        return 0
      }
      r = Os.proc(ISZ(nativeImage.string, "--version")).env(env).standard.run()
      if (!(r.ok && ops.StringOps(r.out).contains("GraalVM"))) {
        return 0
      }
      println(s"Generating native image $nativ ...")
      val flags: ISZ[String] = Os.kind match {
        case Os.Kind.Mac => ISZ("--no-server")
        case Os.Kind.Linux => ISZ("--no-server", "--static")
        case Os.Kind.LinuxArm => ISZ("--no-server", "--static")
        case Os.Kind.Win => ISZ("--static")
        case _ => return 0
      }
      command = (nativeImage.string +: flags) ++ ISZ("--initialize-at-build-time",
        "--report-unsupported-elements-at-runtime", "--no-fallback", "-cp", sJar.string, "-jar", jarFile.name, nativeName)
      r = Os.proc(command).at(jarFile.up).console.bufferErr.run()
      if (r.exitCode != 0) {
        for (line <- ops.StringOps(r.err).split((c: C) => c === '\n') if !ops.StringOps(line).startsWith("warning: unknown anonymous info")) {
          eprintln(line)
        }
      }
      for (f <- scriptHome.list if ops.StringOps(f.name).startsWith(s"$nativeName.") && !ops.StringOps(f.name).endsWith(".exe")) {
        f.removeAll()
      }
      (scriptHome / s"$nativeName.o").removeAll()
      if ((scriptHome / s"$nativeName.exe").exists) {
        (scriptHome / s"$nativeName.exe").moveOverTo(scriptHome / nativeName)
      }
      if ((scriptHome / s"${script.name}.jar").exists) {
        (scriptHome / s"${script.name}.jar").removeAll()
      }
      if (r.ok) {
        return 0
      } else {
        (scriptHome / s"$nativeName.exe").removeAll()
        eprintln(s"Failed to generate native executable $nativ, exit code: ${r.exitCode}")
        eprint(r.err)
        return GraalError
      }
    } else {
      return 0
    }
  }
}
