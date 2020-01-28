(ns upnid-twitter.db
  (:use korma.db))

(defdb db (mysql
            { :classname "com.mysql.jdbc.Driver"
             :subprotocol "mysql"
             :subname "//localhost/upnid"
             :user "root"
             :password "root"}))
