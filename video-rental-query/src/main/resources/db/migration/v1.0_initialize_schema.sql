CREATE TABLE movie (
	id character varying(255) COLLATE pg_catalog."default" NOT NULL,
	name character varying(255) COLLATE pg_catalog."default" NOT NULL,
	genre character varying(255) COLLATE pg_catalog."default" NOT NULL,
	CONSTRAINT movie PRIMARY KEY (id)
);