package hr.tvz.biljan.studapp.features.students;

import an.awesome.pipelinr.Pipeline;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "students", produces = "application/json")
public final class StudentsController {

    private final Pipeline pipeline;

    public StudentsController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @GetMapping
    public List<StudentDto> getAll() {
        return pipeline.send(new GetAllStudents.Request());
    }

    @GetMapping("/{uid}")
    public StudentDto getByUid(@PathVariable final String uid) {
        return pipeline.send(new GetStudentById.Request(uid));
    }

    @PostMapping
    public StudentDto add(@Valid @RequestBody final CreateStudent.Request request) {
        return pipeline.send(request);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity delete(@PathVariable final String uid) {
        pipeline.send(new DeleteStudent.Request(uid));

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}