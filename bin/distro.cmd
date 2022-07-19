::#! 2> /dev/null                                   #
@ 2>/dev/null # 2>nul & echo off & goto BOF         #
if [ -z ${SIREUM_HOME} ]; then                      #
  echo "Please set SIREUM_HOME env var"             #
  exit -1                                           #
fi                                                  #
exec ${SIREUM_HOME}/bin/sireum slang run "$0" "$@"  #
:BOF
setlocal
if not defined SIREUM_HOME (
  echo Please set SIREUM_HOME env var
  exit /B -1
)
%SIREUM_HOME%\bin\sireum.bat slang run "%0" %*
exit /B %errorlevel%
::!#
// #Sireum
/*
 Copyright (c) 2017-2022, Robby, Kansas State University
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


import org.sireum._

@datatype class Plugin(val id: String,
                       val isDev: B,
                       val isJar: B,
                       val devVer: String,
                       val ver: String) {
  @strictpure def version(isDev: B): String =
    if (isDev) devVer else ver
}

var isDev: B = T
var buildSfx: B = F
var isUltimate: B = F
var isServer: B = F
val devSuffix: String = if (isDev) "-dev" else ""
var platform: String = Os.kind match {
  case Os.Kind.Mac => "mac"
  case Os.Kind.LinuxArm => "linux/arm"
  case Os.Kind.Linux => "linux"
  case Os.Kind.Win => "win"
  case _ => halt("Unsupported platform")
}

val cliArgs = Os.cliArgs

;{
  var i = 0
  def parsePlatform(): Unit = {
    i = i + 1
    if (i < cliArgs.size) {
      platform = cliArgs(i)
    } else {
      eprintln(s"Expecting an argument for --platform")
      Os.exit(-1)
    }
  }

  def parseSfx(): Unit = {
    buildSfx = T
  }

  def parseUltimate(): Unit = {
    isUltimate = T
    isDev = T
  }

  def parseServer(): Unit = {
    isUltimate = T
    isServer = T
    isDev = T
  }

  def parseRelease(): Unit = {
    if (!isUltimate) {
      isDev = F
    }
  }

  def help(): Unit = {
    println(
      st"""Sireum Distro
          |
          |Usage: <option>*
          |
          |-p, --platform   Target platform string to build distro for
          |                   (either 'mac', 'linux', 'linux/arm', or 'win')
          |-r               Set to release version
          |-s, --sfx        Build sfx package
          |-u, --ultimate   Use IntelliJ Ultimate edition
          |                   (always non-release version)
          |    --server     Use IntelliJ Ultimate edition for remote dev
          |                   (always non-release version)
          |-h               Display this information""".render
    )
    Os.exit(0)
  }

  while (i < cliArgs.size) {
    cliArgs(i) match {
      case string"--platform" => parsePlatform()
      case string"--release" => parseRelease()
      case string"--sfx" => parseSfx()
      case string"--ultimate" => parseUltimate()
      case string"--server" => parseServer()
      case string"-p" => parsePlatform()
      case string"-r" => parseRelease()
      case string"-s" => parseSfx()
      case string"-u" => parseUltimate()
      case string"-h" => help()
    }
    i = i + 1
  }
}


if (isServer && Os.kind != Os.Kind.Linux) {
  eprintln(s"Server setup is only available in Linux")
  Os.exit(-1)
}

val homeBin = Os.slashDir
val home = homeBin.up.canon
val ideaDir: Os.Path = home / "bin" / platform / (if (isServer) "idea-server" else if (isUltimate) "idea-ultimate" else "idea")
val sireumAppDir: Os.Path = ideaDir / s"IVE.app"
val delPlugins = ISZ[String]("android", "smali", "Ktor")
val pluginPrefix: String = "org.sireum.version.plugin."
val versions = HashMap ++ (home / "versions.properties").properties.entries
val isLocal: B = ops.StringOps(home.string).startsWith(Os.home.canon.string)
val settingsDir: String = if (isLocal) (home / ".settings").string else "${user.home}"

@pure def devRelVer(key: String): (String, String) = {
  ops.StringOps(versions.get(key).get).split((c: C) => c === ',') match {
    case ISZ(devVer, ver) =>
      return (ops.StringOps(devVer).trim, ops.StringOps(ver).trim)
    case ISZ(ver) =>
      val v = ops.StringOps(ver).trim
      return (v, v)
  }
}

@strictpure def zipName(id: String, version: String): String = s"$id-$version.zip"

val ignoredIcons = HashSet ++ ISZ[String](
  "idea.icns",
  "idea-dev.icns",
  "idea.png",
  "idea-dev.png",
  "idea.svg",
  "idea-dev.svg",
  "idea-dev.ico",
  "idea-ce.svg",
  "idea-ce-eap.svg",
  "idea-ce_16.svg",
  "idea-ce-eap_16.svg",
  "idea-ce_16@2x.svg",
  "idea-ce-eap_16@2x.svg",
  "icon_CE_256.png",
  "icon_CE_256@2x.png",
  "icon_CE_512.png",
  "icon_CE_512@2x.png",
  "idea_logo_background.png"
)

val ideaExtMap =
  HashMap.empty[String, String] +
    "mac" ~> ".dmg" +
    "mac/arm" ~> "-aarch64.dmg" +
    "win" ~> ".win.zip" +
    "linux" ~> ".tar.gz" +
    "linux/arm" ~> ".tar.gz"

val cacheDir: Os.Path = {
  Os.env("SIREUM_CACHE") match {
    case Some(p) => Os.path(p).canon
    case _ =>
      val p = Os.home / "Downloads" / "sireum"
      p.mkdirAll()
      p
  }
}

val ideaCacheDir = cacheDir / "idea"
val pluginsCacheDir = ideaCacheDir / "plugins"
pluginsCacheDir.mkdirAll()

val plugins: HashMap[String, Plugin] = {
  var r = HashMap.empty[String, Plugin]
  for (key <- versions.keys if ops.StringOps(key).startsWith(pluginPrefix)) {
    val id = ops.StringOps(key).substring(pluginPrefix.size, key.size)
    ops.StringOps(versions.get(key).get).split((c: C) => c === ',') match {
      case ISZ(isDev, isJar, devVer) =>
        r = r + id ~> Plugin(id, isDev === "true", isJar === "true", devVer, devVer)
      case ISZ(isDev, isJar, devVer, ver) =>
        r = r + id ~> Plugin(id, isDev === "true", isJar === "true", devVer, ver)
    }
  }
  r
}

val distroMap = HashMap.empty[String, ISZ[ISZ[String]]] +
  "win" ~> ISZ(
    ISZ("bin", "scala"),
    ISZ("bin", "win", "idea"),
    ISZ("bin", "win", "java"),
    ISZ("bin", "win", "z3"),
    ISZ("bin", "win", "cvc.exe"),
    ISZ("bin", "install", "antlrworks.cmd"),
    ISZ("bin", "install", "clion.cmd"),
    ISZ("bin", "install", "fmide.cmd"),
    ISZ("bin", "install", "graal.cmd"),
    ISZ("bin", "sireum.bat"),
    ISZ("bin", "sireum.jar"),
    ISZ("bin", "slang-run.bat"),
    ISZ("bin", "VER"),
    ISZ("lib"),
    ISZ("license.txt"),
    ISZ("readme.md"),
    ISZ("versions.properties"),
    ISZ("..", "setup.bat")
  ) +
  "linux" ~> ISZ(
    ISZ("bin", "scala"),
    ISZ("bin", "linux", "idea"),
    ISZ("bin", "linux", "java"),
    ISZ("bin", "linux", "z3"),
    ISZ("bin", "linux", "cvc"),
    ISZ("bin", "linux", "alt-ergo-open"),
    ISZ("bin", "install", "acl2.cmd"),
    ISZ("bin", "install", "alt-ergo.cmd"),
    ISZ("bin", "install", "antlrworks.cmd"),
    ISZ("bin", "install", "clion.cmd"),
    ISZ("bin", "install", "compcert.cmd"),
    ISZ("bin", "install", "coq.cmd"),
    ISZ("bin", "install", "ffmpeg-libs.cmd"),
    ISZ("bin", "install", "fmide.cmd"),
    ISZ("bin", "install", "graal.cmd"),
    ISZ("bin", "install", "menhir.cmd"),
    ISZ("bin", "install", "opam.cmd"),
    ISZ("bin", "install", "projector-server.cmd"),
    ISZ("bin", "sireum"),
    ISZ("bin", "sireum.jar"),
    ISZ("bin", "slang-run.sh"),
    ISZ("bin", "VER"),
    ISZ("lib"),
    ISZ("license.txt"),
    ISZ("readme.md"),
    ISZ("versions.properties"),
    ISZ("..", "setup")
  ) +
  "linux/arm" ~> ISZ(
    ISZ("bin", "scala"),
    ISZ("bin", "linux", "arm", "fsnotifier"),
    ISZ("bin", "linux", "arm", "idea"),
    ISZ("bin", "linux", "arm", "java"),
    ISZ("bin", "install", "clion.cmd"),
    ISZ("bin", "sireum"),
    ISZ("bin", "sireum.jar"),
    ISZ("bin", "slang-run.sh"),
    ISZ("bin", "VER"),
    ISZ("lib"),
    ISZ("license.txt"),
    ISZ("readme.md"),
    ISZ("versions.properties"),
    ISZ("..", "setup")
  ) +
  "mac" ~> ISZ(
    ISZ("bin", "scala"),
    ISZ("bin", "mac", "idea"),
    ISZ("bin", "mac", "java"),
    ISZ("bin", "mac", "z3"),
    ISZ("bin", "mac", "cvc"),
    ISZ("bin", "mac", "alt-ergo-open"),
    ISZ("bin", "install", "alt-ergo.cmd"),
    ISZ("bin", "install", "antlrworks.cmd"),
    ISZ("bin", "install", "clion.cmd"),
    ISZ("bin", "install", "compcert.cmd"),
    ISZ("bin", "install", "coq.cmd"),
    ISZ("bin", "install", "fmide.cmd"),
    ISZ("bin", "install", "graal.cmd"),
    ISZ("bin", "install", "menhir.cmd"),
    ISZ("bin", "install", "opam.cmd"),
    ISZ("bin", "sireum"),
    ISZ("bin", "sireum.jar"),
    ISZ("bin", "slang-run.sh"),
    ISZ("bin", "VER"),
    ISZ("lib"),
    ISZ("license.txt"),
    ISZ("readme.md"),
    ISZ("versions.properties"),
    ISZ("..", "setup")
  )

val pluginsDir: Os.Path =
  if (platform === "mac") sireumAppDir / "Contents" / "plugins"
  else ideaDir / "plugins"

val libDir: Os.Path =
  if (platform === "mac") sireumAppDir / "Contents" / "lib"
  else ideaDir / "lib"

val version: String = {
  val v = proc"git log -n 1 --date=format:%Y%m%d --pretty=format:4.%cd.%h".at(home).runCheck().out
  (home / "bin" / "VER").writeOver(v)
  v
}

val ideaVer: String = {
  val (devVer, ver) = devRelVer("org.sireum.version.idea")
  if (isDev) devVer else ver
}

val jbrVer: String = {
  val (devVer, ver) = devRelVer("org.sireum.version.jbr")
  if (isDev) devVer else ver
}

val jbrBuildVer: String = {
  val (devVer, ver) = devRelVer("org.sireum.version.jbr.build")
  if (isDev) devVer else ver
}

val pwd7z: Os.Path = Os.kind match {
  case Os.Kind.Mac => homeBin / "mac" / "7za"
  case Os.Kind.LinuxArm => homeBin / "linux" / "arm" / "7za"
  case Os.Kind.Linux => homeBin / "linux" / "7za"
  case Os.Kind.Win => homeBin / "win" / "7za.exe"
  case _ => halt("Infeasible")
}

def downloadPlugins(): Unit = {
  for (p <- plugins.values) {
    val ver = p.version(isDev)
    val zip = zipName(p.id, ver)
    if (!(pluginsCacheDir / zip).exists) {
      val pidOps = ops.StringOps(p.id)
      val prefix: String = "sireum-"
      val url: String =
        if (pidOps.startsWith(prefix)) {
          val repo = pidOps.substring(prefix.size, p.id.size)
          s"https://github.com/sireum/$repo/releases/download/$ver/$repo.zip"
        } else {
          s"https://plugins.jetbrains.com/plugin/download?pr=idea&updateId=$ver"
        }
      print(s"Downloading ${p.id} plugin from $url ... ")
      (pluginsCacheDir / zip).downloadFrom(url)
      println("done!")
    }
  }
}

def extractPlugins(): Unit = {
  for (p <- plugins.values) {
    val zip = zipName(p.id, p.version(isDev))
    val zipPath = ideaCacheDir / "plugins" / zip
    if (p.isJar) {
      print(s"Copying ${p.id} plugin ... ")
      zipPath.copyOverTo(pluginsDir / s"${p.id}.jar")
      println("done!")
    } else {
      print(s"Extracting ${p.id} plugin from $zipPath ... ")
      proc"$pwd7z x -y $zipPath".at(pluginsDir).runCheck()
      println("done!")
    }
  }
}

def patchIdeaProperties(p: Os.Path): Unit = {
  if (isServer) {
    return
  }
  print(s"Patching $p ... ")
  val content = p.read
  val ult: String = if (isUltimate) "-ult" else ""
  val newContent: String = platform match {
    case "mac" =>
      val contentOps = ops.StringOps(content)
      val i = contentOps.stringIndexOf("idea.paths.selector")
      val j = contentOps.stringIndexOfFrom("<string>", i)
      val k = contentOps.stringIndexOfFrom("</string>", j)
      if (isLocal) s"${contentOps.substring(0, j)}<string>.SireumIVE$ult$devSuffix</string>\n        <key>idea.config.path</key>\n        <string>$settingsDir/.SireumIVE$ult$devSuffix/config</string>\n        <key>idea.system.path</key>\n        <string>$settingsDir/.SireumIVE$ult$devSuffix/system</string>\n        <key>idea.log.path</key>\n        <string>$settingsDir/.SireumIVE$ult$devSuffix/log</string>\n        <key>idea.plugins.path</key>\n        <string>$settingsDir/.SireumIVE$ult$devSuffix/plugins${contentOps.substring(k, content.size)}"
      else s"${contentOps.substring(0, j)}<string>SireumIVE$ult$devSuffix${contentOps.substring(k, content.size)}"
    case "win" =>
      s"idea.config.path=$settingsDir/.SireumIVE$ult$devSuffix/config\r\nidea.system.path=$settingsDir/.SireumIVE$ult$devSuffix/system\r\nidea.log.path=$settingsDir/.SireumIVE$ult$devSuffix/log\r\nidea.plugins.path=$settingsDir/.SireumIVE$ult$devSuffix/plugins\r\n$content"
    case _ if platform === "linux" || platform === "linux/arm" =>
      s"idea.config.path=$settingsDir/.SireumIVE$ult$devSuffix/config\nidea.system.path=$settingsDir/.SireumIVE$ult$devSuffix/system\nidea.log.path=$settingsDir/.SireumIVE$ult$devSuffix/log\nidea.plugins.path=$settingsDir/.SireumIVE$ult$devSuffix/plugins\n$content"
  }
  p.writeOver(newContent)
  println("done!")
}

def patchVMOptions(p: Os.Path): Unit = {
  print(s"Patching $p ... ")
  val content = ops.StringOps(p.read).trim
  val newContent: String =
    if (Os.isWin) s"$content\r\n-Dorg.sireum.ive=Sireum$devSuffix\r\n-Dorg.sireum.ive.dev=$isDev\r\n-Dfile.encoding=UTF-8\r\n"
    else s"$content\n-Dorg.sireum.ive=Sireum$devSuffix\n-Dorg.sireum.ive.dev=$isDev\n-Dfile.encoding=UTF-8\n"
  p.writeOver(newContent)
  println("done!")
}

def patchExe(): Unit = {
  if (isUltimate) {
    return
  }
  val rhExe = "ResourceHacker.exe"
  val rhDir = home / "resources" / "rh"
  rhDir.mkdirAll()
  if (!(rhDir / rhExe).exists) {
    print("Downloading ResourceHacker ... ")
    (rhDir / "rh.zip").downloadFrom("http://angusj.com/resourcehacker/resource_hacker.zip")
    proc"$pwd7z x rh.zip $rhExe".at(rhDir).runCheck()
    println("done!")
  }
  val binDir = ideaDir / "bin"
  val idea64Exe = binDir / "idea64.exe"
  print(s"Patching $idea64Exe ... ")
  if (Os.isWin) {
    proc"${rhDir / rhExe} -open .\\${idea64Exe.name} -save .\\${idea64Exe.name} -action addoverwrite -res .\\idea.ico -mask ICONGROUP,2000,1033".at(binDir).runCheck()
  } else {
    proc"wine ${rhDir / rhExe} -open .\\${idea64Exe.name} -save .\\${idea64Exe.name} -action addoverwrite -res .\\idea.ico -mask ICONGROUP,2000,1033".at(binDir).runCheck()
  }
  println("done!")
}

def patchIcon(isWin: B): Unit = {
  if (isUltimate) {
    return
  }
  val iconsPath = home / "resources" / "distro" / "icons"
  val (dirPath, srcFilename, filename): (Os.Path, String, String) = platform match {
    case string"mac" =>
      if (isDev) {
        (iconsPath / "idea-dev.svg").copyOverTo(sireumAppDir / "Contents" / "bin" / "idea.svg")
        (sireumAppDir / "Contents" / "Resources", "idea-dev.icns", "idea.icns")
      } else {
        (iconsPath / "idea.svg").copyOverTo(sireumAppDir / "Contents" / "bin" / "idea.svg")
        (sireumAppDir / "Contents" / "Resources", "idea.icns", "idea.icns")
      }
    case string"win" =>
      if (isDev) {
        (iconsPath / "idea-dev.svg").copyOverTo(ideaDir / "bin" / "idea.svg")
        (ideaDir / "bin", "idea-dev.ico", "idea.ico")
      } else {
        (iconsPath / "idea.svg").copyOverTo(ideaDir / "bin" / "idea.svg")
        (ideaDir / "bin", "idea_CE.ico", "idea.ico")
      }
    case _ if platform === "linux" || platform === "linux/arm" =>
      if (isDev) {
        (iconsPath / "idea-dev.svg").copyOverTo(ideaDir / "bin" / "idea.svg")
        (ideaDir / "bin", "idea-dev.png", "idea.png")
      } else {
        (iconsPath / "idea.svg").copyOverTo(ideaDir / "bin" / "idea.svg")
        (ideaDir / "bin", "idea.png", "idea.png")
      }
  }
  print(s"Replacing icon ${dirPath / filename} ... ")
  (iconsPath / srcFilename).copyOverTo(dirPath / filename)
  println("done!")
  if (isWin) {
    patchExe()
  }
}

def patchApp(): Unit = {
  if (isUltimate) {
    return
  }
  val iconsPath = home / "resources" / "distro" / "icons"
  val appJar = libDir / "app.jar"
  val tempDir = libDir / "app-temp"
  tempDir.removeAll()
  tempDir.mkdirAll()
  print(s"Patching $appJar ... ")
  appJar.unzipTo(tempDir)
  val entriesToUpdate: ISZ[String] =
    for (f <- ISZ[String](
      "Logo_welcomeScreen.png",
      "Logo_welcomeScreen@2x.png",
      "Logo_welcomeScreen_CE.png",
      "Logo_welcomeScreen_CE@2x.png",
      "icon.png",
      "icon@2x.png",
      "icon_128.png",
      "icon_CE.png",
      "icon_CE@2x.png",
      "icon_CE_128.png",
      "icon_CE_128@2x.png",
      "icon_CE_64.png",
      "icon_CE_64@2x.png",
      "icon_CEsmall.png",
      "icon_CEsmall@2x.png",
      "icon_small@2x.png",
      "icon_small@2x_dark.png",
      "icon_small_dark.png",
      "icon_small.png",
      "idea_CE.ico",
      "idea_logo_welcome.png"
    ) if !ignoredIcons.contains(f)) yield f
  for (e <- entriesToUpdate) {
    (iconsPath / e).copyOverTo(tempDir / e)
  }

  val distroDir = home / "resources" / "distro"
  val iai = tempDir / "idea" / "IdeaApplicationInfo.xml"
  val content = iai.read
  iai.writeOver(
    ops.StringOps(ops.StringOps(content).
      replaceAllLiterally("svg-small=\"/idea-ce_16.svg\"", "svg-small=\"/idea-ce_16.png\"")).
      replaceAllLiterally("svg-small=\"/idea-ce-eap_16.svg\"", "svg-small=\"/idea-ce_16.png\""))
  for (e <- ISZ("idea-ce_16.svg", "idea-ce_16@2x.svg", "idea-ce-eap_16.svg", "idea-ce-eap_16@2x.svg")) {
    (tempDir / e).removeAll()
  }
  val d = distroDir / "images" / (if (isDev) "dev" else "release")
  for (e <- ISZ("idea_community_about.png", "idea_community_about@2x.png", "idea_community_logo.png", "idea_community_logo@2x.png", "idea-ce.svg", "idea-ce-eap.svg", "idea-ce_16.png", "idea-ce_16@2x.png")) {
    (d / e).copyOverTo(tempDir / e)
  }
  val appTempJar = libDir / "app-temp.jar"
  tempDir.zipTo(appTempJar)
  appTempJar.moveOverTo(appJar)
  tempDir.removeAll()
  println("done!")
}

def deleteSources(): Unit = {
  for (p <- Os.Path.walk(ideaDir, F, T, (p: Os.Path) => p.ext === "java" || p.ext === "scala")) {
    p.removeAll()
  }
}

def deletePlugins(): Unit = {
  if (isServer) {
    return
  }
  for (p <- delPlugins) {
    print(s"Removing $p plugin ... ")
    (pluginsDir / p).removeAll()
    println("done!")
  }
}

def setupMac(ideaDrop: Os.Path): Unit = {
  proc"hdiutil attach $ideaDrop".at(home).runCheck()
  var dirPath = Os.home
  for (p <- Os.path("/Volumes").list if ops.StringOps(p.name).startsWith("IntelliJ")) {
    dirPath = p
  }
  var appPath = dirPath
  for (p <- dirPath.list if ops.StringOps(p.name).endsWith(".app")) {
    appPath = p
  }
  sireumAppDir.up.mkdirAll()
  appPath.copyOverTo(sireumAppDir)
  proc"hdiutil eject $dirPath".at(home).runCheck()
  println("done!")
  deleteSources()
  deletePlugins()
  extractPlugins()
  patchIcon(F)
  patchApp()
  patchIdeaProperties(sireumAppDir / "Contents" / "Info.plist")
  patchVMOptions(sireumAppDir / "Contents" / "bin" / "idea.vmoptions")
  proc"codesign --force --deep --sign - $sireumAppDir".run()
}

def setupLinux(ideaDrop: Os.Path): Unit = {
  if (isServer) {
    val ideaVerOps = ops.StringOps(ideaVer)
    val rel: String = ideaVerOps.split((c: C) => c === '.') match {
      case ISZ(_, r, _) => r
      case ISZ(_, r) => r
    }
    val namePart = s"_ideaIU-${ideaVerOps.substring(2, 4)}$rel"
    var dist = Os.home / ".cache" / "JetBrains" / "RemoteDev" / "dist"
    for (p <- dist.list if p.isDir && ops.StringOps(p.name).contains(namePart)) {
      if (dist.name === "dist" || p.lastModified >= dist.lastModified) {
        dist = p
      }
    }
    if (dist.name === "dist") {
      eprintln(s"Could not find IntelliJ Ultimate for remote development in $dist")
      Os.exit(-1)
    }
    ideaDir.mklink(dist)
  } else {
    val ideaDirParent = ideaDir.up.canon
    proc"tar xfz $ideaDrop".at(ideaDirParent).runCheck()

    for (p <- ideaDirParent.list if ops.StringOps(p.name).startsWith(s"idea-${if (isUltimate) "IU" else "IC"}-")) {
      p.moveOverTo(ideaDir)
    }
  }
  deleteSources()
  if (!isServer) {
    println("done!")
  }
  deletePlugins()
  extractPlugins()
  patchIcon(F)
  patchApp()
  patchIdeaProperties(ideaDir / "bin" / "idea.properties")
  patchVMOptions(ideaDir / "bin" / "idea64.vmoptions")
  (ideaDir / "bin" / "idea.vmoptions").removeAll()
  val ideash = ideaDir / "bin" / "idea.sh"
  if (platform == "linux/arm") {
    println(s"Patching $ideash ...")
    ideash.writeOver(ops.StringOps(ideash.read).replaceAllLiterally(""""x86_64"""", """"aarch64""""))
    val jbrFilename = s"jbr-$jbrVer-linux-aarch64-b$jbrBuildVer.tar.gz"
    val jbrUrl = s"https://cache-redirector.jetbrains.com/intellij-jbr/$jbrFilename"
    val jbrDrop = ideaCacheDir / jbrFilename
    if (!jbrDrop.exists) {
      print(s"Downloading from $jbrUrl ... ")
      (ideaCacheDir / jbrFilename).downloadFrom(jbrUrl)
      println("done!")
    }
    println(s"Replacing ${ideaDir / "jbr"} ...")
    (ideaDir / "jbr").removeAll()
    proc"tar xfz $jbrDrop".at(ideaDir).runCheck()

    if (!buildSfx) {
      val ideaVerOps = ops.StringOps(ideaVer)
      val ideaMajorVer = ideaVerOps.substring(0, ideaVerOps.lastIndexOf('.'))
      val config = Os.home / ".config" / "JetBrains" / s"IdeaIC$ideaMajorVer" / "idea.properties"
      val configContent = s"idea.filewatcher.executable.path=${home / "bin" / platform / "fsnotifier"}"
      if (config.exists) {
        println(s"Please ensure the following line is in the existing $config")
        println(configContent)
      } else {
        config.up.mkdirAll()
        config.writeOver(configContent)
        println(s"Wrote $config")
      }
    }
  }
  if (!isServer) {
    ideash.moveOverTo(ideaDir / "bin" / "IVE.sh")
    ideash.mklink(ideaDir / "bin" / "IVE.sh")
  }
}


def setupWin(ideaDrop: Os.Path): Unit = {
  ideaDir.mkdirAll()
  proc"$pwd7z x -y $ideaDrop".at(ideaDir).runCheck()
  (ideaDir / "$PLUGINSDIR").removeAll()
  deleteSources()
  println("done!")
  deletePlugins()
  extractPlugins()
  patchIcon(T)
  patchApp()
  patchIdeaProperties(ideaDir / "bin" / "idea.properties")
  patchVMOptions(ideaDir / "bin" / "idea64.exe.vmoptions")
  (ideaDir / "bin" / "idea.exe").removeAll()
  (ideaDir / "bin" / "idea.exe.vmoptions").removeAll()
  (ideaDir / "bin" / "idea64.exe").moveOverTo(ideaDir / "bin" / "IVE.exe")
  (ideaDir / "bin" / "idea64.exe.vmoptions").moveOverTo(ideaDir / "bin" / "IVE.exe.vmoptions")
}

def pack(): Unit = {
  val plat = ops.StringOps(platform).replaceAllChars('/', '-')
  val sfxSuffix: String = if (platform === "win") ".exe" else ".sfx"
  val r = home / "distro" / s"$plat$devSuffix$sfxSuffix"
  r.removeAll()
  print(s"Packaging $r ... ")
  val distro7z = s"$plat.7z"
  val setupDir = home / "distro" / (if (isDev) "dev" else "release")
  val oldPwd = home
  val (repoDir, distroDir): (Os.Path, Os.Path) = {
    val dir = setupDir / s"Sireum$devSuffix"
    for (rp <- distroMap.get(platform).get if rp(0) =!= "..") {
      (dir /+ rp).up.mkdirAll()
      val orp = oldPwd /+ rp
      if (orp.exists) {
        orp.copyOverTo(dir /+ rp)
      } else {
        println()
        println(s"Warning: Could not find $orp")
      }
    }
    (oldPwd, dir)
  }
  val sfx = repoDir / "distro" / s"$plat$devSuffix$sfxSuffix"
  val files: ISZ[String] =
    for (p <- distroMap.get(platform).get.map((rp: ISZ[String]) => Os.path(distroDir.name) /+ rp)) yield p.string
  val cmd = ISZ[String](pwd7z.string, "a", distro7z) ++ files

  Os.proc(cmd).at(distroDir.up).runCheck()
  platform match {
    case string"win" =>
      sfx.mergeFrom(ISZ(repoDir / "bin" / "win" / "7z.sfx", distroDir.up / "config.txt", distroDir.up / distro7z))
    case _ =>
      sfx.mergeFrom(ISZ(repoDir / "bin" / platform / "7z.sfx", distroDir.up / distro7z))
  }
  (distroDir.up / distro7z).removeAll()
  println("done!")
  distroDir.removeAll()
}

def patchSecurityManager(p: Os.Path): Unit = {
  print(s"Patching $p ... ")
  Asm.rewriteSetSecurityManager(p)
  println("done!")
}

def build(): Unit = {
  println(s"Setting up Sireum$devSuffix IVE $platform in $ideaDir ...")
  val suffix: String =
    if (platform == "mac" && ops.StringOps(proc"uname -m".redirectErr.run().out).trim === "arm64") ideaExtMap.get("mac/arm").get
    else ideaExtMap.get(platform).get
  val url: String = s"https://download.jetbrains.com/idea/idea${if (isUltimate) "IU" else "IC"}-$ideaVer$suffix"
  val urlOps = ops.StringOps(url)
  val filename = urlOps.substring(urlOps.lastIndexOf('/') + 1, url.size)
  val buildDir = ideaDir.up.canon
  buildDir.mkdirAll()
  val ideaDrop = ideaCacheDir / filename
  if (!ideaDrop.exists && !isServer) {
    print(s"Downloading from $url ... ")
    (ideaCacheDir / filename).downloadFrom(url)
    println("done!")
  }
  downloadPlugins()
  if (!isServer) {
    print(s"Extracting $ideaDrop ... ")
  }
  ideaDir.removeAll()
  platform match {
    case string"mac" => setupMac(ideaDrop)
    case string"win" => setupWin(ideaDrop)
    case _ if platform === "linux" || platform === "linux/arm" => setupLinux(ideaDrop)
  }
  patchSecurityManager(pluginsDir / "Scala" / "lib" / "jps" / "nailgun.jar")
  val sireumJar = pluginsDir / "sireum-intellij-plugin" / "lib" / "sireum.jar"
  val homeBinSireumJar = homeBin / "sireum.jar"
  sireumJar.removeAll()
  sireumJar.mklink(homeBinSireumJar)
  if (buildSfx) {
    pack()
  }
  println("Done!")
}

build()
