(ns one
  (:require
   [clojure.string :as str]
   [util.input :refer [get-input!]]))

(defn- format-input
  "Input string consists of two columns of integers separated by whitespace.
  Parse that into a vector of two vectors of integers."
  [input]
  (let [vectors (->> input
                     (str/split-lines)
                     (map #(str/split % #"\s+"))
                     (map (fn [[a b]] [(Integer/parseInt a) (Integer/parseInt b)])))]
    [(sort (map first vectors))
     (sort (map second vectors))]))

(defn solve []
  (let [[left right] (format-input (get-input! 1))]
    (reduce + (map (fn [a b] (abs (- a b))) left right))))
