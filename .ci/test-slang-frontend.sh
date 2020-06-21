#!/bin/sh -e
export SIREUM_HOME=$(cd -P $(dirname "$0")/.. && pwd -P)
cd $SIREUM_HOME
bin/mill slang.frontend.shared.tests