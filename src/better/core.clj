(ns better.core
  (:require [better.string :as str])
  (:import [java.lang Number String]
           [java.util Collection Map]
           java.util.regex.Pattern))

(defn- deep-merge [& maps]
  (if (every? map? maps)
    (apply merge-with deep-merge maps)
    (last maps)))

(defn- coll- [v v2]
  (let [indexes (reduce
                  #(or %1
                       (let [[x y] %2]
                         (when (= v2 (subvec v x y))
                           %2)))
                  nil
                  (for [i  (range 0 (count v))
                        i2 (range i (inc (count v)))]
                    [i i2]))]
    (if indexes
      (vec (concat (subvec v 0 (first indexes))
                   (subvec v (second indexes))))
      v)))

(defn- colls- [c & cs]
  (reduce #(coll- %1 %2) c cs))

(defmulti + (fn [& xs] (type (first (remove nil? xs)))))
(defmethod + Number [& args] (reduce clojure.core/+ (remove nil? args)))
(defmethod + String [& args] (reduce str args))
(defmethod + Collection [& args] (reduce concat args))
(defmethod + Map [& args] (reduce deep-merge args))
(defmethod + nil [_] nil)

(defmulti  - (fn [& xs] (type (first (remove nil? xs)))))
(defmethod - Number [& args] (reduce clojure.core/- (remove nil? args)))
(defmethod - String [& args] (reduce (fn [s m] (str/replace-first s m "")) args))
(defmethod - Collection [& args] (apply colls- args))
#_(defmethod - Map [& args] (reduce deep-merge args))
