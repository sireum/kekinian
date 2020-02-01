::#! 2> /dev/null                                                                                           #
@ 2>/dev/null # 2>nul & echo off & goto BOF                                                                 #
export SIREUM_HOME=$(cd -P $(dirname "$0")/../.. && pwd -P)                                                 #
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
if not exist "%~dp0..\sireum.jar" call "%~dp0..\init.bat"
"%~dp0..\sireum.bat" slang run -s -n "%0" %*
exit /B %errorlevel%
:native
%~dpnx0.com %*
exit /B %errorlevel%
::!#
// #Sireum
import org.sireum._

val homeBin: Os.Path = Os.slashDir.up.canon
val home = homeBin.up.canon

var platformNameUrlMap = Map.empty[Os.Kind.Type, (String, String)]

for (r <- GitHub.repo("loonwerks", "formal-methods-workbench").releases.take(1); a <- r.assets) {
  val aNameOps = ops.StringOps(a.name)
  val p = (a.name, a.url)
  if (aNameOps.contains("win32")) {
    platformNameUrlMap = platformNameUrlMap + Os.Kind.Win ~> p
  } else if (aNameOps.contains("linux")) {
    platformNameUrlMap = platformNameUrlMap + Os.Kind.Linux ~> p
  } else if (aNameOps.contains("macos")) {
    platformNameUrlMap = platformNameUrlMap + Os.Kind.Mac ~> p
  }
}

def download(kind: Os.Kind.Type): Os.Path = {
  val (name, url) = platformNameUrlMap.get(kind).get
  val r = Os.home / "Downloads" / "sireum" / name
  if (!r.exists) {
    println(s"Downloading $name ...")
    r.downloadFrom(url)
    println()
  }
  return r
}

def linux(): Unit = {
  val f = download(Os.Kind.Linux)
  val d = homeBin / "linux" / "fmide"
  d.removeAll()
  d.mkdirAll()
  println(s"Extracting ${f.name} ...")
  Os.proc(ISZ("tar", "xfz", f.string)).at(d).runCheck()
  println()
  println(s"FMIDE is installed at $d")
}

def mac(): Unit = {
  val f = download(Os.Kind.Mac)
  val homeBinMac = homeBin / "mac"
  val d = homeBinMac / "fmide.app"
  d.removeAll()
  homeBinMac.mkdirAll()
  println(s"Extracting ${f.name} ...")
  Os.proc(ISZ("tar", "xfz", f.string)).at(homeBinMac).runCheck()
  (homeBinMac / "com.collins.trustedsystems.fmw.ide.app").moveTo(d)
  println()
  println(s"FMIDE is installed at $d")
}

def win(): Unit = {
  val f = download(Os.Kind.Win)
  val d = homeBin / "win" / "fmide"
  d.removeAll()
  d.mkdirAll()
  println(s"Extracting ${f.name} ...")
  f.unzipTo(d)
  println()
  println(s"FMIDE is installed at $d")
}

Os.kind match {
  case Os.Kind.Linux => linux()
  case Os.Kind.Mac => mac()
  case Os.Kind.Win => win()
  case _ =>
    eprintln("Unsupported platform")
    Os.exit(-1)
}
