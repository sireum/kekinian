::#! 2> /dev/null                                                                                           #
@ 2>/dev/null # 2>nul & echo off & goto BOF                                                                 #
export SIREUM_HOME=$(cd -P $(dirname "$0")/../.. && pwd -P)                                                 #
if [ -f "$0.com" ] && [ "$0.com" -nt "$0" ]; then                                                           #
  exec "$0.com" "$@"                                                                                        #
else                                                                                                        #
  rm -fR "$0.com"                                                                                           #
  exec "${SIREUM_HOME}/bin/sireum" slang run "$0" "$@"                                                   #
fi                                                                                                          #
:BOF
setlocal
set NEWER=False
if exist %~dpnx0.com for /f %%i in ('powershell -noprofile -executionpolicy bypass -command "(Get-Item %~dpnx0.com).LastWriteTime -gt (Get-Item %~dpnx0).LastWriteTime"') do @set NEWER=%%i
if "%NEWER%" == "True" goto native
del "%~dpnx0.com" > nul 2>&1
if not exist "%~dp0..\sireum.jar" call "%~dp0..\init.bat"
"%~dp0..\sireum.bat" slang run "%0" %*
exit /B %errorlevel%
:native
%~dpnx0.com %*
exit /B %errorlevel%
::!#
// #Sireum
// @formatter:off

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

// This file is auto-generated from fmide-cli.sc

import org.sireum._

// BEGIN USER CODE
val homeBin: Os.Path = Os.slashDir.up.canon
val home = homeBin.up.canon

val briefCaseFeatureId: String = "com.collins.trustedsystems.briefcase.feature.feature.group"
@strictpure def briefCaseUpdateSite(url: String): String = s"jar:$url!"

val eclipseRelease = "2019-12"
val hamrUpdateSite = "https://raw.githubusercontent.com/sireum/hamr-plugin-update-site/master"
val hamrFeatureId = "org.sireum.aadl.osate.hamr.feature.feature.group"

//val metalsUpdateSite = s"https://download.eclipse.org/releases/$eclipseRelease,http://scalameta.org/metals-eclipse/update"
//val metalsFeatureId = "lsp.scala.feature.feature.group"

@strictpure def p2Args(uninstall: B, updateSite: String, featureId: String): ISZ[String] = ISZ[String](
  "-nosplash",
  "-console",
  "-consoleLog",
  "-application",
  "org.eclipse.equinox.p2.director",
  "-repository",
  updateSite,
  "-installIU",
  featureId,
) ++ (if (uninstall) ISZ[String]("-uninstallIU", featureId) else ISZ[String]())

val hamrP2Args = p2Args(T, hamrUpdateSite, hamrFeatureId)
//val metalsP2Args = p2Args(F, metalsUpdateSite, metalsFeatureId)

val envs = ISZ[(String, String)]("PATH" ~>
  s"${Os.env("JAVA_HOME").get}${Os.fileSep}bin${Os.pathSep}${Os.env("PATH").get}")
var fmwPlatformNameUrlMap = Map.empty[Os.Kind.Type, (String, String)]
var briefCaseUrlOpt: Option[String] = None()
var fmwReleaseTagNameOpt: Option[String] = None()
var briefCaseReleaseTagNameOpt: Option[String] = None()
val useLast: B = T

Cli(conversions.String.toCis(Os.pathSep)(0) ).parseFmide(Os.cliArgs, 0) match {
  case Some(option: Cli.FmideOption) =>
    option.args.size match {
      case z"0" =>
      case z"1" =>
        option.args(0) match {
          case string"nightly" =>
          case tagName => fmwReleaseTagNameOpt = Some(tagName)
        }
      case z"2" =>
        fmwReleaseTagNameOpt = Some(option.args(0))
        briefCaseReleaseTagNameOpt = Some(option.args(1))
      case _ =>
        println("Usage: fmide.cmd [ <fmw-tag-name> [ <briefcase-tag-name> ] | nightly ]")
        Os.exit(-1)
    }
  case _ => Os.exit(-1)
}

