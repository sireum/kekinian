// #Sireum
import org.sireum._
import org.sireum.cli.CliOpt._

val fmideTool: Tool = Tool(
  name = "fmide",
  command = "fmide",
  description = "FMIDE Installer",
  header = "FMIDE Installer",
  usage = "<option>* ( fixed | latest )",
  usageDescOpt = None(),
  opts = ISZ(
    Opt(name = "agree", longKey = "agree", shortKey = None(),
      tpe = Type.Str(sep = None(), default = Some("agree_2.7.0")), description = "AGREE version"),
    Opt(name = "briefcase", longKey = "briefcase", shortKey = None(),
      tpe = Type.Str(sep = None(), default = Some("briefcase_0.5.1")), description = "BriefCASE version"),
    Opt(name = "eclipse", longKey = "eclipse", shortKey = None(),
      tpe = Type.Str(sep = None(), default = Some("2020-06")), description = "Eclipse release version"),
    Opt(name = "hamr", longKey = "hamr", shortKey = None(),
      tpe = Type.Str(sep = None(), default = Some("CASE-Tool-Assessment-4")), description = "Sireum HAMR version"),
    Opt(name = "osate", longKey = "osate", shortKey = None(),
      tpe = Type.Str(sep = None(), default = Some("2.9.0-vfinal")), description = "OSATE version"),
    Opt(name = "resolute", longKey = "resolute", shortKey = None(),
      tpe = Type.Str(sep = None(), default = Some("resolute_2.7.1")), description = "Resolute version"),
  ),
  groups = ISZ()
)

println(org.sireum.cli.JSON.fromCliOpt(fmideTool, T))
