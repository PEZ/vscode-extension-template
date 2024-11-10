(ns vsc-et.extension
  (:require ["vscode" :as vscode]
            [vsc-et.hellos :as hellos]
            [vsc-et.extension.db :as db]
            [vsc-et.extension.life-cycle-helpers :as lc-helpers]
            [vsc-et.extension.when-contexts :as when-contexts]))

;;;;; Extension activation entry point

(defn ^:export activate [context]
  (println "Extension Template activate START")
  (when context
    (swap! db/!app-db assoc
           :extension/context context))
  (try (let [{:keys [extension/context]} @db/!app-db]
         (lc-helpers/register-command! db/!app-db context "vsc-et.hello" #'hellos/hello-command!+)
         (lc-helpers/register-command! db/!app-db context "vsc-et.newHelloDocument" #'hellos/new-hello-doc-command!+)
         (when-contexts/set-context!+ db/!app-db :vsc-et/active? true))
       (catch :default e
         (vscode/window.showErrorMessage (str "Extension Template activation failed: "
                                              (.-message e)
                                              ", see Development Console for stack trace"))
         (throw e))
       (finally
         (println "Extension Template activate END")))
  #js {:v1 {}})

(comment
  ;; When you have updated the activate function, cleanup and call activate again
  ;; NB: If you have updated the extension manifest, you will need to restart the extension host instead
  (lc-helpers/cleanup! db/!app-db)
  (activate nil)
  :rcf)

;;;;; Extension deactivation entry point

(defn ^:export deactivate []
  (lc-helpers/cleanup! db/!app-db))


;;;;; shadow-cljs hot reload hooks
;; We don't need to do anything here, but it is nice to see that reloading is happening

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn ^{:dev/before-load true} before-load []
  (println "shadow-cljs reloading..."))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn ^{:dev/after-load true} after-load []
  (println "shadow-cljs reload complete"))
