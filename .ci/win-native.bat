@echo off
setlocal
set SIREUM_HOME=%~dp0..
call %SIREUM_HOME%\bin\build.cmd native
%SIREUM_HOME%\bin\win\upx.exe -9 %SIREUM_HOME%\bin\win\sireum.exe
%SIREUM_HOME%\bin\sireum
