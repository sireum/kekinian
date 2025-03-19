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
      halt("")
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
}
