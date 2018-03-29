(ns better.string
  (:import clojure.lang.Symbol))

(defmacro defn-nil-safe [fn-name func]
  `(intern
     ~*ns*
     (with-meta ~(symbol (name fn-name)) (meta ~func))
           (fn [& args#] (when (first args#) (apply ~func args#)))))

(doseq [[sym func] (ns-publics 'clojure.string)]
  (defn-nil-safe sym func))
