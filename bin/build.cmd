::#! 2> /dev/null                                                                                           #
@ 2>/dev/null # 2>nul & echo off & goto BOF                                                                 #
export SIREUM_HOME=$(cd -P $(dirname "$0")/.. && pwd -P)                                                    #
if [ ! -z ${SIREUM_PROVIDED_SCALA++} ]; then                                                                #
  SIREUM_PROVIDED_JAVA=true                                                                                 #
fi                                                                                                          #
if [ ! -f "${SIREUM_HOME}/bin/sireum.jar" ]; then                                                           #
  "${SIREUM_HOME}/bin/init.sh"                                                                              #
elif [ "${SIREUM_HOME}/versions.properties" -nt "${SIREUM_HOME}/bin/sireum.jar" ]; then                     #
  "${SIREUM_HOME}/bin/init.sh"                                                                              #
fi                                                                                                          #
if [ -n "$COMSPEC" -a -x "$COMSPEC" ]; then                                                                 #
  PLATFORM="win"                                                                                            #
  export SIREUM_HOME=$(cygpath -C OEM -w -a ${SIREUM_HOME})                                                 #
  if [ -z ${SIREUM_PROVIDED_JAVA++} ]; then                                                                 #
    export JAVA_HOME="${SIREUM_HOME}\\bin\\win\\java"                                                       #
    export Z3_HOME="${SIREUM_HOME}\\bin\\win\\z3"                                                           #
    export PATH="${SIREUM_HOME}/bin/win/java":"${SIREUM_HOME}/bin/win/z3":$PATH                             #
    export PATH="$(cygpath -C OEM -w -a ${JAVA_HOME}/bin)":"$(cygpath -C OEM -w -a ${Z3_HOME}/bin)":$PATH   #
  fi                                                                                                        #
elif [ "$(uname)" = "Darwin" ]; then                                                                        #
  PLATFORM="mac"                                                                                            #
  if [ -z ${SIREUM_PROVIDED_JAVA++} ]; then                                                                 #
    export JAVA_HOME="${SIREUM_HOME}/bin/mac/java"                                                          #
    export Z3_HOME="${SIREUM_HOME}/bin/mac/z3"                                                              #
    export PATH="${JAVA_HOME}/bin":"${Z3_HOME}/bin":$PATH                                                   #
  fi                                                                                                        #
elif [ "$(expr substr $(uname -s) 1 5)" = "Linux" ]; then                                                   #
  PLATFORM="linux"                                                                                          #
  if [ -z ${SIREUM_PROVIDED_JAVA++} ]; then                                                                 #
    export JAVA_HOME="${SIREUM_HOME}/bin/linux/java"                                                        #
    export Z3_HOME="${SIREUM_HOME}/bin/linux/z3"                                                            #
    export PATH="${JAVA_HOME}/bin":"${Z3_HOME}/bin":$PATH                                                   #
  fi                                                                                                        #
fi                                                                                                          #
if [ -f "$0.com" ] && [ "$0.com" -nt "$0" ]; then                                                           #
  exec "$0.com" "$@"                                                                                        #
else                                                                                                        #
  rm -fR "$0.com"                                                                                           #
  exec "${SIREUM_HOME}/bin/sireum" slang run -s -n "$0" "$@"                                                #
fi                                                                                                          #
:BOF
if defined SIREUM_PROVIDED_SCALA set SIREUM_PROVIDED_JAVA=true
if not exist "%~dp0sireum.jar" call "%~dp0init.bat"
if not defined SIREUM_PROVIDED_JAVA set PATH=%~dp0win\java\bin;%~dp0win\z3\bin;%PATH%
"%~dp0sireum.bat" slang run -s "%0" %*
exit /B %errorlevel%
::!#
// #Sireum
import org.sireum._


