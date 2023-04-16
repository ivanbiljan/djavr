package hr.tvz.biljan.studapp.features.courses;

import an.awesome.pipelinr.Pipeline;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "courses", produces = "application/json")
public final class CoursesController {
    private final Pipeline pipeline;

    public CoursesController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @GetMapping
    public List<CourseDto> getAll() {
        return pipeline.send(new GetAllCourses.Query());
    }

    @GetMapping("/{courseId}")
    public CourseDto getById(@PathVariable final int courseId) {
        return pipeline.send(new GetCourseById.Query(courseId));
    }
}
