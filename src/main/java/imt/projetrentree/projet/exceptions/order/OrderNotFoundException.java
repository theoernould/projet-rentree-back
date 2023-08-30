package imt.projetrentree.projet.exceptions.order;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class OrderNotFoundException extends WebApplicationException {
    public OrderNotFoundException() {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity("Order not found")
                .build());
    }
}
