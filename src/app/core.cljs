(ns app.core
  (:require 
    [reagent.dom   :as rdom]
    [re-frame.core :as rf]
    [app.db]
    [app.router    :as router]

    ;; -- auth --
    [app.auth.views.log-in  :refer [log-in]]
    [app.auth.views.profile :refer [profile]]
    [app.auth.views.sign-up :refer [sign-up]]
    [app.auth.events]
    [app.auth.subs]
    ;; -- become-a-chef --
    [app.become-a-chef.views.become-a-chef :refer [become-a-chef]]
    ;; -- inboxes --
    [app.inbox.views.inboxes :refer [inboxes]]
    ;; -- nav --
    [app.nav.events]
    [app.nav.subs]
    [app.nav.views.nav :refer [nav]]
    ;; -- recipes
    [app.recipes.views.recipes :refer [recipes]]

    [app.theme :refer [cheffy-theme]]
    ["@smooth-ui/core-sc" :refer [Button Col Grid Normalize Row ThemeProvider]]))
    

(defn pages [page-name]
  (case page-name 
    :profile       [profile]
    :log-in        [log-in]
    :sign-up       [sign-up]
    :become-a-chef [become-a-chef]
    :inboxes       [inboxes]
    :recipes       [recipes]
    [recipes]))

(defn app []
  (let [active-page @(rf/subscribe [:active-page])]
    [:<>
     [:> Normalize]
     [:> ThemeProvider {:theme cheffy-theme}
       [:> Grid {:fluid false}
        [:> Row 
         [:> Col 
          [nav]
          [pages active-page]]]]]]))

(defn ^:dev/after-load start []
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export init []
  (router/start!)
  (rf/dispatch-sync [:initialize-db])
  (start))
