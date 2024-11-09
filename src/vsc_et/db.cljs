(ns vsc-et.db)

(def init-db {:extension/context nil
              :extension/disposables []
              :workspace/root-path nil
              :extension/when-contexts {:vsc-et/active? false}})

(defonce !app-db (atom init-db))

(comment
  @!app-db
  :rcf)

