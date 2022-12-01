::/*#! 2> /dev/null                                                                                         #
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
::!#*/
// #Sireum
import org.sireum._

val homeBin = Os.slashDir.up.canon
val home = homeBin.up.canon
val compCertVersion = "3.11"

val cores: String = Os.cliArgs match {
  case ISZ(n) => Z(n).getOrElse(Os.numOfProcessors).string
  case _ => s"${Os.numOfProcessors}"
}

val cacheDir: Os.Path = Os.env("SIREUM_CACHE") match {
  case Some(dir) => Os.path(dir)
  case _ => Os.home / "Downloads" / "sireum"
}


def compCert(dir: Os.Path): Unit = {
  println(s"Installing CompCert $compCertVersion ...")
  val opam = (dir.up / "opam").canon.string
  Os.proc(ISZ(opam, "pin", s"--root=$dir", "remove", "coq-compcert", "-y")).runCheck()
  Os.proc(ISZ(opam, "install", s"--root=$dir", "--no-self-upgrade", s"coq-compcert=$compCertVersion", "-y", "-j", cores)).console.runCheck()
  Os.proc(ISZ(opam, "pin", s"--root=$dir", "add", "coq-compcert", s"$compCertVersion", "-y")).runCheck()
  println()
}

def install(platformDir: Os.Path): Unit = {
  val opamDir = platformDir / ".opam"
  val ver = platformDir / ".compcert.ver"

  (Os.slashDir / "opam.cmd").slash(ISZ())

  if (ver.exists && ver.read == compCertVersion) {
    return
  }

  println(
    st"""Note that:
        |  "The CompCert C compiler is not free software.
        |   This public release can be used for evaluation, research and
        |   education purposes, but not for commercial purposes."
        |   (see: https://github.com/AbsInt/CompCert/blob/master/LICENSE)
        |""".render)

  val opam = opamDir.up / "opam"

  if (opam.exists) {
    Os.proc(ISZ(opam.canon.string, "update", s"--root=$opamDir")).console.runCheck()
  }

  (Os.slashDir / "menhir.cmd").slash(ISZ())
  (Os.slashDir / "coq.cmd").slash(ISZ())
  compCert(opamDir)

  ver.writeOver(compCertVersion)

  println(s"CompCert is installed")
}


Os.kind match {
  case Os.Kind.Mac => install(homeBin / "mac")
  case Os.Kind.Linux => install(homeBin / "linux")
  case Os.Kind.LinuxArm => install(homeBin / "linux" / "arm")
  case _ =>
    eprintln("Unsupported platform")
    Os.exit(-1)
}
