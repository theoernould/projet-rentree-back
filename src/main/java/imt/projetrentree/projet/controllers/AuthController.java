package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.dto.UserCreationDTO;
import imt.projetrentree.projet.models.User;
import imt.projetrentree.projet.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public UUID login(@RequestParam String email, @RequestParam String password) {
        return authService.login(email, password);
    }

    @PostMapping("/register")
    public UUID register(@RequestBody UserCreationDTO user) {
        log.info("Registering user: {}", user);
        return authService.register(user);
    }

    @GetMapping("/info")
    public User info(@RequestParam UUID token) {
        return authService.info(token);
    }

    @GetMapping("/logout")
    public void logout(@RequestParam UUID token) {
        authService.logout(token);
    }
}