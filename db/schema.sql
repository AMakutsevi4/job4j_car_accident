create table accident_type(
    id serial primary key;
name varchar(50)
);

create table accident_rule(
    id serial primary key;
name varchar(50)
);

create table accident (
    id serial primary key,
name varchar(2000),
text varchar (2000),
address varchar(1000),
type_id int references accident_type(id),
rule_id int references accident_rule(id)
);

insert into accident_type (name) values ('Предупреждение'), ('Штраф'),('Лишение прав');

insert into accident_rule (name) values ('Статья. 1'), ('Статья. 2'), ('Статья. 3');