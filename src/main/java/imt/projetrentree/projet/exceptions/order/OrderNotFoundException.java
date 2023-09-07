package imt.projetrentree.projet.exceptions.order;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class OrderNotFoundException extends CustomException {
    public OrderNotFoundException() {
        super(Response.Status.NOT_FOUND, "Order not found");
    }
}
