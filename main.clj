;; Reader file reads the data from map.txt file and store return vector of line data

(defn Reader []
   (with-open [rdr (clojure.java.io/reader "map.txt")]
   (reduce conj [] (line-seq rdr))))

;; row = no of lines 
;; column = no. of characters in each line
(def myVec Reader)
(def mylist '())
(def row (count (myVec)))
(def column (count (clojure.string/split (eachLine 0) #"")))
(def a (* row column))

;; return individual line
(defn eachLine [a]
    (get (myVec) a))

;; return one character given row and column number
(defn eachElement [row_index column_index]
    (get (clojure.string/split (eachLine row_index) #"") column_index))

(defn wall_encounter [column_index]
    (- column_index 1))

;; gives next - element
(defn search_path [row_index, column_index]
  (def check1 (+ row_index 1))
  (def check2 (+ column_index 1))
  (def check3 (- row_index 1))
  (def check4 (- column_index 1))
  (if (and (< check1 row) (== 0 (compare (eachElement check1 column_index) "-")))
    (def v1 (vector check1 column_index))
    (if (and (< check2 column) (== 0 (compare (eachElement row_index check2) "-")))
        (def v1 (vector row_index check2))
        (if (and (> check3 0) (== 0 (compare (eachElement check3 column_index) "-")))
          (def v1 (vector check3 column_index))
          (if (and (> check4 0) (== 0 (compare (eachElement row_index check4) "-")))
            (def v1 (vector row_index check4))))))
            (println v1)
            v1)

(defn Data_Getter []
   (def x (atom 0))
   (while (< @x row)
      (do
      (def y (atom 0))
      (while (< @y column)
      (do
          (search_path @x @y)
          (swap! y inc)
      ))
      (swap! x inc))))

(Data_Getter)
