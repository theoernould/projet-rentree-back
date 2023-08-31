package imt.projetrentree.projet.exceptions.dish;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;



public class DishNotFoundException extends WebApplicationException {
    public DishNotFoundException() {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity("The dish does not exist")
                .build());
    }
    public DishNotFoundException(String id) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity("The dish with id : '" + id + "' does not exist")
                .build());
    }
}
