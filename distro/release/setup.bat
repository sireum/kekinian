@echo off
set INSTALL_PATH="%USERPROFILE%\Applications"
set /p INSTALL_PATH="Enter the directory where Sireum should be installed under [Press Enter for %INSTALL_PATH%]:"
md %INSTALL_PATH%
move %~dp0Sireum %INSTALL_PATH%
echo Sireum IVE can now be launched by running %INSTALL_PATH%\Sireum\bin\win\idea\bin\idea64.exe
echo Java Development Kit (JDK) is available at %INSTALL_PATH%\Sireum\bin\win\java
echo Scala is available at %INSTALL_PATH%\Sireum\bin\scala
echo Mill can be found at %INSTALL_PATH%\Sireum\bin\mill.bat
echo Press any key to exit ...
pause
(goto) 2>nul & del "%~f0"