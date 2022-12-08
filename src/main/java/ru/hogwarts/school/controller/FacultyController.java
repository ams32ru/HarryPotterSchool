package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Грифиндор всегда 1, id тут неважен")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    @Operation (summary = "Проверить что факультета по прежнему 4")
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @PostMapping
    @Operation(summary = "Основать факультет(если ты конечно достоин)")
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    @Operation(summary = "Факультеты существуют более 1000 лет!, что ты хочешь изменить?")
    public ResponseEntity<Faculty> setFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Давно пора распустить слизерин, одни уголовники")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter{color}")
    @Operation(summary = "Серьезно? не запомнишь 4 цвета?")
    public ResponseEntity<Collection<Faculty>> getColorFaculty(String color) {
        return ResponseEntity.ok(facultyService.findFacultyIbColor(color));
    }
}