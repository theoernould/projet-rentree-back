package imt.projetrentree.projet.services;

import imt.projetrentree.projet.config.AuthContext;
import imt.projetrentree.projet.dto.user.UserCreationDTO;
import imt.projetrentree.projet.exceptions.user.AlreadyAuthenticatedException;
import imt.projetrentree.projet.exceptions.user.BadCredentialsException;
import imt.projetrentree.projet.exceptions.user.EmailAlreadyUsedException;
import imt.projetrentree.projet.exceptions.user.InvalidEmailException;
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
    public static final Map<String, Long> usersIds = new HashMap<>();
    private static final Double BEGIN_BALANCE = 100.0;

    private final UserRepository userRepository;

    public User getCurrentUser() {
        Long currentUserId = UserService.usersIds.get(AuthContext.getToken());
        return userRepository.findById(currentUserId).orElseThrow();
    }

    public String login(String email, String password) {
        if (!isEmailValid(email)) {
            throw new InvalidEmailException();
        }
        if (userRepository.existsByEmailAndPassword(email, password)) {
            User user = userRepository.findByEmailAndPassword(email, password);
            verifyThatUserIsNotAlreadyAuthenticated(user);
            String token = UUID.randomUUID().toString();
            UserService.usersIds.put(token, user.getId());
            return token;
        } else {
            throw new BadCredentialsException();
        }
    }

    private void verifyThatUserIsNotAlreadyAuthenticated(User user) {
        if (UserService.usersIds.values().stream().anyMatch(id -> id.equals(user.getId()))) {
            throw new AlreadyAuthenticatedException();
        }
    }

    private boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    }

    public void register(UserCreationDTO userToCreate){
        register(userToCreate, BEGIN_BALANCE);
    }

    public void register(UserCreationDTO userToCreate,double balance) {
        if (!isEmailValid(userToCreate.getEmail())) {
            throw new InvalidEmailException();
        }
        if (userRepository.existsByEmail(userToCreate.getEmail())) {
            throw new EmailAlreadyUsedException();
        }
        userRepository.save(userToCreate.toUser(balance));
    }

    public void logout() {
        UserService.usersIds.remove(AuthContext.getToken());
    }

    public void changeBalanceOfUser(User user, Double newBalance) {
        User userFromDb = userRepository.findById(user.getId()).orElseThrow();
        userFromDb.setBalance(newBalance);
        userRepository.save(userFromDb);
    }
}
