(ns better.core-test
  (:require [clojure.test :refer :all]
            [better.core :refer :all]))

(deftest +-test
  (testing "++ works with numbers"
    (is (= (++ 3 2) 5))
    (is (= (++ 1 2.0) 3.0))
    (is (= (++ 1) 1)))

  (testing "++ works with strings"
    (is (= (++ "foo" "bar") "foobar"))
    (is (= (++ "foo") "foo"))
    (is (= (++ "foo" 1) "foo1")))

  (testing "++ works with maps"
    (is (= (++ {:a 1} {:b 2}) {:a 1 :b 2}))
    (is (= (++ {:a 1 :b 1} {:b 2}) {:a 1 :b 2}))
    (is (= (++ {:a 1 :b {:c 1 :d 1}} {:b 2}) {:a 1 :b 2}))
    (is (= (++ {:a 1 :b {:c 1 :d 1}} {:b {:c 2}}) {:a 1, :b {:c 2, :d 1}})))

  (testing "++ works with collections"
    ;; TODO write tests
    (is (= (++ [] []) []))
    (is (= (++ [1] [2]) [1 2]))
    (is (= (++ [1 2] [2]) [1 2 2]))
    (is (= (++ [1 "2"] [3.0]) [1 "2" 3.0]))))
