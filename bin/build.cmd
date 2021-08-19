::#! 2> /dev/null                                                                                           #
@ 2>/dev/null # 2>nul & echo off & goto BOF                                                                 #
export SIREUM_HOME=$(cd -P $(dirname "$0")/.. && pwd -P)                                                    #
if [ ! -z ${SIREUM_PROVIDED_SCALA++} ]; then                                                                #
  SIREUM_PROVIDED_JAVA=true                                                                                 #
fi                                                                                                          #
"${SIREUM_HOME}/bin/init.sh" || exit $?                                                                     #
if [ -n "$COMSPEC" -a -x "$COMSPEC" ]; then                                                                 #
  export SIREUM_HOME=$(cygpath -C OEM -w -a ${SIREUM_HOME})                                                 #
  if [ -z ${SIREUM_PROVIDED_JAVA++} ]; then                                                                 #
    export PATH="${SIREUM_HOME}/bin/win/java":"${SIREUM_HOME}/bin/win/z3":"$PATH"                           #
    export PATH="$(cygpath -C OEM -w -a ${JAVA_HOME}/bin)":"$(cygpath -C OEM -w -a ${Z3_HOME}/bin)":"$PATH" #
  fi                                                                                                        #
elif [ "$(uname)" = "Darwin" ]; then                                                                        #
  if [ -z ${SIREUM_PROVIDED_JAVA++} ]; then                                                                 #
    export PATH="${SIREUM_HOME}/bin/mac/java/bin":"${SIREUM_HOME}/bin/mac/z3/bin":"$PATH"                   #
  fi                                                                                                        #
elif [ "$(expr substr $(uname -s) 1 5)" = "Linux" ]; then                                                   #
  if [ -z ${SIREUM_PROVIDED_JAVA++} ]; then                                                                 #
    if [ "$(uname -m)" = "aarch64" ]; then                                                                  #
      export PATH="${SIREUM_HOME}/bin/linux/arm/java/bin":"$PATH"                                           #
    else                                                                                                    #
      export PATH="${SIREUM_HOME}/bin/linux/java/bin":"${SIREUM_HOME}/bin/linux/z3/bin":"$PATH"             #
    fi                                                                                                      #
  fi                                                                                                        #
fi                                                                                                          #
if [ -f "$0.com" ] && [ "$0.com" -nt "$0" ]; then                                                           #
  exec "$0.com" "$@"                                                                                        #
else                                                                                                        #
  rm -fR "$0.com"                                                                                           #
  exec "${SIREUM_HOME}/bin/sireum" slang run -n "$0" "$@"                                                   #
fi                                                                                                          #
:BOF
setlocal
set SIREUM_HOME=%~dp0../
call "%~dp0init.bat" || exit /B %errorlevel%
if defined SIREUM_PROVIDED_SCALA set SIREUM_PROVIDED_JAVA=true
if not defined SIREUM_PROVIDED_JAVA set PATH=%~dp0win\java\bin;%~dp0win\z3\bin;%PATH%
set NEWER=False
if exist %~dpnx0.com for /f %%i in ('powershell -noprofile -executionpolicy bypass -command "(Get-Item %~dpnx0.com).LastWriteTime -gt (Get-Item %~dpnx0).LastWriteTime"') do @set NEWER=%%i
if "%NEWER%" == "True" goto native
del "%~dpnx0.com" > nul 2>&1
"%~dp0sireum.bat" slang run -n "%0" %*
exit /B %errorlevel%
:native
%~dpnx0.com %*
exit /B %errorlevel%
::!#
// #Sireum

import org.sireum._
import org.sireum.project.DependencyManager

def usage(): Unit = {
  println(
    st"""Sireum /build
        |Usage: ( setup[-ultimate] | project[-ultimate] | fresh        | native
        |       | tipe             | compile[-js]       | test         | mill
        |       | regen-project    | regen-slang        | regen-logika | regen-air
        |       | regen-act        | regen-server       | regen-cliopt | regen-cli
        |       | regen-fmide-cli  | m2[-lib[-js]]      | jitpack      | ghpack
        |       | cvc4             | z3                 | ram                      )*
      """.render)
}

