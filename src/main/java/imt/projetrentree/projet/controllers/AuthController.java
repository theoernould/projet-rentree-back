package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.models.User;
import imt.projetrentree.projet.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public UUID login(@RequestParam String email, @RequestParam String password) {
        return authService.login(email, password);
    }

    @PostMapping("/register")
    public UUID register(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String email, @RequestParam String password) {
        return authService.register(firstname, lastname, email, password);
    }

    @GetMapping("/info")
    public User info(@RequestParam UUID token) {
        return authService.info(token);
    }

    @PostMapping("/logout")
    public void logout(@RequestParam UUID token) {
        authService.logout(token);
    }
}