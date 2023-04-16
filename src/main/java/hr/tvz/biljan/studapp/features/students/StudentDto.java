package hr.tvz.biljan.studapp.features.students;

import hr.tvz.biljan.studapp.models.Student;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class StudentDto {
    @Getter
    @Setter
    private String uid;

    @Getter
    @Setter
    private int ectsPoints;

    @Getter
    @Setter
    private Boolean hasToPayTuition;

    public StudentDto(String uid, int ectsPoints, Boolean hasToPayTuition) {

        this.uid = uid;
        this.ectsPoints = ectsPoints;
        this.hasToPayTuition = hasToPayTuition;
    }

    public static StudentDto fromStudent(Student student) {
        return new StudentDto(student.getUid(), student.getEctsPoints(), ChronoUnit.YEARS.between(student.getDateOfBirth(), LocalDate.now()) >= 26);
    }
}
