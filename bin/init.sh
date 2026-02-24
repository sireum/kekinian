#!/bin/bash -e
#
# Copyright (c) 2017-2026,Robby, Kansas State University
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are met:
#
# 1. Redistributions of source code must retain the above copyright notice, this
#    list of conditions and the following disclaimer.
# 2. Redistributions in binary form must reproduce the above copyright notice,
#    this list of conditions and the following disclaimer in the documentation
#    and/or other materials provided with the distribution.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
# ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
# WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
# DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
# ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
# (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
# LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
# ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
# (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
#

: ${SIREUM_V:=master}
if [[ "${SIREUM_V}" == "master" ]]; then
  : ${SIREUM_INIT_V:=dev}
else
  if [[ -z "${SIREUM_INIT_V}" ]]; then
    if [[ ${SIREUM_V} == 4.* ]]; then
      SIREUM_INIT_V=${SIREUM_V}
    else
      SIREUM_INIT_V=4.${SIREUM_V}
    fi
  fi
fi

: ${SIREUM_CACHE:="$( cd ~ &> /dev/null && pwd )/Downloads/sireum"}
mkdir -p ${SIREUM_CACHE}


function versionNorm {
  printf "%03d%03d%03d%03d" $(echo "$1" | tr '.' ' ')
}

if [ -n "$COMSPEC" -a -x "$COMSPEC" ]; then
  if [[ -z "${PLATFORM}" ]]; then
    PLATFORM=win
  fi
elif [[ "$(uname)" == "Darwin" ]]; then
  if [[ -z "${PLATFORM}" ]]; then
    PLATFORM=mac
  fi
elif [[ "$(expr substr $(uname -s) 1 5)" == "Linux" ]]; then
  if [[ "$(uname -m)" == "aarch64" ]]; then
    if [[ -z "${PLATFORM}" ]]; then
      PLATFORM=linux/arm
    fi
  else
    if [[ -z "${PLATFORM}" ]]; then
      PLATFORM=linux
    fi
  fi
else
  >&2 echo "Sireum does not support: $(uname)."
  exit 1
fi

getVersion() {
  grep "^$1=" "${SIREUM_HOME}/versions.properties" | cut -d'=' -f2-
}

download() {
  if hash curl 2>/dev/null; then
    curl -c /dev/null -JLo "$1" $2
  elif hash wget 2>/dev/null; then
    wget -O "$1" $2
  else
    >&2 echo "Either curl or wget is required, but none found."
    exit 1
  fi
  if [[ ! -f "$1" ]]; then
      >&2 echo "Could not download $2"
      exit 1
  fi
}

uncompress() {
  if hash unzip 2>/dev/null; then
    unzip -qo $1
  elif hash 7z 2>/dev/null; then
    7z x -y $1 > /dev/null
  else
    >&2 echo "Either unzip or 7z is required, but none found."
    exit 1
  fi
}

#
# Sireum
#
SIREUM_HOME=$( cd "$( dirname "$0" )"/.. &> /dev/null && pwd )
cd "${SIREUM_HOME}"
if [[ ! -f bin/sireum.jar ]]; then
  echo "Please wait while downloading Sireum ..."
  download bin/sireum.jar https://github.com/sireum/kekinian/releases/download/${SIREUM_INIT_V}/sireum.jar
  chmod +x bin/sireum.jar
  echo
fi
if [[ ! -f bin/sireum ]]; then
  download bin/sireum https://raw.githubusercontent.com/sireum/kekinian/${SIREUM_V}/bin/sireum
  chmod +x bin/sireum
fi
if [[ ! -f versions.properties ]]; then
  download versions.properties https://raw.githubusercontent.com/sireum/kekinian/${SIREUM_V}/versions.properties
fi


#
# GraalVM JVMCI
#
GRAAL_VERSION=$(getVersion "org.graalvm.compiler%compiler%")
mkdir -p lib
for _mvn in \
  "org.graalvm.sdk/collections" \
  "org.graalvm.compiler/compiler" \
  "org.graalvm.sdk/jniutils" \
  "org.graalvm.sdk/nativeimage" \
  "org.graalvm.truffle/truffle-compiler" \
  "org.graalvm.sdk/word"; do
  _group="${_mvn%%/*}"
  _artifact="${_mvn##*/}"
  _group_path="${_group//.//}"
  if [[ ! -f "lib/${_artifact}-${GRAAL_VERSION}.jar" ]]; then
    rm -f lib/"${_artifact}"-*.jar
    echo "Please wait while downloading ${_artifact}-${GRAAL_VERSION}.jar ..."
    download "lib/${_artifact}-${GRAAL_VERSION}.jar" \
      "https://repo1.maven.org/maven2/${_group_path}/${_artifact}/${GRAAL_VERSION}/${_artifact}-${GRAAL_VERSION}.jar"
    echo
  fi
