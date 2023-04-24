package hr.tvz.biljan.studapp.features.students;

import an.awesome.pipelinr.Command;
import hr.tvz.biljan.studapp.infrastructure.dtos.StudentDto;
import hr.tvz.biljan.studapp.infrastructure.persistence.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// ZADATAK SA LABOSA
// ZADATAK SA LABOSA
// ZADATAK SA LABOSA

public final class GetStudentDetails {
    public record Dto(String jmbag, String firstName, String lastName, int ectsPoints, LocalDate dateOfBirth, boolean hasToPayTuition) {}

    public record Query(String jmbag) implements Command<Dto> { }

    @Component
    public static final class Handler implements Command.Handler<Query, Dto> {

        private final StudentRepository studentRepository;

        public Handler(StudentRepository studentRepository) {
            this.studentRepository = studentRepository;
        }

        @Override
        public Dto handle(Query query) {
            var student = this.studentRepository.findStudentByJmbag(query.jmbag).map(s ->
                    new Dto(
                        s.getUid(),
                        s.getFirstName(),
                        s.getLastName(),
                        s.getEctsPoints(),
                        s.getDateOfBirth(),
                        ChronoUnit.YEARS.between(s.getDateOfBirth(), LocalDate.now()) >= 26));

            if (!student.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            return student.get();
        }

    }
}
