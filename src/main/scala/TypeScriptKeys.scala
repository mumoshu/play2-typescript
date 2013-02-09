package com.github.mumoshu.play2.typescript

import java.io.File
import sbt.{PathFinder, SettingKey, TaskKey}

trait TypeScriptKeys {
  val buildTypeScript = TaskKey[Seq[(File, File)]]("play-typescript-build-assets")
  val tsEntryPoints = SettingKey[PathFinder]("play-typescript-entry-points")
  val tsOptions = SettingKey[Seq[String]]("play-typescript-options")
  val tsDirectory = SettingKey[String]("play-typescript-directory")
}

object TypeScriptKeys extends TypeScriptKeys
