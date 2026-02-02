// #Sireum
/*
 Copyright (c) 2017-2026,Kansas State University
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
import org.sireum.Cli.SireumHamrPhantomOption
import org.sireum.hamr.phantom.Phantom.{Feature => PFeature}
import org.sireum.hamr.phantom.Verbosity

object Phantom {

  val baseFeatures: ISZ[PFeature] = ISZ(
    PFeature("Sireum", "org.sireum.aadl.osate.feature.feature.group", "https://raw.githubusercontent.com/sireum/osate-update-site/master"),
    PFeature("Phantom CLI", "org.sireum.aadl.osate.cli.feature.feature.group", "https://raw.githubusercontent.com/sireum/osate-update-site/master"),
    PFeature("HAMR", "org.sireum.aadl.osate.hamr.feature.feature.group", "https://raw.githubusercontent.com/sireum/osate-update-site/master"),
    PFeature("AWAS", "org.sireum.aadl.osate.awas.feature.feature.group", "https://raw.githubusercontent.com/sireum/osate-update-site/master"),
    PFeature("GUMBO", "org.sireum.aadl.gumbo.feature.feature.group", "https://raw.githubusercontent.com/sireum/aadl-gumbo-update-site/master"),
    PFeature("GUMBO to AIR", "org.sireum.aadl.osate.gumbo2air.feature.feature.group", "https://raw.githubusercontent.com/sireum/aadl-gumbo-update-site/master"),
  )

  def run(o: SireumHamrPhantomOption): Z = {

    o.args.size match {
      case z"0" => // ok
      case z"1" => // ok
      case _ =>
        addError("Too many arguments provided. Expecting a single project directory")
        return -1
    }

    if(!SireumApi.homeFound) {
      addError("Could not determine Sireum's location")
      return -1
    }

    val altSireumHome: Option[Os.Path] = {
      o.sireum match {
        case Some(p) =>
          val cand = Os.path(p)
          if (!cand.exists || !(cand / "bin" / "sireum.jar").exists) {
            addError(s"Not a valid Sireum home directory: $cand")
            return - 1
          } else {
            Some(cand)
          }
        case _ => None()
      }
    }

    val osate: Option[Os.Path] = o.osate match {
      case Some(d) =>
        val cand = Os.path(d)
        if (cand.exists && !cand.isDir) {
          addError(s"Path for OSATE is not a directory: $d")
          return -1
        }
        if (Os.isMac && cand.ext != "app") {
          addError(s"osate option must have an app extension on Macs (e.g. change to ${cand}.app)")
          return -1
        }
        Some(cand)
      case _ => None()
    }

    val projects: ISZ[Os.Path] = o.projects.map { it =>
      val f = Os.path(it).canon
      if (!f.exists) {
        addError(s"$it is not a valid directory")
        return -1
      }
      f
    }

    val main: Option[Os.Path] = o.main match {
      case Some(m) =>
        val f = Os.path(m).canon
        if (!f.exists) {
          addError(s"$m is not file")
          return -1
        }
        Some(f)
      case _ => None()
    }

    val projectDir: Option[Os.Path] = if (o.args.nonEmpty) {
      val p = Os.path(o.args(0)).canon
      if (!p.exists || !p.isDir) {
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

    // directory supplied via arg, or options filled in, but not both
    val dirOrProj = (projectDir.nonEmpty && (projects.isEmpty && main.isEmpty && o.impl.isEmpty)) ||
      (projectDir.isEmpty && (projects.nonEmpty && main.nonEmpty && o.impl.nonEmpty))

    val justInstall = o.update && projectDir.isEmpty && (projects.isEmpty && main.isEmpty && o.impl.isEmpty)

    if (!justInstall && !dirOrProj) {
      println(o.help)
      return -1
    }

    val verbosity: Verbosity.Type = if(o.verbosePlus) Verbosity.High else if (o.verbose) Verbosity.Low else Verbosity.Off
    val phantom = org.sireum.hamr.phantom.Phantom(o.version.get, osate, verbosity, SireumApi.homeOpt.get, altSireumHome)

    val ret: Z = phantom.getOsateExe() match {
      case Some(osateExe) =>
        var ret: Z = 0
        var features = ISZ[PFeature]()
        for (feature <- o.features) {
          ops.StringOps(feature).split((c: C) => c == '=') match {
            case ISZ(featureId, url) =>
              features = features :+ org.sireum.hamr.phantom.Phantom.Feature(featureId, featureId, url)
            case _ =>
              addError(s"Invalid feature form: $feature")
              ret = -1
          }
        }

        def add(f: PFeature, _features: ISZ[PFeature]): ISZ[PFeature] = {
          if(!ops.ISZOps(_features).exists(featureOption => {
            val ops = org.sireum.ops.StringOps(featureOption.id)
            val featureId: String = if(ops.contains("/")) ops.substring(0, ops.indexOf('/')) else ops.s
            featureId == f.id
          })) { return _features :+ f }
          else { return _features }
        }

        for(f <- ops.ISZOps(baseFeatures).reverse) {
          features = add(f, features)
        }

        if (ret == 0 && (o.update || !phantom.featuresInstalled(features, osateExe))) {
          ret = phantom.update(osateExe, features)
        }

        if (ret == 0 && dirOrProj) {
          ret = phantom.execute(
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

  def addError(s: String): Unit = {
    eprintln(s"Error: $s.")
  }

  def addWarning(s: String): Unit = {
    println(s"Warning: $s")
  }
}
