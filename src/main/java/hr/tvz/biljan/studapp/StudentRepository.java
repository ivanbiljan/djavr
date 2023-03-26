package hr.tvz.biljan.studapp;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();

    Optional<Student> findStudentByJmbag(String jmbag);
}

