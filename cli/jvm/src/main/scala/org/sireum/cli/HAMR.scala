// #Sireum
/*
 Copyright (c) 2017-2026,Jason Belt, Kansas State University
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
import org.sireum.LibUtil.FileOptionMap
import org.sireum.Os.Path
import org.sireum.hamr.codegen.arsit.plugin.ArsitPlugin
import org.sireum.hamr.codegen.common.CommonUtil.Store
import org.sireum.hamr.codegen.common.containers.{SireumProyekIveOption, SireumSlangTranspilersCOption, SireumToolsSergenOption, SireumToolsSlangcheckGeneratorOption}
import org.sireum.hamr.codegen.common.plugin.Plugin
import org.sireum.hamr.codegen.common.reporting.CodegenReporting
import org.sireum.hamr.sysml.{FrontEnd, LongKeys, ShortKeys}
import org.sireum.hamr.codegen.common.util.HamrCli.{CodegenHamrPlatform, CodegenLaunchCodeLanguage, CodegenNodesCodeLanguage, CodegenOption}
import org.sireum.hamr.codegen.common.util._
import org.sireum.hamr.codegen.microkit.plugins.MicrokitPlugins
import org.sireum.hamr.ir.{Aadl, JSON => irJSON, MsgPack => irMsgPack}
import org.sireum.hamr.sysml.integration.IntegrationConstraints.IntegrationConnection
import org.sireum.hamr.sysml.parser.SysMLGrammar
import org.sireum.hamr.sysml.stipe.{TypeHierarchy => sysmlTypeHierarchy}
import org.sireum.logika.NoTransitionSmt2Cache
import org.sireum.logika.Task.Claim
import org.sireum.message._

object HAMR {

  @ext object HAMR {
    def getIntegrationConstraintReporter(integrationOnly: B,
                                         integrationConnection: IntegrationConnection,
                                         reporter: Reporter): logika.Logika.Reporter = $
  }

  val toolName: String = "HAMR"

  val defaultCodegenPlugins: ISZ[Plugin] = ArsitPlugin.gumboEnhancedPlugins ++ MicrokitPlugins.defaultMicrokitPlugins

  val ERROR_URL: Z = -1
  //val PARSING_FAILED: Z = -2 // used by SysMLGrammar
  val FILE_DOES_NOT_EXIST: Z = -3
  val INVALID_OPTIONS: Z = -4
  val ILL_FORMED: Z = -5

  // cli interface
  def codeGen(o: Cli.SireumHamrCodegenOption, reporter: Reporter): Z = {
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

    val model: Aadl = if (o.msgpack) {
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

    codeGenReporter(model, o, reporter)

    return if (reporter.hasError) 1 else 0
  }

  // sysml interface
  def codeGenS(o: Cli.SireumHamrSysmlCodegenOption, reporter: Reporter): Z = {
    if (o.args.isEmpty && o.sourcepath.isEmpty) {
      println(o.help)
      return 0
    }

    if (o.sourcepath.isEmpty) {
      eprintln("The sourcepath option is required")
      return INVALID_OPTIONS
    }

    if (o.line > 0 && o.system.nonEmpty) {
      eprintln(s"Either specify the line number or the name of the system to instantiate, not both")
      return INVALID_OPTIONS
    }

    if (o.line > 0 && o.args.isEmpty) {
      eprintln("The sysml file argument is required when using the line option")
      return INVALID_OPTIONS
    }

    if (o.system.isEmpty && o.args.isEmpty) {
      eprintln("Must provide the file argument or use the system-name option")
      return INVALID_OPTIONS
    }

    if (o.system.nonEmpty && o.args.nonEmpty) {
      println("The file argument is currently ignored when the system-name is set")
    }

    var tipeOpts = Cli.SireumHamrSysmlTipeOption(
      help = "",
      args = ISZ(),
      exclude = ISZ(),
      sourcepath = o.sourcepath,
      parseableMessages = F
    )

    o.args match {
      case ISZ(cand) =>
        val p = Os.path(cand)
        if (p.exists && p.isFile && p.ext == "sysml") {
          mergeOptionsU2(o, LibUtil.mineOptions(p.read)) match {
            case Some(mo) => tipeOpts = tipeOpts(sourcepath = mo.sourcepath)
            case _ =>
          }
        } else {
          eprintln(s"$p is not a valid SysMLv2 file")
          return INVALID_OPTIONS
        }
      case x if x.size > 1 =>
        eprintln(s"Only a single file argument is allowed")
        return INVALID_OPTIONS
    }

    val results: Either[(sysmlTypeHierarchy, ISZ[ModelUtil.ModelElements], ISZ[FrontEnd.Input], Store, B), Z] = sysmlRun(tipeOpts, reporter)

    if (!reporter.hasError) {
      results match {
        case Either.Left((th, models, inputs, store, _)) =>

          var fileOptionMap: LibUtil.FileOptionMap = HashMap.empty
          for (input <- inputs) {
            fileOptionMap = fileOptionMap + input.fileUri ~> LibUtil.mineOptions(input.content)
          }

          val (modelElement, mergedOptions): (ModelUtil.ModelElements, Cli.SireumHamrSysmlCodegenOption) = {
            if (o.system.nonEmpty) {
              models.filter(p => p.symbolTable.rootSystem.classifierAsString == o.system.get) match {
                case ISZ(me) =>
                  mergeOptions(o, me.symbolTable.rootSystem.component.identifier.pos, fileOptionMap) match {
                    case Some(mo) => (me, mo)
                    case _ =>
                      return INVALID_OPTIONS
                  }
                case x =>
                  eprintln(s"Found ${x.size} system roots matching ${o.system.get}")
                  return FILE_DOES_NOT_EXIST
              }
            } else {
              val f = Os.path(o.args(0))
              if (!f.exists) {
                eprintln(s"File does not exist: ${f.value}")
                return FILE_DOES_NOT_EXIST
              }

              @strictpure def matchingUris(p: Option[Position], uri: String): B = p.nonEmpty && p.get.uriOpt.nonEmpty && p.get.uriOpt.get == uri

              @strictpure def matchingLine(p: Option[Position], line: Z): B = p.nonEmpty && p.get.beginLine <= line && line <= p.get.endLine

              val mergedOptions: Cli.SireumHamrSysmlCodegenOption = mergeOptionsU1(o, f.toUri, fileOptionMap) match {
                case Some(mo) => mo
                case _ => return INVALID_OPTIONS
              }

              val cands = models.filter(p => matchingUris(p.modelPosOpt, f.toUri))
              if (cands.size == 1) {
                (cands(0), mergedOptions)
              } else if (cands.size > 1) {
                cands.filter(p => matchingLine(p.modelPosOpt, o.line)) match {
                  case ISZ(single) =>
                    (single, mergedOptions)
                  case x =>
                    eprintln(
                      st"""There are ${cands.size} systems in ${f.value}. Please place the cursor
                          |(or set the --line option) so that it's inside the system you want to instantiate.""".render)
                    return -1
                }
              } else {
                eprintln(s"No systems contained in ${f.value}")
                return -1
              }
            }
          }

          val dummyToolReport = CodegenReporting.emptyToolReport

          val localStore: Store = CodegenReporting.addCodegenReport(CodegenReporting.KEY_TOOL_REPORT,
            dummyToolReport(commandLineArgs = SireumHamrSysmlCodegenOptionUtil.optionsToString(mergedOptions, F)), store)

          codeGenReporterS(modelElement.model, convertSysmlOptions(mergedOptions), localStore, reporter)

          if (!reporter.hasError) {
            mergedOptions.workspaceRootDir match {
              case Some(p) if Os.path(p).exists =>
                val sout = Os.path(p) / ".slang" / st"${(modelElement.model.components(0).identifier.name, "_")}.json".render
                sout.writeOver(irJSON.fromAadl(modelElement.model, F))
                println(s"Wrote: $sout")
              case _ =>
            }
            reporter.info(None(), "codegen", "Code generation successful!")
          } else {
            reporter.info(None(), "codegen", "Code generation failed")
          }

        case Either.Right(msg) => return msg
      }
    }

    if (o.parseableMessages) {
      Os.printParseableMessages(reporter)
    }

    return if (reporter.hasError) 1 else 0
  }

  // JAVA/OSATE interface
  /*
   * @deprecated this will be deleted at some point, but leaving it as old versions of the OSATE plugin
   * call this
   */
  def codeGenR(model: Aadl,
               //
               verbose: B,
               runtimeMonitoring: B,
               platform: Cli.SireumHamrCodegenHamrPlatform.Type,
               outputDir: Option[String],
               //
               slangOutputDir: Option[String],
               slangPackageName: Option[String],
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
               sel4OutputDirectory: Option[String],
               sel4AuxCodeDirs: ISZ[String],
               workspaceRootDir: Option[String],
               //
               experimentalOptions: ISZ[String],
               //
               reporter: Reporter
              ): Z = {

    val results = codeGenP(
      model = model,
      verbose = verbose,
      runtimeMonitoring = runtimeMonitoring,
      platform = platform,
      outputDir = outputDir,
      //
      slangOutputDir = slangOutputDir,
      slangPackageName = slangPackageName,
      noProyekIve = noProyekIve,
      noEmbedArt = noEmbedArt,
      devicesAsThreads = devicesAsThreads,
      genSbtMill = genSbtMill,
      //
      slangAuxCodeDir = slangAuxCodeDir,
      slangOutputCDirectory = slangOutputCDirectory,
      excludeComponentImpl = excludeComponentImpl,
      bitWidth = bitWidth,
      maxStringSize = maxStringSize,
      maxArraySize = maxArraySize,
      runTranspiler = runTranspiler,
      //
      sel4OutputDirectory = sel4OutputDirectory,
      sel4AuxCodeDirs = sel4AuxCodeDirs,
      workspaceRootDir = workspaceRootDir,
      //
      experimentalOptions = experimentalOptions,
      plugins = defaultCodegenPlugins,
      store = Map.empty,
      reporter = reporter)._1

    return if(reporter.hasError) 1 else 0
  }

  /** JAVA/OSATE interface with Plugins
    *
    * @param plugins should at least include those in defaultCodegenPlugins.
    *        Most plugin usage is based on a first-come, first-and-potentially-only-one-served basis
    *        so place plugins that override default behavior before the default plugins.
    *
    *        Plugins created in Slang are immutable and can use the passed in store to maintain state if needed.
    *        Plugins created outside of Slang can violate the immutability property (eg. they can implement @datatype
    *        but also maintain a state by customizing their $clone method (e.g. just return 'this') or by having their
    *        $owned method always return false.
    *
    * @deprecated this will be deleted at some point, but leaving it as old versions of the OSATE plugin
    * call this
    */
  def codeGenP(model: Aadl,
               //
               verbose: B,
               runtimeMonitoring: B,
               platform: Cli.SireumHamrCodegenHamrPlatform.Type,
               outputDir: Option[String],
               //
               slangOutputDir: Option[String],
               slangPackageName: Option[String],
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
               sel4OutputDirectory: Option[String],
               sel4AuxCodeDirs: ISZ[String],
               workspaceRootDir: Option[String],
               //
               experimentalOptions: ISZ[String],
               //
               plugins: ISZ[Plugin],
               store: Store,
               //
               reporter: Reporter
              ): (CodeGenResults, Store) = {

    val o = Cli.SireumHamrCodegenOption(
      help = "",
      args = ISZ(),
      //
      msgpack = F, // not relevant since in-memory AIR being used
      verbose = verbose,
      runtimeMonitoring = runtimeMonitoring,
      platform = platform,
      outputDir = outputDir,
      parseableMessages = F,
      //
      slangOutputDir = slangOutputDir,
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
      sel4OutputDir = sel4OutputDirectory,
      sel4AuxCodeDirs = sel4AuxCodeDirs,
      workspaceRootDir = workspaceRootDir,
      //
      strictAadlMode = F,
      ros2OutputWorkspaceDir = None(),
      ros2Dir = None(),
      ros2NodesLanguage = Cli.SireumHamrCodegenNodesCodeLanguage.Cpp,
      ros2LaunchLanguage = Cli.SireumHamrCodegenLaunchCodeLanguage.Xml,
      invertTopicBinding = F,
      //
      experimentalOptions = experimentalOptions
    )

    return codeGenReporterP(model, o, plugins, store, reporter)
  }

  def codeGenReporter(model: Aadl, o: Cli.SireumHamrCodegenOption, reporter: Reporter): (CodeGenResults, Store) = {
    return codeGenReporterS(model, o, Map.empty, reporter)
  }

  def codeGenReporterS(model: Aadl, o: Cli.SireumHamrCodegenOption, store: Store, reporter: Reporter): (CodeGenResults, Store) = {
    return codeGenReporterP(model, o, ArsitPlugin.gumboEnhancedPlugins ++ MicrokitPlugins.defaultMicrokitPlugins, store, reporter)
  }

  /**
    * @param plugins should at least include those in defaultCodegenPlugins.
    *        Most plugin usage is based on a first-come, first-and-potentially-only-one-served basis
    *        so place plugins that override default behavior before the default plugins.
    *
    *        Plugins created in Slang are immutable and can use the passed in store to maintain state if needed.
    *        Plugins created outside of Slang can violate the immutability property (eg. they can implement @datatype
    *        but also maintain a state by customizing their $clone method (e.g. just return 'this') or by having their
    *        $owned method always return false.
    */
  def codeGenReporterP(model: Aadl, o: Cli.SireumHamrCodegenOption, plugins: ISZ[Plugin], store: Store, reporter: Reporter): (CodeGenResults, Store) = {

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

    val (proxyHost, proxyProtocol, proxyPort, proxyUser, proxyPassword, proxyNonHosts): (Option[String], Option[String], Option[String], Option[String], Option[String], Option[String]) = {
      ExperimentalOptions.proyekIveOptions(o.experimentalOptions) match {
        case Some(opts) => Cli(Os.pathSepChar).parseSireumProyekIve(opts, 0) match {
          case Some(opt: Cli.SireumProyekIveOption) =>
            (opt.proxyHost, opt.proxyProtocol, opt.proxyPort, opt.proxyUser, opt.proxyPassword, opt.proxyNonHosts)
          case _ => (None(), None(), None(), None(), None(), None())
        }
        case _ => (None(), None(), None(), None(), None(), None())
      }
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
        rebuildIve = F,
        proxyHost = proxyHost,
        proxyProtocol = proxyProtocol,
        proxyPort = proxyPort,
        proxyUser = proxyUser,
        proxyPassword = proxyPassword,
        proxyNonHosts = proxyNonHosts
      )
      return Proyek.ive(spivo)
    }

    // call back function
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

    // call back function
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

    val localStore: Store = {
      CodegenReporting.getCodegenReport(CodegenReporting.KEY_TOOL_REPORT, store) match {
        case Some(_) => store
        case _ =>
          val dummyToolReport = CodegenReporting.emptyToolReport

          CodegenReporting.addCodegenReport(CodegenReporting.KEY_TOOL_REPORT,
            dummyToolReport(commandLineArgs =SireumHamrCodegenOptionUtil.optionsToString(o, F)), store)
      }
    }

    return SireumApi.hamrCodeGen(model, T, cgops, plugins, localStore, reporter,
      transpile _, proyekIve _, sergen _, slangCheck _)
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

  def sysmlRun(o: Cli.SireumHamrSysmlTipeOption,
               reporter: Reporter): Either[(sysmlTypeHierarchy, ISZ[ModelUtil.ModelElements], ISZ[FrontEnd.Input], Store, B), Z] = {
    if (o.args.isEmpty && o.sourcepath.isEmpty) {
      println(o.help)
      return Either.Right(0)
    }
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

    var store: Store = Map.empty
    val ret: Either[(sysmlTypeHierarchy, ISZ[ModelUtil.ModelElements], ISZ[FrontEnd.Input], Store, B), Z] = FrontEnd.typeCheck(0, inputs, store, reporter) match {
      case (Some(th), aadls, s) => Either.Left((th, aadls, inputs, s, reporter.hasError))
      case _ => Either.Right(1)
    }

    if (reporter.hasIssue) {
      println()
      if (o.parseableMessages) {
        Os.printParseableMessages(reporter)
      } else {
        reporter.printMessages()
      }
    }

    return ret
  }

  def sysmlLogika(o: Cli.SireumHamrSysmlLogikaOption, reporter: logika.Logika.Reporter): Z = {
    if (o.args.isEmpty && o.sourcepath.isEmpty) {
      println(o.help)
      return 0
    }
    val start = extension.Time.currentMillis
    var uris = HashSet.empty[String]
    var ok = T
    for (arg <- o.args) {
      val p = Os.path(arg)
      if (p.exists) {
        uris = uris + p.toUri
      } else {
        eprintln(s"$arg does not exist")
        ok = F
      }
    }
    if (!ok) {
      return FILE_DOES_NOT_EXIST
    }
    if (uris.isEmpty && o.line != 0) {
      eprintln(s"Line option cannot be provided without a file argument")
      return INVALID_OPTIONS
    }
    var connections: ISZ[(lang.tipe.TypeHierarchy, HashSMap[String, IntegrationConnection])] = ISZ()
    var fileOptionMap: LibUtil.FileOptionMap = HashMap.empty
    sysmlRun(Cli.SireumHamrSysmlTipeOption(o.help, o.args, o.exclude, o.sourcepath, o.parseableMessages), reporter) match {
      case Either.Left((_, models, inputs, store, hasError)) =>
        if (hasError) {
          return -1
        }
        var fileContentMap = HashMap.empty[String, String]
        for (input <- inputs) {
          fileContentMap = fileContentMap + input.fileUri.get ~> input.content
        }
        for (ic <- hamr.sysml.FrontEnd.getIntegrationConstraints(models, reporter)) {
          var h: HashSMap[String, IntegrationConnection] = HashSMap.empty
          for (c <- ic.connections.entries if c._2.dstConstraint.nonEmpty) {
            var add = F
            for (posOpt <- c._2.connectionReferences.values) {
              posOpt match {
                case Some(pos) => pos.uriOpt match {
                  case Some(uri) =>
                    if (fileOptionMap.get(pos.uriOpt).isEmpty) {
                      fileOptionMap = fileOptionMap + pos.uriOpt ~> LibUtil.mineOptions(fileContentMap.get(uri).get)
                    }
                    if (uris.isEmpty || (uris.contains(uri) && (o.line == 0 || (pos.beginLine <= o.line && o.line <= pos.endLine)))) {
                      add = T
                    }
                  case _ =>
                }
                case _ =>
              }
            }
            if (add) {
              h = h + c
            }
          }
          if (h.nonEmpty) {
            connections = connections :+ (ic.typeHierarchy, h)
          }
        }
      case Either.Right(ret) => return ret
    }
    if (connections.isEmpty || reporter.hasError) {
      if (o.parseableMessages) {
        Os.printParseableMessages(reporter)
      } else {
        reporter.printMessages()
      }
      return if (reporter.hasError) ILL_FORMED else 0
    }

    o.charBitWidth match {
      case z"8" =>
      case z"16" =>
      case z"32" =>
      case _ =>
        eprintln(s"C (character) bit-width has to be 8, 16, or 32 (instead of ${o.charBitWidth})")
        return Proyek.INVALID_CHAR_WIDTH
    }

    o.intBitWidth match {
      case z"0" =>
      case z"8" =>
      case z"16" =>
      case z"32" =>
      case z"64" =>
      case _ =>
        eprintln(s"Z (integer) bit-width has to be 0 (arbitrary-precision), 8, 16, 32, or 64 (instead of ${o.intBitWidth})")
        return Proyek.INVALID_INT_WIDTH
    }

    val nameExePathMap: HashMap[String, String] = SireumApi.homeOpt match {
      case Some(sireumHome) => logika.Smt2Invoke.nameExePathMap(sireumHome)
      case _ =>
        val exeOpt: Option[String] = if (Os.isWin) Some(".exe") else None()
        HashMap.empty[String, String] ++ ISZ[(String, String)](
          "cvc4" ~> st"cvc4$exeOpt".render,
          "cvc5" ~> st"cvc5$exeOpt".render,
          "z3" ~> st"z3$exeOpt".render
        )
    }

    val smt2Configs =
      logika.Smt2.parseConfigs(nameExePathMap, F, o.smt2ValidConfigs.get).left ++
        logika.Smt2.parseConfigs(nameExePathMap, T, o.smt2SatConfigs.get).left

    val fpRoundingMode: String = o.fpRounding match {
      case Cli.SireumHamrSysmlLogikaFPRoundingMode.NearestTiesToEven => "RNE"
      case Cli.SireumHamrSysmlLogikaFPRoundingMode.NearestTiesToAway => "RNA"
      case Cli.SireumHamrSysmlLogikaFPRoundingMode.TowardPositive => "RTP"
      case Cli.SireumHamrSysmlLogikaFPRoundingMode.TowardNegative => "RTN"
      case Cli.SireumHamrSysmlLogikaFPRoundingMode.TowardZero => "RTZ"
    }
    val parCores = SireumApi.parCoresOpt(o.par)
    val branchPar: org.sireum.logika.Config.BranchPar.Type = (o.branchPar, o.branchParReturn) match {
      case (T, F) => org.sireum.logika.Config.BranchPar.All
      case (T, T) => org.sireum.logika.Config.BranchPar.OnlyAllReturns
      case (F, F) => org.sireum.logika.Config.BranchPar.Disabled
      case (F, T) => org.sireum.logika.Config.BranchPar.Disabled
    }
    val spMode: org.sireum.logika.Config.StrictPureMode.Type = o.strictPureMode match {
      case Cli.SireumHamrSysmlLogikaStrictPureMode.Default => org.sireum.logika.Config.StrictPureMode.Default
      case Cli.SireumHamrSysmlLogikaStrictPureMode.Flip => org.sireum.logika.Config.StrictPureMode.Flip
      case Cli.SireumHamrSysmlLogikaStrictPureMode.Uninterpreted => org.sireum.logika.Config.StrictPureMode.Uninterpreted
    }
    val config = logika.Config(
      smt2Configs = smt2Configs,
      parCores = parCores,
      sat = o.sat,
      rlimit = o.rlimit,
      timeoutInMs = o.timeout * 1000,
      charBitWidth = o.charBitWidth,
      intBitWidth = o.intBitWidth,
      useReal = o.useReal,
      logPc = o.logPc,
      logRawPc = o.logRawPc,
      logVc = o.logVc,
      logVcDirOpt = o.logVcDir,
      dontSplitPfq = o.dontSplitFunQuant,
      splitAll = o.splitAll,
      splitIf = o.splitIf,
      splitMatch = o.splitMatch,
      splitContract = o.splitContract,
      simplifiedQuery = o.simplify,
      checkInfeasiblePatternMatch = T,
      fpRoundingMode = fpRoundingMode,
      smt2Caching = F,
      smt2Seq = o.sequential,
      branchPar = branchPar,
      atLinesFresh = o.logPcLines,
      interp = o.interprocedural,
      loopBound = o.loopBound,
      callBound = o.callBound,
      interpContracts = o.interproceduralContracts,
      elideEncoding = o.elideEncoding,
      rawInscription = o.rawInscription,
      strictPureMode = spMode,
      transitionCache = F,
      patternExhaustive = o.patternExhaustive,
      pureFun = o.pureFun,
      detailedInfo = o.logDetailedInfo,
      satTimeout = o.satTimeout,
      isAuto = T,
      background = org.sireum.logika.Config.BackgroundMode.Disabled,
      atRewrite = o.logAtRewrite,
      searchPc = o.searchPC,
      rwTrace = o.rwTrace,
      rwMax = o.rwMax,
      rwPar = o.rwPar,
      rwEvalTrace = o.rwEvalTrace,
      branchParPredNum = o.branchPredNum,
      branchParPredComp = o.branchPredComplexity
      //          undefined = o.undefined,
      //          useInt = o.useInt,
      //          branchSat = o.branchSat
    )
    val plugins = logika.Logika.defaultPlugins

    val verifyingStartTime = extension.Time.currentMillis

    var tasks = MSZ[(logika.Task, logika.Logika.Reporter)]()
    for (p <- connections) {
      for (c <- p._2.values) {
        val claim = c.claim
        val (ids, midPointPos) = c.connectionMidPoint
        println(s"Checking ${ops.StringOps(c.title).firstToLower}")

        var conf = config
        midPointPos match {
          case Some(pos) => fileOptionMap.get(pos.uriOpt) match {
            case Some(m) => m.get(logika.options.OptionsUtil.logika) match {
              case Some(options) => logika.options.OptionsUtil.toConfig(config, parCores, "file", nameExePathMap, options(0)) match {
                case Either.Left(conf2) => conf = conf2
                case Either.Right(msgs) =>
                  for (msg <- msgs) {
                    reporter.error(None(), logika.Logika.kind, msg)
                  }
              }
              case _ =>
            }
            case _ =>
          }
          case _ =>
        }
        tasks = tasks :+ (logika.Task.Claim(p._1, conf, c.title, claim, plugins),
          HAMR.getIntegrationConstraintReporter(T, c, reporter))
      }
    }

    val smt2f = (th: lang.tipe.TypeHierarchy, r: logika.Logika.Reporter) =>
      logika.Smt2Impl.create(
        config = config,
        plugins = logika.plugin.Plugin.claimPlugins(plugins),
        typeHierarchy = th,
        reporter = r)

    logika.Logika.checkTasksH(
      tasks = tasks,
      par = parCores,
      nameExePathMap = nameExePathMap,
      maxCores = Os.numOfProcessors,
      fileOptions = fileOptionMap,
      smt2f = smt2f,
      cache = NoTransitionSmt2Cache.create,
      verifyingStartTime = verifyingStartTime,
      reporter = reporter
    )

    if (o.parseableMessages) {
      Os.printParseableMessages(reporter)
    } else {
      reporter.printMessages()
    }

    if (reporter.hasError) {
      return ILL_FORMED
    } else {
      println("Integration constraints verified!")
      return 0
    }
  }

  def convertSysmlOptions(o: Cli.SireumHamrSysmlCodegenOption): Cli.SireumHamrCodegenOption = {
    return Cli.SireumHamrCodegenOption(
      help = "",
      args = ISZ(),
      msgpack = F,
      verbose = o.verbose,
      runtimeMonitoring = o.runtimeMonitoring,
      platform = Cli.SireumHamrCodegenHamrPlatform.byName(o.platform.name).get,
      outputDir = o.outputDir,
      parseableMessages = o.parseableMessages,
      slangOutputDir = o.slangOutputDir,
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
      sel4OutputDir = o.sel4OutputDir,
      sel4AuxCodeDirs = o.sel4AuxCodeDirs,
      workspaceRootDir = o.workspaceRootDir,
      //
      strictAadlMode = o.strictAadlMode,
      ros2OutputWorkspaceDir = o.ros2OutputWorkspaceDir,
      ros2Dir = o.ros2Dir,
      ros2NodesLanguage = Cli.SireumHamrCodegenNodesCodeLanguage.byName(o.ros2NodesLanguage.name).get,
      ros2LaunchLanguage = Cli.SireumHamrCodegenLaunchCodeLanguage.byName(o.ros2LaunchLanguage.name).get,
      invertTopicBinding = o.invertTopicBinding,
      //
      experimentalOptions = o.experimentalOptions)
  }

  def toCodeGenOptions(o: Cli.SireumHamrCodegenOption): CodegenOption = {
    return CodegenOption(
      help = o.help,
      args = o.args,
      msgpack = o.msgpack,
      verbose = o.verbose,
      runtimeMonitoring = o.runtimeMonitoring,
      platform = CodegenHamrPlatform.byName(o.platform.name).get,
      outputDir = o.outputDir,
      parseableMessages = o.parseableMessages,
      //
      slangOutputDir = o.slangOutputDir,
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
      sel4OutputDir = o.sel4OutputDir,
      sel4AuxCodeDirs = o.sel4AuxCodeDirs,
      workspaceRootDir = o.workspaceRootDir,
      //
      strictAadlMode = o.strictAadlMode,
      ros2OutputWorkspaceDir = o.ros2OutputWorkspaceDir,
      ros2Dir = o.ros2Dir,
      ros2NodesLanguage = CodegenNodesCodeLanguage.byName(o.ros2NodesLanguage.name).get,
      ros2LaunchLanguage = CodegenLaunchCodeLanguage.byName(o.ros2LaunchLanguage.name).get,
      invertTopicBinding = o.invertTopicBinding,
      //
      experimentalOptions = o.experimentalOptions
    )
  }

  def mergeOptions(o: Cli.SireumHamrSysmlCodegenOption, systemPos: Option[Position], fileOptionMap: FileOptionMap): Option[Cli.SireumHamrSysmlCodegenOption] = {
    if (systemPos.nonEmpty && systemPos.get.uriOpt.nonEmpty) {
      return mergeOptionsU1(o, systemPos.get.uriOpt.get, fileOptionMap)
    } else {
      return Some(o)
    }
  }

  def mergeOptionsU1(o: Cli.SireumHamrSysmlCodegenOption, fileUri: String, fileOptionMap: FileOptionMap): Option[Cli.SireumHamrSysmlCodegenOption] = {
    fileOptionMap.get(Some(fileUri)) match {
      case Some(optionMap) =>
        return mergeOptionsU2(o, optionMap)
      case _ => return Some(o)
    }
  }

  def mergeOptionsU2(o: Cli.SireumHamrSysmlCodegenOption, optionMap: LibUtil.OptionMap): Option[Cli.SireumHamrSysmlCodegenOption] = {
    if(optionMap.contains(toolName)) {
        for (options <- optionMap.get(toolName).get) {
          val str = ops.StringOps(ops.StringOps(ops.StringOps(ops.StringOps(options).replaceAllChars('␣', ' ')).replaceAllLiterally("  ", " ")).replaceAllLiterally("\t", " ")).split(c => c == ' ')

          Cli(':').parseSireumHamrSysmlCodegen(str, 0) match {
            case Some(fileOptions: Cli.SireumHamrSysmlCodegenOption) =>
              if (fileOptions.platform == o.platform) {
                mergeOptionsM(o, fileOptions, str) match {
                  case Either.Left((opt, _)) => return Some(opt)
                  case Either.Right(msg) =>
                    eprintln(msg)
                    return None()
                }
              }
            case _ =>
              // parser should have emitted errors to console
              return None()
          }
        }
    }
    return Some(o)
  }

  // Note: this method is also used by sireum forms (insert url)
  def mergeOptionsM(o: Cli.SireumHamrSysmlCodegenOption,
                    fileOptions: Cli.SireumHamrSysmlCodegenOption,
                    fileOpts: ISZ[String]): Either[(Cli.SireumHamrSysmlCodegenOption, ISZ[String]), String] = {

    assert(LongKeys.allKeys.size == 31, s"Expecting 31 long keys but found ${LongKeys.allKeys.size}") // will need to update the if/elses below to reflect added/removed options
    assert(ShortKeys.allKeys.size == 12, s"Expecting 12 short keys but found ${ShortKeys.allKeys.size}") // will need to update the if/elses below to reflect added/removed options

    var userModifiedKeys: ISZ[String] = ISZ()
    // TODO: for now the file options (if set) takes precedence over any cli options (expect line and system-name)
    var ret = o
    var i = 0
    while (i < fileOpts.size) {
      val k = fileOpts(i)
      if ((ops.StringOps(k).startsWith("--") && LongKeys.allKeys.contains(k)) ||
        (ops.StringOps(k).startsWith("-") && ShortKeys.allKeys.contains(k))) {
        if (k == LongKeys.sourcepath) {
          ret = ret(sourcepath = fileOptions.sourcepath)
          userModifiedKeys = userModifiedKeys :+ LongKeys.sourcepath
          i = i + 2
        } else if (k == LongKeys.line) {
          return Either.Right("Cannot set 'line' in file options")
        } else if (k == LongKeys.system) {
          return Either.Right("Cannot set 'system-name' in file options")
        }
        // common tool options
        else if (k == ShortKeys.verbose || k == LongKeys.verbose) {
          ret = ret(verbose = fileOptions.verbose)
          userModifiedKeys = userModifiedKeys :+ LongKeys.verbose
          i = i + 1
        } else if (k == ShortKeys.runtimeMonitoring || k == LongKeys.runtimeMonitoring) {
          ret = ret(runtimeMonitoring = fileOptions.runtimeMonitoring)
          userModifiedKeys = userModifiedKeys :+ LongKeys.runtimeMonitoring
          i = i + 1
        } else if (k == ShortKeys.platform || k == LongKeys.platform) {
          ret = ret(platform = fileOptions.platform)
          userModifiedKeys = userModifiedKeys :+ LongKeys.platform
          i = i + 2
        } else if (k == ShortKeys.outputDir || k == LongKeys.outputDir) {
          ret = ret(outputDir = fileOptions.outputDir)
          userModifiedKeys = userModifiedKeys :+ LongKeys.outputDir
          i = i + 2
        } else if (k == LongKeys.parseableMessages) {
          ret = ret(parseableMessages = fileOptions.parseableMessages)
          userModifiedKeys = userModifiedKeys :+ LongKeys.parseableMessages
          i = i + 1
        }
        // slang group options
        else if (k == LongKeys.Slang_slangOutputDir) {
          ret = ret(slangOutputDir = fileOptions.slangOutputDir)
          userModifiedKeys = userModifiedKeys :+ LongKeys.Slang_slangOutputDir
          i = i + 2
        } else if (k == ShortKeys.Slang_packageName || k == LongKeys.Slang_packageName) {
          ret = ret(packageName = fileOptions.packageName)
          userModifiedKeys = userModifiedKeys :+ LongKeys.Slang_packageName
          i = i + 2
        } else if (k == LongKeys.Slang_noProyekIve) {
          ret = ret(noProyekIve = fileOptions.noProyekIve)
          userModifiedKeys = userModifiedKeys :+ LongKeys.Slang_noProyekIve
          i = i + 1
        } else if (k == LongKeys.Slang_noEmbedArt) {
          ret = ret(noEmbedArt = fileOptions.noEmbedArt)
          userModifiedKeys = userModifiedKeys :+ LongKeys.Slang_noEmbedArt
          i = i + 1
        } else if (k == LongKeys.Slang_devicesAsThreads) {
          ret = ret(devicesAsThreads = fileOptions.devicesAsThreads)
          userModifiedKeys = userModifiedKeys :+ LongKeys.Slang_devicesAsThreads
          i = i + 1
        } else if (k == LongKeys.Slang_genSbtMill) {
          ret = ret(genSbtMill = fileOptions.genSbtMill)
          userModifiedKeys = userModifiedKeys :+ LongKeys.Slang_genSbtMill
          i = i + 1
        }
        // transpiler group options
        else if (k == LongKeys.Transpiler_slangAuxCodeDirs) {
          ret = ret(slangAuxCodeDirs = fileOptions.slangAuxCodeDirs)
          userModifiedKeys = userModifiedKeys :+ LongKeys.Transpiler_slangAuxCodeDirs
          i = i + 2
        } else if (k == LongKeys.Transpiler_slangOutputCDir) {
          ret = ret(slangOutputCDir = fileOptions.slangOutputCDir)
          userModifiedKeys = userModifiedKeys :+ LongKeys.Transpiler_slangOutputCDir
          i = i + 2
        } else if (k == ShortKeys.Transpiler_excludeComponentImpl || k == LongKeys.Transpiler_excludeComponentImpl) {
          ret = ret(excludeComponentImpl = fileOptions.excludeComponentImpl)
          userModifiedKeys = userModifiedKeys :+ LongKeys.Transpiler_excludeComponentImpl
          i = i + 1
        } else if (k == ShortKeys.Transpiler_bitWidth || k == LongKeys.Transpiler_bitWidth) {
          ret = ret(bitWidth = fileOptions.bitWidth)
          userModifiedKeys = userModifiedKeys :+ LongKeys.Transpiler_bitWidth
          i = i + 2
        } else if (k == ShortKeys.Transpiler_maxStringSize || k == LongKeys.Transpiler_maxStringSize) {
          ret = ret(maxStringSize = fileOptions.maxStringSize)
          userModifiedKeys = userModifiedKeys :+ LongKeys.Transpiler_maxStringSize
          i = i + 2
        } else if (k == ShortKeys.Transpiler_maxArraySize || k == LongKeys.Transpiler_maxArraySize) {
          ret = ret(maxArraySize = fileOptions.maxArraySize)
          userModifiedKeys = userModifiedKeys :+ LongKeys.Transpiler_maxArraySize
          i = i + 2
        } else if (k == ShortKeys.Transpiler_runTranspiler || k == LongKeys.Transpiler_runTranspiler) {
          ret = ret(runTranspiler = fileOptions.runTranspiler)
          userModifiedKeys = userModifiedKeys :+ LongKeys.Transpiler_runTranspiler
          i = i + 1
        }
        // camkes group options
        else if (k == LongKeys.CAmkES_Microkit_sel4OutputDir) {
          ret = ret(sel4OutputDir = fileOptions.sel4OutputDir)
          userModifiedKeys = userModifiedKeys :+ LongKeys.CAmkES_Microkit_sel4OutputDir
          i = i + 2
        } else if (k == LongKeys.CAmkES_Microkit_sel4AuxCodeDirs) {
          ret = ret(sel4AuxCodeDirs = fileOptions.sel4AuxCodeDirs)
          userModifiedKeys = userModifiedKeys :+ LongKeys.CAmkES_Microkit_sel4AuxCodeDirs
          i = i + 2
        } else if (k == ShortKeys.CAmkES_Microkit_workspaceRootDir || k == LongKeys.CAmkES_Microkit_workspaceRootDir) {
          ret = ret(workspaceRootDir = fileOptions.workspaceRootDir)
          userModifiedKeys = userModifiedKeys :+ LongKeys.CAmkES_Microkit_workspaceRootDir
          i = i + 2
        }
        // ros2 group options
        else if (k == LongKeys.ROS2_strictAadlMode) {
          ret = ret(strictAadlMode = fileOptions.strictAadlMode)
          userModifiedKeys = userModifiedKeys :+ LongKeys.ROS2_strictAadlMode
          i = i + 1
        } else if (k == LongKeys.ROS2_invertTopicBinding) {
          ret = ret(invertTopicBinding = fileOptions.invertTopicBinding)
          userModifiedKeys = userModifiedKeys :+ LongKeys.ROS2_invertTopicBinding
          i = i + 1
        }
        else if (k == LongKeys.ROS2_ros2OutputWorkspaceDir) {
          ret = ret(ros2OutputWorkspaceDir = fileOptions.ros2OutputWorkspaceDir)
          userModifiedKeys = userModifiedKeys :+ LongKeys.ROS2_ros2OutputWorkspaceDir
          i = i + 2
        } else if (k == LongKeys.ROS2_ros2Dir) {
          ret = ret(ros2Dir = fileOptions.ros2Dir)
          userModifiedKeys = userModifiedKeys :+ LongKeys.ROS2_ros2Dir
          i = i + 2
        } else if (k == LongKeys.ROS2_ros2NodesLanguage) {
          ret = ret(ros2NodesLanguage = fileOptions.ros2NodesLanguage)
          userModifiedKeys = userModifiedKeys :+ LongKeys.ROS2_ros2NodesLanguage
          i = i + 2
        } else if (k == LongKeys.ROS2_ros2LaunchLanguage) {
          ret = ret(ros2LaunchLanguage = fileOptions.ros2LaunchLanguage)
          userModifiedKeys = userModifiedKeys :+ LongKeys.ROS2_ros2LaunchLanguage
          i = i + 2
        }
        // experimental group options
        else if (k == ShortKeys.Experimental_experimentalOptions || k == LongKeys.Experimental_experimentalOptions) {
          ret = ret(experimentalOptions = fileOptions.experimentalOptions)
          userModifiedKeys = userModifiedKeys :+ LongKeys.Experimental_experimentalOptions
          i = i + 2
        } else {
          return Either.Right(s"'$k' is not a valid option key")
        }
      } else {
        return Either.Right(s"Invalid option '${fileOpts(i)}'. File options can only set codegen options, not arguments")
      }
    }
    return Either.Left((ret, userModifiedKeys))
  }

  def sysmlConfig(o: Cli.SireumHamrSysmlConfigOption): Z = {
    if (o.args.size != 1) {
      println(o.help)
      return 0
    }
    val theme: String = o.theme match {
      case Cli.SireumHamrSysmlConfigTheme.Dark => "dark"
      case Cli.SireumHamrSysmlConfigTheme.Light => "light"
    }
    val java: Os.Path = SireumApi.javaHomeOpt.get / "bin" / (if (Os.isWin) "java.exe" else "java")
    val cmds = ISZ[String](java.string, "-cp",
      s"${SireumApi.homeOpt.get / "bin" / "sireum.jar"}${Os.pathSep}${SireumApi.homeOpt.get / "lib" / "forms.jar"}",
      "org.sireum.forms.FormsApp", "hamr") ++ o.args :+ theme
    Os.proc(cmds).runCheck()
    return 0
  }
}

