// #Sireum
import org.sireum._
import org.sireum.cli.CliOpt._

// the usage field will be placed inside a ST so to get newlines that don't have large
// indentations we need to nest another ST inside that.
val tqs: String = "\"\"\""
val usage: String =
  st"""$${st$tqs
      ||<option>* [ fixed | latest ]
      ||
      ||'latest' attempts to install the most recent versions of the
      ||FMIDE plugins. This is the default behavior. If the installation
      ||fails due to missing requirements then use the 'fixed' argument
      ||which installs the default versions of the plugins$tqs.render}""".render

val fmideTool: Tool = Tool(
  name = "fmide",
  command = "fmide",
  description = "FMIDE Installer",
  header = "FMIDE Installer",
  usage = usage,
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
  ),
  groups = ISZ(
    OptGroup(name = "Installation", opts = ISZ(
      Opt(name = "existingInstall", longKey = "existing-install", shortKey = None(),
        tpe = Type.Path(multiple = F, default = None()),
        description="Path to an existing OSATE installation where the FMIDE plugins will be installed/updated. The '--osate' option will be ignored if provided"),
      Opt(name = "verbose", longKey = "verbose", shortKey = Some('v'),
        tpe = Type.Flag(F), description = "Verbose output"),
      Opt(name = "verbosePlus", longKey = "verbose+", shortKey = None(),
        tpe = Type.Flag(F), description = "Increased verbose output ")
    ))
  )
)

println(org.sireum.cli.JSON.fromCliOpt(fmideTool, T))
