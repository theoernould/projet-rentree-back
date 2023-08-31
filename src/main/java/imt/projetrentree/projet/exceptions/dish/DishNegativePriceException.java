package imt.projetrentree.projet.exceptions.dish;
import imt.projetrentree.projet.models.enums.DishDiet;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;

public class DishNegativePriceException extends WebApplicationException {
    public DishNegativePriceException(){
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity("The dish price must be positive or zero")
                .build());
    }
}
