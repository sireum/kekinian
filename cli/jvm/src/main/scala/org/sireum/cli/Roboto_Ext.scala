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
package org.sireum.cli

import org.sireum._
import org.sireum.roboto._

import java.awt.{Graphics2D, GraphicsEnvironment, Robot, Toolkit}
import java.awt.datatransfer.StringSelection
import java.awt.event.{InputEvent, KeyEvent}
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

object Roboto_Ext {

  private val robot: Robot = {
    System.setProperty("apple.awt.UIElement", "true")
    val r = new Robot()
    r.setAutoDelay(10)
    r
  }

  def capture(output: Os.Path): Z = {
    println("Capturing screen...")
    for (s <- 5 to 1 by -1) {
      println(s"  Capturing in $s...")
      showOverlay(String(s"Capturing in $s..."), 900)
    }
    val screenSize = Toolkit.getDefaultToolkit.getScreenSize
    val screen = robot.createScreenCapture(
      new java.awt.Rectangle(0, 0, screenSize.width, screenSize.height)
    )
    ImageIO.write(screen, "png", new java.io.File(output.value.value))
    println(s"Captured ${screen.getWidth}x${screen.getHeight} -> $output")
    showOverlay(String(s"Captured -> ${output.name}"), 2000)
    return 0
  }

