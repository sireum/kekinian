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


val clionVersion = "2020.3.1"

val url = s"https://download.jetbrains.com/cpp"

val homeBin = Os.slashDir.up.canon
val home = homeBin.up.canon
val sireumJar = homeBin / "sireum.jar"
val sireum = homeBin / (if (Os.isWin) "sireum.bat" else "sireum")
val versions = (home / "versions.properties").properties
val jbrVer = versions.get("org.sireum.version.jbr").get
val jbrBuildVer = versions.get("org.sireum.version.jbr.build").get
val jbrFilename = s"jbr-$jbrVer-linux-aarch64-b$jbrBuildVer.tar.gz"
val jbrUrl = s"https://bintray.com/jetbrains/intellij-jbr/download_file?file_path=$jbrFilename"

def deleteSources(dir: Os.Path): Unit = {
  for (f <- Os.Path.walk(dir, F, F, (p: Os.Path) => ops.StringOps(p.name).endsWith(".java") || ops.StringOps(p.name).endsWith(".scala"))) {
    f.removeAll()
  }
}

def mac(): Unit = {
  val platformDir = homeBin / "mac"
  val clionDir = platformDir / "clion"
  val clionAppDir = clionDir / "CLion.app"
  val ver = clionDir / "VER"

  if (ver.exists && ver.read === clionVersion) {
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

  deleteSources(clionDir)

  ver.writeOver(clionVersion)

  println()
  println(s"CLion is installed at $clionDir")
}

def linux(isArm: B): Unit = {
  val platformDir: Os.Path = if (isArm) homeBin / "linux" / "arm" else homeBin / "linux"
  val clionDir = platformDir / "clion"
  val ver = clionDir / "VER"

  if (ver.exists && ver.read === clionVersion) {
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

  if (isArm) {
    val clionsh = clionDir / "bin" / "clion.sh"
    println(s"Patching $clionsh ... ")
    clionsh.writeOver(ops.StringOps(clionsh.read).replaceAllLiterally("\"x86_64\"", "\"aarch64\""))
    clionsh.chmod("+x")

    val jbrCache = Os.home / "Downloads" / "sireum" / "idea" / jbrFilename
    if (!jbrCache.exists) {
      jbrCache.up.mkdirAll()
      println(s"Downloading from $jbrUrl ...")
      jbrCache.downloadFrom(jbrUrl)
    }
    println(s"Replacing ${clionDir / "jbr"} ...")
    (clionDir / "jbr").removeAll()
    Os.proc(ISZ("tar", "xfz", jbrCache.string)).at(clionDir).console.runCheck()

    val clionVersionOps = ops.StringOps(clionVersion)
    val clionMajorVersion = clionVersionOps.substring(0, clionVersionOps.lastIndexOf('.'))
    val config = Os.home / ".config" / "JetBrains" / s"CLion$clionMajorVersion" / "idea.properties"
    val configContent = s"idea.filewatcher.executable.path=${platformDir / "fsnotifier"}"
    if (config.exists) {
      println(s"Please ensure the following line is in the existing $config")
      println(configContent)
    } else {
      config.up.mkdirAll()
      config.writeOver(configContent)
      println(s"Wrote $config")
    }
  }

  deleteSources(clionDir)

  ver.writeOver(clionVersion)

  println()
  println(s"CLion is installed at $clionDir")
}

def win(): Unit = {
  val platformDir = homeBin / "win"
  val clionDir = platformDir / "clion"
  val ver = clionDir / "VER"

  if (ver.exists && ver.read === clionVersion) {
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

  deleteSources(clionDir)

  ver.writeOver(clionVersion)

  println()
  println(s"CLion is installed at $clionDir")
}

Os.kind match {
  case Os.Kind.Mac => mac()
  case Os.Kind.Linux => linux(F)
  case Os.Kind.LinuxArm => linux(T)
  case Os.Kind.Win => win()
  case _ =>
    eprintln("Unsupported platform")
    Os.exit(-1)
}
