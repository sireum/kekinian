#!/bin/bash -e
if [ -n "$COMSPEC" -a -x "$COMSPEC" ]; then
  >&2 echo "Please use clean.bat"
  exit 1
elif [[ "$(uname)" == "Darwin" ]]; then
  rm -fR ~/.mill ~/Downloads/sireum ~/.m2/repository/org/sireum/kekinian ~/Library/Application\ Support/JetBrains/SireumIVE-dev ~/Library/Caches/JetBrains/SireumIVE-dev ~/Library/Application\ Support/JetBrains/SireumIVE-ult-dev ~/Library/Caches/JetBrains/SireumIVE-ult-dev ~/Library/Application\ Support/org.sireum ~/.sireum
elif [[ "$(expr substr $(uname -s) 1 5)" == "Linux" ]]; then
  rm -fR ~/.mill ~/Downloads/sireum ~/.m2/repository/org/sireum/kekinian ~/.SireumIVE-dev ~/.SireumIVE-ult-dev
else
  >&2 echo "Sireum does not support: $(uname)."
  exit 1
fi
git reset --hard
git submodule foreach --recursive 'git checkout -- . || :'
git submodule update --init --recursive
git clean -d -f -f -x
git submodule foreach --recursive git clean -d -f -f -x
