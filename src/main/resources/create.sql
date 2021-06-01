create table pokemon
(
    id bigint not null primary key,
    name varchar not null,
    image_png varchar ,
    image_svg varchar ,
    evolution varchar ,
    hp int,
    attack int,
    defense int,
    speed int,
    special_attack int,
    special_defense int
);

create table type
(
    id bigint not null primary key,
    name_type varchar not null
);

create table pokemon_type
(
    id bigint not null primary key GENERATED ALWAYS AS IDENTITY,
    pokemon_id int not null,
    type_id int not null,
    CONSTRAINT fk_pokemon
        FOREIGN KEY(pokemon_id)
            REFERENCES pokemon(id),
    CONSTRAINT fk_type
        FOREIGN KEY(type_id)
            REFERENCES type(id)
);

CREATE SEQUENCE pokemon_sequence
    INCREMENT 1
MINVALUE 1
START 1
CYCLE;

CREATE SEQUENCE type_sequence
    INCREMENT 1
MINVALUE 1
START 1
CYCLE;