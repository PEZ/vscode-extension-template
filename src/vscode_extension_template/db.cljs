(ns vscode-extension-template.db)

(def init-db {:extension/context nil
              :extension/disposables []
              :workspace/root-path nil
              :extension/when-contexts {:vscode-extension-template/active? false}})

(defonce !app-db (atom init-db))

(comment
  @!app-db
  :rcf)

