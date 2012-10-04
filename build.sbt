name := "play2-typescript"

version := "0.1.0-SNAPSHOT"

sbtPlugin := true

organization := "com.github.mumoshu"

description := "SBT plugin for handling TypeScript assets in Play 2"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

/// Dependencies

libraryDependencies ++= Seq(
  "play" %% "play" % "2.0.3",
  "org.scalatest" %% "scalatest" % "1.7.1" % "test"
)

addSbtPlugin("play" % "sbt-plugin" % "2.0.3")
