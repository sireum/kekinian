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

package org.sireum.cli

import org.sireum._
import org.sireum.bitcodec.Spec
import org.sireum.lang._
import org.sireum.lang.parser.Parser
import org.sireum.message._
import org.sireum.tools._
import org.sireum.SireumApi._
import org.sireum.lang.tipe.TypeHierarchy

object GenTools {


  def bcGen(o: Cli.SireumToolsBcgenOption, reporter: Reporter): Z = {
    o.args.size match {
      case z"0" => println(o.help); return 0
      case z"1" =>
      case _ => println(s"Expecting one argument, but found ${o.args.size}."); return -1
    }

    val lOpt = path2fileOpt("license file", o.license, T)
    val src = paths2fileOpt("config file", o.args, T).get
    val destDir = path2fileOpt("output directory", o.outputDir, T).get
    if (!destDir.isDir) {
      eprintln(s"Path $destDir is not a directory")
      return -1
    }
    val outTemp = Os.temp()
    val r = SlangRunner.run(Cli.SireumSlangRunOption("", ISZ(src.string), None(),
      Some(outTemp.string), F, F))
    if (r != 0) {
      eprintln(outTemp.read)
      return r
    }
    val text = src.read
    Parser.parseTopUnit[ast.TopUnit.Program](text, T, F, Some(src.string), reporter) match {
      case Some(p) if !reporter.hasError =>
        val (_, program) = FrontEnd.checkWorksheet(0, None(), p, reporter)
        if (reporter.hasError) {
          reporter.printMessages()
          return -1
        }
        val specText = outTemp.read
        outTemp.removeAll()
        Spec.fromJSON(specText) match {
          case Either.Left(spec) =>
            for (mode <- o.mode) {
              val ext: String = mode match {
                case Cli.SireumToolsBcgenBitCodecMode.Program => "scala"
                case Cli.SireumToolsBcgenBitCodecMode.Script => "sc"
                case Cli.SireumToolsBcgenBitCodecMode.Json => "json"
                case Cli.SireumToolsBcgenBitCodecMode.Dot => "dot"
              }
              val dest = destDir / s"${o.name.get}.$ext"
              val prev: String = if (dest.isFile) dest.read else ""
              val output: BitCodecGen.Output.Type = mode match {
                case Cli.SireumToolsBcgenBitCodecMode.Program => BitCodecGen.Output.Program
                case Cli.SireumToolsBcgenBitCodecMode.Script => BitCodecGen.Output.Script
                case Cli.SireumToolsBcgenBitCodecMode.Json => BitCodecGen.Output.Json
                case Cli.SireumToolsBcgenBitCodecMode.Dot => BitCodecGen.Output.Dot
              }
              val out = BitCodecGen.gen(output, !o.isLittleEndian, o.isMutable, lOpt.map((l: Os.Path) => l.read), src.name,
                o.packageName, o.name.get, text, o.traits, spec, bitcodecPrint(spec), program, prev, reporter)
              if (reporter.hasError) {
                reporter.printMessages()
                return -1
              }
              dest.writeOver(out.render)
              println(s"Wrote $dest")
            }
            if (reporter.hasIssue) {
              println()
              reporter.printMessages()
            }
            return 0
          case _ =>
            eprintln(s"Invalid config produced by running ${o.args(0)}")
            eprintln(specText)
            return -1
        }
      case _ =>
        reporter.printMessages()
        return -1
    }
  }

