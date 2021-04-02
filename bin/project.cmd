::#! 2> /dev/null                                   #
@ 2>/dev/null # 2>nul & echo off & goto BOF         #
if [ -z ${SIREUM_HOME} ]; then                      #
  echo "Please set SIREUM_HOME env var"             #
  exit -1                                           #
fi                                                  #
exec ${SIREUM_HOME}/bin/sireum slang run "$0" "$@"  #
:BOF
setlocal
if not defined SIREUM_HOME (
  echo Please set SIREUM_HOME env var
  exit /B -1
)
%SIREUM_HOME%\bin\sireum.bat slang run -n "%0" %*
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
  homeDir / "proyek",
  homeDir / "server",
  homeDir / "cli"
)

var project = Project.empty

for (p <- projects) {
  val f = p / "bin" / "project.cmd"
  println(s"Loading $f ...")
  var prj = Project.empty

  val r = Os.proc(ISZ(f.string, "json")).run()
  if (r.ok) {
    projectJsonLine(r.out) match {
      case Some(line) =>
        org.sireum.project.JSON.toProject(line) match {
          case Either.Left(sp) => prj = sp
          case _ =>
        }
      case _ =>
    }
  } else {
    eprintln(s"Failed loading $f ...")
    Os.exit(-1)
  }
  project = project ++ prj
}

if (isDot) {
  val projectDot = homeDir / "project.dot"
  projectDot.writeOver(toDot(project))
  println(s"Written $projectDot")
} else {
  println(JSON.fromProject(project, T))
}

