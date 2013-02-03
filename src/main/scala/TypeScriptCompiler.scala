package net.litola

import sbt.PlayExceptions.AssetCompilationException
import java.io.File
import scala.sys.process._
import sbt.IO
import io.Source._
import scalax.file.Path
import scalax.io.JavaConverters._

object TypeScriptCompiler {
  def compile(tsFile: File, options: Seq[String]): (String, Option[String], Seq[File]) = {
    try {
      val parentPath = tsFile.getParentFile.getAbsolutePath
      val writeDeclarations = Path(tsFile).string.contains("module")
      val writeDeclarationsOptions = if (writeDeclarations)
        Seq("--declarations") else Seq.empty
      val cmd = if (System.getProperty("os.name").startsWith("Windows"))
        Seq("cmd", "/C", "tsc")
      else
        Seq("tsc")
      val tscOutput = runCompiler(
        cmd ++ options ++ writeDeclarationsOptions ++ Seq(tsFile.getAbsolutePath)
      )

      val outputJsPath = Path.fromString(tsFile.getAbsolutePath.replaceFirst("""\.ts$""", ".js"))
      val tsOutput = outputJsPath.string
      outputJsPath.delete()

      val declarationsFiles = if (writeDeclarations)
        List(new File(tsFile.getAbsolutePath.replace("\\.ts$", ".d.ts")))
      else Nil

      (tsOutput, None, List(tsFile) ++ declarationsFiles )
    } catch {
      case e: TypeScriptCompilationException => {
        throw AssetCompilationException(e.file.orElse(Some(tsFile)), "TypeScript compiler: " + e.message, Some(e.line), Some(e.column))
      }
    }
  }

  private val DependencyLine = """^/\* line \d+, (.*) \*/$""".r

  /**
   * Runs TypeScript compiler and returns its stdout
   * @param command
   * @return Compiler's stdout
   */
  private def runCompiler(command: ProcessBuilder): String = {
    val err = new StringBuilder
    val out = new StringBuilder

    val capturer = ProcessLogger(
      (output: String) => out.append(output + "\n"),
      (error: String) => err.append(error + "\n"))

    val process = command.run(capturer)
    if (process.exitValue == 0) {
      out.mkString
    } else
      throw new TypeScriptCompilationException(err.toString)
  }

  private val LocationLine = """\s*on line (\d+) of (.*)""".r

  private class TypeScriptCompilationException(stderr: String) extends RuntimeException {

    val (file: Option[File], line: Int, column: Int, message: String) = parseError(stderr)

    private def parseError(error: String): (Option[File], Int, Int, String) = {
      var line = 0
      var seen = 0
      var column = 0
      var file : Option[File] = None
      var message = "Unknown error, try running tsc directly"
      for (errline: String <- augmentString(error).lines) {
        errline match {
          case LocationLine(l, f) => { line = l.toInt; file = Some(new File(f)); }
          case other if (seen == 0) => { message = other; seen += 1 }
          case other =>
        }
      }
      (file, line, column, message)
    }
  }
}
