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
val ocamlVersion = "4.06.1"
val opamVersion = "2.1.2"
val duneVersion = "2.9.3"

val cores: String = Os.cliArgs match {
  case ISZ(n) => Z(n).getOrElse(Os.numOfProcessors).string
  case _ => s"${Os.numOfProcessors}"
}

val cacheDir: Os.Path = Os.env("SIREUM_CACHE") match {
  case Some(dir) => Os.path(dir)
  case _ => Os.home / "Downloads" / "sireum"
}


def opam(dir: Os.Path, bundle: String): Unit = {
  val opamExe = (dir.up / "opam").canon

  dir.removeAll()
  opamExe.removeAll()

  val cache = cacheDir / bundle

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
  Os.proc(ISZ((dir.up / "opam").canon.string, "install", s"--root=$dir", "--no-self-upgrade", s"dune=$duneVersion", "-y", "-j", cores)).runCheck()
  Os.proc(ISZ((dir.up / "opam").canon.string, "pin", s"--root=$dir", "add", "dune", s"$duneVersion", "-y")).runCheck()
  println()
}

def install(platformDir: Os.Path, opamSuffix: String): Unit = {
  val opamDir = platformDir / ".opam"
  val ver = platformDir / ".opam.ver"
  val oVer = s"$opamVersion-20220523.0"

  if (ver.exists && ver.read == oVer) {
    return
  }

  opamDir.removeAll()

  opam(opamDir, s"opam-$opamVersion-$opamSuffix")

  ver.writeOver(oVer)
  (platformDir / ".alt-ergo.ver").removeAll()
  (platformDir / ".coq.ver").removeAll()
  (platformDir / ".coqide.ver").removeAll()
  (platformDir / ".compcert.ver").removeAll()
  (platformDir / ".menhir.ver").removeAll()

  println(s"OPAM is installed")
}


Os.kind match {
  case Os.Kind.Mac =>
    install(homeBin / "mac",
      if (ops.StringOps(proc"uname -m".redirectErr.run().out).trim == "arm64") "arm64-macos"
      else "x86_64-macos")
  case Os.Kind.Linux => install(homeBin / "linux", "x86_64-linux")
  case Os.Kind.LinuxArm => install(homeBin / "linux" / "arm", "arm64-linux")
  case _ =>
    eprintln("Unsupported platform")
    Os.exit(-1)
}