  def cliGen(o: Cli.SireumToolsCligenOption): Z = {
    o.args.size match {
      case z"0" => println(o.help); return 0
      case z"1" =>
      case _ =>
        eprintln(s"Expecting one argument, but found ${o.args.size}.")
        return -1
    }

    if (o.packageName.nonEmpty && o.script.nonEmpty) {
      eprintln("Package name cannot be used along with script generation")
      return -1
    }

    val lOpt = path2fileOpt("license file", o.license, T)
    val src = paths2fileOpt("config file", o.args, T).get
    val destDir = path2fileOpt("output directory", o.outputDir, T).get
    if (!destDir.isDir) {
      eprintln(s"Path $destDir is not a directory")
    }
    val dest: Os.Path = o.script match {
      case Some(script) => destDir / script
      case _ => destDir / s"${o.name.get}.scala"
    }
    val (first, second): (Z, Z) = o.width.size match {
      case z"2" => (o.width(0), o.width(1))
      case _ => (z"25", z"55")
    }
    val outTemp = Os.temp()
    val r = SlangRunner.run(Cli.SireumSlangRunOption("", ISZ(src.string), None(),
      Some(outTemp.string), F, F))
    if (r != 0) {
      eprintln(s"Could not run ${o.args(0)}")
      return r
    }
    val configText = outTemp.read
    outTemp.removeAll()
    JSON.toCliOpt(configText) match {
      case Either.Left(config) =>
        val out = CliGenJvm.run(lOpt, config, src, dest, o.packageName, o.name, first, second)
        dest.writeOver(out)
        if (dest.ext == "cmd") {
          dest.chmod("+x")
        }
        println(s"Wrote $dest")
        return 0
      case _ =>
        eprintln(s"Invalid config produced by running ${o.args(0)}")
        eprintln(configText)
        return -1
    }
  }

