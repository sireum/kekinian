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

package org.sireum.cli

import org.sireum._
import org.sireum.Sireum._
import org.sireum.message._
import org.sireum.tools._

object GenTools {

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
      val dest = os.Path(destDir.getCanonicalFile) / s"${o.name.get}.scala"
      val (first, second) = o.width.size match {
        case z"2" => (o.width(0), o.width(1))
        case _ => (z"25", z"55")
      }
      val out = CliGenJvm(lOpt, src, dest.toIO, o.packageName, o.name, first, second)
      os.write.over(dest, out)
      println(s"Wrote $dest")
      0
    } catch {
      case e: Throwable =>
        eprintln(e.getMessage)
        -1
    }

  def iveGen(o: Cli.IvegenOption): Int =
    if (o.args.size != 1) {
      println(o.help)
      0
    } else {
      val HomeNotFound = -1
      val InvalidDir = -2
      val ProjectExists = -3

      val d = os.Path(path2fileOpt("project parent folder", Some(o.args(0)), F).get.getCanonicalFile)
      if (!os.exists(d)) {
        os.makeDir.all(d)
      }
      if (!os.exists(d)) {
        eprintln(s"Could not create $d")
        return InvalidDir
      }
      val project = d / o.name.get.value
      if (os.exists(project) && o.mode == Cli.IveMode.Idea) {
        eprintln(s"Cannot overwrite an existing $project directory in idea mode")
        return ProjectExists
      } else os.makeDir.all(project)

      if (!homeFound) return HomeNotFound
      val home = if (isWin) homeOpt.get.toString.replaceAllLiterally("\\", "/") else homeOpt.get

      val name = o.name.get

      val projectPath = project.toString

      val files =
        if (o.mode == Cli.IveMode.Idea)
          IveGen.idea(isWin, uriPathSep(home.toString), name, projectPath, o.jdk.get, scalaVer, scalacPluginVer)
        else
          IveGen.mill(os.exists(project / "build.sc"), name, projectPath, o.jdk.get, scalaVer, scalacPluginVer)

      for ((path, text) <- files.entries) {
        val p = project / os.RelPath(st"${(path, "/")}".render.value)
        os.makeDir.all(p / os.up)
        os.write.over(p, text.render.value)
      }
      val mill = if (isWin) "mill.bat" else "mill"
      if (o.mode == Cli.IveMode.Mill)
        if (o.millPath)
          os.proc(mill, 'all, "__.compile", "mill.scalalib.GenIdea/idea").
            call(cwd = project, stdout = os.Inherit, stderr = os.Inherit)
        else
          os.proc(homeOpt.get / 'bin / mill, 'all, "__.compile", "mill.scalalib.GenIdea/idea").
            call(cwd = project, stdout = os.Inherit, stderr = os.Inherit)
      println(s"Generated Sireum IVE project at $project")
      0
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
        val dest = os.Path(destDir.getCanonicalFile) / s"$name.scala"
        val reporter = Reporter.create
        val packageNameOpt: Option[ISZ[String]] = if (o.packageName.isEmpty) None() else Some(o.packageName)
        SerializerGenJvm(T, mode, lOpt, srcs, dest.toIO, packageNameOpt, Some(String(name)), reporter) match {
          case Some(out) =>
            os.write.over(dest, out)
            println(s"Wrote $dest")
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
        val dest = os.Path(destDir.getCanonicalFile) / s"$name.scala"
        val reporter = Reporter.create
        TransformerGenJvm(T, mode, lOpt, src, dest.toIO, Some(String(name)), reporter) match {
          case Some(out) =>
            os.write.over(dest, out)
            println(s"Wrote $dest")
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

}
