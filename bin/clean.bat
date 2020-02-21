@echo off
rmdir /q/s %USERPROFILE%\Downloads\sireum 2> nul
git reset --hard
git submodule foreach --recursive "git checkout -- . || :"
git submodule update --init --recursive
git clean -d -f -f -x
git submodule foreach --recursive "git clean -d -f -f -x"
