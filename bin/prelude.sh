#!/bin/bash -e
#
# Copyright (c) 2019, Robby, Kansas State University
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
COMMANDS="git curl 7z"
for COMMAND in ${COMMANDS}; do
	type -P ${COMMAND} &>/dev/null && continue || { >&2 echo "${COMMAND} command not found."; exit 1; }
done
SIREUM_HOME=$( cd "$( dirname "$0" )"/.. &> /dev/null && pwd )
getVersion() {
  grep "^org.sireum.version.$1=" ${SIREUM_HOME}/versions.properties | cut -d'=' -f2-
}
: ${SIREUM_CACHE:="$( cd ~ &> /dev/null && pwd )/Downloads/sireum"}
mkdir -p ${SIREUM_CACHE}
: ${ZULU_VERSION:=$(getVersion "zulu")}
: ${SCALA_VERSION=$(getVersion "scala")}
SCALA_MAJ_VER=${SCALA_VERSION%.*}
SCALAC_PLUGIN_VER=$(getVersion "scalac-plugin")
cd ${SIREUM_HOME}/bin
source platform.sh
if [[ "${PLATFORM}" = "win"  ]]; then
  ZULU_DROP_URL=http://cdn.azul.com/zulu/bin/zulu${ZULU_VERSION}-win_x64.zip
elif [[ "${PLATFORM}" = "mac"  ]]; then
  ZULU_DROP_URL=http://cdn.azul.com/zulu/bin/zulu${ZULU_VERSION}-macosx_x64.zip
elif [[ "${PLATFORM}" = "linux"  ]]; then
  type -P xz &>/dev/null || { >&2 echo "xz command not found."; exit 1; }
  ZULU_DROP_URL=http://cdn.azul.com/zulu/bin/zulu${ZULU_VERSION}-linux_x64.tar.gz
else
  >&2 echo "Sireum does not support: $(uname)."
  exit 1
fi
mkdir -p ${PLATFORM}
cd ${PLATFORM}
ZULU_DROP="${ZULU_DROP_URL##*/}"
ZULU_DIR="${ZULU_DROP%.*}"
if [[ ${ZULU_DIR} == *.tar ]]; then
  ZULU_DIR="${ZULU_DIR%.*}"
fi
grep -q ${ZULU_VERSION} java/VER &> /dev/null && ZULU_UPDATE=false || ZULU_UPDATE=true
if [[ ! -d "java" ]] || [[ "${ZULU_UPDATE}" = "true" ]]; then
  if [[ ! -f ${SIREUM_CACHE}/${ZULU_DROP} ]]; then
      echo "Please wait while downloading Zulu JDK ${ZULU_VERSION} ..."
      curl -JLso ${SIREUM_CACHE}/${ZULU_DROP} ${ZULU_DROP_URL}
      echo
  fi
  if [[ ${ZULU_DROP} == *.tar.gz ]]; then
    7z x -so ${SIREUM_CACHE}/${ZULU_DROP} | 7z x -y -si -ttar > /dev/null
  else
    7z x -y ${SIREUM_CACHE}/${ZULU_DROP} > /dev/null
  fi
  rm -fR java
  mv ${ZULU_DIR} java
  if [[ -d "java/bin" ]]; then
    chmod +x java/bin/*
    echo "${ZULU_VERSION}" > java/VER
  else
    >&2 echo "Could not install Zulu JDK ${ZULU_VERSION}."
    exit 1
  fi
fi
cd ${SIREUM_HOME}/bin
SCALA_DROP_URL=http://downloads.lightbend.com/scala/${SCALA_VERSION}/scala-${SCALA_VERSION}.zip
SCALA_DROP="${SCALA_DROP_URL##*/}"
grep -q ${SCALA_VERSION} scala/VER &> /dev/null && SCALA_UPDATE=false || SCALA_UPDATE=true
if [[ ! -d "scala" ]] || [[ "${SCALA_UPDATE}" = "true" ]]; then
  if [[ ! -f ${SIREUM_CACHE}/${SCALA_DROP} ]]; then
    echo "Please wait while downloading Scala ${SCALA_VERSION} ..."
    curl -JLso ${SIREUM_CACHE}/${SCALA_DROP} ${SCALA_DROP_URL}
    echo
  fi
  7z x -y ${SIREUM_CACHE}/${SCALA_DROP} > /dev/null
  rm -fR scala
  mv scala-${SCALA_VERSION} scala
  if [[ -d "scala/bin" ]]; then
    echo "${SCALA_VERSION}" > scala/VER
    chmod +x scala/bin/*
  else
    >&2 echo "Could not install Scala ${SCALA_VERSION}."
    exit 1
  fi
fi
mkdir -p ${SIREUM_HOME}/lib
cd ${SIREUM_HOME}/lib
SCALAC_PLUGIN_DROP=scalac-plugin-${SCALAC_PLUGIN_VER}.jar
SCALAC_PLUGIN_DROP_URL=https://oss.sonatype.org/service/local/repositories/releases/content/org/sireum/scalac-plugin_${SCALA_MAJ_VER}/${SCALAC_PLUGIN_VER}/scalac-plugin_${SCALA_MAJ_VER}-${SCALAC_PLUGIN_VER}.jar
if [[ ! -f ${SIREUM_CACHE}/${SCALAC_PLUGIN_DROP} ]]; then
  echo "Please wait while downloading Slang scalac plugin ${SCALAC_PLUGIN_VER} ..."
  curl -JLso ${SIREUM_CACHE}/${SCALAC_PLUGIN_DROP} ${SCALAC_PLUGIN_DROP_URL}
  echo
fi
rm -f scalac-plugin-*.jar
cp ${SIREUM_CACHE}/${SCALAC_PLUGIN_DROP} .
cd ..
bin/mill-build/build-standalone.sh
