--1----------------------
create table departments (
    id serial primary key,
    name varchar(255)
);

create table emploees (
    id serial primary key,
	name varchar(255),
    number_id int references departments(id)
);

INSERT INTO departments(name)
VALUES ('department1'), ('department2'), ('department3');
INSERT INTO departments(name)
VALUES ('department4');

insert into emploees(name, number_id) values('Boris', 1);
insert into emploees(name, number_id) values('Ivan', 1);
insert into emploees(name, number_id) values('Kiril', 2);
insert into emploees(name, number_id) values ('Marina', 2);
insert into emploees(name, number_id) values ('Pers', 3);
insert into emploees(name, number_id) values ('Alexander', 3);
insert into emploees(name, number_id) values ('Alexander', null);

--2----------------------
SELECT * FROM emploees e LEFT JOIN departments d ON e.number_id = d.id;
SELECT * FROM departments d RIGHT JOIN emploees e ON d.id = e.number_id;
SELECT * FROM departments d FULL JOIN emploees e ON d.id = e.number_id;
SELECT * FROM departments d CROSS JOIN emploees e;

--3----------------------
SELECT * FROM departments d LEFT JOIN emploees e ON d.id = e.number_id WHERE e.name is null;

--4----------------------
SELECT * FROM emploees e LEFT JOIN departments d ON e.number_id = d.id;
SELECT * FROM departments d RIGHT JOIN emploees e ON d.id = e.number_id;

--5----------------------
CREATE TABLE teens(
	id SERIAL PRIMARY KEY,
    name VARCHAR (20),
    gender CHAR (1)
);

INSERT INTO teens(name, gender)
VALUES ('Jon', 'M'), ('Kate', 'Ж'), ('Tim', 'M'), ('Jessika', 'Ж'), ('Man', 'M'), ('Mari', 'Ж');

SELECT t1.name, t2.name FROM teens t1 CROSS JOIN teens t2
WHERE t1.gender <> t2.gender;