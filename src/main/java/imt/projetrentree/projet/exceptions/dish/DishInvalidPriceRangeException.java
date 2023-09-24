package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class DishInvalidPriceRangeException extends CustomException {
    public DishInvalidPriceRangeException() {
        super(Response.Status.BAD_REQUEST, "Le prix minimum d'un plat doit être inférieur au prix maximum.");
    }
}
