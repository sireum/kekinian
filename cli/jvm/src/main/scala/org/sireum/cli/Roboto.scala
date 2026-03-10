// #Sireum
/*
 Copyright (c) 2017-2026,Robby, Kansas State University
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
import org.sireum.message.Reporter

object Roboto {

  @ext("Roboto_Ext") object Ext {
    def run(script: roboto.Script, audioDir: Os.Path, audioExt: String, recordPath: Option[String]): Z = $
    def capture(output: Os.Path): Z = $
    def parseMarkdown(path: Os.Path, args: ISZ[String], reporter: message.Reporter): Option[roboto.Script] = $
  }

  val INVALID_PATH: Z = -2
  val INVALID_SPEC: Z = -3

  def capture(o: Cli.SireumRobotoCaptureOption): Z = {
    o.args match {
      case ISZ(outStr) =>
        val output = Os.path(outStr).canon
        return Ext.capture(output)
      case _ =>
        println(o.help)
        return 0
    }
  }

  @pure def fingerprint(text: String): String = {
    val c = crypto.SHA3.init256
    c.update(conversions.String.toU8is(text))
    return st"${(ops.ISZOps(c.finalise()).take(3), "")}".render
  }

  def run(o: Cli.SireumRobotoRunOption): Z = {
    val path: Os.Path = o.args match {
      case ISZ() =>
        println(o.help)
        return 0
      case ISZ(p, _*) => Os.path(p).canon
    }

    if (!path.exists) {
      eprintln(s"Could not find $path")
      return INVALID_PATH
    }

    val script: roboto.Script = if (path.ext == "md") {
      val reporter = message.Reporter.create
      Ext.parseMarkdown(path, ops.ISZOps(o.args).drop(1), reporter) match {
        case Some(s) =>
          if (reporter.hasError) {
            reporter.printMessages()
            return INVALID_SPEC
          }
          s
        case _ =>
          reporter.printMessages()
          eprintln(s"Failed to parse $path")
          return INVALID_SPEC
      }
    } else {
      val outTemp = Os.temp()
      val r = SlangRunner.run(Cli.SireumSlangRunOption("", path.string +: ops.ISZOps(o.args).drop(1), F, None(), Some(outTemp.string), F, F))
      if (r != 0) {
        eprintln(outTemp.read)
        return INVALID_SPEC
      }
      org.sireum.roboto.JSON.toScript(outTemp.read) match {
        case Either.Left(s) => s
        case _ =>
          eprintln(s"Failed to process $path")
          return INVALID_SPEC
      }
    }

    // Determine TTS service for Presentasi text2speech
    val service: Cli.SireumPresentasiText2speechService.Type = o.service match {
      case Cli.SireumRobotoRunService.Azure => Cli.SireumPresentasiText2speechService.Azure
      case Cli.SireumRobotoRunService.Aws => Cli.SireumPresentasiText2speechService.Aws
      case Cli.SireumRobotoRunService.Elevenlabs => Cli.SireumPresentasiText2speechService.Elevenlabs
      case Cli.SireumRobotoRunService.Google => Cli.SireumPresentasiText2speechService.Google
      case _ => Cli.SireumPresentasiText2speechService.Mary
    }

    // MaryTTS only supports WAV; other services use MP3
    val (format, ext): (Cli.SireumPresentasiText2speechOutputFormat.Type, String) =
      if (service == Cli.SireumPresentasiText2speechService.Mary)
        (Cli.SireumPresentasiText2speechOutputFormat.Wav, "wav")
      else
        (Cli.SireumPresentasiText2speechOutputFormat.Mp3, "mp3")

    val engine: Cli.SireumPresentasiText2speechEngine.Type = o.engine match {
      case Cli.SireumRobotoRunEngine.Neural => Cli.SireumPresentasiText2speechEngine.Neural
      case Cli.SireumRobotoRunEngine.Standard => Cli.SireumPresentasiText2speechEngine.Standard
    }

    // Set up audio directory next to script, namespaced by script name
    val audioDir: Os.Path = {
      val scriptName = ops.StringOps(path.name).substring(0, path.name.size - path.ext.size - 1)
      var d = path.up / "audio" / scriptName
      o.service match {
        case Cli.SireumRobotoRunService.Azure => d = d / "azure"
        case Cli.SireumRobotoRunService.Aws => d = d / "aws"
        case Cli.SireumRobotoRunService.Elevenlabs => d = d / "elevenlabs"
        case Cli.SireumRobotoRunService.Google => d = d / "google"
        case _ => d = d / "marytts"
      }
      o.voice match {
        case Some(v) => d = d / v
        case _ =>
      }
      d.mkdirAll()
      d
    }

    // Collect all Speak texts and generate audio
    var speakTexts = HashSSet.empty[String]
    for (action <- script.actions; command <- action.commands) {
      command match {
        case s: roboto.Speak => speakTexts = speakTexts + s.text
        case _ =>
      }
    }

    if (speakTexts.nonEmpty) {
      println(s"Generating speech audio (${speakTexts.size} entries)...")

      // Track existing generated audio files for clean
      var generatedAudioFiles = HashSSet.empty[Os.Path]
      for (p <- audioDir.list if p.ext == ext) {
        generatedAudioFiles = generatedAudioFiles + p
      }

      for (text <- speakTexts.elements) {
        val fp = fingerprint(text)
        val sub = conversions.String.fromCis(
          for (c <- conversions.String.toCis(
            if (text.size > 32) ops.StringOps(text).substring(0, 32) else text)) yield
            if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9')) c else '_')
        val filename = s"$sub-$fp.$ext"
        val audioFile = audioDir / filename

        generatedAudioFiles = generatedAudioFiles - audioFile

        if (!audioFile.exists) {
          println(s"  Synthesizing: $text")
          val tmp = Os.tempFix("", ".txt")
          tmp.writeOver(text)
          val code = Presentasi.text2speech(Cli.SireumPresentasiText2speechOption(
            help = "",
            args = ISZ(tmp.string),
            force = F,
            output = Some(audioFile.string),
            service = service,
            voice = o.voice,
            awsPath = o.awsPath,
            gender = o.gender,
            key = o.key,
            lang = o.lang,
            outputFormat = format,
            region = o.region,
            voiceLang = o.voiceLang,
            engine = engine,
            elevenLabsKey = o.elevenLabsKey,
            elevenLabsModel = o.elevenLabsModel,
            googleKey = o.googleKey
          ))
          tmp.removeAll()
          if (code != 0) {
            eprintln(s"  Failed to generate audio for: $text")
          }
        } else {
          println(s"  Cached: $text")
        }
      }

      if (o.clean) {
        for (p <- generatedAudioFiles.elements) {
          println(s"  Removing unused: ${p.name}")
          p.removeAll()
        }
      }
    }

    return Ext.run(script, audioDir, ext, o.record)
  }
}