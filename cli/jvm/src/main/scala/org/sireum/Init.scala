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
                       sireumJar: Os.Path,
                       z3Home: Os.Path)

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
    var first = T
    val cache = Os.home / "Downloads" / "sireum"
    def printInstall(): Unit = {
      if (first) {
        first = F
        println(s"Installing dependencies at $home ...")
        println()
      }
    }

    val javaHome: Os.Path = home / "java"
    var javaName = ""
    val javaVer = javaHome / "VER"
    Os.kind match {
      case Os.Kind.Mac =>
        val javaVersion = versions.get("org.sireum.version.graal").get
        if (!javaVer.exists || javaVer.read != javaVersion) {
          printInstall()
          println(s"Please wait while downloading GraalVM $javaVersion ...")
          val javaUrl = s"https://github.com/oracle/graal/releases/download/vm-$javaVersion/graalvm-ce-darwin-amd64-$javaVersion.tar.gz"
          javaName = s"1.8-graalvm-ce-$javaVersion"
          val temp = Os.temp()
          temp.removeOnExit()
          temp.downloadFrom(javaUrl)
          Os.proc(ISZ("tar", "xf", temp.string)).at(home).runCheck()
          javaHome.removeAll()
          val graal = home / s"graalvm-ce-$javaVersion"
          (graal / "Contents" / "Home").moveOverTo(javaHome)
          graal.removeAll()
          val niJar = javaHome / "native-image.jar"
          niJar.downloadFrom(s"https://github.com/oracle/graal/releases/download/vm-$javaVersion/native-image-installable-svm-darwin-amd64-$javaVersion.jar")
          Os.proc(ISZ((javaHome / "bin" / "jar").string, "xf", niJar.string)).at(javaHome).runCheck()
          niJar.removeAll()
          for (f <- (javaHome / "jre" / "lib" / "svm" / "bin").list) {
            f.chmodAll("+x")
          }
          javaVer.write(javaVersion)
          println()
        }
      case Os.Kind.Linux =>
        val javaVersion = versions.get("org.sireum.version.graal").get
        if (!javaVer.exists || javaVer.read != javaVersion) {
          printInstall()
          println(s"Please wait while downloading GraalVM $javaVersion ...")
          val javaUrl = s"https://github.com/oracle/graal/releases/download/vm-$javaVersion/graalvm-ce-linux-amd64-$javaVersion.tar.gz"
          javaName = s"1.8-graalvm-ce-$javaVersion"
          val temp = Os.temp()
          temp.removeOnExit()
          temp.downloadFrom(javaUrl)
          Os.proc(ISZ("tar", "xf", temp.string)).at(home).runCheck()
          javaHome.removeAll()
          val graal = home / s"graalvm-ce-$javaVersion"
          graal.moveOverTo(javaHome)
          val niJar = javaHome / "native-image.jar"
          niJar.downloadFrom(s"https://github.com/oracle/graal/releases/download/vm-$javaVersion/native-image-installable-svm-linux-amd64-$javaVersion.jar")
          Os.proc(ISZ((javaHome / "bin" / "jar").string, "xf", niJar.string)).at(javaHome).runCheck()
          niJar.removeAll()
          for (f <- (javaHome / "jre" / "lib" / "svm" / "bin").list) {
            f.chmodAll("+x")
          }
          javaVer.write(javaVersion)
          println()
        }
      case Os.Kind.Win =>
        val javaVersion = versions.get("org.sireum.version.zulu").get
        if (!javaVer.exists || javaVer.read != javaVersion) {
          printInstall()
          println(s"Please wait while downloading ZuluFX $javaVersion ...")
          val javaUrl = s"https://cdn.azul.com/zulu/bin/zulu$javaVersion-win_x64.zip"
          javaName = s"1.8-zulu-$javaVersion"
          val temp = Os.temp()
          temp.removeOnExit()
          temp.downloadFrom(javaUrl)
          temp.unzipTo(home)
          javaHome.removeAll()
          (home / s"zulu$javaVersion").moveOverTo(javaHome)
          javaVer.write(javaVersion)
          println()
        }
      case _ =>
        eprintln("Unsupported platform")
        Os.exit(1)
    }

    val scalaHome = home / "scala"
    val scalaVer = scalaHome / "VER"
    val scalaVersion = versions.get("org.sireum.version.scala").get
    if (!scalaVer.exists || scalaVer.read != scalaVersion) {
      printInstall()
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
      printInstall()
      println("Please wait while downloading Slang scalac plugin ...")
      scalacPlugin.downloadFrom(scalacPluginUrl)
      println()
    }

    val sireumJar = home / s"sireum-$version.jar"
    if (!sireumJar.exists) {
      for (p <- home.list if ops.StringOps(p.name).startsWith("sireum-")) {
        p.removeAll()
      }
      printInstall()
      println(s"Please wait while downloading Sireum jar assembly v$version ...")
      sireumJar.downloadFrom(s"https://github.com/sireum/kekinian/releases/download/$version/sireum.jar")
      //sireumJar.downloadFrom(s"http://files.sireum.org/sireum")
      sireumJar.chmodAll("+x")
      val r = Os.proc(ISZ((javaHome / "bin" / (if (Os.isWin) "java.exe" else "java")).string,
        "-jar", sireumJar.string, "-v")).run()
      if (!r.ok || !ops.StringOps(r.out).contains(version)) {
        sireumJar.removeAll()
        eprintln(s"Failed to download Sireum jar assembly v$version")
        Os.exit(-1)
      }
      println()
    }


    val z3Home = home / "z3"
    val z3Ver = z3Home / "VER"
    val z3Version = versions.get("org.sireum.version.z3").get
    val z3VersionOps = ops.StringOps(z3Version)
    val z3MVersion = z3VersionOps.substring(0, z3VersionOps.lastIndexOf('.'))
    if (!z3Ver.exists || z3Ver.read != z3Version) {
      printInstall()
      println(s"Please wait while downloading Z3 $z3Version ...")
      val z3Url: String = Os.kind match {
        case Os.Kind.Win => s"https://github.com/Z3Prover/z3/releases/download/z3-$z3MVersion/z3-$z3Version-x64-win.zip"
        case Os.Kind.Linux => s"https://github.com/Z3Prover/z3/releases/download/z3-$z3MVersion/z3-$z3Version-x64-ubuntu-14.04.zip"
        case Os.Kind.Mac => s"https://github.com/Z3Prover/z3/releases/download/z3-$z3MVersion/z3-$z3Version-x64-osx-10.14.1.zip"
        case _ => halt("Infeasible")
      }
      val temp = Os.temp()
      temp.removeOnExit()
      temp.downloadFrom(z3Url)
      temp.unzipTo(home)
      z3Home.removeAll()
      for (p <- home.list if ops.StringOps(p.name).startsWith("z3-")) {
        p.moveOverTo(z3Home)
      }
      (z3Home / "bin").chmodAll("+x")
      z3Ver.write(z3Version)
      println()
    }

    return Info(javaHome, javaName, scalaHome, scalacPlugin, sireumJar, z3Home)
  }
}
