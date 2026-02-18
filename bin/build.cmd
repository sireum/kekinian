::/*#! 2> /dev/null                                            #
@ 2>/dev/null # 2>nul & echo off & goto BOF                    #
export SIREUM_HOME=$(cd -P "$(dirname "$0")/.." && pwd -P)     #
if [ -z "${SIREUM_NO_INIT+set}" ]; then                        #
  "${SIREUM_HOME}/bin/init.sh" || exit $?                      #
fi                                                             #
exec "${SIREUM_HOME}/bin/sireum" slang run "$0" "$@"           #
:BOF
setlocal
set SIREUM_HOME=%~dp0..
IF [%$SIREUM_NO_INIT%] == [] (call "%~dp0init.bat" || exit /B %errorlevel%)
call "%SIREUM_HOME%\bin\sireum.bat" slang run %0 %*
exit /B %errorlevel%
::!#*/
// #Sireum
import org.sireum._
import org.sireum.project.DependencyManager

def usage(): Unit = {
  println(
    st"""Sireum /build
        |Usage: ( setup            | project
        |       | jar              | fresh               | uber
        |       | tipe             | compile[-js]        | native[-script]
        |       | test             | verify              | test-verify
        |       | regen-project    | regen-presentasi    | regen-slang
        |       | regen-logika     | regen-air           | regen-act
        |       | regen-slang-ll2  | regen-parser        | regen-parser-antlr3
        |       | regen-server     | regen-cliopt        | regen-cli
        |       | regen-fmide-cli  | regen-vscodium-cli  | regen-json
        |       | regen-slang-tt   | regen-reflect       | regen-anvil
        |       | regen-dap        | forms
        |       | cvc              | z3                  | m2[-lib[-js] | -scalac]
        |       | mill             | jitpack             | ghpack            | ram
        |       | distro ( --linux | --linux-arm         | --mac             | --win
        |                | --pack  | --ultimate          | --server          | --vscodium  )*  )*
        |""".render)
}

val proyekName: String = "sireum-proyek"
val jarName: String = "sireum"

val homeBin: Os.Path = Os.slashDir
val home: Os.Path = homeBin.up.canon

val versions: Map[String, String] = (home / "versions.properties").properties

val sireumJar = homeBin / s"$jarName.jar"
val sireum = homeBin / (if (Os.isWin) "sireum.bat" else "sireum")

var proxyOpt: String = ""

Os.env("PROXY_HOST") match {
  case Some(v) => proxyOpt = s" --proxy-host $v$proxyOpt"
  case _ =>
}
Os.env("PROXY_NON_HOSTS") match {
  case Some(v) => proxyOpt = s" --proxy-non-hosts $v$proxyOpt"
  case _ =>
}
Os.env("PROXY_PORT") match {
  case Some(v) => proxyOpt = s" --proxy-port $v$proxyOpt"
  case _ =>
}
Os.env("PROXY_PROTOCOL") match {
  case Some(v) => proxyOpt = s" --proxy-protocol $v$proxyOpt"
  case _ =>
}
Os.env("PROXY_USER") match {
  case Some(_) => proxyOpt = s" --proxy-user-env PROXY_USER$proxyOpt"
  case _ =>
}
Os.env("PROXY_PASSWD") match {
  case Some(_) => proxyOpt = s" --proxy-passwd-env PROXY_PASSWD$proxyOpt"
  case _ =>
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
  Init(home, kind, versions).installZ3()
}


def z3(): Unit = {
  println("Installing Z3 for macOS ...")
  println()
  installZ3(Os.Kind.Mac)
  println()

  println("Installing Z3 for Linux ...")
  println()
  installZ3(Os.Kind.Linux)
  println()

  println("Installing Z3 for Windows ...")
  println()
  installZ3(Os.Kind.Win)
  println()
}


def installCVC(kind: Os.Kind.Type): Unit = {
  Init(home, kind, versions).installCVC()
}


