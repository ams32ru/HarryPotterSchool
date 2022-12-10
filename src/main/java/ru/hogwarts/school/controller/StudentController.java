package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Акцио студент")
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    //Сюда можно было бы впихнуть и метод поиска студента по возрасту, но у меня там шутка
    // про Дамболдора, поэтому оставил.
    @GetMapping
    @Operation(summary = "Посмотреть кто собрался в большом зале Хогвартса")
    public ResponseEntity<Collection<Student>> getAllStudentsOrFiktreAge(@RequestParam(required = false) Integer minAge,
                                                                         @RequestParam(required = false) Integer maxAge) {
        if (minAge != null && maxAge != null) {
            return ResponseEntity.ok(studentService.findByAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/filter{age}")
    @Operation(summary = "Дамболдор!!! Зачем вам это заклинание?")
    public ResponseEntity<Collection<Student>> filterStudentAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.findByAge(age));
    }

    @GetMapping("/filter{id}")
    @Operation(summary = "Узнать факультет студента")
    public Faculty findFacultyStudents(@PathVariable Long id) {
        return studentService.findStudent(id).getFaculty();
    }

    @PostMapping
    @Operation(summary = "Зачислить студента в Хогвартс")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    @Operation(summary = "Выпить оборотное зелье")
    public ResponseEntity<Student> setStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Авада Кедавра!")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
