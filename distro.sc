import java.io.{File, FileInputStream}
import java.nio.file.StandardOpenOption
import java.util.jar.JarInputStream

import ammonite.ops._

import scala.jdk.CollectionConverters._

object distro {
  final case class Plugin(id: String, isDev: Boolean, isJar: Boolean, devVer: String, ver: String) {
    def version(isDev: Boolean): String = if (isDev) devVer else ver
  }

  val delPlugins = Seq("android", "Kotlin")
  val pluginPrefix = "org.sireum.version.plugin."
  val properties = org.sireum.mill.SireumModule.properties

  val ignoredIcons = Set(
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
    "idea_logo_background.png",
  )
  val ideaExtMap = Map("mac" -> ".dmg", "win" -> ".win.zip", "linux" -> ".tar.gz", "linux/arm" -> ".tar.gz")

  val cacheDir: Path = {
    Option(System.getenv("SIREUM_CACHE")) match {
      case Some(p) => os.Path(new File(p).getCanonicalFile)
      case _ =>
        val r = os.Path(new File(System.getProperty("user.home")).getCanonicalFile) / 'Downloads / 'sireum
        os.makeDir.all(r)
        r
    }
  }
  val ideaCacheDir = cacheDir / 'idea
  val pluginsCacheDir = ideaCacheDir / 'plugins

  val plugins: Map[String, Plugin] = {
    var r = Map[String, Plugin]()
    for (key <- properties.keys().asScala.map(_.toString) if key.startsWith(pluginPrefix)) {
      val id = key.substring(pluginPrefix.length)
      properties.get(key).toString.split(',') match {
        case Array(isDev, isJar, devVer) =>
          r += id -> Plugin(id, isDev.toBoolean, isJar.toBoolean, devVer, devVer)
        case Array(isDev, isJar, devVer, ver) =>
          r += id -> Plugin(id, isDev.toBoolean, isJar.toBoolean, devVer, ver)
      }
    }
    r
  }

  val distroMap = Map[String, Seq[RelPath]](
    "win" -> Seq(
      RelPath(Vector("bin", "scala"), 0),
      RelPath(Vector("bin", "win", "idea"), 0),
      RelPath(Vector("bin", "win", "java"), 0),
      RelPath(Vector("bin", "win", "z3"), 0),
      RelPath(Vector("bin", "install", "clion.cmd"), 0),
      RelPath(Vector("bin", "install", "fmide.cmd"), 0),
      RelPath(Vector("bin", "install", "graal.cmd"), 0),
      RelPath(Vector("bin", "mill.bat"), 0),
      RelPath(Vector("bin", "sireum.bat"), 0),
      RelPath(Vector("bin", "sireum.jar"), 0),
      RelPath(Vector("bin", "slang-run.bat"), 0),
      RelPath(Vector("bin", "VER"), 0),
      RelPath(Vector("lib"), 0),
      RelPath(Vector("license.txt"), 0),
      RelPath(Vector("versions.properties"), 0),
      RelPath(Vector("setup.bat"), 1)
    ),
    "linux" -> Seq(
      RelPath(Vector("bin", "scala"), 0),
      RelPath(Vector("bin", "linux", "idea"), 0),
      RelPath(Vector("bin", "linux", "java"), 0),
      RelPath(Vector("bin", "linux", "z3"), 0),
      RelPath(Vector("bin", "linux", "sireum"), 0),
      RelPath(Vector("bin", "install", "acl2.cmd"), 0),
      RelPath(Vector("bin", "install", "clion.cmd"), 0),
      RelPath(Vector("bin", "install", "fmide.cmd"), 0),
      RelPath(Vector("bin", "install", "graal.cmd"), 0),
      RelPath(Vector("bin", "mill"), 0),
      RelPath(Vector("bin", "sireum"), 0),
      RelPath(Vector("bin", "sireum.jar"), 0),
      RelPath(Vector("bin", "slang-run.sh"), 0),
      RelPath(Vector("bin", "VER"), 0),
      RelPath(Vector("lib"), 0),
      RelPath(Vector("license.txt"), 0),
      RelPath(Vector("versions.properties"), 0),
      RelPath(Vector("setup"), 1)
    ),
    "linux/arm" -> Seq(
      RelPath(Vector("bin", "scala"), 0),
      RelPath(Vector("bin", "linux", "arm", "fsnotifier"), 0),
      RelPath(Vector("bin", "linux", "arm", "idea"), 0),
      RelPath(Vector("bin", "linux", "arm", "java"), 0),
      RelPath(Vector("bin", "install", "clion.cmd"), 0),
      RelPath(Vector("bin", "mill"), 0),
      RelPath(Vector("bin", "sireum"), 0),
      RelPath(Vector("bin", "sireum.jar"), 0),
      RelPath(Vector("bin", "slang-run.sh"), 0),
      RelPath(Vector("bin", "VER"), 0),
      RelPath(Vector("lib"), 0),
      RelPath(Vector("license.txt"), 0),
      RelPath(Vector("versions.properties"), 0),
      RelPath(Vector("setup"), 1)
    ),
    "mac" -> Seq(
      RelPath(Vector("bin", "scala"), 0),
      RelPath(Vector("bin", "mac", "idea"), 0),
      RelPath(Vector("bin", "mac", "java"), 0),
      RelPath(Vector("bin", "mac", "z3"), 0),
      RelPath(Vector("bin", "mac", "sireum"), 0),
      RelPath(Vector("bin", "install", "clion.cmd"), 0),
      RelPath(Vector("bin", "install", "fmide.cmd"), 0),
      RelPath(Vector("bin", "install", "graal.cmd"), 0),
      RelPath(Vector("bin", "mill"), 0),
      RelPath(Vector("bin", "sireum"), 0),
      RelPath(Vector("bin", "sireum.jar"), 0),
      RelPath(Vector("bin", "slang-run.sh"), 0),
      RelPath(Vector("bin", "VER"), 0),
      RelPath(Vector("lib"), 0),
      RelPath(Vector("license.txt"), 0),
      RelPath(Vector("versions.properties"), 0),
      RelPath(Vector("setup"), 1)
    )
  )

