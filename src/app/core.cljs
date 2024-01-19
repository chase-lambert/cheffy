(ns app.core
  (:require
   ["@smooth-ui/core-sc" :refer [Col Grid Normalize Row ThemeProvider]] 
   ;; -- auth --
   [app.auth.events]
   [app.auth.subs]
   [app.auth.views.log-in  :refer [log-in]]
   [app.auth.views.profile :refer [profile]]
   [app.auth.views.sign-up :refer [sign-up]] 
   ;; -- become-a-chef --
   [app.become-a-chef.views.become-a-chef :refer [become-a-chef]]
   [app.become-a-chef.views.events] 
   ;; -- errors --
   [app.errors.events]
   [app.errors.subs]
   ;; -- inboxes --
   [app.db]
   [app.inbox.events] 
   ;; -- nav --
   [app.inbox.subs]
   [app.inbox.views.inbox-page :refer [inbox-page]]
   [app.inbox.views.inboxes-page :refer [inboxes-page]]
   [app.nav.events]
   [app.nav.subs]
   [app.nav.views.nav :refer [nav]] 
   ;; -- recipes
   [app.recipes.events]
   [app.recipes.subs]
   [app.recipes.views.recipe-page :as recipe-page]
   [app.recipes.views.recipes-page :refer [recipes-page]]
   [app.recipes.views.saved-page :refer [saved-page]]
   [app.router :as router] 
   ;; -- auth --
   [app.theme :refer [cheffy-theme]]
   [re-frame.core :as rf]
   [reagent.dom :as rdom]))

(defn pages [page-name]
  (case page-name
    :profile       [profile]
    :log-in        [log-in]
    :sign-up       [sign-up]
    :become-a-chef [become-a-chef]
    :inbox         [inbox-page]
    :inboxes       [inboxes-page]
    :recipes       [recipes-page]
    :recipe        [recipe-page/recipe-page]
    :saved         [saved-page]
    [recipes-page]))

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

(comment 
  (js/alert "working")
  (day8.re-frame-10x/show-panel! true)
  ,)
