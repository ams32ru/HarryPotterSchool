package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    @Autowired
    private StudentRepositories studentRepositories;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contexLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void getAllTest() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotEmpty();
    }

    @Test
    void getStudentInfoTest() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1", String.class))
                .isNotEmpty();
    }

    @Test
    void findFacultyStudents() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/filterId/1", String.class))
                .isNotEmpty();
    }

    @Test
    void filterStudentAge() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/filterAge/14", String.class))
                .isNotEmpty();
    }

    @Test
    void createStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Piter Test");
        student.setAge(1234);
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student",
                        student, String.class))
                .isNotNull();

    }

    @Test
    void deleteStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Piter Test");
        student.setAge(1234);

        studentRepositories.save(student);
        long idTest = student.getId();
        assertEquals(1, studentRepositories.findAll().size());
        this.restTemplate.delete("http://localhost:" + port + "/student/" + idTest);
        assertEquals(0, studentRepositories.findAll().size());

    }

}
