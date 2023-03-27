package hr.tvz.biljan.studapp;

import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDto> findAll();

    Optional<StudentDto> findStudentByJmbag(String jmbag);

    Optional<StudentDto> addStudent(StudentCommand studentCommand);

    boolean deleteByJmbag(String jmbag);
}
