create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people(name) values ('Аня');
insert into people(name) values ('Ваня');
insert into people(name) values ('Боря');

insert into devices(name, price) values ('device1', 2000.0);
insert into devices(name, price) values ('device2', 6000.0);
insert into devices(name, price) values ('device3', 10000.0);

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (3, 2);
insert into devices_people(device_id, people_id) values (3, 3);

select avg(price) from devices;

SELECT p.name,avg(d.price) FROM people as p
	JOIN devices_people as dp on p.id = dp.people_id
	JOIN devices as d on dp.device_id = d.id
GROUP BY p.name;

SELECT p.name,AVG(d.price) FROM people AS p
	JOIN devices_people AS dp ON p.id = dp.people_id
	JOIN devices AS d ON dp.device_id = d.id
GROUP BY p.name
HAVING AVG(d.price) > 5000;