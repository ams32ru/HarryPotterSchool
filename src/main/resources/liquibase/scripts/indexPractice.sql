--liquibase formatted sql
CREATE INDEX name_student_index on student (name);
CREATE INDEX name_color_index on faculty (name, color);
