::#! 2> /dev/null                                            #
@ 2>/dev/null # 2>nul & echo off & goto BOF                  #
export SIREUM_HOME=$(cd -P $(dirname "$0")/../.. && pwd -P)  #
if [ -f "$0.com" ] && [ "$0.com" -nt "$0" ]; then            #
  exec "$0.com" "$@"                                         #
else                                                         #
  rm -fR "$0.com"                                            #
  exec "${SIREUM_HOME}/bin/sireum" slang run "$0" "$@"       #
fi                                                           #
:BOF
setlocal
if not exist "%~dp0..\sireum.jar" call "%~dp0..\init.bat"
"%~dp0..\sireum.bat" slang run "%0" %*
exit /B %errorlevel%
::!#
// #Sireum
// @formatter:off

// This file is auto-generated from fmide-cli.sc

import org.sireum._

object Cli {

  @datatype trait FmideTopOption

  @datatype class HelpOption extends FmideTopOption

  @datatype class FmideOption(
    val help: String,
    val args: ISZ[String],
    val agree: Option[String],
    val briefcase: Option[String],
    val eclipse: Option[String],
    val hamr: Option[String],
    val osate: Option[String],
    val resolute: Option[String]
  ) extends FmideTopOption
}

import Cli._

@record class Cli(val pathSep: C) {

