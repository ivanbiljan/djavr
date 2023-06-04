package hr.tvz.biljan.studapp.features.users;

import an.awesome.pipelinr.Pipeline;
import hr.tvz.biljan.studapp.features.students.GetStudentById;
import hr.tvz.biljan.studapp.infrastructure.dtos.StudentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UsersControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Pipeline pipeline;

    @Test
    void missingToken_Returns401() throws Exception {
        mockMvc.perform(get("/api/user/current-user"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getCurrentUser() throws Exception {
        given(pipeline.send(new GetCurrentUser.Query())).willReturn(new GetCurrentUser.Dto(1, "admin", "FirstName", "LastName", Set.of()));

        mockMvc.perform(get("/api/user/current-user").with(user("admin").password("test").authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))
                        .with(csrf())
                        .contentType("application/json")
                        .accept("application/json")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDYwODMwOSwiaWF0IjoxNjU0MDAzNTA5LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.Mb-GxODv7FepzM8HYO3y_15iVxe7Rof_V7UVUOUfuL6x7UspPhLs-7R1zOpRA24Ul0k5bm3QxkSgqbbciSozzg"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("admin")));
    }
}
