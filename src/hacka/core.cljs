(ns hacka.core
  (:require [reagent.core :as r :refer [atom]]))

(enable-console-print!)

(println "HEJ")
(def test-text "Hejsan")
(println test-text)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!" :lista '()}))

(println app-state)
(println (deref app-state))
(println @app-state)

(defonce tal (atom 0))
(println (swap! tal inc))

(def talet (atom 0))
(println (swap! talet inc))

(defn hej-världen [namn]
  [:h1 "Hej " namn "!"]
  )

(defn textfält [väg]
  [:input {:type "text"
           :value (väg @app-state)
           :on-change #(swap! app-state assoc väg (-> % .-target .-value))}]
  )

(defn sida []
  [:div
   [hej-världen "Adam"]
   [hej-världen @app-state ]
   [:ul (for [item (:lista @app-state)]
          [:li item])]
   [:p "Viktig text"]
   [:button {:on-click (fn [] (js/alert "hejsan"))} "hej"]
   [:br]
   [textfält :text]
   [:br]
   [:input {:type "button"
            :value "tryck på mig"
            :on-click (fn [] (swap! app-state update :lista #(conj % (:text @app-state))))}]
   ;;		 :on-click (fn [] (swap! app-state #(assoc % :lista (-> % :lista (conj "hej")))))}]
   ]
  )

(def hej (fn [] "hej"))
(defn hej2 [] "hej")

(r/render [sida] (.getElementById js/document "app"))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
