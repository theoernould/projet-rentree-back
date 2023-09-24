package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.exceptions.CustomException;
import imt.projetrentree.projet.models.enums.DishSortingMethod;
import jakarta.ws.rs.core.Response;

import static imt.projetrentree.projet.services.UtilsService.getEnumKeysString;

public class InvalidSortByException extends CustomException {
    public InvalidSortByException(String sortBy) {
        super(Response.Status.NOT_FOUND, "La méthode de tri \"" + sortBy + "\" n'existe pas. Les méthodes de tri valides sont : " + getEnumKeysString(DishSortingMethod.class));
    }
}
