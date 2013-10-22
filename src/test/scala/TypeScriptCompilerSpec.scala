import org.scalatest.FunSpec
import java.io.File
import com.github.mumoshu.play2.typescript.TypeScriptCompiler

class TypeScriptCompilerSpec extends FunSpec {
  describe("TypeScriptCompiler") {
    it("should compile ts file") {
      val tsFile = new File("src/test/resources/ok.ts")
      val (full, minified, deps) = TypeScriptCompiler.compile(tsFile, Nil)
      assert(full ===
        """function greeter(person) {
    return "Hello, " + person;
}

var user = "Jane User";

document.body.innerHTML = greeter(user);
""")
      assert(minified.isEmpty)
      assert(deps.length === 1)
      assert(deps(0).getName() === "ok.ts")
    }

    it("should compile ts file with compiler with the `--module amd` compiler option") {
      val tsFile = new File("src/test/resources/amd.ts")
      val (full, minified, deps) = TypeScriptCompiler.compile(tsFile, Seq("--module", "amd"))
      assert(full ===
        """define(["require", "exports", "./lib"], function(require, exports, __lib__) {
          |    /// <reference path="lib.ts">
          |    var lib = __lib__;
          |
          |    var greeter = new lib.Greeter();
          |
          |    document.body.innerHTML = greeter.greet("mikoto");
          |});
          |""".stripMargin)
      assert(minified.isEmpty)
      assert(deps.length === 2)
      assert(deps(0).getName() === "amd.ts")
      assert(deps(1).getName() === "lib.ts")
    }

    it("should compile ts file with the `--sourcemap` compiler option") {
      val tsFile = new File("src/test/resources/amd.ts")
      val (full, minified, deps) = TypeScriptCompiler.compile(tsFile, Seq("--module", "amd", "--sourcemap"))
      assert(full ===
        """define(["require", "exports", "./lib"], function(require, exports, __lib__) {
          |    /// <reference path="lib.ts">
          |    var lib = __lib__;
          |
          |    var greeter = new lib.Greeter();
          |
          |    document.body.innerHTML = greeter.greet("mikoto");
          |});
          |//# sourceMappingURL=amd.js.map
          |""".stripMargin)
      assert(minified.isEmpty)
      assert(deps.length === 2)
      assert(deps(0).getName() === "amd.ts")
      assert(deps(1).getName() === "lib.ts")
    }

    it("should compile .ts files with two non-referenced but dependent modules") {
      val tsFile = new File("src/test/resources/main.ts")
      val (full, minified, deps) = TypeScriptCompiler.compile(tsFile, Seq("--module", "amd", "--sourcemap"))
      assert(full ===
        """define(["require", "exports", "jquery", "underscore"], function(require, exports, __jquery__, __underscore__) {
          |    var jquery = __jquery__;
          |    var underscore = __underscore__;
          |
          |    (function (A) {
          |        console.log(jquery.$ + underscore._);
          |    })(exports.A || (exports.A = {}));
          |    var A = exports.A;
          |});
          |//# sourceMappingURL=main.js.map
          |""".stripMargin)
      assert(minified.isEmpty)
      assert(deps.length === 1)
      assert(deps(0).getName() === "main.ts")
    }
  }
}
