(ns picture_gallery.routes.home
  (:require [compojure.core :refer :all]
            [picture_gallery.views.layout :as layout]))

(defn home [] 
  (layout/common [:h1 "Hello World!"]))

(defroutes home-routes
  (GET "/" [] (home)))
