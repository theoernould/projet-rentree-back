package imt.projetrentree.projet.exceptions.dish;
import imt.projetrentree.projet.models.enums.Diet;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;

public class DishDietDoesNotExistException extends WebApplicationException {
    public DishDietDoesNotExistException(String diet){
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity("The dish diet "+diet+" does not exist. Valid categories are : "+ Arrays.toString(Diet.values()))
                .build());
    }
}
