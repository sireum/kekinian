/*
 Copyright (c) 2017-2026,Robby, Kansas State University
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
package org.sireum

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import io.modelcontextprotocol.server.McpServer
import io.modelcontextprotocol.server.McpServerFeatures
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider
import io.modelcontextprotocol.spec.McpSchema
import io.modelcontextprotocol.json.McpJsonMapper

import java.io.{FileOutputStream, FilterInputStream, PrintWriter, StringWriter}
import java.util.concurrent.{CountDownLatch, TimeUnit}
import java.util.concurrent.atomic.AtomicLong

object SireumMcpServer {

  private type JString = java.lang.String
  private type JMap[K, V] = java.util.Map[K, V]

  private val mapper = new ObjectMapper()

  @volatile private var terminalProcess: Process = _
  @volatile private var mcpOutPath: JString = _

  private case class ToolInfo(commandParts: Seq[JString], opts: Seq[JsonNode], groups: Seq[JsonNode])

  val sireumCliSpec: JString = {
    val m = $internal.RC.text(Vector(".")) { (p, _) => p.last == "sireum-cli-spec.json" }
    m.head._2
  }

  def run(): Unit = {
    val stdinClosedLatch = new CountDownLatch(1)

    System.setIn(new FilterInputStream(System.in) {
      override def read(): Int = {
        try {
          val b = super.read()
          if (b == -1) stdinClosedLatch.countDown()
          b
        } catch {
          case _: java.io.IOException =>
            stdinClosedLatch.countDown()
            -1
        }
      }
      override def read(buf: Array[Byte], off: Int, len: Int): Int = {
        try {
          val n = super.read(buf, off, len)
          if (n == -1) stdinClosedLatch.countDown()
          n
        } catch {
          case _: java.io.IOException =>
            stdinClosedLatch.countDown()
            -1
        }
      }
    })

    val root = mapper.readTree(sireumCliSpec)

    val toolInfos = new java.util.LinkedHashMap[JString, ToolInfo]()
    collectTools(root, List.empty[JString], toolInfos)

    val toolSpecs = new java.util.ArrayList[McpServerFeatures.SyncToolSpecification]()

    val it = toolInfos.entrySet().iterator()
    while (it.hasNext) {
      val entry = it.next()
      val toolName: JString = entry.getKey
      val info = entry.getValue
      if (info == null) {
        halt(s"Invalid info for $toolName")
      }
      val inputSchema = buildInputSchema(info)
      val tool = McpSchema.Tool.builder()
        .name(toolName)
        .description(buildDescription(root, info))
        .inputSchema(inputSchema)
        .build()

      toolSpecs.add(McpServerFeatures.SyncToolSpecification.builder()
        .tool(tool)
        .callHandler((_, request) => handleToolCall(info, request.arguments()))
        .build())
    }

    // Add terminal toggle tool
    val terminalProps = new java.util.LinkedHashMap[JString, AnyRef]()
    val openProp = new java.util.LinkedHashMap[JString, AnyRef]()
    openProp.put("type", "boolean")
    openProp.put("description", "true to open the output terminal, false to close it")
    terminalProps.put("open", openProp)
    val terminalSchema = new McpSchema.JsonSchema("object", terminalProps,
      java.util.Collections.emptyList[JString](), java.lang.Boolean.FALSE, null, null)
    val terminalTool = McpSchema.Tool.builder()
      .name("sireum_mcp_terminal")
      .description("Toggle the MCP output terminal window that shows real-time command output")
      .inputSchema(terminalSchema)
      .build()
    toolSpecs.add(McpServerFeatures.SyncToolSpecification.builder()
      .tool(terminalTool)
      .callHandler((_, request) => handleTerminalToggle(request.arguments()))
      .build())

    val jsonMapper = McpJsonMapper.getDefault
    val transport = new StdioServerTransportProvider(jsonMapper)
    val server = McpServer.sync(transport)
      .serverInfo("sireum", SireumApi.version.value)
      .tools(toolSpecs)
      .build()

    // Monitor parent process — exit if parent dies
    val parentPid = ProcessHandle.current().parent().map[Long](_.pid()).orElse(-1L)
    val watchdog = new Thread(() => {
      while (true) {
        try { Thread.sleep(2000) } catch { case _: InterruptedException => return }
        if (parentPid > 0 && !ProcessHandle.of(parentPid).isPresent) {
          Runtime.getRuntime.halt(0)
        }
      }
    })
    watchdog.setDaemon(true)
    watchdog.start()

    // Wait for stdin EOF, then force exit
    stdinClosedLatch.await()
    Runtime.getRuntime.halt(0)
  }

  private def handleTerminalToggle(arguments: JMap[JString, AnyRef]): McpSchema.CallToolResult = {
    try {
      val open: Boolean =
        if (arguments != null && arguments.containsKey("open"))
          arguments.get("open") match {
            case b: java.lang.Boolean => b
            case _ => true
          }
        else true

      val msg: JString = if (open) {
        destroyTerminalProcess()
        launchOutputTerminal()
        if (terminalProcess != null) "Output terminal opened."
        else "Failed to open output terminal."
      } else {
        if (terminalProcess != null) {
          destroyTerminalProcess()
          "Output terminal closed."
        } else {
          "Output terminal is not running."
        }
      }

      McpSchema.CallToolResult.builder()
        .addTextContent(msg)
        .isError(java.lang.Boolean.FALSE)
        .build()
    } catch {
      case t: Throwable =>
        McpSchema.CallToolResult.builder()
          .addTextContent(s"Terminal toggle failed: ${t.getMessage}")
          .isError(java.lang.Boolean.TRUE)
          .build()
    }
  }

  private def destroyTerminalProcess(): Unit = {
    val p = terminalProcess
    terminalProcess = null
    if (p != null) {
      try { p.destroyForcibly() } catch { case _: Throwable => }
    }
    val path = mcpOutPath
    if (path != null) {
      try {
        val osName: JString = System.getProperty("os.name", "").toLowerCase
        if (osName.contains("mac")) {
          new ProcessBuilder("osascript", "-e",
            s"""tell application "Terminal"
               |  repeat with w in windows
               |    repeat with t in tabs of w
               |      if processes of t contains "tail" and (name of w contains ".claude.out" or name of t contains ".claude.out") then
               |        close w
               |        exit repeat
               |      end if
               |    end repeat
               |  end repeat
               |end tell""".stripMargin
          ).start().waitFor(5, TimeUnit.SECONDS)
        }
      } catch {
        case _: Throwable =>
      }
    }
  }

  private def launchOutputTerminal(): Unit = {
    try {
      val mcpOutFile = new java.io.File(".claude.out")
      mcpOutPath = mcpOutFile.getAbsolutePath
      // Delete stale output, then touch so tail -f doesn't fail on non-existent file
      mcpOutFile.delete()
      new java.io.FileOutputStream(mcpOutFile).close()

      val osName: JString = System.getProperty("os.name", "").toLowerCase
      val proc: Process = if (osName.contains("mac")) {
        new ProcessBuilder("osascript", "-e",
          s"""tell application "Terminal" to do script "tail -f '$mcpOutPath'" """
        ).start()
      } else if (osName.contains("win")) {
        new ProcessBuilder("cmd", "/c", "start", "powershell", "-NoExit", "-Command",
          s"""Get-Content -Path '$mcpOutPath' -Wait -Tail 50"""
        ).start()
      } else {
        // Linux: try common terminal emulators in preference order
        val script: JString =
          s"""if command -v x-terminal-emulator >/dev/null 2>&1; then
             |  x-terminal-emulator -e tail -f "$mcpOutPath"
             |elif command -v gnome-terminal >/dev/null 2>&1; then
             |  gnome-terminal -- tail -f "$mcpOutPath"
             |elif command -v konsole >/dev/null 2>&1; then
             |  konsole -e tail -f "$mcpOutPath"
             |elif command -v xterm >/dev/null 2>&1; then
             |  xterm -e tail -f "$mcpOutPath"
             |fi""".stripMargin
        new ProcessBuilder("sh", "-c", script).start()
      }
      terminalProcess = proc
    } catch {
      case _: Throwable => // Silently ignore — non-critical
    }
  }

  private def collectTools(node: JsonNode, path: List[JString],
                           result: java.util.LinkedHashMap[JString, ToolInfo]): Unit = {
    val tpe: JString = node.path("type").asText("")
    tpe match {
      case "CliOpt.Group" =>
        if (node.path("unlisted").asBoolean(false)) return
        val name: JString = node.path("name").asText("")
        val newPath: List[JString] = path :+ name
        val subs = node.path("subs")
        if (subs.isArray) {
          val iter = subs.elements()
          while (iter.hasNext) {
            collectTools(iter.next(), newPath, result)
          }
        }
      case "CliOpt.Tool" =>
        val command: JString = node.path("command").asText("")
        if (command == "server") return
        val fullPath: List[JString] = path :+ command
        val toolName: JString = fullPath.mkString("_")

        val allOpts = new java.util.ArrayList[JsonNode]()
        val opts = node.path("opts")
        if (opts.isArray) {
          val iter = opts.elements()
          while (iter.hasNext) allOpts.add(iter.next())
        }

        val groups = node.path("groups")
        val groupNodes = new java.util.ArrayList[JsonNode]()
        if (groups.isArray) {
          val gIter = groups.elements()
          while (gIter.hasNext) {
            val g = gIter.next()
            groupNodes.add(g)
            val gOpts = g.path("opts")
            if (gOpts.isArray) {
              val oIter = gOpts.elements()
              while (oIter.hasNext) allOpts.add(oIter.next())
            }
          }
        }

        import scala.jdk.CollectionConverters._
        result.put(toolName, ToolInfo(
          fullPath.drop(1),
          allOpts.asScala.toSeq,
          groupNodes.asScala.toSeq
        ))
      case _ =>
    }
  }

  private def buildDescription(root: JsonNode, info: ToolInfo): JString = {
    val node = findToolNode(root, ("sireum": JString) +: info.commandParts.toList)
    val header: JString = if (node != null) node.path("header").asText("") else ""
    val usage: JString = if (node != null) node.path("usage").asText("") else ""
    val sb = new java.lang.StringBuilder
    if (header.nonEmpty) sb.append(header)
    if (usage.nonEmpty) {
      if (sb.length() > 0) sb.append("\n\nUsage: sireum ")
      sb.append(info.commandParts.mkString(" "))
      sb.append(" ")
      sb.append(usage)
    }
    sb.toString
  }

  private def findToolNode(node: JsonNode, path: Seq[JString]): JsonNode = {
    if (path.isEmpty) return node
    val tpe: JString = node.path("type").asText("")
    tpe match {
      case "CliOpt.Group" =>
        val name: JString = node.path("name").asText("")
        if (name == path.head) {
          if (path.tail.isEmpty) return node
          val subs = node.path("subs")
          if (subs.isArray) {
            val iter = subs.elements()
            while (iter.hasNext) {
              val r = findToolNode(iter.next(), path.tail)
              if (r != null) return r
            }
          }
        }
      case "CliOpt.Tool" =>
        val cmd: JString = node.path("command").asText("")
        if (cmd == path.head && path.tail.isEmpty) return node
      case _ =>
    }
    null
  }

  private def buildInputSchema(info: ToolInfo): McpSchema.JsonSchema = {
    val properties = new java.util.LinkedHashMap[JString, AnyRef]()

    for (opt <- info.opts) {
      val longKey: JString = opt.path("longKey").asText("")
      val desc: JString = opt.path("description").asText("")
      val tpe = opt.path("tpe")
      val tpeType: JString = tpe.path("type").asText("")

      val prop = new java.util.LinkedHashMap[JString, AnyRef]()

      tpeType match {
        case "CliOpt.Type.Flag" =>
          prop.put("type", "boolean")
          prop.put("default", java.lang.Boolean.valueOf(tpe.path("default").asBoolean(false)))

        case "CliOpt.Type.Num" =>
          val hasSep = tpe.path("sep").path("type").asText("None") != "None"
          if (hasSep) {
            prop.put("type", "string")
          } else {
            prop.put("type", "integer")
            prop.put("default", java.lang.Integer.valueOf(tpe.path("default").asInt(0)))
          }

        case "CliOpt.Type.NumFlag" =>
          prop.put("type", "integer")
          prop.put("default", java.lang.Integer.valueOf(tpe.path("default").asInt(0)))

        case "CliOpt.Type.NumChoice" =>
          val hasSep = tpe.path("sep").path("type").asText("None") != "None"
          if (hasSep) {
            prop.put("type", "string")
          } else {
            prop.put("type", "integer")
            val choices = tpe.path("choices")
            if (choices.isArray) {
              val enumList = new java.util.ArrayList[AnyRef]()
              val iter = choices.elements()
              while (iter.hasNext) enumList.add(java.lang.Integer.valueOf(iter.next().asInt()))
              prop.put("enum", enumList)
            }
          }

        case "CliOpt.Type.Choice" =>
          prop.put("type", "string")
          val hasSep = tpe.path("sep").path("type").asText("None") != "None"
          if (!hasSep) {
            val elements = tpe.path("elements")
            if (elements.isArray) {
              val enumList = new java.util.ArrayList[AnyRef]()
              val iter = elements.elements()
              while (iter.hasNext) enumList.add(iter.next().asText(): JString)
              prop.put("enum", enumList)
            }
          }

        case "CliOpt.Type.Str" =>
          prop.put("type", "string")
          val hasSep = tpe.path("sep").path("type").asText("None") != "None"
          if (!hasSep) {
            val default = tpe.path("default")
            if (default.path("type").asText("None") == "Some") {
              prop.put("default", default.path("value").asText(""))
            }
          }

        case "CliOpt.Type.Path" =>
          val multi = tpe.path("multiple").asBoolean(false)
          if (multi) {
            prop.put("type", "array")
            val items = new java.util.LinkedHashMap[JString, AnyRef]()
            items.put("type", "string")
            prop.put("items", items)
          } else {
            prop.put("type", "string")
          }

        case _ =>
          prop.put("type", "string")
      }

      if (desc.nonEmpty) prop.put("description", desc)
      properties.put(longKey, prop)
    }

    // Add positional args
    val toolNode = findToolNode(mapper.readTree(sireumCliSpec),
      ("sireum": JString) +: info.commandParts.toList)
    if (toolNode != null) {
      val usage: JString = toolNode.path("usage").asText("")
      if (usage.contains("<") && usage.contains(">")) {
        val argsProp = new java.util.LinkedHashMap[JString, AnyRef]()
        argsProp.put("type", "array")
        val items = new java.util.LinkedHashMap[JString, AnyRef]()
        items.put("type", "string")
        argsProp.put("items", items)
        argsProp.put("description", "Positional arguments")
        properties.put("args", argsProp)
      }
    }

    new McpSchema.JsonSchema("object", properties, java.util.Collections.emptyList[JString](),
      java.lang.Boolean.FALSE, null, null)
  }

  private def buildCliArgs(info: ToolInfo, arguments: JMap[JString, AnyRef]): java.util.ArrayList[JString] = {
    val cliArgs = new java.util.ArrayList[JString]()
    for (part <- info.commandParts) {
      cliArgs.add(part)
    }

    val args: JMap[JString, AnyRef] =
      if (arguments != null) arguments else new java.util.HashMap[JString, AnyRef]()

    for (opt <- info.opts) {
      val longKey: JString = opt.path("longKey").asText("")
      val tpe = opt.path("tpe")
      val tpeType: JString = tpe.path("type").asText("")

      if (args.containsKey(longKey)) {
        val value = args.get(longKey)
        tpeType match {
          case "CliOpt.Type.Flag" =>
            value match {
              case b: java.lang.Boolean if b => cliArgs.add(s"--$longKey")
              case _ =>
            }

          case "CliOpt.Type.Num" | "CliOpt.Type.NumFlag" | "CliOpt.Type.NumChoice" =>
            cliArgs.add(s"--$longKey")
            cliArgs.add(value.toString)

          case "CliOpt.Type.Choice" | "CliOpt.Type.Str" =>
            val s: JString = value.toString
            if (s.nonEmpty) {
              cliArgs.add(s"--$longKey")
              cliArgs.add(s)
            }

          case "CliOpt.Type.Path" =>
            val multi = tpe.path("multiple").asBoolean(false)
            if (multi) {
              value match {
                case list: java.util.List[_] if !list.isEmpty =>
                  cliArgs.add(s"--$longKey")
                  val sb = new java.lang.StringBuilder
                  val iter = list.iterator()
                  var first = true
                  while (iter.hasNext) {
                    if (!first) sb.append(java.io.File.pathSeparator)
                    sb.append(iter.next().toString)
                    first = false
                  }
                  cliArgs.add(sb.toString)
                case _ =>
                  val s: JString = value.toString
                  if (s.nonEmpty) {
                    cliArgs.add(s"--$longKey")
                    cliArgs.add(s)
                  }
              }
            } else {
              val s: JString = value.toString
              if (s.nonEmpty) {
                cliArgs.add(s"--$longKey")
                cliArgs.add(s)
              }
            }

          case _ =>
            val s: JString = value.toString
            if (s.nonEmpty) {
              cliArgs.add(s"--$longKey")
              cliArgs.add(s)
            }
        }
      }
    }

    // Append positional args
    if (args.containsKey("args")) {
      args.get("args") match {
        case list: java.util.List[_] =>
          val iter = list.iterator()
          while (iter.hasNext) {
            cliArgs.add(iter.next().toString)
          }
        case _ =>
      }
    }

    cliArgs
  }

  // McpRep buffers Logika proof-state feedback in memory instead of writing to files.
  // It extends Sireum.Rep with a fake feedbackDirOpt (path is never used — write() is overridden)
  // and shares a ConcurrentLinkedQueue across all empty() clones created during parallel verification.
  private class McpRep(
      logPc: B, logRawPc: B, logVc: B, logDetailedInfo: B, stats: B,
      val shared: java.util.concurrent.ConcurrentLinkedQueue[JString],
      nv: AtomicLong, ns: AtomicLong, vm: AtomicLong, nm: AtomicLong)
    extends Sireum.Rep(
      Some(Os.cwd), logPc, logRawPc, logVc, logDetailedInfo, stats,
      stateFeedback = T, queryFeedback = T, coverageFeedback = F, informFeedback = T,
      nv, ns, vm, nm) {

    def this(logPc: B, logRawPc: B, logVc: B, logDetailedInfo: B, stats: B) =
      this(logPc, logRawPc, logVc, logDetailedInfo, stats,
           new java.util.concurrent.ConcurrentLinkedQueue[JString](),
           new AtomicLong(0), new AtomicLong(0), new AtomicLong(0), new AtomicLong(0))

    override def write(d: Os.Path, content: String): Unit =
      shared.add(content.value)

    override def query(pos: message.Position, title: String, isSat: B, time: Z, forceReport: B, detailElided: B, r: logika.Smt2Query.Result): Unit = {
      super.query(pos, title, isSat, time, forceReport, T, r(query = ""))
    }

    override def empty: logika.Logika.Reporter = {
      val r = new McpRep(logPc, logRawPc, logVc, logDetailedInfo, stats, shared, nv, ns, vm, nm)
      r.collectStats = stats
      r
    }
  }

  private val logikaToolPaths: scala.collection.immutable.Set[Seq[JString]] =
    scala.collection.immutable.Set(Seq("logika", "verifier"), Seq("hamr", "sysml", "logika"))

  private def handleToolCall(info: ToolInfo, arguments: JMap[JString, AnyRef]): McpSchema.CallToolResult = {
    val cliArgs = buildCliArgs(info, arguments)

    import scala.jdk.CollectionConverters._
    val sireumArgs = ISZ[String](cliArgs.asScala.toSeq.map(s => String(s)): _*)

    // Open .claude.out for streaming output in real-time
    val mcpOutStream = try {
      val fos = new FileOutputStream(".claude.out", false)
      val ps = new java.io.PrintStream(fos, true, "UTF-8")
      ps.println(s"=== sireum ${cliArgs.asScala.mkString(" ")} ===")
      ps
    } catch {
      case _: Throwable => null
    }

    // Create tee output streams that write to both a capture buffer and .claude.out
    val bout = new java.io.ByteArrayOutputStream()
    val berr = new java.io.ByteArrayOutputStream()

    def makeTee(capture: java.io.ByteArrayOutputStream): java.io.PrintStream = {
      new java.io.PrintStream(new java.io.OutputStream {
        override def write(b: Int): Unit = {
          capture.write(b)
          if (mcpOutStream != null) { mcpOutStream.write(b); mcpOutStream.flush() }
        }
        override def write(b: Array[Byte], off: Int, len: Int): Unit = {
          capture.write(b, off, len)
          if (mcpOutStream != null) { mcpOutStream.write(b, off, len); mcpOutStream.flush() }
        }
        override def flush(): Unit = {
          capture.flush()
          if (mcpOutStream != null) mcpOutStream.flush()
        }
      }, true)
    }

    val oldOut = System.out
    val oldErr = System.err
    val teeOut = makeTee(bout)
    val teeErr = makeTee(berr)
    System.setOut(teeOut)
    System.setErr(teeErr)

    var mcpRep: McpRep = null
    if (logikaToolPaths.contains(info.commandParts)) mcpRep = new McpRep(F, F, F, F, T)
    val reporter: message.Reporter = if (mcpRep != null) mcpRep else message.Reporter.create
    val exitCode: Z = try {
      Sireum.run(sireumArgs, reporter)
    } catch {
      case t: Throwable =>
        val sw = new StringWriter
        t.printStackTrace(new PrintWriter(sw))
        reporter.internalError(None(), "Sireum", sw.toString)
        Z(-1)
    } finally {
      System.out.flush()
      System.err.flush()
      System.setOut(oldOut)
      System.setErr(oldErr)
      if (mcpOutStream != null) {
        try { mcpOutStream.close() } catch { case _: Throwable => }
      }
    }

    val sb = new java.lang.StringBuilder
    val stdoutStr: JString = bout.toString("UTF-8")
    val stderrStr: JString = berr.toString("UTF-8")
    if (stdoutStr.nonEmpty) {
      sb.append(stdoutStr)
    }
    if (stderrStr.nonEmpty) {
      if (sb.length() > 0) sb.append("\n")
      sb.append(stderrStr)
    }

    val messages = reporter.messages
    if (messages.nonEmpty) {
      if (sb.length() > 0) sb.append("\n")
      var i = Z(0)
      while (i < messages.size) {
        val m = messages(i)
        sb.append("[")
        sb.append(m.level.toString)
        sb.append("] ")
        sb.append(m.text.value)
        m.posOpt match {
          case Some(pos) =>
            sb.append(" at ")
            pos.uriOpt match {
              case Some(uri) => sb.append(uri.value)
              case _ => sb.append("?")
            }
            sb.append(":")
            sb.append(pos.beginLine.toString)
            sb.append(":")
            sb.append(pos.beginColumn.toString)
          case _ =>
        }
        sb.append("\n")
        i = i + Z(1)
      }
    }

    if (sb.length() == 0) {
      if (exitCode == Z(0)) sb.append("Command completed successfully.")
      else sb.append("Command failed.")
    }

    val resultBuilder = McpSchema.CallToolResult.builder()
      .addTextContent(sb.toString)
      .isError(java.lang.Boolean.valueOf(exitCode != Z(0)))
    if (mcpRep != null) {
      import scala.jdk.CollectionConverters._
      val items = mcpRep.shared.iterator().asScala.toList
      if (items.nonEmpty) {
        val arrNode = mapper.createArrayNode()
        items.foreach { item =>
          try arrNode.add(mapper.readTree(item))
          catch { case _: Throwable => arrNode.add(item) }
        }
        resultBuilder.addTextContent(mapper.writeValueAsString(arrNode))
      }
    }
    resultBuilder.build()
  }
}
