package imt.projetrentree.projet.exceptions.order;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

public class NotEnoughMoneyException extends CustomException {
    public NotEnoughMoneyException() {
        super(Response.Status.FORBIDDEN, "Not enough money");
    }
}