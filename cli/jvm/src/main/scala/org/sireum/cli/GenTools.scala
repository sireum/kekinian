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

package org.sireum.cli

import org.sireum._
import org.sireum.Sireum._
import org.sireum.message._
import org.sireum.tools._

object GenTools {

  def cliGen(o: Cli.CligenOption): Int =
    try {
      o.args.size match {
        case z"0" => println(o.help); return 0
        case z"1" =>
        case _ => println(s"Expecting one argument, but found ${o.args.size}."); return -1
      }
      val lOpt = path2fileOpt("license file", o.license, T)
      val src = paths2fileOpt("config file", o.args, T).get
      val destDir = path2fileOpt("output directory", o.outputDir, T).get
      if (!destDir.isDirectory) error(s"Path ${destDir.getPath} is not a directory")
      val dest = os.Path(destDir.getCanonicalFile) / s"${o.name.get}.scala"
      val (first, second) = o.width.size match {
        case z"2" => (o.width(0), o.width(1))
        case _ => (z"25", z"55")
      }
      val out = CliGenJvm(lOpt, src, dest.toIO, o.packageName, o.name, first, second)
      os.write.over(dest, out)
      println(s"Wrote $dest")
      0
    } catch {
      case e: Throwable =>
        eprintln(e.getMessage)
        -1
    }

