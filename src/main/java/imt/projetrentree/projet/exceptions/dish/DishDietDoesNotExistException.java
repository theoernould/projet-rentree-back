package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.exceptions.CustomException;
import imt.projetrentree.projet.models.enums.DishDiet;
import jakarta.ws.rs.core.Response;

import static imt.projetrentree.projet.services.UtilsService.getEnumKeysString;

public class DishDietDoesNotExistException extends CustomException {
    public DishDietDoesNotExistException() {
        super(Response.Status.NOT_FOUND, "Ce régime de plat n'est pas valides. Les régimes valides sont : " + getEnumKeysString(DishDiet.class));
    }
}
