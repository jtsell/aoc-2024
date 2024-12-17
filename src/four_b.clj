(ns four-b
  (:require
   [util.input :refer [get-input!]]
   [util.matrix :refer [flatten-coordinates
                        get-in-direction
                        get-in-matrix
                        string->matrix]]))

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

(defn solve []
  (let [matrix (string->matrix (get-input! 4))]
    (reduce (fn [acc coordinates] (+ acc (check-cross matrix coordinates)))
            0
            (flatten-coordinates matrix))))
