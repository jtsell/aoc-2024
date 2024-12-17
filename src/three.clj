(ns three
  (:require
   [clojure.string :as str]
   [util.input :refer [get-input!]]))

(defn- parse-muls [input]
  (->> input
       (re-seq #"mul\((\d+),(\d+)\)")
       (map rest)
       (map (fn [[a b]] [(Integer/parseInt a) (Integer/parseInt b)]))))

(defn solve-a []
  (reduce (fn [acc [a b]] (+ acc (* a b))) 0 (parse-muls (get-input! 3))))

(defn- keep-enabled [input]
  (let [split (str/split input #"don't\(\)")]
    (println (first split))
    (->> split
         (map (fn [s] (str/split s #"do\(\)")))
         (map rest)
         (remove empty?)
         flatten
         (concat (first split))
         (apply str))))

(defn solve-b []
  (reduce (fn [acc [a b]] (+ acc (* a b))) 0 (parse-muls (keep-enabled (get-input! 3)))))
