@echo off
start "Sireum-dev Installation" cmd /c  %~dp0install.bat
(goto) 2>nul & del "%~f0"