package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.dto.UserConnectionDTO;
import imt.projetrentree.projet.dto.UserCreationDTO;
import imt.projetrentree.projet.models.User;
import imt.projetrentree.projet.services.UserService;
import jakarta.ws.rs.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

@Path("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GET
    @Path("/")
    @Produces("application/json")
    public User info(@QueryParam("token") String token) {
        log.info("Getting user info for token: {}", token);
        return userService.info(token);
    }

    @POST
    @Path("login")
    @Consumes("application/json")
    public String login(@RequestBody UserConnectionDTO userConnectionDTO) {
        log.info("Logging in user: {}", userConnectionDTO);
        return userService.login(userConnectionDTO.getEmail(), userConnectionDTO.getPassword());
    }

    @POST
    @Path("register")
    @Consumes("application/json")
    public String register(@RequestBody UserCreationDTO user) {
        log.info("Registering user: {}", user);
        return userService.register(user);
    }

    @POST
    @Path("logout")
    public void logout(@QueryParam("token") String token) {
        log.info("Logging out user with token: {}", token);
        userService.logout(token);
    }
}