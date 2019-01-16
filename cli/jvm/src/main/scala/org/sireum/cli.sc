import org.sireum._
import org.sireum.cli.CliOpt._

Group(
  name = "sireum",
  description = "",
  header =
    st"""Sireum: A High-Assurance Software Development Platform
        |(c) 2019, SAnToS Laboratory, Kansas State University""".render,
  unlisted = F,
  subs = ISZ(org.sireum.lang.cli.group, org.sireum.tools.cli.group)
)
