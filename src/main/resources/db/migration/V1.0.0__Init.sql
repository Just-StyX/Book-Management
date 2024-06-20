--create schema if not exists bookmanagement;
--
--CREATE TABLE IF NOT EXISTS bookmanagement.users(
--	id varchar primary key not null,
--	username varchar not null,
--	password varchar not null,
--	enabled int default(1)
--);
--
--CREATE TABLE IF NOT EXISTS bookmanagement.books(
--	id varchar primary key not null,
--	title varchar not null,
--	author varchar not null,
--	isbn varchar not null,
--	edition varchar not null
--);
--
--CREATE TABLE IF NOT EXISTS bookmanagement.reviews(
--	id varchar primary key not null,
--	content text not null,
--	rating int not null,
--	username varchar not null,
--	book_id varchar references books(id)
--);
--
--CREATE TABLE IF NOT EXISTS bookmanagement.tags(
--	id varchar primary key not null,
--	tag_name varchar not null
--);
--
--CREATE TABLE IF NOT EXISTS bookmanagement.authority(
--	id varchar primary key not null,
--	authority varchar not null
--);
--
--CREATE TABLE IF NOT EXISTS bookmanagement.book_tags(
--	book_id varchar references books(id),
--	tag_id varchar references tags(id)
--);
--
--CREATE TABLE IF NOT EXISTS bookmanagement.user_authority(
--	user_id varchar references users(id),
--	authority_id varchar references authority(id)
--);
--
--CREATE TABLE IF NOT EXISTS bookmanagement.user_books(
--	user_id varchar references users(id),
--	book_id varchar references books(id)
--);