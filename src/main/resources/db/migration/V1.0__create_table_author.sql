-- create table author
CREATE TABLE if not exists author (
	id serial not null primary key,
	name varchar(100) not null,
	nationality varchar(30) not null
);