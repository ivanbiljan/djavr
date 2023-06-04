package hr.tvz.biljan.studapp.features.students;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Voidy;
import hr.tvz.biljan.studapp.features.students.*;
import hr.tvz.biljan.studapp.infrastructure.dtos.CourseDto;
import hr.tvz.biljan.studapp.infrastructure.dtos.StudentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class StudentsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Pipeline pipeline;

    private List<StudentDto> students;

    @BeforeEach
    void setUp() {
        students = Arrays.asList(
                new StudentDto("John Doe", "john.doe@example.com", "123", 150, true),
                new StudentDto("Jane Smith", "jane.smith@example.com", "312", 150, false)
        );
    }

    @Test
    void getAll_ReturnsListOfStudents() throws Exception {
        given(pipeline.send(new GetAllStudents.Query())).willReturn(students);

        mockMvc.perform(get("/student").with(user("admin").password("test").authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))
                        .with(csrf())
                        .contentType("application/json")
                        .accept("application/json")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDYwODMwOSwiaWF0IjoxNjU0MDAzNTA5LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.Mb-GxODv7FepzM8HYO3y_15iVxe7Rof_V7UVUOUfuL6x7UspPhLs-7R1zOpRA24Ul0k5bm3QxkSgqbbciSozzg"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("John Doe")))
                .andExpect(jsonPath("$[1].firstName", is("Jane Smith")));
    }

    @Test
    void getByUid_ReturnsStudentByUid() throws Exception {
        String uid = "12345";
        StudentDto student = new StudentDto("John Doe", "john.doe@example.com", "123", 150, true);
        given(pipeline.send(new GetStudentById.Query(uid))).willReturn(student);

        mockMvc.perform(get("/student/{uid}", uid).with(user("admin").password("test").authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))
                        .with(csrf())
                        .contentType("application/json")
                        .accept("application/json")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDYwODMwOSwiaWF0IjoxNjU0MDAzNTA5LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.Mb-GxODv7FepzM8HYO3y_15iVxe7Rof_V7UVUOUfuL6x7UspPhLs-7R1zOpRA24Ul0k5bm3QxkSgqbbciSozzg"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("John Doe")));
    }

    @Test
    void add_ReturnsAddedStudent() throws Exception {
        CreateStudent.Command command = new CreateStudent.Command(
                "John",
                "Doe",
                LocalDate.of(1990, 1, 1),
                "12345678",
                150
        );

        StudentDto addedStudent = new StudentDto(
                "John",
                "Doe",
                "12345678",
                150,
                true
        );

        given(pipeline.send(command)).willReturn(addedStudent);

        mockMvc.perform(post("/student").with(user("admin").password("test").authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))
                        .with(csrf())
                        .accept("application/json")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDYwODMwOSwiaWF0IjoxNjU0MDAzNTA5LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.Mb-GxODv7FepzM8HYO3y_15iVxe7Rof_V7UVUOUfuL6x7UspPhLs-7R1zOpRA24Ul0k5bm3QxkSgqbbciSozzg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"dateOfBirth\":\"01.01.1990\",\"uid\":\"12345678\",\"ectsPoints\":150}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.jmbag", is("12345678")));
    }

    @Test
    void delete_ReturnsNoContent() throws Exception {
        String uid = "12345";

        given(pipeline.send(new DeleteStudent.Command("123"))).willReturn(new Voidy());

        mockMvc.perform(delete("/student/123", uid).with(user("admin").password("test").authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))
                        .with(csrf())
                        .contentType("application/json")
                        .accept("application/json")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDYwODMwOSwiaWF0IjoxNjU0MDAzNTA5LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.Mb-GxODv7FepzM8HYO3y_15iVxe7Rof_V7UVUOUfuL6x7UspPhLs-7R1zOpRA24Ul0k5bm3QxkSgqbbciSozzg"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getEnrolledCourses_ReturnsListOfEnrolledCourses() throws Exception {
        String uid = "12345";
        List<CourseDto> courses = Arrays.asList(
                new CourseDto("Course 1", 8),
                new CourseDto("Course 2", 7)
        );
        given(pipeline.send(new GetEnrolledCourses.Query(uid))).willReturn(courses);

        mockMvc.perform(get("/student/{uid}/courses", uid).with(user("admin").password("test").authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))
                        .with(csrf())
                        .contentType("application/json")
                        .accept("application/json")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDYwODMwOSwiaWF0IjoxNjU0MDAzNTA5LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.Mb-GxODv7FepzM8HYO3y_15iVxe7Rof_V7UVUOUfuL6x7UspPhLs-7R1zOpRA24Ul0k5bm3QxkSgqbbciSozzg"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Course 1")))
                .andExpect(jsonPath("$[1].name", is("Course 2")));

        verify(pipeline).send(new GetEnrolledCourses.Query(uid));
    }
}
