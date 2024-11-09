(ns vsc-et.extension.life-cycle
  (:require ["vscode" :as vscode]
            [vsc-et.extension.commands :as commands]
            [vsc-et.extension.api :as api]
            [vsc-et.extension.life-cycle-helpers :as helpers]
            [vsc-et.extension.when-contexts :as when-contexts]))

(defn activate! [!state context]
  (println "Extension Template activate START")
  (when context
    (swap! !state assoc
           :extension/context context
           :workspace/root-path (some-> vscode/workspace.workspaceFolders first)))
  (try (let [{:keys [extension/context]} @!state]
         (helpers/register-command! !state context "vsc-et.hello" #'commands/hello!+)
         (helpers/register-command! !state context "vsc-et.newHelloDocument" #'commands/new-untitled-hello-document!+)
         (when-contexts/set-context!+ !state :vsc-et/active? true))
       (catch :default e
         (vscode/window.showErrorMessage (str "Extension Template activation failed: "
                                              (.-message e)
                                              ", see Development Console for stack trace"))
         (throw e))
       (finally
        (println "Extension Template activate END")))
  api/extension-api)

;;;;; Extension deactivation entry point

(defn deactivate! [!state]
  (helpers/cleanup! !state))