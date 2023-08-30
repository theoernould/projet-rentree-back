package imt.projetrentree.projet.exceptions.user;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class BadCredentialsException extends WebApplicationException {
    public BadCredentialsException() {
        super(Response.status(Response.Status.UNAUTHORIZED)
                .entity("Bad credentials")
                .build());
    }
}