done


#
# Java
#
if [[ -n ${SIREUM_PROVIDED_JAVA} ]]; then
  exit
fi
JAVA_NAME="JDK"
if [[ -z ${JAVA_VERSION} ]]; then
  JAVA_VERSION=$(getVersion "org.sireum.version.java")
fi
if [[ "${PLATFORM}" == "mac" ]]; then
  if [[ "$(uname -m)" == "arm64" ]]; then
    JAVA_DROP_URL=https://download.bell-sw.com/java/${JAVA_VERSION}/bellsoft-jdk${JAVA_VERSION}-macos-aarch64-full.tar.gz
  else
    JAVA_DROP_URL=https://download.bell-sw.com/java/${JAVA_VERSION}/bellsoft-jdk${JAVA_VERSION}-macos-amd64-full.tar.gz
  fi
elif [[ "${PLATFORM}" == "linux/arm" ]]; then
  JAVA_DROP_URL=https://download.bell-sw.com/java/${JAVA_VERSION}/bellsoft-jdk${JAVA_VERSION}-linux-aarch64-full.tar.gz
elif [[ "${PLATFORM}" == "linux" ]]; then
  JAVA_DROP_URL=https://download.bell-sw.com/java/${JAVA_VERSION}/bellsoft-jdk${JAVA_VERSION}-linux-amd64-full.tar.gz
elif [[ "${PLATFORM}" == "win" ]]; then
  if [[ "${PROCESSOR_ARCHITECTURE}" == "ARM64" ]]; then
    JAVA_DROP_URL=https://download.bell-sw.com/java/${JAVA_VERSION}/bellsoft-jdk${JAVA_VERSION}-windows-aarch64-full.zip
  else
    JAVA_DROP_URL=https://download.bell-sw.com/java/${JAVA_VERSION}/bellsoft-jdk${JAVA_VERSION}-windows-amd64-full.zip
  fi
fi
mkdir -p "${SIREUM_HOME}/bin/${PLATFORM}"
cd "${SIREUM_HOME}/bin/${PLATFORM}"
JAVA_DROP="${JAVA_DROP_URL##*/}"
if [[ "${PLATFORM}" == "mac" ]]; then
  JAVA_DIR="jdk-${JAVA_VERSION%+*}-full.jdk"
else
  JAVA_DIR="jdk-${JAVA_VERSION%+*}-full"
fi
grep -q ${JAVA_VERSION} java/VER &> /dev/null && JAVA_UPDATE=false || JAVA_UPDATE=true
if [[ ! -d "java" ]] || [[ "${JAVA_UPDATE}" = "true" ]]; then
  if [[ ! -f "${SIREUM_CACHE}/${JAVA_DROP}" ]]; then
      echo "Please wait while downloading ${JAVA_NAME} ${JAVA_VERSION} ..."
      download "${SIREUM_CACHE}/${JAVA_DROP}" ${JAVA_DROP_URL}
  fi
  echo "Extracting ${JAVA_NAME} ${JAVA_VERSION} ..."
  if [[ ${JAVA_DROP} == *.tar.gz ]]; then
    tar xf "${SIREUM_CACHE}/${JAVA_DROP}"
  else
    uncompress "${SIREUM_CACHE}/${JAVA_DROP}"
  fi
  rm -fR java
  mv "${JAVA_DIR}" java
  echo
  if [[ -d "java/bin" ]]; then
    chmod +x java/bin/*
    chmod -fR u+w java
    echo "${JAVA_VERSION}" > java/VER
  else
    >&2 echo "Could not install ${JAVA_NAME} ${JAVA_VERSION}."
    exit 1
  fi
fi


#
# Setup
#
if [[ ! -f ${SIREUM_HOME}/bin/build.cmd ]] && [[ ! "${SIREUM_NO_SETUP}" = "true" ]]; then
  export PATH=${SIREUM_HOME}/bin/${PLATFORM}/java/bin:$PATH
  java -jar "${SIREUM_HOME}/bin/sireum.jar" setup ive
fi