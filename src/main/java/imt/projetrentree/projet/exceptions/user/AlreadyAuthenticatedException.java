package imt.projetrentree.projet.exceptions.user;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class AlreadyAuthenticatedException extends WebApplicationException {
    public AlreadyAuthenticatedException() {
        super(Response.status(Response.Status.CONFLICT)
                .entity("User already authenticated")
                .build());
    }
}