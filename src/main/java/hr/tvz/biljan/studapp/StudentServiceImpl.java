package hr.tvz.biljan.studapp;

import org.springframework.stereotype.Service;

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

    @Override
    public Optional<StudentDto> addStudent(StudentCommand studentCommand) {
        if (studentRepository.findAll().stream().anyMatch(s -> s.getUid() == studentCommand.getUid())) {
            return Optional.empty();
        }

        var student = new Student(studentCommand.getFirstName(), studentCommand.getLastName(), studentCommand.getDateOfBirth(), studentCommand.getUid(), studentCommand.getEctsPoints());
        studentRepository.addStudent(student);

        return Optional.of(StudentDto.fromStudent(student));
    }

    @Override
    public boolean deleteByJmbag(String jmbag) {
        var student = studentRepository.findAll().stream().filter(s -> s.getUid().equals(jmbag)).findFirst();
        if (!student.isPresent()) {
            return false;
        }

        studentRepository.removeStudent(student.get());

        return true;
    }
}
