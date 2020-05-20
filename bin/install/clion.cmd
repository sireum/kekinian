::#! 2> /dev/null                                                                                           #
@ 2>/dev/null # 2>nul & echo off & goto BOF                                                                 #
export SIREUM_HOME=$(cd -P $(dirname "$0")/../.. && pwd -P)                                                 #
if [ -f "$0.com" ] && [ "$0.com" -nt "$0" ]; then                                                           #
  exec "$0.com" "$@"                                                                                        #
else                                                                                                        #
  rm -fR "$0.com"                                                                                           #
  exec "${SIREUM_HOME}/bin/sireum" slang run -s "$0" "$@"                                                   #
fi                                                                                                          #
:BOF
setlocal
set NEWER=False
if exist %~dpnx0.com for /f %%i in ('powershell -noprofile -executionpolicy bypass -command "(Get-Item %~dpnx0.com).LastWriteTime -gt (Get-Item %~dpnx0).LastWriteTime"') do @set NEWER=%%i
if "%NEWER%" == "True" goto native
del "%~dpnx0.com" > nul 2>&1
if not exist "%~dp0..\sireum.jar" call "%~dp0..\init.bat"
"%~dp0..\sireum.bat" slang run -s "%0" %*
exit /B %errorlevel%
:native
%~dpnx0.com %*
exit /B %errorlevel%
::!#
// #Sireum
import org.sireum._


val clionVersion = "2020.1.1"

val url = s"https://download.jetbrains.com/cpp"

val homeBin: Os.Path = Os.slashDir.up.canon
val home = homeBin.up.canon
val sireumJar = homeBin / "sireum.jar"
val sireum = homeBin / (if (Os.isWin) "sireum.bat" else "sireum")


def mac(): Unit = {
  val platformDir = homeBin / "mac"
  val clionDir = platformDir / "clion"
  val clionAppDir = clionDir / "CLion.app"
  val ver = clionDir / "VER"

  if (ver.exists && ver.read == clionVersion) {
    return
  }

  val bundle = s"CLion-$clionVersion.dmg"
  val cache = Os.home / "Downloads" / "sireum" / bundle

  if (!cache.exists) {
    cache.up.mkdirAll()
    println(s"Downloading CLion $clionVersion ...")
    cache.downloadFrom(s"$url/$bundle")
  }
  if (clionDir.exists) {
    clionDir.removeAll()
  }

  println(s"Extracting $cache ...")
  Os.proc(ISZ("hdiutil", "attach", cache.string)).runCheck()
  val dirPath = Os.path("/Volumes/CLion")
  val appPath = dirPath / "CLion.app"
  clionDir.mkdirAll()
  appPath.copyTo(clionAppDir)
  Os.proc(ISZ("hdiutil", "eject", dirPath.string)).runCheck()

  ver.writeOver(clionVersion)

  println()
  println(s"CLion is installed at $clionDir")
}

def linux(): Unit = {
  val platformDir = homeBin / "linux"
  val clionDir = platformDir / "clion"
  val ver = clionDir / "VER"

  if (ver.exists && ver.read == clionVersion) {
    return
  }

  val bundle = s"CLion-$clionVersion.tar.gz"
  val cache = Os.home / "Downloads" / "sireum" / bundle

  if (!cache.exists) {
    cache.up.mkdirAll()
    println(s"Downloading CLion $clionVersion ...")
    cache.downloadFrom(s"$url/$bundle")
  }
  if (clionDir.exists) {
    clionDir.removeAll()
  }
  println(s"Extracting $cache ...")
  Os.proc(ISZ("tar", "xfz", cache.string)).at(platformDir).console.runCheck()
  (platformDir / s"clion-$clionVersion").moveTo(clionDir)

  ver.writeOver(clionVersion)

  println()
  println(s"CLion is installed at $clionDir")
}

def win(): Unit = {
  val platformDir = homeBin / "win"
  val clionDir = platformDir / "clion"
  val ver = clionDir / "VER"

  if (ver.exists && ver.read == clionVersion) {
    return
  }

  val bundle = s"CLion-$clionVersion.win.zip"
  val cache = Os.home / "Downloads" / "sireum" / bundle

  if (!cache.exists) {
    cache.up.mkdirAll()
    println(s"Downloading CLion $clionVersion ...")
    cache.downloadFrom(s"$url/$bundle")
  }
  if (clionDir.exists) {
    clionDir.removeAll()
  }
  println(s"Extracting $cache ...")
  clionDir.mkdirAll()
  cache.unzipTo(clionDir)

  ver.writeOver(clionVersion)

  println()
  println(s"CLion is installed at $clionDir")
}

def platform(p: String): Unit = {
  p match {
    case string"mac" => mac()
    case string"linux" => linux()
    case string"win" => win()
    case _ =>
  }
}

Os.kind match {
  case Os.Kind.Mac => mac()
  case Os.Kind.Linux => linux()
  case Os.Kind.Win => win()
  case _ =>
    eprintln("Unsupported platform")
    Os.exit(-1)
}
