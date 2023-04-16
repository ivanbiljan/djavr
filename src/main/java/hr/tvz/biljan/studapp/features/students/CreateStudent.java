package hr.tvz.biljan.studapp.features.students;

import com.fasterxml.jackson.annotation.JsonFormat;
import hr.tvz.biljan.studapp.models.Student;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

public final class CreateStudent {

    public record Command(
            @NotBlank(message = "First name must not be empty")
            String firstName,
            @NotBlank(message = "Last name must not be empty")
            String lastName,
            @JsonFormat(pattern = "dd.MM.yyyy")
            @Past(message = "Date of birth must be in the past")
            LocalDate dateOfBirth,
            @NotBlank(message = "UID must not be empty")
            @Pattern(regexp = "\\d{8,10}", message = "UID must be 8-10 digits long")
            String uid,
            @PositiveOrZero(message = "Number of ECTS points must be greater than or equal to zero")
            @Max(value = 300, message = "ECTS points must not exceed 300")
            int ectsPoints

    ) implements an.awesome.pipelinr.Command<StudentDto>
    {
    }

    @Component
    public static final class Handler implements Command.Handler<Command, StudentDto> {

        private final StudentRepository studentRepository;

        public Handler(StudentRepository studentRepository) {
            this.studentRepository = studentRepository;
        }

        @Override
        public StudentDto handle(Command query) {
            if (studentRepository.findAll().stream().anyMatch(s -> s.getUid().equals(query.uid))) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            var student = new Student(query.firstName, query.lastName, query.dateOfBirth, query.uid, query.ectsPoints);
            studentRepository.addStudent(student);

            return StudentDto.fromStudent(student);
        }

    }

}
