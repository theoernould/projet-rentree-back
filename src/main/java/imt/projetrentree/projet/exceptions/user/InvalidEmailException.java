package imt.projetrentree.projet.exceptions.user;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class InvalidEmailException extends WebApplicationException {
    public InvalidEmailException() {
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity("The email is not valid")
                .build());
    }
}
