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
    val context = Context.SimpleSandboxInstallationContext_v2020_1(workspace, installSireum, petalinuxInstallerPath, xilinxUnifiedPath) // todo needs context selector

    return AnvilSandbox.install(context)
  }

  def compile(args: Cli.SireumAnvilCompileOption): Z = {
    validate(args) // fail fast if possible
    val hc: HardwareContext = HardwareContext_Zynq_7000_SoC_ZedBoard() // hardware descriptors
    val tc: ToolchainContext = DefaultToolchainContext() // toolchain support
    val ec: ExecutionContext = createExecutionContext(args) // execution plan
    val tm = (m: TranspilersCOptionMirror) => CTranspiler.run(mirrorToPrototype(m))
    return AnvilCompiler.compile(hc, tc, ec, tm)
  }

  @pure def validate(args: Cli.SireumAnvilCompileOption): Unit = {
    expect(args.stage.nonEmpty, st"--stage cannot be empty")
    val path = getPath("transpiler-args-file", args.transpilerArgs, T)
    expect(path.isFile, st"Usage: --transpiler-args-file must be a path to an existing file.")
  }

  def createExecutionContext(args: Cli.SireumAnvilCompileOption): ExecutionContext = {
    /*
     * Creates a new SandboxContext and hides its implementation type
     */
    def createSandboxContext(sandboxWorkspace: SandboxWorkspace): SandboxCompilationContext = {
      return SimpleSandboxCompilationContext(sandboxWorkspace)
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
    val stages: ISZ[Context.CompileStage.Type] = {
      expect(args.stage.nonEmpty, st"At least one stage must be specified.")
      args.stage.flatMap((s: SireumAnvilCompileStage.Type) => convertStage(s))
    }

    // validate the passed sandbox-path (if present)
    // todo confirm that path has VagrantFile (easy sanity check for users)
    val sandboxRoot: Option[Os.Path] = getPathOpt(string"sandbox-path", args.sandboxPath, true)

    // get the workspace's root path
    val transpilerArgs = parseTranspilerArgs(args)
    expect(transpilerArgs.anvilTranspilerPass == Cli.SireumSlangTranspilersCAnvilExecutionPass.None,
      st"Expected transpiler pass be none (or direct call to CTranspiler)")
    expect(transpilerArgs.anvilTranspilerContext.isEmpty,
      st"Expected empty context (or direct call to CTranspiler)")
    val projectRoot: Os.Path = getPath(string"output-dir", transpilerArgs.output, false)

    // parse and validate method descriptor
    val methodDescriptor = parseMethodDescriptors(args.args) // e.g. ISZ("org", "sireum", "Math", "add")
    val methodName = ISZOps(methodDescriptor).last // e.g. "add"
    val mangledMethodName = org.sireum.lang.ast.Util.mangleName(methodDescriptor).render // e.g. "org_sireum_Math_add"

    // create the project and (optionally) the sandbox to interact with the project in
    val mirror: TranspilersCOptionMirror = prototypeToMirror(transpilerArgs)
    val project: ProjectContext = SimpleProjectContext(ProjectWorkspace(projectRoot), methodDescriptor, methodName, mangledMethodName, mirror)
    val sandbox: Option[SandboxCompilationContext] = sandboxRoot
      .map((localPath: Os.Path) => SandboxWorkspace(localPath))
      .map((sandboxWorkspace: SandboxWorkspace) => createSandboxContext(sandboxWorkspace))

    return SimpleExecutionContext(project, sandbox, set(stages))
  }

  @pure def parseTranspilerArgs(args: Cli.SireumAnvilCompileOption): Cli.SireumSlangTranspilersCOption = {
    val transpilerArgs: String = {
      expect(args.transpilerArgs.nonEmpty, st"Transpiler args cannot be empty.")
      expect(args.transpilerArgs.map((pathString: String) => Os.path(pathString)).exists(path => path.exists && path.isFile), st"The transpiler-args.")
      args.transpilerArgs.get
    }

    val topOption: Cli.SireumTopOption = {
      val cli = Cli(Os.pathSepChar)
      val lines = Os.path(transpilerArgs).readLines
      val cliOpt = cli.parseSireumSlangTranspilersC(lines, z"0")
      expect(cliOpt.nonEmpty, st"Unable to parse transpiler args $transpilerArgs")
      cliOpt.get
    }

    topOption match {
      case o: SireumSlangTranspilersCOption => {
        expect(o.output.nonEmpty, st"The transpiler does not support a default --output path when called by anvil.")
        return o
      }
      case e => halt(st"Expected transpiler args but got $e".render)
    }
  }

  @pure def getPath(key: String, container: Option[String], requirePathExists: B): Os.Path = {
    expect(container.nonEmpty, st"--$key requires a value but is empty")
    val path = Os.path(container.get)
    if (requirePathExists) {
      expect(path.exists, st"--$key requires a path that exists but got $path")
    }
    return path
  }

  @pure def getPathOpt(key: String, container: Option[String], requirePathExistsIfPresent: B): Option[Os.Path] = {
    val result = container.map((pathString: String) => Os.path(pathString))
    if (result.nonEmpty && requirePathExistsIfPresent) {
      expect(result.get.exists, st"--$key requires a path that exists but got ${result.get}")
    }
    return result
  }

  @pure def prototypeToMirror(prototype: SireumSlangTranspilersCOption): TranspilersCOptionMirror = {
    @pure def convertInner(innerPrototype: SireumSlangTranspilersCAnvilExecutionPass.Type): TranspilersCAnvilExecutionPassMirror.Type = {
      innerPrototype match {
        case SireumSlangTranspilersCAnvilExecutionPass.None => return TranspilersCAnvilExecutionPassMirror.None
        case SireumSlangTranspilersCAnvilExecutionPass.First => return TranspilersCAnvilExecutionPassMirror.First
        case SireumSlangTranspilersCAnvilExecutionPass.Second => return TranspilersCAnvilExecutionPassMirror.Second
      }
    }

    return TranspilersCOptionMirror(
      help = prototype.help,
      args = prototype.args,
      sourcepath = prototype.sourcepath,
      strictAliasing = prototype.strictAliasing,
      output = prototype.output,
      verbose = prototype.verbose,
      apps = prototype.apps,
      bitWidth = prototype.bitWidth,
      projectName = prototype.projectName,
      stackSize = prototype.stackSize,
      customArraySizes = prototype.customArraySizes,
      maxArraySize = prototype.maxArraySize,
      maxStringSize = prototype.maxStringSize,
      cmakeIncludes = prototype.cmakeIncludes,
      exts = prototype.exts,
      libOnly = prototype.libOnly,
      excludeBuild = prototype.excludeBuild,
      plugins = prototype.plugins,
      fingerprint = prototype.fingerprint,
      stableTypeId = prototype.stableTypeId,
      unroll = prototype.unroll,
      save = prototype.save,
      load = prototype.load,
      customConstants = prototype.customConstants,
      forwarding = prototype.forwarding,
      anvilTranspilerPass = convertInner(prototype.anvilTranspilerPass),
      anvilTranspilerContext = prototype.anvilTranspilerContext
    )
  }

  @pure def mirrorToPrototype(mirror: TranspilersCOptionMirror): SireumSlangTranspilersCOption = {
    @pure def convertInner(innerMirror: TranspilersCAnvilExecutionPassMirror.Type): SireumSlangTranspilersCAnvilExecutionPass.Type = {
      innerMirror match {
        case TranspilersCAnvilExecutionPassMirror.None => return SireumSlangTranspilersCAnvilExecutionPass.None
        case TranspilersCAnvilExecutionPassMirror.First => return SireumSlangTranspilersCAnvilExecutionPass.First
        case TranspilersCAnvilExecutionPassMirror.Second => return SireumSlangTranspilersCAnvilExecutionPass.Second
      }
    }
    return SireumSlangTranspilersCOption(
      help = mirror.help,
      args = mirror.args,
      sourcepath = mirror.sourcepath,
      strictAliasing = mirror.strictAliasing,
      output = mirror.output,
      verbose = mirror.verbose,
      apps = mirror.apps,
      bitWidth = mirror.bitWidth,
      projectName = mirror.projectName,
      stackSize = mirror.stackSize,
      customArraySizes = mirror.customArraySizes,
      maxArraySize = mirror.maxArraySize,
      maxStringSize = mirror.maxStringSize,
      cmakeIncludes = mirror.cmakeIncludes,
      exts = mirror.exts,
      libOnly = mirror.libOnly,
      excludeBuild = mirror.excludeBuild,
      plugins = mirror.plugins,
      fingerprint = mirror.fingerprint,
      stableTypeId = mirror.stableTypeId,
      unroll = mirror.unroll,
      save = mirror.save,
      load = mirror.load,
      customConstants = mirror.customConstants,
      forwarding = mirror.forwarding,
      anvilTranspilerPass = convertInner(mirror.anvilTranspilerPass),
      anvilTranspilerContext = mirror.anvilTranspilerContext
    )
  }

}