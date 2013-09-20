package com.github.mumoshu.play2.typescript

import sbt._
import sbt.Keys._
import play.Project.AssetsCompiler

trait TypeScriptSettings extends TypeScriptKeys with TypeScriptCommands {

  val typeScriptCompiler = AssetsCompiler(
  "typescript",
  { file => (file ** "*.ts") },
  tsEntryPoints,
  { (name, min) =>
    name.replace(".ts", ".js")
  },
  { (tsFile, options) =>
    TypeScriptCompiler.compile(tsFile, options)
  },
  tsOptions
  )

  lazy val defaultSettings: Seq[Setting[_]] = List(
    buildTypeScript <<= buildTypeScriptTask,
    packageBin in Compile <<= (packageBin in Compile).dependsOn(buildTypeScript),
    tsDirectory := "javascripts",
    tsEntryPoints <<= (sourceDirectory in Compile, tsDirectory)( (base, tsDir) => ((base / "assets" / tsDir ** "*.ts") --- (base / "assets" ** "_*") --- (base / "assets" ** "*.d.ts"))),
    tsOptions := Seq("--module", "amd"),
    resourceGenerators in Compile <+= typeScriptCompiler
  )
}

object TypeScriptSettings extends TypeScriptSettings
