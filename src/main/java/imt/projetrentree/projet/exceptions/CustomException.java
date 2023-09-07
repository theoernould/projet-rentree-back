package imt.projetrentree.projet.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class CustomException extends WebApplicationException {
    public CustomException(Response.Status status, String message) {
        super(Response.status(status).entity(message).build());
    }
}