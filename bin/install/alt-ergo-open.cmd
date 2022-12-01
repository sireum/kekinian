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
val altErgoVersion = "2.3.3"
val altErgoGists = HashMap.empty[String, (String, String, String)] +
  "2.3.3" ~> ((
    "https://gist.github.com/34d4cd2e2cce0ded01382b3d0c112935.git",
    "https://gist.github.com/786e87c1c383a7813c4aa0b33dddf80e.git",
    "https://gist.github.com/64fe5ddc996a96e502ced3418fe3a633.git"
  ))

val cores: String = Os.cliArgs match {
  case ISZ(n) => Z(n).getOrElse(Os.numOfProcessors).string
  case _ => s"${Os.numOfProcessors}"
}

val cacheDir: Os.Path = Os.env("SIREUM_CACHE") match {
  case Some(dir) => Os.path(dir)
  case _ => Os.home / "Downloads" / "sireum"
}


def altErgo(dir: Os.Path): Unit = {
  val (altErgoLibOpenUrl, altErgoParsersOpenUrl, altErgoOpenUrl) = altErgoGists.get(altErgoVersion).get
  val env = ISZ("PATH" ~> s"${dir.up.canon}${Os.pathSep}${Os.env("PATH").get}")
  println(s"Installing Alt-Ergo $altErgoVersion (Apache 2.0 License) ...")
  Os.proc(ISZ((dir.up / "opam").canon.string, "pin", s"--root=$dir", "remove", "alt-ergo-lib-open", "alt-ergo-parsers-open", "alt-ergo-open", "-y")).runCheck()
  Os.proc(ISZ((dir.up / "opam").canon.string, "pin", s"--root=$dir", altErgoLibOpenUrl, "-y", "-j", cores)).env(env).runCheck()
  Os.proc(ISZ((dir.up / "opam").canon.string, "pin", s"--root=$dir", altErgoParsersOpenUrl, "-y", "-j", cores)).env(env).runCheck()
  Os.proc(ISZ((dir.up / "opam").canon.string, "pin", s"--root=$dir", altErgoOpenUrl, "-y", "-j", cores)).env(env).runCheck()

  for (d <- dir.list if (d / ".opam-switch").exists) {
    for (p <- (d / ".opam-switch" / "overlay").list ++ (d / ".opam-switch" / "sources").list if p.isDir) {
      val nameOps = ops.StringOps(p.name)
      if (nameOps.startsWith("alt-ergo") && nameOps.endsWith("-open")) {
        p.removeAll()
      }
    }
  }
  println()
}

def install(platformDir: Os.Path): Unit = {
  val opamDir = platformDir / ".opam"
  val ver = platformDir / ".alt-ergo.ver"

  (Os.slashDir / "opam.cmd").slash(ISZ())

  if (ver.exists && ver.read == altErgoVersion) {
    return
  }

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
