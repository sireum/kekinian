#!/bin/sh -e
if [ "$(uname)" = "Darwin" ]; then
  export SIREUM_HOME=$(cd -P $(dirname "$0")/.. && pwd -P)
  $SIREUM_HOME/bin/build.cmd native
  $SIREUM_HOME/bin/build.cmd -h
  $SIREUM_HOME/bin/mac/sireum
fi
