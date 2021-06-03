create table camp (
    id serial primary key,
    squads varchar(50),
    number int
);

create table childrens (
    id serial primary key,
	lastname varchar(55),
    firstname varchar(55),
    number_id int references camp(id)
);

insert into camp(squads, number) values ('pionner', 1);
insert into camp(squads, number) values ('monkeys', 2);
insert into camp(squads, number) values ('hooligans', 3);

insert into childrens(lastname, firstname, number_id) values('Boris', 'Ivanov', 1);
insert into childrens(lastname, firstname, number_id) values('Ivan', 'Petrov', 1);
insert into childrens(lastname, firstname, number_id) values('Kiril', 'Sidorov', 2);
insert into childrens(lastname, firstname, number_id) values ('Marina', 'Rachina', 2);
insert into childrens(lastname, firstname, number_id) values ('Pers', 'Persov', 3);
insert into childrens(lastname, firstname) values ('Alexander', 'Pushkin');

select * from childrens join camp as c on childrens.number_id = c.id;
select * from camp as cc join children as c on cc.id = c.number_id;

select c.squads as отряд, ch.lastname as имя, ch.firstname as фамилия
from childrens ch join camp as c on ch.number_id = c.id;