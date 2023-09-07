package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.exceptions.CustomException;
import imt.projetrentree.projet.models.enums.DishTag;
import jakarta.ws.rs.core.Response;

import static imt.projetrentree.projet.services.UtilsService.getEnumKeysString;

public class DishTagDoesNotExistException extends CustomException {
    public DishTagDoesNotExistException(String tag) {
        super(Response.Status.NOT_FOUND, "The dish tag " + tag + " does not exist. Valid categories are : " + getEnumKeysString(DishTag.class));
    }
}
