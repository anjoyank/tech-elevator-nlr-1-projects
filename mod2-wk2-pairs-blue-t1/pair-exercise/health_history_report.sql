-- assume all pets have owners
-- 

BEGIN TRANSACTION;

CREATE TABLE owner(
ownerId serial,
firstName varchar(20) not null,
lastName varchar(20) not null,
constraint pk_owner primary key(ownerId)
);

CREATE TABLE pet(
petId serial,
petName varchar(20) not null,
petType varchar(10) not null,
petAge int,
ownerId int not null,
constraint pk_pet primary key(petId),
constraint fk_pet_owner foreign key(ownerId) references owner(ownerId)
);

CREATE TABLE procedure(
procedureId serial,
procedureName varchar(100) not null,
constraint pk_procedure primary key(procedureId)
);

CREATE TABLE visit(
ownerId int not null,
petId int not null,
procedureId int not null,
dateTime timestamp not null,
constraint pk_visit primary key(procedureId, dateTime, petId),
constraint fk_visit_procedure foreign key(procedureId) references procedure(procedureId),
constraint fk_visit_owner foreign key(ownerId) references owner(ownerId),
constraint fk_visit_pet foreign key(petId) references pet(petId)
);

ROLLBACK;