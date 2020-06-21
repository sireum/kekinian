#!/bin/sh -e
if [ "$(expr substr $(uname -s) 1 5)" = "Linux" ]; then
  if [ "$(uname -m)" = "aarch64" ]; then
    export SIREUM_HOME=$(cd -P $(dirname "$0")/.. && pwd -P)
    $SIREUM_HOME/bin/build.cmd native
    $SIREUM_HOME/bin/linux/arm/upx -9 bin/linux/arm/sireum
    $SIREUM_HOME/bin/linux/arm/sireum
  fi
fi
