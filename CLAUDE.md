# Kekinian Project

**Note:** This file is shared across contributors. Do not include machine-specific full paths (e.g., `/Users/someone/...`). Use relative paths or generic descriptions instead.

## Coding Conventions
- When modifying a method that has documentation (Scaladoc, comments, etc.), always update the documentation to reflect the code change in the same edit.
- When generating code with method/constructor calls that have more than 3 arguments, use named arguments for readability (e.g., `Rule(name = n, isLexer = F, isFragment = F, isHidden = F, isSynthetic = T, posOpt = pOpt, alts = alts)`).

## Slang Language

### Core Rules
- CRITICAL: Non-`@strictpure` methods (including nested `def`s) must use explicit `return` keyword for every return path. Slang does not support implicit returns from match expressions or other blocks. Every branch in a `match` must have `return`.
- Non-`@strictpure` methods can use if-else as expressions for `val` initialization (e.g., `val x: T = if (...) { ... } else { ... }`), similar to Scala. This avoids code duplication and unnecessary initial allocation.
- `@strictpure` methods cannot have `return`, loops (`while`/`for` without `yield`), `var`, assignments other than `val` initialization, or nested `def`s. They must be single expressions. `for-yield` is allowed.
- When writing Slang code, prioritize `@strictpure` methods over regular methods, except when loops or mutable variables are needed.
- `@strictpure` annotation must be explicitly specified on every strictpure method, including nested `def`s inside other methods.
- `for-yield` cannot have code blocks `{ ... }`. The yield body must be a single expression. Inline variable declarations directly into the expression (e.g., use `for (e <- xs) yield st"${f(e)}"` instead of `for (e <- xs) yield { val v = f(e); st"$v" }`).
- `@memoize` methods cannot have empty parameter lists `()`. Use parameterless def syntax instead.
- Function parameters expecting `@pure` callees need `@pure` on the function type (e.g., `((E, Z), (E, Z)) => B @pure`). Regular Scala lambdas satisfy this from non-Slang code.
- Slang does not support nested classes. All `@datatype`, `@record`, `@sig`, and `@enum` declarations must be at the top level or inside a top-level `object`.
- Slang does NOT support method overloading. Each method name must be unique within a scope.
- Slang traits (`@datatype trait`, `@record trait`, `@sig`, `@msig`) support concrete method implementations (like Scala traits), not just abstract declarations. Use this to avoid repeating the same override in every subtype.

### Built-in Types
- Slang built-in type declarations (B, C, Z, F32, F64, R, String, ST, IS, MS, etc.) are in `runtime/library/shared/src/main/scala/org/sireum/BuiltInTypes.slang`. The Slang type checker reads this file to know which methods are available on these types. When adding new methods to `IS.scala`/`MS.scala` (plain Scala), the corresponding declaration must also be added here for Slang code to call them.
- User-defined types written in Slang (`@datatype`, `@record`, `@sig`, `@msig`, `@enum`) do NOT need to be declared in `BuiltInTypes.slang`. Only built-in types that are implemented in plain Scala need declarations there.

### Types and Collections
- Slang `C` (character) supports comparison operators directly: `c1 <= c2`, `c1 < c2`, `c1 >= c2`, etc. No need to convert to `Z` or `U32` first for comparisons.
- Convert `C` to `Z` simply with `.toZ` (e.g., `c.toZ`). No need for the verbose `conversions.U32.toZ(conversions.C.toU32(c))` roundtrip.
- `ops.StringOps` has no `charAt` — use `conversions.String.toCis(text)` + indexing for character access.
- Slang `String` can't chain method calls like `.replaceAllLiterally(...).replaceAllLiterally(...)` — use sequential `var` assignments.
- Slang distinguishes object immutability at the type level. Immutable types are transitive, i.e., they cannot hold mutable types. Prefer to use mutable objects for shallow structures mixed with immutable ones at deeper level, or at the localized scope.
- `@datatype` = immutable, `@record` = mutable.
- Mutable objects (`@record`) are deeply cloned on assignment to fields, local variables, or array elements (if the object was previously assigned). Returning a mutable object also creates a deep copy if it has been previously assigned to a field, array element, or local variable. Parameter passing creates an alias, with the restriction that a mutable object and one of its substructures cannot be passed simultaneously as arguments. Hence, mutable object graphs are at most trees, while immutable ones (`@datatype`) can form DAGs.
- To avoid deep-copy overhead when "returning" a mutable object, use the "hand-me-down" maneuver, a.k.a. out parameter (C#, Ada) or destination-passing style (DPS): pass an `MBox[T]` as a parameter and have the method construct the result directly into `MBox.value` instead of returning the mutable object. Do not assign the mutable object to a local variable first — assigning to `MBox.value` (a field) also triggers a deep copy if the object was previously assigned.
- Collections: `ISZ` (immutable seq), `MSZ (mutable seq)`, `HashSMap` (ordered hash map by insertion order), `HashSSet` (ordered hash set by insertion order).
- Unsigned integer types (`U8`, `U16`, `U32`, `U64`) print as hex by default in string interpolation.
- `Z` and `@range` class types do not support bitwise operations (`&`, `|`, `<<`, `>>`, `>>>`, `~`). Use unsigned integer types (`U8`, `U16`, `U32`, `U64`) for bitwise ops, converting via `conversions.Z.toU32`/`conversions.U32.toZ` etc.
- Use `~>` for map entry creation with `+` to add to maps.
- `MSZ[Subtype].toIS` returns `ISZ[Subtype]`, not `ISZ[Supertype]` — Scala's `=:=` evidence requires exact type match. Use `MSZ[SuperType]` directly so `.toIS` returns the expected type (e.g., `MSZ[Token]` instead of `MSZ[ParseTree.Leaf]` when `ISZ[Token]` is needed).
- `@record class MStack[T]` cannot have a method returning `ISZ[T]` because `T` could be mutable, but `ISZ` requires immutable element types. Use `@record class MIStack[@imm T]` to constrain `T` to immutable types, enabling a `toISZ` method.
- Check `runtime/library/shared/src/main/scala/org/sireum/conversions.scala` for available direct conversion methods between scalar types (e.g., `conversions.S32.toU32`, `conversions.C.toS32`) instead of always routing through Z (e.g., `conversions.Z.toU32(conversions.S32.toZ(x))`).
- `s32"..."`, `u32"..."`, etc. are optimized by the scalac plugin to `S32(intLiteral)` / `U32(intLiteral)` constructor calls — compile-time constants, no runtime string parsing. `IS[S32, T].apply(S32)` uses the S32 index type directly — the `@bits` `toIndex` method returns an `Int` which IS uses for raw array access.
- `@datatype` and `@record` classes compile to **final** JVM classes. `INVOKEVIRTUAL` on these types is effectively non-virtual (JVM devirtualizes).
- `MStack.peek` is defined as `def peek: T` (no parens). Calling with `stack.peek()` gives a confusing "Cannot invoke" Slang type-check error.
- Use `if (cond) halt(msg)` instead of `assert(cond, msg)` in hot paths. Scala's `assert` takes by-name `message: => Any`, creating a lambda closure on EVERY call even when the condition passes. `if`/`halt` only constructs the message string on the error path.

### String Templates (ST)
- Use `st"..."` or `st"""..."""` string interpolator to create `ST` values.
- `${(seq, sep)}` renders each element of a sequence with a separator in between.
- `$opt` renders `Some`'s element and nothing for `None`.
- In triple-quoted `st"""...|..."""`, `|` is the margin delimiter for auto-indentation.
- ST computes indentation automatically based on argument position — nested multi-line ST values are indented correctly.
- To include literal `"` in generated code within `st"""..."""`: just write `"` directly (allowed inside triple-quoted strings as long as fewer than three consecutive `"`).
- Example generating `u32"0"`: `st"""u32"${conversions.U32.toZ(v)}""""`  (the 4th `"` is content, the last `"""` closes).
- `render` produces the full string; `renderCompact` collapses contiguous whitespace to single spaces.
- `\` in `st"..."` is an escape character, same as in regular Scala strings. `st"\n"` produces a newline, `st"\\"` produces `\`.

### Serialization (toCompact / fromCompact)
- Slang `@datatype`/`@record`/`@sig`/`@msig` types can be serialized to compact base64 strings using `MessagePack` + LZ4 compression + Base64 encoding by generating `fromCompact/toCompact'.
- **`toCompact`**: serializes via `MessagePack.writer(T)`, writes fields with `w.writeZ`/`w.writeU32`/`w.writeString`/`w.writeB`/etc., then `ops.StringOps.toBase64(ops.ISZOps.lz4Compress(w.result))`. The uncompressed data adheres to the standard [MessagePack](https://msgpack.org) binary format (adapted from `msgpack4z-native`).
- **`fromCompact`**: deserializes via `ops.ISZOps.lz4Decompress(ops.StringOps.fromBase64(s).left).left`, then `MessagePack.reader(data)` with `r.init()` and `r.readZ`/`r.readU32`/etc.
- **`toCompactST`**: convenience method returning `st"""Xxx.fromCompact("${toCompact}")"""`
- For `@sig` trait hierarchies, use integer tags to distinguish variants: write a tag via `w.writeZ(tag)` before fields, read it back to dispatch to the correct constructor.
- Used by `NGrammar`, `PredictiveTable`, and `LexerDfas` to embed serialized data structures as string constants in generated parser source files.
- **Auto-generated serializers**: `sireum tools sergen` generates `JSON` and `MsgPack` serializer objects for `@datatype`/`@record`/`@sig`/`@msig` types (e.g., `slang/tipe/shared/.../JSON.scala`, `MsgPack.scala`). These are higher-level than hand-written `toCompact`/`fromCompact` and support full type hierarchies automatically. Both use standard formats (JSON and MessagePack respectively). Limitations: does not work on generic types (all type parameters must be instantiated), and does not work with nested collection classes. The JSON de/serialization is strict — a `type` field is added to record the class simple name, and keys must be in the same order as the class fields.

## Slang Scala-based Syntax

### Imports
- `import org.sireum._` must be the first import in any Slang file.
- `u32"..."` interpolator requires `import org.sireum.U32._`. Same pattern for other fixed-width types (e.g., `U8._`, `U16._`, `U64._`).

### Generated Slang Code
- Generated Slang files MUST start with `// #Sireum` as the very first line. Without it, `@range`, `@record`, `@datatype`, `@pure`, and string interpolators like `state"..."` are not processed.
- `@range` class string interpolators (e.g., `state"0"`) require `import State._` to be in scope. Code inside the companion object where `State` is defined does NOT automatically have the interpolator — use `State(0)` constructor instead.
- `ops.COps` has `escapeString` (returns `String`); `ops.StringOps` has `escapeST` (returns `ST`). Don't confuse them.
- JVM limits string constants to 65535 UTF-8 bytes. When generating large string literals (e.g., base64-encoded `NGrammar.toCompact`), split into chunks stored as separate `val`s and concatenate at runtime via `st"$s0$s1".render`.
- CRITICAL: Slang `String` does NOT support `+` for concatenation in `// #Sireum` files. Use `st"$s1$s2".render` instead.
- Scala/Slang semicolon inference gotcha: `None()` followed by `{` on the next line is parsed as `None(){...}`. Insert an empty line (double newline) before bare `{` blocks to force semicolon inference. This applies to both `// #Sireum` files and plain Scala files.
- Pattern matching on `Option` of tuples: `case Some(pair) =>` on `Option[(A, B)]` triggers a Scala deprecation warning ("crushing into 2-tuple") which fails under `-Werror`. Always destructure explicitly: `case Some((a, b)) =>`.

### Immutability Integrity
- CRITICAL: Never make immutable types (`@sig`, `@datatype`) secretly mutable through `@ext` methods. If a type is `@sig`, its state must be truly immutable after construction. Use factory methods that accept the full initial state (e.g., `create(a: IS[S32, B]): BitSet`), not `create()` + `set()`.

### @ext Implementation Classes
- The scalac plugin generates `hashCode`, `equals`, and `string` for `@datatype`/`@sig` types declared in Slang. When implementing a Slang type via `@ext` in plain Scala (e.g., `BitSet_Ext.Impl`), you must manually implement `hashCode: scala.Int`, `equals(other: Any): scala.Boolean` (delegate to `===`), and `string: String` (human-readable representation). Without these, `@datatype` class equality comparisons on fields of the ext type will fail.

### @ext Objects
- `@ext("Foo_Ext") object Ext { def bar(x: String): Z = $ }` maps to `object Foo_Ext { def bar(...): Z = ... }` in a plain Scala file (same package). The argument is the exact implementation class name.
- `@ext object Foo { ... }` (no argument) maps to `object Foo_Ext { ... }` — appends `_Ext` to the object name.

### Plain Scala Files with `import org.sireum._`
- `import org.sireum._` shadows `java.lang.String` with `org.sireum.String`. Use `.value` to convert to `java.lang.String` for Java API calls. Use `Predef.String` for explicit `java.lang.String` type positions (e.g., `HashMap[Predef.String, Predef.String]`).
- `null` should not be assigned to Slang type. For strings, use `Predef.String` for Java collections that need nulls.
- Wrap `java.lang.String` back to `org.sireum.String` via `String(javaString)` for return values.
- Tuples work across Slang/Scala boundaries (e.g., `(Z, String, String)` return type).
- **Direct IS/MS construction from raw arrays**: In plain Scala files, avoid `ISZ[V](arr.toIndexedSeq: _*)` (copies through varargs). Instead, construct directly from the raw backing array:
  ```scala
  // IS (immutable): new IS[I, V](companion, rawArray, length, boxer)
  val rawArr = new Array[scala.Int](n)  // Int for U32/S32, Long for U64/S64, etc.
  // ... fill rawArr ...
  val is = new IS[Z, U32](Z, rawArr, Z.MP(n), U32.fromZ(0).boxer)

  // MS (mutable): new MS[I, V](companion, rawArray, length, boxer)
  val ms = new MS[Z, U32](Z, rawArr, Z.MP(n), U32.fromZ(0).boxer)
  ```
  - `companion`: the index type's companion object (e.g., `Z` for `ISZ`/`MSZ`)
  - `rawArray`: the JVM backing array matching the boxer's type (`Array[scala.Int]` for `BV.Int` types like U32/S32, `Array[scala.Long]` for U64/S64, `Array[AnyRef]` for reference types)
  - `length`: `Z.MP(n)` (wraps a Long as Z)
  - `boxer`: for primitive-backed types (U32, S32, etc.), obtained from any instance via `HasBoxer.boxer` (e.g., `U32.fromZ(0).boxer`). Boxer source is only in `.class` form for generated types like U32. For object/reference types (`@datatype`, `@sig`, traits, etc.), use `$internal.IdentityBoxer` with `Array[AnyRef]`.

## Prototyping with Slash Scripts
- Before writing complex Slang logic, prototype it in a Slash script first
- Slash = Sireum universal shell script with Slang scripting capability
- Example reference: `../slang-by-examples/bin/slash.cmd`
- Create a temp script (e.g., `bin/test-patch.cmd`), test the algorithm independently, then port to the target Slang source
- Make the script executable on macOS/Linux (`chmod +x`) so it can be run from any shell cross-platform, including Windows
- Catches bugs early before they require a full rebuild cycle
- Delete the prototype script after porting

## Build, Type Check, Compile, and Test

### Verification Strategy
- **Small changes**: run `sireum_proyek_tipe` (Slang type checker, fastest).
- **Large changes**: run `sireum_proyek_compile` (full Scala compilation).
- **Updated `sireum.jar` needed**: run `bin/build.cmd` (compiles + assembles jar).

### Tools
- IMPORTANT: Unless the Sireum MCP server is unavailable, always use the Sireum MCP tools (`sireum_proyek_tipe`, `sireum_proyek_compile`, `sireum_proyek_test`, etc.) instead of invoking CLI commands via Bash. If the Task agent API fails three times in a session, switch to using direct tool calls for the remainder of the session instead of Task agents.
- Console output goes to two files in the project root: `.claude.out` (MCP tools) and `.claude.shell.out` (shell commands). Both truncate per invocation. Occasionally remind the user: run `tail -f .claude.out .claude.shell.out` in a separate terminal (or prompt `sireum open terminal`) to see live output.
- IMPORTANT: When running shell commands, always tee output to `.claude.shell.out` so the user can follow along in their terminal while output also remains visible in the Claude Code UI. Use `tee` (not `>`/`>>`) to truncate per command and write to both stdout and file. On Unix: `command 2>&1 | tee <project-root>/.claude.shell.out`. On Windows: `command 2>&1 | Tee-Object -FilePath <project-root>\.claude.shell.out`.
- **`sireum_proyek_tipe`**: Slang type checker. Only checks `// #Sireum` files — plain Scala files are not checked. Use `sireum_proyek_compile` to verify non-Slang Scala files compile.
- **`sireum_proyek_compile`**: Full Scala compilation (Slang and non-Slang files).
- **`sireum_proyek_test`**: Test runner. Provide `classes` option with comma-separated FQCNs. Optionally supply `coverage` with `<path>/<name>` where `<path>` is a temporary folder for JaCoCo coverage output and `<name>` is a session name.
- **`bin/build.cmd`**: Full build — compiles all modules and assembles `sireum.jar`. Pass `native` argument to also build the native executable (e.g., `bin/build.cmd native`). The `native` build also copies the executable and jar to the appropriate `bin/` locations — no manual copying needed. Has named task IDs for parser regen: `regen-json`, `regen-slang-ll2`, `regen-slang-tt`, `regen-parser` (slang mode), `regen-parser-antlr3`. Use these instead of manual `sireum parser gen` commands.
- **Bootstrap recovery (last resort)**: If `bin/sireum.jar` is broken/corrupted, delete it and run `bin/init.sh` — the script downloads the last dev release from GitHub. Before modifying proyek or anything that affects `sireum.jar` build, **always back up the known-good `bin/sireum.jar`** to the session's `tmp/` subdirectory first (e.g., `cp bin/sireum.jar tmp/<session-dir>/sireum.jar.bak`).

### Environment Variables
- Set `SIREUM_NATIVE=false` to skip the native binary and run via `java -jar sireum.jar` instead. Supported in `bin/sireum`, `bin/sireum.bat`, and `bin/sireum-mcp.bat`.

### Output Directory
- `proyek` stores compiled artifacts under `<dir>/out/<name>/`, where `<name>` defaults to the directory name of `<dir>` (e.g., `out/kekinian/` when `<dir>` is the kekinian project root).
- Supply the `name` option (e.g., a session ID) to control the subdirectory name so you know exactly where compiled classes will be located (e.g., `out/<session-id>/modules/library-shared/classes/`).
- `bin/build.cmd` uses `name=sireum-proyek` → artifacts go to `out/sireum-proyek/`.
- When testing bytecode optimizers (BitsOptimizer, ZUnboxer) on compiled classes, make sure to look in the correct `out/<name>/` subdirectory.

### Common Options
- IMPORTANT: Always supply `slice` with the relevant module ID for `sireum_proyek_tipe`, `sireum_proyek_compile`, and `sireum_proyek_test` to avoid unnecessary full-project processing and for quicker turnaround.
- IMPORTANT: Do NOT supply the `recompile` option (comma-separated module IDs) for `sireum_proyek_compile` or `sireum_proyek_test` unless the user explicitly asks for it.
- All three tools take the project root directory as the positional argument.

### Finding Module IDs
- Read the `project.json` in an ancestor folder of the source file and match the file path to a module's `basePath`/`subPathOpt`.
- Example: `DfaTest` in `runtime/library/shared/src/test/` → module `library-shared` (from `runtime/project.json`).

### Test Framework
- Test files are plain Scala (not `// #Sireum`), using `TestSuite` from `org.sireum.test._`.
- Pattern: `val tests = Tests { * - { ... } }`.
- In JVM runtime, `ISZ` extends Scala's `IndexedSeq`, so `.size` returns Scala `Int`. Use plain `Int` literals (e.g., `== 1`) for size comparisons in test code, NOT `Z(1)` — `Int == Z` is always `false`.
- CRITICAL: Comparing `org.sireum.String` fields (e.g., `.text`, `.ruleName`) with Scala string literals via `==` fails under `-Werror` ("unrelated types"). Wrap literals with `String("...")` (e.g., `assert(token.text == String("hello"))`).

### Bytecode Optimizers (BitsOptimizer, ZUnboxer)
- `BitsOptimizer.java` and `ZUnboxer.java` live in `runtime/library/jvm/src/main/java/org/sireum/lang/optimizer/`.
- They run during `proyek compile` (called from `Compile_Ext.scala`'s `scalac` method after successful scalac). So `proyek test` also benefits.
- **Quick testing without full rebuild**: Compile the optimizer `.java` file from source with ASM jars + `sireum.jar`, then run on target `.class` files via a test driver. No `bin/build.cmd` needed for iterating on optimizer changes.
  ```bash
  CACHE=lib/cache/https/repo1.maven.org/maven2/org/ow2/asm
  ASM_CP="$CACHE/asm/9.9.1/asm-9.9.1.jar:$CACHE/asm-tree/9.9.1/asm-tree-9.9.1.jar:$CACHE/asm-util/9.9.1/asm-util-9.9.1.jar:$CACHE/asm-analysis/9.9.1/asm-analysis-9.9.1.jar"
  javac -cp "$ASM_CP:bin/sireum.jar" -d tmp/<session>/out <optimizer>.java TestDriver.java
  java -cp "tmp/<session>/out:$ASM_CP:bin/sireum.jar" TestDriver <class-files>
  ```
- The test driver calls `new BitsOptimizer(true).transformClassBytes(original)` (or `ZUnboxer`) + `CheckClassAdapter.verify` to validate the transformation.
- CRITICAL: Put `tmp/<session>/out` FIRST in classpath before `bin/sireum.jar` — otherwise the old optimizer from the jar shadows the freshly compiled one.
- CRITICAL: After rebuilding `sireum.jar`, the `proyek compile` JVM still uses the old jar. Must recompile target modules with `--recompile <module>` using `SIREUM_NATIVE=false bin/sireum` CLI to pick up the new optimizer.

### Temporary Directory Convention
All temporary output (profiling, coverage, analysis scripts, etc.) goes under `<project-root>/tmp/`. Each session gets its own subdirectory named after its session ID (the UUID from the conversation, e.g., `c81295c9-f27e-4804-ad41-41b3d04f59f8`). Only directories should exist immediately under `tmp/` — no loose files.

Before creating a new subdirectory, prune old ones to keep at most 42 directories:
```bash
# Prune oldest directories beyond the limit of 42
cd <project-root>/tmp && ls -1dt */ | tail -n +43 | xargs rm -rf
```

When referencing `<project-root>/tmp/` in profiling or coverage commands, always use the session-specific subdirectory path (e.g., `<project-root>/tmp/<session-id>/`).

## JVM Profiling with async-profiler

### Setup
async-profiler v4.3 is installed per-platform under `bin/`:
- macOS: `bin/mac/async-profiler/lib/libasyncProfiler.dylib`
- Linux x64: `bin/linux/async-profiler/lib/libasyncProfiler.so` (if installed)
- Linux arm64: `bin/linux/arm/async-profiler/lib/libasyncProfiler.so` (if installed)

If the library is missing for the current platform, download from [async-profiler releases](https://github.com/async-profiler/async-profiler/releases):
- macOS: `async-profiler-<ver>-macos.zip` → extract to `bin/mac/async-profiler/`
- Linux x64: `async-profiler-<ver>-linux-x64.tar.gz` → extract to `bin/linux/async-profiler/`
- Linux arm64: `async-profiler-<ver>-linux-arm64.tar.gz` → extract to `bin/linux/arm/async-profiler/`

No Windows build exists (async-profiler is Linux/macOS only).

### Running Allocation Profiling
Use `JAVA_TOOL_OPTIONS` to propagate the agent to all spawned JVMs (`proyek test` spawns separate test runner JVMs):

```bash
# Determine library path based on OS
# macOS: bin/mac/async-profiler/lib/libasyncProfiler.dylib
# Linux: bin/linux/async-profiler/lib/libasyncProfiler.so (or bin/linux/arm/...)

SIREUM_NATIVE=false \
JAVA_TOOL_OPTIONS="-agentpath:<lib-path>=start,event=alloc,file=<session-dir>/alloc-ap-%p.txt,collapsed,jstackdepth=128" \
bin/sireum proyek test --slice <module> --classes <test.class> <project-dir>
```

CRITICAL: The `-agentpath:` value must be an **absolute path** to the library. Relative paths fail because spawned JVMs have different working directories.

Key options: `event=alloc` (allocation profiling), `collapsed` (one-line-per-stack text format), `jstackdepth=128` (deep stacks), `%p` (per-JVM PID in filename), `SIREUM_NATIVE=false` (forces JVM mode).

For CPU profiling, use `event=cpu` instead of `event=alloc`.

### Identifying the Right Output File
`proyek test` spawns ~4 JVMs. The test runner produces the **largest** file: `ls -lhS <session-dir>/alloc-ap-*.txt`.

### Analyzing Collapsed Stack Output
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

### Why Not JFR
GraalVM JVMCI JDK (which Sireum uses) caps JFR allocation sample stack traces at 5 frames regardless of `-XX:FlightRecorderOptions=stackdepth`. async-profiler's JFR output format (`FLR` header) is also incompatible with the JDK `jfr` tool. Always use async-profiler with `collapsed` text format.

## Module-Specific Documentation
- `runtime/CLAUDE.md` — Parse tree structure (`ParseTree.Leaf`, `ParseTree.Node`, synthetic rules, `NGrammar.parse/parseRec`)
- `parser/CLAUDE.md` — LL(k) parser generator (`LLkParserGenerator`, DFA construction, generated parser structure)
- `slang/CLAUDE.md` — Slang language construct mapping (Scala-based vs LL(2) syntax, AST node types)
- `vscode-extension/CLAUDE.md` — VSCode/CodeIVE extension (build, deploy, Sensmetry patching, SysML syntax highlighting)
