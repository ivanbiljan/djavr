package hr.tvz.biljan.studapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "course")
@NoArgsConstructor
public final class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "uid")
    private String uid;

    @Column(name = "ects_points")
    private int ectsPoints;

    @ManyToMany(targetEntity = Course.class)
    private Set courses;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, String uid, int ectsPoints) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.uid = uid;
        this.ectsPoints = ectsPoints;
    }
}

