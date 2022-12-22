--liquibase formatted sql

--changeset asobolev:1
CREATE INDEX student_name_index on student (name);

--changeset asobolev:2
CREATE INDEX faculty_name_color_index on faculty (name, color);