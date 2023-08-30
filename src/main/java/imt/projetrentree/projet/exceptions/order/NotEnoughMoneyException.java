package imt.projetrentree.projet.exceptions.order;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class NotEnoughMoneyException extends WebApplicationException {
    public NotEnoughMoneyException() {
        super(Response.status(Response.Status.FORBIDDEN)
                .entity("Not enough money")
                .build());
    }
}