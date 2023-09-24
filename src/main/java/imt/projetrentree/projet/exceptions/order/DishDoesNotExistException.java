package imt.projetrentree.projet.exceptions.order;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class DishDoesNotExistException extends CustomException {
    public DishDoesNotExistException(Long id) {
        super(Response.Status.NOT_FOUND, "Le plat avec l'id " + id + " n'existe pas");
    }
}