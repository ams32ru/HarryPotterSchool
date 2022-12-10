package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepositories;

import java.util.*;

@Service
public class StudentService {
    private final StudentRepositories studentRepositories;

    public StudentService(StudentRepositories studentRepositories) {
        this.studentRepositories = studentRepositories;
    }

    public Student createStudent(Student student) {
        return studentRepositories.save(student);
    }

    public Student findStudent(long id) {
        return studentRepositories.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepositories.save(student);
    }

    public void deleteStudent(long id) {
        studentRepositories.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        return studentRepositories.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        return studentRepositories.findByAgeBetween(minAge, maxAge);
    }

    public Collection<Student> getAllStudents() {
        return studentRepositories.findAll();
    }
}
