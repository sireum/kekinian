// #Sireum

package org.sireum.cli

import org.sireum._
import org.sireum.Cli.{SireumAnvilCompileStage, SireumSlangTranspilersCAnvilExecutionPass, SireumSlangTranspilersCOption}
import org.sireum.anvil.{AnvilCompiler, AnvilSandbox, Context, InstallerWorkspace, ProjectWorkspace, SandboxWorkspace}
import org.sireum.anvil.Context._
import org.sireum.ops.{ISZOps, StringOps}

object Anvil {

  def sandbox(args: Cli.SireumAnvilSandboxOption): Z = {

    def mkdirRoot(): Os.Path = {
      expect(args.args.size == z"1", st"Sandbox requires a single argument (a path to the folder).")
      val arg: Os.Path = Os.path(args.args(z"0"))
      if (arg.exists) {
        expect(arg.isDir, st"Sandbox cannot overwrite an existing file.") // don't overwrite non-directories
        expect(arg.list.isEmpty, st"Sandbox cannot target a non-empty directory: ${arg.list}") // todo confirm that this checks if empty (what about special files in some operating systems?)
      } else {
        arg.mkdirAll()
      }
      return arg
    }

    def sanitizePathOpt(fileName: String, cliOpt: Option[String]): Option[Os.Path] = {
      cliOpt.map((pathString: String) => {
        val path = Os.path(pathString)
        // todo add check that pathname matches filename...
        expect(path.exists, st"The $fileName does not exist ($path)")
        path
      })
    }

    val root: Os.Path = mkdirRoot()
    val installSireum: B = !args.excludeSireum
    val petalinuxInstallerPath: Option[Os.Path] = sanitizePathOpt(string"petalinux-installer-path", args.petalinuxInstallerPath)
    val xilinxUnifiedPath: Option[Os.Path] = sanitizePathOpt(string"xilinx-unified-path", args.xilinxUnifiedPath)

    val workspace = InstallerWorkspace(root)
    val context = Context.SimpleSandboxInstallationContext_v2020_1(workspace, installSireum, petalinuxInstallerPath, xilinxUnifiedPath)

    return AnvilSandbox.install(context)
  }

  // needs pathSep because File.pathSeparatorChar is java.io
  def compile(args: Cli.SireumAnvilCompileOption, pathSep: C): Z = {
    expect(args.transpilerArgs.nonEmpty, st"Transpiler args cannot be empty.")
    expect(args.transpilerArgs.map((pathString: String) => Os.path(pathString)).exists(path => path.exists && path.isFile), st"The transpiler-args.")
    expect(args.stage.nonEmpty, st"At least one stage must be specified.")

    val unparsed: ISZ[String] = Os.path(args.transpilerArgs.get).readLines
    val parseResult: Option[Cli.SireumTopOption] = Cli(pathSep).parseSireumSlangTranspilers(unparsed, z"0")
    val optWorkspace: Option[CompileContext] = parseResult.flatMap((cliOpt: Cli.SireumTopOption) => convertCliOpt(args, cliOpt))

    expect(optWorkspace.nonEmpty, st"Unable to parse transpiler args")
    val context: CompileContext = optWorkspace.get

    return AnvilCompiler.compile(context)
  }

  def handleTranspilerArgs(args: Cli.SireumAnvilCompileOption, transpilerArgs: Cli.SireumSlangTranspilersCOption): CompileContext = {
    expect(transpilerArgs.output.nonEmpty, st"The transpiler does not support a default --output path when called by anvil.")

    /*
     * Creates a new SandboxContext and hides its implementation type
     */
    def createSandboxContext(sandboxWorkspace: SandboxWorkspace): SandboxContext = {
      return SimpleSandboxContext(sandboxWorkspace)
    }

    def convertStage(stage: SireumAnvilCompileStage.Type): ISZ[CompileStage.Type] = {
      stage match {
        case SireumAnvilCompileStage.Hls => return ISZ(CompileStage.Hls)
        case SireumAnvilCompileStage.Hw => return ISZ(CompileStage.Hw)
        case SireumAnvilCompileStage.Sw => return ISZ(CompileStage.Sw)
        case SireumAnvilCompileStage.Os => return ISZ(CompileStage.Os)
        case SireumAnvilCompileStage.All => return ISZ(CompileStage.Hls, CompileStage.Hw, CompileStage.Sw, CompileStage.Os)
      }
    }

    def parseMethodDescriptors(mds: ISZ[String]): ISZ[String] = {
      // should be of form Path#simpleName
      expect(mds.size == z"1", st"There should be one exactly arg to indicate the target method")
      val result = StringOps(mds(z"0")).split((c: C) => c == c"#" || c == c".")
      expect(result.size >= z"2", st"MethodDescriptors should be of the form fully.qualified.Name#methodName")
      return result
    }

    // convert cliOpt stages to a Set of anvil stages
    val stages: ISZ[Context.CompileStage.Type] = args.stage.flatMap((s: SireumAnvilCompileStage.Type) => convertStage(s))

    // validate the passed sandbox-path (if present)
    // todo confirm that path has VagrantFile (easy sanity check for users)
    val sandboxRoot: Option[Os.Path] = getPathOpt(string"sandbox-path", args.sandboxPath, true)

    // get the workspace's root path
    val projectRoot: Os.Path = getPath(string"output-dir", transpilerArgs.output, false)

    // parse and validate method descriptor
    val methodDescriptor = parseMethodDescriptors(args.args)
    val methodName = ISZOps(methodDescriptor).last

    // create the project and (optionally) the sandbox to interact with the project in
    val project: ProjectContext = SimpleProjectContext(ProjectWorkspace(projectRoot), methodName, transpilerArgs.apps)
    val sandbox: Option[SandboxContext] = sandboxRoot
      .map((localPath: Os.Path) => SandboxWorkspace(localPath))
      .map((sandboxWorkspace: SandboxWorkspace) => createSandboxContext(sandboxWorkspace))

    // full context for anvil compilation is the hardware, a supporting toolchain, and an execution plan
    val hardware: HardwareContext = HardwareContext_Zynq_7000_SoC_ZedBoard()
    val toolchain: ToolchainContext = DefaultToolchainContext()
    val env = SimpleExecutionContext(project, sandbox, seqToSet(stages))

    return DefaultCompileContext(hardware, toolchain, env)
  }

  def convertCliOpt(args: Cli.SireumAnvilCompileOption, cliOpt: Cli.SireumTopOption): Option[CompileContext] = {
    cliOpt match {
      case o: SireumSlangTranspilersCOption => Some[CompileContext](handleTranspilerArgs(args, o))
      case _ => return None[CompileContext]()
    }
  }

  def getPath(name: String, container: Option[String], requirePathExists: B): Os.Path = {
    expect(container.nonEmpty, st"Missing required argument: $name")
    val path = Os.path(container.get)
    if (requirePathExists) {
      expect(path.exists, st"The given path does not exist: $name")
    }
    return path
  }

  def getPathOpt(name: String, container: Option[String], requirePathExistsIfPresent: B): Option[Os.Path] = {
    val result = container.map((pathString: String) => Os.path(pathString))
    if (result.nonEmpty && requirePathExistsIfPresent) {
      expect(result.get.exists, st"The given path does not exist: $name")
    }
    return result
  }

  def seqToSet[T](seq: ISZ[T]): Set[T] = {
    return Set.empty[T] ++ seq
  }

  def expect(requirement: B, message: ST): Unit = {
    if (!requirement) {
      eprintln(message.render)
      Os.exit(z"1")
    }
  }

}