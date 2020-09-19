#!/bin/bash -e
export SCRIPT_DIR=$( cd "$( dirname "$0" )" &> /dev/null && pwd )
cd ${SCRIPT_DIR}
docker build --rm -f ./Dockerfile -t sireum/ci:latest .
