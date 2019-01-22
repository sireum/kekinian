import java.io.{File, FileInputStream}
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
  val ignoredIcons = Set("idea.icns", "idea-dev.icns", "idea.png", "idea-dev.png", "idea-dev.ico")
  val ideaExtMap = Map(
    "mac" -> ".dmg",
    "win" -> ".exe",
    "linux" -> ".tar.gz"
  )
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

  val distroMap = Map[String, Seq[Path]](
    "win" -> Seq(
      pwd / 'bin / 'scala,
      pwd / 'bin / 'win / 'idea,
      pwd / 'bin / 'win / 'java,
      pwd / 'bin / "mill.bat",
      pwd / 'bin / "sireum.bat",
      pwd / 'bin / "sireum.jar",
      pwd / 'bin / "slang-run.bat",
      pwd / 'lib,
      pwd / "license.txt",
      pwd / "versions.properties"
    ),
    "linux" -> Seq(
      pwd / 'bin / 'scala,
      pwd / 'bin / 'linux / 'idea,
      pwd / 'bin / 'linux / 'java,
      pwd / 'bin / "mill",
      pwd / 'bin / "sireum",
      pwd / 'bin / "sireum.jar",
      pwd / 'bin / "slang-run.sh",
      pwd / 'lib,
      pwd / "license.txt",
      pwd / "versions.properties"
    ),
    "mac" -> Seq(
      pwd / 'bin / 'scala,
      pwd / 'bin / 'mac / 'idea,
      pwd / 'bin / 'mac / 'java,
      pwd / 'bin / "mill",
      pwd / 'bin / "sireum",
      pwd / 'bin / "sireum.jar",
      pwd / 'bin / "slang-run.sh",
      pwd / 'lib,
      pwd / "license.txt",
      pwd / "versions.properties"
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

class distro(platform: String, isDev: Boolean) {

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
        val url = s"https://plugins.jetbrains.com/plugin/download?pr=idea&updateId=$ver"
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
        s"${content.trim}\r\n-Dorg.sireum.ive=Sireum$ideaVer$devSuffix\r\n-Dorg.sireum.ive.dev=$isDev\r\n-Dfile.encoding=UTF-8\r\n"
      case _ =>
        s"${content.trim}\n-Dorg.sireum.ive=Sireum$ideaVer$devSuffix\n-Dorg.sireum.ive.dev=$isDev\n-Dfile.encoding=UTF-8\n"
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
        "7z", 'u,
        resourcesJar,
        "idea_community_about.png",
        "idea_community_about@2x.png",
        "idea_community_logo.png",
        "idea_community_logo@2x.png"
      )(pwd / 'resources / 'distro / 'images / 'dev)
    else
      %%(
        "7z", 'u,
        resourcesJar,
        "idea_community_about.png",
        "idea_community_about@2x.png",
        "idea_community_logo.png",
        "idea_community_logo@2x.png"
      )(pwd / 'resources / 'distro / 'images / 'release)
    println("done!")
  }

  def replaceExe(filePath: Path): Unit = {
    print(s"Replacing $filePath ... ")
    os.copy.over(ideaCacheDir / filePath.last, filePath)
    println("done!")
  }

  def patchIcon(): Unit = {
    val iconsPath = pwd / 'resources / 'distro / 'icons
    val (dirPath, srcFilename, filename) = platform match {
      case "mac" =>
        if (isDev) (sireumAppDir / 'Contents / 'Resources, "idea-dev.icns", "idea.icns")
        else (sireumAppDir / 'Contents / 'Resources, "idea.icns", "idea.icns")
      case "win" =>
        replaceExe(ideaDir / 'bin / "idea.exe")
        replaceExe(ideaDir / 'bin / "idea64.exe")
        if (isDev) (ideaDir / 'bin, "idea-dev.ico", "idea.ico")
        else (ideaDir / 'bin, "idea.ico", "idea.ico")
      case "linux" =>
        if (isDev) (ideaDir / 'bin, "idea-dev.png", "idea.png")
        else (ideaDir / 'bin, "idea.png", "idea.png")
    }
    print(s"Replacing icon $dirPath/$filename ... ")
    os.copy.over(iconsPath / srcFilename, dirPath / filename)
    println("done!")
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

  def setup(): Unit = {
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
    if (scala.util.Properties.isWin)
      %%('cmd, "/c",
        s"""cd /d ${sireumJar / os.up} && dir && mklink ${sireumJar.last} "${link.toNIO}"""")(pwd)
    else os.symlink(sireumJar, link)
    println("Done!")
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
    os.remove.all(ideaDir / 'Applications)
    os.remove.all(ideaDir / "[HFS+ Private Data]")
    os.remove.all(ideaDir / ".background")
    os.remove.all(ideaDir / ".DS_Store")
    os.remove.all(ideaDir / ".fseventsd")
    os.remove.all(ideaDir / ".HFS+ Private Directory Data_")
    os.remove.all(ideaDir / ".Trashes")
    os.move(ideaDir / "IntelliJ IDEA CE.app", sireumAppDir)
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
    %%("7z", 'x, "-y", ideaDrop)(ideaDirParent)
    val ideaTar = ideaDirParent / ideaDrop.last.substring(0, ideaDrop.last.lastIndexOf('.'))
    %%("7z", 'x, "-y", ideaTar)(ideaDirParent)
    os.remove(ideaTar)
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

  /*

  def jdkTable: String = {
    val javaHome = (pwd / 'bin / platform / 'java).segments.mkString("/")
    s"""<application>
       |  <component name="ProjectJdkTable">
       |    <jdk version="2">
       |      <name value="Java" />
       |      <type value="JavaSDK" />
       |      <version value="Zulu ${properties.get("org.sireum.version.zulu")}" />
       |      <homePath value="$javaHome" />
       |      <roots>
       |        <annotationsPath>
       |          <root type="composite">
       |            <root url="jar://$$APPLICATION_HOME_DIR$$/lib/jdkAnnotations.jar!/" type="simple" />
       |          </root>
       |        </annotationsPath>
       |        <classPath>
       |          <root type="composite">
       |            <root url="jrt://$javaHome!/java.base" type="simple" />
       |            <root url="jrt://$javaHome!/java.compiler" type="simple" />
       |            <root url="jrt://$javaHome!/java.datatransfer" type="simple" />
       |            <root url="jrt://$javaHome!/java.desktop" type="simple" />
       |            <root url="jrt://$javaHome!/java.instrument" type="simple" />
       |            <root url="jrt://$javaHome!/java.logging" type="simple" />
       |            <root url="jrt://$javaHome!/java.management" type="simple" />
       |            <root url="jrt://$javaHome!/java.management.rmi" type="simple" />
       |            <root url="jrt://$javaHome!/java.naming" type="simple" />
       |            <root url="jrt://$javaHome!/java.net.http" type="simple" />
       |            <root url="jrt://$javaHome!/java.prefs" type="simple" />
       |            <root url="jrt://$javaHome!/java.rmi" type="simple" />
       |            <root url="jrt://$javaHome!/java.scripting" type="simple" />
       |            <root url="jrt://$javaHome!/java.se" type="simple" />
       |            <root url="jrt://$javaHome!/java.security.jgss" type="simple" />
       |            <root url="jrt://$javaHome!/java.security.sasl" type="simple" />
       |            <root url="jrt://$javaHome!/java.smartcardio" type="simple" />
       |            <root url="jrt://$javaHome!/java.sql" type="simple" />
       |            <root url="jrt://$javaHome!/java.sql.rowset" type="simple" />
       |            <root url="jrt://$javaHome!/java.transaction.xa" type="simple" />
       |            <root url="jrt://$javaHome!/java.xml" type="simple" />
       |            <root url="jrt://$javaHome!/java.xml.crypto" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.accessibility" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.aot" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.attach" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.charsets" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.compiler" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.crypto.cryptoki" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.crypto.ec" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.crypto.mscapi" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.dynalink" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.editpad" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.hotspot.agent" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.httpserver" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.internal.ed" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.internal.jvmstat" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.internal.le" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.internal.opt" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.internal.vm.ci" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.internal.vm.compiler" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.internal.vm.compiler.management" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.jartool" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.javadoc" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.jcmd" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.jconsole" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.jdeps" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.jdi" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.jdwp.agent" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.jfr" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.jlink" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.jshell" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.jsobject" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.jstatd" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.localedata" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.management" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.management.agent" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.management.jfr" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.naming.dns" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.naming.rmi" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.net" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.pack" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.rmic" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.scripting.nashorn" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.scripting.nashorn.shell" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.sctp" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.security.auth" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.security.jgss" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.unsupported" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.unsupported.desktop" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.xml.dom" type="simple" />
       |            <root url="jrt://$javaHome!/jdk.zipfs" type="simple" />
       |          </root>
       |        </classPath>
       |        <javadocPath>
       |          <root type="composite" />
       |        </javadocPath>
       |        <sourcePath>
       |          <root type="composite">
       |            <root url="jar://$javaHome/lib/src.zip!/java.se" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.aot" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.jdi" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.jfr" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.net" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.rmi" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.sql" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.xml" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.jcmd" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.pack" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.rmic" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.sctp" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.base" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.jdeps" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.jlink" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.zipfs" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.prefs" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.attach" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.jshell" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.jstatd" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.naming" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.editpad" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.jartool" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.javadoc" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.xml.dom" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.desktop" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.logging" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.charsets" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.compiler" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.dynalink" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.jconsole" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.jsobject" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.compiler" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.net.http" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.crypto.ec" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.scripting" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.httpserver" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.jdwp.agent" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.localedata" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.management" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.naming.dns" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.naming.rmi" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.instrument" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.management" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.sql.rowset" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.xml.crypto" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.internal.ed" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.internal.le" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.unsupported" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.smartcardio" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.internal.opt" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.datatransfer" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.accessibility" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.crypto.mscapi" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.hotspot.agent" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.security.auth" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.security.jgss" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.security.jgss" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.security.sasl" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.internal.vm.ci" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.management.jfr" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.management.rmi" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/java.transaction.xa" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.crypto.cryptoki" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.internal.jvmstat" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.management.agent" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.scripting.nashorn" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.unsupported.desktop" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.internal.vm.compiler" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.scripting.nashorn.shell" type="simple" />
       |            <root url="jar://$javaHome/lib/src.zip!/jdk.internal.vm.compiler.management" type="simple" />
       |          </root>
       |        </sourcePath>
       |      </roots>
       |      <additional />
       |    </jdk>
       |  </component>
       |</application>""".stripMargin
  }
  */

}

def setupIve(platform: String, isDev: Boolean): Unit = {
  import distro._
  println(s"Using cache at $cacheDir")
  os.makeDir.all(pluginsCacheDir)
  new distro(platform, isDev).setup()
}
