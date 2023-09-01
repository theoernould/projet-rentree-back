package imt.projetrentree.projet.exceptions.dish;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class DishInvalidSearchTextException extends WebApplicationException {
    public DishInvalidSearchTextException(){
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity("Invalid search text length (must be between 0 and 50)")
                .build());
    }
}
