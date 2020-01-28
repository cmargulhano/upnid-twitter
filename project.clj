(defproject upnid-twitter "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.5.0"]
                 [korma "0.4.3"]
                 [mysql/mysql-connector-java "5.1.6"]
                 [metosin/compojure-api "2.0.0-alpha30"]]
  :ring {:handler upnid-twitter.handler/app}
  :plugins [[lein-ring "0.12.5"]
            [clj-sql-up "0.3.7"]]

  :clj-sql-up {:database {:subprotocol "mysql"
                          :subname ~(get (System/getenv) "DATABASE_URL")
                          :user ~(get (System/getenv) "DATABASE_USER")
                          :password ~(get (System/getenv) "DATABASE_PASSWORD")}
               :deps [[mysql/mysql-connector-java "5.1.6"]]}

  :uberjar-name "server.jar"
  :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]
                                  [ring/ring-mock "0.3.2"]]
                   :plugins      [[lein-ring "0.12.5"]]}})
