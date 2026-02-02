@@setlocal
@@set POWERSHELL_BAT_ARGS=%~dp0 %*
@@set SIREUM_HOME=%~dp0..\
@@pushd %SIREUM_HOME%
@@set SIREUM_HOME=%CD%
@@popd
@@if defined POWERSHELL_BAT_ARGS set POWERSHELL_BAT_ARGS=%POWERSHELL_BAT_ARGS:"=\"%
@@PowerShell -noprofile -executionpolicy bypass -Command Invoke-Expression $('$args=@(^&{$args} %POWERSHELL_BAT_ARGS%);'+[String]::Join([char]10,$((Get-Content '%~f0') -notmatch '^^@@'))) & goto :EOF

# Copyright (c) 2017-2026,Robby, Kansas State University
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are met:
#
# 1. Redistributions of source code must retain the above copyright notice, this
#    list of conditions and the following disclaimer.
# 2. Redistributions in binary form must reproduce the above copyright notice,
#    this list of conditions and the following disclaimer in the documentation
#    and/or other materials provided with the distribution.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
# ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
# WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
# DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
# ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
# (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
# LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
# ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
# (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

$OriginalProgressPreference = $Global:ProgressPreference
$Global:ProgressPreference = 'SilentlyContinue'

if ($Env:SIREUM_V) {
  $sireum_v = "$Env:SIREUM_V"
} else {
  $sireum_v = "master"
}
if ($Env:SIREUM_INIT_V) {
  $init_v = "$Env:SIREUM_INIT_V"
} else {
  if ($sireum_v -eq "master") {
    $init_v = "dev"
  } else {
    if ($sireum_v.StartsWith('4.')) {
      $init_v = "$sireum_v"
    } else {
      $init_v = "4.$sireum_v"
    }
  }
}

if ($Env:SIREUM_CACHE) {
  $cache_dir = "$Env:SIREUM_CACHE"
} else {
  $cache_dir = "~\Downloads\sireum"
}
New-Item -Type directory -Path "$cache_dir" -Force | Out-Null


$sireum_home = $env:SIREUM_HOME
$sireum_bin = "$sireum_home\bin"
$sireum_jar = "$sireum_bin\sireum.jar"
$sireum_bat = "$sireum_bin\sireum.bat"
$versions_properties = "$sireum_home\versions.properties"

if (!(Test-Path $sireum_jar)) {
  "Please wait while downloading Sireum ..."
  Invoke-WebRequest -Uri "https://github.com/sireum/kekinian/releases/download/$init_v/sireum.jar" -OutFile "$sireum_jar"
  ""
}
if (!(Test-Path $sireum_bat)) {
  Invoke-WebRequest -Uri "https://raw.githubusercontent.com/sireum/kekinian/$sireum_v/bin/sireum.bat" -OutFile "$sireum_bat"
}
if (!(Test-Path $versions_properties)) {
  Invoke-WebRequest -Uri "https://raw.githubusercontent.com/sireum/kekinian/$sireum_v/versions.properties" -OutFile "$versions_properties"
}


$fileContents = Get-Content "$versions_properties"
$properties = @{}
foreach($line in $fileContents) {
  $words = $line.Split('=',2)
  if ($words[0] -And $words[1]) {
    $properties.Add($words[0].Trim(), $words[1].Trim())
  }
}


if ($Env:SIREUM_PROVIDED_JAVA) {
  $Global:ProgressPreference = $OriginalProgressPreference
  Exit
}
$java_version = $properties["org.sireum.version.java"]
$java_ver_path = "$sireum_bin\win\java\VER"
$java_update = $TRUE
if (Test-Path "$java_ver_path") {
  $java_ver = Get-Content "$java_ver_path"
  if ($java_ver -Eq $java_version) {
    $java_update = $FALSE
  }
}
if ($java_update) {
  if ($Env:PROCESSOR_ARCHITECTURE -eq "ARM64") {
    $arch = "aarch64"
  } else {
    $arch = "amd64"
  }
  $java_drop = "$cache_dir\bellsoft-jdk$java_version-windows-$arch-full.zip"
  if (!(Test-Path "$java_drop")) {
    "Please wait while downloading JDK $java_version ... "
    $java_url = "https://download.bell-sw.com/java/$java_version/bellsoft-jdk$java_version-windows-$arch-full.zip"
    Invoke-WebRequest -Uri "$java_url" -OutFile "$java_drop"
  }
  "Extracting JDK $java_version ... "
  Expand-Archive "$java_drop" -DestinationPath "$sireum_bin\win"
  ""
  if (Test-Path "$sireum_bin\win\java") {
    Remove-Item -Path "$sireum_bin\win\java" -Recurse -Force
  }
  $jver = $java_version -replace "\+.*"
  Move "$sireum_bin\win\jdk-$jver-full" "$sireum_bin\win\java"
  "$java_version" | Set-Content "$java_ver_path"
}

if ($Env:SIREUM_NO_SETUP) {
  $sireum_setup = $FALSE
} else {
  $sireum_setup = $TRUE
}
$build_path = "$sireum_bin\build.cmd"
if (!(Test-Path "$build_path") -And $sireum_setup) {
  if (Test-Path "$java_ver_path") {
    $java = "$sireum_bin\win\java\bin\java.exe"
  } else {
    $java = "java"
  }
  Start-Process -NoNewWindow -Wait -FilePath "$java" -ArgumentList @("-Dorg.sireum.home=`"$sireum_home`"", "-jar", "`"$sireum_jar`"", "setup", "ive")
}

$Global:ProgressPreference = $OriginalProgressPreference
