(ns app.become-a-chef.views.events
  (:require
   [app.spec :refer [check-spec-interceptor]]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [re-frame.core :refer [reg-event-fx]]))

(reg-event-fx
  :agree-to-cook
  [check-spec-interceptor]
  (fn-traced [{:keys [db]} _]
    (let [uid (get-in db [:auth :uid])]
      {:db (assoc-in db [:users uid :role] :chef)
       :dispatch-n [[:close-modal]
                    [:set-active-nav :recipes]
                    [:set-active-page :recipes]]
       :navigate-to {:path "/recipes"}})))
