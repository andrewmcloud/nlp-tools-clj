(ns nlp-tools-clj.core
  (:require [clojure.core.matrix :as m]
            [nlp-tools-clj.term-frequencies :as tf]
            [nlp-tools-clj.tfidf :as tfidf]))

(m/set-current-implementation :vectorz)

(defn term-frequency-matrix
  "builds a sparse term frequency matrix for a corpus of documents

  args: corpus - coll of strings (documents).
  returns: sparse matrix (PersistentArrayMap)"
  [corpus]
  (let [term-frequency-maps (map tf/term-frequencies corpus)
        terms (tf/build-corpus-vocab term-frequency-maps)
        M (m/new-sparse-array [(count corpus) (count terms)])]
    (dorun
      (map-indexed (fn [row term-frequency-map]
                     (m/set-row! M row (m/sparse (tf/get-frequencies terms term-frequency-map))))
                   term-frequency-maps))
    {:terms terms
     :term-frequency-matrix M}))

(defn tf-idf
  [term-frequency-matrix]
  (let [M (m/new-sparse-array (m/shape term-frequency-matrix))]
    (dorun
      (map-indexed (fn [idx row]
                     (m/set-row! M idx row))
                   (tfidf/tfidf-corpus term-frequency-matrix)))
    M))