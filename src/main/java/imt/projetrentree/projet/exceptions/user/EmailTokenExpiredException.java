package imt.projetrentree.projet.exceptions.user;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class EmailTokenExpiredException extends CustomException {

    public EmailTokenExpiredException() {
        super(Response.Status.UNAUTHORIZED, "The email token is invalid or expired");
    }
}
