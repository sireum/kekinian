#!/bin/bash # 2>nul & echo off & goto BOF
SIREUM_SCRIPT=$(cd -P "$(dirname "$0")" && pwd -P) && SIREUM_SCRIPT=$SIREUM_SCRIPT/$(basename "$0")                                                                                                    #
while [[ -h "$SIREUM_SCRIPT" ]]; do DIR=$(dirname "$SIREUM_SCRIPT"); SYM=$(readlink "$SIREUM_SCRIPT"); SIREUM_SCRIPT=$(cd "$DIR" && cd "$(dirname "$SYM")" && pwd)/$(basename "$SYM"); done            #
export SIREUM_HOME=$(cd -P "$(dirname "$SIREUM_SCRIPT")/.." && pwd -P)                                                                                                                                 #
if [[ -n ${SIREUM_PROVIDED_SCALA} ]]; then                                                                                                                                                             #
  SIREUM_PROVIDED_JAVA=true                                                                                                                                                                            #
fi                                                                                                                                                                                                     #
JAVA=java                                                                                                                                                                                              #
if [ -n "$COMSPEC" -a -x "$COMSPEC" ]; then                                                                                                                                                            #
  PLATFORM="win"                                                                                                                                                                                       #
  export SIREUM_HOME=$(cygpath -C OEM -w -a ${SIREUM_HOME})                                                                                                                                            #
  SIREUM_JAR="${SIREUM_HOME}\\bin\\sireum.jar"                                                                                                                                                         #
  if [[ -z ${SIREUM_PROVIDED_JAVA} ]]; then                                                                                                                                                            #
    export JAVA_HOME="${SIREUM_HOME}/bin/win/java"                                                                                                                                                     #
    JAVA="${JAVA_HOME}/bin/java.exe"                                                                                                                                                                   #
  fi                                                                                                                                                                                                   #
elif [[ "$(uname)" == "Darwin" ]]; then                                                                                                                                                                #
  PLATFORM="mac"                                                                                                                                                                                       #
  SIREUM_JAR="${SIREUM_HOME}/bin/sireum.jar"                                                                                                                                                           #
  if [[ -z ${SIREUM_PROVIDED_JAVA} ]]; then                                                                                                                                                            #
    export JAVA_HOME="${SIREUM_HOME}/bin/mac/java"                                                                                                                                                     #
    JAVA="${JAVA_HOME}/bin/java"                                                                                                                                                                       #
  fi                                                                                                                                                                                                   #
elif [[ "$(expr substr $(uname -s) 1 5)" == "Linux" ]]; then                                                                                                                                           #
  if [[ "$(uname -m)" == "aarch64" ]]; then                                                                                                                                                            #
    PLATFORM="linux/arm"                                                                                                                                                                               #
  else                                                                                                                                                                                                 #
    PLATFORM="linux"                                                                                                                                                                                   #
  fi                                                                                                                                                                                                   #
  SIREUM_JAR="${SIREUM_HOME}/bin/sireum.jar"                                                                                                                                                           #
  if [[ -z ${SIREUM_PROVIDED_JAVA} ]]; then                                                                                                                                                            #
    export JAVA_HOME="${SIREUM_HOME}/bin/${PLATFORM}/java"                                                                                                                                             #
    JAVA="${JAVA_HOME}/bin/java"                                                                                                                                                                       #
  fi                                                                                                                                                                                                   #
  if [[ -d "${SIREUM_HOME}/lib/$PLATFORM" ]]; then                                                                                                                                                     #
    export LD_LIBRARY_PATH=${SIREUM_HOME}/lib/$PLATFORM:$LD_LIBRARY_PATH                                                                                                                               #
  fi                                                                                                                                                                                                   #
fi                                                                                                                                                                                                     #
if [[ -z ${SIREUM_PROVIDED_SCALA} ]]; then                                                                                                                                                             #
  export SCALA_HOME=$SIREUM_HOME/bin/scala                                                                                                                                                             #
fi                                                                                                                                                                                                     #
exec "${JAVA}" --enable-native-access=javafx.media --enable-native-access=javafx.graphics --enable-native-access=ALL-UNNAMED -Djava.net.useSystemProxies=true ${JAVA_OPTS} -jar "${SIREUM_JAR}" --mcp  #
:BOF
setlocal
set SIREUM_HOME=%~dp0..\
pushd %SIREUM_HOME%
set SIREUM_HOME=%CD%
popd
set JAVA_HOME=%SIREUM_HOME%\bin\win\java
set NEWER=False
set JAVA=java.exe
if defined SIREUM_PROVIDED_SCALA set SIREUM_PROVIDED_JAVA=true
if not defined SIREUM_PROVIDED_SCALA set SCALA_HOME=%SIREUM_HOME%/bin/scala
if not defined SIREUM_PROVIDED_JAVA set JAVA=%JAVA_HOME%\bin\java.exe
copy /Y "%~dp0sireum.jar" "%~dp0.sireum-win.jar" > nul 2>&1
"%JAVA%" --enable-native-access=javafx.media --enable-native-access=javafx.graphics --enable-native-access=ALL-UNNAMED -Djava.net.useSystemProxies=true %JAVA_OPTS% -jar "%~dp0.sireum-win.jar" --mcp
exit /B %errorlevel%
