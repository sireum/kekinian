::#! 2> /dev/null                                            #
@ 2>/dev/null # 2>nul & echo off & goto BOF                  #
export SIREUM_HOME=$(cd -P $(dirname "$0")/../.. && pwd -P)  #
exec "${SIREUM_HOME}/bin/sireum" slang run "$0" "$@"         #
:BOF
setlocal
if not exist "%~dp0..\sireum.jar" call "%~dp0..\init.bat"
"%~dp0..\sireum.bat" slang run "%0" %*
exit /B %errorlevel%
::!#
// #Sireum
import org.sireum._

val version = "1.5.0"

if (Os.kind != Os.Kind.Linux) {
  eprintln("This feature is only available on Linux amd64")
  Os.exit(-1)
}

val homeBin = Os.slashDir.up.canon
val ideaDir = homeBin / "linux" / "idea"
val iveSh = ideaDir / "bin" / "IVE.sh"

if (!ideaDir.exists || !iveSh.exists) {
  eprintln("Please setup Sireum IVE first")
  Os.exit(-1)
}

val projectorDir = ideaDir / "lib" / "projector-server"
val ver = projectorDir / "VER"

if (ver.exists && ver.read == version) {
  Os.exit(0)
}

projectorDir.removeAll()
projectorDir.mkdirAll()

val bundle = s"projector-server-v$version.zip"
val url = s"https://github.com/JetBrains/projector-server/releases/download/v$version/projector-server-v$version.zip"

val cacheDir: Os.Path = Os.env("SIREUM_CACHE") match {
  case Some(dir) => Os.path(dir)
  case _ => Os.home / "Downloads" / "sireum"
}

val cache: Os.Path = cacheDir / bundle
if (!cache.exists) {
  cache.up.mkdirAll()
  println(s"Downloading projector-server v$version ...")
  cache.downloadFrom(url)
  println()
}

println(s"Extracting $cache ...")
cache.unzipTo(projectorDir.up)
(projectorDir.up / s"projector-server-$version").moveTo(projectorDir)
println()

val f = ideaDir / "bin" / "projector-server.sh"
f.writeOver(ops.StringOps(ops.StringOps(iveSh.read).
  replaceAllLiterally("$CLASSPATH" , "$CLASSPATH:$IDE_HOME/lib/projector-server/lib/*")).
  replaceAllLiterally("com.intellij.idea.Main",
    "-Dorg.jetbrains.projector.server.classToLaunch=com.intellij.idea.Main org.jetbrains.projector.server.ProjectorLauncher"))
f.chmod("+x")
println(s"Wrote $f")

ver.writeOver(version)
