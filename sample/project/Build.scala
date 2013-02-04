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

    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here
      javascriptEntryPoints <<= baseDirectory(base =>
        base / "app" / "assets" ** "*.js"
      )
    )

}
