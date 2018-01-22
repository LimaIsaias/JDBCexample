# JDBCexample
Here is an example to show you how to connect to PostgreSQL database with JDBC driver.

create schema contacts;
CREATE SEQUENCE contacts.contact_id_seq;
CREATE TABLE contact (
id INTEGER NOT NULL DEFAULT nextval('contacts.contact_id_seq'),
name varchar(50) null ,
phone varchar(50) null,
email varchar(50) null, 
dt_cad date null,
obs text,
primary key(id)
);
