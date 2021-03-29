::#! 2> /dev/null                                             #
@ 2>/dev/null # 2>nul & echo off & goto BOF                   #
if [ -f "$0.com" ] && [ "$0.com" -nt "$0" ]; then             #
  exec "$0.com" "$@"                                          #
fi                                                            #
rm -f "$0.com"                                                #
if [ -z ${SIREUM_HOME} ]; then                                #
  echo "Please set SIREUM_HOME env var"                       #
  exit -1                                                     #
fi                                                            #
exec ${SIREUM_HOME}/bin/sireum slang run "$0" "$@"            #
:BOF
setlocal
if not defined SIREUM_HOME (
  echo Please set SIREUM_HOME env var
  exit /B -1
)
set NEWER=False
if exist %~dpnx0.com for /f %%i in ('powershell -noprofile -executionpolicy bypass -command "(Get-Item %~dpnx0.com).LastWriteTime -gt (Get-Item %~dpnx0).LastWriteTime"') do @set NEWER=%%i
if "%NEWER%" == "True" goto native
del "%~dpnx0.com" > nul 2>&1
%SIREUM_HOME%\bin\sireum.bat slang run "%0" %*
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
  if (isDot) {
    println(s"Loading $f ...")
  }
  val proc = Os.proc(ISZ(f.string, "json"))
  val r: Os.Proc.Result = if (isDot) proc.console.runCheck() else proc.runCheck()
  val prj = org.sireum.project.JSON.toProject(ops.StringOps(r.out).split((c: C) => c === '\n')(0)).left
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