val proyekName: String = "sireum-proyek"
val jarName: String = "sireum"

val homeBin: Os.Path = Os.slashDir
val home = homeBin.up
val sireumJar = homeBin / s"$jarName.jar"
val sireum = homeBin / (if (Os.isWin) "sireum.bat" else "sireum")
val mill = homeBin / (if (Os.isWin) "mill.bat" else "mill")
val mainFile = home / "cli" / "jvm" / "src" / "main" / "scala" / "org" / "sireum" / "Sireum.scala"
val versions = (home / "versions.properties").properties
val cache: Os.Path = Os.env("SIREUM_CACHE") match {
  case Some(p) =>
    val d = Os.path(p)
    if (!d.exists) {
      d.mkdirAll()
    }
    d
  case _ => Os.home / "Downloads" / "sireum"
}

def platformKind(kind: Os.Kind.Type): String = {
  kind match {
    case Os.Kind.Win => return "win"
    case Os.Kind.Linux => return "linux"
    case Os.Kind.LinuxArm => return "linux/arm"
    case Os.Kind.Mac => return "mac"
    case _ => return "unsupported"
  }
}

def platform: String = {
  return platformKind(Os.kind)
}

def installZ3(kind: Os.Kind.Type): Unit = {
  val version = versions.get("org.sireum.version.z3").get
  val dir = homeBin / platformKind(kind) / "z3"
  val ver = dir / "VER"

  if (ver.exists && ver.read == version) {
    return
  }

  val filename: String = kind match {
    case Os.Kind.Win => s"z3-$version-x64-win.zip"
    case Os.Kind.Linux => s"z3-$version-x64-glibc-2.31.zip"
    case Os.Kind.Mac => s"z3-$version-x64-osx-10.15.7.zip"
    case _ => return
  }

  val bundle = cache / filename

  if (!bundle.exists) {
    println(s"Please wait while downloading Z3 $version ...")
    bundle.up.mkdirAll()
    bundle.downloadFrom(s"https://github.com/Z3Prover/z3/releases/download/z3-$version/$filename")
  }

  println("Extracting Z3 ...")
  bundle.unzipTo(dir.up)
  println()

  for (p <- dir.up.list if ops.StringOps(p.name).startsWith("z3-")) {
    dir.removeAll()
    p.moveTo(dir)
  }

  kind match {
    case Os.Kind.Linux => (dir / "bin" / "z3").chmod("+x")
    case Os.Kind.Mac => (dir / "bin" / "z3").chmod("+x")
    case _ =>
  }

  ver.writeOver(version)
}


def z3(): Unit = {
  println("Installing Z3 for macOS ...")
  println()
  installZ3(Os.Kind.Mac)

  println("Installing Z3 for Linux ...")
  println()
  installZ3(Os.Kind.Linux)

  println("Installing Z3 for Windows ...")
  println()
  installZ3(Os.Kind.Win)
}


def installCVC4(kind: Os.Kind.Type): Unit = {
  val version = versions.get("org.sireum.version.cvc4").get
  val exe = homeBin / platformKind(kind) / (if (kind == Os.Kind.Win) "cvc4.exe" else "cvc4")
  val ver = homeBin / platformKind(kind) / ".cvc4.ver"

  if (ver.exists && ver.read == version) {
    return
  }

  val filename: String = kind match {
    case Os.Kind.Win => s"cvc4-$version-win64-opt.exe"
    case Os.Kind.Linux => s"cvc4-$version-x86_64-linux-opt"
    case Os.Kind.Mac => s"cvc4-$version-macos-opt"
    case _ => return
  }

  val drop = cache / filename

  if (!drop.exists) {
    println(s"Please wait while downloading CVC4 $version ...")
    drop.up.mkdirAll()
    drop.downloadFrom(s"https://github.com/cvc5/cvc5/releases/download/$version/$filename")
  }

  drop.copyOverTo(exe)
  println()

  kind match {
    case Os.Kind.Linux => exe.chmod("+x")
    case Os.Kind.Mac => exe.chmod("+x")
    case _ =>
  }

  ver.writeOver(version)
}


