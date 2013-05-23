;; namespace

(ns clojure-tutorial)


;; numbers

1
1.2
1/3
84762146234912363219874326149871234692138746


;; srings

"foo"

"foo
bar"



;; keywords

:foo

::bar ; this equals :clojure-tutorial/bar

:foo.bar/foobar



;; linked lists

'(1 2 3)

'(1 :foo "bar")



;; vectors

[1 2 3]

[1 :foo "bar"]



;; hash maps

{:a 1
 :b 2}

{:foo {:bar "foobar"}}


;; sets

#{:foo :bar}
#{1 2 3 :foo "bar"}


;; booleans

;true
;false

;; null, nil

;nil


;; test equality
;; http://clojuredocs.org/clojure_core/clojure.core/=

(= 1 1)

(= nil false)

(= :foo :foo :foo)

(= 1 1 1 2)


;; add numbers

(+ 3 2)

(+ 3 2 3 4)


;; substract numbers

(- 1 1 1)


;; increment a number

(inc 1)

;; create a string
(str 1 2)

(str 1 " " :foo)

(str [:foo :bar] " " {:foo :bar})


;; if is a function

(if true :foo :bar)

(if false :foo :bar)

(if nil :foo :bar) ; nil is false

(if 4 :bar :foobar) ; everything else is true


;; select one based on a value

(case :foo
  :foobar 3
  :foo 1
  :bar 2)


;; select one based on a condition

(cond
 (= 1 2) :foo
 (= 1 1) :bar
 :default :foobar)


;; apply function to each item in a collection and return a list of results

(map inc [1 2 3])


;; a function

(fn [x] (+ x 1))

((fn [x] (+ x 1)) 1)

(fn [x y] (+ x y))

;; reduce applies a function to the first and second values and then to the result and the third value etc.

(reduce + [1 2 3])


(reduce (fn [x y] (str x "," y))
        ["a" "b" "c"])


;; define a symbol

(def x 1)


;; define a functin

(def my-add (fn [x y] (+ x y)))

(defn my-add2 [x y]
  (+ x y))

(my-add2 1 2)






