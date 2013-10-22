package com.github.mumoshu.play2.typescript

import sbt._
import sbt.Keys._

object TypeScriptPlugin extends Plugin with TypeScriptSettings {
    val typescript = defaultSettings
}

// vim: set ts=4 sw=4 et:
