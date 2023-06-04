package hr.tvz.biljan.studapp.infrastructure.persistence;

import hr.tvz.biljan.studapp.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        // Create a sample user
        User user = new User();
        user.setUsername("johndoe");
        user.setPassword("johndoe");
        user.setFirstName("John");
        user.setLastName("johndoe");

        // Save the user in the database
        userRepository.save(user);

        // Invoke the method being tested
        Optional<User> optionalUser = userRepository.findByUsername("johndoe");

        // Assert that the user is found
        Assertions.assertTrue(optionalUser.isPresent());

        // Retrieve the found user
        User foundUser = optionalUser.get();

        // Assert that the retrieved user matches the saved user
        Assertions.assertEquals(user.getUsername(), foundUser.getUsername());
        Assertions.assertEquals(user.getPassword(), foundUser.getPassword());
    }

    @Test
    public void testFindByUsername_DoesntExist() {
        // Invoke the method being tested
        Optional<User> optionalUser = userRepository.findByUsername("nema");

        // Assert that the user is found
        Assertions.assertTrue(optionalUser.isEmpty());
    }
}