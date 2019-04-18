/*
 Copyright (c) 2018, Jason Belt, Kansas State University
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

import java.io.File

import org.sireum._
import org.sireum.aadl.ir.{Aadl, JSON => AirJSON, MsgPack}
import Cli.{ActFormat, ActMode}

object Act {

  def act(o: Cli.ActOption): Z = {
    o.args.size match {
      case z"0" => println(o.help); return 0
      case _ =>
    }

    val outputDir = Sireum.path2fileOpt("output directory", o.outputDir, F).get
    if(!outputDir.exists) {
      outputDir.mkdirAll()
    }
    if(!outputDir.isDir) {
      eprintln(s"$outputDir is not a directory")
      return -1
    }

    o.input match {
      case ActFormat.Aadl =>
        eprintln("AADL is not currently supported")
        return -1
      case ActFormat.Air =>
        if(o.args.length > 1) {
          eprintln(s"Only expecting a single AIR input file")
          return -1
        }
        val input: String = Sireum.path2fileOpt("input file", Some(o.args(0)), T) match {
          case Some(file) => file.readLines.elements.map(_.value).mkString
          case _ => return -1
        }
        val aadl: Aadl = o.mode match {
          case ActMode.Json =>
            AirJSON.toAadl(input) match {
              case Either.Left(m) => m
              case Either.Right(m) =>
                eprintln(s"Json deserialization error at (${m.line}, ${m.column}: ${m.message}")
                return -1
            }
          case ActMode.Msgpack =>
            conversions.String.fromBase64(input) match {
              case Either.Left(u) =>
                MsgPack.toAadl(u) match {
                  case Either.Left(m) => m
                  case Either.Right(m) =>
                    eprintln(s"MsgPack deserialization error at offset (${m.offset}: ${m.message}")
                    return -1
                }
              case Either.Right(m) =>
                eprintln(s"Input is not a valid Base64 encoded string: ${m}")
                return -1
            }
        }

        val rootDir: Option[File] = o.aadlRootDir match {
          case Some(p) => Some(new File(p.value))
          case _ => None()
        }

        org.sireum.aadl.act.Act.run(new File(outputDir.string.value), aadl, o.auxDirs.map(_.native), rootDir)

      case ActFormat.Camkesir =>
        eprintln("CAmkES IR is not currently supported")
        -1
    }

  }

}