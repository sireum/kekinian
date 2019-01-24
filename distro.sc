import java.io.{File, FileInputStream}
import java.nio.file.StandardOpenOption
import java.util.jar.JarInputStream

import ammonite.ops._

import scala.collection.JavaConverters._

object distro {
    final case class Plugin(id: String, isDev: Boolean, isJar: Boolean, devVer: String, ver: String) {
      def version(isDev: Boolean): String = if (isDev) devVer else ver
    }

    val delPlugins = Seq("android", "gradle", "Groovy", "Kotlin")
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
      "idea-ce-eap.svg"
    )
    val ideaExtMap = Map("mac" -> ".dmg", "win" -> ".exe", "linux" -> ".tar.gz")

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
        RelPath(Vector("bin", "mill.bat"), 0),
        RelPath(Vector("bin", "sireum.bat"), 0),
        RelPath(Vector("bin", "sireum.jar"), 0),
        RelPath(Vector("bin", "slang-run.bat"), 0),
        RelPath(Vector("lib"), 0),
        RelPath(Vector("license.txt"), 0),
        RelPath(Vector("versions.properties"), 0),
        RelPath(Vector("setup.bat"), 1),
        RelPath(Vector("install.bat"), 1)
      ),
      "linux" -> Seq(
        RelPath(Vector("bin", "scala"), 0),
        RelPath(Vector("bin", "linux", "idea"), 0),
        RelPath(Vector("bin", "linux", "java"), 0),
        RelPath(Vector("bin", "mill"), 0),
        RelPath(Vector("bin", "sireum"), 0),
        RelPath(Vector("bin", "sireum.jar"), 0),
        RelPath(Vector("bin", "slang-run.sh"), 0),
        RelPath(Vector("lib"), 0),
        RelPath(Vector("license.txt"), 0),
        RelPath(Vector("versions.properties"), 0),
        RelPath(Vector("setup"), 1)
      ),
      "mac" -> Seq(
        RelPath(Vector("bin", "scala"), 0),
        RelPath(Vector("bin", "mac", "idea"), 0),
        RelPath(Vector("bin", "mac", "java"), 0),
        RelPath(Vector("bin", "mill"), 0),
        RelPath(Vector("bin", "sireum"), 0),
        RelPath(Vector("bin", "sireum.jar"), 0),
        RelPath(Vector("bin", "slang-run.sh"), 0),
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

class distro(platform: String, isDev: Boolean, setupConfig: Boolean, sfx: Boolean, clone: Boolean) {

    import distro._

    val devSuffix = if (isDev) "-dev" else ""
    val ideaDir = os.pwd / 'bin / platform / 'idea
    val sireumAppDir = ideaDir / s"Sireum$devSuffix.app"

    val pluginsDir =
      if (platform == "mac") sireumAppDir / 'Contents / 'plugins
      else ideaDir / 'plugins

    val libDir =
      if (platform == "mac") sireumAppDir / 'Contents / 'lib
      else ideaDir / 'lib
    val version = %%('git, 'log, "-n", "1", "--pretty=format:%H")(pwd).out.lines.head.trim

    val ideaVer = {
      val (devVer, ver) = devRelVer("org.sireum.version.idea")
      if (isDev) devVer else ver
    }

    def downloadPlugins(): Unit = {
      for (p <- plugins.values) {
        val ver = p.version(isDev)
        val zip = zipName(p.id, ver)
        if (!os.exists(pluginsCacheDir / zip)) {
          val url =
            if (p.id.startsWith("sireum-")) {
              val repo = p.id.substring("sireum-".length)
              s"https://github.com/sireum/$repo/releases/download/v.$ver/$repo.zip"
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
          print(s"Extracting ${p.id} plugin ... ")
          %%("7z", 'x, "-y", zipPath)(pluginsDir)
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
          s"idea.config.path=$${user.home}/.SireumIVE$devSuffix/config\r\nidea.system.path=$${user.home}/.SireumIVE$devSuffix/system\r\n" + content
        case "linux" =>
          s"idea.config.path=$${user.home}/.SireumIVE$devSuffix/config\nidea.system.path=$${user.home}/.SireumIVE$devSuffix/system\n" + content
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
      print(s"Patching $resourcesJar ... ")
      if (isDev)
        %%(
          "7z",
          'u,
          resourcesJar,
          "idea_community_about.png",
          "idea_community_about@2x.png",
          "idea_community_logo.png",
          "idea_community_logo@2x.png",
          "idea-ce.svg",
          "idea-ce-eap.svg"
        )(pwd / 'resources / 'distro / 'images / 'dev)
      else
        %%(
          "7z",
          'u,
          resourcesJar,
          "idea_community_about.png",
          "idea_community_about@2x.png",
          "idea_community_logo.png",
          "idea_community_logo@2x.png",
          "idea-ce.svg",
          "idea-ce-eap.svg"
        )(pwd / 'resources / 'distro / 'images / 'release)
      println("done!")
    }

    def patchExe(): Unit = {
      val rhExe = "ResourceHacker.exe"
      val rhDir = pwd / 'resources / 'rh
      os.makeDir.all(rhDir)
      if (!os.exists(rhDir / rhExe)) {
        print("Downloading ResourceHacker ...")
        %%('curl, "-JLso", "rh.zip", "http://angusj.com/resourcehacker/resource_hacker.zip")(rhDir)
        %%("7z", 'x, "rh.zip", rhExe)(rhDir)
        println("done!")
      }
      val binDir = ideaDir / 'bin
      val ideaExe = binDir / "idea.exe"
      val idea64Exe = binDir / "idea64.exe"
      print(s"Patching $ideaExe and $idea64Exe ... ")
      if (scala.util.Properties.isWin) {
        %%(
          rhDir / rhExe,
          "-open",
          s".\\${ideaExe.last}",
          "-save",
          s".\\${ideaExe.last}",
          "-action",
          'addoverwrite,
          "-res",
          ".\\idea.ico",
          "-mask",
          "ICONGROUP,2000,1033"
        )(binDir)
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
          s".\\${ideaExe.last}",
          "-save",
          s".\\${ideaExe.last}",
          "-action",
          'addoverwrite,
          "-res",
          ".\\idea.ico",
          "-mask",
          "ICONGROUP,2000,1033"
        )(binDir)
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
          if (isDev) (sireumAppDir / 'Contents / 'Resources, "idea-dev.icns", "idea.icns")
          else (sireumAppDir / 'Contents / 'Resources, "idea.icns", "idea.icns")
        case "win" =>
          if (isDev) (ideaDir / 'bin, "idea-dev.ico", "idea.ico")
          else (ideaDir / 'bin, "idea.ico", "idea.ico")
        case "linux" =>
          if (isDev) {
            os.copy.over(iconsPath / "idea-dev.svg", ideaDir / 'bin / "idea.svg")
            (ideaDir / 'bin, "idea-dev.png", "idea.png")
          } else {
            os.copy.over(iconsPath / "idea.svg", ideaDir / 'bin / "idea.svg")
            (ideaDir / 'bin, "idea.png", "idea.png")
          }
      }
      print(s"Replacing icon $dirPath/$filename ... ")
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
            f.last
          }).toVector
      val cmd = "7z" +: "u" +: iconsJar.toString +: entriesToUpdate
      Shellout.executeStream(iconsPath, Command(cmd, Map(), Shellout.executeStream))
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
        case "linux" => setupLinux(ideaDrop)
        case "win" => setupWin(ideaDrop)
      }
      val sireumJar = pluginsDir / "kekinian-intellij" / 'lib / "sireum.jar"
      val link = (pwd / 'bin / "sireum.jar").relativeTo(sireumJar / os.up)
      os.remove.all(sireumJar)
      mkLink(sireumJar, link)
      if (setupConfig) {
        val configOptions = os.home / s".SireumIVE$devSuffix" / 'config / 'options
        os.makeDir.all(configOptions)
        os.write.over(configOptions / "jdk.table.xml", jdkTable)
      }
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
        print(s"Removing plugin $p ... ")
        os.remove.all(pluginsDir / p)
        println("done!")
      }
    }

    def setupMac(ideaDrop: Path): Unit = {
      val ideaDirParent = ideaDir / os.up
      %%("7z", 'x, "-y", ideaDrop)(ideaDirParent)
      os.move(ideaDirParent / "IntelliJ IDEA CE", ideaDir)
      for (p <- os.list(ideaDir) if !p.last.endsWith(".app")) os.remove.all(p)
      os.move(ideaDir / "IntelliJ IDEA CE.app", sireumAppDir)
      val libjli = sireumAppDir / 'Contents / 'jdk / 'Contents / 'MacOS / "libjli.dylib"
      os.remove(libjli)
      mkLink(libjli, RelPath(new File("../Home/jre/lib/jli/libjli.dylib")))
      if (!scala.util.Properties.isWin) {
        os.perms.set(sireumAppDir / 'Contents / 'MacOS / 'idea, 0x755)
        os.perms.set(sireumAppDir / 'Contents / 'bin / "format.sh", 0x755)
        os.perms.set(sireumAppDir / 'Contents / 'bin / "inspect.sh", 0x755)
      }
      println("done!")
      deletePlugins()
      extractPlugins()
      patchIdeaProperties(sireumAppDir / 'Contents / "Info.plist")
      patchVMOptions(sireumAppDir / 'Contents / 'bin / "idea.vmoptions")
      patchImages()
      patchIcon()
    }

    def setupLinux(ideaDrop: Path): Unit = {
      val ideaDirParent = ideaDir / os.up
      %%('tar, 'xfz, ideaDrop)(ideaDirParent)
      os.move(os.list(ideaDirParent).find(p => p.last.startsWith("idea-IC-")).get, ideaDir)
      println("done!")
      deletePlugins()
      extractPlugins()
      patchIdeaProperties(ideaDir / 'bin / "idea.properties")
      patchVMOptions(ideaDir / 'bin / "idea.vmoptions")
      patchVMOptions(ideaDir / 'bin / "idea64.vmoptions")
      patchImages()
      patchIcon()
    }

    def setupWin(ideaDrop: Path): Unit = {
      os.makeDir.all(ideaDir)
      %%("7z", 'x, "-y", ideaDrop)(ideaDir)
      os.remove.all(ideaDir / '$PLUGINSDIR)
      println("done!")
      deletePlugins()
      extractPlugins()
      patchIdeaProperties(ideaDir / 'bin / "idea.properties")
      patchVMOptions(ideaDir / 'bin / "idea.exe.vmoptions")
      patchVMOptions(ideaDir / 'bin / "idea64.exe.vmoptions")
      patchImages()
      patchIcon()
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
      val sfxSuffix = if (platform == "win") ".exe" else ".sfx"
      val r = pwd / 'distro / s"$platform$devSuffix$sfxSuffix"
      os.remove.all(r)
      print(s"Packaging $r ... ")
      val distro7z = s"$platform.7z"
      val distro = s"$platform.sfx"
      val setupDir = pwd / 'distro / (if (isDev) 'dev else 'release)
      val setups = os.list(setupDir)
      val shouldClone = clone || scala.util.Properties.isWin
      val oldPwd = pwd
      val (repoDir, distroDir) = if (shouldClone) {
        val dir = setupDir / s"Sireum$devSuffix"
        for (rp <- distroMap(platform) if rp.ups == 0) {
          os.makeDir.all(dir / rp / os.up)
          os.copy(oldPwd / rp, dir / rp)
        }
        (oldPwd, dir)
      } else {
        val dir = oldPwd / os.up / s"Sireum$devSuffix"
        for (p <- setups) os.copy(p, oldPwd / os.up / p.last)
        os.move(oldPwd, dir)
        (dir, dir)
      }
      try {
        val sfx = repoDir / 'distro / s"$platform$devSuffix$sfxSuffix"
        val cmd = Seq[os.Shellable]("7z", 'a, distro7z) ++
          distroMap(platform).map(rp => RelPath(distroDir.last) / rp: os.Shellable)
        os.proc(cmd: _*).call(cwd = distroDir / os.up)
        platform match {
          case "win" =>
            merge(sfx, repoDir / 'bin / 'win / "7z.sfx",
              distroDir / os.up / "config.txt", distroDir / os.up / distro7z)
          case _ =>
            merge(sfx, repoDir / 'bin / platform / "7z.sfx", distroDir / os.up / distro7z)
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

    def jdkTable: String = {
      var javaHome = (pwd / 'bin / platform / 'java).toNIO.toString
      if (scala.util.Properties.isWin) {
        javaHome = javaHome.replaceAllLiterally("\\", "/")
      }
      val zuluVer = properties.get("org.sireum.version.zulu")
      s"""<application>
         |  <component name="ProjectJdkTable">
         |    <jdk version="2">
         |      <name value="Java" />
         |      <type value="JavaSDK" />
         |      <version value="Zulu $zuluVer" />
         |      <homePath value="JAVA_HOME" />
         |      <roots>
         |        <annotationsPath>
         |          <root type="composite">
         |            <root url="jar://$$APPLICATION_HOME_DIR$$/lib/jdkAnnotations.jar!/" type="simple" />
         |          </root>
         |        </annotationsPath>
         |        <classPath>
         |          <root type="composite">
         |            <root url="jrt://JAVA_HOME!/java.base" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.compiler" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.datatransfer" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.desktop" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.instrument" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.logging" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.management" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.management.rmi" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.naming" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.net.http" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.prefs" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.rmi" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.scripting" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.se" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.security.jgss" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.security.sasl" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.smartcardio" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.sql" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.sql.rowset" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.transaction.xa" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.xml" type="simple" />
         |            <root url="jrt://JAVA_HOME!/java.xml.crypto" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.accessibility" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.aot" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.attach" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.charsets" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.compiler" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.crypto.cryptoki" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.crypto.ec" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.crypto.mscapi" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.dynalink" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.editpad" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.hotspot.agent" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.httpserver" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.internal.ed" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.internal.jvmstat" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.internal.le" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.internal.opt" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.internal.vm.ci" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.internal.vm.compiler" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.internal.vm.compiler.management" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.jartool" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.javadoc" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.jcmd" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.jconsole" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.jdeps" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.jdi" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.jdwp.agent" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.jfr" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.jlink" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.jshell" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.jsobject" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.jstatd" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.localedata" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.management" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.management.agent" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.management.jfr" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.naming.dns" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.naming.rmi" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.net" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.pack" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.rmic" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.scripting.nashorn" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.scripting.nashorn.shell" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.sctp" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.security.auth" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.security.jgss" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.unsupported" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.unsupported.desktop" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.xml.dom" type="simple" />
         |            <root url="jrt://JAVA_HOME!/jdk.zipfs" type="simple" />
         |          </root>
         |        </classPath>
         |        <javadocPath>
         |          <root type="composite" />
         |        </javadocPath>
         |        <sourcePath>
         |          <root type="composite">
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.se" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.aot" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.jdi" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.jfr" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.net" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.rmi" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.sql" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.xml" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.jcmd" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.pack" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.rmic" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.sctp" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.base" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.jdeps" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.jlink" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.zipfs" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.prefs" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.attach" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.jshell" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.jstatd" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.naming" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.editpad" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.jartool" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.javadoc" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.xml.dom" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.desktop" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.logging" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.charsets" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.compiler" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.dynalink" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.jconsole" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.jsobject" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.compiler" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.net.http" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.crypto.ec" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.scripting" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.httpserver" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.jdwp.agent" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.localedata" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.management" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.naming.dns" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.naming.rmi" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.instrument" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.management" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.sql.rowset" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.xml.crypto" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.internal.ed" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.internal.le" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.unsupported" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.smartcardio" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.internal.opt" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.datatransfer" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.accessibility" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.crypto.mscapi" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.hotspot.agent" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.security.auth" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.security.jgss" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.security.jgss" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.security.sasl" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.internal.vm.ci" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.management.jfr" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.management.rmi" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/java.transaction.xa" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.crypto.cryptoki" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.internal.jvmstat" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.management.agent" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.scripting.nashorn" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.unsupported.desktop" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.internal.vm.compiler" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.scripting.nashorn.shell" type="simple" />
         |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.internal.vm.compiler.management" type="simple" />
         |          </root>
         |        </sourcePath>
         |      </roots>
         |      <additional />
         |    </jdk>
         |  </component>
         |</application>""".stripMargin.replaceAllLiterally("JAVA_HOME", javaHome)
    }

  }

def build(platform: String, isDev: Boolean, setupConfig: Boolean, sfx: Boolean, clone: Boolean): Unit = {
    import distro._
    os.makeDir.all(pluginsCacheDir)
    new distro(platform, isDev, setupConfig, sfx, clone).build()
  }