  def devRelVer(key: String): (String, String) = {
    properties.get(key).toString.split(',') match {
      case Array(devVer, ver) => (devVer.trim, ver.trim)
      case Array(ver) => (ver.trim, ver.trim)
    }
  }

  def zipName(id: String, version: String): String = s"$id-$version.zip"

}

class distro(platform: String, isDev: Boolean, sfx: Boolean, clone: Boolean) {

  import distro._

  val devSuffix = if (isDev) "-dev" else ""
  val ideaDir = os.pwd / 'bin / RelPath(platform) / 'idea
  val sireumAppDir = ideaDir / s"IVE.app"

  val pluginsDir =
    if (platform == "mac") sireumAppDir / 'Contents / 'plugins
    else ideaDir / 'plugins

  val libDir =
    if (platform == "mac") sireumAppDir / 'Contents / 'lib
    else ideaDir / 'lib

  val version = {
    val v = %%("git", "log", "-n", "1", "--date=format:%Y%m%d", "--pretty=format:4.%cd.%h")(pwd).out.lines.head.trim
    os.write.over(pwd / 'bin / 'VER, v)
    v
  }

  val ideaVer = {
    val (devVer, ver) = devRelVer("org.sireum.version.idea")
    if (isDev) devVer else ver
  }

  val jbrVer = {
    val (devVer, ver) = devRelVer("org.sireum.version.jbr")
    if (isDev) devVer else ver
  }

  val jbrBuildVer = {
    val (devVer, ver) = devRelVer("org.sireum.version.jbr.build")
    if (isDev) devVer else ver
  }

  val z7: RelPath = {
    val (plat, exe): (String, String) =
      if (scala.util.Properties.isMac) ("mac", "7za")
      else if (scala.util.Properties.isLinux)
        if (%%("uname", "-m")(pwd).out.trim == "aarch64") ("linux/arm", "7za")
        else ("linux", "7za")
      else if (scala.util.Properties.isWin) ("win", "7za.exe")
      else ("unsupported", "")
    RelPath("bin") / RelPath(plat) / exe
  }
  val pwd7z = os.pwd / z7

