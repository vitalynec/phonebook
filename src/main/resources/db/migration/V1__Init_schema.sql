drop table if exists "users";
drop table if exists "phones";

create table users
(
    id     uuid default random_uuid() primary key,
    "name" text
);

create table phones
(
    id      uuid default random_uuid() primary key,
    user_id uuid,
    number  text
);

