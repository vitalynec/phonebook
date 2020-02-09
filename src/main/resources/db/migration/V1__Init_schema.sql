drop table user;
drop table phone;

create table user
(
    id   int(5) not null auto_increment primary key,
    name text
);

create table phone
(
    id      int(5) not null auto_increment primary key,
    user_id int(5),
    number  text,
    foreign key (user_id) references user (id)
);

