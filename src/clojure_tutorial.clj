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

:bar

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


;; apply function to each item in a collection and return a list of results

(map inc [1 2 3])

