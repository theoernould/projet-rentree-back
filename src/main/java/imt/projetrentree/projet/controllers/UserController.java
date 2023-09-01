package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.annotations.NeedToBeAuthenticated;
import imt.projetrentree.projet.dto.user.UserCredentialsDTO;
import imt.projetrentree.projet.dto.user.UserCreationDTO;
import imt.projetrentree.projet.dto.user.UserInfoDTO;
import imt.projetrentree.projet.services.UserService;
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
    @NeedToBeAuthenticated
    public UserInfoDTO info() {
        return userService.getCurrentUser().toUserInfoDTO();
    }

    @POST
    @Path("logout")
    @NeedToBeAuthenticated
    public void logout() {
        userService.logout();
    }

    @POST
    @Path("login")
    @Consumes("application/json")
    public String login(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        log.info("Logging in user: {}", userCredentialsDTO);
        return userService.login(userCredentialsDTO.getEmail(), userCredentialsDTO.getPassword());
    }

    @POST
    @Path("register")
    @Consumes("application/json")
    public void register(@RequestBody UserCreationDTO user) {
        log.info("Registering user: {}", user);
        userService.register(user);
    }

}