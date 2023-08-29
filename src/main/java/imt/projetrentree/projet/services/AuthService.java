package imt.projetrentree.projet.services;

import imt.projetrentree.projet.dto.UserCreationDTO;
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
        if (userRepository.existsByEmailAndPassword(email, password)) {
            UUID token = UUID.randomUUID();
            AuthService.users.put(token, userRepository.findByEmailAndPassword(email, password));
            return token;
        } else {
            return null;
        }
    }

    public UUID register(UserCreationDTO userToCreate) {
        UUID id = UUID.randomUUID();

        AuthService.users.put(id, userRepository.save(userToCreate.toUser(BEGIN_BALANCE)));
        return id;
    }

    public void logout(UUID token) {
        AuthService.users.remove(token);
    }
}
