# shadow-cljs

[shadow-cljs](https://github.com/thheller/shadow-cljs) template.
Supports Om, Reagent and Rum.

Build tools supported are lein, deps.edn and native shadow-cljs.

## Usage
Give it a UI framework and a build tool.  Defaults to native shadow-cljs and reagent.

``` shell
lein new shadow-cljs your-project +reagent
lein new shadow-cljs your-project +om
lein new shadow-cljs your-project +rum
lein new shadow-cljs your-project +lein +om
lein new shadow-cljs your-project +depsedn +om
lein new shadow-cljs your-project +native +om
...
```

## License

Copyright © 2017 Tienson Qin

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
