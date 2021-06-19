# Sireum: A High Assurance System Engineering Platform

[![Actions Status](https://github.com/sireum/kekinian/workflows/macOS/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-macOS.yml) 
[![Actions Status](https://github.com/sireum/kekinian/workflows/Linux/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-linux.yml) 
[![Actions Status](https://github.com/sireum/kekinian/workflows/Windows/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-windows.yml) 
[![Actions Status](https://github.com/sireum/kekinian/workflows/macOS-CompCert/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-macOS-compcert.yml) 
[![Actions Status](https://github.com/sireum/kekinian/workflows/Linux-CompCert/badge.svg)](https://github.com/sireum/kekinian/actions/workflows/CI-linux-compcert.yml) 
[![](https://jitpack.io/v/org.sireum/kekinian.svg)](https://jitpack.io/#org.sireum/kekinian)
 
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

Slang programs run on the JVM (Java 8+), in the browser or Node.js 
(via [Scala.js](http://scala-js.org) Javascript translation), and natively
via [Graal](http://graalvm.org) targeting macOS, Linux, and Windows on amd64, and
Linux on aarch64.

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

* [HAMR](http://hamr.sireum.org): A **H**igh **A**ssurance **M**odel-based **R**apid Engineering of Embedded Systems

## Installing

Sireum is available as pre-built binaries/installers or from source.
The main advantage of using the source distribution is that updates
can be done incrementally while the binary distribution requires complete
re-installation. On the other hand, source distribution requires more setup.
As Sireum is currently in its early active development phase, it is highly
recommended to use the source distribution.

### Binary Distributions

Sireum binary distribution files are [7z](https://www.7-zip.org/7z.html) 
[self-extracting archives (SFX)](https://en.wikipedia.org/wiki/Self-extracting_archive) 
with command-line installers to (optionally) configure where Sireum should be 
installed.
 
Below are the installation instructions for 64-bit (amd64) macOS, Linux, and 
Windows (or, one can simply download the distribution files from the
[GitHub releases page](https://github.com/sireum/kekinian/releases)
and extract them  using a program capable of uncompressing `7z` archive).

* **macOS**: run the following command in a terminal:

  ```bash
  (sd=sireum-dev-mac.sfx && curl -JLo $sd -c /dev/null http://mac.distro.sireum.org && chmod +x $sd && p=$(pwd) && cd /tmp && $p/$sd)
  ```

* **Linux**: download and run [sireum-dev-linux.sfx](http://linux.distro.sireum.org), 
  or run the following command in a terminal if you have `curl` installed:

  ```bash
  (sd=sireum-dev-linux.sfx && curl -JLo $sd -c /dev/null http://linux.distro.sireum.org && chmod +x $sd && p=$(pwd) && cd /tmp && $p/$sd)
  ```

* **Windows**: download and run [sireum-dev-win.exe](http://win.distro.sireum.org)

If you want to ensure that the downloaded files are genuine, download 
the appropriate [Minisign](https://jedisct1.github.io/minisign/) signature files 
for the specific platforms for [macOS](http://minisig.mac.distro.sireum.org), 
[Linux](http://minisig.linux.distro.sireum.org), and 
[Windows](http://minisig.win.distro.sireum.org), then run:

```console
minisign -P RWShRZe/1tMRHAcQ2162Wq5FhU2ptktJdQxzUxvK0MwVjDYRC4JY87Fb -Vm <installer-file>
```

Alternatively, you can also use a port of [OpenBSD's signify](https://man.openbsd.org/signify) for your
operating system (e.g., `signify-openbsd` in Ubuntu) instead of `minisign` as follows:

```console
signify-openbsd -V -p sireum.pub -x <installer-file>.minisig -m <installer-file>
```

where `sireum.pub`'s content is:

```
untrusted comment: Sireum
RWShRZe/1tMRHAcQ2162Wq5FhU2ptktJdQxzUxvK0MwVjDYRC4JY87Fb
```

Set the `SIREUM_HOME` env var to the Sireum installation path, then proceed to [Using Sireum](#using-sireum).


### Git Source Distribution

#### Requirements:

* **macOS**: `curl` and `git`

* **Linux** (amd64, aarch64): `curl` and `git`

* **Windows**, either: 
  
  * [Developer Mode enabled](https://docs.microsoft.com/en-us/windows/uwp/get-started/enable-your-device-for-development) and `git` ([Git For Windows](https://git-scm.com/download/win), [MSYS2](https://www.msys2.org/), or [Cygwin](https://www.cygwin.com)); or
  
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
`build.cmd setup` (or simply `build.cmd` to rebuild Sireum CLI tools).
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


#### Using Vagrant and VirtualBox

By using [Vagrant](https://www.vagrantup.com/), you can automatically provision a 
[VirtualBox](https://www.virtualbox.org) [Xubuntu](https://xubuntu.org/) 
virtual machine (VM) with Sireum set up as follows:

1. Download the [resources/vagrant](resources/vagrant) folder as an archive
   ([link](https://downgit.github.io/#/home?url=https://github.com/sireum/kekinian/tree/master/resources/vagrant&fileName=sireum-vagrant&rootDirectory=sireum-vagrant)) 
   and uncompress it.

2. If desired, modify the [Vagrantfile](resources/vagrant/Vagrantfile) to customize the number of CPUs (default: 4),
   RAM (default: 16GB), and VRAM (default: 64MB).
   Note that the disk size defaults to 64GB, which is derived from the [bento/ubuntu](https://app.vagrantup.com/bento)
   base box.
   Moreover, it installs
   [FMIDE](https://github.com/loonwerks/formal-methods-workbench), 
   [CLion](https://www.jetbrains.com/clion), and 
   [CompCert](http://compcert.inria.fr) by default.
   These can be adjusted by commenting out lines that start with `bin/install/` in the [Vagrantfile](resources/vagrant/Vagrantfile).
  
3. Run the following in a terminal console inside the uncompressed directory:

   * **macOS/Linux**:
   
     ```bash
     bash setup.sh
     ```
   
   * **Windows**:

     ```cmd
     setup.bat
     ```
   
Once the VM has been provisioned successfully, you can log in as the user `vagrant` with default password `vagrant`.
Sireum is installed in `/home/vagrant/Sireum`, which the `SIREUM_HOME` environment variable is set to.

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

  * Visual Studio 2017 Community
  
    ```cmd
    call "C:\Program Files (x86)\Microsoft Visual Studio\2017\Community\VC\Auxiliary\Build\vcvars64.bat"
    %SIREUM_HOME%\bin\build.cmd native
    ```

  * Visual Studio 2019 Community
  
    ```cmd
    call "C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\VC\Auxiliary\Build\vcvars64.bat"
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
| [ASM](https://gitlab.ow2.org/asm/asm) | [(link)](https://search.maven.org/artifact/org.ow2.asm/asm) | [BSD3](https://gitlab.ow2.org/asm/asm/-/blob/master/LICENSE.txt) |
| [Coursier](https://github.com/coursier/coursier) | [(link)](https://search.maven.org/artifact/io.get-coursier/coursier_2.13) | [Apache 2.0](https://github.com/coursier/coursier/blob/master/LICENSE) |
| [GitHub API](https://github.com/hub4j/github-api) | [(link)](https://search.maven.org/artifact/org.kohsuke/github-api) | [MIT](https://github.com/hub4j/github-api/blob/main/LICENSE.txt) |
| [JavaFX](https://github.com/openjdk/jfx) | [(link)](https://search.maven.org/artifact/org.openjfx/javafx) | [GPL v2 with "Classpath" exception](https://github.com/openjdk/jfx) |
| [NuProcess](https://github.com/brettwooldridge/NuProcess) | [(link)](https://search.maven.org/artifact/com.zaxxer/nuprocess) | [Apache 2.0](https://github.com/brettwooldridge/NuProcess/blob/master/LICENSE) |
| [OS-Lib](https://github.com/com-lihaoyi/os-lib) | [(link)](https://search.maven.org/artifact/com.lihaoyi/os-lib_2.13) | [MIT](https://github.com/com-lihaoyi/os-lib/blob/master/LICENSE) |
| [Scala](https://github.com/scala/scala) | [(link)](https://search.maven.org/artifact/org.scala-lang/scala-library) | [Apache 2.0](https://github.com/scala/scala/blob/2.13.x/LICENSE) |
| [Scalameta](https://github.com/scalameta/scalameta) | [(link)](https://search.maven.org/artifact/org.scalameta/scalameta_2.13) | [BSD3](https://github.com/scalameta/scalameta/blob/main/LICENSE.md) |
| [ScalaTest](https://github.com/scalatest/scalatest) | [(link)](https://search.maven.org/artifact/org.scalatest/scalatest_2.13) | [Apache 2.0](https://github.com/scalatest/scalatest/blob/3.2.x-new/LICENSE) |
| [Scala Java 8 Compatibility Kit](https://github.com/scala/scala-java8-compat) | [(link)](https://search.maven.org/artifact/org.scala-lang.modules/scala-java8-compat_2.13) | [Apache 2.0](https://github.com/scala/scala-java8-compat/blob/main/LICENSE) |
| [Scala Parallel Collection](https://github.com/scala/scala-parallel-collections) | [(link)](https://search.maven.org/artifact/org.scala-lang.modules/scala-parallel-collections_2.13) | [Apache 2.0](https://github.com/scala/scala-parallel-collections/blob/main/LICENSE) |
| [Scala.js](https://github.com/scala-js/scala-js) | [(link)](https://search.maven.org/artifact/org.scala-js/scalajs-compiler_2.13.6) | [Apache 2.0](https://github.com/scala-js/scala-js/blob/master/LICENSE) |
| [Scala.js: DOM](https://github.com/scala-js/scala-js-dom) | [(link)](https://search.maven.org/artifact/org.scala-js/scalajs-dom_sjs0.6_2.13) | [MIT](https://github.com/scala-js/scala-js-dom/blob/master/LICENSE) |
| [Scala.js: jQuery Facade](https://github.com/jducoeur/jquery-facade) | [(link)](https://search.maven.org/artifact/org.querki/jquery-facade_sjs1_2.13) | [MIT](https://github.com/jducoeur/jquery-facade/blob/master/README.md#license) |
| [Scala.js: Scalatags](https://github.com/com-lihaoyi/scalatags) | [(link)](https://search.maven.org/artifact/com.lihaoyi/scalatags_2.13) | [MIT](https://github.com/com-lihaoyi/scalatags/blob/master/LICENSE) |

In addition, Sireum includes adaptation of the following code:

| Source | Adaptation | License |
| :--- | :--- | :---: | 
| [diff_match_patch](https://github.com/google/diff-match-patch/blob/master/java/src/name/fraser/neil/plaintext/diff_match_patch.java) | [DiffMatchPatch](https://github.com/sireum/runtime/blob/master/library/jvm/src/main/java/org/sireum/DiffMatchPatch.java) | [Apache 2.0](https://github.com/google/diff-match-patch/blob/master/LICENSE) |
| [Geny](https://github.com/com-lihaoyi/geny) | [Jen](https://github.com/sireum/runtime/blob/master/library/shared/src/main/scala/org/sireum/Jen.scala) & [MJen](https://github.com/sireum/runtime/blob/master/library/shared/src/main/scala/org/sireum/MJen.scala) | [MIT](https://github.com/com-lihaoyi/geny/blob/master/LICENSE) |
| [SHA3UIF](https://github.com/brainhub/SHA3IUF) | [SHA3](https://github.com/sireum/runtime/blob/master/library/shared/src/main/scala/org/sireum/crypto/SHA3.scala) | [MIT](https://github.com/brainhub/SHA3IUF/blob/master/LICENSE) |
| [UnsafeUtils](https://github.com/plokhotnyuk/jsoniter-scala/tree/e089f06c2d8b4bdb87a6874e17bf716e8608b117/jsoniter-scala-examples/src/main/scala-2.13/com/github/plokhotnyuk/jsoniter_scala/examples) | [UnsafeUtils](https://github.com/sireum/runtime/blob/master/macros/shared/src/main/java/org/sireum/%24internal/UnsafeUtils.java) | [MIT](https://github.com/plokhotnyuk/jsoniter-scala/blob/e089f06c2d8b4bdb87a6874e17bf716e8608b117/LICENSE)

### Application Dependencies

| Application | License |
| :--- | :---: |
| [Azul Zulu JDK](https://www.azul.com/downloads/) | [GPL v2 with "Classpath" exception](https://docs.azul.com/core/tpl) |
| [CVC4](https://github.com/cvc5/cvc5) | [BSD3](https://github.com/cvc5/cvc5/blob/master/COPYING) |
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
| [IntelliJ + Plugins](https://github.com/JetBrains/intellij-community) | [Apache 2.0](https://github.com/JetBrains/intellij-community/blob/master/LICENSE.txt) |
| [IntelliJ ANTLR4 Plugin](https://github.com/antlr/intellij-plugin-v4) | [BSD3](https://github.com/antlr/intellij-plugin-v4/blob/master/LICENSE) |
| [JetBrains Runtime](https://github.com/JetBrains/JetBrainsRuntime) | [GPL v2 with "Classpath" exception](https://github.com/JetBrains/JetBrainsRuntime/blob/master/LICENSE) |
| [Mill](https://github.com/com-lihaoyi/mill) | [MIT](https://github.com/com-lihaoyi/mill/blob/main/LICENSE) |
| [OSATE2](https://github.com/osate/osate2) | [EPL 2.0](https://github.com/osate/osate2/blob/master/LICENSE) |
