package hr.tvz.biljan.studapp.features.students;

import an.awesome.pipelinr.Command;
import hr.tvz.biljan.studapp.infrastructure.dtos.StudentDto;
import hr.tvz.biljan.studapp.infrastructure.persistence.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public final class GetAllStudents {

    public record Query() implements Command<List<StudentDto>> { }

    @Component
    public static final class Handler implements Command.Handler<Query, List<StudentDto>> {

        private final StudentRepository studentRepository;

        public Handler(StudentRepository studentRepository) {
            this.studentRepository = studentRepository;
        }

        @Override
        public List<StudentDto> handle(Query query) {
            return this.studentRepository.findAll().stream().map(s -> StudentDto.fromStudent(s)).collect(Collectors.toList());
        }

    }

}
