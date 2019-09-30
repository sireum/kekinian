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
import org.sireum.aadl.arsit.ArsitBridge
import java.io.File
import org.sireum.aadl.ir.{Aadl, JSON => irJSON, MsgPack => irMsgPack}

object HAMR {
  def codeGen(o: Cli.HamrCodeGenOption): Z = {
    o.args.size match {
      case z"0" => println(o.help); return 0
      case _ =>
    }

    val inputFile: Option[File] = if(o.args.size != 1) None[File] else Some(new File(o.args(0).native))
    val input = if (inputFile.nonEmpty && inputFile.get.exists) {
      scala.io.Source.fromFile(inputFile.get).getLines.mkString
    } else {
      eprintln("Input file not found.  Expecting exactly 1")
      return -1
    }

    val model: org.sireum.aadl.ir.Aadl = if (o.json) {
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

    val targetingSel4 = o.platform == Platform.SeL4

    val camkesOutputDir = toFile(o.camkesOutputDir)
    val slangOutputDir = toFile(o.outputDir)

    val outputCDirectory = if(targetingSel4) {
      new File(camkesOutputDir, "hamr")
    }  else {
      new File(slangOutputDir, "src/c/nix")
    }

    val packageName: Option[Predef.String] = if(o.packageName.nonEmpty) {
      o.packageName.map(m => cleanupPackageName(m.native))
    } else {
      Some(cleanupPackageName(slangOutputDir.getCanonicalFile.getName))
    }

    var retValue = 0

    if(!o.trustedBuildProfile) {
      val genBlessEntryPoints = false
      val ipc = ArsitBridge.IPCMechanism.valueOf(o.ipc.name.native)
      val platform = ArsitBridge.Platform.valueOf(o.platform.name.native)
      val slangAuxCodeDir = o.slangAuxCodeDir.map(_.native)

      retValue = org.sireum.aadl.arsit.Arsit.run( //
        model, toOption(slangOutputDir), packageName, o.embedArt, genBlessEntryPoints, o.verbose, o.devicesAsThreads,
        ipc, o.excludeComponentImpl, slangAuxCodeDir, toOption(outputCDirectory), platform,
        o.bitWidth.toInt, o.maxStringSize.toInt, o.maxArraySize.toInt)
    }

    if(retValue == 0 && o.platform == Cli.Platform.SeL4) {
      val camkesAuxCodeDirs = o.camkesAuxCodeDirs.map(_.native)
      val aadlRootDir = o.aadlRootDir.map(_.native)
      val hamrIntegration = !o.trustedBuildProfile
      val hamrIncludeDirs = ISZ[Predef.String](outputCDirectory.getAbsolutePath)
      val hamrStaticLib = toOption(new File(outputCDirectory, "sel4-build/libmain.a"))
      val hamrBasePackageName = packageName

      retValue = org.sireum.aadl.act.Act.run(toOption(camkesOutputDir), model, camkesAuxCodeDirs, aadlRootDir,
        hamrIntegration, hamrIncludeDirs, hamrStaticLib, hamrBasePackageName)
    }

    return retValue
  }

  def toFile(o: Option[String]): File = {
    return o match {
      case Some(path) =>
        val f = new File(path.native)
        return if(!f.exists() && !f.mkdirs()) {
          new File(".")
        } else {
          f
        }
      case _ => new File(".")
    }
  }

  def toOption(f: File): Option[Predef.String] = {
    return Some(f.getCanonicalPath)
  }

  def cleanupPackageName(s: Predef.String): Predef.String = {
    return s.replaceAll("-", "_")
  }
}
