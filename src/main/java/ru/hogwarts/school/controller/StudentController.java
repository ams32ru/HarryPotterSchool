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

    @GetMapping
    @Operation(summary = "Посмотреть кто собрался в большом зале Хогвартса")
    public ResponseEntity<Collection<Student>> getAllStudentsOrFiktreAge(@RequestParam(required = false) Integer minAge,
                                                                         @RequestParam(required = false) Integer maxAge) {
        if (minAge != null && maxAge != null) {
            return ResponseEntity.ok(studentService.findByAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
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

    @GetMapping("/ThreadStudent")
    @Operation(summary = "Список студентов в разных потоках", tags = "Потоки")
    public void getStudentFoThread() {
        studentService.getStudentFoThread();

    }

    @GetMapping("/ThreadStudentSyn")
    @Operation(summary = "Список студентов в разных потоках синхронно", tags = "Потоки")
    public void getStudentfoThreadSynhron() {
        studentService.getStudentFoThreadSynhronized();

    }

    @GetMapping("/filterNameSorted")
    @Operation(summary = "Получить отсортированный по алфавиту список студентов", tags = "streamAPI")
    public Collection<String> getSortedNameStudent() {
        return studentService.getSortedNameStudent();
    }

    @GetMapping("/filterAverageAge")
    @Operation(summary = "Получить средний возраст студентов", tags = "streamAPI")
    public Double getAverageAge() {
        return studentService.getAveregeAge();
    }


    @GetMapping("/filterId{id}")
    @Operation(summary = "Спросить у распределяющей шляпы на каком факультете учится этот студент")
    public Faculty findFacultyStudents(@PathVariable Long id) {
        return studentService.findStudent(id).getFaculty();
    }

    @GetMapping("/filterAge{age}")
    @Operation(summary = "Дамболдор!!! Зачем вам это заклинание?")
    public ResponseEntity<Collection<Student>> filterStudentAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.findByAge(age));
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


    @Operation(summary = "Сколько студентов в Хогвартсе", tags = "Запросы SQL")
    @GetMapping("countStudents")
    public int getStudentCount() {
        return studentService.getStudentCount();
    }

    @Operation(summary = "Средний возраст студентов", tags = "Запросы SQL")
    @GetMapping("avgAgeStudents")
    public int getStudentByAgeAvg() {
        return studentService.getStudentByAgeAvg();
    }

    @Operation(summary = "Посмотреть последние 5 студентов в списках", tags = "Запросы SQL")
    @GetMapping("last5Students")
    public Collection<Student> get5lastStudent() {
        return studentService.get5lastStudent();
    }

}
