    package hr.tvz.biljan.studapp.infrastructure.persistence;

    import static org.junit.jupiter.api.Assertions.assertEquals;

    import hr.tvz.biljan.studapp.models.Course;
    import hr.tvz.biljan.studapp.models.Student;
    import org.junit.jupiter.api.Test;
    import org.junit.runner.RunWith;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
    import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
    import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.test.context.junit4.SpringRunner;

    import java.time.LocalDate;
    import java.util.List;

    @SpringBootTest
    public class CourseRepositoryTests {

        @Autowired
        private CourseRepository courseRepository;

        @Autowired
        private StudentRepository studentRepository;

        @Test
        public void findCoursesAttendedBy_ReturnsListOfCourses() {
            // Create a sample student
            Student student = new Student("John", "Doe", LocalDate.now(), "12345678", 150);
            studentRepository.save(student);

            Course course1 = new Course();
            course1.setName("Course1");

            Course course2 = new Course();
            course2.setName("Course2");

            courseRepository.save(course1);
            courseRepository.save(course2);

            // Assign the courses to the student
            student.getCourses().add(course1);
            student.getCourses().add(course2);

            studentRepository.save(student);

            // Retrieve the attended courses for the student
            List<Course> attendedCourses = courseRepository.findCoursesAttendedBy(student.getUid());

            // Assert that the attended courses are correct
            assertEquals(2, attendedCourses.size());
            assertEquals(course1.getName(), attendedCourses.get(0).getName());
            assertEquals(course2.getName(), attendedCourses.get(1).getName());
        }
    }