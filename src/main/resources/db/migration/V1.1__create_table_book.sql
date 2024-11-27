-- create table book
CREATE TABLE if not exists book (
	id serial primary key not null,
	title varchar(50) not null,
	publication_date date not null,
	author_id int not null,
	constraint fk_book_author foreign key (author_id) references author(id)
);