package imt.projetrentree.projet.exceptions.dish;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class DishInvalidPriceRangeException extends WebApplicationException {
    public DishInvalidPriceRangeException(){
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity("The dish price range is invalid")
                .build());
    }
}
