/*
 Copyright (c) 2019, Robby, Kansas State University
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

import mill._
import mill.scalalib._
import org.sireum.mill.SireumModule._

trait Module extends JvmOnly {

  final override def crossDeps =
    if (isSourceDep) Seq(alirObject, transpilersCObject, toolsObject, logikaObject, hamrCodegenObject)
    else Seq()

  final override def ivyDeps = T {
    if (isSourceDep) Agg.empty
    else Agg(
      jpLatest(isCross = false, "sireum", "alir"),
      jpLatest(isCross = false, "sireum", "transpilers", "c"),
      jpLatest(isCross = false, "sireum", "tools"),
      jpLatest(isCross = false, "sireum", "logika"),
      jpLatest(isCross = false, "sireum", "phantom")
    )
  }

  final override def deps =
    if (isSourceDep) Seq(phantomObject)
    else Seq()

  final override def testFrameworks = Seq()

  final override def testIvyDeps = Agg.empty

  final override def scalacPluginIvyDeps = testScalacPluginIvyDeps

  final override def testScalacPluginIvyDeps = Agg(
    ivy"org.sireum::scalac-plugin:$scalacPluginVersion"
  )

  def alirObject: CrossJvmJsPublish

  def transpilersCObject: CrossJvmJsPublish

  def toolsObject: CrossJvmJsPublish

  def logikaObject: CrossJvmJsPublish

  def phantomObject: JvmOnly
  
  def hamrCodegenObject: CrossJvmJsPublish

  final override def mainClass = Some("org.sireum.Sireum")
}
