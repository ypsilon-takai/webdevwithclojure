(ns picture-gallery.handler
  (:require [compojure.route :as route]
            [compojure.core :refer [defroutes]]
            [noir.util.middleware :as middelware]
            [picture_gallery.routes.home :refer [home-routes]]
            ))

(defn init []
  (println "picture_gallery is starting"))

(defn destroy []
  (println "picture_gallery is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (noir.util.middleware/app-handler [home-routes app-routes])))


