package imt.projetrentree.projet.exceptions.user;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class InvalidEmailException extends CustomException {
    public InvalidEmailException() {
        super(Response.Status.BAD_REQUEST, "L'adresse email saisie est invalide");
    }
}
