package hr.tvz.biljan.studapp.features.courses;

import hr.tvz.biljan.studapp.models.Course;

public record CourseDto(String name, int ectsPoints) {
    public static CourseDto fromCourse(Course course) {
        return new CourseDto(course.getName(), course.getEctsPoints());
    }
}