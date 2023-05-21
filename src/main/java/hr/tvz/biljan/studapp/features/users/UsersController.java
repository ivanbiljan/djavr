package hr.tvz.biljan.studapp.features.users;

import an.awesome.pipelinr.Pipeline;
import hr.tvz.biljan.studapp.features.students.GetStudentById;
import hr.tvz.biljan.studapp.infrastructure.dtos.StudentDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/user")
@CrossOrigin(origins = "http://localhost:4200")
public final class UsersController {
    private final Pipeline pipeline;

    public UsersController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @GetMapping("/current-user")
    @CrossOrigin(origins = "http://localhost:4200")
    public GetCurrentUser.Dto getCurrentUser() {
        return pipeline.send(new GetCurrentUser.Query());
    }
}
