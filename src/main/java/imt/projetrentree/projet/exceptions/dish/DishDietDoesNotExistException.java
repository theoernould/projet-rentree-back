package imt.projetrentree.projet.exceptions.dish;
import imt.projetrentree.projet.models.enums.DishDiet;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;

public class DishDietDoesNotExistException extends WebApplicationException {
    public DishDietDoesNotExistException(String diet){
        super(Response.status(Response.Status.NOT_FOUND)
                .entity("The dish diet "+diet+" does not exist. Valid categories are : "+ Arrays.toString(DishDiet.values()))
                .build());
    }
}
