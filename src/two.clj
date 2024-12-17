(ns two
  (:require
   [clojure.string :as str]
   [util.input :refer [get-input!]]))

(defn- format-input [input]
  (->> input
       (str/split-lines)
       (map (fn [row] (mapv #(Integer/parseInt %) (str/split row #"\s+"))))))

(def rules
  [(fn [row] (or (apply > row) (apply < row))) ;; Levels are either all increasing or all decreasing.
   (fn [row] (every? #(<= 1 (abs (- (first %) (second %)))) (partition 2 1 row))) ;; Levels differ by at least one
   (fn [row] (every? #(>= 3 (abs (- (first %) (second %)))) (partition 2 1 row)))]) ;; Levels differ by at most three

(defn solve []
  (let [input (format-input (get-input! 2))]
    (count (filter (fn [row] (every? #(% row) rules)) input))))
