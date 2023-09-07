package imt.projetrentree.projet.exceptions.user;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class AlreadyAuthenticatedException extends CustomException {
    public AlreadyAuthenticatedException() {
        super(Response.Status.CONFLICT, "User already authenticated");
    }
}