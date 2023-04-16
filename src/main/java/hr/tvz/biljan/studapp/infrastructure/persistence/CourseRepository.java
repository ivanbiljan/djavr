package hr.tvz.biljan.studapp.infrastructure.persistence;

import hr.tvz.biljan.studapp.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "select c.id, c.name, c.ects_points from student_course cs inner join course c on c.id = cs.course_id inner join student s on s.id = cs.student_id where s.uid = :studentJmbag", nativeQuery = true)
    List<Course> findCoursesAttendedBy(@Param("studentJmbag") String studentJmbag);
}
