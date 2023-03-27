package hr.tvz.biljan.studapp;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public final class StudentCommand {
    /**
     * Gets or sets the first name.
     */
    @Getter
    @Setter
    @NotBlank(message = "First name must not be empty")
    private String firstName;

    /**
     * Gets or sets the last name.
     */
    @Getter
    @Setter
    @NotBlank(message = "Last name must not be empty")
    private String lastName;

    /**
     * Gets or sets the date of birth.
     */
    @Getter
    @Setter
    @JsonFormat(pattern = "dd.MM.yyyy")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    /**
     * Gets or sets the student's unique identifier.
     */
    @Getter
    @Setter
    @NotBlank(message = "UID must not be empty")
    @Pattern(regexp = "\\d{8,10}", message = "UID must be 8-10 digits long")
    private String uid;

    /**
     * ?????
     */
    @Getter
    @Setter
    @PositiveOrZero(message = "Number of ECTS points must be greater than or equal to zero")
    @Max(value = 300, message = "ECTS points must not exceed 300")
    private int ectsPoints;
}
