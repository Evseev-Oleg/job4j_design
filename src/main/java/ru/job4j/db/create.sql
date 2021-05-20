create table role(
	id serial primary key,
	type_role varchar(250)
);

create table users(
	id serial primary key,
	name varchar(250),
	role_id int REFERENCES role(id)
);

create table rules(
	id serial primary key,
	rules_type varchar(250)
);
CREATE TABLE role_rule (
	id serial primary key,
    role_id int REFERENCES role(id),
    rule_id int REFERENCES rules(id)
);

create table category(
	id serial primary key,
	name varchar(250)
);

create table state(
	id serial primary key,
	name varchar(250)
);

create table item(
	id serial primary key,
	name varchar(250),
	users_id int REFERENCES users(id),
	category_id int REFERENCES category(id),
	state_id int REFERENCES state(id)
);

create table comments(
	id serial primary key,
	name varchar(250),
	item_id int REFERENCES item(id)
);

create table attachs(
	id serial primary key,
	name varchar(250),
	item_id int REFERENCES item(id)
);