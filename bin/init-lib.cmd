::/*#! 2> /dev/null                                     #
@ 2>/dev/null # 2>nul & echo off & goto BOF             #
git clone https://github.com/sireum/kekinian            #
cd kekinian                                             #
git submodule update --init bin/mac bin/linux bin/win   #
bin/init.sh                                             #
cd ..                                                   #
exec kekinian/bin/sireum slang run "$0" "$@"            #
:BOF
git clone https://github.com/sireum/kekinian
cd kekinian
git submodule update --init bin\mac bin\linux bin\win
call bin\init.bat
cd ..
kekinian\bin\sireum.bat slang run "%0" %*
exit /B %errorlevel%
::!#*/
// #Sireum
import org.sireum._

val sireumHome = Os.path(Sireum.homePathString)
proc"git checkout ${Sireum.commitHash}".console.at(sireumHome).runCheck()
proc"git submodule update --init --recursive".console.at(sireumHome).runCheck()
proc"${sireumHome / "bin" / "build.cmd"} m2-lib".console.at(sireumHome).runCheck()
