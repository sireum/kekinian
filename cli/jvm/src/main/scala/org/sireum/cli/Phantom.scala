/*
 Copyright (c) 2018, Kansas State University
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
import org.sireum.Cli.PhantomOption

object Phantom {

  def run(o: PhantomOption): Z = {
    o.args.size match {
      case z"0" => println(o.help); return 0
      case z"1" =>
      case _ =>
        addError("Too many arguments provided. Expecting a single system implementation")
        return -1
    }

    val projects = o.projects.map { it =>
      val f = Os.path(it)
      if(!f.exists) {
        addError(s"${it.value} is not a valid directory")
        return -1
      }
      f
    }

    val mainPackage : String = if(o.main.nonEmpty) {
      o.main.get
    } else {
      projects(0).list(0).name
    }

    val (serializeType, outExt) = o.mode match {
      case Cli.PhantomMode.Json => (T, ".json")
      case _ =>
        addWarning("Currently only JSON is supported.  Using that instead.")
        (T, ".json")
    }

    val impl : String = o.args(0)

    val outFile: Os.Path = if (o.output.nonEmpty) {
      val f = Os.path(o.output.get)
      if(f.exists && f.isDir) {
        addError(s"$f is a directory.  Should be the name for the generated $serializeType file.")
        return -1
      }
      f
    } else {
      Os.path(projects(0).value.value + impl.value + outExt)
    }

    val osateDirOpt: Option[Os.Path] = o.osate match {
      case Some(d) => Some(Os.path(d))
      case _ => None()
    }

    return org.sireum.aadl.phantom.Phantom.run(serializeType, osateDirOpt, projects, mainPackage, outFile, o.args(0))
    0
  }

  def addError(s: String): Unit = { Console.err.println(s"Error: $s") }
  def addWarning(s: String): Unit = { Console.out.println(s"Warning: $s") }
}
