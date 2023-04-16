package hr.tvz.biljan.studapp.infrastructure.persistence;

import hr.tvz.biljan.studapp.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();

    Optional<Student> findStudentByJmbag(String jmbag);

    void addStudent(Student student);

    void removeStudent(Student student);
}