  def downloadPlugins(): Unit = {
    for (p <- plugins.values) {
      val ver = p.version(isDev)
      val zip = zipName(p.id, ver)
      if (!os.exists(pluginsCacheDir / zip)) {
        val url =
          if (p.id.startsWith("sireum-")) {
            val repo = p.id.substring("sireum-".length)
            s"https://github.com/sireum/$repo/releases/download/$ver/$repo.zip"
          } else s"https://plugins.jetbrains.com/plugin/download?pr=idea&updateId=$ver"
        print(s"Downloading ${p.id} plugin from $url ... ")
        %%('curl, "-JLso", zip, url)(pluginsCacheDir)
        println("done!")
      }
    }
  }

  def extractPlugins(): Unit = {
    for (p <- plugins.values) {
      val zip = zipName(p.id, p.version(isDev))
      val zipPath = ideaCacheDir / 'plugins / zip
      if (p.isJar) {
        print(s"Copying ${p.id} plugin ... ")
        os.copy(zipPath, pluginsDir / s"${p.id}.jar")
        println("done!")
      } else {
        print(s"Extracting ${p.id} plugin from $zipPath ... ")
        %%(pwd7z, 'x, "-y", zipPath)(pluginsDir)
        println("done!")
      }
    }
  }

  def patchIdeaProperties(p: Path): Unit = {
    print(s"Patching $p ... ")
    val content = os.read(p)
    val newContent = platform match {
      case "mac" =>
        val i = content.indexOf("idea.paths.selector")
        val j = content.indexOf("<string>", i)
        val k = content.indexOf("</string>", j)
        content.substring(0, j) + s"<string>SireumIVE$devSuffix" + content.substring(k)
      case "win" =>
        s"idea.config.path=$${user.home}/.SireumIVE$devSuffix/config\r\nidea.system.path=$${user.home}/.SireumIVE$devSuffix/system\r\nidea.log.path=$${user.home}/.SireumIVE$devSuffix/log\r\nidea.plugins.path=$${user.home}/.SireumIVE$devSuffix/plugins\r\n" + content
      case "linux" | "linux/arm" =>
        s"idea.config.path=$${user.home}/.SireumIVE$devSuffix/config\nidea.system.path=$${user.home}/.SireumIVE$devSuffix/system\nidea.log.path=$${user.home}/.SireumIVE$devSuffix/log\nidea.plugins.path=$${user.home}/.SireumIVE$devSuffix/plugins\n" + content
    }
    os.remove.all(p)
    os.write(p, newContent)
    println("done!")
  }

  def patchVMOptions(p: Path): Unit = {
    print(s"Patching $p ... ")
    val content = read ! p
    val newContent = platform match {
      case "win" =>
        s"${content.trim}\r\n-Dorg.sireum.ive=Sireum$devSuffix\r\n-Dorg.sireum.ive.dev=$isDev\r\n-Dfile.encoding=UTF-8\r\n"
      case _ =>
        s"${content.trim}\n-Dorg.sireum.ive=Sireum$devSuffix\n-Dorg.sireum.ive.dev=$isDev\n-Dfile.encoding=UTF-8\n"
    }
    os.remove.all(p)
    os.write(p, newContent)
    println("done!")
  }

  def patchImages(): Unit = {
    val resourcesJar = libDir / "resources.jar"
    val distroDir = pwd / 'resources / 'distro
    print(s"Patching $resourcesJar ... ")
    rm ! distroDir / 'idea
    %%(
      pwd7z,
      'x,
      resourcesJar,
      "idea/IdeaApplicationInfo.xml"
    )(distroDir)
    val iai = distroDir / 'idea / "IdeaApplicationInfo.xml"
    val content = read ! iai
    write.over(iai, content.replaceAllLiterally("svg-small=\"/idea-ce_16.svg\"", "svg-small=\"/idea-ce_16.png\"").replaceAllLiterally("svg-small=\"/idea-ce-eap_16.svg\"", "svg-small=\"/idea-ce_16.png\""))
    %%(
      pwd7z,
      'a,
      resourcesJar,
      "idea/IdeaApplicationInfo.xml"
    )(distroDir)
    rm ! distroDir / 'idea
    %%(
      pwd7z,
      'd,
      resourcesJar,
      "idea-ce_16.svg",
      "idea-ce_16@2x.svg",
      "idea-ce-eap_16.svg",
      "idea-ce-eap_16@2x.svg"
    )(distroDir / 'images)
    if (isDev) {
      %%(
        pwd7z,
        'a,
        resourcesJar,
        "idea_community_about.png",
        "idea_community_about@2x.png",
        "idea_community_logo.png",
        "idea_community_logo@2x.png",
        "idea-ce.svg",
        "idea-ce-eap.svg",
        "idea-ce_16.png",
        "idea-ce_16@2x.png"
      )(distroDir / 'images / 'dev)
    } else {
      %%(
        pwd7z,
        'a,
        resourcesJar,
        "idea_community_about.png",
        "idea_community_about@2x.png",
        "idea_community_logo.png",
        "idea_community_logo@2x.png",
        "idea-ce.svg",
        "idea-ce-eap.svg",
        "idea-ce_16.png",
        "idea-ce_16@2x.png"
      )(distroDir / 'images / 'release)
    }
    println("done!")
  }

  def patchExe(): Unit = {
    val rhExe = "ResourceHacker.exe"
    val rhDir = pwd / 'resources / 'rh
    os.makeDir.all(rhDir)
    if (!os.exists(rhDir / rhExe)) {
      print("Downloading ResourceHacker ... ")
      %%('curl, "-JLso", "rh.zip", "http://angusj.com/resourcehacker/resource_hacker.zip")(rhDir)
      %%(pwd7z, 'x, "rh.zip", rhExe)(rhDir)
      println("done!")
    }
    val binDir = ideaDir / 'bin
    val idea64Exe = binDir / "idea64.exe"
    print(s"Patching $idea64Exe ... ")
    if (scala.util.Properties.isWin) {
      %%(
        rhDir / rhExe,
        "-open",
        s".\\${idea64Exe.last}",
        "-save",
        s".\\${idea64Exe.last}",
        "-action",
        'addoverwrite,
        "-res",
        ".\\idea.ico",
        "-mask",
        "ICONGROUP,2000,1033"
      )(binDir)
    } else {
      %%(
        'wine,
        rhDir / rhExe,
        "-open",
        s".\\${idea64Exe.last}",
        "-save",
        s".\\${idea64Exe.last}",
        "-action",
        'addoverwrite,
        "-res",
        ".\\idea.ico",
        "-mask",
        "ICONGROUP,2000,1033"
      )(binDir)
    }
    println("done!")
  }

  def patchIcon(): Unit = {
    val iconsPath = pwd / 'resources / 'distro / 'icons
    val (dirPath, srcFilename, filename) = platform match {
      case "mac" =>
        if (isDev) {
          os.copy.over(iconsPath / "idea-dev.svg", sireumAppDir / 'Contents / 'bin / "idea.svg")
          (sireumAppDir / 'Contents / 'Resources, "idea-dev.icns", "idea.icns")
        } else {
          os.copy.over(iconsPath / "idea.svg", sireumAppDir / 'Contents / 'bin / "idea.svg")
          (sireumAppDir / 'Contents / 'Resources, "idea.icns", "idea.icns")
        }
      case "win" =>
        if (isDev) (ideaDir / 'bin, "idea-dev.ico", "idea.ico")
        else (ideaDir / 'bin, "idea.ico", "idea.ico")
      case "linux" | "linux/arm" =>
        if (isDev) {
          os.copy.over(iconsPath / "idea-dev.svg", ideaDir / 'bin / "idea.svg")
          (ideaDir / 'bin, "idea-dev.png", "idea.png")
        } else {
          os.copy.over(iconsPath / "idea.svg", ideaDir / 'bin / "idea.svg")
          (ideaDir / 'bin, "idea.png", "idea.png")
        }
    }
    print(s"Replacing icon ${dirPath / filename} ... ")
    os.copy.over(iconsPath / srcFilename, dirPath / filename)
    println("done!")
    if (platform == "win") patchExe()
    val iconsJar = libDir / "icons.jar"
    print(s"Patching $iconsJar ... ")
    val jis = new JarInputStream(new FileInputStream(iconsJar.toIO))
    var entries = Set[String]()
    var done = false
    do {
      Option(jis.getNextEntry) match {
        case Some(e) if !e.isDirectory => entries += e.getName
        case None => done = true
        case _ =>
      }
    } while (!done)
    jis.close()
    val entriesToUpdate =
      (for (f <- os.list(iconsPath) if !ignoredIcons.contains(f.last))
        yield {
          require(entries.contains(f.last), s"File ${f.last} is not in $iconsJar.")
          f.last : os.Shellable
        }).toVector
    os.proc(Seq[os.Shellable](pwd7z, 'a, iconsJar) ++ entriesToUpdate: _*).call(cwd = iconsPath)
    println("done!")
  }

  def build(): Unit = {
    println(s"Setting up Sireum$devSuffix IVE ${platform}64 in $ideaDir ...")
    val url =
      s"https://download.jetbrains.com/idea/ideaIC-$ideaVer${ideaExtMap(platform)}"
    val filename = url.substring(url.lastIndexOf('/') + 1)
    val buildDir = ideaDir / os.up
    os.makeDir.all(buildDir)
    val ideaDrop = ideaCacheDir / filename
    if (!os.exists(ideaDrop)) {
      print(s"Downloading from $url ... ")
      %%('curl, "-JLso", filename, url)(ideaCacheDir)
      println("done!")
    }
    downloadPlugins()
    print(s"Extracting $ideaDrop ... ")
    os.remove.all(ideaDir)
    platform match {
      case "mac" => setupMac(ideaDrop)
      case "linux" | "linux/arm" => setupLinux(ideaDrop)
      case "win" => setupWin(ideaDrop)
    }
    val sireumJar = pluginsDir / "sireum-kekinian-intellij" / 'lib / "sireum.jar"
    val link = (pwd / 'bin / "sireum.jar").relativeTo(sireumJar / os.up)
    os.remove.all(sireumJar)
    mkLink(sireumJar, link)
    if (sfx) pack()
    println("Done!")
  }

  def mkLink(p: Path, target: FilePath): Unit = {
    if (scala.util.Properties.isWin)
      %%('cmd, "/c", s"""cd /d ${p / os.up} && dir && mklink ${p.last} "${target.toNIO}"""")(pwd)
    else os.symlink(p, target)
  }

  def deletePlugins(): Unit = {
    for (p <- delPlugins) {
      print(s"Removing $p plugin ... ")
      os.remove.all(pluginsDir / p)
      println("done!")
    }
  }

  def setupMac(ideaDrop: Path): Unit = {
    %%('hdiutil, 'attach, ideaDrop)(pwd)
    val dirPath = os.list(os.root / 'Volumes).find(_.last.startsWith("IntelliJ")).get
    val appPath = dirPath / os.list(dirPath).find(_.last.endsWith(".app")).get.last
    os.makeDir.all(sireumAppDir / os.up)
    %%('cp, "-R", appPath, sireumAppDir)(pwd)
    %%("hdiutil", "eject", dirPath)(pwd)
    println("done!")
    deletePlugins()
    extractPlugins()
    patchIcon()
    patchImages()
    patchIdeaProperties(sireumAppDir / 'Contents / "Info.plist")
    patchVMOptions(sireumAppDir / 'Contents / 'bin / "idea.vmoptions")
  }

  def setupLinux(ideaDrop: Path): Unit = {
    val ideaDirParent = ideaDir / os.up
    %%('tar, 'xfz, ideaDrop)(ideaDirParent)
    os.move(os.list(ideaDirParent).find(p => p.last.startsWith("idea-IC-")).get, ideaDir)
    println("done!")
    deletePlugins()
    extractPlugins()
    patchIcon()
    patchImages()
    patchIdeaProperties(ideaDir / 'bin / "idea.properties")
    patchVMOptions(ideaDir / 'bin / "idea64.vmoptions")
    os.remove(ideaDir / 'bin / "idea.vmoptions")
    val ideash = ideaDir / 'bin / "idea.sh"
    if (platform == "linux/arm") {
      println(s"Patching $ideash ...")
      write.over(ideash, read(ideash).replace(""""x86_64"""", """"aarch64""""))
      val jbrFilename = s"jbr-$jbrVer-linux-aarch64-b$jbrBuildVer.tar.gz"
      val jbrUrl = s"https://bintray.com/jetbrains/intellij-jbr/download_file?file_path=$jbrFilename"
      val jbrDrop = ideaCacheDir / jbrFilename
      if (!os.exists(jbrDrop)) {
        print(s"Downloading from $jbrUrl ... ")
        %%('curl, "-JLso", jbrFilename, jbrUrl)(ideaCacheDir)
        println("done!")
      }
      println(s"Replacing ${ideaDir / "jbr"} ...")
      os.remove.all(ideaDir / "jbr")
      %%('tar, 'xfz, jbrDrop)(ideaDir)

      if (!sfx) {
        val ideaMajorVer = ideaVer.substring(0, ideaVer.lastIndexOf('.'))
        val config = os.home / ".config" / "JetBrains" / s"IdeaIC$ideaMajorVer" / "idea.properties"
        val configContent = s"idea.filewatcher.executable.path=${os.pwd / 'bin / RelPath(platform) / "fsnotifier"}"
        if (os.exists(config)) {
          println(s"Please ensure the following line is in the existing $config")
          println(configContent)
        } else {
          mkdir(config / os.up)
          os.write.over(config, configContent)
          println(s"Wrote $config")
        }
      }
    }
    os.move(ideash, ideaDir / 'bin / "IVE.sh")
    mkLink(ideash, "IVE.sh")
  }

  def setupWin(ideaDrop: Path): Unit = {
    os.makeDir.all(ideaDir)
    %%(pwd7z, 'x, "-y", ideaDrop)(ideaDir)
    os.remove.all(ideaDir / '$PLUGINSDIR)
    println("done!")
    deletePlugins()
    extractPlugins()
    patchIcon()
    patchImages()
    patchIdeaProperties(ideaDir / 'bin / "idea.properties")
    patchVMOptions(ideaDir / 'bin / "idea64.exe.vmoptions")
    os.remove.all(ideaDir / 'bin / "idea.exe")
    os.remove.all(ideaDir / 'bin / "idea.exe.vmoptions")
    os.move(ideaDir / 'bin / "idea64.exe", ideaDir / 'bin / "IVE.exe")
    os.move(ideaDir / 'bin / "idea64.exe.vmoptions", ideaDir / 'bin / "IVE.exe.vmoptions")
  }

  def merge(out: Path, ps: Path*): Unit = {
    os.copy(ps.head, out)
    val outOs = os.write.outputStream(out, openOptions = Seq(StandardOpenOption.APPEND))
    try {
      val buffer = new Array[Byte](1000000)
      for (p <- ps.tail) {
        val is = os.read.inputStream(p)
        try {
          var n = is.read(buffer)
          while (n > 0) {
            outOs.write(buffer, 0, n)
            n = is.read(buffer)
          }
        } finally is.close()
      }
    } finally outOs.close()
  }

  def pack(): Unit = {
    val plat = platform.replace("/", "-")
    val sfxSuffix = if (platform == "win") ".exe" else ".sfx"
    val r = pwd / 'distro / s"$plat$devSuffix$sfxSuffix"
    os.remove.all(r)
    print(s"Packaging $r ... ")
    val distro7z = s"$plat.7z"
    val setupDir = pwd / 'distro / (if (isDev) 'dev else 'release)
    val setups = os.list(setupDir)
    val shouldClone = clone || scala.util.Properties.isWin
    val oldPwd = pwd
    val (repoDir, distroDir) = if (shouldClone) {
      val dir = setupDir / s"Sireum$devSuffix"
      for (rp <- distroMap(platform) if rp.ups == 0) {
        os.makeDir.all(dir / rp / os.up)
        os.copy.over(oldPwd / rp, dir / rp)
      }
      (oldPwd, dir)
    } else {
      val dir = oldPwd / os.up / s"Sireum$devSuffix"
      for (p <- setups) os.copy.over(p, oldPwd / os.up / p.last)
      os.move(oldPwd, dir)
      (dir, dir)
    }
    try {
      val sfx = repoDir / 'distro / s"$plat$devSuffix$sfxSuffix"
      val cmd = Seq[os.Shellable](repoDir / z7, 'a, distro7z) ++
        distroMap(platform).map(rp => RelPath(distroDir.last) / rp: os.Shellable)
      os.proc(cmd: _*).call(cwd = distroDir / os.up)
      platform match {
        case "win" =>
          merge(sfx, repoDir / 'bin / 'win / "7z.sfx", distroDir / os.up / "config.txt", distroDir / os.up / distro7z)
        case _ =>
          merge(sfx, repoDir / 'bin / RelPath(platform) / "7z.sfx", distroDir / os.up / distro7z)
      }
      os.remove(distroDir / os.up / distro7z)
      println("done!")
    } finally {
      if (shouldClone) os.remove.all(distroDir)
      else {
        os.move(distroDir, oldPwd)
        for (p <- setups) os.remove.all(distroDir / os.up / p.last)
      }
    }
  }
}

def build(platform: String, isDev: Boolean, sfx: Boolean, clone: Boolean): Unit = {
  import distro._
  os.makeDir.all(pluginsCacheDir)
  new distro(platform, isDev, sfx, clone).build()
}
