#!/bin/sh -e
if [ "$(expr substr $(uname -s) 1 5)" = "Linux" ]; then
  export SIREUM_HOME=$(cd -P $(dirname "$0")/.. && pwd -P)
  cd $SIREUM_HOME
  bin/build.cmd
  bin/install/compcert.cmd
  export CC=`find $SIREUM_HOME/bin/linux -name ccomp`
  bin/sireum proyek test -n sireum-proyek --par --sha3 --packages org.sireum.transpilers.c .
fi
