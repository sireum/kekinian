::#! 2> /dev/null                                                                          #
@ 2>/dev/null # 2>nul & echo off & goto BOF                                                #
BIN=$(cd -P $(dirname "$0") && pwd -P)                                                     #
if [ -f "$0.com" ] && [ "$0.com" -nt "$0" ] && [ "$0.com" -nt "${BIN}/sireum.jar" ]; then  #
  exec "$0.com" "$@"                                                                       #
fi                                                                                         #
rm -f "$0.com"                                                                             #
if [ -z ${SIREUM_HOME} ]; then                                                             #
  echo "Please set SIREUM_HOME env var"                                                    #
  exit -1                                                                                  #
fi                                                                                         #
exec ${SIREUM_HOME}/bin/sireum slang run -n "$0" "$@"                                      #
:BOF
setlocal
if not defined SIREUM_HOME (
  echo Please set SIREUM_HOME env var
  exit /B -1
)
set NEWER=False
if exist %~dpnx0.com for /f %%i in ('powershell -noprofile -executionpolicy bypass -command "((Get-Item %~dpnx0.com).LastWriteTime -gt (Get-Item %~dpnx0).LastWriteTime) -and ((Get-Item %~dpnx0.com).LastWriteTime -gt (Get-Item %~dp0\sireum.jar).LastWriteTime)"') do @set NEWER=%%i
if "%NEWER%" == "True" goto native
del "%~dpnx0.com" > nul 2>&1
%SIREUM_HOME%\bin\sireum.bat slang run -n "%0" %*
exit /B %errorlevel%
:native
%~dpnx0.com %*
exit /B %errorlevel%
::!#
// #Sireum

import org.sireum._
import org.sireum.project.ProjectUtil._
import org.sireum.project.{JSON, Project}

def usage(): Unit = {
  println("Usage: [ json ]")
}

var isDot = T

Os.cliArgs match {
  case ISZ(string"json") => isDot = F
  case ISZ(string"-h") =>
    usage()
    Os.exit(0)
  case ISZ() =>
  case _ =>
    usage()
    Os.exit(-1)
}

val homeDir = Os.slashDir.up.canon

val projects = ISZ(
  homeDir / "runtime",
  homeDir / "slang",
  homeDir / "tools",
  homeDir / "alir",
  homeDir / "transpilers",
  homeDir / "logika",
  homeDir / "hamr" / "air",
  homeDir / "hamr" / "phantom",
  homeDir / "hamr" / "codegen" / "art",
  homeDir / "hamr" / "codegen" / "common",
  homeDir / "hamr" / "codegen" / "act",
  homeDir / "hamr" / "codegen" / "arsit",
  homeDir / "hamr" / "codegen",
  homeDir / "server",
  homeDir / "cli"
)

var project = Project.empty

for (p <- projects) {
  val f = p / "bin" / "project.cmd"
  val fNative = p / "bin" / "project.cmd.com"
  if (isDot) {
    println(s"Loading $f ...")
  }
  var prj = Project.empty
  var ok = F

  def deserialize(text: String): Unit = {
    org.sireum.project.JSON.toProject(ops.StringOps(text).split((c: C) => c === '\n')(0)) match {
      case Either.Left(sp) =>
        prj = sp
        ok = T
      case _ =>
    }
  }

  if (fNative.exists) {
    if (fNative.lastModified > f.lastModified) {
      val r = Os.proc(ISZ(fNative.string, "json")).run()
      if (r.ok) {
        deserialize(r.out)
      }
    }
  }
  if (!ok) {
    fNative.removeAll()
    val proc = Os.proc(ISZ(f.string, "json"))
    val r: Os.Proc.Result = if (isDot) proc.console.run() else proc.run()
    if (r.ok) {
      deserialize(r.out)
    }
  }
  if (!ok) {
    eprintln(s"Failed loading $f ...")
    Os.exit(-1)
  }
  project = project ++ prj
  if (isDot) {
    println()
  }
}

if (isDot) {
  val projectDot = homeDir / "bin" / "project.dot"
  projectDot.writeOver(toDot(project))
  println(s"Written $projectDot")
} else {
  println(JSON.fromProject(project, T))
}

