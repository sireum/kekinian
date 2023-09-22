package org.sireum.cli.slangcheck

import org.sireum._

trait  TestUtil {
  val isCI: B = Os.env("GITLAB_CI").nonEmpty || Os.env("GITHUB_ACTIONS").nonEmpty || Os.env("BUILD_ID").nonEmpty

  def resourceDir: Os.Path

  val willingToWait: B = isCI || (Os.env("USER") match {
    case Some(string"belt") => true
    case Some(string"deck") => true
    case Some(string"jdlegg") => true
    case _ => false
  })

  def copy(s: String, resultDirSuffix: String, filter: Os.Path => B = x => T): Os.Path = {
    val srcDir = resourceDir / s
    val resultDir = resourceDir / s"${s}_${resultDirSuffix}"
    resultDir.removeAll()

    for (r <- Os.Path.walk(srcDir, F, F, filter)) {
      val dest = resultDir / srcDir.relativize(r).value
      r.copyOverTo(dest)
    }
    return resultDir
  }

  def getExpectedDir(resultsDir: Os.Path): Os.Path =
    return resultsDir.up / (ops.StringOps(resultsDir.name).replaceAllLiterally("_results", ""))

  def compare(results: Os.Path, filter: Os.Path => B = _ => T): B = {
    val expected = getExpectedDir(results)

    def toMap(dir: Os.Path): Map[String, Os.Path] = {
      var ret = Map.empty[String, Os.Path]

      def iter(d: Os.Path): Unit = {
        if (d.isFile && filter(d)) ret = ret + dir.relativize(d).value ~> d
        else for (dd <- d.list) yield iter(dd)
      }

      iter(dir)
      return ret
    }

    val eMap = toMap(expected)
    val rMap = toMap(results)

    def compareKeys(a: Set[String], b: Set[String], dir: Os.Path): B = {
      val diff = a -- b.elements
      for (d <- diff.elements) {
        println(s"Couldn't match ${(dir / d).toUri}")
      }
      return diff.isEmpty
    }

    val (eSet, rSet) = (Set.empty ++ eMap.keys, Set.empty ++ rMap.keys)
    var sameContent = compareKeys(eSet, rSet, expected) & compareKeys(rSet, eSet, results)

    if (sameContent) {
      for (e <- eMap.keys) {
        val ef = expected / e
        val rf = results / e
        val expectedText = ef.read
        val resultsText = rf.read
        if (expectedText != resultsText) {
          sameContent = F
          val dmp = new DiffMatchPatch()
          println(s"File content differs: ${rf.toUri}")
          println(dmp.patch_toText(dmp.patch_make(expectedText.native, resultsText.native)))
        }
      }
    }
    return sameContent
  }
}
