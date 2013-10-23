play2-typescript
===========

[TypeScript] [1] asset handling for [Play 2.2.0] [2], implemented as an [sbt] [3]
plugin (very similar to Play's handling of CoffeeScript and LESS).

Prerequisites
-------------

The plugin uses TypeScript compiler executable - `tsc` - which should added to your PATH.

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

    resolvers += "Sonatype OSS Snapshots Repository" at "http://oss.sonatype.org/content/groups/public"

    addSbtPlugin("com.github.mumoshu" % "play2-typescript" % "0.2-RC7-SNAPSHOT")

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

Contributing
------------

Q. How do we setup our own Travis CI build?

A. There are two properties you must customize in .travis.yml

- addons.sauce_connect.username
- addons.sauec_connect.access_key

For the latter, we should encrypt it to prevent our OpenSauce account from abuses.
To do this, run the below command in our project directory:

    travis encrypt --add addons.sauce_connect.access_key THE_ACCESS_KEY

Assuming a .travis.yml file which contains:

    addons:
      sauce_connect:
        username: mumoshu

The command will add our access key encrypted to our .travis.yml file like:

    addons:
      sauce_connect:
        username: mumoshu
        access_key:
          secure: mgjauzNcJeW2co5Am4IqPgaR8CJsGZIQca4M7mZj1z+XlRIE4bw5PNmIk7coNj5/FWSmxlgT1vdK2G2pj4R2u+pl89zdeoBaLSffrFa0sHa2IOg6+UZiAB0EYUACChO9A8gVXd78n/zq52Q0Kj38SjsFnj/G/EsN7zbPqbY0Uis=

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
