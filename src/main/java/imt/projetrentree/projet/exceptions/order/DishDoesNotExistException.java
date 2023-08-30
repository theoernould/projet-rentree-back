package imt.projetrentree.projet.exceptions.order;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class DishDoesNotExistException extends WebApplicationException {
    public DishDoesNotExistException(Long id) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity("Dish " + id + "does not exist")
                .build());
    }
}