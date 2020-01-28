(ns upnid-twitter.user
  (:require [korma.db :refer :all]
            [korma.core :refer :all]
            [upnid-twitter.db :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema UserBody
  {
   :name s/Str
   })

(s/defschema User
  {
   :id   s/Int
   :name s/Str
   })

(defentity user)

(defn create [user_data]
  (let [name (get-in user_data [:name])
        db-resp (insert user (values {:name name}))]
    {:id   (get-in db-resp [:generated_key])
     :name name}))

(defn find-by-id [id]
  (first (select user
                 (where {:id id}))))

(defn find-all []
  (select user))

(defn update-by-id [id user_data]
  (let [name (get-in user_data [:name])]
    (update user
            (set-fields {:name name})
            (where {:id id}))
    {:id   id
     :name name}))

(defn delete-by-id-ok [id]
  (delete user
          (where {:id id}))
  (ok))

(defn delete-by-id [id]
  (let [db-resp-find (find-by-id id)]
    (if (nil? db-resp-find)
      (not-found)
      (delete-by-id-ok id))
    )
  )