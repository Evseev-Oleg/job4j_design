CREATE DATABASE car_table;

CREATE TABLE bodywork(
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE engine(
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE transmission(
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE car(
    id serial PRIMARY KEY,
    name varchar(255),
	id_bodywork int REFERENCES bodywork(id),
	id_engine int REFERENCES engine(id),
	id_transmission int REFERENCES transmission(id)
);

INSERT INTO bodywork (name)
     VALUES ('Body BMW'), ('Body Honda'), ('Body Lada'),
	 ('Body Opel');

INSERT INTO engine(name)
     VALUES ('engine BMW'), ('engine Honda'), ('engine Lada'),
	 ('engine Opel');

INSERT INTO transmission(name)
     VALUES ('transmission BMW'), ('transmission Honda'), ('transmission Lada'),
	 ('transmission Opel');

INSERT INTO car(name, id_bodywork, id_engine, id_transmission)
     VALUES ('BMW', 1, 1, 1), ('Honda', 2, 2, 2), ('Lada', 3, 3, 3),
	 ('Opel', 4, 4, 4);

INSERT INTO bodywork(name)
VALUES ('Body Audi'), ('Body Shkoda'), ('Body UAZ');

INSERT INTO engine (name)
VALUES ('Engine Audi'), ('Engine Shkoda'), ('Engine UAZ');

INSERT INTO transmission(name)
VALUES ('Transmission Shkoda'), ('Transmission Audi'), ('Transmission UAZ');

--Вывести список всех машин и все привязанные к ним детали.
SELECT c.name, b.name, e.name, t.name FROM car AS c
     JOIN bodywork AS b ON b.id = c.id_bodywork
	 JOIN engine AS e ON e.id = c.id_engine
	 JOIN transmission AS t ON t.id = c.id_transmission;

-- Вывести отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине, кузова, двигатели, коробки передач.
SELECT b.name FROM bodywork AS b
   LEFT JOIN car AS c ON b.id = c.id_bodywork
   WHERE c.id_bodywork IS NULL;

SELECT e.name FROM engine AS e
   LEFT JOIN car AS c ON e.id = c.id_engine
   WHERE c.id_engine IS NULL;

 SELECT t.name FROM transmission AS t
   LEFT JOIN car AS c ON t.id = c.id_transmission
   WHERE c.id_transmission IS NULL;