::#! 2> /dev/null                                                                                           #
@ 2>/dev/null # 2>nul & echo off & goto BOF                                                                 #
export SIREUM_HOME=$(cd -P $(dirname "$0")/.. && pwd -P)                                                    #
if [ ! -z ${SIREUM_PROVIDED_SCALA++} ]; then                                                                #
  SIREUM_PROVIDED_JAVA=true                                                                                 #
fi                                                                                                          #
"${SIREUM_HOME}/bin/init.sh"                                                                                #
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
    if [[ "$$(uname -m)" == "aarch64" ]]; then                                                              #
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
  exec "${SIREUM_HOME}/bin/sireum" slang run -s -n "$0" "$@"                                                #
fi                                                                                                          #
:BOF
setlocal
set SIREUM_HOME=%~dp0../
call "%~dp0init.bat"
if defined SIREUM_PROVIDED_SCALA set SIREUM_PROVIDED_JAVA=true
if not defined SIREUM_PROVIDED_JAVA set PATH=%~dp0win\java\bin;%~dp0win\z3\bin;%PATH%
set NEWER=False
if exist %~dpnx0.com for /f %%i in ('powershell -noprofile -executionpolicy bypass -command "(Get-Item %~dpnx0.com).LastWriteTime -gt (Get-Item %~dpnx0).LastWriteTime"') do @set NEWER=%%i
if "%NEWER%" == "True" goto native
del "%~dpnx0.com" > nul 2>&1
"%~dp0sireum.bat" slang run -s -n "%0" %*
exit /B %errorlevel%
:native
%~dpnx0.com %*
exit /B %errorlevel%
::!#
// #Sireum
import org.sireum._

def usage(): Unit = {
  println(
    st"""Sireum /build
        |Usage: ( setup        | project      | fresh        | native
        |       | tipe         | compile      | test         | test-js
        |       | touche       | touche-lib   | touche-slang | touche-transpilers
        |       | regen-slang  | regen-logika | regen-air    | regen-act
        |       | regen-server | regen-cliopt | regen-cli
        |       | m2           | jitpack      | ghpack                            )*
      """.render)
}


val homeBin: Os.Path = Os.slashDir
val home = homeBin.up
val sireumJar = homeBin / "sireum.jar"
val sireum = homeBin / (if (Os.isWin) "sireum.bat" else "sireum")
val mill = homeBin / (if (Os.isWin) "mill.bat" else "mill")
val mainFile = home / "cli" / "jvm" / "src" / "main" / "scala" / "org" / "sireum" / "Sireum.scala"
val libFiles = home / "runtime" / "library" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "Library_Ext.scala"
val slangFiles = home / "slang" / "frontend" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "lang" / "$SlangFiles.scala"
val transpilersFiles = home / "transpilers" / "c" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "transpilers" / "c" / "Runtime_Ext.scala"
val artFiles = home / "hamr" / "codegen" / "arsit" / "jvm" / "src" / "main" / "scala" / "org" / "sireum" / "hamr" / "arsit" / "ArsitLibrary_Ext.scala"
var didTipe = F
var didCompile = F
var didM2 = F
var didBuild = F
val platform: String = Os.kind match {
  case Os.Kind.Win => "win"
  case Os.Kind.Linux => "linux"
  case Os.Kind.LinuxArm => "linux/arm"
  case Os.Kind.Mac => "mac"
  case _ => "unsupported"
}


def touchePath(path: Os.Path): Unit = {
  val content = path.read
  val lineSep: String = if (Os.isWin) "\r\n" else "\n"
  val sops = ops.StringOps(content)
  val newContent: String =
    if (sops.endsWith(lineSep)) ops.StringOps(content).trim
    else s"$content$lineSep"
  path.writeOver(newContent)
}


def touche(): Unit = {
  touchePath(libFiles)
  touchePath(slangFiles)
  touchePath(transpilersFiles)
  touchePath(mainFile)
  touchePath(artFiles)
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
  symlink(millBuild / "versions.properties", home / "versions.properties")
  val millBuildBin = millBuild / "bin"
  symlink(millBuildBin / "sireum.jar", homeBin / "sireum.jar")
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
  (millBuildBin / "build.cmd").slash(ISZ())
  copyIfNewer(millBuild / "mill-standalone", homeBin / "mill")
  copyIfNewer(millBuild / "mill-standalone.bat", homeBin / "mill.bat")
}


