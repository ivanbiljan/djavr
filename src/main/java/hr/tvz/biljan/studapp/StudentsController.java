package hr.tvz.biljan.studapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public ResponseEntity<StudentDto> getByUid(String uid) {
        var student = studentService.findStudentByJmbag(uid);
        if (!student.isPresent()) {
            return new ResponseEntity<StudentDto>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<StudentDto>(student.get(), HttpStatus.OK);
    }

}
