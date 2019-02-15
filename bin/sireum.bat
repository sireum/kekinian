@echo off
set JAVA=java.exe
if defined SIREUM_PROVIDED_JAVA set JAVA=%~dp0win\java\bin\java.exe
%JAVA% %JAVA_OPTS% -jar "%~dp0sireum.jar" %*
exit /B %errorlevel%
