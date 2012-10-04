play2-typescript
===========

[TypeScript] [1] asset handling for [Play 2.0] [2], implemented as an [sbt] [3]
plugin (very similar to Play's handling of CoffeeScript and LESS).

Prerequisites
-------------

The plugin assumes the availability of the `tsc` -  the TypeScript compiler executable. With
node.js and npm installed, run

    npm install -g typescript

to install `tsc` globally, thereby installing not only the module, but also the executable.

Installation
------------

In your Play application folder, add

    resolvers += "Sonatype OSS Snapshots Repository" at "http://oss.sonatype.org/content/groups/public"

    addSbtPlugin("com.github.mumoshu" % "play2-typescript" % "0.1.0-SNAPSHOT")

to `project/plugins.sbt`.

The plugin automatically registers for compilation of `app/assets/**/*.ts`, that is all stylus files in your `app/assets` directory.

sbt settings
------------

  - `compile:resource-generators`: The typescript file watcher is being added here
  - `play-typescript-entry-points`: All files matching `app/assets/**/*.styl`, except files starting in an underscore
  - `play-typescript-options`: A sequence of strings passed to stylus as command-line flags

Acknowledgements
----------------

This plugin is based on Juha Litola's [play-sass][play-sass] plugin for handling Sass assets.

License
-------

Copyright (c) 2012 KUOKA Yusuke

Apache v2 licensing, for details see file LICENSE.

[1]: http://www.typescriptlang.org/
[2]: http://www.playframework.org/
[3]: https://github.com/harrah/xsbt
