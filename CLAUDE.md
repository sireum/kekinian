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
- **Code generation**: `@strictpure` is a language abstraction for the end-user and formal verification only. For code generators, `@strictpure` methods are emitted identically to regular methods — no special-casing needed.
- `for-yield` cannot have code blocks `{ ... }`. The yield body must be a single expression. Inline variable declarations directly into the expression (e.g., use `for (e <- xs) yield st"${f(e)}"` instead of `for (e <- xs) yield { val v = f(e); st"$v" }`).
- **`@par for-yield`**: `@par for (x <- xs) yield f(x)` is a parallel comprehension — body inlined by the compiler, no closure allocation. Prefer over `mapPar` for data-parallel loops; use `mapPar` only when the function is passed dynamically.
- **`do @par { }` structured concurrency**: fork-join block for heterogeneous parallel tasks. `@fork val`/`@fork var` declarations inside form parallel phases; implicit join before the first non-`@fork` statement. `@fork` return types may be `@record` or `@datatype` — Slang's return-clone semantics guarantee each thread receives an independent deep copy.
- `@memoize` methods cannot have empty parameter lists `()`. Use parameterless def syntax instead.
- Function parameters expecting `@pure` callees need `@pure` on the function type (e.g., `((E, Z), (E, Z)) => B @pure`). Regular Scala lambdas satisfy this from non-Slang code.
- Slang does not support nested classes. All `@datatype`, `@record`, `@sig`, and `@enum` declarations must be at the top level or inside a top-level `object`.
- Slang does NOT support method overloading. Each method name must be unique within a scope.
- Slang traits (`@datatype trait`, `@record trait`, `@sig`, `@msig`) support concrete method implementations (like Scala traits), not just abstract declarations. Use this to avoid repeating the same override in every subtype.
- **Closures**: Escaping function values (lambdas) cannot capture mutable variables (`var`). Nested `def`s can mutate outer `var`s but cannot escape. Consequence: no heap-boxing of mutable captures needed — nested `def`s use stack pointers, lambdas capture only immutable values.

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
- `@record` objects are deeply cloned on assignment to fields/locals/arrays (if previously assigned) and on return (if previously stored). Parameter passing creates an alias; a mutable object and its substructure cannot be passed simultaneously. Mutable graphs are trees; `@datatype` graphs can be DAGs.
- To avoid deep-copy on `@record` return, use destination-passing style: pass `MBox[T]` and construct directly into `MBox.value` — don't assign to a local variable first (assigning to a field also triggers deep-copy if previously assigned).
- Collections: `ISZ` (immutable seq), `MSZ (mutable seq)`, `HashSMap` (ordered hash map by insertion order), `HashSSet` (ordered hash set by insertion order).
- Unsigned integer types (`U8`, `U16`, `U32`, `U64`) print as hex by default in string interpolation.
- `Z` and `@range` class types do not support bitwise operations (`&`, `|`, `<<`, `>>`, `>>>`, `~`). Use unsigned integer types (`U8`, `U16`, `U32`, `U64`) for bitwise ops, converting via `conversions.Z.toU32`/`conversions.U32.toZ` etc.
- Use `~>` for map entry creation with `+` to add to maps.
- `MSZ[Subtype].toIS` returns `ISZ[Subtype]`, not `ISZ[Supertype]` — Scala's `=:=` evidence requires exact type match. Use `MSZ[SuperType]` directly so `.toIS` returns the expected type (e.g., `MSZ[Token]` instead of `MSZ[ParseTree.Leaf]` when `ISZ[Token]` is needed).
- `@record class MStack[T]` cannot have a method returning `ISZ[T]` because `T` could be mutable, but `ISZ` requires immutable element types. Use `@record class MIStack[@imm T]` to constrain `T` to immutable types, enabling a `toISZ` method.
- Check `runtime/library/shared/src/main/scala/org/sireum/conversions.scala` for available direct conversion methods between scalar types (e.g., `conversions.S32.toU32`, `conversions.C.toS32`) instead of always routing through Z (e.g., `conversions.Z.toU32(conversions.S32.toZ(x))`).
- `s32"..."`, `u32"..."`, etc. are compile-time constants (scalac plugin optimizes to `S32(intLiteral)` / `U32(intLiteral)`).
- `@datatype`/`@record` compile to **final** JVM classes — effectively non-virtual dispatch.
- `MStack.peek` has no parens: `stack.peek` not `stack.peek()`.
- Prefer `if (cond) halt(msg)` over `assert(cond, msg)` in hot paths — `assert` allocates a closure on every call.

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
- **Direct IS/MS construction from raw arrays**: See `runtime/CLAUDE.md`. Avoid `ISZ[V](arr.toIndexedSeq: _*)` — use `new IS[I, V](companion, rawArray, Z.MP(n), boxer)` directly.

