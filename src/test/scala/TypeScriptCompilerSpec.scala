import org.scalatest.FunSpec
import java.io.File
import net.litola.TypeScriptCompiler

class TypeScriptCompilerSpec extends FunSpec {
  describe("TypeScriptCompiler") {
    it("should compile ts file") {
      val tsFile = new File("src/test/resources/ok.ts")
      val (full, minified, deps) = TypeScriptCompiler.compile(tsFile, Nil)
      assert(full ===
        """function greeter(person: string) {
    return "Hello, " + person;
}

var user = "Jane User";

document.body.innerHTML = greeter(user);
""".stripMargin)
      assert(minified.isEmpty)
      assert(deps.length === 1)
      assert(deps(0).getName() === "ok.ts")
    }
  }
}
