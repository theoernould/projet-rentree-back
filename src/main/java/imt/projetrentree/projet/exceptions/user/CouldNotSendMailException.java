package imt.projetrentree.projet.exceptions.user;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class CouldNotSendMailException extends WebApplicationException {
    public CouldNotSendMailException(String mail) {
        super(Response.status(Response.Status.GATEWAY_TIMEOUT)
                .entity("Could not send mail to address: " + mail)
                .build());
    }
}