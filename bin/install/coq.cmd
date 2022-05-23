::#! 2> /dev/null                                                                                           #
@ 2>/dev/null # 2>nul & echo off & goto BOF                                                                 #
export SIREUM_HOME=$(cd -P $(dirname "$0")/../.. && pwd -P)                                                 #
if [ -f "$0.com" ] && [ "$0.com" -nt "$0" ]; then                                                           #
  exec "$0.com" "$@"                                                                                        #
else                                                                                                        #
  rm -fR "$0.com"                                                                                           #
  exec "${SIREUM_HOME}/bin/sireum" slang run "$0" "$@"                                                      #
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

val homeBin = Os.slashDir.up.canon
val home = homeBin.up.canon
val coqVersion = "8.15.0"

val (cores, isIde): (String, B) = Os.cliArgs match {
  case ISZ(n) => (Z(n).getOrElse(Os.numOfProcessors).string, F)
  case ISZ(string"ide") => (Os.numOfProcessors.string, T)
  case ISZ(n, string"ide") => (Z(n).getOrElse(Os.numOfProcessors).string, T)
  case ISZ(string"ide", n) => (Z(n).getOrElse(Os.numOfProcessors).string, T)
  case _ => (Os.numOfProcessors.string, F)
}

val cacheDir: Os.Path = Os.env("SIREUM_CACHE") match {
  case Some(dir) => Os.path(dir)
  case _ => Os.home / "Downloads" / "sireum"
}

def coq(dir: Os.Path): Unit = {
  println(s"Installing Coq${if (isIde) " IDE" else ""} $coqVersion ...")
  val variant: String = if (isIde) "ide" else ""
  if (isIde) {
    Os.proc(ISZ((dir.up / "opam").canon.string, "pin", s"--root=$dir", "remove", s"coqide", "-y")).runCheck()
  }
  Os.proc(ISZ((dir.up / "opam").canon.string, "pin", s"--root=$dir", "remove", s"coq", "-y")).runCheck()
  Os.proc(ISZ((dir.up / "opam").canon.string, "install", s"--root=$dir", "--no-self-upgrade", s"coq$variant=$coqVersion", "-y", "-j", cores)).console.runCheck()
  Os.proc(ISZ((dir.up / "opam").canon.string, "pin", s"--root=$dir", "add", s"coq", s"$coqVersion", "-y")).runCheck()
  if (isIde) {
    Os.proc(ISZ((dir.up / "opam").canon.string, "pin", s"--root=$dir", "add", s"coqide", s"$coqVersion", "-y")).runCheck()
  }
  println()
}

def install(platformDir: Os.Path): Unit = {
  val opamDir = platformDir / ".opam"
  val ver = platformDir / ".coq.ver"
  val ideVer = platformDir / ".coqide.ver"

  if (isIde && ideVer.exists && ideVer.read === coqVersion) {
    return
  }

  val coqExists = ver.exists && ver.read === coqVersion
  if (!isIde && coqExists) {
    return
  }

  (Os.slashDir / "opam.cmd").slash(ISZ())

  coq(opamDir)

  if (isIde) {
    ideVer.writeOver(coqVersion)
  }

  if (!coqExists) {
    (platformDir / ".compcert.ver").removeAll()
  }

  ver.writeOver(coqVersion)

  println(s"Coq${if (isIde) " IDE" else ""} is installed")
}


Os.kind match {
  case Os.Kind.Mac => install(homeBin / "mac")
  case Os.Kind.Linux => install(homeBin / "linux")
  case Os.Kind.LinuxArm => install(homeBin / "linux" / "arm")
  case _ =>
    eprintln("Unsupported platform")
    Os.exit(-1)
}
