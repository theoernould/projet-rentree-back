package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class DishInvalidSearchTextException extends CustomException {
    public DishInvalidSearchTextException() {
        super(Response.Status.BAD_REQUEST, "Le texte de recherche doit contenir moins de 50 caractères.");
    }
}
