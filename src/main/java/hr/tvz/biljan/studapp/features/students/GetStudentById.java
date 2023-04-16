package hr.tvz.biljan.studapp.features.students;

import an.awesome.pipelinr.Command;
import hr.tvz.biljan.studapp.infrastructure.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

public final class GetStudentById {

    public record Query(String jmbag) implements Command<StudentDto> { }

    @Component
    public static final class Handler implements Command.Handler<Query, StudentDto> {

        private final StudentRepository studentRepository;

        public Handler(StudentRepository studentRepository) {
            this.studentRepository = studentRepository;
        }

        @Override
        public StudentDto handle(Query query) {
            var student = this.studentRepository.findStudentByJmbag(query.jmbag).map(s -> StudentDto.fromStudent(s));
            if (!student.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            return student.get();
        }

    }

}
