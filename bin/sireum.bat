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
for %%f in ("%SIREUM_HOME%\lib\compiler-*.jar") do set "JVMCI_CP=%JVMCI_CP%;%%f"
for %%f in ("%SIREUM_HOME%\lib\truffle-compiler-*.jar") do set "JVMCI_CP=%JVMCI_CP%;%%f"
for %%f in ("%SIREUM_HOME%\lib\word-*.jar") do set "JVMCI_CP=%JVMCI_CP%;%%f"
for %%f in ("%SIREUM_HOME%\lib\collections-*.jar") do set "JVMCI_CP=%JVMCI_CP%;%%f"
for %%f in ("%SIREUM_HOME%\lib\jniutils-*.jar") do set "JVMCI_CP=%JVMCI_CP%;%%f"
for %%f in ("%SIREUM_HOME%\lib\nativeimage-*.jar") do set "JVMCI_CP=%JVMCI_CP%;%%f"
set "JVMCI_OPTS="
if defined JVMCI_CP set "JVMCI_OPTS=-XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI --sun-misc-unsafe-memory-access=allow --upgrade-module-path=%JVMCI_CP%"
set "SIREUM_BASE_CP=%SCALA_HOME%\lib\*;%~dp0.sireum-win.jar"
set "SIREUM_CP=%SIREUM_BASE_CP%%JVMCI_CP%"
set "SIREUM_BASE_OPTS=--enable-native-access=javafx.media --enable-native-access=javafx.graphics --enable-native-access=ALL-UNNAMED --sun-misc-unsafe-memory-access=allow -Djava.net.useSystemProxies=true %JAVA_OPTS%"
set "AOT_DIR=%SIREUM_HOME%\bin\.aot"
set "AOT_CACHE=%AOT_DIR%\sireum.aot"
set "AOT_JAR_HASH=%AOT_DIR%\sireum.jar.sha"
if "%SIREUM_NO_AOT%"=="true" goto no_aot
if not exist "%AOT_DIR%" mkdir "%AOT_DIR%"
set "CUR_HASH="
for /f "tokens=1" %%h in ('certutil -hashfile "%~dp0sireum.jar" SHA256 2^>nul ^| findstr /r "^[0-9a-f]"') do set "CUR_HASH=%%h"
set "OLD_HASH="
if exist "%AOT_JAR_HASH%" set /p OLD_HASH=<"%AOT_JAR_HASH%"
if not exist "%AOT_CACHE%" goto aot_train
if not "%CUR_HASH%"=="%OLD_HASH%" goto aot_train
goto aot_run
:aot_train
"%JAVA%" -XX:AOTCacheOutput="%AOT_CACHE%" %SIREUM_BASE_OPTS% -cp "%SIREUM_BASE_CP%" org.sireum.Sireum --version > "%AOT_DIR%\train.log" 2>&1
echo %CUR_HASH%> "%AOT_JAR_HASH%"
:aot_run
if exist "%AOT_CACHE%" (
  "%JAVA%" -XX:AOTCache="%AOT_CACHE%" -Xlog:aot=off %SIREUM_BASE_OPTS% -cp "%SIREUM_BASE_CP%" org.sireum.Sireum %*
  exit /B %errorlevel%
)
:no_aot
"%JAVA%" %SIREUM_BASE_OPTS% %JVMCI_OPTS% -cp "%SIREUM_CP%" org.sireum.Sireum %*
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
