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

(defn solve-a []
  (let [input (format-input (get-input! 2))]
    (count (filter (fn [row] (every? #(% row) rules)) input))))

(defn drop-nth [coll n]
  (if (= -1 n)
    coll
    (concat (subvec coll 0 n) (subvec coll (inc n)))))

(defn- dampened-eval
  "Evaluate the rules on the row with the nth element removed.
  If the row does not pass, recur with the next index.
  If the row passes, return 1."
  [row removed-idx]
  (if (>= removed-idx (count row))
    0
    (if (every? #(% (drop-nth row removed-idx)) rules)
      1
      (recur row (inc removed-idx)))))

(defn solve-b []
  (reduce (fn [acc row] (+ acc (dampened-eval row -1))) 0 (format-input (get-input! 2))))
