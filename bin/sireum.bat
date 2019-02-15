@echo off
set SIREUM_HOME=%~dp0..\
set JAVA=java.exe
if not defined SIREUM_PROVIDED_JAVA set JAVA=%~dp0win\java\bin\java.exe
%JAVA% %JAVA_OPTS% -jar "%~dp0sireum.jar" %*
exit /B %errorlevel%
