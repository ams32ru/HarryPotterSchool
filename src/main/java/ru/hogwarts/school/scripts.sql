select *
from student;

select *
from student
where age > 14
  and age < 20;

select student.name
from student;

select *
from student
where name like '%on%';

select *
from student
where age < student.id;

select *
from student
ORDER BY age;

select f.name, f.color, s.name
from student as s,
     faculty as f
where s.faculty_id = f.id
  and f.id = 7;