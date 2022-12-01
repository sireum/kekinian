::/*#! 2> /dev/null                                          #
@ 2>/dev/null # 2>nul & echo off & goto BOF                  #
export SIREUM_HOME=$(cd -P $(dirname "$0")/../.. && pwd -P)  #
exec "${SIREUM_HOME}/bin/sireum" slang run "$0" "$@"         #
:BOF
setlocal
if not exist "%~dp0..\sireum.jar" call "%~dp0..\init.bat"
"%~dp0..\sireum.bat" slang run "%0" %*
exit /B %errorlevel%
::!#*/
// #Sireum
import org.sireum._

val version = "1.5.6"

val homeBin = Os.slashDir.up.canon

val ver = homeBin / ".antlrworks.jar.ver"

if (ver.exists && ver.read == version) {
  Os.exit(0)
}

val bundle = s"antlrworks.jar"
val url = s"https://github.com/sireum/antlrworks/releases/download/$version/$bundle"

val cacheDir: Os.Path = Os.env("SIREUM_CACHE") match {
  case Some(dir) => Os.path(dir)
  case _ => Os.home / "Downloads" / "sireum"
}

val cache: Os.Path = cacheDir / bundle
if (!cache.exists) {
  cache.up.mkdirAll()
  println(s"ANTLRWorks v$version ...")
  cache.downloadFrom(url)
  println()
}

cache.copyOverTo(homeBin / bundle)

val java: Os.Path = Os.kind match {
  case Os.Kind.Mac => homeBin / "mac" / "java" / "bin" / "java"
  case Os.Kind.Linux => homeBin / "linux" / "java" / "bin" / "java"
  case Os.Kind.LinuxArm => homeBin / "linux" / "arm" / "java" / "bin" / "java"
  case Os.Kind.Win => homeBin / "win" / "java" / "bin" / "java.exe"
  case _ => Os.path("java")
}

println(s"To launch ANTLRWorks: $java -jar ${homeBin / bundle}")

ver.writeOver(version)
