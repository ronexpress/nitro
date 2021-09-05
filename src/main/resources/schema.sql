--drop table if exists game;

CREATE TABLE IF NOT EXISTS public.game (
	id bigserial NOT NULL PRIMARY KEY,
	pl1_name varchar(30),
	pl2_name varchar(30),
	pl1_win int,
	pl2_win int,
	tie int
);
