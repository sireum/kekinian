::/*#! 2> /dev/null                                   #
@ 2>/dev/null # 2>nul & echo off & goto BOF           #
if [ -z "${SIREUM_HOME}" ]; then                      #
  echo "Please set SIREUM_HOME env var"               #
  exit -1                                             #
fi                                                    #
exec "${SIREUM_HOME}/bin/sireum" slang run "$0" "$@"  #
:BOF
setlocal
if not defined SIREUM_HOME (
  echo Please set SIREUM_HOME env var
  exit /B -1
)
"%SIREUM_HOME%\bin\sireum.bat" slang run %0 %*
exit /B %errorlevel%
::!#*/
// #Sireum
import org.sireum._
import org.sireum.extension.Console.pause

@datatype class CC(val isGcc: B, val path: Os.Path)

val sireumHome = Os.path(Os.env("SIREUM_HOME").get)
val sireumScript = sireumHome / "bin" / (if (Os.isWin) "sireum.bat" else "sireum")
val scriptDir = Os.slashDir
val script = scriptDir / "sha3-demo.cmd"
val sha3SourceDir = sireumHome / "transpilers" / "c" / "jvm" / "src" / "test" / "scala"
val outDir = scriptDir / "sha3-demo"
outDir.removeAll()


println("Initializing runtime library ...")
Sireum.initRuntimeLibrary()
println()

println("Preparing CLion ...")
val binPlatform: Os.Path = Os.kind match {
  case Os.Kind.Mac => sireumHome / "bin" / "mac"
  case Os.Kind.Linux => sireumHome / "bin" / "linux"
  case Os.Kind.LinuxArm => sireumHome / "bin" / "linux" / "arm"
  case Os.Kind.Win => sireumHome / "bin" / "win"
  case _ =>
    eprintln("Unsupported platform")
    Os.exit(-1)
    halt("unsupported")
}
val clion: Os.Path =
  if (Os.isMac) binPlatform / "clion" / "CLion.app"
  else if (Os.isWin) binPlatform / "clion" / "bin" / "clion.bat"
  else binPlatform / "clion" / "bin" / "clion.sh"
(sireumHome / "bin" / "install" / "clion.cmd").slash(ISZ())
println()


val ccompOpt: Option[CC] = if (Os.isWin) {
  None()
} else {
  println("Preparing CompCert ...")
  (sireumHome / "bin" / "install" / "compcert.cmd").slash(ISZ())
  var rOpt: Option[Os.Path] = None()
  for (d <- (binPlatform / ".opam").list if d.isDir && rOpt.isEmpty && (d / "bin").exists) {
    for (p <- (d / "bin").list if rOpt.isEmpty && p.name == "ccomp") {
      rOpt = Some(p)
    }
  }
  println()
  rOpt match {
    case Some(r) => Some(CC(F, r))
    case _ => halt("Infeasible")
  }
}


val gccOpt: Option[CC] = {
  val gcc: Os.Path = Os.env("GCC") match {
    case Some(v) => Os.path(v)
    case _ => Os.kind match {
      case Os.Kind.Mac => Os.path("/opt/local/bin/gcc")
      case _ => Os.path("gcc")
    }
  }
  val r = proc"$gcc --version".run()
  if (r.exitCode == 0 && ops.StringOps(r.out).contains("Free Software")) {
    println("Detected gcc")
    println(r.out)
    Some(CC(T, gcc))
  } else {
    None()
  }
}


val clangOpt: Option[CC] = if (Os.isMac && gccOpt.isEmpty) {
  val clang: Os.Path = Os.env("CLANG") match {
    case Some(v) => Os.path(v)
    case _ => Os.path("clang")
  }
  val r = proc"$clang --version".run()
  if (r.exitCode == 0 && ops.StringOps(r.out).contains("clang")) {
    println("Detected clang")
    println(r.out)
    Some(CC(F, clang))
  } else {
    None()
  }
} else {
  None()
}

@strictpure def split(command: String): ISZ[String] = ops.StringOps(command).split((c: C) => c == ' ')

def proc(desc: String, command: String, inputOpt: Option[String]): Os.Proc = {
  println(command)
  pause(s"Press enter to execute ($desc) ... ")
  val r = Os.proc(split(command))
  inputOpt match {
    case Some(input) => return r.input(input)
    case _ => return r
  }
}

def sireum(desc: String, command: String): Unit = {
  println(s"sireum $command")
  pause(s"Press enter to execute ($desc) ... ")
  Sireum.run(split(command))
}

def clean(): Unit = {
  for (f <- split("CMakeCache.txt sha3 CMakeFiles Makefile cmake_install.cmake libmain.a")) {
    (outDir / f).removeAll()
  }
}

def compileAndRun(ccOpt: Option[CC]): Unit = {
  if (ccOpt.isEmpty) {
    return
  }
  val cc = ccOpt.get.path
  clean()
  proc(s"using $cc", s"cmake $outDir", None()).env(ISZ("CC" ~> cc.string)).console.at(outDir).runCheck()
  println()

  proc(s"compiling", s"make -j4", None()).console.at(outDir).console.runCheck()
  println()

  proc(s"on ${script.name}", s"${outDir / "sha3"} 256", Some(script.read)).console.runCheck()
  println()

  if (!Os.isWin && ccOpt.get.isGcc) {
    val csv = outDir / "stack-usage.csv"
    csv.writeOver(proc(s"save stack usage to ${csv.name}", s"$sireumScript tools checkstack --format csv $outDir", None()).runCheck().out)
    if (Os.isMac) {
      proc"open ${csv.name}".at(outDir).runCheck()
      println()
    } else if (Os.isWin) {
      proc"cmd /c start ${csv.name}".at(outDir).runCheck()
    } else {
      proc"xdg-open ${csv.name}".at(outDir).runCheck()
      println()
    }
  }
}

// Step 1. Transpile sha3 example
sireum("Slang-to-C translation", s"slang transpilers c --sourcepath $sha3SourceDir --exts ${sha3SourceDir / "ext"} --output-dir $outDir --apps sha3")
println()

// Step 2. [gcc]. Compile using gcc and run sha3 256 on this script (with stack usage report)
compileAndRun(gccOpt)

// Step 3. [clang]. Compile using clang and run sha3 256 on this script (only on macOS and when gcc is not available)
compileAndRun(clangOpt)

// Step 4. [CompCert]. Compile using CompCert and run sha3 256 on this script (skip if on Windows)
compileAndRun(ccompOpt)
clean()
(outDir / "CMakeLists.txt").writeAppend("\nadd_definitions(-DSIREUM_LOC)\n")

// Step 5. Browse transpiled sha3 code using CLion
if (Os.isMac) {
  proc("browse C code", s"open $clion --args $outDir", None()).runCheck()
} else {
  proc("browse C code", s"$clion $outDir", None()).runCheck()
}