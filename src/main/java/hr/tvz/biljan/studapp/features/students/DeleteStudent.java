package hr.tvz.biljan.studapp.features.students;

import an.awesome.pipelinr.Voidy;
import hr.tvz.biljan.studapp.infrastructure.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

public final class DeleteStudent {
    public record Command(String jmbag) implements an.awesome.pipelinr.Command<Voidy> {}

    @Component
    public static final class Handler implements Command.Handler<Command, Voidy> {

        private final StudentRepository studentRepository;

        public Handler(StudentRepository studentRepository) {
            this.studentRepository = studentRepository;
        }

        @Override
        public Voidy handle(Command request) {
            var student = studentRepository.findAll().stream().filter(s -> s.getUid().equals(request.jmbag)).findFirst();
            if (!student.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            studentRepository.removeStudent(student.get());

            return new Voidy();
        }
    }
}
