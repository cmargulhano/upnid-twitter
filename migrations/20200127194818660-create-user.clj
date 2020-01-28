;; migrations/20200127194818660-create-user.clj

(defn up []
  ["create table user (id int not null auto_increment, name varchar (100) not null, constraint pk_user_id primary key (id))"])

(defn down []
  ["drop table user"])
