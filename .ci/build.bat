@echo off
setlocal
set SIREUM_HOME=%~dp0..
set errorlevel=
%SIREUM_HOME%\bin\build.cmd %*
exit /b %errorlevel%