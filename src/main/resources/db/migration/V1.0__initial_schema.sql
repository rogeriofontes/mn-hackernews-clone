--create schema mn;
CREATE TABLE tb_user (
	id SERIAL CONSTRAINT pk_id_user PRIMARY KEY,
	username VARCHAR(60) NOT NULL,
	password VARCHAR(60) NOT NULL,
	role VARCHAR(60) NOT NULL,
	token1 VARCHAR(100)
);

CREATE TABLE tb_person (
	id SERIAL CONSTRAINT pk_id_person PRIMARY KEY,
	name VARCHAR(60),
	age integer NOT NULL
);