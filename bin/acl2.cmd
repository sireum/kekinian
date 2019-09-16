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
    export PATH="${SIREUM_HOME}/bin/win/java":$PATH                             #
    export PATH="$(cygpath -C OEM -w -a ${JAVA_HOME}/bin)":$PATH   #
  fi                                                                                                        #
elif [ "$(uname)" = "Darwin" ]; then                                                                        #
  PLATFORM="mac"                                                                                            #
  if [ -z ${SIREUM_PROVIDED_JAVA++} ]; then                                                                 #
    export JAVA_HOME="${SIREUM_HOME}/bin/mac/java"                                                          #
    export PATH="${JAVA_HOME}/bin":$PATH                                                   #
  fi                                                                                                        #
elif [ "$(expr substr $(uname -s) 1 5)" = "Linux" ]; then                                                   #
  PLATFORM="linux"                                                                                          #
  if [ -z ${SIREUM_PROVIDED_JAVA++} ]; then                                                                 #
    export JAVA_HOME="${SIREUM_HOME}/bin/linux/java"                                                        #
    export PATH="${JAVA_HOME}/bin":$PATH                                                   #
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
  println("Usage: [<num-of-cores>] ( mac | linux )*")
}

val homeBin: Os.Path = Os.slashDir
val home = homeBin.up
val sireumJar = homeBin / "sireum.jar"
val sireum = homeBin / (if (Os.isWin) "sireum.bat" else "sireum")
var cores: Z = 4

def ccl(p: String): Unit = {
  val cclVersion = "1.11.5"
  val cclUrlPrefix = s"https://github.com/Clozure/ccl/releases/download/v$cclVersion/"
  val cclBundleMap: Map[String, String] = Map.empty[String, String] ++ ISZ(
    "linux" ~> s"ccl-$cclVersion-linuxx86.tar.gz",
    "mac" ~> s"ccl-$cclVersion-darwinx86.tar.gz"
  )

  val platformDir = homeBin / p
  val cclDir = platformDir / "ccl"
  val ver = cclDir / "VER"

  if (ver.exists && ver.read == cclVersion) {
    return
  }

  val bundle = cclBundleMap.get(p).get

  val cache = Os.home / "Downloads" / "sireum" / bundle

  if (!cache.exists) {
    cache.up.mkdirAll()
    println(s"Downloading Clozure Common Lisp $cclVersion ...")
    cache.downloadFrom(s"$cclUrlPrefix/$bundle")
  }
  if (cclDir.exists) {
    cclDir.removeAll()
  }
  println(s"Extracting Clozure Common Lisp ...")
  Os.proc(ISZ("tar", "xfz", cache.string)).at(platformDir).console.runCheck()

  ver.writeOver(cclVersion)
}

def acl2(p: String): Unit = {
  ccl(p)

  val acl2Version = "8.2"

  val acl2UrlPrefix = s"https://github.com/acl2-devel/acl2-devel/releases/download/$acl2Version/"

  val platformDir = homeBin / p
  val acl2Dir = platformDir / "acl2"
  val cclExe = platformDir / "ccl" / (if (p == "linux") "lx86cl64" else "dx86cl64")
  val ver = acl2Dir / "VER"

  if (ver.exists && ver.read == acl2Version) {
    return
  }

  val bundle = s"acl2-$acl2Version.tar.gz"
  val cache = Os.home / "Downloads" / "sireum" / bundle

  if (!cache.exists) {
    cache.up.mkdirAll()
    println(s"Downloading acl2 $acl2Version ...")
    cache.downloadFrom(s"$acl2UrlPrefix/$bundle")
  }
  if (acl2Dir.exists) {
    acl2Dir.removeAll()
  }
  println(s"Extracting acl2 ...")
  Os.proc(ISZ("tar", "xfz", cache.string)).at(platformDir).console.runCheck()
  (platformDir / s"acl2-$acl2Version").moveTo(acl2Dir)

  val acl2 = acl2Dir / "saved_acl2"
  println(s"Creating $acl2 ...")
  Os.proc(ISZ("make", s"LISP=$cclExe")).at(acl2Dir).console.runCheck()

  println(s"Certifying acl2 books ...")
  Os.proc(ISZ("make", s"ACL2=$acl2", "-j", cores.string, "all")).at(acl2Dir / "books").console.runCheck()

  ver.writeOver(acl2Version)

  println("... done!")
}

def platform(p: String): Unit = {
  p match {
    case string"mac" =>
    case string"linux" =>
    case string"-h" =>
      usage()
      return
    case _ =>
      eprintln("Unsupported platform")
      usage()
      Os.exit(-1)
  }
  acl2(p)
}

val platforms: ISZ[String] = Z(Os.cliArgs(0)) match {
  case Some(n) =>
    cores = n
    ops.ISZOps(Os.cliArgs).drop(1)
  case _ => Os.cliArgs
}

if (platforms.isEmpty) {
  Os.kind match {
    case Os.Kind.Mac => platform("mac")
    case Os.Kind.Linux => platform("linux")
    case Os.Kind.Win => platform("win")
    case _ => platform("???")
  }
} else {
  for (p <- (HashSSet.empty[String] ++ platforms).elements) {
    platform(p)
  }
}