::#! 2> /dev/null                                                                                           #
@ 2>/dev/null # 2>nul & echo off & goto BOF                                                                 #
export SIREUM_HOME=$(cd -P $(dirname "$0")/.. && pwd -P)                                                    #
if [ ! -z ${SIREUM_PROVIDED_SCALA++} ]; then                                                                #
  SIREUM_PROVIDED_JAVA=true                                                                                 #
fi                                                                                                          #
if [ ! -f "${SIREUM_HOME}/bin/sireum.jar" ]; then                                                           #
  "${SIREUM_HOME}/bin/init.sh"                                                                              #
elif [ "${SIREUM_HOME}/versions.properties" -nt "${SIREUM_HOME}/bin/sireum.jar" ]; then                     #
  "${SIREUM_HOME}/bin/init.sh"                                                                              #
fi                                                                                                          #
if [ -n "$COMSPEC" -a -x "$COMSPEC" ]; then                                                                 #
  PLATFORM="win"                                                                                            #
  export SIREUM_HOME=$(cygpath -C OEM -w -a ${SIREUM_HOME})                                                 #
  if [ -z ${SIREUM_PROVIDED_JAVA++} ]; then                                                                 #
    export JAVA_HOME="${SIREUM_HOME}\\bin\\win\\java"                                                       #
    export Z3_HOME="${SIREUM_HOME}\\bin\\win\\z3"                                                           #
    export PATH="${SIREUM_HOME}/bin/win/java":"${SIREUM_HOME}/bin/win/z3":$PATH                             #
    export PATH="$(cygpath -C OEM -w -a ${JAVA_HOME}/bin)":"$(cygpath -C OEM -w -a ${Z3_HOME}/bin)":$PATH   #
  fi                                                                                                        #
elif [ "$(uname)" = "Darwin" ]; then                                                                        #
  PLATFORM="mac"                                                                                            #
  if [ -z ${SIREUM_PROVIDED_JAVA++} ]; then                                                                 #
    export JAVA_HOME="${SIREUM_HOME}/bin/mac/java"                                                          #
    export Z3_HOME="${SIREUM_HOME}/bin/mac/z3"                                                              #
    export PATH="${JAVA_HOME}/bin":"${Z3_HOME}/bin":$PATH                                                   #
  fi                                                                                                        #
elif [ "$(expr substr $(uname -s) 1 5)" = "Linux" ]; then                                                   #
  PLATFORM="linux"                                                                                          #
  if [ -z ${SIREUM_PROVIDED_JAVA++} ]; then                                                                 #
    export JAVA_HOME="${SIREUM_HOME}/bin/linux/java"                                                        #
    export Z3_HOME="${SIREUM_HOME}/bin/linux/z3"                                                            #
    export PATH="${JAVA_HOME}/bin":"${Z3_HOME}/bin":$PATH                                                   #
  fi                                                                                                        #
fi                                                                                                          #
if [ -f "$0.com" ] && [ "$0.com" -nt "$0" ]; then                                                           #
  exec "$0.com" "$@"                                                                                        #
else                                                                                                        #
  rm -fR "$0.com"                                                                                           #
  exec "${SIREUM_HOME}/bin/sireum" slang run -s -n "$0" "$@"                                                #
fi                                                                                                          #
:BOF
setlocal
set NEWER=False
if exist %~dpnx0.com for /f %%i in ('powershell -noprofile -executionpolicy bypass -command "(Get-Item %~dpnx0.com).LastWriteTime -gt (Get-Item %~dpnx0).LastWriteTime"') do @set NEWER=%%i
if "%NEWER%" == "True" goto native
del "%~dpnx0.com" > nul 2>&1
if defined SIREUM_PROVIDED_SCALA set SIREUM_PROVIDED_JAVA=true
if not exist "%~dp0sireum.jar" call "%~dp0init.bat"
if not defined SIREUM_PROVIDED_JAVA set PATH=%~dp0win\java\bin;%~dp0win\z3\bin;%PATH%
"%~dp0sireum.bat" slang run -s -n "%0" %*
exit /B %errorlevel%
:native
%~dpnx0.com %*
exit /B %errorlevel%
::!#
// #Sireum
import org.sireum._


def usage(): Unit = {
  println("Usage: ( mac | linux | win )*")
}

val graalVersion = "19.2.0.1"

val url = s"https://github.com/oracle/graal/releases/download/vm-$graalVersion"

val homeBin: Os.Path = Os.slashDir
val home = homeBin.up
val sireumJar = homeBin / "sireum.jar"
val sireum = homeBin / (if (Os.isWin) "sireum.bat" else "sireum")