def cvc4(): Unit = {
  println("Installing CVC4 for macOS ...")
  println()
  installCVC4(Os.Kind.Mac)

  println("Installing CVC4 for Linux ...")
  println()
  installCVC4(Os.Kind.Linux)

  println("Installing CVC4 for Windows ...")
  println()
  installCVC4(Os.Kind.Win)
}


def buildMill(): Unit = {
  def copyIfNewer(from: Os.Path, to: Os.Path): Unit = {
    if (!to.exists || from.lastModified > to.lastModified) {
      from.copyOverTo(to)
    }
  }

  def symlink(p: Os.Path, target: Os.Path): Unit = {
    if (Os.isWin) {
      copyIfNewer(target, p)
    } else if (!p.isSymLink) {
      if (p.exists) {
        p.removeAll()
      }
      p.up.mkdirAll()
      p.mklink(target)
    }
  }

  val millBuild = home / "build"
  if (millBuild.exists) {
    proc"git pull".at(millBuild).console.runCheck()
  } else {
    proc"git clone https://github.com/sireum/build ${millBuild.name}".at(home).console.runCheck()
  }
  symlink(millBuild / "versions.properties", home / "versions.properties")
  val millBuildBin = millBuild / "bin"
  symlink(millBuildBin / sireumJar.name, homeBin / sireumJar.name)
  symlink(millBuildBin / sireum.name, sireum)
  symlink(millBuildBin / "scala", homeBin / "scala")
  symlink(millBuildBin / "prelude.sh", homeBin / "init.sh")
  symlink(millBuildBin / "prelude.ps1", homeBin / "init.ps1")
  val millBuildBinPlatform = millBuildBin / platform
  symlink(millBuildBinPlatform / "java", homeBin / platform / "java")
  if ((homeBin / platform / "z3").exists) {
    symlink(millBuildBinPlatform / "z3", homeBin / platform / "z3")
  }
  val millBuildLib = millBuild / "lib"
  for (p <- (home / "lib").list) {
    symlink(millBuildLib / p.name, p)
  }
  val homeBinMill = homeBin / "mill"
  val homeBinMillBat = homeBin / "mill.bat"
  (millBuildBin / "build.cmd").slash(ISZ())
  copyIfNewer(millBuild / "mill-standalone", homeBinMill)
  copyIfNewer(millBuild / "mill-standalone.bat", homeBinMillBat)
}


def build(fresh: B, isNative: B): Unit = {
  println("Building ...")

  val recompile: String = if (fresh) {
    " -f"
  } else {
    var r: String = ""
    val pr = proc"git status --porcelain".at(home).run()
    if (ops.StringOps(s"${pr.out}${pr.err}").trim === "") {
      val vOutOps =  ops.StringOps(proc"$sireum -v".run().out)
      if (vOutOps.contains("*") ||
        !vOutOps.contains(ops.StringOps(proc"git log -n 1 --date=format:%Y%m%d --pretty=format:4.%cd.%h".run().out).trim)) {
        r = " --recompile cli"
      }
    }
    r
  }
  val nativ: String = if (isNative) " --native" else ""

  val r = proc"$sireum proyek assemble -n $proyekName -j $jarName -m org.sireum.Sireum --par --sha3 --ignore-runtime$recompile$nativ .".at(home).console.run()
  if (r.exitCode == 0) {
    (home / "out" / proyekName / "assemble" / sireumJar.name).copyOverTo(sireumJar)
    if (isNative) {
      val exePath: Os.Path = Os.kind match {
        case Os.Kind.Win => homeBin / "win" / s"$jarName.exe"
        case Os.Kind.Linux => homeBin / "linux" / jarName
        case Os.Kind.LinuxArm => homeBin / "linux" / "arm" / jarName
        case Os.Kind.Mac => homeBin / "mac" / jarName
        case _ => halt("Infeasible")
      }
      (home / "out" / proyekName / "assemble" / exePath.name).copyOverTo(exePath)
    }
  } else {
    Os.exit(r.exitCode)
  }
  println()
}


