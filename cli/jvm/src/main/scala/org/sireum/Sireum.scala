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

object Sireum extends App {


  Cli(File.pathSeparatorChar).parseSireum(ISZ(args.toSeq.map(s => s: String):_ *), 0) match {
    case Some(o: Cli.SlangTipeOption) => cli.SlangTipe.run(o)
    case Some(o: Cli.CligenOption) => cliGen(o)
    case Some(o: Cli.SergenOption) => serGen(o)
    case Some(o: Cli.TransgenOption) => transGen(o)
    case Some(_: Cli.HelpOption) => 0
    case _ => -1
  }

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
        val f = new File(path(0).value)
        if (checkExist && !f.exists) error(s"File ${path(0)} does not exist.")
        return scala.Some(f.getCanonicalFile.getAbsoluteFile)
      case _ =>
        error(s"Expecting a path for $pathFor, but found multiple.")
    }
  }

  def path2fileOpt(pathFor: String, path: Option[String], checkExist: B): scala.Option[File] = {
    if (path.isEmpty) return scala.None
    val f = new File(path.get.value)
    if (checkExist && !f.exists) error(s"File '$path' does not exist.")
    return scala.Some(f.getCanonicalFile.getAbsoluteFile)
  }

  def error(msg: Predef.String): Nothing = {
    throw new RuntimeException(msg)
  }
}
