package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.models.enums.OrderSortingMethod;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;

public class InvalidOrderSortingMethodException extends WebApplicationException {
    public InvalidOrderSortingMethodException(String sortingMethod) {
        super(Response.status(Response.Status.NOT_FOUND).entity("The sorting order " + sortingMethod + " does not exist. Valid orders are : " + Arrays.toString(OrderSortingMethod.values())).build());
    }
}