  def parseMarkdown(path: Os.Path, args: ISZ[String], reporter: message.Reporter): Option[Script] = {
    val kind = "Roboto"
    val fContent = path.read.value
    val uriOpt = org.sireum.Option.some(message.DocInfo.create(org.sireum.Option.some(path.toUri), fContent))
    val dir = new java.io.File(path.value.value).getParentFile

    import org.commonmark.node._
    import org.commonmark.ext.front.matter._

    val parser = org.commonmark.parser.Parser.builder()
      .extensions(java.util.List.of(YamlFrontMatterExtension.create)).build()
    val doc = parser.parse(fContent).asInstanceOf[Document]

    def getPosOpt(node: Node): org.sireum.Option[message.Position] = {
      var n = node
      while (n != null && n.getSourceSpans.isEmpty) {
        val prev = n.getPrevious
        if (prev == null) n = n.getParent
        else n = prev
      }
      if (n == null) return org.sireum.None()
      val li = n.getSourceSpans
      val offset = li.get(0).getInputIndex
      val last = li.get(li.size - 1)
      val length = last.getInputIndex + last.getLength - offset + 1
      return org.sireum.Some(message.PosInfo(uriOpt.get,
        conversions.Z.toU64(offset) << org.sireum.U64(32) + conversions.Z.toU64(length)))
    }

    def getTexts(node: Node): Vector[Predef.String] = {
      var r = Vector[Predef.String]()
      def rec(n: Node): Unit = {
        var child = n.getFirstChild
        while (child != null) {
          child match {
            case child: Text => r = r :+ child.getLiteral.trim
            case child: Code => r = r :+ child.getLiteral.trim
            case _ => rec(child)
          }
          child = child.getNext
        }
      }
      rec(node)
      r
    }

    def splitKeyValuePair(line: Predef.String): Array[Predef.String] = {
      val i = line.indexOf(':')
      if (i > 0) Array(line.substring(0, i).trim, line.substring(i + 1).trim)
      else Array()
    }

    // Parse YAML frontmatter
    var scriptName = "Roboto"
    var defaultCharDelayMs: Z = 50
    var defaultActionDelayMs: Z = 2000
    var vars = scala.collection.mutable.LinkedHashMap[Predef.String, Predef.String]()
    var substs = scala.collection.mutable.LinkedHashMap[Predef.String, Predef.String]()
    val osName = System.getProperty("os.name").toLowerCase
    val osKey = if (osName.contains("mac")) "Mac"
      else if (osName.contains("win")) "Win"
      else if (System.getProperty("os.arch").contains("aarch64")) "LinuxArm"
      else "Linux"

    var child: Node = doc.getFirstChild
    child match {
      case yaml: YamlFrontMatterBlock =>
        var yamlChild = yaml.getFirstChild
        while (yamlChild != null) {
          yamlChild match {
            case yamlChild: YamlFrontMatterNode =>
              yamlChild.getKey match {
                case "name" if yamlChild.getValues.size == 1 =>
                  scriptName = yamlChild.getValues.get(0)
                case "defaultCharDelayMs" if yamlChild.getValues.size == 1 =>
                  Z(yamlChild.getValues.get(0)) match {
                    case org.sireum.Some(n) => defaultCharDelayMs = n
                    case _ => reporter.error(getPosOpt(yamlChild), kind,
                      s"Could not parse defaultCharDelayMs: ${yamlChild.getValues.get(0)}")
                  }
                case "defaultActionDelayMs" if yamlChild.getValues.size == 1 =>
                  Z(yamlChild.getValues.get(0)) match {
                    case org.sireum.Some(n) => defaultActionDelayMs = n
                    case _ => reporter.error(getPosOpt(yamlChild), kind,
                      s"Could not parse defaultActionDelayMs: ${yamlChild.getValues.get(0)}")
                  }
                case key if key == "vars" || key.startsWith("vars") =>
                  val varKey = key.substring("vars".length)
                  val isBase = varKey.isEmpty
                  val applies = isBase || varKey == osKey
                  if (applies) {
                    for (v <- yamlChild.getValues.toArray) {
                      splitKeyValuePair(v.toString) match {
                        case Array(k, value) => vars(k) = value
                        case _ => reporter.error(getPosOpt(yamlChild), kind,
                          s"Expecting <key>: <value> pair but found: $v")
                      }
                    }
                  }
                case "subst" =>
                  for (v <- yamlChild.getValues.toArray) {
                    splitKeyValuePair(v.toString) match {
                      case Array(k, value) => substs(k) = value
                      case _ => reporter.error(getPosOpt(yamlChild), kind,
                        s"Expecting <key>: <value> pair but found: $v")
                    }
                  }
                case key =>
                  reporter.error(getPosOpt(yamlChild), kind,
                    s"Unknown frontmatter key: $key")
              }
            case _ =>
          }
          yamlChild = yamlChild.getNext
        }
        child = child.getNext
      case _ =>
    }

    // Override vars with CLI args (key=value pairs)
    for (arg <- args.elements) {
      val a = arg.value
      val eqIdx = a.indexOf('=')
      if (eqIdx > 0) {
        vars(a.substring(0, eqIdx).trim) = a.substring(eqIdx + 1).trim
      }
    }

    // Expand environment variables in var values; $$ escapes to literal $
    val envPattern = "\\$([A-Za-z_][A-Za-z0-9_]*)".r
    val dollarEsc = "\u0000"
    for ((k, v) <- vars) {
      val v2 = v.replace("$$", dollarEsc)
      vars(k) = envPattern.replaceAllIn(v2, m => {
        val envName = m.group(1)
        // don't replace if it's a roboto var reference like $var$
        if (v2.contains(s"$$${envName}$$")) java.util.regex.Matcher.quoteReplacement(m.matched)
        else {
          val envVal = System.getenv(envName)
          if (envVal != null) java.util.regex.Matcher.quoteReplacement(envVal) else java.util.regex.Matcher.quoteReplacement(m.matched)
        }
      }).replace(dollarEsc, "$")
    }

    def substVars(text: Predef.String): Predef.String = {
      var r = text
      for ((k, v) <- vars) {
        r = r.replace(s"$$$k$$", v)
      }
      r
    }

    def substSpeak(text: Predef.String): Predef.String = {
      var r = text
      for ((k, v) <- substs) {
        r = r.replace(s"$$$k$$", v)
      }
      r
    }

    // Parse actions
    var actions = ISZ[Action]()

    def parseCommand(actionName: Predef.String, node: Node, text: Predef.String): org.sireum.Option[Command] = {
      val raw = substVars(text)
      // Parse: command[(options)]: arguments
      val cmdPattern = "^(\\w+)(?:\\(([^)]*)\\))?(?::\\s*(.*))?$".r
      raw match {
        case cmdPattern(cmd, optStr, argStr) =>
          val opts = if (optStr != null) optStr.split(",").map(_.trim).filter(_.nonEmpty) else Array[Predef.String]()
          val arg = if (argStr != null) argStr.trim else ""

          def parseZ(s: Predef.String, name: Predef.String): org.sireum.Option[Z] = {
            Z(s) match {
              case r@org.sireum.Some(_) => r
              case _ =>
                reporter.error(getPosOpt(node), kind,
                  s"Invalid $name value in '$actionName': $s")
                org.sireum.None()
            }
          }

          def parseF64(s: Predef.String, name: Predef.String): org.sireum.Option[F64] = {
            F64(s) match {
              case r@org.sireum.Some(_) => r
              case _ =>
                reporter.error(getPosOpt(node), kind,
                  s"Invalid $name value in '$actionName': $s")
                org.sireum.None()
            }
          }

          def parseModifiers(strs: Array[Predef.String]): ISZ[Modifier.Type] = {
            var mods = ISZ[Modifier.Type]()
            for (s <- strs) {
              s match {
                case "Ctrl" | "ctrl" => mods = mods :+ Modifier.Ctrl
                case "Shift" | "shift" => mods = mods :+ Modifier.Shift
                case "Alt" | "alt" => mods = mods :+ Modifier.Alt
                case "Meta" | "meta" | "Cmd" | "cmd" => mods = mods :+ Modifier.Meta
                case _ =>
                  reporter.error(getPosOpt(node), kind,
                    s"Unknown modifier in '$actionName': $s")
              }
            }
            mods
          }

          def parseKey(s: Predef.String): org.sireum.Option[Key.Type] = {
            s match {
              case "Enter" | "enter" => org.sireum.Some(Key.Enter)
              case "Escape" | "escape" | "Esc" | "esc" => org.sireum.Some(Key.Escape)
              case "Tab" | "tab" => org.sireum.Some(Key.Tab)
              case "Space" | "space" => org.sireum.Some(Key.Space)
              case "Backspace" | "backspace" => org.sireum.Some(Key.Backspace)
              case "Delete" | "delete" => org.sireum.Some(Key.Delete)
              case "Up" | "up" => org.sireum.Some(Key.Up)
              case "Down" | "down" => org.sireum.Some(Key.Down)
              case "Left" | "left" => org.sireum.Some(Key.Left)
              case "Right" | "right" => org.sireum.Some(Key.Right)
              case "Home" | "home" => org.sireum.Some(Key.Home)
              case "End" | "end" => org.sireum.Some(Key.End)
              case "PageUp" | "pageUp" | "pageup" => org.sireum.Some(Key.PageUp)
              case "PageDown" | "pageDown" | "pagedown" => org.sireum.Some(Key.PageDown)
              case "F1" => org.sireum.Some(Key.F1)
              case "F2" => org.sireum.Some(Key.F2)
              case "F3" => org.sireum.Some(Key.F3)
              case "F4" => org.sireum.Some(Key.F4)
              case "F5" => org.sireum.Some(Key.F5)
              case "F6" => org.sireum.Some(Key.F6)
              case "F7" => org.sireum.Some(Key.F7)
              case "F8" => org.sireum.Some(Key.F8)
              case "F9" => org.sireum.Some(Key.F9)
              case "F10" => org.sireum.Some(Key.F10)
              case "F11" => org.sireum.Some(Key.F11)
              case "F12" => org.sireum.Some(Key.F12)
              case _ =>
                reporter.error(getPosOpt(node), kind,
                  s"Unknown key in '$actionName': $s")
                org.sireum.None()
            }
          }

          def parseMouseButton(s: Predef.String): MouseButton.Type = s match {
            case "Right" | "right" => MouseButton.Right
            case "Middle" | "middle" => MouseButton.Middle
            case _ => MouseButton.Left
          }

          cmd.toLowerCase match {
            case "typetext" =>
              val delay: Z = if (opts.nonEmpty) parseZ(opts(0), "delayMs").getOrElse(-1) else -1
              org.sireum.Some(TypeText(arg, delay))

            case "presskey" =>
              parseKey(arg) match {
                case org.sireum.Some(key) =>
                  org.sireum.Some(PressKey(key, parseModifiers(opts)))
                case _ => org.sireum.None()
              }

            case "typechar" =>
              if (arg.length == 1) {
                org.sireum.Some(TypeChar(org.sireum.C(arg.charAt(0)), parseModifiers(opts)))
              } else {
                reporter.error(getPosOpt(node), kind,
                  s"typeChar expects a single character in '$actionName': $arg")
                org.sireum.None()
              }

            case "mousemove" =>
              val parts = arg.split(",").map(_.trim)
              if (parts.length == 2) {
                (parseZ(parts(0), "x"), parseZ(parts(1), "y")) match {
                  case (org.sireum.Some(x), org.sireum.Some(y)) =>
                    org.sireum.Some(MouseMove(x, y))
                  case _ => org.sireum.None()
                }
              } else {
                reporter.error(getPosOpt(node), kind,
                  s"mouseMove expects x,y in '$actionName': $arg")
                org.sireum.None()
              }

            case "mouseclick" =>
              val parts = arg.split(",").map(_.trim)
              if (parts.length == 2) {
                val button = if (opts.nonEmpty) parseMouseButton(opts(0)) else MouseButton.Left
                (parseZ(parts(0), "x"), parseZ(parts(1), "y")) match {
                  case (org.sireum.Some(x), org.sireum.Some(y)) =>
                    org.sireum.Some(MouseClick(button, x, y))
                  case _ => org.sireum.None()
                }
              } else {
                reporter.error(getPosOpt(node), kind,
                  s"mouseClick expects x,y in '$actionName': $arg")
                org.sireum.None()
              }

            case "mousedoubleclick" =>
              val parts = arg.split(",").map(_.trim)
              if (parts.length == 2) {
                val button = if (opts.nonEmpty) parseMouseButton(opts(0)) else MouseButton.Left
                (parseZ(parts(0), "x"), parseZ(parts(1), "y")) match {
                  case (org.sireum.Some(x), org.sireum.Some(y)) =>
                    org.sireum.Some(MouseDoubleClick(button, x, y))
                  case _ => org.sireum.None()
                }
              } else {
                reporter.error(getPosOpt(node), kind,
                  s"mouseDoubleClick expects x,y in '$actionName': $arg")
                org.sireum.None()
              }

            case "mousedrag" =>
              val parts = arg.split(",").map(_.trim)
              if (parts.length == 4) {
                (parseZ(parts(0), "fromX"), parseZ(parts(1), "fromY"),
                  parseZ(parts(2), "toX"), parseZ(parts(3), "toY")) match {
                  case (org.sireum.Some(fx), org.sireum.Some(fy),
                  org.sireum.Some(tx), org.sireum.Some(ty)) =>
                    org.sireum.Some(MouseDrag(fx, fy, tx, ty))
                  case _ => org.sireum.None()
                }
              } else {
                reporter.error(getPosOpt(node), kind,
                  s"mouseDrag expects fromX,fromY,toX,toY in '$actionName': $arg")
                org.sireum.None()
              }

            case "clickimage" =>
              val similarity: F64 = if (opts.nonEmpty) parseF64(opts(0), "similarity").getOrElse(org.sireum.F64(0.9)) else org.sireum.F64(0.9)
              val xOffset: Z = if (opts.length > 1) parseZ(opts(1), "xOffset").getOrElse(0) else 0
              val yOffset: Z = if (opts.length > 2) parseZ(opts(2), "yOffset").getOrElse(0) else 0
              val imagePath = new java.io.File(dir, arg).getCanonicalPath
              org.sireum.Some(ClickImage(imagePath, similarity, xOffset, yOffset))

            case "waitforimage" =>
              val similarity: F64 = if (opts.nonEmpty) parseF64(opts(0), "similarity").getOrElse(org.sireum.F64(0.9)) else org.sireum.F64(0.9)
              val timeout: Z = if (opts.length > 1) parseZ(opts(1), "timeoutMs").getOrElse(10000) else 10000
              val imagePath = new java.io.File(dir, arg).getCanonicalPath
              org.sireum.Some(WaitForImage(imagePath, similarity, timeout))

            case "wait" =>
              parseZ(arg, "ms") match {
                case org.sireum.Some(ms) => org.sireum.Some(Wait(ms))
                case _ => org.sireum.None()
              }

            case "notify" =>
              org.sireum.Some(Notify(arg))

            case "speak" =>
              val async = opts.exists(_.toLowerCase == "async")
              org.sireum.Some(Speak(substSpeak(arg), org.sireum.B(async)))

            case "waitforspeech" =>
              org.sireum.Some(WaitForSpeech())

            case "screencapture" =>
              val outPath = new java.io.File(dir, arg).getCanonicalPath
              org.sireum.Some(ScreenCapture(outPath))

            case "clicktext" =>
              val timeout: Z = if (opts.nonEmpty) parseZ(opts(0), "timeoutMs").getOrElse(10000) else 10000
              val xOffset: Z = if (opts.length > 1) parseZ(opts(1), "xOffset").getOrElse(0) else 0
              val yOffset: Z = if (opts.length > 2) parseZ(opts(2), "yOffset").getOrElse(0) else 0
              org.sireum.Some(ClickText(arg, timeout, xOffset, yOffset))

            case "waitfortext" =>
              val timeout: Z = if (opts.nonEmpty) parseZ(opts(0), "timeoutMs").getOrElse(10000) else 10000
              org.sireum.Some(WaitForText(arg, timeout))

            case "hidecursor" =>
              org.sireum.Some(HideCursor())

            case "showcursor" =>
              org.sireum.Some(ShowCursor())

            case _ =>
              reporter.error(getPosOpt(node), kind,
                s"Unknown command in '$actionName': $cmd")
              org.sireum.None()
          }
        case _ =>
          reporter.error(getPosOpt(node), kind,
            s"Invalid command syntax in '$actionName': $raw")
          org.sireum.None()
      }
    }

    while (child != null) {
      child match {
        case heading: Heading =>
          val actionName = getTexts(heading).mkString(" ")
          child = child.getNext
          var commands = ISZ[Command]()
          while (child != null && !child.isInstanceOf[Heading]) {
            child match {
              case bl: BulletList =>
                var listItem = bl.getFirstChild.asInstanceOf[ListItem]
                while (listItem != null) {
                  val texts = getTexts(listItem)
                  for (t <- texts if t.nonEmpty) {
                    parseCommand(actionName, listItem, t) match {
                      case org.sireum.Some(cmd) => commands = commands :+ cmd
                      case _ =>
                    }
                  }
                  listItem = listItem.getNext.asInstanceOf[ListItem]
                }
              case _ =>
                if (!child.isInstanceOf[HtmlBlock]) {
                  reporter.error(getPosOpt(child), kind,
                    s"Expecting bullet list in '$actionName', but found: ${child.getClass.getSimpleName}")
                }
            }
            child = child.getNext
          }
          actions = actions :+ Action(actionName, commands)
        case _ =>
          if (!child.isInstanceOf[HtmlBlock]) {
            reporter.error(getPosOpt(child), kind,
              s"Expecting a heading (#), but found: ${child.getClass.getSimpleName}")
          }
          child = child.getNext
      }
    }

    if (reporter.hasError) return org.sireum.None()

    org.sireum.Some(Script(scriptName, defaultCharDelayMs, defaultActionDelayMs, actions))
  }

