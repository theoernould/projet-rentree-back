package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.dto.UserConnectionDTO;
import imt.projetrentree.projet.dto.UserCreationDTO;
import imt.projetrentree.projet.models.User;
import imt.projetrentree.projet.services.UserService;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

@Path("users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GET
    @Path("/info")
    @Produces("application/json")
    public User info(@NotNull @QueryParam("token") String token) {
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
    public void register(@RequestBody UserCreationDTO user) {
        log.info("Registering user: {}", user);
        userService.register(user);
    }

    @POST
    @Path("logout")
    public void logout(@NotNull @QueryParam("token") String token) {
        log.info("Logging out user with token: {}", token);
        userService.logout(token);
    }
}