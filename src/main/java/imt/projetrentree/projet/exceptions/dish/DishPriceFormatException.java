package imt.projetrentree.projet.exceptions.dish;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class DishPriceFormatException extends WebApplicationException {
    public DishPriceFormatException(String price){
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity("The dish price "+price+" is not a valid price.")
                .build());
    }
}
