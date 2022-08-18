# Sireum: A High Assurance System Engineering Platform

[![License](https://img.shields.io/badge/License-BSD_2--Clause-brightgreen.svg)](https://github.com/sireum/kekinian/blob/master/license.md)
[![Actions Status](https://github.com/sireum/kekinian/workflows/macOS/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-macOS.yml) 
[![Actions Status](https://github.com/sireum/kekinian/workflows/Linux/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-linux.yml) 
[![Actions Status](https://github.com/sireum/kekinian/workflows/Windows/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-windows.yml) 
[![Actions Status](https://github.com/sireum/kekinian/workflows/macOS-CompCert/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-macOS-compcert.yml) 
[![Actions Status](https://github.com/sireum/kekinian/workflows/Linux-CompCert/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-linux-compcert.yml) 
[![Jitpack](https://jitpack.io/v/org.sireum/kekinian.svg)](https://jitpack.io/#org.sireum/kekinian)
[![Download!](http://files.sireum.org/case-env-build.png)](https://bit.ly/case-env)

## Overview

Sireum Kekinian is the most recent evolution of the Sireum platform whose 
core components are being built using the Sireum programming language -- Slang.

Slang is an OO/FP programming language with contract and proof languages
designed for formal verification and analyses; that is, it serves as the basis for the next generation
[Logika](http://logika.sireum.org) verifier and proof checker, as well as for other
formal method-based analysis techniques.
It is currently a subset of Scala 2.13 with tailored semantics 
enabled via Scala's 
[macro](https://github.com/sireum/runtime/blob/master/macros/shared/src/main/scala/org/sireum/%24internal/Macro.scala) 
and  [compiler plugin](https://github.com/sireum/scalac-plugin) 
facilities, supported with a customized version of IntelliJ -- 
Sireum IVE, and an accompanying build tool -- Proyek.

With the exception of a small part of its 
[runtime library](https://github.com/sireum/runtime) and its
parser that uses [scalameta](http://scalameta.org), 
the runtime library and the Slang [codebase](https://github.com/sireum/slang) 
itself (and analyses on top of it) are written using Slang.

Slang programs run on the JVM (Java 11+), in the browser or Node.js 
(via [Scala.js](http://scala-js.org) Javascript translation), and natively
via [Graal](http://graalvm.org) targeting macOS, Linux, and Windows on amd64, and
macOS and Linux on aarch64.

In addition, the Slang-to-C [transpiler](https://github.com/sireum/transpilers)
can compile a subset of Slang -- Slang Embedded (which excludes, e.g., closures and 
recursive types), to C99 without garbage-collection at runtime. 
The generated C code is both Slang source-traceable and 
in the form that is structurally close to the Slang source; 
in addition to `gcc` and `clang`, it can also be compiled using the 
[CompCert](http://compcert.inria.fr/) Verified C Compiler 
to provide a high-assurance toolchain for program correctness down to machine code.

On all compilation targets (e.g., JVM, C, Javascript, etc.), Slang provides extension
method facilities that can extend its language features and integrate with other 
programming languages, platform-specific libraries, and existing/legacy code.

Furthermore, Slang can also be used as a universal shell scripting language 
-- [Slash](https://github.com/sireum/slang-by-examples), which can run
on macOS, Linux, Windows, and others where a JVM runtime is available.
Slash powers many of the shell scripts for developing Kekinian itself.
As Slash is Slang, Slash scripts can be compiled to native via Graal, which speeds 
things up by virtue of having no JVM boot up time.

## Available Products

* [**Slang**](http://slang.sireum.org): 
  The Sireum Programming Language
  
  (ISoLA 2021: [presentation](https://bit.ly/slang-isola21), [paper](https://link.springer.com/chapter/10.1007%2F978-3-030-89159-6_17);
  [simple examples](https://github.com/sireum/slang-by-examples))


* [**HAMR**](http://hamr.sireum.org): 
  Slang-based **H**igh **A**ssurance **M**odel-based **R**apid Engineering of Embedded Systems
  
  (ISoLA 2021: [presentation](https://bit.ly/hamr-isola21), [paper](https://link.springer.com/chapter/10.1007%2F978-3-030-89159-6_18)) 


* **Logika**: The Sireum Verification Framework

  (see it in action in the [TCCoE 2022 presentation](https://github.com/sireum/tccoe22-logika);
  the Logika predecessor pedagogical tool for teaching, along with its documentation and course notes, are available at:
  http://logika.v3.sireum.org)


* **IVE**: Slang IntelliJ-based **I**ntegrated **V**erification **E**nvironment

  (see it in action in the [TCCoE 2022 presentation on Logika](https://github.com/sireum/tccoe22-logika))


* **Proyek**: Incremental and Parallel Slang Build Tool

  (also suitable for Java and Scala/Scala.js projects;
  examples: [simple](https://github.com/sireum/proyek-example), [for Sireum itself](https://github.com/sireum/kekinian/blob/master/bin/project.cmd))


* **Parser Generator**: LL(*k*), PEG/Packrat, or "mixed" (*k*-lookahead with backtracking) Slang parser generator

  It generates readable, easy-to-debug parser/lexer over Unicode codepoints in Slang, which can be compiled further 
  to native or Javascript.
  The [input grammar](https://github.com/sireum/parser/blob/master/jvm/src/main/resources/SireumAntlr3.g) 
  is a small subset of ANTLR3's grammar with [IDE](bin/install/antlrworks.cmd) support. 
  As an example, the parser generator uses its own
  [generated parser/lexer](https://github.com/sireum/parser/blob/master/shared/src/main/scala/org/sireum/parser/SireumGrammarParser.scala) 
  to parse its input grammar to build general
  [parse trees](https://github.com/sireum/runtime/blob/master/library/shared/src/main/scala/org/sireum/parser/ParseTree.scala)
  (that come with a generic tree rewriting algorithm for binary operators with configurable precedence/associativity rules after parsing).


* **Presentasi**: JavaFX Presentation Generator from Slang-based Specifications

  (including text-to-speech synthesizers; [simple example](https://github.com/sireum/presentasi-example),
  [TCCoE 2022 presentation on Logika](https://github.com/sireum/tccoe22-logika))


## Installing

Sireum is available as pre-built binaries/installers or from source.
The main advantage of using the source distribution is that updates
can be done incrementally while the binary distribution requires complete
re-installation. On the other hand, source distribution requires more setup.
As Sireum is currently in its early active development phase, it is highly
recommended to use the source distribution.

Additionally, it is included in a prebuilt
[case-env.ova](https://github.com/sireum/case-env) Debian VM
(which includes [seL4](https://sel4.systems),
[CompCert](https://compcert.org),
[OSATE](https://osate.org),
[FMIDE](https://github.com/loonwerks/formal-methods-workbench), etc.;
respective tool license terms apply).

### Binary Distributions

The binary distribution files are available at: https://github.com/sireum/kekinian/releases

Sireum binary distribution files are [7z](https://www.7-zip.org/7z.html) 
[self-extracting archives (SFX)](https://en.wikipedia.org/wiki/Self-extracting_archive) 
with command-line installers to (optionally) configure where Sireum should be 
installed (the files can also be extracted using a program capable of uncompressing `7z` archive).

If you want to ensure that the downloaded files are genuine, download 
the appropriate [Minisign](https://jedisct1.github.io/minisign/) signature file 
for the specific platform, then run:

```console
minisign -P RWShRZe/1tMRHAcQ2162Wq5FhU2ptktJdQxzUxvK0MwVjDYRC4JY87Fb -VHm <installer-file>
```

Set the `SIREUM_HOME` env var to the Sireum installation path, then proceed to [Using Sireum](#using-sireum).


### Git Source Distribution

#### Requirements:

* **macOS**: `curl` and `git`

* **Linux** (amd64, aarch64): `curl` and `git`

* **Windows**, either: 
  
  * Using a NTFS partition with [developer Mode enabled](https://docs.microsoft.com/en-us/windows/uwp/get-started/enable-your-device-for-development) and `git` ([Git For Windows](https://git-scm.com/download/win), [MSYS2](https://www.msys2.org/), or [Cygwin](https://www.cygwin.com)); or
  
  * [WSL2](https://docs.microsoft.com/en-us/windows/wsl/wsl2-index) (Linux requirements apply)

Note that Sireum stores small, pre-built binary dependencies in its repositories.
Virus analysis results are provided in the respective submodule repositories for 
[macOS](https://github.com/sireum/bin-mac), 
[Linux](https://github.com/sireum/bin-linux), and 
[Windows](https://github.com/sireum/bin-windows). 

#### Setup

In a console terminal:

* **macOS/Linux**:

  ```bash
  git clone --recursive https://github.com/sireum/kekinian
  kekinian/bin/build.cmd setup  # for non-POSIX shell, prefix with sh
  ```

* **Windows**:

  ```cmd
  git clone --recursive https://github.com/sireum/kekinian
  kekinian\bin\build.cmd setup
  ```

The above install Sireum command-line interface (CLI) and 
its IntelliJ-based Integrated Verification Environment (IVE),
as well as their dependencies.
Set the `SIREUM_HOME` env var to the `kekinian` path above.

To update later on, simply do a `git pull --recurse-submodules` and re-run 
`build.cmd`.
Note that after a `setup` update, it is best to invalidate IntelliJ's cache files 
and restart by using IntelliJ's `File -> Invalidate Caches...` 
menu item and select `Clear all file system cache and Local History`.

Occasionally, there might be new API used in `build.cmd` that is available 
in the pre-built binary online but not in your local copy.
This issue happens because `build.cmd` uses Sireum itself, hence it is a
bootstraping issue.
This issue typically manifests by `build.cmd` failing to compile/execute 
due to missing methods/classes.
In that case, first delete your local `sireum.jar` in the `bin` directory and 
then re-run `build.cmd setup`.

If rebuilding Sireum somehow failed still, try cleaning the repo:

* **macOS/Linux**:

  ```bash
  ${SIREUM_HOME}/bin/clean.sh
  ```

* **Windows**:

  ```cmd
  %SIREUM_HOME%\bin\clean.bat
  ```
  
The clean scripts remove all Sireum-related cache directories and revert any changes and delete new files in 
the local git repository.

After cleaning, re-run `git pull --recurse-submodules` (until it reaches a good fix-point) and `build.cmd setup`.

#### Remote Development Setup (Experimental)

##### Using JetBrains Projector

You can project Sireum IVE so it can be viewed by a web browser using 
[JetBrains Projector](https://lp.jetbrains.com/projector/) that ships with IVE.
To launch, click on "Projector" in the IVE status bar.

If you use Linux, you can launch Sireum IVE headless-ly by first installing the projector server:

```bash
$SIREUM_HOME/bin/install/projector-server.cmd
$SIREUM_HOME/bin/linux/idea/bin/projector-server.sh
```

Then open http://*`<machine-ip>`*:8887 in a browser.

##### Using JetBrains Gateway

IntelliJ Ultimate now supports remote development from a Linux server reachable using ssh, and 
Sireum IVE can be set up on top of it:

1. Use [JetBrains Gateway](https://www.jetbrains.com/remote-development/gateway/) to
   install an IntelliJ Ultimate instance in the Linux server.
   
   Note that the supported instance version of IntelliJ should be compatible with 
   what is listed in [version.properties](versions.properties) with property key
   `org.sireum.version.idea` property key). If the listed property value is 
   `20XX.Y.Z` or `20XX.Y`, then you need to IntelliJ Ultimate build that starts
   with`XXY`.
   
2. Connect to the server and install Sireum:

   ```
   git clone --recursive https://github.com/sireum/kekinian
   kekinian/bin/build.cmd setup-server
   ```
   
3. Open the `kekinian` path above with JetBrains Gateway/Client.

#### Using VirtualBox or VMWare

We provide a release build Linux VM `.ova` image with Sireum already set up, 
including OSATE, seL4, etc.:

https://bit.ly/case-env

## Using Sireum 

To launch the Sireum CLI or IVE:
 
* **macOS**:

  ```bash
  ${SIREUM_HOME}/bin/sireum                      # CLI
  open ${SIREUM_HOME}/bin/mac/idea/IVE.app       # IVE
  ```
 
* **Linux (amd64)**:
 
  ```bash
  ${SIREUM_HOME}/bin/sireum
  ${SIREUM_HOME}/bin/linux/idea/bin/IVE.sh
  ```
 
* **Linux (aarch64)**:
 
  ```bash
  ${SIREUM_HOME}/bin/sireum
  ${SIREUM_HOME}/bin/linux/arm/idea/bin/IVE.sh
  ```
 
* **Windows**:
 
  ```cmd
  %SIREUM_HOME%\bin\sireum.bat
  %SIREUM_HOME%\bin\win\idea\bin\IVE.exe
  ```

To proceed, please read the quick tutorial at: https://github.com/sireum/proyek-example


## Learning Slang by Examples

If you would like to learn Slang quickly, you can read and use Sireum IVE
to experiment with several examples designed to highlight various Slang language features:

https://github.com/sireum/slang-by-examples

## Sireum Development

Sireum is best developed (browsed/edited) by using Sireum IVE itself. 
The `build.cmd setup` command above setup IVE for Sireum development.
If you want to re-run just the IVE project re-generation (e.g., when 
there are upgrades to some library dependencies), do the following in 
a terminal:

* **macOS/Linux**:

  ```bash
  ${SIREUM_HOME}/bin/build.cmd project
  ```

* **Windows**:

  ```cmd
  %SIREUM_HOME%\bin\build.cmd project
  ```

Then open the `SIREUM_HOME` directory as a project in Sireum IVE.
 
To build Sireum assembly/CLI tool:

* **macOS/Linux**:

  ```bash
  ${SIREUM_HOME}/bin/build.cmd
  ```

* **Windows**:

  ```cmd
   %SIREUM_HOME%\bin\build.cmd
   ```

## Sireum Native Executable

It is recommended to compile Sireum (and Slash build scripts) to native as it removes JVM boot up time.

First, install [GraalVM](http://graalvm.org) [`native-image`'s prerequisites](https://www.graalvm.org/reference-manual/native-image/#prerequisites)
(note: `native-image` for Windows requires Visual Studio Community 2017 or 2019); 
then, to build Sireum native executable:

* **macOS/Linux**:

  ```bash
  ${SIREUM_HOME}/bin/build.cmd native
  ```

* **Windows**:
    
  * Visual Studio 2022 Enterprise

    ```cmd
    call "C:\Program Files\Microsoft Visual Studio\2022\Enterprise\VC\Auxiliary\Build\vcvars64.bat"
    %SIREUM_HOME%\bin\build.cmd native
    ```

  * Visual Studio 2019 Community
  
    ```cmd
    call "C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\VC\Auxiliary\Build\vcvars64.bat"
    %SIREUM_HOME%\bin\build.cmd native
    ```

  * Visual Studio 2017 Community
  
    ```cmd
    call "C:\Program Files (x86)\Microsoft Visual Studio\2017\Community\VC\Auxiliary\Build\vcvars64.bat"
    %SIREUM_HOME%\bin\build.cmd native
    ```
  
To run:

* **macOS**:

  ```bash
  ${SIREUM_HOME}/bin/mac/sireum
  ```
  
* **Linux**:

  ```bash
  ${SIREUM_HOME}/bin/linux/sireum
  ```
  
* **Windows**:
  
  ```cmd
  %SIREUM_HOME%\bin\win\sireum.exe
  ```
  
Note that once the native version is available (and has a newer timestamp),
`sireum` and `sireum.bat` in `bin` call the native version. 
 This is also similar for `build.cmd` in `bin`.

## Dependencies

Sireum depends on open source software libraries and applications
(the specific versions are listed in [versions.properties](versions.properties)).

### Library Dependencies

| Library | Maven | License |
| :--- | :---: | :---: | 
| [ANTLR3 Runtime](https://github.com/antlr/antlr3) | [(link)](https://search.maven.org/artifact/org.antlr/antlr-runtime) | [BSD3](https://www.antlr3.org/license.html) |
| [ASM](https://gitlab.ow2.org/asm/asm) | [(link)](https://search.maven.org/artifact/org.ow2.asm/asm) | [BSD3](https://gitlab.ow2.org/asm/asm/-/blob/master/LICENSE.txt) |
| [Automaton](https://github.com/sireum/automaton) | [(link)](https://jitpack.io/#org.sireum/automaton) | [BSD3](https://github.com/sireum/automaton/blob/codepoint/COPYING) |
| [Coursier](https://github.com/coursier/coursier) | [(link)](https://search.maven.org/artifact/io.get-coursier/coursier_2.13) | [Apache 2.0](https://github.com/coursier/coursier/blob/master/LICENSE) |
| [GitHub API](https://github.com/hub4j/github-api) | [(link)](https://search.maven.org/artifact/org.kohsuke/github-api) | [MIT](https://github.com/hub4j/github-api/blob/main/LICENSE.txt) |
| [NuProcess](https://github.com/brettwooldridge/NuProcess) | [(link)](https://search.maven.org/artifact/com.zaxxer/nuprocess) | [Apache 2.0](https://github.com/brettwooldridge/NuProcess/blob/master/LICENSE) |
| [OS-Lib](https://github.com/com-lihaoyi/os-lib) | [(link)](https://search.maven.org/artifact/com.lihaoyi/os-lib_2.13) | [MIT](https://github.com/com-lihaoyi/os-lib/blob/master/LICENSE) |
| [Scala](https://github.com/scala/scala) | [(link)](https://search.maven.org/artifact/org.scala-lang/scala-library) | [Apache 2.0](https://github.com/scala/scala/blob/2.13.x/LICENSE) |
| [Scalameta](https://github.com/scalameta/scalameta) | [(link)](https://search.maven.org/artifact/org.scalameta/scalameta_2.13) | [BSD3](https://github.com/scalameta/scalameta/blob/main/LICENSE.md) |
| [ScalaTest](https://github.com/scalatest/scalatest) | [(link)](https://search.maven.org/artifact/org.scalatest/scalatest_2.13) | [Apache 2.0](https://github.com/scalatest/scalatest/blob/3.2.x-new/LICENSE) |
| [Scala Java 8 Compatibility Kit](https://github.com/scala/scala-java8-compat) | [(link)](https://search.maven.org/artifact/org.scala-lang.modules/scala-java8-compat_2.13) | [Apache 2.0](https://github.com/scala/scala-java8-compat/blob/main/LICENSE) |
| [Scala Parallel Collection](https://github.com/scala/scala-parallel-collections) | [(link)](https://search.maven.org/artifact/org.scala-lang.modules/scala-parallel-collections_2.13) | [Apache 2.0](https://github.com/scala/scala-parallel-collections/blob/main/LICENSE) |
| [Scala.js](https://github.com/scala-js/scala-js) | [(link)](https://search.maven.org/artifact/org.scala-js/scalajs-compiler_2.13.6) | [Apache 2.0](https://github.com/scala-js/scala-js/blob/master/LICENSE) |
| [Scala.js: DOM](https://github.com/scala-js/scala-js-dom) | [(link)](https://search.maven.org/artifact/org.scala-js/scalajs-dom_sjs1_2.13) | [MIT](https://github.com/scala-js/scala-js-dom/blob/master/LICENSE) |
| [Scala.js: Scalatags](https://github.com/com-lihaoyi/scalatags) | [(link)](https://search.maven.org/artifact/com.lihaoyi/scalatags_2.13) | [MIT](https://github.com/com-lihaoyi/scalatags/blob/master/LICENSE) |

In addition, Sireum includes adaptation of the following artifact:

| Source | Adaptation | License |
| :--- | :--- | :---: | 
| [ANTLRv3.g](https://github.com/antlr/grammars-v3/blob/master/Antlrv3/ANTLRv3.g) | [SireumAntlr3.g](https://github.com/sireum/parser/blob/master/jvm/src/main/resources/SireumAntlr3.g) | [BSD3](https://github.com/antlr/grammars-v3/blob/master/Antlrv3/ANTLRv3.g) |
| [diff_match_patch.java](https://github.com/google/diff-match-patch/blob/master/java/src/name/fraser/neil/plaintext/diff_match_patch.java) | [DiffMatchPatch](https://github.com/sireum/runtime/blob/master/library/jvm/src/main/java/org/sireum/DiffMatchPatch.java) | [Apache 2.0](https://github.com/google/diff-match-patch/blob/master/LICENSE) |
| [Geny](https://github.com/com-lihaoyi/geny) | [Jen](https://github.com/sireum/runtime/blob/master/library/shared/src/main/scala/org/sireum/Jen.scala) & [MJen](https://github.com/sireum/runtime/blob/master/library/shared/src/main/scala/org/sireum/MJen.scala) | [MIT](https://github.com/com-lihaoyi/geny/blob/master/LICENSE) |
| [SHA3IUF](https://github.com/brainhub/SHA3IUF) | [SHA3](https://github.com/sireum/runtime/blob/master/library/shared/src/main/scala/org/sireum/crypto/SHA3.scala) | [MIT](https://github.com/brainhub/SHA3IUF/blob/master/LICENSE) |
| [UnsafeUtils](https://github.com/plokhotnyuk/jsoniter-scala/tree/e089f06c2d8b4bdb87a6874e17bf716e8608b117/jsoniter-scala-examples/src/main/scala-2.13/com/github/plokhotnyuk/jsoniter_scala/examples) | [UnsafeUtils](https://github.com/sireum/runtime/blob/master/macros/shared/src/main/java/org/sireum/%24internal/UnsafeUtils.java) | [MIT](https://github.com/plokhotnyuk/jsoniter-scala/blob/e089f06c2d8b4bdb87a6874e17bf716e8608b117/LICENSE)

### Application Dependencies

| Application | License |
| :--- | :---: |
| [Alt-Ergo (Free)](https://github.com/OCamlPro/alt-ergo/tree/2.3.3-free) | [Apache 2.0](https://github.com/OCamlPro/alt-ergo/blob/2.3.3-free/Apache-License-2.0.txt) |
| [Azul Zulu JDK FX](https://www.azul.com/downloads/?package=jdk-fx) | [GPL v2 with "Classpath" exception](https://docs.azul.com/core/tpl) |
| [CVC4/5](https://github.com/cvc5/cvc5) | [BSD3](https://github.com/cvc5/cvc5/blob/master/COPYING) |
| [Z3](https://github.com/Z3Prover/z3) | [MIT](https://github.com/Z3Prover/z3/blob/master/LICENSE.txt) |

Sireum stores small, pre-built binary executables in its submodule repositories for
[macOS](https://github.com/sireum/bin-mac), 
[Linux](https://github.com/sireum/bin-linux), and
[Windows](https://github.com/sireum/bin-windows) (please see the respective repository for virus analysis results).

| Pre-built Executable | License |
| :--- | :---: |
| [7-zip](https://sourceforge.net/projects/sevenzip) | [LGPL v2](https://www.7-zip.org/license.txt) |
| [fsnotifier](https://github.com/JetBrains/intellij-community/tree/master/native/fsNotifier) | [Apache 2.0](https://github.com/JetBrains/intellij-community/blob/master/LICENSE.txt) |
| [pts-tiny-7z-sfx](https://github.com/sireum/7z-sfx) | [GPL v2](https://github.com/sireum/7z-sfx#readme) | 
| [upx](https://github.com/upx/upx) | [GPL v2](https://github.com/upx/upx/blob/devel/LICENSE) |

### Optional Application Dependencies

| Application | License |
| :--- | :---: |
| [checkstack.pl](https://github.com/torvalds/linux/blob/master/scripts/checkstack.pl) | [GPL v2](https://github.com/torvalds/linux/blob/master/COPYING) |
| [GraalVM](https://github.com/oracle/graal) | [GPL v2 with "Classpath" exception](https://github.com/oracle/graal/blob/master/LICENSE) |
| [IntelliJ](https://github.com/JetBrains/intellij-community) | [Apache 2.0](https://github.com/JetBrains/intellij-community/blob/master/LICENSE.txt) |
| [JetBrains Runtime](https://github.com/JetBrains/JetBrainsRuntime) | [GPL v2 with "Classpath" exception](https://github.com/JetBrains/JetBrainsRuntime/blob/master/LICENSE) |
| [Mill](https://github.com/com-lihaoyi/mill) | [MIT](https://github.com/com-lihaoyi/mill/blob/main/LICENSE) |
| [OSATE2](https://github.com/osate/osate2) | [EPL 2.0](https://github.com/osate/osate2/blob/master/LICENSE) |

| IntelliJ Plugin | License |
|  :---  | :---: |
| [ANTLR4](https://plugins.jetbrains.com/plugin/7358-antlr-v4) | [BSD3](https://github.com/antlr/intellij-plugin-v4/blob/master/LICENSE) |
| [ASM](https://plugins.jetbrains.com/plugin/10302-asm-bytecode-viewer) | [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0) |
| [JDT AstView](https://plugins.jetbrains.com/plugin/9345-jdt-astview) | [BSD2](https://github.com/ksu-cis-706/jdt-astview/blob/master/license.md) |
| [Projector](https://plugins.jetbrains.com/plugin/16015-projector) | [GPL v2 with "Classpath" exception](https://github.com/JetBrains/projector-server/blob/master/GPLv2%2BCPE.txt) |
| [ReStructuredText](https://plugins.jetbrains.com/plugin/7124-restructuredtext) | [Apache 2.0](https://github.com/JetBrains/intellij-community/blob/master/LICENSE.txt) |
| [Scala](https://plugins.jetbrains.com/plugin/1347-scala) | [Apache 2.0](https://github.com/JetBrains/intellij-scala/blob/idea212.x/LICENSE.txt) |
| [Sireum](https://github.com/sireum/intellij-plugin) | [BSD2](https://github.com/sireum/intellij-plugin/blob/master/license.md) |
| [Slang Injector](https://github.com/sireum/intellij-injector) | [BSD2](https://github.com/sireum/intellij-injector/blob/master/license.md) |
| [Toml](https://plugins.jetbrains.com/plugin/8195-toml) | [MIT](https://github.com/intellij-rust/intellij-toml/blob/master/LICENSE) |