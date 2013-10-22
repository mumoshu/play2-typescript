import sbt._
import Keys._
import play.Project._
import com.github.mumoshu.play2.typescript.TypeScriptPlugin._

object ApplicationBuild extends Build {

  val appName         = "sample"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
  )

  val main = play.Project(appName, appVersion, appDependencies)
    .settings(
      typescriptSettings: _*
    )
    .settings(
      libraryDependencies += "org.specs2" %% "specs2" % "1.13" % "test",
      sourceDirectory in Test <<= baseDirectory / "src/test",
      scalaSource in Test <<= baseDirectory / "src/test/scala",
      requireJs := Seq( "main.js" )
    )

}
