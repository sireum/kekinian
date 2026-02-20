Assist the user with JVM profiling using async-profiler in the Sireum/Kekinian project. Use the reference below to set up and run profiling, then analyze the output.

## Setup

async-profiler v4.3 is installed per-platform under `bin/`:
- macOS: `bin/mac/async-profiler/lib/libasyncProfiler.dylib`
- Linux x64: `bin/linux/async-profiler/lib/libasyncProfiler.so` (if installed)
- Linux arm64: `bin/linux/arm/async-profiler/lib/libasyncProfiler.so` (if installed)

If the library is missing for the current platform, download from [async-profiler releases](https://github.com/async-profiler/async-profiler/releases):
- macOS: `async-profiler-<ver>-macos.zip` → extract to `bin/mac/async-profiler/`
- Linux x64: `async-profiler-<ver>-linux-x64.tar.gz` → extract to `bin/linux/async-profiler/`
- Linux arm64: `async-profiler-<ver>-linux-arm64.tar.gz` → extract to `bin/linux/arm/async-profiler/`

No Windows build exists (async-profiler is Linux/macOS only).

## Running Allocation Profiling

Use `JAVA_TOOL_OPTIONS` to propagate the agent to all spawned JVMs (`proyek test` spawns separate test runner JVMs):

```bash
# macOS lib path: bin/mac/async-profiler/lib/libasyncProfiler.dylib
# Linux lib path: bin/linux/async-profiler/lib/libasyncProfiler.so

SIREUM_NATIVE=false \
JAVA_TOOL_OPTIONS="-agentpath:<lib-path>=start,event=alloc,file=<session-dir>/alloc-ap-%p.txt,collapsed,jstackdepth=128" \
bin/sireum proyek test --slice <module> --classes <test.class> <project-dir>
```

CRITICAL: The `-agentpath:` value must be an **absolute path** to the library. Relative paths fail because spawned JVMs have different working directories.

Key options: `event=alloc` (allocation profiling), `collapsed` (one-line-per-stack text format), `jstackdepth=128` (deep stacks), `%p` (per-JVM PID in filename), `SIREUM_NATIVE=false` (forces JVM mode).

For CPU profiling, use `event=cpu` instead of `event=alloc`.

## Identifying the Right Output File

`proyek test` spawns ~4 JVMs. The test runner produces the **largest** file: `ls -lhS <session-dir>/alloc-ap-*.txt`.

## Analyzing Collapsed Stack Output

Format: `frame1;frame2;...;allocType_[i] count`. Use perl scripts (not one-liner awk) on macOS — complex regexes with `[]` and `$` break in shell one-liners. Write scripts into the session subdirectory and run via `perl <script> < <datafile>`.

```bash
# Top allocation types
perl -ne 'if (/;([^\s;]*_\[i\])\s+(\d+)$/) { $t{$1}+=$2 } END { printf "%10d %s\n", $t{$_}, $_ for sort { $t{$b}<=>$t{$a} } keys %t }' <session-dir>/alloc-ap-XXXXX.txt | head -20

# Find O(n²) ISZ append hotspots (what calls IS.:+)
perl -ne 'if (/(\d+)$/) { $c=$1; @f=split/;/; for my $i(0..$#f) { if($f[$i]=~/IS\.\$colon\$plus/) { $s{$f[$i-1]}+=$c; last } } } END { printf "%10d %s\n",$s{$_},$_ for sort{$s{$b}<=>$s{$a}}keys%s }' <session-dir>/alloc-ap-XXXXX.txt | head -10
```

**Top hotspots by deepest sireum frame + type** — the most accurate analysis. Save as `<session-dir>/analyze-hotspots.pl`:
```perl
#!/usr/bin/perl
# Summarize top allocation hotspots by unique method + type
while (<STDIN>) {
  if (/(\d+)$/) {
    my $c = $1;
    my @f = split /;/;
    my $type = $f[-1];
    $type =~ s/\s+\d+$//;
    # Find the deepest sireum frame (excluding test framework)
    for my $i (reverse 0..$#f-1) {
      if ($f[$i] =~ /sireum/ && $f[$i] !~ /scalatest/) {
        my $key = "$f[$i] → $type";
        $s{$key} += $c;
        last;
      }
    }
  }
}
my $n = 0;
for (sort { $s{$b} <=> $s{$a} } keys %s) {
  printf "%10d %s\n", $s{$_}, $_;
  last if ++$n >= 25;
}
```
Run: `perl <session-dir>/analyze-hotspots.pl < <session-dir>/alloc-ap-XXXXX.txt`

**Phase breakdown** — when classifying stacks into phases, match on specific method names (e.g., `Foo$.methodName`) rather than just class names. A class may appear in multiple phases' stacks (e.g., a callback object created in phase A but called from phase B). Order `elsif` chains from most specific to least specific.

## Why Not JFR

GraalVM JVMCI JDK (which Sireum uses) caps JFR allocation sample stack traces at 5 frames regardless of `-XX:FlightRecorderOptions=stackdepth`. async-profiler's JFR output format (`FLR` header) is also incompatible with the JDK `jfr` tool. Always use async-profiler with `collapsed` text format.
