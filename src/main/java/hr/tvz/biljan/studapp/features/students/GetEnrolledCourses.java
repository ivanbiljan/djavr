package hr.tvz.biljan.studapp.features.students;

import an.awesome.pipelinr.Command;
import hr.tvz.biljan.studapp.infrastructure.dtos.CourseDto;
import hr.tvz.biljan.studapp.infrastructure.persistence.CourseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public final class GetEnrolledCourses {
    public record Query(String uid) implements Command<List<CourseDto>> { }

    @Component
    public static final class Handler implements Command.Handler<Query, List<CourseDto>> {
        private final CourseRepository courseRepository;

        public Handler(CourseRepository courseRepository) {
            this.courseRepository = courseRepository;
        }

        @Override
        public List<CourseDto> handle(Query query) {
            return courseRepository
                    .findCoursesAttendedBy(query.uid)
                    .stream()
                    .map(c -> CourseDto.fromCourse(c)).collect(Collectors.toList());
        }
    }
}
