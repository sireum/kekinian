@echo off
java %JAVA_OPTS% -jar "%~dp0sireum.jar" %*
exit /B %errorlevel%
