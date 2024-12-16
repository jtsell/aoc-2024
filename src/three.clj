(ns three
 (:require
  [input :refer [get-input]]))

(defn- format-input [input]
  (->> input
       (re-seq #"mul\((\d+),(\d+)\)")
       (map rest)
       (map (fn [[a b]] [(Integer/parseInt a) (Integer/parseInt b)]))))

(defn solve []
  (reduce (fn [acc [a b]] (+ acc (* a b))) 0 (format-input (get-input 3))))
