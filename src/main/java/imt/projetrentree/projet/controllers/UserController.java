package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.annotations.NeedToBeAuthenticated;
import imt.projetrentree.projet.dto.user.UserCreationDTO;
import imt.projetrentree.projet.dto.user.UserCredentialsDTO;
import imt.projetrentree.projet.dto.user.UserInfoDTO;
import imt.projetrentree.projet.services.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

@Path("users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GET
    @Path("info")
    @Produces("application/json")
    @NeedToBeAuthenticated
    public UserInfoDTO info() {
        return userService.getCurrentUser().toUserInfoDTO();
    }

    @POST
    @Path("logout")
    @NeedToBeAuthenticated
    public Response logout() {
        userService.logout();
        NewCookie deleteCookie = new NewCookie("auth_token", null, "/", "", "Expired Token", 0, false);
        return Response.ok().cookie(deleteCookie).build();
    }

    @POST
    @Path("login")
    @Consumes("application/json")
    public Response login(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        log.info("Logging in user: {}", userCredentialsDTO);
        String token = userService.login(userCredentialsDTO.getEmail(), userCredentialsDTO.getPassword());

        NewCookie cookie = new NewCookie("auth_token", token, "/", "", "Auth Token", 3600, false);
        return Response.ok().cookie(cookie).build();
    }

    @GET
    @Path("resetPasswordMail")
    public void resetPasswordMal(@QueryParam("email") String email) {
        userService.resetPasswordSendMail(email);
    }

    @GET
    @Path("resetPassword")
    public void resetPassword(@QueryParam("token") String token, @QueryParam("password") String password) {
        userService.resetPassword(token, password);
    }

    @GET
    @NeedToBeAuthenticated
    @Path("resetPasswordAuthentificated")
    public void resetPasswordAuthentificated(@QueryParam("oldPassword") String oldPassword, @QueryParam("password") String password) {
        userService.resetPasswordAuthentificated(oldPassword, password);
    }


    @POST
    @Path("register")
    @Consumes("application/json")
    public void register(@RequestBody UserCreationDTO user) {
        log.info("Registering user: {}", user);
        userService.register(user);
    }

}