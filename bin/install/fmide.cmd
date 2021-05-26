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

import org.sireum._

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

Os.cliArgs.size match {
  case z"0" =>
  case z"1" =>
    Os.cliArgs(0) match {
      case string"nightly" =>
      case tagName => fmwReleaseTagNameOpt = Some(tagName)
    }
  case z"2" =>
    fmwReleaseTagNameOpt = Some(Os.cliArgs(0))
    briefCaseReleaseTagNameOpt = Some(Os.cliArgs(1))
  case _ =>
    println("Usage: fmide.cmd [ <fmw-tag-name> [ <briefcase-tag-name> ] | nightly ]")
    Os.exit(-1)
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
