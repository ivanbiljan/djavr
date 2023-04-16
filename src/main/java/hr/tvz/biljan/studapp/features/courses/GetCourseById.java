package hr.tvz.biljan.studapp.features.courses;

import an.awesome.pipelinr.Command;
import hr.tvz.biljan.studapp.infrastructure.dtos.CourseDto;
import hr.tvz.biljan.studapp.infrastructure.persistence.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

public final class GetCourseById {
    public record Query(int courseId) implements Command<CourseDto> { }

    @Component
    public static final class Handler implements Command.Handler<Query, CourseDto> {
        private final CourseRepository courseRepository;

        public Handler(CourseRepository courseRepository) {
            this.courseRepository = courseRepository;
        }

        @Override
        public CourseDto handle(Query query) {
            var course = courseRepository.findById(query.courseId);
            if (course.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            return CourseDto.fromCourse(course.get());
        }
    }
}
