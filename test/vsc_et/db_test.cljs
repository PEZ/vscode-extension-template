;; You can't unit test code that use the VSCode API
;; (But check out the e2e test runner)
;;
;; To make your business logic testable, factor it out in namespaces
;; that don't require "vscode".
;;
;; This extension template does not have any business logic, but the db
;; namespace at least does not require "vscode".

(ns vsc-et.db-test
  (:require [vsc-et.db :as sut]
            [clojure.test :refer [deftest is testing]]))

(deftest init-db
  (testing "init-db"
    (is (= {:vsc-et/active? false}
           (:extension/when-contexts sut/init-db)))))