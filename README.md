play2-typescript
===========

[TypeScript] [1] asset handling for [Play 2.1.0] [2], implemented as an [sbt] [3]
plugin (very similar to Play's handling of CoffeeScript and LESS).

Prerequisites
-------------

The plugin assumes 0.8.2.0 of TypeScript compiler executable - `tsc` - to be available in your PATH.

Recommended way to install it in your system is, with node.js and npm installed, run:

    npm install -g typescript

You really should put `-g` option, thereby installing not only the module, but also the executable.

If you already have an installation of `tsc` but have forgotten its version, run:

    tsc --version

To update your `tsc` to the latest version, run:

    npm update -g typescript

Installation
------------

In your Play application folder, add

    addSbtPlugin("com.github.mumoshu" % "play2-typescript" % "0.2-RC4")

to `project/plugins.sbt`.

The plugin automatically registers for compilation of `app/assets/**/*.ts`, that is all typescript files in your `app/assets` directory.

Integrated with RequireJS by default
------------------------------------

TypeScript modules requires CommonJS module support on runtime.

Fortunately, play2-typescript is by default integrated with Play's RequireJS support.
It means that every dependency between output JavaScript files is managed by RequireJS, both in DEV and PROD mode.
In DEV mode, JavaScript files required by the *main* source are dynamically and asynchronously downloaded by RequireJS,
while in PROD mode they are pre-compiled altogether that the *main* source is concatenated with its depending files
and minified.

sbt settings
------------

  - `compile:resource-generators`: The typescript file watcher is being added here
  - `play-typescript-entry-points`: All files matching `app/assets/**/*.ts`, except files starting in an underscore
  - `play-typescript-options`: A sequence of strings passed to typescript as command-line flags

TODO sourcemaps support
-----------------------

It's a pain to debug your actual TypeScript code through the glass: JavaScript sources presented by the compiler.

We really should always serve sourcemaps for our TypeScript sources in DEV mode.
The goal is that we could just see the original TypeScript source for each served JavaScript source,
using Google Chrome, opening 'Sources' tab in Developer Tools.

Acknowledgements
----------------

This plugin is based on Juha Litola's [play-sass][play-sass] plugin for handling Sass assets.

License
-------

Copyright (c) 2013 KUOKA Yusuke

Apache v2 licensing, for details see file LICENSE.

[1]: http://www.typescriptlang.org/
[2]: http://www.playframework.org/
[3]: https://github.com/harrah/xsbt
