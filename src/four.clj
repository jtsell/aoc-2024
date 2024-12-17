(ns four
  (:require
   [clojure.string :as str]
   [util.input :refer [get-input!]]
   [util.matrix :refer [flatten-coordinates
                        get-in-direction
                        get-in-matrix
                        valid-directions]]))

(defn- format-input
  "Format the string input into a matrix of characters"
  [input]
  (->> input
       str/split-lines
       (mapv #(str/split % #""))))

(def next-character
  {"X" "M"
   "M" "A"
   "A" "S"
   "S" "done"})

(defn- check-match [matrix coordinates direction check-char]
  (let [new-coords (get-in-direction matrix direction coordinates)
        value-char (get-in-matrix matrix new-coords)
        next-char (get next-character check-char)
        done? (= next-char "done")
        match? (= value-char check-char)]
    (cond
      (not match?) 0
      (and match? done?) 1
      (and match? (not done?)) (check-match matrix new-coords direction next-char))))

(defn- check-initial [matrix coordinates check-char]
  (if (not= (get-in-matrix matrix coordinates) check-char)
    0
    (reduce (fn [acc direction] (+ acc (check-match matrix coordinates direction (get next-character check-char)))) 0 (valid-directions matrix coordinates))))

(defn solve []
  (let [matrix (format-input (get-input! 4))]
    (reduce (fn [acc coordinates] (+ acc (check-initial matrix coordinates "X")))
            0
            (flatten-coordinates matrix))))
