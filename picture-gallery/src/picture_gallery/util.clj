(ns picture-gallery.util
  (:require [noir.session :as session]
            [hiccup.util :refer [url-encode]])
  (:import java.io.File))

(def thumb-prefix "thumb_")

(def galleries "galleries")

(defn gallery-path []
  (str galleries File/separator (session/get :user)))

(defn image-url [userid file-name]
  (str "/img/"
       userid
       "/"
       (url-encode file-name)))

(defn thumb-url [userid file-name]
  (image-url userid
             (str thumb-prefix file-name)))



