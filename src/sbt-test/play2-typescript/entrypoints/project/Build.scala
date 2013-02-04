import sbt._
import Keys._
import PlayProject._
import com.github.mumoshu.play2.typescript.TypeScriptPlugin._

object ApplicationBuild extends Build {

  val appName         = "sample"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
  )

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
    TaskKey[Unit]("check") <<= (target) map { (target) =>
//      val process = sbt.Process("java", Seq("-jar", (target / "foo.jar").toString))
//      val out = (process!!)
//      if (out.trim != "bye") error("unexpected output: " + out)
      println("TARGET:" + target)
      ()
    },
    // Add your own project settings here
    javascriptEntryPoints <<= baseDirectory(base =>
      base / "app" / "assets" ** "*.js"
    )//,
//    tsEntryPoints <<= (sourceDirectory in Compile)(base =>
//      base / "typescripts" ** "*.ts"
//    )
  )

}
