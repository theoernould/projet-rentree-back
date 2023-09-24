package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class DishNegativePriceException extends CustomException {
    public DishNegativePriceException() {
        super(Response.Status.BAD_REQUEST, "Le prix d'un plat ne peut pas être négatif.");
    }
}
