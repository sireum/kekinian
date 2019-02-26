# Sireum: A High-Assurance Software Development Platform

| CI Status | macOS | Linux | Windows |
| :----: | :---: | :---: | :---: | 
| master | [![travis](https://travis-ci.org/sireum/kekinian.svg?branch=master)](https://travis-ci.org/sireum/kekinian) | [![shippable](https://api.shippable.com/projects/5ab59969f488d607007cd6c0/badge?branch=master)](https://app.shippable.com/github/sireum/kekinian/dashboard) | [![appveyor](https://ci.appveyor.com/api/projects/status/1k6elsvubt5r3adm?svg=true)](https://ci.appveyor.com/project/robby-phd/kekinian) |

Sireum Kekinian is the most recent evolution of the Sireum platform whose 
core components are being built using the Sireum Programming Language (Slang).

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
via [Graal](http://graalvm.org) and also via compilation to C.

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
Windows (or, one can simply download the distribution files and extract them 
using a program capable of uncompressing `7z` archive).

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

* **macOS**: `7z` (p7zip from, e.g., [MacPorts](https://macports.org) or [Homebrew](https://brew.sh/))

* **Linux**: `curl`, `git`, and `7z` (install from the Linux distribution package manager,
  e.g., `apt install curl git p7zip-full` in Ubuntu 18.04 universe)

* **Windows**: [Developer Mode enabled](https://docs.microsoft.com/en-us/windows/uwp/get-started/enable-your-device-for-development), `git` ([Git For Windows](https://git-scm.com/download/win), [MSYS2](https://www.msys2.org/), or [Cygwin](https://www.cygwin.com)) and [7-Zip](https://7-zip.org)

  If using Cygwin:
  
  ```
  export CYGWIN=winsymlinks:nativestrict
  ```
  
  Otherwise: 
  
  ```bash
  export MSYS=winsymlinks:nativestrict
  ```


#### Setup

In a console terminal:

* **macOs/Linux**:

  ```bash
  git clone --recursive https://github.com/sireum/kekinian
  kekinian/bin/build.cmd setup
  ```

* **Windows**:

  ```cmd
  git clone --recursive https://github.com/sireum/kekinian
  kekinian\bin\build.cmd setup
  ```


To update later on, simply re-run `build.cmd setup`.


## Using Sireum IVE

First, set the `SIREUM_HOME` env var to the Sireum installation path.

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

Sireum is best developed (browsed/edited) by using Sireum IVE itself. 
The `build.cmd setup` command above setup IVE for Sireum development.
If you want to re-run just the IVE project re-generation, do the following in 
a terminal:

* **macOS/Linux**:

  ```bash
  ${SIREUM_HOME}/bin/build.cmd project
  ```

* **Windows**:

  ```cmd
  ${SIREUM_HOME}\bin\build.cmd project
  ```

Then open the `SIREUM_HOME` directory as a project in Sireum IVE.

To have the codebase and its test suites recompiled upon changes, run:

* **macOS/Linux**:


  ```bash
  cd ${SIREUM_HOME} && bin/mill -w cli.tests.compile
  ```

  and to build its assembly:

  ```bash
  cd ${SIREUM_HOME} && bin/mill build
  ```

* **Windows**:


  ```cmd
  cd %SIREUM_HOME% && bin\mill.bat -w cli.tests.compile
  ```

  and to build its assembly:

  ```cmd
  cd %SIREUM_HOME% && bin\mill.bat build
  ```

#### Sireum Native Executable

To build native executable (currently only available under macOS or Linux; for macOS, GraalVM is assumed to be available in the PATH env var):

```bash
${SIREUM_HOME}/bin/build.cmd native
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