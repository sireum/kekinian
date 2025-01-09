::/*#! 2> /dev/null                                                                                                                                                                         #
@ 2>/dev/null # 2>nul & echo off & goto BOF                                                                                                                                                 #
set -e                                                                                                                                                                                      #
: "${SIREUM_V:=dev}"                                                                                                                                                                        #
: "${DISTRO:=ive}"                                                                                                                                                                          #
: "${DIR:=$HOME/Applications/Sireum}"                                                                                                                                                       #
PDIR=$(dirname "$DIR")                                                                                                                                                                      #
if [ "$DISTRO" != "cli" ] && [ "$DISTRO" != "codeive" ] && [ "$DISTRO" != "ive" ]; then                                                                                                     #
  echo "DISTRO has to be either cli, codeive, or ive"                                                                                                                                       #
  exit -3                                                                                                                                                                                   #
fi                                                                                                                                                                                          #
if [ -n "$COMSPEC" -a -x "$COMSPEC" ]; then                                                                                                                                                 #
  echo "Please execute this script in a cmd terminal"                                                                                                                                       #
  exit -2                                                                                                                                                                                   #
elif [ "$(uname)" = "Darwin" ]; then                                                                                                                                                        #
  OS=mac                                                                                                                                                                                    #
  if [ "$(uname -m)" = "arm64" ]; then                                                                                                                                                      #
    ARCH=arm64                                                                                                                                                                              #
  else                                                                                                                                                                                      #
    ARCH=amd64                                                                                                                                                                              #
  fi                                                                                                                                                                                        #
elif [ "$(expr substr $(uname -s) 1 5)" = "Linux" ]; then                                                                                                                                   #
  OS=linux                                                                                                                                                                                  #
  if [ "$(uname -m)" = "aarch64" ]; then                                                                                                                                                    #
    ARCH=arm64                                                                                                                                                                              #
    echo "Unsupported Linux architecture aarch64"                                                                                                                                           #
    exit -1                                                                                                                                                                                 #
  else                                                                                                                                                                                      #
    ARCH=amd64                                                                                                                                                                              #
  fi                                                                                                                                                                                        #
fi                                                                                                                                                                                          #
rm -fR $DIR ~/Downloads/sireum-$DISTRO-$OS-$ARCH-$SIREUM_V.tar.xz ~/Downloads/org.sireum.m2-$SIREUM_V.zip                                                                                   #
rm -f ~/Downloads/sireum-$DISTRO-$OS-$ARCH-$SIREUM_V.tar.xz ~/Downloads/org.sireum.m2-$SIREUM_V.zip                                                                                         #
mkdir -p $PDIR ~/Downloads                                                                                                                                                                  #
echo "Please wait while downloading https://github.com/sireum/kekinian/releases/download/$SIREUM_V/sireum-$DISTRO-$OS-$ARCH.tar.xz to $HOME/Downloads ..."                                  #
curl -JLso ~/Downloads/sireum-$DISTRO-$OS-$ARCH-$SIREUM_V.tar.xz https://github.com/sireum/kekinian/releases/download/$SIREUM_V/sireum-$DISTRO-$OS-$ARCH.tar.xz                             #
echo ""                                                                                                                                                                                     #
echo "Please wait while downloading https://github.com/sireum/kekinian/releases/download/$SIREUM_V/org.sireum.m2.zip to $HOME/Downloads ..."                                                #
curl -JLso ~/Downloads/org.sireum.m2-$SIREUM_V.zip https://github.com/sireum/kekinian/releases/download/$SIREUM_V/org.sireum.m2.zip                                                         #
echo ""                                                                                                                                                                                     #
cd "$PDIR"                                                                                                                                                                                  #
echo "Extracting sireum-$DISTRO-$OS-$ARCH-$SIREUM_V.tar.xz ..."                                                                                                                             #
tar xf ~/Downloads/sireum-$DISTRO-$OS-$ARCH-$SIREUM_V.tar.xz                                                                                                                                #
echo ""                                                                                                                                                                                     #
cd ~                                                                                                                                                                                        #
echo "Extracting org.sireum.m2-$SIREUM_V.zip ..."                                                                                                                                           #
if [ "$OS" = "mac" ]; then                                                                                                                                                                  #
  tar xf ~/Downloads/org.sireum.m2-$SIREUM_V.zip                                                                                                                                            #
else                                                                                                                                                                                        #
  unzip -qqo ~/Downloads/org.sireum.m2-$SIREUM_V.zip                                                                                                                                        #
fi                                                                                                                                                                                          #
echo ""                                                                                                                                                                                     #
echo "Sireum is installed at $DIR"                                                                                                                                                          #
if [ "$DISTRO" = "cli" ]; then                                                                                                                                                              #
  exit 0                                                                                                                                                                                    #
fi                                                                                                                                                                                          #
if [ "$OS" = "linux" ]; then                                                                                                                                                                #
  if [ "$ARCH" = "arm64" ]; then                                                                                                                                                            #
    echo "CodeIVE can be launched as follow: $DIR/bin/linux/arm/vscodium/bin/codeive"                                                                                                       #
    if [ "$DISTRO" = "ive" ]; then                                                                                                                                                          #
      echo "IVE can be launched as follow: $DIR/bin/linux/arm/idea/bin/IVE.sh"                                                                                                              #
    fi                                                                                                                                                                                      #
  else                                                                                                                                                                                      #
    echo "CodeIVE can be launched as follow: $DIR/bin/linux/vscodium/bin/codeive"                                                                                                           #
    if [ "$DISTRO" = "ive" ]; then                                                                                                                                                          #
      echo "IVE can be launched as follow: $DIR/bin/linux/idea/bin/IVE.sh"                                                                                                                  #
    fi                                                                                                                                                                                      #
  fi                                                                                                                                                                                        #
else                                                                                                                                                                                        #
    echo "CodeIVE can be launched as follow: open $DIR/bin/mac/vscodium/CodeIVE.app"                                                                                                        #
    if [ "$DISTRO" = "ive" ]; then                                                                                                                                                          #
      echo "IVE can be launched as follow: open $DIR/bin/mac/idea/IVE.app"                                                                                                                  #
    fi                                                                                                                                                                                      #
fi                                                                                                                                                                                          #
exit 0                                                                                                                                                                                      #
:BOF
setlocal
set errorlevel=
set "P7ZZ_V=24.08"
set "COSMOCC_V=4.0.2"
if not defined SIREUM_V set "SIREUM_V=dev"
if not defined DISTRO set "DISTRO=ive"
if not defined DIR set "DIR=%USERPROFILE%\Applications\Sireum"
  for %%a in ("%DIR%") do set "PDIR=%%~dpa"
if not "%DISTRO%" == "cli" if not "%DISTRO%" == "codeive" if not "%DISTRO%" == "ive" echo DISTRO has to be either cli, codeive, or ive && exit /b -3
set "ARCH=amd64"
if "%PROCESSOR_ARCHITECTURE%" == "ARM64" set "ARCH=arm64" && echo Unsupported Windows architecture ARM64 && exit /b -2
rmdir /s/q "%DIR%" 2> null
if exist %USERPROFILE%\Downloads\sireum-%DISTRO%-win-%ARCH%-%SIREUM_V%.7z del /q/f "%USERPROFILE%\Downloads\sireum-%DISTRO%-win-%ARCH%-%SIREUM_V%.7z" >nul 2>&1
if exist %USERPROFILE%\Downloads\org.sireum.m2-%SIREUM_V%.zip del /q/f "%USERPROFILE%\Downloads\org.sireum.m2-%SIREUM_V%.zip" >nul 2>&1
if exist %USERPROFILE%\Downloads\7zz-%P7ZZ_V%-cosmo-%COSMOCC_V%.com del /q/f "%USERPROFILE%\Downloads\7zz-%P7ZZ_V%-cosmo-%COSMOCC_V%.com" >nul 2>&1
md "%PDIR%" "%USERPROFILE%\Downloads" 2> nul
echo Please wait while downloading https://github.com/sireum/kekinian/releases/download/%SIREUM_V%/sireum-%DISTRO%-win-%ARCH%.7z to %USERPROFILE%\Downloads ...
curl -JLso "%USERPROFILE%\Downloads\sireum-%DISTRO%-win-%ARCH%-%SIREUM_V%.7z" https://github.com/sireum/kekinian/releases/download/%SIREUM_V%/sireum-%DISTRO%-win-%ARCH%.7z || goto :error
echo.
echo Please wait while downloading https://github.com/sireum/kekinian/releases/download/%SIREUM_V%/org.sireum.m2.zip to %USERPROFILE%\Downloads ...
curl -JLso "%USERPROFILE%\Downloads\org.sireum.m2-%SIREUM_V%.zip" https://github.com/sireum/kekinian/releases/download/%SIREUM_V%/org.sireum.m2.zip || goto :error
echo.
echo Please wait while downloading https://github.com/sireum/rolling/releases/download/misc/7zz-%P7ZZ_V%-cosmo-%COSMOCC_V%.com to %USERPROFILE%\Downloads ...
curl -JLso "%USERPROFILE%\Downloads\7zz-%P7ZZ_V%-cosmo-%COSMOCC_V%.com" https://github.com/sireum/rolling/releases/download/misc/7zz-%P7ZZ_V%-cosmo-%COSMOCC_V%.com || goto :error
echo.
cd /d "%PDIR%"
echo Extracting sireum-%DISTRO%-win-%ARCH%-%SIREUM_V%.7z ...
"%USERPROFILE%\Downloads\7zz-%P7ZZ_V%-cosmo-%COSMOCC_V%.com" x "%USERPROFILE%\Downloads\sireum-%DISTRO%-win-%ARCH%-%SIREUM_V%.7z" >nul || goto :error
echo.
cd /d "%USERPROFILE%"
echo Extracting org.sireum.m2-%SIREUM_V%.zip ...
tar xf "%USERPROFILE%\Downloads\org.sireum.m2-%SIREUM_V%.zip" || goto :error
echo.
echo Sireum is installed at %DIR%
if "%DISTRO%" == "cli" exit /b 0
echo CodeIVE can be launched as follow: %DIR%\bin\win\vscodium\CodeIVE.exe
if "%DISTRO%" == "ive" echo IVE can be launched as follow: %DIR%\bin\win\idea\bin\IVE.exe
goto :EOF
:error
exit /b %errorlevel%
::!#*/
