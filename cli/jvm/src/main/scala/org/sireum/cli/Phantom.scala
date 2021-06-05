// #Sireum
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
          addError(s"Path for OSATE does not exist or isn't a directory: ${d}")
          return -1
        }
        Some(cand)
      case _ => None()
    }

    val projects: ISZ[Os.Path] = o.projects.map { it =>
      val f = Os.path(it).canon
      if(!f.exists) {
        addError(s"${it} is not a valid directory")
        return -1
      }
      f
    }

    val main: Option[Os.Path] = o.main match {
      case Some(m) =>
        val f = Os.path(m).canon
        if(!f.exists) {
          addError(s"${m} is not file")
          return -1
        }
        Some(f)
      case _ => None()
    }

    val projectDir: Option[Os.Path] = if(o.args.nonEmpty) {
      val p = Os.path(o.args(0)).canon
      if(!p.exists || !p.isDir) {
        addError(s"${p.value} is not a directory")
        return -1
      }
      Some(p)
    } else {
      None()
    }

    val output: Option[Os.Path] = o.output match {
      case Some(m) => Some(Os.path(m).canon)
      case _ => None()
    }

    def getKey(name: String): String = {
      val cand = org.sireum.hamr.phantom.cli.phantomTool.opts.filter(f => f.name == name)
      if (cand.isEmpty || cand.size > 1) { halt(s"Issue arose when looking up longKey for ${name}") }
      return cand(0).longKey
    }

    // directory supplied via arg, or options filled in, but not both
    val dirOrProj = projectDir.nonEmpty != (projects.nonEmpty && main.nonEmpty && o.impl.nonEmpty)

    val justInstall = o.update && projectDir.isEmpty && (projects.isEmpty && main.isEmpty && o.impl.isEmpty)

    if(!justInstall && !dirOrProj) {
      println(o.help)
      return -1
    }

    val ret: Z = org.sireum.hamr.phantom.Phantom.getOsateExe(osate) match {
      case Some(osateExe) =>
        var ret: Z = 0
        if(o.update || !org.sireum.hamr.phantom.Phantom.pluginsInstalled(osateExe)) {
          ret = org.sireum.hamr.phantom.Phantom.update(osateExe)
        }

        if(ret == 0 && dirOrProj) {
          ret = org.sireum.hamr.phantom.Phantom.execute(
            osateExe = osateExe,
            mode = ops.StringOps(o.mode.string).firstToLower,
            projects = projects,
            main = main,
            impl = o.impl,
            output = output,
            projectDir = projectDir)
        }

        ret

      case _ => -1
    }

    return ret
  }

  def addError(s: String): Unit = { eprintln(s"Error: $s.") }
  def addWarning(s: String): Unit = { println(s"Warning: $s") }
}
