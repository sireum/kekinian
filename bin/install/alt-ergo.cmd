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
val altErgoVersion = "2.4.1"

val cores: String = Os.cliArgs match {
  case ISZ(n) => Z(n).getOrElse(Os.numOfProcessors).string
  case _ => s"${Os.numOfProcessors}"
}

val cacheDir: Os.Path = Os.env("SIREUM_CACHE") match {
  case Some(dir) => Os.path(dir)
  case _ => Os.home / "Downloads" / "sireum"
}


def altErgo(dir: Os.Path): Unit = {
  val env = ISZ("PATH" ~> s"${Os.env("PATH").get}${Os.pathSep}${dir.up.canon}")
  println(s"Installing Alt-Ergo $altErgoVersion ...")
  Os.proc(ISZ((dir.up / "opam").canon.string, "pin", s"--root=$dir", "remove", "alt-ergo", "-y")).runCheck()
  Os.proc(ISZ((dir.up / "opam").canon.string, "install", s"--root=$dir", "--no-self-upgrade", s"alt-ergo=$altErgoVersion", "-y", "-j", cores)).env(env).console.runCheck()
  Os.proc(ISZ((dir.up / "opam").canon.string, "pin", s"--root=$dir", "add", "alt-ergo", s"$altErgoVersion", "-y")).runCheck()
  println()
}

def install(platformDir: Os.Path): Unit = {
  val opamDir = platformDir / ".opam"
  val ver = platformDir / ".alt-ergo.ver"

  if (ver.exists && ver.read === altErgoVersion) {
    return
  }

  println(
    st"""Note that:
        |  Alt-Ergo is not free software.
        |  This public release can only be used for non-commercial purposes.
        |  (see: https://github.com/OCamlPro/alt-ergo/blob/next/LICENSE.md)
        |""".render)

  (Os.slashDir / "opam.cmd").slash(ISZ())
  (Os.slashDir / "menhir.cmd").slash(ISZ())

  altErgo(opamDir)

  ver.writeOver(altErgoVersion)

  println(s"Alt-Ergo is installed")
}


Os.kind match {
  case Os.Kind.Mac => install(homeBin / "mac")
  case Os.Kind.Linux => install(homeBin / "linux")
  case Os.Kind.LinuxArm => install(homeBin / "linux" / "arm")
  case _ =>
    eprintln("Unsupported platform")
    Os.exit(-1)
}