  def iveGen(o: Cli.SireumToolsIvegenOption): Z = {
    if (o.args.size != 1) {
      println(o.help)
      return 0
    } else {
      val HomeNotFound = -1
      val JavaOrScalaNotFound = -2
      val IdeaNotFound = -3
      val InvalidDir = -4

      val d = path2fileOpt("project parent folder", Some(o.args(0)), F).get
      if (!d.exists) {
        d.mkdirAll()
      }
      if (!d.exists) {
        eprintln(s"Could not create $d")
        return InvalidDir
      }
      val project = d / o.projectName.get
      val projectExists = project.exists
      project.mkdirAll()

      if (!homeFound) {
        return HomeNotFound
      }
      if (!(javaFound && scalaFound)) {
        return JavaOrScalaNotFound
      }
      if (!ideaDir.exists) {
        eprintln("Sireum IVE is not installed")
        return IdeaNotFound
      }
      val home = homeOpt.get
      val javaHome = javaHomeOpt.get
      val scalaHome = scalaHomeOpt.get
      val isWin = Os.isWin
      val jbrHome: Os.Path = if (Os.isMac) ideaDir / "jbr" / "Contents" / "Home" else ideaDir / "jbr"

      @strictpure def uriPathSep(s: String): String =
        if (isWin) ops.StringOps(s).replaceAllChars('\\', '/') else s

      val devSuffix: String = if (isDev) "" else "-dev"

      @strictpure def normalizePath(path: Os.Path): String = uriPathSep(path.string)

      val scalaDir = normalizePath(scalaHome)

      @pure def applicationLib: String = {
        @strictpure def library(name: String): String =
          st"""    <library name="$name" type="Scala">
              |      <properties>
              |        <compiler-classpath>
              |          <root url="file://$scalaDir/lib/scala-compiler.jar" />
              |          <root url="file://$scalaDir/lib/scala-library.jar" />
              |          <root url="file://$scalaDir/lib/scala-reflect.jar" />
              |        </compiler-classpath>
              |      </properties>
              |      <CLASSES>
              |        <root url="jar://$scalaDir/lib/scala-library.jar!/" />
              |        <root url="jar://$scalaDir/lib/scala-reflect.jar!/" />
              |      </CLASSES>
              |      <JAVADOC>
              |        <root url="http://www.scala-lang.org/api/$scalaVer/" />
              |      </JAVADOC>
              |      <SOURCES />
              |    </library>""".render

        val r =
          st"""<application>
              |  <component name="libraryTable">
              |${library("Scala")}
              |${library(s"scala-sdk-$scalaVer")}
              |    <library name="Sireum">
              |      <CLASSES>
              |        <root url="jar://${normalizePath(sireumJar)}!/" />
              |      </CLASSES>
              |      <JAVADOC />
              |      <SOURCES />
              |    </library>
              |  </component>
              |</application>""".render
        return r
      }

      @pure def jdkTable: String = {
        val jdkModules: Set[String] = Set ++ ISZ(
          "java.base", "java.compiler", "java.datatransfer", "java.desktop", "java.instrument", "java.logging",
          "java.management", "java.management.rmi", "java.naming", "java.net.http", "java.prefs", "java.rmi",
          "java.scripting", "java.se", "java.security.jgss", "java.security.sasl", "java.smartcardio",
          "java.sql", "java.sql.rowset", "java.transaction.xa", "java.xml", "java.xml.crypto", "javafx.base",
          "javafx.controls", "javafx.fxml", "javafx.graphics", "javafx.media", "javafx.swing", "javafx.web",
          "jdk.accessibility", "jdk.aot", "jdk.attach", "jdk.charsets", "jdk.compiler", "jdk.crypto.cryptoki",
          "jdk.crypto.ec", "jdk.dynalink", "jdk.editpad", "jdk.hotspot.agent", "jdk.httpserver", "jdk.internal.ed",
          "jdk.internal.jvmstat", "jdk.internal.le", "jdk.internal.opt", "jdk.internal.vm.ci",
          "jdk.internal.vm.compiler", "jdk.internal.vm.compiler.management", "jdk.jartool", "jdk.javadoc",
          "jdk.jcmd", "jdk.jconsole", "jdk.jdeps", "jdk.jdi", "jdk.jdwp.agent", "jdk.jfr", "jdk.jlink", "jdk.jshell",
          "jdk.jsobject", "jdk.jstatd", "jdk.localedata", "jdk.management", "jdk.management.agent",
          "jdk.management.jfr", "jdk.naming.dns", "jdk.naming.rmi", "jdk.net", "jdk.pack", "jdk.rmic",
          "jdk.scripting.nashorn", "jdk.scripting.nashorn.shell", "jdk.sctp", "jdk.security.auth",
          "jdk.security.jgss", "jdk.unsupported", "jdk.unsupported.desktop", "jdk.xml.dom", "jdk.zipfs",
          "org.openjsse"
        )

        val jbrModules: Set[String] = Set ++ ISZ(
          "gluegen.rt", "java.base", "java.compiler", "java.datatransfer", "java.desktop", "java.instrument",
          "java.logging", "java.management", "java.management.rmi", "java.naming", "java.net.http", "java.prefs",
          "java.rmi", "java.scripting", "java.se", "java.security.jgss", "java.security.sasl", "java.smartcardio",
          "java.sql", "java.sql.rowset", "java.transaction.xa", "java.xml", "java.xml.crypto", "jcef",
          "jdk.accessibility", "jdk.aot", "jdk.attach", "jdk.charsets", "jdk.compiler", "jdk.crypto.cryptoki",
          "jdk.crypto.ec", "jdk.dynalink", "jdk.hotspot.agent", "jdk.httpserver", "jdk.internal.ed",
          "jdk.internal.jvmstat", "jdk.internal.le", "jdk.internal.vm.ci", "jdk.internal.vm.compiler",
          "jdk.internal.vm.compiler.management", "jdk.jdi", "jdk.jdwp.agent", "jdk.jfr", "jdk.jsobject",
          "jdk.localedata", "jdk.management", "jdk.management.agent", "jdk.management.jfr", "jdk.naming.dns",
          "jdk.naming.rmi", "jdk.net", "jdk.pack", "jdk.scripting.nashorn", "jdk.scripting.nashorn.shell",
          "jdk.sctp", "jdk.security.auth", "jdk.security.jgss", "jdk.unsupported", "jdk.xml.dom", "jdk.zipfs",
          "jogl.all"
        )

        @strictpure def mkString(ss: ISZ[String]): String = st"${(ss, "\n")}".render

        val (jdkClassPath, jdkSourcePath): (String, String) =
          (mkString(for (m <- jdkModules.elements) yield
            s"""            <root url="jrt://${normalizePath(javaHome)}!/$m" type="simple" />"""),
            mkString(for (m <- jdkModules.elements) yield
              s"""            <root url="jar://${normalizePath(javaHome)}/lib/src.zip!/$m" type="simple" />"""))
        val jbrClassPath: String =
          mkString(for (m <- jbrModules.elements) yield
            s"""            <root url="jrt://${normalizePath(jbrHome)}!/$m" type="simple" />""")

        def ideaLibs: String = {
          return mkString(for (p <- Os.Path.walk(ideaLibDir, F, T, (f: Os.Path) => f.ext == "jar"))
            yield s"""            <root url="jar://${normalizePath(p)}!/" type="simple" />""")
        }

        def ideaJavaLibs: String = {
          return mkString(for (p <- Os.Path.walk(ideaPluginsDir / "java" / "lib", F, T, (f: Os.Path) => f.ext == "jar"))
            yield s"""            <root url="jar://${normalizePath(p)}!/" type="simple" />""")
        }

        def ideaScalaLibs: String = {
          return mkString(for (p <- Os.Path.walk(ideaPluginsDir / "Scala" / "lib", F, T, (f: Os.Path) => f.ext == "jar"))
            yield s"""            <root url="jar://${normalizePath(p)}!/" type="simple" />""")
        }

        @strictpure def idea: String =
          st"""    <jdk version="2">
              |      <name value="Sireum$devSuffix" />
              |      <type value="IDEA JDK" />
              |      <homePath value="$ideaDir" />
              |      <roots>
              |        <annotationsPath>
              |          <root type="composite">
              |            <root url="jar://$$APPLICATION_HOME_DIR$$/plugins/java/lib/jdkAnnotations.jar!/" type="simple" />
              |          </root>
              |        </annotationsPath>
              |        <classPath>
              |          <root type="composite">
              |$jbrClassPath
              |$ideaLibs
              |          </root>
              |        </classPath>
              |        <javadocPath>
              |          <root type="composite">
              |            <root url="https://docs.oracle.com/en/java/javase/17/docs/api/" type="simple" />
              |          </root>
              |        </javadocPath>
              |      </roots>
              |      <additional sdk="Jbr">
              |        <option name="mySandboxHome" value="$$USER_HOME$$/.SireumIVE$devSuffix-sandbox" />
              |      </additional>
              |    </jdk>""".render


        @strictpure def ideaScala: String =
          st"""    <jdk version="2">
              |      <name value="Sireum$devSuffix (with Scala Plugin)" />
              |      <type value="IDEA JDK" />
              |      <homePath value="$ideaDir" />
              |      <roots>
              |        <annotationsPath>
              |          <root type="composite" />
              |        </annotationsPath>
              |        <classPath>
              |          <root type="composite">
              |$jbrClassPath
              |$ideaLibs
              |$ideaJavaLibs
              |$ideaScalaLibs
              |          </root>
              |        </classPath>
              |        <javadocPath>
              |          <root type="composite">
              |            <root url="https://docs.oracle.com/en/java/javase/17/docs/api/" type="simple" />
              |          </root>
              |        </javadocPath>
              |      </roots>
              |      <additional sdk="Jbr">
              |        <option name="mySandboxHome" value="$$USER_HOME$$/.SireumIVE$devSuffix-sandbox" />
              |      </additional>
              |    </jdk>""".render

        val r =
          st"""<application>
              |  <component name="ProjectJdkTable">
              |    <jdk version="2">
              |      <name value="Java" />
              |      <type value="JavaSDK" />
              |      <version value="$javaVer" />
              |      <homePath value="$javaHome" />
              |      <roots>
              |        <annotationsPath>
              |          <root type="composite">
              |            <root url="jar://$$APPLICATION_HOME_DIR$$/lib/jdkAnnotations.jar!/" type="simple" />
              |          </root>
              |        </annotationsPath>
              |        <classPath>
              |          <root type="composite">
              |$jdkClassPath
              |          </root>
              |        </classPath>
              |        <javadocPath>
              |          <root type="composite">
              |            <root url="https://docs.oracle.com/en/java/javase/16/docs/api/" type="simple" />
              |          </root>
              |        </javadocPath>
              |        <sourcePath>
              |          <root type="composite">
              |$jdkSourcePath
              |          </root>
              |        </sourcePath>
              |      </roots>
              |      <additional />
              |    </jdk>
              |    <jdk version="2">
              |      <name value="Jbr" />
              |      <type value="JavaSDK" />
              |      <homePath value="$jbrHome" />
              |      <roots>
              |        <annotationsPath>
              |          <root type="composite">
              |            <root url="jar://$$APPLICATION_HOME_DIR$$/lib/jdkAnnotations.jar!/" type="simple" />
              |          </root>
              |        </annotationsPath>
              |        <classPath>
              |          <root type="composite">
              |$jbrClassPath
              |          </root>
              |        </classPath>
              |        <javadocPath>
              |          <root type="composite">
              |            <root url="https://docs.oracle.com/en/java/javase/11/docs/api/" type="simple" />
              |          </root>
              |        </javadocPath>
              |        <sourcePath>
              |          <root type="composite" />
              |        </sourcePath>
              |      </roots>
              |      <additional />
              |    </jdk>
              |$idea
              |$ideaScala
              |  </component>
              |</application>""".render
        return r
      }

      @strictpure def fileTypes: String =
        st"""<application>
            |  <component name="FileTypeManager" version="17">
            |    <extensionMap>
            |      <mapping ext="cmd" type="Scala" />
            |      <removed_mapping ext="cmd" approved="true" type="PLAIN_TEXT" />
            |    </extensionMap>
            |  </component>
            |</application>""".render

      @strictpure def projectDefault: String =
        st"""<application>
            |  <component name="ProjectManager">
            |    <defaultProject>
            |      <component name="uidesigner-configuration">
            |        <option name="INSTRUMENT_CLASSES" value="false" />
            |      </component>
            |    </defaultProject>
            |  </component>
            |</application>""".render

      (Os.home / s".SireumIVE$devSuffix-sandbox").mkdirAll()

      val projectName = o.projectName.get

      val projectPath = project.string

      val files: Map[ISZ[String], ST] =
        if (o.mode == Cli.SireumToolsIvegenIveMode.Idea) {
          for (p <- project.list if p.ext == ".iml") {
            p.removeAll()
          }
          IveGen.idea(projectExists, isWin, uriPathSep(home.string), projectName,
            o.moduleName.getOrElse(projectName), o.appName.getOrElse("script"),
            projectPath, o.jdk.get, scalaVer, scalacPluginVer)
        } else {
          IveGen.mill((project / "build.sc").exists, isWin, uriPathSep(home.string), projectName,
            o.moduleName.getOrElse(projectName), o.packageName, o.appName.getOrElse("app"),
            projectPath, o.jdk.get, scalaVer, scalacPluginVer)
        }

      def writeFiles(): Unit = {
        for (pair <- files.entries) {
          val (path, text) = pair
          val p = project / st"${(path, Os.fileSep)}".render
          p.up.mkdirAll()
          p.writeOver(text.render)
        }
      }

      writeFiles()
      val mill: String = if (isWin) "mill.bat" else "mill"
      if (o.mode == Cli.SireumToolsIvegenIveMode.Mill) {
        val javaBin = javaHome / "bin"
        val envVarMap = ISZ("PATH" ~> s"$javaBin${Os.pathSep}${Os.env("PATH").get}")
        if (o.millPath) {
          val command: ISZ[String] =
            if (o.compile) ISZ(mill.string, "all", "__.compile", "mill.scalalib.GenIdea/idea")
            else ISZ(mill.string, "mill.scalalib.GenIdea/idea")
          Os.proc(command).at(project).env(envVarMap).console.runCheck()
        } else {
          var millPath = home / "bin" / "mill-build" / mill
          if (isWin || !millPath.exists) {
            millPath = homeOpt.get / "bin" / mill
          }
          val command: ISZ[String] =
            if (o.compile) ISZ(millPath.string, "all", "__.compile", "mill.scalalib.GenIdea/idea")
            else ISZ(millPath.string, "mill.scalalib.GenIdea/idea")
          Os.proc(command).at(project).env(envVarMap).console.runCheck()
        }
        writeFiles()
        for (f <- Os.Path.walk(project / ".idea_modules", F, F, (p: Os.Path) => ops.StringOps(p.name).endsWith(".iml"))) {
          f.writeOver(ops.StringOps(f.read).replaceAllLiterally("ideaCompileOutput", "compile"))
        }
      }
      val configOptions: Os.Path =
        if (Os.isMac) Os.home / "Library" / "Application Support" / "JetBrains" / s"SireumIVE$devSuffix" / "options"
        else Os.home / s".SireumIVE$devSuffix" / "config" / "options"
      configOptions.mkdirAll()
      val jdkTableXml = configOptions / "jdk.table.xml"
      val applicationLibrariesXml = configOptions / "applicationLibraries.xml"
      val fileTypesXml = configOptions / "filetypes.xml"
      val projectDefaultXml = configOptions / "project.default.xml"
      if (o.force || !jdkTableXml.exists) {
        println(s"Generated $jdkTableXml")
        jdkTableXml.writeOver(jdkTable)
      }
      if (o.force || !applicationLibrariesXml.exists) {
        println(s"Generated $applicationLibrariesXml")
        applicationLibrariesXml.writeOver(applicationLib)
      }
      if (o.force || !fileTypesXml.exists) {
        println(s"Generated $fileTypesXml")
        fileTypesXml.writeOver(fileTypes)
      }
      if (o.force || !projectDefaultXml.exists) {
        println(s"Generated $projectDefaultXml")
        projectDefaultXml.writeOver(projectDefault)
      }
      println(s"Generated Sireum IVE project at $project")
      return 0
    }
  }