def cvc(): Unit = {
  println("Installing CVC for macOS ...")
  println()
  installCVC(Os.Kind.Mac)
  println()

  println("Installing CVC for Linux ...")
  println()
  installCVC(Os.Kind.Linux)
  println()

  println("Installing CVC for Windows ...")
  println()
  installCVC(Os.Kind.Win)
  println()
}


def build(fresh: B, isNative: B, isUber: B, isNativeScript: B): Unit = {
  println("Building ...")

  val recompile: String = if (fresh) {
    " -f"
  } else {
    var r: String = ""
    val pr = proc"git status --porcelain".at(home).run()
    if (ops.StringOps(s"${pr.out}${pr.err}").trim == "") {
      val vOutOps = ops.StringOps(proc"$sireum -v".run().out)
      if (vOutOps.contains("*") ||
        !vOutOps.contains(ops.StringOps(proc"git log -n 1 --date=format:%Y%m%d --pretty=format:4.%cd.%h".run().out).trim)) {
        r = " --recompile cli"
      }
    }
    r
  }
  val nativ: String = if (isNativeScript) " --native-script" else if (isNative) " --native" else ""
  val uber: String = if (isUber) " --uber" else ""

  val includeSources: String = if (Os.env("INCLUDE_SOURCES").nonEmpty) "--include-sources " else ""
  val r = Sireum.proc(proc"$sireum proyek assemble$proxyOpt -n $proyekName -j $jarName -m org.sireum.Sireum --par --sha3 --ignore-runtime ${includeSources}--include-tests$recompile$nativ$uber $home".console,
    message.Reporter.create)
  if (r.exitCode == 0) {
    if (Os.isWin) {
      (homeBin / platform / "sireum.exe").removeAll()
      (homeBin / platform / ".sireum.exe").removeOnExit()
    } else {
      (homeBin / platform / "sireum").removeAll()
    }
    val proyekSireumJar = home / "out" / proyekName / "assemble" / sireumJar.name
    proyekSireumJar.copyOverTo(sireumJar)
    println(s"Copied $proyekSireumJar to $sireumJar")
    if (isUber) {
      val uberJar = homeBin / s"${sireumJar.name}.bat"
      val proyekUberJar = home / "out" / proyekName / "assemble" / uberJar.name
      proyekUberJar.copyOverTo(uberJar)
      println(s"Copied $proyekUberJar to $uberJar")
    }
    if (isNative) {
      val exePath: Os.Path = Os.kind match {
        case Os.Kind.Win => homeBin / "win" / s"$jarName.exe"
        case Os.Kind.Linux => homeBin / "linux" / jarName
        case Os.Kind.LinuxArm => homeBin / "linux" / "arm" / jarName
        case Os.Kind.Mac => homeBin / "mac" / jarName
        case _ => halt("Infeasible")
      }
      if (!isNativeScript) {
        val proyekExe = home / "out" / proyekName / "assemble" / exePath.name
        proyekExe.copyOverTo(exePath)
        println(s"Copied $proyekExe to $exePath")
      }
    }
  } else {
    Os.exit(r.exitCode)
  }
  println()
}


def tipe(): Unit = {
  println("Slang type checking ...")
  Os.proc(ISZ(sireum.string, "proyek", "tipe", "--par", "--strict-aliasing", home.string)).console.runCheck()
  println()
}


def compile(isJs: B): Unit = {
  tipe()
  println("Compiling ...")
  proc"$sireum proyek compile$proxyOpt -n $proyekName --par --sha3 --ignore-runtime${if (isJs) " --js" else ""} $home".console.runCheck()
  println()
}


def test(): Unit = {
  tipe()

  println("Testing ...")
  val packageNames = ISZ[String](
    "org.sireum"
  )
  val names = ISZ[String](
    "org.sireum.cli",
    "org.sireum.lang",
    "org.sireum.tools",
    "org.sireum.logika",
    "org.sireum.proyek",
    "org.sireum.hamr.codegen.test.expensive",
    "org.sireum.hamr.sysml"
  )
  proc"$sireum proyek test$proxyOpt --java -Xss2M -n $proyekName --par --sha3 --ignore-runtime --packages ${st"${(packageNames, ",")}".render} $home ${st"${(names, " ")}".render}".
    console.echo.runCheck()
  println()
}

