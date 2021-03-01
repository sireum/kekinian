#!/bin/sh -e
if [ -n "$COMSPEC" -a -x "$COMSPEC" ]; then
  >&2 echo "Please use clean.bat"
  exit 1
elif [[ "$(uname)" == "Darwin" ]]; then
  rm -fR ~/Downloads/sireum ~/Library/Application\ Support/JetBrains/SireumIVE-dev ~/Library/Caches/JetBrains/SireumIVE-dev
elif [[ "$(expr substr $(uname -s) 1 5)" == "Linux" ]]; then
  rm -fR ~/Downloads/sireum ~/.SireumIVE-dev
else
  >&2 echo "Sireum does not support: $(uname)."
  exit 1
fi
git reset --hard
git submodule foreach --recursive 'git checkout -- . || :'
git submodule update --init --recursive
git clean -d -f -f -x
git submodule foreach --recursive git clean -d -f -f -x
