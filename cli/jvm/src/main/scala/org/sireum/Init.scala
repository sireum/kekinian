// #Sireum
/*
 Copyright (c) 2018, Robby, Kansas State University
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sireum

object Init {

  @datatype class Info(javaHome: Os.Path,
                       javaName: String,
                       scalaHome: Os.Path,
                       scalacPlugin: Os.Path,
                       sireumJar: Os.Path)

  @memoize def home: Os.Path = {
    val r: Os.Path = Os.kind match {
      case Os.Kind.Win => Os.home / "AppData" / "Local" / "Sireum"
      case Os.Kind.Mac => Os.home / "Library" / "Application Support" / "org.sireum"
      case _ => Os.home / ".sireum"
    }
    return r
  }

  def info(version: String, versions: Map[String, String]): Info = {
    home.mkdirAll()
    val sireumJar = home / s"sireum-$version.jar"
    if (!sireumJar.exists) {
      sireumJar.downloadFrom(s"https://github.com/sireum/kekinian/releases/download/$version/sireum.jar")
      sireumJar.chmodAll("+x")
      val r = Os.proc(ISZ(sireumJar.string, "-v")).run()
      if (!r.ok || !ops.StringOps(r.out).contains(version)) {
        sireumJar.removeAll()
        eprintln(s"Failed to download Sireum jar assembly v$version")
        Os.exit(-1)
      }
    }

    val javaHome: Os.Path = home / "java"
    var javaName = ""
    val javaVer = javaHome / "VER"
    Os.kind match {
      case Os.Kind.Mac =>
        val javaVersion = versions.get("org.sireum.version.graal").get
        if (!javaVer.exists || javaVer.read != javaVersion) {
          println(s"Please wait while downloading GraalVM $javaVersion ...")
          val javaUrl = s"https://github.com/oracle/graal/releases/download/vm-$javaVersion/graalvm-ce-$javaVersion-macos-amd64.tar.gz"
          javaName = s"1.8-graalvm-ce-$javaVersion"
          val temp = Os.temp()
          temp.removeOnExit()
          temp.downloadFrom(javaUrl)
          Os.proc(ISZ("tar", "xf", temp.string)).at(home).runCheck()
          javaHome.removeAll()
          val graal = home / s"graalvm-ce-$javaVersion"
          (graal / "Contents" / "Home").moveOverTo(javaHome)
          graal.removeAll()
          javaVer.write(javaVersion)
          println()
        }
      case Os.Kind.Linux =>
        val javaVersion = versions.get("org.sireum.version.graal").get
        if (!javaVer.exists || javaVer.read != javaVersion) {
          println(s"Please wait while downloading GraalVM $javaVersion ...")
          val javaUrl = s"https://github.com/oracle/graal/releases/download/vm-$javaVersion/graalvm-ce-$javaVersion-linux-amd64.tar.gz"
          javaName = s"1.8-graalvm-ce-$javaVersion"
          val temp = Os.temp()
          temp.removeOnExit()
          temp.downloadFrom(javaUrl)
          Os.proc(ISZ("tar", "xf", temp.string)).at(home).runCheck()
          javaHome.removeAll()
          val graal = home / s"graalvm-ce-$javaVersion"
          graal.moveOverTo(javaHome)
          javaVer.write(javaVersion)
        }
      case Os.Kind.Win =>
        val javaVersion = versions.get("org.sireum.version.zulu").get
        if (!javaVer.exists || javaVer.read != javaVersion) {
          println(s"Please wait while downloading Zulu $javaVersion ...")
          val javaUrl = s"http://cdn.azul.com/zulu/bin/zulu$javaVersion-win_x64.zip"
          javaName = s"1.8-zulu-$javaVersion"
          val temp = Os.temp()
          temp.removeOnExit()
          temp.downloadFrom(javaUrl)
          temp.unzipTo(home)
          javaHome.removeAll()
          (home / s"zulu$javaVersion").moveOverTo(javaHome)
          javaVer.write(javaVersion)
        }
      case _ =>
        eprintln("Unsupported platform")
        Os.exit(1)
    }

    val scalaHome = home / "scala"
    val scalaVer = scalaHome / "VER"
    val scalaVersion = versions.get("org.sireum.version.scala").get
    if (!scalaVer.exists || scalaVer.read != scalaVersion) {
      println(s"Please wait while downloading Scala $scalaVersion ...")
      val scalaUrl = s"http://downloads.lightbend.com/scala/$scalaVersion/scala-$scalaVersion.zip"
      val temp = Os.temp()
      temp.removeOnExit()
      temp.downloadFrom(scalaUrl)
      temp.unzipTo(home)
      scalaHome.removeAll()
      (home / s"scala-$scalaVersion").moveOverTo(scalaHome)
      (scalaHome / "bin").chmodAll("+x")
      scalaVer.write(scalaVersion)
      println()
    }

    val scalacPluginVersion: String = versions.get("org.sireum.version.scalac-plugin").get
    val scalacPlugin = home / s"scalac-plugin-$scalacPluginVersion.jar"
    if (!scalacPlugin.exists) {
      val scalacPluginUrl = s"https://jitpack.io/org/sireum/scalac-plugin/$scalacPluginVersion/scalac-plugin-$scalacPluginVersion.jar"
      for (p <- home.list if ops.StringOps(p.name).startsWith("scalac-plugin")) {
        p.removeAll()
      }
      println("Please wait while downloading Slang scalac plugin ...")
      scalacPlugin.downloadFrom(scalacPluginUrl)
      println()
    }

    return Info(javaHome, javaName, scalaHome, scalacPlugin, sireumJar)
  }
}
