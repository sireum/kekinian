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

val homeBin = Os.slashDir.up.canon
val home = homeBin.up.canon
val compCertVersion = "3.9"
val menhirVersion = "20210310"
val coqVersion = "8.13.2"
val ocamlVersion = "4.11.1"
val opamVersion = "2.0.8"

val cores: String = Os.cliArgs match {
  case ISZ(n) => Z(n).getOrElse(4).string
  case _ => "4"
}

def opam(dir: Os.Path, bundle: String): Unit = {
  println(
    st"""Note that:
        |  "The CompCert C compiler is not free software.
        |   This public release can be used for evaluation, research and
        |   education purposes, but not for commercial purposes."
        |   (see: https://github.com/AbsInt/CompCert/blob/master/LICENSE)
        |""".render)
  val opamExe = (dir.up / "opam").canon

  dir.removeAll()
  opamExe.removeAll()

  val cache = Os.home / "Downloads" / "sireum" / bundle

  if (!cache.exists) {
    println(s"Downloading $cache ...")
    cache.downloadFrom(s"https://github.com/ocaml/opam/releases/download/$opamVersion/$bundle")
    println()
  }

  cache.copyOverTo(opamExe)
  opamExe.chmod("+x")

  println(s"Initializing opam with OCaml $ocamlVersion in $dir ...")
  Os.proc(ISZ(opamExe.string, "init", s"--root=$dir", s"--comp=$ocamlVersion", "--no-self-upgrade", "--no-setup", "--disable-sandboxing", "--reinit", "-a", "-j", cores)).runCheck()
  Os.proc(ISZ((dir.up / "opam").canon.string, "repo", "add", s"--root=$dir", "--no-self-upgrade", "coq-released", "https://coq.inria.fr/opam/released")).runCheck()
  println()
}

def coq(dir: Os.Path): Unit = {
  println(s"Installing Coq $coqVersion ...")
  Os.proc(ISZ((dir.up / "opam").canon.string, "install", s"--root=$dir", "--no-self-upgrade", s"coq=$coqVersion", "-y", "-j", cores)).runCheck()
  println()
}

def menhir(dir: Os.Path): Unit = {
  println(s"Installing Menhir $menhirVersion ...")
  Os.proc(ISZ((dir.up / "opam").canon.string, "install", s"--root=$dir", "--no-self-upgrade", s"menhir=$menhirVersion", "-y", "-j", cores)).runCheck()
  println()
}

def compCert(dir: Os.Path): Unit = {
  println(s"Installing CompCert $compCertVersion ...")
  Os.proc(ISZ((dir.up / "opam").canon.string, "install", s"--root=$dir", "--no-self-upgrade", s"coq-compcert=$compCertVersion", "-y", "-j", cores)).runCheck()
  println()
}

def install(platformDir: Os.Path, opamSuffix: String): Unit = {
  val opamDir = platformDir / ".opam"
  val ver = platformDir / ".compcert.ver"

  if (ver.exists && ver.read === compCertVersion) {
    return
  }

  opamDir.removeAll()

  opam(opamDir, s"opam-$opamVersion-$opamSuffix")
  coq(opamDir)
  menhir(opamDir)
  compCert(opamDir)

  ver.writeOver(compCertVersion)

  println(s"CompCert is installed")
}


Os.kind match {
  case Os.Kind.Mac => install(homeBin / "mac", "x86_64-macos")
  case Os.Kind.Linux => install(homeBin / "linux", "x86_64-linux")
  case Os.Kind.LinuxArm => install(homeBin / "linux" / "arm", "arm64-linux")
  case _ =>
    eprintln("Unsupported platform")
    Os.exit(-1)
}
