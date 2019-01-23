@echo off
set INSTALL_PATH="%USERPROFILE%\Applications"
set /p INSTALL_PATH="Enter the directory where Sireum-dev should be installed under [Press Enter for %INSTALL_PATH%]:"
move %~dp0Sireum-dev %INSTALL_PATH%\
(goto) 2>nul & del "%~f0"