def verifyLogikaExamples(): Unit = {
  println("Verifying https://github.com/sireum/logika-examples ...")
  val logikaExamples = home / "logika-examples"
  logikaExamples.removeAll()
  proc"git clone https://github.com/sireum/logika-examples".console.echo.at(home).runCheck()
  proc"${logikaExamples / "bin" / "verify.cmd"}".console.echo.at(home).env(ISZ("SIREUM_HOME" ~> home.string)).runCheck()

  println()
}

def verifyRuntime(): Unit = {
  proc"$sireum proyek logika$proxyOpt --all --par --par-branch --slice library-shared --timeout 5 --sat $home".console.echo.runCheck()
  println()
}


def verify(): Unit = {
  verifyLogikaExamples()
  if (!Os.isWinArm) {
    verifyRuntime()
  }
}


def regenProject(): Unit = {
  val projectPackagePath = home / "runtime" / "library" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "project"
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "sergen", "-p", "org.sireum.project", "-l", s"${home / "license.txt"}",
    "-m", "json", "-o", projectPackagePath.string, s"${projectPackagePath / "Project.scala"}")).console,
    message.Reporter.create)
}


def regenPresentasi(): Unit = {
  val presentasiPackagePath = home / "runtime" / "library" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "presentasi"
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "sergen", "-p", "org.sireum.presentasi", "-l",
    s"${home / "license.txt"}", "-m", "json", "-o", presentasiPackagePath.string,
    s"${presentasiPackagePath / "Presentation.scala"}")).console, message.Reporter.create)
}


def regenCliOpt(): Unit = {
  val cliPackagePath = home / "runtime" / "library" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "cli"
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "sergen", "-p", "org.sireum.cli", "-l", s"${home / "license.txt"}",
    "-m", "json", "-o", cliPackagePath.string, s"${cliPackagePath / "CliOpt.scala"}")).console, message.Reporter.create)
}


def regenSlang(): Unit = {
  val astPackagePath = home / "slang" / "ast" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "lang" / "ast"
  val slangPackagePath = home / "slang" / "tipe" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "lang"
  val tipePath = slangPackagePath / "tipe"
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "trafo", "-l", s"${home / "license.txt"}", "-m",
    "immutable,mutable", "-o", astPackagePath.string, s"${astPackagePath / "AST.scala"}",
    s"${astPackagePath / "Typed.scala"}")).console, message.Reporter.create)
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "trafo", "-l", s"${home / "license.txt"}", "-m",
    "immutable,mutable", "-o", astPackagePath.string, "-n", "CoreExp", s"${astPackagePath / "CoreExp.scala"}",
    s"${astPackagePath / "Typed.scala"}")).console, message.Reporter.create)
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "trafo", "-l", s"${home / "license.txt"}", "-m",
    "immutable,mutable", "-o", astPackagePath.string, "-n", "IR", s"${astPackagePath / "IR.scala"}",
    s"${astPackagePath / "Typed.scala"}")).console, message.Reporter.create)
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "sergen", "-p", "org.sireum.lang.tipe", "-l",
    s"${home / "license.txt"}", "-m", "json,msgpack", "-o", tipePath.string,
    s"${slangPackagePath / "symbol" / "Info.scala"}", s"${astPackagePath / "AST.scala"}",
    s"${astPackagePath / "Typed.scala"}")).console, message.Reporter.create)
}


def regenSlangLl2(): Unit = {
  val parserPackagePath = home / "slang" / "parser" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "lang" / "parser"
  val parserResourcesPackagePath = home / "slang" / "parser" / "shared" / "src" / "main" / "resources"
  val parserInput = parserResourcesPackagePath / "SlangLl2.g"
  Sireum.procCheck(proc"$sireum parser gen -l ${home / "license.txt"} -p org.sireum.lang.parser -m slang -n SlangLl2 --no-backtracking -o $parserPackagePath $parserInput".console,
    message.Reporter.create)
}


