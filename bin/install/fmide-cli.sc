// #Sireum
import org.sireum._
import org.sireum.cli.CliOpt._

val fmideTool: Tool = Tool(
  name = "fmide",
  command = "fmide",
  description = "FMIDE installation manager",
  header = "FMIDE installation manager",
  usage = "<option>*  [ <fmide-tag-name> [ <briefcase-tag-name> ] | nightly ]",
  usageDescOpt = None(),
  opts = ISZ(
  ),
  groups = ISZ()
)

println(org.sireum.cli.JSON.fromCliOpt(fmideTool, T))