def tipe(): Unit = {
  println("Slang type checking ...")
  val excludes = "hamr/codegen/arsit/resources,hamr/codegen/arsit/jvm/src/test/results,hamr/codegen/jvm/src/test/result"
  val includedDirs = Set ++ ISZ[String]("alir", "cli", "hamr", "logika", "proyek", "runtime", "server", "slang", "tools", "transpilers")
  val sourcepath: ISZ[Os.Path] = for (p <- home.list if includedDirs.contains(p.name)) yield p
  Os.proc(ISZ(sireum.string,
    "slang", "tipe", "--verbose", "-r", "--strict-aliasing", "-s", st"${(sourcepath, Os.pathSep)}".render, "-x", excludes)).at(home).console.runCheck()
  println()

}


def compile(isJs: B): Unit = {
  tipe()
  println("Compiling ...")
  proc"$sireum proyek compile -n $proyekName --par --sha3 --ignore-runtime${if (isJs) " --js" else ""} .".at(home).console.runCheck()
  println()
}


def test(): Unit = {
  tipe()

  println("Testing ...")
  val packageNames = ISZ[String](
    "org.sireum"
  )
  var names = ISZ[String](
    "org.sireum.lang",
    "org.sireum.tools",
    "org.sireum.hamr.codegen.test.expensive",
    "org.sireum.logika",
    "org.sireum.proyek"
  ) ++ (if (Os.isWin) ISZ[String]() else ISZ("org.sireum.server"))
  proc"$sireum proyek test -n $proyekName --par --sha3 --ignore-runtime --packages ${st"${(packageNames, ",")}".render} . ${st"${(names, " ")}".render}".
    at(home).console.runCheck()
  println()
}


def regenProject(): Unit = {
  val projectPackagePath = home / "runtime" / "library" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "project"
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "sergen", "-p", "org.sireum.project", "-l",
    s"${home / "license.txt"}", "-m", "json", s"${projectPackagePath / "Project.scala"}")).at(projectPackagePath).console.run()
}

def regenCliOpt(): Unit = {
  val cliPackagePath = home / "runtime" / "library" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "cli"
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "sergen", "-p", "org.sireum.cli", "-l",
    s"${home / "license.txt"}", "-m", "json", s"${cliPackagePath / "CliOpt.scala"}")).at(cliPackagePath).console.run()
}


def regenSlang(): Unit = {
  val astPackagePath = home / "slang" / "ast" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "lang" / "ast"
  val slangPackagePath = home / "slang" / "tipe" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "lang"
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "transgen", "-l", s"${home / "license.txt"}", "-m",
    "immutable,mutable", s"${astPackagePath / "AST.scala"}", s"${astPackagePath / "Typed.scala"}")).at(astPackagePath).console.run()
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "sergen", "-p", "org.sireum.lang.tipe", "-l",
    s"${home / "license.txt"}", "-m", "json,msgpack", s"${slangPackagePath / "symbol" / "Info.scala"}",
    s"${astPackagePath / "AST.scala"}", s"${astPackagePath / "Typed.scala"}")).at(slangPackagePath / "tipe").console.run()
}


def regenLogika(): Unit = {
  val logikaPackagePath = home / "logika" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "logika"
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "transgen", "-l", s"${home / "license.txt"}", "-e", "Composite",
    "-m", "immutable,mutable", "-n", "State", s"${logikaPackagePath / "State.scala"}")).at(logikaPackagePath).console.run()
}


