package imt.projetrentree.projet.exceptions.user;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class EmailAlreadyUsedException extends WebApplicationException {
    public EmailAlreadyUsedException() {
        super(Response.status(Response.Status.CONFLICT)
                .entity("Email already used")
                .build());
    }
}
