(ns vsc-et.extension.life-cycle-helpers
  (:require ["vscode" :as vscode]
            [vsc-et.extension.when-contexts :as when-contexts]))

;;;;; Extension lifecycle helper functions
;; These also assist with managing `vscode/Disposable`s in a hot-reloadable way.

(defn push-disposable! [!state ^js context ^js disposable]
  (swap! !state update :extension/disposables conj disposable)
  (.push (.-subscriptions context) disposable))

(defn- clear-disposables! [!state]
  (doseq [^js disposable (:extension/disposables @!state)]
    (.dispose disposable))
  (swap! !state assoc :extension/disposables []))

(defn cleanup! [!state]
  (when-contexts/set-context!+ !state :vsc-et/active? false)
  (clear-disposables! !state))

(defn register-command! [!state ^js ^js context command-id var]
  (push-disposable! !state context (vscode/commands.registerCommand command-id var)))