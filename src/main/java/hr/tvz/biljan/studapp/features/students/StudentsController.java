package hr.tvz.biljan.studapp.features.students;

import an.awesome.pipelinr.Pipeline;
import hr.tvz.biljan.studapp.infrastructure.dtos.AddressDto;
import hr.tvz.biljan.studapp.infrastructure.dtos.CourseDto;
import hr.tvz.biljan.studapp.infrastructure.dtos.StudentDto;
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
        return pipeline.send(new GetAllStudents.Query());
    }

    @GetMapping("/{uid}")
    public StudentDto getByUid(@PathVariable final String uid) {
        return pipeline.send(new GetStudentById.Query(uid));
    }

    @GetMapping("/{uid}/details")
    public GetStudentDetails.Dto getDetailsByUid(@PathVariable final String uid) {
        return pipeline.send(new GetStudentDetails.Query(uid));
    }

    @GetMapping("/{uid}/details/address")
    public AddressDto getStudentAddressByStudentUid(@PathVariable final String uid) {
        return pipeline.send(new GetStudentAddress.Query(uid));
    }

    @PostMapping
    public StudentDto add(@Valid @RequestBody final CreateStudent.Command request) {
        return pipeline.send(request);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity delete(@PathVariable final String uid) {
        pipeline.send(new DeleteStudent.Command(uid));

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{uid}/courses")
    public List<CourseDto> getEnrolledCourses(@PathVariable final String uid) {
        return pipeline.send(new GetEnrolledCourses.Query(uid));
    }

}