def findAssets(repo: String): Unit = {
  var first = T
  for (r <- GitHub.repo("loonwerks", repo).releases) {
    for (a <- r.assets) {
      val aNameOps = ops.StringOps(a.name)
      if (aNameOps.startsWith("fmide-") || aNameOps.startsWith("com.collins.trustedsystems.fmw.ide-")) {
        if (fmwReleaseTagNameOpt == Some(r.tagName) || (first && fmwReleaseTagNameOpt.isEmpty)) {
          val p = (a.name, a.url)
          if (aNameOps.contains("win32") && (useLast || !fmwPlatformNameUrlMap.contains(Os.Kind.Win))) {
            fmwPlatformNameUrlMap = fmwPlatformNameUrlMap + Os.Kind.Win ~> p
          } else if (aNameOps.contains("linux") && (useLast || !fmwPlatformNameUrlMap.contains(Os.Kind.Linux))) {
            fmwPlatformNameUrlMap = fmwPlatformNameUrlMap + Os.Kind.Linux ~> p
          } else if (aNameOps.contains("macos") && (useLast || !fmwPlatformNameUrlMap.contains(Os.Kind.Mac))) {
            fmwPlatformNameUrlMap = fmwPlatformNameUrlMap + Os.Kind.Mac ~> p
          }
        }
      } else if (aNameOps.startsWith("com.collins.trustedsystems.briefcase.repository-")) {
        if (briefCaseReleaseTagNameOpt == Some(r.tagName) || (first && briefCaseReleaseTagNameOpt.isEmpty)) {
          briefCaseUrlOpt = Some(a.url)
          return
        }
      }
    }
    first = F
  }
}

def download(kind: Os.Kind.Type): Os.Path = {
  val (name, url) = fmwPlatformNameUrlMap.get(kind).get
  val r = Os.home / "Downloads" / "sireum" / name
  if (!r.exists) {
    println(s"Downloading $name ...")
    r.downloadFrom(url)
    println()
  }
  return r
}

def linux(): Unit = {
  val f = download(Os.Kind.Linux)
  val d = homeBin / "linux" / "fmide"
  d.removeAll()
  d.mkdirAll()
  println(s"Extracting $f ...")
  Os.proc(ISZ("tar", "xfz", f.string)).at(d).runCheck()
  println()
  val dFmide = (d / "fmide").string
  if (fmwReleaseTagNameOpt.isEmpty) {
    println("Updating BriefCASE plugin ...")
    Os.proc(dFmide +: p2Args(T, briefCaseUpdateSite(briefCaseUrlOpt.get), briefCaseFeatureId)).env(envs).runCheck()
    println()
    println(s"Updating HAMR plugin ...")
    Os.proc(dFmide +: hamrP2Args).env(envs).runCheck()
    println()
  }
  //println(s"Installing Scala Metals plugin ...")
  //Os.proc(dFmide +: metalsP2Args).env(envs).runCheck()
  //println()
  println(s"FMIDE is installed at $d")
}

def mac(): Unit = {
  val f = download(Os.Kind.Mac)
  val homeBinMac = homeBin / "mac"
  val d = homeBinMac / "fmide.app"
  d.removeAll()
  homeBinMac.mkdirAll()
  println(s"Extracting $f ...")
  Os.proc(ISZ("tar", "xfz", f.string)).at(homeBinMac).runCheck()
  for (p <- homeBinMac.list if ops.StringOps(p.name).startsWith("com.collins.")) {
    p.moveTo(d)
  }
  println()
  val dFmide = (d / "Contents" / "MacOS" / "fmide").string
  if (fmwReleaseTagNameOpt.isEmpty) {
    println("Updating BriefCASE plugin ...")
    Os.proc(dFmide +: p2Args(T, briefCaseUpdateSite(briefCaseUrlOpt.get), briefCaseFeatureId)).env(envs).runCheck()
    println()
    println(s"Updating HAMR plugin ...")
    Os.proc(dFmide +: hamrP2Args).env(envs).runCheck()
    println()
  }
  //println(s"Installing Scala Metals plugin ...")
  //Os.proc(dFmide +: metalsP2Args).env(envs).runCheck()
  //println()
  println(s"FMIDE is installed at $d")
}

