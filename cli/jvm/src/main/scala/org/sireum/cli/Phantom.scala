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

    // TODO: the kekinian part of phantom should probably just be responsible for
    //       installing/updating OSATE.  The osate-plugin part should be responsible
    //       for checking/processing the rest of the options/arguments so we probably
    //       don't need to do anything with them here

    o.args.size match {
      case z"0" => // ok
      case z"1" => // ok
      case _ =>
        addError("Too many arguments provided. Expecting a single project directory")
        return -1
    }

    val osate: Option[Os.Path] = o.osate match {
      case Some(d) =>
        val cand = Os.path(d)
        if(!cand.exists || !cand.isDir) {
          addError(s"Path for OSATE does not exist or isn't a directory: ${cand.value}")
          return -1
        }
        Some(cand)
      case _ => None()
    }

    val projects: ISZ[Os.Path] = o.projects.map { it =>
      val f = Os.path(it)
      if(!f.exists) {
        addError(s"${it.value} is not a valid directory")
        return -1
      }
      f
    }

    val projectDir: Option[Os.Path] = if(o.args.nonEmpty) {
      val p = Os.path(o.args(0))
      if(!p.exists || !p.isDir) {
        addError(s"${p.value} is not a directory")
        return -1
      }
      Some(p)
    } else {
      None()
    }

    def getKey(name: String): String = {
      val cand = org.sireum.hamr.phantom.cli.phantomTool.opts.filter(f => f.name == name)
      if (cand.isEmpty || cand.size > 1) { halt(s"Issue arose when looking up longKey for ${name}") }
      return cand(0).longKey
    }

    if (projectDir.nonEmpty == (projects.nonEmpty && o.main.nonEmpty && o.impl.nonEmpty)) {
      addError(s"Must supply a project directory or values for options: ${getKey("projects")}, ${getKey("main")}, and ${getKey("impl")}")
      return -1
    }

    return org.sireum.hamr.phantom.Phantom.run(
      mode = o.mode.string,
      osate = osate,
      projects = projects,
      main = o.main,
      impl = o.impl,
      output = o.output,
      projectDir = projectDir)
  }

  def addError(s: String): Unit = { Console.err.println(s"Error: $s. Pass '-h' for usage info.") }
  def addWarning(s: String): Unit = { Console.out.println(s"Warning: $s") }
}
