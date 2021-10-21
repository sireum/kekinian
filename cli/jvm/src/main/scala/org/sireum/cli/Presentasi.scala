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
package org.sireum.cli

import org.sireum._

object Presentasi {

  def maryTTS(javaHome: Os.Path, maryTtsJar: Os.Path, voice: String, input: Os.Path, output: Os.Path): OsProto.Proc.Result = {
    val javaExe: Os.Path = if (Os.isWin) javaHome / "bin" / "java.exe" else javaHome / "bin" / "java"
    return proc"$javaExe -jar $maryTtsJar -o $output -v $voice -i $input".env(ISZ("JAVA_HOME" ~> javaHome.string)).console.runCheck()
  }

  @pure def outputFile(output: Os.Path, inputFilename: String, i: Z, line: String, ext: String): Os.Path = {
    var num: String = s"$i-"
    while (num.size < 3) {
      num = s"0$num"
    }
    if (output == Os.cwd) {
      var cis = ISZ[C]()
      for (c <- conversions.String.toCStream(line).take(16)) {
        cis = cis :+ (if (c === '.' || c === ',' || c === '!' || c === ' ' || c === '*' || c === '+') '_' else c)
      }
      return output.canon / s"$num${conversions.String.fromCis(cis)}.$ext"
    } else {
      return output.up.canon / s"$num$inputFilename.$ext"
    }
  }


  def text2speech(o: Cli.SireumPresentasiText2speechOption): Z = {
    val inputFile: Os.Path = o.args match {
      case ISZ() =>
        println(o.help)
        println()
        println(
          st"""Available MaryTTS voices are: cmu-bdl-hsmm, cmu-rms-hsmm, cmu-slt-hsmm, dfki-obadiah-hsmm, dfki-prudence-hsmm, dfki-spike-hsmm
              |
              |For Azure, please refer to https://docs.microsoft.com/en-us/azure/cognitive-services/speech-service/rest-text-to-speech""".render)
        return 0
      case ISZ(p) =>
        val f = Os.path(p)
        if (!f.isFile) {
          eprintln(s"$p is not a file")
          Os.exit(-1)
          halt("")
        }
        f
      case _ =>
        println(o.help)
        return -1
    }
    val javaHome = SireumApi.javaHomeOpt.get
    val maryTtsJar: Os.Path = SireumApi.homeOpt.get / "lib" / "marytts_text2wav.jar"

    val output: Os.Path = o.output match {
      case Some(p) =>
        val f = Os.path(p)
        f.mkdirAll()
        f
      case _ => Os.cwd
    }

    o.service match {
      case Cli.SireumPresentasiText2speechService.Azure =>
        val key: String = o.key match {
          case Some(k) => k
          case _ =>
            Os.env("AZURE_KEY") match {
              case Some(k) => k
              case _ =>
                eprintln("Please supply your Azure subscription key via CLi option --key or via the AZURE_KEY env var")
                Os.exit(-1)
                halt("")
            }
        }
        var i = 1
        val tmp = Os.tempFix("", if (Os.isWin) ".bat" else "")
        val echoOffOpt: Option[String] = if (Os.isWin) Some("@echo off") else None()
        tmp.removeOnExit()
        val voice = o.voice.getOrElseEager("en-GB-RyanNeural")
        val (format, ext): (String, String) = o.outputFormat match {
          case Cli.SireumPresentasiText2speechOutputFormat.Mp3_48khz_192kbit => ("audio-48khz-192kbitrate-mono-mp3", "mp3")
          case Cli.SireumPresentasiText2speechOutputFormat.Pcm_48khz_16bit => ("raw-48khz-16bit-mono-pcm", "wav")
          case Cli.SireumPresentasiText2speechOutputFormat.Webm_24khz_16bit => ("webm-24khz-16bit-mono-opus", "webm")
          case Cli.SireumPresentasiText2speechOutputFormat.Ogg_48khz_16bit => ("ogg-48khz-16bit-mono-opus", "ogg")
        }
        for (line <- inputFile.readLineStream if ops.StringOps(line).trim.size > 0) {
          val out = outputFile(output, inputFile.name, i, line, ext)
          if (!out.exists || o.force) {
            println(s"Synthesizing: $line")
            tmp.writeOver(
              st"""$echoOffOpt
                  |curl --location --request POST 'https://${o.region.get}.tts.speech.microsoft.com/cognitiveservices/v1' --header 'Ocp-Apim-Subscription-Key: $key' --header 'Content-Type: application/ssml+xml' --header 'X-Microsoft-OutputFormat: $format' --header 'User-Agent: curl' --data-raw '<speak version="1.0" xml:lang="${o.lang.get}"><voice xml:lang="${o.voiceLang.get}" xml:gender="${o.gender.get}" name="$voice">${ops.StringOps(line).replaceAllLiterally("'", "'\\''")}</voice></speak>' -o $out""".render)
            tmp.chmod("+x")
            proc"$tmp".console.runCheck()
            println()
          } else {
            println(s"Skipping already generated: $line")
            println()
          }
          i = i + 1
        }
      case Cli.SireumPresentasiText2speechService.Mary =>
        if (!maryTtsJar.exists) {
          val cacheJar: Os.Path = Os.env("SIREUM_CACHE") match {
            case Some(c) => Os.path(c) / "sireum" / maryTtsJar.name
            case _ => Os.home / "Downloads" / "sireum" / maryTtsJar.name
          }
          maryTtsJar.up.mkdirAll()
          if (!cacheJar.exists) {
            cacheJar.up.mkdirAll()
            println("Please wait while downloading MaryTTS text2wav ...")
            cacheJar.downloadFrom("https://github.com/sireum/rolling/releases/download/marytts-text2wav/txt2wav.jar")
            cacheJar.copyOverTo(maryTtsJar)
          }
          println()
        }
        var i = 1
        val tmp = Os.temp()
        tmp.removeOnExit()
        val voice = o.voice.getOrElseEager("dfki-spike-hsmm")
        for (line <- inputFile.readLineStream if ops.StringOps(line).trim.size > 0) {
          val out = outputFile(output, inputFile.name, i, line, "wav")
          if (!out.exists || o.force) {
            println(s"Synthesizing: $line")
            tmp.writeOver(line)
            maryTTS(javaHome, maryTtsJar, voice, tmp, out)
            println()
          } else {
            println(s"Skipping already generated: $line")
            println()
          }
          i = i + 1
        }
    }
    return 0
  }
}
