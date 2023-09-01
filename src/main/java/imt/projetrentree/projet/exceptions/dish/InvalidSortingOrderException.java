package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.models.enums.DishDiet;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;

public class InvalidSortingOrderException extends WebApplicationException {
    public InvalidSortingOrderException(String sortOrder){
        super(Response.status(Response.Status.NOT_FOUND)
                .entity("The sorting order "+sortOrder+" does not exist. Valid categories are : "+ Arrays.toString(DishDiet.values()))
                .build());
    }
}