// BEGIN SireumHamrSysmlCodegenOption

// Autogenerated via /Users/belt/devel/sireum/kekinian/hamr/codegen/bin/build.cmd --regen-cli

object SireumHamrSysmlCodegenOptionUtil {
  def optionsToString(o: Cli.SireumHamrSysmlCodegenOption, includeDefaults: B): String = {
    val ops: ISZ[ST] = for(e <- optionsToStringH(o, includeDefaults)) yield st"${e._1}${if (e._2.isEmpty) "" else s" ${e._2.get}"}"
    return st"${(ops, " ")}".render
  }

  def optionsToStringH(o : Cli.SireumHamrSysmlCodegenOption, includeDefaults: B): ISZ[(String, Option[String])] = {
    var ret: ISZ[(String, Option[String])] = ISZ()
    if (o.sourcepath.nonEmpty) {
      ret = ret :+ ("--sourcepath", Some(st"${(o.sourcepath, Os.pathSep)}".render))
    }
    if (includeDefaults || o.line != 0) {
      ret = ret :+ ("--line", Some(o.line.string))
    }
    if (o.system.nonEmpty) {
      ret = ret :+ ("--system-name", Some(o.system.get))
    }
    if (o.verbose) {
      ret = ret :+ ("--verbose", None())
    }
    if (o.runtimeMonitoring) {
      ret = ret :+ ("--runtime-monitoring", None())
    }
    if (includeDefaults || o.platform.name != "JVM") {
      ret = ret :+ ("--platform", Some(o.platform.name))
                      }
    if (o.outputDir.nonEmpty) {
      ret = ret :+ ("--output-dir", Some(st"${(o.outputDir, Os.pathSep)}".render))
    }
    if (o.parseableMessages) {
      ret = ret :+ ("--parseable-messages", None())
    }
    if (o.slangOutputDir.nonEmpty) {
      ret = ret :+ ("--slang-output-dir", Some(st"${(o.slangOutputDir, Os.pathSep)}".render))
    }
    if (includeDefaults || o.packageName.nonEmpty) {
      ret = ret :+ ("--package-name", Some(o.packageName.get))
    }
    if (o.noProyekIve) {
      ret = ret :+ ("--no-proyek-ive", None())
    }
    if (o.noEmbedArt) {
      ret = ret :+ ("--no-embed-art", None())
    }
    if (o.devicesAsThreads) {
      ret = ret :+ ("--devices-as-thread", None())
    }
    if (o.genSbtMill) {
      ret = ret :+ ("--sbt-mill", None())
    }
    if (o.slangAuxCodeDirs.nonEmpty) {
      ret = ret :+ ("--aux-code-dirs", Some(st"${(o.slangAuxCodeDirs, Os.pathSep)}".render))
    }
    if (o.slangOutputCDir.nonEmpty) {
      ret = ret :+ ("--output-c-dir", Some(st"${(o.slangOutputCDir, Os.pathSep)}".render))
    }
    if (o.excludeComponentImpl) {
      ret = ret :+ ("--exclude-component-impl", None())
    }
    if (includeDefaults || o.bitWidth != 64) {
      ret = ret :+ ("--bit-width", Some(o.bitWidth.string))
    }
    if (includeDefaults || o.maxStringSize != 100) {
      ret = ret :+ ("--max-string-size", Some(o.maxStringSize.string))
    }
    if (includeDefaults || o.maxArraySize != 100) {
      ret = ret :+ ("--max-array-size", Some(o.maxArraySize.string))
    }
    if (o.runTranspiler) {
      ret = ret :+ ("--run-transpiler", None())
    }
    if (o.sel4OutputDir.nonEmpty) {
      ret = ret :+ ("--sel4-output-dir", Some(st"${(o.sel4OutputDir, Os.pathSep)}".render))
    }
    if (o.sel4AuxCodeDirs.nonEmpty) {
      ret = ret :+ ("--sel4-aux-code-dirs", Some(st"${(o.sel4AuxCodeDirs, Os.pathSep)}".render))
    }
    if (o.workspaceRootDir.nonEmpty) {
      ret = ret :+ ("--workspace-root-dir", Some(st"${(o.workspaceRootDir, Os.pathSep)}".render))
    }
    if (o.strictAadlMode) {
      ret = ret :+ ("--strict-aadl-mode", None())
    }
    if (o.ros2OutputWorkspaceDir.nonEmpty) {
      ret = ret :+ ("--ros2-output-workspace-dir", Some(st"${(o.ros2OutputWorkspaceDir, Os.pathSep)}".render))
    }
    if (o.ros2Dir.nonEmpty) {
      ret = ret :+ ("--ros2-dir", Some(st"${(o.ros2Dir, Os.pathSep)}".render))
    }
    if (includeDefaults || o.ros2NodesLanguage.name != "Python") {
      ret = ret :+ ("--ros2-nodes-language", Some(o.ros2NodesLanguage.name))
                      }
    if (includeDefaults || o.ros2LaunchLanguage.name != "Python") {
      ret = ret :+ ("--ros2-launch-language", Some(o.ros2LaunchLanguage.name))
                      }
    if (o.invertTopicBinding) {
      ret = ret :+ ("--invert-topic-binding", None())
    }
    if (o.experimentalOptions.nonEmpty) {
      ret = ret :+ ("--experimental-options", Some(st"${(o.experimentalOptions, ";")}".render))
    }
    return ret
  }
}
// END SireumHamrSysmlCodegenOption

