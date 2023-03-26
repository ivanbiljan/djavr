package hr.tvz.biljan.studapp;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(s -> StudentDto.fromStudent(s)).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDto> findStudentByJmbag(String jmbag) {
        return studentRepository.findStudentByJmbag(jmbag).map(s -> StudentDto.fromStudent(s));
    }
}
