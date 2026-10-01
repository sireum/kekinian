package org.sireum.cli.slangcheck

import org.scalatest.DoNotDiscover
import org.sireum._
import org.sireum.test.TestSuite

@DoNotDiscover
class A extends TestSuite {

  "test" in {
    val s = "proyek logika --timeout 300 --log-detailed-info --sat --sat-timeout ./hvac-project ./hvac-project/src/main/bridge/equiv_checker/proofs/root-hvac-visibility-priority-supervisor-hvacsup-a6f3d66458/04-a6f3d664584e-sat-complement.scala"
    val args: Array[Predef.String] = s.split(" ")
    Sireum.main(args)
  }
}
