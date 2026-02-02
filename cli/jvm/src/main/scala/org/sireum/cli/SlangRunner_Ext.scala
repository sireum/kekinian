/*
 Copyright (c) 2017-2026,Robby, Kansas State University
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
// Auto-generated
package org.sireum.cli

import org.sireum._
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap

import Reflection._

object SlangRunner_Ext {
  val enumInfo: Info = Info(Kind.Enum, ISZ(), ISZ())
  def create: Reflection = new SlangRunner_Ext
}

import SlangRunner_Ext._

class SlangRunner_Ext extends Reflection_Ext {

  private lazy val nameMap: Int2ObjectOpenHashMap[Reflection.Info] = {
    val r = new Int2ObjectOpenHashMap[Info](1)
    r.put(0x576AC189, info0) // objectOrTypeKey("org.sireum.Sireum").value
    r
  }

  private lazy val method0Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any](16)
    r.put(0xFB0C1C5BB32AB4D9L, _ => org.sireum.Sireum.initRuntimeLibrary()) // methodKey(T, "org.sireum.Sireum", "initRuntimeLibrary").value
    r.put(0xF5E054D118704914L, _ => org.sireum.Sireum.versions) // methodKey(T, "org.sireum.Sireum", "versions").value
    r.put(0xC3CD893E53079224L, _ => org.sireum.Sireum.javaVer) // methodKey(T, "org.sireum.Sireum", "javaVer").value
    r.put(0x136F71E279E00704L, _ => org.sireum.Sireum.scalaVer) // methodKey(T, "org.sireum.Sireum", "scalaVer").value
    r.put(0x415189CF30209AFFL, _ => org.sireum.Sireum.scalacPluginVer) // methodKey(T, "org.sireum.Sireum", "scalacPluginVer").value
    r.put(0x9B401B1512A3C47FL, _ => org.sireum.Sireum.version) // methodKey(T, "org.sireum.Sireum", "version").value
    r.put(0x876434E6E6438FA5L, _ => org.sireum.Sireum.commitHash) // methodKey(T, "org.sireum.Sireum", "commitHash").value
    r.put(0x6E99765EE088288BL, _ => org.sireum.Sireum.homePathString) // methodKey(T, "org.sireum.Sireum", "homePathString").value
    r.put(0x2DC2458740B8B272L, _ => org.sireum.Sireum.javaHomePathString) // methodKey(T, "org.sireum.Sireum", "javaHomePathString").value
    r.put(0x77E34D986A504A56L, _ => org.sireum.Sireum.scalaHomePathString) // methodKey(T, "org.sireum.Sireum", "scalaHomePathString").value
    r.put(0x8D780FDEB92624CEL, _ => org.sireum.Sireum.commitSha) // methodKey(T, "org.sireum.Sireum", "commitSha").value
    r.put(0xF28F43EF7D1BEECDL, _ => org.sireum.Sireum.platform) // methodKey(T, "org.sireum.Sireum", "platform").value
    r.put(0x441DBA128287F42CL, _ => org.sireum.Sireum.isNative) // methodKey(T, "org.sireum.Sireum", "isNative").value
    r.put(0x550FD78278CD558EL, _ => org.sireum.Sireum.scalacPluginJarPathString) // methodKey(T, "org.sireum.Sireum", "scalacPluginJarPathString").value
    r.put(0xBCCD2D5154CB974FL, _ => org.sireum.Sireum.sireumJarPathString) // methodKey(T, "org.sireum.Sireum", "sireumJarPathString").value
    r.put(0x1FCDACED89770CA8L, _ => org.sireum.Sireum.nativ()) // methodKey(T, "org.sireum.Sireum", "nativ").value
    r
  }

  private lazy val method1Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any](1)
    r.put(0xC8ADF2C9B6AD847FL, _ => (o1: Any) => org.sireum.Sireum.run(X(o1))) // methodKey(T, "org.sireum.Sireum", "run").value
    r
  }

  private lazy val method2Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any](3)
    r.put(0x0E94BB2EEE469936L, _ => (o1: Any) => (o2: Any) => org.sireum.Sireum.proc(X(o1), X(o2))) // methodKey(T, "org.sireum.Sireum", "proc").value
    r.put(0xE50AAFB53DB03B57L, _ => (o1: Any) => (o2: Any) => org.sireum.Sireum.procCheck(X(o1), X(o2))) // methodKey(T, "org.sireum.Sireum", "procCheck").value
    r.put(0xBA9CED93A12DD487L, _ => (o1: Any) => (o2: Any) => org.sireum.Sireum.runWithReporter(X(o1), X(o2))) // methodKey(T, "org.sireum.Sireum", "runWithReporter").value
    r
  }

  private lazy val method3Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any](1)
    r.put(0xD58358E378E71AC1L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Sireum.runWithInputAndReporter(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Sireum", "runWithInputAndReporter").value
    r
  }

  private def illegalReflection(title: String, isInObject: B, owner: String, name: String): Unit = {
    halt(s"$title reflection $owner${if (isInObject) "." else "#"}$name")
  }

  override def info(name: String): Option[Info] = {
    val r = nameMap.get(objectOrTypeKey(name).value)
    if (r == null) None() else Some(r)
  }

  override def classNameOf[T](o: T): Option[String] = {
    o match {
      case _ => return None()
    }
  }

  override def invoke0[T, R](owner: String, name: String, receiver: T): R = {
    val isInObject = receiver == null
    val f = method0Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver)))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke1[T, T1, R](owner: String, name: String, receiver: T, o1: T1): R = {
    val isInObject = receiver == null
    val f = method1Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke2[T, T1, T2, R](owner: String, name: String, receiver: T, o1: T1, o2: T2): R = {
    val isInObject = receiver == null
    val f = method2Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke3[T, T1, T2, T3, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3): R = {
    val isInObject = receiver == null
    val f = method3Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  def info0 = Info( // org.sireum.Sireum
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "initRuntimeLibrary", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "proc", params = ISZ("p", "reporter")),
      Method(isInObject = true, isByName = F, name = "procCheck", params = ISZ("p", "reporter")),
      Method(isInObject = true, isByName = F, name = "run", params = ISZ("args")),
      Method(isInObject = true, isByName = F, name = "runWithInputAndReporter", params = ISZ("args", "input", "reporter")),
      Method(isInObject = true, isByName = F, name = "runWithReporter", params = ISZ("args", "reporter")),
      Method(isInObject = true, isByName = T, name = "versions", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "javaVer", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "scalaVer", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "scalacPluginVer", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "version", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "commitHash", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "homePathString", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "javaHomePathString", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "scalaHomePathString", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "commitSha", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "platform", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "isNative", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "scalacPluginJarPathString", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "sireumJarPathString", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "nativ", params = ISZ())
    )
  )

  @inline def X[T](o: Any): T = o.asInstanceOf[T]

  override def string: String = "SlangRunner_Ext"
}