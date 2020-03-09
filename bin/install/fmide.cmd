::#! 2> /dev/null                                                                                           #
@ 2>/dev/null # 2>nul & echo off & goto BOF                                                                 #
export SIREUM_HOME=$(cd -P $(dirname "$0")/../.. && pwd -P)                                                 #
if [ -f "$0.com" ] && [ "$0.com" -nt "$0" ]; then                                                           #
  exec "$0.com" "$@"                                                                                        #
else                                                                                                        #
  rm -fR "$0.com"                                                                                           #
  exec "${SIREUM_HOME}/bin/sireum" slang run -s "$0" "$@"                                                #
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

val homeBin: Os.Path = Os.slashDir.up.canon
val home = homeBin.up.canon

var platformNameUrlMap = Map.empty[Os.Kind.Type, (String, String)]
var releaseTagNameOpt: Option[String] = None()

Os.cliArgs.size match {
  case z"0" =>
  case z"1" => releaseTagNameOpt = Some(Os.cliArgs(0))
  case _ =>
    println("Usage: fmide.cmd [tag-name]")
    Os.exit(-1)
}

def findAssets(): Unit = {
  var first = T

  for (r <- GitHub.repo("loonwerks", "formal-methods-workbench").releases) {
    if (releaseTagNameOpt == Some(r.tagName) || (first && releaseTagNameOpt.isEmpty)) {
      for (a <- r.assets) {
        val aNameOps = ops.StringOps(a.name)
        if (aNameOps.startsWith("com.collins.trustedsystems.fmw.")) {
          val p = (a.name, a.url)
          if (aNameOps.contains("win32") && !platformNameUrlMap.contains(Os.Kind.Win)) {
            platformNameUrlMap = platformNameUrlMap + Os.Kind.Win ~> p
          } else if (aNameOps.contains("linux") && !platformNameUrlMap.contains(Os.Kind.Linux)) {
            platformNameUrlMap = platformNameUrlMap + Os.Kind.Linux ~> p
          } else if (aNameOps.contains("macos") && !platformNameUrlMap.contains(Os.Kind.Mac)) {
            platformNameUrlMap = platformNameUrlMap + Os.Kind.Mac ~> p
          }
        }
      }
    }
    first = F
  }

  if (platformNameUrlMap.isEmpty) {
    releaseTagNameOpt match {
      case Some(releaseTagName) => eprintln(s"Could not find FMIDE $releaseTagName build assets")
      case _ => eprintln("Could not find FMIDE latest nightly build assets")
    }
    Os.exit(-1)
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
  println(s"Extracting $f ...")
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
  println(s"Extracting $f ...")
  Os.proc(ISZ("tar", "xfz", f.string)).at(homeBinMac).runCheck()
  for (p <- homeBinMac.list if ops.StringOps(p.name).startsWith("com.collins.")) {
    p.moveTo(d)
  }
  println()
  println(s"FMIDE is installed at $d")
}

def win(): Unit = {
  val f = download(Os.Kind.Win)
  val d = homeBin / "win" / "fmide"
  d.removeAll()
  d.mkdirAll()
  println(s"Extracting $f ...")
  f.unzipTo(d)
  println()
  println(s"FMIDE is installed at $d")
}

findAssets()

Os.kind match {
  case Os.Kind.Linux => linux()
  case Os.Kind.Mac => mac()
  case Os.Kind.Win => win()
  case _ =>
    eprintln("Unsupported platform")
    Os.exit(-1)
}