## Prototyping with Slash Scripts
- Prototype complex Slang logic in a temp Slash script (e.g., `bin/test-patch.cmd`) before porting. Example: `../slang-by-examples/bin/slash.cmd`. Delete after porting.

## Build, Type Check, Compile, and Test

### Verification Strategy
- **Small changes**: run `sireum_proyek_tipe` (Slang type checker, fastest).
- **Large changes**: run `sireum_proyek_compile` (full Scala compilation).
- **Updated `sireum.jar` needed**: run `bin/build.cmd` (compiles + assembles jar).

### Tools
- IMPORTANT: Prefer Sireum MCP tools (`sireum_proyek_tipe`, `sireum_proyek_compile`, `sireum_proyek_test`) over CLI via Bash. If Task agent API fails 3 times, switch to direct tool calls.
- Output goes to `.claude.out` (MCP) and `.claude.shell.out` (shell). User can `tail -f` both.
- IMPORTANT: Tee shell output to `.claude.shell.out`: `command 2>&1 | tee <project-root>/.claude.shell.out`.
- **`sireum_proyek_tipe`**: Slang type checker. Only checks `// #Sireum` files — plain Scala files are not checked. Use `sireum_proyek_compile` to verify non-Slang Scala files compile.
- **`sireum_proyek_compile`**: Full Scala compilation (Slang and non-Slang files).
- **`sireum_proyek_test`**: Test runner. Provide `classes` option with comma-separated FQCNs. Optionally supply `coverage` with `<path>/<name>` where `<path>` is a temporary folder for JaCoCo coverage output and `<name>` is a session name.
- **`bin/build.cmd`**: Full build + assemble `sireum.jar`. `bin/build.cmd native` also builds native. Parser regen task IDs: `regen-json`, `regen-slang-ll2`, `regen-slang-tt`, `regen-parser`, `regen-parser-antlr3`.
- **Bootstrap recovery**: If `sireum.jar` is broken, delete it + run `bin/init.sh` (downloads from GitHub). Always back up before modifying jar-affecting code.

### Environment Variables
- Set `SIREUM_NATIVE=false` to skip the native binary and run via `java -jar sireum.jar` instead. Supported in `bin/sireum`, `bin/sireum.bat`, and `bin/sireum-mcp.bat`.

### Output Directory
- Artifacts go to `<dir>/out/<name>/` (`<name>` defaults to dir name). Supply `name` option to control. `bin/build.cmd` uses `name=sireum-proyek`.

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

### Temporary Directory Convention
- Temp output → `<project-root>/tmp/<session-id>/`. Prune to ≤42: `cd tmp && ls -1dt */ | tail -n +43 | xargs rm -rf`

## JVM Profiling

Use the `/profile-jvm` skill for async-profiler setup, allocation/CPU profiling commands, and collapsed-stack analysis scripts.

## Module-Specific Documentation
- `runtime/CLAUDE.md` — Index of runtime sub-docs (parse tree, serialization, IS/MS construction, bytecode optimizers)
- `parser/CLAUDE.md` — LL(k) parser generator (`LLkParserGenerator`, DFA construction, generated parser structure)
- `slang/CLAUDE.md` — Slang language construct mapping (Scala-based vs LL(2) syntax, AST node types); `@par for-yield` / `do @par {}` internals
- `server/CLAUDE.md` — Sireum Server protocol (Request/Response messages, `JSON.scala` vs `JacksonJSON.scala` serializers)
- `vscode-extension/CLAUDE.md` — VSCode/CodeIVE extension (build, deploy, Sensmetry patching, SysML syntax highlighting)