  def opGen(o: Cli.SireumToolsOpgenOption, reporter: Reporter): Z = {
    if (o.args.size == 0) {
      println(o.help)
      return 0
    }
    if (o.args.size != 1) {
      eprintln("Expecting a single fully-qualified type name argument")
      return -1
    }

    val dir: Os.Path = o.output match {
      case Some(v) =>
        val d = Os.path(v)
        d.mkdirAll()
        d
      case _ => Os.cwd
    }

    val to = Cli.SireumSlangTipeOption(
      help = o.help,
      args = ISZ(),
      exclude = o.exclude,
      force = o.force,
      noRuntime = o.noRuntime,
      outline = T,
      sourcepath = o.sourcepath,
      strictAliasing = o.strictAliasing,
      verbose = o.verbose,
      save = o.save,
      load = o.load,
      gzip = o.gzip
    )
    val th: TypeHierarchy = SlangTipe.run(to, reporter) match {
      case Either.Left(v) => v
      case Either.Right(code) => return code
    }
    val lOpt: Option[String] = o.license match {
      case Some(v) =>
        val f = Os.path(v)
        if (f.exists && f.isFile) {
          Some(
            st"""/*
                | ${ops.StringOps(f.read).trim}
                | */""".render)
        } else {
          eprintln(s"$f is not a file")
          return -1
        }
      case _ => None()
    }
    val pOpt: Option[ISZ[String]] = if (o.packageName.nonEmpty) Some(o.packageName) else None()
    val topClassName: ISZ[String] = for (e <- ops.StringOps(o.args(0)).split((c: C) => c == '.')) yield ops.StringOps(e).trim
    val ocgen = ObjectPrinterGen(lOpt, pOpt, o.name.get, topClassName, th)
    val r = ocgen.gen(reporter)
    if (reporter.hasError) {
      reporter.printMessages()
      return -1
    }

    val f = dir / s"${ocgen.name}.scala"
    f.writeOver(r.render)
    println(s"Wrote $f")

    if (reporter.hasIssue) {
      println()
      reporter.printMessages()
    }

    return 0
  }

