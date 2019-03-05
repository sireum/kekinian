#!/bin/bash -e
export SCRIPT_DIR=$( cd "$( dirname "$0" )" &> /dev/null && pwd )
cd ${SCRIPT_DIR}
docker build --rm -f ./Dockerfile-ci-compcert -t sireum/ci-compcert:latest .
