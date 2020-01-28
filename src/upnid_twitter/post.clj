(ns upnid-twitter.post
  (:require [korma.db :refer :all]
            [korma.core :refer :all]
            [upnid-twitter.db :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema TweetBody
  {
   :content s/Str
   })

(s/defschema Tweet
  {
   :id         s/Int
   :content    s/Str
   :user_id    s/Int
   :created_at s/Inst
   :update_at  s/Inst
   })

(defentity post)

(defn find-all []
  (select post))

(defn find-by-id [id]
  (first (select post
                 (where {:id id}))))

(defn find-by-user-id [user_id]
  (ok (select post
          (where {:user_id user_id})))
  )

(defn create [user_id tweet]
  (let [content (get-in tweet [:content])
        db-resp-insert (insert post (values {:content content :user_id user_id}))
        db-resp-select (find-by-id (get-in db-resp-insert [:generated_key]))]
    {:id         (get-in db-resp-select [:id])
     :content    content
     :user_id    user_id
     :created_at (get-in db-resp-select [:created_at])
     :update_at  (get-in db-resp-select [:update_at])}))

(defn delete-by-id-ok [user_id post_id]
  (delete post
          (where {:id post_id :user_id user_id}))
  (ok))

(defn find-by-post-id-user-id [user_id post_id]
  (first (select post
                 (where {:id post_id :user_id user_id}))))

(defn delete-by-id [user_id post_id]
  (let [db-resp-find (find-by-post-id-user-id user_id post_id)]
    (if (nil? db-resp-find)
      (not-found)
      (delete-by-id-ok user_id post_id))
    )
  )