def regenSlangTTLl1(): Unit = {
  val parserPackagePath = home / "slang" / "parser" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "lang" / "parser"
  val parserResourcesPackagePath = home / "slang" / "parser" / "shared" / "src" / "main" / "resources"
  val parserInput = parserResourcesPackagePath / "SlangTruthTable.g"
  Sireum.procCheck(proc"$sireum parser gen -l ${home / "license.txt"} -p org.sireum.lang.parser -m slang -n SlangTruthTableLl1 --no-backtracking -o $parserPackagePath $parserInput".console,
    message.Reporter.create)
}


def regenLogika(): Unit = {
  val logikaPackagePath = home / "logika" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "logika"
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "trafo", "-l", s"${home / "license.txt"}", "-e", "Composite",
    "-m", "immutable,mutable", "-n", "State", "-o", logikaPackagePath.string,
    s"${logikaPackagePath / "State.scala"}")).console, message.Reporter.create)
}


def regenAir(): Unit = {
  val airRootPath = home / "hamr" / "air"
  val airPath = airRootPath / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "hamr" / "ir"
  val airAsts: ISZ[String] = ISZ[String]("AadlAST.scala", "BlessAST.scala", "Emv2AST.scala", "GumboAST.scala",
    "SmfAST.scala", "SysmlAst.scala", "SysmlTyped.scala").map((m: String) => (airPath / m).value)

  val slangPath = home / "slang" / "ast" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "lang" / "ast"
  val slangAsts = ISZ[String]("AST.scala", "Typed.scala").map((m: String) => (slangPath / m).value)

  Sireum.procCheck(Os.proc(ISZ[String](sireum.string, "tools", "trafo", "-l", s"${airRootPath / "license.txt"}",
    "-m", "immutable,mutable", "-o", airPath.string) ++ airAsts ++ slangAsts).console, message.Reporter.create)
  Sireum.procCheck(Os.proc(ISZ[String](sireum.string, "tools", "sergen", "-p", "org.sireum.hamr.ir", "-l",
    s"${airRootPath / "license.txt"}", "-m", "json,msgpack", "-o", airPath.string) ++ airAsts ++ slangAsts).console,
    message.Reporter.create)
}


def regenAct(): Unit = {
  val actPackagePath = home / "hamr" / "codegen" / "act" / "jvm" / "src" / "main" / "scala" / "org" / "sireum" / "hamr" / "act" / "ast"
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "trafo", "-l", s"${home / "license.txt"}", "-m",
    "immutable,mutable", "-o", actPackagePath.string, s"${actPackagePath / "ActAst.scala"}")).console,
    message.Reporter.create)
}


def regenServer(): Unit = {
  val protocolPackagePath = home / "server" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "server" / "protocol"
  val logikaPackagePath = home / "logika" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "logika"
  val astPackagePath = home / "slang" / "ast" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "lang" / "ast"
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "sergen", "-p", "org.sireum.server.protocol", "-l",
    s"${home / "license.txt"}", "-m", "msgpack,json", "-o", protocolPackagePath.string,
    s"${protocolPackagePath / "Message.scala"}",
    s"${logikaPackagePath / "Config.scala"}",
    s"${logikaPackagePath / "Smt2Query.scala"}",
    s"${astPackagePath / "Typed.scala"}"
  )).console, message.Reporter.create)
  val logikaOptionsPackagePath = logikaPackagePath / "options"
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "cligen", "-p", "org.sireum.logika.options", "-l",
    s"${home / "license.txt"}", "-o", logikaOptionsPackagePath.string, "-n", "OptionsCli", "-r",
    (logikaOptionsPackagePath / "options.sc").string
  )).console, message.Reporter.create)
}

