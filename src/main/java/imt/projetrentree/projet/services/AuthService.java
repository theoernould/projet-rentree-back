package imt.projetrentree.projet.services;

import imt.projetrentree.projet.models.User;
import imt.projetrentree.projet.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private static final Map<UUID, User> users = new HashMap<>();
    private static final Double BEGIN_BALANCE = 100.0;

    private final UserRepository userRepository;

    public User info(UUID token) {
        return AuthService.users.get(token);
    }

    public UUID login(String email, String password) {
        if (userRepository.existsByUsernameAndPassword(email, password)) {
            UUID token = UUID.randomUUID();
            AuthService.users.put(token, userRepository.findByUsernameAndPassword(email, password));
            return token;
        } else {
            return null;
        }
    }

    public UUID register(String firstname, String lastname, String email, String password) {
        UUID id = UUID.randomUUID();

        User user = User.builder()
                .email(email)
                .firstname(firstname)
                .lastname(lastname)
                .password(password)
                .balance(BEGIN_BALANCE)
                .build();

        AuthService.users.put(id, userRepository.save(user));
        return id;
    }

    public void logout(UUID token) {
        AuthService.users.remove(token);
    }
}
