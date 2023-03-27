package hr.tvz.biljan.studapp;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "students", produces = "application/json")
public final class StudentsController {

    private final StudentService studentService;

    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDto> getAll() {
        return studentService.findAll();
    }

    @GetMapping("/{uid}")
    public ResponseEntity<StudentDto> getByUid(@PathVariable final String uid) {
        var student = studentService.findStudentByJmbag(uid);
        if (!student.isPresent()) {
            return new ResponseEntity<StudentDto>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<StudentDto>(student.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDto> add(@Valid final StudentCommand studentCommand) {
        return studentService.addStudent(studentCommand)
                .map(
                        studentDto -> new ResponseEntity<>(studentDto, HttpStatus.CREATED)
                ).orElseGet(
                        () -> new ResponseEntity<>(HttpStatus.CONFLICT)
                );
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity delete(@PathVariable final String uid) {
        return studentService.deleteByJmbag(uid) ? new ResponseEntity(HttpStatus.NO_CONTENT) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
