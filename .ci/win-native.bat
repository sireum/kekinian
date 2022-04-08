@echo off
setlocal
set SIREUM_HOME=%~dp0..
set errorlevel=
call %SIREUM_HOME%\bin\build.cmd native || goto :error
REM %SIREUM_HOME%\bin\win\upx.exe -9 %SIREUM_HOME%\bin\win\sireum.exe || goto :error
REM call %SIREUM_HOME%\bin\sireum || goto :error
goto :EOF
:error
exit /b %errorlevel%