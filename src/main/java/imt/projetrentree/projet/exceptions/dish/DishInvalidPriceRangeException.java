package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class DishInvalidPriceRangeException extends CustomException {
    public DishInvalidPriceRangeException() {
        super(Response.Status.BAD_REQUEST, "The dish price range is invalid");
    }
}
