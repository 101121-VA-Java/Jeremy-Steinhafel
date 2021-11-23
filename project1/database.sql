drop table if exists ers_reimbursement;
drop table if exists ers_users;
drop table if exists ers_reimbursement_status;
drop table if exists ers_reimbursement_type;
drop table if exists ers_user_roles;

drop table if exists ers_user_roles;
create table if not exists ers_user_roles(
	ers_user_role_id serial primary key not null,
	user_role varchar(10) not null
);

drop table if exists ers_reimbursement_status;
create table if not exists ers_reimbursement_status (
	reimb_status_id serial primary key not null,
	reimb_status varchar(10) not null
);

drop table if exists ers_reimbursement_type;
create table if not exists ers_reimbursement_type(
	reimb_type_id serial primary key not null,
	reimb_type varchar(20) not null
);

drop table if exists ers_users;
create table if not exists ers_users(
ers_user_id serial primary key not null,
ers_username varchar(50) unique not null,
ers_password varchar(50) not null,
user_first_name varchar(100) not null,
user_last_name varchar(100) not null,
user_email varchar(150) unique not null,
user_role_id integer references ers_user_roles(ers_user_role_id) not null
);


drop table if exists ers_reimbursement;
create table if not exists ers_reimbursement (
	reimb_id serial primary key not null, 
	reimb_amount decimal not null,
	reimb_submitted timestamp not null,
	reimb_resolved timestamp null,
	reimb_description varchar(250) null,
	reimb_receipt bytea null,
	reimb_author integer references ers_users(ers_user_id) not null,
	reimb_resolver integer references ers_users(ers_user_id) null,
	reimb_status_id integer references ers_reimbursement_status(reimb_status_id) not null,
	reimb_type_id integer references ers_reimbursement_type(reimb_type_id) not null
);