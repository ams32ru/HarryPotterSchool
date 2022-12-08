package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepositories;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepositories facultyRepositories;

    public FacultyService(FacultyRepositories facultyRepositories) {
        this.facultyRepositories = facultyRepositories;
    }


    public Faculty createFaculty(Faculty faculty) {
        return (Faculty) facultyRepositories.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return  facultyRepositories.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return  facultyRepositories.save(faculty);
    }

    public void deleteFaculty(long id) {
     facultyRepositories.deleteById(id);
    }

    public Collection<Faculty> findFacultyIbColor(String color) {
        return facultyRepositories.findByColorLike(color);
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyRepositories.findAll();
    }
}
