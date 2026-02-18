package org.sireum.cli

import org.sireum._
import org.sireum.hamr.codegen.common.reporting.IntegrationConstraintReporting.{GclIntegrationConstraint, Smt2QueryResult}
import org.sireum.hamr.codegen.common.reporting.{IntegrationConstraintJSON, IntegrationConstraintReporting}
import org.sireum.hamr.sysml.FrontEnd
import org.sireum.hamr.sysml.integration.IntegrationConstraints
import org.sireum.hamr.sysml.integration.IntegrationConstraints.IntegrationConnection
import org.sireum.logika.{Logika, Smt2Query}
import org.sireum.message.{Position, Reporter}

import java.util.concurrent.atomic.AtomicLong

object HAMR_Ext {

  def getIntegrationConstraintReporter(integrationOnly: B,
                                       integrationConnection: IntegrationConnection,
                                       r: Reporter): logika.Logika.Reporter = {
    r match {
      case h: HamrIntegrationConstraintReporter => return h
      case l: Sireum.Rep =>
        return new org.sireum.cli.HamrIntegrationConstraintReporter(
          conn = integrationConnection,
          integrationOnly = integrationOnly,
          feedbackDirOpt = l.feedbackDirOpt,
          logPc = l.logPc,
          logRawPc = l.logRawPc,
          logVc = l.logPc,
          logDetailedInfo = l.logDetailedInfo,
          stats = l.stats,
          nv = l.nv,
          ns = l.ns,
          vm = l.vm,
          nm = l.nm)
    }
  }
}

class HamrIntegrationConstraintReporter(val conn: IntegrationConnection,
                                        val integrationOnly: B,
                                        feedbackDirOpt: Option[Os.Path],
                                        logPc: B,
                                        logRawPc: B, logVc: B,
                                        logDetailedInfo: B, stats: B,
                                        nv: AtomicLong = new AtomicLong(0),
                                        ns: AtomicLong = new AtomicLong(0),
                                        vm: AtomicLong = new AtomicLong(0),
                                        nm: AtomicLong = new AtomicLong(0)) extends Sireum.Rep(
  feedbackDirOpt = feedbackDirOpt,
  logPc = logPc,
  logRawPc = logRawPc,
  logVc = logVc,
  logDetailedInfo = logDetailedInfo,
  stats = stats,
  stateFeedback = !integrationOnly,
  queryFeedback = !integrationOnly,
  coverageFeedback = !integrationOnly,
  informFeedback = !integrationOnly,
  nv = nv,
  ns = ns,
  vm = vm,
  nm = nm) {

  override def empty: Logika.Reporter = {
    val r = new HamrIntegrationConstraintReporter(conn, integrationOnly, feedbackDirOpt, logPc, logRawPc, logVc, logDetailedInfo, stats, nv, ns, vm, nm)
    r.collectStats = stats
    r
  }

  override def error(posOpt: Option[Position], kind: String, message: String): Unit = {
    super.error(posOpt, kind, message)
  }

  override def query(pos: Position, title: String, isSat: B, time: Z, forceReport: B, detailElided: B, r: Smt2Query.Result): Unit = {
    super.query(pos, title, isSat, time, forceReport, detailElided, r)

    feedbackDirOpt match {
      case Some(d) =>
        val oo = ops.StringOps(title)
        val subTitle = oo.substring(0, oo.stringIndexOf(" at ["))

        val (smt2Result, logkikaMsg): (Smt2QueryResult.Type, String) = r.kind match {
          case Smt2Query.Result.Kind.Unsat => (Smt2QueryResult.Unsat, "")
          case Smt2Query.Result.Kind.Sat => (Smt2QueryResult.Sat, s"Invalid ${ops.StringOps(title).firstToLower}")
          case Smt2Query.Result.Kind.Unknown => (Smt2QueryResult.Unknown, s"Could not deduce that the ${ops.StringOps(title).firstToLower} holds")
          case Smt2Query.Result.Kind.Timeout => (Smt2QueryResult.Timeout, s"Timed out when deducing that the ${ops.StringOps(title).firstToLower} holds")
          case Smt2Query.Result.Kind.Error => (Smt2QueryResult.Error, s"Error encountered when deducing that the ${ops.StringOps(title).firstToLower} holds\n${r.info}")
        }

        if (!isSat && (conn.title == subTitle || smt2Result != Smt2QueryResult.Unsat)) {
          val srcIC: Option[GclIntegrationConstraint] = {
            conn.srcGclSpec match {
              case Some(a) => Some(GclIntegrationConstraint(id = a.id, descriptor = a.descriptor, position = a.posOpt.get))
              case _ => None()
            }
          }
          val dstIC = GclIntegrationConstraint(id = conn.dstGclSpec.get.id, descriptor = conn.dstGclSpec.get.descriptor, position = conn.dstGclSpec.get.posOpt.get)
          val ic = IntegrationConstraintReporting.IntegrationConstraint(
            srcPort = conn.srcPortExp.prettyST.render,
            srcPortPos = conn.srcPort.posOpt.get,
            srcPortIntegrationConstraint = srcIC,

            dstPort = conn.dstPortExp.prettyST.render,
            dstPortPos = conn.dstPort.posOpt.get,

            // hamr only checks integration constraints when the dst port has an integration constraint
            dstPortIntegrationConstraint = dstIC,

            connectionMidPoint = conn.connectionMidPoint._2.get,

            claim = conn.claim.prettyST.render,

            smt2QueryResult = smt2Result,

            logikaMessage = logkikaMsg,

            smt2Query = r.query
          )

          val content = IntegrationConstraintJSON.fromIntegrationConstraintReportingIntegrationConstraint(ic, F)

          val idir = d / "integration_constraints"

          val o = ops.StringOps(conn.title).replaceAllChars(' ', '_')

          val fname = idir / s"${o}_at_${conn.connectionMidPoint._2.get.beginLine}.json"
          fname.writeOver(content)
          println(s"Wrote: $fname")
        }
      case _ =>
    }
  }
}