  def run(script: Script, audioDir: Os.Path, audioExt: String, recordPath: Option[String]): Z = {
    currentAudioDir = audioDir.value.value
    currentAudioExt = audioExt.value
    println(s"Running script: ${script.name}")

    // Prime the Tesseract OCR server if the script uses text-based commands.
    // This triggers WASM loading + JIT compilation during the countdown so
    // the first real OCR call is fast.
    val needsOcr = script.actions.elements.exists(_.commands.elements.exists {
      case _: ClickText | _: WaitForText => true
      case _ => false
    })
    if (needsOcr) {
      val primeThread = new Thread(() => {
        val server = ensureTesseract()
        if (server != null) {
          println("    [ocr] Priming OCR engine with screen capture...")
          val t0 = System.nanoTime()
          val screen = scaleImage(captureScreen(), ocrScale)
          val baos = new java.io.ByteArrayOutputStream()
          ImageIO.write(screen, "png", baos)
          val encMs = (System.nanoTime() - t0) / 1000000L
          val t1 = System.nanoTime()
          server.ocr(baos.toByteArray)
          val ocrMs = (System.nanoTime() - t1) / 1000000L
          println(s"    [ocr] OCR engine primed (encode: ${encMs}ms, ocr: ${ocrMs}ms)")
        }
      })
      primeThread.setDaemon(true)
      primeThread.start()
    }

    for (s <- 5 to 1 by -1) {
      println(s"  Starting in $s...")
      showOverlay(String(s"Starting in $s..."), 900)
    }
    println("  Go!")
    showOverlay(String("Go!"), 500)
    val recording = recordPath match {
      case org.sireum.Some(p) => startRecording(p.value)
      case _ => false
    }
    for (i <- script.actions.indices) {
      val action = script.actions(i)
      println(s"  Action ${i + 1}: ${action.name}")
      for (command <- action.commands.elements) {
        executeCommand(command, script.defaultCharDelayMs, script.defaultActionDelayMs)
      }
      if (i < script.actions.size - 1) {
        robot.delay(script.defaultActionDelayMs.toInt)
      }
    }
    waitForSpeech()
    recordPath match {
      case org.sireum.Some(p) if recording => stopRecording(p.value)
      case _ =>
    }
    tesseractServer.foreach(_.shutdown())
    tesseractServer = scala.None
    showOverlay(String("Script complete."), 2000)
    println("Script complete.")
    return 0
  }

  private def executeCommand(command: Command, defaultCharDelayMs: Z, defaultActionDelayMs: Z): Unit = {
    command match {
      case a: TypeText => typeText(a, defaultCharDelayMs)
      case a: PressKey => pressKey(a)
      case a: TypeChar => typeChar(a)
      case a: MouseMove => mouseMove(a)
      case a: MouseClick => mouseClick(a)
      case a: MouseDoubleClick => mouseDoubleClick(a)
      case a: MouseDrag => mouseDrag(a)
      case a: ClickImage => clickImage(a)
      case a: WaitForImage => waitForImage(a)
      case a: Wait => robot.delay(a.ms.toInt)
      case a: Notify =>
        println(s"    [notify] ${a.message}")
        showOverlay(a.message)
      case a: Speak => speak(a)
      case _: WaitForSpeech => waitForSpeech()
      case a: ScreenCapture => screenCapture(a)
      case a: ClickText => clickText(a)
      case a: WaitForText => waitForText(a)
      case _: HideCursor => hideCursor()
      case _: ShowCursor => showCursor()
    }
  }

  // Holds a long-running helper process whose only job is to keep the
  // macOS OS cursor hidden via CoreGraphics CGDisplayHideCursor. We balance
  // the hide with CGDisplayShowCursor when stdin closes (which happens
  // when ShowCursor or the JVM shutdown hook destroys the process).
  @volatile private var cursorHideProc: java.lang.Process = null
  private val isMac: Boolean =
    System.getProperty("os.name", "").toLowerCase.contains("mac")
  // Register once: if the JVM exits while the cursor is still hidden
  // (script aborted, Ctrl-C, etc.) make sure we still restore it.
  Runtime.getRuntime.addShutdownHook(new Thread(() => {
    val p = cursorHideProc
    if (p != null) {
      try p.getOutputStream.close() catch { case _: Throwable => }
      try p.waitFor(1, java.util.concurrent.TimeUnit.SECONDS) catch { case _: Throwable => }
      try p.destroyForcibly() catch { case _: Throwable => }
      cursorHideProc = null
    }
  }, "roboto-cursor-restore"))

  // Stop compositing the cursor overlay onto recorded frames and, on
  // macOS, hide the OS cursor itself by running a tiny Swift helper that
  // calls CGDisplayHideCursor + waits on stdin. The previous workaround
  // parked the OS pointer in a screen corner — but every corner triggered
  // either the macOS menu bar or the dock. CGDisplayHideCursor truly hides
  // the cursor system-wide for the duration. Falls back to the corner-park
  // workaround on non-macOS platforms.
  private def hideCursor(): Unit = {
    drawCursor = false
    if (isMac) {
      val swiftSrc =
        """import CoreGraphics
          |import Foundation
          |CGDisplayHideCursor(CGMainDisplayID())
          |let _ = FileHandle.standardInput.readDataToEndOfFile()
          |CGDisplayShowCursor(CGMainDisplayID())
          |""".stripMargin
      try {
        val pb = new java.lang.ProcessBuilder("swift", "-")
        pb.redirectErrorStream(true)
        val p = pb.start()
        val os = p.getOutputStream
        os.write(swiftSrc.getBytes("UTF-8"))
        os.flush()
        cursorHideProc = p
        return
      } catch {
        case _: Throwable => // swift not available — fall through to park
      }
    }
    val ss = Toolkit.getDefaultToolkit.getScreenSize
    robot.mouseMove(ss.width - 1, ss.height / 2)
  }

  // Re-enable the cursor overlay. On macOS, close the helper's stdin so it
  // calls CGDisplayShowCursor and exits cleanly.
  private def showCursor(): Unit = {
    drawCursor = true
    val p = cursorHideProc
    if (p != null) {
      try {
        p.getOutputStream.close()
        p.waitFor(2, java.util.concurrent.TimeUnit.SECONDS)
      } catch {
        case _: Throwable =>
      }
      try p.destroyForcibly() catch { case _: Throwable => }
      cursorHideProc = null
    }
  }

  // -- Overlay --

