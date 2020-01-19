CREATE TABLE film (
	id varchar(36) NOT NULL,
	genre varchar(256) NOT NULL,
	name varchar(256) NOT NULL,
	film_type varchar(256) NOT NULL DEFAULT 'REG'
	PRIMARY KEY (id)
);

CREATE TABLE rental (
	id varchar(36) NOT NULL,
	creditCost integer NOT NULL,
	customer_id UUID NOT NULL,
	films_id JsonB NOT NULL,
	rental_end date NOT NULL,
	rental_start date NOT NULL,
	rental_type varchar(256) NOT NULL DEFAULT 'REGULARFILMS',
	PRIMARY KEY (id)
);

CREATE TABLE customer (
	id varchar(36) NOT NULL,
	arquivo_cpf varchar(36),
	birth date NOT NULL,
	credits integer NOT NULL,
	name varchar(256) NOT NULL,
	numero_cpf varchar(256) NOT NULL,
	PRIMARY KEY (id)
);