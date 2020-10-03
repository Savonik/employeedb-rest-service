create table employee
(
    employee_id   serial primary key,
    first_name    character(50) NOT NULL,
    last_name     character(50) NOT NULL,
    department_id integer NOT NULL,
    job_title     character(50) NOT NULL,
    gender        character(50) CHECK (gender = 'MALE' OR gender = 'FEMALE'),
    date_of_birth date NOT NULL
);

