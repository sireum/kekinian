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
export SIREUM_HOME=$( cd "$( dirname "$0" )"/.. &> /dev/null && pwd )
cd ${SIREUM_HOME}
source bin/platform.sh
if [[ "${PLATFORM}" == "win" ]]; then
  echo "Native build is not currently available for Windows"
  exit -1
elif [[ "${PLATFORM}" == "linux" ]]; then
  FLAGS=--static
else
  FLAGS=
fi
cp -a runtime/macros/shared/src/main/scala/org/sireum/\$internal/Macro.scala runtime/macros/shared/src/main/scala/org/sireum/\$internal/Macro.scala.orig
echo "" >> runtime/macros/shared/src/main/scala/org/sireum/\$internal/Macro.scala
SIREUM_NATIVE=true bin/build.sh
native-image ${FLAGS} --no-server -jar ${SIREUM_HOME}/bin/sireum.jar ${SIREUM_HOME}/bin/${PLATFORM}/sireum
rm -fR ${SIREUM_HOME}/bin/${PLATFORM}/sireum.o
mv runtime/macros/shared/src/main/scala/org/sireum/\$internal/Macro.scala.orig runtime/macros/shared/src/main/scala/org/sireum/\$internal/Macro.scala
