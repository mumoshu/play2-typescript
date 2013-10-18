import sbt._
import sbt.Keys._
import Defaults._

object PluginBuild extends Build {

  lazy val play2TypeScript = Project(
    id = "play2-typescript", base = file(".")
  ).settings(
    sbtPlugin := true,
    scalaVersion := "2.10.2",
    description := "SBT plugin for handling TypeScript assets in Play 2",
    resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "1.9.1" % "test"
    ),
    resolvers += Resolver.url("Typesafe ivy releases", url("http://repo.typesafe.com/typesafe/ivy-releases"))(Resolver.ivyStylePatterns),
    addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.0" % "provided"),
    organization := "com.github.mumoshu",
    version := "0.2-RC11",
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
  ).settings(ScriptedPlugin.scriptedSettings:_*).settings(
    ScriptedPlugin.scriptedLaunchOpts ++= Seq("-XX:+CMSClassUnloadingEnabled", "-XX:MaxPermSize=256m", "-Xmx512M", "-Xss2M")
  )

}
