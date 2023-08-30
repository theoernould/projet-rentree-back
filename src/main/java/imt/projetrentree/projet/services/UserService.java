package imt.projetrentree.projet.services;

import imt.projetrentree.projet.dto.UserCreationDTO;
import imt.projetrentree.projet.exceptions.user.AlreadyAuthenticatedException;
import imt.projetrentree.projet.exceptions.user.BadCredentialsException;
import imt.projetrentree.projet.exceptions.user.EmailAlreadyUsedException;
import imt.projetrentree.projet.exceptions.user.UserNotAuthenticatedException;
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
    public static final Map<String, User> users = new HashMap<>();
    private static final Double BEGIN_BALANCE = 100.0;

    private final UserRepository userRepository;

    public User info(String token) {
        return UserService.users.get(token);
    }

    public String login(String email, String password) {
        if (userRepository.existsByEmailAndPassword(email, password)) {
            verifyThatUserIsNotAlreadyAuthenticated(email);
            String token = UUID.randomUUID().toString();
            UserService.users.put(token, userRepository.findByEmailAndPassword(email, password));
            return token;
        } else {
            throw new BadCredentialsException();
        }
    }

    private void verifyThatUserIsNotAlreadyAuthenticated(String email) {
        if (UserService.users.values().stream().anyMatch(user -> user.getEmail().equals(email))) {
            throw new AlreadyAuthenticatedException();
        }
    }

    public void register(UserCreationDTO userToCreate) {
        if (userRepository.existsByEmail(userToCreate.getEmail())) {
            throw new EmailAlreadyUsedException();
        }
        userRepository.save(userToCreate.toUser(BEGIN_BALANCE));
    }

    public void logout(String token) {
        UserService.users.remove(token);
    }
}
