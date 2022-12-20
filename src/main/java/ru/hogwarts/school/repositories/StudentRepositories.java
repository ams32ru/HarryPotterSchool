package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepositories extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int minAge, int maxAge);

    @Query(value = "SELECT COUNT(*) as StudCount from student", nativeQuery = true)
    int getStudent();

    @Query(value = "SELECT avg(age) as avgAgeStudent from student", nativeQuery = true)
    int getStudentByAgeAvg();

    @Query(value = "select *from student ORDER BY id desc LIMIT 5", nativeQuery = true)
    Collection<Student> get5lastStudent();

}
