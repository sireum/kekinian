#!/bin/sh -e
export SIREUM_HOME=$(cd -P $(dirname "$0")/.. && pwd -P)
$SIREUM_HOME/bin/build.cmd $*