def mac(): Unit = {
  val platformDir = homeBin / "mac"
  val graalDir = platformDir / "graal"
  val ver = graalDir / "VER"

  if (ver.exists && ver.read == graalVersion) {
    return
  }

  val bundle = s"graalvm-ce-darwin-amd64-$graalVersion.tar.gz"
  val cache = Os.home / "Downloads" / "sireum" / bundle

  if (!cache.exists) {
    cache.up.mkdirAll()
    println(s"Downloading Graal $graalVersion ...")
    cache.downloadFrom(s"$url/$bundle")
  }
  if (graalDir.exists) {
    graalDir.removeAll()
  }
  println(s"Extracting Graal ...")
  Os.proc(ISZ("tar", "xfz", cache.string)).at(platformDir).console.runCheck()
  (platformDir / s"graalvm-ce-$graalVersion" / "Contents" / "Home").moveTo(graalDir)
  (platformDir / s"graalvm-ce-$graalVersion").removeAll()

  val nativeBundle = s"native-image-installable-svm-darwin-amd64-$graalVersion.jar"
  val nativeCache = Os.home / "Downloads" / "sireum" / nativeBundle
  if (!nativeCache.exists) {
    println(s"Downloading Graal's native-image ...")
    nativeCache.downloadFrom(s"$url/$nativeBundle")
  }

  Os.proc(ISZ((graalDir / "bin" / "gu").string, "install", "--file", nativeCache.string)).console.runCheck()

  ver.writeOver(graalVersion)

  println("... done!")
}

def linux(): Unit = {
  val platformDir = homeBin / "linux"
  val graalDir = platformDir / "graal"
  val ver = graalDir / "VER"

  if (ver.exists && ver.read == graalVersion) {
    return
  }

  val bundle = s"graalvm-ce-linux-amd64-$graalVersion.tar.gz"
  val cache = Os.home / "Downloads" / "sireum" / bundle

  if (!cache.exists) {
    cache.up.mkdirAll()
    println(s"Downloading Graal $graalVersion ...")
    cache.downloadFrom(s"$url/$bundle")
  }
  if (graalDir.exists) {
    graalDir.removeAll()
  }
  println(s"Extracting Graal ...")
  Os.proc(ISZ("tar", "xfz", cache.string)).at(platformDir).console.runCheck()
  (platformDir / s"graalvm-ce-$graalVersion").moveTo(graalDir)

  val nativeBundle = s"native-image-installable-svm-linux-amd64-$graalVersion.jar"
  val nativeCache = Os.home / "Downloads" / "sireum" / nativeBundle
  if (!nativeCache.exists) {
    println(s"Downloading Graal's native-image ...")
    nativeCache.downloadFrom(s"$url/$nativeBundle")
  }

  Os.proc(ISZ((graalDir / "bin" / "gu").string, "install", "--file", nativeCache.string)).console.runCheck()

  ver.writeOver(graalVersion)

  println("... done!")
}

def win(): Unit = {
  val platformDir = homeBin / "win"
  val graalDir = platformDir / "graal"
  val ver = graalDir / "VER"

  if (ver.exists && ver.read == graalVersion) {
    return
  }

  val bundle = s"graalvm-ce-windows-amd64-$graalVersion.zip"
  val cache = Os.home / "Downloads" / "sireum" / bundle

  if (!cache.exists) {
    cache.up.mkdirAll()
    println(s"Downloading Graal $graalVersion ...")
    cache.downloadFrom(s"$url/$bundle")
  }
  if (graalDir.exists) {
    graalDir.removeAll()
  }
  println(s"Extracting Graal ...")
  cache.unzipTo(platformDir)
  (platformDir / s"graalvm-ce-$graalVersion").moveTo(graalDir)

  ver.writeOver(graalVersion)

  println("... done!")
}

def platform(p: String): Unit = {
  p match {
    case string"mac" => mac()
    case string"linux" => linux()
    case string"win" => win()
    case string"-h" => usage()
    case _ =>
      eprintln("Unsupported platform")
      usage()
      Os.exit(-1)
  }
}

if (Os.cliArgs.isEmpty) {
  Os.kind match {
    case Os.Kind.Mac => platform("mac")
    case Os.Kind.Linux => platform("linux")
    case Os.Kind.Win => platform("win")
    case _ => platform("???")
  }
} else {
  for (p <- (HashSSet.empty[String] ++ Os.cliArgs).elements) {
    platform(p)
  }
}

