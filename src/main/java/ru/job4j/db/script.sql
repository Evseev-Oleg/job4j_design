create table car(
   id serial primary key,
   name varchar(255),
   release date,
   area text);
select * from car;
insert into car(name, release, area) values('Skoda','2021.01.15','Czech');
update car set release = '2021.05.20';
delete from car;