def usage(): Unit = {
  println(
    st"""Sireum /build
        |Usage: ( setup        | project      | bin          | native
        |       | tipe         | compile      | test         | test-js
        |       | m2           | touche       | touche-lib   | touche-slang
        |       | regen-cliopt | regen-slang  | regen-logika | regen-cli    )*
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
var didTipe = F
var didCompile = F
var didM2 = F
var didBuild = F
val platform: String = Os.kind match {
  case Os.Kind.Win => "win"
  case Os.Kind.Linux => "linux"
  case Os.Kind.Mac => "mac"
  case _ => "unsupported"
}


def touchePath(path: Os.Path): Unit = {
  val content = path.read
  val lineSep: String = if (Os.isWin) "\r\n" else "\n"
  val sops = ops.StringOps(content)
  if (sops.endsWith(lineSep)) {
    path.writeOver(ops.StringOps(content).trim)
  } else {
    path.writeOver(s"$content$lineSep")
  }
}


def touche(): Unit = {
  touchePath(libFiles)
  touchePath(slangFiles)
  touchePath(mainFile)
}


def buildMill(): Unit = {
  val millBuild = homeBin / "mill-build"
  val millBuildBin = millBuild / "bin"
  if (!(millBuildBin / "sireum.jar").exists ||
    (homeBin / "sireum.jar").lastModified > (millBuildBin / "sireum.jar").lastModified) {
    (homeBin / "sireum.jar").copyOverTo(millBuildBin / "sireum.jar")
  }
  if (!(millBuildBin / sireum.name).exists ||
    sireum.lastModified > (millBuildBin / sireum.name).lastModified) {
    sireum.copyOverTo(millBuildBin / sireum.name)
  }
  if (!(millBuild / "versions.properties").exists ||
    (home / "versions.properties").lastModified > (millBuild / "versions.properties").lastModified) {
    (home / "versions.properties").copyOverTo(millBuild / "versions.properties")
  }
  if (!(millBuildBin / "scala").exists && (homeBin / "scala").exists) {
    (homeBin / "scala").copyOverTo(millBuildBin / "scala")
  }
  if (!(millBuild / "lib").exists) {
    (home / "lib").copyOverTo(millBuild / "lib")
  }
  if (!(millBuildBin / platform / "java").exists && (homeBin / platform / "java").exists) {
    (homeBin / platform / "java").copyOverTo(millBuildBin / platform / "java")
  }
  if (!(millBuildBin / "prelude.sh").exists ||
    (homeBin / "init.sh").lastModified > (millBuildBin / "prelude.sh").lastModified) {
    (homeBin / "init.sh").copyOverTo(millBuildBin / "prelude.sh")
    (millBuildBin / "prelude.sh").chmod("+x")
  }
  if (!(millBuildBin / "prelude.ps1").exists ||
    (homeBin / "init.ps1").lastModified > (millBuildBin / "prelude.ps1").lastModified) {
    (homeBin / "init.ps1").copyOverTo(millBuildBin / "prelude.ps1")
  }
  (millBuildBin / "build.cmd").slash(ISZ())
  (millBuild / "mill-standalone").copyOverTo(homeBin / "mill")
  (millBuild / "mill-standalone.bat").copyOverTo(homeBin / "mill.bat")
}


def build(): Unit = {
  if (!didBuild) {
    didBuild = T
    println("Building ...")
    touche()
    Os.proc(ISZ(mill.string, "build")).at(home).console.runCheck()
    touche()
    println()
  }
}


def nativ(): Unit = {
  val ni = Os.proc(ISZ("native-image", "--help")).run()
  if (!ops.StringOps(ni.out).contains("GraalVM")) {
    eprintln("Could not find GraalVM's native-image")
    Os.exit(-1)
  }
  val flags: ISZ[String] = if (Os.isLinux) ISZ("--static") else ISZ()
  build()
  println("Building native ...")
  val platDir = homeBin / platform
  Os.proc(("native-image" +: flags) ++
    ISZ[String]("--no-server", "--initialize-at-build-time", "--no-fallback",
      "-jar", sireumJar.string, (platDir / "sireum").string)).
    console.runCheck()
  (platDir / "sireum.o").removeAll()
}


def tipe(): Unit = {
  if (!didTipe) {
    didTipe = T
    println("Slang type checking ...")
    Os.proc(ISZ("java", "-jar", sireumJar.string,
      "slang", "tipe", "--verbose", "-r", "-s", home.string)).at(home).console.runCheck()
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
    "runtime.library.jvm.tests.test",
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
    "immutable,mutable", s"${astPackagePath / "AST.scala"}")).at(astPackagePath).console.run()
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "sergen", "-p", "org.sireum.lang.tipe", "-l",
    s"${home / "license.txt"}", "-m", "json,msgpack", s"${slangPackagePath / "symbol" / "Info.scala"}",
    s"${astPackagePath / "AST.scala"}")).at(slangPackagePath / "tipe").console.run()
}


def regenLogika(): Unit = {
  val logikaPackagePath = home / "logika" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "logika"
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "transgen", "-l", s"${home / "license.txt"}", "-m",
    "immutable", "-n", "StateTransformer", s"${logikaPackagePath / "State.scala"}")).at(logikaPackagePath).console.run()
}


def regenCli(): Unit = {
  val sireumPackagePath = home / "cli" / "jvm" / "src" / "main" / "scala" / "org" / "sireum"
  Os.proc(ISZ("java", "-jar", sireumJar.string, "tools", "cligen", "-p", "org.sireum", "-l", s"${home / "license.txt"}",
    s"${sireumPackagePath / "cli.sc"}")).at(sireumPackagePath).console.run()
}


def jitpack(): Unit = {
  println("Triggering jitpack ...")
  val r = Os.proc(ISZ(mill.string, "jitPack", "--owner", "sireum", "--repo", "kekinian", "--lib", "cli")).
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


def m2(): Unit = {
  didM2 = T
  didCompile = F

  val repository = Os.home / ".m2" / "repository"
  repository.removeAll()
  (home / "out").removeAll()

  def sub(name: String, m2s: ISZ[ISZ[String]]): Unit = {
    println(s"Publishing $name...")
    val m2Paths: ISZ[Os.Path] =
      for (cd <- for (m2 <- m2s) yield st"${(m2, Os.fileSep)}".render) yield home / "out" / cd

    for (m2p <- m2Paths) {
      m2p.removeAll()
    }

    Os.proc(ISZ[String](mill.string, "all") ++ (for (m2 <- m2s) yield st"${(m2, ".")}".render)).
      at(home).env(ISZ("SIREUM_SOURCE_BUILD" ~> "false")).console.runCheck()
    println("Artifacts")
    for (m2p <- m2Paths; p <- (m2p / "dest").overlayMove(repository, F, F, _ => T, T).values) {
      println(s"* $p")
    }
    println()
  }

  sub("runtime", for (pkg <- ISZ("macros", "library", "test"); plat <- ISZ("shared", "jvm", "js"))
    yield ISZ("runtime", pkg, plat, "m2"))
  sub("slang", for (pkg <- ISZ("ast", "parser", "tipe", "frontend"); plat <- ISZ("shared", "js"))
    yield ISZ("slang", pkg, plat, "m2"))
  sub("alir", for (plat <- ISZ("shared", "js")) yield ISZ("alir", plat, "m2"))
  sub("tools", for (plat <- ISZ("shared", "jvm")) yield ISZ("tools", plat, "m2"))
}


def project(): Unit = {
  build()
  println("Generating IVE project ...")
  if (!Os.isWin) {
    (homeBin / "mill-build" / "bin" / "build.cmd").slash(ISZ("dev"))
  }
  Os.proc(ISZ(sireum.string, "tools", "ivegen", "-f", "-m", "mill", "-n", home.name, ".")).at(home.up).console.runCheck()
}


def setup(): Unit = {
  println("Setup ...")
  build()
  Os.proc(ISZ(mill.string, "IVE")).at(home).console.run()
  project()
  Os.kind match {
    case Os.Kind.Win =>
      println(
        st"""Sireum-dev IVE can now be launched by running ${homeBin / "win" / "idea" / "bin" / "IVE.exe"}
            |Java Development Kit (JDK) is available at ${homeBin / "win" / "java"}
            |Scala is available at ${homeBin / "scala"}
            |Mill can be found at ${homeBin / "mill.bat"}""".render)
    case Os.Kind.Linux =>
      println(
        st"""Sireum-dev IVE can now be launched by running ${homeBin / "linux" / "idea" / "bin" / "IVE.sh"}
            |Java Development Kit (JDK) is available at ${homeBin / "linux" / "java"}
            |Scala is available at ${homeBin / "scala"}
            |Mill can be found at ${homeBin / "mill"}""".render)
    case Os.Kind.Mac =>
      println(
        st"""Sireum-dev IVE can now be launched by running ${homeBin / "mac" / "idea" / "IVE.app"}
            |Java Development Kit (JDK) is available at ${homeBin / "mac" / "java"}
            |Scala is available at ${homeBin / "scala"}
            |Mill can be found at ${homeBin / "mill"}""".render)
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
  build()
} else {
  for (i <- 0 until Os.cliArgs.size) {
    Os.cliArgs(i) match {
      case string"bin" => build()
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
      case string"regen-cliopt" => regenCliOpt()
      case string"regen-slang" => regenSlang()
      case string"regen-logika" => regenLogika()
      case string"regen-cli" => regenCli()
      case string"m2" => m2()
      case string"-h" => usage()
      case string"--help" => usage()
      //case string"jitpack" => jitpack()
      case cmd =>
        usage()
        eprintln(s"Unrecognized command: $cmd")
        Os.exit(-1)
    }
  }
}
