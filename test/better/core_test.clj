(ns better.core-test
  (:require [clojure.test :refer :all]
            [better.core :as b]))

(deftest +
  (testing "+ works with numbers"
    (is (= (b/+ 3 2) 5))
    (is (= (b/+ 1 2.0) 3.0))
    (is (= (b/+ 1) 1))
    (is (= (b/+ nil 1) 1)))

  (testing "+ works with strings"
    (is (= (b/+ "foo" "bar") "foobar"))
    (is (= (b/+ "foo") "foo"))
    (is (= (b/+ "foo" 1 nil) "foo1")))

  (testing "+ works with maps"
    (is (= (b/+ {:a 1} {:b 2} nil) {:a 1 :b 2}))
    (is (= (b/+ {:a 1 :b 1} {:b 2}) {:a 1 :b 2}))
    (is (= (b/+ {:a 1 :b {:c 1 :d 1}} {:b 2}) {:a 1 :b 2}))
    (is (= (b/+ {:a 1 :b {:c 1 :d 1}} {:b {:c 2}}) {:a 1, :b {:c 2, :d 1}})))

  (testing "+ works with collections"
    ;; TODO write tests
    (is (= (b/+ [] []) []))
    (is (= (b/+ [1] [2] nil) [1 2]))
    (is (= (b/+ [1 2] [2]) [1 2 2]))
    (is (= (b/+ [1 "2"] [3.0]) [1 "2" 3.0]))))

(deftest -
  (testing "- works with numbers"
    (is (= (b/- 3 2) 1))
    (is (= (b/- 1 2.0) -1.0))
    (is (= (b/- 1) 1))
    (is (= (b/- nil 1) 1)))

  (testing "- works with strings"
    (is (= (b/- "foo" "bar") "foo"))
    (is (= (b/- "foobar" "bar" nil) "foo"))
    (is (= (b/- "foo1" 1) "foo")))

  (testing "+ works with collections"
    ;; TODO write tests
    (is (= (b/- [] []) []))
    (is (= (b/- [1 2] [2] nil [nil]) [1]))
    (is (= (b/- [1 2 3 4 5] [2 3]) [1 4 5]))
    (is (= (b/- [1 "2" 3.0] [3.0]) [1 "2"]))))
