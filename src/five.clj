(ns five
  (:require
   [clojure.string :as str]
   [util.input :refer [get-input!]]))

(defn- parse-rules
  "Take strings of pipe separated numbers, converts them to vectors of integers."
  [input]
  (->> input
       (map #(str/split % #"\|"))
       (map (fn [[a b]] [(Integer/parseInt a) (Integer/parseInt b)]))))

(defn- parse-data
  "Take strings of comma separated numbers, converts them to vectors of integers."
  [input]
  (->> input
       (map #(str/split % #","))
       (map (fn [x] (map #(Integer/parseInt %) x)))))

(defn- format-input
  "Sections are divided by two newlines.
  First section is the rules.
  Second section is the data."
  [input]
  (let [lines (str/split-lines input)
        [rules _ data] (partition-by empty? lines)]
    {:rules (parse-rules rules)
     :data (parse-data data)}))

#_(defn solve-a []
    (let [{:keys [rules data]} (format-input (get-input! 5))]))
