#!/bin/bash -e
VER=$(git log -n 1 --date=format:%Y%m%d --pretty=format:4.%cd.%h)
echo "Tagging ${VER}"
git tag ${VER}
git push origin ${VER}