# nlp-tools-clj

A collection of tools to support common NLP tasks *(under development)*.

## Installation

`lein install`

*will push to clojars once library is more full-featured*

## Usage

Import nlp-tools-clj:

```clojure
(ns my-ns (:require [nlp-tools-clj.core :as nlp-tools]))
```

Currently nlp-tools-clj only supports term-frequency.

```clojure
(term-frequency-matrix ["Andrew likes drinking coffee in the morning" "Mowgli likes chewing a bone in the morning"])
```

## License

Copyright © 2018 Andrew McLoud

Distributed under the Eclipse Public License either version 1.0.
