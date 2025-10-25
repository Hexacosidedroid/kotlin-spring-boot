create table students
(
    student_id serial
        primary key,
    name       varchar(50) not null,
    surname    varchar(50) not null,
    birthday   date        not null
);

create table hobbies
(
    hobby_id   serial
        primary key,
    student_id integer      not null
        constraint fk_student
            references students
            on delete cascade,
    hobby      varchar(100) not null
);