def regenAir(): Unit = {
  val airRootPath = home / "hamr" / "air"
  val airPackagePath = airRootPath / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "hamr" / "ir"
  val asts: ISZ[String] = ISZ("AadlAST.scala", "BlessAST.scala", "Emv2AST.scala", "SmfAST.scala")

  Os.proc(ISZ[String]("java", "-jar", sireumJar.string, "tools", "transgen", "-l", s"${airRootPath / "license.txt"}",
    "-m", "immutable,mutable") ++ asts).at(airPackagePath).console.run()
  Os.proc(ISZ[String]("java", "-jar", sireumJar.string, "tools", "sergen", "-p", "org.sireum.hamr.ir", "-l", s"${airRootPath / "license.txt"}",
    "-m", "json,msgpack") ++ asts).at(airPackagePath).console.run()
}


def regenAct(): Unit = {
  val actPackagePath = home / "hamr" / "act" / "jvm" / "src" / "main" / "scala" / "org" / "sireum" / "hamr" / "act" / "ast"
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "transgen", "-l", s"${home / "license.txt"}", "-m",
    "immutable,mutable", s"${actPackagePath / "ActAst.scala"}")).at(actPackagePath).console.run()
}


def regenServer(): Unit = {
  val protocolPackagePath = home / "server" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "server" / "protocol"
  val logikaPackagePath = home / "logika" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "logika"
  val astPackagePath = home / "slang" / "ast" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "lang" / "ast"
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "sergen", "-p", "org.sireum.server.protocol", "-l",
    s"${home / "license.txt"}", "-m", "msgpack,json",
    s"${protocolPackagePath / "Message.scala"}",
    s"${logikaPackagePath / "State.scala"}",
    s"${logikaPackagePath / "Config.scala"}",
    s"${logikaPackagePath / "Smt2Query.scala"}",
    s"${astPackagePath / "Typed.scala"}"
  )).at(protocolPackagePath).console.run()
}

def regenCli(): Unit = {
  val sireumPackagePath = home / "cli" / "jvm" / "src" / "main" / "scala" / "org" / "sireum"
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "cligen", "-p", "org.sireum", "-l", s"${home / "license.txt"}",
    s"${sireumPackagePath / "cli.sc"}")).at(sireumPackagePath).console.run()
}

def regenFmideCli(): Unit = {
  val installPath = homeBin / "install"
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "cligen", "-s", "fmide.cmd",
    s"${installPath / "fmide-cli.sc"}")).at(installPath).console.run()
}


def m2(): Os.Path = {
  val repository = Os.home / ".m2" / "repository"
  val kekinianRepo = repository / "org" / "sireum" / "kekinian"
  kekinianRepo.removeAll()
  proc"$sireum proyek publish -n $proyekName --par --sha3 --ignore-runtime --m2 ${repository.up.canon} . org.sireum.kekinian".at(home).console.runCheck()
  return kekinianRepo
}

def m2Lib(isJs: B): Unit = {
  val repository = Os.home / ".m2" / "repository"

  def version: String = {
    for (line <- ops.StringOps(proc"$sireum --version".runCheck().out).split((c: C) => c === '\n')) {
      val lineOps = ops.StringOps(line)
      if (lineOps.contains(DependencyManager.libraryKey)) {
        val i = lineOps.stringIndexOf("->")
        val j: Z = lineOps.lastIndexOf(',')
        return ops.StringOps(lineOps.substring(i + 2, if (j >= 0) j else line.size)).trim
      }
    }
    halt("Could not detect Slang library version")
  }
  val target: String = if (isJs) "--target js" else "--target jvm"
  proc"$sireum proyek publish -n $proyekName --par --sha3 --ignore-runtime --slice library --m2 ${repository.up.canon} $target --version $version . org.sireum.kekinian".at(home).console.runCheck()
}

def jitpack(): Unit = {
  val ver = ops.StringOps(proc"git log -n 1 --pretty=format:%H".at(home).runCheck().out).substring(0, 10)
  val scalaVer = versions.get(DependencyManager.scalaKey).get
  val sc = Os.tempFix("", ".sc")
  sc.writeOver(
    st"""import org.sireum._
        |Coursier.setScalaVersion("$scalaVer")
        |for (cif <- Coursier.fetch(ISZ(s"org.sireum.kekinian::cli:$ver"))) {
        |  println(cif.path)
        |}""".render
  )
  sc.removeOnExit()
  proc"$sireum slang run $sc".console.run()
}

