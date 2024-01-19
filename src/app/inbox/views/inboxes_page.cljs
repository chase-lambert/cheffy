(ns app.inbox.views.inboxes-page
  (:require
   ["@smooth-ui/core-sc" :refer [Box]]
   [app.components.page-nav :refer [page-nav]]
   [app.inbox.views.inbox-card :refer [inbox-card]]
   [re-frame.core :as rf]))


(defn inboxes-page []
  (let [user-inboxes @(rf/subscribe [:user-inboxes])]
    [:> Box
      [page-nav {:center "Inbox"}]
      [:> Box {:class "cards"}
       (for [[k {:keys [id notifications updated-at]}] user-inboxes
             :let [notifications? (> notifications 0)]]
         ^{:key k}
         [inbox-card {:uid-inbox k 
                      :inbox-id  id
                      :notifications? notifications?
                      :notifications  notifications
                      :updated-at updated-at}])]]))
