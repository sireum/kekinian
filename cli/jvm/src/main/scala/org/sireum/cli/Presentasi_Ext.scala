/*
 Copyright (c) 2017-2022, Robby, Kansas State University
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

object Presentasi_Ext {

  lazy val jfxLatch = new java.util.concurrent.CountDownLatch(1)
  var jfxInit: Boolean = false

  def initJavaFX(): Unit = NativeUtil.nonNative((), {
    def initJavaFXH(): java.util.function.Supplier[Unit] = new java.util.function.Supplier[Unit] {
      class JFX extends javafx.application.Application {
        override def start(primaryStage: javafx.stage.Stage): Unit = {
          jfxInit = true
          jfxLatch.countDown()
        }
      }

      override def get(): Unit = {
        if (jfxInit) {
          return
        }
        val t = new Thread() {
          override def run(): Unit = {
            javafx.application.Application.launch(classOf[JFX])
          }
        }
        t.setDaemon(true)
        t.start()
        while (jfxLatch.getCount > 0) scala.util.Try(jfxLatch.await())
      }
    }

    initJavaFXH()
  })

  def getSoundDuration(uri: String): Option[Z] = NativeUtil.nonNative(None[Z](), {
    def getSoundDurationH(uri: String): java.util.function.Supplier[Option[Z]] = new java.util.function.Supplier[Option[Z]] {
      override def get(): Option[Z] = try {
        initJavaFX()
        val latch = new java.util.concurrent.CountDownLatch(1)
        var duration: Z = 0
        var error = false
        val mediaPlayer = new javafx.scene.media.MediaPlayer(new javafx.scene.media.Media(uri.value))
        mediaPlayer.setOnReady(new Runnable {
          override def run(): Unit = {
            duration = Math.ceil(mediaPlayer.getTotalDuration.toMillis).toLong
            latch.countDown()
          }
        })
        mediaPlayer.setOnError(new Runnable {
          override def run(): Unit = {
            error = true
            latch.countDown()
          }
        })
        latch.await()
        if (error) None[Z]() else Some(duration)
      } catch {
        case t: Throwable =>
          t.printStackTrace()
          None[Z]()
      }
    }

    getSoundDurationH(uri)
  })

  def getVideoDuration(uri: String): Option[Z] = NativeUtil.nonNative(None[Z](), {
    def getVideoDurationH(uri: String): java.util.function.Supplier[Option[Z]] = new java.util.function.Supplier[Option[Z]] {
      override def get(): Option[Z] = try {
        initJavaFX()
        val latch = new java.util.concurrent.CountDownLatch(1)
        val mediaPlayer = new javafx.scene.media.MediaPlayer(new javafx.scene.media.Media(uri.value))
        var error = false
        var duration: Z = 0
        mediaPlayer.setOnReady(new Runnable {
          override def run(): Unit = {
            duration = Math.ceil(mediaPlayer.getTotalDuration.toMillis).toLong
            latch.countDown()
          }
        })
        mediaPlayer.setOnError(new Runnable {
          override def run(): Unit = {
            error = true
            latch.countDown()
          }
        })
        val mediaView = new javafx.scene.media.MediaView(mediaPlayer)
        latch.await()
        if (error) None[Z]() else Some(duration)
      } catch {
        case t: Throwable =>
          t.printStackTrace()
          None[Z]()
      }
    }

    getVideoDurationH(uri)
  })

  def checkImage(uri: String): B = NativeUtil.nonNative(F, {
    def checkImageH(uri: String): java.util.function.Supplier[B] = new java.util.function.Supplier[B] {
      override def get(): B = try {
        initJavaFX()
        val image = new javafx.scene.image.Image(uri.value)
        val imageView = new javafx.scene.image.ImageView(image)
        T
      } catch {
        case t: Throwable =>
          t.printStackTrace()
          F
      }
    }

    checkImageH(uri)
  })

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

  def shutdown(): Unit = NativeUtil.nonNative[Unit]((), () => if (jfxInit) javafx.application.Platform.exit())
}
