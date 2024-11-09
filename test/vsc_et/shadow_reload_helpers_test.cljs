(ns vsc-et.shadow-reload-helpers-test
  (:require [vsc-et.shadow-reload-helpers :as sut]
            [clojure.test :refer [deftest is testing]]))

(deftest reactivate
  (testing "No, when Life-cycle file is not reloaded"
    (is (= false
           (sut/call-activate? #{[:shadow.build.classpath/resource "vsc_et/extension.cljs"]
                                 [:shadow.build.targets.node-library/helper "shadow/umd_helper.cljs"]}))))
  (testing "No, when life-cycle file is reload but the extension.cljs file is not"
    (is (= false
           (sut/call-activate? #{[:shadow.build.classpath/resource "vsc_et/commands.cljs"]
                                 [:shadow.build.classpath/resource "vsc_et/extension/life_cycle.cljs"]}))))
  (testing "No, when both life-cycle and extension.cljs files are not reloaded together, but also some other file"
    (is (= false
           (sut/call-activate? #{[:shadow.build.classpath/resource "vsc_et/commands.cljs"]
                                 [:shadow.build.classpath/resource "vsc_et/extension.cljs"]
                                 [:shadow.build.classpath/resource "vsc_et/extension/life_cycle.cljs"]}))))
  (testing "Yes, when both life-cycle and extension.cljs files are reloaded together, and no other file"
    (is (= true
           (sut/call-activate? #{[:shadow.build.classpath/resource "vsc_et/extension.cljs"]
                                 [:shadow.build.classpath/resource "vsc_et/extension/life_cycle.cljs"]}))))
  (testing "Yes, when both life-cycle and extension.cljs files are reloaded together, if other files are not from the project"
    (is (= true
           (sut/call-activate? #{[:shadow.build.targets.node-library/helper "shadow/umd_helper.cljs"]
                                 [:shadow.build.classpath/resource "vsc_et/extension.cljs"]
                                 [:shadow.build.classpath/resource "vsc_et/extension/life_cycle.cljs"]})))))
