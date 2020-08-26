/*
 Copyright (c) 2020, Robby, Kansas State University
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
import org.sireum.Sireum._
import org.sireum.bitcodec.Spec
import org.sireum.lang._
import org.sireum.lang.parser.Parser
import org.sireum.message._
import org.sireum.tools._

object GenTools {


  def bcGen(o: Cli.BcgenOption): Z = {
    o.args.size match {
      case z"0" => println(o.help); return 0
      case z"1" =>
      case _ => println(s"Expecting one argument, but found ${o.args.size}."); return -1
    }

    val lOpt = path2fileOpt("license file", o.license, T)
    val src = paths2fileOpt("config file", o.args, T).get
    val destDir = path2fileOpt("output directory", o.outputDir, T).get
    if (!destDir.isDir) error(s"Path $destDir is not a directory")
    val outTemp = Os.temp()
    val r = SlangRunner.run(Cli.SlangRunOption("", ISZ(src.value), None(),
      Some(outTemp.string), F, F, F))
    if (r != 0) {
      eprintln(outTemp.read)
      return r
    }
    val reporter = Reporter.create
    val text = src.read
    Parser.parseTopUnit[ast.TopUnit.Program](text, F, T, F, Some(src.value), reporter) match {
      case Some(p) if !reporter.hasIssue =>
        val (_, program) = FrontEnd.checkWorksheet(None(), p, reporter)
        if (reporter.hasIssue) {
          reporter.printMessages()
          return -1
        }
        val specText = outTemp.read
        outTemp.removeAll()
        Spec.fromJSON(specText) match {
          case Either.Left(spec) =>
            for (mode <- o.mode) {
              val ext: String = mode match {
                case Cli.BitCodecMode.Program => "scala"
                case Cli.BitCodecMode.Script => "sc"
                case Cli.BitCodecMode.Json => "json"
                case Cli.BitCodecMode.Dot => "dot"
              }
              val dest = destDir / s"${o.name.get}.$ext"
              val prev: String = if (dest.isFile) dest.read else ""
              val output: BitCodecGen.Output.Type = mode match {
                case Cli.BitCodecMode.Program => BitCodecGen.Output.Program
                case Cli.BitCodecMode.Script => BitCodecGen.Output.Script
                case Cli.BitCodecMode.Json => BitCodecGen.Output.Json
                case Cli.BitCodecMode.Dot => BitCodecGen.Output.Dot
              }
              val r = BitCodecGen.gen(output, !o.isLittleEndian, lOpt.map(_.read), src.name,
                o.packageName, o.name.get, text, o.traits, spec, org.sireum.bitcodec.JSON.Printer.printSpec(spec),
                program, prev, reporter)
              if (reporter.hasIssue) {
                reporter.printMessages()
                return -1
              }
              dest.writeOver(r.render)
              println(s"Wrote $dest")
            }
            0
          case _ =>
            eprintln(s"Invalid config produced by running ${o.args(0)}")
            eprintln(specText)
            -1
        }
      case _ =>
        if (reporter.hasIssue) {
          reporter.printMessages()
        }
        -1
    }
  }

  def cliGen(o: Cli.CligenOption): Z =
    try {
      o.args.size match {
        case z"0" => println(o.help); return 0
        case z"1" =>
        case _ => println(s"Expecting one argument, but found ${o.args.size}."); return -1
      }
      val lOpt = path2fileOpt("license file", o.license, T)
      val src = paths2fileOpt("config file", o.args, T).get
      val destDir = path2fileOpt("output directory", o.outputDir, T).get
      if (!destDir.isDir) error(s"Path $destDir is not a directory")
      val dest = destDir / s"${o.name.get}.scala"
      val (first, second) = o.width.size match {
        case z"2" => (o.width(0), o.width(1))
        case _ => (z"25", z"55")
      }
      val outTemp = Os.temp()
      val r = SlangRunner.run(Cli.SlangRunOption("", ISZ(src.value), None(),
        Some(outTemp.string), T, F, F))
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
          println(s"Wrote $dest")
          0
        case _ =>
          eprintln(s"Invalid config produced by running ${o.args(0)}")
          eprintln(configText)
          -1
      }
    } catch {
      case e: Throwable =>
        e.printStackTrace()
        eprintln(e.getMessage)
        -1
    }

  def iveGen(o: Cli.IvegenOption): Z =
    if (o.args.size != 1) {
      println(o.help)
      0
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

      if (!homeFound) return HomeNotFound
      if (!(javaFound && scalaFound)) return JavaOrScalaNotFound
      if (!ideaDir.exists) {
        eprintln("Sireum IVE is not installed")
        return IdeaNotFound
      }
      val home = homeOpt.get
      val javaHome = javaHomeOpt.get
      val scalaHome = scalaHomeOpt.get
      val isWin = Os.isWin

      def uriPathSep(s: Predef.String): Predef.String =
        if (isWin) s.replace("\\", "/") else s

      val devSuffix = if (isDev) "" else "-dev"

      def normalizePath(path: Os.Path): Predef.String = uriPathSep(path.string.value)

      val scalaDir = normalizePath(scalaHome)

      def applicationLib: Predef.String = {
        def library(name: String): Predef.String =
          s"""    <library name="$name" type="Scala">
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
             |    </library>""".stripMargin
        s"""<application>
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
           |</application>""".stripMargin
      }

      def jdkTable: Predef.String = {
        val modules: Set[String] = Set ++ ISZ(
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

        val (jdkClassPath, jdkSourcePath) =
          ( (for (m <- modules.elements) yield
               s"""            <root url="jrt://${normalizePath(javaHome)}!/$m" type="simple" />""").elements.mkString("\n"),
            (for (m <- modules.elements) yield
               s"""            <root url="jar://${normalizePath(javaHome)}/lib/src.zip!/$m" type="simple" />""").elements.mkString("\n"))
        lazy val ideaLibs = (for (p <- Os.Path.walk(ideaLibDir, F, T, f => f.string.value.endsWith(".jar")))
          yield
            s"""            <root url="jar://${normalizePath(p)}!/" type="simple" />""").elements.mkString("\n")
        lazy val ideaJavaLibs = (for (p <- Os.Path.walk(ideaPluginsDir / "java" / "lib", F, T, f => f.string.value.endsWith(".jar")))
          yield
            s"""            <root url="jar://${normalizePath(p)}!/" type="simple" />""").elements.mkString("\n")
        lazy val ideaScalaLibs = (for (p <- Os.Path.walk(ideaPluginsDir / "Scala" / "lib", F, T, f => f.string.value.endsWith(".jar")))
          yield
            s"""            <root url="jar://${normalizePath(p)}!/" type="simple" />""").elements.mkString("\n")

        def idea: String =
          s"""    <jdk version="2">
             |      <name value="Sireum$devSuffix" />
             |      <type value="IDEA JDK" />
             |      <version value="$javaVer" />
             |      <homePath value="$ideaDir" />
             |      <roots>
             |        <annotationsPath>
             |          <root type="composite">
             |            <root url="jar://$$APPLICATION_HOME_DIR$$/plugins/java/lib/jdkAnnotations.jar!/" type="simple" />
             |          </root>
             |        </annotationsPath>
             |        <classPath>
             |          <root type="composite">
             |$jdkClassPath
             |$ideaLibs
             |          </root>
             |        </classPath>
             |        <javadocPath>
             |          <root type="composite" />
             |        </javadocPath>
             |        <sourcePath>
             |          <root type="composite">
             |$jdkSourcePath
             |          </root>
             |        </sourcePath>
             |      </roots>
             |      <additional sdk="Java">
             |        <option name="mySandboxHome" value="$$USER_HOME$$/.SireumIVE$devSuffix-sandbox" />
             |      </additional>
             |    </jdk>""".stripMargin


        def ideaScala: String =
          s"""    <jdk version="2">
             |      <name value="Sireum$devSuffix (with Scala Plugin)" />
             |      <type value="IDEA JDK" />
             |      <version value="$javaVer" />
             |      <homePath value="$ideaDir" />
             |      <roots>
             |        <annotationsPath>
             |          <root type="composite" />
             |        </annotationsPath>
             |        <classPath>
             |          <root type="composite">
             |$jdkClassPath
             |$ideaLibs
             |$ideaJavaLibs
             |$ideaScalaLibs
             |          </root>
             |        </classPath>
             |        <javadocPath>
             |          <root type="composite" />
             |        </javadocPath>
             |        <sourcePath>
             |          <root type="composite">
             |$jdkSourcePath
             |          </root>
             |        </sourcePath>
             |      </roots>
             |    </jdk>""".stripMargin

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
            |          <root type="composite" />
            |        </javadocPath>
            |        <sourcePath>
            |          <root type="composite">
            |$jdkSourcePath
            |          </root>
            |        </sourcePath>
            |      </roots>
            |      <additional />
            |    </jdk>
            |$idea
            |$ideaScala
            |    <additional sdk="Java">
            |      <option name="mySandboxHome" value="$$USER_HOME$$/.SireumIVE$devSuffix-sandbox" />
            |    </additional>
            |  </component>
            |</application>""".render.value
      }

      def fileTypes: Predef.String = {
        s"""<application>
           |  <component name="FileTypeManager" version="17">
           |    <extensionMap>
           |      <mapping ext="cmd" type="Scala" />
           |      <removed_mapping ext="cmd" approved="true" type="PLAIN_TEXT" />
           |    </extensionMap>
           |  </component>
           |</application>""".stripMargin
      }

      (Os.home / s".SireumIVE$devSuffix-sandbox").mkdirAll()

      val projectName = o.projectName.get

      val projectPath = project.toString

      val files =
        if (o.mode == Cli.IveMode.Idea) {
          for (p <- project.list if p.string.value.endsWith(".iml")) p.removeAll()
          IveGen.idea(projectExists, isWin, uriPathSep(home.toString), projectName,
            o.moduleName.getOrElse(projectName), o.appName.getOrElse("script"),
            projectPath, o.jdk.get, scalaVer, scalacPluginVer)
        } else
          IveGen.mill((project / "build.sc").exists, isWin, uriPathSep(home.toString), projectName,
            o.moduleName.getOrElse(projectName), o.packageName, o.appName.getOrElse("app"),
            projectPath, o.jdk.get, scalaVer, scalacPluginVer)

      def writeFiles(): Unit = {
        for ((path, text) <- files.entries) {
          val p = project / st"${(path, Os.fileSep)}".render.value
          p.up.mkdirAll()
          p.writeOver(text.render)
        }
      }

      writeFiles()
      val mill = if (isWin) "mill.bat" else "mill"
      if (o.mode == Cli.IveMode.Mill) {
        val javaBin = javaHome / "bin"
        val envVarMap = ISZ("PATH" ~> s"$javaBin${java.io.File.pathSeparatorChar}${System.getenv("PATH")}")
        if (o.millPath) {
          val command: ISZ[String] =
            if (o.compile) ISZ(mill.string, "all", "__.compile", "mill.scalalib.GenIdea/idea")
            else ISZ(mill.string, "mill.scalalib.GenIdea/idea")
          Os.proc(command).at(project).env(envVarMap).console.runCheck()
        } else {
          var millPath = home / "bin" / "mill-build" / mill
          if (isWin || !millPath.exists) millPath = homeOpt.get / "bin" / mill
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
      val configOptions =
        if (scala.util.Properties.isMac) Os.home / "Library" / "Application Support" / "JetBrains" / s"SireumIVE$devSuffix" / "options"
        else Os.home / s".SireumIVE$devSuffix" / "config" / "options"
      configOptions.mkdirAll()
      val jdkTableXml = configOptions / "jdk.table.xml"
      val applicationLibrariesXml = configOptions / "applicationLibraries.xml"
      val fileTypesXml = configOptions / "filetypes.xml"
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
      println(s"Generated Sireum IVE project at $project")
      0
    }

  def serGen(o: Cli.SergenOption): Z =
    try {
      if (o.args.isEmpty) {
        println(o.help)
        return 0
      }
      val lOpt = path2fileOpt("license file", o.license, T)
      val srcs = paths2files("Slang file", o.args, T)
      val destDir = path2fileOpt("output directory", o.outputDir, T).get
      if (!destDir.isDir) error(s"Path $destDir is not a directory")
      for (m <- o.modes) {
        val (name, mode) = m match {
          case Cli.SerializerMode.Json =>
            (
              if (o.modes.size > 1)
                if (o.name.isEmpty) "JSON" else s"${o.name.get}JSON"
              else if (o.name.isEmpty) "JSON"
              else o.name.get.value,
              SerializerGen.Mode.JSON
            )
          case Cli.SerializerMode.Msgpack =>
            (
              if (o.modes.size > 1)
                if (o.name.isEmpty) "MsgPack" else s"${o.name.get}MsgPack"
              else if (o.name.isEmpty) "MsgPack"
              else o.name.get.value,
              SerializerGen.Mode.MessagePack
            )
        }
        val dest = destDir / s"$name.scala"
        val reporter = Reporter.create
        val packageNameOpt: Option[ISZ[String]] = if (o.packageName.isEmpty) None() else Some(o.packageName)
        SerializerGenJvm.run(T, mode, lOpt, srcs, dest, packageNameOpt, Some(String(name)), reporter) match {
          case Some(out) =>
            dest.writeOver(out)
            println(s"Wrote $dest")
          case _ =>
            reporter.printMessages()
        }
      }
      0
    } catch {
      case e: Throwable =>
        e.printStackTrace()
        -1
    }

  def transGen(o: Cli.TransgenOption): Z =
    try {
      o.args.size match {
        case z"0" => println(o.help); return 0
        case _ =>
      }
      val lOpt = path2fileOpt("license file", o.license, T)
      val sources = paths2files("Slang file", o.args, T)
      val destDir = path2fileOpt("output directory", o.outputDir, T).get
      if (!destDir.isDir) error(s"Path $destDir is not a directory")
      for (m <- o.modes) {
        val (name, mode) = m match {
          case Cli.TransformerMode.Immutable =>
            (
              if (o.modes.size > 1)
                if (o.name.isEmpty) "Transformer" else s"${o.name.get}Transformer"
              else if (o.name.isEmpty) "Transformer"
              else o.name.get.value,
              T
            )
          case Cli.TransformerMode.Mutable =>
            (
              if (o.modes.size > 1)
                if (o.name.isEmpty) "MTransformer" else s"M${o.name.get}Transformer"
              else if (o.name.isEmpty) "MTransformer"
              else o.name.get.value,
              F
            )
        }
        val dest = destDir / s"$name.scala"
        val reporter = Reporter.create
        TransformerGenJvm.run(T, mode, lOpt, sources, Some(String(name)), reporter) match {
          case Some(out) =>
            dest.writeOver(out)
            println(s"Wrote $dest")
          case _ =>
            reporter.printMessages()
        }
      }
      0
    } catch {
      case e: Throwable =>
        eprintln(e.getMessage)
        -1
    }

}