def build(fresh: B): Unit = {
  if (!didBuild) {
    didBuild = T
    println("Building ...")
    def buildStamp: String = {
      val rStatus = Os.proc(ISZ("git", "status", "--porcelain")).at(home).run()
      if (rStatus.exitCode != 0) {
        return "n/a"
      }
      val r = ops.StringOps(Os.proc(ISZ("git", "log", "-n", "1", "--date=format:%Y%m%d", "--pretty=format:%cd.%h")).at(home).runCheck().out).trim
      return if (ops.StringOps(rStatus.out).trim == "") r else s"$r*"
    }
    val cli = home / "cli" / "jvm" / "src" / "main" / "scala" / "org" / "sireum" / "Cli.scala"
    val oldCli = cli.read
    cli.writeOver(ops.StringOps(oldCli).replaceAllLiterally("yyyymmdd.sha", buildStamp))
    if (fresh) {
      (home / "out").removeAll()
      (Os.home / ".mill").removeAll()
    } else {
      touche()
    }
    val r = Os.proc(ISZ(mill.string, "build")).at(home).console.run()
    if (!fresh) {
      touche()
    }
    cli.writeOver(oldCli)
    if (r.exitCode != 0) {
      Os.exit(r.exitCode)
    }
    println()
  }
}


def nativ(): Unit = {
  val platDir = homeBin / platform
  val nativeImage: Os.Path =
    platDir / "graal" / "bin" / (if (Os.isWin) "native-image.cmd" else "native-image")
  (homeBin / "install" / "graal.cmd").call(ISZ()).console.runCheck()
  val flags: ISZ[String] = Os.kind match {
    case Os.Kind.Mac => ISZ("--no-server")
    case Os.Kind.Linux => ISZ("--static", "--no-server")
    case Os.Kind.Win => ISZ("--static")
    case _ => halt("Unsupported operating system")
  }
  build(F)
  println("Building native ...")
  val r = Os.proc((nativeImage.string +: flags) ++ ISZ[String]("--initialize-at-build-time", "--no-fallback",
      "-jar", sireumJar.string, (platDir / "sireum").string)).console.bufferErr.run()
  if (r.exitCode != 0) {
    for (line <- ops.StringOps(r.err).split((c: C) => c === '\n') if !ops.StringOps(line).startsWith("warning: unknown anonymous info")) {
      eprintln(line)
    }
  }
  (platDir / "sireum.o").removeAll()
  for (f <- platDir.list if ops.StringOps(f.name).startsWith("sireum.") && !ops.StringOps(f.name).endsWith(".exe")) {
    f.removeAll()
  }
  if (r.exitCode != 0) {
    Os.exit(r.exitCode)
  }
  println()
}


def tipe(): Unit = {
  if (!didTipe) {
    didTipe = T
    println("Slang type checking ...")
    val excludes = "hamr/codegen/arsit/resources,hamr/codegen/jvm/src/test/result"
    val excludedDirs = Set ++ ISZ[String]("bin", "out", "distro", "resources")
    val sourcepath: ISZ[Os.Path] = for (p <- home.list if !excludedDirs.contains(p.name)) yield p
    Os.proc(ISZ("java", "-jar", sireumJar.string,
      "slang", "tipe", "--verbose", "-r", "-s", st"${(sourcepath, Os.pathSep)}".render, "-x", excludes)).at(home).console.runCheck()
    println()
  }
}


def compile(): Unit = {
  if (!didCompile) {
    didCompile = T
    if (didM2) {
      didM2 = F
      (home / "out").removeAll()
    }
    tipe()
    println("Compiling ...")
    Os.proc(ISZ(mill.string, "all", "cli.tests.compile", "alir.js.tests.compile")).at(home).console.runCheck()
    println()
  }
}


def test(): Unit = {
  compile()
  println("Running shared tests ...")
  Os.proc(ISZ(mill.string, "all",
    "runtime.library.shared.tests",
    "slang.parser.shared.tests",
    "slang.frontend.shared.tests",
    "alir.shared.tests")).at(home).console.runCheck()
  println()

  println("Running jvm tests ...")
  Os.proc(ISZ(mill.string, "all",
    "runtime.library.jvm.tests",
    "tools.jvm.tests",
    "logika.jvm.tests")).at(home).console.runCheck()
  println()
}


