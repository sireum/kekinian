// #Sireum
/*
 Copyright (c) 2017-2021, Robby, Kansas State University
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

import org.sireum._

@ext("Sireum") object SireumApi {
  def version: String = $

  def commitHash: String = $

  def platform: String = $

  def isNative: B = $

  def initInfo: Init.Info = $

  def homeOpt: Option[Os.Path] = $

  def javaHomeOpt: Option[Os.Path] = $

  def scalaHomeOpt: Option[Os.Path] = $

  def scalacPluginJar: Os.Path = $

  def sireumJar: Os.Path = $

  def ideaDir: Os.Path = $

  def ideaUltimateDir: Os.Path = $

  def ideaLibDir: Os.Path = $

  def ideaPluginsDir: Os.Path = $

  def versions: Map[String, String] = $

  def isDev: B = $

  def javaVer: String = $

  def jbrVer: String = $

  def scalaVer: String = $

  def scalacPluginVer: String = $

  def homeFound: B = $

  def javaFound: B = $

  def scalaFound: B = $

  def paths2files(pathFor: String, paths: ISZ[String], checkExist: B): ISZ[Os.Path] = $

  def paths2fileOpt(pathFor: String, path: ISZ[String], checkExist: B): Option[Os.Path] = $

  def path2fileOpt(pathFor: String, path: Option[String], checkExist: B): Option[Os.Path] = $

  def currentTimeMillis: Z = $

  def readGzipContent(path: Os.Path): Option[ISZ[U8]] = $

  def writeGzipContent(path: Os.Path, content: ISZ[U8]): B = $

  def totalMemory: Z = $

  def freeMemory: Z = $

  def formatMb(bytes: Z): String = $

  def formatSecond(millis: Z): String = $

  def instantiate[T](className: String): Option[T] = $

  def bitcodecPrint(spec: bitcodec.Spec): ST = $

  def hamrCodeGen(model: hamr.ir.Aadl,
                  options: hamr.codegen.common.util.CodeGenConfig,
                  reporter: message.Reporter,
                  transpilerCallback: hamr.codegen.common.containers.TranspilerConfig => Z,
                  proyekIveCallback: hamr.codegen.common.containers.ProyekIveConfig => Z): hamr.codegen.common.util.CodeGenResults = $

  def run(args: ISZ[String]): Z = $

  def initRuntimeLibrary(): Unit = $

  @pure def availableCores: Z = $

  @pure def parCores(percentage: Z): Z = $

  @pure def parCoresOpt(percentageOpt: Option[Z]): Z = $

  def checkImage(uri: String): B = $

  def getSoundDuration(uri: String): Option[Z] = $

  def getVideoDuration(uri: String): Option[Z] = $
}