package imt.projetrentree.projet.exceptions.user;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class UserNotAuthenticatedException extends WebApplicationException {
    public UserNotAuthenticatedException() {
        super(Response.status(Response.Status.UNAUTHORIZED)
                .entity("User not authenticated")
                .build());
    }
}
