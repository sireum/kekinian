# Kekinian Project

**Note:** This file is shared across contributors. Do not include machine-specific full paths (e.g., `/Users/someone/...`). Use relative paths or generic descriptions instead.

## Coding Conventions
- When modifying a method that has documentation (Scaladoc, comments, etc.), always update the documentation to reflect the code change in the same edit.
- When generating code with method/constructor calls that have more than 3 arguments, use named arguments for readability (e.g., `Rule(name = n, isLexer = F, isFragment = F, isHidden = F, isSynthetic = T, posOpt = pOpt, alts = alts)`).

## Slang Language

### Imports
- `import org.sireum._` must be the first import in any Slang file.
- `u32"..."` interpolator requires `import org.sireum.U32._`. Same pattern for other fixed-width types (e.g., `U8._`, `U16._`, `U64._`).

### Core Rules
- CRITICAL: Non-`@strictpure` methods (including nested `def`s) must use explicit `return` keyword for every return path. Slang does not support implicit returns from match expressions or other blocks. Every branch in a `match` must have `return`.
- `@strictpure` methods cannot have `return`, loops (`while`/`for` without `yield`), `var`, assignments other than `val` initialization, or nested `def`s. They must be single expressions. `for-yield` is allowed.
- When writing Slang code, prioritize `@strictpure` methods over regular methods, except when loops or mutable variables are needed.
- `@strictpure` annotation must be explicitly specified on every strictpure method, including nested `def`s inside other methods.
- `for-yield` cannot have code blocks `{ ... }`. The yield body must be a single expression. Inline variable declarations directly into the expression (e.g., use `for (e <- xs) yield st"${f(e)}"` instead of `for (e <- xs) yield { val v = f(e); st"$v" }`).
- `@memoize` methods cannot have empty parameter lists `()`. Use parameterless def syntax instead.
- Function parameters expecting `@pure` callees need `@pure` on the function type (e.g., `((E, Z), (E, Z)) => B @pure`). Regular Scala lambdas satisfy this from non-Slang code.

### Types and Collections
- `@datatype` = immutable, `@record` = mutable.
- Collections: `ISZ` (immutable seq), `MSZ (mutable seq)`, `HashSMap` (sorted hash map), `HashSSet` (sorted hash set).
- Unsigned integer types (`U8`, `U16`, `U32`, `U64`) print as hex by default in string interpolation.
- `Z` and `@range` class types do not support bitwise operations (`&`, `|`, `<<`, `>>`, `>>>`, `~`). Use unsigned integer types (`U8`, `U16`, `U32`, `U64`) for bitwise ops, converting via `conversions.Z.toU32`/`conversions.U32.toZ` etc.
- Use `~>` for map entry creation with `+` to add to maps.

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

### @ext Objects
- `@ext("Foo_Ext") object Ext { def bar(x: String): Z = $ }` maps to `object Foo_Ext { def bar(...): Z = ... }` in a plain Scala file (same package). The argument is the exact implementation class name.
- `@ext object Foo { ... }` (no argument) maps to `object Foo_Ext { ... }` — appends `_Ext` to the object name.

### Plain Scala Files with `import org.sireum._`
- `import org.sireum._` shadows `java.lang.String` with `org.sireum.String`. Use `.value` to convert to `java.lang.String` for Java API calls. Use `Predef.String` for explicit `java.lang.String` type positions (e.g., `HashMap[Predef.String, Predef.String]`).
- `null` is not assignable to `org.sireum.String`. Use `Predef.String` for Java collections that need nulls.
- Wrap `java.lang.String` back to `org.sireum.String` via `String(javaString)` for return values.
- Tuples work across Slang/Scala boundaries (e.g., `(Z, String, String)` return type).

## Build, Type Check, Compile, and Test

### Verification Strategy
- **Small changes**: run `sireum_proyek_tipe` (Slang type checker, fastest).
- **Large changes**: run `sireum_proyek_compile` (full Scala compilation).
- **Updated `sireum.jar` needed**: run `bin/build.cmd` (compiles + assembles jar).

### Tools
- **`sireum_proyek_tipe`**: Slang type checker. Only checks `// #Sireum` files — plain Scala files are not checked. Use `sireum_proyek_compile` to verify non-Slang Scala files compile.
- **`sireum_proyek_compile`**: Full Scala compilation (Slang and non-Slang files).
- **`sireum_proyek_test`**: Test runner. Provide `classes` option with comma-separated FQCNs. Optionally supply `coverage` with `<path>/<name>` where `<path>` is a temporary folder for JaCoCo coverage output and `<name>` is a session name.
- **`bin/build.cmd`**: Full build — compiles all modules and assembles `sireum.jar`. Pass `native` argument to also build the native executable (e.g., `bin/build.cmd native`). The `native` build also copies the executable and jar to the appropriate `bin/` locations — no manual copying needed.

### Environment Variables
- Set `SIREUM_NATIVE=false` to skip the native binary and run via `java -jar sireum.jar` instead. Supported in `bin/sireum`, `bin/sireum.bat`, and `bin/sireum-mcp.bat`.

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

## Project Structure
- Slang LL(2) parser grammars are in `slang/parser/shared/src/main/resources/`
- ANTLR v3 grammar: `SlangLl2.g` (LL(2))
- Tree-sitter grammar: `grammar.js` (created from SlangLl2.g)
- Grammar-Kit BNF: `SlangLl2.bnf` (created from SlangLl2.g)
