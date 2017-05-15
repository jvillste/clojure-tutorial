(ns maps
  (:require [clj-http.client :as client]
            [clojure.data.json :as json])
  (:import [java.net URLEncoder])
  (:use clojure.test))

;; :require for clojure namespaces with aliases
;; :import for Java packages
;; :use for adding symbols from other namespace to this namespace

(def api-key (slurp "api-key.txt"))

;; you can also read clojure data with read-string: (:api-key (read-string (slurp "fake-api-key-map.clj")))

(defn autocomplete-url [input key]
  (str "https://maps.googleapis.com/maps/api/place/autocomplete/json?input="
       (URLEncoder/encode input)
       "&types=address&key="
       key))

(deftest autocomplete-url-test
  (is (= "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=Mechelinin&types=address&key=api-key"
         (autocomplete-url "Mechelinin"
                           "api-key"))))

;; -> is a macro, use C-c M-m to expand in Cider/Emacs

(defn call-autocomplete-api [query api-key]
  (-> (autocomplete-url query
                        api-key)
      (client/get)
      (:body)
      (json/read-str)))

(comment
  (call-autocomplete-api "mechelinin" api-key))

(defn main-text-from-prediction [prediction]
  (get-in prediction
          ["structured_formatting" "main_text"]))

(deftest main-text-from-prediction-test
  (is (= "Mechelininkatu"
         (main-text-from-prediction {"description" "Mechelininkatu, Helsinki, Finland",
                                     "id" "28541c81cfaf55f55d3b62c49516cb15723ca305",
                                     "matched_substrings" [{"length" 10, "offset" 0}],
                                     "place_id" "EiFNZWNoZWxpbmlua2F0dSwgSGVsc2lua2ksIEZpbmxhbmQ",
                                     "reference" "CjQlAAAAS6UCiqL859p88nC1aiD1mf-dZATFjBQ4olu2Sniaor4fwSjF4ryuKr6ZsqNLboZXEhA4PbXaJGDC_UrILBBZxQZpGhSVp8MpoLQLKTzAlrxqiGokBBCSuQ",
                                     "structured_formatting" {"main_text" "Mechelininkatu", "main_text_matched_substrings" [{"length" 10, "offset" 0}], "secondary_text" "Helsinki, Finland"},
                                     "terms" [{"offset" 0, "value" "Mechelininkatu"} {"offset" 16, "value" "Helsinki"} {"offset" 26, "value" "Finland"}],
                                     "types" ["route" "geocode"]}))))

(defn street-names-from-result [result]
  (map main-text-from-prediction
       (get result "predictions")))

(deftest street-names-from-result-test
  (is (= ["Mechelininkatu" "Mechelinintie" "Mechelininaukio"]
         (street-names-from-result {"predictions"
                                    [{"description" "Mechelininkatu, Helsinki, Finland",
                                      "id" "28541c81cfaf55f55d3b62c49516cb15723ca305",
                                      "matched_substrings" [{"length" 10, "offset" 0}],
                                      "place_id" "EiFNZWNoZWxpbmlua2F0dSwgSGVsc2lua2ksIEZpbmxhbmQ",
                                      "reference" "CjQlAAAAS6UCiqL859p88nC1aiD1mf-dZATFjBQ4olu2Sniaor4fwSjF4ryuKr6ZsqNLboZXEhA4PbXaJGDC_UrILBBZxQZpGhSVp8MpoLQLKTzAlrxqiGokBBCSuQ",
                                      "structured_formatting" {"main_text" "Mechelininkatu", "main_text_matched_substrings" [{"length" 10, "offset" 0}], "secondary_text" "Helsinki, Finland"},
                                      "terms" [{"offset" 0, "value" "Mechelininkatu"} {"offset" 16, "value" "Helsinki"} {"offset" 26, "value" "Finland"}],
                                      "types" ["route" "geocode"]}
                                     {"description" "Mechelinintie, Hamina, Finland",
                                      "id" "3b1f26d4350d1804d5543907355e99dcc2e4df19",
                                      "matched_substrings" [{"length" 10, "offset" 0}],
                                      "place_id" "Eh5NZWNoZWxpbmludGllLCBIYW1pbmEsIEZpbmxhbmQ",
                                      "reference" "CjQiAAAA4s9dZoe6O_6X4Vfrh2Ra5vwXqRLCmgIyJ-o7qY4rqCwiToAt_ZzA1IS4gAt8SsLxEhAOEih2AQ5oeQLU44cI-rgEGhT8YsBrS1PtvXLNyGCXMBQrfdQuAA",
                                      "structured_formatting" {"main_text" "Mechelinintie", "main_text_matched_substrings" [{"length" 10, "offset" 0}], "secondary_text" "Hamina, Finland"},
                                      "terms" [{"offset" 0, "value" "Mechelinintie"} {"offset" 15, "value" "Hamina"} {"offset" 23, "value" "Finland"}],
                                      "types" ["route" "geocode"]}
                                     {"description" "Mechelininaukio, Helsinki, Finland",
                                      "id" "a7447855ce04c5b19c92e89ad72d3596b070221d",
                                      "matched_substrings" [{"length" 10, "offset" 0}],
                                      "place_id" "EiJNZWNoZWxpbmluYXVraW8sIEhlbHNpbmtpLCBGaW5sYW5k",
                                      "reference" "CjQmAAAAxLMFfQhf05wff8wvZByLgW5B6tw824poGbD6Hx5uAoU-ujwS5VAdFNbLpcBJ6eUiEhCBzo-NZwvGJKcQPhU2DkDCGhQIm_msRs8rq64KHcckxOSYkIW1_Q",
                                      "structured_formatting" {"main_text" "Mechelininaukio", "main_text_matched_substrings" [{"length" 10, "offset" 0}], "secondary_text" "Helsinki, Finland"},
                                      "terms" [{"offset" 0, "value" "Mechelininaukio"} {"offset" 17, "value" "Helsinki"} {"offset" 27, "value" "Finland"}],
                                      "types" ["route" "geocode"]}],
                                    "status" "OK"}))))

(defn search-streets [query api-key]
  (-> query
      (call-autocomplete-api api-key)
      (street-names-from-result)))

(comment
  (search-streets "helsinki, Mechelin" api-key))

