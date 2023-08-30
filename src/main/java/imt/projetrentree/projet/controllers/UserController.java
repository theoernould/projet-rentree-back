package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.dto.UserConnectionDTO;
import imt.projetrentree.projet.dto.UserCreationDTO;
import imt.projetrentree.projet.models.User;
import imt.projetrentree.projet.services.AuthService;
import jakarta.ws.rs.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

@Path("auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @POST
    @Path("login")
    @Consumes("application/json")
    public String login(@RequestBody UserConnectionDTO userConnectionDTO) {
        log.info("Logging in user: {}", userConnectionDTO);
        return authService.login(userConnectionDTO.getEmail(), userConnectionDTO.getPassword());
    }

    @POST
    @Path("register")
    @Consumes("application/json")
    public String register(@RequestBody UserCreationDTO user) {
        log.info("Registering user: {}", user);
        return authService.register(user);
    }

    @GET
    @Path("info")
    @Produces("application/json")
    public User info(@QueryParam("token") String token) {
        log.info("Getting user info for token: {}", token);
        return authService.info(token);
    }

    @POST
    @Path("logout")
    public void logout(@QueryParam("token") String token) {
        log.info("Logging out user with token: {}", token);
        authService.logout(token);
    }
}