package ru.hogwarts.school.repositories;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepositories extends JpaRepository<Student,Long> {

    List<Student> findByAgeLike(int age);
}