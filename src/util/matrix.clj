(ns util.matrix)

(defn get-in-matrix [matrix [x y]]
  (get-in matrix [x y]))

(defn matrix-width [matrix]
  (count matrix))

(defn matrix-height [matrix]
  (count (first matrix)))

(defn flatten-coordinates [matrix]
  (for [x (range (matrix-width matrix))
        y (range (matrix-height matrix))]
    [x y]))

(defn matrix-neighbors [matrix [x y]]
  (let [width (matrix-width matrix)
        height (matrix-height matrix)]
    (for [dx [-1 0 1]
          dy [-1 0 1]
          :when (not (and (= dx 0) (= dy 0)))
          :let [nx (+ x dx)
                ny (+ y dy)]
          :when (and (>= nx 0) (< nx width)
                     (>= ny 0) (< ny height))]
      [nx ny])))

(defn valid-directions [matrix [x y]]
  (let [width (matrix-width matrix)
        height (matrix-height matrix)]
    (for [[dx dy direction] [[-1 0 :n]
                             [1 0 :s]
                             [0 1 :e]
                             [0 -1 :w]
                             [-1 1 :ne]
                             [-1 -1 :nw]
                             [1 1 :se]
                             [1 -1 :sw]]
          :let [nx (+ x dx)
                ny (+ y dy)]
          :when (and (>= nx 0) (< nx width)
                     (>= ny 0) (< ny height))]
      direction)))

(defn get-in-direction [matrix direction [x y]]
  (let [[dx dy] (case direction
                  :n [-1 0]
                  :s [1 0]
                  :e [0 1]
                  :w [0 -1]
                  :ne [-1 1]
                  :nw [-1 -1]
                  :se [1 1]
                  :sw [1 -1])]
    (when (get-in matrix [(+ x dx) (+ y dy)])
      [(+ x dx) (+ y dy)])))
