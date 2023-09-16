
create table users
(
 id bigint not null primary key,
 login varchar(255) not null,
 password varchar(255) not null unique
);