def win(): Unit = {
  val f = download(Os.Kind.Win)
  val d = homeBin / "win" / "fmide"
  d.removeAll()
  d.mkdirAll()
  println(s"Extracting $f ...")
  f.unzipTo(d)
  println()
  val dFmide = (d / "fmide").string
  if (fmwReleaseTagNameOpt.isEmpty) {
    println("Updating BriefCASE plugin ...")
    Os.proc(dFmide +: p2Args(T, briefCaseUpdateSite(briefCaseUrlOpt.get), briefCaseFeatureId)).env(envs).runCheck()
    println()
    println(s"Updating HAMR plugin ...")
    Os.proc(dFmide +: hamrP2Args).env(envs).runCheck()
    println()
  }
  //println(s"Installing Scala Metals plugin ...")
  //Os.proc(dFmide +: metalsP2Args).env(envs).runCheck()
  //println()
  println(s"FMIDE is installed at $d")
}

findAssets("BriefCASE")
if (briefCaseUrlOpt.isEmpty) {
  briefCaseReleaseTagNameOpt match {
    case Some(releaseTagName) => eprintln(s"Could not find BriefCASE $releaseTagName build assets")
    case _ => eprintln("Could not find BriefCASE latest nightly build assets")
  }
  Os.exit(-1)
}

findAssets("formal-methods-workbench")
if (fmwPlatformNameUrlMap.isEmpty) {
  fmwReleaseTagNameOpt match {
    case Some(releaseTagName) => eprintln(s"Could not find FMIDE $releaseTagName build assets")
    case _ => eprintln("Could not find FMIDE latest nightly build assets")
  }
  Os.exit(-1)
}

Os.kind match {
  case Os.Kind.Linux => linux()
  case Os.Kind.Mac => mac()
  case Os.Kind.Win => win()
  case _ =>
    eprintln("Unsupported platform")
    Os.exit(-1)
}
// END USER CODE

object Cli {

  @datatype trait FmideTopOption

  @datatype class HelpOption extends FmideTopOption

  @datatype class FmideOption(
    val help: String,
    val args: ISZ[String]
  ) extends FmideTopOption
}

import Cli._

@record class Cli(val pathSep: C) {

