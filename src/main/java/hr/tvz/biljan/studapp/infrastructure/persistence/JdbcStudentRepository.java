package hr.tvz.biljan.studapp.infrastructure.persistence;

import hr.tvz.biljan.studapp.models.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@Primary
public class JdbcStudentRepository implements StudentRepository {

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcStudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbc)
                .withTableName("student")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Student> findAll() {
        return List.copyOf(jdbc.query("SELECT * FROM student", JdbcStudentRepository::mapResultToStudent));
    }

    @Override
    public Optional<Student> findStudentByJmbag(String jmbag) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("SELECT * FROM student WHERE uid = ?", JdbcStudentRepository::mapResultToStudent, jmbag));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void addStudent(Student student) {
        Map<String, Object> values = new HashMap<>();

        values.put("uid", student.getUid());
        values.put("first_name", student.getFirstName());
        values.put("last_name", student.getLastName());
        values.put("date_of_birth", student.getDateOfBirth());
        values.put("ects_points", student.getEctsPoints());

        var identity = simpleJdbcInsert.executeAndReturnKey(values).intValue();

        student.setId(identity);
    }

    @Override
    public void removeStudent(Student student) {
        jdbc.update("DELETE FROM student where id = ?", student.getId());
    }

    private static Student mapResultToStudent(ResultSet resultSet, int rowNumber) throws SQLException {
        return new Student(
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getDate("date_of_birth").toLocalDate(),
                resultSet.getString("uid"),
                resultSet.getInt("ects_points")
        );
    }
}
