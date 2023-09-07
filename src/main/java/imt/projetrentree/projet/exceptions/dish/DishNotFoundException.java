package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;


public class DishNotFoundException extends CustomException {
    public DishNotFoundException() {
        super(Response.Status.NOT_FOUND, "The dish does not exist");
    }
}
