(ns app.auth.views.log-in
  (:require
   ["@smooth-ui/core-sc" :refer [Box Button Col FormGroup Input Label Row]]
   [app.components.page-nav :refer [page-nav]]
   [app.components.form-group :refer [form-group]]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn log-in []
  (let [initial-values {:email ""
                        :password ""}
        values (r/atom initial-values)]
    (fn []
      [:> Row {:justify-content "center"}
       [:> Col {:xs 12 :sm 6}
        [page-nav {:center "Log In"}]
        [form-group {:id    :email 
                     :label "Email"
                     :type  "email"
                     :values values}]
        [form-group {:id :password
                     :label "Password"
                     :type "password"
                     :values values}]
        [:> Box {:display "flex"
                 :justify-content "space-between"}
         [:> Box {:py 1
                  :pr 2}
          [:a {:href "#sign-up"
               :on-click #(rf/dispatch [:set-active-nav :sign-up])}
           "New to Cheffy? Create an account!"]]
         [:> Box 
          [:> Button {:on-click #(rf/dispatch [:log-in @values])}
           "Log In"]]]]])))
          
