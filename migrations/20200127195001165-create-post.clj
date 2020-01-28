;; migrations/20200127195001165-create-post.clj

(defn up []
  ["create table post (id int not null auto_increment, user_id int not null, content varchar (255), created_at timestamp not null default current_timestamp, update_at timestamp not null default current_timestamp on update current_timestamp, constraint pk_post_id primary key (id), constraint fk_posts_id_ft foreign key (user_id) references user(id))"])

(defn down []
  ["drop table post"])
