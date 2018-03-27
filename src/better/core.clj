(ns better.core
  (:import [java.lang Number String]
           [java.util Collection Map]))

(defmulti ++ type)

(defmethod ++ [Number] [& nums] (reduce + nums))
(defmethod ++ [String] [& strs] (reduce str strs))
(defmethod ++ [Collection] [& colls] (reduce concat colls))
(defmethod ++ [Map] [& maps] (reduce deep-merge maps))

(defn deep-merge [& maps]
  (if (every? map? maps)
    (apply merge-with deep-merge maps)
    (last maps)))