def regenParser(isSlang: B): Unit = {
  val parserPackagePath = home / "parser" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "parser"
  val parserResourcesPackagePath = home / "parser" / "jvm" / "src" / "main" / "resources"
  val license = home / "license.txt"
  val input = parserResourcesPackagePath / "SireumAntlr3.g"
  val mode: String = if (isSlang) "slang" else "antlr3"
  Sireum.procCheck(proc"$sireum parser gen -l $license -p org.sireum.parser -m $mode -n SireumGrammar --no-backtracking -o $parserPackagePath $input".console,
    message.Reporter.create)
}

def regenCli(): Unit = {
  val sireumPackagePath = home / "cli" / "jvm" / "src" / "main" / "scala" / "org" / "sireum"
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "cligen", "-p", "org.sireum", "-l", s"${home / "license.txt"}",
    "-o", sireumPackagePath.string, s"${sireumPackagePath / "cli.sc"}")).console, message.Reporter.create)
  val f = sireumPackagePath / "sireum-cli-spec.json"
  f.writeOver(Sireum.procCheck(Os.proc(ISZ(sireum.string, "slang", "run", (sireumPackagePath / "cli.sc").string)),
    message.Reporter.create).out)
  println(s"Wrote $f")
}

def regenFmideCli(): Unit = {
  val installPath = homeBin / "install"
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "cligen", "-s", "fmide.cmd", "-o", installPath.string,
    s"${installPath / "fmide-cli.sc"}")).console, message.Reporter.create)
}

def regenVSCodiumCli(): Unit = {
  val installPath = homeBin / "install"
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "cligen", "-s", "vscodium.cmd", "-o", installPath.string,
    s"${installPath / "vscodium-cli.sc"}")).console, message.Reporter.create)
}

def regenJson(): Unit = {
  val jsonPackagePath = home / "runtime" / "library" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "parser"
  val parserResourcesPackagePath = home / "runtime" / "library" / "shared" / "src" / "main" / "resources"
  val license = home / "license.txt"
  val jsonInput = parserResourcesPackagePath / "JSON.g"
  Sireum.procCheck(proc"$sireum parser gen -l $license -p org.sireum.parser -m slang -n Json --no-backtracking -o $jsonPackagePath $jsonInput".console,
    message.Reporter.create)
  val jsoncInput = parserResourcesPackagePath / "JSONC.g"
  Sireum.procCheck(proc"$sireum parser gen -l $license -p org.sireum.parser -m slang -n Jsonc --no-backtracking -o $jsonPackagePath $jsoncInput".console,
    message.Reporter.create)
}

def regenReflect(): Unit = {
  val cliScalaPath = home / "cli" / "jvm" / "src" / "main" / "scala"
  val libraryScalaPath = home / "runtime" / "library" / "jvm" / "src" / "main" / "scala"
  val license = home / "license.txt"
  val xs = ISZ[String]("MessagePack", "B", "Z", "C", "String", "F32", "F64", "ST", "R", "IS", "MS", "RS", "Reflection", "Sireum")
  val excluded: String = st"${(for (x <- xs) yield st"org.sireum.$x", ",")}".render
  Sireum.procCheck(proc"$sireum proyek reflect --license $license --package org.sireum --class LibJvmUtil_Ext --slice library --include-packages org.sireum --exclude $excluded --output $libraryScalaPath $home".console,
    message.Reporter.create)
  println()
  Sireum.procCheck(proc"$sireum proyek reflect --license $license --package org.sireum.cli --class SlangRunner_Ext --slice library-shared --include-packages org.sireum.foo --include org.sireum.Sireum --output $cliScalaPath $home".console,
    message.Reporter.create)
  println()
}

def regenAnvil(): Unit = {
  val astPackagePath = home / "slang" / "ast" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "lang" / "ast"
  val anvilPackagePath = home / "anvil" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "anvil"
  Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "trafo", "-l", s"${home / "license.txt"}", "-m",
    "immutable,mutable", "-o", anvilPackagePath.string, "-n", "AnvilIR",
    s"${anvilPackagePath / "Intrinsic.scala"}",
    s"${astPackagePath / "IR.scala"}"
  )).console, message.Reporter.create)
}

