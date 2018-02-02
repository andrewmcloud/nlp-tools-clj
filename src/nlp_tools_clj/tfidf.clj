(ns nlp-tools-clj.tfidf
  (:require [clojure.core.matrix :as m]))

(m/set-current-implementation :vectorz)

(defn- idf
  [N t:D]
  (let [occurrences (->> t:D (remove zero?) count)]
    (Math/log (/ N occurrences))))

(defn- inverse-doc-freqs
  [term-frequency-matrix]
  (let [N (m/row-count term-frequency-matrix)]
    (map #(idf N %) (m/columns term-frequency-matrix))))

(defn- tfidf-doc
  [tfs term-frequency-matrix]
  (map (fn [idf tf]
         (* tf idf)) (inverse-doc-freqs term-frequency-matrix) tfs))

(defn tfidf-corpus
  [term-frequency-matrix]
  (map #(tfidf-doc % term-frequency-matrix) (m/rows term-frequency-matrix)))
