package com.github.mumoshu.play2.typescript

import sbt._
import sbt.Keys._
import PlayProject._

object TypeScriptPlugin extends Plugin {
    val tsEntryPoints = SettingKey[PathFinder]("play-typescript-entry-points")
    val tsOptions = SettingKey[Seq[String]]("play-typescript-options")
    val tsWatcher = PlayProject.AssetsCompiler("typescript",
        { file => (file ** "*.ts") },
        tsEntryPoints,
        { (name, min) => 
            name.replace(".ts", ".js")
            name.replace(".ts", ".js")
        },
        { (tsFile, options) =>
          TypeScriptCompiler.compile(tsFile, options)
        },
        tsOptions
    )

    override val settings = Seq(
        tsEntryPoints <<= (sourceDirectory in Compile)(base => ((base / "assets" ** "*.ts") --- (base / "assets" ** "_*") --- (base / "assets" ** "*.d.ts"))),
        tsOptions := Seq.empty[String],
        resourceGenerators in Compile <+= tsWatcher
    )
}


// vim: set ts=4 sw=4 et:
