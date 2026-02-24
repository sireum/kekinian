@echo off
setlocal
set SIREUM_HOME=%~dp0..\
pushd %SIREUM_HOME%
set SIREUM_HOME=%CD%
popd
set JAVA_HOME=%SIREUM_HOME%\bin\win\java
set PROYEK_JFX=%JAVA_HOME%\lib\javafx.properties
set NEWER=False
if "%SIREUM_NATIVE%"=="false" goto jar
if exist "%~dp0win\sireum.exe" goto native
:jar
set JAVA=java.exe
if defined SIREUM_PROVIDED_SCALA set SIREUM_PROVIDED_JAVA=true
if not defined SIREUM_PROVIDED_SCALA set SCALA_HOME=%SIREUM_HOME%/bin/scala
if not defined SIREUM_PROVIDED_JAVA set JAVA=%JAVA_HOME%\bin\java.exe
copy /Y "%~dp0sireum.jar" "%~dp0.sireum-win.jar" > nul 2>&1
set "JVMCI_CP="
for %%f in ("%SIREUM_HOME%\lib\collections-*.jar") do set "JVMCI_CP=%JVMCI_CP%;%%f"
for %%f in ("%SIREUM_HOME%\lib\compiler-*.jar") do set "JVMCI_CP=%JVMCI_CP%;%%f"
for %%f in ("%SIREUM_HOME%\lib\jniutils-*.jar") do set "JVMCI_CP=%JVMCI_CP%;%%f"
for %%f in ("%SIREUM_HOME%\lib\nativeimage-*.jar") do set "JVMCI_CP=%JVMCI_CP%;%%f"
for %%f in ("%SIREUM_HOME%\lib\truffle-compiler-*.jar") do set "JVMCI_CP=%JVMCI_CP%;%%f"
for %%f in ("%SIREUM_HOME%\lib\word-*.jar") do set "JVMCI_CP=%JVMCI_CP%;%%f"
"%JAVA%" --enable-native-access=javafx.media --enable-native-access=javafx.graphics --enable-native-access=ALL-UNNAMED -Djava.net.useSystemProxies=true %JAVA_OPTS% -cp "%SCALA_HOME%\lib\*;%~dp0.sireum-win.jar%JVMCI_CP%" org.sireum.Sireum %*
exit /B %errorlevel%

:native
if exist "%~dp0win\.sireum.exe" for /f %%i in ('dir /b /o:d "%~dp0win\.sireum.exe" "%~dp0win\sireum.exe"') do goto native-run
copy /Y "%~dp0win\sireum.exe" "%~dp0win\.sireum.exe" > nul 2>&1
goto native-run

:native-run
"%~dp0win\.sireum.exe" %*
exit /b %errorlevel%

:error
echo Failed with error #%errorlevel%.
exit /b %errorlevel%
