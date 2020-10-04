DROP TABLE IF EXISTS employee;

    CREATE TABLE employee
(
    employee_id   serial primary key,
    first_name    text NOT NULL,
    last_name     text NOT NULL,
    department_id integer NOT NULL,
    job_title     text NOT NULL,
    gender        text CHECK (gender = 'MALE' OR gender = 'FEMALE'),
    date_of_birth date NOT NULL
);

