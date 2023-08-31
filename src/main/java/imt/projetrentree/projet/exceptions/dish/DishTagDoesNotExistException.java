package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.models.enums.DishTag;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;

public class DishTagDoesNotExistException extends WebApplicationException {
    public DishTagDoesNotExistException(String tag){
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity("The dish tag "+tag+" does not exist. Valid categories are : "+ Arrays.toString(DishTag.values()))
                .build());
    }
}