  def serGen(o: Cli.SireumToolsSergenOption, reporter: Reporter): Z = {
    if (o.args.isEmpty) {
      println(o.help)
      return 0
    }
    val lOpt = path2fileOpt("license file", o.license, T)
    val srcs = paths2files("Slang file", o.args, T)
    val destDir = path2fileOpt("output directory", o.outputDir, T).get
    if (!destDir.isDir) {
      eprintln(s"Path $destDir is not a directory")
      return -1
    }
    for (m <- o.modes) {
      val (name, mode): (String, SerializerGen.Mode.Type) = m match {
        case Cli.SireumToolsSergenSerializerMode.Json =>
          (
            if (o.modes.size > 1)
              if (o.name.isEmpty) "JSON" else s"${o.name.get}JSON"
            else if (o.name.isEmpty) "JSON"
            else o.name.get,
            SerializerGen.Mode.JSON
          )
        case Cli.SireumToolsSergenSerializerMode.Msgpack =>
          (
            if (o.modes.size > 1)
              if (o.name.isEmpty) "MsgPack" else s"${o.name.get}MsgPack"
            else if (o.name.isEmpty) "MsgPack"
            else o.name.get,
            SerializerGen.Mode.MessagePack
          )
      }
      val dest = destDir / s"$name.scala"
      val packageNameOpt: Option[ISZ[String]] = if (o.packageName.isEmpty) None() else Some(o.packageName)
      SerializerGenJvm.run(mode, lOpt, srcs, packageNameOpt, Some(name), reporter) match {
        case Some(out) =>
          dest.writeOver(out)
          println(s"Wrote $dest")
        case _ =>
          reporter.printMessages()
      }
    }
    return 0
  }

