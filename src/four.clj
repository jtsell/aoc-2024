(ns four-a
  (:require
   [util.input :refer [get-input!]]
   [util.matrix :refer [flatten-coordinates
                        get-in-direction
                        get-in-matrix
                        string->matrix
                        valid-directions]]))

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

(defn solve-a []
  (let [matrix (string->matrix (get-input! 4))]
    (reduce (fn [acc coordinates] (+ acc (check-initial matrix coordinates "X")))
            0
            (flatten-coordinates matrix))))

(defn- diagonal-edge-set [matrix coordinates [d1 d2]]
  (set [(get-in-matrix matrix (get-in-direction matrix d1 coordinates))
        (get-in-matrix matrix (get-in-direction matrix d2 coordinates))]))

(defn- check-cross [matrix coordinates]
  (if (and (= "A" (get-in-matrix matrix coordinates))
           (= #{"M" "S"}
              (diagonal-edge-set matrix coordinates [:sw :ne]) 
              (diagonal-edge-set matrix coordinates [:nw :se])))
    1
    0))

(defn solve-b []
  (let [matrix (string->matrix (get-input! 4))]
    (reduce (fn [acc coordinates] (+ acc (check-cross matrix coordinates)))
            0
            (flatten-coordinates matrix))))
