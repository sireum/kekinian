// #Sireum
import org.sireum._
import org.sireum.cli.CliOpt._

val fmideTool: Tool = Tool(
  name = "fmide",
  command = "fmide",
  description = "FMIDE Installer",
  header = "FMIDE Installer",
  usage = "<option>* [ fixed | latest ]",
  usageDescOpt = None(),
  opts = ISZ(
    Opt(name = "awas", longKey = "awas", shortKey = None(),
      tpe = Type.Str(sep = None(), default = Some("1.2022.08221314.0e4052e")), description = "AWAS version"),
    Opt(name = "agree", longKey = "agree", shortKey = None(),
      tpe = Type.Str(sep = None(), default = Some("agree_2.9.1")), description = "AGREE version"),
    Opt(name = "briefcase", longKey = "briefcase", shortKey = None(),
      tpe = Type.Str(sep = None(), default = Some("briefcase_0.8.0")), description = "BriefCASE version"),
    Opt(name = "eclipse", longKey = "eclipse", shortKey = None(),
      tpe = Type.Str(sep = None(), default = Some("2021-03")), description = "Eclipse release version"),
    Opt(name = "gumbo", longKey = "gumbo", shortKey = None(),
      tpe = Type.Str(sep = None(), default = Some("1.2022.08231211.b12db9b")), description = "Sireum GUMBO version"),
    Opt(name = "hamr", longKey = "hamr", shortKey = None(),
      tpe = Type.Str(sep = None(), default = Some("1.2022.08221314.0e4052e")), description = "Sireum HAMR version"),
    Opt(name = "osate", longKey = "osate", shortKey = None(),
      tpe = Type.Str(sep = None(), default = Some("2.10.2-vfinal")), description = "OSATE version"),
    Opt(name = "resolute", longKey = "resolute", shortKey = None(),
      tpe = Type.Str(sep = None(), default = Some("resolute_3.0.0")), description = "Resolute version"),
    Opt(name = "verbose", longKey = "verbose", shortKey = Some('v'),
      tpe = Type.Flag(F), description = "Verbose output")
  ),
  groups = ISZ()
)

println(org.sireum.cli.JSON.fromCliOpt(fmideTool, T))
