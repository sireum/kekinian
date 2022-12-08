@echo off
rmdir /q/s %USERPROFILE%\Downloads\sireum 2> nul
rmdir /q/s %USERPROFILE%\.SireumIVE-dev 2> nul
rmdir /q/s %USERPROFILE%\.SireumIVE-ult-dev 2> nul
rmdir /q/s %USERPROFILE%\.mill 2> nul
rmdir /q/s %USERPROFILE%\.m2\repository\org\sireum\kekinian 2> nul
rmdir /q/s %USERPROFILE%\AppData\Local\Sireum 2> nul
git reset --hard
git submodule foreach --recursive "git checkout -- . || :"
git submodule update --init --recursive
git clean -d -f -f -x
git submodule foreach --recursive "git clean -d -f -f -x"
