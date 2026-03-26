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
$nik_versions = $properties["org.sireum.version.nik"]
$nik_parts = $nik_versions.Split(',')
$nik_java_version = $nik_parts[0]
$nik_version = $nik_parts[1]
$nik_display_version = "$nik_version-$nik_java_version"
$nik_full_version = $nik_display_version -replace "\+", "%2B"
# Default to NIK; set SIREUM_JDK=true to use Liberica JDK instead
$use_nik = $TRUE
if ($Env:SIREUM_JDK -eq "true") { $use_nik = $FALSE }
# Windows ARM64 has no NIK -- force JDK
if ($Env:PROCESSOR_ARCHITECTURE -eq "ARM64") { $use_nik = $FALSE }
$java_ver_path = "$sireum_bin\win\java\VER"

if ($use_nik) {
  $java_name = "Liberica NIK"
  $java_ver_check = $nik_full_version
  $java_display_ver = $nik_display_version
  $nik_base = "https://github.com/bell-sw/LibericaNIK/releases/download/$nik_full_version"
  $java_url = "$nik_base/bellsoft-liberica-vm-full-openjdk$nik_java_version-$nik_version-windows-amd64.zip"
  $java_drop = "$cache_dir\bellsoft-liberica-vm-full-openjdk$nik_java_version-$nik_version-windows-amd64.zip"
} else {
  $java_name = "JDK"
  $java_ver_check = $java_version
  $java_display_ver = $java_version
  if ($Env:PROCESSOR_ARCHITECTURE -eq "ARM64") {
    $arch = "aarch64"
  } else {
    $arch = "amd64"
  }
  $java_url = "https://download.bell-sw.com/java/$java_version/bellsoft-jdk$java_version-windows-$arch-full.zip"
  $java_drop = "$cache_dir\bellsoft-jdk$java_version-windows-$arch-full.zip"
}

$java_update = $TRUE
if (Test-Path "$java_ver_path") {
  $java_ver = Get-Content "$java_ver_path"
  if ($java_ver -Eq $java_ver_check) {
    $java_update = $FALSE
  }
}
if ($java_update) {
  if (!(Test-Path "$java_drop")) {
    "Please wait while downloading $java_name $java_display_ver ... "
    Invoke-WebRequest -Uri "$java_url" -OutFile "$java_drop"
  }
  "Extracting $java_name $java_display_ver ... "
  Expand-Archive "$java_drop" -DestinationPath "$sireum_bin\win"
  ""
  if (Test-Path "$sireum_bin\win\java") {
    Remove-Item -Path "$sireum_bin\win\java" -Recurse -Force
  }
  if ($use_nik) {
    $nik_dir = Get-ChildItem "$sireum_bin\win" -Directory | Where-Object { $_.Name -like "bellsoft-liberica-vm-*" } | Select-Object -First 1
    if ($nik_dir) { Move $nik_dir.FullName "$sireum_bin\win\java" }
  } else {
    $jver = $java_version -replace "\+.*"
    Move "$sireum_bin\win\jdk-$jver-full" "$sireum_bin\win\java"
  }
  "$java_ver_check" | Set-Content "$java_ver_path"
}

# JVMCI (GraalVM compiler module jars -- needed for GraalWasm JIT on JDK; harmless on NIK)
$graal_version = $properties["org.graalvm.compiler%compiler%"]
if ($graal_version) {
  $lib_dir = "$sireum_home\lib"
  New-Item -Type directory -Path "$lib_dir" -Force | Out-Null
  $maven_base = "https://repo1.maven.org/maven2"
  $graal_jars = @(
    @("org/graalvm/compiler", "compiler"),
    @("org/graalvm/truffle", "truffle-compiler"),
    @("org/graalvm/sdk", "word"),
    @("org/graalvm/sdk", "collections"),
    @("org/graalvm/sdk", "jniutils"),
    @("org/graalvm/sdk", "nativeimage")
  )
  foreach ($gjar in $graal_jars) {
    $group_path = $gjar[0]
    $artifact = $gjar[1]
    $jar_file = "$lib_dir\$artifact-$graal_version.jar"
    if (!(Test-Path "$jar_file")) {
      "Downloading $artifact-$graal_version.jar ..."
      Invoke-WebRequest -Uri "$maven_base/$group_path/$artifact/$graal_version/$artifact-$graal_version.jar" -OutFile "$jar_file"
    }
  }
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
