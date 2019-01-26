#!/bin/bash -e
SIREUM_HOME=$( cd "$( dirname "$0" )"/.. &> /dev/null && pwd )
exec ${SIREUM_HOME}/bin/sireum slang run $*