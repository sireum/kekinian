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
COMMANDS="git zip unzip curl"
for COMMAND in ${COMMANDS}; do
	type -P ${COMMAND} &>/dev/null && continue || { >&2 echo "${COMMAND} command not found."; exit 1; }
done
SIREUM_HOME=$( cd "$( dirname "$0" )"/.. &> /dev/null && pwd )
: ${ZULU_VERSION:=11.2.3-jdk11.0.1}
: ${SCALA_VERSION=2.12.8}
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
  if [[ ! -f ${ZULU_DROP} ]]; then
    if [[ -f ${SIREUM_CACHE}/${ZULU_DROP} ]]; then
      echo "Using ${SIREUM_CACHE}/${ZULU_DROP} ... "
      ln -s ${SIREUM_CACHE}/${ZULU_DROP}
      echo
    else
      echo "Please wait while downloading Zulu JDK ${ZULU_VERSION} ..."
      curl -qJLo ${ZULU_DROP} ${ZULU_DROP_URL}
      echo
      if [[ ! -z ${SIREUM_CACHE} ]]; then
        echo "Copying to ${SIREUM_CACHE}/${ZULU_DROP} ..."
        cp ${ZULU_DROP} ${SIREUM_CACHE}/${ZULU_DROP}
        echo
      fi
    fi
  fi
  if [[ ${ZULU_DROP} == *.zip ]]; then
    unzip -oq ${ZULU_DROP}
  else
    tar xf ${ZULU_DROP}
  fi
  rm ${ZULU_DROP}
  rm -fR java
  mv ${ZULU_DIR} java
  if [[ -d "java/bin" ]]; then
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
  if [[ ! -f ${SCALA_DROP} ]]; then
    if [[ -f ${SIREUM_CACHE}/${SCALA_DROP} ]]; then
      echo "Using ${SIREUM_CACHE}/${SCALA_DROP} ... "
      ln -s ${SIREUM_CACHE}/${SCALA_DROP}
      echo
    else
      echo "Please wait while downloading Scala ${SCALA_VERSION} ..."
      curl -qJLo ${SCALA_DROP} ${SCALA_DROP_URL}
      echo
      if [[ ! -z ${SIREUM_CACHE} ]]; then
        echo "Copying to ${SIREUM_CACHE}/${SCALA_DROP} ..."
        cp ${SCALA_DROP} ${SIREUM_CACHE}/${SCALA_DROP}
        echo
      fi
    fi
  fi
  rm -fR scala
  unzip -oq ${SCALA_DROP}
  rm ${SCALA_DROP}
  mv scala-${SCALA_VERSION} scala
  if [[ -d "scala/bin" ]]; then
    echo "${SCALA_VERSION}" > scala/VER
  else
    >&2 echo "Could not install Scala ${SCALA_VERSION}."
    exit 1
  fi
fi
${SIREUM_HOME}/bin/mill/build-standalone.sh