  def parseFmide(args: ISZ[String], i: Z): Option[FmideTopOption] = {
    val help =
      st"""FMIDE installation manager
          |
          |Usage: <option>*  [ <fmide-tag-name> [ <briefcase-tag-name> ] | nightly ]
          |
          |Available Options:
          |-h, --help               Display this information""".render

    val j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }

      } else {
        isOption = F
      }
    }
    return Some(FmideOption(help, parseArguments(args, j)))
  }

  def parseArguments(args: ISZ[String], i: Z): ISZ[String] = {
    var r = ISZ[String]()
    var j = i
    while (j < args.size) {
      r = r :+ args(j)
      j = j + 1
    }
    return r
  }

  def parsePaths(args: ISZ[String], i: Z): Option[ISZ[String]] = {
    return tokenize(args, i, "path", pathSep, F)
  }

  def parsePath(args: ISZ[String], i: Z): Option[Option[String]] = {
    if (i >= args.size) {
      eprintln("Expecting a path, but none found.")
    }
    return Some(Some(args(i)))
  }

  def parseStrings(args: ISZ[String], i: Z, sep: C): Option[ISZ[String]] = {
    tokenize(args, i, "string", sep, F) match {
      case r@Some(_) => return r
      case _ => return None()
    }
  }

  def parseString(args: ISZ[String], i: Z): Option[Option[String]] = {
    if (i >= args.size) {
      eprintln("Expecting a string, but none found.")
      return None()
    }
    return Some(Some(args(i)))
  }

  def parseNums(args: ISZ[String], i: Z, sep: C, minOpt: Option[Z], maxOpt: Option[Z]): Option[ISZ[Z]] = {
    tokenize(args, i, "integer", sep, T) match {
      case Some(sargs) =>
        var r = ISZ[Z]()
        for (arg <- sargs) {
          parseNumH(arg, minOpt, maxOpt) match {
            case Some(n) => r = r :+ n
            case _ => return None()
          }
        }
        return Some(r)
      case _ => return None()
    }
  }

  def tokenize(args: ISZ[String], i: Z, tpe: String, sep: C, removeWhitespace: B): Option[ISZ[String]] = {
    if (i >= args.size) {
      eprintln(s"Expecting a sequence of $tpe separated by '$sep', but none found.")
      return None()
    }
    val arg = args(i)
    return Some(tokenizeH(arg, sep, removeWhitespace))
  }

  def tokenizeH(arg: String, sep: C, removeWhitespace: B): ISZ[String] = {
    val argCis = conversions.String.toCis(arg)
    var r = ISZ[String]()
    var cis = ISZ[C]()
    var j = 0
    while (j < argCis.size) {
      val c = argCis(j)
      if (c == sep) {
        r = r :+ conversions.String.fromCis(cis)
        cis = ISZ[C]()
      } else {
        val allowed: B = c match {
          case c"\n" => !removeWhitespace
          case c" " => !removeWhitespace
          case c"\r" => !removeWhitespace
          case c"\t" => !removeWhitespace
          case _ => T
        }
        if (allowed) {
          cis = cis :+ c
        }
      }
      j = j + 1
    }
    if (cis.size > 0) {
      r = r :+ conversions.String.fromCis(cis)
    }
    return r
  }

  def parseNumChoice(args: ISZ[String], i: Z, choices: ISZ[Z]): Option[Z] = {
    val set = HashSet.empty[Z] ++ choices
    parseNum(args, i, None(), None()) match {
      case r@Some(n) =>
        if (set.contains(n)) {
          return r
        } else {
          eprintln(s"Expecting one of the following: $set, but found $n.")
          return None()
        }
      case r => return r
    }
  }

  def parseNum(args: ISZ[String], i: Z, minOpt: Option[Z], maxOpt: Option[Z]): Option[Z] = {
    if (i >= args.size) {
      eprintln(s"Expecting an integer, but none found.")
      return None()
    }
    return parseNumH(args(i), minOpt, maxOpt)
  }

  def parseNumH(arg: String, minOpt: Option[Z], maxOpt: Option[Z]): Option[Z] = {
    Z(arg) match {
      case Some(n) =>
        minOpt match {
          case Some(min) =>
            if (n < min) {
              eprintln(s"Expecting an integer at least $min, but found $n.")
              return None()
            }
          case _ =>
        }
        maxOpt match {
          case Some(max) =>
            if (n > max) {
              eprintln(s"Expecting an integer at most $max, but found $n.")
              return None()
            }
            return Some(n)
          case _ =>
        }
        return Some(n)
      case _ =>
        eprintln(s"Expecting an integer, but found '$arg'.")
        return None()
    }
  }

  def select(mode: String, args: ISZ[String], i: Z, choices: ISZ[String]): Option[String] = {
    val arg = args(i)
    var cs = ISZ[String]()
    for (c <- choices) {
      if (ops.StringOps(c).startsWith(arg)) {
        cs = cs :+ c
      }
    }
    cs.size match {
      case z"0" =>
        eprintln(s"$arg is not a mode of $mode.")
        return None()
      case z"1" => return Some(cs(0))
      case _ =>
        eprintln(
          st"""Which one of the following modes did you mean by '$arg'?
              |${(cs, "\n")}""".render)
        return None()
    }
  }
}