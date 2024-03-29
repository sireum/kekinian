#!/bin/sh -e
if [ "$(expr substr $(uname -s) 1 5)" = "Linux" ]; then
  if [ "$(uname -m)" = "aarch64" ]; then
    export SIREUM_HOME=$(cd -P $(dirname "$0")/.. && pwd -P)
    $SIREUM_HOME/bin/init.sh
    $SIREUM_HOME/bin/install/graal.cmd
    $SIREUM_HOME/bin/build.cmd
    $SIREUM_HOME/bin/build.cmd -h
    $SIREUM_HOME/bin/sireum
  fi
fi
