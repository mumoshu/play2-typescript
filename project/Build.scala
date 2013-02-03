import sbt._
import sbt.Keys._

object PluginBuild extends Build {

  lazy val play2TypeScript = Project(
    id = "play2-typescript", base = file(".")
  ).settings(
    sbtPlugin := true,
    scalaVersion := "2.9.1",
    description := "SBT plugin for handling TypeScript assets in Play 2",
    resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    libraryDependencies ++= Seq(
      //"play" %% "play" % "2.0.3",
      "org.scalatest" %% "scalatest" % "1.7.1" % "test"
    ),
    addSbtPlugin("play" % "sbt-plugin" % "2.0.3"),
    organization := "com.github.mumoshu",
    version := "0.1.3-SNAPSHOT",
    publishTo <<= version { v: String =>
      val nexus = "https://oss.sonatype.org/"
      if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
      else                             Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    publishMavenStyle := true,
    publishArtifact in Test := false,
    pomIncludeRepository := { _ => false },
    pomExtra := (
      <url>https://github.com/mumoshu/play2-typescript</url>
        <licenses>
          <license>
            <name>Apache v2 License</name>
            <url>https://github.com/mumoshu/play2-typescript/blob/master/LICENSE</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:mumoshu/play2-typescript.git</url>
          <connection>scm:git:git@github.com:mumoshu/play2-typescript.git</connection>
        </scm>
        <developers>
          <developer>
            <id>you</id>
            <name>KUOKA Yusuke</name>
            <url>https://github.com/mumoshu</url>
          </developer>
        </developers>
      )
  )

}
