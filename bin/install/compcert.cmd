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

val homeBin = Os.slashDir.up.canon
val home = homeBin.up.canon
val compcertVersion = "v3.7"
val menhirVersion = "20200211"
val coqVersion = "8.11.0"
val ocamlVersion = "4.09.1"
val opamVersion = "2.0.7"

val cores: String = Os.cliArgs match {
  case ISZ(n) => Z(n).getOrElse(4).string
  case _ => "4"
}

def opam(dir: Os.Path, bundle: String): Unit = {
  println(
    st"""Note that:
        |  "The CompCert C compiler is not free software.
        |   This public release can be used for evaluation, research and education purposes,
        |   but not for commercial purposes." (see: http://compcert.inria.fr/doc/LICENSE)
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

def compcert(opamDir: Os.Path, dir: Os.Path, target: String): Unit = {
  val temp = Os.tempDir()
  temp.removeOnExit()
  val git = temp / "CompCert"

  println(s"Cloning CompCert $compcertVersion ...")
  Os.proc(ISZ("git", "clone", "--recursive", "-b", compcertVersion, s"https://github.com/AbsInt/CompCert.git", git.name)).at(temp).runCheck()
  println()

  val opamExe = (opamDir.up / "opam").canon.string

  println(s"Configuring CompCert ...")
  Os.proc(ISZ(opamExe, "exec", s"--root=$opamDir", "--no-self-upgrade", "--", (git / "configure").string, "-prefix", dir.string, target)).at(git).runCheck()
  println()

  println(s"Building CompCert ...")
  Os.proc(ISZ(opamExe, "exec", s"--root=$opamDir", "--no-self-upgrade", "--", "make", "-j", cores, "all")).at(git).runCheck()
  println()

  println(s"Installing CompCert ...")
  Os.proc(ISZ(opamExe, "exec", s"--root=$opamDir", "--no-self-upgrade", "--", "make", "install")).at(git).runCheck()
  println()
}

def mac(): Unit = {
  val platformDir = homeBin / "mac"
  val opamDir = platformDir / ".opam"
  val compcertDir = platformDir / "compcert"
  val ver = compcertDir / "VER"

  if (ver.exists && ver.read == compcertVersion) {
    return
  }

  compcertDir.removeAll()

  opam(opamDir, s"opam-$opamVersion-x86_64-macos")
  coq(opamDir)
  menhir(opamDir)
  compcert(opamDir, compcertDir, "x86_64-macosx")

  ver.writeOver(compcertVersion)

  println()
  println(s"CompCert is installed at $compcertDir")
}

def linux(isArm: B): Unit = {
  val platformDir: Os.Path = if (isArm) homeBin / "linux" / "arm" else homeBin / "linux"
  val opamDir = platformDir / ".opam"
  val compcertDir = platformDir / "compcert"
  val ver = compcertDir / "VER"

  if (ver.exists && ver.read == compcertVersion) {
    return
  }

  compcertDir.removeAll()

  opam(opamDir, if (isArm) s"opam-$opamVersion-arm64-linux" else s"opam-$opamVersion-x86_64-linux")
  coq(opamDir)
  menhir(opamDir)
  compcert(opamDir, compcertDir, if (isArm) "aarch64-linux" else "x86_64-linux")

  ver.writeOver(compcertVersion)

  println(s"CompCert is installed at $compcertDir")
}

Os.kind match {
  case Os.Kind.Mac => mac()
  case Os.Kind.Linux => linux(F)
  case Os.Kind.LinuxArm => linux(T)
  case _ =>
    eprintln("Unsupported platform")
    Os.exit(-1)
}
