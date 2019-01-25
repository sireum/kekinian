@echo off
set INSTALL_PATH="%USERPROFILE%\Applications"
set /p INSTALL_PATH="Enter the directory where Sireum-dev should be installed under [Press Enter for %INSTALL_PATH%]:"
md %INSTALL_PATH%
move %~dp0Sireum-dev %INSTALL_PATH%
echo Sireum-dev IVE can now be launched by running %INSTALL_PATH%\Sireum-dev\bin\win\idea\bin\idea64.exe
echo Java Development Kit (JDK) is available at %INSTALL_PATH%\Sireum-dev\bin\win\java
echo Scala is available at %INSTALL_PATH%\Sireum-dev\bin\scala
echo Mill can be found at %INSTALL_PATH%\Sireum-dev\bin\mill.bat
echo Press any key to exit ...
pause
(goto) 2>nul & del "%~f0"