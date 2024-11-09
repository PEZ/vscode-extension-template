(ns vsc-et.extension
  (:require [vsc-et.extension.db :as db]
            [vsc-et.extension.life-cycle :as lc]
            [vsc-et.extension.life-cycle-helpers :as lc-helpers]
            [vsc-et.shadow-reload-helpers :as reload-helpers]))

;;;;; Extension activation entry point

(defn ^:export activate [context]
  (lc/activate! db/!app-db context))

(defn ^:export deactivate []
  (lc/deactivate! db/!app-db))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn ^{:dev/after-load true} after-load []
  (println "shadow-cljs has compiled any changed files and reloaded their namespaces"))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var
                      :inline-def]}
(defn build-notify!
  [build-info]
  (when (= :build-complete (:type build-info))
    (def build-info build-info)
    (when-let [compiled (-> build-info :info :compiled)]
      (def compiled compiled)
      (when (reload-helpers/call-activate? compiled)
        (println "shadow-cljs hot-reload: vsc_et/extension/life_cycle.cljs changed, reactivating the extension...")
        (lc-helpers/cleanup! db/!app-db)
        (lc/activate! db/!app-db nil)))))