package imt.projetrentree.projet.exceptions.user;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class BadCredentialsException extends CustomException {
    public BadCredentialsException() {
        super(Response.Status.UNAUTHORIZED, "Bad credentials");
    }
}