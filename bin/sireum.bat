@echo off
setlocal
set SIREUM_HOME=%~dp0..\
set JAVA_HOME=%~dp0win\java
set NEWER=False
if exist %~dp0win\sireum.exe for /f %%i in ('powershell -noprofile -executionpolicy bypass -command "(Get-Item %~dp0win\sireum.exe).LastWriteTime -gt (Get-Item %~dp0sireum.jar).LastWriteTime"') do @set NEWER=%%i
if "%NEWER%" == "True" goto native
del "%~dp0win\sireum.exe" > nul 2>&1
del "%~dp0win\.sireum.exe" > nul 2>&1
set JAVA=java.exe
if defined SIREUM_PROVIDED_SCALA set SIREUM_PROVIDED_JAVA=true
if not defined SIREUM_PROVIDED_JAVA set JAVA=%JAVA_HOME%\bin\java.exe
copy /Y "%~dp0sireum.jar" "%~dp0.sireum-win.jar" > nul 2>&1
"%JAVA%" %JAVA_OPTS% -jar "%~dp0.sireum-win.jar" %*
exit /B %errorlevel%

:native
set NEWER=False
if exist %~dp0win\.sireum.exe for /f %%i in ('powershell -noprofile -executionpolicy bypass -command "(Get-Item %~dp0win\.sireum.exe).LastWriteTime -gt (Get-Item %~dp0win\sireum.exe).LastWriteTime"') do @set NEWER=%%i
if "%NEWER%" == "True" goto native-run
copy /Y "%~dp0win\sireum.exe" "%~dp0win\.sireum.exe" > nul 2>&1
goto native-run

:native-run
%~dp0win\.sireum.exe %*
exit /b %errorlevel%

:error
echo Failed with error #%errorlevel%.
exit /b %errorlevel%