#!/bin/bash -e
rm -fR mill-standalone out
curl -Lo mill-standalone http://files.sireum.org/mill-standalone
chmod +x mill-standalone
git submodule update --init --recursive --remote
./mill-standalone -i all \
  cli.tests.compile \
  cli.assembly \
  runtime.library.shared.tests.test \
  runtime.library.jvm.tests.test \
  slang.parser.shared.tests.test \
  slang.frontend.shared.tests.test \
  tools.jvm.tests.test \
  alir.shared.tests.test