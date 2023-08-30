package imt.projetrentree.projet.exceptions.dish;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;



public class DishNotFoundException extends WebApplicationException {
    public DishNotFoundException() {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity("This dish does not exist")
                .build());
    }
}
