# Sireum: A High-Assurance Software Development Platform

Sireum Kekinian is the most recent evolution of the Sireum platform whose 
core components are being built using the Sireum Programming Language (Slang).

Slang is an OO/FP programming language with contract and proof languages
designed for formal analyses; it serves as the basis for the next generation
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

Slang programs run on the JVM (Java 8+), and in the browser or Node.js 
(via [Scala.js](http://scala-js.org) Javascript translation).
