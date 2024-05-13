drop table if exists vaccines;
drop table if exists patients;

create table vaccines(
	id integer auto_increment primary key,
    vaccine_name varchar(255),
    doses_required integer,
    days_between_doses integer,
    doses_recieved integer,
    doses_left integer
);

create table patients(
	id integer auto_increment primary key,
    patient varchar(255),
    vaccine_name varchar(255),
    first_dose varchar(255),
    second_dose varchar(255)
);

insert into vaccines values (1, 'Pfizer/BioNTech', 2, 21, 10000, 10000);
insert into vaccines values (2, 'Johnson & Johnson', 1, null, 5000, 5000);

insert into patients value (1, 'John Doe', 'Pfizer/BioNTech', '02/18/2021', 'no');
insert into patients value (2, 'Jim Lee', 'Johnson & Johnson', '03/12/2021', '-');



