::#! 2> /dev/null                                              #
@ 2>/dev/null # 2>nul & echo off & goto BOF                    #
exec $(cd `dirname $0` && pwd)/sireum slang run -s "$0" "$@"   #
:BOF
%dp0sireum.bat slang run -s "%0" %*
exit /B %errorlevel%
::!#
// #Sireum
import org.sireum._

val home = Os.path(Os.cliArgs(0)).up
val sireumJar = home / "bin" / "sireum.jar"

if (!sireumJar.exists) {
  eprintln(s"Could not find Sireum: $sireumJar")
  Os.exit(-1)
}

Os.cliArgs.size match {
  case z"1" =>
    println("Usage: ( cliopt | slang | cli )+")
    Os.exit(0)
  case _ =>
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

for (i <- 1 until Os.cliArgs.size) {
  Os.cliArgs(i) match {
    case string"cliopt" => regenCliOpt()
    case string"slang" => regenSlang()
    case string"cli" => regenCli()
    case s =>
      eprintln(s"Invalid argument $s")
      Os.exit(-1)
  }
}