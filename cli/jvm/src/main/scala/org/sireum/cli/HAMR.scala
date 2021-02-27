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
import org.sireum.message._
import org.sireum.Os.Path
import org.sireum.hamr.ir.{Aadl, JSON => irJSON, MsgPack => irMsgPack}
import org.sireum.hamr.codegen._
import org.sireum.hamr.codegen.common.containers.TranspilerConfig

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
      val fname: String = if(inputFile.nonEmpty) s"'${inputFile.get.value}' " else ""
      eprintln(s"AIR input file ${fname}not found.  Expecting exactly 1")
      return -1
    }

    val model: Aadl = if (o.msgpack) {
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
      else {
        irJSON.toAadl(input) match {
          case Either.Left(m) => m
          case Either.Right(m) =>
            eprintln(s"Json deserialization error at (${m.line}, ${m.column}): ${m.message}")
            return -1
        }
      }
    return codeGen(model, o)
  }

  // JAVA/OSATE interface
  def codeGen(model: Aadl,
              //
              verbose: B,
              platform: Cli.HamrPlatform.Type,
              slangOutputDir: Option[Predef.String],
              slangPackageName: Option[Predef.String],
              noEmbedArt: B,
              devicesAsThreads: B,
              //
              slangAuxCodeDir: ISZ[Predef.String],
              slangOutputCDirectory: Option[Predef.String],
              excludeComponentImpl: B,
              bitWidth: Int,
              maxStringSize: Int,
              maxArraySize: Int,
              runTranspiler: B,
              //
              camkesOutputDirectory: Option[Predef.String],
              camkesAuxCodeDirs: ISZ[Predef.String],
              aadlRootDir: Option[Predef.String],
              //
              experimentalOptions: ISZ[Predef.String]
             ): Int = {

    val o = Cli.HamrCodeGenOption(
      help = "",
      args = ISZ(),
      //
      msgpack = F, // not relevant since in-memory AIR being used
      verbose = verbose,
      platform = platform,
      //
      outputDir = slangOutputDir.map(f => org.sireum.String(f)),
      packageName = slangPackageName.map(f => org.sireum.String(f)),
      noEmbedArt = noEmbedArt,
      devicesAsThreads = devicesAsThreads,
      //
      slangAuxCodeDirs = slangAuxCodeDir.map(f => org.sireum.String(f)),
      slangOutputCDir = slangOutputCDirectory.map(f => org.sireum.String(f)),
      excludeComponentImpl = excludeComponentImpl,
      bitWidth = bitWidth,
      maxStringSize = maxStringSize,
      maxArraySize = maxArraySize,
      runTranspiler = runTranspiler,
      //
      camkesOutputDir = camkesOutputDirectory.map(f => org.sireum.String(f)),
      camkesAuxCodeDirs = camkesAuxCodeDirs.map(f => org.sireum.String(f)),
      aadlRootDir = aadlRootDir.map(f => org.sireum.String(f)),
      //
      experimentalOptions = experimentalOptions.map(f => org.sireum.String(f))
    )

    return codeGen(model, o)
  }

  def codeGen(model: Aadl, o: Cli.HamrCodeGenOption): Int = {

    val ops = CodeGenConfig(
      writeOutResources = T,
      ipc = CodeGenIpcMechanism.SharedMemory,
      //
      verbose = o.verbose,
      platform = CodeGenPlatform.byName(o.platform.name).get,
      //
      slangOutputDir = o.outputDir,
      packageName = o.packageName,
      noEmbedArt = o.noEmbedArt,
      devicesAsThreads = o.devicesAsThreads,
      //
      slangAuxCodeDirs = o.slangAuxCodeDirs,
      slangOutputCDir = o.slangOutputCDir,
      excludeComponentImpl = o.excludeComponentImpl,
      bitWidth = o.bitWidth,
      maxStringSize = o.maxStringSize,
      maxArraySize = o.maxArraySize,
      runTranspiler = o.runTranspiler,
      //
      camkesOutputDir = o.camkesOutputDir,
      camkesAuxCodeDirs = o.camkesAuxCodeDirs,
      aadlRootDir = o.aadlRootDir,
      //
      experimentalOptions = o.experimentalOptions
    )
    
    var reporter = Reporter.create
        
    // call back function
    def transpile(ao: TranspilerConfig): Z = {
      val to = Cli.CTranspilerOption(
        help = "",
        args = ISZ(),
        sourcepath = ao.sourcepath,
        output = ao.output,
        verbose = ao.verbose,
        projectName = ao.projectName,
        apps = ao.apps,
        unroll = ao.unroll,
        fingerprint = ao.fingerprint,
        bitWidth = ao.bitWidth,
        maxStringSize = ao.maxStringSize,
        maxArraySize = ao.maxArraySize,
        customArraySizes = ao.customArraySizes,
        customConstants = ao.customConstants,
        plugins = ao.plugins,
        exts = ao.exts,
        forwarding = ao.forwarding,
        stackSize = ao.stackSize,
        excludeBuild = ao.excludeBuild,
        libOnly = ao.libOnly,
        stableTypeId = ao.stableTypeId,
        save = ao.save,
        load = ao.load,
        cmakeIncludes = ao.cmakeIncludes
      )
      
      val result: Z = CTranspiler.run(to)

      return result
    }
    
    val results: CodeGenResults = CodeGen.codeGen(model, ops, reporter, transpile _ )
    
    return if(reporter.hasError) 1 else 0
  }
}
