package com.github.mumoshu.play2.typescript

import java.io.{File => JFile}
import sbt._
import sbt.Keys._
import sbt.PlayKeys._
import sbt.IO

trait TypeScriptCommands {
  self: TypeScriptKeys =>

  // We dont' use Play's built-in RequireJs compiler here, but use our own one.
  // Under app/assets/, we do have .ts files but dont't have JavaScript files which will be optimzed by Play's RequireJs compiler.
  // Thus, here, we call RequireJs compiler to optimze js sources genenrated by TypeScript compiler.
  val buildTypeScriptTask = (copyResources in Compile, tsEntryPoints, tsDirectory, crossTarget, requireJs, requireJsFolder, requireJsShim, requireNativePath, streams) map { (cr, tsEntryPoints, tsDirectory, crossTarget, requireJs2, requireJsFolder, requireJsShim, requireNativePath,  s) =>
    s.log.info("Optimizing generated JavaScript sources...")
    val buildDescName = "app.build.js"
//    val jsFolder = if(!tsDirectory.isEmpty) {tsDirectory} else "javascripts"
    val  jsFolder = tsDirectory
    val rjoldDir = crossTarget / "classes" / "public" / jsFolder
    val buildDesc = crossTarget / "classes" / "public" / buildDescName
    val requireJs = (rjoldDir ** "*.js").getPaths.map(_.replaceAll("""\.ts$""", ".js"))
    s.log.info("requireJs modules: " + requireJs)
    s.log.info("rjoldDir: " + rjoldDir.getAbsolutePath)
    if (requireJs.isEmpty == false) {
      val rjnewDir = new JFile(rjoldDir.getAbsolutePath + "-min")
      //cleanup previous version
      IO.delete(rjnewDir)
      val relativeModulePath = (str: String) => str.replaceAll(rjoldDir.getAbsolutePath + "/", "").replace(".js", "")
//      val shim = if (!requireJsShim.isEmpty) {"""mainConfigFile: """" + jsFolder + """/""" + requireJsShim + """", """} else {""};
      val shim = ""
      val content =  """({appDir: """" + jsFolder + """",
          baseUrl: ".",
          dir:"""" + rjnewDir.getName + """", """ +
        shim +
        """modules: [""" + requireJs.map(f => "{name: \"" + relativeModulePath(f) + "\"}").mkString(",") + """]})""".stripMargin

      IO.write(buildDesc,content)
      //run requireJS
      s.log.info("RequireJS optimization has begun...")
      s.log.info(buildDescName+":")
      s.log.info(content)
      try {
        requireNativePath.map(nativePath =>
          println(play.core.jscompile.JavascriptCompiler.executeNativeCompiler(nativePath + " -o " + buildDesc.getAbsolutePath, buildDesc))
        ).getOrElse {
          play.core.jscompile.JavascriptCompiler.require(buildDesc)
        }
        s.log.info("RequireJS optimization finished.")
      } catch {case ex: Exception =>
        s.log.error("RequireJS optimization has failed...")
        throw ex
      }
      //clean-up
      IO.delete(buildDesc)
    }
    cr
  }
}
