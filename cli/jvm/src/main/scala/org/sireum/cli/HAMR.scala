// #Sireum
/*
 Copyright (c) 2017-2024, Jason Belt, Kansas State University
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package org.sireum.cli

import org.sireum._
import org.sireum.Os.Path
import org.sireum.hamr.arsit.plugin.ArsitPlugin
import org.sireum.hamr.codegen.common.containers.{SireumProyekIveOption, SireumSlangTranspilersCOption, SireumToolsSergenOption, SireumToolsSlangcheckGeneratorOption}
import org.sireum.hamr.codegen.common.plugin.Plugin
import org.sireum.hamr.codegen.common.util.{CodeGenConfig, CodeGenIpcMechanism, CodeGenPlatform, CodeGenResults, ModelUtil}
import org.sireum.hamr.ir.{Aadl, JSON => irJSON, MsgPack => irMsgPack}
import org.sireum.hamr.sysml.{FrontEnd, SysMLGrammar}
import org.sireum.hamr.sysml.stipe.{TypeHierarchy => sysmlTypeHierarchy}
import org.sireum.message._

object HAMR {

  val toolName: String = "HAMR"

  val ERROR_URL: Z = -1
  //val PARSING_FAILED: Z = -2 // used by SysMLGrammar
  val FILE_DOES_NOT_EXIST: Z = -3
  val INVALID_OPTIONS: Z = -4

  // cli interface
  def codeGen(o: Cli.SireumHamrCodegenOption, reporter: Reporter): Z = {

    val model: Aadl =
      if (o.systemRoot.nonEmpty) {
        if (o.sourcePaths.isEmpty) {
          eprintln(s"Source paths of .sysml files required")
          return -1
        } else {
          val tipeOpts = Cli.SireumHamrSysmlTipeOption(
            help = "",
            args = ISZ(),
            exclude = ISZ(),
            sourcepath = o.sourcePaths,
            parseableMessages = F
          )
          sysmlRun(tipeOpts, reporter) match {
            case Either.Left((_, models)) =>
              val cands = models.filter(p => p.symbolTable.rootSystem.classifier == o.systemRoot.get)
              cands match {
                case ISZ(modelElements) =>
                  codeGenReporter(modelElements.model, o, reporter)
                  if (!reporter.hasError && o.outputDir.nonEmpty) {
                    val airOut = Os.path(o.outputDir.get) / "air.json"
                    airOut.writeOver(org.sireum.hamr.ir.JSON.fromAadl(modelElements.model, F))
                    println(s"Wrote: ${airOut}")
                  }
                  return if (reporter.hasError) -1 else 0
                case _ =>
                  if (reporter.hasError) {
                    return -1
                  }
                  eprintln(s"Found ${cands.size} system roots matching ${o.systemRoot.get}")
                  return -1
              }
            case Either.Right(msg) => return msg
          }
        }
      } else {
        o.args.size match {
          case z"0 " => println(o.help); return 0
          case _ =>
        }

        val inputFile: Option[Path] = if (o.args.size != 1) None[Path]() else Some(Os.path(o.args(0)))

        val input: String = if (inputFile.nonEmpty && inputFile.get.exists && inputFile.get.isFile) {
          inputFile.get.read
        } else {
          val fname: String = if (inputFile.nonEmpty) s"'${inputFile.get.value}' " else ""
          eprintln(s"AIR input file ${fname}not found.  Expecting exactly 1")
          return -1
        }

        if (o.msgpack) {
          org.sireum.conversions.String.fromBase64(input) match {
            case Either.Left(u) =>
              irMsgPack.toAadl(u) match {
                case Either.Left(m) => m
                case Either.Right(m) =>
                  eprintln(s"MsgPack deserialization error at offset ${m.offset}: ${m.message}")
                  return -1
              }
            case Either.Right(m) =>
              eprintln(m)
              return -1
          }
        }
        else {
          irJSON.toAadl(input) match {
            case Either.Left(m) => m
            case Either.Right(m) =>
              eprintln(s"Json deserialization error at (${m.line}, ${m.column}): ${m.message}")
              return -1
          }
        }
      }

    codeGenReporter(model, o, reporter)

    return if (reporter.hasError) 1 else 0
  }

  // JAVA/OSATE interface
  def codeGenR(model: Aadl,
               //
               verbose: B,
               runtimeMonitoring: B,
               platform: Cli.SireumHamrCodegenHamrPlatform.Type,
               slangOutputDir: Option[String],
               slangPackageName: Option[String],
               //
               noProyekIve: B,
               noEmbedArt: B,
               devicesAsThreads: B,
               genSbtMill: B,
               //
               slangAuxCodeDir: ISZ[String],
               slangOutputCDirectory: Option[String],
               excludeComponentImpl: B,
               bitWidth: Z,
               maxStringSize: Z,
               maxArraySize: Z,
               runTranspiler: B,
               //
               camkesOutputDirectory: Option[String],
               camkesAuxCodeDirs: ISZ[String],
               aadlRootDir: Option[String],
               //
               experimentalOptions: ISZ[String],
               //
               reporter: Reporter
              ): Z = {

    return codeGenP(
      model = model,
      verbose = verbose,
      runtimeMonitoring = runtimeMonitoring,
      platform = platform,
      slangOutputDir = slangOutputDir,
      slangPackageName = slangPackageName,
      noProyekIve = noProyekIve,
      noEmbedArt = noEmbedArt,
      devicesAsThreads = devicesAsThreads,
      genSbtMill = genSbtMill,
      slangAuxCodeDir = slangAuxCodeDir,
      slangOutputCDirectory = slangOutputCDirectory,
      excludeComponentImpl = excludeComponentImpl,
      bitWidth = bitWidth,
      maxStringSize = maxStringSize,
      maxArraySize = maxArraySize,
      runTranspiler = runTranspiler,
      camkesOutputDirectory = camkesOutputDirectory,
      camkesAuxCodeDirs = camkesAuxCodeDirs,
      aadlRootDir = aadlRootDir,
      experimentalOptions = experimentalOptions,
      plugins = ArsitPlugin.gumboEnhancedPlugins,
      reporter = reporter)
  }

  /** JAVA/OSATE interface with Plugins
    *
    * @param plugins should at least include those in org.sireum.hamr.arsit.plugin.ArsitPlugin.defaultPlugins.
    *        Most plugin usage is based on a first-come, first-and-potentially-only-one-served basis
    *        so place plugins that override default behavior before the default plugins.
    *
    *        Note that MS operations like ++ may clone elements before placing them in the new sequence so updates may
    *        be performed on the clone rather than the original. You could do one of the following if you want to
    *        retrieve info from the plugin's state after codegen returns:
    *          - (preferred) fetch/use the version from the new sequence
    *          - define the plugin outside of Slang and customize its $clone method (e.g. just return 'this') or have
    *            its $owned method always return false
    */
  def codeGenP(model: Aadl,
               //
               verbose: B,
               runtimeMonitoring: B,
               platform: Cli.SireumHamrCodegenHamrPlatform.Type,
               slangOutputDir: Option[String],
               slangPackageName: Option[String],
               //
               noProyekIve: B,
               noEmbedArt: B,
               devicesAsThreads: B,
               genSbtMill: B,
               //
               slangAuxCodeDir: ISZ[String],
               slangOutputCDirectory: Option[String],
               excludeComponentImpl: B,
               bitWidth: Z,
               maxStringSize: Z,
               maxArraySize: Z,
               runTranspiler: B,
               //
               camkesOutputDirectory: Option[String],
               camkesAuxCodeDirs: ISZ[String],
               aadlRootDir: Option[String],
               //
               experimentalOptions: ISZ[String],
               //
               plugins: MSZ[Plugin],
               //
               reporter: Reporter
              ): Z = {

    val o = Cli.SireumHamrCodegenOption(
      help = "",
      args = ISZ(),
      //
      msgpack = F, // not relevant since in-memory AIR being used
      verbose = verbose,
      runtimeMonitoring = runtimeMonitoring,
      platform = platform,
      //
      outputDir = slangOutputDir,
      packageName = slangPackageName,
      noProyekIve = noProyekIve,
      noEmbedArt = noEmbedArt,
      devicesAsThreads = devicesAsThreads,
      genSbtMill = genSbtMill,
      //
      slangAuxCodeDirs = slangAuxCodeDir,
      slangOutputCDir = slangOutputCDirectory,
      excludeComponentImpl = excludeComponentImpl,
      bitWidth = bitWidth,
      maxStringSize = maxStringSize,
      maxArraySize = maxArraySize,
      runTranspiler = runTranspiler,
      //
      camkesOutputDir = camkesOutputDirectory,
      camkesAuxCodeDirs = camkesAuxCodeDirs,
      aadlRootDir = aadlRootDir,
      //
      systemRoot = None(),
      sourcePaths = ISZ(),
      //
      experimentalOptions = experimentalOptions,
      parseableMessages = F
    )

    codeGenReporterP(model, o, plugins, reporter)

    return if (reporter.hasError) 1 else 0
  }

  def codeGenReporter(model: Aadl, o: Cli.SireumHamrCodegenOption, reporter: Reporter): CodeGenResults = {
    return codeGenReporterP(model, o, ArsitPlugin.gumboEnhancedPlugins, reporter)
  }

  /**
    * @param plugins should at least include those in org.sireum.hamr.arsit.plugin.ArsitPlugin.defaultPlugins.
    *        Most plugin usage is based on a first-come, first-and-potentially-only-one-served basis
    *        so place plugins that override default behavior before the default plugins.
    *
    *        Note that MS operations like ++ may clone elements before placing them in the new sequence so updates may
    *        be performed on the clone rather than the original. You could do one of the following if you want to
    *        retrieve info from the plugin's state after codegen returns:
    *          - (preferred) fetch/use the version from the new sequence
    *          - define the plugin outside of Slang and customize its $clone method (e.g. just return 'this') or have
    *            its $owned method always return false
    */
  def codeGenReporterP(model: Aadl, o: Cli.SireumHamrCodegenOption, plugins: MSZ[Plugin], reporter: Reporter): CodeGenResults = {

    // call back function. CTranspiler prints all the messages in the
    // passed in reporter so don't use codegen's primary reporter as
    // that leads to codegen's messages being emitted multiple times
    def transpile(ao: SireumSlangTranspilersCOption, transpileReporter: Reporter): Z = {
      val sstco = Cli.SireumSlangTranspilersCOption(
        help = "",
        args = ISZ(),
        sourcepath = ao.sourcepath,
        output = ao.output,
        verbose = ao.verbose,
        projectName = ao.projectName,
        apps = ao.apps,
        unroll = ao.unroll,
        fingerprint = ao.fingerprint,
        bitWidth = ao.bitWidth,
        maxStringSize = ao.maxStringSize,
        maxArraySize = ao.maxArraySize,
        customArraySizes = ao.customArraySizes,
        customConstants = ao.customConstants,
        plugins = ao.plugins,
        exts = ao.exts,
        forwarding = ao.forwarding,
        stackSize = ao.stackSize,
        excludeBuild = ao.excludeBuild,
        libOnly = ao.libOnly,
        stableTypeId = ao.stableTypeId,
        save = ao.save,
        load = ao.load,
        cmakeIncludes = ao.cmakeIncludes,
        strictAliasing = F
      )

      return CTranspiler.run(sstco, transpileReporter)
    }

    // call back function
    def proyekIve(po: SireumProyekIveOption): Z = {
      val spivo = Cli.SireumProyekIveOption(
        help = po.help,
        args = po.args,
        force = po.force,
        edition = Cli.SireumProyekIveEdition.byName(po.edition.name).get,
        javac = po.javac,
        scalac = po.scalac,
        ignoreRuntime = po.ignoreRuntime,
        json = po.json,
        name = po.name,
        outputDirName = po.outputDirName,
        project = po.project,
        slice = po.slice,
        symlink = po.symlink,
        versions = po.versions,
        cache = po.cache,
        docs = po.docs,
        sources = po.sources,
        repositories = po.repositories,
        empty = F,
        rebuildIve = F
      )
      return Proyek.ive(spivo)
    }

    def sergen(stso: SireumToolsSergenOption, sreporter: Reporter): Z = {
      val cstso = Cli.SireumToolsSergenOption(
        help = stso.help,
        args = stso.args,
        modes = for (m <- stso.modes) yield Cli.SireumToolsSergenSerializerMode.byName(m.name).get,
        packageName = stso.packageName,
        name = stso.name,
        license = stso.license,
        outputDir = stso.outputDir
      )
      if (stso.outputDir.nonEmpty) {
        Os.path(stso.outputDir.get).mkdirAll() // sergen requires the output dir to exist
      }
      return GenTools.serGen(cstso, sreporter)
    }

    def slangCheck(stsgo: SireumToolsSlangcheckGeneratorOption, sreporter: Reporter): Z = {
      val cstsgo = Cli.SireumToolsSlangcheckGeneratorOption(
        help = stsgo.help,
        args = stsgo.args,
        license = stsgo.license,
        packageName = stsgo.packageName,
        outputDir = stsgo.outputDir,
        testDir = stsgo.testDir
      )
      if (stsgo.outputDir.nonEmpty) {
        Os.path(stsgo.outputDir.get).mkdirAll()
      }
      return SlangCheck.generate(cstsgo, sreporter)
    }

    val cgops = toCodeGenOptions(o)

    return SireumApi.hamrCodeGen(model, cgops, plugins, reporter,
      transpile _, proyekIve _, sergen _, slangCheck _)
  }

  def toCodeGenOptions(o: Cli.SireumHamrCodegenOption): CodeGenConfig = {
    return CodeGenConfig(
      writeOutResources = T,
      ipc = CodeGenIpcMechanism.SharedMemory,
      //
      verbose = o.verbose,
      runtimeMonitoring = o.runtimeMonitoring,
      platform = CodeGenPlatform.byName(o.platform.name).get,
      //
      slangOutputDir = o.outputDir,
      packageName = o.packageName,
      noProyekIve = o.noProyekIve,
      noEmbedArt = o.noEmbedArt,
      devicesAsThreads = o.devicesAsThreads,
      genSbtMill = o.genSbtMill,
      //
      slangAuxCodeDirs = o.slangAuxCodeDirs,
      slangOutputCDir = o.slangOutputCDir,
      excludeComponentImpl = o.excludeComponentImpl,
      bitWidth = o.bitWidth,
      maxStringSize = o.maxStringSize,
      maxArraySize = o.maxArraySize,
      runTranspiler = o.runTranspiler,
      //
      camkesOutputDir = o.camkesOutputDir,
      camkesAuxCodeDirs = o.camkesAuxCodeDirs,
      aadlRootDir = o.aadlRootDir,
      //
      experimentalOptions = o.experimentalOptions
    )
  }

  def sysmlTranslator(o: Cli.SireumHamrSysmlTranslatorOption): Z = {
    if (o.args.size != 1) {
      println(o.help)
      return 0
    }

    val outFile = Os.path(o.args(0))
    outFile.up.mkdirAll()

    val (content, uri): (String, Option[String]) = (o.url, o.grammar) match {
      case (Some(url), None()) =>
        val input = Os.tempFix(outFile.name, ".g")
        input.removeAll()
        val version: String = if (o.version.nonEmpty) o.version.get else ""
        val u = ops.StringOps(url).replaceAllLiterally("%version", version)
        input.downloadFrom(u)
        input.removeOnExit()
        if (!input.exists) {
          eprintln(s"Could not download from $u")
          return ERROR_URL
        }
        (input.read, Some(u))
      case (None(), Some(file)) =>
        val input = Os.path(file)
        if (!input.exists) {
          eprintln(s"Grammar file does not exist: $file")
          return FILE_DOES_NOT_EXIST
        }
        (input.read, Some(input.value))
      case _ =>
        eprintln("Provide either a URL or a grammar file")
        return INVALID_OPTIONS
    }
    return SysMLGrammar.translate(content, uri, o.keywords, outFile)
  }

  def sysmlRun(o: Cli.SireumHamrSysmlTipeOption, reporter: Reporter): Either[(sysmlTypeHierarchy, ISZ[ModelUtil.ModelElements]), Z] = {
    var sysmlFiles: ISZ[Os.Path] = ISZ()
    for (p <- o.sourcepath) {
      val cand = Os.path(p)
      if (cand.exists) {
        sysmlFiles = sysmlFiles ++ Os.Path.walk(cand, T, T, f => f.isFile && f.ext == "sysml")
      } else {
        eprintln(s"Directory does not exist: $cand")
        return Either.Right(FILE_DOES_NOT_EXIST)
      }
    }

    for (a <- o.args) {
      val cand = Os.path(a)
      if (cand.isFile && cand.ext == "sysml") {
        sysmlFiles = sysmlFiles :+ cand
      } else {
        eprintln(s"Not a valid SysML v2 file: $cand")
        return Either.Right(INVALID_OPTIONS)
      }
    }

    val inputs: ISZ[FrontEnd.Input] = for (f <- sysmlFiles) yield FrontEnd.Input(content = f.read, fileUri = Some(f.toUri))

    val ret: Either[(sysmlTypeHierarchy, ISZ[ModelUtil.ModelElements]), Z] = FrontEnd.typeCheck(0, inputs, reporter) match {
      case (Some(th), aadls) => Either.Left((th, aadls))
      case _ => Either.Right(1)
    }

    if (reporter.hasIssue) {
      println()
      if (o.parseableMessages) {
        reporter.printParseableMessages()
      } else {
        reporter.printMessages()
      }
    }

    return ret
  }

  def sysmlLogika(o: Cli.SireumHamrSysmlLogikaOption, reporter: Reporter): Z = {
    sysmlRun(Cli.SireumHamrSysmlTipeOption(o.help, o.args, o.exclude, o.sourcepath, o.parseableMessages), reporter) match {
      case Either.Left((th, models)) =>
        println("SysML v2 verification coming soon")
        return 0
      case Either.Right(ret) => return ret
    }
  }
}
