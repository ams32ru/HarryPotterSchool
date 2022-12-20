package ru.hogwarts.school.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepositories;

import java.util.Collection;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepositories facultyRepositories;

    public FacultyService(FacultyRepositories facultyRepositories) {
        this.facultyRepositories = facultyRepositories;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepositories.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepositories.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepositories.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepositories.deleteById(id);
    }

    public Collection<Faculty> getAllFaculty(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return facultyRepositories.findAll(pageRequest).getContent();
    }

    public Collection<Faculty> findFacultyByNameIgnoreCaseOrColorIgnoreCase(String name, String color) {
        return facultyRepositories.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }
}
