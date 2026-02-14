::/*#! 2> /dev/null                                   #
@ 2>/dev/null # 2>nul & echo off & goto BOF           #
if [ -z "${SIREUM_HOME}" ]; then                      #
  echo "Please set SIREUM_HOME env var"               #
  exit -1                                             #
fi                                                    #
exec "${SIREUM_HOME}/bin/sireum" slang run "$0" "$@"  #
:BOF
setlocal
if not defined SIREUM_HOME (
  echo Please set SIREUM_HOME env var
  exit /B -1
)
"%SIREUM_HOME%\bin\sireum.bat" slang run %0 %*
exit /B %errorlevel%
::!#*/
// #Sireum

import org.sireum._
import org.sireum.project.ProjectUtil._
import org.sireum.project.Project

val server = "server"

val cli = "cli"

val homeDir = Os.slashDir.up.canon

val cliJvm = moduleJvmPub(
  id = cli,
  baseDir = homeDir,
  jvmDeps = ISZ(server),
  jvmIvyDeps = ISZ(
    "io.modelcontextprotocol.sdk:mcp:",
    "org.slf4j:slf4j-nop:"
  ),
  pubOpt = pub(
    desc = "Sireum Command Line Interface (CLI)",
    url = "github.com/sireum/kekinian",
    licenses = bsd2,
    devs = ISZ(robby, jasonBelt)
  )
)

val project = Project.empty + cliJvm

projectCli(Os.cliArgs, project)