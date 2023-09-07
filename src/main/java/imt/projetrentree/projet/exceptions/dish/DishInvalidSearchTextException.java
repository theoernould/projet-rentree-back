package imt.projetrentree.projet.exceptions.dish;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class DishInvalidSearchTextException extends CustomException {
    public DishInvalidSearchTextException() {
        super(Response.Status.BAD_REQUEST, "Invalid search text length (must be between 0 and 50)");
    }
}
