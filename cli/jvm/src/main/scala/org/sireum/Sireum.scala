/*
 Copyright (c) 2018, Robby, Kansas State University
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

package org.sireum

import java.io.{File, FileWriter}

import org.sireum.message.Reporter
import org.sireum.tools.{CliGenJvm, SerializerGen, SerializerGenJvm, TransformerGenJvm}

object Sireum extends scala.App {

  System.exit(Cli(File.pathSeparatorChar).parseSireum(ISZ(args.toSeq.map(s => s: String):_ *), 0) match {
    case Some(o: Cli.SlangTipeOption) => cli.SlangTipe.run(o, Reporter.create)
    case Some(o: Cli.SlangRunOption) => cli.SlangRunner.run(o)
    case Some(o: Cli.CligenOption) => cliGen(o)
    case Some(o: Cli.SergenOption) => serGen(o)
    case Some(o: Cli.TransgenOption) => transGen(o)
    case Some(_: Cli.HelpOption) => 0
    case _ => -1
  })

  def cliGen(o: Cli.CligenOption): Int =
    try {
      o.args.size match {
        case z"0" => println(o.help); return 0
        case z"1" =>
        case _ => println(s"Expecting one argument, but found ${o.args.size}."); return -1
      }
      val lOpt = path2fileOpt("license file", o.license, T)
      val src = paths2fileOpt("config file", o.args, T).get
      val destDir = path2fileOpt("output directory", o.outputDir, T).get
      if (!destDir.isDirectory) error(s"Path ${destDir.getPath} is not a directory")
      val dest = new File(destDir, o.name.get + ".scala")
      val (first, second) = o.width.size match {
        case z"2" => (o.width(0), o.width(1))
        case _ => (z"25", z"55")
      }
      val out = CliGenJvm(lOpt, src, dest, o.packageName, o.name, first, second)
      val fw = new FileWriter(dest)
      fw.write(out)
      fw.close()
      println(s"Wrote ${dest.getAbsolutePath}")
      0
    } catch {
      case e: Throwable =>
        eprintln(e.getMessage)
        -1
    }

  def serGen(o: Cli.SergenOption): Int =
    try {
      if (o.args.isEmpty) {
        println(o.help)
        return 0
      }
      val lOpt = path2fileOpt("license file", o.license, T)
      val srcs = paths2files("Slang file", o.args, T)
      val destDir = path2fileOpt("output directory", o.outputDir, T).get
      if (!destDir.isDirectory) error(s"Path ${destDir.getPath} is not a directory")
      for (m <- o.modes) {
        val (name, mode) = m match {
          case Cli.SerializerMode.Json =>
            (
              if (o.modes.size > 1)
                if (o.name.isEmpty) "JSON" else s"${o.name.get}JSON"
              else if (o.name.isEmpty) "JSON"
              else o.name.get.value,
              SerializerGen.Mode.JSON
            )
          case Cli.SerializerMode.Msgpack =>
            (
              if (o.modes.size > 1)
                if (o.name.isEmpty) "MsgPack" else s"${o.name.get}MsgPack"
              else if (o.name.isEmpty) "MsgPack"
              else o.name.get.value,
              SerializerGen.Mode.MessagePack
            )
        }
        val dest = new File(destDir, name + ".scala")
        val reporter = Reporter.create
        val packageNameOpt: Option[ISZ[String]] = if (o.packageName.isEmpty) None() else Some(o.packageName)
        SerializerGenJvm(T, mode, lOpt, srcs, dest, packageNameOpt, Some(String(name)), reporter) match {
          case Some(out) =>
            val fw = new FileWriter(dest)
            fw.write(out)
            fw.close()
            println(s"Wrote ${dest.getAbsolutePath}")
          case _ =>
            reporter.printMessages()
        }
      }
      0
    } catch {
      case e: Throwable =>
        e.printStackTrace()
        -1
    }

  def transGen(o: Cli.TransgenOption): Int =
    try {
      o.args.size match {
        case z"0" => println(o.help); return 0
        case z"1" =>
        case _ => println(s"Expecting one argument, but found ${o.args.size}."); return -1
      }
      val lOpt = path2fileOpt("license file", o.license, T)
      val src = paths2fileOpt("Slang file", o.args, T).get
      val destDir = path2fileOpt("output directory", o.outputDir, T).get
      if (!destDir.isDirectory) error(s"Path ${destDir.getPath} is not a directory")
      for (m <- o.modes) {
        val (name, mode) = m match {
          case Cli.TransformerMode.Immutable =>
            (
              if (o.modes.size > 1)
                if (o.name.isEmpty) "Transformer" else s"${o.name.get}Transformer"
              else if (o.name.isEmpty) "Transformer"
              else o.name.get.value,
              T
            )
          case Cli.TransformerMode.Mutable =>
            (
              if (o.modes.size > 1)
                if (o.name.isEmpty) "MTransformer" else s"M${o.name.get}Transformer"
              else if (o.name.isEmpty) "MTransformer"
              else o.name.get.value,
              F
            )
        }
        val dest = new File(destDir, name + ".scala")
        val reporter = Reporter.create
        TransformerGenJvm(T, mode, lOpt, src, dest, Some(String(name)), reporter) match {
          case Some(out) =>
            val fw = new FileWriter(dest)
            fw.write(out)
            fw.close()
            println(s"Wrote ${dest.getAbsolutePath}")
          case _ =>
            reporter.printMessages()
        }
      }
      0
    } catch {
      case e: Throwable =>
        eprintln(e.getMessage)
        -1
    }

  def path2File(path: Predef.String): File = {
    if (scala.util.Properties.isWin && path.startsWith("/cygdrive/")) {
      val p = path.substring("/cygdrive/".length)
      val ps = p.split('/')
      new File(p.head + ":\\" + (for (i <- 1 until ps.length) yield ps(i)).mkString("\\"))
    } else new File(path)
  }

  def paths2files(pathFor: String, paths: ISZ[String], checkExist: B): Seq[File] = {
    var r = Vector[File]()
    for (p <- paths) {
      r = r :+ paths2fileOpt(pathFor, ISZ(p), checkExist).get
    }
    r
  }

  def paths2fileOpt(pathFor: String, path: ISZ[String], checkExist: B): scala.Option[File] = {
    path.size match {
      case z"0" => scala.None
      case z"1" =>
        val f = path2File(path(0).value)
        if (checkExist && !f.exists) error(s"File ${path(0)} does not exist.")
        return scala.Some(f.getCanonicalFile.getAbsoluteFile)
      case _ =>
        error(s"Expecting a path for $pathFor, but found multiple.")
    }
  }

  def path2fileOpt(pathFor: String, path: Option[String], checkExist: B): scala.Option[File] = {
    if (path.isEmpty) return scala.None
    val f = path2File(path.get.value)
    if (checkExist && !f.exists) error(s"File '$path' does not exist.")
    return scala.Some(f.getCanonicalFile.getAbsoluteFile)
  }

  def error(msg: Predef.String): Nothing = {
    throw new RuntimeException(msg)
  }

  lazy val isWin: Boolean = scala.util.Properties.isWin

  lazy val platform: Predef.String =
    if (isWin) "win"
    else if (scala.util.Properties.isMac) "mac"
    else if (scala.util.Properties.isLinux) "linux"
    else "unsupported"

  lazy val homeOpt: scala.Option[os.Path] = {
    var r = scala.Option(System.getenv("SIREUM_HOME")).map(envVar => os.Path(new File(envVar).getCanonicalPath))
    if (r.isEmpty) {
      r = scala.Option(System.getProperty("org.sireum.home")).map(p => os.Path(new File(p).getCanonicalPath))
    }
    if (r.nonEmpty) r
    else {
      val uri = getClass.getProtectionDomain.getCodeSource.getLocation.toURI.toASCIIString
      val i = uri.indexOf(".jar")
      if (i >= 0) {
        val path = os.Path(new java.io.File(new java.net.URI(uri.substring(0,
          uri.lastIndexOf('/', i)))).getCanonicalPath) / os.up
        if (os.exists(path / 'bin / platform / 'java) && os.exists(path / 'bin / "sireum.jar") &&
          os.exists(path / 'bin / 'scala) && os.exists(path / 'lib)) scala.Some(path)
        else scala.None
      } else scala.None
    }
  }

  lazy val javaHome: os.Path = homeOpt.get / 'bin / platform / 'java
  lazy val scalaHome: os.Path = homeOpt.get / 'bin / 'scala
  lazy val scalacPluginJar: os.Path =
    os.list(homeOpt.get / 'lib).find(p => p.last.startsWith("scalac-plugin-")).get
  lazy val sireumJar: os.Path = homeOpt.get / 'bin / "sireum.jar"
}