// BEGIN SireumHamrCodegenOption

// Autogenerated via /Users/belt/devel/sireum/kekinian/hamr/codegen/bin/build.cmd --regen-cli

object SireumHamrCodegenOptionUtil {
  def optionsToString(o: Cli.SireumHamrCodegenOption, includeDefaults: B): String = {
    val ops: ISZ[ST] = for(e <- optionsToStringH(o, includeDefaults)) yield st"${e._1}${if (e._2.isEmpty) "" else s" ${e._2.get}"}"
    return st"${(ops, " ")}".render
  }

  def optionsToStringH(o : Cli.SireumHamrCodegenOption, includeDefaults: B): ISZ[(String, Option[String])] = {
    var ret: ISZ[(String, Option[String])] = ISZ()
    if (o.msgpack) {
      ret = ret :+ ("--msgpack", None())
    }
    if (o.verbose) {
      ret = ret :+ ("--verbose", None())
    }
    if (o.runtimeMonitoring) {
      ret = ret :+ ("--runtime-monitoring", None())
    }
    if (includeDefaults || o.platform.name != "JVM") {
      ret = ret :+ ("--platform", Some(o.platform.name))
                      }
    if (o.outputDir.nonEmpty) {
      ret = ret :+ ("--output-dir", Some(st"${(o.outputDir, Os.pathSep)}".render))
    }
    if (o.parseableMessages) {
      ret = ret :+ ("--parseable-messages", None())
    }
    if (o.slangOutputDir.nonEmpty) {
      ret = ret :+ ("--slang-output-dir", Some(st"${(o.slangOutputDir, Os.pathSep)}".render))
    }
    if (includeDefaults || o.packageName.nonEmpty) {
      ret = ret :+ ("--package-name", Some(o.packageName.get))
    }
    if (o.noProyekIve) {
      ret = ret :+ ("--no-proyek-ive", None())
    }
    if (o.noEmbedArt) {
      ret = ret :+ ("--no-embed-art", None())
    }
    if (o.devicesAsThreads) {
      ret = ret :+ ("--devices-as-thread", None())
    }
    if (o.genSbtMill) {
      ret = ret :+ ("--sbt-mill", None())
    }
    if (o.slangAuxCodeDirs.nonEmpty) {
      ret = ret :+ ("--aux-code-dirs", Some(st"${(o.slangAuxCodeDirs, Os.pathSep)}".render))
    }
    if (o.slangOutputCDir.nonEmpty) {
      ret = ret :+ ("--output-c-dir", Some(st"${(o.slangOutputCDir, Os.pathSep)}".render))
    }
    if (o.excludeComponentImpl) {
      ret = ret :+ ("--exclude-component-impl", None())
    }
    if (includeDefaults || o.bitWidth != 64) {
      ret = ret :+ ("--bit-width", Some(o.bitWidth.string))
    }
    if (includeDefaults || o.maxStringSize != 100) {
      ret = ret :+ ("--max-string-size", Some(o.maxStringSize.string))
    }
    if (includeDefaults || o.maxArraySize != 100) {
      ret = ret :+ ("--max-array-size", Some(o.maxArraySize.string))
    }
    if (o.runTranspiler) {
      ret = ret :+ ("--run-transpiler", None())
    }
    if (o.sel4OutputDir.nonEmpty) {
      ret = ret :+ ("--sel4-output-dir", Some(st"${(o.sel4OutputDir, Os.pathSep)}".render))
    }
    if (o.sel4AuxCodeDirs.nonEmpty) {
      ret = ret :+ ("--sel4-aux-code-dirs", Some(st"${(o.sel4AuxCodeDirs, Os.pathSep)}".render))
    }
    if (o.workspaceRootDir.nonEmpty) {
      ret = ret :+ ("--workspace-root-dir", Some(st"${(o.workspaceRootDir, Os.pathSep)}".render))
    }
    if (o.strictAadlMode) {
      ret = ret :+ ("--strict-aadl-mode", None())
    }
    if (o.ros2OutputWorkspaceDir.nonEmpty) {
      ret = ret :+ ("--ros2-output-workspace-dir", Some(st"${(o.ros2OutputWorkspaceDir, Os.pathSep)}".render))
    }
    if (o.ros2Dir.nonEmpty) {
      ret = ret :+ ("--ros2-dir", Some(st"${(o.ros2Dir, Os.pathSep)}".render))
    }
    if (includeDefaults || o.ros2NodesLanguage.name != "Python") {
      ret = ret :+ ("--ros2-nodes-language", Some(o.ros2NodesLanguage.name))
                      }
    if (includeDefaults || o.ros2LaunchLanguage.name != "Python") {
      ret = ret :+ ("--ros2-launch-language", Some(o.ros2LaunchLanguage.name))
                      }
    if (o.invertTopicBinding) {
      ret = ret :+ ("--invert-topic-binding", None())
    }
    if (o.experimentalOptions.nonEmpty) {
      ret = ret :+ ("--experimental-options", Some(st"${(o.experimentalOptions, ";")}".render))
    }
    return ret
  }
}
// END SireumHamrCodegenOption


