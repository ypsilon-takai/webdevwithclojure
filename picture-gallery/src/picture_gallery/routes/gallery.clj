(ns picture-gallery.routes.gallery
  (:require [compojure.core :refer :all]
            [hiccup.element :refer :all]
            [hiccup.form :refer [check-box]]
            [hiccup.page :refer :all]
            [picture-gallery.views.layout :as layout]
            [picture-gallery.util
             :refer [thumb-prefix image-url thumb-url]]
            [picture-gallery.models.db :as db]
            [noir.session :as session]))

(defn thumbnail-link [{:keys [userid name]}]
  [:div.thumbnail
   [:a {:class name :href (image-url userid name)}
    (image (thumb-url userid name))
    (if (= userid (session/get :user))
      (check-box name))]])

(defn display-gallery [userid]
  (if-let [gallery (not-empty (map thumbnail-link (db/images-by-user userid)))]
    [:div
     [:div#error]
     gallery
     (if (= userid (session/get :user))
       [:input#delete {:type "submit" :value "delete images"}])]
    [:p "The user " userid " does not have any galleries"]))

(defn gallery-link [{:keys [userid name]}]
  [:div.thumbnail
   [:a {:href (str "/gallery/" userid)}
    (image (thumb-url userid name))
    userid "'s gallery"]])

(defn show-galleries []
  (map gallery-link (db/get-gallery-previews)))

(defroutes gallery-routes
  (GET "/gallery/:userid" [userid]
       (layout/common
;;        (include-js "/js/gallery-cljs.js")
        (display-gallery userid))))


