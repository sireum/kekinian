/*
 Copyright (c) 2017-2024, Robby, Kansas State University
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

object SlangRunner_Ext {
  def create: Reflection = new SlangRunner_Ext
}

import Reflection._

class SlangRunner_Ext extends Reflection {

  private lazy val nameMap: Int2ObjectOpenHashMap[Reflection.Info] = {
    val r = new Int2ObjectOpenHashMap[Info](157)
    r.put(0xBF340B69 /* objectOrTypeKey("org.sireum.AssocS.Entries").value */, info0)
    r.put(0x576AC189 /* objectOrTypeKey("org.sireum.Sireum").value */, info1)
    r.put(0x0A395928 /* objectOrTypeKey("org.sireum.ContractUtil").value */, info2)
    r.put(0xC2495125 /* objectOrTypeKey("org.sireum.Graph.Internal").value */, info3)
    r.put(0x00F54577 /* objectOrTypeKey("org.sireum.Hash").value */, info4)
    r.put(0x50D63C7A /* objectOrTypeKey("org.sireum.Jen.Internal").value */, info5)
    r.put(0x754EACAC /* objectOrTypeKey("org.sireum.LibUtil").value */, info6)
    r.put(0x6E5F5F55 /* objectOrTypeKey("org.sireum.LibUtil.IS").value */, info7)
    r.put(0xA793A41C /* objectOrTypeKey("org.sireum.Library").value */, info8)
    r.put(0x0E1A94DA /* objectOrTypeKey("org.sireum.MJen.Internal").value */, info9)
    r.put(0x04D8C3B5 /* objectOrTypeKey("org.sireum.Map.Entries").value */, info10)
    r.put(0xEB44D893 /* objectOrTypeKey("org.sireum.OsProto").value */, info11)
    r.put(0x1C08E5AC /* objectOrTypeKey("org.sireum.Poset.Internal").value */, info12)
    r.put(0x9E01815E /* objectOrTypeKey("org.sireum.Random").value */, info13)
    r.put(0xE94336DC /* objectOrTypeKey("org.sireum.Random.Impl").value */, info14)
    r.put(0x72D7E325 /* objectOrTypeKey("org.sireum.Random.Ext").value */, info15)
    r.put(0x65C0B3B4 /* objectOrTypeKey("org.sireum.Set.Elements").value */, info16)
    r.put(0x2306EA3F /* objectOrTypeKey("org.sireum.UnionFind.Internal").value */, info17)
    r.put(0x5A1EBCEC /* objectOrTypeKey("org.sireum.justification").value */, info18)
    r.put(0xB3F5A778 /* objectOrTypeKey("org.sireum.justification.natded").value */, info19)
    r.put(0x0B82CB8A /* objectOrTypeKey("org.sireum.justification.natded.prop").value */, info20)
    r.put(0xB964ADEE /* objectOrTypeKey("org.sireum.justification.natded.pred").value */, info21)
    r.put(0xB689416D /* objectOrTypeKey("org.sireum.Asm").value */, info22)
    r.put(0x146E7442 /* objectOrTypeKey("org.sireum.Coursier").value */, info23)
    r.put(0x01A0B5C2 /* objectOrTypeKey("org.sireum.GitHub").value */, info24)
    r.put(0xD9DEF21A /* objectOrTypeKey("org.sireum.GitHub.Ext").value */, info25)
    r.put(0xBB04BE7C /* objectOrTypeKey("org.sireum.Os").value */, info26)
    r.put(0x298F2B40 /* objectOrTypeKey("org.sireum.Os.Ext").value */, info27)
    r.put(0x5F9DDA1F /* objectOrTypeKey("org.sireum.Scalafmt").value */, info28)
    r.put(0x97BB94A6 /* objectOrTypeKey("org.sireum.Unit").value */, info29)
    r.put(0xD292453A /* objectOrTypeKey("org.sireum.Nothing").value */, info30)
    r.put(0x4B6BCF82 /* objectOrTypeKey("org.sireum.AssocS").value */, info31)
    r.put(0xF9FC3D12 /* objectOrTypeKey("org.sireum.Bag").value */, info32)
    r.put(0x9D4EC2EB /* objectOrTypeKey("org.sireum.StepId").value */, info33)
    r.put(0xC68F5781 /* objectOrTypeKey("org.sireum.CircularQueue").value */, info34)
    r.put(0x8FB81288 /* objectOrTypeKey("org.sireum.CircularQueue.NoDrop").value */, info35)
    r.put(0x5F444BD4 /* objectOrTypeKey("org.sireum.CircularQueue.DropFront").value */, info36)
    r.put(0xE129FFA4 /* objectOrTypeKey("org.sireum.CircularQueue.DropRear").value */, info37)
    r.put(0x31666B93 /* objectOrTypeKey("org.sireum.Either").value */, info38)
    r.put(0x19E203F0 /* objectOrTypeKey("org.sireum.Either.Left").value */, info39)
    r.put(0xE373A36D /* objectOrTypeKey("org.sireum.Either.Right").value */, info40)
    r.put(0xDFE49387 /* objectOrTypeKey("org.sireum.Graph.Edge").value */, info41)
    r.put(0x7A143155 /* objectOrTypeKey("org.sireum.Graph.Edge.Plain").value */, info42)
    r.put(0x0FBB6850 /* objectOrTypeKey("org.sireum.Graph.Edge.Data").value */, info43)
    r.put(0x047E61CC /* objectOrTypeKey("org.sireum.Graph.Internal.Edge").value */, info44)
    r.put(0x17E44376 /* objectOrTypeKey("org.sireum.Graph.Internal.Edges").value */, info45)
    r.put(0xE619E2A5 /* objectOrTypeKey("org.sireum.Graph.Internal.Edges.Set").value */, info46)
    r.put(0xB788F86D /* objectOrTypeKey("org.sireum.Graph.Internal.Edges.Bag").value */, info47)
    r.put(0x31657A1F /* objectOrTypeKey("org.sireum.Graph.Internal.Edge.Plain").value */, info48)
    r.put(0x158AA86A /* objectOrTypeKey("org.sireum.Graph.Internal.Edge.Data").value */, info49)
    r.put(0x9C2C0C94 /* objectOrTypeKey("org.sireum.Graph").value */, info50)
    r.put(0x883E9A99 /* objectOrTypeKey("org.sireum.HashBag").value */, info51)
    r.put(0xC1C8FDA4 /* objectOrTypeKey("org.sireum.HashMap").value */, info52)
    r.put(0xEBADDB0C /* objectOrTypeKey("org.sireum.HashSBag").value */, info53)
    r.put(0xFB8738A0 /* objectOrTypeKey("org.sireum.HashSMap").value */, info54)
    r.put(0xF13418B2 /* objectOrTypeKey("org.sireum.HashSSet").value */, info55)
    r.put(0x842F9727 /* objectOrTypeKey("org.sireum.HashSet").value */, info56)
    r.put(0x0DB5A38F /* objectOrTypeKey("org.sireum.IndexMap").value */, info57)
    r.put(0x6A9C0C3F /* objectOrTypeKey("org.sireum.Indexable").value */, info58)
    r.put(0x15561D5B /* objectOrTypeKey("org.sireum.Indexable.Pos").value */, info59)
    r.put(0x8F9F62AF /* objectOrTypeKey("org.sireum.Indexable.Isz").value */, info60)
    r.put(0x68CE5E47 /* objectOrTypeKey("org.sireum.Indexable.IszDocInfo").value */, info61)
    r.put(0x1BD55A1E /* objectOrTypeKey("org.sireum.Jen").value */, info62)
    r.put(0x3DE02576 /* objectOrTypeKey("org.sireum.Jen.Internal.ISImpl").value */, info63)
    r.put(0x00F6F10D /* objectOrTypeKey("org.sireum.Jen.Internal.MapImpl").value */, info64)
    r.put(0xA6059042 /* objectOrTypeKey("org.sireum.Jen.Internal.HashMapImpl").value */, info65)
    r.put(0x8BD3A96F /* objectOrTypeKey("org.sireum.Jen.Internal.Filtered").value */, info66)
    r.put(0x6FC3A2A2 /* objectOrTypeKey("org.sireum.Jen.Internal.Mapped").value */, info67)
    r.put(0x6E1636F5 /* objectOrTypeKey("org.sireum.Jen.Internal.FlatMapped").value */, info68)
    r.put(0x74EEC5C0 /* objectOrTypeKey("org.sireum.Jen.Internal.Sliced").value */, info69)
    r.put(0x7BAD7D88 /* objectOrTypeKey("org.sireum.Jen.Internal.TakeWhile").value */, info70)
    r.put(0x5B1FD227 /* objectOrTypeKey("org.sireum.Jen.Internal.DropWhile").value */, info71)
    r.put(0xCB753410 /* objectOrTypeKey("org.sireum.Jen.Internal.ZipWithIndexed").value */, info72)
    r.put(0xBB793B19 /* objectOrTypeKey("org.sireum.Jen.Internal.Zipped").value */, info73)
    r.put(0x007A1385 /* objectOrTypeKey("org.sireum.Jen.Internal.Concat").value */, info74)
    r.put(0x1200DEFB /* objectOrTypeKey("org.sireum.Jen.Internal.Product").value */, info75)
    r.put(0x22D9BA60 /* objectOrTypeKey("org.sireum.MBox").value */, info76)
    r.put(0x85596090 /* objectOrTypeKey("org.sireum.MBox2").value */, info77)
    r.put(0x19620664 /* objectOrTypeKey("org.sireum.MBox3").value */, info78)
    r.put(0x17E6C186 /* objectOrTypeKey("org.sireum.MBox4").value */, info79)
    r.put(0x49391657 /* objectOrTypeKey("org.sireum.MBox5").value */, info80)
    r.put(0x90C028AA /* objectOrTypeKey("org.sireum.MBox6").value */, info81)
    r.put(0x00547F85 /* objectOrTypeKey("org.sireum.MBox7").value */, info82)
    r.put(0x4010E999 /* objectOrTypeKey("org.sireum.MBox8").value */, info83)
    r.put(0x4367D13F /* objectOrTypeKey("org.sireum.MBox9").value */, info84)
    r.put(0xD087D69D /* objectOrTypeKey("org.sireum.MBox10").value */, info85)
    r.put(0xAB355F5F /* objectOrTypeKey("org.sireum.MBox11").value */, info86)
    r.put(0x5695E90D /* objectOrTypeKey("org.sireum.MBox12").value */, info87)
    r.put(0x6A36A51C /* objectOrTypeKey("org.sireum.MBox13").value */, info88)
    r.put(0x9479D861 /* objectOrTypeKey("org.sireum.MBox14").value */, info89)
    r.put(0x88C35A8D /* objectOrTypeKey("org.sireum.MBox15").value */, info90)
    r.put(0x42D49241 /* objectOrTypeKey("org.sireum.MBox16").value */, info91)
    r.put(0xC36F65D5 /* objectOrTypeKey("org.sireum.MBox17").value */, info92)
    r.put(0x73158A8A /* objectOrTypeKey("org.sireum.MBox18").value */, info93)
    r.put(0x8C268331 /* objectOrTypeKey("org.sireum.MBox19").value */, info94)
    r.put(0x75BBCAA6 /* objectOrTypeKey("org.sireum.MBox20").value */, info95)
    r.put(0x043C4FA8 /* objectOrTypeKey("org.sireum.MBox21").value */, info96)
    r.put(0x33B3CA6B /* objectOrTypeKey("org.sireum.MBox22").value */, info97)
    r.put(0x789B9607 /* objectOrTypeKey("org.sireum.MEither").value */, info98)
    r.put(0x8E5C2AF7 /* objectOrTypeKey("org.sireum.MEither.Left").value */, info99)
    r.put(0xA8BFF8F9 /* objectOrTypeKey("org.sireum.MEither.Right").value */, info100)
    r.put(0xE0B7A907 /* objectOrTypeKey("org.sireum.MJen").value */, info101)
    r.put(0x89D55DB4 /* objectOrTypeKey("org.sireum.MJen.Internal.ISImpl").value */, info102)
    r.put(0x604300B1 /* objectOrTypeKey("org.sireum.MJen.Internal.MSImpl").value */, info103)
    r.put(0xFAF9A5C6 /* objectOrTypeKey("org.sireum.MJen.Internal.MapImpl").value */, info104)
    r.put(0xFBB3388F /* objectOrTypeKey("org.sireum.MJen.Internal.HashMapImpl").value */, info105)
    r.put(0x7593553A /* objectOrTypeKey("org.sireum.MJen.Internal.Filtered").value */, info106)
    r.put(0xA021C7B2 /* objectOrTypeKey("org.sireum.MJen.Internal.Mapped").value */, info107)
    r.put(0xDD7B9281 /* objectOrTypeKey("org.sireum.MJen.Internal.FlatMapped").value */, info108)
    r.put(0x26B896DF /* objectOrTypeKey("org.sireum.MJen.Internal.Sliced").value */, info109)
    r.put(0x04A2D591 /* objectOrTypeKey("org.sireum.MJen.Internal.TakeWhile").value */, info110)
    r.put(0x42A4EB51 /* objectOrTypeKey("org.sireum.MJen.Internal.DropWhile").value */, info111)
    r.put(0x2EB52536 /* objectOrTypeKey("org.sireum.MJen.Internal.ZipWithIndexed").value */, info112)
    r.put(0xF9106E42 /* objectOrTypeKey("org.sireum.MJen.Internal.Zipped").value */, info113)
    r.put(0x95939F2E /* objectOrTypeKey("org.sireum.MJen.Internal.Concat").value */, info114)
    r.put(0x7097D42B /* objectOrTypeKey("org.sireum.MJen.Internal.Product").value */, info115)
    r.put(0xBFA96725 /* objectOrTypeKey("org.sireum.MOption").value */, info116)
    r.put(0x55BD72AC /* objectOrTypeKey("org.sireum.MNone").value */, info117)
    r.put(0xDD7FCF81 /* objectOrTypeKey("org.sireum.MSome").value */, info118)
    r.put(0xDB9D0399 /* objectOrTypeKey("org.sireum.Map").value */, info119)
    r.put(0xC001D0F8 /* objectOrTypeKey("org.sireum.ObjPrinter").value */, info120)
    r.put(0x14C6A50F /* objectOrTypeKey("org.sireum.Option").value */, info121)
    r.put(0xC7F1E051 /* objectOrTypeKey("org.sireum.None").value */, info122)
    r.put(0xAAFF1217 /* objectOrTypeKey("org.sireum.Some").value */, info123)
    r.put(0x49AE4217 /* objectOrTypeKey("org.sireum.OsProto.Path").value */, info124)
    r.put(0x14E10E5F /* objectOrTypeKey("org.sireum.OsProto.Proc.Result").value */, info125)
    r.put(0x81966518 /* objectOrTypeKey("org.sireum.OsProto.Proc").value */, info126)
    r.put(0xDF372A83 /* objectOrTypeKey("org.sireum.Poset").value */, info127)
    r.put(0x773232EE /* objectOrTypeKey("org.sireum.Random.Gen.TestRunner").value */, info128)
    r.put(0x20CD55DB /* objectOrTypeKey("org.sireum.Random.Gen").value */, info129)
    r.put(0x0120E7AB /* objectOrTypeKey("org.sireum.Random.Gen64").value */, info130)
    r.put(0x7F636E7F /* objectOrTypeKey("org.sireum.Random.Gen64Impl").value */, info131)
    r.put(0x775186D0 /* objectOrTypeKey("org.sireum.Random.Impl.SplitMix64").value */, info132)
    r.put(0x0C40779D /* objectOrTypeKey("org.sireum.Random.Impl.Xoshiro256").value */, info133)
    r.put(0x7F11D172 /* objectOrTypeKey("org.sireum.Random.Impl.Xoroshiro128").value */, info134)
    r.put(0x8010C428 /* objectOrTypeKey("org.sireum.Set").value */, info135)
    r.put(0x2B1C8D90 /* objectOrTypeKey("org.sireum.Stack").value */, info136)
    r.put(0x45FD9933 /* objectOrTypeKey("org.sireum.UnionFind").value */, info137)
    r.put(0x8D7F602F /* objectOrTypeKey("org.sireum.CoursierFileInfo").value */, info138)
    r.put(0xBD7F2C41 /* objectOrTypeKey("org.sireum.Coursier.Proxy").value */, info139)
    r.put(0x3CC69620 /* objectOrTypeKey("org.sireum.GitHub.Credential").value */, info140)
    r.put(0xF38C97D4 /* objectOrTypeKey("org.sireum.GitHub.Repository").value */, info141)
    r.put(0xF0EADD92 /* objectOrTypeKey("org.sireum.GitHub.Release").value */, info142)
    r.put(0x2924BD4D /* objectOrTypeKey("org.sireum.GitHub.Asset").value */, info143)
    r.put(0x984F1492 /* objectOrTypeKey("org.sireum.Init.Plugin").value */, info144)
    r.put(0x656AD968 /* objectOrTypeKey("org.sireum.Init").value */, info145)
    r.put(0x9639C557 /* objectOrTypeKey("org.sireum.Os.Path.Impl").value */, info146)
    r.put(0x467528A7 /* objectOrTypeKey("org.sireum.Os.Path.Jen").value */, info147)
    r.put(0xFC765856 /* objectOrTypeKey("org.sireum.Os.Path.MJen").value */, info148)
    r.put(0x88822D20 /* objectOrTypeKey("org.sireum.Os.Proc.LineFilter").value */, info149)
    r.put(0xCA390F1D /* objectOrTypeKey("org.sireum.Os.Proc.FunLineFilter").value */, info150)
    r.put(0xDBA67033 /* objectOrTypeKey("org.sireum.Os.Proc.Result").value */, info151)
    r.put(0xD6DC0267 /* objectOrTypeKey("org.sireum.Os.Proc.Result.Normal").value */, info152)
    r.put(0x3EAB24E6 /* objectOrTypeKey("org.sireum.Os.Proc.Result.Exception").value */, info153)
    r.put(0x8B77F5D3 /* objectOrTypeKey("org.sireum.Os.Proc.Result.Timeout").value */, info154)
    r.put(0x8989ABF6 /* objectOrTypeKey("org.sireum.Os.Proc").value */, info155)
    r.put(0xE8D15C6E /* objectOrTypeKey("org.sireum.Os.Path").value */, info156)
    r
  }

  private lazy val method0Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any](1155)
    r.put(0xFB0C1C5BB32AB4D9L /* methodKey(T, "org.sireum.Sireum", "initRuntimeLibrary").value */, _ => org.sireum.Sireum.initRuntimeLibrary())
    r.put(0xF5E054D118704914L /* methodKey(T, "org.sireum.Sireum", "versions").value */, _ => org.sireum.Sireum.versions)
    r.put(0xC3CD893E53079224L /* methodKey(T, "org.sireum.Sireum", "javaVer").value */, _ => org.sireum.Sireum.javaVer)
    r.put(0x136F71E279E00704L /* methodKey(T, "org.sireum.Sireum", "scalaVer").value */, _ => org.sireum.Sireum.scalaVer)
    r.put(0x415189CF30209AFFL /* methodKey(T, "org.sireum.Sireum", "scalacPluginVer").value */, _ => org.sireum.Sireum.scalacPluginVer)
    r.put(0x9B401B1512A3C47FL /* methodKey(T, "org.sireum.Sireum", "version").value */, _ => org.sireum.Sireum.version)
    r.put(0x876434E6E6438FA5L /* methodKey(T, "org.sireum.Sireum", "commitHash").value */, _ => org.sireum.Sireum.commitHash)
    r.put(0x6E99765EE088288BL /* methodKey(T, "org.sireum.Sireum", "homePathString").value */, _ => org.sireum.Sireum.homePathString)
    r.put(0x2DC2458740B8B272L /* methodKey(T, "org.sireum.Sireum", "javaHomePathString").value */, _ => org.sireum.Sireum.javaHomePathString)
    r.put(0x77E34D986A504A56L /* methodKey(T, "org.sireum.Sireum", "scalaHomePathString").value */, _ => org.sireum.Sireum.scalaHomePathString)
    r.put(0x8D780FDEB92624CEL /* methodKey(T, "org.sireum.Sireum", "commitSha").value */, _ => org.sireum.Sireum.commitSha)
    r.put(0xF28F43EF7D1BEECDL /* methodKey(T, "org.sireum.Sireum", "platform").value */, _ => org.sireum.Sireum.platform)
    r.put(0x441DBA128287F42CL /* methodKey(T, "org.sireum.Sireum", "isNative").value */, _ => org.sireum.Sireum.isNative)
    r.put(0x550FD78278CD558EL /* methodKey(T, "org.sireum.Sireum", "scalacPluginJarPathString").value */, _ => org.sireum.Sireum.scalacPluginJarPathString)
    r.put(0xBCCD2D5154CB974FL /* methodKey(T, "org.sireum.Sireum", "sireumJarPathString").value */, _ => org.sireum.Sireum.sireumJarPathString)
    r.put(0x1FCDACED89770CA8L /* methodKey(T, "org.sireum.Sireum", "nativ").value */, _ => org.sireum.Sireum.nativ())
    r.put(0xC7F143202794CB16L /* methodKey(T, "org.sireum.LibUtil", "setOptions").value */, _ => org.sireum.LibUtil.setOptions)
    r.put(0x2770437707001E59L /* methodKey(T, "org.sireum.Library", "sharedFiles").value */, _ => org.sireum.Library.sharedFiles)
    r.put(0x0D137D659C21744EL /* methodKey(T, "org.sireum.Library", "files").value */, _ => org.sireum.Library.files)
    r.put(0x48DC4AC1CAF34241L /* methodKey(T, "org.sireum.Poset.Internal", "emptySet").value */, _ => org.sireum.Poset.Internal.emptySet)
    r.put(0xDED3A22AA5504011L /* methodKey(T, "org.sireum.Random", "create64").value */, _ => org.sireum.Random.create64)
    r.put(0x67FCFBFFD09DC393L /* methodKey(T, "org.sireum.Random.Ext", "instance").value */, _ => org.sireum.Random.Ext.instance)
    r.put(0x68DBB979A8C0F3E3L /* methodKey(T, "org.sireum.Coursier", "defaultCacheDir").value */, _ => org.sireum.Coursier.defaultCacheDir)
    r.put(0x6CC38C9860E280F6L /* methodKey(T, "org.sireum.Os", "cliArgs").value */, _ => org.sireum.Os.cliArgs)
    r.put(0xCEE866DF844E988CL /* methodKey(T, "org.sireum.Os", "cwd").value */, _ => org.sireum.Os.cwd)
    r.put(0x8BB7902119190DC4L /* methodKey(T, "org.sireum.Os", "envs").value */, _ => org.sireum.Os.envs)
    r.put(0x41D23D46DA893D6DL /* methodKey(T, "org.sireum.Os", "fileSep").value */, _ => org.sireum.Os.fileSep)
    r.put(0xC214995D93B8F73AL /* methodKey(T, "org.sireum.Os", "freeMemory").value */, _ => org.sireum.Os.freeMemory)
    r.put(0xF9EC465B049ED421L /* methodKey(T, "org.sireum.Os", "home").value */, _ => org.sireum.Os.home)
    r.put(0xEC7DB9323B09CA07L /* methodKey(T, "org.sireum.Os", "isLinux").value */, _ => org.sireum.Os.isLinux)
    r.put(0x5A09B80316284B83L /* methodKey(T, "org.sireum.Os", "isMac").value */, _ => org.sireum.Os.isMac)
    r.put(0xD9D4C954179BBD4EL /* methodKey(T, "org.sireum.Os", "isMacArm").value */, _ => org.sireum.Os.isMacArm)
    r.put(0xF9FC3F44DEB5F97DL /* methodKey(T, "org.sireum.Os", "isWinArm").value */, _ => org.sireum.Os.isWinArm)
    r.put(0x3101B846165B6861L /* methodKey(T, "org.sireum.Os", "isWin").value */, _ => org.sireum.Os.isWin)
    r.put(0x2162A94D5C1D8143L /* methodKey(T, "org.sireum.Os", "kind").value */, _ => org.sireum.Os.kind)
    r.put(0x5DD1938BC48065EDL /* methodKey(T, "org.sireum.Os", "lineSep").value */, _ => org.sireum.Os.lineSep)
    r.put(0xE02DE01C74364D6AL /* methodKey(T, "org.sireum.Os", "maxMemory").value */, _ => org.sireum.Os.maxMemory)
    r.put(0x8C3B943F32D64AA1L /* methodKey(T, "org.sireum.Os", "numOfProcessors").value */, _ => org.sireum.Os.numOfProcessors)
    r.put(0xB86A63627E305B55L /* methodKey(T, "org.sireum.Os", "pathSep").value */, _ => org.sireum.Os.pathSep)
    r.put(0xC53EC069E477E334L /* methodKey(T, "org.sireum.Os", "pathSepChar").value */, _ => org.sireum.Os.pathSepChar)
    r.put(0x683F197235B3C132L /* methodKey(T, "org.sireum.Os", "props").value */, _ => org.sireum.Os.props)
    r.put(0x7B2B06A3A9331CFBL /* methodKey(T, "org.sireum.Os", "roots").value */, _ => org.sireum.Os.roots)
    r.put(0x8C8EEEEE9D803AB1L /* methodKey(T, "org.sireum.Os", "sireumHomeOpt").value */, _ => org.sireum.Os.sireumHomeOpt)
    r.put(0x90A1AA721E5FD5D2L /* methodKey(T, "org.sireum.Os", "slashDir").value */, _ => org.sireum.Os.slashDir)
    r.put(0x270D0A80EE586F97L /* methodKey(T, "org.sireum.Os", "temp").value */, _ => org.sireum.Os.temp())
    r.put(0x0283DE424184CE3FL /* methodKey(T, "org.sireum.Os", "tempDir").value */, _ => org.sireum.Os.tempDir())
    r.put(0xE5B225005BEE82ABL /* methodKey(T, "org.sireum.Os", "totalMemory").value */, _ => org.sireum.Os.totalMemory)
    r.put(0x512868A2DFFF0366L /* methodKey(T, "org.sireum.Os.Ext", "cliArgs").value */, _ => org.sireum.Os.Ext.cliArgs)
    r.put(0xF67727BD6B4BFB5AL /* methodKey(T, "org.sireum.Os.Ext", "fileSep").value */, _ => org.sireum.Os.Ext.fileSep)
    r.put(0x75C795413F5DF8E3L /* methodKey(T, "org.sireum.Os.Ext", "lineSep").value */, _ => org.sireum.Os.Ext.lineSep)
    r.put(0x0C2CD76F448C2CF7L /* methodKey(T, "org.sireum.Os.Ext", "pathSep").value */, _ => org.sireum.Os.Ext.pathSep)
    r.put(0x1291511091A5C879L /* methodKey(T, "org.sireum.Os.Ext", "pathSepChar").value */, _ => org.sireum.Os.Ext.pathSepChar)
    r.put(0xACEF8573B2625500L /* methodKey(T, "org.sireum.Os.Ext", "osKind").value */, _ => org.sireum.Os.Ext.osKind)
    r.put(0x25757E71A6138921L /* methodKey(T, "org.sireum.Os.Ext", "roots").value */, _ => org.sireum.Os.Ext.roots)
    r.put(0x53AB3FFC7BF84304L /* methodKey(T, "org.sireum.Os.Ext", "detectSireumHome").value */, _ => org.sireum.Os.Ext.detectSireumHome)
    r.put(0x0FDF2BB7A3EA5DBEL /* methodKey(T, "org.sireum.Os.Ext", "envs").value */, _ => org.sireum.Os.Ext.envs)
    r.put(0x70D223245F2CF55AL /* methodKey(T, "org.sireum.Os.Ext", "freeMemory").value */, _ => org.sireum.Os.Ext.freeMemory)
    r.put(0xE6D80B11AF5BDBA9L /* methodKey(T, "org.sireum.Os.Ext", "maxMemory").value */, _ => org.sireum.Os.Ext.maxMemory)
    r.put(0x24603CB8E6908AAAL /* methodKey(T, "org.sireum.Os.Ext", "numOfProcessors").value */, _ => org.sireum.Os.Ext.numOfProcessors)
    r.put(0xF666343D097F2149L /* methodKey(T, "org.sireum.Os.Ext", "props").value */, _ => org.sireum.Os.Ext.props)
    r.put(0xD26F00F84A190465L /* methodKey(T, "org.sireum.Os.Ext", "slashDir").value */, _ => org.sireum.Os.Ext.slashDir)
    r.put(0x60EAA4F2A4E64BAAL /* methodKey(T, "org.sireum.Os.Ext", "totalMemory").value */, _ => org.sireum.Os.Ext.totalMemory)
    r.put(0x4EFBC222D945FCDDL /* methodKey(T, "org.sireum.Scalafmt", "version").value */, _ => org.sireum.Scalafmt.version)
    r.put(0x677F242B4FAF94AAL /* methodKey(T, "org.sireum.Scalafmt", "minimalConfig").value */, _ => org.sireum.Scalafmt.minimalConfig)
    r.put(0x8046C6A907740B58L /* methodKey(F, "org.sireum.AssocS", "entries").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].entries)
    r.put(0x62BCB0192A28B940L /* methodKey(F, "org.sireum.AssocS", "keys").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].keys)
    r.put(0xC21B0FAD8BEBB5B1L /* methodKey(F, "org.sireum.AssocS", "values").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].values)
    r.put(0x92A3558D12E51570L /* methodKey(F, "org.sireum.AssocS", "keySet").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].keySet)
    r.put(0x4CB17D1AB54562D0L /* methodKey(F, "org.sireum.AssocS", "valueSet").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].valueSet)
    r.put(0x6C073ACE949EED18L /* methodKey(F, "org.sireum.AssocS", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].isEmpty)
    r.put(0xCDF8D0550413FEF0L /* methodKey(F, "org.sireum.AssocS", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].nonEmpty)
    r.put(0x78FFED3F0A646E4CL /* methodKey(F, "org.sireum.AssocS", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].size)
    r.put(0x6329883C58D3B78BL /* methodKey(F, "org.sireum.AssocS", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].string)
    r.put(0x76277DBA56BFBEB6L /* methodKey(F, "org.sireum.Bag", "map").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].map)
    r.put(0xAD5449B003689F50L /* methodKey(F, "org.sireum.Bag", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].size)
    r.put(0x84F49574E9895AB0L /* methodKey(F, "org.sireum.Bag", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].elements)
    r.put(0xFA2128D55B20B854L /* methodKey(F, "org.sireum.Bag", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].isEmpty)
    r.put(0xCA01C47C167AB2EFL /* methodKey(F, "org.sireum.Bag", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].nonEmpty)
    r.put(0x0E9086DBD0E8D949L /* methodKey(F, "org.sireum.Bag", "entries").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].entries)
    r.put(0x45730085E39225BBL /* methodKey(F, "org.sireum.Bag", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].string)
    r.put(0x223C1F06107C2E62L /* methodKey(F, "org.sireum.CircularQueue", "max").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue[Any]].max)
    r.put(0x3D4C892767234BC8L /* methodKey(F, "org.sireum.CircularQueue", "default").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue[Any]].default)
    r.put(0xE7D06D3B33067175L /* methodKey(F, "org.sireum.CircularQueue", "scrub").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue[Any]].scrub)
    r.put(0xD49CD5CEC8E01284L /* methodKey(F, "org.sireum.CircularQueue", "policy").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue[Any]].policy)
    r.put(0xE7318356AC819909L /* methodKey(F, "org.sireum.CircularQueue", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue[Any]].size)
    r.put(0x73C70C0EE547E8CCL /* methodKey(F, "org.sireum.CircularQueue", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue[Any]].isEmpty)
    r.put(0x95A445364D5E454CL /* methodKey(F, "org.sireum.CircularQueue", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue[Any]].nonEmpty)
    r.put(0x4EB7801F271CB48BL /* methodKey(F, "org.sireum.CircularQueue", "isFull").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue[Any]].isFull)
    r.put(0xECC7D595417EDBEAL /* methodKey(F, "org.sireum.CircularQueue", "dequeue").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue[Any]].dequeue())
    r.put(0x81C91BFB08E0863CL /* methodKey(F, "org.sireum.CircularQueue", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue[Any]].elements)
    r.put(0x368E681149492D69L /* methodKey(F, "org.sireum.CircularQueue", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue[Any]].string)
    r.put(0x5DC7A12F41A0195AL /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "max").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].max)
    r.put(0xF040A4E28B79100CL /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "default").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].default)
    r.put(0xCF17D6488B108FBAL /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "scrub").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].scrub)
    r.put(0x9B97E4274520A738L /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "queue").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].queue)
    r.put(0x83635F2544DBB000L /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "front").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].front)
    r.put(0x8AA3A9EC01F6C68CL /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "rear").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].rear)
    r.put(0x2F25A3C1171BE437L /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "numOfElements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].numOfElements)
    r.put(0x4875C30AC81476EDL /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "policy").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].policy)
    r.put(0x5B30B166598F5AE1L /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].size)
    r.put(0x7C4F6842A0CF624AL /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].isEmpty)
    r.put(0x5EDC6B81D4B5BA28L /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].nonEmpty)
    r.put(0x421D06476079545CL /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "isFull").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].isFull)
    r.put(0x8FE9A26F34520CC8L /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "dequeue").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].dequeue())
    r.put(0x0B7C093A31A6B20CL /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].elements)
    r.put(0x501575E7B8021981L /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].string)
    r.put(0xFF40A2140F41420BL /* methodKey(F, "org.sireum.CircularQueue.DropFront", "max").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].max)
    r.put(0xA514EE074D755F3BL /* methodKey(F, "org.sireum.CircularQueue.DropFront", "default").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].default)
    r.put(0xD0A8ACC2B5C4BD19L /* methodKey(F, "org.sireum.CircularQueue.DropFront", "scrub").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].scrub)
    r.put(0xCB2AB9271689BAD4L /* methodKey(F, "org.sireum.CircularQueue.DropFront", "queue").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].queue)
    r.put(0x5602F90ADED609BBL /* methodKey(F, "org.sireum.CircularQueue.DropFront", "front").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].front)
    r.put(0xAFE9BE534536CC65L /* methodKey(F, "org.sireum.CircularQueue.DropFront", "rear").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].rear)
    r.put(0xDEA8748FECD2A64DL /* methodKey(F, "org.sireum.CircularQueue.DropFront", "numOfElements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].numOfElements)
    r.put(0x92BBF2D639C60C74L /* methodKey(F, "org.sireum.CircularQueue.DropFront", "policy").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].policy)
    r.put(0x42B366522E7CD6E6L /* methodKey(F, "org.sireum.CircularQueue.DropFront", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].size)
    r.put(0x1E5194BDEA2CC2BEL /* methodKey(F, "org.sireum.CircularQueue.DropFront", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].isEmpty)
    r.put(0xED587D4B493BFE5DL /* methodKey(F, "org.sireum.CircularQueue.DropFront", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].nonEmpty)
    r.put(0x7F4F15F9B2923C6CL /* methodKey(F, "org.sireum.CircularQueue.DropFront", "isFull").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].isFull)
    r.put(0x5929FC5EB460EB48L /* methodKey(F, "org.sireum.CircularQueue.DropFront", "dequeue").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].dequeue())
    r.put(0x208C4EC36D13AA58L /* methodKey(F, "org.sireum.CircularQueue.DropFront", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].elements)
    r.put(0x9C20899BB57704D3L /* methodKey(F, "org.sireum.CircularQueue.DropFront", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].string)
    r.put(0xBAED923342328B90L /* methodKey(F, "org.sireum.CircularQueue.DropRear", "max").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].max)
    r.put(0x56E1E82040819A7CL /* methodKey(F, "org.sireum.CircularQueue.DropRear", "default").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].default)
    r.put(0xF3508F6EBBD543CAL /* methodKey(F, "org.sireum.CircularQueue.DropRear", "scrub").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].scrub)
    r.put(0x7A1390FB997EA0BCL /* methodKey(F, "org.sireum.CircularQueue.DropRear", "queue").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].queue)
    r.put(0xA2931F54E05A217BL /* methodKey(F, "org.sireum.CircularQueue.DropRear", "front").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].front)
    r.put(0x82E19E80A35BFD5EL /* methodKey(F, "org.sireum.CircularQueue.DropRear", "rear").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].rear)
    r.put(0x0EC733AA128D14B3L /* methodKey(F, "org.sireum.CircularQueue.DropRear", "numOfElements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].numOfElements)
    r.put(0x6170B5145708A8C9L /* methodKey(F, "org.sireum.CircularQueue.DropRear", "policy").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].policy)
    r.put(0xAC44F744B1C7923DL /* methodKey(F, "org.sireum.CircularQueue.DropRear", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].size)
    r.put(0x3333A7E8BBE991D8L /* methodKey(F, "org.sireum.CircularQueue.DropRear", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].isEmpty)
    r.put(0xFE38156A35BC937BL /* methodKey(F, "org.sireum.CircularQueue.DropRear", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].nonEmpty)
    r.put(0xBC06802938A9E29BL /* methodKey(F, "org.sireum.CircularQueue.DropRear", "isFull").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].isFull)
    r.put(0xB9E27A19EF29A998L /* methodKey(F, "org.sireum.CircularQueue.DropRear", "dequeue").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].dequeue())
    r.put(0x4270E60161D4F430L /* methodKey(F, "org.sireum.CircularQueue.DropRear", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].elements)
    r.put(0x083BFBEFA08914F4L /* methodKey(F, "org.sireum.CircularQueue.DropRear", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].string)
    r.put(0xAAE77EAF79958027L /* methodKey(F, "org.sireum.Either", "isLeft").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either[Any, Any]].isLeft)
    r.put(0x2F822A8F70E0269EL /* methodKey(F, "org.sireum.Either", "isRight").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either[Any, Any]].isRight)
    r.put(0xD0717150BABD768FL /* methodKey(F, "org.sireum.Either", "leftOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either[Any, Any]].leftOpt)
    r.put(0x560F20931A6F4D3DL /* methodKey(F, "org.sireum.Either", "left").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either[Any, Any]].left)
    r.put(0x06229B602C8AB6F7L /* methodKey(F, "org.sireum.Either", "rightOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either[Any, Any]].rightOpt)
    r.put(0x52AA8ED57EA119F1L /* methodKey(F, "org.sireum.Either", "right").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either[Any, Any]].right)
    r.put(0x969093BCFCE53975L /* methodKey(F, "org.sireum.Either.Left", "value").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either.Left[Any, Any]].value)
    r.put(0x00B9C41459E4D037L /* methodKey(F, "org.sireum.Either.Left", "isLeft").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either.Left[Any, Any]].isLeft)
    r.put(0x1ECD1DC87C0826B1L /* methodKey(F, "org.sireum.Either.Left", "isRight").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either.Left[Any, Any]].isRight)
    r.put(0x01967E572F6632AFL /* methodKey(F, "org.sireum.Either.Left", "leftOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either.Left[Any, Any]].leftOpt)
    r.put(0x58ED77E0C66452B4L /* methodKey(F, "org.sireum.Either.Left", "left").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either.Left[Any, Any]].left)
    r.put(0x0995385CD20A51CFL /* methodKey(F, "org.sireum.Either.Left", "rightOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either.Left[Any, Any]].rightOpt)
    r.put(0x7081EACF3055BE6FL /* methodKey(F, "org.sireum.Either.Left", "right").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either.Left[Any, Any]].right)
    r.put(0x3751193D4CA11DA1L /* methodKey(F, "org.sireum.Either.Right", "value").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either.Right[Any, Any]].value)
    r.put(0xC540A20143E31C9BL /* methodKey(F, "org.sireum.Either.Right", "isLeft").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either.Right[Any, Any]].isLeft)
    r.put(0xC5F355FBA8C435EEL /* methodKey(F, "org.sireum.Either.Right", "isRight").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either.Right[Any, Any]].isRight)
    r.put(0x8F9EA91CECC1384DL /* methodKey(F, "org.sireum.Either.Right", "leftOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either.Right[Any, Any]].leftOpt)
    r.put(0xB309B00ACA69625DL /* methodKey(F, "org.sireum.Either.Right", "left").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either.Right[Any, Any]].left)
    r.put(0xE36EF0E9793CFDA3L /* methodKey(F, "org.sireum.Either.Right", "rightOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either.Right[Any, Any]].rightOpt)
    r.put(0xD18121A971BE8F5CL /* methodKey(F, "org.sireum.Either.Right", "right").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Either.Right[Any, Any]].right)
    r.put(0x55240CEFBF3EB8CEL /* methodKey(F, "org.sireum.Graph.Edge", "source").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Edge[Any, Any]].source)
    r.put(0x70CBD6CF9812FED1L /* methodKey(F, "org.sireum.Graph.Edge", "dest").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Edge[Any, Any]].dest)
    r.put(0x903DD615139EBB74L /* methodKey(F, "org.sireum.Graph.Edge.Plain", "source").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Edge.Plain[Any, Any]].source)
    r.put(0x60D75CDC943DEC77L /* methodKey(F, "org.sireum.Graph.Edge.Plain", "dest").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Edge.Plain[Any, Any]].dest)
    r.put(0x27F5242BB71E4FD4L /* methodKey(F, "org.sireum.Graph.Edge.Data", "source").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Edge.Data[Any, Any]].source)
    r.put(0x0F0295FF0CA5AC6AL /* methodKey(F, "org.sireum.Graph.Edge.Data", "dest").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Edge.Data[Any, Any]].dest)
    r.put(0x13E3C28C365B1FD6L /* methodKey(F, "org.sireum.Graph.Edge.Data", "data").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Edge.Data[Any, Any]].data)
    r.put(0x37D14B950AEC31EBL /* methodKey(F, "org.sireum.Graph.Internal.Edge", "source").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edge[Any]].source)
    r.put(0x15FD5186D215A53BL /* methodKey(F, "org.sireum.Graph.Internal.Edge", "dest").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edge[Any]].dest)
    r.put(0x9235930F9A060AA5L /* methodKey(F, "org.sireum.Graph.Internal.Edges", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges[Any]].elements)
    r.put(0xD04F866D5C14B73AL /* methodKey(F, "org.sireum.Graph.Internal.Edges", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges[Any]].size)
    r.put(0x4BE6F5EE429E8616L /* methodKey(F, "org.sireum.Graph.Internal.Edges.Set", "set").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges.Set[Any]].set)
    r.put(0x5EC4D2B307D05499L /* methodKey(F, "org.sireum.Graph.Internal.Edges.Set", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges.Set[Any]].elements)
    r.put(0x306E16ACB9B0926DL /* methodKey(F, "org.sireum.Graph.Internal.Edges.Set", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges.Set[Any]].size)
    r.put(0xA5E5C327787ADF29L /* methodKey(F, "org.sireum.Graph.Internal.Edges.Bag", "set").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges.Bag[Any]].set)
    r.put(0xAEA9E6B6F301B258L /* methodKey(F, "org.sireum.Graph.Internal.Edges.Bag", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges.Bag[Any]].elements)
    r.put(0x476CF556CF1AF28DL /* methodKey(F, "org.sireum.Graph.Internal.Edges.Bag", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges.Bag[Any]].size)
    r.put(0xDED88B81E1C994A3L /* methodKey(F, "org.sireum.Graph.Internal.Edge.Plain", "source").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edge.Plain[Any]].source)
    r.put(0x2AC2D49938BB711BL /* methodKey(F, "org.sireum.Graph.Internal.Edge.Plain", "dest").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edge.Plain[Any]].dest)
    r.put(0x5FF1D7A11A0C3639L /* methodKey(F, "org.sireum.Graph.Internal.Edge.Data", "source").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edge.Data[Any]].source)
    r.put(0x13139E9F30CD591DL /* methodKey(F, "org.sireum.Graph.Internal.Edge.Data", "dest").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edge.Data[Any]].dest)
    r.put(0xBA7CE2A5E375A3CBL /* methodKey(F, "org.sireum.Graph.Internal.Edge.Data", "data").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edge.Data[Any]].data)
    r.put(0x57D6E4700AC95C07L /* methodKey(F, "org.sireum.Graph", "nodes").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].nodes)
    r.put(0x590002C0539358ECL /* methodKey(F, "org.sireum.Graph", "nodesInverse").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].nodesInverse)
    r.put(0xB1F093D049E10503L /* methodKey(F, "org.sireum.Graph", "incomingEdges").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].incomingEdges)
    r.put(0xDB545E9EEFEC782CL /* methodKey(F, "org.sireum.Graph", "outgoingEdges").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].outgoingEdges)
    r.put(0x8B3BEEEA206C8BFBL /* methodKey(F, "org.sireum.Graph", "nextNodeId").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].nextNodeId)
    r.put(0x0E4646D7446A0029L /* methodKey(F, "org.sireum.Graph", "multi").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].multi)
    r.put(0xFFB19AC0792729AAL /* methodKey(F, "org.sireum.Graph", "allEdges").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].allEdges)
    r.put(0xF17B714E62DE85A0L /* methodKey(F, "org.sireum.Graph", "numOfNodes").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].numOfNodes)
    r.put(0x6761EF83E13D8F9EL /* methodKey(F, "org.sireum.Graph", "numOfEdges").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].numOfEdges)
    r.put(0x3D0559A0B5177C06L /* methodKey(F, "org.sireum.Graph", "hash").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].hash)
    r.put(0xC3E9872E88387C3BL /* methodKey(F, "org.sireum.Graph", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].string)
    r.put(0x11CECA6C5DB29A25L /* methodKey(F, "org.sireum.HashBag", "map").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].map)
    r.put(0x0417D07F496D4F48L /* methodKey(F, "org.sireum.HashBag", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].size)
    r.put(0x64F7A253EE7E5AEEL /* methodKey(F, "org.sireum.HashBag", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].elements)
    r.put(0xB6224B2F8FF1C04BL /* methodKey(F, "org.sireum.HashBag", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].isEmpty)
    r.put(0x028283385166C7E5L /* methodKey(F, "org.sireum.HashBag", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].nonEmpty)
    r.put(0x05585FBAEC6EAC73L /* methodKey(F, "org.sireum.HashBag", "entries").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].entries)
    r.put(0xCB7D5A19A07B5336L /* methodKey(F, "org.sireum.HashBag", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].string)
    r.put(0x7075D377D6CE5551L /* methodKey(F, "org.sireum.HashMap", "mapEntries").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].mapEntries)
    r.put(0xCE4CB4B56644A20BL /* methodKey(F, "org.sireum.HashMap", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].size)
    r.put(0xCC08BFE28C980847L /* methodKey(F, "org.sireum.HashMap", "entries").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].entries)
    r.put(0x44900222B91A2A70L /* methodKey(F, "org.sireum.HashMap", "keys").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].keys)
    r.put(0xC79610A26A7D8F2AL /* methodKey(F, "org.sireum.HashMap", "values").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].values)
    r.put(0x0C0146FC21923A10L /* methodKey(F, "org.sireum.HashMap", "keySet").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].keySet)
    r.put(0x70133DF796A94034L /* methodKey(F, "org.sireum.HashMap", "valueSet").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].valueSet)
    r.put(0x63D776EB10C63CE4L /* methodKey(F, "org.sireum.HashMap", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].isEmpty)
    r.put(0xB19DF446C1962523L /* methodKey(F, "org.sireum.HashMap", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].nonEmpty)
    r.put(0xB1C65CD08579E6E7L /* methodKey(F, "org.sireum.HashMap", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].string)
    r.put(0xC205519E2CAD1AEAL /* methodKey(F, "org.sireum.HashMap", "hash").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].hash)
    r.put(0x8D6DB3863687AE9FL /* methodKey(F, "org.sireum.HashSBag", "map").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].map)
    r.put(0xE015050BABF2465DL /* methodKey(F, "org.sireum.HashSBag", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].size)
    r.put(0x287DC532FD79BD04L /* methodKey(F, "org.sireum.HashSBag", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].elements)
    r.put(0xA4506FBCDE362798L /* methodKey(F, "org.sireum.HashSBag", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].isEmpty)
    r.put(0x2A3BDAEC1E701CE4L /* methodKey(F, "org.sireum.HashSBag", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].nonEmpty)
    r.put(0xD70CF0F6487F4401L /* methodKey(F, "org.sireum.HashSBag", "entries").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].entries)
    r.put(0xE65948CC431CDF5DL /* methodKey(F, "org.sireum.HashSBag", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].string)
    r.put(0x24864ADE14D85CA4L /* methodKey(F, "org.sireum.HashSMap", "map").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].map)
    r.put(0xA51A087485E52DCEL /* methodKey(F, "org.sireum.HashSMap", "keys").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].keys)
    r.put(0x4108B8ECC45E15D0L /* methodKey(F, "org.sireum.HashSMap", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].size)
    r.put(0xB6C980F5FEE3AE75L /* methodKey(F, "org.sireum.HashSMap", "entries").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].entries)
    r.put(0x316D377EAC8A9164L /* methodKey(F, "org.sireum.HashSMap", "values").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].values)
    r.put(0xE3A40F3DE9A2453FL /* methodKey(F, "org.sireum.HashSMap", "keySet").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].keySet)
    r.put(0xBD30F906E84E433FL /* methodKey(F, "org.sireum.HashSMap", "valueSet").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].valueSet)
    r.put(0xFD283301B59B92DFL /* methodKey(F, "org.sireum.HashSMap", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].isEmpty)
    r.put(0xEB429920957EA19CL /* methodKey(F, "org.sireum.HashSMap", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].nonEmpty)
    r.put(0x67C3AF2E5ADA2406L /* methodKey(F, "org.sireum.HashSMap", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].string)
    r.put(0x068AFA70FAFA0707L /* methodKey(F, "org.sireum.HashSMap", "hash").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].hash)
    r.put(0x54FAD30B61718A3EL /* methodKey(F, "org.sireum.HashSSet", "map").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].map)
    r.put(0x53F0849083D302E7L /* methodKey(F, "org.sireum.HashSSet", "hash").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].hash)
    r.put(0x55796D04B482BF24L /* methodKey(F, "org.sireum.HashSSet", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].isEmpty)
    r.put(0x9411F6DCC1A0213CL /* methodKey(F, "org.sireum.HashSSet", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].nonEmpty)
    r.put(0x33717A7874A8E8D6L /* methodKey(F, "org.sireum.HashSSet", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].size)
    r.put(0x88745B24F5BAD0AEL /* methodKey(F, "org.sireum.HashSSet", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].elements)
    r.put(0x6756AAB6782B6D46L /* methodKey(F, "org.sireum.HashSSet", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].string)
    r.put(0xC58EA225CC631AACL /* methodKey(F, "org.sireum.HashSet", "map").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].map)
    r.put(0x860BDED12EE54AB9L /* methodKey(F, "org.sireum.HashSet", "hash").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].hash)
    r.put(0xCDB68C22AE1AE76FL /* methodKey(F, "org.sireum.HashSet", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].isEmpty)
    r.put(0xF72D16447F4A3A27L /* methodKey(F, "org.sireum.HashSet", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].nonEmpty)
    r.put(0x67ABDFD9D0E31FACL /* methodKey(F, "org.sireum.HashSet", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].size)
    r.put(0x6F3C59D424DED487L /* methodKey(F, "org.sireum.HashSet", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].elements)
    r.put(0xA1B2A8FAC7523B03L /* methodKey(F, "org.sireum.HashSet", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].string)
    r.put(0x38D34BD1B77D97AEL /* methodKey(F, "org.sireum.IndexMap", "s").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.IndexMap[Any, Any]].s)
    r.put(0x171B31E618BBEF27L /* methodKey(F, "org.sireum.IndexMap", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.IndexMap[Any, Any]].size)
    r.put(0x291DCC5A91D699EBL /* methodKey(F, "org.sireum.IndexMap", "entries").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.IndexMap[Any, Any]].entries)
    r.put(0x18C85343074BC388L /* methodKey(F, "org.sireum.IndexMap", "keys").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.IndexMap[Any, Any]].keys)
    r.put(0x950F56C2A5A02FFBL /* methodKey(F, "org.sireum.IndexMap", "values").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.IndexMap[Any, Any]].values)
    r.put(0xAB964318F56E2603L /* methodKey(F, "org.sireum.IndexMap", "prettyST").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.IndexMap[Any, Any]].prettyST)
    r.put(0x36978CB2B0C86340L /* methodKey(F, "org.sireum.IndexMap", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.IndexMap[Any, Any]].string)
    r.put(0xB0BEE7CCBA4CDB92L /* methodKey(F, "org.sireum.Indexable.Isz", "is").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Indexable.Isz[Any]].is)
    r.put(0xA3A23BD88A988B7BL /* methodKey(F, "org.sireum.Indexable.IszDocInfo", "is").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Indexable.IszDocInfo[Any]].is)
    r.put(0x66CC763BE087E76DL /* methodKey(F, "org.sireum.Indexable.IszDocInfo", "info").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Indexable.IszDocInfo[Any]].info)
    r.put(0x1BB03D0342BB4BC7L /* methodKey(F, "org.sireum.Jen", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].count())
    r.put(0x49AB3033CC454E67L /* methodKey(F, "org.sireum.Jen", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].zipWithIndex)
    r.put(0x278C8E8E5C1BACFBL /* methodKey(F, "org.sireum.Jen", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].head)
    r.put(0x684D655591EC81B5L /* methodKey(F, "org.sireum.Jen", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].headOption)
    r.put(0x8CD6C7BFE1DB6F53L /* methodKey(F, "org.sireum.Jen", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].toISZ)
    r.put(0x5DB39F91D4B2DB58L /* methodKey(F, "org.sireum.Jen", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].toMSZ)
    r.put(0x9AEA729DC1847040L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "s").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].s)
    r.put(0x44BA39CC01CAB452L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].string)
    r.put(0x1ECB43BE41DB229EL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].count())
    r.put(0x4538CA39B4BCDA1FL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].zipWithIndex)
    r.put(0x708FC921DC35A819L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].head)
    r.put(0x358BF973864B2587L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].headOption)
    r.put(0x3356633B4691D61AL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].toISZ)
    r.put(0xA65DD845D96249B2L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].toMSZ)
    r.put(0xAA6544E1CF9CE372L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "m").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].m)
    r.put(0xDF18342AC22A9B4DL /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].string)
    r.put(0xAB137B3D10A3E031L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].count())
    r.put(0x5F21C5FF44C5CB85L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].zipWithIndex)
    r.put(0x77C84C96BD4A1575L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].head)
    r.put(0xE42FD4BD60F39BA3L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].headOption)
    r.put(0xC540EFD702B58F32L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].toISZ)
    r.put(0xB70852F02A0142EEL /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].toMSZ)
    r.put(0x2E027524BDFC4FF0L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "m").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].m)
    r.put(0xBB2960EB923F7657L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].string)
    r.put(0x4C47CBCD624FAA9BL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].count())
    r.put(0x0974C4ABE39EF3F6L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].zipWithIndex)
    r.put(0x53A942795B4D88D5L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].head)
    r.put(0xE9C70B1FBEE0D8A8L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].headOption)
    r.put(0xD83E89054CB51665L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].toISZ)
    r.put(0xC04E4B76D212A272L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].toMSZ)
    r.put(0xA74A21AD9FCB63ACL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].gen)
    r.put(0x7B636CE1742A07B8L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "p").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].p)
    r.put(0x0447C7DC058B58FBL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].string)
    r.put(0x4ACEED6B6F122940L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].count())
    r.put(0x6A01807DCDF05A2EL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].zipWithIndex)
    r.put(0xEDC3EE85DA620D4FL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].head)
    r.put(0xD598E6A5855E474BL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].headOption)
    r.put(0x9BB8B1A72D81198EL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].toISZ)
    r.put(0x8B4542C294698E11L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].toMSZ)
    r.put(0xB4AFF47FC6944630L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].gen)
    r.put(0x666E3465E7927372L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "f").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].f)
    r.put(0x7BB4A85113907737L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].string)
    r.put(0xFC7D980D0A05B1DCL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].count())
    r.put(0x8E3AB87835B75781L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].zipWithIndex)
    r.put(0xC694966F1DAFC63DL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].head)
    r.put(0x034E79B4D014E599L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].headOption)
    r.put(0x225F75E4DC0EA227L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].toISZ)
    r.put(0xBEBCDADBD493D3DEL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].toMSZ)
    r.put(0xD3BE14C1D7901DE4L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].gen)
    r.put(0x1B138DBB5CA6FEA4L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "f").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].f)
    r.put(0xE684242635A98A01L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].string)
    r.put(0x6DABE7EF38243D99L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].count())
    r.put(0x539BEDBCAD2ACEEEL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].zipWithIndex)
    r.put(0x0B7DDDFA53AF5842L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].head)
    r.put(0x384BFCA355BBE450L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].headOption)
    r.put(0xEDF87CF33704312EL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].toISZ)
    r.put(0xEFA2F959C393BF97L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].toMSZ)
    r.put(0x7B8EF4EAD605DE31L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].gen)
    r.put(0x58614EEF6A1B4F6BL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "start").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].start)
    r.put(0x4734FEA37190BE20L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "end").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].end)
    r.put(0x1040DE74A2FCFBD8L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].string)
    r.put(0xB28EB5FEAEFC5C93L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].count())
    r.put(0xB83F9B3EC508D06AL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].zipWithIndex)
    r.put(0xAA12DFC04F0971C5L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].head)
    r.put(0x55A45923153525D9L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].headOption)
    r.put(0x86C2F20435B6D08BL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].toISZ)
    r.put(0x1F8DE11301B98871L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].toMSZ)
    r.put(0x763E9BDBBB2DB39FL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].gen)
    r.put(0xEEAA2B6444544F07L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "p").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].p)
    r.put(0x717C14DD689D34A0L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].string)
    r.put(0xBA2BD601697FEB17L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].count())
    r.put(0xCCD6DA33BB550937L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].zipWithIndex)
    r.put(0xBEF3C20B52B0C177L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].head)
    r.put(0x37C06782CE062732L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].headOption)
    r.put(0xAFA5314C73A2DE5DL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].toISZ)
    r.put(0x27E64C5DE430A42AL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].toMSZ)
    r.put(0x684A6497FA64F6CCL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].gen)
    r.put(0x54DD9D5C7993DEF0L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "p").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].p)
    r.put(0xD28357F961C9E47BL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].string)
    r.put(0x45A487DADB78707DL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].count())
    r.put(0x9000A2378904C571L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].zipWithIndex)
    r.put(0x3C102CA704F1562DL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].head)
    r.put(0x117C81C8D30C54A6L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].headOption)
    r.put(0xCEF869C2C8B55A82L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].toISZ)
    r.put(0xA097630B0C041565L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].toMSZ)
    r.put(0x3C7BF25485AAEBAFL /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].gen)
    r.put(0x8C21F5D6B2654B1CL /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].string)
    r.put(0x94AC449E0159F956L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].count())
    r.put(0xFE7F83AE57380129L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].zipWithIndex)
    r.put(0x6F473ACC2151E1F2L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].head)
    r.put(0x3A14C68F2615D96DL /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].headOption)
    r.put(0x7E63E7E6B969DF44L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].toISZ)
    r.put(0xF4A23FC83FA13D74L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].toMSZ)
    r.put(0xE2F880D9097A7A3AL /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].gen)
    r.put(0x61786E07E75AB543L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "gen2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].gen2)
    r.put(0x596CD4866534B55DL /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].string)
    r.put(0x85022719A9C78EF9L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].count())
    r.put(0xFDB60986EA7D2977L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].zipWithIndex)
    r.put(0x414F4550F3AACF8EL /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].head)
    r.put(0xB0A6739A1457CBE2L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].headOption)
    r.put(0x67921FBDFC524CE7L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].toISZ)
    r.put(0x3B1018B815151FFAL /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].toMSZ)
    r.put(0x4F680E13EAC3D452L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].gen)
    r.put(0x1A68048137C41C09L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "gen2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].gen2)
    r.put(0x85C169F9BC594D88L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].string)
    r.put(0x8FAD28D10B06FD07L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].count())
    r.put(0xB9D8105988A1412FL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].zipWithIndex)
    r.put(0x3B2EA745EFD653DDL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].head)
    r.put(0x351BF0182BA0C00CL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].headOption)
    r.put(0x33673D3836BC19F0L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].toISZ)
    r.put(0xE2E026156646D7FBL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].toMSZ)
    r.put(0xD02B3C88CF817CA7L /* methodKey(F, "org.sireum.Jen.Internal.Product", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].gen)
    r.put(0x3B2814E81E5AC5CEL /* methodKey(F, "org.sireum.Jen.Internal.Product", "gen2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].gen2)
    r.put(0xEC0BD6317F3094C5L /* methodKey(F, "org.sireum.Jen.Internal.Product", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].string)
    r.put(0x00ED1CEB16366700L /* methodKey(F, "org.sireum.Jen.Internal.Product", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].count())
    r.put(0x135B570E0626BBAFL /* methodKey(F, "org.sireum.Jen.Internal.Product", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].zipWithIndex)
    r.put(0x35FD48DDE1DE27E2L /* methodKey(F, "org.sireum.Jen.Internal.Product", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].head)
    r.put(0x26F54F97A094C910L /* methodKey(F, "org.sireum.Jen.Internal.Product", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].headOption)
    r.put(0x378AE4093F027C7DL /* methodKey(F, "org.sireum.Jen.Internal.Product", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].toISZ)
    r.put(0x2DE874B3B8321AF0L /* methodKey(F, "org.sireum.Jen.Internal.Product", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].toMSZ)
    r.put(0xFC518D2C3D292165L /* methodKey(F, "org.sireum.MBox", "value").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox[Any]].value)
    r.put(0xB61A9F850C758B3AL /* methodKey(F, "org.sireum.MBox2", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox2[Any, Any]].value1)
    r.put(0xB9D2F442E2175A75L /* methodKey(F, "org.sireum.MBox2", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox2[Any, Any]].value2)
    r.put(0xA325943EFD0DF751L /* methodKey(F, "org.sireum.MBox3", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox3[Any, Any, Any]].value1)
    r.put(0x73BFA8D55A7D6AA4L /* methodKey(F, "org.sireum.MBox3", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox3[Any, Any, Any]].value2)
    r.put(0x3D5D418DDF95F471L /* methodKey(F, "org.sireum.MBox3", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox3[Any, Any, Any]].value3)
    r.put(0x981B19E21BFD00BDL /* methodKey(F, "org.sireum.MBox4", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox4[Any, Any, Any, Any]].value1)
    r.put(0xBCB653D5A03E2C65L /* methodKey(F, "org.sireum.MBox4", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox4[Any, Any, Any, Any]].value2)
    r.put(0x5AD5B01F6DDC2CE8L /* methodKey(F, "org.sireum.MBox4", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox4[Any, Any, Any, Any]].value3)
    r.put(0x21AB04204C3D9731L /* methodKey(F, "org.sireum.MBox4", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox4[Any, Any, Any, Any]].value4)
    r.put(0xE849418E7FB1EB3BL /* methodKey(F, "org.sireum.MBox5", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox5[Any, Any, Any, Any, Any]].value1)
    r.put(0x0FB5ACEDC7AE384CL /* methodKey(F, "org.sireum.MBox5", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox5[Any, Any, Any, Any, Any]].value2)
    r.put(0xAE88DF6425E4A25FL /* methodKey(F, "org.sireum.MBox5", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox5[Any, Any, Any, Any, Any]].value3)
    r.put(0x70266555E06EF94DL /* methodKey(F, "org.sireum.MBox5", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox5[Any, Any, Any, Any, Any]].value4)
    r.put(0xE6CFAAB3AC64A367L /* methodKey(F, "org.sireum.MBox5", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox5[Any, Any, Any, Any, Any]].value5)
    r.put(0x35DE551BEBAF2FC4L /* methodKey(F, "org.sireum.MBox6", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox6[Any, Any, Any, Any, Any, Any]].value1)
    r.put(0xBBB9C3C43D127F80L /* methodKey(F, "org.sireum.MBox6", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox6[Any, Any, Any, Any, Any, Any]].value2)
    r.put(0xDA0EB338104BA4A0L /* methodKey(F, "org.sireum.MBox6", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox6[Any, Any, Any, Any, Any, Any]].value3)
    r.put(0xEB067DD96C355ACBL /* methodKey(F, "org.sireum.MBox6", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox6[Any, Any, Any, Any, Any, Any]].value4)
    r.put(0xA66EE1180B4B0592L /* methodKey(F, "org.sireum.MBox6", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox6[Any, Any, Any, Any, Any, Any]].value5)
    r.put(0x161E277A408757E4L /* methodKey(F, "org.sireum.MBox6", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox6[Any, Any, Any, Any, Any, Any]].value6)
    r.put(0x0D6318C4BD4DB281L /* methodKey(F, "org.sireum.MBox7", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0xEEA9D90766312BEFL /* methodKey(F, "org.sireum.MBox7", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0x0F907565F337780EL /* methodKey(F, "org.sireum.MBox7", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0xEFC13064CA6D5EE0L /* methodKey(F, "org.sireum.MBox7", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0x88AB52DC3D3386AAL /* methodKey(F, "org.sireum.MBox7", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0x6A598A55D248727DL /* methodKey(F, "org.sireum.MBox7", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0x95E820F54F9BBBE4L /* methodKey(F, "org.sireum.MBox7", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0x6EE392AD462EF167L /* methodKey(F, "org.sireum.MBox8", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0xB3626086F5D1D02DL /* methodKey(F, "org.sireum.MBox8", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0x478EB4920636CF9DL /* methodKey(F, "org.sireum.MBox8", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0x6D9D14BA952BBBDAL /* methodKey(F, "org.sireum.MBox8", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0x717B1E8CB8583B33L /* methodKey(F, "org.sireum.MBox8", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0x34C25C83A8B182E6L /* methodKey(F, "org.sireum.MBox8", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0x728F8F27AC922CB8L /* methodKey(F, "org.sireum.MBox8", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0x4E77F52504DCCFC1L /* methodKey(F, "org.sireum.MBox8", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0x504345716AD3DDF1L /* methodKey(F, "org.sireum.MBox9", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0x58321756722E09EBL /* methodKey(F, "org.sireum.MBox9", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0x7CC2F5EB6B9D87F0L /* methodKey(F, "org.sireum.MBox9", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0x8A419A508DD97A63L /* methodKey(F, "org.sireum.MBox9", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0x7FC1569A0140BE10L /* methodKey(F, "org.sireum.MBox9", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0x40E3499504AB0E61L /* methodKey(F, "org.sireum.MBox9", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0xFEDDC8D3041F40D4L /* methodKey(F, "org.sireum.MBox9", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0x6A46712A7CF01563L /* methodKey(F, "org.sireum.MBox9", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0x8AD9A6E158C96988L /* methodKey(F, "org.sireum.MBox9", "value9").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].value9)
    r.put(0x3A9D481C87812FB9L /* methodKey(F, "org.sireum.MBox10", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0x43CD0AE0F64738EDL /* methodKey(F, "org.sireum.MBox10", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0xFCB691CDB87D9DB0L /* methodKey(F, "org.sireum.MBox10", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0x976FC58A2425CD35L /* methodKey(F, "org.sireum.MBox10", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0x606B1DCF7E59D4D2L /* methodKey(F, "org.sireum.MBox10", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0x0B73CD1CBABEAD53L /* methodKey(F, "org.sireum.MBox10", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0x3730DCB31E997C78L /* methodKey(F, "org.sireum.MBox10", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0xBAC54C1F235A375AL /* methodKey(F, "org.sireum.MBox10", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0x3BFBED2AC0A9FAF4L /* methodKey(F, "org.sireum.MBox10", "value9").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value9)
    r.put(0x8F5C483B6711A507L /* methodKey(F, "org.sireum.MBox10", "value10").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value10)
    r.put(0xD70F76B598B0CD49L /* methodKey(F, "org.sireum.MBox11", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0xABA53DF11F756DC8L /* methodKey(F, "org.sireum.MBox11", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0xA8831C0F1647664AL /* methodKey(F, "org.sireum.MBox11", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0x56F6A0A5A782347CL /* methodKey(F, "org.sireum.MBox11", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0x74F78483A36E3364L /* methodKey(F, "org.sireum.MBox11", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0x2DABBE6A83394BC8L /* methodKey(F, "org.sireum.MBox11", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0xF92FE07FEA8C664DL /* methodKey(F, "org.sireum.MBox11", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0xB5FF0D054AA1EF20L /* methodKey(F, "org.sireum.MBox11", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0xDD04BDABD63768BDL /* methodKey(F, "org.sireum.MBox11", "value9").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value9)
    r.put(0xF63500FF667D4030L /* methodKey(F, "org.sireum.MBox11", "value10").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value10)
    r.put(0x67FD54A0968C25A0L /* methodKey(F, "org.sireum.MBox11", "value11").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value11)
    r.put(0x9BBC3E51975AEFD1L /* methodKey(F, "org.sireum.MBox12", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0x42A1F660262E77CBL /* methodKey(F, "org.sireum.MBox12", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0x9D6A9E23ECBCAC80L /* methodKey(F, "org.sireum.MBox12", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0x08FFE0A7FF0428CFL /* methodKey(F, "org.sireum.MBox12", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0x137B25A66AA313CEL /* methodKey(F, "org.sireum.MBox12", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0xDCF0C840381AF402L /* methodKey(F, "org.sireum.MBox12", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0xBA988AB81F4EA168L /* methodKey(F, "org.sireum.MBox12", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0xB884ABC22B68B341L /* methodKey(F, "org.sireum.MBox12", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0xA1C8A3CB1EF4BF99L /* methodKey(F, "org.sireum.MBox12", "value9").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value9)
    r.put(0x39290EA7DD77BB21L /* methodKey(F, "org.sireum.MBox12", "value10").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value10)
    r.put(0x7BE7599967F81AE6L /* methodKey(F, "org.sireum.MBox12", "value11").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value11)
    r.put(0x07E1F9EBA06DA58DL /* methodKey(F, "org.sireum.MBox12", "value12").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value12)
    r.put(0x1F1AFBB8EE8DC3A6L /* methodKey(F, "org.sireum.MBox13", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0x85F0E6FD040B5FFDL /* methodKey(F, "org.sireum.MBox13", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0x05FB7FBE2603FD3EL /* methodKey(F, "org.sireum.MBox13", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0xDC6DC9742A0D2918L /* methodKey(F, "org.sireum.MBox13", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0x2B4559FBDD5DEBF4L /* methodKey(F, "org.sireum.MBox13", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0x891765DD66EDDE4EL /* methodKey(F, "org.sireum.MBox13", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0x57CF3D0113EEBDC5L /* methodKey(F, "org.sireum.MBox13", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0xB1BB32AF3D4146A6L /* methodKey(F, "org.sireum.MBox13", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0x12CA215F152FC507L /* methodKey(F, "org.sireum.MBox13", "value9").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value9)
    r.put(0x8599E4B8C5A29834L /* methodKey(F, "org.sireum.MBox13", "value10").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value10)
    r.put(0x4783B0AB62C27F44L /* methodKey(F, "org.sireum.MBox13", "value11").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value11)
    r.put(0x67DC9344416D69EDL /* methodKey(F, "org.sireum.MBox13", "value12").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value12)
    r.put(0x43F70FD9FB9F5448L /* methodKey(F, "org.sireum.MBox13", "value13").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value13)
    r.put(0x18FC5CF414D743F5L /* methodKey(F, "org.sireum.MBox14", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0x6ACFCC0230D7031AL /* methodKey(F, "org.sireum.MBox14", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0x6F1D8AC5BBA315E6L /* methodKey(F, "org.sireum.MBox14", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0xEBB3E6C1F7C43390L /* methodKey(F, "org.sireum.MBox14", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0x4A69F5DF907A8E9CL /* methodKey(F, "org.sireum.MBox14", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0x8A083C340F4A83D0L /* methodKey(F, "org.sireum.MBox14", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0x4D41A72560BF8845L /* methodKey(F, "org.sireum.MBox14", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0x3E4700CB0FB149E6L /* methodKey(F, "org.sireum.MBox14", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0xF3595788115CE6E8L /* methodKey(F, "org.sireum.MBox14", "value9").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value9)
    r.put(0x8703F8948B00B563L /* methodKey(F, "org.sireum.MBox14", "value10").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value10)
    r.put(0xA8DB56E2F775E46FL /* methodKey(F, "org.sireum.MBox14", "value11").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value11)
    r.put(0xB71DC5BBDC5B6029L /* methodKey(F, "org.sireum.MBox14", "value12").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value12)
    r.put(0x05D02D5A5BD714F6L /* methodKey(F, "org.sireum.MBox14", "value13").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value13)
    r.put(0xE2E74178FDB163B0L /* methodKey(F, "org.sireum.MBox14", "value14").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value14)
    r.put(0x39F50343A556239EL /* methodKey(F, "org.sireum.MBox15", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0xBE581149109F1030L /* methodKey(F, "org.sireum.MBox15", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0x49D85288D49ED1C4L /* methodKey(F, "org.sireum.MBox15", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0xA0F46BC82F7FC6E2L /* methodKey(F, "org.sireum.MBox15", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0xCD7F38EE94D56F44L /* methodKey(F, "org.sireum.MBox15", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0x97741EF52A797555L /* methodKey(F, "org.sireum.MBox15", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0xCCEBA61EFE2BF71CL /* methodKey(F, "org.sireum.MBox15", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0x504B8667ED261339L /* methodKey(F, "org.sireum.MBox15", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0xBB81B573349F715CL /* methodKey(F, "org.sireum.MBox15", "value9").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value9)
    r.put(0xBEAEC63B608C3403L /* methodKey(F, "org.sireum.MBox15", "value10").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value10)
    r.put(0xD5A4AD07BBD6872EL /* methodKey(F, "org.sireum.MBox15", "value11").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value11)
    r.put(0x8944FD0AEF42FAE4L /* methodKey(F, "org.sireum.MBox15", "value12").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value12)
    r.put(0x237202CE7CC5C8AEL /* methodKey(F, "org.sireum.MBox15", "value13").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value13)
    r.put(0xFD818B564028DB6BL /* methodKey(F, "org.sireum.MBox15", "value14").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value14)
    r.put(0x29DD36635DBA2DD8L /* methodKey(F, "org.sireum.MBox15", "value15").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value15)
    r.put(0xEAF6A1895B808743L /* methodKey(F, "org.sireum.MBox16", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0x1003A5DED27A5744L /* methodKey(F, "org.sireum.MBox16", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0x51112535DB7BCC0AL /* methodKey(F, "org.sireum.MBox16", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0xC79AAB780821B6EBL /* methodKey(F, "org.sireum.MBox16", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0x51BF64C2BC263A57L /* methodKey(F, "org.sireum.MBox16", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0xC35845C0A9B6049BL /* methodKey(F, "org.sireum.MBox16", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0x88E17CB7CF7563FEL /* methodKey(F, "org.sireum.MBox16", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0x6CC1274DF937EE16L /* methodKey(F, "org.sireum.MBox16", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0x7F80C4E4A9BB4CDDL /* methodKey(F, "org.sireum.MBox16", "value9").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value9)
    r.put(0xBD7A2D7115B0C9A1L /* methodKey(F, "org.sireum.MBox16", "value10").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value10)
    r.put(0xFB4DE0D7ABCE5CFFL /* methodKey(F, "org.sireum.MBox16", "value11").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value11)
    r.put(0xDC0671C92C829B8EL /* methodKey(F, "org.sireum.MBox16", "value12").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value12)
    r.put(0xDDF17388A522EDDAL /* methodKey(F, "org.sireum.MBox16", "value13").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value13)
    r.put(0x005DCED853FDFA0EL /* methodKey(F, "org.sireum.MBox16", "value14").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value14)
    r.put(0x804AD0C5ACA3BB49L /* methodKey(F, "org.sireum.MBox16", "value15").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value15)
    r.put(0x6D0CF01595CC6919L /* methodKey(F, "org.sireum.MBox16", "value16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value16)
    r.put(0x352B9535E1C0011BL /* methodKey(F, "org.sireum.MBox17", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0xC5261E7538ED9747L /* methodKey(F, "org.sireum.MBox17", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0xECD9B612CAC5FCF4L /* methodKey(F, "org.sireum.MBox17", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0xB02589DAE41B0860L /* methodKey(F, "org.sireum.MBox17", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0x292DACE04D3B4B5EL /* methodKey(F, "org.sireum.MBox17", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0x69227D818CD47B31L /* methodKey(F, "org.sireum.MBox17", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0xF47C82F926F0DF18L /* methodKey(F, "org.sireum.MBox17", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0x1A32010DB68A6240L /* methodKey(F, "org.sireum.MBox17", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0xF4477EA7B2B130B1L /* methodKey(F, "org.sireum.MBox17", "value9").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value9)
    r.put(0x1528D2A96A8968B7L /* methodKey(F, "org.sireum.MBox17", "value10").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value10)
    r.put(0xCEEEFFEC8EFDE719L /* methodKey(F, "org.sireum.MBox17", "value11").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value11)
    r.put(0x5395F58B7CB65848L /* methodKey(F, "org.sireum.MBox17", "value12").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value12)
    r.put(0x7F70FFF8990CE826L /* methodKey(F, "org.sireum.MBox17", "value13").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value13)
    r.put(0x61595EA9376B5E5AL /* methodKey(F, "org.sireum.MBox17", "value14").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value14)
    r.put(0xE8023461F069B9FFL /* methodKey(F, "org.sireum.MBox17", "value15").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value15)
    r.put(0x459A1B2C35BF7F12L /* methodKey(F, "org.sireum.MBox17", "value16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value16)
    r.put(0xD89E7DDC4ECB321CL /* methodKey(F, "org.sireum.MBox17", "value17").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value17)
    r.put(0x0BD9BE8222814630L /* methodKey(F, "org.sireum.MBox18", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0xDCDEAE4E1CE3801DL /* methodKey(F, "org.sireum.MBox18", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0xA995C37CEF198D3EL /* methodKey(F, "org.sireum.MBox18", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0xD972595BCC526E76L /* methodKey(F, "org.sireum.MBox18", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0xC17411CE0EDE23EFL /* methodKey(F, "org.sireum.MBox18", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0x254FC4A6CF4C9A56L /* methodKey(F, "org.sireum.MBox18", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0x45053F500E4667B5L /* methodKey(F, "org.sireum.MBox18", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0x03AB5A6F3779BE52L /* methodKey(F, "org.sireum.MBox18", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0xA275C1E8D9879CECL /* methodKey(F, "org.sireum.MBox18", "value9").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value9)
    r.put(0xED3D28B8AFBDF28AL /* methodKey(F, "org.sireum.MBox18", "value10").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value10)
    r.put(0x0D0709931741A07FL /* methodKey(F, "org.sireum.MBox18", "value11").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value11)
    r.put(0x1C40D3B0EEF0EAA1L /* methodKey(F, "org.sireum.MBox18", "value12").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value12)
    r.put(0x7AE8B94CE571DED4L /* methodKey(F, "org.sireum.MBox18", "value13").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value13)
    r.put(0xABC63019F8E29368L /* methodKey(F, "org.sireum.MBox18", "value14").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value14)
    r.put(0x8F3EB626C5589445L /* methodKey(F, "org.sireum.MBox18", "value15").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value15)
    r.put(0x26AB07E856C8CF27L /* methodKey(F, "org.sireum.MBox18", "value16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value16)
    r.put(0x5928295762B5F029L /* methodKey(F, "org.sireum.MBox18", "value17").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value17)
    r.put(0x8F7A43D394F13EF8L /* methodKey(F, "org.sireum.MBox18", "value18").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value18)
    r.put(0x133D9BA419DE8B2BL /* methodKey(F, "org.sireum.MBox19", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0xD50AE0ED4FE571D4L /* methodKey(F, "org.sireum.MBox19", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0x8205DCA39469121AL /* methodKey(F, "org.sireum.MBox19", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0xEA0044FB8E464BDFL /* methodKey(F, "org.sireum.MBox19", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0x574BF3DC81AB5DFAL /* methodKey(F, "org.sireum.MBox19", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0x0BCE7DEE58486D4CL /* methodKey(F, "org.sireum.MBox19", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0xDF55038063D012B6L /* methodKey(F, "org.sireum.MBox19", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0xE11633D8C78F7B50L /* methodKey(F, "org.sireum.MBox19", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0x59BF208BBDA4FF19L /* methodKey(F, "org.sireum.MBox19", "value9").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value9)
    r.put(0x8C93173A8E5F40F2L /* methodKey(F, "org.sireum.MBox19", "value10").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value10)
    r.put(0x52C51C7C056CF339L /* methodKey(F, "org.sireum.MBox19", "value11").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value11)
    r.put(0xAEBA49D202301133L /* methodKey(F, "org.sireum.MBox19", "value12").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value12)
    r.put(0x3F64CE8BAFE63F7BL /* methodKey(F, "org.sireum.MBox19", "value13").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value13)
    r.put(0xA663C41A31E8A48AL /* methodKey(F, "org.sireum.MBox19", "value14").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value14)
    r.put(0x1821FEAAF948502AL /* methodKey(F, "org.sireum.MBox19", "value15").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value15)
    r.put(0x1B1B9D3DCC247368L /* methodKey(F, "org.sireum.MBox19", "value16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value16)
    r.put(0xD3A2FDC314AEE403L /* methodKey(F, "org.sireum.MBox19", "value17").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value17)
    r.put(0x31DD20F447B5F347L /* methodKey(F, "org.sireum.MBox19", "value18").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value18)
    r.put(0xD19438E4865F5818L /* methodKey(F, "org.sireum.MBox19", "value19").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value19)
    r.put(0x8A435AF0DE60E79CL /* methodKey(F, "org.sireum.MBox20", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0x75415C939F1B54AAL /* methodKey(F, "org.sireum.MBox20", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0x016A7BF9DE70001CL /* methodKey(F, "org.sireum.MBox20", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0x36BFC2A15A7B2833L /* methodKey(F, "org.sireum.MBox20", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0x847CBB10FDC3A8ECL /* methodKey(F, "org.sireum.MBox20", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0x6CD1808EC3BE82D7L /* methodKey(F, "org.sireum.MBox20", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0xF079A934D85EC9B5L /* methodKey(F, "org.sireum.MBox20", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0xE0265B532BCCE106L /* methodKey(F, "org.sireum.MBox20", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0x2AFB7BC6BA589A37L /* methodKey(F, "org.sireum.MBox20", "value9").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value9)
    r.put(0xB4917DBD576EE422L /* methodKey(F, "org.sireum.MBox20", "value10").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value10)
    r.put(0x71CBE331924F8053L /* methodKey(F, "org.sireum.MBox20", "value11").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value11)
    r.put(0x5827500207C186F6L /* methodKey(F, "org.sireum.MBox20", "value12").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value12)
    r.put(0xD2892DE0F3B88C43L /* methodKey(F, "org.sireum.MBox20", "value13").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value13)
    r.put(0xFB14A03A5EC61CCAL /* methodKey(F, "org.sireum.MBox20", "value14").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value14)
    r.put(0x055CFAC3DA4BF5F2L /* methodKey(F, "org.sireum.MBox20", "value15").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value15)
    r.put(0xF37C01384ECC924DL /* methodKey(F, "org.sireum.MBox20", "value16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value16)
    r.put(0x1A4D439280F410BAL /* methodKey(F, "org.sireum.MBox20", "value17").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value17)
    r.put(0xE450D51172518877L /* methodKey(F, "org.sireum.MBox20", "value18").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value18)
    r.put(0x6B13BECDAD4C21CBL /* methodKey(F, "org.sireum.MBox20", "value19").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value19)
    r.put(0x99F093A82131E7BCL /* methodKey(F, "org.sireum.MBox20", "value20").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value20)
    r.put(0x97D7B5C47C11BD3CL /* methodKey(F, "org.sireum.MBox21", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0xE0554DB1C9FF7E60L /* methodKey(F, "org.sireum.MBox21", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0x45A1081C2B7A10A9L /* methodKey(F, "org.sireum.MBox21", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0xE8497351AFDBCB25L /* methodKey(F, "org.sireum.MBox21", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0x66F6DE82854F1F8FL /* methodKey(F, "org.sireum.MBox21", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0xD8B0927F341240E4L /* methodKey(F, "org.sireum.MBox21", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0x93B36D427374ACBDL /* methodKey(F, "org.sireum.MBox21", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0x5362CFB761295CF3L /* methodKey(F, "org.sireum.MBox21", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0xD937ACD00CAEB935L /* methodKey(F, "org.sireum.MBox21", "value9").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value9)
    r.put(0x05C39B6592A7CDF5L /* methodKey(F, "org.sireum.MBox21", "value10").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value10)
    r.put(0x43021A5A35EA7671L /* methodKey(F, "org.sireum.MBox21", "value11").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value11)
    r.put(0x4ADAB3DB8F29D51FL /* methodKey(F, "org.sireum.MBox21", "value12").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value12)
    r.put(0x3E78A47BD161A51EL /* methodKey(F, "org.sireum.MBox21", "value13").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value13)
    r.put(0xEAAAA55AF42A34EDL /* methodKey(F, "org.sireum.MBox21", "value14").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value14)
    r.put(0xFB240EB4A6A3CAFFL /* methodKey(F, "org.sireum.MBox21", "value15").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value15)
    r.put(0x454CBA1831561D6CL /* methodKey(F, "org.sireum.MBox21", "value16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value16)
    r.put(0xA3CA241D786EF651L /* methodKey(F, "org.sireum.MBox21", "value17").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value17)
    r.put(0xB9F47ED2A7923CE6L /* methodKey(F, "org.sireum.MBox21", "value18").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value18)
    r.put(0xEEA50CF7A1F324F6L /* methodKey(F, "org.sireum.MBox21", "value19").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value19)
    r.put(0x58943BF2DBDEC6C9L /* methodKey(F, "org.sireum.MBox21", "value20").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value20)
    r.put(0x47CBDA6B957CB7FBL /* methodKey(F, "org.sireum.MBox21", "value21").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value21)
    r.put(0x6D87693A7A783097L /* methodKey(F, "org.sireum.MBox22", "value1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value1)
    r.put(0x771386CB1E5D3EC8L /* methodKey(F, "org.sireum.MBox22", "value2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value2)
    r.put(0xAF03B4B46E0DDA3CL /* methodKey(F, "org.sireum.MBox22", "value3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value3)
    r.put(0xFEF897E3E94FCDDFL /* methodKey(F, "org.sireum.MBox22", "value4").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value4)
    r.put(0xF29198C61ABF40A4L /* methodKey(F, "org.sireum.MBox22", "value5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value5)
    r.put(0xBC5D61385D78D976L /* methodKey(F, "org.sireum.MBox22", "value6").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value6)
    r.put(0xFAD3F487B2CB8F00L /* methodKey(F, "org.sireum.MBox22", "value7").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value7)
    r.put(0x772B591779CA5C02L /* methodKey(F, "org.sireum.MBox22", "value8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value8)
    r.put(0xCE7EB15899A1A327L /* methodKey(F, "org.sireum.MBox22", "value9").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value9)
    r.put(0xA1575A2E514A421CL /* methodKey(F, "org.sireum.MBox22", "value10").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value10)
    r.put(0xA54B6C7008B8ABB3L /* methodKey(F, "org.sireum.MBox22", "value11").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value11)
    r.put(0x60896232D60F6411L /* methodKey(F, "org.sireum.MBox22", "value12").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value12)
    r.put(0x38F24703842A4F2CL /* methodKey(F, "org.sireum.MBox22", "value13").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value13)
    r.put(0x3AB9898A4D75756DL /* methodKey(F, "org.sireum.MBox22", "value14").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value14)
    r.put(0xEF74A54571B36EA0L /* methodKey(F, "org.sireum.MBox22", "value15").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value15)
    r.put(0x2D11CE3BAC81AA87L /* methodKey(F, "org.sireum.MBox22", "value16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value16)
    r.put(0x1A298AC0C5B2F470L /* methodKey(F, "org.sireum.MBox22", "value17").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value17)
    r.put(0x9D231E65804F6DADL /* methodKey(F, "org.sireum.MBox22", "value18").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value18)
    r.put(0xFE6ED168F6CAF385L /* methodKey(F, "org.sireum.MBox22", "value19").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value19)
    r.put(0xCCEC9A46AB6C033EL /* methodKey(F, "org.sireum.MBox22", "value20").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value20)
    r.put(0xF90C359594A6BE32L /* methodKey(F, "org.sireum.MBox22", "value21").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value21)
    r.put(0xC49F15F11E37C961L /* methodKey(F, "org.sireum.MBox22", "value22").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].value22)
    r.put(0x03B760E292FE47EEL /* methodKey(F, "org.sireum.MEither", "isLeft").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither[Any, Any]].isLeft)
    r.put(0x051B8EFE4C845FDCL /* methodKey(F, "org.sireum.MEither", "isRight").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither[Any, Any]].isRight)
    r.put(0x5BEE522E09D24258L /* methodKey(F, "org.sireum.MEither", "leftOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither[Any, Any]].leftOpt)
    r.put(0xACA606421F38C9FEL /* methodKey(F, "org.sireum.MEither", "left").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither[Any, Any]].left)
    r.put(0x218131EF957842EFL /* methodKey(F, "org.sireum.MEither", "rightOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither[Any, Any]].rightOpt)
    r.put(0x0111FBBABFA1BD1EL /* methodKey(F, "org.sireum.MEither", "right").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither[Any, Any]].right)
    r.put(0x326FD747ADF7F7A8L /* methodKey(F, "org.sireum.MEither.Left", "value").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither.Left[Any, Any]].value)
    r.put(0xC064B24A38CBF063L /* methodKey(F, "org.sireum.MEither.Left", "isLeft").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither.Left[Any, Any]].isLeft)
    r.put(0xA76FD2A5126A0BCBL /* methodKey(F, "org.sireum.MEither.Left", "isRight").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither.Left[Any, Any]].isRight)
    r.put(0x75C9B9814FE45822L /* methodKey(F, "org.sireum.MEither.Left", "leftOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither.Left[Any, Any]].leftOpt)
    r.put(0x709359A9B444700AL /* methodKey(F, "org.sireum.MEither.Left", "left").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither.Left[Any, Any]].left)
    r.put(0x44AD81477C5B1E77L /* methodKey(F, "org.sireum.MEither.Left", "rightOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither.Left[Any, Any]].rightOpt)
    r.put(0xB117C20DBCBBB7E6L /* methodKey(F, "org.sireum.MEither.Left", "right").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither.Left[Any, Any]].right)
    r.put(0x9C2BD49A91DB4D1AL /* methodKey(F, "org.sireum.MEither.Right", "value").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither.Right[Any, Any]].value)
    r.put(0xD34F89DD72E214ADL /* methodKey(F, "org.sireum.MEither.Right", "isLeft").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither.Right[Any, Any]].isLeft)
    r.put(0x7CCB2C3D46EF4C07L /* methodKey(F, "org.sireum.MEither.Right", "isRight").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither.Right[Any, Any]].isRight)
    r.put(0x4A9410B7626451BBL /* methodKey(F, "org.sireum.MEither.Right", "leftOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither.Right[Any, Any]].leftOpt)
    r.put(0xA766A5C229E15FD2L /* methodKey(F, "org.sireum.MEither.Right", "left").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither.Right[Any, Any]].left)
    r.put(0xC4D5AD7892FDEF1AL /* methodKey(F, "org.sireum.MEither.Right", "rightOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither.Right[Any, Any]].rightOpt)
    r.put(0x0B8F6A50C930592DL /* methodKey(F, "org.sireum.MEither.Right", "right").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MEither.Right[Any, Any]].right)
    r.put(0xB36D4E44CACFA1F9L /* methodKey(F, "org.sireum.MJen", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].count())
    r.put(0x0D2BA92FE00E2FFDL /* methodKey(F, "org.sireum.MJen", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].zipWithIndex)
    r.put(0xAF3E452F9041F388L /* methodKey(F, "org.sireum.MJen", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].head)
    r.put(0x2F0BC7B0EE6F0EFEL /* methodKey(F, "org.sireum.MJen", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].headOption)
    r.put(0x10D40047468F756BL /* methodKey(F, "org.sireum.MJen", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].toMSZ)
    r.put(0xD62CE0BDE97C47D7L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "s").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].s)
    r.put(0x9949B38324E4AFE3L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].string)
    r.put(0xCA851D5FCB7EAEFFL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].count())
    r.put(0xCD43E1E82900418EL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].zipWithIndex)
    r.put(0x08102CB1521F05F2L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].head)
    r.put(0x9FBE9E0E53BC3F53L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].headOption)
    r.put(0x3A596E0A00387803L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].toMSZ)
    r.put(0xA06146A1FDB1B157L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "s").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].s)
    r.put(0x0F3D04487A85EFAEL /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].string)
    r.put(0x4BB76F11A1E7A9CDL /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].count())
    r.put(0x1C0E9D824426342BL /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].zipWithIndex)
    r.put(0xB8C40658CE3A99E1L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].head)
    r.put(0xF7A7945786347706L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].headOption)
    r.put(0xE408B8C67E1CCFDCL /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].toMSZ)
    r.put(0xC8FD3A523A2E815AL /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "m").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].m)
    r.put(0xDE371502346F1447L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].string)
    r.put(0xAAF3610356E989C1L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].count())
    r.put(0x5F61C05B4552AC1AL /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].zipWithIndex)
    r.put(0x42EB2D65D1A90604L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].head)
    r.put(0x5BF84A4474B5A305L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].headOption)
    r.put(0x48FD05FCA77C43B5L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].toMSZ)
    r.put(0x73EAA4A7B4842E95L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "m").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].m)
    r.put(0xC247219EDE3F289AL /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].string)
    r.put(0xF056F3BB58C843D7L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].count())
    r.put(0x4582588B5159457CL /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].zipWithIndex)
    r.put(0x9F7B8BC67AEAF118L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].head)
    r.put(0x3EB38422E79791D0L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].headOption)
    r.put(0x5577D6B6FD6543CCL /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].toMSZ)
    r.put(0x5863E78C7A6AD471L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].gen)
    r.put(0x617DCA39BBB91327L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "p").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].p)
    r.put(0x928BCB1FB6792FB1L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].string)
    r.put(0x5D29B85542EF6CE4L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].count())
    r.put(0xA523999F1508E644L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].zipWithIndex)
    r.put(0x81DD803C10B0CC99L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].head)
    r.put(0x22F8FC1AF942A3A1L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].headOption)
    r.put(0x0FAC1758F3ADC1CAL /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].toMSZ)
    r.put(0x4E33389E3A2C836CL /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].gen)
    r.put(0x9BB209666EF7F748L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "f").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].f)
    r.put(0x3863697995693370L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].string)
    r.put(0x814D4615844400F0L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].count())
    r.put(0x648B69AD92993A88L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].zipWithIndex)
    r.put(0xF0369F539F4B9A91L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].head)
    r.put(0xE27A43938E164A83L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].headOption)
    r.put(0x311F46FB10DE4570L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].toMSZ)
    r.put(0xF001279820F29455L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].gen)
    r.put(0xD9B1DF25A1D1154AL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "f").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].f)
    r.put(0x71F2D652D35CCBD0L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].string)
    r.put(0x6FCCD790DC306DAEL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].count())
    r.put(0x025AA085F8548168L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].zipWithIndex)
    r.put(0x997471A7E32614EAL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].head)
    r.put(0x6E95BF3107A2702AL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].headOption)
    r.put(0xCDFCFD7AEF05A5C5L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].toMSZ)
    r.put(0x760357B25A630227L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].gen)
    r.put(0x6E1F7221FAC48879L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "start").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].start)
    r.put(0xD67CC3427062D69BL /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "end").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].end)
    r.put(0x1692EF225FEED4F8L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].string)
    r.put(0xE89AFEB487E69B36L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].count())
    r.put(0xE4E09EB6ACF6A8F4L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].zipWithIndex)
    r.put(0xBE4E9F3981F5BFC7L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].head)
    r.put(0x52BEC2E0D935B925L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].headOption)
    r.put(0x1108D4A6BC585695L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].toMSZ)
    r.put(0x4B72975D0CBCA03DL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].gen)
    r.put(0x9FCE0432FDA9100FL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "p").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].p)
    r.put(0xD2A7DA4EBDAFD949L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].string)
    r.put(0xA378A99C439309D9L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].count())
    r.put(0x3D75F8E36E1E54AEL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].zipWithIndex)
    r.put(0x8E8DEE8D1C4A7843L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].head)
    r.put(0x9DB3C3FAB61B4997L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].headOption)
    r.put(0xCF893E015A4A836CL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].toMSZ)
    r.put(0x5F2A9D30488A95B9L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].gen)
    r.put(0xE05A1012BF918616L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "p").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].p)
    r.put(0x508E2C5FD3A7A98BL /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].string)
    r.put(0x8142196B6CDB2E29L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].count())
    r.put(0x77C3C012023813D9L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].zipWithIndex)
    r.put(0x6F27B9AAD6ED2DFEL /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].head)
    r.put(0xD4714DCD0E32C0FDL /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].headOption)
    r.put(0x0455326B6F5F6D22L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].toMSZ)
    r.put(0x287D598288236E19L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].gen)
    r.put(0xBCA172A6DAA1C267L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].string)
    r.put(0xCDE245B062E110CAL /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].count())
    r.put(0xFB350ECA9D12ACE1L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].zipWithIndex)
    r.put(0xCCDD3B6DBDA719B0L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].head)
    r.put(0x6889BBFFA32C04F5L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].headOption)
    r.put(0xB2EB1056DEB1A365L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].toMSZ)
    r.put(0xE0DEC887663C593DL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].gen)
    r.put(0x690FB319DB5D9D90L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "gen2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].gen2)
    r.put(0x9B900BD0BFAADB1AL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].string)
    r.put(0xD0B6628DC963895FL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].count())
    r.put(0x38C8BE9A2CD4966CL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].zipWithIndex)
    r.put(0xB3404E5253C45FBFL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].head)
    r.put(0x5DB6B77430BEA748L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].headOption)
    r.put(0x3CC60E2366C233B7L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].toMSZ)
    r.put(0x60B25CE8FE1AF566L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].gen)
    r.put(0x3230F1D60D95E16EL /* methodKey(F, "org.sireum.MJen.Internal.Concat", "gen2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].gen2)
    r.put(0x66DD51D7B0339E0AL /* methodKey(F, "org.sireum.MJen.Internal.Concat", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].string)
    r.put(0x62D038981F81AEA0L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].count())
    r.put(0xC6FB210BD61057E4L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].zipWithIndex)
    r.put(0xAC045219309F28A1L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].head)
    r.put(0xF0D65FE4CAEE3CDCL /* methodKey(F, "org.sireum.MJen.Internal.Concat", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].headOption)
    r.put(0xFA3BB7B17DA5B0F1L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].toMSZ)
    r.put(0x2E40DDBD5409CEABL /* methodKey(F, "org.sireum.MJen.Internal.Product", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].gen)
    r.put(0x37E2FCCF440FC912L /* methodKey(F, "org.sireum.MJen.Internal.Product", "gen2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].gen2)
    r.put(0x3F47AB82E45E1309L /* methodKey(F, "org.sireum.MJen.Internal.Product", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].string)
    r.put(0x9C6B7B3FBEF41567L /* methodKey(F, "org.sireum.MJen.Internal.Product", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].count())
    r.put(0x21AE9804935EC353L /* methodKey(F, "org.sireum.MJen.Internal.Product", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].zipWithIndex)
    r.put(0x605D7713134A0ACDL /* methodKey(F, "org.sireum.MJen.Internal.Product", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].head)
    r.put(0xF636B60B7710ED93L /* methodKey(F, "org.sireum.MJen.Internal.Product", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].headOption)
    r.put(0x24772ACD4535A57FL /* methodKey(F, "org.sireum.MJen.Internal.Product", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].toMSZ)
    r.put(0xD249655CA7A1CC2AL /* methodKey(F, "org.sireum.MOption", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MOption[Any]].isEmpty)
    r.put(0xB5A276945A5A83AFL /* methodKey(F, "org.sireum.MOption", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MOption[Any]].nonEmpty)
    r.put(0x6E95A4393E70BA6AL /* methodKey(F, "org.sireum.MOption", "get").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MOption[Any]].get)
    r.put(0xA2F560165877704CL /* methodKey(F, "org.sireum.MOption", "toMS").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MOption[Any]].toMS)
    r.put(0xFEA70FCCF6CCC0AEL /* methodKey(T, "org.sireum.MNone", "apply").value */, _ => org.sireum.MNone.apply())
    r.put(0x2FE7AAE45DF26868L /* methodKey(F, "org.sireum.MNone", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MNone[Any]].isEmpty)
    r.put(0x2EDAC939D7B5A26AL /* methodKey(F, "org.sireum.MNone", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MNone[Any]].nonEmpty)
    r.put(0xD992F31A882A750EL /* methodKey(F, "org.sireum.MNone", "get").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MNone[Any]].get)
    r.put(0x761B1B6ACF9A3E1CL /* methodKey(F, "org.sireum.MNone", "toMS").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MNone[Any]].toMS)
    r.put(0xEC37BDFDA5A8EE0DL /* methodKey(F, "org.sireum.MSome", "value").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MSome[Any]].value)
    r.put(0xF1E362051A5A346DL /* methodKey(F, "org.sireum.MSome", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MSome[Any]].isEmpty)
    r.put(0x6D922EAF7D6877A2L /* methodKey(F, "org.sireum.MSome", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MSome[Any]].nonEmpty)
    r.put(0x196790B18C1EA704L /* methodKey(F, "org.sireum.MSome", "get").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MSome[Any]].get)
    r.put(0x0966E955F9B80C4EL /* methodKey(F, "org.sireum.MSome", "toMS").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.MSome[Any]].toMS)
    r.put(0x39008A07B8040E60L /* methodKey(F, "org.sireum.Map", "entries").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].entries)
    r.put(0x7858D1EB852B9473L /* methodKey(F, "org.sireum.Map", "keys").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].keys)
    r.put(0x6716220D903A2C3EL /* methodKey(F, "org.sireum.Map", "values").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].values)
    r.put(0x76E95A60C2DEBC87L /* methodKey(F, "org.sireum.Map", "keySet").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].keySet)
    r.put(0x88744F508721D301L /* methodKey(F, "org.sireum.Map", "valueSet").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].valueSet)
    r.put(0xD1BD87AD63F80A6AL /* methodKey(F, "org.sireum.Map", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].isEmpty)
    r.put(0x136DED63433F9151L /* methodKey(F, "org.sireum.Map", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].nonEmpty)
    r.put(0xB8D5248EF0579882L /* methodKey(F, "org.sireum.Map", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].size)
    r.put(0x18332C225979836FL /* methodKey(F, "org.sireum.Map", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].string)
    r.put(0x8451A12070FDF8B5L /* methodKey(F, "org.sireum.Map", "hash").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].hash)
    r.put(0xAE06B58811D6AC23L /* methodKey(F, "org.sireum.ObjPrinter", "freshNum").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].freshNum())
    r.put(0x055A847F797C8A9AL /* methodKey(F, "org.sireum.Option", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Option[Any]].isEmpty)
    r.put(0xF85F056ABDD42C4AL /* methodKey(F, "org.sireum.Option", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Option[Any]].nonEmpty)
    r.put(0x66789EDFA0B390D2L /* methodKey(F, "org.sireum.Option", "get").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Option[Any]].get)
    r.put(0x83CEBCAD28E43C27L /* methodKey(F, "org.sireum.Option", "toIS").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Option[Any]].toIS)
    r.put(0x1725EFD9C70BC18EL /* methodKey(T, "org.sireum.None", "apply").value */, _ => org.sireum.None.apply())
    r.put(0x9FAE67433DCA559FL /* methodKey(F, "org.sireum.None", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.None[Any]].isEmpty)
    r.put(0x5176776D31203C0CL /* methodKey(F, "org.sireum.None", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.None[Any]].nonEmpty)
    r.put(0x740ACAC144861CAFL /* methodKey(F, "org.sireum.None", "get").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.None[Any]].get)
    r.put(0xEEA76DF934D260E6L /* methodKey(F, "org.sireum.None", "toIS").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.None[Any]].toIS)
    r.put(0x244076A6C28D9174L /* methodKey(F, "org.sireum.Some", "value").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Some[Any]].value)
    r.put(0x7632AD54400C065EL /* methodKey(F, "org.sireum.Some", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Some[Any]].isEmpty)
    r.put(0x87D6C4FC8BC56AB0L /* methodKey(F, "org.sireum.Some", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Some[Any]].nonEmpty)
    r.put(0x66419D546C36C8D2L /* methodKey(F, "org.sireum.Some", "get").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Some[Any]].get)
    r.put(0x0C3BDF0F10F5EF12L /* methodKey(F, "org.sireum.Some", "toIS").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Some[Any]].toIS)
    r.put(0xDF6D109B1995ED1CL /* methodKey(F, "org.sireum.OsProto.Proc.Result", "ok").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc.Result].ok)
    r.put(0xCB8EA07C0E209EA3L /* methodKey(F, "org.sireum.OsProto.Proc.Result", "out").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc.Result].out)
    r.put(0x09BEE08FBA133EB2L /* methodKey(F, "org.sireum.OsProto.Proc.Result", "err").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc.Result].err)
    r.put(0xEB9FF3BFA83F6F4CL /* methodKey(F, "org.sireum.OsProto.Proc.Result", "exitCode").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc.Result].exitCode)
    r.put(0x3E8B1C6D27FDEFD7L /* methodKey(F, "org.sireum.OsProto.Proc", "dontInheritEnv").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].dontInheritEnv)
    r.put(0xDBD27F5EE5291B46L /* methodKey(F, "org.sireum.OsProto.Proc", "redirectErr").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].redirectErr)
    r.put(0x4776B6063D67E314L /* methodKey(F, "org.sireum.OsProto.Proc", "bufferErr").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].bufferErr)
    r.put(0x002B7657F42F47CBL /* methodKey(F, "org.sireum.OsProto.Proc", "console").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].console)
    r.put(0x08A22769F88ECACAL /* methodKey(F, "org.sireum.OsProto.Proc", "echoEnv").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].echoEnv)
    r.put(0xDE332EEC9220CD4DL /* methodKey(F, "org.sireum.OsProto.Proc", "echo").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].echo)
    r.put(0x5D1453F86AA879BBL /* methodKey(F, "org.sireum.OsProto.Proc", "standard").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].standard)
    r.put(0x84C864B3FF3BA9B5L /* methodKey(F, "org.sireum.OsProto.Proc", "script").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].script)
    r.put(0x11B3D6416D75FBDFL /* methodKey(F, "org.sireum.OsProto.Proc", "shouldPrintCommands").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].shouldPrintCommands)
    r.put(0xBB51C3989E35E517L /* methodKey(F, "org.sireum.OsProto.Proc", "shouldOutputConsole").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].shouldOutputConsole)
    r.put(0x82B6DC0F736F83B4L /* methodKey(F, "org.sireum.OsProto.Proc", "isErrAsOut").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].isErrAsOut)
    r.put(0x1949000AA4956ACFL /* methodKey(F, "org.sireum.OsProto.Proc", "in").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].in)
    r.put(0x932DD7345D58AD62L /* methodKey(F, "org.sireum.OsProto.Proc", "cmds").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].cmds)
    r.put(0x340ADD96EFF0FAD7L /* methodKey(F, "org.sireum.OsProto.Proc", "run").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].run())
    r.put(0x4C7720AB71830661L /* methodKey(F, "org.sireum.OsProto.Proc", "runCheck").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].runCheck())
    r.put(0xEE8B3DCE4ABB66FEL /* methodKey(F, "org.sireum.Poset", "nodes").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].nodes)
    r.put(0xE03DA67D49832938L /* methodKey(F, "org.sireum.Poset", "nodesInverse").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].nodesInverse)
    r.put(0x7D737F7E6B0CBA13L /* methodKey(F, "org.sireum.Poset", "parents").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].parents)
    r.put(0x4570DD4B0EE22E1DL /* methodKey(F, "org.sireum.Poset", "children").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].children)
    r.put(0x70CD3071C5CA7545L /* methodKey(F, "org.sireum.Poset", "emptySet").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].emptySet)
    r.put(0x407A41EC93240598L /* methodKey(F, "org.sireum.Poset", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].size)
    r.put(0x23CA7C6652E0E048L /* methodKey(F, "org.sireum.Poset", "hash").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].hash)
    r.put(0xFEF609F4D0BD0087L /* methodKey(F, "org.sireum.Poset", "rootNodes").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].rootNodes)
    r.put(0x5359B8BCA6824660L /* methodKey(F, "org.sireum.Poset", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].string)
    r.put(0xBB49FFEE27734060L /* methodKey(F, "org.sireum.Random.Gen.TestRunner", "next").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen.TestRunner[Any]].next())
    r.put(0x70C3ADA95BF97AF6L /* methodKey(F, "org.sireum.Random.Gen", "nextB").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextB())
    r.put(0x3A59C7C91E0B02D7L /* methodKey(F, "org.sireum.Random.Gen", "nextC").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextC())
    r.put(0x6D4FF9EE81E245DEL /* methodKey(F, "org.sireum.Random.Gen", "nextZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextZ())
    r.put(0x29FDB54015ADCB02L /* methodKey(F, "org.sireum.Random.Gen", "nextF32_01").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextF32_01())
    r.put(0x6E6F0DCE8E950E3CL /* methodKey(F, "org.sireum.Random.Gen", "nextF64_01").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextF64_01())
    r.put(0xA3446174C977C743L /* methodKey(F, "org.sireum.Random.Gen", "nextF32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextF32())
    r.put(0x0807983A8C70B8B9L /* methodKey(F, "org.sireum.Random.Gen", "nextF64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextF64())
    r.put(0x0A9C273C416E6CA3L /* methodKey(F, "org.sireum.Random.Gen", "nextR").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextR())
    r.put(0x85765CAEBF99C0C4L /* methodKey(F, "org.sireum.Random.Gen", "nextS8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextS8())
    r.put(0x01FD49E6B697B682L /* methodKey(F, "org.sireum.Random.Gen", "nextS16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextS16())
    r.put(0x7E44BFC8F3468C79L /* methodKey(F, "org.sireum.Random.Gen", "nextS32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextS32())
    r.put(0xA22F425C81D79CD3L /* methodKey(F, "org.sireum.Random.Gen", "nextS64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextS64())
    r.put(0x5C581E106F8DACA3L /* methodKey(F, "org.sireum.Random.Gen", "nextU8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextU8())
    r.put(0x55D54A23041AB6AFL /* methodKey(F, "org.sireum.Random.Gen", "nextU16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextU16())
    r.put(0x6A77B14E0E8F6468L /* methodKey(F, "org.sireum.Random.Gen", "nextU32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextU32())
    r.put(0x0413A0FB99F18F6FL /* methodKey(F, "org.sireum.Random.Gen", "nextU64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextU64())
    r.put(0x2C6620C10FF5985EL /* methodKey(F, "org.sireum.Random.Gen", "nextN8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextN8())
    r.put(0x0ED5B6E668FB7A12L /* methodKey(F, "org.sireum.Random.Gen", "nextN16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextN16())
    r.put(0x2A9BD70891D5EBE2L /* methodKey(F, "org.sireum.Random.Gen", "nextN32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextN32())
    r.put(0x20E3F7CF0BCFB243L /* methodKey(F, "org.sireum.Random.Gen", "nextN64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextN64())
    r.put(0x17E38D41277CE680L /* methodKey(F, "org.sireum.Random.Gen", "nextZ8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextZ8())
    r.put(0xBC5AF0C19FB9A67AL /* methodKey(F, "org.sireum.Random.Gen", "nextZ16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextZ16())
    r.put(0x8BC955DDBF5BAA4CL /* methodKey(F, "org.sireum.Random.Gen", "nextZ32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextZ32())
    r.put(0xD34048B746B2A386L /* methodKey(F, "org.sireum.Random.Gen", "nextZ64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextZ64())
    r.put(0x0100CF13FC37E316L /* methodKey(F, "org.sireum.Random.Gen64", "genU64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].genU64())
    r.put(0x2FFEC0E7E431C48DL /* methodKey(F, "org.sireum.Random.Gen64", "nextU32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextU32())
    r.put(0x8D16BDD1818A38A6L /* methodKey(F, "org.sireum.Random.Gen64", "nextU64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextU64())
    r.put(0x52F3D7E1A51D7579L /* methodKey(F, "org.sireum.Random.Gen64", "nextB").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextB())
    r.put(0x5AAF9BEF32D0AABEL /* methodKey(F, "org.sireum.Random.Gen64", "nextC").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextC())
    r.put(0x77AA30FCB09ABF46L /* methodKey(F, "org.sireum.Random.Gen64", "nextZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextZ())
    r.put(0xA771A86C83F5649CL /* methodKey(F, "org.sireum.Random.Gen64", "nextF32_01").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextF32_01())
    r.put(0xE15984A1C74FF89AL /* methodKey(F, "org.sireum.Random.Gen64", "nextF64_01").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextF64_01())
    r.put(0xE7F7D5D6DCB3FD70L /* methodKey(F, "org.sireum.Random.Gen64", "nextF32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextF32())
    r.put(0xB55CC739186C283EL /* methodKey(F, "org.sireum.Random.Gen64", "nextF64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextF64())
    r.put(0x38A963B9DEEF5393L /* methodKey(F, "org.sireum.Random.Gen64", "nextR").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextR())
    r.put(0x281DE6EE2FD3AE6FL /* methodKey(F, "org.sireum.Random.Gen64", "nextS8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextS8())
    r.put(0x81D60483022FB187L /* methodKey(F, "org.sireum.Random.Gen64", "nextS16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextS16())
    r.put(0xBF0F90688DF01DF7L /* methodKey(F, "org.sireum.Random.Gen64", "nextS32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextS32())
    r.put(0xD232F92820118A84L /* methodKey(F, "org.sireum.Random.Gen64", "nextS64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextS64())
    r.put(0xF322B9A8CE958BACL /* methodKey(F, "org.sireum.Random.Gen64", "nextU8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextU8())
    r.put(0xFE1824054D455FB8L /* methodKey(F, "org.sireum.Random.Gen64", "nextU16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextU16())
    r.put(0xA712220E4972404FL /* methodKey(F, "org.sireum.Random.Gen64", "nextN8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextN8())
    r.put(0x96DEA8DBCE8ECA24L /* methodKey(F, "org.sireum.Random.Gen64", "nextN16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextN16())
    r.put(0x849D4647D9F2575DL /* methodKey(F, "org.sireum.Random.Gen64", "nextN32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextN32())
    r.put(0xCB2313A80D1F7412L /* methodKey(F, "org.sireum.Random.Gen64", "nextN64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextN64())
    r.put(0x5EE69B204E2C2196L /* methodKey(F, "org.sireum.Random.Gen64", "nextZ8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextZ8())
    r.put(0xE71A54A1C38B99B7L /* methodKey(F, "org.sireum.Random.Gen64", "nextZ16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextZ16())
    r.put(0x8FCB51BECB555E06L /* methodKey(F, "org.sireum.Random.Gen64", "nextZ32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextZ32())
    r.put(0x4832E47D8ED0A6E6L /* methodKey(F, "org.sireum.Random.Gen64", "nextZ64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextZ64())
    r.put(0x319F59BE1F98CEFBL /* methodKey(F, "org.sireum.Random.Gen64Impl", "gen").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].gen)
    r.put(0x4F93271AB86D3A9EL /* methodKey(F, "org.sireum.Random.Gen64Impl", "genU64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].genU64())
    r.put(0xE5B8F03A8497E881L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextU32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextU32())
    r.put(0x44C6563B1FB3BF87L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextU64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextU64())
    r.put(0x183873AB8C493DB7L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextB").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextB())
    r.put(0xE78A3838FFCE7823L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextC").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextC())
    r.put(0x57635D9E5CCC1730L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextZ())
    r.put(0xCE5F6054F5E14D6BL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextF32_01").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextF32_01())
    r.put(0x7C90BE0B74EFA888L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextF64_01").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextF64_01())
    r.put(0x38826B9968049000L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextF32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextF32())
    r.put(0xB93C1A48D694C867L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextF64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextF64())
    r.put(0x08FE378860485E59L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextR").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextR())
    r.put(0xF8D9EB1F3631413FL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextS8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextS8())
    r.put(0x2B4CA64DE7BBCEFDL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextS16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextS16())
    r.put(0x066B49490F253F56L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextS32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextS32())
    r.put(0x4156EAAA4185DBEEL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextS64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextS64())
    r.put(0x543364FDE746B215L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextU8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextU8())
    r.put(0x29F8F6F14EDEBC8CL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextU16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextU16())
    r.put(0xE5C14FC1DB1DBFF8L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextN8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextN8())
    r.put(0xA2D27C28E749B7BBL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextN16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextN16())
    r.put(0xA4391EB6AE593D6AL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextN32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextN32())
    r.put(0x33DB4CA7686B1E02L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextN64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextN64())
    r.put(0xA2C6BB7883FC121FL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ8").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextZ8())
    r.put(0x730DB046E5BABE5AL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ16").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextZ16())
    r.put(0x48C12E1D83627721L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ32").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextZ32())
    r.put(0xB6054FAAB08F9395L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ64").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextZ64())
    r.put(0x666BDBF3CCB5B846L /* methodKey(F, "org.sireum.Random.Impl.SplitMix64", "seed").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.SplitMix64].seed)
    r.put(0x905BE489B918BF5CL /* methodKey(F, "org.sireum.Random.Impl.SplitMix64", "next").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.SplitMix64].next())
    r.put(0xDA240AD9FE9CF266L /* methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed0").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoshiro256].seed0)
    r.put(0x54E757587F88CAE5L /* methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoshiro256].seed1)
    r.put(0x058EADA932BAA68AL /* methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoshiro256].seed2)
    r.put(0xA55F3F89694D9FB6L /* methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoshiro256].seed3)
    r.put(0x46E718DA75CEBEA4L /* methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "update").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoshiro256].update())
    r.put(0x532F777800268AAAL /* methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "pp").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoshiro256].pp())
    r.put(0xF94E24B9A0D7AD03L /* methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "ss").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoshiro256].ss())
    r.put(0x29B5EE96D40CFF2FL /* methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "p").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoshiro256].p())
    r.put(0x0EF16FE049591C27L /* methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed0").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoroshiro128].seed0)
    r.put(0xB6FF0EECDF928E96L /* methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoroshiro128].seed1)
    r.put(0x1E9552681FB9FA0FL /* methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed2").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoroshiro128].seed2)
    r.put(0xA97A2E309C9FECBDL /* methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoroshiro128].seed3)
    r.put(0x37FD9CE69FB1967DL /* methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "update").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoroshiro128].update())
    r.put(0xF5194CBD3F1AFA7FL /* methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "pp").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoroshiro128].pp())
    r.put(0x61E85DBC318ABC8BL /* methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "ss").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoroshiro128].ss())
    r.put(0x8742A1AA945FA553L /* methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "p").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoroshiro128].p())
    r.put(0xBA04DDC1AAB5F952L /* methodKey(F, "org.sireum.Set", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].elements)
    r.put(0x84C3C498A59AE972L /* methodKey(F, "org.sireum.Set", "hash").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].hash)
    r.put(0x175A84A7796C2B15L /* methodKey(F, "org.sireum.Set", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].isEmpty)
    r.put(0xD62C82F2B6D86FCEL /* methodKey(F, "org.sireum.Set", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].nonEmpty)
    r.put(0xF536D42F73F31576L /* methodKey(F, "org.sireum.Set", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].size)
    r.put(0x3132731ADE4E99CBL /* methodKey(F, "org.sireum.Set", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].string)
    r.put(0x6C76D6BF1777DCB1L /* methodKey(F, "org.sireum.Stack", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Stack[Any]].elements)
    r.put(0x2365A3560264E9F4L /* methodKey(F, "org.sireum.Stack", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Stack[Any]].size)
    r.put(0x13E54D95483378A4L /* methodKey(F, "org.sireum.Stack", "isEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Stack[Any]].isEmpty)
    r.put(0x2765184A9F9ED91BL /* methodKey(F, "org.sireum.Stack", "nonEmpty").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Stack[Any]].nonEmpty)
    r.put(0x86F5AC1507460108L /* methodKey(F, "org.sireum.Stack", "peek").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Stack[Any]].peek)
    r.put(0xE4FC7C3BEBC50A99L /* methodKey(F, "org.sireum.Stack", "pop").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Stack[Any]].pop)
    r.put(0x296028B53A88B384L /* methodKey(F, "org.sireum.UnionFind", "elements").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.UnionFind[Any]].elements)
    r.put(0xD1F62928F60386F4L /* methodKey(F, "org.sireum.UnionFind", "elementsInverse").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.UnionFind[Any]].elementsInverse)
    r.put(0xCCED102E798724F3L /* methodKey(F, "org.sireum.UnionFind", "parentOf").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.UnionFind[Any]].parentOf)
    r.put(0x9CCF7505C16DE5CBL /* methodKey(F, "org.sireum.UnionFind", "sizeOf").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.UnionFind[Any]].sizeOf)
    r.put(0xF299120462C83E3FL /* methodKey(F, "org.sireum.UnionFind", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.UnionFind[Any]].size)
    r.put(0xC34755F56DC6F16AL /* methodKey(F, "org.sireum.UnionFind", "hash").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.UnionFind[Any]].hash)
    r.put(0xC3C3DDB94929FEB0L /* methodKey(F, "org.sireum.UnionFind", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.UnionFind[Any]].string)
    r.put(0x3F7BC72B371022B5L /* methodKey(F, "org.sireum.CoursierFileInfo", "org").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CoursierFileInfo].org)
    r.put(0xF48034BB86BD0CC6L /* methodKey(F, "org.sireum.CoursierFileInfo", "module").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CoursierFileInfo].module)
    r.put(0x4061521D26B92766L /* methodKey(F, "org.sireum.CoursierFileInfo", "version").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CoursierFileInfo].version)
    r.put(0x8F21A4D5F1E5555FL /* methodKey(F, "org.sireum.CoursierFileInfo", "pathString").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CoursierFileInfo].pathString)
    r.put(0xABF77A217D4E0D5CL /* methodKey(F, "org.sireum.CoursierFileInfo", "path").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.CoursierFileInfo].path)
    r.put(0xA413F637248EA204L /* methodKey(F, "org.sireum.Coursier.Proxy", "hostOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Coursier.Proxy].hostOpt)
    r.put(0x3C94143049E7C6E3L /* methodKey(F, "org.sireum.Coursier.Proxy", "nonHostsOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Coursier.Proxy].nonHostsOpt)
    r.put(0xF0A802E1BE5D29E0L /* methodKey(F, "org.sireum.Coursier.Proxy", "portOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Coursier.Proxy].portOpt)
    r.put(0x0E615CEB697B5DA9L /* methodKey(F, "org.sireum.Coursier.Proxy", "protocolOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Coursier.Proxy].protocolOpt)
    r.put(0xB0EEB8831C596641L /* methodKey(F, "org.sireum.Coursier.Proxy", "protocolUserEnvKeyOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Coursier.Proxy].protocolUserEnvKeyOpt)
    r.put(0xFC8FBEA89C7FB601L /* methodKey(F, "org.sireum.Coursier.Proxy", "protocolPasswordEnvKeyOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Coursier.Proxy].protocolPasswordEnvKeyOpt)
    r.put(0x6385FC3ED3C1DF7EL /* methodKey(F, "org.sireum.GitHub.Repository", "connection").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Repository].connection)
    r.put(0xC52541F38D449DCAL /* methodKey(F, "org.sireum.GitHub.Repository", "owner").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Repository].owner)
    r.put(0xFAAF9B7E15827429L /* methodKey(F, "org.sireum.GitHub.Repository", "name").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Repository].name)
    r.put(0xCF01113035769901L /* methodKey(F, "org.sireum.GitHub.Repository", "latestRelease").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Repository].latestRelease)
    r.put(0x45F1062445734626L /* methodKey(F, "org.sireum.GitHub.Repository", "releases").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Repository].releases)
    r.put(0x8C8C6951055588F4L /* methodKey(F, "org.sireum.GitHub.Release", "repo").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Release].repo)
    r.put(0x4C924DF002A544A0L /* methodKey(F, "org.sireum.GitHub.Release", "id").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Release].id)
    r.put(0xCAAF4CC1284EBDE7L /* methodKey(F, "org.sireum.GitHub.Release", "name").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Release].name)
    r.put(0xDC9411339225D406L /* methodKey(F, "org.sireum.GitHub.Release", "publishedTime").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Release].publishedTime)
    r.put(0x83D4A07986173B79L /* methodKey(F, "org.sireum.GitHub.Release", "isDraft").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Release].isDraft)
    r.put(0x91991F73C7E620F0L /* methodKey(F, "org.sireum.GitHub.Release", "isPrerelease").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Release].isPrerelease)
    r.put(0x047622B7DA857025L /* methodKey(F, "org.sireum.GitHub.Release", "tagName").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Release].tagName)
    r.put(0x560388A8408E8ADAL /* methodKey(F, "org.sireum.GitHub.Release", "commit").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Release].commit)
    r.put(0x2F28B593ECCAFF91L /* methodKey(F, "org.sireum.GitHub.Release", "tarUrl").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Release].tarUrl)
    r.put(0x9C28FB26DDECDCB4L /* methodKey(F, "org.sireum.GitHub.Release", "zipUrl").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Release].zipUrl)
    r.put(0xCAEF5DB9B679AD88L /* methodKey(F, "org.sireum.GitHub.Release", "assets").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Release].assets)
    r.put(0x8031F0F23347ECACL /* methodKey(F, "org.sireum.GitHub.Asset", "release").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Asset].release)
    r.put(0x637EEB16C018C8BDL /* methodKey(F, "org.sireum.GitHub.Asset", "id").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Asset].id)
    r.put(0x9D70C3296341DE4EL /* methodKey(F, "org.sireum.GitHub.Asset", "name").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Asset].name)
    r.put(0x75006ECF02212C51L /* methodKey(F, "org.sireum.GitHub.Asset", "label").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Asset].label)
    r.put(0x8C97F5A910E6652BL /* methodKey(F, "org.sireum.GitHub.Asset", "state").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Asset].state)
    r.put(0x7C7315FF31E3D523L /* methodKey(F, "org.sireum.GitHub.Asset", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Asset].size)
    r.put(0xC9090B7DE5593913L /* methodKey(F, "org.sireum.GitHub.Asset", "contentType").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Asset].contentType)
    r.put(0x6C93F7AC844F4E01L /* methodKey(F, "org.sireum.GitHub.Asset", "url").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Asset].url)
    r.put(0xF61B1C3493728DF8L /* methodKey(F, "org.sireum.GitHub.Asset", "downloadCount").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Asset].downloadCount)
    r.put(0x7192811918B18FFCL /* methodKey(F, "org.sireum.Init.Plugin", "id").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init.Plugin].id)
    r.put(0xB8E00829EE9A8DE1L /* methodKey(F, "org.sireum.Init.Plugin", "isCommunity").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init.Plugin].isCommunity)
    r.put(0x29A0906CF65F6132L /* methodKey(F, "org.sireum.Init.Plugin", "isJar").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init.Plugin].isJar)
    r.put(0xF0ED9FA74CECFEACL /* methodKey(F, "org.sireum.Init.Plugin", "version").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init.Plugin].version)
    r.put(0xEEDA64964F06D49FL /* methodKey(F, "org.sireum.Init", "home").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].home)
    r.put(0xE8D2F2859C20CB1BL /* methodKey(F, "org.sireum.Init", "kind").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].kind)
    r.put(0xF7716769FAACABEDL /* methodKey(F, "org.sireum.Init", "versions").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].versions)
    r.put(0x65CFE7BF52111E3EL /* methodKey(F, "org.sireum.Init", "sireumV").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].sireumV)
    r.put(0x8C55504143C57434L /* methodKey(F, "org.sireum.Init", "cache").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].cache)
    r.put(0x981B2B3232167A66L /* methodKey(F, "org.sireum.Init", "pluginPrefix").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].pluginPrefix)
    r.put(0xC0B0C2DE4A0CB881L /* methodKey(F, "org.sireum.Init", "ideaCacheDir").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].ideaCacheDir)
    r.put(0x032FD793073A3372L /* methodKey(F, "org.sireum.Init", "pluginsCacheDir").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].pluginsCacheDir)
    r.put(0x9D735E53E4BEA32BL /* methodKey(F, "org.sireum.Init", "homeBin").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].homeBin)
    r.put(0xAA88BE7C2E40044DL /* methodKey(F, "org.sireum.Init", "homeLib").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].homeLib)
    r.put(0xCA6FE7482DBCBD9EL /* methodKey(F, "org.sireum.Init", "homeBinPlatform").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].homeBinPlatform)
    r.put(0xF78FA3367448F578L /* methodKey(F, "org.sireum.Init", "distroPlugins").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].distroPlugins)
    r.put(0xE2A1015CD69EA9F3L /* methodKey(F, "org.sireum.Init", "scalacPluginVersion").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].scalacPluginVersion)
    r.put(0x510160BCC772FB1AL /* methodKey(F, "org.sireum.Init", "coursierVersion").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].coursierVersion)
    r.put(0x15ED0A616E935901L /* methodKey(F, "org.sireum.Init", "jacocoVersion").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].jacocoVersion)
    r.put(0xBE63343C2C18F73EL /* methodKey(F, "org.sireum.Init", "scalacPlugin").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].scalacPlugin)
    r.put(0x0145EBAD83F068DEL /* methodKey(F, "org.sireum.Init", "scalaVersion").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].scalaVersion)
    r.put(0xF45FC5D3A94CC9B1L /* methodKey(F, "org.sireum.Init", "scalaHome").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].scalaHome)
    r.put(0xFF0CE42D296BA155L /* methodKey(F, "org.sireum.Init", "sireumJar").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].sireumJar)
    r.put(0x1213E2FE38E802A4L /* methodKey(F, "org.sireum.Init", "maryTtsJar").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].maryTtsJar)
    r.put(0xECAA1CDF1B2E8932L /* methodKey(F, "org.sireum.Init", "checkstack").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].checkstack)
    r.put(0x3672E35612705476L /* methodKey(F, "org.sireum.Init", "checkstackVersion").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].checkstackVersion)
    r.put(0x36E11EC52DA0B6F5L /* methodKey(F, "org.sireum.Init", "javaVersion").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].javaVersion)
    r.put(0xAD801C858273905DL /* methodKey(F, "org.sireum.Init", "pwd7z").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].pwd7z)
    r.put(0xFC8E5B3CD4A66226L /* methodKey(F, "org.sireum.Init", "installScala").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].installScala())
    r.put(0x1FFA7C92CE77EAD3L /* methodKey(F, "org.sireum.Init", "installScalacPlugin").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].installScalacPlugin())
    r.put(0xAE2412F12F627832L /* methodKey(F, "org.sireum.Init", "installCoursier").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].installCoursier())
    r.put(0x4924789C9337C287L /* methodKey(F, "org.sireum.Init", "installJacoco").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].installJacoco())
    r.put(0x25C9B4E2D44AD008L /* methodKey(F, "org.sireum.Init", "pwd7zUrl").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].pwd7zUrl)
    r.put(0x607A0504446119A2L /* methodKey(F, "org.sireum.Init", "install7z").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].install7z())
    r.put(0x7AF0439B866D4FB2L /* methodKey(F, "org.sireum.Init", "installZ3").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].installZ3())
    r.put(0x2F3811F83D8BEEFFL /* methodKey(F, "org.sireum.Init", "installCVC").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].installCVC())
    r.put(0xAC68FF7561F9281AL /* methodKey(F, "org.sireum.Init", "installMaryTTS").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].installMaryTTS())
    r.put(0x9A32C9E5F4212A3FL /* methodKey(F, "org.sireum.Init", "installCheckStack").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].installCheckStack())
    r.put(0x7D2A68A135CD2753L /* methodKey(F, "org.sireum.Init", "installScripts").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].installScripts())
    r.put(0xBA28ABD6C1F69D05L /* methodKey(F, "org.sireum.Init", "isIdeaInUserHome").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].isIdeaInUserHome)
    r.put(0xEC4A65D5E8BF769FL /* methodKey(F, "org.sireum.Init", "basicDeps").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].basicDeps())
    r.put(0xD878810D05D5DD20L /* methodKey(F, "org.sireum.Init", "proyekCompileDeps").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].proyekCompileDeps())
    r.put(0x253BA259D4C72EA3L /* methodKey(F, "org.sireum.Init", "logikaDeps").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].logikaDeps())
    r.put(0xAFCA44E74EF326E5L /* methodKey(F, "org.sireum.Init", "deps").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Init].deps())
    r.put(0xC0213A716A8664FBL /* methodKey(F, "org.sireum.Os.Path.Impl", "value").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].value)
    r.put(0x51BAFEDF90121833L /* methodKey(F, "org.sireum.Os.Path.Impl", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].string)
    r.put(0xF195EEACA5668080L /* methodKey(F, "org.sireum.Os.Path.Impl", "procString").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].procString)
    r.put(0x6D8890A413C1C1F6L /* methodKey(F, "org.sireum.Os.Path.Impl", "canon").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].canon)
    r.put(0xB852B90E1E2652E3L /* methodKey(F, "org.sireum.Os.Path.Impl", "abs").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].abs)
    r.put(0x090D4A8D735DAA86L /* methodKey(F, "org.sireum.Os.Path.Impl", "exists").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].exists)
    r.put(0xA143EDEEACC8E625L /* methodKey(F, "org.sireum.Os.Path.Impl", "ext").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].ext)
    r.put(0xC8FD3C0F73F0D47AL /* methodKey(F, "org.sireum.Os.Path.Impl", "isAbs").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].isAbs)
    r.put(0xF853F9FAF786EACAL /* methodKey(F, "org.sireum.Os.Path.Impl", "isDir").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].isDir)
    r.put(0x709C38275EBBF09DL /* methodKey(F, "org.sireum.Os.Path.Impl", "isFile").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].isFile)
    r.put(0x2E3D25A0843F4BF0L /* methodKey(F, "org.sireum.Os.Path.Impl", "isSymLink").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].isSymLink)
    r.put(0x0B5B9A35FCF7F22DL /* methodKey(F, "org.sireum.Os.Path.Impl", "isExecutable").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].isExecutable)
    r.put(0x4D93EE6A51798EECL /* methodKey(F, "org.sireum.Os.Path.Impl", "isReadable").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].isReadable)
    r.put(0x0BB28086343CFBAEL /* methodKey(F, "org.sireum.Os.Path.Impl", "isWritable").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].isWritable)
    r.put(0xF00A3C1F5928799FL /* methodKey(F, "org.sireum.Os.Path.Impl", "kind").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].kind)
    r.put(0x25C244AEFC4BE2B9L /* methodKey(F, "org.sireum.Os.Path.Impl", "lastModified").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].lastModified)
    r.put(0x6CF43ED98C9D211EL /* methodKey(F, "org.sireum.Os.Path.Impl", "length").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].length)
    r.put(0xDEB07C577875983DL /* methodKey(F, "org.sireum.Os.Path.Impl", "list").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].list)
    r.put(0xBEEB449B918AF22FL /* methodKey(F, "org.sireum.Os.Path.Impl", "md5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].md5)
    r.put(0x77B34D3360D4FF89L /* methodKey(F, "org.sireum.Os.Path.Impl", "mkdir").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].mkdir())
    r.put(0x4E0C8831254BB259L /* methodKey(F, "org.sireum.Os.Path.Impl", "mkdirAll").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].mkdirAll())
    r.put(0x5CB031BDE5A30A33L /* methodKey(F, "org.sireum.Os.Path.Impl", "name").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].name)
    r.put(0xFDE5D9C87121E492L /* methodKey(F, "org.sireum.Os.Path.Impl", "properties").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].properties)
    r.put(0xEFA74AF4E12BDBB0L /* methodKey(F, "org.sireum.Os.Path.Impl", "readSymLink").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].readSymLink)
    r.put(0x23BBC638A789968AL /* methodKey(F, "org.sireum.Os.Path.Impl", "read").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].read)
    r.put(0xEC4CC34B06675055L /* methodKey(F, "org.sireum.Os.Path.Impl", "readLines").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].readLines)
    r.put(0x06C397D31DC10A35L /* methodKey(F, "org.sireum.Os.Path.Impl", "readLineStream").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].readLineStream)
    r.put(0xF4BF0CB61E7F0DADL /* methodKey(F, "org.sireum.Os.Path.Impl", "readLineMStream").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].readLineMStream)
    r.put(0x75DD71636D8AE1F0L /* methodKey(F, "org.sireum.Os.Path.Impl", "readU8s").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].readU8s)
    r.put(0x3E06166BA7CF286CL /* methodKey(F, "org.sireum.Os.Path.Impl", "readU8ms").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].readU8ms)
    r.put(0x12BCE2A81B980FDDL /* methodKey(F, "org.sireum.Os.Path.Impl", "readU8Stream").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].readU8Stream)
    r.put(0x542A0B77401C64F3L /* methodKey(F, "org.sireum.Os.Path.Impl", "readU8MStream").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].readU8MStream)
    r.put(0xF6F689B92711237BL /* methodKey(F, "org.sireum.Os.Path.Impl", "readCStream").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].readCStream)
    r.put(0xE2DE18167274AE23L /* methodKey(F, "org.sireum.Os.Path.Impl", "readIndexableC").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].readIndexableC)
    r.put(0xAB8EA0753FB3D740L /* methodKey(F, "org.sireum.Os.Path.Impl", "readCMStream").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].readCMStream)
    r.put(0x5DD0190285063061L /* methodKey(F, "org.sireum.Os.Path.Impl", "remove").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].remove())
    r.put(0xF2BABC839A9413FFL /* methodKey(F, "org.sireum.Os.Path.Impl", "removeAll").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].removeAll())
    r.put(0x7B95E0EE2D006919L /* methodKey(F, "org.sireum.Os.Path.Impl", "removeOnExit").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].removeOnExit())
    r.put(0x7571CF897B6EC471L /* methodKey(F, "org.sireum.Os.Path.Impl", "sha1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].sha1)
    r.put(0x4175AE92111F86F0L /* methodKey(F, "org.sireum.Os.Path.Impl", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].size)
    r.put(0x9EBCB808A930F8D7L /* methodKey(F, "org.sireum.Os.Path.Impl", "toUri").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].toUri)
    r.put(0xD9F373D6960E1661L /* methodKey(F, "org.sireum.Os.Path.Impl", "up").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].up)
    r.put(0xE5513EC0AA7EA1CEL /* methodKey(F, "org.sireum.Os.Path.Jen", "path").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].path)
    r.put(0xE79485389ECA27D7L /* methodKey(F, "org.sireum.Os.Path.Jen", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].string)
    r.put(0x25C397E2779C1D97L /* methodKey(F, "org.sireum.Os.Path.Jen", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].count())
    r.put(0xC8AAC0079B66C86BL /* methodKey(F, "org.sireum.Os.Path.Jen", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].zipWithIndex)
    r.put(0x639CA3616F723FBBL /* methodKey(F, "org.sireum.Os.Path.Jen", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].head)
    r.put(0xE22F30C19105F200L /* methodKey(F, "org.sireum.Os.Path.Jen", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].headOption)
    r.put(0x720EA45AA9C91028L /* methodKey(F, "org.sireum.Os.Path.Jen", "toISZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].toISZ)
    r.put(0x1B06D59E6FD13ED5L /* methodKey(F, "org.sireum.Os.Path.Jen", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].toMSZ)
    r.put(0x478C5208623E08EFL /* methodKey(F, "org.sireum.Os.Path.MJen", "path").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].path)
    r.put(0x10B6ACF09D76A8BFL /* methodKey(F, "org.sireum.Os.Path.MJen", "string").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].string)
    r.put(0xFF7433ACEAE3587DL /* methodKey(F, "org.sireum.Os.Path.MJen", "count").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].count())
    r.put(0x547C16567F6A286BL /* methodKey(F, "org.sireum.Os.Path.MJen", "zipWithIndex").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].zipWithIndex)
    r.put(0x88AC050CB3F50896L /* methodKey(F, "org.sireum.Os.Path.MJen", "head").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].head)
    r.put(0x8BBF5C1FD2B64C0DL /* methodKey(F, "org.sireum.Os.Path.MJen", "headOption").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].headOption)
    r.put(0x136A9CD428AD5398L /* methodKey(F, "org.sireum.Os.Path.MJen", "toMSZ").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].toMSZ)
    r.put(0xFFA674CE69506E84L /* methodKey(F, "org.sireum.Os.Proc.FunLineFilter", "f").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.FunLineFilter].f)
    r.put(0x45E25F50F6921939L /* methodKey(F, "org.sireum.Os.Proc.Result", "ok").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result].ok)
    r.put(0x0FAB8127EB30AA83L /* methodKey(F, "org.sireum.Os.Proc.Result", "out").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result].out)
    r.put(0x5EF8B77D0D43274DL /* methodKey(F, "org.sireum.Os.Proc.Result", "err").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result].err)
    r.put(0x62545190B0655E48L /* methodKey(F, "org.sireum.Os.Proc.Result", "exitCode").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result].exitCode)
    r.put(0xAF1C09A79821DE03L /* methodKey(F, "org.sireum.Os.Proc.Result.Normal", "exitCode").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result.Normal].exitCode)
    r.put(0xDEAEA22E03806D25L /* methodKey(F, "org.sireum.Os.Proc.Result.Normal", "out").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result.Normal].out)
    r.put(0x1932A12302762539L /* methodKey(F, "org.sireum.Os.Proc.Result.Normal", "err").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result.Normal].err)
    r.put(0x22CB1AD01E471320L /* methodKey(F, "org.sireum.Os.Proc.Result.Normal", "ok").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result.Normal].ok)
    r.put(0xB91DEEC96677E85AL /* methodKey(F, "org.sireum.Os.Proc.Result.Exception", "err").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result.Exception].err)
    r.put(0xC9DCEF3F5408B581L /* methodKey(F, "org.sireum.Os.Proc.Result.Exception", "out").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result.Exception].out)
    r.put(0x5866CF9DFBDDB6F8L /* methodKey(F, "org.sireum.Os.Proc.Result.Exception", "exitCode").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result.Exception].exitCode)
    r.put(0x48919E3218C4D2D9L /* methodKey(F, "org.sireum.Os.Proc.Result.Exception", "ok").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result.Exception].ok)
    r.put(0xD7DA598AB4050F8EL /* methodKey(F, "org.sireum.Os.Proc.Result.Timeout", "out").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result.Timeout].out)
    r.put(0xDE44842BA5717574L /* methodKey(F, "org.sireum.Os.Proc.Result.Timeout", "err").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result.Timeout].err)
    r.put(0xC4B515AEA99091AEL /* methodKey(F, "org.sireum.Os.Proc.Result.Timeout", "exitCode").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result.Timeout].exitCode)
    r.put(0x4EB14452A1B66C8EL /* methodKey(F, "org.sireum.Os.Proc.Result.Timeout", "ok").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.Result.Timeout].ok)
    r.put(0x5E889CBF516A2D98L /* methodKey(F, "org.sireum.Os.Proc", "cmds").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].cmds)
    r.put(0x8A1D33F621B3BB8EL /* methodKey(F, "org.sireum.Os.Proc", "wd").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].wd)
    r.put(0xA39AC17106D97021L /* methodKey(F, "org.sireum.Os.Proc", "envMap").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].envMap)
    r.put(0x53997E91EB108FD9L /* methodKey(F, "org.sireum.Os.Proc", "shouldAddEnv").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].shouldAddEnv)
    r.put(0xCD39EC2C5D1D4A01L /* methodKey(F, "org.sireum.Os.Proc", "in").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].in)
    r.put(0xE07071DA908DA7A0L /* methodKey(F, "org.sireum.Os.Proc", "isErrAsOut").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].isErrAsOut)
    r.put(0x01C88905C062B005L /* methodKey(F, "org.sireum.Os.Proc", "shouldOutputConsole").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].shouldOutputConsole)
    r.put(0x795B831B65D10149L /* methodKey(F, "org.sireum.Os.Proc", "isErrBuffered").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].isErrBuffered)
    r.put(0x886850B036B9164FL /* methodKey(F, "org.sireum.Os.Proc", "shouldPrintEnv").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].shouldPrintEnv)
    r.put(0x7D8FBF4750E9F406L /* methodKey(F, "org.sireum.Os.Proc", "shouldPrintCommands").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].shouldPrintCommands)
    r.put(0x71C5CEE17875FF22L /* methodKey(F, "org.sireum.Os.Proc", "timeoutInMillis").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].timeoutInMillis)
    r.put(0x73E340402259E354L /* methodKey(F, "org.sireum.Os.Proc", "shouldUseStandardLib").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].shouldUseStandardLib)
    r.put(0x9477813FBA8D57FAL /* methodKey(F, "org.sireum.Os.Proc", "isScript").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].isScript)
    r.put(0x1D6975C045FF88E0L /* methodKey(F, "org.sireum.Os.Proc", "outLineActionOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].outLineActionOpt)
    r.put(0x742021EBF7550CF1L /* methodKey(F, "org.sireum.Os.Proc", "errLineActionOpt").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].errLineActionOpt)
    r.put(0xD3740F8CD880ED69L /* methodKey(F, "org.sireum.Os.Proc", "dontInheritEnv").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].dontInheritEnv)
    r.put(0x51A5D85B37A96621L /* methodKey(F, "org.sireum.Os.Proc", "redirectErr").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].redirectErr)
    r.put(0x384F9F970FCA7EF2L /* methodKey(F, "org.sireum.Os.Proc", "bufferErr").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].bufferErr)
    r.put(0xD07BDE34A5967C39L /* methodKey(F, "org.sireum.Os.Proc", "console").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].console)
    r.put(0x55412441A49F0E5FL /* methodKey(F, "org.sireum.Os.Proc", "echoEnv").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].echoEnv)
    r.put(0x2B079AEB81F41EBCL /* methodKey(F, "org.sireum.Os.Proc", "echo").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].echo)
    r.put(0x8A8A45EC93F42133L /* methodKey(F, "org.sireum.Os.Proc", "standard").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].standard)
    r.put(0xB3E82D00DAFE9CC0L /* methodKey(F, "org.sireum.Os.Proc", "script").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].script)
    r.put(0x67C8B5D47773B076L /* methodKey(F, "org.sireum.Os.Proc", "run").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].run())
    r.put(0x739A2E673EB76952L /* methodKey(F, "org.sireum.Os.Proc", "runCheck").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].runCheck())
    r.put(0x7E46A39777DADADFL /* methodKey(F, "org.sireum.Os.Path", "value").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].value)
    r.put(0x4CF98A1EC7F86843L /* methodKey(F, "org.sireum.Os.Path", "procString").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].procString)
    r.put(0xFF1B4FE564BC2AEEL /* methodKey(F, "org.sireum.Os.Path", "canon").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].canon)
    r.put(0x14E66AA5CBF404D3L /* methodKey(F, "org.sireum.Os.Path", "abs").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].abs)
    r.put(0x3CE5A2AB2D664395L /* methodKey(F, "org.sireum.Os.Path", "exists").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].exists)
    r.put(0x0A7CD7EA244F1CB7L /* methodKey(F, "org.sireum.Os.Path", "ext").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].ext)
    r.put(0x758F1D3D76BC336FL /* methodKey(F, "org.sireum.Os.Path", "isAbs").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].isAbs)
    r.put(0x80D491CC52D321FAL /* methodKey(F, "org.sireum.Os.Path", "isDir").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].isDir)
    r.put(0x97B0699DA71242D0L /* methodKey(F, "org.sireum.Os.Path", "isFile").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].isFile)
    r.put(0x926BB3D9E5CEE661L /* methodKey(F, "org.sireum.Os.Path", "isSymLink").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].isSymLink)
    r.put(0x8DBEC4AA8EB247C5L /* methodKey(F, "org.sireum.Os.Path", "isExecutable").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].isExecutable)
    r.put(0xF4A64F34C27FD111L /* methodKey(F, "org.sireum.Os.Path", "isReadable").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].isReadable)
    r.put(0x4BAD999A0952EE1CL /* methodKey(F, "org.sireum.Os.Path", "isWritable").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].isWritable)
    r.put(0x6CAEE9336697A9B8L /* methodKey(F, "org.sireum.Os.Path", "kind").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].kind)
    r.put(0xB7285EB9C581DBD0L /* methodKey(F, "org.sireum.Os.Path", "lastModified").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].lastModified)
    r.put(0x64C24849A8F8B0D9L /* methodKey(F, "org.sireum.Os.Path", "length").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].length)
    r.put(0x9FC27D448C840C63L /* methodKey(F, "org.sireum.Os.Path", "list").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].list)
    r.put(0x5509BDC284BE3516L /* methodKey(F, "org.sireum.Os.Path", "md5").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].md5)
    r.put(0x3CD678AF52B38EC8L /* methodKey(F, "org.sireum.Os.Path", "mkdir").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].mkdir())
    r.put(0xC089F447448D84C7L /* methodKey(F, "org.sireum.Os.Path", "mkdirAll").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].mkdirAll())
    r.put(0xE971DF1D625A82DCL /* methodKey(F, "org.sireum.Os.Path", "name").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].name)
    r.put(0x6B1DE84DA87A2E67L /* methodKey(F, "org.sireum.Os.Path", "properties").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].properties)
    r.put(0x4359BF52CAFF8C98L /* methodKey(F, "org.sireum.Os.Path", "readSymLink").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].readSymLink)
    r.put(0x358A90D915DB7F8FL /* methodKey(F, "org.sireum.Os.Path", "read").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].read)
    r.put(0x9BB16DD596F89A47L /* methodKey(F, "org.sireum.Os.Path", "readLines").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].readLines)
    r.put(0x33D478A9F8710C0CL /* methodKey(F, "org.sireum.Os.Path", "readLineStream").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].readLineStream)
    r.put(0xAAD8E8CA2F73D5C1L /* methodKey(F, "org.sireum.Os.Path", "readLineMStream").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].readLineMStream)
    r.put(0x6C6416A79F59A999L /* methodKey(F, "org.sireum.Os.Path", "readU8s").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].readU8s)
    r.put(0xC6FA450DB00D1356L /* methodKey(F, "org.sireum.Os.Path", "readU8ms").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].readU8ms)
    r.put(0x7EFA3916B86F98CCL /* methodKey(F, "org.sireum.Os.Path", "readU8Stream").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].readU8Stream)
    r.put(0xDA32A2250D30585DL /* methodKey(F, "org.sireum.Os.Path", "readU8MStream").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].readU8MStream)
    r.put(0xEBA885AA8D503271L /* methodKey(F, "org.sireum.Os.Path", "readCStream").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].readCStream)
    r.put(0x9B39E592485160F2L /* methodKey(F, "org.sireum.Os.Path", "readIndexableC").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].readIndexableC)
    r.put(0xD250E31E0EA2385DL /* methodKey(F, "org.sireum.Os.Path", "readCMStream").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].readCMStream)
    r.put(0x07C0E9662908D97DL /* methodKey(F, "org.sireum.Os.Path", "remove").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].remove())
    r.put(0xF33CB77816BF6448L /* methodKey(F, "org.sireum.Os.Path", "removeAll").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].removeAll())
    r.put(0x9335B41FA7C3F484L /* methodKey(F, "org.sireum.Os.Path", "removeOnExit").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].removeOnExit())
    r.put(0x04B89F4CC0E5740CL /* methodKey(F, "org.sireum.Os.Path", "sha1").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].sha1)
    r.put(0x19FD88503431BB56L /* methodKey(F, "org.sireum.Os.Path", "size").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].size)
    r.put(0xF0A7A3326CE0FA74L /* methodKey(F, "org.sireum.Os.Path", "toUri").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].toUri)
    r.put(0xCF3FA32FE8F80BE1L /* methodKey(F, "org.sireum.Os.Path", "up").value */, receiverOpt => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].up)
    r
  }

  private lazy val method1Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any](1534)
    r.put(0x1854D576D83AF7E4L /* methodKey(T, "org.sireum.AssocS.Entries", "uniqueKeys").value */, _ => (o1: Any) => org.sireum.AssocS.Entries.uniqueKeys(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]]))
    r.put(0xE5CBF86C0B8F9696L /* methodKey(T, "org.sireum.AssocS.Entries", "keys").value */, _ => (o1: Any) => org.sireum.AssocS.Entries.keys(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]]))
    r.put(0xCEBF203EDC8BD12DL /* methodKey(T, "org.sireum.AssocS.Entries", "values").value */, _ => (o1: Any) => org.sireum.AssocS.Entries.values(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]]))
    r.put(0xC8ADF2C9B6AD847FL /* methodKey(T, "org.sireum.Sireum", "run").value */, _ => (o1: Any) => org.sireum.Sireum.run(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]]))
    r.put(0x41C2E9EB2CF6AA25L /* methodKey(T, "org.sireum.LibUtil", "mineOptions").value */, _ => (o1: Any) => org.sireum.LibUtil.mineOptions(o1.asInstanceOf[org.sireum.String]))
    r.put(0xE79E067CDCA2CD9DL /* methodKey(T, "org.sireum.LibUtil.IS", "unique").value */, _ => (o1: Any) => org.sireum.LibUtil.IS.unique(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x3EEC4C26206902F9L /* methodKey(T, "org.sireum.Random", "setSeed").value */, _ => (o1: Any) => org.sireum.Random.setSeed(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xBEE95E2C47100F36L /* methodKey(T, "org.sireum.Random", "createSeed64").value */, _ => (o1: Any) => org.sireum.Random.createSeed64(o1.asInstanceOf[org.sireum.U64]))
    r.put(0x8DA178C956824E65L /* methodKey(T, "org.sireum.Random.Ext", "setSeed").value */, _ => (o1: Any) => org.sireum.Random.Ext.setSeed(o1.asInstanceOf[org.sireum.U64]))
    r.put(0xF07C2BB05ECC202BL /* methodKey(T, "org.sireum.Set.Elements", "unique").value */, _ => (o1: Any) => org.sireum.Set.Elements.unique(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]]))
    r.put(0xF4B2B517FE88542BL /* methodKey(T, "org.sireum.justification.natded.prop", "negE").value */, _ => (o1: Any) => org.sireum.justification.natded.prop.negE(o1.asInstanceOf[org.sireum.B]))
    r.put(0xBDD03CC267AD9A16L /* methodKey(T, "org.sireum.justification.natded.prop", "bottomE").value */, _ => (o1: Any) => org.sireum.justification.natded.prop.bottomE(o1.asInstanceOf[org.sireum.B]))
    r.put(0x34C4F76CCF64D14DL /* methodKey(T, "org.sireum.Asm", "eraseNonNative").value */, _ => (o1: Any) => org.sireum.Asm.eraseNonNative(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x8C80F050BF0CBAC2L /* methodKey(T, "org.sireum.Asm", "rewriteReleaseFence").value */, _ => (o1: Any) => org.sireum.Asm.rewriteReleaseFence(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0xEB109662C014D2E4L /* methodKey(T, "org.sireum.Asm", "rewriteSetSecurityManager").value */, _ => (o1: Any) => org.sireum.Asm.rewriteSetSecurityManager(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x22EAEEB5EB5E0D89L /* methodKey(T, "org.sireum.GitHub.Ext", "latestRelease").value */, _ => (o1: Any) => org.sireum.GitHub.Ext.latestRelease(o1.asInstanceOf[org.sireum.GitHub.Repository]))
    r.put(0xE3B4C7390C3E1BE0L /* methodKey(T, "org.sireum.GitHub.Ext", "releases").value */, _ => (o1: Any) => org.sireum.GitHub.Ext.releases(o1.asInstanceOf[org.sireum.GitHub.Repository]))
    r.put(0x1B5C15598D84A189L /* methodKey(T, "org.sireum.GitHub.Ext", "assets").value */, _ => (o1: Any) => org.sireum.GitHub.Ext.assets(o1.asInstanceOf[org.sireum.GitHub.Release]))
    r.put(0x29BBF2727F5FF44DL /* methodKey(T, "org.sireum.Os", "exit").value */, _ => (o1: Any) => org.sireum.Os.exit(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xF1DFEE31D449E56AL /* methodKey(T, "org.sireum.Os", "env").value */, _ => (o1: Any) => org.sireum.Os.env(o1.asInstanceOf[org.sireum.String]))
    r.put(0xC187877C8C9D667CL /* methodKey(T, "org.sireum.Os", "javaExe").value */, _ => (o1: Any) => org.sireum.Os.javaExe(o1.asInstanceOf[org.sireum.Option[org.sireum.Os.Path]]))
    r.put(0xD9706FE633E8860FL /* methodKey(T, "org.sireum.Os", "path").value */, _ => (o1: Any) => org.sireum.Os.path(o1.asInstanceOf[org.sireum.String]))
    r.put(0x6DBC59F180255399L /* methodKey(T, "org.sireum.Os", "printParseableMessages").value */, _ => (o1: Any) => org.sireum.Os.printParseableMessages(o1.asInstanceOf[org.sireum.message.Reporter]))
    r.put(0x51D1983D914F61E0L /* methodKey(T, "org.sireum.Os", "proc").value */, _ => (o1: Any) => org.sireum.Os.proc(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]]))
    r.put(0x88680E48F4D8795CL /* methodKey(T, "org.sireum.Os", "procs").value */, _ => (o1: Any) => org.sireum.Os.procs(o1.asInstanceOf[org.sireum.String]))
    r.put(0x1DB0CCEFF2FC0711L /* methodKey(T, "org.sireum.Os", "prop").value */, _ => (o1: Any) => org.sireum.Os.prop(o1.asInstanceOf[org.sireum.String]))
    r.put(0x64FCFF3E03947CDDL /* methodKey(T, "org.sireum.Os", "readIndexableCFrom").value */, _ => (o1: Any) => org.sireum.Os.readIndexableCFrom(o1.asInstanceOf[org.sireum.String]))
    r.put(0xC83806499908BC11L /* methodKey(T, "org.sireum.Os", "scalaHomeOpt").value */, _ => (o1: Any) => org.sireum.Os.scalaHomeOpt(o1.asInstanceOf[org.sireum.Option[org.sireum.Os.Path]]))
    r.put(0xFF2FCA6FC2D9791BL /* methodKey(T, "org.sireum.Os", "scalaScript").value */, _ => (o1: Any) => org.sireum.Os.scalaScript(o1.asInstanceOf[org.sireum.Option[org.sireum.Os.Path]]))
    r.put(0xABFDBD8361D7E672L /* methodKey(T, "org.sireum.Os", "scalacScript").value */, _ => (o1: Any) => org.sireum.Os.scalacScript(o1.asInstanceOf[org.sireum.Option[org.sireum.Os.Path]]))
    r.put(0x6940B3C2DA6C507CL /* methodKey(T, "org.sireum.Os", "tempDirFix").value */, _ => (o1: Any) => org.sireum.Os.tempDirFix(o1.asInstanceOf[org.sireum.String]))
    r.put(0x9A85E310FF6BB59FL /* methodKey(T, "org.sireum.Os", "uriToPath").value */, _ => (o1: Any) => org.sireum.Os.uriToPath(o1.asInstanceOf[org.sireum.String]))
    r.put(0x2BD71ED4E5A0CA33L /* methodKey(T, "org.sireum.Os.Ext", "abs").value */, _ => (o1: Any) => org.sireum.Os.Ext.abs(o1.asInstanceOf[org.sireum.String]))
    r.put(0x777FDF41C07CAE5FL /* methodKey(T, "org.sireum.Os.Ext", "canon").value */, _ => (o1: Any) => org.sireum.Os.Ext.canon(o1.asInstanceOf[org.sireum.String]))
    r.put(0x2F24462D4C597D6BL /* methodKey(T, "org.sireum.Os.Ext", "env").value */, _ => (o1: Any) => org.sireum.Os.Ext.env(o1.asInstanceOf[org.sireum.String]))
    r.put(0x4068EDA353008E8DL /* methodKey(T, "org.sireum.Os.Ext", "exists").value */, _ => (o1: Any) => org.sireum.Os.Ext.exists(o1.asInstanceOf[org.sireum.String]))
    r.put(0xFC94285E96D1BFEEL /* methodKey(T, "org.sireum.Os.Ext", "exit").value */, _ => (o1: Any) => org.sireum.Os.Ext.exit(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x14BD954CB8BF5680L /* methodKey(T, "org.sireum.Os.Ext", "fromUri").value */, _ => (o1: Any) => org.sireum.Os.Ext.fromUri(o1.asInstanceOf[org.sireum.String]))
    r.put(0x05CB9440ADDA4A95L /* methodKey(T, "org.sireum.Os.Ext", "isAbs").value */, _ => (o1: Any) => org.sireum.Os.Ext.isAbs(o1.asInstanceOf[org.sireum.String]))
    r.put(0xE5B9CF099012F3D4L /* methodKey(T, "org.sireum.Os.Ext", "isDir").value */, _ => (o1: Any) => org.sireum.Os.Ext.isDir(o1.asInstanceOf[org.sireum.String]))
    r.put(0x49EAC87F1E767033L /* methodKey(T, "org.sireum.Os.Ext", "isFile").value */, _ => (o1: Any) => org.sireum.Os.Ext.isFile(o1.asInstanceOf[org.sireum.String]))
    r.put(0x153DDEF28DB317CDL /* methodKey(T, "org.sireum.Os.Ext", "isSymLink").value */, _ => (o1: Any) => org.sireum.Os.Ext.isSymLink(o1.asInstanceOf[org.sireum.String]))
    r.put(0x159E0E4EAE1DE0F2L /* methodKey(T, "org.sireum.Os.Ext", "isExecutable").value */, _ => (o1: Any) => org.sireum.Os.Ext.isExecutable(o1.asInstanceOf[org.sireum.String]))
    r.put(0x86AD4399B50BEB33L /* methodKey(T, "org.sireum.Os.Ext", "isReadable").value */, _ => (o1: Any) => org.sireum.Os.Ext.isReadable(o1.asInstanceOf[org.sireum.String]))
    r.put(0xE60ACD0E07DE0F89L /* methodKey(T, "org.sireum.Os.Ext", "isWritable").value */, _ => (o1: Any) => org.sireum.Os.Ext.isWritable(o1.asInstanceOf[org.sireum.String]))
    r.put(0x44851BF8982C044FL /* methodKey(T, "org.sireum.Os.Ext", "kind").value */, _ => (o1: Any) => org.sireum.Os.Ext.kind(o1.asInstanceOf[org.sireum.String]))
    r.put(0xBCEC9882CE26B939L /* methodKey(T, "org.sireum.Os.Ext", "lastModified").value */, _ => (o1: Any) => org.sireum.Os.Ext.lastModified(o1.asInstanceOf[org.sireum.String]))
    r.put(0x0A7AD7D69C6278C2L /* methodKey(T, "org.sireum.Os.Ext", "length").value */, _ => (o1: Any) => org.sireum.Os.Ext.length(o1.asInstanceOf[org.sireum.String]))
    r.put(0x5B5D0FB408BCF0BEL /* methodKey(T, "org.sireum.Os.Ext", "list").value */, _ => (o1: Any) => org.sireum.Os.Ext.list(o1.asInstanceOf[org.sireum.String]))
    r.put(0xCA6540F5B52E1EB7L /* methodKey(T, "org.sireum.Os.Ext", "md5").value */, _ => (o1: Any) => org.sireum.Os.Ext.md5(o1.asInstanceOf[org.sireum.String]))
    r.put(0x08A0CE13E438E8D6L /* methodKey(T, "org.sireum.Os.Ext", "name").value */, _ => (o1: Any) => org.sireum.Os.Ext.name(o1.asInstanceOf[org.sireum.String]))
    r.put(0x75302B4FD4C9E303L /* methodKey(T, "org.sireum.Os.Ext", "norm").value */, _ => (o1: Any) => org.sireum.Os.Ext.norm(o1.asInstanceOf[org.sireum.String]))
    r.put(0x643B1957BC5770BAL /* methodKey(T, "org.sireum.Os.Ext", "prop").value */, _ => (o1: Any) => org.sireum.Os.Ext.prop(o1.asInstanceOf[org.sireum.String]))
    r.put(0xBA78121AB1FA86F0L /* methodKey(T, "org.sireum.Os.Ext", "properties").value */, _ => (o1: Any) => org.sireum.Os.Ext.properties(o1.asInstanceOf[org.sireum.String]))
    r.put(0xC8C7C3CA2E1557F2L /* methodKey(T, "org.sireum.Os.Ext", "readSymLink").value */, _ => (o1: Any) => org.sireum.Os.Ext.readSymLink(o1.asInstanceOf[org.sireum.String]))
    r.put(0x4D9E3CB2112EA85CL /* methodKey(T, "org.sireum.Os.Ext", "read").value */, _ => (o1: Any) => org.sireum.Os.Ext.read(o1.asInstanceOf[org.sireum.String]))
    r.put(0xFF680F42777C0EE7L /* methodKey(T, "org.sireum.Os.Ext", "readU8s").value */, _ => (o1: Any) => org.sireum.Os.Ext.readU8s(o1.asInstanceOf[org.sireum.String]))
    r.put(0xD2AF46FD84B3AB08L /* methodKey(T, "org.sireum.Os.Ext", "readU8ms").value */, _ => (o1: Any) => org.sireum.Os.Ext.readU8ms(o1.asInstanceOf[org.sireum.String]))
    r.put(0x5EEEBBB5D9EC30F4L /* methodKey(T, "org.sireum.Os.Ext", "readLineStream").value */, _ => (o1: Any) => org.sireum.Os.Ext.readLineStream(o1.asInstanceOf[org.sireum.String]))
    r.put(0xAF0AB0B2BE460167L /* methodKey(T, "org.sireum.Os.Ext", "readU8Stream").value */, _ => (o1: Any) => org.sireum.Os.Ext.readU8Stream(o1.asInstanceOf[org.sireum.String]))
    r.put(0x33037E6497608428L /* methodKey(T, "org.sireum.Os.Ext", "readCStream").value */, _ => (o1: Any) => org.sireum.Os.Ext.readCStream(o1.asInstanceOf[org.sireum.String]))
    r.put(0xE2420BEBA98611A0L /* methodKey(T, "org.sireum.Os.Ext", "readIndexableCPath").value */, _ => (o1: Any) => org.sireum.Os.Ext.readIndexableCPath(o1.asInstanceOf[org.sireum.String]))
    r.put(0xA872DE4E6C809008L /* methodKey(T, "org.sireum.Os.Ext", "readIndexableCUrl").value */, _ => (o1: Any) => org.sireum.Os.Ext.readIndexableCUrl(o1.asInstanceOf[org.sireum.String]))
    r.put(0xF68FA5DA0B29CD9AL /* methodKey(T, "org.sireum.Os.Ext", "readLineMStream").value */, _ => (o1: Any) => org.sireum.Os.Ext.readLineMStream(o1.asInstanceOf[org.sireum.String]))
    r.put(0x79128326455D9140L /* methodKey(T, "org.sireum.Os.Ext", "readCMStream").value */, _ => (o1: Any) => org.sireum.Os.Ext.readCMStream(o1.asInstanceOf[org.sireum.String]))
    r.put(0x5D31B1791B67E1AAL /* methodKey(T, "org.sireum.Os.Ext", "readU8MStream").value */, _ => (o1: Any) => org.sireum.Os.Ext.readU8MStream(o1.asInstanceOf[org.sireum.String]))
    r.put(0xE3758699471EA0E1L /* methodKey(T, "org.sireum.Os.Ext", "remove").value */, _ => (o1: Any) => org.sireum.Os.Ext.remove(o1.asInstanceOf[org.sireum.String]))
    r.put(0x7C85384C87EED7D9L /* methodKey(T, "org.sireum.Os.Ext", "removeAll").value */, _ => (o1: Any) => org.sireum.Os.Ext.removeAll(o1.asInstanceOf[org.sireum.String]))
    r.put(0x8689872DCAC62F38L /* methodKey(T, "org.sireum.Os.Ext", "removeOnExit").value */, _ => (o1: Any) => org.sireum.Os.Ext.removeOnExit(o1.asInstanceOf[org.sireum.String]))
    r.put(0x2E98906BCA688C1EL /* methodKey(T, "org.sireum.Os.Ext", "sha1").value */, _ => (o1: Any) => org.sireum.Os.Ext.sha1(o1.asInstanceOf[org.sireum.String]))
    r.put(0xE00E5998690EAE7CL /* methodKey(T, "org.sireum.Os.Ext", "size").value */, _ => (o1: Any) => org.sireum.Os.Ext.size(o1.asInstanceOf[org.sireum.String]))
    r.put(0x34BA7C4F89FEE10BL /* methodKey(T, "org.sireum.Os.Ext", "tempDir").value */, _ => (o1: Any) => org.sireum.Os.Ext.tempDir(o1.asInstanceOf[org.sireum.String]))
    r.put(0xB6ABB9E19C3F01E2L /* methodKey(T, "org.sireum.Os.Ext", "toUri").value */, _ => (o1: Any) => org.sireum.Os.Ext.toUri(o1.asInstanceOf[org.sireum.String]))
    r.put(0x175181727A1304B0L /* methodKey(T, "org.sireum.Os.Ext", "parent").value */, _ => (o1: Any) => org.sireum.Os.Ext.parent(o1.asInstanceOf[org.sireum.String]))
    r.put(0x5B7D21E947D83182L /* methodKey(T, "org.sireum.Os.Ext", "proc").value */, _ => (o1: Any) => org.sireum.Os.Ext.proc(o1.asInstanceOf[org.sireum.Os.Proc]))
    r.put(0x89C0705BC7C1FC1EL /* methodKey(T, "org.sireum.AssocS", "apply").value */, _ => (o1: Any) => org.sireum.AssocS.apply(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]]))
    r.put(0x5705C5608F220805L /* methodKey(T, "org.sireum.AssocS", "unapply").value */, _ => (o1: Any) => org.sireum.AssocS.unapply(o1.asInstanceOf[org.sireum.AssocS[Any, Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x3DF6BE21C08E7781L /* methodKey(F, "org.sireum.AssocS", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].`+`(o1.asInstanceOf[(Any, Any)]))
    r.put(0x5E46A971AA573A6BL /* methodKey(F, "org.sireum.AssocS", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].`++`(o1.asInstanceOf[org.sireum.IS[Any, (Any, Any)]]))
    r.put(0x817009D68D430268L /* methodKey(F, "org.sireum.AssocS", "get").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].get(o1))
    r.put(0x33801691372E3502L /* methodKey(F, "org.sireum.AssocS", "entry").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].entry(o1))
    r.put(0x23BF2AB505C4A2D7L /* methodKey(F, "org.sireum.AssocS", "indexOf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].indexOf(o1))
    r.put(0xFCE7930216C71B3FL /* methodKey(F, "org.sireum.AssocS", "--").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].`--`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x0A780174366BAE11L /* methodKey(F, "org.sireum.AssocS", "-").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].`-`(o1.asInstanceOf[(Any, Any)]))
    r.put(0x9F000E1C8F7C2A63L /* methodKey(F, "org.sireum.AssocS", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].contains(o1))
    r.put(0x6E3C1FD5354E803FL /* methodKey(T, "org.sireum.Bag", "apply").value */, _ => (o1: Any) => org.sireum.Bag.apply(o1.asInstanceOf[org.sireum.Map[Any, org.sireum.Z]]))
    r.put(0x1F8E6A9BB348E56AL /* methodKey(T, "org.sireum.Bag", "unapply").value */, _ => (o1: Any) => org.sireum.Bag.unapply(o1.asInstanceOf[org.sireum.Bag[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x930BDFEE70F7A00FL /* methodKey(F, "org.sireum.Bag", "count").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].count(o1))
    r.put(0xF7853EF7AE515EF3L /* methodKey(F, "org.sireum.Bag", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].contains(o1))
    r.put(0x1E048E36177E575EL /* methodKey(F, "org.sireum.Bag", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].`+`(o1))
    r.put(0x737118352F16934BL /* methodKey(F, "org.sireum.Bag", "+#").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].`+#`(o1.asInstanceOf[(Any, org.sireum.Z)]))
    r.put(0x9A4547715116D586L /* methodKey(F, "org.sireum.Bag", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].`++`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x3A7DFC5AD0489680L /* methodKey(F, "org.sireum.Bag", "-").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].`-`(o1))
    r.put(0xA37B81919137E419L /* methodKey(F, "org.sireum.Bag", "--").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].`--`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x2318472F63B9BF92L /* methodKey(F, "org.sireum.Bag", "\\").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].`\\`(o1.asInstanceOf[org.sireum.Bag[Any]]))
    r.put(0xB18A7CE2DBA4166BL /* methodKey(F, "org.sireum.Bag", "-#").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].`-#`(o1.asInstanceOf[(Any, org.sireum.Z)]))
    r.put(0x26DA5DD109F2C6AEL /* methodKey(F, "org.sireum.Bag", "union").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].union(o1.asInstanceOf[org.sireum.Bag[Any]]))
    r.put(0x580AACA68A226AF0L /* methodKey(F, "org.sireum.Bag", "\u222A").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].`\u222A`(o1.asInstanceOf[org.sireum.Bag[Any]]))
    r.put(0x3D9003066414219CL /* methodKey(F, "org.sireum.Bag", "intersect").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].intersect(o1.asInstanceOf[org.sireum.Bag[Any]]))
    r.put(0x7AE9E2879FF2D3E0L /* methodKey(F, "org.sireum.Bag", "\u2229").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].`\u2229`(o1.asInstanceOf[org.sireum.Bag[Any]]))
    r.put(0xBF1FB262FB0491C8L /* methodKey(F, "org.sireum.CircularQueue", "enqueue").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue[Any]].enqueue(o1))
    r.put(0xAE128494C0056022L /* methodKey(T, "org.sireum.CircularQueue.NoDrop", "unapply").value */, _ => (o1: Any) => org.sireum.CircularQueue.NoDrop.unapply(o1.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6)) => Some((o0, o1, o2, o3, o4, o5, o6))
      case _ => None()
    })
    r.put(0x0637E1CED50B78D2L /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "front_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].`front_=`(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x72F696238F3CEA25L /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "rear_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].`rear_=`(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x23B1E5E0EDDDEBA4L /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "numOfElements_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].`numOfElements_=`(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xE158CA4F8D2867D4L /* methodKey(F, "org.sireum.CircularQueue.NoDrop", "enqueue").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.NoDrop[Any]].enqueue(o1))
    r.put(0x7A3ED9FFA1E5A875L /* methodKey(T, "org.sireum.CircularQueue.DropFront", "unapply").value */, _ => (o1: Any) => org.sireum.CircularQueue.DropFront.unapply(o1.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6)) => Some((o0, o1, o2, o3, o4, o5, o6))
      case _ => None()
    })
    r.put(0x01A268220BF9BB1BL /* methodKey(F, "org.sireum.CircularQueue.DropFront", "front_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].`front_=`(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xB494DD50755C8A73L /* methodKey(F, "org.sireum.CircularQueue.DropFront", "rear_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].`rear_=`(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xA061C74E1B8659E8L /* methodKey(F, "org.sireum.CircularQueue.DropFront", "numOfElements_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].`numOfElements_=`(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xD63CEAFA17735F45L /* methodKey(F, "org.sireum.CircularQueue.DropFront", "enqueue").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropFront[Any]].enqueue(o1))
    r.put(0x6F602304F71FA26BL /* methodKey(T, "org.sireum.CircularQueue.DropRear", "unapply").value */, _ => (o1: Any) => org.sireum.CircularQueue.DropRear.unapply(o1.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6)) => Some((o0, o1, o2, o3, o4, o5, o6))
      case _ => None()
    })
    r.put(0x28D3C7D36C9E7201L /* methodKey(F, "org.sireum.CircularQueue.DropRear", "front_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].`front_=`(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xEDA0AE85B2946AECL /* methodKey(F, "org.sireum.CircularQueue.DropRear", "rear_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].`rear_=`(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x4D43AE3FCCBB4139L /* methodKey(F, "org.sireum.CircularQueue.DropRear", "numOfElements_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].`numOfElements_=`(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x2807B6FF24535695L /* methodKey(F, "org.sireum.CircularQueue.DropRear", "enqueue").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.CircularQueue.DropRear[Any]].enqueue(o1))
    r.put(0x2A145156F47E6E4FL /* methodKey(T, "org.sireum.Either.Left", "apply").value */, _ => (o1: Any) => org.sireum.Either.Left.apply(o1))
    r.put(0xDB1661F19775E39EL /* methodKey(T, "org.sireum.Either.Left", "unapply").value */, _ => (o1: Any) => org.sireum.Either.Left.unapply(o1.asInstanceOf[org.sireum.Either.Left[Any, Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x8E115A84FCF61952L /* methodKey(T, "org.sireum.Either.Right", "apply").value */, _ => (o1: Any) => org.sireum.Either.Right.apply(o1))
    r.put(0x1F04F661E5DD82C3L /* methodKey(T, "org.sireum.Either.Right", "unapply").value */, _ => (o1: Any) => org.sireum.Either.Right.unapply(o1.asInstanceOf[org.sireum.Either.Right[Any, Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0xB9883FCCD850BFC2L /* methodKey(F, "org.sireum.Graph.Edge", "toInternal").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Edge[Any, Any]].toInternal(o1.asInstanceOf[org.sireum.HashSMap[Any, org.sireum.Z]]))
    r.put(0x44C00D57780B7A3CL /* methodKey(T, "org.sireum.Graph.Edge.Plain", "unapply").value */, _ => (o1: Any) => org.sireum.Graph.Edge.Plain.unapply(o1.asInstanceOf[org.sireum.Graph.Edge.Plain[Any, Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x616B0AAC7C4CB469L /* methodKey(F, "org.sireum.Graph.Edge.Plain", "toInternal").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Edge.Plain[Any, Any]].toInternal(o1.asInstanceOf[org.sireum.HashSMap[Any, org.sireum.Z]]))
    r.put(0xAFC6C8E24E139EE9L /* methodKey(T, "org.sireum.Graph.Edge.Data", "unapply").value */, _ => (o1: Any) => org.sireum.Graph.Edge.Data.unapply(o1.asInstanceOf[org.sireum.Graph.Edge.Data[Any, Any]]) match {
      case scala.Some((o0, o1, o2)) => Some((o0, o1, o2))
      case _ => None()
    })
    r.put(0x7344932FA132ABC6L /* methodKey(F, "org.sireum.Graph.Edge.Data", "toInternal").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Edge.Data[Any, Any]].toInternal(o1.asInstanceOf[org.sireum.HashSMap[Any, org.sireum.Z]]))
    r.put(0x5EF1FD5DA4CC3C4BL /* methodKey(F, "org.sireum.Graph.Internal.Edge", "toEdge").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edge[Any]].toEdge(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]]))
    r.put(0xB045800B483A8835L /* methodKey(F, "org.sireum.Graph.Internal.Edges", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges[Any]].`+`(o1.asInstanceOf[org.sireum.Graph.Internal.Edge[Any]]))
    r.put(0xC942B2044C8B0B19L /* methodKey(F, "org.sireum.Graph.Internal.Edges", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges[Any]].`++`(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.Graph.Internal.Edge[Any]]]))
    r.put(0x7EBD9AE009AD01E3L /* methodKey(F, "org.sireum.Graph.Internal.Edges", "-#").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges[Any]].`-#`(o1.asInstanceOf[(org.sireum.Graph.Internal.Edge[Any], org.sireum.Z)]))
    r.put(0x9F2109BB5D2F78F7L /* methodKey(T, "org.sireum.Graph.Internal.Edges.Set", "apply").value */, _ => (o1: Any) => org.sireum.Graph.Internal.Edges.Set.apply(o1.asInstanceOf[org.sireum.HashSSet[org.sireum.Graph.Internal.Edge[Any]]]))
    r.put(0x70B14E29F21A904FL /* methodKey(T, "org.sireum.Graph.Internal.Edges.Set", "unapply").value */, _ => (o1: Any) => org.sireum.Graph.Internal.Edges.Set.unapply(o1.asInstanceOf[org.sireum.Graph.Internal.Edges.Set[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x10A3A0EB5A4B2E20L /* methodKey(F, "org.sireum.Graph.Internal.Edges.Set", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges.Set[Any]].`+`(o1.asInstanceOf[org.sireum.Graph.Internal.Edge[Any]]))
    r.put(0x1A135CE0356CD55DL /* methodKey(F, "org.sireum.Graph.Internal.Edges.Set", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges.Set[Any]].`++`(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.Graph.Internal.Edge[Any]]]))
    r.put(0x6F5FBD17F756AC87L /* methodKey(F, "org.sireum.Graph.Internal.Edges.Set", "-#").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges.Set[Any]].`-#`(o1.asInstanceOf[(org.sireum.Graph.Internal.Edge[Any], org.sireum.Z)]))
    r.put(0xBFB6233E4680DCB4L /* methodKey(T, "org.sireum.Graph.Internal.Edges.Bag", "apply").value */, _ => (o1: Any) => org.sireum.Graph.Internal.Edges.Bag.apply(o1.asInstanceOf[org.sireum.HashSBag[org.sireum.Graph.Internal.Edge[Any]]]))
    r.put(0xD31E0D2E5F3B4CACL /* methodKey(T, "org.sireum.Graph.Internal.Edges.Bag", "unapply").value */, _ => (o1: Any) => org.sireum.Graph.Internal.Edges.Bag.unapply(o1.asInstanceOf[org.sireum.Graph.Internal.Edges.Bag[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0xFE9D6760AFB3005AL /* methodKey(F, "org.sireum.Graph.Internal.Edges.Bag", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges.Bag[Any]].`+`(o1.asInstanceOf[org.sireum.Graph.Internal.Edge[Any]]))
    r.put(0x365FD0277AF6836EL /* methodKey(F, "org.sireum.Graph.Internal.Edges.Bag", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges.Bag[Any]].`++`(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.Graph.Internal.Edge[Any]]]))
    r.put(0x03C3086DC88A19AFL /* methodKey(F, "org.sireum.Graph.Internal.Edges.Bag", "-#").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edges.Bag[Any]].`-#`(o1.asInstanceOf[(org.sireum.Graph.Internal.Edge[Any], org.sireum.Z)]))
    r.put(0x650413D1EB377875L /* methodKey(T, "org.sireum.Graph.Internal.Edge.Plain", "unapply").value */, _ => (o1: Any) => org.sireum.Graph.Internal.Edge.Plain.unapply(o1.asInstanceOf[org.sireum.Graph.Internal.Edge.Plain[Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x8480B0D35D747F55L /* methodKey(F, "org.sireum.Graph.Internal.Edge.Plain", "toEdge").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edge.Plain[Any]].toEdge(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]]))
    r.put(0x86BE8B8C300FF258L /* methodKey(T, "org.sireum.Graph.Internal.Edge.Data", "unapply").value */, _ => (o1: Any) => org.sireum.Graph.Internal.Edge.Data.unapply(o1.asInstanceOf[org.sireum.Graph.Internal.Edge.Data[Any]]) match {
      case scala.Some((o0, o1, o2)) => Some((o0, o1, o2))
      case _ => None()
    })
    r.put(0xCE3348EBA7737064L /* methodKey(F, "org.sireum.Graph.Internal.Edge.Data", "toEdge").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph.Internal.Edge.Data[Any]].toEdge(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]]))
    r.put(0x46B747C8682AA910L /* methodKey(T, "org.sireum.Graph", "unapply").value */, _ => (o1: Any) => org.sireum.Graph.unapply(o1.asInstanceOf[org.sireum.Graph[Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5)) => Some((o0, o1, o2, o3, o4, o5))
      case _ => None()
    })
    r.put(0x13569C20BEAF68B7L /* methodKey(F, "org.sireum.Graph", "*").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].`*`(o1))
    r.put(0xBAE20750141FD137L /* methodKey(F, "org.sireum.Graph", "--*").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].`--*`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x06369F67ACF9D25AL /* methodKey(F, "org.sireum.Graph", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].`+`(o1.asInstanceOf[(Any, Any)]))
    r.put(0x8FD5F54D9498EDBFL /* methodKey(F, "org.sireum.Graph", "+@").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].`+@`(o1.asInstanceOf[((Any, Any), Any)]))
    r.put(0xD7DEB22798FA4775L /* methodKey(F, "org.sireum.Graph", "-").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].`-`(o1.asInstanceOf[org.sireum.Graph.Edge[Any, Any]]))
    r.put(0x0287137C0FAE2E23L /* methodKey(F, "org.sireum.Graph", "-#").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].`-#`(o1.asInstanceOf[(org.sireum.Graph.Edge[Any, Any], org.sireum.Z)]))
    r.put(0x5B363D982C88CCF2L /* methodKey(F, "org.sireum.Graph", "--").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].`--`(o1.asInstanceOf[org.sireum.IS[Any, org.sireum.Graph.Edge[Any, Any]]]))
    r.put(0xFF5E0CC96DFD0033L /* methodKey(F, "org.sireum.Graph", "incoming").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].incoming(o1))
    r.put(0xCE2F58F6EE4BD98FL /* methodKey(F, "org.sireum.Graph", "outgoing").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].outgoing(o1))
    r.put(0x4D8587690CCDF18CL /* methodKey(F, "org.sireum.Graph", "addEdge").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].addEdge(o1.asInstanceOf[org.sireum.Graph.Edge[Any, Any]]))
    r.put(0xE4014371D0E22051L /* methodKey(F, "org.sireum.Graph", "isEqual").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].isEqual(o1.asInstanceOf[org.sireum.Graph[Any, Any]]))
    r.put(0xF8A739D900A5B52DL /* methodKey(T, "org.sireum.HashBag", "apply").value */, _ => (o1: Any) => org.sireum.HashBag.apply(o1.asInstanceOf[org.sireum.HashMap[Any, org.sireum.Z]]))
    r.put(0x8592A189C44ADEAEL /* methodKey(T, "org.sireum.HashBag", "unapply").value */, _ => (o1: Any) => org.sireum.HashBag.unapply(o1.asInstanceOf[org.sireum.HashBag[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x14FE0A08F57C9F53L /* methodKey(F, "org.sireum.HashBag", "count").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].count(o1))
    r.put(0x12C3654428000C69L /* methodKey(F, "org.sireum.HashBag", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].contains(o1))
    r.put(0x5E7E5D6642A4D2C2L /* methodKey(F, "org.sireum.HashBag", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].`+`(o1))
    r.put(0xB15AB83BA61193B1L /* methodKey(F, "org.sireum.HashBag", "+#").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].`+#`(o1.asInstanceOf[(Any, org.sireum.Z)]))
    r.put(0x4240141859FF14A6L /* methodKey(F, "org.sireum.HashBag", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].`++`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0xC03850D27E4728ABL /* methodKey(F, "org.sireum.HashBag", "-").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].`-`(o1))
    r.put(0x2864D1CA3EF8A048L /* methodKey(F, "org.sireum.HashBag", "--").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].`--`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0xD3363A1408EB0F8BL /* methodKey(F, "org.sireum.HashBag", "-#").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].`-#`(o1.asInstanceOf[(Any, org.sireum.Z)]))
    r.put(0x757AA409EB11DD6EL /* methodKey(F, "org.sireum.HashBag", "\\").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].`\\`(o1.asInstanceOf[org.sireum.HashBag[Any]]))
    r.put(0x9D819EB7A536752AL /* methodKey(F, "org.sireum.HashBag", "union").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].union(o1.asInstanceOf[org.sireum.HashBag[Any]]))
    r.put(0x231DD63D12F042F0L /* methodKey(F, "org.sireum.HashBag", "\u222A").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].`\u222A`(o1.asInstanceOf[org.sireum.HashBag[Any]]))
    r.put(0xAAD5EB7F8B09EDF5L /* methodKey(F, "org.sireum.HashBag", "intersect").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].intersect(o1.asInstanceOf[org.sireum.HashBag[Any]]))
    r.put(0xB6E99A870EF6F830L /* methodKey(F, "org.sireum.HashBag", "\u2229").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].`\u2229`(o1.asInstanceOf[org.sireum.HashBag[Any]]))
    r.put(0x3D9F6BDE8FB0A203L /* methodKey(T, "org.sireum.HashMap", "unapply").value */, _ => (o1: Any) => org.sireum.HashMap.unapply(o1.asInstanceOf[org.sireum.HashMap[Any, Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0xD577D5020B803994L /* methodKey(F, "org.sireum.HashMap", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].`+`(o1.asInstanceOf[(Any, Any)]))
    r.put(0x6AB1E10BDBB27AE5L /* methodKey(F, "org.sireum.HashMap", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].`++`(o1.asInstanceOf[org.sireum.IS[Any, (Any, Any)]]))
    r.put(0x6F5A3D682DFCF7E3L /* methodKey(F, "org.sireum.HashMap", "ensureCapacity").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].ensureCapacity(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x56CD0C4554BD3DF7L /* methodKey(F, "org.sireum.HashMap", "hashIndex").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].hashIndex(o1))
    r.put(0x9D72F48EE0E1C5F1L /* methodKey(F, "org.sireum.HashMap", "get").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].get(o1))
    r.put(0xC6B28FB27F5F62A2L /* methodKey(F, "org.sireum.HashMap", "entry").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].entry(o1))
    r.put(0x79C53A9C4CAD5BF8L /* methodKey(F, "org.sireum.HashMap", "--").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].`--`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x5829834C2C0BAAC9L /* methodKey(F, "org.sireum.HashMap", "-").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].`-`(o1.asInstanceOf[(Any, Any)]))
    r.put(0xEDCB1EC58C955B44L /* methodKey(F, "org.sireum.HashMap", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].contains(o1))
    r.put(0x5F99C17253B17CDDL /* methodKey(F, "org.sireum.HashMap", "isEqual").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashMap[Any, Any]].isEqual(o1.asInstanceOf[org.sireum.HashMap[Any, Any]]))
    r.put(0x8CFFAA3ABB9175CDL /* methodKey(T, "org.sireum.HashSBag", "apply").value */, _ => (o1: Any) => org.sireum.HashSBag.apply(o1.asInstanceOf[org.sireum.HashSMap[Any, org.sireum.Z]]))
    r.put(0xE5EF1CD507E500A8L /* methodKey(T, "org.sireum.HashSBag", "unapply").value */, _ => (o1: Any) => org.sireum.HashSBag.unapply(o1.asInstanceOf[org.sireum.HashSBag[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0xC4B83CC78D888E6EL /* methodKey(F, "org.sireum.HashSBag", "count").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].count(o1))
    r.put(0x361844EC960DF41BL /* methodKey(F, "org.sireum.HashSBag", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].contains(o1))
    r.put(0x0B5DEFCC3FCA0088L /* methodKey(F, "org.sireum.HashSBag", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].`+`(o1))
    r.put(0xB50B0CEF51ADB256L /* methodKey(F, "org.sireum.HashSBag", "+#").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].`+#`(o1.asInstanceOf[(Any, org.sireum.Z)]))
    r.put(0x04B0585BE3C9BE45L /* methodKey(F, "org.sireum.HashSBag", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].`++`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0xE481A7A9483A9EB8L /* methodKey(F, "org.sireum.HashSBag", "-").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].`-`(o1))
    r.put(0xB0BF15918EC699FBL /* methodKey(F, "org.sireum.HashSBag", "--").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].`--`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x3882E271ACD171D1L /* methodKey(F, "org.sireum.HashSBag", "-#").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].`-#`(o1.asInstanceOf[(Any, org.sireum.Z)]))
    r.put(0x54F6E3976EC841DCL /* methodKey(F, "org.sireum.HashSBag", "\\").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].`\\`(o1.asInstanceOf[org.sireum.HashSBag[Any]]))
    r.put(0x9E5AD49C288F45D9L /* methodKey(F, "org.sireum.HashSBag", "union").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].union(o1.asInstanceOf[org.sireum.HashSBag[Any]]))
    r.put(0x22ACAF56B02EF169L /* methodKey(F, "org.sireum.HashSBag", "\u222A").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].`\u222A`(o1.asInstanceOf[org.sireum.HashSBag[Any]]))
    r.put(0x819E13266DD1FEE7L /* methodKey(F, "org.sireum.HashSBag", "intersect").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].intersect(o1.asInstanceOf[org.sireum.HashSBag[Any]]))
    r.put(0x165909AF5127E816L /* methodKey(F, "org.sireum.HashSBag", "\u2229").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].`\u2229`(o1.asInstanceOf[org.sireum.HashSBag[Any]]))
    r.put(0x8A303DF7682517E6L /* methodKey(T, "org.sireum.HashSMap", "unapply").value */, _ => (o1: Any) => org.sireum.HashSMap.unapply(o1.asInstanceOf[org.sireum.HashSMap[Any, Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x67ECF5672213A3B8L /* methodKey(F, "org.sireum.HashSMap", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].`+`(o1.asInstanceOf[(Any, Any)]))
    r.put(0xAB543E66DC36082BL /* methodKey(F, "org.sireum.HashSMap", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].`++`(o1.asInstanceOf[org.sireum.IS[Any, (Any, Any)]]))
    r.put(0x008CA0B6B0130329L /* methodKey(F, "org.sireum.HashSMap", "get").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].get(o1))
    r.put(0xD1325A5B3B985B6BL /* methodKey(F, "org.sireum.HashSMap", "entry").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].entry(o1))
    r.put(0x9303E11CF76CA5DCL /* methodKey(F, "org.sireum.HashSMap", "--").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].`--`(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]]))
    r.put(0x28F83DA5652CF8EFL /* methodKey(F, "org.sireum.HashSMap", "-").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].`-`(o1.asInstanceOf[(Any, Any)]))
    r.put(0xCC93B9EC1CC49D6EL /* methodKey(F, "org.sireum.HashSMap", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].contains(o1))
    r.put(0x0C988672729CD106L /* methodKey(F, "org.sireum.HashSMap", "isEqual").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSMap[Any, Any]].isEqual(o1.asInstanceOf[org.sireum.HashSMap[Any, Any]]))
    r.put(0xE5CE52FF3AE85B25L /* methodKey(T, "org.sireum.HashSSet", "apply").value */, _ => (o1: Any) => org.sireum.HashSSet.apply(o1.asInstanceOf[org.sireum.HashSMap[Any, org.sireum.B]]))
    r.put(0x8F87C7AE7CCE3C1FL /* methodKey(T, "org.sireum.HashSSet", "unapply").value */, _ => (o1: Any) => org.sireum.HashSSet.unapply(o1.asInstanceOf[org.sireum.HashSSet[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x5968249A649B012BL /* methodKey(F, "org.sireum.HashSSet", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].`+`(o1))
    r.put(0x7FE4A180BA29371AL /* methodKey(F, "org.sireum.HashSSet", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].`++`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x3C9A59C990110750L /* methodKey(F, "org.sireum.HashSSet", "-").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].`-`(o1))
    r.put(0xF30D56C88BFE6BC5L /* methodKey(F, "org.sireum.HashSSet", "--").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].`--`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0xF7C9E025193327D2L /* methodKey(F, "org.sireum.HashSSet", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].contains(o1))
    r.put(0x94C2A0E86C861C92L /* methodKey(F, "org.sireum.HashSSet", "union").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].union(o1.asInstanceOf[org.sireum.HashSSet[Any]]))
    r.put(0xF3CE7C692404629CL /* methodKey(F, "org.sireum.HashSSet", "\u222A").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].`\u222A`(o1.asInstanceOf[org.sireum.HashSSet[Any]]))
    r.put(0x78ABA93A8F5C10CCL /* methodKey(F, "org.sireum.HashSSet", "intersect").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].intersect(o1.asInstanceOf[org.sireum.HashSSet[Any]]))
    r.put(0x9DB5786C6F09CBEBL /* methodKey(F, "org.sireum.HashSSet", "\u2229").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].`\u2229`(o1.asInstanceOf[org.sireum.HashSSet[Any]]))
    r.put(0x057459837BCC7247L /* methodKey(F, "org.sireum.HashSSet", "\\").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].`\\`(o1.asInstanceOf[org.sireum.HashSSet[Any]]))
    r.put(0x12247002D3440BC1L /* methodKey(F, "org.sireum.HashSSet", "isEqual").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSSet[Any]].isEqual(o1.asInstanceOf[org.sireum.HashSSet[Any]]))
    r.put(0x5E6195818F2A4DD4L /* methodKey(T, "org.sireum.HashSet", "apply").value */, _ => (o1: Any) => org.sireum.HashSet.apply(o1.asInstanceOf[org.sireum.HashMap[Any, org.sireum.B]]))
    r.put(0x98C1596D7FD76D41L /* methodKey(T, "org.sireum.HashSet", "unapply").value */, _ => (o1: Any) => org.sireum.HashSet.unapply(o1.asInstanceOf[org.sireum.HashSet[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x959DA8AEBD379681L /* methodKey(F, "org.sireum.HashSet", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].`+`(o1))
    r.put(0xAFF5FFF07FF06147L /* methodKey(F, "org.sireum.HashSet", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].`++`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0xF18CE82ADAACDE0AL /* methodKey(F, "org.sireum.HashSet", "-").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].`-`(o1))
    r.put(0x767A5038C516366DL /* methodKey(F, "org.sireum.HashSet", "--").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].`--`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0xE30657A3BB3935F5L /* methodKey(F, "org.sireum.HashSet", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].contains(o1))
    r.put(0x3260A355055B0CD9L /* methodKey(F, "org.sireum.HashSet", "union").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].union(o1.asInstanceOf[org.sireum.HashSet[Any]]))
    r.put(0x8FFEE6FE59FC38B4L /* methodKey(F, "org.sireum.HashSet", "\u222A").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].`\u222A`(o1.asInstanceOf[org.sireum.HashSet[Any]]))
    r.put(0x251D0E2352A5BF4BL /* methodKey(F, "org.sireum.HashSet", "intersect").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].intersect(o1.asInstanceOf[org.sireum.HashSet[Any]]))
    r.put(0x00B1DD9285C75DA4L /* methodKey(F, "org.sireum.HashSet", "\u2229").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].`\u2229`(o1.asInstanceOf[org.sireum.HashSet[Any]]))
    r.put(0xD1302D81DA7A1D99L /* methodKey(F, "org.sireum.HashSet", "\\").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].`\\`(o1.asInstanceOf[org.sireum.HashSet[Any]]))
    r.put(0x406E00366E215560L /* methodKey(F, "org.sireum.HashSet", "isEqual").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSet[Any]].isEqual(o1.asInstanceOf[org.sireum.HashSet[Any]]))
    r.put(0x82CEE009EB5FFEA0L /* methodKey(T, "org.sireum.IndexMap", "apply").value */, _ => (o1: Any) => org.sireum.IndexMap.apply(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0xA4969954EA3D5585L /* methodKey(T, "org.sireum.IndexMap", "unapply").value */, _ => (o1: Any) => org.sireum.IndexMap.unapply(o1.asInstanceOf[org.sireum.IndexMap[Any, Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x900B060FEFCCEDFFL /* methodKey(F, "org.sireum.IndexMap", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.IndexMap[Any, Any]].`+`(o1.asInstanceOf[(Any, Any)]))
    r.put(0x8800DAEDDA0889E2L /* methodKey(F, "org.sireum.IndexMap", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.IndexMap[Any, Any]].contains(o1))
    r.put(0x21FC6362EF3EB274L /* methodKey(F, "org.sireum.IndexMap", "get").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.IndexMap[Any, Any]].get(o1))
    r.put(0xF98BB83B2BCD410AL /* methodKey(F, "org.sireum.Indexable", "at").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Indexable[Any]].at(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x777373F372FC29FAL /* methodKey(F, "org.sireum.Indexable", "has").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Indexable[Any]].has(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xC99DD8AD8ED5B4BBL /* methodKey(F, "org.sireum.Indexable.Pos", "at").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Indexable.Pos[Any]].at(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xE8DEE8204B6975D8L /* methodKey(F, "org.sireum.Indexable.Pos", "has").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Indexable.Pos[Any]].has(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x86024C81261236BFL /* methodKey(T, "org.sireum.Indexable.Isz", "apply").value */, _ => (o1: Any) => org.sireum.Indexable.Isz.apply(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]]))
    r.put(0x56D67B232E75303CL /* methodKey(T, "org.sireum.Indexable.Isz", "unapply").value */, _ => (o1: Any) => org.sireum.Indexable.Isz.unapply(o1.asInstanceOf[org.sireum.Indexable.Isz[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x1B2C4D0447C26BC6L /* methodKey(F, "org.sireum.Indexable.Isz", "at").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Indexable.Isz[Any]].at(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x0CC1D563A132AAABL /* methodKey(F, "org.sireum.Indexable.Isz", "has").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Indexable.Isz[Any]].has(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x5AB9FB1645CDDF4CL /* methodKey(T, "org.sireum.Indexable.IszDocInfo", "unapply").value */, _ => (o1: Any) => org.sireum.Indexable.IszDocInfo.unapply(o1.asInstanceOf[org.sireum.Indexable.IszDocInfo[Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x7BA930D8E6F0AAAEL /* methodKey(F, "org.sireum.Indexable.IszDocInfo", "at").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Indexable.IszDocInfo[Any]].at(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xA33A566D3398C70DL /* methodKey(F, "org.sireum.Indexable.IszDocInfo", "has").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Indexable.IszDocInfo[Any]].has(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x696079B2288A0789L /* methodKey(F, "org.sireum.Jen", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x65BDA0C4BE89B4FAL /* methodKey(F, "org.sireum.Jen", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xFA824C2EEBF91A39L /* methodKey(F, "org.sireum.Jen", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x29F6F7DE797D2F38L /* methodKey(F, "org.sireum.Jen", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x323429C83E87F4C4L /* methodKey(F, "org.sireum.Jen", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].contains(o1))
    r.put(0xC3AE606DC5792974L /* methodKey(F, "org.sireum.Jen", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xDF19F58833F948C1L /* methodKey(F, "org.sireum.Jen", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x6ED18A737F43902FL /* methodKey(F, "org.sireum.Jen", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x293839077AE48AF4L /* methodKey(F, "org.sireum.Jen", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x09123E14D86E3677L /* methodKey(F, "org.sireum.Jen", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xD06DAE1260C563ABL /* methodKey(F, "org.sireum.Jen", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xDBA73A7EFDE41222L /* methodKey(F, "org.sireum.Jen", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xA86D82C85DA64C81L /* methodKey(F, "org.sireum.Jen", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0xF3D56566AF548D3BL /* methodKey(F, "org.sireum.Jen", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0x8866765E0EA72E3CL /* methodKey(F, "org.sireum.Jen", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x28B365DFCA120DAFL /* methodKey(F, "org.sireum.Jen", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x79A815794A1411E6L /* methodKey(F, "org.sireum.Jen", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xD25644F25254E580L /* methodKey(F, "org.sireum.Jen", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xEAD5D10AC100858BL /* methodKey(F, "org.sireum.Jen", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x5191A63B6B74429DL /* methodKey(F, "org.sireum.Jen", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xD8A570F0DDBCEF49L /* methodKey(F, "org.sireum.Jen", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].`++`(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x9986490B255567BFL /* methodKey(F, "org.sireum.Jen", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0xA6DC351B6BD7CA78L /* methodKey(F, "org.sireum.Jen", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x783C95FA2234ADF5L /* methodKey(F, "org.sireum.Jen", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0xC218D3440F9C4305L /* methodKey(T, "org.sireum.Jen.Internal.ISImpl", "apply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.ISImpl.apply(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0xE64547F736B456DBL /* methodKey(T, "org.sireum.Jen.Internal.ISImpl", "unapply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.ISImpl.unapply(o1.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x59243DFE59C50986L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x3BE45166C1041A74L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x6523D9D3EA5CBEF7L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xBF31DED6E028E676L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x6A3A7C840FA4C13DL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].contains(o1))
    r.put(0xA9E82492903945DBL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xBF3DE5E21C95DCE3L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x77D6D7464CE1C0BEL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xACE0200A8A8A0658L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x0E208264E6A35A1BL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x868FEACFB9748173L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xF025B2D3126B0E37L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x0B8F9A312B83FCF1L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0x845EDE65C8A055FCL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0x078683F9042FC460L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x2E1840F599D4EF7AL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x924D9F4090C2E1D5L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xAADB15CC2F44B34AL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x5C4F5DF893E75870L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xDEC6E39BF6614145L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xE32672040C1E3B5DL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].`++`(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x3C5477B34CD8D9DFL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0xFCFF5A0146F19692L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x6F5EFB41710F7C4EL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x637A2B3806FF723CL /* methodKey(T, "org.sireum.Jen.Internal.MapImpl", "apply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.MapImpl.apply(o1.asInstanceOf[org.sireum.Map[Any, Any]]))
    r.put(0xA772BC42ED3CA7CFL /* methodKey(T, "org.sireum.Jen.Internal.MapImpl", "unapply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.MapImpl.unapply(o1.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x2D3FDF824A52FD76L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].generate(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xCBB288F4C4F9AD86L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].foreach(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0x3D71DC02991C3CFAL /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].find(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x7B0ECFDECF9607BEL /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].exists(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x2BB7DA8502D25E84L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].contains(o1.asInstanceOf[(Any, Any)]))
    r.put(0x46E18CB3108CA415L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].forall(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x00794397E9304CEEL /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].countIf(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x2CA81E90EB95798DL /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].reduce(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0x9DDBE26D7C178676L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].reduceLeft(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0x73977D5829105195L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].filter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x3B8E7F2B560DF1E8L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].withFilter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x539A7DBC1952D7B4L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].map(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0x91A88A70D977C9F7L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].flatMap(o1.asInstanceOf[(((Any, Any)) => org.sireum.Jen[Any])]))
    r.put(0x643CBD495A5E4F29L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].flatten(o1.asInstanceOf[(((Any, Any)) => org.sireum.Jen[Any])]))
    r.put(0x861A563FDF790237L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x8F8BD440B32A037DL /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x800F0BB4F1D9F069L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].takeWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xF2ACD4AA1F495E61L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].dropWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x75C55A0B8E45F000L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xDA9514DEBDCF0B48L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x4E0505E2649195ABL /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].`++`(o1.asInstanceOf[org.sireum.Jen[(Any, Any)]]))
    r.put(0x8B038345A48E08E2L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, (Any, Any)]]))
    r.put(0x6FB11FF2E6FBD7DEL /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, (Any, Any)]]))
    r.put(0x9A58E5B573CA4713L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x660AEC3EEA0C9D90L /* methodKey(T, "org.sireum.Jen.Internal.HashMapImpl", "apply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.HashMapImpl.apply(o1.asInstanceOf[org.sireum.HashMap[Any, Any]]))
    r.put(0x0F952A110D0B81C7L /* methodKey(T, "org.sireum.Jen.Internal.HashMapImpl", "unapply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.HashMapImpl.unapply(o1.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x99AEEE6BBE77854DL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].generate(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x86280BC4DCFACCAFL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].foreach(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0x8BDE7E903E33B02EL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].find(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x4F1EB6621917951CL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].exists(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xE4CA216636081972L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].contains(o1.asInstanceOf[(Any, Any)]))
    r.put(0xABAE042438BA9CB3L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].forall(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x811C99A8C7E98EB8L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].countIf(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x75FC216AF9010241L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].reduce(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0x30CF9E057876A7A9L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].reduceLeft(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0x6AECFDBD0DC05B16L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].filter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xC4607AB7229834FEL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].withFilter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x27A25BFA5887E355L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].map(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0x3D0513F0ABB0F2CFL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].flatMap(o1.asInstanceOf[(((Any, Any)) => org.sireum.Jen[Any])]))
    r.put(0x11FFD4AFB1DEC7EEL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].flatten(o1.asInstanceOf[(((Any, Any)) => org.sireum.Jen[Any])]))
    r.put(0x02408E93586F1D1AL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xE392FA57E8A696B1L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x217F11225A51381CL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].takeWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xC69A95F93716AE2CL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].dropWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xB155F75224B3C891L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x6AF05897016C210CL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x322DC3DB2119D55BL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].`++`(o1.asInstanceOf[org.sireum.Jen[(Any, Any)]]))
    r.put(0xEFB8B4859AA0424BL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, (Any, Any)]]))
    r.put(0xD6C33A68D82BE896L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, (Any, Any)]]))
    r.put(0x7D1755F1B26D68F0L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x6E2C5C63EF656547L /* methodKey(T, "org.sireum.Jen.Internal.Filtered", "unapply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.Filtered.unapply(o1.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x8E8A2A6712AC66E0L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x440550A4E1856327L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xF2072D08B3F5E98AL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x8FEBFA6F104F0B39L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xBAB6084195EDAB85L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].contains(o1))
    r.put(0x9D9F9164BB23CD41L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x2EE1E258005A62DCL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xC5E7EE4916B1FAEEL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x240D2D7B7240498CL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x30AB954FF351629DL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x3765A676C911EE3DL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x0979DF9B78D23772L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x280FBCFDCD74B944L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0xFDADA8D53B647B10L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0xE9EECCC51DCACF6DL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x00BE5E5B87E7749CL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xE531F629B95832E5L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xE8E2695620C38055L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x817F1753B9332D2FL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xFEBDE34E5FD7A2B7L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xBDA7FC2B92B07E68L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].`++`(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xF5B3BE96FAF4390EL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x979D6DB62113685BL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x50B16868C6334E68L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x2A5B2E6E018C1E33L /* methodKey(T, "org.sireum.Jen.Internal.Mapped", "unapply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.Mapped.unapply(o1.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0xB534F6C2FC7592EFL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x5C74DD9336D1A3D8L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x4BF1689C7033D058L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x01143985B828661CL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x5FA6556E41BBFA0BL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].contains(o1))
    r.put(0xAC5F447183E97466L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x5B2ADFC8E8A0579CL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x1A5150C063AD5FA3L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xD5A9F61361666569L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xB407736C7FF9BFEAL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x59CCC5476E38554CL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x47E59AE6E10E0486L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xBCD9F51B9D88EC2AL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0x0CAB36BAF33109B5L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0x3B6464D220F52B20L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xC9C07080B0888DCDL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x2038B74307A89789L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xC56791793F718BD0L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xB674E783D3F192F7L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xA09C65AE20496250L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x01890CB538884929L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].`++`(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xE71ED3205CDB851AL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0xEA1B4B643BA25439L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x7E9282B8A2C7CDCBL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0xA8C036FF8C109F32L /* methodKey(T, "org.sireum.Jen.Internal.FlatMapped", "unapply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.FlatMapped.unapply(o1.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0xFBD94D3EFECEEED9L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x02A662DF626C1D3AL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x9B0D24BE8E1E899AL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x41CC133770A1EB2EL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x392831669F9D9960L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].contains(o1))
    r.put(0xC4DD89835729C6FCL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x56A3C9D99A03AFF0L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xA2D0830943CEBE32L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x0548EAE641D40DDDL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x7E479BB5F7D250DDL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x3B0E3AE589C9FAE4L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x451A1A1F42AABE5AL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xFEC085164D6083F5L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0xBE289F50B57E60D1L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0xD02E153A0F183A4DL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x215C2BE3EC5FD3B7L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xF97D44CC1ECA8E1EL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x4D32DB7A1AADAA6CL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x682136A9BF391C8AL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x84A31CB24C12EF73L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xFA98AA5F5E3B44E6L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].`++`(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xDE54103ADD7DCD6AL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x2656A0F32157E4FCL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x45C525AE595F4296L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x82E349E3FEAAD511L /* methodKey(T, "org.sireum.Jen.Internal.Sliced", "unapply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.Sliced.unapply(o1.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]]) match {
      case scala.Some((o0, o1, o2)) => Some((o0, o1, o2))
      case _ => None()
    })
    r.put(0x153F96BFDDE97355L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x80E39F1C072CF7F6L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x484DEC2ECF5C842EL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x0D52E1622D63F7C8L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x4B3B092C7A334102L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].contains(o1))
    r.put(0x40BA1FD0BD63AD2AL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x536077DBDDABD446L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x09B9ACC2014CD9B0L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x2533D2DEF7FD7CF6L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x2CDE2014CFA6202DL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xC763125C92152D1DL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x2C1D65D8BBA219A8L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x3F874AEED9643ED0L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0xC70351054A57DDAEL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0x668FB670F8A9361AL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x915D1EC1962628A7L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xE3FC1C49B6E5B7CDL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x0C6143B958CC5759L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x1F9E08EA24CCBD2EL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x7A2C2AABCAEDCB3FL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x263F5431CD61743EL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].`++`(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x9D927EA132B610D8L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x13F5222944843B17L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x3A2C64FEDF811FA3L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0xA87B3DE2919D6FC9L /* methodKey(T, "org.sireum.Jen.Internal.TakeWhile", "unapply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.TakeWhile.unapply(o1.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x84C9A233D46F1C96L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xD55279C0FD5A01BAL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x27DF2CFC60A3A4C4L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x783DE0928D6CC66CL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xD0535266FC6CD69AL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].contains(o1))
    r.put(0x19775856366B34ABL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x44B5B01408CA590DL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x92878BE853AC804AL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x5CD4F92E061010EAL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x00887C012A7B23A9L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xFD5A0CB19C740B92L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x9AEC724ED46A8BA1L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x71794754A4F24076L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0xF7514158835C9C1AL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0x772B7BD57C878DCBL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xF4B2D729C63A05D6L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x4DDADF88A418182EL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xD6BC6F161CC85A8AL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xD286D91BDA445F1BL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x93FB6BEC2817DD44L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xEEB6215A6570FE07L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].`++`(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xBF9C47398940727AL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x4128ACD75B3156EBL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x62132A1BED6EC37EL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x15286882C909AED2L /* methodKey(T, "org.sireum.Jen.Internal.DropWhile", "unapply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.DropWhile.unapply(o1.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0xF813C1B074DB24D7L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xB3A27F84403A4B62L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xAD76D57EAD77EB7EL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x4D2E6913967FD086L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x88412BE6DA70F484L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].contains(o1))
    r.put(0x8D7AEE3CBA82084CL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xC1F46ED9528598ADL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x6435A035BF819A1AL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x10AEE2475D5B5F2AL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x09334021DB91D9EEL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x17BC2B131F622BF9L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x7F7205B920D675EAL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xC219B588734A0591L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0x9FF33F59CE5918DAL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0x564D035F5D38E070L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xF1F37A3F98D569F8L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xF07B8CAECD0FF451L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xEFF8A5A35B681778L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x8DAD8B8EBE272B5EL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x1981AFA730A2DC2EL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x5C777C2EBADDFA8FL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].`++`(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x96ED7BD49D7D1F46L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x56132E9CC5668068L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0xFDA10A97EE3FD99CL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x87AE6F7EB1B57247L /* methodKey(T, "org.sireum.Jen.Internal.ZipWithIndexed", "apply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.ZipWithIndexed.apply(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x0361FB9B511A7D5AL /* methodKey(T, "org.sireum.Jen.Internal.ZipWithIndexed", "unapply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.ZipWithIndexed.unapply(o1.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x2C86FE836F05CB27L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].generate(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0xDAD82D3C1550A2FAL /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].foreach(o1.asInstanceOf[(((Any, org.sireum.Z)) => Any)]))
    r.put(0x22954832D4DE9778L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].find(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0xE1429CB297D342BCL /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].exists(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0x039574D293A0E1A8L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].contains(o1.asInstanceOf[(Any, org.sireum.Z)]))
    r.put(0xA441204ABDC09D60L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].forall(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0x8B2610335F8ED7B6L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].countIf(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0x7D451546EA00B441L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].reduce(o1.asInstanceOf[(((Any, org.sireum.Z), (Any, org.sireum.Z)) => (Any, org.sireum.Z))]))
    r.put(0xA1C47947A35090C2L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].reduceLeft(o1.asInstanceOf[(((Any, org.sireum.Z), (Any, org.sireum.Z)) => (Any, org.sireum.Z))]))
    r.put(0x4085B67D5EEAC26AL /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].filter(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0x2D48C4C18A199213L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].withFilter(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0xBCFBEE82FC3E25D3L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].map(o1.asInstanceOf[(((Any, org.sireum.Z)) => Any)]))
    r.put(0x8453B96A4467505DL /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].flatMap(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.Jen[Any])]))
    r.put(0x1F167370BB05D90FL /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].flatten(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.Jen[Any])]))
    r.put(0x9A8146D177268C30L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x4A6F3569257F7940L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xE747EB0997FBBC61L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].takeWhile(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0x8FF12D7362084178L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].dropWhile(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0x93CB4E0B5C680C43L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xDF2498801EE4A062L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x623A542B363554DEL /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].`++`(o1.asInstanceOf[org.sireum.Jen[(Any, org.sireum.Z)]]))
    r.put(0x531088986BCE1C0FL /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, (Any, org.sireum.Z)]]))
    r.put(0xD7B222919E9BB0F6L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, (Any, org.sireum.Z)]]))
    r.put(0x0BF72FEBA43B67D2L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x9FA34680DEAFF91DL /* methodKey(T, "org.sireum.Jen.Internal.Zipped", "unapply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.Zipped.unapply(o1.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x8EB223876C49ACFCL /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].generate(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x5B132FE14C56C805L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].foreach(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0x16CD59785FEAA918L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].find(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x72D429843ED865F3L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].exists(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x05D02F8BF21C1384L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].contains(o1.asInstanceOf[(Any, Any)]))
    r.put(0xBC89531366096A90L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].forall(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x78374D6D345A32F6L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].countIf(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xC2E239A9CC2D17F4L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].reduce(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0xA4F43AB87A38DBBEL /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].reduceLeft(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0xDBB2E6B561AEE143L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].filter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xC6602ED8334A3C2DL /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].withFilter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x476A10888CABEC9CL /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].map(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0xA7686EF834E3E3DDL /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].flatMap(o1.asInstanceOf[(((Any, Any)) => org.sireum.Jen[Any])]))
    r.put(0x49AF211454BACE02L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].flatten(o1.asInstanceOf[(((Any, Any)) => org.sireum.Jen[Any])]))
    r.put(0x1E0BF199A14D4009L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xD775EBE1703ED459L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x417AC1CE7E492EB7L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].takeWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x783BE7851D55D8F5L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].dropWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x8233431B6F9264B9L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xAD75B50B8DF10465L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x38E328B0E314A142L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].`++`(o1.asInstanceOf[org.sireum.Jen[(Any, Any)]]))
    r.put(0x982B0B27B439CEDDL /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, (Any, Any)]]))
    r.put(0x5A136BA39A1740F7L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, (Any, Any)]]))
    r.put(0x29DD6E61F97D725AL /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x0940D60810E8C100L /* methodKey(T, "org.sireum.Jen.Internal.Concat", "unapply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.Concat.unapply(o1.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x098653871134C443L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x97B0AF10BA9C0766L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x71DDB98A410EFED9L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x5A51B624D930C680L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xE865E7BEF6F3902AL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].contains(o1))
    r.put(0xE334946DA058B374L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xAD11F57DE4481226L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x73CBC04CC82F3FF1L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x4E2E83DB3332B4ECL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x1A02049B53089038L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x1C129193F75DBF74L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xED1CEAADAD772A46L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x002F0A7E6A593344L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0xE99C1B94FED5C2ECL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0xD940DDBCAA6F39D6L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x513DE56CC38FD47EL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xD8D35CA2D1F1F48DL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xFC2CFC53877D02AAL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xF51536054523745BL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x3CC757C07819CB79L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xC35FC8307EFE8786L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].`++`(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xB8C89D9BCB7CEA30L /* methodKey(F, "org.sireum.Jen.Internal.Concat", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x751CFFB6094E1A3AL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0xB98780F96B57FCABL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0xF52D015D607A591AL /* methodKey(T, "org.sireum.Jen.Internal.Product", "unapply").value */, _ => (o1: Any) => org.sireum.Jen.Internal.Product.unapply(o1.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x3E88062635BE5EDBL /* methodKey(F, "org.sireum.Jen.Internal.Product", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].generate(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x495C6BDE892E1ED4L /* methodKey(F, "org.sireum.Jen.Internal.Product", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].foreach(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0x95C69B5FE1E1E47BL /* methodKey(F, "org.sireum.Jen.Internal.Product", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].find(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x53E424AEC85FAE4FL /* methodKey(F, "org.sireum.Jen.Internal.Product", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].exists(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x674EEADFA0DBFF4DL /* methodKey(F, "org.sireum.Jen.Internal.Product", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].contains(o1.asInstanceOf[(Any, Any)]))
    r.put(0xD4817CB4CC0298C6L /* methodKey(F, "org.sireum.Jen.Internal.Product", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].forall(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xB8F200C307E74C53L /* methodKey(F, "org.sireum.Jen.Internal.Product", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].countIf(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xE9DC53052A285817L /* methodKey(F, "org.sireum.Jen.Internal.Product", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].reduce(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0x4C39D8DE285973C0L /* methodKey(F, "org.sireum.Jen.Internal.Product", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].reduceLeft(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0x42711B9EC9B8E506L /* methodKey(F, "org.sireum.Jen.Internal.Product", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].filter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x59ADD0E0AE178ECCL /* methodKey(F, "org.sireum.Jen.Internal.Product", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].withFilter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x69BE5F2E06729631L /* methodKey(F, "org.sireum.Jen.Internal.Product", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].map(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0x28D9413173C8409BL /* methodKey(F, "org.sireum.Jen.Internal.Product", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].flatMap(o1.asInstanceOf[(((Any, Any)) => org.sireum.Jen[Any])]))
    r.put(0x36E9F9DA28CC3F6AL /* methodKey(F, "org.sireum.Jen.Internal.Product", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].flatten(o1.asInstanceOf[(((Any, Any)) => org.sireum.Jen[Any])]))
    r.put(0x4ABF016C27D6B9CDL /* methodKey(F, "org.sireum.Jen.Internal.Product", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x061152B40AF5BA70L /* methodKey(F, "org.sireum.Jen.Internal.Product", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x8DCE8CA4B0CE3DA4L /* methodKey(F, "org.sireum.Jen.Internal.Product", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].takeWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x1EF8B9DDE43D0B0BL /* methodKey(F, "org.sireum.Jen.Internal.Product", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].dropWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xB51C51AA8A4117B0L /* methodKey(F, "org.sireum.Jen.Internal.Product", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xB8EDDCE2C63EB85BL /* methodKey(F, "org.sireum.Jen.Internal.Product", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x2A50D6AD1819C9FEL /* methodKey(F, "org.sireum.Jen.Internal.Product", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].`++`(o1.asInstanceOf[org.sireum.Jen[(Any, Any)]]))
    r.put(0x9A9B8FE9E6CAC2C0L /* methodKey(F, "org.sireum.Jen.Internal.Product", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, (Any, Any)]]))
    r.put(0xEC81F495C1E6D003L /* methodKey(F, "org.sireum.Jen.Internal.Product", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, (Any, Any)]]))
    r.put(0xF74FFA40444D9B26L /* methodKey(F, "org.sireum.Jen.Internal.Product", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x10E6FED5D7EC3F59L /* methodKey(T, "org.sireum.MBox", "apply").value */, _ => (o1: Any) => org.sireum.MBox.apply(o1))
    r.put(0xDF42214D89E9FB2DL /* methodKey(T, "org.sireum.MBox", "unapply").value */, _ => (o1: Any) => org.sireum.MBox.unapply(o1.asInstanceOf[org.sireum.MBox[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x3F9E049682FE132CL /* methodKey(F, "org.sireum.MBox", "value_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox[Any]].`value_=`(o1))
    r.put(0x4380B1DC3E6004A2L /* methodKey(T, "org.sireum.MBox2", "unapply").value */, _ => (o1: Any) => org.sireum.MBox2.unapply(o1.asInstanceOf[org.sireum.MBox2[Any, Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x366206617A5D71D4L /* methodKey(F, "org.sireum.MBox2", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox2[Any, Any]].`value1_=`(o1))
    r.put(0xB5B9B6C0175F4325L /* methodKey(F, "org.sireum.MBox2", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox2[Any, Any]].`value2_=`(o1))
    r.put(0xB597FF5FACEECF68L /* methodKey(T, "org.sireum.MBox3", "unapply").value */, _ => (o1: Any) => org.sireum.MBox3.unapply(o1.asInstanceOf[org.sireum.MBox3[Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2)) => Some((o0, o1, o2))
      case _ => None()
    })
    r.put(0x839E76F1B157869BL /* methodKey(F, "org.sireum.MBox3", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox3[Any, Any, Any]].`value1_=`(o1))
    r.put(0x2B8BB11A7C5A2878L /* methodKey(F, "org.sireum.MBox3", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox3[Any, Any, Any]].`value2_=`(o1))
    r.put(0xA1A1D81DE2521BB0L /* methodKey(F, "org.sireum.MBox3", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox3[Any, Any, Any]].`value3_=`(o1))
    r.put(0x638006497E6C44E9L /* methodKey(T, "org.sireum.MBox4", "unapply").value */, _ => (o1: Any) => org.sireum.MBox4.unapply(o1.asInstanceOf[org.sireum.MBox4[Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3)) => Some((o0, o1, o2, o3))
      case _ => None()
    })
    r.put(0x3C563B50B7F54C1BL /* methodKey(F, "org.sireum.MBox4", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox4[Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x3A60A48132B59A79L /* methodKey(F, "org.sireum.MBox4", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox4[Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0xB281B56867FE432BL /* methodKey(F, "org.sireum.MBox4", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox4[Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0x7722203028CFDDD6L /* methodKey(F, "org.sireum.MBox4", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox4[Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0x84A18D41CBFC5490L /* methodKey(T, "org.sireum.MBox5", "unapply").value */, _ => (o1: Any) => org.sireum.MBox5.unapply(o1.asInstanceOf[org.sireum.MBox5[Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4)) => Some((o0, o1, o2, o3, o4))
      case _ => None()
    })
    r.put(0x192785A9481E7992L /* methodKey(F, "org.sireum.MBox5", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox5[Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0xE9045E4E664417BAL /* methodKey(F, "org.sireum.MBox5", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox5[Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0x8A3ECD6EB6F8DE93L /* methodKey(F, "org.sireum.MBox5", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox5[Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0x3928933399F50528L /* methodKey(F, "org.sireum.MBox5", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox5[Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0x8DA5CB7D6FB6AA85L /* methodKey(F, "org.sireum.MBox5", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox5[Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0xC45CE302317197E9L /* methodKey(T, "org.sireum.MBox6", "unapply").value */, _ => (o1: Any) => org.sireum.MBox6.unapply(o1.asInstanceOf[org.sireum.MBox6[Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5)) => Some((o0, o1, o2, o3, o4, o5))
      case _ => None()
    })
    r.put(0x52013BF83AC0B4C5L /* methodKey(F, "org.sireum.MBox6", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox6[Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x3B092D9A0FDDD126L /* methodKey(F, "org.sireum.MBox6", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox6[Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0x6FE9BA0E2073BEE6L /* methodKey(F, "org.sireum.MBox6", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox6[Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0x48F21FBA541F8AAFL /* methodKey(F, "org.sireum.MBox6", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox6[Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0x2EFA8BB7BCE82330L /* methodKey(F, "org.sireum.MBox6", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox6[Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0x49270AD9AEE2633FL /* methodKey(F, "org.sireum.MBox6", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox6[Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0x6BC644A5DAA7B5A5L /* methodKey(T, "org.sireum.MBox7", "unapply").value */, _ => (o1: Any) => org.sireum.MBox7.unapply(o1.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6)) => Some((o0, o1, o2, o3, o4, o5, o6))
      case _ => None()
    })
    r.put(0x1D779094B9018851L /* methodKey(F, "org.sireum.MBox7", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0xB2C17AEFE8EC4AC3L /* methodKey(F, "org.sireum.MBox7", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0x94555BDEFFCB9DFCL /* methodKey(F, "org.sireum.MBox7", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0xFA1D0539D139FF7EL /* methodKey(F, "org.sireum.MBox7", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0xF7F87ACF387012C6L /* methodKey(F, "org.sireum.MBox7", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0x784E80ACD4B494CEL /* methodKey(F, "org.sireum.MBox7", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0xF4DD3CA2FB52C8C9L /* methodKey(F, "org.sireum.MBox7", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox7[Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0xF60FA9D4F630A710L /* methodKey(T, "org.sireum.MBox8", "unapply").value */, _ => (o1: Any) => org.sireum.MBox8.unapply(o1.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7)) => Some((o0, o1, o2, o3, o4, o5, o6, o7))
      case _ => None()
    })
    r.put(0xAEAFCACB1308B779L /* methodKey(F, "org.sireum.MBox8", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x8005A76695C49443L /* methodKey(F, "org.sireum.MBox8", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0x24D85246197C7EC8L /* methodKey(F, "org.sireum.MBox8", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0x92B8B0C27B90A2F3L /* methodKey(F, "org.sireum.MBox8", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0xF36CBEC5C33D10A9L /* methodKey(F, "org.sireum.MBox8", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0xBF9CF8B41F7629F9L /* methodKey(F, "org.sireum.MBox8", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0xDF40F69AF135E066L /* methodKey(F, "org.sireum.MBox8", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0xBE8AFFCB5DFDEA3AL /* methodKey(F, "org.sireum.MBox8", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox8[Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0x9D418F4104DC2A6AL /* methodKey(T, "org.sireum.MBox9", "unapply").value */, _ => (o1: Any) => org.sireum.MBox9.unapply(o1.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8))
      case _ => None()
    })
    r.put(0x0BEB3DB7481AE745L /* methodKey(F, "org.sireum.MBox9", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x97DCAC8CDB0D06CFL /* methodKey(F, "org.sireum.MBox9", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0xDB46C6DD4C7B0C7DL /* methodKey(F, "org.sireum.MBox9", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0xF5366114CB666C66L /* methodKey(F, "org.sireum.MBox9", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0xD5FEAE9FF5801124L /* methodKey(F, "org.sireum.MBox9", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0x8D6743417B957D92L /* methodKey(F, "org.sireum.MBox9", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0xCBCEAAD933379C6EL /* methodKey(F, "org.sireum.MBox9", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0x09488CD659FA787DL /* methodKey(F, "org.sireum.MBox9", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0x619768351FB9F112L /* methodKey(F, "org.sireum.MBox9", "value9_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox9[Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value9_=`(o1))
    r.put(0xE15C4A615F1B3B18L /* methodKey(T, "org.sireum.MBox10", "unapply").value */, _ => (o1: Any) => org.sireum.MBox10.unapply(o1.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9))
      case _ => None()
    })
    r.put(0xD7B06E3794AAA725L /* methodKey(F, "org.sireum.MBox10", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0xAC70B3F9A9FB97FCL /* methodKey(F, "org.sireum.MBox10", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0x57133E7DF76407BFL /* methodKey(F, "org.sireum.MBox10", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0xCC5BB5848413521DL /* methodKey(F, "org.sireum.MBox10", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0x04E500CEACBBA052L /* methodKey(F, "org.sireum.MBox10", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0x4407EB8AAD1E20F7L /* methodKey(F, "org.sireum.MBox10", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0xF5C3B0BCC98B453CL /* methodKey(F, "org.sireum.MBox10", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0xE3F0CF6D5043CCBCL /* methodKey(F, "org.sireum.MBox10", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0xC9A2FA0FD54EAD4DL /* methodKey(F, "org.sireum.MBox10", "value9_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value9_=`(o1))
    r.put(0x25ECBFC29DF2C129L /* methodKey(F, "org.sireum.MBox10", "value10_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox10[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value10_=`(o1))
    r.put(0x3D6ECE8ED082B7BEL /* methodKey(T, "org.sireum.MBox11", "unapply").value */, _ => (o1: Any) => org.sireum.MBox11.unapply(o1.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10))
      case _ => None()
    })
    r.put(0x7353679A1F00F4DBL /* methodKey(F, "org.sireum.MBox11", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x2617A92B0D621456L /* methodKey(F, "org.sireum.MBox11", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0x9565F8440ADFA28BL /* methodKey(F, "org.sireum.MBox11", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0x2E371EFF14883642L /* methodKey(F, "org.sireum.MBox11", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0x2E512B778A0100A2L /* methodKey(F, "org.sireum.MBox11", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0xEE024DC9243DF1E6L /* methodKey(F, "org.sireum.MBox11", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0xDE6EB0D3988730E3L /* methodKey(F, "org.sireum.MBox11", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0xABE52D61C4B8225FL /* methodKey(F, "org.sireum.MBox11", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0xE5576D69301390D2L /* methodKey(F, "org.sireum.MBox11", "value9_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value9_=`(o1))
    r.put(0xABA79404FA2C7468L /* methodKey(F, "org.sireum.MBox11", "value10_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value10_=`(o1))
    r.put(0x2A7A0258ED200267L /* methodKey(F, "org.sireum.MBox11", "value11_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox11[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value11_=`(o1))
    r.put(0xC933C8C370DD2374L /* methodKey(T, "org.sireum.MBox12", "unapply").value */, _ => (o1: Any) => org.sireum.MBox12.unapply(o1.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11))
      case _ => None()
    })
    r.put(0x9DDE92E3C01299E0L /* methodKey(F, "org.sireum.MBox12", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x266FAC60AC7E6D98L /* methodKey(F, "org.sireum.MBox12", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0x4C8215DF51213AABL /* methodKey(F, "org.sireum.MBox12", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0x780264E3F7C6A629L /* methodKey(F, "org.sireum.MBox12", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0x1027B3ABCB079D0BL /* methodKey(F, "org.sireum.MBox12", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0x9874F3144C4DD103L /* methodKey(F, "org.sireum.MBox12", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0x3BA661BBF0F4D0CFL /* methodKey(F, "org.sireum.MBox12", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0xB94C23AA5848AC87L /* methodKey(F, "org.sireum.MBox12", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0xD5F31B6C050426E8L /* methodKey(F, "org.sireum.MBox12", "value9_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value9_=`(o1))
    r.put(0x124283ACD1291AE1L /* methodKey(F, "org.sireum.MBox12", "value10_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value10_=`(o1))
    r.put(0x3E51F8CADCC77F53L /* methodKey(F, "org.sireum.MBox12", "value11_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value11_=`(o1))
    r.put(0x3205AB5C9C943143L /* methodKey(F, "org.sireum.MBox12", "value12_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox12[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value12_=`(o1))
    r.put(0x2D384C7B89C9692FL /* methodKey(T, "org.sireum.MBox13", "unapply").value */, _ => (o1: Any) => org.sireum.MBox13.unapply(o1.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12))
      case _ => None()
    })
    r.put(0x3F16FD8A12B00569L /* methodKey(F, "org.sireum.MBox13", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x661BDC686EF85B97L /* methodKey(F, "org.sireum.MBox13", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0x081C1195912D3E3EL /* methodKey(F, "org.sireum.MBox13", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0xB45C7A23CB6C2051L /* methodKey(F, "org.sireum.MBox13", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0x7C42C9F3F1685199L /* methodKey(F, "org.sireum.MBox13", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0x433310604012492FL /* methodKey(F, "org.sireum.MBox13", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0x2724F960BA553C29L /* methodKey(F, "org.sireum.MBox13", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0xD532CCE1096DF91CL /* methodKey(F, "org.sireum.MBox13", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0xE4ABD509BA3294CFL /* methodKey(F, "org.sireum.MBox13", "value9_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value9_=`(o1))
    r.put(0x12444FD25B4B3200L /* methodKey(F, "org.sireum.MBox13", "value10_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value10_=`(o1))
    r.put(0x9A11854FF4D6B9CFL /* methodKey(F, "org.sireum.MBox13", "value11_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value11_=`(o1))
    r.put(0xF8C3F4FDD8F635A2L /* methodKey(F, "org.sireum.MBox13", "value12_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value12_=`(o1))
    r.put(0x329A0DDD19FA331BL /* methodKey(F, "org.sireum.MBox13", "value13_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox13[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value13_=`(o1))
    r.put(0x32D444222F52CDCFL /* methodKey(T, "org.sireum.MBox14", "unapply").value */, _ => (o1: Any) => org.sireum.MBox14.unapply(o1.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13))
      case _ => None()
    })
    r.put(0x6D43230182921729L /* methodKey(F, "org.sireum.MBox14", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x6D9BA241BF0B17C0L /* methodKey(F, "org.sireum.MBox14", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0x7EA5101AAA9F9772L /* methodKey(F, "org.sireum.MBox14", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0xC5DFE5421B19A081L /* methodKey(F, "org.sireum.MBox14", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0xC152DFAC34AE310BL /* methodKey(F, "org.sireum.MBox14", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0x755062B9163B5214L /* methodKey(F, "org.sireum.MBox14", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0x470D012E8D00E8B1L /* methodKey(F, "org.sireum.MBox14", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0xEF3C9CE967E44336L /* methodKey(F, "org.sireum.MBox14", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0xDCFCCCF911C6541AL /* methodKey(F, "org.sireum.MBox14", "value9_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value9_=`(o1))
    r.put(0xEAC74E284CF7E5A6L /* methodKey(F, "org.sireum.MBox14", "value10_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value10_=`(o1))
    r.put(0xF63405D1286D63A0L /* methodKey(F, "org.sireum.MBox14", "value11_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value11_=`(o1))
    r.put(0xD8320579AD4A8D21L /* methodKey(F, "org.sireum.MBox14", "value12_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value12_=`(o1))
    r.put(0x436FDC6099043842L /* methodKey(F, "org.sireum.MBox14", "value13_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value13_=`(o1))
    r.put(0x01B01BEFCC891DB3L /* methodKey(F, "org.sireum.MBox14", "value14_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox14[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value14_=`(o1))
    r.put(0xCE24E34145C0C273L /* methodKey(T, "org.sireum.MBox15", "unapply").value */, _ => (o1: Any) => org.sireum.MBox15.unapply(o1.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14))
      case _ => None()
    })
    r.put(0x7CCD5169B560D961L /* methodKey(F, "org.sireum.MBox15", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x85397E3143B4F8E7L /* methodKey(F, "org.sireum.MBox15", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0xD7CF78B820505717L /* methodKey(F, "org.sireum.MBox15", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0xB16DC2B0B450C929L /* methodKey(F, "org.sireum.MBox15", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0xD99F8BAAE9CBFE75L /* methodKey(F, "org.sireum.MBox15", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0x9CB859D090368535L /* methodKey(F, "org.sireum.MBox15", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0x1DD42DD3CD0C4B84L /* methodKey(F, "org.sireum.MBox15", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0x2A55688561F4A922L /* methodKey(F, "org.sireum.MBox15", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0x2C38657D5231B701L /* methodKey(F, "org.sireum.MBox15", "value9_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value9_=`(o1))
    r.put(0xB9A56806E81DE0E2L /* methodKey(F, "org.sireum.MBox15", "value10_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value10_=`(o1))
    r.put(0x594A02AC8716817BL /* methodKey(F, "org.sireum.MBox15", "value11_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value11_=`(o1))
    r.put(0x5853ACD6B46B6A99L /* methodKey(F, "org.sireum.MBox15", "value12_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value12_=`(o1))
    r.put(0x9433D3FF2F343226L /* methodKey(F, "org.sireum.MBox15", "value13_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value13_=`(o1))
    r.put(0x5915E21AA6DCDFC9L /* methodKey(F, "org.sireum.MBox15", "value14_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value14_=`(o1))
    r.put(0xCAC72EEBD6BED640L /* methodKey(F, "org.sireum.MBox15", "value15_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox15[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value15_=`(o1))
    r.put(0x8032B7C9EAB0DD2CL /* methodKey(T, "org.sireum.MBox16", "unapply").value */, _ => (o1: Any) => org.sireum.MBox16.unapply(o1.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15))
      case _ => None()
    })
    r.put(0xFA150D13F2C94D58L /* methodKey(F, "org.sireum.MBox16", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x6EE5CFB8CD4736F9L /* methodKey(F, "org.sireum.MBox16", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0x244DF646543BE54CL /* methodKey(F, "org.sireum.MBox16", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0x133AD430DA66735BL /* methodKey(F, "org.sireum.MBox16", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0x7C2860FAE7ACC117L /* methodKey(F, "org.sireum.MBox16", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0x82DFA6828B071D7CL /* methodKey(F, "org.sireum.MBox16", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0xC8EDCB512CBB1A5DL /* methodKey(F, "org.sireum.MBox16", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0xD0D077DB982C9752L /* methodKey(F, "org.sireum.MBox16", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0x2167AA02601B9F3AL /* methodKey(F, "org.sireum.MBox16", "value9_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value9_=`(o1))
    r.put(0x2676B0C99AC651CBL /* methodKey(F, "org.sireum.MBox16", "value10_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value10_=`(o1))
    r.put(0xD0F7CD6118E09693L /* methodKey(F, "org.sireum.MBox16", "value11_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value11_=`(o1))
    r.put(0xF05A0D95C409FC57L /* methodKey(F, "org.sireum.MBox16", "value12_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value12_=`(o1))
    r.put(0xA93F253F6BD473A7L /* methodKey(F, "org.sireum.MBox16", "value13_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value13_=`(o1))
    r.put(0x52B4E422323A21CEL /* methodKey(F, "org.sireum.MBox16", "value14_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value14_=`(o1))
    r.put(0x32F936B47473496AL /* methodKey(F, "org.sireum.MBox16", "value15_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value15_=`(o1))
    r.put(0x5F385A6D3C0A8322L /* methodKey(F, "org.sireum.MBox16", "value16_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox16[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value16_=`(o1))
    r.put(0xB4530560FFA83DF7L /* methodKey(T, "org.sireum.MBox17", "unapply").value */, _ => (o1: Any) => org.sireum.MBox17.unapply(o1.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16))
      case _ => None()
    })
    r.put(0xA7E9365FA9C3EBB2L /* methodKey(F, "org.sireum.MBox17", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0xFA2E2A3D273514E9L /* methodKey(F, "org.sireum.MBox17", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0x060269A271959A4CL /* methodKey(F, "org.sireum.MBox17", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0x3F47D154F993689FL /* methodKey(F, "org.sireum.MBox17", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0xF56A2B6CD9BCD7C2L /* methodKey(F, "org.sireum.MBox17", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0xCE2CF08D9BDDB94EL /* methodKey(F, "org.sireum.MBox17", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0x05EAAAE42E5483BDL /* methodKey(F, "org.sireum.MBox17", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0x0957DE444F515017L /* methodKey(F, "org.sireum.MBox17", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0x41B70F8E19769A8BL /* methodKey(F, "org.sireum.MBox17", "value9_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value9_=`(o1))
    r.put(0x8828F73D5582AA26L /* methodKey(F, "org.sireum.MBox17", "value10_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value10_=`(o1))
    r.put(0xCAFDE1702610528DL /* methodKey(F, "org.sireum.MBox17", "value11_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value11_=`(o1))
    r.put(0xA16FB7569A08EA94L /* methodKey(F, "org.sireum.MBox17", "value12_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value12_=`(o1))
    r.put(0xB60E378DDAD7777CL /* methodKey(F, "org.sireum.MBox17", "value13_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value13_=`(o1))
    r.put(0xCF5AB6740CFFB48CL /* methodKey(F, "org.sireum.MBox17", "value14_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value14_=`(o1))
    r.put(0xBDCC54D593111987L /* methodKey(F, "org.sireum.MBox17", "value15_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value15_=`(o1))
    r.put(0x9B1DEE62974E527EL /* methodKey(F, "org.sireum.MBox17", "value16_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value16_=`(o1))
    r.put(0x39E9D8F5059A64DCL /* methodKey(F, "org.sireum.MBox17", "value17_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox17[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value17_=`(o1))
    r.put(0xAD859AD1EFA30E47L /* methodKey(T, "org.sireum.MBox18", "unapply").value */, _ => (o1: Any) => org.sireum.MBox18.unapply(o1.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17))
      case _ => None()
    })
    r.put(0x090D7781C005C3A9L /* methodKey(F, "org.sireum.MBox18", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x0F5E5D06B40F96FAL /* methodKey(F, "org.sireum.MBox18", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0x0C975BAF470429BAL /* methodKey(F, "org.sireum.MBox18", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0x12E22DA1E261880BL /* methodKey(F, "org.sireum.MBox18", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0xEBDEB9A762DE8196L /* methodKey(F, "org.sireum.MBox18", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0x926C4224C67B5C03L /* methodKey(F, "org.sireum.MBox18", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0xC721D0EA5A0588B9L /* methodKey(F, "org.sireum.MBox18", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0xAC08B572FA4D27D6L /* methodKey(F, "org.sireum.MBox18", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0x333763F80A856EFBL /* methodKey(F, "org.sireum.MBox18", "value9_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value9_=`(o1))
    r.put(0xCD63253A8E2549A0L /* methodKey(F, "org.sireum.MBox18", "value10_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value10_=`(o1))
    r.put(0x14B8EDB242B43A0AL /* methodKey(F, "org.sireum.MBox18", "value11_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value11_=`(o1))
    r.put(0xC44237395A8987FBL /* methodKey(F, "org.sireum.MBox18", "value12_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value12_=`(o1))
    r.put(0x0F9B65C0903D8E29L /* methodKey(F, "org.sireum.MBox18", "value13_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value13_=`(o1))
    r.put(0x2569A911B9641A69L /* methodKey(F, "org.sireum.MBox18", "value14_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value14_=`(o1))
    r.put(0xDE8C7A08EC204C79L /* methodKey(F, "org.sireum.MBox18", "value15_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value15_=`(o1))
    r.put(0xAE6B8A07BDBB1F29L /* methodKey(F, "org.sireum.MBox18", "value16_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value16_=`(o1))
    r.put(0xF041528747472440L /* methodKey(F, "org.sireum.MBox18", "value17_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value17_=`(o1))
    r.put(0xC44C8FE951FB79CCL /* methodKey(F, "org.sireum.MBox18", "value18_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox18[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value18_=`(o1))
    r.put(0xE7CBCACEACB00E4CL /* methodKey(T, "org.sireum.MBox19", "unapply").value */, _ => (o1: Any) => org.sireum.MBox19.unapply(o1.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18))
      case _ => None()
    })
    r.put(0x0F8FA3C9A8B12AF7L /* methodKey(F, "org.sireum.MBox19", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x5653F40D3506D8B4L /* methodKey(F, "org.sireum.MBox19", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0x66E81DDA25E9F581L /* methodKey(F, "org.sireum.MBox19", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0x767C3F73EEE61BFFL /* methodKey(F, "org.sireum.MBox19", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0x91013A854CBB91C8L /* methodKey(F, "org.sireum.MBox19", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0x485C4F31A59681A8L /* methodKey(F, "org.sireum.MBox19", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0x4899F897D834D7ECL /* methodKey(F, "org.sireum.MBox19", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0x4D2C663A1A03E838L /* methodKey(F, "org.sireum.MBox19", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0x133D11BAD10A1D97L /* methodKey(F, "org.sireum.MBox19", "value9_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value9_=`(o1))
    r.put(0x6F24CC2A2FEC7707L /* methodKey(F, "org.sireum.MBox19", "value10_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value10_=`(o1))
    r.put(0xE782308EAD93CC95L /* methodKey(F, "org.sireum.MBox19", "value11_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value11_=`(o1))
    r.put(0x2AD7F4F0375491EBL /* methodKey(F, "org.sireum.MBox19", "value12_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value12_=`(o1))
    r.put(0xB4069014F8380749L /* methodKey(F, "org.sireum.MBox19", "value13_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value13_=`(o1))
    r.put(0x52B1B83BC692CB92L /* methodKey(F, "org.sireum.MBox19", "value14_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value14_=`(o1))
    r.put(0x9EFE73B92B0B6FC6L /* methodKey(F, "org.sireum.MBox19", "value15_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value15_=`(o1))
    r.put(0x03B5FBEE3C103E4EL /* methodKey(F, "org.sireum.MBox19", "value16_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value16_=`(o1))
    r.put(0x28CE6B25A230252DL /* methodKey(F, "org.sireum.MBox19", "value17_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value17_=`(o1))
    r.put(0xDB99F2E3693E02ADL /* methodKey(F, "org.sireum.MBox19", "value18_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value18_=`(o1))
    r.put(0x438BA5580CFCDF54L /* methodKey(F, "org.sireum.MBox19", "value19_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox19[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value19_=`(o1))
    r.put(0xC3C512383AA9AF6BL /* methodKey(T, "org.sireum.MBox20", "unapply").value */, _ => (o1: Any) => org.sireum.MBox20.unapply(o1.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19))
      case _ => None()
    })
    r.put(0xE8019935B589C7D8L /* methodKey(F, "org.sireum.MBox20", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x541D332CDBBD6118L /* methodKey(F, "org.sireum.MBox20", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0xFC651A798034C3BFL /* methodKey(F, "org.sireum.MBox20", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0x64E64CBCDE72FE90L /* methodKey(F, "org.sireum.MBox20", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0xE776FBD4A5200360L /* methodKey(F, "org.sireum.MBox20", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0x028A1127C1D5863BL /* methodKey(F, "org.sireum.MBox20", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0x8DB7C47659DE6202L /* methodKey(F, "org.sireum.MBox20", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0x7B3F1BA3CB5F8D75L /* methodKey(F, "org.sireum.MBox20", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0x59FA8626555F142DL /* methodKey(F, "org.sireum.MBox20", "value9_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value9_=`(o1))
    r.put(0x70E185D24AB5B4EAL /* methodKey(F, "org.sireum.MBox20", "value10_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value10_=`(o1))
    r.put(0xAE621230B0CD531DL /* methodKey(F, "org.sireum.MBox20", "value11_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value11_=`(o1))
    r.put(0x84B9FD7FD29740F8L /* methodKey(F, "org.sireum.MBox20", "value12_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value12_=`(o1))
    r.put(0x0C9E1AA95FE1473BL /* methodKey(F, "org.sireum.MBox20", "value13_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value13_=`(o1))
    r.put(0x95935ED8A6483771L /* methodKey(F, "org.sireum.MBox20", "value14_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value14_=`(o1))
    r.put(0xA37FCFB62E3BEFFEL /* methodKey(F, "org.sireum.MBox20", "value15_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value15_=`(o1))
    r.put(0xEF1CAD835B9F64A9L /* methodKey(F, "org.sireum.MBox20", "value16_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value16_=`(o1))
    r.put(0x8341ED717D341DDEL /* methodKey(F, "org.sireum.MBox20", "value17_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value17_=`(o1))
    r.put(0x926E15E11D90DB10L /* methodKey(F, "org.sireum.MBox20", "value18_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value18_=`(o1))
    r.put(0xF9303072FB7E7F3DL /* methodKey(F, "org.sireum.MBox20", "value19_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value19_=`(o1))
    r.put(0x1DA6142576743FFEL /* methodKey(F, "org.sireum.MBox20", "value20_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox20[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value20_=`(o1))
    r.put(0x04FBFBCEC1BAAA1DL /* methodKey(T, "org.sireum.MBox21", "unapply").value */, _ => (o1: Any) => org.sireum.MBox21.unapply(o1.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20))
      case _ => None()
    })
    r.put(0x9CDBD87ED22F242CL /* methodKey(F, "org.sireum.MBox21", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x9E4B9F2E7037AE36L /* methodKey(F, "org.sireum.MBox21", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0xC0DD8AF42EB1C64CL /* methodKey(F, "org.sireum.MBox21", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0xBD2072EDDAA2AA07L /* methodKey(F, "org.sireum.MBox21", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0x52CE055B98055655L /* methodKey(F, "org.sireum.MBox21", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0xBA64BAE360FB2CBBL /* methodKey(F, "org.sireum.MBox21", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0x244F9E50CB47EA60L /* methodKey(F, "org.sireum.MBox21", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0x0E17DD70B7CE1963L /* methodKey(F, "org.sireum.MBox21", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0x233E30EF8DFEF4BBL /* methodKey(F, "org.sireum.MBox21", "value9_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value9_=`(o1))
    r.put(0x4D38C1BE76CCE7D0L /* methodKey(F, "org.sireum.MBox21", "value10_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value10_=`(o1))
    r.put(0x9E83BB1542F7917EL /* methodKey(F, "org.sireum.MBox21", "value11_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value11_=`(o1))
    r.put(0xE81B93F89C509CBBL /* methodKey(F, "org.sireum.MBox21", "value12_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value12_=`(o1))
    r.put(0x5C098CE2795255E9L /* methodKey(F, "org.sireum.MBox21", "value13_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value13_=`(o1))
    r.put(0x82080F82FA0DB180L /* methodKey(F, "org.sireum.MBox21", "value14_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value14_=`(o1))
    r.put(0x7A1D7B936B6E28A5L /* methodKey(F, "org.sireum.MBox21", "value15_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value15_=`(o1))
    r.put(0x10CCD1F7F2F8DF9DL /* methodKey(F, "org.sireum.MBox21", "value16_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value16_=`(o1))
    r.put(0x1D78A544A7368BE0L /* methodKey(F, "org.sireum.MBox21", "value17_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value17_=`(o1))
    r.put(0x9BD17ACFF0668870L /* methodKey(F, "org.sireum.MBox21", "value18_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value18_=`(o1))
    r.put(0xD023633A40A8892DL /* methodKey(F, "org.sireum.MBox21", "value19_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value19_=`(o1))
    r.put(0x37C95BE61C5BA9A8L /* methodKey(F, "org.sireum.MBox21", "value20_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value20_=`(o1))
    r.put(0xA636C9E7303A26E5L /* methodKey(F, "org.sireum.MBox21", "value21_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox21[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value21_=`(o1))
    r.put(0x12DBC75AFD510AB1L /* methodKey(T, "org.sireum.MBox22", "unapply").value */, _ => (o1: Any) => org.sireum.MBox22.unapply(o1.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20, o21)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20, o21))
      case _ => None()
    })
    r.put(0xFF903F4250F295C7L /* methodKey(F, "org.sireum.MBox22", "value1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value1_=`(o1))
    r.put(0x0D9F5D3CFABC4DB3L /* methodKey(F, "org.sireum.MBox22", "value2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value2_=`(o1))
    r.put(0x24936296286D256AL /* methodKey(F, "org.sireum.MBox22", "value3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value3_=`(o1))
    r.put(0xEA08307CCCFC66F4L /* methodKey(F, "org.sireum.MBox22", "value4_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value4_=`(o1))
    r.put(0x6AE86BA70F89198AL /* methodKey(F, "org.sireum.MBox22", "value5_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value5_=`(o1))
    r.put(0xA0128FC5D077A17AL /* methodKey(F, "org.sireum.MBox22", "value6_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value6_=`(o1))
    r.put(0xC1EB86C62FA682FAL /* methodKey(F, "org.sireum.MBox22", "value7_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value7_=`(o1))
    r.put(0xE1834D894BE83805L /* methodKey(F, "org.sireum.MBox22", "value8_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value8_=`(o1))
    r.put(0x31BDF12637115FBDL /* methodKey(F, "org.sireum.MBox22", "value9_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value9_=`(o1))
    r.put(0x10FA4D207B1A663FL /* methodKey(F, "org.sireum.MBox22", "value10_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value10_=`(o1))
    r.put(0x043DED22133C4837L /* methodKey(F, "org.sireum.MBox22", "value11_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value11_=`(o1))
    r.put(0x59AF02CB5B6FBE86L /* methodKey(F, "org.sireum.MBox22", "value12_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value12_=`(o1))
    r.put(0x147DD616E6B70D21L /* methodKey(F, "org.sireum.MBox22", "value13_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value13_=`(o1))
    r.put(0xF5084307193E7258L /* methodKey(F, "org.sireum.MBox22", "value14_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value14_=`(o1))
    r.put(0xBA2FAFD998115D05L /* methodKey(F, "org.sireum.MBox22", "value15_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value15_=`(o1))
    r.put(0x9E6A698EC97743D6L /* methodKey(F, "org.sireum.MBox22", "value16_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value16_=`(o1))
    r.put(0x2EDE7977AE4C55CBL /* methodKey(F, "org.sireum.MBox22", "value17_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value17_=`(o1))
    r.put(0x833F6CE015E37555L /* methodKey(F, "org.sireum.MBox22", "value18_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value18_=`(o1))
    r.put(0xA05EEB9F935AA3EEL /* methodKey(F, "org.sireum.MBox22", "value19_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value19_=`(o1))
    r.put(0xD6297F381542BF92L /* methodKey(F, "org.sireum.MBox22", "value20_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value20_=`(o1))
    r.put(0x8587197B2FD25C92L /* methodKey(F, "org.sireum.MBox22", "value21_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value21_=`(o1))
    r.put(0x44C002E249EC24A4L /* methodKey(F, "org.sireum.MBox22", "value22_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MBox22[Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any]].`value22_=`(o1))
    r.put(0xAE1BDA7620D87456L /* methodKey(T, "org.sireum.MEither.Left", "apply").value */, _ => (o1: Any) => org.sireum.MEither.Left.apply(o1))
    r.put(0xE9B3AF3490A7C8D6L /* methodKey(T, "org.sireum.MEither.Left", "unapply").value */, _ => (o1: Any) => org.sireum.MEither.Left.unapply(o1.asInstanceOf[org.sireum.MEither.Left[Any, Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x07293F91A03D569BL /* methodKey(T, "org.sireum.MEither.Right", "apply").value */, _ => (o1: Any) => org.sireum.MEither.Right.apply(o1))
    r.put(0x4BFDB2677A12F1AFL /* methodKey(T, "org.sireum.MEither.Right", "unapply").value */, _ => (o1: Any) => org.sireum.MEither.Right.unapply(o1.asInstanceOf[org.sireum.MEither.Right[Any, Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x1D68AB3DDC2149F9L /* methodKey(F, "org.sireum.MJen", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x5757E3A8229B845BL /* methodKey(F, "org.sireum.MJen", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x7FDC27E72E445171L /* methodKey(F, "org.sireum.MJen", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x7C0DD08B18166304L /* methodKey(F, "org.sireum.MJen", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x6FF4F1347F7290CAL /* methodKey(F, "org.sireum.MJen", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].contains(o1))
    r.put(0x4DF2D866B6F591D3L /* methodKey(F, "org.sireum.MJen", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x90BA1557BAC3D921L /* methodKey(F, "org.sireum.MJen", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x135D42CCD7B43473L /* methodKey(F, "org.sireum.MJen", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x935CCB32D63CBDC1L /* methodKey(F, "org.sireum.MJen", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xD187D28E30BC3B1DL /* methodKey(F, "org.sireum.MJen", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xB0146683EC7A0C15L /* methodKey(F, "org.sireum.MJen", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xB818E68F1B1BAB54L /* methodKey(F, "org.sireum.MJen", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xEEE628345643D997L /* methodKey(F, "org.sireum.MJen", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x8E9159B484E12778L /* methodKey(F, "org.sireum.MJen", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x3ADB29EA417F191BL /* methodKey(F, "org.sireum.MJen", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xBB0C6EA5729BE049L /* methodKey(F, "org.sireum.MJen", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x4342B43B5C4B6AA3L /* methodKey(F, "org.sireum.MJen", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x26C89FB00C8B5B4BL /* methodKey(F, "org.sireum.MJen", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x834E0DFA340D1A84L /* methodKey(F, "org.sireum.MJen", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xDB7A0BEA84AAC3F0L /* methodKey(F, "org.sireum.MJen", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x9936DD47C3136BFCL /* methodKey(F, "org.sireum.MJen", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].`++`(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x9CBF401D1965FD82L /* methodKey(F, "org.sireum.MJen", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0xF8ECA94EC0BC2578L /* methodKey(F, "org.sireum.MJen", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x05589D4775A6D726L /* methodKey(T, "org.sireum.MJen.Internal.ISImpl", "apply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.ISImpl.apply(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0xF909F2169D6F94BEL /* methodKey(T, "org.sireum.MJen.Internal.ISImpl", "unapply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.ISImpl.unapply(o1.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x032CE3BEA77AB9B0L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x6B6AB8B334D09E9CL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x4E9664BEA67240EFL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x0146704EB9EA850FL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x69FCD398AB915DE5L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].contains(o1))
    r.put(0x507E41C33F6D745CL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x8B5B3B0EE88904FDL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xECB771C3B566964FL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x63C086A6EA649FEFL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xC0F5B591A5BC2DD7L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x0871BB690918019BL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xB8F35C1D83F4648CL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x0DC8093BA5087A20L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x71AF1887B1CC80B5L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0xF9F700BAFBDF697EL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x48CBE1D00DB85F50L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xC2B1448C2F96519CL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xAB906C28187D9E7FL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xB041C94754E1411EL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x29CB2704A4EADF80L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x94B8BE908890883DL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].`++`(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x3781BAD4F478C936L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x99A67283CFBE0E27L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x84F6AE1C7EDA1E97L /* methodKey(T, "org.sireum.MJen.Internal.MSImpl", "apply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.MSImpl.apply(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x37DAD371250A2160L /* methodKey(T, "org.sireum.MJen.Internal.MSImpl", "unapply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.MSImpl.unapply(o1.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x45B56324A199F666L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xAE29242A44EE39B8L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x9FC8F20D5D9C3E13L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x717B9F3D2CDD7F50L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xC72504274B7D8E68L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].contains(o1))
    r.put(0x78540B9F42704CA1L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x9EFD1A3BB1022CD9L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x6FC0EC54557BC2E9L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xBFF57C0097625E64L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xE833F151253EE926L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x5F75FC09B656CF0CL /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x0A3CB512A36AD59FL /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xACEB43BFAF59D707L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x8E9363C419D17EF0L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x88A2D9098A98204FL /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xAC823DCB7BB9FEFEL /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x6400001D49C4BCF2L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x89C3C089C0968C4DL /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x7EAF6430E747D473L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x6030213F9743229DL /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xCD69825AA8D434C3L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].`++`(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x4D4CD8E67895BB50L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0xDE133E7AACCBBDFEL /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0xF69DE3F744017E3AL /* methodKey(T, "org.sireum.MJen.Internal.MapImpl", "apply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.MapImpl.apply(o1.asInstanceOf[org.sireum.Map[Any, Any]]))
    r.put(0xF95969283346BCBAL /* methodKey(T, "org.sireum.MJen.Internal.MapImpl", "unapply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.MapImpl.unapply(o1.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0xD68CC0DE13291756L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].generate(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x8C406ABCB4C8F9CDL /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].foreach(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0xD4042E67248B9E6DL /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].find(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x647DA97991295675L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].exists(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xB3EDB54C4104617AL /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].contains(o1.asInstanceOf[(Any, Any)]))
    r.put(0x360B6ADDB7F3BB11L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].forall(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x1117A64D6E84487DL /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].countIf(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xFBF67A62871799D5L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].reduce(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0xEEB43EE5FF377A2FL /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].reduceLeft(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0x7F159CB7825B0E41L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].filter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x4E27EE2165647567L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].withFilter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x9F64944BF4352DFBL /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].map(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0x270BE98EC7DDB33EL /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].flatMap(o1.asInstanceOf[(((Any, Any)) => org.sireum.MJen[Any])]))
    r.put(0xADC04E4B0655A295L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].flatten(o1.asInstanceOf[(((Any, Any)) => org.sireum.MJen[Any])]))
    r.put(0x1C703E2D0B412032L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xB682D4D786D9F13AL /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xA63352E02538B296L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].takeWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x14208078508A46E4L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].dropWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x1B8398BF5A743735L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xD58EFE0977636DC0L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xE32C401FEE44685DL /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].`++`(o1.asInstanceOf[org.sireum.MJen[(Any, Any)]]))
    r.put(0x767E5E4476CDA2FEL /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, (Any, Any)]]))
    r.put(0xE0E57FE1584F4BC9L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x53158A8A7AC69B71L /* methodKey(T, "org.sireum.MJen.Internal.HashMapImpl", "apply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.HashMapImpl.apply(o1.asInstanceOf[org.sireum.HashMap[Any, Any]]))
    r.put(0x247118E2F2C9C499L /* methodKey(T, "org.sireum.MJen.Internal.HashMapImpl", "unapply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.HashMapImpl.unapply(o1.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0xA88174ABC846EA80L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].generate(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x8C7216D49BDCA9FCL /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].foreach(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0x7D204ACC732226D5L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].find(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x37634F2D158155B9L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].exists(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xD9700B5FDD1FBFE6L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].contains(o1.asInstanceOf[(Any, Any)]))
    r.put(0xFCC75E9553F96B46L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].forall(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x9F3413A3740C7B2AL /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].countIf(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x44F74FDB185E5F83L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].reduce(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0x37C23C6CCA7DC964L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].reduceLeft(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0xF5AC6F85FDFE2345L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].filter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x86D9C4E2D8237A23L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].withFilter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x75635EDB8BC938ADL /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].map(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0xB51178C60121CF82L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].flatMap(o1.asInstanceOf[(((Any, Any)) => org.sireum.MJen[Any])]))
    r.put(0xEFD553DA32028422L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].flatten(o1.asInstanceOf[(((Any, Any)) => org.sireum.MJen[Any])]))
    r.put(0x4F6FEDF2350259C4L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xA4E18B33B6572DD3L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xB97C94F9204A9638L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].takeWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x37C1391B1E18D689L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].dropWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xC046F31BE5B4C787L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x08B69DA1BEA3A84AL /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xE78DDE1D0CB12B29L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].`++`(o1.asInstanceOf[org.sireum.MJen[(Any, Any)]]))
    r.put(0xADDA902D459832D0L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, (Any, Any)]]))
    r.put(0xAAE8DA7199A48B11L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x3EA3C633194E993BL /* methodKey(T, "org.sireum.MJen.Internal.Filtered", "unapply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.Filtered.unapply(o1.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0xCEBA0C174E8108E3L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x5B16126D8999FF81L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x04F79E066530FF5AL /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xA6D3CFD4AF06C8F2L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xB9FE46916ED78381L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].contains(o1))
    r.put(0xCBF02236C553E1F2L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x5E3BDB1849774C91L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x54BE225175DCA86FL /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x79BA04DAC9BC9660L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xAB625CACB9916707L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xB21F734F104E15C0L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x55D9C61143E54D88L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x5BE2E147E989980CL /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x354362B35DA85EF5L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0xE2B22CAFB076E692L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x57B21287A2A5660DL /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x8F28A6AE84DD1478L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x9E2EBB159AAE9EF0L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xC08A6EEFA7262C14L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x1F5848E26D37842EL /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x52CA7C8D588F9099L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].`++`(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x1F5E0D52912E8FB8L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0xFF13F00BB7F2E722L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x46D2B9309B670192L /* methodKey(T, "org.sireum.MJen.Internal.Mapped", "unapply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.Mapped.unapply(o1.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0xB727537692A5C708L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x951C1485C09334D5L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x6D26EDC9402F7404L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x6E0BC546140D9B21L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xDD07C1C0ECD6EF31L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].contains(o1))
    r.put(0x8C67BE2144BDA1ADL /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x82D8BF8282CD9DAAL /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x670DECDB469CFCF3L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x16BA2FA6093A57EBL /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x76A5A7674DD5A0CBL /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xF01FF38E6E3E64F6L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xB5DD85DD8B204642L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x857AF1693E3B17E3L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x9AAB29C5D7C090E9L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x4189FA7718632EF6L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x9D8DA775BC38FBD3L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xB7FC0358C913E343L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x399D328DFBDB024DL /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x51B31E2510DF69F8L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x5DFD6A063BC44C14L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xC714D6C8281B8E67L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].`++`(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x9C4376AAF2FE2976L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x19CA23FDF439F84EL /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x1AB6CE14DD5BD0FBL /* methodKey(T, "org.sireum.MJen.Internal.FlatMapped", "unapply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.FlatMapped.unapply(o1.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0xA5836E52FE39A747L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x4CBFC777EB905C17L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x9908B502353FF5EAL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x8A13626ED4E9B22DL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xC1615D0E6C023C4EL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].contains(o1))
    r.put(0xB82E43E990210C46L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x3F492F424234210CL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xB937AE9C05376185L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xFAD11F68AAA49B92L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xFFC57223BAA50672L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xA07157293550D58AL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x269BDAFC6C61ABFEL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xC3A5E0726DAF29E3L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x9D87203B63017496L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x790B96A8F3221716L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x72F441DBEADD79CFL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xBD19D514F4D0784BL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x6A9B2D79C8F59237L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x273C72CD90103E4EL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x0BB60073C6B19188L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x299E92C4E36FB51AL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].`++`(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x418919F736BA8F89L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x094D29E0E7C8CEDDL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0xC01F40AF5C8E4E72L /* methodKey(T, "org.sireum.MJen.Internal.Sliced", "unapply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.Sliced.unapply(o1.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]]) match {
      case scala.Some((o0, o1, o2)) => Some((o0, o1, o2))
      case _ => None()
    })
    r.put(0x9C89C731FC80A7FDL /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x90E52F056A570C17L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x2EA541622C19627AL /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x332DACC6D34D5BE4L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x74795DD79F866B86L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].contains(o1))
    r.put(0x3E2584D3DB07E034L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xA7F99FD361C05500L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x2C64FB26875F82C2L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xF6BD1345199FA185L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xA7D72469E0315D9BL /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x5EEA5FBFE6DF98E4L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x97DEFC1BEF572057L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x17906C5D3FF9AAFDL /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0xDF0A240EF847149DL /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0xE3C583C557833526L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x50A44585EACC1103L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xED06C0EECF9119F0L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xAB55C8183E4E70B9L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xA36B35900319CE5BL /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x4D39601484001DBEL /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xECA1D7A4CB77C831L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].`++`(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x1BA3FDD7DB8A9F9FL /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x8CFA2E8911E546D9L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x03F0895E774A6B1FL /* methodKey(T, "org.sireum.MJen.Internal.TakeWhile", "unapply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.TakeWhile.unapply(o1.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x506B61EB25789625L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x61DE0175AC1EB7BAL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x0570AD1B0695EB2FL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x5A3C626722B8880DL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x525F074C813DDB2BL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].contains(o1))
    r.put(0xB6B3924B2FA183C5L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xD4B05BF8F891D24CL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x2E1D478EDA652834L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x2A0B65E14D8333D7L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xBFA8E6BD1FAF4D60L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x00C980454A206617L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x50A3850F32B27CE6L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xE39061246AE6579AL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x82607C853BEAF13DL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0xD3456EB33E351576L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x508635A1DE2A4DE4L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x7D7BB13D79ED391BL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x359E1868A5085A30L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xB12A9F5007965226L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x433A08CBC04D674FL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x43253C70D5A1013CL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].`++`(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x288E64F501B8DFF7L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0xF446CEE9672EC329L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x4214D7BD7A92B593L /* methodKey(T, "org.sireum.MJen.Internal.DropWhile", "unapply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.DropWhile.unapply(o1.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x90E03A8561991A81L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x6A119623DEB06CA7L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x3AC4EE1CE7CD5CB0L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x3DF301F459073450L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xD1B79D02132FBE00L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].contains(o1))
    r.put(0x651EECF803DD423DL /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xEE166286ED855931L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x5C18285D153E6DC9L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x3F53BDEA02C51C95L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xA1114CF379124C91L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x974D5D039537C6F0L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x5688DB0F04BAA65CL /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xEFF9A50435377B5EL /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0xC6AE9DF145797BD9L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x168AC40DCF49B69CL /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x95C10E3E2BC55EBEL /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xC52ED986B63A8942L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xB5E40F29EBD0FDBDL /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x84FF2B22239B7A56L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xD3800A4267A81D06L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x3C4A562F5F0F82E1L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].`++`(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xFD9735D8CEF5BAA8L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x8022DEDA87C4BEC8L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x619FFF6FCC290B35L /* methodKey(T, "org.sireum.MJen.Internal.ZipWithIndexed", "apply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.ZipWithIndexed.apply(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x1926C09AD919F5E9L /* methodKey(T, "org.sireum.MJen.Internal.ZipWithIndexed", "unapply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.ZipWithIndexed.unapply(o1.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0xBB468AB194D617A6L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].generate(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0x377624A3A4911036L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].foreach(o1.asInstanceOf[(((Any, org.sireum.Z)) => Any)]))
    r.put(0x803E965492C6666AL /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].find(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0xFAACB7EADF0B3F37L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].exists(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0xCB3C0F54A89D0998L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].contains(o1.asInstanceOf[(Any, org.sireum.Z)]))
    r.put(0x681DD2910A45724EL /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].forall(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0x6527C77178D85D87L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].countIf(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0xC0639B2824E292D8L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].reduce(o1.asInstanceOf[(((Any, org.sireum.Z), (Any, org.sireum.Z)) => (Any, org.sireum.Z))]))
    r.put(0xE02A046420DC48A6L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].reduceLeft(o1.asInstanceOf[(((Any, org.sireum.Z), (Any, org.sireum.Z)) => (Any, org.sireum.Z))]))
    r.put(0x9FA3EDF29F624D36L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].filter(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0x5661E49DEC060194L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].withFilter(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0xAD8F1B4779095112L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].map(o1.asInstanceOf[(((Any, org.sireum.Z)) => Any)]))
    r.put(0xE3B0F88206C8FCC5L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].flatMap(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.MJen[Any])]))
    r.put(0x44C78DB98B71C454L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].flatten(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.MJen[Any])]))
    r.put(0xC66BE8B74DA8B281L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x0EF556E422659A34L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xC361C0DCC0384309L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].takeWhile(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0x6D41E8176CC7DFACL /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].dropWhile(o1.asInstanceOf[(((Any, org.sireum.Z)) => org.sireum.B)]))
    r.put(0x9BB6445E87F33FD5L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xE48879ED6D8A4E65L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x0832674DE05FE910L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].`++`(o1.asInstanceOf[org.sireum.MJen[(Any, org.sireum.Z)]]))
    r.put(0xBA492886985987ACL /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, (Any, org.sireum.Z)]]))
    r.put(0xD86C1507F2DB4E35L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x43C71D655547DE8AL /* methodKey(T, "org.sireum.MJen.Internal.Zipped", "unapply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.Zipped.unapply(o1.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x7018654D62541CBEL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].generate(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x27D22B74F64F021EL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].foreach(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0x40311B391060F755L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].find(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xDDA2CEAA044CC7AFL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].exists(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x79B49D0326B3D84DL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].contains(o1.asInstanceOf[(Any, Any)]))
    r.put(0x4D8E7FE47B40DA93L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].forall(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x443700E84B730A84L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].countIf(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xA3E70ED1668E5F27L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].reduce(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0x97B7B500E6CE5AE8L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].reduceLeft(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0x25B8DD86D50B4B99L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].filter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xD2E51D5DB9A1192CL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].withFilter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xC0B84285C53B25C6L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].map(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0x24E8BA15C317BCDBL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].flatMap(o1.asInstanceOf[(((Any, Any)) => org.sireum.MJen[Any])]))
    r.put(0x5AED13A877A6DC32L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].flatten(o1.asInstanceOf[(((Any, Any)) => org.sireum.MJen[Any])]))
    r.put(0x631310A62B9577A0L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xDDA1412E92760940L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x2039EAD3A4278ADDL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].takeWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x299D4D30C2B75B8FL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].dropWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x4FAB5EBD336AADA4L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xE9B47F2520872180L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x7DD992C29009CE97L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].`++`(o1.asInstanceOf[org.sireum.MJen[(Any, Any)]]))
    r.put(0x7DAC448A5283C9E3L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, (Any, Any)]]))
    r.put(0xD317ED7C1DDCCDBFL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x115E3F9A95B3A6D6L /* methodKey(T, "org.sireum.MJen.Internal.Concat", "unapply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.Concat.unapply(o1.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x5A0063942B613B50L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x7343F5A08E1458AEL /* methodKey(F, "org.sireum.MJen.Internal.Concat", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x3D108DFE66489EA8L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xE73EAA7108510568L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x06724A5BBD4CBA90L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].contains(o1))
    r.put(0x926FE282D706E1D3L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xBA730233EB264715L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x8E646687C45FCF21L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x1823EA48E38762FAL /* methodKey(F, "org.sireum.MJen.Internal.Concat", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x49E831284A55B8B4L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xB6F6E3012750A9CAL /* methodKey(F, "org.sireum.MJen.Internal.Concat", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x696333B199B90212L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xE4E8CC123DE62ABFL /* methodKey(F, "org.sireum.MJen.Internal.Concat", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x7D59EF57B61BC973L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0xE23DC01EF155E766L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xB1964C376CE4F378L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xCCBFFD7B833BD074L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xDCB52D9D3483F7EFL /* methodKey(F, "org.sireum.MJen.Internal.Concat", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x1C7E58D05A03D4D1L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x684D9C3CBCCEE5C8L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x9DBCB05C38D0B04FL /* methodKey(F, "org.sireum.MJen.Internal.Concat", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].`++`(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x2D6A2A638CECC918L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x6AF424D58F210E7FL /* methodKey(F, "org.sireum.MJen.Internal.Concat", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0xD80C64B05A3A7AF2L /* methodKey(T, "org.sireum.MJen.Internal.Product", "unapply").value */, _ => (o1: Any) => org.sireum.MJen.Internal.Product.unapply(o1.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0x1289BDDB0AAE82B4L /* methodKey(F, "org.sireum.MJen.Internal.Product", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].generate(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xC4A7776E8D70DDCAL /* methodKey(F, "org.sireum.MJen.Internal.Product", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].foreach(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0x836EF7790760C579L /* methodKey(F, "org.sireum.MJen.Internal.Product", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].find(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x00C4DAB5C3E21339L /* methodKey(F, "org.sireum.MJen.Internal.Product", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].exists(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xC0DC62D4E1F1B826L /* methodKey(F, "org.sireum.MJen.Internal.Product", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].contains(o1.asInstanceOf[(Any, Any)]))
    r.put(0xD8B02407E74F9596L /* methodKey(F, "org.sireum.MJen.Internal.Product", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].forall(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x327DD8BCA193747CL /* methodKey(F, "org.sireum.MJen.Internal.Product", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].countIf(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x54155B4D24B7C37DL /* methodKey(F, "org.sireum.MJen.Internal.Product", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].reduce(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0xFCB0C9B9A5CD52CCL /* methodKey(F, "org.sireum.MJen.Internal.Product", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].reduceLeft(o1.asInstanceOf[(((Any, Any), (Any, Any)) => (Any, Any))]))
    r.put(0x9174316302C7A7E4L /* methodKey(F, "org.sireum.MJen.Internal.Product", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].filter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x7DB21D01AA262F96L /* methodKey(F, "org.sireum.MJen.Internal.Product", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].withFilter(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x57061255F469E3BFL /* methodKey(F, "org.sireum.MJen.Internal.Product", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].map(o1.asInstanceOf[(((Any, Any)) => Any)]))
    r.put(0xEB58C56F30B6B20FL /* methodKey(F, "org.sireum.MJen.Internal.Product", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].flatMap(o1.asInstanceOf[(((Any, Any)) => org.sireum.MJen[Any])]))
    r.put(0xAB98E58AEF7FDD62L /* methodKey(F, "org.sireum.MJen.Internal.Product", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].flatten(o1.asInstanceOf[(((Any, Any)) => org.sireum.MJen[Any])]))
    r.put(0x0D6B8E257282F90DL /* methodKey(F, "org.sireum.MJen.Internal.Product", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x9261791774A13D3DL /* methodKey(F, "org.sireum.MJen.Internal.Product", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xD9671B0515E7D3B4L /* methodKey(F, "org.sireum.MJen.Internal.Product", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].takeWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0x1A8DF62D518A3F06L /* methodKey(F, "org.sireum.MJen.Internal.Product", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].dropWhile(o1.asInstanceOf[(((Any, Any)) => org.sireum.B)]))
    r.put(0xD8EBC68DA0BF34B8L /* methodKey(F, "org.sireum.MJen.Internal.Product", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x91BFC1F5DAAD96B2L /* methodKey(F, "org.sireum.MJen.Internal.Product", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xD7FD9DCBBD18949CL /* methodKey(F, "org.sireum.MJen.Internal.Product", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].`++`(o1.asInstanceOf[org.sireum.MJen[(Any, Any)]]))
    r.put(0xFE287A837C66F7DEL /* methodKey(F, "org.sireum.MJen.Internal.Product", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, (Any, Any)]]))
    r.put(0x3DEC5CFF90F17A3AL /* methodKey(F, "org.sireum.MJen.Internal.Product", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0xE6F41B6AE7B807BEL /* methodKey(F, "org.sireum.MOption", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MOption[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x4E20AE2185B74238L /* methodKey(F, "org.sireum.MOption", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MOption[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.MOption[Any])]))
    r.put(0xBA9F77CAF4AF9F7EL /* methodKey(F, "org.sireum.MOption", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MOption[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x0EB321736C08CF5DL /* methodKey(F, "org.sireum.MOption", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MOption[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xC95D4FEBFC0EAFD9L /* methodKey(F, "org.sireum.MOption", "getOrElse").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MOption[Any]].getOrElse(o1.asInstanceOf[(() => Any)]()))
    r.put(0x69B3F1A1FF988069L /* methodKey(F, "org.sireum.MOption", "getOrElseEager").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MOption[Any]].getOrElseEager(o1))
    r.put(0xBC0A35C7EFCA97E2L /* methodKey(F, "org.sireum.MOption", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MOption[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xCB6F5E1D3EEB301DL /* methodKey(T, "org.sireum.MNone", "unapply").value */, _ => (o1: Any) => if (org.sireum.MNone.unapply(o1.asInstanceOf[org.sireum.MNone[Any]])) Some(T) else None())
    r.put(0x92C215D5382AB3B1L /* methodKey(F, "org.sireum.MNone", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MNone[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x640A2B089CB5821EL /* methodKey(F, "org.sireum.MNone", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MNone[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.MOption[Any])]))
    r.put(0x352CEC78F096724BL /* methodKey(F, "org.sireum.MNone", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MNone[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xBC0797965AA9DE97L /* methodKey(F, "org.sireum.MNone", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MNone[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x34227019476FC35AL /* methodKey(F, "org.sireum.MNone", "getOrElse").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MNone[Any]].getOrElse(o1.asInstanceOf[(() => Any)]()))
    r.put(0x2C1FE944FE5BD975L /* methodKey(F, "org.sireum.MNone", "getOrElseEager").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MNone[Any]].getOrElseEager(o1))
    r.put(0xE300858ECDE1EEAEL /* methodKey(F, "org.sireum.MNone", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MNone[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xA1B9D317BCABEA11L /* methodKey(T, "org.sireum.MSome", "apply").value */, _ => (o1: Any) => org.sireum.MSome.apply(o1))
    r.put(0xC21F31D858242B47L /* methodKey(T, "org.sireum.MSome", "unapply").value */, _ => (o1: Any) => org.sireum.MSome.unapply(o1.asInstanceOf[org.sireum.MSome[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0xBF196EBAA11ACE26L /* methodKey(F, "org.sireum.MSome", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MSome[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x0B709DA7585A2A16L /* methodKey(F, "org.sireum.MSome", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MSome[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.MOption[Any])]))
    r.put(0x26C7824554596BCEL /* methodKey(F, "org.sireum.MSome", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MSome[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xA8A13AFC476E3B25L /* methodKey(F, "org.sireum.MSome", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MSome[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xB255A5F713274A38L /* methodKey(F, "org.sireum.MSome", "getOrElse").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MSome[Any]].getOrElse(o1.asInstanceOf[(() => Any)]()))
    r.put(0xD0C64824CE838D44L /* methodKey(F, "org.sireum.MSome", "getOrElseEager").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MSome[Any]].getOrElseEager(o1))
    r.put(0x5A733A62DE4FE625L /* methodKey(F, "org.sireum.MSome", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.MSome[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x468370A0081F2479L /* methodKey(T, "org.sireum.Map", "apply").value */, _ => (o1: Any) => org.sireum.Map.apply(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]]))
    r.put(0x7A38FE2715A3348AL /* methodKey(T, "org.sireum.Map", "unapply").value */, _ => (o1: Any) => org.sireum.Map.unapply(o1.asInstanceOf[org.sireum.Map[Any, Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x26F2C27D1303CD41L /* methodKey(F, "org.sireum.Map", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].`+`(o1.asInstanceOf[(Any, Any)]))
    r.put(0x7C52AFE6925BE5A8L /* methodKey(F, "org.sireum.Map", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].`++`(o1.asInstanceOf[org.sireum.IS[Any, (Any, Any)]]))
    r.put(0x9CFEA110524F9C9AL /* methodKey(F, "org.sireum.Map", "get").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].get(o1))
    r.put(0xAE9DA24B2F75EFA3L /* methodKey(F, "org.sireum.Map", "entry").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].entry(o1))
    r.put(0xBA7079B5DC85A0A8L /* methodKey(F, "org.sireum.Map", "indexOf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].indexOf(o1))
    r.put(0xD0F78B942CB39657L /* methodKey(F, "org.sireum.Map", "--").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].`--`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0xCD5FA8E915D553B6L /* methodKey(F, "org.sireum.Map", "-").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].`-`(o1.asInstanceOf[(Any, Any)]))
    r.put(0x594F8299064E2ECEL /* methodKey(F, "org.sireum.Map", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].contains(o1))
    r.put(0x2FBFA327B8B2BDC4L /* methodKey(F, "org.sireum.Map", "isEqual").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].isEqual(o1.asInstanceOf[org.sireum.Map[Any, Any]]))
    r.put(0x4CB0DAEE0EA3E967L /* methodKey(F, "org.sireum.ObjPrinter", "write").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].write(o1.asInstanceOf[org.sireum.ST]))
    r.put(0xB70AFF4604121695L /* methodKey(F, "org.sireum.ObjPrinter", "printMessage").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printMessage(o1.asInstanceOf[org.sireum.message.Message]))
    r.put(0xC1346C9054C10176L /* methodKey(F, "org.sireum.ObjPrinter", "printPosition").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printPosition(o1.asInstanceOf[org.sireum.message.Position]))
    r.put(0x2AF33D7B8D5EE4A5L /* methodKey(F, "org.sireum.ObjPrinter", "printDocInfo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printDocInfo(o1.asInstanceOf[org.sireum.message.DocInfo]))
    r.put(0x7CACBDF347F52EBCL /* methodKey(F, "org.sireum.Option", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Option[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x19C072D2B4735C98L /* methodKey(F, "org.sireum.Option", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Option[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.Option[Any])]))
    r.put(0x02E057BE4C7561CCL /* methodKey(F, "org.sireum.Option", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Option[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x59D079030ADBCFD1L /* methodKey(F, "org.sireum.Option", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Option[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x18CE0DE7F2ECB384L /* methodKey(F, "org.sireum.Option", "getOrElse").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Option[Any]].getOrElse(o1.asInstanceOf[(() => Any)]()))
    r.put(0xA05EE525056BE57FL /* methodKey(F, "org.sireum.Option", "getOrElseEager").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Option[Any]].getOrElseEager(o1))
    r.put(0xFDA69505F916490BL /* methodKey(F, "org.sireum.Option", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Option[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xC86ADFE866BFED41L /* methodKey(T, "org.sireum.None", "unapply").value */, _ => (o1: Any) => if (org.sireum.None.unapply(o1.asInstanceOf[org.sireum.None[Any]])) Some(T) else None())
    r.put(0x1FB380EA28D14879L /* methodKey(F, "org.sireum.None", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.None[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x46F8397E30915E21L /* methodKey(F, "org.sireum.None", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.None[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.Option[Any])]))
    r.put(0x0A617B941A326281L /* methodKey(F, "org.sireum.None", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.None[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xA9FD7A31D5DBD9B9L /* methodKey(F, "org.sireum.None", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.None[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x8107BDEDDF2A8523L /* methodKey(F, "org.sireum.None", "getOrElse").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.None[Any]].getOrElse(o1.asInstanceOf[(() => Any)]()))
    r.put(0x1A54FDC06D66AE6BL /* methodKey(F, "org.sireum.None", "getOrElseEager").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.None[Any]].getOrElseEager(o1))
    r.put(0x689452DE36656F3FL /* methodKey(F, "org.sireum.None", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.None[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xBD9F34035158E169L /* methodKey(T, "org.sireum.Some", "apply").value */, _ => (o1: Any) => org.sireum.Some.apply(o1))
    r.put(0x98AD011FFEFD9B75L /* methodKey(T, "org.sireum.Some", "unapply").value */, _ => (o1: Any) => org.sireum.Some.unapply(o1.asInstanceOf[org.sireum.Some[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x0B5B8E5079082393L /* methodKey(F, "org.sireum.Some", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Some[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0xEC0EEDC4D5E4B4F3L /* methodKey(F, "org.sireum.Some", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Some[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.Option[Any])]))
    r.put(0x59130C9FC99CC80DL /* methodKey(F, "org.sireum.Some", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Some[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x85AAE4082F2DF842L /* methodKey(F, "org.sireum.Some", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Some[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x70AE9E597EF2A8D2L /* methodKey(F, "org.sireum.Some", "getOrElse").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Some[Any]].getOrElse(o1.asInstanceOf[(() => Any)]()))
    r.put(0x522A37BAE32FD80CL /* methodKey(F, "org.sireum.Some", "getOrElseEager").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Some[Any]].getOrElseEager(o1))
    r.put(0x7BA2E78EE54B8DB2L /* methodKey(F, "org.sireum.Some", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Some[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x857D155E156245FFL /* methodKey(F, "org.sireum.OsProto.Proc", "commands").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].commands(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]]))
    r.put(0x69C5688A1107EBE2L /* methodKey(F, "org.sireum.OsProto.Proc", "at").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].at(o1.asInstanceOf[org.sireum.OsProto.Path]))
    r.put(0xEC05B0247CCCDE85L /* methodKey(F, "org.sireum.OsProto.Proc", "env").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].env(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (org.sireum.String, org.sireum.String)]]))
    r.put(0x6AFDF88BA74FCE37L /* methodKey(F, "org.sireum.OsProto.Proc", "input").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].input(o1.asInstanceOf[org.sireum.String]))
    r.put(0x2345F5D064D830D7L /* methodKey(F, "org.sireum.OsProto.Proc", "timeout").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].timeout(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x7FB1808CC2FD0113L /* methodKey(F, "org.sireum.OsProto.Proc", "outLineAction").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].outLineAction(o1.asInstanceOf[((org.sireum.String) => org.sireum.B)]))
    r.put(0x4D46231C2DA56C66L /* methodKey(F, "org.sireum.OsProto.Proc", "errLineAction").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.OsProto.Proc].errLineAction(o1.asInstanceOf[((org.sireum.String) => org.sireum.B)]))
    r.put(0x3DFB281C9AF3EC49L /* methodKey(T, "org.sireum.Poset", "unapply").value */, _ => (o1: Any) => org.sireum.Poset.unapply(o1.asInstanceOf[org.sireum.Poset[Any]]) match {
      case scala.Some((o0, o1, o2, o3)) => Some((o0, o1, o2, o3))
      case _ => None()
    })
    r.put(0xE3FA837A659F98A5L /* methodKey(F, "org.sireum.Poset", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].`++`(o1.asInstanceOf[org.sireum.Poset[Any]]))
    r.put(0x03D45CAF69E30029L /* methodKey(F, "org.sireum.Poset", "isEqual").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].isEqual(o1.asInstanceOf[org.sireum.Poset[Any]]))
    r.put(0xA4D8676D27566079L /* methodKey(F, "org.sireum.Poset", "addNode").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].addNode(o1))
    r.put(0xCFFFCFB2137BF9F1L /* methodKey(F, "org.sireum.Poset", "childrenOf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].childrenOf(o1))
    r.put(0x4F675EB7A4528C95L /* methodKey(F, "org.sireum.Poset", "parentsOf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].parentsOf(o1))
    r.put(0xF7B63DD9AA0CE748L /* methodKey(F, "org.sireum.Poset", "ancestorsOf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].ancestorsOf(o1))
    r.put(0xA426D215C89FFD43L /* methodKey(F, "org.sireum.Poset", "lub").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].lub(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]]))
    r.put(0xA204C23FF3DC12EAL /* methodKey(F, "org.sireum.Poset", "descendantsOf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].descendantsOf(o1))
    r.put(0xE07C7BE37C58D68AL /* methodKey(F, "org.sireum.Poset", "glb").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].glb(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]]))
    r.put(0x352C342682A8DB6DL /* methodKey(F, "org.sireum.Poset", "toST").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].toST(o1.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0x27003546383F6868L /* methodKey(F, "org.sireum.Random.Gen.TestRunner", "toCompactJson").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen.TestRunner[Any]].toCompactJson(o1))
    r.put(0x0806B2EEFB3E2DB5L /* methodKey(F, "org.sireum.Random.Gen.TestRunner", "fromJson").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen.TestRunner[Any]].fromJson(o1.asInstanceOf[org.sireum.String]))
    r.put(0x14138F285F620224L /* methodKey(F, "org.sireum.Random.Gen.TestRunner", "test").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen.TestRunner[Any]].test(o1))
    r.put(0x9E14DD15AA6DD391L /* methodKey(T, "org.sireum.Random.Gen64Impl", "apply").value */, _ => (o1: Any) => org.sireum.Random.Gen64Impl.apply(o1.asInstanceOf[org.sireum.Random.Impl.Xoshiro256]))
    r.put(0x782B3A1B2A51D090L /* methodKey(T, "org.sireum.Random.Gen64Impl", "unapply").value */, _ => (o1: Any) => org.sireum.Random.Gen64Impl.unapply(o1.asInstanceOf[org.sireum.Random.Gen64Impl]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0xF62718D24F94901CL /* methodKey(T, "org.sireum.Random.Impl.SplitMix64", "apply").value */, _ => (o1: Any) => org.sireum.Random.Impl.SplitMix64.apply(o1.asInstanceOf[org.sireum.U64]))
    r.put(0xA09F873F77C65EA1L /* methodKey(T, "org.sireum.Random.Impl.SplitMix64", "unapply").value */, _ => (o1: Any) => org.sireum.Random.Impl.SplitMix64.unapply(o1.asInstanceOf[org.sireum.Random.Impl.SplitMix64]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0xC4C231B4E94FF71BL /* methodKey(F, "org.sireum.Random.Impl.SplitMix64", "seed_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.SplitMix64].`seed_=`(o1.asInstanceOf[org.sireum.U64]))
    r.put(0x304020669424EEFDL /* methodKey(T, "org.sireum.Random.Impl.Xoshiro256", "unapply").value */, _ => (o1: Any) => org.sireum.Random.Impl.Xoshiro256.unapply(o1.asInstanceOf[org.sireum.Random.Impl.Xoshiro256]) match {
      case scala.Some((o0, o1, o2, o3)) => Some((o0, o1, o2, o3))
      case _ => None()
    })
    r.put(0xF8D3FB64DAF5AD87L /* methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed0_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoshiro256].`seed0_=`(o1.asInstanceOf[org.sireum.U64]))
    r.put(0x07D1D9A90EA2D934L /* methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoshiro256].`seed1_=`(o1.asInstanceOf[org.sireum.U64]))
    r.put(0x28A7527EDD2F9BE9L /* methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoshiro256].`seed2_=`(o1.asInstanceOf[org.sireum.U64]))
    r.put(0xB5670D87C026461CL /* methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoshiro256].`seed3_=`(o1.asInstanceOf[org.sireum.U64]))
    r.put(0xD381A35FD1EB9321L /* methodKey(T, "org.sireum.Random.Impl.Xoroshiro128", "unapply").value */, _ => (o1: Any) => org.sireum.Random.Impl.Xoroshiro128.unapply(o1.asInstanceOf[org.sireum.Random.Impl.Xoroshiro128]) match {
      case scala.Some((o0, o1, o2, o3)) => Some((o0, o1, o2, o3))
      case _ => None()
    })
    r.put(0x655DAF2C48EC4800L /* methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed0_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoroshiro128].`seed0_=`(o1.asInstanceOf[org.sireum.U32]))
    r.put(0xCB599029CD09925BL /* methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed1_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoroshiro128].`seed1_=`(o1.asInstanceOf[org.sireum.U32]))
    r.put(0xB210CCE0FBABB843L /* methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed2_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoroshiro128].`seed2_=`(o1.asInstanceOf[org.sireum.U32]))
    r.put(0xD96CC7FD73F4FD99L /* methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed3_=").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Impl.Xoroshiro128].`seed3_=`(o1.asInstanceOf[org.sireum.U32]))
    r.put(0x816E386F7EF2AA76L /* methodKey(T, "org.sireum.Set", "apply").value */, _ => (o1: Any) => org.sireum.Set.apply(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]]))
    r.put(0xD9FFB62902606651L /* methodKey(T, "org.sireum.Set", "unapply").value */, _ => (o1: Any) => org.sireum.Set.unapply(o1.asInstanceOf[org.sireum.Set[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x9F9D5CAFC37A2F2DL /* methodKey(F, "org.sireum.Set", "+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].`+`(o1))
    r.put(0x810EBF5FEFAADACBL /* methodKey(F, "org.sireum.Set", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].`++`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x43B496D45163D222L /* methodKey(F, "org.sireum.Set", "-").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].`-`(o1))
    r.put(0x5EB341B1E1672C32L /* methodKey(F, "org.sireum.Set", "--").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].`--`(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x7C9D7DF7C465A480L /* methodKey(F, "org.sireum.Set", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].contains(o1))
    r.put(0x78092E8F1272CD27L /* methodKey(F, "org.sireum.Set", "union").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].union(o1.asInstanceOf[org.sireum.Set[Any]]))
    r.put(0x2A9C4141CE6A0336L /* methodKey(F, "org.sireum.Set", "\u222A").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].`\u222A`(o1.asInstanceOf[org.sireum.Set[Any]]))
    r.put(0x42C7B8BB94061C4EL /* methodKey(F, "org.sireum.Set", "intersect").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].intersect(o1.asInstanceOf[org.sireum.Set[Any]]))
    r.put(0xCB8B46C0B64B6D7CL /* methodKey(F, "org.sireum.Set", "\u2229").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].`\u2229`(o1.asInstanceOf[org.sireum.Set[Any]]))
    r.put(0x50FBC26E798B4037L /* methodKey(F, "org.sireum.Set", "\\").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].`\\`(o1.asInstanceOf[org.sireum.Set[Any]]))
    r.put(0x12AE37174837461EL /* methodKey(F, "org.sireum.Set", "isEqual").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].isEqual(o1.asInstanceOf[org.sireum.Set[Any]]))
    r.put(0x7E2652B8DCC3DF6DL /* methodKey(F, "org.sireum.Set", "indexOf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Set[Any]].indexOf(o1))
    r.put(0x3BC0F29752B98FFDL /* methodKey(T, "org.sireum.Stack", "apply").value */, _ => (o1: Any) => org.sireum.Stack.apply(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]]))
    r.put(0x3B6DF5F952207A60L /* methodKey(T, "org.sireum.Stack", "unapply").value */, _ => (o1: Any) => org.sireum.Stack.unapply(o1.asInstanceOf[org.sireum.Stack[Any]]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x1EB542E68906C468L /* methodKey(F, "org.sireum.Stack", "push").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Stack[Any]].push(o1))
    r.put(0xC9663614762AE5E9L /* methodKey(T, "org.sireum.UnionFind", "unapply").value */, _ => (o1: Any) => org.sireum.UnionFind.unapply(o1.asInstanceOf[org.sireum.UnionFind[Any]]) match {
      case scala.Some((o0, o1, o2, o3)) => Some((o0, o1, o2, o3))
      case _ => None()
    })
    r.put(0x607484EA4698172FL /* methodKey(F, "org.sireum.UnionFind", "isEqual").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.UnionFind[Any]].isEqual(o1.asInstanceOf[org.sireum.UnionFind[Any]]))
    r.put(0x5FE84FDCB63A310BL /* methodKey(F, "org.sireum.UnionFind", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.UnionFind[Any]].find(o1))
    r.put(0x4E66F5FB9A61B820L /* methodKey(F, "org.sireum.UnionFind", "findCompress").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.UnionFind[Any]].findCompress(o1))
    r.put(0xEE5BE955EDEEF8CDL /* methodKey(F, "org.sireum.UnionFind", "toST").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.UnionFind[Any]].toST(o1.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0x3807C91400AA6467L /* methodKey(T, "org.sireum.CoursierFileInfo", "unapply").value */, _ => (o1: Any) => org.sireum.CoursierFileInfo.unapply(o1.asInstanceOf[org.sireum.CoursierFileInfo]) match {
      case scala.Some((o0, o1, o2, o3)) => Some((o0, o1, o2, o3))
      case _ => None()
    })
    r.put(0x566D579F4EE7C313L /* methodKey(T, "org.sireum.Coursier.Proxy", "unapply").value */, _ => (o1: Any) => org.sireum.Coursier.Proxy.unapply(o1.asInstanceOf[org.sireum.Coursier.Proxy]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5)) => Some((o0, o1, o2, o3, o4, o5))
      case _ => None()
    })
    r.put(0x23189B2D18BDD3F8L /* methodKey(T, "org.sireum.GitHub.Repository", "unapply").value */, _ => (o1: Any) => org.sireum.GitHub.Repository.unapply(o1.asInstanceOf[org.sireum.GitHub.Repository]) match {
      case scala.Some((o0, o1, o2)) => Some((o0, o1, o2))
      case _ => None()
    })
    r.put(0x7540A8A77D21168AL /* methodKey(T, "org.sireum.GitHub.Release", "unapply").value */, _ => (o1: Any) => org.sireum.GitHub.Release.unapply(o1.asInstanceOf[org.sireum.GitHub.Release]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9))
      case _ => None()
    })
    r.put(0x92B4F78506AB8A3AL /* methodKey(T, "org.sireum.GitHub.Asset", "unapply").value */, _ => (o1: Any) => org.sireum.GitHub.Asset.unapply(o1.asInstanceOf[org.sireum.GitHub.Asset]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8))
      case _ => None()
    })
    r.put(0x6834726706939AF3L /* methodKey(T, "org.sireum.Init.Plugin", "unapply").value */, _ => (o1: Any) => org.sireum.Init.Plugin.unapply(o1.asInstanceOf[org.sireum.Init.Plugin]) match {
      case scala.Some((o0, o1, o2, o3)) => Some((o0, o1, o2, o3))
      case _ => None()
    })
    r.put(0xD893E6662D1AC24FL /* methodKey(T, "org.sireum.Init", "unapply").value */, _ => (o1: Any) => org.sireum.Init.unapply(o1.asInstanceOf[org.sireum.Init]) match {
      case scala.Some((o0, o1, o2)) => Some((o0, o1, o2))
      case _ => None()
    })
    r.put(0x394D50D66209F24FL /* methodKey(F, "org.sireum.Init", "platform").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Init].platform(o1.asInstanceOf[org.sireum.Os.Kind.Type]))
    r.put(0x820EB4449968AFCDL /* methodKey(F, "org.sireum.Init", "installJava").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Init].installJava(o1.asInstanceOf[org.sireum.Map[org.sireum.String, org.sireum.String]]))
    r.put(0x6AEDD8A12084FD26L /* methodKey(F, "org.sireum.Init", "installMill").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Init].installMill(o1.asInstanceOf[org.sireum.B]))
    r.put(0x885C121A873E93F3L /* methodKey(F, "org.sireum.Init", "ideaSandbox").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Init].ideaSandbox(o1.asInstanceOf[org.sireum.B]))
    r.put(0xB42ABE770C9ECC42L /* methodKey(F, "org.sireum.Init", "init").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Init].init(o1.asInstanceOf[org.sireum.B]))
    r.put(0xE9DC95EFDFAF94C7L /* methodKey(T, "org.sireum.Os.Path.Impl", "apply").value */, _ => (o1: Any) => org.sireum.Os.Path.Impl.apply(o1.asInstanceOf[org.sireum.String]))
    r.put(0x16BF72C5C67B6932L /* methodKey(T, "org.sireum.Os.Path.Impl", "unapply").value */, _ => (o1: Any) => org.sireum.Os.Path.Impl.unapply(o1.asInstanceOf[org.sireum.Os.Path.Impl]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0xB6C51877F05C73AAL /* methodKey(F, "org.sireum.Os.Path.Impl", "/").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].`/`(o1.asInstanceOf[org.sireum.String]))
    r.put(0x13D04B1E09D02C80L /* methodKey(F, "org.sireum.Os.Path.Impl", "/+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].`/+`(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]]))
    r.put(0x5B8E12A1219BB443L /* methodKey(F, "org.sireum.Os.Path.Impl", "call").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].call(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]]))
    r.put(0xA43B7339042D0552L /* methodKey(F, "org.sireum.Os.Path.Impl", "chmod").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].chmod(o1.asInstanceOf[org.sireum.String]))
    r.put(0x1D1148B386BCC472L /* methodKey(F, "org.sireum.Os.Path.Impl", "chmodAll").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].chmodAll(o1.asInstanceOf[org.sireum.String]))
    r.put(0x44C1828086D47C7CL /* methodKey(F, "org.sireum.Os.Path.Impl", "combineFrom").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].combineFrom(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.Os.Path]]))
    r.put(0x9E619573ADDA00EAL /* methodKey(F, "org.sireum.Os.Path.Impl", "copyTo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].copyTo(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x01033C7BAC3CF04AL /* methodKey(F, "org.sireum.Os.Path.Impl", "copyOverTo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].copyOverTo(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0xC8515482D9BD130DL /* methodKey(F, "org.sireum.Os.Path.Impl", "downloadFrom").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].downloadFrom(o1.asInstanceOf[org.sireum.String]))
    r.put(0xC6AF223E0FEF9126L /* methodKey(F, "org.sireum.Os.Path.Impl", "mergeFrom").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].mergeFrom(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.Os.Path]]))
    r.put(0xA176A1DA1E5EDADDL /* methodKey(F, "org.sireum.Os.Path.Impl", "moveTo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].moveTo(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0xD2D71B79480A1AC6L /* methodKey(F, "org.sireum.Os.Path.Impl", "moveOverTo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].moveOverTo(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x21DEF52BDAEFFE47L /* methodKey(F, "org.sireum.Os.Path.Impl", "mklink").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].mklink(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x4D1118DA7AD477C9L /* methodKey(F, "org.sireum.Os.Path.Impl", "prependWith").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].prependWith(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x177BD36757FFA2FCL /* methodKey(F, "org.sireum.Os.Path.Impl", "relativize").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].relativize(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x4087B80DA941BE05L /* methodKey(F, "org.sireum.Os.Path.Impl", "slash").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].slash(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]]))
    r.put(0xBC5E847DF4333665L /* methodKey(F, "org.sireum.Os.Path.Impl", "write").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].write(o1.asInstanceOf[org.sireum.String]))
    r.put(0xFC5755AC50A8319FL /* methodKey(F, "org.sireum.Os.Path.Impl", "writeOver").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeOver(o1.asInstanceOf[org.sireum.String]))
    r.put(0xA4BF53402F24860AL /* methodKey(F, "org.sireum.Os.Path.Impl", "writeAppend").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeAppend(o1.asInstanceOf[org.sireum.String]))
    r.put(0xB2BE3981886E837FL /* methodKey(F, "org.sireum.Os.Path.Impl", "writeLineStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeLineStream(o1.asInstanceOf[org.sireum.Jen[org.sireum.String]]))
    r.put(0xDD42F39FE6E0FE8BL /* methodKey(F, "org.sireum.Os.Path.Impl", "writeOverLineStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeOverLineStream(o1.asInstanceOf[org.sireum.Jen[org.sireum.String]]))
    r.put(0x5A2DCA4B5A141339L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendLineStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeAppendLineStream(o1.asInstanceOf[org.sireum.Jen[org.sireum.String]]))
    r.put(0x2301227FA9E074C6L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeLineMStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeLineMStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.String]]))
    r.put(0x582F878CEEB752CEL /* methodKey(F, "org.sireum.Os.Path.Impl", "writeOverLineMStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeOverLineMStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.String]]))
    r.put(0xCB277F12A7012990L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendLineMStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeAppendLineMStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.String]]))
    r.put(0x92E9B0DA7551FBACL /* methodKey(F, "org.sireum.Os.Path.Impl", "writeU8s").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeU8s(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]]))
    r.put(0x331BF5D9ABB8A21BL /* methodKey(F, "org.sireum.Os.Path.Impl", "writeOverU8s").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeOverU8s(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]]))
    r.put(0x48D985FAB22B187FL /* methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendU8s").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeAppendU8s(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]]))
    r.put(0x7CA2A23DCB49EF29L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeU8ms").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeU8ms(o1.asInstanceOf[org.sireum.MS[org.sireum.Z, org.sireum.U8]]))
    r.put(0xF77E19B361B2D85AL /* methodKey(F, "org.sireum.Os.Path.Impl", "writeOverU8ms").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeOverU8ms(o1.asInstanceOf[org.sireum.MS[org.sireum.Z, org.sireum.U8]]))
    r.put(0xA666FC8ECD47F0E2L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendU8ms").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeAppendU8ms(o1.asInstanceOf[org.sireum.MS[org.sireum.Z, org.sireum.U8]]))
    r.put(0x4ADED046D6CCD621L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeU8Stream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeU8Stream(o1.asInstanceOf[org.sireum.Jen[org.sireum.U8]]))
    r.put(0xD396CC4D710D9FD8L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeOverU8Stream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeOverU8Stream(o1.asInstanceOf[org.sireum.Jen[org.sireum.U8]]))
    r.put(0x5B91EB4360AD8C78L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendU8Stream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeAppendU8Stream(o1.asInstanceOf[org.sireum.Jen[org.sireum.U8]]))
    r.put(0x6738BAD017C093AFL /* methodKey(F, "org.sireum.Os.Path.Impl", "writeU8MStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeU8MStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.U8]]))
    r.put(0xC6F34800776CB844L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeOverU8MStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeOverU8MStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.U8]]))
    r.put(0x25CC706531185626L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendU8MStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeAppendU8MStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.U8]]))
    r.put(0x5A4ADDEA0F849AD3L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeCStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeCStream(o1.asInstanceOf[org.sireum.Jen[org.sireum.C]]))
    r.put(0xE188E38C78F93CC7L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeOverCStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeOverCStream(o1.asInstanceOf[org.sireum.Jen[org.sireum.C]]))
    r.put(0xB73F34F19212168CL /* methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendCStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeAppendCStream(o1.asInstanceOf[org.sireum.Jen[org.sireum.C]]))
    r.put(0x6C43E2B167A265D0L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeCMStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeCMStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.C]]))
    r.put(0x2560A38A9F29D357L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeOverCMStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeOverCMStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.C]]))
    r.put(0xC75D104F605319C1L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendCMStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeAppendCMStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.C]]))
    r.put(0x2D6F42350B84D61AL /* methodKey(F, "org.sireum.Os.Path.Impl", "zipTo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].zipTo(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0xD922F6BF9E155CE0L /* methodKey(F, "org.sireum.Os.Path.Impl", "unzipTo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].unzipTo(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x9F58CA4F78DAE96AL /* methodKey(F, "org.sireum.Os.Path.Impl", "unTarGzTo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].unTarGzTo(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0xE504DC4A1AE9E4F4L /* methodKey(F, "org.sireum.Os.Path.Jen", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xF192AF21F3DF714BL /* methodKey(F, "org.sireum.Os.Path.Jen", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x5C6DA5677F47654CL /* methodKey(F, "org.sireum.Os.Path.Jen", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xC349757229B551F1L /* methodKey(F, "org.sireum.Os.Path.Jen", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x3412DD80C8EB878FL /* methodKey(F, "org.sireum.Os.Path.Jen", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].contains(o1))
    r.put(0x71083CDF2134DCBBL /* methodKey(F, "org.sireum.Os.Path.Jen", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x37E702954C8331D2L /* methodKey(F, "org.sireum.Os.Path.Jen", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x26BBEE9D275BCB76L /* methodKey(F, "org.sireum.Os.Path.Jen", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x86A24A56685868D3L /* methodKey(F, "org.sireum.Os.Path.Jen", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x12D83D7433BED153L /* methodKey(F, "org.sireum.Os.Path.Jen", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x9AED3EE3F1595759L /* methodKey(F, "org.sireum.Os.Path.Jen", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xA2D69BC1F60E03EAL /* methodKey(F, "org.sireum.Os.Path.Jen", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x2CEC342FA15D1AB8L /* methodKey(F, "org.sireum.Os.Path.Jen", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0x6C59A08C3A25E85CL /* methodKey(F, "org.sireum.Os.Path.Jen", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0xE11A0A821500A40DL /* methodKey(F, "org.sireum.Os.Path.Jen", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xD55B5BE32BCD4E49L /* methodKey(F, "org.sireum.Os.Path.Jen", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0x24E20A1D88C0B4F0L /* methodKey(F, "org.sireum.Os.Path.Jen", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xD17AEB090A9CC6CDL /* methodKey(F, "org.sireum.Os.Path.Jen", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xFF90B9E2E09112E2L /* methodKey(F, "org.sireum.Os.Path.Jen", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].zip(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x473580799AAC9C1DL /* methodKey(F, "org.sireum.Os.Path.Jen", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].product(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xD337CD5D3B9148C6L /* methodKey(F, "org.sireum.Os.Path.Jen", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].`++`(o1.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x5746756940B4FEA9L /* methodKey(F, "org.sireum.Os.Path.Jen", "toIS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].toIS(o1.asInstanceOf[org.sireum.IS[Any, Any]]))
    r.put(0x5D620090D32C566DL /* methodKey(F, "org.sireum.Os.Path.Jen", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x1CE314095592A545L /* methodKey(F, "org.sireum.Os.Path.Jen", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0x2F808DF29F9BD8B8L /* methodKey(F, "org.sireum.Os.Path.MJen", "generate").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].generate(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x4F9F8BEC3BF50FBDL /* methodKey(F, "org.sireum.Os.Path.MJen", "foreach").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].foreach(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x600DEA75B6CFDAC7L /* methodKey(F, "org.sireum.Os.Path.MJen", "find").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].find(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x2B9A40744B8D385EL /* methodKey(F, "org.sireum.Os.Path.MJen", "exists").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].exists(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x3055A923C765BFECL /* methodKey(F, "org.sireum.Os.Path.MJen", "contains").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].contains(o1))
    r.put(0xAD28E5CBC6A66099L /* methodKey(F, "org.sireum.Os.Path.MJen", "forall").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].forall(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x70CCE1C74C4241AAL /* methodKey(F, "org.sireum.Os.Path.MJen", "countIf").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].countIf(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xF807E5B57198FC55L /* methodKey(F, "org.sireum.Os.Path.MJen", "reduce").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].reduce(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x7BD687205A7666ECL /* methodKey(F, "org.sireum.Os.Path.MJen", "reduceLeft").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].reduceLeft(o1.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xB7320217DEFFA0C6L /* methodKey(F, "org.sireum.Os.Path.MJen", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].filter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xC890C7CB127C22ACL /* methodKey(F, "org.sireum.Os.Path.MJen", "withFilter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].withFilter(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xFDB2D3A6439BF5F4L /* methodKey(F, "org.sireum.Os.Path.MJen", "map").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].map(o1.asInstanceOf[((Any) => Any)]))
    r.put(0x599777FEF096683FL /* methodKey(F, "org.sireum.Os.Path.MJen", "flatMap").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].flatMap(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x43EBA08FC8BF09D5L /* methodKey(F, "org.sireum.Os.Path.MJen", "flatten").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].flatten(o1.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x0C08B2659BC66A2EL /* methodKey(F, "org.sireum.Os.Path.MJen", "take").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].take(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xE740361A8E5F48AAL /* methodKey(F, "org.sireum.Os.Path.MJen", "drop").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].drop(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xB5196E9D071110A3L /* methodKey(F, "org.sireum.Os.Path.MJen", "takeWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].takeWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x2B738E92EB8043EEL /* methodKey(F, "org.sireum.Os.Path.MJen", "dropWhile").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].dropWhile(o1.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x172994381AEE1BF5L /* methodKey(F, "org.sireum.Os.Path.MJen", "zip").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].zip(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x072F36BC89AAD005L /* methodKey(F, "org.sireum.Os.Path.MJen", "product").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].product(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x10A32BBBB2EAEF4BL /* methodKey(F, "org.sireum.Os.Path.MJen", "++").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].`++`(o1.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xC7A8C8746CDF7FBBL /* methodKey(F, "org.sireum.Os.Path.MJen", "toMS").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].toMS(o1.asInstanceOf[org.sireum.MS[Any, Any]]))
    r.put(0x65FAAAA4F2E97AFEL /* methodKey(F, "org.sireum.Os.Path.MJen", "mkString").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].mkString(o1.asInstanceOf[org.sireum.String]))
    r.put(0xB3693C2306B12ED9L /* methodKey(F, "org.sireum.Os.Proc.LineFilter", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.LineFilter].filter(o1.asInstanceOf[org.sireum.String]))
    r.put(0x25D4185D3171F924L /* methodKey(T, "org.sireum.Os.Proc.FunLineFilter", "apply").value */, _ => (o1: Any) => org.sireum.Os.Proc.FunLineFilter.apply(o1.asInstanceOf[((org.sireum.String) => org.sireum.B)]))
    r.put(0x439C9310C9507110L /* methodKey(T, "org.sireum.Os.Proc.FunLineFilter", "unapply").value */, _ => (o1: Any) => org.sireum.Os.Proc.FunLineFilter.unapply(o1.asInstanceOf[org.sireum.Os.Proc.FunLineFilter]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0xF02501562FEAFBC4L /* methodKey(F, "org.sireum.Os.Proc.FunLineFilter", "filter").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc.FunLineFilter].filter(o1.asInstanceOf[org.sireum.String]))
    r.put(0x95307A2A007D1C02L /* methodKey(T, "org.sireum.Os.Proc.Result.Normal", "unapply").value */, _ => (o1: Any) => org.sireum.Os.Proc.Result.Normal.unapply(o1.asInstanceOf[org.sireum.Os.Proc.Result.Normal]) match {
      case scala.Some((o0, o1, o2)) => Some((o0, o1, o2))
      case _ => None()
    })
    r.put(0x3CDCC31B5CF13A37L /* methodKey(T, "org.sireum.Os.Proc.Result.Exception", "apply").value */, _ => (o1: Any) => org.sireum.Os.Proc.Result.Exception.apply(o1.asInstanceOf[org.sireum.String]))
    r.put(0xBC87FE5BCA7D4999L /* methodKey(T, "org.sireum.Os.Proc.Result.Exception", "unapply").value */, _ => (o1: Any) => org.sireum.Os.Proc.Result.Exception.unapply(o1.asInstanceOf[org.sireum.Os.Proc.Result.Exception]) match {
      case scala.Some(o) => Some(o)
      case _ => None()
    })
    r.put(0x09CC8A8CF8586482L /* methodKey(T, "org.sireum.Os.Proc.Result.Timeout", "unapply").value */, _ => (o1: Any) => org.sireum.Os.Proc.Result.Timeout.unapply(o1.asInstanceOf[org.sireum.Os.Proc.Result.Timeout]) match {
      case scala.Some((o0, o1)) => Some((o0, o1))
      case _ => None()
    })
    r.put(0xF48724F667564882L /* methodKey(T, "org.sireum.Os.Proc", "unapply").value */, _ => (o1: Any) => org.sireum.Os.Proc.unapply(o1.asInstanceOf[org.sireum.Os.Proc]) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14)) => Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14))
      case _ => None()
    })
    r.put(0x22AE762A24FDA146L /* methodKey(F, "org.sireum.Os.Proc", "commands").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].commands(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]]))
    r.put(0xF7D890943AB6E6E0L /* methodKey(F, "org.sireum.Os.Proc", "at").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].at(o1.asInstanceOf[org.sireum.OsProto.Path]))
    r.put(0x43B478AB84A1281FL /* methodKey(F, "org.sireum.Os.Proc", "env").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].env(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (org.sireum.String, org.sireum.String)]]))
    r.put(0x8BF2289950C96DC5L /* methodKey(F, "org.sireum.Os.Proc", "input").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].input(o1.asInstanceOf[org.sireum.String]))
    r.put(0x4856DFF2DF605546L /* methodKey(F, "org.sireum.Os.Proc", "timeout").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].timeout(o1.asInstanceOf[org.sireum.Z]))
    r.put(0xE4022EB9A069886CL /* methodKey(F, "org.sireum.Os.Proc", "outLineAction").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].outLineAction(o1.asInstanceOf[((org.sireum.String) => org.sireum.B)]))
    r.put(0x0AC3E43736A8B56FL /* methodKey(F, "org.sireum.Os.Proc", "errLineAction").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Proc].errLineAction(o1.asInstanceOf[((org.sireum.String) => org.sireum.B)]))
    r.put(0x2FF6E774BD0698A8L /* methodKey(F, "org.sireum.Os.Path", "/").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].`/`(o1.asInstanceOf[org.sireum.String]))
    r.put(0x3C8B9B4275598250L /* methodKey(F, "org.sireum.Os.Path", "/+").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].`/+`(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]]))
    r.put(0x082FC7021A4EF670L /* methodKey(F, "org.sireum.Os.Path", "call").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].call(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]]))
    r.put(0xC7CBEB2394D1FC82L /* methodKey(F, "org.sireum.Os.Path", "chmod").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].chmod(o1.asInstanceOf[org.sireum.String]))
    r.put(0x222A8ADB3CD3B58AL /* methodKey(F, "org.sireum.Os.Path", "chmodAll").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].chmodAll(o1.asInstanceOf[org.sireum.String]))
    r.put(0x80B94D36DCCF759CL /* methodKey(F, "org.sireum.Os.Path", "combineFrom").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].combineFrom(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.Os.Path]]))
    r.put(0xA084752F882FAE81L /* methodKey(F, "org.sireum.Os.Path", "copyTo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].copyTo(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x94B11624FED74821L /* methodKey(F, "org.sireum.Os.Path", "copyOverTo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].copyOverTo(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x3F50F20B6F8F62ABL /* methodKey(F, "org.sireum.Os.Path", "downloadFrom").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].downloadFrom(o1.asInstanceOf[org.sireum.String]))
    r.put(0xA9700CAD1EDA75B0L /* methodKey(F, "org.sireum.Os.Path", "mergeFrom").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].mergeFrom(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.Os.Path]]))
    r.put(0x2D43694D7292606CL /* methodKey(F, "org.sireum.Os.Path", "moveTo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].moveTo(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x0C68870FD7214D3BL /* methodKey(F, "org.sireum.Os.Path", "moveOverTo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].moveOverTo(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0xA6BA777B1FBE47B4L /* methodKey(F, "org.sireum.Os.Path", "mklink").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].mklink(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x0879FE2B2FB52FF4L /* methodKey(F, "org.sireum.Os.Path", "prependWith").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].prependWith(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x96414EBF95277030L /* methodKey(F, "org.sireum.Os.Path", "relativize").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].relativize(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0xA1348253E3A5B1E3L /* methodKey(F, "org.sireum.Os.Path", "slash").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].slash(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]]))
    r.put(0xDC4DB49A09541247L /* methodKey(F, "org.sireum.Os.Path", "write").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].write(o1.asInstanceOf[org.sireum.String]))
    r.put(0xC311034B333607A4L /* methodKey(F, "org.sireum.Os.Path", "writeOver").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeOver(o1.asInstanceOf[org.sireum.String]))
    r.put(0xAE51F4DDB35625E1L /* methodKey(F, "org.sireum.Os.Path", "writeAppend").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeAppend(o1.asInstanceOf[org.sireum.String]))
    r.put(0xA5B574C8DAF7D0D0L /* methodKey(F, "org.sireum.Os.Path", "writeLineStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeLineStream(o1.asInstanceOf[org.sireum.Jen[org.sireum.String]]))
    r.put(0xB1E1D93B23AF3D54L /* methodKey(F, "org.sireum.Os.Path", "writeOverLineStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeOverLineStream(o1.asInstanceOf[org.sireum.Jen[org.sireum.String]]))
    r.put(0x172A5B19FB1832B0L /* methodKey(F, "org.sireum.Os.Path", "writeAppendLineStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeAppendLineStream(o1.asInstanceOf[org.sireum.Jen[org.sireum.String]]))
    r.put(0x80C840BD5A8452D4L /* methodKey(F, "org.sireum.Os.Path", "writeLineMStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeLineMStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.String]]))
    r.put(0xAC8C72657E18D3B7L /* methodKey(F, "org.sireum.Os.Path", "writeOverLineMStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeOverLineMStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.String]]))
    r.put(0xEF5B82812CC825ECL /* methodKey(F, "org.sireum.Os.Path", "writeAppendLineMStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeAppendLineMStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.String]]))
    r.put(0xF2E84E31EE7F7596L /* methodKey(F, "org.sireum.Os.Path", "writeU8s").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeU8s(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]]))
    r.put(0xC9BB4AC0C0A3A88BL /* methodKey(F, "org.sireum.Os.Path", "writeOverU8s").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeOverU8s(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]]))
    r.put(0x04B206DD3BD2B55BL /* methodKey(F, "org.sireum.Os.Path", "writeAppendU8s").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeAppendU8s(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]]))
    r.put(0xB002EC6DC69A184BL /* methodKey(F, "org.sireum.Os.Path", "writeU8ms").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeU8ms(o1.asInstanceOf[org.sireum.MS[org.sireum.Z, org.sireum.U8]]))
    r.put(0xDA04F0DE676B52C8L /* methodKey(F, "org.sireum.Os.Path", "writeOverU8ms").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeOverU8ms(o1.asInstanceOf[org.sireum.MS[org.sireum.Z, org.sireum.U8]]))
    r.put(0x8A8D8DBD66744422L /* methodKey(F, "org.sireum.Os.Path", "writeAppendU8ms").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeAppendU8ms(o1.asInstanceOf[org.sireum.MS[org.sireum.Z, org.sireum.U8]]))
    r.put(0xC849943DA937A2B7L /* methodKey(F, "org.sireum.Os.Path", "writeU8Stream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeU8Stream(o1.asInstanceOf[org.sireum.Jen[org.sireum.U8]]))
    r.put(0x937BC9C5CFB2C574L /* methodKey(F, "org.sireum.Os.Path", "writeOverU8Stream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeOverU8Stream(o1.asInstanceOf[org.sireum.Jen[org.sireum.U8]]))
    r.put(0x5A9284ADCA91DC30L /* methodKey(F, "org.sireum.Os.Path", "writeAppendU8Stream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeAppendU8Stream(o1.asInstanceOf[org.sireum.Jen[org.sireum.U8]]))
    r.put(0x437B07C4ED800FF8L /* methodKey(F, "org.sireum.Os.Path", "writeU8MStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeU8MStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.U8]]))
    r.put(0xADC6B5E9BE1D2BB2L /* methodKey(F, "org.sireum.Os.Path", "writeOverU8MStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeOverU8MStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.U8]]))
    r.put(0xDF9324D2F22B94A8L /* methodKey(F, "org.sireum.Os.Path", "writeAppendU8MStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeAppendU8MStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.U8]]))
    r.put(0x869210C150BC4217L /* methodKey(F, "org.sireum.Os.Path", "writeCStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeCStream(o1.asInstanceOf[org.sireum.Jen[org.sireum.C]]))
    r.put(0x74E4DCA0494CCB14L /* methodKey(F, "org.sireum.Os.Path", "writeOverCStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeOverCStream(o1.asInstanceOf[org.sireum.Jen[org.sireum.C]]))
    r.put(0x4D2ACE69102F0E97L /* methodKey(F, "org.sireum.Os.Path", "writeAppendCStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeAppendCStream(o1.asInstanceOf[org.sireum.Jen[org.sireum.C]]))
    r.put(0x805E21DFEBA74CB8L /* methodKey(F, "org.sireum.Os.Path", "writeCMStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeCMStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.C]]))
    r.put(0x20829FE1E25FD61EL /* methodKey(F, "org.sireum.Os.Path", "writeOverCMStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeOverCMStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.C]]))
    r.put(0xAB2D728FCFFCE0A2L /* methodKey(F, "org.sireum.Os.Path", "writeAppendCMStream").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeAppendCMStream(o1.asInstanceOf[org.sireum.MJen[org.sireum.C]]))
    r.put(0x5BF82ECCE71C4E29L /* methodKey(F, "org.sireum.Os.Path", "zipTo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].zipTo(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0xC3E14F7AF35CABFEL /* methodKey(F, "org.sireum.Os.Path", "unzipTo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].unzipTo(o1.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x659B7BC16EA8D227L /* methodKey(F, "org.sireum.Os.Path", "unTarGzTo").value */, receiverOpt => (o1: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].unTarGzTo(o1.asInstanceOf[org.sireum.Os.Path]))
    r
  }

  private lazy val method2Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any](271)
    r.put(0x4D89D302473509F8L /* methodKey(T, "org.sireum.AssocS.Entries", "contain").value */, _ => (o1: Any) => (o2: Any) => org.sireum.AssocS.Entries.contain(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]], o2.asInstanceOf[(Any, Any)]))
    r.put(0x178633CC914B88ACL /* methodKey(T, "org.sireum.AssocS.Entries", "containKey").value */, _ => (o1: Any) => (o2: Any) => org.sireum.AssocS.Entries.containKey(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]], o2))
    r.put(0x4E5075BEB3306304L /* methodKey(T, "org.sireum.AssocS.Entries", "containValue").value */, _ => (o1: Any) => (o2: Any) => org.sireum.AssocS.Entries.containValue(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]], o2))
    r.put(0x2B283EA5F678304FL /* methodKey(T, "org.sireum.AssocS.Entries", "add").value */, _ => (o1: Any) => (o2: Any) => org.sireum.AssocS.Entries.add(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]], o2.asInstanceOf[(Any, Any)]))
    r.put(0x39E044506B21236DL /* methodKey(T, "org.sireum.AssocS.Entries", "indexOf").value */, _ => (o1: Any) => (o2: Any) => org.sireum.AssocS.Entries.indexOf(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]], o2))
    r.put(0xE94CD6B15156FDB9L /* methodKey(T, "org.sireum.AssocS.Entries", "remove").value */, _ => (o1: Any) => (o2: Any) => org.sireum.AssocS.Entries.remove(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]], o2.asInstanceOf[(Any, Any)]))
    r.put(0x0E94BB2EEE469936L /* methodKey(T, "org.sireum.Sireum", "proc").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Sireum.proc(o1.asInstanceOf[org.sireum.OsProto.Proc], o2.asInstanceOf[org.sireum.message.Reporter]))
    r.put(0xE50AAFB53DB03B57L /* methodKey(T, "org.sireum.Sireum", "procCheck").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Sireum.procCheck(o1.asInstanceOf[org.sireum.OsProto.Proc], o2.asInstanceOf[org.sireum.message.Reporter]))
    r.put(0xBA9CED93A12DD487L /* methodKey(T, "org.sireum.Sireum", "runWithReporter").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Sireum.runWithReporter(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]], o2.asInstanceOf[org.sireum.message.Reporter]))
    r.put(0x9CBB5B7098269807L /* methodKey(T, "org.sireum.ContractUtil", "modPos").value */, _ => (o1: Any) => (o2: Any) => org.sireum.ContractUtil.modPos(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0xB16CAA123C3555CBL /* methodKey(T, "org.sireum.ContractUtil", "modNeg").value */, _ => (o1: Any) => (o2: Any) => org.sireum.ContractUtil.modNeg(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x2383098E48B3B87DL /* methodKey(T, "org.sireum.ContractUtil", "isAllIS").value */, _ => (o1: Any) => (o2: Any) => org.sireum.ContractUtil.isAllIS(o1.asInstanceOf[org.sireum.IS[Any, Any]], o2))
    r.put(0x562C04357BD2A178L /* methodKey(T, "org.sireum.ContractUtil", "isAllMS").value */, _ => (o1: Any) => (o2: Any) => org.sireum.ContractUtil.isAllMS(o1.asInstanceOf[org.sireum.MS[Any, Any]], o2))
    r.put(0x928FF1D4B1DFA839L /* methodKey(T, "org.sireum.Graph.Internal", "addEdge").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Graph.Internal.addEdge(o1.asInstanceOf[org.sireum.Graph[Any, Any]], o2.asInstanceOf[org.sireum.Graph.Internal.Edge[Any]]))
    r.put(0x4524CDA3F6F6F88DL /* methodKey(T, "org.sireum.Graph.Internal", "incoming").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Graph.Internal.incoming(o1.asInstanceOf[org.sireum.Graph[Any, Any]], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x53B7E0C7DF3E3F43L /* methodKey(T, "org.sireum.Graph.Internal", "outgoing").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Graph.Internal.outgoing(o1.asInstanceOf[org.sireum.Graph[Any, Any]], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x82E7E2C688CFAA3EL /* methodKey(T, "org.sireum.Hash", "murmur3a").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Hash.murmur3a(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]], o2.asInstanceOf[org.sireum.U32]))
    r.put(0x1AC38055921AEF7DL /* methodKey(T, "org.sireum.Hash", "t1ha0").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Hash.t1ha0(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]], o2.asInstanceOf[org.sireum.U64]))
    r.put(0x612CEAFCE4129CA6L /* methodKey(T, "org.sireum.LibUtil", "parCores").value */, _ => (o1: Any) => (o2: Any) => org.sireum.LibUtil.parCores(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x5DA2792530D35966L /* methodKey(T, "org.sireum.LibUtil", "parCoresOpt").value */, _ => (o1: Any) => (o2: Any) => org.sireum.LibUtil.parCoresOpt(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Option[org.sireum.Z]]))
    r.put(0xD6FBE73F1F9665F3L /* methodKey(T, "org.sireum.LibUtil", "mineOptionsWithPrefix").value */, _ => (o1: Any) => (o2: Any) => org.sireum.LibUtil.mineOptionsWithPrefix(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String]))
    r.put(0x93D980CAF274553DL /* methodKey(T, "org.sireum.Poset.Internal", "addNode").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.addNode(o1.asInstanceOf[org.sireum.Poset[Any]], o2))
    r.put(0xC30153150F7EC2EAL /* methodKey(T, "org.sireum.Poset.Internal", "addNodes").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.addNodes(o1.asInstanceOf[org.sireum.Poset[Any]], o2.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]]))
    r.put(0x2038FE88D1916323L /* methodKey(T, "org.sireum.Poset.Internal", "childrenOf").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.childrenOf(o1.asInstanceOf[org.sireum.Poset[Any]], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x6CBF913BC0DFD772L /* methodKey(T, "org.sireum.Poset.Internal", "parentsOf").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.parentsOf(o1.asInstanceOf[org.sireum.Poset[Any]], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x6E87431738B70955L /* methodKey(T, "org.sireum.Poset.Internal", "ancestorsOf").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.ancestorsOf(o1.asInstanceOf[org.sireum.Poset[Any]], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x4FC217B48FF6D852L /* methodKey(T, "org.sireum.Poset.Internal", "lub").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.lub(o1.asInstanceOf[org.sireum.Poset[Any]], o2.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.Z]]))
    r.put(0xAB0AE33B6F0B9982L /* methodKey(T, "org.sireum.Poset.Internal", "descendantsOf").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.descendantsOf(o1.asInstanceOf[org.sireum.Poset[Any]], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x3DAA5793705FEED8L /* methodKey(T, "org.sireum.Poset.Internal", "glb").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.glb(o1.asInstanceOf[org.sireum.Poset[Any]], o2.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.Z]]))
    r.put(0xE2A2334F047744D1L /* methodKey(T, "org.sireum.Random", "rotl32").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Random.rotl32(o1.asInstanceOf[org.sireum.U32], o2.asInstanceOf[org.sireum.U32]))
    r.put(0xE5B1FC9A31989748L /* methodKey(T, "org.sireum.Random", "rotl64").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Random.rotl64(o1.asInstanceOf[org.sireum.U64], o2.asInstanceOf[org.sireum.U64]))
    r.put(0xEA73CBDA8ECB493EL /* methodKey(T, "org.sireum.Set.Elements", "contain").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Set.Elements.contain(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]], o2))
    r.put(0x546627C6E3C63868L /* methodKey(T, "org.sireum.UnionFind.Internal", "find").value */, _ => (o1: Any) => (o2: Any) => org.sireum.UnionFind.Internal.find(o1.asInstanceOf[org.sireum.UnionFind[Any]], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x0D002218A9369CFDL /* methodKey(T, "org.sireum.UnionFind.Internal", "findCompress").value */, _ => (o1: Any) => (o2: Any) => org.sireum.UnionFind.Internal.findCompress(o1.asInstanceOf[org.sireum.UnionFind[Any]], o2.asInstanceOf[org.sireum.Z]))
    r.put(0xE4B8D936C9D8CAB1L /* methodKey(T, "org.sireum.justification.natded.prop", "andI").value */, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.andI(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B]))
    r.put(0x1A1476F25B111A5EL /* methodKey(T, "org.sireum.justification.natded.prop", "andE1").value */, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.andE1(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B]))
    r.put(0x3B2F80F532036C22L /* methodKey(T, "org.sireum.justification.natded.prop", "andE2").value */, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.andE2(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B]))
    r.put(0x789DED1BA67B03D7L /* methodKey(T, "org.sireum.justification.natded.prop", "orI1").value */, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.orI1(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B]))
    r.put(0x48A884BB5B4FBA1FL /* methodKey(T, "org.sireum.justification.natded.prop", "orI2").value */, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.orI2(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B]))
    r.put(0x91CB57D0A3455180L /* methodKey(T, "org.sireum.justification.natded.prop", "implyE").value */, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.implyE(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B]))
    r.put(0x472F0AAA08F546D6L /* methodKey(T, "org.sireum.justification.natded.prop", "sandI").value */, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.sandI(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B]))
    r.put(0xB5261A98D0D7D827L /* methodKey(T, "org.sireum.justification.natded.prop", "sandE1").value */, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.sandE1(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B]))
    r.put(0xD1EF48B22113CD6EL /* methodKey(T, "org.sireum.justification.natded.prop", "sandE2").value */, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.sandE2(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B]))
    r.put(0xCC48284B33C6C1FCL /* methodKey(T, "org.sireum.justification.natded.prop", "sorI1").value */, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.sorI1(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B]))
    r.put(0x8B0141E580B126DAL /* methodKey(T, "org.sireum.justification.natded.prop", "sorI2").value */, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.sorI2(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B]))
    r.put(0xD2BF1208B8800EDEL /* methodKey(T, "org.sireum.justification.natded.prop", "simplyE").value */, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.simplyE(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B]))
    r.put(0x684C1670B4F120B1L /* methodKey(T, "org.sireum.justification.natded.pred", "allE").value */, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.pred.allE(o1.asInstanceOf[((Any) => org.sireum.B)], o2))
    r.put(0x8B0B01BC9C6EEE54L /* methodKey(T, "org.sireum.justification.natded.pred", "existsI").value */, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.pred.existsI(o1.asInstanceOf[((Any) => org.sireum.B)], o2))
    r.put(0x6307744B6E77C1A7L /* methodKey(T, "org.sireum.Coursier", "isRuntimePublishedLocally").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Coursier.isRuntimePublishedLocally(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String]))
    r.put(0x1DA92106EB6589D4L /* methodKey(T, "org.sireum.GitHub", "repo").value */, _ => (o1: Any) => (o2: Any) => org.sireum.GitHub.repo(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String]))
    r.put(0xF300BD9CE5AC1119L /* methodKey(T, "org.sireum.Os", "javaHomeOpt").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Os.javaHomeOpt(o1.asInstanceOf[org.sireum.Os.Kind.Type], o2.asInstanceOf[org.sireum.Option[org.sireum.Os.Path]]))
    r.put(0x862E599A4E9195F8L /* methodKey(T, "org.sireum.Os", "tempFix").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Os.tempFix(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String]))
    r.put(0x4DA63867A8230393L /* methodKey(T, "org.sireum.Os.Ext", "download").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.download(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String]))
    r.put(0xF22E9998BC9F3381L /* methodKey(T, "org.sireum.Os.Ext", "mergeFrom").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.mergeFrom(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]]))
    r.put(0x601C666A8F930DA3L /* methodKey(T, "org.sireum.Os.Ext", "mkdir").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.mkdir(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.B]))
    r.put(0xC0EEB8B2898E63D2L /* methodKey(T, "org.sireum.Os.Ext", "mklink").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.mklink(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String]))
    r.put(0xB82E01BF2363E5BBL /* methodKey(T, "org.sireum.Os.Ext", "relativize").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.relativize(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String]))
    r.put(0x384F977029B938BAL /* methodKey(T, "org.sireum.Os.Ext", "temp").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.temp(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String]))
    r.put(0x837B0EC490F1849CL /* methodKey(T, "org.sireum.Os.Ext", "zip").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.zip(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String]))
    r.put(0x396E341024F63799L /* methodKey(T, "org.sireum.Os.Ext", "unzip").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.unzip(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String]))
    r.put(0xFFE8DB1E12437074L /* methodKey(T, "org.sireum.Os.Ext", "unTarGz").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.unTarGz(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String]))
    r.put(0xFEA79E6B2C203917L /* methodKey(T, "org.sireum.Scalafmt", "formatFile").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Scalafmt.formatFile(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.Os.Path]))
    r.put(0x49C093B097A01EBDL /* methodKey(F, "org.sireum.AssocS", "getOrElse").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].getOrElse(o1, o2.asInstanceOf[(() => Any)]()))
    r.put(0x6C3073DA0AD132D8L /* methodKey(F, "org.sireum.AssocS", "getOrElseEager").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.AssocS[Any, Any]].getOrElseEager(o1, o2))
    r.put(0x08021F8181E0F814L /* methodKey(F, "org.sireum.Bag", "addN").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].addN(o1, o2.asInstanceOf[org.sireum.Z]))
    r.put(0x785EDDA06BFE8C1AL /* methodKey(F, "org.sireum.Bag", "removeN").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Bag[Any]].removeN(o1, o2.asInstanceOf[org.sireum.Z]))
    r.put(0x3FE76C85E5F4411CL /* methodKey(T, "org.sireum.Graph.Edge.Plain", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Graph.Edge.Plain.apply(o1, o2))
    r.put(0xD1480A237264634EL /* methodKey(T, "org.sireum.Graph.Internal.Edge.Plain", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Graph.Internal.Edge.Plain.apply(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x2BE976A74FF136C8L /* methodKey(F, "org.sireum.Graph", "addPlainEdge").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].addPlainEdge(o1, o2))
    r.put(0xE0AD4F19FFDA9091L /* methodKey(F, "org.sireum.Graph", "removeEdgeN").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].removeEdgeN(o1.asInstanceOf[org.sireum.Graph.Edge[Any, Any]], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x6C9F6B0CF10F6977L /* methodKey(F, "org.sireum.Graph", "edges").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].edges(o1, o2))
    r.put(0x16C330EA829F6E77L /* methodKey(F, "org.sireum.HashBag", "addN").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].addN(o1, o2.asInstanceOf[org.sireum.Z]))
    r.put(0x49A9FF8AAB03F510L /* methodKey(F, "org.sireum.HashBag", "removeN").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashBag[Any]].removeN(o1, o2.asInstanceOf[org.sireum.Z]))
    r.put(0xCEC30333071D03DBL /* methodKey(T, "org.sireum.HashMap", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.HashMap.apply(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.Map[Any, Any]]], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x7845CD39AB58D63EL /* methodKey(F, "org.sireum.HashSBag", "addN").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].addN(o1, o2.asInstanceOf[org.sireum.Z]))
    r.put(0xE31AD83762526B77L /* methodKey(F, "org.sireum.HashSBag", "removeN").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.HashSBag[Any]].removeN(o1, o2.asInstanceOf[org.sireum.Z]))
    r.put(0xB7B7A30167138B13L /* methodKey(T, "org.sireum.HashSMap", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.HashSMap.apply(o1.asInstanceOf[org.sireum.HashMap[Any, Any]], o2.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]]))
    r.put(0x9EC0B1DA5C9CCE89L /* methodKey(F, "org.sireum.Indexable.Pos", "posOpt").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Indexable.Pos[Any]].posOpt(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x9F7CDF54AFAA71DBL /* methodKey(T, "org.sireum.Indexable.IszDocInfo", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Indexable.IszDocInfo.apply(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]], o2.asInstanceOf[org.sireum.message.DocInfo]))
    r.put(0x6D58B4D33B940EEEL /* methodKey(F, "org.sireum.Indexable.IszDocInfo", "posOpt").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Indexable.IszDocInfo[Any]].posOpt(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x041DA465174F906EL /* methodKey(F, "org.sireum.Jen", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x4D751DF563BB3F9DL /* methodKey(F, "org.sireum.Jen", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x8959AC5C355C37CFL /* methodKey(F, "org.sireum.Jen", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0xA54B03218B1CCBFFL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x6E353A74748FB104L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x7FBB2F2B7D80BAEEL /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x40BA7BA624C1375BL /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].fold(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0x101A1EBE5C403EE1L /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0xC0D44E9ACAD095DFL /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x64E6BD2733F64140L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].fold(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0x38B3EE3BC094B1DDL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0x8713BAB26393E996L /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x40A879D7CA2541AEL /* methodKey(T, "org.sireum.Jen.Internal.Filtered", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.Filtered.apply(o1.asInstanceOf[org.sireum.Jen[Any]], o2.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x6D72E1EDB6347535L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xA636738B275FCD7EL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xFAF3388C679CFC01L /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x9B8DE6A6D6B5BE2AL /* methodKey(T, "org.sireum.Jen.Internal.Mapped", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.Mapped.apply(o1.asInstanceOf[org.sireum.Jen[Any]], o2.asInstanceOf[((Any) => Any)]))
    r.put(0x09CFEA30136FAEEDL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x86C1BC8BE26BBC47L /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xE36598706B63AFDBL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0xEEE44E5821CF63E1L /* methodKey(T, "org.sireum.Jen.Internal.FlatMapped", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.FlatMapped.apply(o1.asInstanceOf[org.sireum.Jen[Any]], o2.asInstanceOf[((Any) => org.sireum.Jen[Any])]))
    r.put(0x2D0172125C239932L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x9FBD18FC1D806BD1L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x3F64F21E5A1466F8L /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x4CA9D96227CB4CBEL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x7C360DEA12CF4E24L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xF52245B0C8C6D013L /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x14C647E1A8667A59L /* methodKey(T, "org.sireum.Jen.Internal.TakeWhile", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.TakeWhile.apply(o1.asInstanceOf[org.sireum.Jen[Any]], o2.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x0E75D4FF81407E86L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xDF7978AA1C9F5975L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x5C42C51FA9270097L /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x087F8F5BB125889DL /* methodKey(T, "org.sireum.Jen.Internal.DropWhile", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.DropWhile.apply(o1.asInstanceOf[org.sireum.Jen[Any]], o2.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x1A89DE7BE8A08DA4L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xD09713B66C83794FL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xFDA7DE926D86C6B1L /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x96A52EFFE75C18D3L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].fold(o1, o2.asInstanceOf[((Any, (Any, org.sireum.Z)) => Any)]))
    r.put(0x4F8024E1901E7328L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].foldLeft(o1, o2.asInstanceOf[((Any, (Any, org.sireum.Z)) => Any)]))
    r.put(0x4EA66F42272AE270L /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x6557C41078F36872L /* methodKey(T, "org.sireum.Jen.Internal.Zipped", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.Zipped.apply(o1.asInstanceOf[org.sireum.Jen[Any]], o2.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0xC19BFE174B8CA38CL /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].fold(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0x82B390B17F0FB813L /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0x3C643385D22826CBL /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0xEA59DE915A5618E3L /* methodKey(T, "org.sireum.Jen.Internal.Concat", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.Concat.apply(o1.asInstanceOf[org.sireum.Jen[Any]], o2.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x3DAFE7D542EED8CFL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xB08F6F70BE6D80FFL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x4F240169B4420ADFL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0xB82C1BDA097B4B2CL /* methodKey(T, "org.sireum.Jen.Internal.Product", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.Product.apply(o1.asInstanceOf[org.sireum.Jen[Any]], o2.asInstanceOf[org.sireum.Jen[Any]]))
    r.put(0x26CC544A64D9B260L /* methodKey(F, "org.sireum.Jen.Internal.Product", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].fold(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0x0D868ACA49D40F2AL /* methodKey(F, "org.sireum.Jen.Internal.Product", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0x529DC6529237411DL /* methodKey(F, "org.sireum.Jen.Internal.Product", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x26239569EB9F78B4L /* methodKey(T, "org.sireum.MBox2", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.MBox2.apply(o1, o2))
    r.put(0xE1647A47813A1867L /* methodKey(F, "org.sireum.MJen", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xC736E72ADF065E1CL /* methodKey(F, "org.sireum.MJen", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x49B4E013B3069183L /* methodKey(F, "org.sireum.MJen", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x1CAA5B26F8EA86C1L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x18CDC7D977A6A510L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x2659AD904C933FBEL /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0xF4D6E5F049B1AAF9L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xF9A58A914487A8A2L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x2EB78D386B05FA88L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x2A62D75947EF6E92L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].fold(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0xD583C036081A2061L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0x85568BE5447F8C5FL /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0xE9F46A8C7E11192DL /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].fold(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0x3F06D70F1D52E356L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0xF364398B24C7A027L /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x3ACC67C13766A04DL /* methodKey(T, "org.sireum.MJen.Internal.Filtered", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.Filtered.apply(o1.asInstanceOf[org.sireum.MJen[Any]], o2.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x4EBF495CC2A079F6L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xFFC826DDB864B673L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x944ADCC475E3AC22L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x5875AAC450B6296EL /* methodKey(T, "org.sireum.MJen.Internal.Mapped", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.Mapped.apply(o1.asInstanceOf[org.sireum.MJen[Any]], o2.asInstanceOf[((Any) => Any)]))
    r.put(0x3A385F2B03C68B95L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xE86C87692152FA04L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xDBBBEC8FD6C79F79L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x579F3426215EB81EL /* methodKey(T, "org.sireum.MJen.Internal.FlatMapped", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.FlatMapped.apply(o1.asInstanceOf[org.sireum.MJen[Any]], o2.asInstanceOf[((Any) => org.sireum.MJen[Any])]))
    r.put(0x1153F80972B0A671L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x571782490C0755AFL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xB51371D89EB8EF08L /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x1CB36C58E310985FL /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xFED6B5BFBB723944L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x6518F0B1965489FBL /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x0DDBB7AC73BB86BAL /* methodKey(T, "org.sireum.MJen.Internal.TakeWhile", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.TakeWhile.apply(o1.asInstanceOf[org.sireum.MJen[Any]], o2.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0xC99683DA8906A89AL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xFBA200C66EA07181L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xC69C1C296E741816L /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0xACA940E29AD22134L /* methodKey(T, "org.sireum.MJen.Internal.DropWhile", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.DropWhile.apply(o1.asInstanceOf[org.sireum.MJen[Any]], o2.asInstanceOf[((Any) => org.sireum.B)]))
    r.put(0x1B9450768B4E93FBL /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xBE059E9E79636EA2L /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xD2E6A45B4D64984CL /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x6701E9995B5CBD3BL /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].fold(o1, o2.asInstanceOf[((Any, (Any, org.sireum.Z)) => Any)]))
    r.put(0x6986B635B0DFDA64L /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].foldLeft(o1, o2.asInstanceOf[((Any, (Any, org.sireum.Z)) => Any)]))
    r.put(0x12B16202CDB71E9AL /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x3F5A3D2A490E8F11L /* methodKey(T, "org.sireum.MJen.Internal.Zipped", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.Zipped.apply(o1.asInstanceOf[org.sireum.MJen[Any]], o2.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0x83618B195D301F1FL /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].fold(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0xF35CA04BE8BFC2E6L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0x76BBF2F6647078C8L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0xC3F74161DA5A3DA4L /* methodKey(T, "org.sireum.MJen.Internal.Concat", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.Concat.apply(o1.asInstanceOf[org.sireum.MJen[Any]], o2.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xD4C995E94ABEF8BCL /* methodKey(F, "org.sireum.MJen.Internal.Concat", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x5B917C8D1D8A3526L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x17488483C80B2750L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0xE3DF71B549861218L /* methodKey(T, "org.sireum.MJen.Internal.Product", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.Product.apply(o1.asInstanceOf[org.sireum.MJen[Any]], o2.asInstanceOf[org.sireum.MJen[Any]]))
    r.put(0xE73BF9A6DBC89FC7L /* methodKey(F, "org.sireum.MJen.Internal.Product", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].fold(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0x48E03C03AC985EF8L /* methodKey(F, "org.sireum.MJen.Internal.Product", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].foldLeft(o1, o2.asInstanceOf[((Any, (Any, Any)) => Any)]))
    r.put(0x6B472FE507A3A6E4L /* methodKey(F, "org.sireum.MJen.Internal.Product", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0xEBBA5A921655D1ABL /* methodKey(F, "org.sireum.Map", "getOrElse").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].getOrElse(o1, o2.asInstanceOf[(() => Any)]()))
    r.put(0x8D2BD2B523F38C93L /* methodKey(F, "org.sireum.Map", "getOrElseEager").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Map[Any, Any]].getOrElseEager(o1, o2))
    r.put(0x33107511A8A9A90DL /* methodKey(F, "org.sireum.ObjPrinter", "cache").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].cache(o1, o2.asInstanceOf[(() => org.sireum.ST)]))
    r.put(0xF6743F8BCA07FA13L /* methodKey(F, "org.sireum.Poset", "addParents").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].addParents(o1, o2.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]]))
    r.put(0x6F1E7BD23D213918L /* methodKey(F, "org.sireum.Poset", "removeParent").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].removeParent(o1, o2))
    r.put(0xABC8C2831F2FCE95L /* methodKey(F, "org.sireum.Poset", "removeChild").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].removeChild(o1, o2))
    r.put(0xDFA2DB5486B9737DL /* methodKey(F, "org.sireum.Poset", "addChildren").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].addChildren(o1, o2.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]]))
    r.put(0xD6042E4C76C9036DL /* methodKey(F, "org.sireum.Poset", "isChildOf").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].isChildOf(o1, o2))
    r.put(0x06CDD5451AE7D4D7L /* methodKey(F, "org.sireum.Poset", "isParentOf").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Poset[Any]].isParentOf(o1, o2))
    r.put(0x0273B337856240CFL /* methodKey(F, "org.sireum.Random.Gen", "nextCBetween").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextCBetween(o1.asInstanceOf[org.sireum.C], o2.asInstanceOf[org.sireum.C]))
    r.put(0x54610064CE2B1FB3L /* methodKey(F, "org.sireum.Random.Gen", "nextZBetween").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextZBetween(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x5DFD5CF7205195A3L /* methodKey(F, "org.sireum.Random.Gen", "nextF32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextF32Between(o1.asInstanceOf[org.sireum.F32], o2.asInstanceOf[org.sireum.F32]))
    r.put(0x0AE43149E7A18DCDL /* methodKey(F, "org.sireum.Random.Gen", "nextF64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextF64Between(o1.asInstanceOf[org.sireum.F64], o2.asInstanceOf[org.sireum.F64]))
    r.put(0xB6FDEE15FFC58F4BL /* methodKey(F, "org.sireum.Random.Gen", "nextRBetween").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextRBetween(o1.asInstanceOf[org.sireum.R], o2.asInstanceOf[org.sireum.R]))
    r.put(0x6B9AF4A7179C8D82L /* methodKey(F, "org.sireum.Random.Gen", "nextU8Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextU8Between(o1.asInstanceOf[org.sireum.U8], o2.asInstanceOf[org.sireum.U8]))
    r.put(0x07E0B84E7B6E22ACL /* methodKey(F, "org.sireum.Random.Gen", "nextU16Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextU16Between(o1.asInstanceOf[org.sireum.U16], o2.asInstanceOf[org.sireum.U16]))
    r.put(0xFDE58991B33D0CECL /* methodKey(F, "org.sireum.Random.Gen", "nextU32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextU32Between(o1.asInstanceOf[org.sireum.U32], o2.asInstanceOf[org.sireum.U32]))
    r.put(0x1417912AB6680986L /* methodKey(F, "org.sireum.Random.Gen", "nextU64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextU64Between(o1.asInstanceOf[org.sireum.U64], o2.asInstanceOf[org.sireum.U64]))
    r.put(0x21756EE67C0B7B80L /* methodKey(F, "org.sireum.Random.Gen", "nextN8Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextN8Between(o1.asInstanceOf[org.sireum.N8], o2.asInstanceOf[org.sireum.N8]))
    r.put(0xFCEE661EB664DF37L /* methodKey(F, "org.sireum.Random.Gen", "nextN16Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextN16Between(o1.asInstanceOf[org.sireum.N16], o2.asInstanceOf[org.sireum.N16]))
    r.put(0x1A5DFAD1041D9378L /* methodKey(F, "org.sireum.Random.Gen", "nextN32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextN32Between(o1.asInstanceOf[org.sireum.N32], o2.asInstanceOf[org.sireum.N32]))
    r.put(0x43FED2D22365FBE6L /* methodKey(F, "org.sireum.Random.Gen", "nextN64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextN64Between(o1.asInstanceOf[org.sireum.N64], o2.asInstanceOf[org.sireum.N64]))
    r.put(0xA00E7C63F51ADB3EL /* methodKey(F, "org.sireum.Random.Gen", "nextS8Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextS8Between(o1.asInstanceOf[org.sireum.S8], o2.asInstanceOf[org.sireum.S8]))
    r.put(0x45CE377FCCF8EAA1L /* methodKey(F, "org.sireum.Random.Gen", "nextS16Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextS16Between(o1.asInstanceOf[org.sireum.S16], o2.asInstanceOf[org.sireum.S16]))
    r.put(0x49C08EE69F41C23AL /* methodKey(F, "org.sireum.Random.Gen", "nextS32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextS32Between(o1.asInstanceOf[org.sireum.S32], o2.asInstanceOf[org.sireum.S32]))
    r.put(0x445188613D4B2EB9L /* methodKey(F, "org.sireum.Random.Gen", "nextS64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextS64Between(o1.asInstanceOf[org.sireum.S64], o2.asInstanceOf[org.sireum.S64]))
    r.put(0x07A046CA6A85DD2DL /* methodKey(F, "org.sireum.Random.Gen", "nextZ8Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextZ8Between(o1.asInstanceOf[org.sireum.Z8], o2.asInstanceOf[org.sireum.Z8]))
    r.put(0x930AAB6E314B2AD5L /* methodKey(F, "org.sireum.Random.Gen", "nextZ16Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextZ16Between(o1.asInstanceOf[org.sireum.Z16], o2.asInstanceOf[org.sireum.Z16]))
    r.put(0x1BE761A3B9B356F6L /* methodKey(F, "org.sireum.Random.Gen", "nextZ32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextZ32Between(o1.asInstanceOf[org.sireum.Z32], o2.asInstanceOf[org.sireum.Z32]))
    r.put(0x26505C6C2A9E2E69L /* methodKey(F, "org.sireum.Random.Gen", "nextZ64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen].nextZ64Between(o1.asInstanceOf[org.sireum.Z64], o2.asInstanceOf[org.sireum.Z64]))
    r.put(0x14409448DA5A4BF4L /* methodKey(F, "org.sireum.Random.Gen64", "nextCBetween").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextCBetween(o1.asInstanceOf[org.sireum.C], o2.asInstanceOf[org.sireum.C]))
    r.put(0xD08E2B5DFD58AD8DL /* methodKey(F, "org.sireum.Random.Gen64", "nextZBetween").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextZBetween(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x3235C5B94A8F9A87L /* methodKey(F, "org.sireum.Random.Gen64", "nextF32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextF32Between(o1.asInstanceOf[org.sireum.F32], o2.asInstanceOf[org.sireum.F32]))
    r.put(0x503B7ABC2B802176L /* methodKey(F, "org.sireum.Random.Gen64", "nextF64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextF64Between(o1.asInstanceOf[org.sireum.F64], o2.asInstanceOf[org.sireum.F64]))
    r.put(0x844FEB1158C04564L /* methodKey(F, "org.sireum.Random.Gen64", "nextRBetween").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextRBetween(o1.asInstanceOf[org.sireum.R], o2.asInstanceOf[org.sireum.R]))
    r.put(0xFD7F076894E3F7B2L /* methodKey(F, "org.sireum.Random.Gen64", "nextU8Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextU8Between(o1.asInstanceOf[org.sireum.U8], o2.asInstanceOf[org.sireum.U8]))
    r.put(0xB290FCAC6BC2E35DL /* methodKey(F, "org.sireum.Random.Gen64", "nextU16Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextU16Between(o1.asInstanceOf[org.sireum.U16], o2.asInstanceOf[org.sireum.U16]))
    r.put(0x53F00FE90CFD8D5AL /* methodKey(F, "org.sireum.Random.Gen64", "nextU32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextU32Between(o1.asInstanceOf[org.sireum.U32], o2.asInstanceOf[org.sireum.U32]))
    r.put(0x616A829A30AB3468L /* methodKey(F, "org.sireum.Random.Gen64", "nextU64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextU64Between(o1.asInstanceOf[org.sireum.U64], o2.asInstanceOf[org.sireum.U64]))
    r.put(0xE0A74441E3B0CBFBL /* methodKey(F, "org.sireum.Random.Gen64", "nextN8Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextN8Between(o1.asInstanceOf[org.sireum.N8], o2.asInstanceOf[org.sireum.N8]))
    r.put(0x6FCB7B45439580A3L /* methodKey(F, "org.sireum.Random.Gen64", "nextN16Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextN16Between(o1.asInstanceOf[org.sireum.N16], o2.asInstanceOf[org.sireum.N16]))
    r.put(0xD2DEC6F2F27C6509L /* methodKey(F, "org.sireum.Random.Gen64", "nextN32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextN32Between(o1.asInstanceOf[org.sireum.N32], o2.asInstanceOf[org.sireum.N32]))
    r.put(0x334D3DC616ED854AL /* methodKey(F, "org.sireum.Random.Gen64", "nextN64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextN64Between(o1.asInstanceOf[org.sireum.N64], o2.asInstanceOf[org.sireum.N64]))
    r.put(0x84E982D02DE4FE72L /* methodKey(F, "org.sireum.Random.Gen64", "nextS8Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextS8Between(o1.asInstanceOf[org.sireum.S8], o2.asInstanceOf[org.sireum.S8]))
    r.put(0x1007F66A7F880012L /* methodKey(F, "org.sireum.Random.Gen64", "nextS16Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextS16Between(o1.asInstanceOf[org.sireum.S16], o2.asInstanceOf[org.sireum.S16]))
    r.put(0xFA32DDB5D61F2060L /* methodKey(F, "org.sireum.Random.Gen64", "nextS32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextS32Between(o1.asInstanceOf[org.sireum.S32], o2.asInstanceOf[org.sireum.S32]))
    r.put(0x40576DDF27B4F2BAL /* methodKey(F, "org.sireum.Random.Gen64", "nextS64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextS64Between(o1.asInstanceOf[org.sireum.S64], o2.asInstanceOf[org.sireum.S64]))
    r.put(0x9265F5C1287424ADL /* methodKey(F, "org.sireum.Random.Gen64", "nextZ8Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextZ8Between(o1.asInstanceOf[org.sireum.Z8], o2.asInstanceOf[org.sireum.Z8]))
    r.put(0xBB7143BBD75CBA25L /* methodKey(F, "org.sireum.Random.Gen64", "nextZ16Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextZ16Between(o1.asInstanceOf[org.sireum.Z16], o2.asInstanceOf[org.sireum.Z16]))
    r.put(0xA7811BF8AB271D89L /* methodKey(F, "org.sireum.Random.Gen64", "nextZ32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextZ32Between(o1.asInstanceOf[org.sireum.Z32], o2.asInstanceOf[org.sireum.Z32]))
    r.put(0xB8CF2AF8220A9D15L /* methodKey(F, "org.sireum.Random.Gen64", "nextZ64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64].nextZ64Between(o1.asInstanceOf[org.sireum.Z64], o2.asInstanceOf[org.sireum.Z64]))
    r.put(0x76348B5C013AF62BL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextCBetween").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextCBetween(o1.asInstanceOf[org.sireum.C], o2.asInstanceOf[org.sireum.C]))
    r.put(0xF600F43938E2AA7BL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextZBetween").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextZBetween(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0xB9EE1BFE4227968CL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextF32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextF32Between(o1.asInstanceOf[org.sireum.F32], o2.asInstanceOf[org.sireum.F32]))
    r.put(0xE8274867D21CBCBBL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextF64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextF64Between(o1.asInstanceOf[org.sireum.F64], o2.asInstanceOf[org.sireum.F64]))
    r.put(0xEDDD587A35FB0078L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextRBetween").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextRBetween(o1.asInstanceOf[org.sireum.R], o2.asInstanceOf[org.sireum.R]))
    r.put(0x6952B96EAEF6A68DL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextU8Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextU8Between(o1.asInstanceOf[org.sireum.U8], o2.asInstanceOf[org.sireum.U8]))
    r.put(0xE1CE1B6E560D183FL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextU16Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextU16Between(o1.asInstanceOf[org.sireum.U16], o2.asInstanceOf[org.sireum.U16]))
    r.put(0x5456861DBA9A3F54L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextU32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextU32Between(o1.asInstanceOf[org.sireum.U32], o2.asInstanceOf[org.sireum.U32]))
    r.put(0x23EDCAEB3193BF68L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextU64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextU64Between(o1.asInstanceOf[org.sireum.U64], o2.asInstanceOf[org.sireum.U64]))
    r.put(0x01283EDCC7C983EEL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextN8Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextN8Between(o1.asInstanceOf[org.sireum.N8], o2.asInstanceOf[org.sireum.N8]))
    r.put(0x6ECB0ACC5C0F5CE2L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextN16Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextN16Between(o1.asInstanceOf[org.sireum.N16], o2.asInstanceOf[org.sireum.N16]))
    r.put(0xAEA432676BF4FFFFL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextN32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextN32Between(o1.asInstanceOf[org.sireum.N32], o2.asInstanceOf[org.sireum.N32]))
    r.put(0x6936E6C66C6161C9L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextN64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextN64Between(o1.asInstanceOf[org.sireum.N64], o2.asInstanceOf[org.sireum.N64]))
    r.put(0xC57A03809A59A291L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextS8Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextS8Between(o1.asInstanceOf[org.sireum.S8], o2.asInstanceOf[org.sireum.S8]))
    r.put(0xC5ECF5F942E31F65L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextS16Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextS16Between(o1.asInstanceOf[org.sireum.S16], o2.asInstanceOf[org.sireum.S16]))
    r.put(0x2E72D4BF8F4A5AABL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextS32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextS32Between(o1.asInstanceOf[org.sireum.S32], o2.asInstanceOf[org.sireum.S32]))
    r.put(0x3297AFB86FEC5FDAL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextS64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextS64Between(o1.asInstanceOf[org.sireum.S64], o2.asInstanceOf[org.sireum.S64]))
    r.put(0x4CF285298439D819L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ8Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextZ8Between(o1.asInstanceOf[org.sireum.Z8], o2.asInstanceOf[org.sireum.Z8]))
    r.put(0x7973643D49727353L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ16Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextZ16Between(o1.asInstanceOf[org.sireum.Z16], o2.asInstanceOf[org.sireum.Z16]))
    r.put(0xF12EE98E548C3293L /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ32Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextZ32Between(o1.asInstanceOf[org.sireum.Z32], o2.asInstanceOf[org.sireum.Z32]))
    r.put(0x412BF8793082D76AL /* methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ64Between").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Random.Gen64Impl].nextZ64Between(o1.asInstanceOf[org.sireum.Z64], o2.asInstanceOf[org.sireum.Z64]))
    r.put(0x254E4794134C66CEL /* methodKey(F, "org.sireum.UnionFind", "inSameSet").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.UnionFind[Any]].inSameSet(o1, o2))
    r.put(0x81CA0BCB569395A3L /* methodKey(F, "org.sireum.UnionFind", "inSameSetCompress").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.UnionFind[Any]].inSameSetCompress(o1, o2))
    r.put(0x00CE94D4772D3C2DL /* methodKey(F, "org.sireum.UnionFind", "merge").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.UnionFind[Any]].merge(o1, o2))
    r.put(0xA419F44D4B9054C8L /* methodKey(F, "org.sireum.GitHub.Repository", "submoduleShaOf").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.GitHub.Repository].submoduleShaOf(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String]))
    r.put(0xE1CDFE6DAFB04D1CL /* methodKey(F, "org.sireum.Init", "ideaDirPath").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Init].ideaDirPath(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B]))
    r.put(0x3DE404F7DAD376EFL /* methodKey(F, "org.sireum.Init", "zipName").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Init].zipName(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String]))
    r.put(0xC82368A1132E4391L /* methodKey(F, "org.sireum.Init", "downloadPlugins").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Init].downloadPlugins(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[((org.sireum.Init.Plugin) => org.sireum.B)]))
    r.put(0x603CA0A438AA20D2L /* methodKey(F, "org.sireum.Init", "extractPlugins").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Init].extractPlugins(o1.asInstanceOf[org.sireum.Os.Path], o2.asInstanceOf[((org.sireum.Init.Plugin) => org.sireum.B)]))
    r.put(0x2BDC90C1840281B1L /* methodKey(F, "org.sireum.Os.Path.Jen", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xA883C33DC3705736L /* methodKey(F, "org.sireum.Os.Path.Jen", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x25C9A6219BB27462L /* methodKey(F, "org.sireum.Os.Path.Jen", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0x124B90849498E509L /* methodKey(F, "org.sireum.Os.Path.MJen", "fold").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].fold(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0xD1ABFCD4B646E299L /* methodKey(F, "org.sireum.Os.Path.MJen", "foldLeft").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].foldLeft(o1, o2.asInstanceOf[((Any, Any) => Any)]))
    r.put(0x9829504A22CFA7B4L /* methodKey(F, "org.sireum.Os.Path.MJen", "slice").value */, receiverOpt => (o1: Any) => (o2: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].slice(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z]))
    r.put(0xB1C03B3B08BB65F4L /* methodKey(T, "org.sireum.Os.Proc.Result.Timeout", "apply").value */, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Proc.Result.Timeout.apply(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String]))
    r
  }

  private lazy val method3Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any](97)
    r.put(0x6FDEC8AB0C968E16L /* methodKey(T, "org.sireum.AssocS.Entries", "keyIndexOfFrom").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.AssocS.Entries.keyIndexOfFrom(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]], o2, o3.asInstanceOf[org.sireum.Z]))
    r.put(0x52BB07D48F688896L /* methodKey(T, "org.sireum.AssocS.Entries", "valueIndexOfFrom").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.AssocS.Entries.valueIndexOfFrom(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]], o2, o3.asInstanceOf[org.sireum.Z]))
    r.put(0x228EEDE99FBBA817L /* methodKey(T, "org.sireum.AssocS.Entries", "indexOfFrom").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.AssocS.Entries.indexOfFrom(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]], o2.asInstanceOf[(Any, Any)], o3.asInstanceOf[org.sireum.Z]))
    r.put(0x9322FB77BCD3AF80L /* methodKey(T, "org.sireum.AssocS.Entries", "addPost").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.AssocS.Entries.addPost(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]], o2.asInstanceOf[(Any, Any)], o3.asInstanceOf[org.sireum.IS[org.sireum.Z, (Any, Any)]]))
    r.put(0xD58358E378E71AC1L /* methodKey(T, "org.sireum.Sireum", "runWithInputAndReporter").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Sireum.runWithInputAndReporter(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.message.Reporter]))
    r.put(0x2E90B1694BECC8C8L /* methodKey(T, "org.sireum.ContractUtil", "isEqualExcept").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.ContractUtil.isEqualExcept(o1.asInstanceOf[org.sireum.IS[Any, Any]], o2.asInstanceOf[org.sireum.IS[Any, Any]], o3))
    r.put(0x0DDF533857299A0DL /* methodKey(T, "org.sireum.ContractUtil", "msEqualExcept").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.ContractUtil.msEqualExcept(o1.asInstanceOf[org.sireum.MS[Any, Any]], o2.asInstanceOf[org.sireum.MS[Any, Any]], o3))
    r.put(0xE3D2C75414BEA770L /* methodKey(T, "org.sireum.Graph.Internal", "addPlainEdge").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Graph.Internal.addPlainEdge(o1.asInstanceOf[org.sireum.Graph[Any, Any]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0x3509BAD41ADDA52BL /* methodKey(T, "org.sireum.Graph.Internal", "removeEdge").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Graph.Internal.removeEdge(o1.asInstanceOf[org.sireum.Graph[Any, Any]], o2.asInstanceOf[org.sireum.Graph.Internal.Edge[Any]], o3.asInstanceOf[org.sireum.Z]))
    r.put(0x1062A10279C277FFL /* methodKey(T, "org.sireum.Hash", "t1ha").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Hash.t1ha(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]], o3.asInstanceOf[org.sireum.U64]))
    r.put(0x4D73E8667D8597E5L /* methodKey(T, "org.sireum.Poset.Internal", "addParents").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Poset.Internal.addParents(o1.asInstanceOf[org.sireum.Poset[Any]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.Z]]))
    r.put(0xB1343508C6F553D7L /* methodKey(T, "org.sireum.Poset.Internal", "removeParent").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Poset.Internal.removeParent(o1.asInstanceOf[org.sireum.Poset[Any]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0x249BB802C7C46A2AL /* methodKey(T, "org.sireum.Poset.Internal", "addChildren").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Poset.Internal.addChildren(o1.asInstanceOf[org.sireum.Poset[Any]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.Z]]))
    r.put(0x0B8D1CDFFBF16C64L /* methodKey(T, "org.sireum.Poset.Internal", "ancestorsCache").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Poset.Internal.ancestorsCache(o1.asInstanceOf[org.sireum.Poset[Any]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.HashSMap[org.sireum.Z, org.sireum.HashSSet[org.sireum.Z]]]))
    r.put(0x1C02775232F5395DL /* methodKey(T, "org.sireum.Poset.Internal", "ancestorsRec").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Poset.Internal.ancestorsRec(o1.asInstanceOf[org.sireum.Poset[Any]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.HashSMap[org.sireum.Z, org.sireum.HashSSet[org.sireum.Z]]]))
    r.put(0xCB568988509D2993L /* methodKey(T, "org.sireum.Poset.Internal", "descendantsCache").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Poset.Internal.descendantsCache(o1.asInstanceOf[org.sireum.Poset[Any]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.HashSMap[org.sireum.Z, org.sireum.HashSSet[org.sireum.Z]]]))
    r.put(0xE9CE47CBFFF8DC34L /* methodKey(T, "org.sireum.Poset.Internal", "descendantsRec").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Poset.Internal.descendantsRec(o1.asInstanceOf[org.sireum.Poset[Any]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.HashSMap[org.sireum.Z, org.sireum.HashSSet[org.sireum.Z]]]))
    r.put(0xB614309C8FAD008CL /* methodKey(T, "org.sireum.Set.Elements", "indexOfFrom").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Set.Elements.indexOfFrom(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]], o2, o3.asInstanceOf[org.sireum.Z]))
    r.put(0x04583A070BBF6EC1L /* methodKey(T, "org.sireum.UnionFind.Internal", "merge").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.UnionFind.Internal.merge(o1.asInstanceOf[org.sireum.UnionFind[Any]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0x668E264D687C980BL /* methodKey(T, "org.sireum.Coursier", "fetch").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Coursier.fetch(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]], o3.asInstanceOf[org.sireum.Coursier.Proxy]))
    r.put(0x1C60971559E6B847L /* methodKey(T, "org.sireum.GitHub.Ext", "submoduleShaOf").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.GitHub.Ext.submoduleShaOf(o1.asInstanceOf[org.sireum.GitHub.Repository], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x46B352D8800F4CFAL /* methodKey(T, "org.sireum.Os.Ext", "chmod").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.chmod(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.B]))
    r.put(0x7DAC84684D93AAA4L /* methodKey(T, "org.sireum.Os.Ext", "copy").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.copy(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.B]))
    r.put(0x1775AC46D28B4F9EL /* methodKey(T, "org.sireum.Os.Ext", "move").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.move(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.B]))
    r.put(0x7FA7C5BCA7A2CA1FL /* methodKey(T, "org.sireum.Os.Ext", "write").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.write(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.Os.Path.WriteMode.Type]))
    r.put(0x9C94130A1DC57158L /* methodKey(T, "org.sireum.Os.Ext", "writeLineStream").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.writeLineStream(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.Jen[org.sireum.String]], o3.asInstanceOf[org.sireum.Os.Path.WriteMode.Type]))
    r.put(0xD014C73F8AAC3580L /* methodKey(T, "org.sireum.Os.Ext", "writeU8Stream").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.writeU8Stream(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.Jen[org.sireum.U8]], o3.asInstanceOf[org.sireum.Os.Path.WriteMode.Type]))
    r.put(0x25A99BBB5E3C63AAL /* methodKey(T, "org.sireum.Os.Ext", "writeCStream").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.writeCStream(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.Jen[org.sireum.C]], o3.asInstanceOf[org.sireum.Os.Path.WriteMode.Type]))
    r.put(0xF88709441D0321E9L /* methodKey(T, "org.sireum.Os.Ext", "writeLineMStream").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.writeLineMStream(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.MJen[org.sireum.String]], o3.asInstanceOf[org.sireum.Os.Path.WriteMode.Type]))
    r.put(0xBCE34BEF7DFA0590L /* methodKey(T, "org.sireum.Os.Ext", "writeU8MStream").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.writeU8MStream(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.MJen[org.sireum.U8]], o3.asInstanceOf[org.sireum.Os.Path.WriteMode.Type]))
    r.put(0x31DA590A38D5287BL /* methodKey(T, "org.sireum.Os.Ext", "writeCMStream").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.writeCMStream(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.MJen[org.sireum.C]], o3.asInstanceOf[org.sireum.Os.Path.WriteMode.Type]))
    r.put(0xD8B223B08AD2918DL /* methodKey(T, "org.sireum.Scalafmt", "format").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Scalafmt.format(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.B], o3.asInstanceOf[org.sireum.String]))
    r.put(0x184C956AEF36DBA4L /* methodKey(T, "org.sireum.Graph.Edge.Data", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Graph.Edge.Data.apply(o1, o2, o3))
    r.put(0x16CDC2B2E5E9D85EL /* methodKey(T, "org.sireum.Graph.Internal.Edge.Data", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Graph.Internal.Edge.Data.apply(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.Z], o3))
    r.put(0x0C458229D3378884L /* methodKey(F, "org.sireum.Graph", "addDataEdge").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].addDataEdge(o1, o2, o3))
    r.put(0x444C9E8719867F4CL /* methodKey(F, "org.sireum.Graph", "toST").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Graph[Any, Any]].toST(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.ST]], o2.asInstanceOf[((Any) => org.sireum.ST)], o3.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0x787367F096995B9EL /* methodKey(F, "org.sireum.Jen", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xA15BD8534D05E3C8L /* methodKey(F, "org.sireum.Jen.Internal.ISImpl", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ISImpl[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x963ED3095A0760EFL /* methodKey(F, "org.sireum.Jen.Internal.MapImpl", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.MapImpl[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xD12B484C1464F62CL /* methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.HashMapImpl[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x46CEEE35D0A4999EL /* methodKey(F, "org.sireum.Jen.Internal.Filtered", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Filtered[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x5809E4E98609034BL /* methodKey(F, "org.sireum.Jen.Internal.Mapped", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Mapped[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x6FA79256BAD2305CL /* methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.FlatMapped[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x1F20A70027EF46F5L /* methodKey(T, "org.sireum.Jen.Internal.Sliced", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Jen.Internal.Sliced.apply(o1.asInstanceOf[org.sireum.Jen[Any]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0x366958C953A00A9BL /* methodKey(F, "org.sireum.Jen.Internal.Sliced", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Sliced[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xDE1B4DBF8E44109FL /* methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.TakeWhile[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x81C510FD69E84D4FL /* methodKey(F, "org.sireum.Jen.Internal.DropWhile", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.DropWhile[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xA4F1B8CC410EDF1EL /* methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.ZipWithIndexed[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x5C09C89C1C261A8DL /* methodKey(F, "org.sireum.Jen.Internal.Zipped", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Zipped[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x48CA3BC611DBA95BL /* methodKey(F, "org.sireum.Jen.Internal.Concat", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Concat[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x8B927C84275DB772L /* methodKey(F, "org.sireum.Jen.Internal.Product", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Jen.Internal.Product[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xEAF1B28C0EBD1142L /* methodKey(T, "org.sireum.MBox3", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.MBox3.apply(o1, o2, o3))
    r.put(0x01A7AF73C280A31DL /* methodKey(F, "org.sireum.MJen", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xF3278A167FFC72D6L /* methodKey(F, "org.sireum.MJen.Internal.ISImpl", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ISImpl[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x37BDEB23C31F7566L /* methodKey(F, "org.sireum.MJen.Internal.MSImpl", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MSImpl[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x7BF58DDBC33252E0L /* methodKey(F, "org.sireum.MJen.Internal.MapImpl", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.MapImpl[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xA5617F60E2C354EDL /* methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.HashMapImpl[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xC27613A6B3517AB3L /* methodKey(F, "org.sireum.MJen.Internal.Filtered", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Filtered[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xA09E153FB6FD9692L /* methodKey(F, "org.sireum.MJen.Internal.Mapped", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Mapped[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xDA3586F855F642FBL /* methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.FlatMapped[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x9008587C42D2459FL /* methodKey(T, "org.sireum.MJen.Internal.Sliced", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.MJen.Internal.Sliced.apply(o1.asInstanceOf[org.sireum.MJen[Any]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0x140A902880F8A1C3L /* methodKey(F, "org.sireum.MJen.Internal.Sliced", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Sliced[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x98CB5F797BCFEC8FL /* methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.TakeWhile[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xD809B82E86AD5FEFL /* methodKey(F, "org.sireum.MJen.Internal.DropWhile", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.DropWhile[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x92C286595BE13E0DL /* methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.ZipWithIndexed[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xA1FA8195943CC5D1L /* methodKey(F, "org.sireum.MJen.Internal.Zipped", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Zipped[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xF342D7048857B3D3L /* methodKey(F, "org.sireum.MJen.Internal.Concat", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Concat[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x05BE6A5CF5B9FC9BL /* methodKey(F, "org.sireum.MJen.Internal.Product", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.MJen.Internal.Product[Any, Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x682510235FFB2F29L /* methodKey(F, "org.sireum.ObjPrinter", "addMethod").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].addMethod(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.B], o3.asInstanceOf[org.sireum.ST]))
    r.put(0xC774C4E905CD863DL /* methodKey(F, "org.sireum.ObjPrinter", "printISZ").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printISZ(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]], o3.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0x7DF8D8888E89477EL /* methodKey(F, "org.sireum.ObjPrinter", "printSet").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printSet(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.Set[Any]], o3.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0xE6EBD88FE91BC38CL /* methodKey(F, "org.sireum.ObjPrinter", "printHashSet").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printHashSet(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.HashSet[Any]], o3.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0x9D64A06762FFE355L /* methodKey(F, "org.sireum.ObjPrinter", "printHashSSet").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printHashSSet(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.HashSSet[Any]], o3.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0x473CAD133AED46A0L /* methodKey(F, "org.sireum.ObjPrinter", "printStack").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printStack(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.Stack[Any]], o3.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0xD9B7D4E75D513C07L /* methodKey(F, "org.sireum.ObjPrinter", "printBag").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printBag(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.Bag[Any]], o3.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0xF8FC1F9FCAC4B392L /* methodKey(F, "org.sireum.ObjPrinter", "printHashBag").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printHashBag(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.HashBag[Any]], o3.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0x7AA56F850105BB51L /* methodKey(F, "org.sireum.ObjPrinter", "printHashSBag").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printHashSBag(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.HashSBag[Any]], o3.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0x0CFC490B08B4B9D6L /* methodKey(F, "org.sireum.ObjPrinter", "printPoset").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printPoset(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.Poset[Any]], o3.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0x851B1A8A32CFFEC4L /* methodKey(F, "org.sireum.ObjPrinter", "printUnionFind").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printUnionFind(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.UnionFind[Any]], o3.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0xC24A2E235997D748L /* methodKey(T, "org.sireum.GitHub.Repository", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.GitHub.Repository.apply(o1.asInstanceOf[org.sireum.Option[org.sireum.GitHub.Credential]], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xDA6C2AA0F5002E98L /* methodKey(T, "org.sireum.Init", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Init.apply(o1.asInstanceOf[org.sireum.Os.Path], o2.asInstanceOf[org.sireum.Os.Kind.Type], o3.asInstanceOf[org.sireum.Map[org.sireum.String, org.sireum.String]]))
    r.put(0xCE6EED424CD35C08L /* methodKey(F, "org.sireum.Init", "ideaPlugins").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Init].ideaPlugins(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B], o3.asInstanceOf[org.sireum.Option[org.sireum.Os.Path]]))
    r.put(0x5B2DF5DFB4F876F2L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeU8Parts").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeU8Parts(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0x69E527D13D6CA649L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeOverU8Parts").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeOverU8Parts(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0xD4876598C0D4A522L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendU8Parts").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeAppendU8Parts(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0xF1D3CC5C7F56BBD8L /* methodKey(F, "org.sireum.Os.Path.Impl", "writeU8Partms").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeU8Partms(o1.asInstanceOf[org.sireum.MS[org.sireum.Z, org.sireum.U8]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0x20F0EB502EDED45DL /* methodKey(F, "org.sireum.Os.Path.Impl", "writeOverU8Partms").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeOverU8Partms(o1.asInstanceOf[org.sireum.MS[org.sireum.Z, org.sireum.U8]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0xD1095420663E952EL /* methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendU8Partms").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].writeAppendU8Partms(o1.asInstanceOf[org.sireum.MS[org.sireum.Z, org.sireum.U8]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0x2D7ACDB284BE9041L /* methodKey(F, "org.sireum.Os.Path.Jen", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Jen[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x5DAE25747AEE6BA1L /* methodKey(F, "org.sireum.Os.Path.MJen", "mkStringWrap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.MJen[Any]].mkStringWrap(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0xC510A095F9E1E58DL /* methodKey(T, "org.sireum.Os.Proc.Result.Normal", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Proc.Result.Normal.apply(o1.asInstanceOf[org.sireum.Z], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String]))
    r.put(0x141CAD03B7C461B2L /* methodKey(F, "org.sireum.Os.Path", "writeU8Parts").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeU8Parts(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0x3CE9773FD4E1E75DL /* methodKey(F, "org.sireum.Os.Path", "writeOverU8Parts").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeOverU8Parts(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0x3482D1D12596E8E4L /* methodKey(F, "org.sireum.Os.Path", "writeAppendU8Parts").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeAppendU8Parts(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0xC2E531A0CA90523EL /* methodKey(F, "org.sireum.Os.Path", "writeU8Partms").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeU8Partms(o1.asInstanceOf[org.sireum.MS[org.sireum.Z, org.sireum.U8]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0x30F40B8AC19DE4AAL /* methodKey(F, "org.sireum.Os.Path", "writeOverU8Partms").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeOverU8Partms(o1.asInstanceOf[org.sireum.MS[org.sireum.Z, org.sireum.U8]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r.put(0xD5C0119A35696389L /* methodKey(F, "org.sireum.Os.Path", "writeAppendU8Partms").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].writeAppendU8Partms(o1.asInstanceOf[org.sireum.MS[org.sireum.Z, org.sireum.U8]], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.Z]))
    r
  }

  private lazy val method4Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any](12)
    r.put(0x06CE4C1C6299A4B7L /* methodKey(T, "org.sireum.ContractUtil", "isEqualExcept2").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.ContractUtil.isEqualExcept2(o1.asInstanceOf[org.sireum.IS[Any, Any]], o2.asInstanceOf[org.sireum.IS[Any, Any]], o3, o4))
    r.put(0x462192823D0C380DL /* methodKey(T, "org.sireum.ContractUtil", "msEqualExcept2").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.ContractUtil.msEqualExcept2(o1.asInstanceOf[org.sireum.MS[Any, Any]], o2.asInstanceOf[org.sireum.MS[Any, Any]], o3, o4))
    r.put(0x42524DE40C4B0149L /* methodKey(T, "org.sireum.Graph.Internal", "addDataEdge").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Graph.Internal.addDataEdge(o1.asInstanceOf[org.sireum.Graph[Any, Any]], o2, o3.asInstanceOf[org.sireum.Z], o4.asInstanceOf[org.sireum.Z]))
    r.put(0x1F8DA6713740AC23L /* methodKey(T, "org.sireum.MBox4", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.MBox4.apply(o1, o2, o3, o4))
    r.put(0xE6761393B87A0B27L /* methodKey(T, "org.sireum.Poset", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Poset.apply(o1.asInstanceOf[org.sireum.HashSMap[Any, org.sireum.Z]], o2.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]], o3.asInstanceOf[org.sireum.HashSMap[org.sireum.Z, org.sireum.HashSSet[org.sireum.Z]]], o4.asInstanceOf[org.sireum.HashSMap[org.sireum.Z, org.sireum.HashSSet[org.sireum.Z]]]))
    r.put(0x7459C34E401B215FL /* methodKey(T, "org.sireum.Random.Impl.Xoshiro256", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Random.Impl.Xoshiro256.apply(o1.asInstanceOf[org.sireum.U64], o2.asInstanceOf[org.sireum.U64], o3.asInstanceOf[org.sireum.U64], o4.asInstanceOf[org.sireum.U64]))
    r.put(0x8B1A2EB12D18417EL /* methodKey(T, "org.sireum.Random.Impl.Xoroshiro128", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Random.Impl.Xoroshiro128.apply(o1.asInstanceOf[org.sireum.U32], o2.asInstanceOf[org.sireum.U32], o3.asInstanceOf[org.sireum.U32], o4.asInstanceOf[org.sireum.U32]))
    r.put(0xC71F3B113F1B0EEEL /* methodKey(T, "org.sireum.UnionFind", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.UnionFind.apply(o1.asInstanceOf[org.sireum.HashSMap[Any, org.sireum.Z]], o2.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]], o3.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.Z]], o4.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.Z]]))
    r.put(0x2671667D67ADD78FL /* methodKey(T, "org.sireum.CoursierFileInfo", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.CoursierFileInfo.apply(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.String], o4.asInstanceOf[org.sireum.String]))
    r.put(0x0AC204F233E5D49EL /* methodKey(T, "org.sireum.Init.Plugin", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Init.Plugin.apply(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.B], o3.asInstanceOf[org.sireum.B], o4.asInstanceOf[org.sireum.String]))
    r.put(0x02E547788D981E95L /* methodKey(F, "org.sireum.Init", "ideaConfig").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => receiverOpt.get.asInstanceOf[org.sireum.Init].ideaConfig(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B], o3.asInstanceOf[org.sireum.B], o4.asInstanceOf[org.sireum.Option[org.sireum.Os.Path]]))
    r.put(0x05BE5790F8372819L /* methodKey(F, "org.sireum.Init", "distro").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => receiverOpt.get.asInstanceOf[org.sireum.Init].distro(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.B], o3.asInstanceOf[org.sireum.B], o4.asInstanceOf[org.sireum.B]))
    r
  }

  private lazy val method5Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any](14)
    r.put(0xDA827F8B92A80AC2L /* methodKey(T, "org.sireum.Coursier", "commandPrefix").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => org.sireum.Coursier.commandPrefix(o1.asInstanceOf[org.sireum.B], o2.asInstanceOf[org.sireum.String], o3.asInstanceOf[org.sireum.Option[org.sireum.Os.Path]], o4.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]], o5.asInstanceOf[org.sireum.Coursier.Proxy]))
    r.put(0xDE4F36C302DFCBC4L /* methodKey(T, "org.sireum.Os.Ext", "writeU8s").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => org.sireum.Os.Ext.writeU8s(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.U8]], o3.asInstanceOf[org.sireum.Z], o4.asInstanceOf[org.sireum.Z], o5.asInstanceOf[org.sireum.Os.Path.WriteMode.Type]))
    r.put(0x683DDCC0FF753645L /* methodKey(T, "org.sireum.Os.Ext", "writeU8ms").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => org.sireum.Os.Ext.writeU8ms(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.MS[org.sireum.Z, org.sireum.U8]], o3.asInstanceOf[org.sireum.Z], o4.asInstanceOf[org.sireum.Z], o5.asInstanceOf[org.sireum.Os.Path.WriteMode.Type]))
    r.put(0x74183BA1134AC95EL /* methodKey(T, "org.sireum.MBox5", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => org.sireum.MBox5.apply(o1, o2, o3, o4, o5))
    r.put(0x9E0FDFFEA6E86BC1L /* methodKey(F, "org.sireum.ObjPrinter", "printIS").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printIS(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.ST], o3.asInstanceOf[org.sireum.IS[Any, Any]], o4.asInstanceOf[((Any) => org.sireum.ST)], o5.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0x1D79701D6408C907L /* methodKey(F, "org.sireum.ObjPrinter", "printMS").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printMS(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.ST], o3.asInstanceOf[org.sireum.MS[Any, Any]], o4.asInstanceOf[((Any) => org.sireum.ST)], o5.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0x1C23120B4E0C7260L /* methodKey(F, "org.sireum.ObjPrinter", "printMap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printMap(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.ST], o3.asInstanceOf[org.sireum.Map[Any, Any]], o4.asInstanceOf[((Any) => org.sireum.ST)], o5.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0xE8348A9E975D463AL /* methodKey(F, "org.sireum.ObjPrinter", "printHashMap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printHashMap(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.ST], o3.asInstanceOf[org.sireum.HashMap[Any, Any]], o4.asInstanceOf[((Any) => org.sireum.ST)], o5.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0xFA866B06D8002275L /* methodKey(F, "org.sireum.ObjPrinter", "printHashSMap").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printHashSMap(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.ST], o3.asInstanceOf[org.sireum.HashSMap[Any, Any]], o4.asInstanceOf[((Any) => org.sireum.ST)], o5.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0x0DBB68BCF918CC73L /* methodKey(F, "org.sireum.ObjPrinter", "printGraph").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => receiverOpt.get.asInstanceOf[org.sireum.ObjPrinter].printGraph(o1.asInstanceOf[org.sireum.ST], o2.asInstanceOf[org.sireum.ST], o3.asInstanceOf[org.sireum.Graph[Any, Any]], o4.asInstanceOf[((Any) => org.sireum.ST)], o5.asInstanceOf[((Any) => org.sireum.ST)]))
    r.put(0x74F0C80EA75C4D63L /* methodKey(F, "org.sireum.Os.Path.Impl", "overlayCopy").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].overlayCopy(o1.asInstanceOf[org.sireum.Os.Path], o2.asInstanceOf[org.sireum.B], o3.asInstanceOf[org.sireum.B], o4.asInstanceOf[((org.sireum.Os.Path) => org.sireum.B)], o5.asInstanceOf[org.sireum.B]))
    r.put(0xC715DEB4676AE509L /* methodKey(F, "org.sireum.Os.Path.Impl", "overlayMove").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path.Impl].overlayMove(o1.asInstanceOf[org.sireum.Os.Path], o2.asInstanceOf[org.sireum.B], o3.asInstanceOf[org.sireum.B], o4.asInstanceOf[((org.sireum.Os.Path) => org.sireum.B)], o5.asInstanceOf[org.sireum.B]))
    r.put(0xE5454CF7FC6199B2L /* methodKey(F, "org.sireum.Os.Path", "overlayCopy").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].overlayCopy(o1.asInstanceOf[org.sireum.Os.Path], o2.asInstanceOf[org.sireum.B], o3.asInstanceOf[org.sireum.B], o4.asInstanceOf[((org.sireum.Os.Path) => org.sireum.B)], o5.asInstanceOf[org.sireum.B]))
    r.put(0x8EFC011C607080A7L /* methodKey(F, "org.sireum.Os.Path", "overlayMove").value */, receiverOpt => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => receiverOpt.get.asInstanceOf[org.sireum.Os.Path].overlayMove(o1.asInstanceOf[org.sireum.Os.Path], o2.asInstanceOf[org.sireum.B], o3.asInstanceOf[org.sireum.B], o4.asInstanceOf[((org.sireum.Os.Path) => org.sireum.B)], o5.asInstanceOf[org.sireum.B]))
    r
  }

  private lazy val method6Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any](5)
    r.put(0x4FA7CB1E41AEA038L /* methodKey(T, "org.sireum.Coursier", "resolve").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => org.sireum.Coursier.resolve(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.Option[org.sireum.Os.Path]], o3.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]], o4.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]], o5.asInstanceOf[org.sireum.B], o6.asInstanceOf[org.sireum.Coursier.Proxy]))
    r.put(0x953C464460D14B29L /* methodKey(T, "org.sireum.Coursier", "fetchClassifiers").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => org.sireum.Coursier.fetchClassifiers(o1.asInstanceOf[org.sireum.String], o2.asInstanceOf[org.sireum.Option[org.sireum.Os.Path]], o3.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]], o4.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]], o5.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.CoursierClassifier.Type]], o6.asInstanceOf[org.sireum.Coursier.Proxy]))
    r.put(0x0A7E12ED73F8644FL /* methodKey(T, "org.sireum.Graph", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => org.sireum.Graph.apply(o1.asInstanceOf[org.sireum.HashSMap[Any, org.sireum.Z]], o2.asInstanceOf[org.sireum.IS[org.sireum.Z, Any]], o3.asInstanceOf[org.sireum.HashSMap[org.sireum.Z, org.sireum.Graph.Internal.Edges[Any]]], o4.asInstanceOf[org.sireum.HashSMap[org.sireum.Z, org.sireum.Graph.Internal.Edges[Any]]], o5.asInstanceOf[org.sireum.Z], o6.asInstanceOf[org.sireum.B]))
    r.put(0xE8EF57F0E31EDDF8L /* methodKey(T, "org.sireum.MBox6", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => org.sireum.MBox6.apply(o1, o2, o3, o4, o5, o6))
    r.put(0xF6A1101DA2660EB3L /* methodKey(T, "org.sireum.Coursier.Proxy", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => org.sireum.Coursier.Proxy.apply(o1.asInstanceOf[org.sireum.Option[org.sireum.String]], o2.asInstanceOf[org.sireum.Option[org.sireum.String]], o3.asInstanceOf[org.sireum.Option[org.sireum.String]], o4.asInstanceOf[org.sireum.Option[org.sireum.String]], o5.asInstanceOf[org.sireum.Option[org.sireum.String]], o6.asInstanceOf[org.sireum.Option[org.sireum.String]]))
    r
  }

  private lazy val method7Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any](4)
    r.put(0xD3D12C034D283665L /* methodKey(T, "org.sireum.CircularQueue.NoDrop", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => org.sireum.CircularQueue.NoDrop.apply(o1.asInstanceOf[org.sireum.Z], o2, o3.asInstanceOf[org.sireum.B], o4.asInstanceOf[org.sireum.MS[org.sireum.Z, Any]], o5.asInstanceOf[org.sireum.Z], o6.asInstanceOf[org.sireum.Z], o7.asInstanceOf[org.sireum.Z]))
    r.put(0xA9BB46281F064D95L /* methodKey(T, "org.sireum.CircularQueue.DropFront", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => org.sireum.CircularQueue.DropFront.apply(o1.asInstanceOf[org.sireum.Z], o2, o3.asInstanceOf[org.sireum.B], o4.asInstanceOf[org.sireum.MS[org.sireum.Z, Any]], o5.asInstanceOf[org.sireum.Z], o6.asInstanceOf[org.sireum.Z], o7.asInstanceOf[org.sireum.Z]))
    r.put(0xB1C12AC490122F88L /* methodKey(T, "org.sireum.CircularQueue.DropRear", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => org.sireum.CircularQueue.DropRear.apply(o1.asInstanceOf[org.sireum.Z], o2, o3.asInstanceOf[org.sireum.B], o4.asInstanceOf[org.sireum.MS[org.sireum.Z, Any]], o5.asInstanceOf[org.sireum.Z], o6.asInstanceOf[org.sireum.Z], o7.asInstanceOf[org.sireum.Z]))
    r.put(0x6BE67996FFA234D4L /* methodKey(T, "org.sireum.MBox7", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => org.sireum.MBox7.apply(o1, o2, o3, o4, o5, o6, o7))
    r
  }

  private lazy val method8Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0xCA514E40F5139C68L /* methodKey(T, "org.sireum.MBox8", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => org.sireum.MBox8.apply(o1, o2, o3, o4, o5, o6, o7, o8))
    r
  }

  private lazy val method9Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](2)
    r.put(0x6A2DDA75A4C30EB0L /* methodKey(T, "org.sireum.MBox9", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => org.sireum.MBox9.apply(o1, o2, o3, o4, o5, o6, o7, o8, o9))
    r.put(0xBD4A2EF600F586C1L /* methodKey(T, "org.sireum.GitHub.Asset", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => org.sireum.GitHub.Asset.apply(o1.asInstanceOf[org.sireum.GitHub.Release], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.String], o4.asInstanceOf[org.sireum.String], o5.asInstanceOf[org.sireum.String], o6.asInstanceOf[org.sireum.Z], o7.asInstanceOf[org.sireum.String], o8.asInstanceOf[org.sireum.String], o9.asInstanceOf[org.sireum.Z]))
    r
  }

  private lazy val method10Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](2)
    r.put(0x8BB1B93F03C8898BL /* methodKey(T, "org.sireum.MBox10", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => org.sireum.MBox10.apply(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10))
    r.put(0xD0E329570385C52DL /* methodKey(T, "org.sireum.GitHub.Release", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => org.sireum.GitHub.Release.apply(o1.asInstanceOf[org.sireum.GitHub.Repository], o2.asInstanceOf[org.sireum.Z], o3.asInstanceOf[org.sireum.String], o4.asInstanceOf[org.sireum.Z], o5.asInstanceOf[org.sireum.B], o6.asInstanceOf[org.sireum.B], o7.asInstanceOf[org.sireum.String], o8.asInstanceOf[org.sireum.String], o9.asInstanceOf[org.sireum.String], o10.asInstanceOf[org.sireum.String]))
    r
  }

  private lazy val method11Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0x2B2172B798271894L /* methodKey(T, "org.sireum.MBox11", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => org.sireum.MBox11.apply(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11))
    r
  }

  private lazy val method12Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0x782BF8EFC1D69E3AL /* methodKey(T, "org.sireum.MBox12", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => org.sireum.MBox12.apply(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12))
    r
  }

  private lazy val method13Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0x9A8DDB9D2BD004C0L /* methodKey(T, "org.sireum.MBox13", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => org.sireum.MBox13.apply(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13))
    r
  }

  private lazy val method14Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0xF8242534A881F319L /* methodKey(T, "org.sireum.MBox14", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => org.sireum.MBox14.apply(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14))
    r
  }

  private lazy val method15Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](2)
    r.put(0xF4ACF715C69B9ADEL /* methodKey(T, "org.sireum.MBox15", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => org.sireum.MBox15.apply(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15))
    r.put(0xFB019D05939EA79BL /* methodKey(T, "org.sireum.Os.Proc", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => org.sireum.Os.Proc.apply(o1.asInstanceOf[org.sireum.IS[org.sireum.Z, org.sireum.String]], o2.asInstanceOf[org.sireum.Os.Path], o3.asInstanceOf[org.sireum.IS[org.sireum.Z, (org.sireum.String, org.sireum.String)]], o4.asInstanceOf[org.sireum.B], o5.asInstanceOf[org.sireum.Option[org.sireum.String]], o6.asInstanceOf[org.sireum.B], o7.asInstanceOf[org.sireum.B], o8.asInstanceOf[org.sireum.B], o9.asInstanceOf[org.sireum.B], o10.asInstanceOf[org.sireum.B], o11.asInstanceOf[org.sireum.Z], o12.asInstanceOf[org.sireum.B], o13.asInstanceOf[org.sireum.B], o14.asInstanceOf[org.sireum.Option[org.sireum.Os.Proc.LineFilter]], o15.asInstanceOf[org.sireum.Option[org.sireum.Os.Proc.LineFilter]]))
    r
  }

  private lazy val method16Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0x0FE1479BF9373CB5L /* methodKey(T, "org.sireum.MBox16", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => (o16: Any) => org.sireum.MBox16.apply(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16))
    r
  }

  private lazy val method17Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0xAB525661561D8484L /* methodKey(T, "org.sireum.MBox17", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => (o16: Any) => (o17: Any) => org.sireum.MBox17.apply(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17))
    r
  }

  private lazy val method18Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0xA51A10B8273F879EL /* methodKey(T, "org.sireum.MBox18", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => (o16: Any) => (o17: Any) => (o18: Any) => org.sireum.MBox18.apply(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18))
    r
  }

  private lazy val method19Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0xDE5423BF02F065D3L /* methodKey(T, "org.sireum.MBox19", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => (o16: Any) => (o17: Any) => (o18: Any) => (o19: Any) => org.sireum.MBox19.apply(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19))
    r
  }

  private lazy val method20Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0x93254705C04B46D0L /* methodKey(T, "org.sireum.MBox20", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => (o16: Any) => (o17: Any) => (o18: Any) => (o19: Any) => (o20: Any) => org.sireum.MBox20.apply(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20))
    r
  }

  private lazy val method21Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0x938D74B129B20BA0L /* methodKey(T, "org.sireum.MBox21", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => (o16: Any) => (o17: Any) => (o18: Any) => (o19: Any) => (o20: Any) => (o21: Any) => org.sireum.MBox21.apply(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20, o21))
    r
  }

  private lazy val method22Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0x593E3FC00A2EF78AL /* methodKey(T, "org.sireum.MBox22", "apply").value */, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => (o16: Any) => (o17: Any) => (o18: Any) => (o19: Any) => (o20: Any) => (o21: Any) => (o22: Any) => org.sireum.MBox22.apply(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20, o21, o22))
    r
  }

  private def illegalReflection(title: String, isInObject: B, owner: String, name: String): Unit = {
    halt(s"$title reflection $owner${if (isInObject) "." else "#"}$name")
  }

  override def kind(name: String): Option[Info] = {
    val r = nameMap.get(objectOrTypeKey(name).value)
    if (r == null) None() else Some(r)
  }

  override def invoke0[T, R](owner: String, name: String, receiverOpt: Option[T]): R = {
    val f = method0Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]]).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke1[T, T1, R](owner: String, name: String, receiverOpt: Option[T], o1: T1): R = {
    val f = method1Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke2[T, T1, T2, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2): R = {
    val f = method2Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke3[T, T1, T2, T3, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3): R = {
    val f = method3Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke4[T, T1, T2, T3, T4, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4): R = {
    val f = method4Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke5[T, T1, T2, T3, T4, T5, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5): R = {
    val f = method5Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke6[T, T1, T2, T3, T4, T5, T6, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6): R = {
    val f = method6Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke7[T, T1, T2, T3, T4, T5, T6, T7, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7): R = {
    val f = method7Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke8[T, T1, T2, T3, T4, T5, T6, T7, T8, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8): R = {
    val f = method8Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke9[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9): R = {
    val f = method9Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke10[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10): R = {
    val f = method10Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke11[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11): R = {
    val f = method11Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke12[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12): R = {
    val f = method12Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke13[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13): R = {
    val f = method13Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke14[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14): R = {
    val f = method14Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke15[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15): R = {
    val f = method15Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke16[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16): R = {
    val f = method16Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15)(o16).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke17[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17): R = {
    val f = method17Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15)(o16)(o17).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke18[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18): R = {
    val f = method18Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15)(o16)(o17)(o18).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke19[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19): R = {
    val f = method19Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15)(o16)(o17)(o18)(o19).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke20[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20): R = {
    val f = method20Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15)(o16)(o17)(o18)(o19)(o20).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke21[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21): R = {
    val f = method21Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15)(o16)(o17)(o18)(o19)(o20)(o21).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  override def invoke22[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](owner: String, name: String, receiverOpt: Option[T], o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22): R = {
    val f = method22Map.get(methodKey(receiverOpt.isEmpty, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", receiverOpt.isEmpty, owner, name)
    }
    val r = f(receiverOpt.asInstanceOf[Option[Object]])(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15)(o16)(o17)(o18)(o19)(o20)(o21)(o22).asInstanceOf[R]
    if (r == null) {
      illegalReflection("Invalid", receiverOpt.isEmpty, owner, name)
    }
    r
  }

  def info0 = Info( // org.sireum.AssocS.Entries
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "uniqueKeys", params = ISZ("entries")),
      Method(isInObject = true, isByName = F, name = "contain", params = ISZ("entries", "kv")),
      Method(isInObject = true, isByName = F, name = "containKey", params = ISZ("entries", "key")),
      Method(isInObject = true, isByName = F, name = "containValue", params = ISZ("entries", "value")),
      Method(isInObject = true, isByName = F, name = "keyIndexOfFrom", params = ISZ("entries", "key", "from")),
      Method(isInObject = true, isByName = F, name = "valueIndexOfFrom", params = ISZ("entries", "value", "from")),
      Method(isInObject = true, isByName = F, name = "indexOfFrom", params = ISZ("entries", "kv", "from")),
      Method(isInObject = true, isByName = F, name = "keys", params = ISZ("entries")),
      Method(isInObject = true, isByName = F, name = "values", params = ISZ("entries")),
      Method(isInObject = true, isByName = F, name = "addPost", params = ISZ("entries", "p", "res")),
      Method(isInObject = true, isByName = F, name = "add", params = ISZ("entries", "p")),
      Method(isInObject = true, isByName = F, name = "indexOf", params = ISZ("entries", "key")),
      Method(isInObject = true, isByName = F, name = "remove", params = ISZ("entries", "p"))
    )
  )

  def info1 = Info( // org.sireum.Sireum
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

  def info2 = Info( // org.sireum.ContractUtil
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "modPos", params = ISZ("n", "m")),
      Method(isInObject = true, isByName = F, name = "modNeg", params = ISZ("n", "m")),
      Method(isInObject = true, isByName = F, name = "isEqualExcept", params = ISZ("s1", "s2", "i")),
      Method(isInObject = true, isByName = F, name = "isEqualExcept2", params = ISZ("s1", "s2", "i1", "i2")),
      Method(isInObject = true, isByName = F, name = "msEqualExcept", params = ISZ("s1", "s2", "i")),
      Method(isInObject = true, isByName = F, name = "msEqualExcept2", params = ISZ("s1", "s2", "i1", "i2")),
      Method(isInObject = true, isByName = F, name = "isAllIS", params = ISZ("s", "e")),
      Method(isInObject = true, isByName = F, name = "isAllMS", params = ISZ("s", "e"))
    )
  )

  def info3 = Info( // org.sireum.Graph.Internal
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "addEdge", params = ISZ("g", "e")),
      Method(isInObject = true, isByName = F, name = "addPlainEdge", params = ISZ("g", "src", "dst")),
      Method(isInObject = true, isByName = F, name = "addDataEdge", params = ISZ("g", "data", "src", "dst")),
      Method(isInObject = true, isByName = F, name = "removeEdge", params = ISZ("g", "e", "n")),
      Method(isInObject = true, isByName = F, name = "incoming", params = ISZ("g", "dst")),
      Method(isInObject = true, isByName = F, name = "outgoing", params = ISZ("g", "src"))
    )
  )

  def info4 = Info( // org.sireum.Hash
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "murmur3a", params = ISZ("data", "seed")),
      Method(isInObject = true, isByName = F, name = "t1ha0", params = ISZ("data", "seed")),
      Method(isInObject = true, isByName = F, name = "t1ha", params = ISZ("isFirst", "data", "seed"))
    )
  )

  def info5 = Info( // org.sireum.Jen.Internal
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info6 = Info( // org.sireum.LibUtil
    kind = Kind.Object,
    fields = ISZ(
      Field(isInObject = true, isVal = T, kind = Field.Kind.Normal, name = "setOptions")
    ),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "parCores", params = ISZ("maxCores", "percentage")),
      Method(isInObject = true, isByName = F, name = "parCoresOpt", params = ISZ("maxCores", "percentageOpt")),
      Method(isInObject = true, isByName = F, name = "mineOptions", params = ISZ("fileContent")),
      Method(isInObject = true, isByName = F, name = "mineOptionsWithPrefix", params = ISZ("prefix", "fileContent"))
    )
  )

  def info7 = Info( // org.sireum.LibUtil.IS
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "unique", params = ISZ("s"))
    )
  )

  def info8 = Info( // org.sireum.Library
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = T, name = "sharedFiles", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "files", params = ISZ())
    )
  )

  def info9 = Info( // org.sireum.MJen.Internal
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info10 = Info( // org.sireum.Map.Entries
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info11 = Info( // org.sireum.OsProto
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info12 = Info( // org.sireum.Poset.Internal
    kind = Kind.Object,
    fields = ISZ(
      Field(isInObject = true, isVal = T, kind = Field.Kind.Normal, name = "emptySet")
    ),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "addNode", params = ISZ("poset", "node")),
      Method(isInObject = true, isByName = F, name = "addNodes", params = ISZ("poset", "nodes")),
      Method(isInObject = true, isByName = F, name = "addParents", params = ISZ("poset", "n", "ns")),
      Method(isInObject = true, isByName = F, name = "removeParent", params = ISZ("poset", "n", "parent")),
      Method(isInObject = true, isByName = F, name = "addChildren", params = ISZ("poset", "n", "ns")),
      Method(isInObject = true, isByName = F, name = "childrenOf", params = ISZ("poset", "n")),
      Method(isInObject = true, isByName = F, name = "parentsOf", params = ISZ("poset", "n")),
      Method(isInObject = true, isByName = F, name = "ancestorsOf", params = ISZ("poset", "n")),
      Method(isInObject = true, isByName = F, name = "ancestorsCache", params = ISZ("poset", "n", "acc")),
      Method(isInObject = true, isByName = F, name = "ancestorsRec", params = ISZ("poset", "m", "acc")),
      Method(isInObject = true, isByName = F, name = "lub", params = ISZ("poset", "ns")),
      Method(isInObject = true, isByName = F, name = "descendantsOf", params = ISZ("poset", "n")),
      Method(isInObject = true, isByName = F, name = "descendantsCache", params = ISZ("poset", "n", "acc")),
      Method(isInObject = true, isByName = F, name = "descendantsRec", params = ISZ("poset", "m", "acc")),
      Method(isInObject = true, isByName = F, name = "glb", params = ISZ("poset", "ns"))
    )
  )

  def info13 = Info( // org.sireum.Random
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "setSeed", params = ISZ("seed")),
      Method(isInObject = true, isByName = T, name = "create64", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "createSeed64", params = ISZ("seed")),
      Method(isInObject = true, isByName = F, name = "rotl32", params = ISZ("x", "k")),
      Method(isInObject = true, isByName = F, name = "rotl64", params = ISZ("x", "k"))
    )
  )

  def info14 = Info( // org.sireum.Random.Impl
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info15 = Info( // org.sireum.Random.Ext
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = T, name = "instance", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "setSeed", params = ISZ("n"))
    )
  )

  def info16 = Info( // org.sireum.Set.Elements
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "unique", params = ISZ("elements")),
      Method(isInObject = true, isByName = F, name = "contain", params = ISZ("elements", "e")),
      Method(isInObject = true, isByName = F, name = "indexOfFrom", params = ISZ("elements", "e", "from"))
    )
  )

  def info17 = Info( // org.sireum.UnionFind.Internal
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "find", params = ISZ("ds", "e")),
      Method(isInObject = true, isByName = F, name = "findCompress", params = ISZ("ds", "e")),
      Method(isInObject = true, isByName = F, name = "merge", params = ISZ("ds", "e1", "e2"))
    )
  )

  def info18 = Info( // org.sireum.justification
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info19 = Info( // org.sireum.justification.natded
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info20 = Info( // org.sireum.justification.natded.prop
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "andI", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "andE1", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "andE2", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "orI1", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "orI2", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "implyE", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "negE", params = ISZ("p")),
      Method(isInObject = true, isByName = F, name = "bottomE", params = ISZ("p")),
      Method(isInObject = true, isByName = F, name = "sandI", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "sandE1", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "sandE2", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "sorI1", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "sorI2", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "simplyE", params = ISZ("p", "q"))
    )
  )

  def info21 = Info( // org.sireum.justification.natded.pred
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "allE", params = ISZ("P", "E")),
      Method(isInObject = true, isByName = F, name = "existsI", params = ISZ("P", "E"))
    )
  )

  def info22 = Info( // org.sireum.Asm
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "eraseNonNative", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "rewriteReleaseFence", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "rewriteSetSecurityManager", params = ISZ("path"))
    )
  )

  def info23 = Info( // org.sireum.Coursier
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = T, name = "defaultCacheDir", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "commandPrefix", params = ISZ("isResolve", "scalaVersion", "cacheOpt", "mavenRepoUrls", "proxy")),
      Method(isInObject = true, isByName = F, name = "resolve", params = ISZ("scalaVersion", "cacheOpt", "mavenRepoUrls", "deps", "printTree", "proxy")),
      Method(isInObject = true, isByName = F, name = "fetch", params = ISZ("scalaVersion", "deps", "proxy")),
      Method(isInObject = true, isByName = F, name = "fetchClassifiers", params = ISZ("scalaVersion", "cacheOpt", "mavenRepoUrls", "deps", "cls", "proxy")),
      Method(isInObject = true, isByName = F, name = "isRuntimePublishedLocally", params = ISZ("scalaVersion", "version"))
    )
  )

  def info24 = Info( // org.sireum.GitHub
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "repo", params = ISZ("owner", "repo"))
    )
  )

  def info25 = Info( // org.sireum.GitHub.Ext
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "latestRelease", params = ISZ("repo")),
      Method(isInObject = true, isByName = F, name = "releases", params = ISZ("repo")),
      Method(isInObject = true, isByName = F, name = "assets", params = ISZ("release")),
      Method(isInObject = true, isByName = F, name = "submoduleShaOf", params = ISZ("repo", "path", "ref"))
    )
  )

  def info26 = Info( // org.sireum.Os
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = T, name = "cliArgs", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "cwd", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "exit", params = ISZ("code")),
      Method(isInObject = true, isByName = F, name = "env", params = ISZ("name")),
      Method(isInObject = true, isByName = T, name = "envs", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "fileSep", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "freeMemory", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "home", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "isLinux", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "isMac", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "isMacArm", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "isWinArm", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "isWin", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "javaHomeOpt", params = ISZ("kind", "homeOpt")),
      Method(isInObject = true, isByName = F, name = "javaExe", params = ISZ("homeOpt")),
      Method(isInObject = true, isByName = T, name = "kind", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "lineSep", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "maxMemory", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "numOfProcessors", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "pathSep", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "pathSepChar", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "path", params = ISZ("value")),
      Method(isInObject = true, isByName = F, name = "printParseableMessages", params = ISZ("reporter")),
      Method(isInObject = true, isByName = F, name = "proc", params = ISZ("commands")),
      Method(isInObject = true, isByName = F, name = "procs", params = ISZ("commands")),
      Method(isInObject = true, isByName = F, name = "prop", params = ISZ("name")),
      Method(isInObject = true, isByName = T, name = "props", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "readIndexableCFrom", params = ISZ("url")),
      Method(isInObject = true, isByName = T, name = "roots", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "scalaHomeOpt", params = ISZ("homeOpt")),
      Method(isInObject = true, isByName = F, name = "scalaScript", params = ISZ("homeOpt")),
      Method(isInObject = true, isByName = F, name = "scalacScript", params = ISZ("homeOpt")),
      Method(isInObject = true, isByName = T, name = "sireumHomeOpt", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "slashDir", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "temp", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "tempFix", params = ISZ("prefix", "suffix")),
      Method(isInObject = true, isByName = F, name = "tempDir", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "tempDirFix", params = ISZ("prefix")),
      Method(isInObject = true, isByName = T, name = "totalMemory", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "uriToPath", params = ISZ("uri"))
    )
  )

  def info27 = Info( // org.sireum.Os.Ext
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = T, name = "cliArgs", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "fileSep", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "lineSep", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "pathSep", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "pathSepChar", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "osKind", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "roots", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "abs", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "canon", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "chmod", params = ISZ("path", "mask", "all")),
      Method(isInObject = true, isByName = F, name = "copy", params = ISZ("path", "target", "over")),
      Method(isInObject = true, isByName = T, name = "detectSireumHome", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "download", params = ISZ("path", "url")),
      Method(isInObject = true, isByName = F, name = "env", params = ISZ("name")),
      Method(isInObject = true, isByName = T, name = "envs", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "exists", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "exit", params = ISZ("code")),
      Method(isInObject = true, isByName = T, name = "freeMemory", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "fromUri", params = ISZ("uri")),
      Method(isInObject = true, isByName = F, name = "isAbs", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "isDir", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "isFile", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "isSymLink", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "isExecutable", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "isReadable", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "isWritable", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "kind", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "lastModified", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "length", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "list", params = ISZ("path")),
      Method(isInObject = true, isByName = T, name = "maxMemory", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "numOfProcessors", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "mergeFrom", params = ISZ("path", "sources")),
      Method(isInObject = true, isByName = F, name = "md5", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "move", params = ISZ("path", "target", "over")),
      Method(isInObject = true, isByName = F, name = "mkdir", params = ISZ("path", "all")),
      Method(isInObject = true, isByName = F, name = "mklink", params = ISZ("path", "target")),
      Method(isInObject = true, isByName = F, name = "name", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "norm", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "prop", params = ISZ("name")),
      Method(isInObject = true, isByName = T, name = "props", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "properties", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readSymLink", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "relativize", params = ISZ("path", "other")),
      Method(isInObject = true, isByName = F, name = "read", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readU8s", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readU8ms", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readLineStream", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readU8Stream", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readCStream", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readIndexableCPath", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readIndexableCUrl", params = ISZ("url")),
      Method(isInObject = true, isByName = F, name = "readLineMStream", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readCMStream", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readU8MStream", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "remove", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "removeAll", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "removeOnExit", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "sha1", params = ISZ("path")),
      Method(isInObject = true, isByName = T, name = "slashDir", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "size", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "temp", params = ISZ("prefix", "suffix")),
      Method(isInObject = true, isByName = F, name = "tempDir", params = ISZ("prefix")),
      Method(isInObject = true, isByName = T, name = "totalMemory", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "toUri", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "write", params = ISZ("path", "content", "mode")),
      Method(isInObject = true, isByName = F, name = "writeU8s", params = ISZ("path", "u8s", "offset", "len", "mode")),
      Method(isInObject = true, isByName = F, name = "writeU8ms", params = ISZ("path", "u8s", "offset", "len", "mode")),
      Method(isInObject = true, isByName = F, name = "writeLineStream", params = ISZ("path", "lines", "mode")),
      Method(isInObject = true, isByName = F, name = "writeU8Stream", params = ISZ("path", "u8s", "mode")),
      Method(isInObject = true, isByName = F, name = "writeCStream", params = ISZ("path", "cs", "mode")),
      Method(isInObject = true, isByName = F, name = "writeLineMStream", params = ISZ("path", "lines", "mode")),
      Method(isInObject = true, isByName = F, name = "writeU8MStream", params = ISZ("path", "u8s", "mode")),
      Method(isInObject = true, isByName = F, name = "writeCMStream", params = ISZ("path", "cs", "mode")),
      Method(isInObject = true, isByName = F, name = "zip", params = ISZ("path", "target")),
      Method(isInObject = true, isByName = F, name = "unzip", params = ISZ("path", "target")),
      Method(isInObject = true, isByName = F, name = "unTarGz", params = ISZ("path", "target")),
      Method(isInObject = true, isByName = F, name = "parent", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "proc", params = ISZ("e"))
    )
  )

  def info28 = Info( // org.sireum.Scalafmt
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = T, name = "version", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "minimalConfig", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "format", params = ISZ("config", "isScript", "content")),
      Method(isInObject = true, isByName = F, name = "formatFile", params = ISZ("config", "file"))
    )
  )

  def info29 = Info( // org.sireum.Unit
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ()
  )

  def info30 = Info( // org.sireum.Nothing
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ()
  )

  def info31 = Info( // org.sireum.AssocS
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "entries")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "keys", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "values", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "keySet", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "valueSet", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("kvs")),
      Method(isInObject = false, isByName = F, name = "get", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("key", "default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("key", "default")),
      Method(isInObject = false, isByName = F, name = "entry", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "indexOf", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("keys")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("key")),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info32 = Info( // org.sireum.Bag
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "map")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "addN", params = ISZ("e", "n")),
      Method(isInObject = false, isByName = F, name = "+#", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("es")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("es")),
      Method(isInObject = false, isByName = F, name = "\\", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "-#", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "removeN", params = ISZ("e", "n")),
      Method(isInObject = false, isByName = T, name = "entries", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "union", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u222A", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "intersect", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u2229", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info33 = Info( // org.sireum.StepId
    kind = Kind.MSig,
    fields = ISZ(),
    methods = ISZ()
  )

  def info34 = Info( // org.sireum.CircularQueue
    kind = Kind.RecordTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "max", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "default", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "scrub", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "policy", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isFull", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "enqueue", params = ISZ("element")),
      Method(isInObject = false, isByName = F, name = "dequeue", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info35 = Info( // org.sireum.CircularQueue.NoDrop
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "max"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "default"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "scrub"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "queue"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "front"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "rear"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "numOfElements")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "policy", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isFull", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "enqueue", params = ISZ("element")),
      Method(isInObject = false, isByName = F, name = "dequeue", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info36 = Info( // org.sireum.CircularQueue.DropFront
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "max"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "default"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "scrub"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "queue"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "front"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "rear"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "numOfElements")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "policy", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isFull", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "enqueue", params = ISZ("element")),
      Method(isInObject = false, isByName = F, name = "dequeue", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info37 = Info( // org.sireum.CircularQueue.DropRear
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "max"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "default"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "scrub"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "queue"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "front"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "rear"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "numOfElements")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "policy", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isFull", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "enqueue", params = ISZ("element")),
      Method(isInObject = false, isByName = F, name = "dequeue", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info38 = Info( // org.sireum.Either
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isLeft", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isRight", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "leftOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "left", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "rightOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "right", params = ISZ())
    )
  )

  def info39 = Info( // org.sireum.Either.Left
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isLeft", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isRight", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "leftOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "left", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "rightOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "right", params = ISZ())
    )
  )

  def info40 = Info( // org.sireum.Either.Right
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isLeft", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isRight", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "leftOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "left", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "rightOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "right", params = ISZ())
    )
  )

  def info41 = Info( // org.sireum.Graph.Edge
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "source", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "dest", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toInternal", params = ISZ("map"))
    )
  )

  def info42 = Info( // org.sireum.Graph.Edge.Plain
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "source"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "dest")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "toInternal", params = ISZ("map"))
    )
  )

  def info43 = Info( // org.sireum.Graph.Edge.Data
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "source"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "dest"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "data")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "toInternal", params = ISZ("map"))
    )
  )

  def info44 = Info( // org.sireum.Graph.Internal.Edge
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "source", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "dest", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toEdge", params = ISZ("map"))
    )
  )

  def info45 = Info( // org.sireum.Graph.Internal.Edges
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("es")),
      Method(isInObject = false, isByName = F, name = "-#", params = ISZ("p"))
    )
  )

  def info46 = Info( // org.sireum.Graph.Internal.Edges.Set
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "set")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("es")),
      Method(isInObject = false, isByName = F, name = "-#", params = ISZ("p"))
    )
  )

  def info47 = Info( // org.sireum.Graph.Internal.Edges.Bag
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "set")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("es")),
      Method(isInObject = false, isByName = F, name = "-#", params = ISZ("p"))
    )
  )

  def info48 = Info( // org.sireum.Graph.Internal.Edge.Plain
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "source"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "dest")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "toEdge", params = ISZ("map"))
    )
  )

  def info49 = Info( // org.sireum.Graph.Internal.Edge.Data
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "source"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "dest"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "data")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "toEdge", params = ISZ("map"))
    )
  )

  def info50 = Info( // org.sireum.Graph
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "nodes"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "nodesInverse"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "incomingEdges"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "outgoingEdges"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "nextNodeId"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "multi")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "*", params = ISZ("node")),
      Method(isInObject = false, isByName = F, name = "--*", params = ISZ("ns")),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("edge")),
      Method(isInObject = false, isByName = F, name = "+@", params = ISZ("edge")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("edge")),
      Method(isInObject = false, isByName = F, name = "-#", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("edges")),
      Method(isInObject = false, isByName = F, name = "incoming", params = ISZ("dest")),
      Method(isInObject = false, isByName = F, name = "outgoing", params = ISZ("source")),
      Method(isInObject = false, isByName = F, name = "addEdge", params = ISZ("edge")),
      Method(isInObject = false, isByName = F, name = "addPlainEdge", params = ISZ("source", "dest")),
      Method(isInObject = false, isByName = F, name = "addDataEdge", params = ISZ("data", "source", "dest")),
      Method(isInObject = false, isByName = T, name = "allEdges", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "removeEdgeN", params = ISZ("edge", "n")),
      Method(isInObject = false, isByName = F, name = "edges", params = ISZ("source", "dest")),
      Method(isInObject = false, isByName = T, name = "numOfNodes", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "numOfEdges", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "toST", params = ISZ("attributes", "f", "g")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info51 = Info( // org.sireum.HashBag
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "map")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "+#", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "addN", params = ISZ("e", "n")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("es")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("s")),
      Method(isInObject = false, isByName = F, name = "-#", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "removeN", params = ISZ("e", "n")),
      Method(isInObject = false, isByName = F, name = "\\", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "entries", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "union", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u222A", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "intersect", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u2229", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info52 = Info( // org.sireum.HashMap
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "mapEntries"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "size")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "entries", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "keys", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "values", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "keySet", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "valueSet", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("entries")),
      Method(isInObject = false, isByName = F, name = "ensureCapacity", params = ISZ("sz")),
      Method(isInObject = false, isByName = F, name = "hashIndex", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "get", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "entry", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("keys")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("key")),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other"))
    )
  )

  def info53 = Info( // org.sireum.HashSBag
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "map")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "+#", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "addN", params = ISZ("e", "n")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("es")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("s")),
      Method(isInObject = false, isByName = F, name = "-#", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "removeN", params = ISZ("e", "n")),
      Method(isInObject = false, isByName = F, name = "\\", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "entries", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "union", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u222A", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "intersect", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u2229", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info54 = Info( // org.sireum.HashSMap
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "map"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "keys")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "entries", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "values", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "keySet", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "valueSet", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("entries")),
      Method(isInObject = false, isByName = F, name = "get", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "entry", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("keys")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("key")),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other"))
    )
  )

  def info55 = Info( // org.sireum.HashSSet
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "map")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("is")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("is")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "union", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u222A", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "intersect", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u2229", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\\", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info56 = Info( // org.sireum.HashSet
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "map")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("is")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("is")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "union", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u222A", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "intersect", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u2229", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\\", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info57 = Info( // org.sireum.IndexMap
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "s")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("k")),
      Method(isInObject = false, isByName = F, name = "get", params = ISZ("k")),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "entries", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "keys", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "values", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "prettyST", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info58 = Info( // org.sireum.Indexable
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "at", params = ISZ("i")),
      Method(isInObject = false, isByName = F, name = "has", params = ISZ("i"))
    )
  )

  def info59 = Info( // org.sireum.Indexable.Pos
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "posOpt", params = ISZ("offset", "length")),
      Method(isInObject = false, isByName = F, name = "at", params = ISZ("i")),
      Method(isInObject = false, isByName = F, name = "has", params = ISZ("i"))
    )
  )

  def info60 = Info( // org.sireum.Indexable.Isz
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "is")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "at", params = ISZ("i")),
      Method(isInObject = false, isByName = F, name = "has", params = ISZ("i"))
    )
  )

  def info61 = Info( // org.sireum.Indexable.IszDocInfo
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "is"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "info")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "at", params = ISZ("i")),
      Method(isInObject = false, isByName = F, name = "has", params = ISZ("i")),
      Method(isInObject = false, isByName = F, name = "posOpt", params = ISZ("offset", "length"))
    )
  )

  def info62 = Info( // org.sireum.Jen
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info63 = Info( // org.sireum.Jen.Internal.ISImpl
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "s")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info64 = Info( // org.sireum.Jen.Internal.MapImpl
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "m")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info65 = Info( // org.sireum.Jen.Internal.HashMapImpl
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "m")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info66 = Info( // org.sireum.Jen.Internal.Filtered
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "p")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info67 = Info( // org.sireum.Jen.Internal.Mapped
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "f")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("g")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info68 = Info( // org.sireum.Jen.Internal.FlatMapped
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "f")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("g")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info69 = Info( // org.sireum.Jen.Internal.Sliced
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "start"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "end")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info70 = Info( // org.sireum.Jen.Internal.TakeWhile
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "p")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info71 = Info( // org.sireum.Jen.Internal.DropWhile
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "p")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info72 = Info( // org.sireum.Jen.Internal.ZipWithIndexed
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info73 = Info( // org.sireum.Jen.Internal.Zipped
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen2")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info74 = Info( // org.sireum.Jen.Internal.Concat
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen2")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info75 = Info( // org.sireum.Jen.Internal.Product
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen2")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info76 = Info( // org.sireum.MBox
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ()
  )

  def info77 = Info( // org.sireum.MBox2
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2")
    ),
    methods = ISZ()
  )

  def info78 = Info( // org.sireum.MBox3
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3")
    ),
    methods = ISZ()
  )

  def info79 = Info( // org.sireum.MBox4
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4")
    ),
    methods = ISZ()
  )

  def info80 = Info( // org.sireum.MBox5
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5")
    ),
    methods = ISZ()
  )

  def info81 = Info( // org.sireum.MBox6
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6")
    ),
    methods = ISZ()
  )

  def info82 = Info( // org.sireum.MBox7
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7")
    ),
    methods = ISZ()
  )

  def info83 = Info( // org.sireum.MBox8
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8")
    ),
    methods = ISZ()
  )

  def info84 = Info( // org.sireum.MBox9
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9")
    ),
    methods = ISZ()
  )

  def info85 = Info( // org.sireum.MBox10
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10")
    ),
    methods = ISZ()
  )

  def info86 = Info( // org.sireum.MBox11
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11")
    ),
    methods = ISZ()
  )

  def info87 = Info( // org.sireum.MBox12
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12")
    ),
    methods = ISZ()
  )

  def info88 = Info( // org.sireum.MBox13
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13")
    ),
    methods = ISZ()
  )

  def info89 = Info( // org.sireum.MBox14
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14")
    ),
    methods = ISZ()
  )

  def info90 = Info( // org.sireum.MBox15
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15")
    ),
    methods = ISZ()
  )

  def info91 = Info( // org.sireum.MBox16
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value16")
    ),
    methods = ISZ()
  )

  def info92 = Info( // org.sireum.MBox17
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value16"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value17")
    ),
    methods = ISZ()
  )

  def info93 = Info( // org.sireum.MBox18
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value16"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value17"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value18")
    ),
    methods = ISZ()
  )

  def info94 = Info( // org.sireum.MBox19
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value16"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value17"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value18"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value19")
    ),
    methods = ISZ()
  )

  def info95 = Info( // org.sireum.MBox20
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value16"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value17"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value18"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value19"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value20")
    ),
    methods = ISZ()
  )

  def info96 = Info( // org.sireum.MBox21
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value16"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value17"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value18"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value19"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value20"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value21")
    ),
    methods = ISZ()
  )

  def info97 = Info( // org.sireum.MBox22
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value16"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value17"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value18"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value19"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value20"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value21"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value22")
    ),
    methods = ISZ()
  )

  def info98 = Info( // org.sireum.MEither
    kind = Kind.RecordTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isLeft", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isRight", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "leftOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "left", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "rightOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "right", params = ISZ())
    )
  )

  def info99 = Info( // org.sireum.MEither.Left
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isLeft", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isRight", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "leftOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "left", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "rightOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "right", params = ISZ())
    )
  )

  def info100 = Info( // org.sireum.MEither.Right
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isLeft", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isRight", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "leftOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "left", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "rightOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "right", params = ISZ())
    )
  )

  def info101 = Info( // org.sireum.MJen
    kind = Kind.MSig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info102 = Info( // org.sireum.MJen.Internal.ISImpl
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "s")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info103 = Info( // org.sireum.MJen.Internal.MSImpl
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "s")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info104 = Info( // org.sireum.MJen.Internal.MapImpl
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "m")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info105 = Info( // org.sireum.MJen.Internal.HashMapImpl
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "m")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info106 = Info( // org.sireum.MJen.Internal.Filtered
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "p")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info107 = Info( // org.sireum.MJen.Internal.Mapped
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "f")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("g")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info108 = Info( // org.sireum.MJen.Internal.FlatMapped
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "f")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("g")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info109 = Info( // org.sireum.MJen.Internal.Sliced
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "start"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "end")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info110 = Info( // org.sireum.MJen.Internal.TakeWhile
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "p")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info111 = Info( // org.sireum.MJen.Internal.DropWhile
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "p")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info112 = Info( // org.sireum.MJen.Internal.ZipWithIndexed
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info113 = Info( // org.sireum.MJen.Internal.Zipped
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen2")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info114 = Info( // org.sireum.MJen.Internal.Concat
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen2")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info115 = Info( // org.sireum.MJen.Internal.Product
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen2")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info116 = Info( // org.sireum.MOption
    kind = Kind.RecordTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "get", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("default")),
      Method(isInObject = false, isByName = T, name = "toMS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f"))
    )
  )

  def info117 = Info( // org.sireum.MNone
    kind = Kind.RecordClass,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("default")),
      Method(isInObject = false, isByName = T, name = "get", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f"))
    )
  )

  def info118 = Info( // org.sireum.MSome
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("default")),
      Method(isInObject = false, isByName = T, name = "get", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f"))
    )
  )

  def info119 = Info( // org.sireum.Map
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "entries")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "keys", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "values", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "keySet", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "valueSet", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("kvs")),
      Method(isInObject = false, isByName = F, name = "get", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("key", "default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("key", "default")),
      Method(isInObject = false, isByName = F, name = "entry", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "indexOf", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("keys")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("key")),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other"))
    )
  )

  def info120 = Info( // org.sireum.ObjPrinter
    kind = Kind.MSig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "freshNum", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "write", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "cache", params = ISZ("o", "f")),
      Method(isInObject = false, isByName = F, name = "addMethod", params = ISZ("tipe", "isStrictPure", "body")),
      Method(isInObject = false, isByName = F, name = "printISZ", params = ISZ("elementType", "s", "e")),
      Method(isInObject = false, isByName = F, name = "printIS", params = ISZ("indexType", "elementType", "s", "i", "e")),
      Method(isInObject = false, isByName = F, name = "printMS", params = ISZ("indexType", "elementType", "s", "i", "e")),
      Method(isInObject = false, isByName = F, name = "printMap", params = ISZ("keyType", "valueType", "o", "k", "v")),
      Method(isInObject = false, isByName = F, name = "printSet", params = ISZ("elementType", "o", "f")),
      Method(isInObject = false, isByName = F, name = "printHashMap", params = ISZ("keyType", "valueType", "o", "k", "v")),
      Method(isInObject = false, isByName = F, name = "printHashSet", params = ISZ("elementType", "o", "f")),
      Method(isInObject = false, isByName = F, name = "printHashSMap", params = ISZ("keyType", "valueType", "o", "k", "v")),
      Method(isInObject = false, isByName = F, name = "printHashSSet", params = ISZ("elementType", "o", "f")),
      Method(isInObject = false, isByName = F, name = "printStack", params = ISZ("elementType", "o", "f")),
      Method(isInObject = false, isByName = F, name = "printBag", params = ISZ("elementType", "o", "f")),
      Method(isInObject = false, isByName = F, name = "printHashBag", params = ISZ("elementType", "o", "f")),
      Method(isInObject = false, isByName = F, name = "printHashSBag", params = ISZ("elementType", "o", "f")),
      Method(isInObject = false, isByName = F, name = "printPoset", params = ISZ("elementType", "o", "e")),
      Method(isInObject = false, isByName = F, name = "printGraph", params = ISZ("vType", "eType", "o", "v", "e")),
      Method(isInObject = false, isByName = F, name = "printUnionFind", params = ISZ("eType", "o", "e")),
      Method(isInObject = false, isByName = F, name = "printMessage", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "printPosition", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "printDocInfo", params = ISZ("o"))
    )
  )

  def info121 = Info( // org.sireum.Option
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "get", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("default")),
      Method(isInObject = false, isByName = T, name = "toIS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f"))
    )
  )

  def info122 = Info( // org.sireum.None
    kind = Kind.DatatypeClass,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("default")),
      Method(isInObject = false, isByName = T, name = "get", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toIS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f"))
    )
  )

  def info123 = Info( // org.sireum.Some
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("default")),
      Method(isInObject = false, isByName = T, name = "get", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toIS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f"))
    )
  )

  def info124 = Info( // org.sireum.OsProto.Path
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ()
  )

  def info125 = Info( // org.sireum.OsProto.Proc.Result
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "ok", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "out", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "err", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "exitCode", params = ISZ())
    )
  )

  def info126 = Info( // org.sireum.OsProto.Proc
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "commands", params = ISZ("cs")),
      Method(isInObject = false, isByName = F, name = "at", params = ISZ("dir")),
      Method(isInObject = false, isByName = F, name = "env", params = ISZ("m")),
      Method(isInObject = false, isByName = F, name = "input", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "timeout", params = ISZ("millis")),
      Method(isInObject = false, isByName = T, name = "dontInheritEnv", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "redirectErr", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "bufferErr", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "console", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "echoEnv", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "echo", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "standard", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "script", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "outLineAction", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "errLineAction", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "shouldPrintCommands", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "shouldOutputConsole", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isErrAsOut", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "in", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "cmds", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "run", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "runCheck", params = ISZ())
    )
  )

  def info127 = Info( // org.sireum.Poset
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "nodes"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "nodesInverse"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "parents"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "children"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Normal, name = "emptySet")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("that")),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "addNode", params = ISZ("node")),
      Method(isInObject = false, isByName = T, name = "rootNodes", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "addParents", params = ISZ("node", "nds")),
      Method(isInObject = false, isByName = F, name = "removeParent", params = ISZ("node", "parent")),
      Method(isInObject = false, isByName = F, name = "removeChild", params = ISZ("n", "child")),
      Method(isInObject = false, isByName = F, name = "addChildren", params = ISZ("node", "nds")),
      Method(isInObject = false, isByName = F, name = "childrenOf", params = ISZ("node")),
      Method(isInObject = false, isByName = F, name = "isChildOf", params = ISZ("node1", "node2")),
      Method(isInObject = false, isByName = F, name = "parentsOf", params = ISZ("node")),
      Method(isInObject = false, isByName = F, name = "isParentOf", params = ISZ("node1", "node2")),
      Method(isInObject = false, isByName = F, name = "ancestorsOf", params = ISZ("node")),
      Method(isInObject = false, isByName = F, name = "lub", params = ISZ("nds")),
      Method(isInObject = false, isByName = F, name = "descendantsOf", params = ISZ("node")),
      Method(isInObject = false, isByName = F, name = "glb", params = ISZ("nds")),
      Method(isInObject = false, isByName = F, name = "toST", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info128 = Info( // org.sireum.Random.Gen.TestRunner
    kind = Kind.MSig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "next", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toCompactJson", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "fromJson", params = ISZ("json")),
      Method(isInObject = false, isByName = F, name = "test", params = ISZ("o"))
    )
  )

  def info129 = Info( // org.sireum.Random.Gen
    kind = Kind.MSig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "nextB", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextC", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF32_01", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF64_01", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextR", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextCBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextF32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextF64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextRBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ64Between", params = ISZ("min", "max"))
    )
  )

  def info130 = Info( // org.sireum.Random.Gen64
    kind = Kind.MSig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "genU64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextB", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextC", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF32_01", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF64_01", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextR", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextCBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextF32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextF64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextRBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ64Between", params = ISZ("min", "max"))
    )
  )

  def info131 = Info( // org.sireum.Random.Gen64Impl
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "genU64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextB", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextC", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF32_01", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF64_01", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextR", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextCBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextF32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextF64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextRBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ64Between", params = ISZ("min", "max"))
    )
  )

  def info132 = Info( // org.sireum.Random.Impl.SplitMix64
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "next", params = ISZ())
    )
  )

  def info133 = Info( // org.sireum.Random.Impl.Xoshiro256
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed0"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed3")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "update", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "pp", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "ss", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "p", params = ISZ())
    )
  )

  def info134 = Info( // org.sireum.Random.Impl.Xoroshiro128
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed0"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed3")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "update", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "pp", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "ss", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "p", params = ISZ())
    )
  )

  def info135 = Info( // org.sireum.Set
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "elements")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("is")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("is")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "union", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u222A", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "intersect", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u2229", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\\", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "indexOf", params = ISZ("e")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info136 = Info( // org.sireum.Stack
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "elements")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "peek", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "push", params = ISZ("e")),
      Method(isInObject = false, isByName = T, name = "pop", params = ISZ())
    )
  )

  def info137 = Info( // org.sireum.UnionFind
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "elements"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "elementsInverse"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "parentOf"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "sizeOf")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "inSameSet", params = ISZ("element1", "element2")),
      Method(isInObject = false, isByName = F, name = "inSameSetCompress", params = ISZ("element1", "element2")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("element")),
      Method(isInObject = false, isByName = F, name = "findCompress", params = ISZ("element")),
      Method(isInObject = false, isByName = F, name = "merge", params = ISZ("element1", "element2")),
      Method(isInObject = false, isByName = F, name = "toST", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info138 = Info( // org.sireum.CoursierFileInfo
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "org"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "module"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "version"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "pathString")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "path", params = ISZ())
    )
  )

  def info139 = Info( // org.sireum.Coursier.Proxy
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "hostOpt"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "nonHostsOpt"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "portOpt"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "protocolOpt"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "protocolUserEnvKeyOpt"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "protocolPasswordEnvKeyOpt")
    ),
    methods = ISZ()
  )

  def info140 = Info( // org.sireum.GitHub.Credential
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ()
  )

  def info141 = Info( // org.sireum.GitHub.Repository
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "connection"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "owner"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "name")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "latestRelease", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "releases", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "submoduleShaOf", params = ISZ("path", "ref"))
    )
  )

  def info142 = Info( // org.sireum.GitHub.Release
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "repo"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "id"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "name"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "publishedTime"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "isDraft"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "isPrerelease"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "tagName"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "commit"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "tarUrl"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "zipUrl")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "assets", params = ISZ())
    )
  )

  def info143 = Info( // org.sireum.GitHub.Asset
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "release"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "id"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "name"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "label"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "state"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "size"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "contentType"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "url"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "downloadCount")
    ),
    methods = ISZ()
  )

  def info144 = Info( // org.sireum.Init.Plugin
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "id"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "isCommunity"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "isJar"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "version")
    ),
    methods = ISZ()
  )

  def info145 = Info( // org.sireum.Init
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "home"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "kind"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "versions"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Normal, name = "sireumV"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Normal, name = "cache"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Normal, name = "pluginPrefix"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Normal, name = "ideaCacheDir"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Normal, name = "pluginsCacheDir")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "homeBin", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "homeLib", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "homeBinPlatform", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "distroPlugins", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "scalacPluginVersion", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "coursierVersion", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "jacocoVersion", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "scalacPlugin", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "scalaVersion", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "scalaHome", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "sireumJar", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "maryTtsJar", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "checkstack", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "checkstackVersion", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "javaVersion", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "platform", params = ISZ("k")),
      Method(isInObject = false, isByName = T, name = "pwd7z", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installJava", params = ISZ("vs")),
      Method(isInObject = false, isByName = F, name = "installScala", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installScalacPlugin", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installCoursier", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installJacoco", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "pwd7zUrl", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "install7z", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installZ3", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installCVC", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installMaryTTS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installCheckStack", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installMill", params = ISZ("verbose")),
      Method(isInObject = false, isByName = F, name = "ideaDirPath", params = ISZ("isUltimate", "isServer")),
      Method(isInObject = false, isByName = F, name = "installScripts", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isIdeaInUserHome", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "ideaConfig", params = ISZ("isSetup", "isDev", "isUltimate", "projectPathOpt")),
      Method(isInObject = false, isByName = F, name = "ideaPlugins", params = ISZ("isDev", "isUltimate", "projectPathOpt")),
      Method(isInObject = false, isByName = F, name = "ideaSandbox", params = ISZ("isDev")),
      Method(isInObject = false, isByName = F, name = "zipName", params = ISZ("id", "version")),
      Method(isInObject = false, isByName = F, name = "downloadPlugins", params = ISZ("isDev", "pluginFilter")),
      Method(isInObject = false, isByName = F, name = "extractPlugins", params = ISZ("pluginsDir", "pluginFilter")),
      Method(isInObject = false, isByName = F, name = "distro", params = ISZ("isDev", "buildSfx", "isUltimate", "isServer")),
      Method(isInObject = false, isByName = F, name = "basicDeps", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "proyekCompileDeps", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "logikaDeps", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "deps", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "init", params = ISZ("setup"))
    )
  )

  def info146 = Info( // org.sireum.Os.Path.Impl
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "procString", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "/", params = ISZ("name")),
      Method(isInObject = false, isByName = F, name = "/+", params = ISZ("names")),
      Method(isInObject = false, isByName = F, name = "call", params = ISZ("args")),
      Method(isInObject = false, isByName = T, name = "canon", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "abs", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "chmod", params = ISZ("mask")),
      Method(isInObject = false, isByName = F, name = "chmodAll", params = ISZ("mask")),
      Method(isInObject = false, isByName = F, name = "combineFrom", params = ISZ("sources")),
      Method(isInObject = false, isByName = F, name = "copyTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "copyOverTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "downloadFrom", params = ISZ("url")),
      Method(isInObject = false, isByName = T, name = "exists", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "ext", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isAbs", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isDir", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isFile", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isSymLink", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isExecutable", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isReadable", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isWritable", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "kind", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "lastModified", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "length", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "list", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "mergeFrom", params = ISZ("sources")),
      Method(isInObject = false, isByName = T, name = "md5", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "moveTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "moveOverTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "mkdir", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "mkdirAll", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "mklink", params = ISZ("target")),
      Method(isInObject = false, isByName = T, name = "name", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "overlayCopy", params = ISZ("target", "includeDir", "followLink", "pred", "report")),
      Method(isInObject = false, isByName = F, name = "overlayMove", params = ISZ("target", "includeDir", "followLink", "pred", "report")),
      Method(isInObject = false, isByName = F, name = "prependWith", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "properties", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readSymLink", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "relativize", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "read", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readLines", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readLineStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readLineMStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8s", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8ms", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8Stream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8MStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readCStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readIndexableC", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readCMStream", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "remove", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "removeAll", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "removeOnExit", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "sha1", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "slash", params = ISZ("args")),
      Method(isInObject = false, isByName = T, name = "toUri", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "write", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOver", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppend", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeLineStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverLineStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendLineStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeLineMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverLineMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendLineMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8s", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8Parts", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeOverU8s", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8Parts", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8s", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8Parts", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeU8ms", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8Partms", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeOverU8ms", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8Partms", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8ms", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8Partms", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeU8Stream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8Stream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8Stream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8MStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8MStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8MStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeCStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverCStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendCStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeCMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverCMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendCMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "zipTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "unzipTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "unTarGzTo", params = ISZ("target")),
      Method(isInObject = false, isByName = T, name = "up", params = ISZ())
    )
  )

  def info147 = Info( // org.sireum.Os.Path.Jen
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "path", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info148 = Info( // org.sireum.Os.Path.MJen
    kind = Kind.MSig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "path", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info149 = Info( // org.sireum.Os.Proc.LineFilter
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("line"))
    )
  )

  def info150 = Info( // org.sireum.Os.Proc.FunLineFilter
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "f")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("line"))
    )
  )

  def info151 = Info( // org.sireum.Os.Proc.Result
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "ok", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "out", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "err", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "exitCode", params = ISZ())
    )
  )

  def info152 = Info( // org.sireum.Os.Proc.Result.Normal
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "exitCode"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "out"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "err")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "ok", params = ISZ())
    )
  )

  def info153 = Info( // org.sireum.Os.Proc.Result.Exception
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "err")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "out", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "exitCode", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "ok", params = ISZ())
    )
  )

  def info154 = Info( // org.sireum.Os.Proc.Result.Timeout
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "out"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "err")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "exitCode", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "ok", params = ISZ())
    )
  )

  def info155 = Info( // org.sireum.Os.Proc
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "cmds"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "wd"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "envMap"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "shouldAddEnv"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "in"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "isErrAsOut"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "shouldOutputConsole"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "isErrBuffered"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "shouldPrintEnv"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "shouldPrintCommands"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "timeoutInMillis"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "shouldUseStandardLib"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "isScript"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "outLineActionOpt"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "errLineActionOpt")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "commands", params = ISZ("cs")),
      Method(isInObject = false, isByName = F, name = "at", params = ISZ("dir")),
      Method(isInObject = false, isByName = F, name = "env", params = ISZ("m")),
      Method(isInObject = false, isByName = F, name = "input", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "timeout", params = ISZ("millis")),
      Method(isInObject = false, isByName = T, name = "dontInheritEnv", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "redirectErr", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "bufferErr", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "console", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "echoEnv", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "echo", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "standard", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "script", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "outLineAction", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "errLineAction", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "run", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "runCheck", params = ISZ())
    )
  )

  def info156 = Info( // org.sireum.Os.Path
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "value", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "procString", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "/", params = ISZ("name")),
      Method(isInObject = false, isByName = F, name = "/+", params = ISZ("names")),
      Method(isInObject = false, isByName = F, name = "call", params = ISZ("args")),
      Method(isInObject = false, isByName = T, name = "canon", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "abs", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "chmod", params = ISZ("mask")),
      Method(isInObject = false, isByName = F, name = "chmodAll", params = ISZ("mask")),
      Method(isInObject = false, isByName = F, name = "combineFrom", params = ISZ("sources")),
      Method(isInObject = false, isByName = F, name = "copyTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "copyOverTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "downloadFrom", params = ISZ("url")),
      Method(isInObject = false, isByName = T, name = "exists", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "ext", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isAbs", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isDir", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isFile", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isSymLink", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isExecutable", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isReadable", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isWritable", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "kind", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "lastModified", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "length", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "list", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "mergeFrom", params = ISZ("sources")),
      Method(isInObject = false, isByName = T, name = "md5", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "moveTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "moveOverTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "mkdir", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "mkdirAll", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "mklink", params = ISZ("target")),
      Method(isInObject = false, isByName = T, name = "name", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "overlayCopy", params = ISZ("target", "includeDir", "followLink", "pred", "report")),
      Method(isInObject = false, isByName = F, name = "overlayMove", params = ISZ("target", "includeDir", "followLink", "pred", "report")),
      Method(isInObject = false, isByName = F, name = "prependWith", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "properties", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readSymLink", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "relativize", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "read", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readLines", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readLineStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readLineMStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8s", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8ms", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8Stream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8MStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readCStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readIndexableC", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readCMStream", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "remove", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "removeAll", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "removeOnExit", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "sha1", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "slash", params = ISZ("args")),
      Method(isInObject = false, isByName = T, name = "toUri", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "write", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOver", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppend", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeLineStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverLineStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendLineStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeLineMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverLineMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendLineMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8s", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8Parts", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeOverU8s", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8Parts", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8s", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8Parts", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeU8ms", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8Partms", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeOverU8ms", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8Partms", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8ms", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8Partms", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeU8Stream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8Stream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8Stream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8MStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8MStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8MStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeCStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverCStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendCStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeCMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverCMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendCMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "zipTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "unzipTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "unTarGzTo", params = ISZ("target")),
      Method(isInObject = false, isByName = T, name = "up", params = ISZ())
    )
  )

  override def string: String = "SlangRunner_Ext"
}