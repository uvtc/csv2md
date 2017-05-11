#!/usr/bin/env inlein

;; Copyright 2014, 2015, 2016, 2017 John Gabriele <jgabriele@fastmail.fm>
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

'{:dependencies [[org.clojure/clojure  "1.8.0"]
                 [org.clojure/data.csv "0.1.3"]]}

(require '[clojure.data.csv :as csv])
(require '[clojure.string   :as str])


(def usage-msg "Please pass exactly one arg, a csv filename, like so:

    csv2md.clj filename.csv

Pretty Pandoc-markdown table output will go to stdout.")


(defn main
  []
  (when (or (not= (count *command-line-args*) 1)
            (not (str/ends-with? (first *command-line-args*)
                                 ".csv")))
    (println usage-msg)
    (System/exit 0))
  (let [input-file (first *command-line-args*)
        rows (csv/read-csv (slurp input-file))
        ;;_ (println rows)
        inverted-rows (apply map vector rows)
        ;;_ (println inverted-rows)
        num-chars (for [r inverted-rows]
                    (map count r))
        ;;_ (println num-chars)
        max-col-sizes (map #(apply max %) num-chars)
        ;;_ (println max-col-sizes)
        header (str/join "  " (for [n max-col-sizes]
                                (str/join (repeat (+ n 2) "-"))))]
    (println header)
    (doseq [row rows]
      (let [padded (for [[idx elem] (map-indexed vector row)]
                     (format (str "%-" (+ 2 (nth max-col-sizes idx)) "s")
                             elem))]
        (println (str/trimr (str/join "  " padded)))))
    (println header)))

;; ------------------------
(main)
