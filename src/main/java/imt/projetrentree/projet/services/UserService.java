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
public class UserService {
    private static final Map<String, User> users = new HashMap<>();
    private static final Double BEGIN_BALANCE = 100.0;

    private final UserRepository userRepository;

    public User info(String token) {
        return UserService.users.get(token);
    }

    public String login(String email, String password) {
        if (userRepository.existsByEmailAndPassword(email, password)) {
            String token = UUID.randomUUID().toString();
            UserService.users.put(token, userRepository.findByEmailAndPassword(email, password));
            return token;
        } else {
            return null;
        }
    }

    public String register(UserCreationDTO userToCreate) {
        String id = UUID.randomUUID().toString();

        UserService.users.put(id, userRepository.save(userToCreate.toUser(BEGIN_BALANCE)));
        return id;
    }

    public void logout(String token) {
        UserService.users.remove(token);
    }
}
