create table medics
(
 id bigint not null primary key,
 name varchar(255) not null,
 email varchar(255) not null unique,
 document varchar(255) not null unique,
 specialicity varchar(255) not null,
 street varchar(255),
 distrit varchar(255) not null,
 compliment varchar(255),
 number varchar(255),
 city varchar(255) not null
)