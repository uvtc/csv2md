#!/usr/bin/env inlein

;; Copyright 2014, 2015, 2016 John Gabriele <jgabriele@fastmail.fm>
;;
;; This program is free software: you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.
;;
;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.
;;
;; You should have received a copy of the GNU General Public License
;; along with this program.  If not, see <http://www.gnu.org/licenses/>.

'{:dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.csv "0.1.3"]]}

(require '[clojure.string   :as str]
         '[clojure.data.csv :as csv])

(when (not= 1 (count *command-line-args*))
  (println "Please pass exactly one arg: the csv filename.")
  (System/exit 0))

(def infile-name (first *command-line-args*))

(defn find-max-len
  "Given a list of lists of strings, and also given an index
(indicating which \"column\" you're interested in), find the
max string length for all the strings in that column."
  [coll col-num]
  (let [these  (map (fn [row]
                      (nth row col-num))
                    coll)
        counts (map count these)]
    (apply max counts)))

(defn print-guide-bars
  "Prints out the md table delimiters. `max-lengths` is a map of
col-num to that col-nums max size."
  [max-lengths]
  (doseq [[_ size] (sort max-lengths)]
    (let [size (+ 2 size)]
      (print (apply str (repeat size \-)))
      (print "  ")))
  (newline))
  
;; -----------------------------------------------------------------------
(let [csv-data        (csv/read-csv (slurp infile-name))
      num-cols        (count (first csv-data))
      all-max-lengths (reduce (fn [accum i]
                                (assoc accum i (find-max-len csv-data i)))
                              {}
                              (range num-cols))]
  (dotimes [row-num (count csv-data)]
    (when (= row-num 1)
      (print-guide-bars all-max-lengths))
    (dotimes [col-num num-cols]
      (let [this-one     (nth (nth csv-data row-num)
                              col-num)
            max-col-size (+ 4 (get all-max-lengths col-num))
            num-spaces   (- max-col-size (count this-one))
            spaces       (apply str (repeat num-spaces \space))]
        (print (str this-one spaces))))
    (newline))
  (print-guide-bars all-max-lengths))
