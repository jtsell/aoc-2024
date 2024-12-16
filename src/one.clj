(ns one
  (:require
   [clojure.string :as str]
   [input :refer [get-input]]))

(defn- format-input [input]
  (let [vectors (->> input
                     (str/split-lines)
                     (map #(str/split % #"\s+"))
                     (map (fn [[a b]] [(Integer/parseInt a) (Integer/parseInt b)])))]
    [(sort (map first vectors))
     (sort (map second vectors))]))

(defn solve []
  (let [input (format-input (get-input 1))]
    (reduce + (map (fn [a b] (abs (- a b))) (first input) (second input)))))
