# Sireum: A High Assurance System Engineering Platform

[![License](https://img.shields.io/badge/License-BSD_2--Clause-brightgreen.svg)](https://github.com/sireum/kekinian/blob/master/license.md)
[![Actions Status](https://github.com/sireum/kekinian/workflows/macOS/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-macOS.yml) 
[![Actions Status](https://github.com/sireum/kekinian/workflows/Linux/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-linux.yml) 
[![Actions Status](https://github.com/sireum/kekinian/workflows/Windows/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-windows.yml) 
[![Actions Status](https://github.com/sireum/kekinian/workflows/macOS-CompCert/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-macOS-compcert.yml) 
[![Actions Status](https://github.com/sireum/kekinian/workflows/Linux-CompCert/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-linux-compcert.yml) 
[![Jitpack](https://jitpack.io/v/org.sireum/kekinian.svg)](https://jitpack.io/#org.sireum/kekinian)

Sireum Kekinian is the most recent evolution of the Sireum platform.
For more information, please visit Sireum's website:

https://sireum.org ([Download/Getting Started](https://sireum.org/getting-started))

## Dependencies

Sireum depends on open source software libraries and applications
(the specific versions are listed in [versions.properties](versions.properties)).

### Library Dependencies

| Library | Maven | License |
| :--- | :---: | :---: | 
| [ANTLR3 Runtime](https://github.com/antlr/antlr3) | [(link)](https://search.maven.org/artifact/org.antlr/antlr-runtime) | [BSD3](https://www.antlr3.org/license.html) |
| [Apache Common Compress](https://commons.apache.org/proper/commons-compress) | [(link)](https://search.maven.org/artifact/org.apache.commons/commons-compress) | [Apache 2.0](https://gitbox.apache.org/repos/asf?p=commons-compress.git;a=blob_plain;f=LICENSE.txt;hb=HEAD) |
| [ASM](https://gitlab.ow2.org/asm/asm) | [(link)](https://search.maven.org/artifact/org.ow2.asm/asm) | [BSD3](https://gitlab.ow2.org/asm/asm/-/blob/master/LICENSE.txt) |
| [Automaton](https://github.com/sireum/automaton) | [(link)](https://jitpack.io/#org.sireum/automaton) | [BSD3](https://github.com/sireum/automaton/blob/codepoint/COPYING) |
| [GitHub API](https://github.com/hub4j/github-api) | [(link)](https://search.maven.org/artifact/org.kohsuke/github-api) | [MIT](https://github.com/hub4j/github-api/blob/main/LICENSE.txt) |
| [NuProcess](https://github.com/brettwooldridge/NuProcess) | [(link)](https://search.maven.org/artifact/com.zaxxer/nuprocess) | [Apache 2.0](https://github.com/brettwooldridge/NuProcess/blob/master/LICENSE) |
| [OS-Lib](https://github.com/com-lihaoyi/os-lib) | [(link)](https://search.maven.org/artifact/com.lihaoyi/os-lib_2.13) | [MIT](https://github.com/com-lihaoyi/os-lib/blob/master/LICENSE) |
| [JavaFX for Presentasi](https://github.com/sireum/presentasi-jfx) | [(link)](https://jitpack.io/#org.sireum/presentasi-jfx) | [BSD2](https://github.com/sireum/presentasi-jfx/blob/master/license.md) |
| [Scala](https://github.com/scala/scala) | [(link)](https://search.maven.org/artifact/org.scala-lang/scala-library) | [Apache 2.0](https://github.com/scala/scala/blob/2.13.x/LICENSE) |
| [Scalafmt](https://github.com/scalameta/scalafmt) | [(link)](https://search.maven.org/artifact/org.scalameta/scalafmt-cli_2.13) | [Apache 2.0](https://github.com/scalameta/scalafmt/blob/master/LICENCE.md) |
| [Scalameta](https://github.com/scalameta/scalameta) | [(link)](https://search.maven.org/artifact/org.scalameta/scalameta_2.13) | [BSD3](https://github.com/scalameta/scalameta/blob/main/LICENSE.md) |
| [ScalaTest](https://github.com/scalatest/scalatest) | [(link)](https://search.maven.org/artifact/org.scalatest/scalatest_2.13) | [Apache 2.0](https://github.com/scalatest/scalatest/blob/3.2.x-new/LICENSE) |
| [Scala Java 8 Compatibility Kit](https://github.com/scala/scala-java8-compat) | [(link)](https://search.maven.org/artifact/org.scala-lang.modules/scala-java8-compat_2.13) | [Apache 2.0](https://github.com/scala/scala-java8-compat/blob/main/LICENSE) |
| [Scala Parallel Collection](https://github.com/scala/scala-parallel-collections) | [(link)](https://search.maven.org/artifact/org.scala-lang.modules/scala-parallel-collections_2.13) | [Apache 2.0](https://github.com/scala/scala-parallel-collections/blob/main/LICENSE) |
| [Scala.js](https://github.com/scala-js/scala-js) | [(link)](https://search.maven.org/artifact/org.scala-js/scalajs-compiler_2.13.6) | [Apache 2.0](https://github.com/scala-js/scala-js/blob/master/LICENSE) |
| [Scala.js: DOM](https://github.com/scala-js/scala-js-dom) | [(link)](https://search.maven.org/artifact/org.scala-js/scalajs-dom_sjs1_2.13) | [MIT](https://github.com/scala-js/scala-js-dom/blob/master/LICENSE) |
| [Scala.js: Scalatags](https://github.com/com-lihaoyi/scalatags) | [(link)](https://search.maven.org/artifact/com.lihaoyi/scalatags_2.13) | [MIT](https://github.com/com-lihaoyi/scalatags/blob/master/LICENSE) |

In addition, Sireum includes adaptations/forks of the following artifacts:

| Source | Adaptation/Fork | License |
| :--- | :--- | :---: | 
| [ANTLRv3.g](https://github.com/antlr/grammars-v3/blob/master/Antlrv3/ANTLRv3.g) | [SireumAntlr3.g](https://github.com/sireum/parser/blob/master/jvm/src/main/resources/SireumAntlr3.g) | [BSD3](https://github.com/antlr/grammars-v3/blob/master/Antlrv3/ANTLRv3.g) |
| [diff_match_patch.java](https://github.com/google/diff-match-patch/blob/master/java/src/name/fraser/neil/plaintext/diff_match_patch.java) | [DiffMatchPatch](https://github.com/sireum/runtime/blob/master/library/jvm/src/main/java/org/sireum/DiffMatchPatch.java) | [Apache 2.0](https://github.com/google/diff-match-patch/blob/master/LICENSE) |
| [Geny](https://github.com/com-lihaoyi/geny) | [Jen](https://github.com/sireum/runtime/blob/master/library/shared/src/main/scala/org/sireum/Jen.scala) & [MJen](https://github.com/sireum/runtime/blob/master/library/shared/src/main/scala/org/sireum/MJen.scala) | [MIT](https://github.com/com-lihaoyi/geny/blob/master/LICENSE) |
| [SHA3IUF](https://github.com/brainhub/SHA3IUF) | [SHA3](https://github.com/sireum/runtime/blob/master/library/shared/src/main/scala/org/sireum/crypto/SHA3.scala) | [MIT](https://github.com/brainhub/SHA3IUF/blob/master/LICENSE) |
| [UnsafeUtils](https://github.com/plokhotnyuk/jsoniter-scala/tree/e089f06c2d8b4bdb87a6874e17bf716e8608b117/jsoniter-scala-examples/src/main/scala-2.13/com/github/plokhotnyuk/jsoniter_scala/examples) | [UnsafeUtils](https://github.com/sireum/runtime/blob/master/macros/shared/src/main/java/org/sireum/%24internal/UnsafeUtils.java) | [MIT](https://github.com/plokhotnyuk/jsoniter-scala/blob/e089f06c2d8b4bdb87a6874e17bf716e8608b117/LICENSE)
| [tmLanguage: AADL v2](https://github.com/virtuald/language-aadl/blob/119576c99d614383ba47a79c1759a368db4eac5f/grammars/aadl.cson) | [resources/aadl](https://github.com/sireum/resources/tree/master/textmate/aadl) | Public |
| [tmLanguage: smtlib2](https://github.com/AdrienChampion/tmlanguages/tree/8273ebf8cfb91afe4fc9af0e4c5a06c6187fc6f5/smtlib2) | [resources/smt2](https://github.com/sireum/resources/tree/master/textmate/smt2) | Public |
| [tmLanguage: SysML v2](https://gitlab.com/sensmetry/public/sysml-2ls/-/tree/092431855f8effa16901a9a6fb3a2fb9b3fee226/packages/syside-languageserver/syntaxes) | [resources/sysmlv2](https://github.com/sireum/resources/tree/master/textmate/sysml) | [Eclipse 2.0](https://github.com/sireum/resources/blob/master/textmate/sysml/LICENSE) |

### Application Dependencies

| Application | License |
| :--- | :---: |
| [Azul Zulu JDK FX](https://www.azul.com/downloads/?package=jdk-fx) | [GPL v2 with "Classpath" exception](https://docs.azul.com/core/tpl) |
| [Alt-Ergo (Free)](https://github.com/OCamlPro/alt-ergo/tree/2.3.3-free) | [Apache 2.0](https://github.com/OCamlPro/alt-ergo/blob/2.3.3-free/Apache-License-2.0.txt) |
| [CVC4/5](https://github.com/cvc5/cvc5) | [BSD3](https://github.com/cvc5/cvc5/blob/master/COPYING) |
| [Coursier](https://github.com/coursier/coursier) | [Apache 2.0](https://github.com/coursier/coursier/blob/master/LICENSE) |
| [Z3](https://github.com/Z3Prover/z3) | [MIT](https://github.com/Z3Prover/z3/blob/master/LICENSE.txt) |

Sireum stores small, pre-built binary executables in its submodule repositories for
[macOS](https://github.com/sireum/bin-mac), 
[Linux](https://github.com/sireum/bin-linux), and
[Windows](https://github.com/sireum/bin-windows) (please see the respective repository for virus analysis results).

| Pre-built Executable | License |
| :--- | :---: |
| [7-zip](https://sourceforge.net/projects/sevenzip) | [LGPL v2](https://www.7-zip.org/license.txt) |
| [pts-tiny-7z-sfx](https://github.com/sireum/7z-sfx) | [GPL v2](https://github.com/sireum/7z-sfx#readme) | 
| [upx](https://github.com/upx/upx) | [GPL v2](https://github.com/upx/upx/blob/devel/LICENSE) |

### Optional Application Dependencies

| Application | License |
| :--- | :---: |
| [checkstack.pl](https://github.com/torvalds/linux/blob/master/scripts/checkstack.pl) | [GPL v2](https://github.com/torvalds/linux/blob/master/COPYING) |
| [GraalVM](https://github.com/oracle/graal) | [GPL v2 with "Classpath" exception](https://github.com/oracle/graal/blob/master/LICENSE) |
| [IntelliJ](https://github.com/JetBrains/intellij-community) | [Apache 2.0](https://github.com/JetBrains/intellij-community/blob/master/LICENSE.txt) |
| [Mill](https://github.com/com-lihaoyi/mill) | [MIT](https://github.com/com-lihaoyi/mill/blob/main/LICENSE) |
| [OSATE2](https://github.com/osate/osate2) | [EPL 2.0](https://github.com/osate/osate2/blob/master/LICENSE) |

| IntelliJ Plugin | License |
|  :---  | :---: |
| [ANTLR4](https://plugins.jetbrains.com/plugin/7358-antlr-v4) | [BSD3](https://github.com/antlr/intellij-plugin-v4/blob/master/LICENSE) |
| [ASM](https://plugins.jetbrains.com/plugin/10302-asm-bytecode-viewer) | [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0) |
| [Native Debugging Support](https://plugins.jetbrains.com/plugin/12775-native-debugging-support) | Commercial (Ultimate only) |
| [JDT AstView](https://plugins.jetbrains.com/plugin/9345-jdt-astview) | [BSD2](https://github.com/ksu-cis-706/jdt-astview/blob/master/license.md) |
| [ReStructuredText](https://plugins.jetbrains.com/plugin/7124-restructuredtext) | [Apache 2.0](https://github.com/JetBrains/intellij-community/blob/master/LICENSE.txt) |
| [Rust](https://plugins.jetbrains.com/plugin/8182-rust) | [MIT](https://github.com/intellij-rust/intellij-toml/blob/master/LICENSE) |
| [Scala](https://plugins.jetbrains.com/plugin/1347-scala) | [Apache 2.0](https://github.com/JetBrains/intellij-scala/blob/idea212.x/LICENSE.txt) |
| [Sireum](https://github.com/sireum/intellij-plugin) | [BSD2](https://github.com/sireum/intellij-plugin/blob/master/license.md) |
| [Slang Injector](https://github.com/sireum/intellij-injector) | [BSD2](https://github.com/sireum/intellij-injector/blob/master/license.md) |
| [Terminal](https://plugins.jetbrains.com/plugin/13123-terminal) | [Apache 2.0](https://github.com/JetBrains/intellij-community/blob/master/LICENSE.txt) |
| [Toml](https://plugins.jetbrains.com/plugin/8195-toml) | [MIT](https://github.com/intellij-rust/intellij-toml/blob/master/LICENSE) |
