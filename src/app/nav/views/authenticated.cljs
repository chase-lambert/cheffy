(ns app.nav.views.authenticated
  (:require 
    [app.nav.views.nav-item :refer [nav-item]]
    [re-frame.core :as rf]
    ["@smooth-ui/core-sc" :refer [Box]]))

(defn authenticated []
  (let [active-nav @(rf/subscribe [:active-nav])
        nav-items [{:id :saved
                    :name "Saved"
                    :href "#saved"
                    :dispatch #(rf/dispatch [:set-active-nav :saved])}
                   {:id :recipes
                    :name "Recipes"
                    :href "#recipes"
                    :dispatch #(rf/dispatch [:set-active-nav :recipes])}
                   {:id :inboxes
                    :name "Inbox"
                    :href "#inbox"
                    :dispatch #(rf/dispatch [:set-active-nav :inboxes])}
                   {:id :become-a-chef
                    :name "Chef"
                    :href "#become-a-chef"
                    :dispatch #(rf/dispatch [:set-active-nav :become-a-chef])}
                   {:id :profile
                    :name "Profile"
                    :href "#profile"
                    :dispatch #(rf/dispatch [:set-active-nav :profile])}]]
    [:> Box {:display "flex"
             :justify-content "flex-end"
             :py 1}
     (for [{:keys [id name href dispatch]} nav-items]
       ^{:key id}
       [nav-item {:id id
                  :name name
                  :href href
                  :dispatch dispatch
                  :active-nav active-nav}])]))

