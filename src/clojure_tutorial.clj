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


;; a function

#(+ %1 %2)

#(inc %)


;; reduce applies a function to the first and second values and then to the result and the third value etc.

(reduce + [1 2 3])


(reduce (fn [x y] (str x "," y))
        ["a" "b" "c"])


;; add to a collection

(conj [1 2] 3)

(conj '(1 2) 3)

(conj #{1 2} 3)


;; drop one from a sequence

(rest [1 2])

(rest '(1 2))

;; take the first from a seuquence

(first [1 2])

(first '(1 2))


;; add to a map

(assoc {:foo 1}
  :bar 2
  :foobar 3)


;; add to a map of maps

(assoc-in {} [:foo :bar :foobar] 3)


;; update in a map of maps

(update-in {:foo 1
            :bar {:foobar 2}}

           [:bar :foobar]

           inc)


;; remove from a map

(dissoc {:foo 1
         :bar 2}
        :bar)



;; remove from a set

(disj #{1 2} 2)


;; filter a sequence

(filter even? [1 2 3 4])


;; define a symbol

(def x 1)


;; define a functin

(def my-add (fn [x y] (+ x y)))

(defn my-add2 [x y]
  (+ x y))

(my-add2 1 2)



;; define a protocol

(defprotocol Worker
  "Worker does work"
  (do-work [worker work] "works")
  (do-nothing [worker] "takes a nap"))

;; define a type

(defrecord MyWorker [earnings])


;; implement a protocol

(extend-type MyWorker
  Worker
  (do-work [worker work]
           (update-in worker [:earnings] #(+ % work)))

  (do-nothing [worker]
              (Thread/sleep 1000)
              worker))
