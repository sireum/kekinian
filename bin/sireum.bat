@echo off
%~dp0win\java\bin\java.exe %JAVA_OPTS% -jar "%~dp0sireum.jar" %*
exit /B %errorlevel%
