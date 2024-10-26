@echo off
setlocal
set SIREUM_HOME=%~dp0..
set errorlevel=
call %SIREUM_HOME%\bin\build.cmd || goto :error
del %SIREUM_HOME%\bin\.sireum-win.jar || goto :error
call %SIREUM_HOME%\bin\build.cmd native || goto :error
call %SIREUM_HOME%\bin\build.cmd -h || goto :error
call %SIREUM_HOME%\bin\sireum.bat || goto :error
goto :EOF
:error
exit /b %errorlevel%