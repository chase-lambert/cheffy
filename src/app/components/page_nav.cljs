(ns app.components.page-nav
  (:require
   ["@smooth-ui/core-sc" :refer [Box Button Typography]]
   ["styled-icons/fa-solid/ChevronLeft" :refer [ChevronLeft]]
   [app.router :as router]))


(defn page-nav [{:keys [left center right]}]
  [:> Box {:py 20
           :display "flex"
           :justify-content "space-between"
           :align-items "center"}
   [:> Box 
    (when left
      [:> Button {:as "a"
                  :my 20
                  :variant "light"
                  :aria-label "Back"
                  :href (router/path-for left)}
       [:> ChevronLeft {:size 16}]])]
   [:> Box 
    (if (string? center)
      [:> Typography {:variant "h2"
                      :py 20
                      :font-weight 700}
       center]
      center)]
    
   [:> Box 
    (when right
      right)]])
