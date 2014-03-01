(ns guestbook.routes.home
  (:require [compojure.core :refer :all]
            [guestbook.views.layout :as layout]
            [hiccup.form :refer :all]
            [noir.session :as session]
            [guestbook.models.db :as db]
            ))

(defn format-time [timestamp]
  (-> "yyyy/MM/dd hh:mm"
      (java.text.SimpleDateFormat.)
      (.format timestamp)))

(defn show-guests []
  [:ul.guests
   (for [{:keys [message name timestamp]} (db/read-guests)]
     [:li
      [:blockquote message]
      [:p "- " [:cite name]]
      [:time (format-time timestamp)]])])

(defn home [& [name message error]]
  (layout/common
   [:h1 "Guest book [ " (session/get :user) " ]"]
   [:p "Welcome to my guestbook."]
   [:p error]
   (show-guests)
   [:hr]
   (form-to [:post "/"]
            [:p "Name:"]
            (text-field "name" name)
            [:p "Message"]
            (text-area {:rows 10 :cols 40} "message" message)
            [:br]
            (submit-button "comment"))))

(defn save-message [name message]
  (cond
   (empty? name)
   ,,(home name message "some dummy forgot to leave name")
   (empty? message)
   ,,(home name message "Don't you have samething to say?")
   :else
   ,,(do
       (db/save-message name message)
       (home))))

(defroutes home-routes
  (GET "/" [] (home))
  (POST "/" [name message] (save-message name message)))
