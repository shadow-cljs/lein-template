(ns {{name}}.core
    (:require [goog.dom :as gdom]
              [om.next :as om :refer-macros [defui]]
              [om.dom :as dom]))

(enable-console-print!)

(defui HelloWorld
  Object
  (render [this]
    (dom/div nil "Hello, world!")))

(def hello (om/factory HelloWorld))

(defn start []
  (js/ReactDOM.render (hello) (gdom/getElement "app")))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))
