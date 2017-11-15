(ns {{name}}.core
  (:require [rum.core :as rum]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello world!"}))

(rum/defc hello-world []
  [:div
   [:h1 (:text @app-state)]
   [:h3 "Edit this and watch it change!"]])

(defn start []
  (rum/mount (hello-world)
             (. js/document (getElementById "app"))))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))
