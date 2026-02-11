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

import java.io.{FilterInputStream, PrintWriter, StringWriter}
import java.util.concurrent.CountDownLatch

object SireumMcpServer {

  private type JString = java.lang.String
  private type JMap[K, V] = java.util.Map[K, V]

  private val mapper = new ObjectMapper()

  private case class ToolInfo(commandParts: Seq[JString], opts: Seq[JsonNode], groups: Seq[JsonNode])

  val sireumCliSpec: JString = {
    val m = $internal.RC.text(Vector(".")) { (p, _) => p.last == "sireum-cli-spec.json" }
    m.head._2
  }

  def run(): Unit = {
    val stdinClosedLatch = new CountDownLatch(1)
    val originalIn = System.in
    System.setIn(new FilterInputStream(originalIn) {
      override def read(): Int = {
        val b = super.read()
        if (b == -1) stdinClosedLatch.countDown()
        b
      }
      override def read(buf: Array[Byte], off: Int, len: Int): Int = {
        val n = super.read(buf, off, len)
        if (n == -1) stdinClosedLatch.countDown()
        n
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

    val jsonMapper = McpJsonMapper.getDefault
    val transport = new StdioServerTransportProvider(jsonMapper)
    val server = McpServer.sync(transport)
      .serverInfo("sireum", SireumApi.version.value)
      .tools(toolSpecs)
      .build()

    stdinClosedLatch.await()
    server.close()
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

  private def handleToolCall(info: ToolInfo, arguments: JMap[JString, AnyRef]): McpSchema.CallToolResult = {
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

    import scala.jdk.CollectionConverters._
    val sireumArgs = ISZ[String](cliArgs.asScala.toSeq.map(s => String(s)): _*)

    val reporter = message.Reporter.create
    val (exitCode, stdout, stderr) = try {
      SireumApi.runWithReporter(sireumArgs, reporter)
    } catch {
      case t: Throwable =>
        val sw = new StringWriter
        t.printStackTrace(new PrintWriter(sw))
        (-1, String(""), String(sw.toString))
    }

    val sb = new java.lang.StringBuilder
    val stdoutStr: JString = stdout.value
    val stderrStr: JString = stderr.value
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

    McpSchema.CallToolResult.builder()
      .addTextContent(sb.toString)
      .isError(java.lang.Boolean.valueOf(exitCode != Z(0)))
      .build()
  }
}
