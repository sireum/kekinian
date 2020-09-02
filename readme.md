# Sireum: A High-Assurance System Engineering Platform

| [![Actions Status](https://github.com/sireum/kekinian/workflows/CI/badge.svg)](https://github.com/sireum/kekinian/actions) | [![Build Status](https://travis-ci.org/sireum/kekinian.svg?branch=master)](https://travis-ci.org/sireum/kekinian) | [![](https://jitpack.io/v/org.sireum/kekinian.svg)](https://jitpack.io/#org.sireum/kekinian) |
| :---: | :---: | :---: | 
| <sub><sup>amd64: mac, linux, windows</sup></sub> | <sub><sup>aarch64: linux</sup></sub> | <sub><sup>maven package repository</sup></sub> |

* [Overview](#overview)
* [Installing](#installing)
  * [Binary Distributions](#binary-distributions)
  * [Git Source Distribution](#git-source-distribution)
    * [Requirements](#requirements)
    * [Setup](#setup)
* [Using Sireum IVE](#using-sireum-ive)
  * [Slang Script Example Project](#slang-script-example-project)
  * [Learning Slang](#learning-slang)
  * [Slang App Example Mill Project](#slang-app-example-mill-project)
* [Sireum Kekinian Development](#sireum-kekinian-development)
  * [Using Sireum IVE](#using-sireum-ive)
  * [Using Scala Metals](#using-scala-metals)
* [Sireum Native Executable via Graal](#sireum-native-executable-via-graal)
---

## Overview

Sireum Kekinian is the most recent evolution of the Sireum platform whose 
core components are being built using the Sireum programming language -- Slang.

Slang is an OO/FP programming language with contract and proof languages
designed for formal verification and analyses; that is, it serves as the basis for the next generation
[Logika](http://logika.sireum.org) verifier and proof checker, as well as for other
formal method-based analysis techniques.
It is currently a subset of Scala 2.x with different memory models 
enabled via Scala's 
[macro](https://github.com/sireum/runtime/blob/master/macros/shared/src/main/scala/org/sireum/%24internal/Macro.scala) 
and  [compiler plugin](https://github.com/sireum/scalac-plugin) 
facilities, with support for [IntelliJ](https://github.com/sireum/intellij-injector).

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
can compile a subset of Slang -- Slang Embedded, which does not include 
closure and recursive types, can be compiled to C without
garbage-collection at runtime. 
The generated C code is both Slang source-traceable and 
in the form that is structurally close to the Slang source; 
in addition to `gcc` and `clang`, it can also be compiled using the 
[CompCert](http://compcert.inria.fr/) Verified C Compiler 
to provide a high-assurance toolchain for program correctness down to machine code.

## Installing

Sireum is available as pre-built binaries/installers or from source.
The main advantage of using the source distribution is that updates
can be done incrementally while the binary distribution requires complete
re-installation. On the other hand, source distribution requires more setup.

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


### Git Source Distribution

#### Requirements:

* **macOS**: none

* **Linux** (amd64, aarch64): `curl` and `git`

* **Windows**, either: 
  
  * [Developer Mode enabled](https://docs.microsoft.com/en-us/windows/uwp/get-started/enable-your-device-for-development) and `git` ([Git For Windows](https://git-scm.com/download/win), [MSYS2](https://www.msys2.org/), or [Cygwin](https://www.cygwin.com)); or
  
  * [WSL2](https://docs.microsoft.com/en-us/windows/wsl/wsl2-index) (Linux requirements apply)


#### Setup

In a console terminal:

* **macOs/Linux**:

  ```bash
  git clone --recursive https://github.com/sireum/kekinian
  kekinian/bin/build.cmd setup  # for non-POSIX shell, prefix with sh
  ```

* **Windows**:

  ```cmd
  git clone --recursive https://github.com/sireum/kekinian
  kekinian\bin\build.cmd setup
  ```

Set the `SIREUM_HOME` env var to the `kekinian` path above.

To update later on, simply do a `git pull --recurse-submodules` and re-run 
`build.cmd setup` (or simply `build.cmd` to rebuild Sireum CLI tools).

If rebuilding Sireum somehow failed, try cleaning the repo:

* **macOS/Linux**:

  ```bash
  ${SIREUM_HOME}/bin/clean.sh
  ```

* **Windows**:

  ```cmd
  %SIREUM_HOME%\bin\clean.bat
  ```
  
and re-run `build.cmd setup`.

## Using Sireum IVE

### Slang Script Example Project

To generate a "hello world" Slang script project in the current directory
and launch Sireum IVE:

* **macOS**: 

  ```bash
  # Generates ./hello project directory with ./hello/src/script.sc
  ${SIREUM_HOME}/bin/sireum tools ivegen .
  open ${SIREUM_HOME}/bin/mac/idea/IVE.app
  ```

* **Linux**:

  ```bash
  # Generates ./hello project directory with ./hello/src/script.sc
  ${SIREUM_HOME}/bin/sireum tools ivegen .
  ${SIREUM_HOME}/bin/linux/idea/bin/IVE.sh
  ```

* **Windows**:

  ```cmd
  REM Generates .\hello project directory with .\hello\src\script.sc
  %SIREUM_HOME%\bin\sireum.bat tools ivegen . 
  cmd /C %SIREUM_HOME%\bin\win\idea\bin\IVE.exe
  ```
  
Once Sireum IVE is running, open the `hello` directory as a project,
then open the `script.sc` file for editing.
(When asked to add `Ammonite` dependencies, choose `Ignore` for now; you can add
the dependencies but it might take a while to download the packages.)

To run the script, click on the green ► button at the top-left part of the editor
(or click on the green ► button on the right side of "Run script.sc" at
the top-right part of the window).

#### Learning Slang

If you would like to learn Slang quickly, you can read and use the IVE to experiment
with several examples designed to highlight various Slang language features:

https://github.com/sireum/slang-by-examples


### Slang App Example [Mill](https://www.lihaoyi.com/mill/) Project

To generate a "hello world" Slang app project in the current directory
and launch Sireum IVE:

* **macOS**: 

  ```bash
  # Generates ./hello-app project directory with ./hello-app/hello-app/src/app.scala
  ${SIREUM_HOME}/bin/sireum tools ivegen -m mill -n hello-app .
  ```

* **Linux**:

  ```bash
  # Generates ./hello-app project directory with ./hello-app/hello-app/src/app.scala
  ${SIREUM_HOME}/bin/sireum tools ivegen -m mill -n hello-app .
  ```

* **Windows**:

  ```cmd
  REM Generates .\hello-app project directory with .\hello-app\hello-app\src\app.scala
  %SIREUM_HOME%\bin\sireum.bat tools ivegen -m mill -n hello-app . 
  ```
  
Open the `hello-app` directory as a project in Sireum IVE, open the `app.scala` 
file for editing.  To run it, click on the green ► button near the definition of 
`object app` in the editor.

Note that `mill` (or `mill.bat` under Windows) is available under the `bin`
directory of `SIREUM_HOME`.


### Sireum Kekinian Development

#### Using Sireum IVE

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

To have the codebase and its test suites recompiled upon changes, run:

* **macOS/Linux**:

  ```bash
  cd ${SIREUM_HOME} && bin/mill -w cli.tests.compile
  ```

  and to build its assembly/CLI tool:

  ```bash
  ${SIREUM_HOME}/bin/build.cmd
  ```

* **Windows**:

  ```cmd
  cd %SIREUM_HOME% && bin\mill.bat -w cli.tests.compile
  ```

  and to build its assembly/CLI tool:

  ```cmd
  %SIREUM_HOME%\bin\build.cmd
  ```
  
#### Using Scala Metals

An alternative development environment is [Scala Metals](https://scalameta.org/metals/).
Below are the instruction steps on how to set it up with [VSCode](https://code.visualstudio.com/):

1. Prepare Sireum:

   * macOS/Linux
   
     ```
     git clone --recursive https://github.com/sireum/kekinian
     kekinian/bin/build.cmd
     ``` 
   
   * Windows
   
     ```
     git clone --recursive https://github.com/sireum/kekinian
     kekinian\bin\build.cmd
     ``` 

2. Set the `SIREUM_HOME` environment variable (user-wide) to the absolute path of `kekinian`

3. Install VSCode with the [Scala Metals extension](https://marketplace.visualstudio.com/items?itemName=scalameta.metals).
   
4. Set the following Metals' settings in VSCode:

   * `Java Home`
   
     * **macOS**: set to the absolute path of `kekinian\bin\mac\java`.
     
     * **Linux**: set to the absolute path of `kekinian\bin\linux\java`.
     
     * **Windows**: set to the absolute path of `kekinian/bin/win/java`.

   * `Mill Script`
     
     * **macOS/Linux**: set to the absolute path of `kekinian/bin/mill` .
     
     * **Windows**: set to the absolute path of `kekinian\bin\mill.bat`.
   
4. Open the `kekinian` folder in VSCode, and import the build
   when asked (also available as `Metals: Import build` in the command 
   palette).

5. Run `Metals: Recompile Workspace` in the command palette.

### Sireum Native Executable via Graal

It is recommended to compile Sireum and its [Slash](https://github.com/sireum/slang-by-examples/blob/master/src/slash.cmd) build scripts to native as it speeds up build tasks.

First, install [GraalVM](http://graalvm.org) [`native-image`'s prerequisites](https://www.graalvm.org/docs/reference-manual/aot-compilation/#prerequisites)
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
 This is also similar for `build.cmd` in `bin` and `bin/mill-build/bin`.