  private def showOverlay(message: String, durationMs: Int = 1500): Unit = {
    val screenSize = Toolkit.getDefaultToolkit.getScreenSize
    javax.swing.SwingUtilities.invokeAndWait(new Runnable {
      def run(): Unit = {
        val frame = new javax.swing.JWindow()
        frame.setAlwaysOnTop(true)
        val label = new javax.swing.JLabel(message.value, javax.swing.SwingConstants.CENTER)
        label.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 36))
        label.setForeground(java.awt.Color.WHITE)
        label.setOpaque(false)
        val panel = new javax.swing.JPanel(new java.awt.BorderLayout()) {
          override def paintComponent(g: java.awt.Graphics): Unit = {
            val g2 = g.asInstanceOf[Graphics2D]
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
              java.awt.RenderingHints.VALUE_ANTIALIAS_ON)
            g2.setColor(new java.awt.Color(0, 0, 0, 180))
            g2.fillRoundRect(0, 0, getWidth, getHeight, 20, 20)
          }
        }
        panel.setOpaque(false)
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 32, 16, 32))
        panel.add(label)
        frame.setContentPane(panel)
        frame.setBackground(new java.awt.Color(0, 0, 0, 0))
        frame.pack()
        frame.setLocation(
          (screenSize.width - frame.getWidth) / 2,
          (screenSize.height - frame.getHeight) / 2
        )
        frame.setVisible(true)
        val timer = new javax.swing.Timer(durationMs, new java.awt.event.ActionListener {
          def actionPerformed(e: java.awt.event.ActionEvent): Unit = {
            frame.dispose()
          }
        })
        timer.setRepeats(false)
        timer.start()
      }
    })
    robot.delay(durationMs + 200)
  }

  // -- Keyboard Actions --

  private def typeText(a: TypeText, defaultCharDelayMs: Z): Unit = {
    val delay = if (a.delayMs >= 0) a.delayMs.toInt else defaultCharDelayMs.toInt
    if (delay == 0) {
      // instant: paste from clipboard
      val clipboard = Toolkit.getDefaultToolkit.getSystemClipboard
      clipboard.setContents(new StringSelection(a.text.value), null)
      val pasteKey = if (System.getProperty("os.name").toLowerCase.contains("mac")) KeyEvent.VK_META else KeyEvent.VK_CONTROL
      robot.keyPress(pasteKey)
      robot.keyPress(KeyEvent.VK_V)
      robot.keyRelease(KeyEvent.VK_V)
      robot.keyRelease(pasteKey)
    } else {
      for (c <- a.text.value.toCharArray) {
        typeCharNative(c)
        robot.delay(delay)
      }
    }

  }

  private def typeCharNative(c: Char): Unit = {
    val shift = c.isUpper || shiftChars.contains(c)
    val code = charToKeyCode(c)
    if (code >= 0) {
      if (shift) robot.keyPress(KeyEvent.VK_SHIFT)
      robot.keyPress(code)
      robot.keyRelease(code)
      if (shift) robot.keyRelease(KeyEvent.VK_SHIFT)
    }
  }

  private def pressKey(a: PressKey): Unit = {
    val code = keyToKeyCode(a.key)
    val mods = a.modifiers.elements.map(modToKeyCode)
    for (m <- mods) robot.keyPress(m)
    robot.keyPress(code)
    robot.keyRelease(code)
    for (m <- mods.reverse) robot.keyRelease(m)

  }

  private def typeChar(a: TypeChar): Unit = {
    val code = charToKeyCode(a.char.value.toChar)
    val mods = a.modifiers.elements.map(modToKeyCode)
    if (code >= 0) {
      for (m <- mods) robot.keyPress(m)
      robot.keyPress(code)
      robot.keyRelease(code)
      for (m <- mods.reverse) robot.keyRelease(m)
    }

  }

  // -- Screen Recording (pure Java + ffmpeg) --

  private val recordingFps = 30
  @volatile private var recording = false
  private var ffmpegProcess: Process = _
  private var videoTmpFile: java.io.File = _
  private var captureThread: Thread = _
  private var audioLine: javax.sound.sampled.TargetDataLine = _
  private var audioFile: java.io.File = _
  private var audioThread: Thread = _
  @volatile private var recordedFrameCount: Long = 0
  @volatile private var recordingStartMs: Long = 0
  @volatile private var recordingEndMs: Long = 0
  // Toggled by HideCursor / ShowCursor commands. When false, the capture
  // loop skips compositing the cursor overlay (and a HideCursor parks the
  // OS pointer offscreen at the same time).
  @volatile private var drawCursor: Boolean = true

  // Cursor image (22x32 PNG, base64-embedded) composited onto each captured frame
  private val cursorImage: BufferedImage = {
    val b64 = "iVBORw0KGgoAAAANSUhEUgAAABYAAAAgCAYAAAAWl4iLAAAABGdBTUEAALGPC/xhBQAAACBjSFJN" +
      "AAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAhGVYSWZNTQAqAAAACAAFARIAAwAA" +
      "AAEAAQAAARoABQAAAAEAAABKARsABQAAAAEAAABSASgAAwAAAAEAAgAAh2kABAAAAAEAAABaAAAAAAAA" +
      "AFoAAAABAAAAWgAAAAEAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAFqADAAQAAAABAAAAIAAAAAA7AvFDAA" +
      "AACXBIWXMAAA3XAAAN1wFCKJt4AAACyGlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPHg6eG1wbWV0" +
      "YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iWE1QIENvcmUgNi4wLjAiPgogICA8" +
      "cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRh" +
      "eC1ucyMiPgogICAgICA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIgogICAgICAgICAgICB4" +
      "bWxuczp0aWZmPSJodHRwOi8vbnMuYWRvYmUuY29tL3RpZmYvMS4wLyIKICAgICAgICAgICAgeG1s" +
      "bnM6ZXhpZj0iaHR0cDovL25zLmFkb2JlLmNvbS9leGlmLzEuMC8iPgogICAgICAgICA8dGlmZjpZ" +
      "UmVzb2x1dGlvbj45MDwvdGlmZjpZUmVzb2x1dGlvbj4KICAgICAgICAgPHRpZmY6UmVzb2x1dGlv" +
      "blVuaXQ+MjwvdGlmZjpSZXNvbHV0aW9uVW5pdD4KICAgICAgICAgPHRpZmY6WFJlc29sdXRpb24+" +
      "OTA8L3RpZmY6WFJlc29sdXRpb24+CiAgICAgICAgIDx0aWZmOk9yaWVudGF0aW9uPjE8L3RpZmY6" +
      "T3JpZW50YXRpb24+CiAgICAgICAgIDxleGlmOlBpeGVsWERpbWVuc2lvbj4zMjwvZXhpZjpQaXhl" +
      "bFhEaW1lbnNpb24+CiAgICAgICAgIDxleGlmOkNvbG9yU3BhY2U+MTwvZXhpZjpDb2xvclNwYWNl" +
      "PgogICAgICAgICA8ZXhpZjpQaXhlbFlEaW1lbnNpb24+MzI8L2V4aWY6UGl4ZWxZRGltZW5zaW9u" +
      "PgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KC7lA" +
      "EwAABC1JREFUSA2tlktIZEcUhm8/bTUKRnyQaKTV+MAXvkAFiRoDIVGMSSALUSYrce/OyCwUV5FZ" +
      "BAS3s5OBLEwWwY0SSYTQAWOnfSQgqBk0cQidbnu0n5X/r1s1XKd94cyB6lP31Llf1T11TlUbhmFM" +
      "oznRKA5TvZ7fv4D52YLSk1hM9+v6XC6XKCkp+QWvv6EQrvuhLG9lZGRs4VGweb3eP6GL1PArw/19" +
      "fX1idnb2GeEVFRV/5+bmvqvgbqXvpfydnZ0iHo+Hl5aWwoRXVlZGSktL214V7q+vrxfnEAFZXl7+" +
      "j3CAGZoPFPxeYfG3traK5xCCIam1tbUQ4Xl5eaKqquoLBWe22FT/Tsrf0tLCFUtwMpmUcJ/Px7Ak" +
      "0URtbe2EIjHP7wy/BOaKFVxsb2+fARQjvKGh4SsFt0Oz3SqXwKlUiisWGn5wcBAB4QJNNDY2PrLQ" +
      "boX/bg2FBhOeSCSoxMnJyXl7ezsnEE1NTY8t8BuPAAnWm2cFE6rhwWDwYnh4WKYj4N9b4NceATeCrXBM" +
      "Hh0fH5cZA/hPgGeoCa5Mx1vBhCPmMvgopNjU1JSEY0MDAL95HVzHOELAy6GgTQvGzJ0VIj4/Py8L" +
      "qa6u7sjj8ZRdBb8TmFnChphreGJxcVHCUUhBtEYFf3G+3AmsV/2STq6srGh4LCcnp1vBndeWKQCG" +
      "zWYWGfqp4+PjOKrTFo1GBVo8HA4beLajb+/u7o6sr69nZ2Zm/gjwZ2jfcoKAymMZY0JUKPnJZiJD" +
      "T09PP4evLHFoeX5bNS8LPKdU+yQtBxFHw+FwGHt7e7Hd3d340NAQbxXH6OiobWZmxo4D6xm+5BFA" +
      "IafTGYRvcHNzM4Q8Z44zLKxSLiBtxWJ/f59nRDQrKyvO3MXKKbGJiYko7GJwcPAdvniTyHrHSwZW" +
      "KgOKVUbKy8s9BQUFbkCdGxsb/DyKa2xsjAeScXh4OEcN8aCxrHUjj02yAshFruhsZ2fnHEYZR1TW" +
      "1+ifjYyMJDBpjA7YqGh/f38C9gvcOm9DU+TizO7l38DAwIBYXV39B2auTlRXV39JF0z4HZ+3trbO" +
      "CIakcMPIqmtra3tIH8iV5cyBAG8KaAnFdfQ5jZSurq4+KIGMYMbIDMEm8auE3W5/2tPTw1BQzLw0" +
      "++Yvdvg39ERRUZEoLi7+WI2xeqRzWVnZrxw/OjrS6ZhcWFiQpxwmfqD807KL9j8AF0id95STLknp" +
      "3NHR8QB2gfINY9Xy3jo9PWVOi/z8fP7JoaSvGEYf2vschWgo+9KZn+t2u5/iWYRCId7kCb/fH2pu" +
      "bpYHf2Fh4ad0hqQd+iWm/cUfQ/UoldwYpB83SszNzf07OTkpryk+Z2dn70J/KD2vANOeNpty1qn0" +
      "FvJaXqqwi5qamh+w4R8pHyrtZzFdY7R46Em/8Xq9T3p7e+ssYwyXHreYDeN/99xFR2Y5s2cAAAAA" +
      "SUVORK5CYII="
    val bytes = java.util.Base64.getDecoder.decode(b64)
    javax.imageio.ImageIO.read(new java.io.ByteArrayInputStream(bytes))
  }

  // Sonar effect state — composited onto frames in the capture thread
  private class SonarState(val x: Int, val y: Int, val startTime: Long) {
    val durationMs = 400
    val maxRadius = 40
    def isActive: Boolean = System.currentTimeMillis() - startTime < durationMs
    def radius: Int = {
      val t = (System.currentTimeMillis() - startTime).toDouble / durationMs
      (5 + t * (maxRadius - 5)).toInt
    }
    def alpha: Int = {
      val t = (System.currentTimeMillis() - startTime).toDouble / durationMs
      math.max(0, (200 * (1.0 - t)).toInt)
    }
  }
  @volatile private var activeSonar: SonarState = _

  private def showSonarEffect(x: Int, y: Int): Unit = {
    if (!recording) return
    activeSonar = new SonarState(x, y, System.currentTimeMillis())
  }

  private def startAudioRecording(): Unit = {
    import javax.sound.sampled._
    try {
      val format = new AudioFormat(48000f, 16, 2, true, false)
      val info = new DataLine.Info(classOf[TargetDataLine], format)
      val line = AudioSystem.getLine(info).asInstanceOf[TargetDataLine]
      line.open(format)
      line.start()
      audioLine = line
      audioFile = java.io.File.createTempFile("roboto-audio-", ".wav")
      val thread = new Thread(() => {
        try {
          AudioSystem.write(new AudioInputStream(line), AudioFileFormat.Type.WAVE, audioFile)
        } catch {
          case _: Exception =>
        }
      })
      thread.setDaemon(true)
      thread.start()
      audioThread = thread
      println(s"    [record] Audio recording from default input device")
    } catch {
      case t: Throwable =>
        System.err.println(s"    [record] Failed to start audio recording: ${t.getMessage}")
    }
  }

  private def stopAudioRecording(): java.io.File = {
    if (audioLine != null) {
      audioLine.stop()
      audioLine.close()
      audioLine = null
    }
    if (audioThread != null) {
      audioThread.join(5000)
      audioThread = null
    }
    val f = audioFile
    audioFile = null
    f
  }

  private def startRecording(outputPath: Predef.String): Boolean = {
    try {
      val screenSize = Toolkit.getDefaultToolkit.getScreenSize
      val w = screenSize.width
      val h = screenSize.height
      // Park cursor away from edges
      robot.mouseMove(w / 2, h * 3 / 4)

      // Start audio
      startAudioRecording()

      // Start ffmpeg — pipe raw BGR frames in, encode HEVC MP4 with audio placeholder
      // Audio will be muxed in stopRecording after capture completes
      val wavTmp = audioFile

      val cmd = new java.util.ArrayList[Predef.String]()
      cmd.add("ffmpeg"); cmd.add("-y")
      // Raw video input from pipe
      cmd.add("-f"); cmd.add("rawvideo")
      cmd.add("-pix_fmt"); cmd.add("bgr24")
      cmd.add("-s"); cmd.add(s"${w}x${h}")
      cmd.add("-r"); cmd.add(recordingFps.toString)
      cmd.add("-i"); cmd.add("pipe:0")
      // Video encoding (H.264 for JavaFX compatibility)
      cmd.add("-pix_fmt"); cmd.add("yuv420p")
      cmd.add("-c:v"); cmd.add(if (Os.isMac) "h264_videotoolbox" else "libx264")
      cmd.add("-r"); cmd.add(recordingFps.toString)
      cmd.add("-g"); cmd.add("60")
      // Output to temp video file (audio muxed later)
      videoTmpFile = java.io.File.createTempFile("roboto-video-", ".mp4")
      cmd.add(videoTmpFile.getAbsolutePath)

      val pb = new ProcessBuilder(cmd)
      pb.environment().put("PATH", s"/opt/homebrew/bin:${System.getenv("PATH")}")
      pb.redirectErrorStream(true)
      val proc = pb.start()
      ffmpegProcess = proc

      // Drain ffmpeg stderr/stdout in background
      new Thread(() => {
        try {
          val r = new java.io.BufferedReader(new java.io.InputStreamReader(proc.getInputStream))
          while (r.readLine() != null) {}
        } catch { case _: Exception => }
      }, "ffmpeg-drain").start()

      // Capture thread
      recording = true
      val frameIntervalMs = 1000 / recordingFps
      val out = proc.getOutputStream
      val captureRobot = new java.awt.Robot()
      val rect = new java.awt.Rectangle(0, 0, w, h)
      val frameBytes = w * h * 3

      captureThread = new Thread(() => {
        try {
          val buf = new Array[Byte](frameBytes)
          recordingStartMs = System.currentTimeMillis()
          var frameCount = 0L
          while (recording) {
            val frameStart = System.currentTimeMillis()


            // Capture screen
            val screen = captureRobot.createScreenCapture(rect)

            // Composite cursor at current mouse position (unless hidden).
            val g2 = screen.createGraphics()
            try {
              if (drawCursor) {
                val mousePos = java.awt.MouseInfo.getPointerInfo.getLocation
                g2.drawImage(cursorImage, mousePos.x - 2, mousePos.y - 5, null)
              }

              // Composite sonar if active
              val sonar = activeSonar
              if (sonar != null && sonar.isActive) {
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                  java.awt.RenderingHints.VALUE_ANTIALIAS_ON)
                val a = math.min(255, math.max(0, sonar.alpha))
                g2.setColor(new java.awt.Color(0x90, 0x50, 0xE0, a))
                g2.setStroke(new java.awt.BasicStroke(3f))
                val r = sonar.radius
                g2.drawOval(sonar.x - r, sonar.y - r, r * 2, r * 2)
              } else if (sonar != null && !sonar.isActive) {
                activeSonar = null
              }
            } finally {
              g2.dispose()
            }

            // Extract BGR bytes and write to ffmpeg
            val raster = screen.getRaster
            val pixels = raster.getDataBuffer.asInstanceOf[java.awt.image.DataBufferInt].getData
            var bi = 0
            var pi = 0
            while (pi < pixels.length) {
              val px = pixels(pi)
              buf(bi) = (px & 0xFF).toByte         // B
              buf(bi + 1) = ((px >> 8) & 0xFF).toByte  // G
              buf(bi + 2) = ((px >> 16) & 0xFF).toByte // R
              bi += 3
              pi += 1
            }
            out.write(buf)
            frameCount += 1

            // Pace to target FPS
            val elapsed = System.currentTimeMillis() - frameStart
            val sleep = frameIntervalMs - elapsed
            if (sleep > 0) Thread.sleep(sleep)
          }
          recordingEndMs = System.currentTimeMillis()
          recordedFrameCount = frameCount
          out.flush()
          out.close()
        } catch {
          case _: Exception =>
            try { out.close() } catch { case _: Exception => }
        }
      }, "screen-capture")
      captureThread.setDaemon(true)
      captureThread.start()

      println(s"    [record] Recording to $outputPath (${w}x${h} @ ${recordingFps}fps)")
      true
    } catch {
      case t: Throwable =>
        System.err.println(s"    [record] Failed to start recording: ${t.getMessage}")
        t.printStackTrace()
        false
    }
  }

  private def stopRecording(outputPath: Predef.String): Unit = {
    // Stop capture thread
    recording = false
    if (captureThread != null) {
      captureThread.join(10000)
      captureThread = null
    }

    // Stop audio
    val wavFile = stopAudioRecording()

    // Wait for ffmpeg video encoding to finish
    val videoTmp = if (ffmpegProcess != null) {
      try {
        ffmpegProcess.waitFor(600, java.util.concurrent.TimeUnit.SECONDS)
      } catch { case _: Exception => }
      ffmpegProcess = null
      val f = videoTmpFile
      videoTmpFile = null
      f
    } else null

    // Mux video + audio into final output
    if (videoTmp != null && videoTmp.exists()) {
      val target = new java.io.File(outputPath)
      val outDir = target.getParentFile
      if (outDir != null && !outDir.exists()) outDir.mkdirs()

      if (wavFile != null && wavFile.exists() && wavFile.length() > 44) {
        // Correct video duration: ffmpeg thinks video is frameCount/declaredFps seconds,
        // but actual wall-clock duration may differ due to capture overhead
        val declaredDuration = recordedFrameCount.toDouble / recordingFps
        val actualDuration = (recordingEndMs - recordingStartMs) / 1000.0
        val itsScale = if (declaredDuration > 0 && actualDuration > 0) actualDuration / declaredDuration else 1.0
        println(f"    [record] frames=$recordedFrameCount, declared=${declaredDuration}%.1fs, actual=${actualDuration}%.1fs, itsscale=$itsScale%.4f")

        val cmd = new java.util.ArrayList[Predef.String]()
        cmd.add("ffmpeg"); cmd.add("-y")
        cmd.add("-itsscale"); cmd.add(f"$itsScale%.6f")
        cmd.add("-i"); cmd.add(videoTmp.getAbsolutePath)
        cmd.add("-i"); cmd.add(wavFile.getAbsolutePath)
        cmd.add("-c:v"); cmd.add("copy")
        cmd.add("-c:a"); cmd.add("aac")
        cmd.add("-q:a"); cmd.add("2")
        cmd.add("-b:a"); cmd.add("192k")
        cmd.add("-movflags"); cmd.add("+faststart")
        cmd.add(target.getAbsolutePath)
        println(s"    [record] Muxing audio into ${target.getAbsolutePath} ...")
        val pb = new ProcessBuilder(cmd)
        pb.environment().put("PATH", s"/opt/homebrew/bin:${System.getenv("PATH")}")
        pb.redirectErrorStream(true)
        val p = pb.start()
        val r = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream))
        while (r.readLine() != null) {}
        p.waitFor(600, java.util.concurrent.TimeUnit.SECONDS)
        if (p.exitValue() == 0) {
          videoTmp.delete()
          wavFile.delete()
          println(s"    [record] Recording saved to $outputPath")
        } else {
          System.err.println(s"    [record] Audio mux failed; video at ${videoTmp.getAbsolutePath}")
        }
      } else {
        // No audio; just move video
        videoTmp.renameTo(target)
        if (wavFile != null) wavFile.delete()
        println(s"    [record] Recording saved to $outputPath (video only)")
      }
    } else {
      System.err.println(s"    [record] No video file produced")
    }
  }

  // -- Mouse Actions --

  private def smoothMouseMove(targetX: Int, targetY: Int, durationMs: Int = 300): Unit = {

    val pos = java.awt.MouseInfo.getPointerInfo.getLocation
    val startX = pos.x.toDouble
    val startY = pos.y.toDouble
    val steps = math.max(20, durationMs / 10)
    for (i <- 1 to steps) {
      val t = i.toDouble / steps
      // ease-in-out cubic
      val ease = if (t < 0.5) 4 * t * t * t else 1 - math.pow(-2 * t + 2, 3) / 2
      val x = (startX + (targetX - startX) * ease).toInt
      val y = (startY + (targetY - startY) * ease).toInt
      robot.mouseMove(x, y)
      robot.delay(durationMs / steps)
    }
    robot.mouseMove(targetX, targetY)
  }

  private def mouseMove(a: MouseMove): Unit = {
    robot.mouseMove(a.x.toInt, a.y.toInt)
  }

  private def mouseClick(a: MouseClick): Unit = {
    val button = buttonToMask(a.button)
    robot.mouseMove(a.x.toInt, a.y.toInt)
    robot.delay(50)
    robot.mousePress(button)
    robot.mouseRelease(button)
    showSonarEffect(a.x.toInt, a.y.toInt)

  }

  private def mouseDoubleClick(a: MouseDoubleClick): Unit = {
    val button = buttonToMask(a.button)
    robot.mouseMove(a.x.toInt, a.y.toInt)
    robot.delay(50)
    robot.mousePress(button)
    robot.mouseRelease(button)
    robot.delay(50)
    robot.mousePress(button)
    robot.mouseRelease(button)
    showSonarEffect(a.x.toInt, a.y.toInt)

  }

  private def mouseDrag(a: MouseDrag): Unit = {
    robot.mouseMove(a.fromX.toInt, a.fromY.toInt)
    robot.delay(50)
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
    robot.delay(50)
    robot.mouseMove(a.toX.toInt, a.toY.toInt)
    robot.delay(50)
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
  }

  // -- Image Actions --

  private def clickImage(a: ClickImage): Unit = {
    val target = loadImage(a.imagePath)
    findImageOnScreen(target, a.similarity.value) match {
      case scala.Some((x, y)) =>
        val cx = x + a.xOffset.toInt
        val cy = y + a.yOffset.toInt
        println(s"    [debug] match center at ($x, $y), clicking at ($cx, $cy)")
        smoothMouseMove(cx, cy)
        robot.delay(100)
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
        robot.delay(50)
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
        showSonarEffect(cx, cy)
    
      case _ =>
        System.err.println(s"    [error] Image not found on screen: ${a.imagePath}")
    }
  }

  private def waitForImage(a: WaitForImage): Unit = {
    val target = loadImage(a.imagePath)
    val deadline = System.currentTimeMillis() + a.timeoutMs.toLong
    var found = false
    while (!found && System.currentTimeMillis() < deadline) {
      findImageOnScreen(target, a.similarity.value) match {
        case scala.Some(_) => found = true
        case _ => robot.delay(500)
      }
    }
    if (!found) {
      System.err.println(s"    [error] Timed out waiting for image: ${a.imagePath}")
    }
  }

  private def screenCapture(a: ScreenCapture): Unit = {
    val capture = captureScreen()
    ImageIO.write(capture, "png", new java.io.File(a.outputPath.value))
    println(s"    [capture] Saved to ${a.outputPath}")
  }

  // -- OCR (Tesseract WASM) --

  private var tesseractServer: scala.Option[TesseractWasmServer] = scala.None

  private def ensureTesseract(): TesseractWasmServer = {
    tesseractServer match {
      case scala.Some(s) if s.isAlive => s
      case _ =>
        if (!WasmServer.hasJvmci) {
          System.err.println(s"    [error] JVMCI is not enabled; OCR requires JVMCI for acceptable performance")
          return null
        }
        val wasmPath: Os.Path = Os.sireumHomeOpt match {
          case org.sireum.Some(h) => h / "lib" / "tesseract_server.wasm"
          case _ =>
            System.err.println(s"    [error] SIREUM_HOME not set")
            return null
        }
        if (!wasmPath.exists) {
          System.err.println(s"    [error] tesseract_server.wasm not found at $wasmPath")
          return null
        }
        println(s"    [ocr] Loading tesseract from $wasmPath")
        val bytes = java.nio.file.Files.readAllBytes(java.nio.file.Path.of(wasmPath.value.value))
        val s = new TesseractWasmServer(bytes)
        s.start()
        tesseractServer = scala.Some(s)
        s
    }
  }

  private val ocrScale = 1.0

  private def ocrScreen(): Seq[TesseractWasmServer#Word] = {
    val server = ensureTesseract()
    if (server == null) return Seq.empty
    val t0 = System.nanoTime()
    val screen = captureScreen()
    val scaled = scaleImage(screen, ocrScale)
    val baos = new java.io.ByteArrayOutputStream()
    ImageIO.write(scaled, "png", baos)
    val encMs = (System.nanoTime() - t0) / 1000000L
    val t1 = System.nanoTime()
    val rawWords = server.ocr(baos.toByteArray)
    val ocrMs = (System.nanoTime() - t1) / 1000000L
    // Scale coordinates back to original screen space
    val invScale = 1.0 / ocrScale
    val words = rawWords.map(w => w.copy(
      x1 = (w.x1 * invScale).toInt, y1 = (w.y1 * invScale).toInt,
      x2 = (w.x2 * invScale).toInt, y2 = (w.y2 * invScale).toInt))
    println(s"    [ocr] Screen OCR: ${words.size} words (encode: ${encMs}ms, ocr: ${ocrMs}ms, scale: ${ocrScale})")
    words
  }

  /** Keep only letters and digits for OCR-tolerant comparison. */
  private def alphaOnly(s: Predef.String): Predef.String =
    s.filter(_.isLetterOrDigit).toLowerCase

  private def findTextOnScreen(text: Predef.String): scala.Option[(Int, Int)] = {
    val t0 = System.nanoTime()
    val words = ocrScreen()
    val tOcr = System.nanoTime()
    val scale = getScreenScale
    val searchTokens = text.split("\\s+").map(alphaOnly).filter(_.nonEmpty)

    def done(result: scala.Option[(Int, Int)]): scala.Option[(Int, Int)] = {
      val matchMs = (System.nanoTime() - tOcr) / 1000000L
      val totalMs = (System.nanoTime() - t0) / 1000000L
      println(s"    [ocr] findText total: ${totalMs}ms (ocr: ${totalMs - matchMs}ms, match: ${matchMs}ms)")
      result
    }

    if (searchTokens.isEmpty) return done(scala.None)

    val wordSeq = words.toIndexedSeq

    // Single-word match
    if (searchTokens.length == 1) {
      for (w <- wordSeq) {
        if (alphaOnly(w.text) == searchTokens(0)) {
          val cx = (((w.x1 + w.x2) / 2) / scale).toInt
          val cy = (((w.y1 + w.y2) / 2) / scale).toInt
          println(s"    [ocr] Found '$text' as '${w.text.trim}' at (${w.x1},${w.y1})-(${w.x2},${w.y2}), clicking ($cx,$cy)")
          return done(scala.Some((cx, cy)))
        }
      }
    }

    // Multi-word consecutive match using alphanumeric-only comparison.
    // Among multiple matches, prefer one NOT followed by more words on the same line.
    if (searchTokens.length > 1) {
      case class Match(idx: Int, x1: Int, y1: Int, x2: Int, y2: Int, label: Predef.String)
      var matches = Vector.empty[Match]

      for (i <- wordSeq.indices if i + searchTokens.length <= wordSeq.length) {
        if ((0 until searchTokens.length).forall(j =>
          alphaOnly(wordSeq(i + j).text) == searchTokens(j))) {
          val first = wordSeq(i)
          val last = wordSeq(i + searchTokens.length - 1)
          matches = matches :+ Match(i,
            math.min(first.x1, last.x1), math.min(first.y1, last.y1),
            math.max(first.x2, last.x2), math.max(first.y2, last.y2),
            (0 until searchTokens.length).map(j => wordSeq(i + j).text.trim).mkString(" "))
        }
      }

      if (matches.nonEmpty) {
        // Prefer match where the next word is NOT on the same line (standalone phrase)
        val best = matches.find { m =>
          val nextIdx = m.idx + searchTokens.length
          nextIdx >= wordSeq.length || {
            val nextWord = wordSeq(nextIdx)
            val myCy = (m.y1 + m.y2) / 2
            val nextCy = (nextWord.y1 + nextWord.y2) / 2
            math.abs(nextCy - myCy) > (m.y2 - m.y1)
          }
        }.getOrElse(matches.head)

        val cx = (((best.x1 + best.x2) / 2) / scale).toInt
        val cy = (((best.y1 + best.y2) / 2) / scale).toInt
        println(s"    [ocr] Found '$text' as '${best.label}' at (${best.x1},${best.y1})-(${best.x2},${best.y2}), clicking ($cx,$cy)")
        return done(scala.Some((cx, cy)))
      }
    }

    // Fallback: single-word substring match
    val searchAlpha = searchTokens.mkString
    for (w <- wordSeq) {
      if (alphaOnly(w.text).contains(searchAlpha)) {
        val cx = (((w.x1 + w.x2) / 2) / scale).toInt
        val cy = (((w.y1 + w.y2) / 2) / scale).toInt
        println(s"    [ocr] Found '$text' in '${w.text.trim}' at (${w.x1},${w.y1})-(${w.x2},${w.y2}), clicking ($cx,$cy)")
        return done(scala.Some((cx, cy)))
      }
    }
    println(s"    [ocr] Text '$text' not found. Detected words: ${words.map(_.text.trim).mkString(", ")}")
    done(scala.None)
  }

  private def clickText(a: ClickText): Unit = {
    val deadline = System.currentTimeMillis() + a.timeoutMs.toLong
    var found = false
    while (!found && System.currentTimeMillis() < deadline) {
      findTextOnScreen(a.text.value) match {
        case scala.Some((x, y)) =>
          val cx = x + a.xOffset.toInt
          val cy = y + a.yOffset.toInt
          smoothMouseMove(cx, cy)
          robot.delay(100)
          robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
          robot.delay(50)
          robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
          showSonarEffect(cx, cy)
      
          found = true
        case _ => robot.delay(500)
      }
    }
    if (!found) {
      System.err.println(s"    [error] Timed out waiting to click text: ${a.text}")
    }
  }

  private def waitForText(a: WaitForText): Unit = {
    val deadline = System.currentTimeMillis() + a.timeoutMs.toLong
    var found = false
    while (!found && System.currentTimeMillis() < deadline) {
      findTextOnScreen(a.text.value) match {
        case scala.Some(_) => found = true
        case _ => robot.delay(500)
      }
    }
    if (!found) {
      System.err.println(s"    [error] Timed out waiting for text: ${a.text}")
    }
  }

  // -- Speech --

  private var currentAudioDir: scala.Predef.String = ""
  private var currentAudioExt: scala.Predef.String = "mp3"
  private var jfxInitialized: scala.Boolean = false
  private var speakPlayer: scala.Option[AnyRef] = scala.None // javafx.scene.media.MediaPlayer
  private var speakLatch: scala.Option[java.util.concurrent.CountDownLatch] = scala.None

  private def ensureJfx(): Unit = {
    if (!jfxInitialized) {
      try {
        // Initialize JavaFX toolkit
        val platformClass = Class.forName("javafx.application.Platform")
        val startupMethod = platformClass.getMethod("startup", classOf[Runnable])
        startupMethod.invoke(null, new Runnable { def run(): Unit = {} })
      } catch {
        case _: Throwable =>
          try {
            // Already initialized, set implicit exit to false
            val platformClass = Class.forName("javafx.application.Platform")
            val setImplicitExit = platformClass.getMethod("setImplicitExit", classOf[scala.Boolean])
            setImplicitExit.invoke(null, java.lang.Boolean.FALSE)
          } catch { case _: Throwable => }
      }
      jfxInitialized = true
    }
  }

  private def waitForSpeech(): Unit = {
    speakLatch match {
      case scala.Some(latch) =>
        latch.await()
        speakLatch = scala.None
      case _ =>
    }
    speakPlayer.foreach { p =>
      val disposeMethod = p.getClass.getMethod("dispose")
      disposeMethod.invoke(p)
    }
    speakPlayer = scala.None
  }

  private def speak(a: Speak): Unit = {
    waitForSpeech()
    ensureJfx()
    val text = a.text.value
    val audioPath = findAudioFile(text)
    audioPath match {
      case scala.Some(path) =>
        println(s"    [speak] Playing: $text")
        val file = new java.io.File(path)
        val uri = file.toURI.toString
        val latch = new java.util.concurrent.CountDownLatch(1)
        // Create Media and MediaPlayer via reflection
        val mediaClass = Class.forName("javafx.scene.media.Media")
        val playerClass = Class.forName("javafx.scene.media.MediaPlayer")
        val media = mediaClass.getConstructor(classOf[Predef.String]).newInstance(uri)
        val player = playerClass.getConstructor(mediaClass).newInstance(media)
        // Set onEndOfMedia callback
        val setOnEndMethod = playerClass.getMethod("setOnEndOfMedia", classOf[Runnable])
        setOnEndMethod.invoke(player, new Runnable { def run(): Unit = latch.countDown() })
        // Set onError callback
        val setOnErrorMethod = playerClass.getMethod("setOnError", classOf[Runnable])
        setOnErrorMethod.invoke(player, new Runnable { def run(): Unit = {
          System.err.println(s"    [error] JavaFX MediaPlayer error for: $text")
          latch.countDown()
        }})
        // Play on JavaFX thread
        val platformClass = Class.forName("javafx.application.Platform")
        val runLaterMethod = platformClass.getMethod("runLater", classOf[Runnable])
        val playMethod = playerClass.getMethod("play")
        runLaterMethod.invoke(null, new Runnable { def run(): Unit = playMethod.invoke(player) })
        speakPlayer = scala.Some(player)
        if (a.async.value) {
          speakLatch = scala.Some(latch)
        } else {
          latch.await()
          val disposeMethod = playerClass.getMethod("dispose")
          disposeMethod.invoke(player)
          speakPlayer = scala.None
        }
      case _ =>
        System.err.println(s"    [error] No audio found for: $text")
    }
  }

  /* javax.sound.sampled approach (commented out — has clicking noise)
  private var speakLine: scala.Option[javax.sound.sampled.SourceDataLine] = scala.None
  private var speakThread: scala.Option[Thread] = scala.None

  private def closeSpeakLine(): Unit = {
    speakLine.foreach { line =>
      line.drain()
      line.stop()
      line.close()
    }
    speakLine = scala.None
  }

  private def waitForSpeechOld(): Unit = {
    speakThread match {
      case scala.Some(t) =>
        t.join()
        speakThread = scala.None
      case _ =>
    }
  }

  private def speakOld(a: Speak): Unit = {
    waitForSpeechOld()
    val text = a.text.value
    val audioPath = findAudioFile(text)
    audioPath match {
      case scala.Some(path) =>
        println(s"    [speak] Playing: $text")
        val playback: Runnable = new Runnable {
          def run(): Unit = {
            val file = new java.io.File(path)
            val audioStream = javax.sound.sampled.AudioSystem.getAudioInputStream(file)
            val fmt = audioStream.getFormat
            val line = speakLine match {
              case scala.Some(l) if l.getFormat == fmt => l
              case _ =>
                closeSpeakLine()
                val info = new javax.sound.sampled.DataLine.Info(classOf[javax.sound.sampled.SourceDataLine], fmt)
                val l = javax.sound.sampled.AudioSystem.getLine(info).asInstanceOf[javax.sound.sampled.SourceDataLine]
                l.open(fmt)
                l.start()
                speakLine = scala.Some(l)
                l
            }
            val allBytes = audioStream.readAllBytes()
            audioStream.close()
            val sampleSize = fmt.getSampleSizeInBits / 8
            val channels = fmt.getChannels
            val frameSize = sampleSize * channels
            val fadeFrames = math.min((fmt.getSampleRate * 0.005).toInt, allBytes.length / frameSize)
            val data = allBytes.clone()
            val bigEndian = fmt.isBigEndian
            for (i <- 0 until fadeFrames) {
              val gain = i.toDouble / fadeFrames
              for (ch <- 0 until channels) {
                val offset = i * frameSize + ch * sampleSize
                if (sampleSize == 2 && offset + 1 < data.length) {
                  val sample = if (bigEndian) ((data(offset) << 8) | (data(offset + 1) & 0xFF)).toShort
                              else ((data(offset + 1) << 8) | (data(offset) & 0xFF)).toShort
                  val faded = (sample * gain).toShort
                  if (bigEndian) { data(offset) = (faded >> 8).toByte; data(offset + 1) = faded.toByte }
                  else { data(offset) = faded.toByte; data(offset + 1) = (faded >> 8).toByte }
                }
              }
            }
            val totalFrames = data.length / frameSize
            for (i <- 0 until fadeFrames) {
              val frameIdx = totalFrames - 1 - i
              val gain = i.toDouble / fadeFrames
              for (ch <- 0 until channels) {
                val offset = frameIdx * frameSize + ch * sampleSize
                if (sampleSize == 2 && offset + 1 < data.length) {
                  val sample = if (bigEndian) ((data(offset) << 8) | (data(offset + 1) & 0xFF)).toShort
                              else ((data(offset + 1) << 8) | (data(offset) & 0xFF)).toShort
                  val faded = (sample * gain).toShort
                  if (bigEndian) { data(offset) = (faded >> 8).toByte; data(offset + 1) = faded.toByte }
                  else { data(offset) = faded.toByte; data(offset + 1) = (faded >> 8).toByte }
                }
              }
            }
            line.write(data, 0, data.length)
            line.drain()
          }
        }
        if (a.async.value) {
          val t = new Thread(playback)
          t.setDaemon(true)
          t.start()
          speakThread = scala.Some(t)
        } else {
          playback.run()
        }
      case _ =>
        System.err.println(s"    [error] No audio found for: $text")
    }
  }
  */

  private def findAudioFile(text: scala.Predef.String): scala.Option[scala.Predef.String] = {
    if (currentAudioDir.isEmpty) return scala.None
    val dir = new java.io.File(currentAudioDir)
    if (!dir.isDirectory) return scala.None
    val fp = fingerprint(text)
    // try both naming conventions: {text}-{hash}.ext and {hash}-{text}.ext
    val files = dir.listFiles()
    if (files == null) return scala.None
    for (f <- files) {
      val name = f.getName
      if (name.contains(fp) && name.endsWith("." + currentAudioExt)) {
        return scala.Some(f.getAbsolutePath)
      }
    }
    scala.None
  }

  private def fingerprint(text: scala.Predef.String): scala.Predef.String = {
    val md = java.security.MessageDigest.getInstance("SHA3-256")
    val hash = md.digest(text.getBytes("UTF-8"))
    hash.take(3).map(b => f"${b & 0xFF}%02X").mkString
  }

  // -- Image Actions --

  private def loadImage(path: String): BufferedImage = {
    ImageIO.read(new java.io.File(path.value))
  }

  private def getScreenScale: Double = {
    val gd = GraphicsEnvironment.getLocalGraphicsEnvironment.getDefaultScreenDevice
    gd.getDefaultConfiguration.getDefaultTransform.getScaleX
  }

  private def captureScreen(): BufferedImage = {
    val screenSize = Toolkit.getDefaultToolkit.getScreenSize
    robot.createScreenCapture(new java.awt.Rectangle(0, 0, screenSize.width, screenSize.height))
  }

  private def scaleImage(img: BufferedImage, scale: Double): BufferedImage = {
    val w = (img.getWidth * scale).toInt
    val h = (img.getHeight * scale).toInt
    val scaled = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB)
    val g = scaled.createGraphics()
    g.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION,
      java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR)
    g.drawImage(img, 0, 0, w, h, null)
    g.dispose()
    scaled
  }

  private def findImageOnScreen(target: BufferedImage, similarity: Double): scala.Option[(Int, Int)] = {
    val screen = captureScreen()
    val scale = getScreenScale

    // Both screen capture and roboto capture produce physical resolution images,
    // so no scaling needed. Scale is only used to convert match coordinates back to logical space.
    println(s"    [debug] screen: ${screen.getWidth}x${screen.getHeight}, " +
      s"target: ${target.getWidth}x${target.getHeight}, " +
      s"display scale: $scale")

    templateMatch(screen, target, similarity, scale)
  }

  // Pixel comparison with tolerance for subpixel rendering and minor theme differences
  private val COLOR_TOLERANCE = 8

  private def pixelMismatch(sp: Int, tp: Int): Boolean = {
    if (((tp >> 24) & 0xFF) < 128) return false // skip transparent template pixels
    val sr = (sp >> 16) & 0xFF; val tr = (tp >> 16) & 0xFF
    val sg = (sp >> 8) & 0xFF;  val tg = (tp >> 8) & 0xFF
    val sb = sp & 0xFF;         val tb = tp & 0xFF
    math.abs(sr - tr) + math.abs(sg - tg) + math.abs(sb - tb) > COLOR_TOLERANCE
  }

  // returns click coordinates in logical (pre-scale) space for Robot.mouseMove
  private def templateMatch(screen: BufferedImage, target: BufferedImage,
                            similarity: Double, scale: Double): scala.Option[(Int, Int)] = {
    val sw = screen.getWidth
    val sh = screen.getHeight
    val tw = target.getWidth
    val th = target.getHeight
    if (tw > sw || th > sh) return scala.None

    val screenPixels = screen.getRGB(0, 0, sw, sh, null, 0, sw)
    val targetPixels = target.getRGB(0, 0, tw, th, null, 0, tw)
    var opaquePixels = 0
    var i = 0
    while (i < targetPixels.length) {
      if (((targetPixels(i) >> 24) & 0xFF) >= 128) opaquePixels += 1
      i += 1
    }
    val totalPixels = if (opaquePixels > 0) opaquePixels else tw * th
    val threshold = ((1.0 - similarity) * totalPixels).toInt

    val step = math.max(1, scale.toInt)
    val nThreads = Runtime.getRuntime.availableProcessors
    val bandHeight = math.max(th, (sh - th + nThreads - 1) / nThreads)

    import java.util.concurrent.atomic.AtomicInteger
    import java.util.concurrent.{Callable, Executors}

    val bestMismatch = new AtomicInteger(totalPixels + 1)
    val bestCoord = new java.util.concurrent.atomic.AtomicLong(-1L) // encode x,y

    val pool = Executors.newFixedThreadPool(nThreads)
    val tasks = new java.util.ArrayList[Callable[Unit]]()

    var bandStart = 0
    while (bandStart <= sh - th) {
      val syStart = bandStart
      val syEnd = math.min(bandStart + bandHeight, sh - th)
      tasks.add(new Callable[Unit] {
        def call(): Unit = {
          var sy = syStart
          while (sy <= syEnd) {
            var sx = 0
            while (sx <= sw - tw) {
              // first row quick check
              var mismatch = 0
              val screenRowOffset0 = sy * sw + sx
              var col = 0
              while (col < tw && mismatch <= threshold) {
                if (pixelMismatch(screenPixels(screenRowOffset0 + col), targetPixels(col))) {
                  mismatch += 1
                }
                col += 1
              }
              // remaining rows
              if (mismatch <= threshold) {
                var row = 1
                while (row < th && mismatch <= threshold) {
                  val screenRowOffset = (sy + row) * sw + sx
                  val targetRowOffset = row * tw
                  col = 0
                  while (col < tw && mismatch <= threshold) {
                    if (pixelMismatch(screenPixels(screenRowOffset + col), targetPixels(targetRowOffset + col))) {
                      mismatch += 1
                    }
                    col += 1
                  }
                  row += 1
                }
              }
              if (mismatch <= threshold) {
                var current = bestMismatch.get()
                while (mismatch < current) {
                  if (bestMismatch.compareAndSet(current, mismatch)) {
                    val lx = ((sx + tw / 2) / scale).toInt
                    val ly = ((sy + th / 2) / scale).toInt
                    bestCoord.set((lx.toLong << 32) | (ly.toLong & 0xFFFFFFFFL))
                  }
                  current = bestMismatch.get()
                }
              }
              sx += step
            }
            sy += step
          }
        }
      })
      bandStart += bandHeight + 1
    }

    try {
      val futures = pool.invokeAll(tasks)
      for (i <- 0 until futures.size()) futures.get(i).get()
    } finally {
      pool.shutdown()
    }

    val coord = bestCoord.get()
    if (coord >= 0) {
      val rx = (coord >> 32).toInt
      val ry = (coord & 0xFFFFFFFFL).toInt
      println(s"    [debug] best mismatch: ${bestMismatch.get()} / $totalPixels (threshold: $threshold)")
      scala.Some((rx, ry))
    } else {
      println(s"    [debug] best mismatch: ${bestMismatch.get()} / $totalPixels (threshold: $threshold)")
      // Dump screen capture to /tmp for debugging
      val debugFile = new java.io.File("/tmp/roboto-debug-screen.png")
      ImageIO.write(screen, "png", debugFile)
      println(s"    [debug] screen saved to ${debugFile.getAbsolutePath}")
      scala.None
    }
  }

  // -- Key Code Mappings --

  private def keyToKeyCode(key: Key.Type): Int = key match {
    case Key.Enter => KeyEvent.VK_ENTER
    case Key.Escape => KeyEvent.VK_ESCAPE
    case Key.Tab => KeyEvent.VK_TAB
    case Key.Space => KeyEvent.VK_SPACE
    case Key.Backspace => KeyEvent.VK_BACK_SPACE
    case Key.Delete => KeyEvent.VK_DELETE
    case Key.Up => KeyEvent.VK_UP
    case Key.Down => KeyEvent.VK_DOWN
    case Key.Left => KeyEvent.VK_LEFT
    case Key.Right => KeyEvent.VK_RIGHT
    case Key.Home => KeyEvent.VK_HOME
    case Key.End => KeyEvent.VK_END
    case Key.PageUp => KeyEvent.VK_PAGE_UP
    case Key.PageDown => KeyEvent.VK_PAGE_DOWN
    case Key.F1 => KeyEvent.VK_F1
    case Key.F2 => KeyEvent.VK_F2
    case Key.F3 => KeyEvent.VK_F3
    case Key.F4 => KeyEvent.VK_F4
    case Key.F5 => KeyEvent.VK_F5
    case Key.F6 => KeyEvent.VK_F6
    case Key.F7 => KeyEvent.VK_F7
    case Key.F8 => KeyEvent.VK_F8
    case Key.F9 => KeyEvent.VK_F9
    case Key.F10 => KeyEvent.VK_F10
    case Key.F11 => KeyEvent.VK_F11
    case Key.F12 => KeyEvent.VK_F12
  }

  private def modToKeyCode(mod: Modifier.Type): Int = mod match {
    case Modifier.Ctrl => KeyEvent.VK_CONTROL
    case Modifier.Shift => KeyEvent.VK_SHIFT
    case Modifier.Alt => KeyEvent.VK_ALT
    case Modifier.Meta => KeyEvent.VK_META
  }

  private def buttonToMask(button: MouseButton.Type): Int = button match {
    case MouseButton.Left => InputEvent.BUTTON1_DOWN_MASK
    case MouseButton.Middle => InputEvent.BUTTON2_DOWN_MASK
    case MouseButton.Right => InputEvent.BUTTON3_DOWN_MASK
  }

  private def charToKeyCode(c: Char): Int = c match {
    case c if c >= 'a' && c <= 'z' => KeyEvent.VK_A + (c - 'a')
    case c if c >= 'A' && c <= 'Z' => KeyEvent.VK_A + (c - 'A')
    case c if c >= '0' && c <= '9' => KeyEvent.VK_0 + (c - '0')
    case ' ' => KeyEvent.VK_SPACE
    case '.' => KeyEvent.VK_PERIOD
    case ',' => KeyEvent.VK_COMMA
    case ';' => KeyEvent.VK_SEMICOLON
    case ':' => KeyEvent.VK_SEMICOLON  // shift+;
    case '\'' => KeyEvent.VK_QUOTE
    case '"' => KeyEvent.VK_QUOTE      // shift+'
    case '-' => KeyEvent.VK_MINUS
    case '_' => KeyEvent.VK_MINUS      // shift+-
    case '=' => KeyEvent.VK_EQUALS
    case '+' => KeyEvent.VK_EQUALS     // shift+=
    case '/' => KeyEvent.VK_SLASH
    case '?' => KeyEvent.VK_SLASH      // shift+/
    case '\\' => KeyEvent.VK_BACK_SLASH
    case '|' => KeyEvent.VK_BACK_SLASH // shift+\
    case '[' => KeyEvent.VK_OPEN_BRACKET
    case '{' => KeyEvent.VK_OPEN_BRACKET  // shift+[
    case ']' => KeyEvent.VK_CLOSE_BRACKET
    case '}' => KeyEvent.VK_CLOSE_BRACKET // shift+]
    case '`' => KeyEvent.VK_BACK_QUOTE
    case '~' => KeyEvent.VK_BACK_QUOTE   // shift+`
    case '!' => KeyEvent.VK_1         // shift+1
    case '@' => KeyEvent.VK_2         // shift+2
    case '#' => KeyEvent.VK_3         // shift+3
    case '$' => KeyEvent.VK_4         // shift+4
    case '%' => KeyEvent.VK_5         // shift+5
    case '^' => KeyEvent.VK_6         // shift+6
    case '&' => KeyEvent.VK_7         // shift+7
    case '*' => KeyEvent.VK_8         // shift+8
    case '(' => KeyEvent.VK_9         // shift+9
    case ')' => KeyEvent.VK_0         // shift+0
    case '\n' => KeyEvent.VK_ENTER
    case '\t' => KeyEvent.VK_TAB
    case _ => -1
  }

  private val shiftChars: scala.collection.immutable.Set[Char] = scala.collection.immutable.Set(
    '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')',
    '_', '+', '{', '}', '|', ':', '"', '?', '<', '>'
  )
}
