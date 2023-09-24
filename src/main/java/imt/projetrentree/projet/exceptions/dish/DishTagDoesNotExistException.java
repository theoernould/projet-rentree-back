package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.exceptions.CustomException;
import imt.projetrentree.projet.models.enums.DishTag;
import jakarta.ws.rs.core.Response;

import static imt.projetrentree.projet.services.UtilsService.getEnumKeysString;

public class DishTagDoesNotExistException extends CustomException {
    public DishTagDoesNotExistException() {
        super(Response.Status.NOT_FOUND, "Cette catégorie de plat n'existe pas. Les catégories valides sont : " + getEnumKeysString(DishTag.class));
    }
}
