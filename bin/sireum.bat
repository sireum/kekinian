@echo off
set SIREUM_HOME=%~dp0..\
set JAVA=java.exe
if defined SIREUM_PROVIDED_SCALA set SIREUM_PROVIDED_JAVA=true
if not defined SIREUM_PROVIDED_JAVA set JAVA=%~dp0win\java\bin\java.exe
if exist "%~dp0.sireum-win.jar" attrib -H "%~dp0.sireum-win.jar"
if exist "%~dp0.sireum-win.jar" del "%~dp0.sireum-win.jar" > nul 2>&1
copy /Y "%~dp0sireum.jar" "%~dp0.sireum-win.jar" > nul 2>&1
attrib +H "%~dp0.sireum-win.jar"
"%JAVA%" %JAVA_OPTS% -jar "%~dp0.sireum-win.jar" %*
exit /B %errorlevel%

:error
echo Failed with error #%errorlevel%.
exit /b %errorlevel%