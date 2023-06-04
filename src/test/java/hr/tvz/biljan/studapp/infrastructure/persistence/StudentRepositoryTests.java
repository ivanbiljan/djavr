package hr.tvz.biljan.studapp.infrastructure.persistence;

import hr.tvz.biljan.studapp.models.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testFindStudentByUid_StudentDoesNotExist() {
        // Create a sample student
        Student student = new Student("John", "Doe", LocalDate.now(), "12345678", 150);

        studentRepository.save(student);

        // Invoke the method being tested
        Optional<Student> optionalStudent = studentRepository.findStudentByUid("0");

        // Assert that the student is found
        Assertions.assertTrue(optionalStudent.isEmpty());
    }

    @Test
    public void testFindStudentByUid_StudentExists() {
        // Create a sample student
        Student student = new Student("John", "Doe", LocalDate.now(), "12345678", 150);

        studentRepository.save(student);

        // Invoke the method being tested
        Optional<Student> optionalStudent = studentRepository.findStudentByUid("12345678");

        // Assert that the student is found
        Assertions.assertTrue(optionalStudent.isPresent());

        // Retrieve the found student
        Student foundStudent = optionalStudent.get();

        // Assert that the retrieved student matches the saved student
        Assertions.assertEquals(student.getFirstName(), foundStudent.getFirstName());
        Assertions.assertEquals(student.getLastName(), foundStudent.getLastName());
        Assertions.assertEquals(student.getDateOfBirth(), foundStudent.getDateOfBirth());
        Assertions.assertEquals(student.getUid(), foundStudent.getUid());
        Assertions.assertEquals(student.getEctsPoints(), foundStudent.getEctsPoints());
    }
}