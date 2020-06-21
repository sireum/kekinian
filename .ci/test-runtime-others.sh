#!/bin/sh -e
export SIREUM_HOME=$(cd -P $(dirname "$0")/.. && pwd -P)
cd $SIREUM_HOME
bin/build.cmd
bin/mill all runtime.library.shared.tests runtime.library.jvm.tests alir.shared.tests slang.parser.shared.tests tools.jvm.tests