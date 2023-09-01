package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.models.enums.DishDiet;
import imt.projetrentree.projet.models.enums.DishSortingMethod;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;

public class InvalidSortByException extends WebApplicationException {
    public InvalidSortByException(String sortby) {
        super(Response.status(Response.Status.NOT_FOUND).entity("The sort by " + sortby + " does not exist. Valid sort by are : " + Arrays.toString(DishSortingMethod.values())).build());
    }
}
