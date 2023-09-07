package imt.projetrentree.projet.services;

import imt.projetrentree.projet.config.AuthContext;
import imt.projetrentree.projet.dto.user.UserCreationDTO;
import imt.projetrentree.projet.exceptions.user.*;
import imt.projetrentree.projet.models.User;
import imt.projetrentree.projet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserService {
    public static final Map<String, Long> usersIds = new HashMap<>();

    public static final Map<String, String> resetPasswordTokens = new HashMap<>();
    private static final Double BEGIN_BALANCE = 100.0;

    private final UserRepository userRepository;

    private final EmailService emailService;

    @Autowired
    public UserService(EmailService emailService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public User getCurrentUser() {
        Long currentUserId = UserService.usersIds.get(AuthContext.getToken());
        return getUserById(currentUserId);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public String login(String email, String password) {
        if (!isEmailValid(email)) {
            throw new InvalidEmailException();
        }
        if (userRepository.existsByEmailAndPassword(email, password)) {
            return loginUser(email, password);
        } else {
            throw new BadCredentialsException();
        }
    }

    private String loginUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        verifyThatUserIsNotAlreadyAuthenticated(user);
        String token = UUID.randomUUID().toString();
        UserService.usersIds.put(token, user.getId());
        return token;
    }

    public void resetPasswordSendMail(String email) {
        if (!isEmailValid(email)) {
            throw new InvalidEmailException();
        }
        if (userRepository.existsByEmail(email)) {
            String token = UUID.randomUUID().toString();
            resetPasswordTokens.put(token, email);
            emailService.sendChangePasswordEmail(token, email);
        } else {
            throw new BadCredentialsException();
        }
    }

    public void resetPassword(String token, String password) {
        if (resetPasswordTokens.containsKey(token)) {
            String email = resetPasswordTokens.get(token);
            User user = userRepository.findByEmail(email);
            user.setPassword(password);
            userRepository.save(user);
            disconnectUser(email);
            emailService.sendPasswordChangeConfirmationEmail(user);
        } else {
            throw new EmailTokenExpiredException();
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

    public void register(UserCreationDTO userToCreate) {
        register(userToCreate, BEGIN_BALANCE);
    }

    public void register(UserCreationDTO userToCreate, double balance) {
        if (!isEmailValid(userToCreate.getEmail())) {
            throw new InvalidEmailException();
        }
        if (userRepository.existsByEmail(userToCreate.getEmail())) {
            throw new EmailAlreadyUsedException();
        }
        User userCreated = userRepository.save(userToCreate.toUser(balance));
        emailService.sendWelcomeEmail(userCreated);
    }

    public void logout() {
        UserService.usersIds.remove(AuthContext.getToken());
    }

    private void disconnectUser(String email) {
        UserService.usersIds.remove(findTokenByUserEmail(email));
    }

    private String findTokenByUserEmail(String email) {
        AtomicReference<String> userToken = new AtomicReference<>("");
        UserService.usersIds.forEach((token, id) -> {
            User user = userRepository.findById(id).orElseThrow();
            if (user.getEmail().equals(email)) {
                userToken.set(token);
            }
        });
        return userToken.get();
    }

    public void changeBalanceOfUser(User user, Double newBalance) {
        User userFromDb = userRepository.findById(user.getId()).orElseThrow();
        userFromDb.setBalance(newBalance);
        userRepository.save(userFromDb);
    }
}
