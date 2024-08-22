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
| [ANTLR3 Runtime](https://github.com/antlr/antlr3) | [(link)](https://central.sonatype.com/artifact/org.antlr/antlr-runtime) | [BSD3](https://www.antlr3.org/license.html) |
| [ANTLR4 Runtime](https://github.com/antlr/antlr4) | [(link)](https://central.sonatype.com/artifact/org.antlr/antlr4-runtime) | [BSD3](https://www.antlr.org/license.html) |
| [Apache Common Compress](https://commons.apache.org/proper/commons-compress) | [(link)](https://central.sonatype.com/artifact/org.apache.commons/commons-compress) | [Apache 2.0](https://gitbox.apache.org/repos/asf?p=commons-compress.git;a=blob_plain;f=LICENSE.txt;hb=HEAD) |
| [ASM](https://gitlab.ow2.org/asm/asm) | [(link)](https://central.sonatype.com/artifact/org.ow2.asm/asm) | [BSD3](https://gitlab.ow2.org/asm/asm/-/blob/master/LICENSE.txt) |
| [Automaton](https://github.com/sireum/automaton) | [(link)](https://jitpack.io/#org.sireum/automaton) | [BSD3](https://github.com/sireum/automaton/blob/codepoint/COPYING) |
| [GitHub API](https://github.com/hub4j/github-api) | [(link)](https://central.sonatype.com/artifact/org.kohsuke/github-api) | [MIT](https://github.com/hub4j/github-api/blob/main/LICENSE.txt) |
| [NuProcess](https://github.com/brettwooldridge/NuProcess) | [(link)](https://central.sonatype.com/artifact/com.zaxxer/nuprocess) | [Apache 2.0](https://github.com/brettwooldridge/NuProcess/blob/master/LICENSE) |
| [OS-Lib](https://github.com/com-lihaoyi/os-lib) | [(link)](https://central.sonatype.com/artifact/com.lihaoyi/os-lib_2.13) | [MIT](https://github.com/com-lihaoyi/os-lib/blob/master/LICENSE) |
| [JavaFX for Presentasi](https://github.com/sireum/presentasi-jfx) | [(link)](https://jitpack.io/#org.sireum/presentasi-jfx) | [BSD2](https://github.com/sireum/presentasi-jfx/blob/master/license.md) |
| [Scala](https://github.com/scala/scala) | [(link)](https://central.sonatype.com/artifact/org.scala-lang/scala-library) | [Apache 2.0](https://github.com/scala/scala/blob/2.13.x/LICENSE) |
| [Scalafmt](https://github.com/scalameta/scalafmt) | [(link)](https://central.sonatype.com/artifact/org.scalameta/scalafmt-cli_2.13) | [Apache 2.0](https://github.com/scalameta/scalafmt/blob/master/LICENCE.md) |
| [Scalameta](https://github.com/scalameta/scalameta) | [(link)](https://central.sonatype.com/artifact/org.scalameta/scalameta_2.13) | [BSD3](https://github.com/scalameta/scalameta/blob/main/LICENSE.md) |
| [ScalaTest](https://github.com/scalatest/scalatest) | [(link)](https://central.sonatype.com/artifact/org.scalatest/scalatest_2.13) | [Apache 2.0](https://github.com/scalatest/scalatest/blob/3.2.x-new/LICENSE) |
| [Scala Java 8 Compatibility Kit](https://github.com/scala/scala-java8-compat) | [(link)](https://central.sonatype.com/artifact/org.scala-lang.modules/scala-java8-compat_2.13) | [Apache 2.0](https://github.com/scala/scala-java8-compat/blob/main/LICENSE) |
| [Scala Parallel Collection](https://github.com/scala/scala-parallel-collections) | [(link)](https://central.sonatype.com/artifact/org.scala-lang.modules/scala-parallel-collections_2.13) | [Apache 2.0](https://github.com/scala/scala-parallel-collections/blob/main/LICENSE) |
| [Scala.js](https://github.com/scala-js/scala-js) | [(link)](https://central.sonatype.com/artifact/org.scala-js/scalajs-compiler_2.13.14) | [Apache 2.0](https://github.com/scala-js/scala-js/blob/master/LICENSE) |
| [Scala.js: DOM](https://github.com/scala-js/scala-js-dom) | [(link)](https://central.sonatype.com/artifact/org.scala-js/scalajs-dom_sjs1_2.13) | [MIT](https://github.com/scala-js/scala-js-dom/blob/master/LICENSE) |
| [Scala.js: Scalatags](https://github.com/com-lihaoyi/scalatags) | [(link)](https://central.sonatype.com/artifact/com.lihaoyi/scalatags_2.13) | [MIT](https://github.com/com-lihaoyi/scalatags/blob/master/LICENSE) |
| [SMT-LIB Parser](https://github.com/sireum/smtlib-parser) | [(link)](https://jitpack.io/#org.sireum/smtlib-parser) | [MIT](https://github.com/julianthome/smtlibv2-grammar/blob/master/LICENCE.md) |
| [SysML v2 Parser for HAMR](https://github.com/sireum/hamr-sysml-parser) | [(link)](https://jitpack.io/#org.sireum/hamr-sysml-parser) | [LGPL3](https://github.com/Systems-Modeling/SysML-v2-Pilot-Implementation/blob/master/LICENSE) |

In addition, Sireum includes adaptations of the following artifacts:

| Source | Adaptation | License |
| :--- | :--- | :---: | 
| [ANTLRv3.g](https://github.com/antlr/grammars-v3/blob/master/Antlrv3/ANTLRv3.g) | [SireumAntlr3.g](https://github.com/sireum/parser/blob/master/jvm/src/main/resources/SireumAntlr3.g) | [BSD3](https://github.com/antlr/grammars-v3/blob/master/Antlrv3/ANTLRv3.g) |
| [diff_match_patch.java](https://github.com/google/diff-match-patch/blob/master/java/src/name/fraser/neil/plaintext/diff_match_patch.java) | [DiffMatchPatch](https://github.com/sireum/runtime/blob/master/library/jvm/src/main/java/org/sireum/DiffMatchPatch.java) | [Apache 2.0](https://github.com/google/diff-match-patch/blob/master/LICENSE) |
| [Geny](https://github.com/com-lihaoyi/geny) | [Jen](https://github.com/sireum/runtime/blob/master/library/shared/src/main/scala/org/sireum/Jen.scala) & [MJen](https://github.com/sireum/runtime/blob/master/library/shared/src/main/scala/org/sireum/MJen.scala) | [MIT](https://github.com/com-lihaoyi/geny/blob/master/LICENSE) |
| [JetBrains Mono](https://github.com/JetBrains/JetBrainsMono/tree/v2.304) & [FiraCode](https://github.com/tonsky/FiraCode/tree/e9943d2d631a4558613d7a77c58ed1d3cb790992) | [Sireum Mono](https://github.com/sireum/resources/tree/master/fonts) | [OFL](https://github.com/JetBrains/JetBrainsMono/blob/v2.304/OFL.txt) & [OFL](https://github.com/tonsky/FiraCode/blob/e9943d2d631a4558613d7a77c58ed1d3cb790992/LICENSE)|
| [SHA3IUF](https://github.com/brainhub/SHA3IUF) | [SHA3](https://github.com/sireum/runtime/blob/master/library/shared/src/main/scala/org/sireum/crypto/SHA3.scala) | [MIT](https://github.com/brainhub/SHA3IUF/blob/master/LICENSE) |
| [UnsafeUtils](https://github.com/plokhotnyuk/jsoniter-scala/tree/e089f06c2d8b4bdb87a6874e17bf716e8608b117/jsoniter-scala-examples/src/main/scala-2.13/com/github/plokhotnyuk/jsoniter_scala/examples) | [UnsafeUtils](https://github.com/sireum/runtime/blob/master/macros/shared/src/main/java/org/sireum/%24internal/UnsafeUtils.java) | [MIT](https://github.com/plokhotnyuk/jsoniter-scala/blob/e089f06c2d8b4bdb87a6874e17bf716e8608b117/LICENSE) |


### Application Dependencies

| Application | License |
| :--- | :---: |
| [Liberica JDK (Full)](https://bell-sw.com/libericajdk/) | [GPL v2 with "Classpath" exception](https://github.com/bell-sw/Liberica/blob/master/LICENSE) |
| [CVC4/5](https://github.com/cvc5/cvc5) | [BSD3](https://github.com/cvc5/cvc5/blob/master/COPYING) |
| [Coursier](https://github.com/coursier/coursier) | [Apache 2.0](https://github.com/coursier/coursier/blob/master/LICENSE) |
| [JaCoCo](https://github.com/jacoco/jacoco) | [EPL 2.0](https://github.com/jacoco/jacoco/blob/master/LICENSE.md) |
| [Z3](https://github.com/Z3Prover/z3) | [MIT](https://github.com/Z3Prover/z3/blob/master/LICENSE.txt) |


Sireum stores small, pre-built binary executables in its submodule repositories for
[macOS](https://github.com/sireum/bin-mac), 
[Linux](https://github.com/sireum/bin-linux), and
[Windows](https://github.com/sireum/bin-windows) (please see the respective repository for virus analysis results).

| Pre-built Executable | License |
| :--- | :---: |
| [7z](https://sourceforge.net/projects/sevenzip) | [LGPL v2, BSD3, unRAR restriction](https://www.7-zip.org/license.txt) |


### Optional Applications

| Application | License |
| :--- | :---: |
| [checkstack.pl](https://github.com/torvalds/linux/blob/master/scripts/checkstack.pl) | [GPL v2](https://github.com/torvalds/linux/blob/master/COPYING) |
| [pts-tiny-7z-sfx](https://github.com/sireum/7z-sfx) | [GPL v2](https://github.com/sireum/7z-sfx#readme) | 
| [ACL2](https://www.cs.utexas.edu/~moore/acl2/) | [BSD3](https://www.cs.utexas.edu/~moore/acl2/v8-5/HTML/LICENSE) |
| [Alt-Ergo](https://alt-ergo.ocamlpro.com/) | [OCamlPro-Non-Commercial-License](https://github.com/OCamlPro/alt-ergo/blob/next/licenses/OCamlPro-Non-Commercial-License.pdf) |
| [Brave Browser](https://brave.com/) | [MPL 2.0](https://github.com/brave/brave-browser/blob/master/LICENSE) |
| [CompCert](https://compcert.org/) | [INRIA Non-Commercial License Agreement](https://compcert.org/doc/LICENSE.txt) |
| [Coq](https://coq.inria.fr/) | [LGPL 2.1](https://github.com/coq/coq/blob/master/LICENSE) |
| [CLion](https://www.jetbrains.com/clion/) | Commercial |
| [FM Workbench](https://github.com/loonwerks/formal-methods-workbench) | [BSD3](https://github.com/loonwerks/formal-methods-workbench/blob/master/LICENSE) |
| [GraalVM (OpenJDK)](https://github.com/oracle/graal) | [GPL v2 with "Classpath" exception](https://github.com/oracle/graal/blob/master/LICENSE) |
| [IntelliJ](https://github.com/JetBrains/intellij-community) | [Apache 2.0](https://github.com/JetBrains/intellij-community/blob/master/LICENSE.txt) |
| [Isabelle](https://isabelle.in.tum.de/) | [BSD3](https://isabelle-dev.sketis.net/source/isabelle/browse/default/COPYRIGHT) |
| [Oracle GraalVM](https://www.oracle.com/java/graalvm/) | [GFTC](https://www.oracle.com/downloads/licenses/graal-free-license.html) |
| [OSATE2](https://github.com/osate/osate2) | [EPL 2.0](https://github.com/osate/osate2/blob/master/LICENSE) |
| [Rust](https://www.rust-lang.org/) | [MIT](https://www.rust-lang.org/policies/licenses) |
| [VSCodium](https://vscodium.com/) | [MIT](https://github.com/VSCodium/vscodium/blob/master/LICENSE) |


| IntelliJ/CLion Plugin | License |
|  :---  | :---: |
| [ANTLR4](https://plugins.jetbrains.com/plugin/7358-antlr-v4) | [BSD3](https://github.com/antlr/intellij-plugin-v4/blob/master/LICENSE) |
| [ASM](https://github.com/sireum/intellij-asm) | [Apache 2.0](https://github.com/sireum/intellij-asm/blob/master/LICENSE) |
| [JDT AstView](https://plugins.jetbrains.com/plugin/9345-jdt-astview) | [BSD2](https://github.com/ksu-cis-706/jdt-astview/blob/master/license.md) |
| [Native Debugging Support](https://plugins.jetbrains.com/plugin/12775-native-debugging-support) | Commercial (Ultimate only) |
| [PDF Viewer](https://plugins.jetbrains.com/plugin/14494-pdf-viewer) | [MIT](https://github.com/FirstTimeInForever/intellij-pdf-viewer/blob/master/LICENSE) |
| [PlantUML Integration](https://plugins.jetbrains.com/plugin/7017-plantuml-integration) | [Apache 2.0](https://github.com/esteinberg/plantuml4idea/blob/master/LICENSE.txt) |
| [Rust](https://plugins.jetbrains.com/plugin/22407-rust) | Commercial (Ultimate only) |
| [Scala](https://plugins.jetbrains.com/plugin/1347-scala) | [Apache 2.0](https://github.com/JetBrains/intellij-scala/blob/idea212.x/LICENSE.txt) |
| [Sireum](https://github.com/sireum/intellij-plugin) | [BSD2](https://github.com/sireum/intellij-plugin/blob/master/license.md) |
| [Slang Injector](https://github.com/sireum/intellij-injector) | [BSD2](https://github.com/sireum/intellij-injector/blob/master/license.md) |
| [Terminal](https://plugins.jetbrains.com/plugin/13123-terminal) | [Apache 2.0](https://github.com/JetBrains/intellij-community/blob/master/LICENSE.txt) |
| [TeXiFy IDEA](https://plugins.jetbrains.com/plugin/9473-texify-idea) | [MIT](https://github.com/Hannah-Sten/TeXiFy-IDEA/blob/master/LICENSE) |
| [Toml](https://plugins.jetbrains.com/plugin/8195-toml) | [MIT](https://github.com/intellij-rust/intellij-toml/blob/master/LICENSE) |


| VSCodium Extension | License |
|  :---  | :---: |
| [clangd](https://open-vsx.org/extension/llvm-vs-code-extensions/vscode-clangd) | [MIT](https://github.com/clangd/vscode-clangd/blob/master/LICENSE) |
| [Bash IDE](https://open-vsx.org/extension/mads-hartmann/bash-ide-vscode) | [MIT](https://github.com/bash-lsp/bash-language-server/blob/main/LICENSE) |
| [Git Graph](https://open-vsx.org/extension/mhutchie/git-graph) | [MIT w/ non-derivative work restrictions](https://github.com/mhutchie/vscode-git-graph/blob/develop/LICENSE) |
| [HTML CSS](https://open-vsx.org/extension/ecmel/vscode-html-css) | [MIT](https://github.com/ecmel/vscode-html-css/blob/master/LICENSE.md) |
| [Hugo Utilities](https://open-vsx.org/extension/kofuk/hugo-utils) | [MIT](https://github.com/kofuk/vscode-hugo-utils/blob/master/LICENSE) |
| [Java](https://open-vsx.org/extension/redhat/java) | [EPL 2.0](https://github.com/redhat-developer/vscode-java) |
| [LaTeX Workshop](https://open-vsx.org/extension/James-Yu/latex-workshop) | [MIT](https://github.com/James-Yu/LaTeX-Workshop/blob/master/LICENSE.txt) |
| [Python](https://open-vsx.org/extension/ms-python/python) | [MIT](https://github.com/microsoft/vscode-python/blob/main/LICENSE) |
| [Rust Analyzer](https://open-vsx.org/extension/rust-lang/rust-analyzer) | [MIT](https://github.com/rust-lang/rust-analyzer/blob/master/LICENSE-MIT) & [Apache 2.0](https://github.com/rust-lang/rust-analyzer/blob/master/LICENSE-APACHE) |
| [Scala Metals](https://open-vsx.org/extension/scalameta/metals) | [Apache 2.0](https://github.com/scalameta/metals/blob/main/LICENSE) |
| [SysIDE CE](https://open-vsx.org/extension/sensmetry/sysml-2ls) | [EPL 2.0](https://gitlab.com/sensmetry/public/sysml-2ls/-/blob/main/LICENSE?ref_type=heads) |
| [VerilogHDL/SystemVerilog/Bluespec SystemVerilog](https://open-vsx.org/extension/mshr-h/veriloghdl) | [MIT](https://github.com/mshr-h/vscode-verilog-hdl-support/blob/main/LICENSE) |
| [XML](https://open-vsx.org/extension/redhat/vscode-xml) | [EPL 2.0](https://github.com/redhat-developer/vscode-xml/blob/main/LICENSE) |
| [YAML](https://open-vsx.org/extension/redhat/vscode-yaml) | [MIT](https://github.com/redhat-developer/vscode-yaml/blob/main/LICENSE) |
| [Zip Tools](https://open-vsx.org/extension/adamraichu/zip-viewer) | [MIT](https://github.com/AdamRaichu/vscode-zip-viewer/blob/main/LICENSE.md) |
