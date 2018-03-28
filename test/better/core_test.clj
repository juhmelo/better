(ns better.core-test
  (:require [clojure.test :refer :all]
            [better.core :as b]))

(deftest +-test
  (testing "+ works with numbers"
    (is (= (b/+ 3 2) 5))
    (is (= (b/+ 1 2.0) 3.0))
    (is (= (b/+ 1) 1)))

  (testing "+ works with strings"
    (is (= (b/+ "foo" "bar") "foobar"))
    (is (= (b/+ "foo") "foo"))
    (is (= (b/+ "foo" 1) "foo1")))

  (testing "+ works with maps"
    (is (= (b/+ {:a 1} {:b 2}) {:a 1 :b 2}))
    (is (= (b/+ {:a 1 :b 1} {:b 2}) {:a 1 :b 2}))
    (is (= (b/+ {:a 1 :b {:c 1 :d 1}} {:b 2}) {:a 1 :b 2}))
    (is (= (b/+ {:a 1 :b {:c 1 :d 1}} {:b {:c 2}}) {:a 1, :b {:c 2, :d 1}})))

  (testing "+ works with collections"
    ;; TODO write tests
    (is (= (b/+ [] []) []))
    (is (= (b/+ [1] [2]) [1 2]))
    (is (= (b/+ [1 2] [2]) [1 2 2]))
    (is (= (b/+ [1 "2"] [3.0]) [1 "2" 3.0]))))
