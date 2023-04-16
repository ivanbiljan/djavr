package hr.tvz.biljan.studapp.infrastructure.dtos;

import hr.tvz.biljan.studapp.models.Student;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record StudentDto(String uid, int ectsPoints, Boolean hasToPayTuition) {
    public static StudentDto fromStudent(Student student) {
        return new StudentDto(student.getUid(), student.getEctsPoints(), ChronoUnit.YEARS.between(student.getDateOfBirth(), LocalDate.now()) >= 26);
    }
}
