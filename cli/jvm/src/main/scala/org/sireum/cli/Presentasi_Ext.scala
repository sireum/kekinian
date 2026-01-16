/*
 Copyright (c) 2017-2025, Robby, Kansas State University
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

import org.sireum.$internal.###
import org.sireum._
import org.sireum.U64._

object Presentasi_Ext {

  ###("true" == System.getenv("PROYEK_JFX") || scala.util.Try(Class.forName("javafx.stage.Stage", false, getClass.getClassLoader)).isSuccess) {

    def initJavaFX(): Unit = NativeUtil.nonNative((), { () => presentasi.PresentasiJFX.initJavaFX() })

    def getSoundDuration(uri: String): Option[Z] = NativeUtil.nonNative(None[Z](), { () =>
      val r = Z(presentasi.PresentasiJFX.getSoundDuration(uri.value))
      if (r < 0) None[Z]() else Some(r)
    })

    def getVideoDuration(uri: String): Option[Z] = NativeUtil.nonNative(None[Z](), { () =>
      val r = Z(presentasi.PresentasiJFX.getVideoDuration(uri.value))
      if (r < 0) None[Z]() else Some(r)
    })

    def checkImage(uri: String): B = NativeUtil.nonNative(F, { () =>
      B(presentasi.PresentasiJFX.checkImage(uri.value))
    })

    def shutdown(): Unit = NativeUtil.nonNative((), { () => presentasi.PresentasiJFX.shutdown() })
  }

  ###(!("true" == System.getenv("PROYEK_JFX") || scala.util.Try(Class.forName("javafx.stage.Stage", false, getClass.getClassLoader)).isSuccess)) {
    def err(): Nothing = {
      System.err.println("JavaFX is not available under this setup")
      System.err.flush()
      System.exit(-1)
      throw new RuntimeException("")
    }

    def initJavaFX(): Unit = err()

    def getSoundDuration(uri: String): Option[Z] = err()

    def getVideoDuration(uri: String): Option[Z] = err()

    def checkImage(uri: String): B = err()

    def shutdown(): Unit = err()
  }

  def pcm2wav(path: Os.Path, srate: Z): Unit = {
    val rate = srate.toInt

    def rawToWave(rawFile: java.io.File, waveFile: java.io.File): Unit = {
      import java.io.DataInputStream
      import java.io.DataOutputStream
      import java.io.FileInputStream
      import java.io.FileOutputStream
      import java.io.IOException
      import java.nio.ByteBuffer
      import java.nio.ByteOrder
      def fullyReadFileToBytes(f: java.io.File) = {
        val size = f.length.asInstanceOf[Int]
        val bytes = new Array[Byte](size)
        val tmpBuff = new Array[Byte](size)
        val fis = new FileInputStream(f)
        try {
          var read = fis.read(bytes, 0, size)
          if (read < size) {
            var remain = size - read
            while ( {
              remain > 0
            }) {
              read = fis.read(tmpBuff, 0, remain)
              System.arraycopy(tmpBuff, 0, bytes, size - remain, read)
              remain -= read
            }
          }
        } catch {
          case e: IOException =>
            throw e
        } finally fis.close()
        bytes
      }

      def writeInt(output: DataOutputStream, value: Int): Unit = {
        output.write(value >> 0)
        output.write(value >> 8)
        output.write(value >> 16)
        output.write(value >> 24)
      }

      def writeShort(output: DataOutputStream, value: Short): Unit = {
        output.write(value >> 0)
        output.write(value >> 8)
      }

      def writeString(output: DataOutputStream, value: Predef.String): Unit = {
        for (i <- 0 until value.length) {
          output.write(value.charAt(i))
        }
      }

      val rawData = new Array[Byte](rawFile.length.asInstanceOf[Int])
      var input: DataInputStream = null
      try {
        input = new DataInputStream(new FileInputStream(rawFile))
        input.read(rawData)
      } finally if (input != null) input.close()
      var output: DataOutputStream = null
      try {
        val data = fullyReadFileToBytes(rawFile)
        output = new DataOutputStream(new FileOutputStream(waveFile))
        writeString(output, "RIFF") // chunk id

        writeInt(output, 36 + rawData.length) // chunk size

        writeString(output, "WAVE") // format

        writeString(output, "fmt ") // subchunk 1 id

        writeInt(output, 16) // subchunk 1 size

        writeShort(output, 1.toShort) // audio format (1 = PCM)

        writeShort(output, 1.toShort) // number of channels

        writeInt(output, rate) // sample rate

        writeInt(output, rate * 2) // byte rate

        writeShort(output, 2.toShort) // block align

        writeShort(output, 16.toShort) // bits per sample

        writeString(output, "data") // subchunk 2 id

        writeInt(output, rawData.length) // subchunk 2 size

        // Audio data (conversion big endian -> little endian)
        val shorts = new Array[Short](rawData.length / 2)
        ByteBuffer.wrap(rawData).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer.get(shorts)
        val bytes = ByteBuffer.allocate(shorts.length * 2)
        for (s <- shorts) {
          bytes.putShort(s)
        }
        output.write(data)
      } finally if (output != null) output.close()
    }

    val f = new java.io.File(path.canon.value.value)
    rawToWave(f, f)
  }

  def parseMarkdowns(args: ISZ[String], path: Os.Path, reporter: message.Reporter): ISZ[presentasi.Presentation] = {
    val argsKey = st"${(args, "")}".render.value
    val parser = org.commonmark.parser.Parser.builder().
      extensions(java.util.List.of(org.commonmark.ext.front.matter.YamlFrontMatterExtension.create)).build()
    var audioSubst = HashSMap.empty[String, String]
    var substs = HashSMap.empty[String, String]
    var ccs = HashSMap.empty[String, String]
    var entries = ISZ[presentasi.Presentation.Entry]()

    def document(f: Os.Path): presentasi.Presentation = {
      val fContent = f.read.value
      val uriOpt = Option.some(f.toUri)
      val docInfo = message.DocInfo.create(uriOpt, fContent)
      var r = presentasi.Presentation.empty(name = "Presentasi", args = args)
      val dir = new java.io.File(f.value.value).getParentFile
      var ccMap = HashSMap.empty[String, String]
      import org.commonmark.node._
      import org.commonmark.ext.front.matter._
      def getPosOpt(node: Node): Option[message.Position] = {
        if (node == null) return None()
        val li = node.getSourceSpans
        if (li.isEmpty) {
          return Some(message.PosInfo(docInfo, u64"0"))
        } else {
          val offset = li.get(0).getInputIndex
          val last = li.get(li.size - 1)
          val length = last.getInputIndex + last.getLength - offset + 1
          return Some(message.PosInfo(docInfo, conversions.Z.toU64(offset) << u64"32" + conversions.Z.toU64(length)))
        }
      }
      def printTree(indent: String, node: org.commonmark.node.Node): Unit = {
        print(indent)
        print(node.getClass)
        node match {
          case node: org.commonmark.ext.front.matter.YamlFrontMatterNode =>
            print(": ")
            print(node.getKey)
            print(" [")
            print(node.getValues.toArray.map(_.toString).mkString(", "))
            print("]")
          case _ =>
        }
        println()
        var child = node.getFirstChild
        while (child != null) {
          printTree(s"$indent  ", child)
          child = child.getNext
        }
      }

      def getTexts(node: Node): Vector[Predef.String] = {
        var r = Vector[Predef.String]()

        def rec(n: Node): Unit = {
          var child = n.getFirstChild
          while (child != null) {
            child match {
              case child: Text =>
                var content = child.getLiteral
                for (p <- audioSubst.entries) {
                  content = content.replace(s"$$${p._1}$$", p._2.value)
                }
                r = r :+ content.trim
              case _ => rec(child)
            }
            child = child.getNext
          }
        }

        rec(node)
        r
      }

      val d = parser.parse(fContent).asInstanceOf[Document]
      //printTree("", d)
      var child = d.getFirstChild
      child match {
        case yaml: YamlFrontMatterBlock =>
          var yamlChild = yaml.getFirstChild
          while (yamlChild != null) {
            yamlChild match {
              case yamlChild: YamlFrontMatterNode =>
                yamlChild.getKey match {
                  case "name" if yamlChild.getValues.size == 1 =>
                    val idPattern = "^([a-zA-Z_$][a-zA-Z\\d_$]*)$".r
                    yamlChild.getValues.get(0) match {
                      case name@idPattern(_*) => r = r(name = name)
                      case name => reporter.error(getPosOpt(yamlChild), Presentasi.kind, s"Invalid Java identifier for name: $name")
                    }
                  case "delay" if yamlChild.getValues.size == 1 =>
                    Z(yamlChild.getValues.get(0)) match {
                      case Some(n) => r = r(delay = n)
                      case _ => reporter.error(getPosOpt(yamlChild), Presentasi.kind, s"Could not parse delay: ${yamlChild.getValues.get(0)}")
                    }
                  case "textDelay" if yamlChild.getValues.size == 1 =>
                    Z(yamlChild.getValues.get(0)) match {
                      case Some(n) => r = r(textDelay = n)
                      case _ => reporter.error(getPosOpt(yamlChild), Presentasi.kind, s"Could not parse textDelay: ${yamlChild.getValues.get(0)}")
                    }
                  case "vseekDelay" if yamlChild.getValues.size == 1 =>
                    Z(yamlChild.getValues.get(0)) match {
                      case Some(n) => r = r(vseekDelay = n)
                      case _ => reporter.error(getPosOpt(yamlChild), Presentasi.kind, s"Could not parse vseekDelay: ${yamlChild.getValues.get(0)}")
                    }
                  case "textVolume" if yamlChild.getValues.size == 1 =>
                    F64(yamlChild.getValues.get(0)) match {
                      case Some(n) => r = r(textVolume = n)
                      case _ => reporter.error(getPosOpt(yamlChild), Presentasi.kind, s"Could not parse textVolume: ${yamlChild.getValues.get(0)}")
                    }
                  case "trailing" if yamlChild.getValues.size == 1 =>
                    Z(yamlChild.getValues.get(0)) match {
                      case Some(n) => r = r(trailing = n)
                      case _ => reporter.error(getPosOpt(yamlChild), Presentasi.kind, s"Could not parse trailing: ${yamlChild.getValues.get(0)}")
                    }
                  case "granularity" if yamlChild.getValues.size == 1 =>
                    Z(yamlChild.getValues.get(0)) match {
                      case Some(n) => r = r(granularity = n)
                      case _ => reporter.error(getPosOpt(yamlChild), Presentasi.kind, s"Could not parse granularity: ${yamlChild.getValues.get(0)}")
                    }
                  case "audio" =>
                    for (v <- yamlChild.getValues.toArray) {
                      v.toString.split(':') match {
                        case Array(key, value) =>
                          val audio = Os.path(new java.io.File(dir, value.trim).getCanonicalPath)
                          if (!audio.exists) reporter.error(getPosOpt(yamlChild), Presentasi.kind, s"Non-existing audio substitution path: $audio")
                          audioSubst = audioSubst + String(key.trim) ~> audio.value
                        case _ => reporter.error(getPosOpt(yamlChild), Presentasi.kind, s"Expecting <key>: <path> pair but found: $v")
                      }
                    }
                  case "cc" =>
                    for (v <- yamlChild.getValues.toArray) {
                      v.toString.split(':') match {
                        case Array(key, value) =>
                          ccs = ccs + String(key.trim) ~> value.trim
                        case _ => reporter.error(getPosOpt(yamlChild), Presentasi.kind, s"Expecting <key>: <path> pair but found: $v")
                      }
                    }
                  case key if key.startsWith("subst") =>
                    def parseSubst(): Unit = for (v <- yamlChild.getValues.toArray) {
                      v.toString.split(':') match {
                        case Array(key, value) => substs = substs + String(key.trim) ~> String(value.trim)
                        case _ => reporter.error(getPosOpt(yamlChild), Presentasi.kind, s"Expecting <key>: <path> pair but found: $v")
                      }
                    }

                    val substKey = key.substring("subst".length)
                    if (argsKey.startsWith(substKey)) parseSubst()
                }
              case _ =>
            }
            yamlChild = yamlChild.getNext
          }
          child = child.getNext
        case _ =>
      }
      var prevChild: Node = d
      while (child != null) {
        child match {
          case heading: Heading =>
            if (!child.getSourceSpans.isEmpty) prevChild = child
            child = child.getNext
            var media = ""
            var mediaNode: Node = null
            var code = ""
            var codeNode: Node = null
            var text = Vector[Predef.String]()
            while (child != null && !child.isInstanceOf[Heading]) {
              child match {
                case child: Paragraph if child.getFirstChild.isInstanceOf[Code] && child.getFirstChild.getNext == null =>
                  codeNode = child.getFirstChild
                  code = codeNode.asInstanceOf[Code].getLiteral
                case child: Paragraph if child.getFirstChild.isInstanceOf[Image] && child.getFirstChild.getNext == null =>
                  mediaNode = child.getFirstChild
                  media = new java.io.File(dir, mediaNode.asInstanceOf[Image].getDestination).getCanonicalPath
                case child: BulletList =>
                  var listItem = child.getFirstChild.asInstanceOf[ListItem]
                  while (listItem != null) {
                    text = (text :+ "") ++ getTexts(listItem)
                    listItem = listItem.getNext.asInstanceOf[ListItem]
                  }
                case _ =>
                  reporter.error(getPosOpt(child), Presentasi.kind, s"Unrecognized structure ${child.getClass}: ${child.toString}")
              }
              if (!child.getSourceSpans.isEmpty) prevChild = child
              child = child.getNext
            }
            if (media.isEmpty) reporter.error(getPosOpt(heading), Presentasi.kind, "Missing image/video path")
            if (!Os.path(media).exists) reporter.error(getPosOpt(mediaNode), Presentasi.kind, s"Non-existent image/video path: $media")
            def substText(m: HashSMap[String, String], v: Vector[Predef.String]): Vector[Predef.String] = {
              var r = v
              for (subst <- m.entries) r = r.map(_.replace(s"$$${subst._1.value}$$", subst._2.value))
              r
            }
            if (Os.path(media).ext.value == "mp4") {
              var delay: Z = 0
              var volume: F64 = 1d
              var rate: F64 = 1d
              var start: F64 = 0d
              var end: F64 = 0d
              var useVideoDuration: B = false
              for (property <- code.split(',')) {
                property.split('=') match {
                  case Array(key, value) =>
                    val v = value.trim
                    key.trim match {
                      case "delay" => Z(v) match {
                        case Some(n) => delay = n
                        case _ => reporter.error(getPosOpt(codeNode), Presentasi.kind, s"Invalid delay property value: $v")
                      }
                      case "volume" => F64(v) match {
                        case Some(n) => volume = n
                        case _ => reporter.error(getPosOpt(codeNode), Presentasi.kind, s"Invalid volume property value: $v")
                      }
                      case "rate" => F64(v) match {
                        case Some(n) => rate = n
                        case _ => reporter.error(getPosOpt(codeNode), Presentasi.kind, s"Invalid rate property value: $v")
                      }
                      case "start" => F64(v) match {
                        case Some(n) => start = n
                        case _ => reporter.error(getPosOpt(codeNode), Presentasi.kind, s"Invalid start property value: $v")
                      }
                      case "end" => F64(v) match {
                        case Some(n) => end = n
                        case _ => reporter.error(getPosOpt(codeNode), Presentasi.kind, s"Invalid end property value: $v")
                      }
                      case "useVideoDuration" =>
                        if (v == "T" || v == "true") useVideoDuration = T
                        else if (v == "F" || v == "false") useVideoDuration = F
                        else reporter.error(getPosOpt(codeNode), Presentasi.kind, s"Invalid useVideoDuration property value: $v")
                      case _ => reporter.error(getPosOpt(codeNode), Presentasi.kind, s"Invalid video $media code key: $key")
                    }
                  case Array("") =>
                  case _ => reporter.error(getPosOpt(codeNode), Presentasi.kind, s"Invalid video $media code property: $property")
                }
              }
              val voiceText = substText(substs, text :+ "")
              val ccText = substText(ccs, text :+ "")
              for ((voicet, cct) <- voiceText.zip(ccText) if voicet.nonEmpty && !voicet.contains('[')) ccMap = ccMap + voicet ~> cct
              entries = entries :+ presentasi.Presentation.VideoEntry(media, delay, volume, rate, start, end,
                useVideoDuration, if (text.nonEmpty) Some(voiceText.mkString("\r\n")) else None())
            } else {
              var delay: Z = 0
              for (property <- code.split(',')) {
                property.split('=') match {
                  case Array(key, value) => key.trim match {
                    case "delay" => Z(value.trim) match {
                      case Some(n) => delay = n
                      case _ => reporter.error(getPosOpt(codeNode), Presentasi.kind, s"Invalid delay property value: $value")
                    }
                    case _ => reporter.error(getPosOpt(codeNode), Presentasi.kind, s"Invalid image $media code key: $key")
                  }
                  case Array("") =>
                  case _ => reporter.error(getPosOpt(codeNode), Presentasi.kind, s"Invalid image $media code property: $code")
                }
              }
              val voiceText = substText(substs, text :+ "")
              val ccText = substText(ccs, text :+ "")
              for ((voicet, cct) <- voiceText.zip(ccText) if voicet.nonEmpty && !voicet.startsWith("[")) ccMap = ccMap + voicet ~> cct
              entries = entries :+ presentasi.Presentation.SlideEntry(media, delay, voiceText.mkString("\r\n"))
            }
          case _ =>
            if (child.getSourceSpans.isEmpty) {
              reporter.error(getPosOpt(prevChild), Presentasi.kind, s"Expecting a new heading (#) next, but found: ${child.getClass}")
            } else {
              reporter.error(getPosOpt(child), Presentasi.kind, s"Expecting a new heading (#), but found: ${child.getClass}")
            }
            child = child.getNext
        }
      }
      r(entries = entries, cc = ccMap)
    }

    def processFile(f: Os.Path): ISZ[presentasi.Presentation] =
      if (f.isFile && f.ext.value == "md" && f.name.value != "readme.md")
        try ISZ(document(f))
        catch {
          case t: Throwable =>
            eprintln(s"Could not recognize $f (ignored): ${t.getMessage}")
            ISZ()
        }
      else ISZ()

    if (path.isDir) for (sub <- path.list; r <- processFile(sub)) yield r
    else processFile(path)
  }

  def formatCcTime(isSrt: B, ms: Z): String = {
    import java.util.concurrent.TimeUnit
    val millis = ms.toLong
    java.lang.String.format(s"%02d:%02d:%02d${if (isSrt) "," else "."}%03d", TimeUnit.MILLISECONDS.toHours(millis),
      TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
      TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1), millis % 1000)
  }
}
