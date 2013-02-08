import org.scalatest.FunSpec
import java.io.File
import net.litola.TypeScriptCompiler

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

    it("should compile ts file with compiler options") {
      val tsFile = new File("src/test/resources/amd.ts")
      val (full, minified, deps) = TypeScriptCompiler.compile(tsFile, Seq("--module", "amd"))
      assert(full ===
        """define(["require", "exports", "./lib"], function(require, exports, __lib__) {
          |    var lib = __lib__;
          |
          |    var greeter = new lib.Greeter();
          |    document.body.innerHTML = greeter.greet("mikoto");
          |})
          |""".stripMargin)
      assert(minified.isEmpty)
      assert(deps.length === 2)
      assert(deps(1).getName() === "amd.ts")
      assert(deps(1).getName() === "amd.ts.d")

    }
  }
}