def ghpack(): Unit = {
  val repository = m2()
  for (p <- Os.Path.walk(repository, F, F, (f: Os.Path) => ops.StringOps(f.name).endsWith("pom"))) {
    val d = p.up.canon
    val pom = d / "pom.xml"
    p.copyOverTo(pom)
    val pops = ops.StringOps(pom.read)
    val i = pops.stringIndexOf("</project>")
    pom.writeOver(pops.substring(0, i))
    pom.writeAppend(
      st"""    <distributionManagement>
          |        <repository>
          |            <id>github</id>
          |            <name>GitHub Sireum Apache Maven Packages</name>
          |            <url>https://maven.pkg.github.com/sireum/kekinian</url>
          |        </repository>
          |    </distributionManagement>
          |""".render
    )
    pom.writeAppend(pops.substring(i, pops.size))
    Os.proc(ISZ("mvn", "--settings", (home / "distro" / "mvn-settings.xml").string, "deploy")).at(d).console.runCheck()
  }
}

def setup(isUltimate: B): Unit = {
  println("Setup ...")
  build(F, F)
  proc"${homeBin / "distro.cmd"}${if (isUltimate) " --ultimate" else ""}".at(home).console.runCheck()
  project(T, isUltimate)
  Os.kind match {
    case Os.Kind.Win =>
      println(s"Sireum-dev IVE can now be launched by running ${homeBin / "win" / "idea" / "bin" / "IVE.exe"}")
      println(s"Java Development Kit (JDK) is available at ${homeBin / "win" / "java"}")
      println(s"Scala is available at ${homeBin / "scala"}")
    case Os.Kind.Linux =>
      println(s"Sireum-dev IVE can now be launched by running ${homeBin / "linux" / "idea" / "bin" / "IVE.sh"}")
      println(s"Java Development Kit (JDK) is available at ${homeBin / "linux" / "java"}")
      println(s"Scala is available at ${homeBin / "scala"}")
    case Os.Kind.LinuxArm =>
      println(s"Sireum-dev IVE can now be launched by running ${homeBin / "linux" / "arm" / "idea" / "bin" / "IVE.sh"}")
      println(s"Java Development Kit (JDK) is available at ${homeBin / "linux" / "arm" / "java"}")
      println(s"Scala is available at ${homeBin / "scala"}")
    case Os.Kind.Mac =>
      println(s"Sireum-dev IVE can now be launched by running ${homeBin / "mac" / "idea" / "IVE.app"}")
      println(s"Java Development Kit (JDK) is available at ${homeBin / "mac" / "java"}")
      println(s"Scala is available at ${homeBin / "scala"}")
    case _ =>
  }
}

def project(skipBuild: B, isUltimate: B): Unit = {
  if (!skipBuild) {
    build(F, F)
  }
  println("Generating IVE project ...")
  proc"$sireum proyek ive --force${if (isUltimate) " --ultimate" else ""} .".at(home).console.runCheck()
}

