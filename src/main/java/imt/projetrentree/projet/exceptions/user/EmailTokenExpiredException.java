package imt.projetrentree.projet.exceptions.user;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class EmailTokenExpiredException extends WebApplicationException
{

    public EmailTokenExpiredException() {
        super(Response.status(Response.Status.UNAUTHORIZED)
                .entity("The email token is invalid or expired")
                .build());
    }
}
