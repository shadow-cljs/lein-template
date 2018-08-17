(ns leiningen.new.shadow-cljs
  (:require [leiningen.new.templates :refer [renderer raw-resourcer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "shadow-cljs"))
(def raw (raw-resourcer "shadow-cljs"))

(defn templates-by-lib
  [lib data]
  (get
   {:om      [["src/{{sanitized}}/core.cljs" (render "om/core.cljs" data)]]
    :reagent [["src/{{sanitized}}/core.cljs" (render "reagent/core.cljs" data)]]
    :rum     [["src/{{sanitized}}/core.cljs" (render "rum/core.cljs" data)]]}
   lib))

(defn templates-by-tool
  [tool data]
  (get
   {:native   [["shadow-cljs.edn" (render "native/shadow-cljs.edn" data)]]
    :depsedn [["shadow-cljs.edn" (render "native/shadow-cljs.edn" data)]
              ["deps.edn" (render "deps/deps.edn" data)]]
    :lein     [["shadow-cljs.edn" (render "native/shadow-cljs.edn" data)]
               ["project.clj" (render "lein/project.clj" data)]]}
   tool))

(def supported-libs
  #{:om :reagent :rum})

(def supported-build-tools
  #{:native :lein :depsedn})

(defn keywordize
  [option]
  (keyword (clojure.string/replace option "+" "")))

(defn shadow-cljs
  [name & opts]
  (main/info "Generating fresh shadow-cljs project.")
  (let [opts (map keywordize opts)
        lib  (if-let [lib (first (remove supported-build-tools opts))]
               lib
               :reagent)
        tool (if-let [tool (first (remove supported-libs opts))]
               tool
               :native)
        data {:name name
              :sanitized (name-to-path name)
              tool true
              lib true}]
    (main/info data)
    (->>
     [["package.json" (raw "package.json")]
      ["README.md" (raw "README.md")]
      ["public/index.html" (render "index.html" data)]
      ["public/css/style.css" (raw "style.css")]]
     (concat (templates-by-lib lib data))
     (concat (templates-by-tool tool data))
     (apply ->files data))))
