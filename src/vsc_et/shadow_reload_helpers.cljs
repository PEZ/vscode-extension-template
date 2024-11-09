(ns vsc-et.shadow-reload-helpers)

;; Only run the extension activate function if it is likely that it has changed
(defn call-activate? [compiled]
  (let [expected-entries #{[:shadow.build.classpath/resource "vsc_et/extension.cljs"]
                           [:shadow.build.classpath/resource "vsc_et/extension/life_cycle.cljs"]}
        filtered-entries (set (filter #(.startsWith (second %) "vsc_et/") compiled))]
    (= filtered-entries expected-entries)))


