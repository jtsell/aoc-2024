(ns util.input
  (:require
   [clj-http.client :as http]
   [clojure.java.io :as io]))

(defn- download-input [day]
  (let [url (format "https://adventofcode.com/2024/day/%s/input" day)
        token (System/getenv "AOC_SESSION")
        headers {"Cookie" (str "session=" token)}
        response (http/get url {:headers headers :as :string})]
    (spit (str "inputs/" day) (:body response))))

(defn get-input! [day]
  (let [file (str "inputs/" day)]
    (if (not (io/file file))
      (println (str "Downloading input for day " day))
      (download-input day))
    (slurp file)))
