(ns vsc-et.extension
  (:require ["vscode" :as vscode]
            [vsc-et.commands :as commands]
            [vsc-et.db :as db]
            [vsc-et.when-contexts :as when-contexts]))

;;;;; Extension lifecycle helper functions
;; These also assist with managing `vscode/Disposable`s in a hot-reloadable way.

(defn- push-disposable! [!state ^js context ^js disposable]
  (swap! !state update :extension/disposables conj disposable)
  (.push (.-subscriptions context) disposable))

(defn- clear-disposables! [!state]
  (doseq [^js disposable (:extension/disposables @!state)]
    (.dispose disposable))
  (swap! !state assoc :extension/disposables []))

(defn- register-command! [!state ^js ^js context command-id var]
  (push-disposable! !state context (vscode/commands.registerCommand command-id var)))

(defn- cleanup! []
  (when-contexts/set-context!+ db/!app-db :vsc-et/active? false)
  (clear-disposables! db/!app-db))

;;;;; Extension API
;; The extension commands are often enough of API, but sometimes you need more.

(def extension-api #js {:v1 {}})

;;;;; Extension activation entry point

(defn ^:export activate [context]
  (println "Extension Template activate START")
  (when context
    (swap! db/!app-db assoc
           :extension/context context
           :workspace/root-path (some-> vscode/workspace.workspaceFolders first)))
  (try (let [{:keys [extension/context]} @db/!app-db]
         (register-command! db/!app-db context "vsc-et.hello" #'commands/hello!+)
         (register-command! db/!app-db context "vsc-et.newHelloDocument" #'commands/new-untitled-hello-document!+)
         (when-contexts/set-context!+ db/!app-db :vsc-et/active? true))
       (catch :default e
         (vscode/window.showErrorMessage (str "Extension Template activation failed: "
                                              (.-message e)
                                              ", see Development Console for stack trace"))
         (throw e))
       (finally
        (println "Extension Template activate END")))
  extension-api)

;;;;; Extension deactivation entry point

(defn ^:export deactivate []
  (cleanup!))

;;;;; shadow-cljs hot reload hooks

(defn ^{:dev/before-load-async true
        :export true} before [done]
  (js/console.debug "hot-reload: before reloading")
  (done))

(defn ^{:dev/after-load-async true
        :export true} after [done]
  (js/console.debug "hot-reload: after reloaded")
  (done))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn build-notify!
  [build-info]
  (when (= :build-complete (:type build-info))
    (when-let [compiled (-> build-info :info :compiled)]
      (when (compiled [:shadow.build.classpath/resource "vsc_et/extension.cljs"])
        (println "hot-reload: vsc_et/extension.cljs reloaded, reactivating the extension...")
        (cleanup!)
        (activate nil)))))