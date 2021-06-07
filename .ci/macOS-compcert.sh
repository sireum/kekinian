#!/bin/sh -e
if [ "$(uname)" = "Darwin" ]; then
  export SIREUM_HOME=$(cd -P $(dirname "$0")/.. && pwd -P)
  cd $SIREUM_HOME
  bin/init.sh
  bin/install/compcert.cmd
  export CC=`find $SIREUM_HOME/bin/mac -name ccomp`
  bin/sireum proyek test -n sireum-proyek --par --sha3 --packages org.sireum.transpilers.c .
fi
