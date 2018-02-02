(ns nlp-tools-clj.term-frequencies
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [opennlp.nlp :as nlp]))

(def ^:private stopwords (set (s/split-lines (slurp (io/resource "stopwords.txt")))))
(def ^:private tokenizer (nlp/make-tokenizer (io/resource "en-token.bin")))

(defn clean-text
  "lowercase and strip garbage from text

  args: text - string
  returns: string"
  [text]
  (-> text
      (s/lower-case)
      (s/replace #"[!@#$%^&*=+(),.>?/]" " ")))

(defn tokenize
  "tokenize text with opennlp english tokenizer

  args: text - string
  returns: PersistentVector"
  [text]
  (tokenizer text))

(defn build-doc-vocab
  "converts document to a vocabulary for term-frequency counting.
  cleans text, tokenizes, removes stopwords, converts tokens to keywords

  args: text - string
  returns: lazy-seq"
  [text]
  (let [clean-xf (comp (remove stopwords) (map keyword))]
    (->> text
         clean-text
         tokenize
         (sequence clean-xf))))

(defn get-frequencies
  "builds a document term-frequency vector based on corpus vocabulary

  args: corpus-terms - coll of unique terms in corpus
        doc-terms - coll of all terms in document
  returns: vector"
  [corpus-terms doc-terms]
  (mapv #(get doc-terms % 0) corpus-terms))

(def term-frequencies
  "returns: function that counts term occurrences in a document"
  (comp frequencies build-doc-vocab))

(defn build-corpus-vocab
  "builds the corpus vocabulary

  args: term-frequency-maps - coll of maps document-terms and frequencies [{term1 freq, ... , term2 freq} {...} ... ]
  returns: lazy-seq"
  [term-frequency-maps]
  (sequence (comp (mapcat keys) (distinct)) term-frequency-maps))
