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


;; equality

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
;; http://clojuredocs.org/clojure_core/clojure.core/inc

(inc 1)


;; condition

;; apply function to each item in a collection and return a list of results

(map inc [1 2 3])