  def iveGen(o: Cli.IvegenOption): Int =
    if (o.args.size != 1) {
      println(o.help)
      0
    } else {
      val HomeNotFound = -1
      val InvalidDir = -2
      val ProjectExists = -3

      val d = os.Path(path2fileOpt("project parent folder", Some(o.args(0)), F).get.getCanonicalFile)
      if (!os.exists(d)) {
        os.makeDir.all(d)
      }
      if (!os.exists(d)) {
        eprintln(s"Could not create $d")
        return InvalidDir
      }
      val project = d / o.name.get.value
      if (os.exists(project) && o.mode == Cli.IveMode.Idea) {
        eprintln(s"Cannot overwrite an existing $project directory in idea mode")
        return ProjectExists
      } else os.makeDir.all(project)

      if (!homeFound) return HomeNotFound
      val home = if (isWin) homeOpt.get.toString.replaceAllLiterally("\\", "/") else homeOpt.get

      val devSuffixOpt = homeOpt.get.last match {
        case "Sireum-dev" | "kekinian" | "sireum-kekinian" => scala.Some("-dev")
        case "Sireum" => scala.Some("")
        case _ => scala.None
      }

      def normalizePath(path: os.Path): Predef.String = uriPathSep(path.toNIO.toString)

      for (devSuffix <- devSuffixOpt) {
        val scalaDir = normalizePath(scalaHome)
        def applicationLib: Predef.String = {
          s"""<application>
             |  <component name="libraryTable">
             |    <library name="Scala" type="Scala">
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
             |    </library>
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

        def jdkPaths: (String, String) = (
          s"""            <root url="jrt://JAVA_HOME!/java.base" type="simple" />
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
             |            <root url="jrt://JAVA_HOME!/jdk.zipfs" type="simple" />""".stripMargin
            .replaceAllLiterally("JAVA_HOME", normalizePath(javaHome)),
          s"""            <root url="jar://JAVA_HOME/lib/src.zip!/java.se" type="simple" />
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
             |            <root url="jar://JAVA_HOME/lib/src.zip!/jdk.internal.vm.compiler.management" type="simple" />""".stripMargin
            .replaceAllLiterally("JAVA_HOME", normalizePath(javaHome))
        )

        def jdkTable: Predef.String = {
          val (jdkClassPath, jdkSourcePath) = jdkPaths
          val ideaLibs = (for (p <- os.walk(ideaLibDir) if p.last.endsWith(".jar"))
            yield s"""            <root url="jar://${normalizePath(p)}!/" type="simple" />""").mkString("\n")
          val ideaScalaLibs = (for (p <- os.walk(ideaPluginsDir / 'Scala / 'lib) if p.last.endsWith(".jar"))
            yield s"""            <root url="jar://${normalizePath(p)}!/" type="simple" />""").mkString("\n")
          s"""<application>
             |  <component name="ProjectJdkTable">
             |    <jdk version="2">
             |      <name value="Java" />
             |      <type value="JavaSDK" />
             |      <version value="Zulu $zuluVer" />
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
             |    <jdk version="2">
             |      <name value="Sireum$devSuffix" />
             |      <type value="IDEA JDK" />
             |      <version value="Zulu $zuluVer" />
             |      <homePath value="$ideaDir" />
             |      <roots>
             |        <annotationsPath>
             |          <root type="composite" />
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
             |    </jdk>
             |    <jdk version="2">
             |      <name value="Sireum$devSuffix (with Scala Plugin)" />
             |      <type value="IDEA JDK" />
             |      <version value="Zulu $zuluVer" />
             |      <homePath value="$ideaDir" />
             |      <roots>
             |        <annotationsPath>
             |          <root type="composite" />
             |        </annotationsPath>
             |        <classPath>
             |          <root type="composite">
             |$jdkClassPath
             |$ideaLibs
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
             |    </jdk>
             |  </component>
             |</application>""".stripMargin
        }

        val configOptions = os.home / s".SireumIVE$devSuffix" / 'config / 'options
        os.makeDir.all(configOptions)
        val jdkTableXml = configOptions / "jdk.table.xml"
        val applicationLibrariesXml = configOptions / "applicationLibraries.xml"
        if (!os.exists(jdkTableXml)) {
          println(s"Generated $jdkTableXml")
          os.write.over(jdkTableXml, jdkTable)
        }
        if (!os.exists(applicationLibrariesXml)) {
          println(s"Generated $applicationLibrariesXml")
          os.write.over(applicationLibrariesXml, applicationLib)
        }
      }

      val name = o.name.get

      val projectPath = project.toString

      val files =
        if (o.mode == Cli.IveMode.Idea)
          IveGen.idea(isWin, uriPathSep(home.toString), name, projectPath, o.jdk.get, scalaVer, scalacPluginVer)
        else
          IveGen.mill(os.exists(project / "build.sc"), name, projectPath, o.jdk.get, scalaVer, scalacPluginVer)

      for ((path, text) <- files.entries) {
        val p = project / os.RelPath(st"${(path, "/")}".render.value)
        os.makeDir.all(p / os.up)
        os.write.over(p, text.render.value)
      }
      val mill = if (isWin) "mill.bat" else "mill"
      if (o.mode == Cli.IveMode.Mill) {
        val platform = if (isWin) "win" else if (scala.util.Properties.isLinux) "linux" else "mac"
        val javaBin = homeOpt.get / 'bin / platform / 'java / 'bin
        val envVarMap =
          scala.collection.immutable
            .Map(("PATH", s"$javaBin${java.io.File.pathSeparatorChar}${System.getenv("PATH")}"))
        if (o.millPath)
          os.proc(mill, 'all, "__.compile", "mill.scalalib.GenIdea/idea")
            .call(cwd = project, stdout = os.Inherit, stderr = os.Inherit)
        else
          os.proc(homeOpt.get / 'bin / mill, 'all, "__.compile", "mill.scalalib.GenIdea/idea")
            .call(cwd = project, env = envVarMap, stdout = os.Inherit, stderr = os.Inherit)
      }
      println(s"Generated Sireum IVE project at $project")
      0
    }

  def serGen(o: Cli.SergenOption): Int =
    try {
      if (o.args.isEmpty) {
        println(o.help)
        return 0
      }
      val lOpt = path2fileOpt("license file", o.license, T)
      val srcs = paths2files("Slang file", o.args, T)
      val destDir = path2fileOpt("output directory", o.outputDir, T).get
      if (!destDir.isDirectory) error(s"Path ${destDir.getPath} is not a directory")
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
        val dest = os.Path(destDir.getCanonicalFile) / s"$name.scala"
        val reporter = Reporter.create
        val packageNameOpt: Option[ISZ[String]] = if (o.packageName.isEmpty) None() else Some(o.packageName)
        SerializerGenJvm(T, mode, lOpt, srcs, dest.toIO, packageNameOpt, Some(String(name)), reporter) match {
          case Some(out) =>
            os.write.over(dest, out)
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

  def transGen(o: Cli.TransgenOption): Int =
    try {
      o.args.size match {
        case z"0" => println(o.help); return 0
        case z"1" =>
        case _ => println(s"Expecting one argument, but found ${o.args.size}."); return -1
      }
      val lOpt = path2fileOpt("license file", o.license, T)
      val src = paths2fileOpt("Slang file", o.args, T).get
      val destDir = path2fileOpt("output directory", o.outputDir, T).get
      if (!destDir.isDirectory) error(s"Path ${destDir.getPath} is not a directory")
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
        val dest = os.Path(destDir.getCanonicalFile) / s"$name.scala"
        val reporter = Reporter.create
        TransformerGenJvm(T, mode, lOpt, src, dest.toIO, Some(String(name)), reporter) match {
          case Some(out) =>
            os.write.over(dest, out)
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
