::/*#! 2> /dev/null                                                                                                                                                                         #
@ 2>/dev/null # 2>nul & echo off & goto BOF                                                                                                                                                 #
set -e                                                                                                                                                                                      #
: "${SIREUM_V:=dev}"                                                                                                                                                                        #
: "${DISTRO:=ive}"                                                                                                                                                                          #
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
    echo "Unsupported architecture aarch64"                                                                                                                                                 #
    exit -1                                                                                                                                                                                 #
  else                                                                                                                                                                                      #
    ARCH=amd64                                                                                                                                                                              #
  fi                                                                                                                                                                                        #
fi                                                                                                                                                                                          #
rm -fR ~/Applications/Sireum ~/Downloads/sireum-$DISTRO-$OS-$ARCH-$SIREUM_V.tar.xz ~/Downloads/org.sireum.library.m2-$SIREUM_V.zip                                                          #
rm -f ~/Downloads/sireum-$DISTRO-$OS-$ARCH-$SIREUM_V.tar.xz ~/Downloads/org.sireum.library.m2-$SIREUM_V.zip                                                                                 #
mkdir -p ~/Applications ~/Downloads                                                                                                                                                         #
echo "Please wait while downloading https://github.com/sireum/kekinian/releases/download/$SIREUM_V/sireum-$DISTRO-$OS-$ARCH.tar.xz to $HOME/Downloads ..."                                  #
curl -JLso ~/Downloads/sireum-$DISTRO-$OS-$ARCH-$SIREUM_V.tar.xz https://github.com/sireum/kekinian/releases/download/$SIREUM_V/sireum-$DISTRO-$OS-$ARCH.tar.xz                             #
echo ""                                                                                                                                                                                     #
echo "Please wait while downloading https://github.com/sireum/kekinian/releases/download/$SIREUM_V/org.sireum.library.m2.zip to $HOME/Downloads ..."                                        #
curl -JLso ~/Downloads/org.sireum.library.m2-$SIREUM_V.zip https://github.com/sireum/kekinian/releases/download/$SIREUM_V/org.sireum.library.m2.zip                                         #
echo ""                                                                                                                                                                                     #
cd ~/Applications                                                                                                                                                                           #
echo "Extracting sireum-$DISTRO-$OS-$ARCH-$SIREUM_V.tar.xz ..."                                                                                                                             #
tar xf ~/Downloads/sireum-$DISTRO-$OS-$ARCH-$SIREUM_V.tar.xz                                                                                                                                #
echo ""                                                                                                                                                                                     #
cd ~                                                                                                                                                                                        #
echo "Extracting org.sireum.library.m2-$SIREUM_V.zip ..."                                                                                                                                   #
if [ "$OS" = "mac" ]; then                                                                                                                                                                  #
  tar xf ~/Downloads/org.sireum.library.m2-$SIREUM_V.zip                                                                                                                                    #
else                                                                                                                                                                                        #
  unzip -qq ~/Downloads/org.sireum.library.m2-$SIREUM_V.zip                                                                                                                                 #
fi                                                                                                                                                                                          #
echo ""                                                                                                                                                                                     #
echo "Sireum is installed at $HOME/Sireum"                                                                                                                                                  #
exit 0                                                                                                                                                                                      #
:BOF
setlocal
set errorlevel=
set P7ZZ_V=24.08
set COSMOCC_V=3.9.6
if not defined SIREUM_V set SIREUM_V=dev
if not defined DISTRO set DISTRO=ive
set ARCH=amd64
if "%PROCESSOR_ARCHITECTURE%" == "ARM64" set ARCH=arm64 && echo Unsupported architecture ARM64 && exit /b -2
rmdir /s/q "%USERPROFILE%\Applications\Sireum" 2> null
if exist %USERPROFILE%\Downloads\sireum-%DISTRO%-win-%ARCH%-%SIREUM_V%.7z delete /s "%USERPROFILE%\Downloads\sireum-%DISTRO%-win-%ARCH%-%SIREUM_V%.7z" >nul 2>&1
if exist %USERPROFILE%\Downloads\org.sireum.library.m2-%SIREUM_V%.zip delete /s "%USERPROFILE%\Downloads\org.sireum.library.m2-%SIREUM_V%.zip" >nul 2>&1
if exist %USERPROFILE%\Downloads\7zz-%P7ZZ_V%-cosmo-%COSMOCC_V%.com delete /s "%USERPROFILE%\Downloads\7zz-%P7ZZ_V%-cosmo-%COSMOCC_V%.com" >nul 2>&1
md "%USERPROFILE%\Applications" "%USERPROFILE%\Downloads" 2> nul
echo Please wait while downloading https://github.com/sireum/kekinian/releases/download/%SIREUM_V%/sireum-%DISTRO%-win-%ARCH%.7z to %USERPROFILE%\Downloads ...
curl -JLso "%USERPROFILE%\Downloads\sireum-%DISTRO%-win-%ARCH%-%SIREUM_V%.7z" https://github.com/sireum/kekinian/releases/download/%SIREUM_V%/sireum-%DISTRO%-win-%ARCH%.7z || goto :error
echo.
echo Please wait while downloading https://github.com/sireum/kekinian/releases/download/%SIREUM_V%/org.sireum.library.m2.zip to %USERPROFILE%\Downloads ...
curl -JLso "%USERPROFILE%\Downloads\org.sireum.library.m2-%SIREUM_V%.zip" https://github.com/sireum/kekinian/releases/download/%SIREUM_V%/org.sireum.library.m2.zip || goto :error
echo.
echo Please wait while downloading https://github.com/sireum/rolling/releases/download/misc/7zz-%P7ZZ_V%-cosmo-%COSMOCC_V%.com to %USERPROFILE%\Downloads ...
curl -JLso "%USERPROFILE%\Downloads\7zz-%P7ZZ_V%-cosmo-%COSMOCC_V%.com" https://github.com/sireum/rolling/releases/download/misc/7zz-%P7ZZ_V%-cosmo-%COSMOCC_V%.com || goto :error
echo.
cd /d "%USERPROFILE%\Applications"
echo Extracting sireum-%DISTRO%-win-%ARCH%-%SIREUM_V%.7z ...
"%USERPROFILE%\Downloads\7zz-%P7ZZ_V%-cosmo-%COSMOCC_V%.com" x "%USERPROFILE%\Downloads\sireum-%DISTRO%-win-%ARCH%-%SIREUM_V%.7z" >nul || goto :error
echo.
cd /d "%USERPROFILE%"
echo Extracting org.sireum.library.m2-%SIREUM_V%.zip ...
tar xf "%USERPROFILE%\Downloads\org.sireum.library.m2-%SIREUM_V%.zip" || goto :error
echo.
echo Sireum is installed at %USERPROFILE%\Applications\Sireum
goto :EOF
:error
exit /b %errorlevel%
::!#*/
