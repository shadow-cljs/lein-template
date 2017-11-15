(ns leiningen.new.shadow-cljs
  (:require [leiningen.new.templates :refer [renderer raw-resourcer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "shadow-cljs"))
(def raw (raw-resourcer "shadow-cljs"))

(defn templates-by-lib
  [lib data]
  (get
   {:om [["src/{{sanitized}}/core.cljs" (render "om/core.cljs" data)]
         ["shadow-cljs.edn" (render "om/shadow-cljs.edn" data)]]
    :reagent [["src/{{sanitized}}/core.cljs" (render "reagent/core.cljs" data)]
              ["shadow-cljs.edn" (render "reagent/shadow-cljs.edn" data)]]
    :rum [["src/{{sanitized}}/core.cljs" (render "rum/core.cljs" data)]
          ["shadow-cljs.edn" (render "rum/shadow-cljs.edn" data)]]}
   lib))

(defn shadow-cljs
  [name & lib]
  (main/info "Generating fresh shadow-cljs project.")
  (let [lib (if-let [lib (first lib)]
              (let [v (keyword (clojure.string/replace lib "+" ""))]
                v)
              :reagent)
        data {:name name
              :sanitized (name-to-path name)}]
    (->>
     [["package.json" (raw "package.json")]
      ["README.md" (raw "README.md")]
      ["public/index.html" (render "index.html" data)]
      ["public/css/style.css" (raw "style.css")]
      ]
     (concat (templates-by-lib lib data))
     (apply ->files data))))
