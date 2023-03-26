package hr.tvz.biljan.studapp;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public final class StudentRepositoryImpl implements StudentRepository {
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student("Ivan", "Biljan", LocalDate.of(2001, 9, 22), "0246096864", 180),
            new Student("Luka", "Ratković", LocalDate.of(1991, 9, 22), "0347092814", 60),
            new Student("Matej", "Koscec", LocalDate.of(1975, 9, 22), "0816236871", 118),
            new Student("Marko", "Strk", LocalDate.of(2005, 9, 22), "9205718200", 25));

    @Override
    public List<Student> findAll() {
        return STUDENTS;
    }

    @Override
    public Optional<Student> findStudentByJmbag(String jmbag) {
        return STUDENTS.stream().filter(s -> s.getUid() == jmbag).findFirst();
    }
}
