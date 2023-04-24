package hr.tvz.biljan.studapp.infrastructure.persistence;

import hr.tvz.biljan.studapp.infrastructure.dtos.AddressDto;
import hr.tvz.biljan.studapp.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();

    Optional<Student> findStudentByJmbag(String jmbag);

    // ZADATAK SA LABOSA - dodano samo zato sto pise "novi upit", ovi podatci se mogu dohvatiti i iz findStudentByJmbag. Dto nebi trebao biti tu
    Optional<AddressDto> findAddressByJmbag(String jmbag);

    void addStudent(Student student);

    void removeStudent(Student student);
}

