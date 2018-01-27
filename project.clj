(defproject nlp-tools-clj "0.1.0"
  :description "A collection of tools to support common NLP tasks."
  :url "https://github.com/andrewmcloud"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [net.mikera/core.matrix "0.61.0"]
                 [net.mikera/vectorz-clj "0.47.0"]
                 [clojure-opennlp "0.4.0"]]
  :main ^:skip-aot nlp-tools-clj.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
