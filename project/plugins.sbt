//resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

//addSbtPlugin("play" % "sbt-plugin" % "2.1")

resolvers += Resolver.url("Typesafe ivy releases", url("http://repo.typesafe.com/typesafe/ivy-releases"))(Resolver.ivyStylePatterns)

libraryDependencies <+= (sbtVersion) { sv =>
  "org.scala-sbt" % "scripted-plugin" % sv
}
