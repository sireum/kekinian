@echo off
set INSTALL_PATH="%USERPROFILE%\Applications"
set /p INSTALL_PATH="Enter the directory where Sireum should be installed under [Press Enter for %INSTALL_PATH%]:"
move %~dp0Sireum %INSTALL_PATH%\
(goto) 2>nul & del "%~f0"