def ram(): Unit = {
  def mac(): Unit = {
    val ramdisk = Os.path("/Volumes") / "RAM"
    val ramdiskHome = ramdisk / home.name
    if (!(homeBin / "mac" / "idea" / "IVE.app").exists) {
      setup(F)
    }
    if (ramdisk.exists) {
      proc"launchctl remove org.sireum.ram.rsync".echo.console.run()
      proc"rsync -a --exclude .idea --exclude project.json --exclude out --exclude '*.cmd.com' $ramdiskHome ${home.up.canon}".echo.console.runCheck()
      proc"hdiutil eject $ramdisk".echo.console.runCheck()
    } else {
      val disk = ops.StringOps(proc"hdiutil attach -nomount ram://${8 * 1024 * 2048}".echo.console.runCheck().out).trim
      proc"diskutil erasevolume HFS+ ${ramdisk.name} $disk".echo.console.runCheck()
      proc"rsync -a --exclude .idea --exclude project.json --exclude out --exclude '*.cmd.com' $home $ramdisk".echo.console.runCheck()
      val plist = ramdisk / "rsync.plist"
      plist.writeOver(
        st"""<?xml version="1.0" encoding="UTF-8"?>
            |<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
            |<plist version="1.0">
            |<dict>
            |  <key>Label</key>
            |  <string>org.sireum.ram.rsync</string>
            |  <key>ProgramArguments</key>
            |  <array>
            |    <string>/usr/bin/rsync</string>
            |    <string>-av</string>
            |    <string>--exclude</string>
            |    <string>project.json</string>
            |    <string>--exclude</string>
            |    <string>.idea</string>
            |    <string>--exclude</string>
            |    <string>out</string>
            |    <string>--exclude</string>
            |    <string>*.cmd.com</string>
            |    <string>$ramdiskHome</string>
            |    <string>${home.up.canon}</string>
            |  </array>
            |  <key>Nice</key>
            |  <integer>1</integer>
            |  <key>StartInterval</key>
            |  <integer>150</integer>
            |  <key>RunAtLoad</key>
            |  <true/>
            |  <key>StandardErrorPath</key>
            |  <string>${plist.up.canon / s"${plist.name}.err.txt"}</string>
            |  <key>StandardOutPath</key>
            |  <string>${plist.up.canon / s"${plist.name}.out.txt"}</string>
            |</dict>
            |</plist>
            | """.render)
      println(s"""Wrote $plist""")
      proc"launchctl load $plist".echo.console.runCheck()
      println()
      val ive = ramdiskHome / "bin" / "mac" / "idea" / "IVE.app"
      proc"${ramdiskHome / "bin" / "build.cmd"} project".echo.console.runCheck()
      proc"open --env SIREUM_HOME=$ramdiskHome $ive".echo.console.runCheck()
    }
  }

  Os.kind match {
    case Os.Kind.Mac => mac()
    case _ =>
      eprintln("This utility is only available in macOS")
      Os.exit(-1)
  }
}

if (!(home / "runtime" / "build.sc").exists) {
  eprintln("Some sub-modules are not present; please clone recursively or run:")
  eprintln("git submodule update --init --recursive --remote")
  Os.exit(-1)
}

installZ3(Os.kind)
installCVC4(Os.kind)

if (Os.cliArgs.isEmpty) {
  build(F, F)
} else {
  for (i <- 0 until Os.cliArgs.size) {
    Os.cliArgs(i) match {
      case string"fresh" => build(T, F)
      case string"native" => build(F, T)
      case string"setup" => setup(F)
      case string"setup-ultimate" => setup(T)
      case string"project" => project(F, F)
      case string"project-ultimate" => project(F, T)
      case string"tipe" => tipe()
      case string"compile" => compile(F)
      case string"compile-js" => compile(T)
      case string"test" => test()
      case string"mill" => buildMill()
      case string"regen-slang" => regenSlang()
      case string"regen-logika" => regenLogika()
      case string"regen-project" => regenProject()
      case string"regen-cliopt" => regenCliOpt()
      case string"regen-air" => regenAir()
      case string"regen-act" => regenAct()
      case string"regen-server" => regenServer()
      case string"regen-cli" => regenCli()
      case string"regen-fmide-cli" => regenFmideCli()
      case string"m2" => m2()
      case string"m2-lib" => m2Lib(F)
      case string"m2-lib-js" => m2Lib(T)
      case string"jitpack" => jitpack()
      case string"ghpack" => ghpack()
      case string"ram" => ram()
      case string"cvc4" => cvc4()
      case string"z3" => z3()
      case string"-h" => usage()
      case string"--help" => usage()
      case cmd =>
        usage()
        eprintln(s"Unrecognized command: $cmd")
        Os.exit(-1)
    }
  }
}
