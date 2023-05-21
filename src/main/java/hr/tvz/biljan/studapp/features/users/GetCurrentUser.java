package hr.tvz.biljan.studapp.features.users;

import an.awesome.pipelinr.Command;
import hr.tvz.biljan.studapp.features.students.GetStudentById;
import hr.tvz.biljan.studapp.infrastructure.dtos.StudentDto;
import hr.tvz.biljan.studapp.infrastructure.persistence.UserRepository;
import hr.tvz.biljan.studapp.infrastructure.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class GetCurrentUser {
    public record Dto(int Id, String username, String firstName, String lastName, Set<String> authorities) { }

    public record Query() implements Command<Dto> { }

    @Component
    public static final class Handler implements Command.Handler<GetCurrentUser.Query, Dto> {

        private final UserRepository userRepository;

        public Handler(UserRepository studentRepository) {
            this.userRepository = studentRepository;
        }

        @Override
        public Dto handle(GetCurrentUser.Query query) {
            var username = SecurityUtils.getCurrentUserUsername();
            if (username.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }

            return this.userRepository.findByUsername(username.get()).map(s -> new Dto(s.getId(), s.getUsername(), s.getFirstName(), s.getLastName(), s.getAuthorities().stream().map(a -> a.getName()).collect(Collectors.toSet()))).get();
        }
    }
}
