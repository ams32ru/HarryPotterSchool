package ru.hogwarts.school.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    //дурацкий метод, не смог придумать как все стримом сделать
    public String getLongName() {
        List<String> nameFaculty = facultyRepositories.
                findAll().stream().map(Faculty::getName).toList();
        int fMax = 0;
        for (int i = 0; i < nameFaculty.size(); i++) {
            for (int j = i + 1; j < nameFaculty.size(); j++) {
                if (i >= j) {
                    fMax = i;
                }
            }
        }
        return nameFaculty.get(fMax);
    }
}