def testJs(): Unit = {
  compile()
  Os.proc(ISZ(mill.string, "all",
    "runtime.library.js.tests",
    "slang.parser.js.tests",
    "slang.frontend.js.tests",
    "alir.js.tests")).at(home).env(ISZ("NODEJS_MAX_HEAP" ~> "4096")).console.runCheck()
}


def regenCliOpt(): Unit = {
  if (sireumJar.exists) {
    val cliPackagePath = home / "runtime" / "library" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "cli"
    Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "sergen", "-p", "org.sireum.cli", "-l",
      s"${home / "license.txt"}", "-m", "json", s"${cliPackagePath / "CliOpt.scala"}")).at(cliPackagePath).console.run()
  } else {
    println(s"Could not find Sireum assembly.")
  }
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
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "transgen", "-l", s"${home / "license.txt"}", "-m",
    "immutable", "-n", "StateTransformer", s"${logikaPackagePath / "State.scala"}")).at(logikaPackagePath).console.run()
}


def regenAir(): Unit = {
  val airPackagePath = home / "hamr" / "air" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "hamr" / "ir"
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "transgen", "-l", s"${home / "license.txt"}", "-m",
    "immutable,mutable", s"${airPackagePath / "AadlAST.scala"}")).at(airPackagePath).console.run()
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
    s"${home / "license.txt"}", "-m", "msgpack",
    s"${protocolPackagePath / "Message.scala"}",
    s"${logikaPackagePath / "State.scala"}",
    s"${logikaPackagePath / "Config.scala"}",
    s"${logikaPackagePath / "Smt2Query.scala"}",
    s"${astPackagePath / "Typed.scala"}",
  )).at(protocolPackagePath).console.run()
}

def regenCli(): Unit = {
  val sireumPackagePath = home / "cli" / "jvm" / "src" / "main" / "scala" / "org" / "sireum"
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "cligen", "-p", "org.sireum", "-l", s"${home / "license.txt"}",
    s"${sireumPackagePath / "cli.sc"}")).at(sireumPackagePath).console.run()
}


def m2(): Os.Path = {
  didM2 = T
  didCompile = F

  val repository = Os.home / ".m2" / "repository"
  (repository / "org" / "sireum").removeAll()

  var m2s: ISZ[ISZ[String]] = for (pkg <- ISZ("macros", "library", "test"); plat <- ISZ("shared", "jvm", "js")) yield
    ISZ("runtime", pkg, plat, "m2") // runtime
  m2s = m2s ++ (for (pkg <- ISZ("ast", "parser", "tipe", "frontend"); plat <- ISZ("shared", "jvm" /*, "js"*/))
    yield ISZ("slang", pkg, plat, "m2")) // slang
  m2s = m2s ++ (for (plat <- ISZ("shared", "jvm" /*, "js"*/)) yield ISZ("alir", plat, "m2")) // alir
  m2s = m2s ++ (for (pkg <- ISZ("common", "c"); plat <- ISZ("shared", "jvm" /*, "js"*/))
    yield ISZ("transpilers", pkg, plat, "m2")) // transpilers
  m2s = m2s ++ (for (plat <- ISZ("shared", "jvm" /*, "js"*/)) yield ISZ("logika", plat, "m2")) // logika
  m2s = m2s ++ (for (plat <- ISZ("shared", "jvm" /*, "js"*/)) yield ISZ("tools", plat, "m2"))
  m2s = m2s ++ (for (pkg <- ISZ("air"); plat <- ISZ("shared", "jvm", "js"))
    yield ISZ("hamr", pkg, plat, "m2")) // air
  m2s = m2s :+ ISZ("hamr", "phantom", "m2") // phantom
  m2s = m2s ++ (for (pkg <- ISZ("act", "arsit", "art"); plat <- ISZ("shared", "jvm"))
    yield ISZ("hamr", "codegen", pkg, plat, "m2")) // act, arsit, art
  m2s = m2s ++ (for (plat <- ISZ("shared", "jvm" /*, "js"*/)) yield ISZ("hamr", "codegen", plat, "m2"))
  m2s = m2s :+ ISZ("cli", "m2")

  println(s"Publishing local m2 ...")
  val m2Paths: ISZ[Os.Path] =
    for (cd <- for (m2 <- m2s) yield st"${(m2, Os.fileSep)}".render) yield home / "out" / cd

  for (m2p <- m2Paths) {
    m2p.removeAll()
  }

  Os.proc(ISZ[String](mill.string, "all") ++ (for (m2 <- m2s) yield st"${(m2, ".")}".render)).
    at(home).console.runCheck()
  println("Artifacts")
  for (m2p <- m2Paths; p <- (m2p / "dest").overlayMove(repository, F, F, _ => T, T).values) {
    println(s"* $p")
  }
  println()

  return repository / "org" / "sireum"
}


