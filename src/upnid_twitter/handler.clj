(ns upnid-twitter.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [upnid-twitter.post :as post]
            [upnid-twitter.user :as user]
            [schema.core :as s]))

(def app
  (api
    {:swagger
     {:ui   "/"
      :spec "/swagger.json"
      :data {:info {:title       "Upnid Twitter"
                    :description "Upnid's technical challenge"}
             :tags [{:name "api", :description "Twitter"}]}}}

    (context "/twitter" []
      :tags ["twitter"]

      (POST "/users" []
        :return user/User
        :body [user user/UserBody]
        :summary "Creates a user"
        (ok (user/create user)))

      (GET "/users" []
        :return [user/User]
        :summary "Finds all users"
        (ok (user/find-all)))

      (PATCH "/users/:user_id" []
        :return user/User
        :path-params [user_id :- s/Int]
        :body [user user/UserBody]
        :summary "Updates a user"
        (ok (user/update-by-id user_id user)))

      (DELETE "/users/:id" []
        :path-params [id :- s/Int]
        :summary "Deletes a user"
        (user/delete-by-id id))

      (POST "/users/:user_id/posts" []
        :return post/Tweet
        :path-params [user_id :- s/Int]
        :body [tweet post/TweetBody]
        :summary "Creates a post"
        (ok (post/create user_id tweet)))

      (GET "/users/:user_id/posts" []
        :return [post/Tweet]
        :path-params [user_id :- s/Int]
        :summary "Gets post by user id"
        (post/find-by-user-id user_id))

      (DELETE "/users/:user_id/posts/:post_id" []
        :path-params [user_id :- s/Int
                      post_id :- s/Int]
        :summary "Deletes a post"
        (post/delete-by-id user_id post_id))

      (GET "/posts/:id" []
        :return post/Tweet
        :path-params [id :- s/Int]
        :summary "Gets post by id"
        (ok (post/find-by-id id)))

      (GET "/posts" []
        :return [post/Tweet]
        :summary "Find all posts"
        (ok (post/find-all)))

      )
    )
  )

