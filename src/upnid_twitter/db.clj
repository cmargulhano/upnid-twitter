(ns upnid-twitter.db
  (:use korma.db))

(defdb db (mysql
            { :classname "com.mysql.jdbc.Driver"
             :subprotocol "mysql"
             :subname (get (System/getenv) "DATABASE_URL")
             :user (get (System/getenv) "DATABASE_USER")
             :password (get (System/getenv) "DATABASE_PASSWORD")}))
