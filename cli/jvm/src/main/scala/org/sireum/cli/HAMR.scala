/*
 Copyright (c) 2019, Jason Belt, Kansas State University
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
import org.sireum.Cli
import org.sireum.Cli._
import org.sireum.Cli.HamrPlatform._

import org.sireum.hamr.arsit
import org.sireum.hamr.ir.{Aadl, JSON => irJSON, MsgPack => irMsgPack}

import org.sireum.message._
import org.sireum.ops.StringOps
import org.sireum.Os.Path

object HAMR {

  val toolName: String = "HAMR"

  // cli interface
  def codeGen(o: Cli.HamrCodeGenOption): Int = {
    o.args.size match {
      case z"0 " => println(o.help); return 0
      case _ =>
    }

    val inputFile: Option[Path] = if (o.args.size != 1) None[Path]() else Some(Os.path(o.args(0)))

    val input: String = if (inputFile.nonEmpty && inputFile.get.exists && inputFile.get.isFile) {
      inputFile.get.read
    } else {
      eprintln("Input file not found.  Expecting exactly 1")
      return -1
    }

    val model: Aadl = if (o.json) {
      irJSON.toAadl(input) match {
        case Either.Left(m) => m
        case Either.Right(m) =>
          eprintln(s"Json deserialization error at (${m.line}, ${m.column}): ${m.message}")
          return -1
      }
    }
    else {
      org.sireum.conversions.String.fromBase64(input) match {
        case Either.Left(u) =>
          irMsgPack.toAadl(u) match {
            case Either.Left(m) => m
            case Either.Right(m) =>
              eprintln(s"MsgPack deserialization error at offset ${m.offset}: ${m.message}")
              return -1
          }
        case Either.Right(m) =>
          eprintln(m)
          return -1
      }
    }

    return codeGen(model, o)
  }

  // JAVA/OSATE interface
  def codeGen(model: Aadl,
              //
              verbose: B,
              platform: HamrPlatform.Type,
              slangOutputDir: Option[Predef.String],
              slangPackageName: Option[Predef.String],
              embedArt: B,
              devicesAsThreads: B,
              //
              ipcMechanism: Option[HamrIpcMechanism.Type],
              slangAuxCodeDir: ISZ[Predef.String],
              slangOutputCDirectory: Option[Predef.String],
              excludeComponentImpl: B,
              bitWidth: Int,
              maxStringSize: Int,
              maxArraySize: Int,
              //
              camkesOutputDirectory: Option[Predef.String],
              camkesAuxCodeDirs: ISZ[Predef.String],
              aadlRootDir: Option[Predef.String]
             ): Int = {

    val _ipcMechanism = if(ipcMechanism.isEmpty) { HamrIpcMechanism.MessageQueue } else { ipcMechanism.get }

    val o = Cli.HamrCodeGenOption("", ISZ(), F,
      verbose,
      platform,
      slangOutputDir.map(f => org.sireum.String(f)),
      slangPackageName.map(f => org.sireum.String(f)),
      embedArt,
      devicesAsThreads,
      //
      _ipcMechanism,
      slangAuxCodeDir.map(f => org.sireum.String(f)),
      slangOutputCDirectory.map(f => org.sireum.String(f)),
      excludeComponentImpl,
      bitWidth,
      maxStringSize,
      maxArraySize,
      //
      camkesOutputDirectory.map(f => org.sireum.String(f)),
      camkesAuxCodeDirs.map(f => org.sireum.String(f)),
      aadlRootDir.map(f => org.sireum.String(f))
    )

    return codeGen(model, o)
  }

  def codeGen(model: Aadl, o: Cli.HamrCodeGenOption): Int = {

    val targetingSel4 = o.platform == HamrPlatform.SeL4

    val camkesOutputDir: Path = toPath(o.camkesOutputDir)
    val slangOutputDir: Path = toPath(o.outputDir)

    val outputCDirectory = if(targetingSel4) {
      camkesOutputDir / "hamr"
    }  else {
      slangOutputDir / "src" / "c" / "nix"
    }

    val packageName: String = if(o.packageName.nonEmpty) {
      cleanupPackageName(o.packageName.get)
    } else {
      cleanupPackageName(slangOutputDir.name)
    }

    val (runArsit, runTranspiler, runACT, hamrIntegration) = o.platform match {
      case JVM => (T, F, F, F)
      case Linux | Cygwin | MacOS => (T, T, F, F)
      case SeL4 => (T, T, T, T)
      case SeL4_Only | SeL4_TB => (F, F, T, F)
    }

    var reporter = Reporter.create

    if(runArsit) {
      val arsitReporter = Reporter.create

      val genBlessEntryPoints = false
      val ipc = arsit.Cli.IpcMechanism.byName(o.ipc.name).get
      val platform = arsit.Cli.ArsitPlatform.byName(o.platform.name).get
      val fileSep = StringOps(org.sireum.Os.fileSep).first

      val opt = arsit.Cli.ArsitOption(
        slangOutputDir.abs.value,
        packageName,
        o.embedArt,
        genBlessEntryPoints,
        o.verbose,
        o.devicesAsThreads,
        ipc,
        o.slangAuxCodeDirs,
        toOption(outputCDirectory),
        o.excludeComponentImpl,
        platform,
        o.bitWidth,
        o.maxStringSize,
        o.maxArraySize,
        fileSep
      )

      arsitReporter.info(None(), "HAMR", "Generating Slang artifacts...")

      val results = arsit.Arsit.run(model, opt, arsitReporter)

      var index = printMessages(arsitReporter.messages, 0)

      if(!arsitReporter.hasError) {
        for (r <- results.resources) {
          val p = Os.path(r.path)
          assert(!p.exists || p.isFile)
          p.up.mkdirAll()
          if (r.overwrite || !p.exists) {
            p.writeOver(r.content.render)
            arsitReporter.info(None(), toolName, s"Wrote: ${p}")
            if(r.makeExecutable) {
              p.chmod("700")
              arsitReporter.info(None(), toolName, s"Made ${p} executable")
            }
          } else {
            arsitReporter.info(None(), toolName, s"File exists, will not overwrite: ${p}")
          }
        }
      }

      index = printMessages(arsitReporter.messages, index)

      if(!arsitReporter.hasError && runTranspiler) {

        val ao = results.transpilerOptions.get
        val o = Cli.CTranspilerOption(
          help = "",
          args = ISZ(), //
          sourcepath = ao.sourcepath, //
          output = ao.output, //
          verbose = ao.verbose, //
          projectName = ao.projectName, //
          apps = ao.apps, //
          unroll = ao.unroll, //
          fingerprint = ao.fingerprint, //
          bitWidth = ao.bitWidth, //
          maxStringSize = ao.maxStringSize, //
          maxArraySize = ao.maxArraySize, //
          customArraySizes = ao.customArraySizes, //
          customConstants = ao.customConstants, //
          plugins = ao.plugins, //
          exts = ao.exts, //
          forwarding = ao.forwarding, //
          stackSize = ao.stackSize, //
          excludeBuild = ao.excludeBuild, //
          libOnly = ao.libOnly, //
          stableTypeId = ao.stableTypeId, //
          save = ao.save, //
          load = ao.load
        )
        if (CTranspiler.run(o) != 0) {
          arsitReporter.error(None(), toolName, s"Transpiler did not complete successfully")
        }
      }

      printMessages(arsitReporter.messages, index)

      reporter = Reporter.combine(reporter, arsitReporter)
    }

    if(!reporter.hasError && runACT) {
      val actReporter = Reporter.create

      val hamrIncludeDirs = if(hamrIntegration) { ISZ[String](outputCDirectory.canon.value) } else { ISZ[String]() }
      val hamrStaticLib = if(hamrIntegration) { toOption(outputCDirectory / "sel4-build" / "libmain.a") } else { None[String]() }
      val platform = org.sireum.hamr.act.ActPlatform.byName(o.platform.name).get

      actReporter.info(None(), "HAMR", "Generating CAmkES artifacts...")

      val results = org.sireum.hamr.act.Act.run(
        toOption(camkesOutputDir),
        model,
        o.camkesAuxCodeDirs,
        o.aadlRootDir,
        platform, hamrIncludeDirs, hamrStaticLib, Some(packageName),
        actReporter)

      var index = printMessages(actReporter.messages, 0)

      if(!reporter.hasError) {
        for (r <- results.resources) {
          val p = Os.path(r.path)
          assert(!p.exists || p.isFile)
          p.up.mkdirAll()
          if (r.overwrite || !p.exists) {
            p.writeOver(r.content.render)
            actReporter.info(None(), toolName, s"Wrote: ${p}")
            if(r.makeExecutable) {
              p.chmod("700")
              actReporter.info(None(), toolName, s"Made ${p} executable")
            }
          } else {
            actReporter.info(None(), toolName, s"File exists, will not overwrite: ${p}")
          }
        }
      }
      printMessages(actReporter.messages, 0)
      reporter = Reporter.combine(reporter, actReporter)
    }

    return if(reporter.hasError) 1 else 0
  }

  def printMessages(messages: ISZ[Message], index: Int): Int = {
    for(i <- index until messages.size) {
      val m = messages(i)
      val t = if(m.level != org.sireum.message.Level.Info) s"${m.level.name}: " else ""
      val err = m.level == org.sireum.message.Level.Error
      val mText: String = m.posOpt match {
        case Some(pos) => s"${t}[${pos.beginLine}, ${pos.beginColumn}] ${m.text}"
        case _ => s"${t}${m.text}"
      }
      cprintln(err, mText)
    }
    return messages.size.toInt
  }

  def toPath(o: Option[String]): Path = {
    return o match {
      case Some(path) =>
        val f = Os.path(path)
        assert(!f.exists || f.isDir)
        f.canon.mkdirAll() // f.mkdirAll will throw a FileAlreadyExistsException if path is a symlink to a dir
        return if(!f.exists) {
          Os.path(".")
        } else {
          f
        }
      case _ => Os.path(".")
    }
  }

  def toOption(f: Path): Option[String] = {
    return Some(f.canon.value)
  }

  def cleanupPackageName(s: String): String = {
    return s.native.replaceAll("[\\-|\\.]", "_")
  }
}
