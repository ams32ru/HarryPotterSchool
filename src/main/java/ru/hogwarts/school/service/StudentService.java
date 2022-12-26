package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepositories;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepositories studentRepositories;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepositories studentRepositories) {
        this.studentRepositories = studentRepositories;
    }

    public Student createStudent(Student student) {
        logger.info("the following method was called: createStudent ");
        return studentRepositories.save(student);
    }

    public Student findStudent(long id) {
        logger.info("the following method was called: findStudent");
        return studentRepositories.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("the following method was called: editStudent");
        return studentRepositories.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("the following method was called: deleteStudent");
        studentRepositories.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        logger.info("the following method was called: findByStudent");
        return studentRepositories.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        logger.info("the following method was called: findByAgeBetween");
        return studentRepositories.findByAgeBetween(minAge, maxAge);
    }

    public Collection<Student> getAllStudents() {
        logger.info("the following method was called: getAllStudent");
        return studentRepositories.findAll();
    }

    public int getStudentCount() {
        logger.info("the following method was called: getStudentCount");
        return studentRepositories.getStudent();
    }

    public int getStudentByAgeAvg() {
        logger.info("the following method was called: getStudentByAgeAvg");
        return studentRepositories.getStudentByAgeAvg();
    }

    public Collection<Student> get5lastStudent() {
        logger.info("the following method was called: get5lastStudent");
        return studentRepositories.get5lastStudent();
    }

    public List<String> getSortedNameStudent() {
        return studentRepositories.findAll().stream().
                map(s -> s.getName().toUpperCase()).
                sorted().collect(Collectors.toList());
    }

    public double getAveregeAge() {
        return studentRepositories.findAll().stream().
                mapToDouble(Student::getAge).
                average().
                getAsDouble();
    }
}
