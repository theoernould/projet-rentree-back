package imt.projetrentree.projet.exceptions.user;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class SamePasswordException extends CustomException {
    public SamePasswordException() {
        super(Response.Status.BAD_REQUEST, "The old password and the new password are the same.");
    }
}