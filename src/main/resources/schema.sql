create table if not exists employee
(
    employee_id   serial primary key,
    first_name    character(50),
    last_name     character(50),
    department_id integer,
    job_title     character(50),
    gender        character(50),
    date_of_birth date
);

