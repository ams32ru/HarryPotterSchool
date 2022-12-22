ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK ( age > 16 );

ALTER TABLE student
    ADD UNIQUE  (name);

ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20;

ALTER TABLE faculty
    ADD CONSTRAINT name_color_unique UNIQUE (name, color);



CREATE TABLE human
(
    id             SERIAL primary key,
    name           VARCHAR(30) NOT NULL,
    age            INTEGER     NOT NULL CHECK (age > 0 ),
    driver_license BOOLEAN,
    car_id         INTEGER CHECK ( driver_license = true),
    FOREIGN KEY (car_id) REFERENCES car (id)


);

CREATE TABLE car
(
    id    SERIAL primary key,
    brand VARCHAR(30)   NOT NULL,
    model VARCHAR(30)   NOT NULL,
    prise NUMERIC(7, 2) NOT NULL CHECK ( prise > 0 )
);

SELECT student.name, student.age, faculty.name
FROM student
         INNER JOIN faculty ON student.faculty_id = faculty.id;

SELECT *
FROM student as s
         RIGHT JOIN avatar as a on s.id = a.student_id;






