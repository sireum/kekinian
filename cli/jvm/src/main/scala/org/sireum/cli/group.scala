package org.sireum.cli

import org.sireum._
import org.sireum.cli.CliOpt._

object group {

  val main: Group = Group(
    name = "sireum",
    description = "",
    header =
      st"""Sireum: A High-Assurance Software Development Platform 2
          |(c) 2018, SAnToS Laboratory, Kansas State University""".render,
    unlisted = F,
    subs = ISZ(org.sireum.lang.cli.group, org.sireum.tools.cli.group)
  )

}
