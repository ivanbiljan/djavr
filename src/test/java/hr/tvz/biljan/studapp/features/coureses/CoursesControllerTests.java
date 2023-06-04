package hr.tvz.biljan.studapp.features.coureses;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Arrays;
import java.util.List;

import an.awesome.pipelinr.Pipeline;
import hr.tvz.biljan.studapp.features.courses.CoursesController;
import hr.tvz.biljan.studapp.features.courses.GetAllCourses;
import hr.tvz.biljan.studapp.features.courses.GetCourseById;
import hr.tvz.biljan.studapp.infrastructure.dtos.CourseDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CoursesControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Pipeline pipeline;

    private List<CourseDto> courses;

    @BeforeEach
    public void setUp() {
        courses = Arrays.asList(
                new CourseDto("Course 1", 8),
                new CourseDto("Course 2", 7),
                new CourseDto("Course 3", 6)
        );
    }

    @Test
    public void getAll_ReturnsListOfCourses() throws Exception {
        given(pipeline.send(new GetAllCourses.Query())).willReturn(courses);

        mockMvc.perform(
                get("/course")
                        .with(user("admin").password("test").authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))
                        .with(csrf())
                        .contentType("application/json")
                        .accept("application/json")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDYwODMwOSwiaWF0IjoxNjU0MDAzNTA5LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.Mb-GxODv7FepzM8HYO3y_15iVxe7Rof_V7UVUOUfuL6x7UspPhLs-7R1zOpRA24Ul0k5bm3QxkSgqbbciSozzg")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("Course 1")))
                .andExpect(jsonPath("$[1].name", is("Course 2")))
                .andExpect(jsonPath("$[2].name", is("Course 3")));
    }

    @Test
    public void getById_ReturnsCourseById() throws Exception {
        when(pipeline.send(new GetCourseById.Query(1))).thenReturn(courses.get(0));

        mockMvc.perform(
                get("/course/1")
                        .with(user("admin").password("test").authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))
                        .with(csrf())
                        .contentType("application/json")
                        .accept("application/json")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDYwODMwOSwiaWF0IjoxNjU0MDAzNTA5LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.Mb-GxODv7FepzM8HYO3y_15iVxe7Rof_V7UVUOUfuL6x7UspPhLs-7R1zOpRA24Ul0k5bm3QxkSgqbbciSozzg")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Course 1")));
    }

    @Test
    public void getById_DoesNotExist_Returns404() throws Exception {
        when(pipeline.send(new GetCourseById.Query(anyInt()))).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(
                        get("/course/123")
                                .with(user("admin").password("test").authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))
                                .with(csrf())
                                .contentType("application/json")
                                .accept("application/json")
                                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDYwODMwOSwiaWF0IjoxNjU0MDAzNTA5LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.Mb-GxODv7FepzM8HYO3y_15iVxe7Rof_V7UVUOUfuL6x7UspPhLs-7R1zOpRA24Ul0k5bm3QxkSgqbbciSozzg")
                )
                .andExpect(status().isNotFound());
    }
}
