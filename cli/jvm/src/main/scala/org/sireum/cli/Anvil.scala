// #Sireum

package org.sireum.cli

import org.sireum._
import org.sireum.Cli.SireumAnvilCompileStage
import org.sireum.anvil.{AnvilCompiler, AnvilSandbox, Context, InstallerWorkspace, ProjectWorkspace, SandboxWorkspace}
import org.sireum.anvil.Context._
import org.sireum.ops.{ISZOps, StringOps}

object Anvil {

  def sandbox(args: Cli.SireumAnvilSandboxOption): Z = {

    def mkdirRoot(): Os.Path = {
      assert(args.args.length == z"1", "Sandbox requires a single argument (a path to the folder).")
      val arg: Os.Path = Os.path(args.args(0))
      if (arg.exists) {
        assert(arg.isDir, "Sandbox cannot overwrite an existing file.") // don't overwrite non-directories
        assert(arg.list.isEmpty, st"Sandbox cannot target a non-empty directory: ${arg.list}".render) // todo confirm that this checks if empty (what about special files in some operating systems?)
      } else {
        arg.mkdirAll()
      }
      return arg
    }
    def sanitizePathOpt(fileName: String, cliOpt: Option[String]): Option[Os.Path] = {
      cliOpt.map(pathString => {
        val path = Os.path(pathString)
        // todo add check that pathname matches filename...
        assert(path.exists, s"The $fileName does not exist ($path)")
        path
      })
    }

    val root: Os.Path = mkdirRoot()
    val installSireum: B = !args.excludeSireum
    val petalinuxInstallerPath: Option[Os.Path] = sanitizePathOpt("petalinux-installer-path", args.petalinuxInstallerPath)
    val xilinxUnifiedPath: Option[Os.Path] = sanitizePathOpt("xilinx-unified-path", args.xilinxUnifiedPath)

    //

    val workspace = InstallerWorkspace(root)
    val context = Context.SimpleSandboxInstallationContext_v2020_1(workspace, installSireum, petalinuxInstallerPath, xilinxUnifiedPath)

    return AnvilSandbox.install(context)
  }

  // needs pathSep because File.pathSeparatorChar is java.io
  def compile(args: Cli.SireumAnvilCompileOption, pathSep: C): Z = {

    def parseMethodDescriptors(args: ISZ[String]): ISZ[String] = {
      // should be of form Path#simpleName
      assert(args.size == 1, "There should be one exactly arg to indicate the target method")
      val result = StringOps(args(0)).split((c: C) => c == c"#" || c == c".")
      assert(result.length >= 2, "MethodDescriptors should be of the form fully.qualified.Name#methodName")
      return result
    }

    assert(args.transpilerArgs.nonEmpty, "Transpiler args cannot be empty.")
    assert(args.transpilerArgs.map(Os.path).exists(path => path.exists && path.isFile), "The transpiler-args.")
    assert(args.stage.nonEmpty, "At least one stage must be specified.")

//    @pure def shouldRunStage(stage: Cli.SireumAnvilCompileStage.Type): B = {
//      val stages = ISZOps(args.stage)
//      return stages.contains(Cli.SireumAnvilCompileStage.All) || stages.contains(stage)
//    }

    val unparsed: ISZ[String] = Os.path(args.transpilerArgs.get).readLines
    val parseResult = Cli(pathSep).parseSireumSlangTranspilers(unparsed, 0)
    val optWorkspace: Option[CompileContext] = parseResult.flatMap(cliOpt => cliOpt match {
      case transpilerArgs: Cli.SireumSlangTranspilersCOption => {
        assert(transpilerArgs.output.nonEmpty, s"The transpiler does not support a default --output path when called by anvil.")

        // convert cliOpt stages to a Set of anvil stages
        val stages: Set[CompileStage.Type] = {
          val seq: ISZ[CompileStage.Type] = args.stage.flatMap(cliOpt => cliOpt match {
            case SireumAnvilCompileStage.Hls => ISZ(CompileStage.Hls)
            case SireumAnvilCompileStage.Hw => ISZ(CompileStage.Hw)
            case SireumAnvilCompileStage.Sw => ISZ(CompileStage.Sw)
            case SireumAnvilCompileStage.Os => ISZ(CompileStage.Os)
            case SireumAnvilCompileStage.All => ISZ(CompileStage.Hls, CompileStage.Hw, CompileStage.Sw, CompileStage.Os)
          })
          seqToSet(seq)
        }

        // validate the passed sandbox-path (if present)
        val sandboxRoot: Option[Os.Path] = {
          val unconfirmedPath = getPathOpt("sandbox-path", args.sandboxPath, true)
          // todo confirm that path has VagrantFile (easy sanity check for users)
          unconfirmedPath
        }

        // get the workspace's root path
        val projectRoot: Os.Path = getPath("output-dir", transpilerArgs.output, false)

        // parse and validate method descriptor
        val methodDescriptor = parseMethodDescriptors(args.args)
        val methodName = ISZOps(methodDescriptor).last

        // AnvilWorkspace
        val project: ProjectContext = SimpleProjectContext(ProjectWorkspace(projectRoot), methodName, transpilerArgs.apps)
        val sandbox: Option[SandboxContext] = sandboxRoot.map(SandboxWorkspace.apply).map(SimpleSandboxContext.apply)
        val hardware: HardwareContext = HardwareContext_Zynq_7000_SoC_ZedBoard()
        val toolchain: ToolchainContext = DefaultToolchainContext()
        val env = SimpleExecutionContext(project, sandbox, stages)
        val context: CompileContext = DefaultCompileContext(hardware, toolchain, env)
        Some(context)
      }
      case _ => None[CompileContext]()
    })

    val context: CompileContext = {
      assert(optWorkspace.nonEmpty, "Unable to parse transpiler args")
      optWorkspace.get
    }

    AnvilCompiler.compile(context)
  }

  def getPath(name: String, container: Option[String], requirePathExists: B): Os.Path = {
    assert(container.nonEmpty, s"Missing required argument: $name")
    val path = Os.path(container.get)
    if (requirePathExists) {
      assert(path.exists, s"The given path does not exist: $name")
    }
    return path
  }

  def getPathOpt(name: String, container: Option[String], requirePathExistsIfPresent: B): Option[Os.Path] = {
    val result = container.map(Os.path)
    if (result.nonEmpty && requirePathExistsIfPresent) {
      assert(result.get.exists, s"The given path does not exist: $name")
    }
    return result
  }

  def seqToSet[T](seq: ISZ[T]): Set[T] = { return Set.empty[T] ++ seq }

}





