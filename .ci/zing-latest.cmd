::/*#! 2> /dev/null                                 #
@ 2>/dev/null # 2>nul & echo off & goto BOF         #
if [ -z ${SIREUM_HOME} ]; then                      #
  echo "Please set SIREUM_HOME env var"             #
  exit -1                                           #
fi                                                  #
exec ${SIREUM_HOME}/bin/sireum slang run "$0" "$@"  #
:BOF
setlocal
if not defined SIREUM_HOME (
  echo Please set SIREUM_HOME env var
  exit /B -1
)
%SIREUM_HOME%\bin\sireum.bat slang run "%0" %*
exit /B %errorlevel%
::!#*/
// #Sireum

import org.sireum._

var jdkVersion: String = "jdk15"

Os.cliArgs match {
  case ISZ(s) => jdkVersion = s"jdk$s"
  case _ =>
    println("Usage: <jdk-version> ")
    Os.exit(-1)
}
val f = Os.temp()
f.removeAll()
f.downloadFrom("https://docs.azul.com/prime/prime-quick-start-tar.html")
f.removeOnExit()
val content = ops.StringOps(f.read)
var i = z"0"
while (i >= 0) {
  i = content.stringIndexOfFrom("https://cdn.azul.com/zing-zvm/", i)
  if (i >= 0) {
    val j = content.stringIndexOfFrom("\"", i + 1)
    val url = ops.StringOps(content.substring(i, j))
    if (url.contains(jdkVersion)) {
      val s: String = "/zing"
      var k = url.stringIndexOf(s)
      k = url.stringIndexOfFrom(s, k + s.size)
      val l = url.stringIndexOfFrom("-linux", k + s.size)
      print(url.substring(k + s.size, l))
      Os.exit(0)
    }
    i = i + 1
  }
}
Os.exit(-1)
