(ns app.nav.events
  (:require
   [app.helpers :refer [close-modal]]
   [app.router :as router]
   [app.spec :refer [check-spec-interceptor]]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [re-frame.core :refer [path reg-event-db reg-event-fx reg-fx]]))

(def nav-interceptors [check-spec-interceptor
                       (path :nav)])
                       

(reg-fx
  :navigate-to
  (fn-traced [{:keys [path]}]
    (router/set-token! path)))

(reg-event-fx 
  :route-changed
  nav-interceptors
  (fn-traced [{nav :db} [_ {:keys [handler route-params]}]]
    (let [nav (assoc nav :active-page handler)]
      (case handler
        :recipes {:db nav
                  :dispatch [:get-recipes]}
        :recipe {:db (assoc nav :active-recipe (keyword (:recipe-id route-params)))
                 :dispatch [:get-recipes]}
        ;; :inboxes {:db nav
        ;;           :dispatch [:get-inboxes]}
        :inbox  {:db (assoc nav :active-inbox (keyword (:inbox-id route-params)))}
        
        {:db (dissoc nav :active-recipe :active-inbox)}))))

(reg-event-db
  :set-active-nav
  nav-interceptors
  (fn-traced [nav [_ active-nav]]
    (assoc nav :active-nav active-nav)))

(reg-event-db
  :set-active-page
  nav-interceptors
  (fn-traced [nav [_ active-page]]
    (assoc nav :active-page active-page)))

(reg-event-db
  :close-modal
  (fn-traced [db _]
    (close-modal db)))

(reg-event-db
  :open-modal
  nav-interceptors
  (fn-traced [nav [_ modal-name]]
    (assoc nav :active-modal modal-name)))