def regenDap(): Unit = {
  val dir = Os.tempDir()
  val dapVersion = versions.get("org.sireum.version.dap").get
  val schema = dir / s"debugAdapterProtocol-v$dapVersion.json"
  val url = s"https://raw.githubusercontent.com/microsoft/debug-adapter-protocol/refs/tags/v$dapVersion/debugAdapterProtocol.json"
  if (schema.downloadFrom(url)) {
    val dapPackagePath = home / "protocol" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "protocol" / "dap"
    dapPackagePath.mkdirAll()
    Sireum.procCheck(Os.proc(ISZ(sireum.string, "tools", "jsons", "-l", s"${home / "license.txt"}", "-p",
      "org.sireum.protocol.dap", "-n", "DapBinding", "-o", dapPackagePath.string, schema.string)).
      console, message.Reporter.create)
  } else {
    eprintln(s"Could not download $url")
  }
  dir.removeAll()

}

def m2Version: String = {
  for (line <- ops.StringOps(proc"$sireum --version".runCheck().out).split((c: C) => c == '\n')) {
    val lineOps = ops.StringOps(line)
    if (lineOps.contains(DependencyManager.libraryKey)) {
      val i = lineOps.stringIndexOf("->")
      val j: Z = lineOps.lastIndexOf(',')
      return ops.StringOps(lineOps.substring(i + 2, if (j >= 0) j else line.size)).trim
    }
  }
  halt("Could not detect Slang library version")
}

def m2(): Os.Path = {
  val repository = Os.home / ".m2" / "repository"
  val kekinianRepo = repository / "org" / "sireum" / "kekinian"
  kekinianRepo.removeAll()
  Sireum.procCheck(proc"$sireum proyek publish$proxyOpt -n $proyekName --par --sha3 --ignore-runtime --m2 ${repository.up.canon} --version $m2Version $home org.sireum.kekinian".console,
    message.Reporter.create)
  return kekinianRepo
}

def m2Lib(isJs: B): Unit = {
  val repository = Os.home / ".m2" / "repository"

  val target: String = if (isJs) "--target js" else "--target jvm"
  Sireum.procCheck(proc"$sireum proyek publish$proxyOpt -n $proyekName --par --sha3 --ignore-runtime --slice library --m2 ${repository.up.canon} $target --version $m2Version $home org.sireum.kekinian".console,
    message.Reporter.create)
}

def m2ScalacPlugin(): Unit = {
  val scalacPluginVersion = versions.get("org.sireum%%scalac-plugin%").get
  val repository = Os.home / ".m2" / "repository" / "org" / "sireum" / "scalac-plugin_2.13" / scalacPluginVersion
  repository.mkdirAll()
  for (suffix <- ISZ(".pom", ".jar", "-sources.jar", "-javadoc.jar")) {
    val f = repository / s"scalac-plugin_2.13-$scalacPluginVersion$suffix"
    val url = s"https://jitpack.io/org/sireum/scalac-plugin/$scalacPluginVersion/scalac-plugin-$scalacPluginVersion$suffix"
    println(s"Downloading ${f.name} from $url ...")
    f.downloadFrom(url)
    println()
  }
}

