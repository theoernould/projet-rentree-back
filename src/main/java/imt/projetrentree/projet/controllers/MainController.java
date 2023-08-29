package imt.projetrentree.projet.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("main")
public class MainController {
    @GET
    @Path("welcome")
    public String welcome() {
        return "Bienvenue !";
    }
}
