::#! 2> /dev/null                                   #
@ 2>/dev/null # 2>nul & echo off & goto BOF         #
SCRIPT_HOME=$(cd "$(dirname "$0")" && pwd)          #
if [ ! -f ${SCRIPT_HOME}/sireum.jar ]; then         #
  ${SCRIPT_HOME}/init.sh                            #
fi                                                  #
exec ${SCRIPT_HOME}/sireum slang run -s "$0" "$@"   #
:BOF
if not exist %~dp0sireum.jar call %~dp0init.bat
%~dp0sireum.bat slang run -s "%0" %*
exit /B %errorlevel%
::!#
// #Sireum
import org.sireum._


def usage(): Unit = {
  println(
    st"""Sireum /build
        |Usage: ( setup        | project      | bin        | native
        |       | tipe         | compile      | test       | test-js
        |       | touche       | touche-macro | touche-lib | touche-slang
        |       | regen-cliopt | regen-slang  | regen-cli  | m2           )*
      """.render)
}


if (Os.cliArgs.size < 1) {
  usage()
  Os.exit(0)
}


val homeBin = Os.path(Os.cliArgs(0))
val home = homeBin.up
val sireumJar = homeBin / "sireum.jar"
val sireum = homeBin / (if (Os.isWin) "sireum.bat" else "sireum")
val mill = homeBin / (if (Os.isWin) "mill.bat" else "mill")
val macroFiles = home / "runtime" / "macros" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "$internal" / "Macro.scala"
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
}


def buildMill(): Unit = {
  val millBuild = homeBin / "mill-build"
  val millBuildBin = millBuild / "bin"
  if (!(millBuildBin / "sireum.jar").exists) {
    (homeBin / "sireum.jar").copyOverTo(millBuildBin / "sireum.jar")
  }
  if (!(millBuildBin / sireum.name).exists) {
    sireum.copyOverTo(millBuildBin / sireum.name)
  }
  if (!(millBuild / "versions.properties").exists) {
    (home / "versions.properties").copyOverTo(millBuild / "versions.properties")
  }
  if (!(millBuildBin / "scala").exists && (homeBin / "scala").exists) {
    (homeBin / "scala").copyOverTo(millBuildBin / "scala")
  }
  if (!(millBuild / "lib").exists) {
    (home / "lib").copyOverTo(millBuild / "lib")
  }
  if (Os.kind != Os.Kind.Unsupported && !(millBuildBin / platform / "java").exists &&
    (homeBin / platform / "java").exists) {
    (homeBin / platform / "java").copyOverTo(millBuildBin / platform / "java")
  }
  Os.proc(ISZ((millBuildBin / "build.cmd").string)).console.runCheck()
  (millBuild / "mill-standalone").copyOverTo(homeBin / "mill")
  (millBuild / "mill-standalone.bat").copyOverTo(homeBin / "mill.bat")
}


def build(env: ISZ[(String, String)]): Unit = {
  if (!didBuild) {
    didBuild = T
    println("Building ...")
    touche()
    Os.proc(ISZ(mill.string, "build")).env(env).at(home).console.runCheck()
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
  build(ISZ("SIREUM_NATIVE" ~> "true"))
  println("Building native ...")
  val platDir = homeBin / platform
  Os.proc(("native-image" +: flags) ++
    ISZ[String]("--no-server", "-jar", sireumJar.string, (platDir / "sireum").string)).
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
    "tools.jvm.tests")).at(home).console.runCheck()
  println()
}


def testJs(): Unit = {
  compile()
  Os.proc(ISZ(mill.string, "all",
    "runtime.library.shared.tests",
    "slang.parser.shared.tests",
    "slang.frontend.shared.tests",
    "alir.shared.tests")).at(home).console.runCheck()
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
  build(ISZ())
  println("Generating IVE project ...")
  if (!Os.isWin) {
    Os.proc(ISZ((homeBin / "mill-build" / "bin" / "build.cmd").string, "dev")).console.runCheck()
  }
  Os.proc(ISZ(sireum.string, "tools", "ivegen", "-f", "-m", "mill", "-n", home.name, ".")).at(home.up).console.runCheck()
}


def setup(): Unit = {
  println("Setup ...")
  if (!(home / "runtime" / "build.sc").exists) {
    Os.proc(ISZ("git", "submodule", "update", "--init", "--recursive", "--remote")).at(home).runCheck()
    Os.proc(ISZ("git", "pull", "--recurse-submodules")).at(home).runCheck()
  }
  build(ISZ())
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


buildMill()

Os.cliArgs.size match {
  case z"1" => build(ISZ())
  case n =>
    for (i <- 1 until n) {
      Os.cliArgs(i) match {
        case string"bin" => build(ISZ())
        case string"native" => nativ()
        case string"setup" => setup()
        case string"project" => project()
        case string"tipe" => tipe()
        case string"compile" => compile()
        case string"test" => test()
        case string"test-js" => testJs()
        case string"touche" => touche()
        case string"touche-macro" => touchePath(macroFiles)
        case string"touche-lib" => touchePath(libFiles)
        case string"touche-slang" => touchePath(slangFiles)
        case string"regen-cliopt" => regenCliOpt()
        case string"regen-slang" => regenSlang()
        case string"regen-cli" => regenSlang()
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