def jitpack(): Unit = {
  val init = Init(home, Os.kind, versions)
  init.installCoursier()
  val ver = ops.StringOps(Sireum.commitHash).substring(0, 10)
  println(s"Triggering jitpack build for $ver ...")
  val sc = Os.tempFix("", ".sc")
  sc.writeOver(
    st"""import org.sireum._
        |for (cif <- Coursier.fetch("${Sireum.scalaVer}", ISZ(s"org.sireum.kekinian::cli:$ver"), Coursier.Proxy.empty)) {
        |  println(cif.path)
        |}""".render
  )
  sc.removeOnExit()
  Sireum.procCheck(proc"$sireum slang run $sc".console, message.Reporter.create)
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

def mill(): Unit = {
  val init = Init(home, Os.kind, versions)
  init.installMill(T)
}

def setup(fresh: B): Unit = {
  println("Setup ...")
  build(fresh, F, F, F)
  val init = Init(home, Os.kind, versions)
  init.deps()
  init.distro(isDev = F, buildPackage = F, buildIve = T, buildVSCodePackage = F, isUltimate = F, isServer = F)
  val suffix: String = ""
  project(T)
  Os.kind match {
    case Os.Kind.Win =>
      println(s"Sireum IVE can now be launched by running ${homeBin / "win" / s"idea$suffix" / "bin" / "IVE.exe"}")
      println(s"Java Development Kit (JDK) is available at ${homeBin / "win" / "java"}")
      println(s"Scala is available at ${homeBin / "scala"}")
    case Os.Kind.Linux =>
      println(s"Sireum IVE can now be launched by running ${homeBin / "linux" / s"idea$suffix" / "bin" / "IVE.sh"}")
      println(s"Java Development Kit (JDK) is available at ${homeBin / "linux" / "java"}")
      println(s"Scala is available at ${homeBin / "scala"}")
    case Os.Kind.LinuxArm =>
      println(s"Sireum IVE can now be launched by running ${homeBin / "linux" / "arm" / s"idea$suffix" / "bin" / "IVE.sh"}")
      println(s"Java Development Kit (JDK) is available at ${homeBin / "linux" / "arm" / "java"}")
      println(s"Scala is available at ${homeBin / "scala"}")
    case Os.Kind.Mac =>
      println(s"Sireum IVE can now be launched by running ${homeBin / "mac" / s"idea$suffix" / "IVE.app"}")
      println(s"Java Development Kit (JDK) is available at ${homeBin / "mac" / "java"}")
      println(s"Scala is available at ${homeBin / "scala"}")
    case _ =>
  }
}

def project(skipBuild: B): Unit = {
  if (!skipBuild) {
    build(F, F, F, F)
  }
  println("Generating IVE project ...")
  proc"$sireum proyek ive$proxyOpt --force $home".console.runCheck()
}

def ram(): Unit = {
  Os.kind match {
    case Os.Kind.Mac =>
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
    case _ =>
      eprintln("This utility is only available in macOS")
      Os.exit(-1)
  }
}

def buildForms(): Unit = {
  Init(home, Os.kind, versions).buildForms()
}

@pure def builtIn: Os.Path = {
  return home / "runtime" / "library" / "shared" / "src" / "main" / "scala" / "org" / "sireum" / "BuiltInTypes.slang"
}

if (!builtIn.exists) {
  eprintln("Some sub-modules are not present; please clone recursively or run:")
  eprintln("git submodule update --init --recursive --remote")
  Os.exit(-1)
}

if (Os.cliArgs.isEmpty) {
  val fresh: B = sireumJar.exists && builtIn.lastModified > sireumJar.lastModified
  val idea = homeBin / platform / "idea"
  val vscodium = homeBin / platform / "vscodium"
  val version: B = sireumJar.exists && (home / "versions.properties").lastModified > sireumJar.lastModified
  val vers = Sireum.versions
  val rebuildIVE: B = {
    var r = F
    for (key <- vers.keys if !r) {
      if (key == "org.sireum.version.idea" || ops.StringOps(key).startsWith("org.sireum.version.plugin.")) {
        if (versions.get(key) != vers.get(key)) {
          r = T
        }
      }
    }
    r
  }
  val rebuildCodeIVE: B = {
    var r = F
    for (key <- vers.keys if !r) {
      if (key == "org.sireum.version.vscodium" || ops.StringOps(key).startsWith("org.sireum.version.vscodium.extension")) {
        if (versions.get(key) != vers.get(key)) {
          r = T
        }
      }
    }
    r
  }
  val isUltimate: B = rebuildIVE

  if (fresh) {
    println(s"Modifications to ${builtIn.name} detected ... ")
    println()
  } else if (version & isUltimate) {
    println(s"Modifications to version.properties detected ... ")
    println()
  }
  if (rebuildIVE) {
    println(s"Rebuilding IVE ...")
    println()
  }
  if (isUltimate) {
    setup(fresh)
  } else {
    build(fresh, F, F, F)
    if (version) {
      project(T)
    }
  }
  if (rebuildCodeIVE && vscodium.exists) {
    println(s"Rebuilding CodeIVE ...")
    proc"${Os.javaExe(Some(home))} -jar $sireumJar setup vscode".console.runCheck()
    println()
  }
} else {
  val cliArgs = Os.cliArgs
  val size = cliArgs.size
  var i = 0
  while (i < size) {
    cliArgs(i) match {
      case string"jar" => build(F, F, F, F)
      case string"fresh" => build(T, F, F, F)
      case string"uber" => build(F, F, T, F)
      case string"native" => build(F, T, F, F)
      case string"native-script" => build(F, T, F, T)
      case string"setup" => setup(F)
      case string"project" => project(F)
      case string"tipe" => tipe()
      case string"compile" => compile(F)
      case string"compile-js" => compile(T)
      case string"test" => test()
      case string"verify" => verify()
      case string"test-verify" => test(); verify()
      case string"regen-slang" => regenSlang()
      case string"regen-slang-ll2" => regenSlangLl2()
      case string"regen-logika" => regenLogika()
      case string"regen-project" => regenProject()
      case string"regen-presentasi" => regenPresentasi()
      case string"regen-cliopt" => regenCliOpt()
      case string"regen-air" => regenAir()
      case string"regen-act" => regenAct()
      case string"regen-server" => regenServer()
      case string"regen-parser" => regenParser(T)
      case string"regen-parser-antlr3" => regenParser(F)
      case string"regen-cli" => regenCli()
      case string"regen-fmide-cli" => regenFmideCli()
      case string"regen-vscodium-cli" => regenVSCodiumCli()
      case string"regen-json" => regenJson()
      case string"regen-slang-tt" => regenSlangTTLl1()
      case string"regen-reflect" => regenReflect()
      case string"regen-anvil" => regenAnvil()
      case string"regen-dap" => regenDap()
      case string"m2" => m2()
      case string"m2-lib" => m2Lib(F)
      case string"m2-lib-js" => m2Lib(T)
      case string"m2-scalac" => m2ScalacPlugin()
      case string"jitpack" => jitpack()
      case string"ghpack" => ghpack()
      case string"mill" => mill()
      case string"ram" => ram()
      case string"cvc" => cvc()
      case string"z3" => z3()
      case string"forms" => buildForms()
      case string"-h" => usage()
      case string"--help" => usage()
      case string"distro" =>
        var buildPackage: B = F
        var buildVSCodiumPackage: B = F
        var kinds = ISZ[Os.Kind.Type]()
        i = i + 1
        while (i < size && ops.StringOps(cliArgs(i)).startsWith("--")) {
          cliArgs(i) match {
            case string"--linux" => kinds = kinds :+ Os.Kind.Linux
            case string"--linux-arm" => kinds = kinds :+ Os.Kind.LinuxArm
            case string"--mac" => kinds = kinds :+ Os.Kind.Mac
            case string"--win" => kinds = kinds :+ Os.Kind.Win
            case string"--vscodium" => buildVSCodiumPackage = T
            case string"--pack" => buildPackage = T
            case opt =>
              usage()
              eprintln(s"Unrecognized distro command option: $opt")
              Os.exit(-1)
          }
          i = i + 1
        }
        i = i - 1
        if (kinds.isEmpty) {
          kinds = kinds :+ Os.kind
        }
        for (kind <- kinds) {
          val init = Init(home, kind, versions)
          init.deps()
          init.distro(F, buildPackage, T, buildVSCodiumPackage, F, F)
          println()
        }
      case cmd =>
        usage()
        eprintln(s"Unrecognized command: $cmd")
        Os.exit(-1)
    }
    i = i + 1
  }
}
