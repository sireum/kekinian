# Copyright (c) 2017-2021, Robby, Kansas State University
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

if ($Env:SIREUM_CACHE) {
  $cache_dir = "$Env:SIREUM_CACHE"
} else {
  $cache_dir = "~\Downloads\sireum"
}
New-Item -Type directory -Path "$cache_dir" -Force | Out-Null
if ($Env:SIREUM_INIT_V) {
  $init_v = "$Env:SIREUM_INIT_V"
} else {
  $init_v = "latest"
}

$sireum_home = "$PSScriptRoot\.."


$sireum_jar = "$sireum_home\bin\sireum.jar"
$sireum_bat = "$sireum_home\bin\sireum.bat"
$versions_properties = "$sireum_home\versions.properties"
if (!(Test-Path $sireum_jar)) {
  "Please wait while downloading Sireum ..."
  Invoke-WebRequest -Uri "https://github.com/sireum/init/releases/download/$init_v/sireum.jar" -OutFile "$sireum_jar"
  if (!(Test-Path $sireum_bat)) {
    Invoke-WebRequest -Uri "https://raw.githubusercontent.com/sireum/kekinian/master/bin/sireum.bat" -OutFile "$sireum_bat"
  }
  if (!(Test-Path $versions_properties)) {
    Invoke-WebRequest -Uri "https://raw.githubusercontent.com/sireum/kekinian/master/versions.properties" -OutFile "$versions_properties"
  }
  ""
}


$fileContents = Get-Content "$versions_properties"
$properties = @{}
foreach($line in $fileContents) {
  $words = $line.Split('=',2)
  if ($words[0] -And $words[1]) {
    $properties.Add($words[0].Trim(), $words[1].Trim())
  }
}


$scalac_plugin_version = $properties["org.sireum%%scalac-plugin%"]
New-Item -Type directory -Path "$sireum_home\lib" -Force | Out-Null
$scalac_plugin_jar = "scalac-plugin-$scalac_plugin_version.jar"
$scalac_plugin_drop = "$cache_dir\$scalac_plugin_jar"
$scalac_plugin_lib = "$sireum_home\lib\$scalac_plugin_jar"
if (!(Test-Path "$scalac_plugin_lib")) {
  if (!(Test-Path "$scalac_plugin_drop")) {
    "Please wait while downloading Slang scalac plugin $scalac_plugin_version ..."
    $scalac_plugin_url = "https://github.com/sireum/scalac-plugin/releases/download/$scalac_plugin_version/$scalac_plugin_jar"
    Invoke-WebRequest -Uri "$scalac_plugin_url" -OutFile "$scalac_plugin_drop"
    ""
  }
  Copy-Item -Path "$scalac_plugin_drop" -Destination "$scalac_plugin_lib" -Force
}


if ($Env:SIREUM_PROVIDED_SCALA) {
  Exit
}
$scala_version = $properties["org.scala-lang%scala-library%"]
$scala_ver_path = "$sireum_home\bin\scala\VER"
$scala_update = $TRUE
if (Test-Path "$scala_ver_path") {
  $scala_ver = Get-Content "$scala_ver_path"
  if ($scala_ver -Eq $scala_version) {
    $scala_update = $FALSE
  }
}
if ($scala_update) {
  $scala_drop = "$cache_dir\scala-$scala_version.zip"
  if (!(Test-Path "$scala_drop")) {
    "Please wait while downloading Scala $scala_version ..."
    $scala_url = "https://downloads.lightbend.com/scala/$scala_version/scala-$scala_version.zip"
    Invoke-WebRequest -Uri "$scala_url" -OutFile "$scala_drop"
  }
  "Extracting Scala $scala_version ..."
  Expand-Archive "$scala_drop" -DestinationPath "$sireum_home\bin"
  ""
  if (Test-Path "$sireum_home\bin\scala") {
    Remove-Item -Path "$sireum_home\bin\scala" -Recurse -Force
  }
  Move-Item -Path "$sireum_home\bin\scala-$scala_version" -Destination "$sireum_home\bin\scala" -Force
  "$scala_version" | Set-Content "$scala_ver_path"
}


if ($Env:SIREUM_PROVIDED_JAVA) {
  Exit
}
$java_version = $properties["org.sireum.version.zulu"]
$java_ver_path = "$sireum_home\bin\win\java\VER"
$java_update = $TRUE
if (Test-Path "$java_ver_path") {
  $java_ver = Get-Content "$java_ver_path"
  if ($java_ver -Eq $java_version) {
    $java_update = $FALSE
  }
}
if ($java_update) {
  $java_drop = "$cache_dir\zulu$java_version-win_x64.zip"
  if (!(Test-Path "$java_drop")) {
    "Please wait while downloading Zulu JDK $java_version ... "
    $java_url = "https://cdn.azul.com/zulu/bin/zulu$java_version-win_x64.zip"
    Invoke-WebRequest -Uri "$java_url" -OutFile "$java_drop"
  }
  "Extracting Zulu JDK $java_version ... "
  Expand-Archive "$java_drop" -DestinationPath "$sireum_home\bin\win"
  ""
  if (Test-Path "$sireum_home\bin\win\java") {
    Remove-Item -Path "$sireum_home\bin\win\java" -Recurse -Force
  }
  Move "$sireum_home\bin\win\zulu$java_version-win_x64" "$sireum_home\bin\win\java"
  "$java_version" | Set-Content "$java_ver_path"
}
