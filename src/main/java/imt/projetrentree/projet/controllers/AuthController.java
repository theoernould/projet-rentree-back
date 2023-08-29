package imt.projetrentree.projet.controllers;

import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

@Path("auth")
@Slf4j
public class AuthController {

    /*@Autowired
    private AuthService authService;

    @POST
    @Path("login")
    public String login(@RequestBody UserConnectionDTO userConnectionDTO) {
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
    public User info(@RequestParam String token) {
        return authService.info(token);
    }

    @POST
    @Path("logout")
    public void logout(@RequestParam String token) {
        authService.logout(token);
    }*/
}