  def transGen(o: Cli.SireumToolsTransgenOption, reporter: Reporter): Z = {
    o.args.size match {
      case z"0" => println(o.help); return 0
      case _ =>
    }
    val lOpt = path2fileOpt("license file", o.license, T)
    val sources = paths2files("Slang file", o.args, T)
    val destDir = path2fileOpt("output directory", o.outputDir, T).get
    if (!destDir.isDir) {
      eprintln(s"Path $destDir is not a directory")
      return -1
    }
    for (m <- o.modes) {
      val (name, mode): (String, B) = m match {
        case Cli.SireumToolsTransgenTransformerMode.Immutable =>
          (
            if (o.modes.size > 1)
              if (o.name.isEmpty) "Transformer" else s"${o.name.get}Transformer"
            else if (o.name.isEmpty) "Transformer"
            else o.name.get,
            T
          )
        case Cli.SireumToolsTransgenTransformerMode.Mutable =>
          (
            if (o.modes.size > 1)
              if (o.name.isEmpty) "MTransformer" else s"M${o.name.get}Transformer"
            else if (o.name.isEmpty) "MTransformer"
            else o.name.get,
            F
          )
      }
      val dest = destDir / s"$name.scala"
      TransformerGenJvm.run(mode, lOpt, sources, Some(name), o.exclude, reporter) match {
        case Some(out) =>
          dest.writeOver(out)
          println(s"Wrote $dest")
        case _ =>
          reporter.printMessages()
      }
    }
    return 0
  }

}
