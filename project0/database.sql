drop table if exists customers;
CREATE table customers (
	c_id serial4 primary key,
	c_first_name varchar(40) NOT NULL,
	c_last_name varchar(40) NOT NULL,
	c_email varchar(40) NOT NULL,
	c_password varchar(40) NOT NULL,
	c_logged_in bool NULL
);

insert into customers (c_first_name, c_last_name, c_email, c_password) values ('Test', 'Customer', 'tc@mail.com', 'helloworld');

drop table if exists employees;
CREATE TABLE employees (
	e_id int4 primary key,
	e_first_name varchar(40) NOT NULL,
	e_last_name varchar(40) NOT NULL,
	e_email varchar(40) NOT NULL,
	e_password varchar(40) NOT NULL
);

insert into employees (e_id, e_first_name, e_last_name, e_email, e_password) values ('123','Jeremy', 'S', 'js@mail.com', 'helloworld');
insert into employees (e_id, e_first_name, e_last_name, e_email, e_password) values ('987','Boss', 'Man', 'boss@mail.com', 'helloworld');


drop table if exists skis;
CREATE TABLE skis (
	s_id serial primary key,
	s_brand varchar(40) NOT NULL,
	s_model varchar(40) NOT NULL,
	s_price numeric NOT NULL,
	s_in_stock int4 NOT NULL
);

drop table if exists orders;
CREATE TABLE orders (
	o_id serial4 primary key,
	o_status varchar(40) NOT NULL,
	o_customer_id int4 NOT NULL,
	o_purchase_date date NOT NULL
);


drop table if exists order_items;
create table if not exists order_items (
	oi_id serial4 primary KEY,
	oi_order_id int4 NOT NULL,
	oi_ski_id int4 NOT NULL,
	oi_quantity int4 NOT NULL,
	oi_status varchar(40) not null
);


