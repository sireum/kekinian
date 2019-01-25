@echo off
set INSTALL_PATH="%USERPROFILE%\Applications"
choice /T 5 /C YN /D N /N /M "Customize where Sireum should be installed under (defauts to %INSTALL_PATH%) [Y/N]? "
if errorlevel 2 goto :install
set /p INSTALL_PATH="Enter the directory path [Press Enter to use the default]: "
:install
md %INSTALL_PATH% 1> nul 2>&1
rd /S /Q %INSTALL_PATH%\Sireum 2>nul
move %~dp0Sireum %INSTALL_PATH% 1> nul 2>&1
echo Sireum IVE can now be launched by running %INSTALL_PATH%\Sireum\bin\win\idea\bin\idea64.exe
echo Java Development Kit (JDK) is available at %INSTALL_PATH%\Sireum\bin\win\java
echo Scala is available at %INSTALL_PATH%\Sireum\bin\scala
echo Mill can be found at %INSTALL_PATH%\Sireum\bin\mill.bat
echo Press any key to exit ...
pause >nul
(goto) 2>nul & del "%~f0"