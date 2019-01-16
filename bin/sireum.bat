@echo off
java %JAVA_OPTS% -cp "%~dp0sireum.jar" org.sireum.Sireum %*
exit /B %errorlevel%