  def parseFmide(args: ISZ[String], i: Z): Option[FmideTopOption] = {
    val help =
      st"""FMIDE Installer
          |
          |Usage: <option>* [ fixed | latest ]
          |
          |Available Options:
          |    --agree              AGREE version (expects a string; default is
          |                           "agree_2.7.0")
          |    --briefcase          BriefCASE version (expects a string; default is
          |                           "briefcase_0.5.1")
          |    --eclipse            Eclipse release version (expects a string; default is
          |                           "2020-06")
          |    --hamr               Sireum HAMR version (expects a string; default is
          |                           "1.0.2109141821.effe14b")
          |    --osate              OSATE version (expects a string; default is
          |                           "2.9.0-vfinal")
          |    --resolute           Resolute version (expects a string; default is
          |                           "resolute_2.7.1")
          |-h, --help               Display this information""".render

    var agree: Option[String] = Some("agree_2.7.0")
    var briefcase: Option[String] = Some("briefcase_0.5.1")
    var eclipse: Option[String] = Some("2020-06")
    var hamr: Option[String] = Some("1.0.2109141821.effe14b")
    var osate: Option[String] = Some("2.9.0-vfinal")
    var resolute: Option[String] = Some("resolute_2.7.1")
    var j = i
    var isOption = T
    while (j < args.size && isOption) {
      val arg = args(j)
      if (ops.StringOps(arg).first == '-') {
        if (args(j) == "-h" || args(j) == "--help") {
          println(help)
          return Some(HelpOption())
        } else if (arg == "--agree") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => agree = v
             case _ => return None()
           }
         } else if (arg == "--briefcase") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => briefcase = v
             case _ => return None()
           }
         } else if (arg == "--eclipse") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => eclipse = v
             case _ => return None()
           }
         } else if (arg == "--hamr") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => hamr = v
             case _ => return None()
           }
         } else if (arg == "--osate") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => osate = v
             case _ => return None()
           }
         } else if (arg == "--resolute") {
           val o: Option[Option[String]] = parseString(args, j + 1)
           o match {
             case Some(v) => resolute = v
             case _ => return None()
           }
         } else {
          eprintln(s"Unrecognized option '$arg'.")
          return None()
        }
        j = j + 2
      } else {
        isOption = F
      }
    }
    return Some(FmideOption(help, parseArguments(args, j), agree, briefcase, eclipse, hamr, osate, resolute))
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
          parseNumH(F, arg, minOpt, maxOpt)._2 match {
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
    return parseNumH(F, args(i), minOpt, maxOpt)._2
  }

  def parseNumFlag(args: ISZ[String], i: Z, minOpt: Option[Z], maxOpt: Option[Z]): Option[Option[Z]] = {
    if (i >= args.size) {
      return Some(None())
    }
    parseNumH(T, args(i), minOpt, maxOpt) match {
      case (T, vOpt) => return Some(vOpt)
      case _ => return None()
    }
  }

  def parseNumH(optArg: B, arg: String, minOpt: Option[Z], maxOpt: Option[Z]): (B, Option[Z]) = {
    Z(arg) match {
      case Some(n) =>
        minOpt match {
          case Some(min) =>
            if (n < min) {
              eprintln(s"Expecting an integer at least $min, but found $n.")
              return (F, None())
            }
          case _ =>
        }
        maxOpt match {
          case Some(max) =>
            if (n > max) {
              eprintln(s"Expecting an integer at most $max, but found $n.")
              return (F, None())
            }
          case _ =>
        }
        return (T, Some(n))
      case _ =>
        if (!optArg) {
          eprintln(s"Expecting an integer, but found '$arg'.")
          return (F, None())
        } else {
          return (T, None())
       }
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
// @formatter:on

// BEGIN USER CODE
val homeBin = Os.slashDir.up.canon
val sireum = homeBin / (if (Os.isWin) "sireum.bat" else "sireum")

val fmideDir: Os.Path = Os.kind match {
  case Os.Kind.Mac => homeBin / "mac" / "fmide.app"
  case Os.Kind.Linux => homeBin / "linux" / "fmide"
  case Os.Kind.Win => homeBin / "win" / "fmide"
  case _ =>
    eprintln("Unsupported operating system")
    Os.exit(-1)
    halt("Infeasible")
}

def parseCliArgs(): (B, Cli.FmideOption) = {
  Cli(Os.pathSepChar).parseFmide(Os.cliArgs, 0) match {
    case Some(o: Cli.FmideOption) if o.args.size === 1 && (o.args(0) === "fixed" || o.args(0) == "latest") =>
      return (o.args(0) === "fixed", o)
    case Some(o: Cli.FmideOption) if o.args.isEmpty =>
      return (F, o)
    case Some(_: Cli.HelpOption) =>
      Os.exit(0)
    case _ =>
  }
  eprintln("Could not recognize arguments")
  Os.exit(-1)
  halt("Infeasible")
}

val (isFixed, option) = parseCliArgs()

def lookupVersion(name: String, url: String, default: String): String = {
  val compositeArtifacts = "compositeArtifacts.xml"
  def lookupVersionH(): String = {
    if (isFixed) {
      return default
    }
    val urls = ops.StringOps(url).split((c: C) => c === ',')
    val f = Os.temp()
    f.removeAll()
    val durl = urls(urls.size - 1)
    if (!f.downloadFrom(s"$durl/$compositeArtifacts")) {
      eprintln(s"Could not find the latest version of $name; using $default")
      return default
    }
    val fOps = ops.StringOps(f.read)
    f.removeAll()
    var i: Z = 0
    var rOpt: Option[String] = None()
    val text: String = "<child location="
    while (i < fOps.size) {
      val j = fOps.stringIndexOfFrom(text, i)
      if (j < 0) {
        i = fOps.size
      } else {
        i = j + text.size + 1
        val quote = fOps.substring(j + text.size, i)
        val k = fOps.stringIndexOfFrom(quote, i)
        if (k >= 0) {
          val version = fOps.substring(i, k)
          rOpt match {
            case Some(r) =>
              if (ops.StringOps(version).compareVersion(r) > 0) {
                rOpt = Some(version)
              }
            case _ => rOpt = Some(version)
          }
        }
      }
    }
    rOpt match {
      case Some(r) => return r
      case _ =>
        eprintln(s"Could not find the latest version of $name; using $default")
        return default
    }
  }
  val r = lookupVersionH()
  return r
}

val eclipseVersion = option.eclipse.get
val osateVersion = option.osate.get

val agreeId = "com.rockwellcollins.atc.agree.feature.feature.group"
val agreeUrl = "https://raw.githubusercontent.com/loonwerks/AGREE-Updates/master"
val agreeVersion = lookupVersion("AGREE", agreeUrl, option.agree.get)

val resoluteId = "com.rockwellcollins.atc.resolute.feature.feature.group"
val resoluteUrl = "https://raw.githubusercontent.com/loonwerks/Resolute-Updates/master"
val resoluteVersion = lookupVersion("Resolute", resoluteUrl, option.resolute.get)

val briefCaseId = "com.collins.trustedsystems.briefcase.feature.feature.group"
val briefCaseUrl = s"https://download.eclipse.org/releases/$eclipseVersion,https://raw.githubusercontent.com/loonwerks/BriefCASE-Updates/master"
val briefCaseVersion = lookupVersion("BriefCASE", briefCaseUrl, option.briefcase.get)

val hamrCliId = "org.sireum.aadl.osate.cli.feature.feature.group"
val hamrId = "org.sireum.aadl.osate.hamr.feature.feature.group"
val hamrUrl = "https://raw.githubusercontent.com/sireum/osate-update-site/master"
val hamrVersion: String = lookupVersion("HAMR", hamrUrl, option.hamr.get)

val features = s"$hamrCliId=$hamrUrl/$hamrVersion;$hamrId=$hamrUrl/$hamrVersion;$briefCaseId=$briefCaseUrl/$briefCaseVersion;$resoluteId=$resoluteUrl/$resoluteVersion;$agreeId=$agreeUrl/$agreeVersion"
val verContent = s"eclipse=$eclipseVersion;osate=$osateVersion;$features"
val ver: Os.Path = if (Os.isMac) fmideDir / "Contents" / "Eclipse" / "VER"  else fmideDir / "VER"

if (ver.exists && ver.read == verContent) {
  Os.exit(0)
}

ver.removeAll()

fmideDir.removeAll()
val temp = fmideDir.up.canon / s".${fmideDir.name}"
temp.removeAll()
var env = ISZ[(String, String)]()
Os.env("JAVA_HOME") match {
  case Some(v) => env = env :+ (("PATH", s"${Os.path(v) / "bin"}${Os.pathSep}${Os.env("PATH").get}"))
  case _ =>
}
println("Installing FMIDE ...")
proc"$sireum hamr phantom --quiet --update --osate $temp --version $osateVersion --features $features".env(env).console.runCheck()
for (p <- temp.list if ops.StringOps(p.name).startsWith("osate-")) {
  p.moveTo(fmideDir)
  Os.kind match {
    case Os.Kind.Linux =>
      (fmideDir / "osate").moveTo(fmideDir / "fmide")
      (fmideDir / "osate.ini").moveTo(fmideDir / "fmide.ini")
    case Os.Kind.Win =>
      (fmideDir / "osate.exe").moveTo(fmideDir / "fmide.exe")
      (fmideDir / "osate.ini").moveTo(fmideDir / "fmide.ini")
    case Os.Kind.Mac =>
      proc"xattr -rd com.apple.quarantine $fmideDir".runCheck()
      // don't need to move osate.ini to fmide.ini for Mac
    case _ =>
  }
}
temp.removeAll()

ver.writeOver(verContent)
println(s"FMIDE is installed at $fmideDir")
// END USER CODE
