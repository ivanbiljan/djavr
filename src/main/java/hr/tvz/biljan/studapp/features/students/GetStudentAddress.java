package hr.tvz.biljan.studapp.features.students;

// ZADATAK SA LABOSA
// ZADATAK SA LABOSA
// ZADATAK SA LABOSA

import an.awesome.pipelinr.Command;
import hr.tvz.biljan.studapp.infrastructure.dtos.AddressDto;
import hr.tvz.biljan.studapp.infrastructure.persistence.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class GetStudentAddress {

    public record Query(String jmbag) implements Command<AddressDto> { }

    @Component
    public static final class Handler implements Command.Handler<Query, AddressDto> {

        private final StudentRepository studentRepository;

        public Handler(StudentRepository studentRepository) {
            this.studentRepository = studentRepository;
        }

        @Override
        public AddressDto handle(Query query) {
            var student = this.studentRepository.findStudentByJmbag(query.jmbag);

            if (!student.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            return student.map(s ->
                    new AddressDto(
                            s.getAddressLine1(),
                            s.getCity(),
                            s.getCountry(),
                            s.getZipCode()
                    ))
                    .get();
        }

    }
}
