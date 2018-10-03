#!/bin/bash -e
export SCRIPT_HOME=$( cd "$( dirname "$0" )" &> /dev/null && pwd )
rm -fR mill-standalone out
curl -Lo mill-standalone http://files.sireum.org/mill-standalone
chmod +x mill-standalone
git submodule update --init --recursive --remote
$SCRIPT_HOME/mill-standalone cli.assembly
out/cli/assembly/dest/out.jar slang tipe --verbose -r -s runtime/library:slang:tools:alir:cli
$SCRIPT_HOME/mill-standalone all \
  cli.tests.compile \
  runtime.library.shared.tests.test \
  runtime.library.jvm.tests.test \
  slang.parser.shared.tests.test \
  slang.frontend.shared.tests.test \
  tools.jvm.tests.test \
  alir.shared.tests.test