def jitpack(): Unit = {
  println("Triggering jitpack ...")
  val r = mill.call(ISZ("jitPack", "--owner", "sireum", "--repo", "kekinian", "--lib", "cli")).
    at(home).console.run()
  r match {
    case r: Os.Proc.Result.Normal =>
      println(r.out)
      println(r.err)
      if (!r.ok) {
        eprintln(s"Exit code: ${r.exitCode}")
      }
    case r: Os.Proc.Result.Exception =>
      eprintln(s"Exception: ${r.err}")
    case _: Os.Proc.Result.Timeout =>
      eprintln("Timeout")
      eprintln()
  }
  println()
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

def project(): Unit = {
  build(F)
  println("Generating IVE project ...")
  if (!Os.isWin) {
    (home / "build" / "bin" / "build.cmd").slash(ISZ("dev"))
  }
  Os.proc(ISZ(sireum.string, "tools", "ivegen", "-f", "-m", "mill", "-n", home.name, ".")).at(home.up).console.runCheck()
}


def setup(): Unit = {
  println("Setup ...")
  build(F)
  Os.proc(ISZ(mill.string, "IVE")).at(home).console.run()
  project()
  Os.kind match {
    case Os.Kind.Win =>
      println(s"Sireum-dev IVE can now be launched by running ${homeBin / "win" / "idea" / "bin" / "IVE.exe"}")
      println(s"Java Development Kit (JDK) is available at ${homeBin / "win" / "java"}")
      println(s"Scala is available at ${homeBin / "scala"}")
      println(s"Mill can be found at ${homeBin / "mill.bat"}")
    case Os.Kind.Linux =>
      println(s"Sireum-dev IVE can now be launched by running ${homeBin / "linux" / "idea" / "bin" / "IVE.sh"}")
      println(s"Java Development Kit (JDK) is available at ${homeBin / "linux" / "java"}")
      println(s"Scala is available at ${homeBin / "scala"}")
      println(s"Mill can be found at ${homeBin / "mill"}")
    case Os.Kind.Mac =>
      println(s"Sireum-dev IVE can now be launched by running ${homeBin / "mac" / "idea" / "IVE.app"}")
      println(s"Java Development Kit (JDK) is available at ${homeBin / "mac" / "java"}")
      println(s"Scala is available at ${homeBin / "scala"}")
      println(s"Mill can be found at ${homeBin / "mill"}")
    case _ =>
  }
}


if (!(home / "runtime" / "build.sc").exists) {
  eprintln("Some sub-modules are not present; please clone recursively or run:")
  eprintln("git submodule update --init --recursive --remote")
  Os.exit(-1)
}

buildMill()

if (Os.cliArgs.isEmpty) {
  build(F)
} else {
  for (i <- 0 until Os.cliArgs.size) {
    Os.cliArgs(i) match {
      case string"fresh" => build(T)
      case string"native" => nativ()
      case string"setup" => setup()
      case string"project" => project()
      case string"tipe" => tipe()
      case string"compile" => compile()
      case string"test" => test()
      case string"test-js" => testJs()
      case string"touche" => touche()
      case string"touche-lib" => touchePath(libFiles)
      case string"touche-slang" => touchePath(slangFiles)
      case string"touche-transpilers" => touchePath(transpilersFiles)
      case string"regen-cliopt" => regenCliOpt()
      case string"regen-slang" => regenSlang()
      case string"regen-logika" => regenLogika()
      case string"regen-air" => regenAir()
      case string"regen-act" => regenAct()
      case string"regen-server" => regenServer()
      case string"regen-cli" => regenCli()
      case string"m2" => m2()
      case string"jitpack" => jitpack()
      case string"ghpack" => ghpack()
      case string"-h" => usage()
      case string"--help" => usage()
      case cmd =>
        usage()
        eprintln(s"Unrecognized command: $cmd")
        Os.exit(-1)
    }
  }
}
