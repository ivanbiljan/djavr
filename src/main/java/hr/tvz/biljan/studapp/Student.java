package hr.tvz.biljan.studapp;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Represents a student.
 */
public final class Student {
    /**
     * Gets or sets the first name.
     */
    @Getter
    @Setter
    private String firstName;

    /**
     * Gets or sets the last name.
     */
    @Getter
    @Setter
    private String lastName;

    /**
     * Gets or sets the date of birth.
     */
    @Getter
    @Setter
    private LocalDate dateOfBirth;

    /**
     * Gets or sets the student's unique identifier.
     */
    @Getter
    @Setter
    private String uid;

    /**
     * ?????
     */
    @Getter
    @Setter
    private int ectsPoints;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, String uid, int ectsPoints) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.uid = uid;
        this.ectsPoints = ectsPoints;
    }
}

