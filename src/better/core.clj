(ns better.core
  (:import [java.lang Number String]
           [java.util Collection Map]))

(defn deep-merge [& maps]
  (if (every? map? maps)
    (apply merge-with deep-merge maps)
    (last maps)))

(defmulti ++ (fn [x & xs] (type x)))
(defmethod ++ Number [& args] (reduce clojure.core/+ args))
(defmethod ++ String [& args] (reduce str args))
(defmethod ++ Collection [& args] (reduce concat args))
(defmethod ++ Map [& args] (reduce deep-merge args))
