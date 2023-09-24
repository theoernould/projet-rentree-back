package imt.projetrentree.projet.exceptions.user;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class CouldNotSendMailException extends CustomException {
    public CouldNotSendMailException(String mail) {
        super(Response.Status.GATEWAY_TIMEOUT, "Impossible d'envoyer un mail à l'adresse \"" + mail + "\"");
    }
}