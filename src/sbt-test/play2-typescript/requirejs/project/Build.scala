import sbt._
import Keys._
import PlayProject._
import net.litola.TypeScriptPlugin._

object ApplicationBuild extends Build {

  val appName         = "sample"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    TaskKey[Unit]("check") <<= (target) map { (target) =>
//      val process = sbt.Process("java", Seq("-jar", (target / "foo.jar").toString))
//      val out = (process!!)
//      if (out.trim != "bye") error("unexpected output: " + out)
      println("TARGET:" + target)
      ()
    },
    tsOptions ++= Seq("--module", "amd")
  )

}
