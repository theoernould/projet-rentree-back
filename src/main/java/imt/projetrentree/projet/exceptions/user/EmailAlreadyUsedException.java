package imt.projetrentree.projet.exceptions.user;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class EmailAlreadyUsedException extends CustomException {
    public EmailAlreadyUsedException() {
        super(Response.Status.CONFLICT, "Email already used");
    }
}
