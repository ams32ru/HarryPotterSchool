package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentService {
    Map<Long, Student> studentMap = new HashMap<>();
    private long lastId = 0;

    public Student createStudent(Student student) {
        student.setId(lastId++);
        studentMap.put(lastId, student);
        return student;
    }

    public Student findStudent(Long id) {
        return studentMap.get(id);
    }

    public Student editStudent(Student student) {
        if (!studentMap.containsKey(student.getId())) {
            return null;
        }
        return studentMap.put(student.getId(), student);
    }

    public Student deleteStudent(long id) {
        return studentMap.remove(id);
    }

    public Collection<Student> findStudentInAge(long age) {
        return studentMap.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toSet());
    }
}
