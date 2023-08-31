package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.models.enums.DishTag;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;

public class DishCategoryDoesNotExistException extends WebApplicationException {
    public DishCategoryDoesNotExistException(String category){
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity("The dish category "+category+" does not exist. Valid categories are : "+ Arrays.toString(DishTag.values()))
                .build());
    }
}
