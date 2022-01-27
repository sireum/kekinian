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

if (Os.kind != Os.Kind.Linux) {
  eprintln("This feature is only available on Linux amd64")
  Os.exit(-1)
}

val version = "2.13.6"

val bundle = s"ffmpeg-$version-libs.zip"
val url = s"https://github.com/sireum/rolling/releases/download/ffmpeg/$bundle"
val home = Os.slashDir.up.up.canon
val lib = home / "lib" / "linux"
val licenseDir = lib / s"ffmpeg-$version-license"

if (licenseDir.exists) {
  Os.exit(0)
}

val cacheDir: Os.Path = Os.env("SIREUM_CACHE") match {
  case Some(dir) => Os.path(dir)
  case _ => Os.home / "Downloads" / "sireum"
}

val cache: Os.Path = cacheDir / bundle
if (!cache.exists) {
  cache.up.mkdirAll()
  println(s"Downloading ffmpeg $version libraries ...")
  cache.downloadFrom(url)
  println()
}

println(s"Extracting $cache ...")
lib.mkdirAll()
cache.unzipTo(lib)
println()
