package imt.projetrentree.projet.dto.order;

import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.User;
import lombok.Data;

import java.util.Map;

@Data
public class OrderSummaryDTO {
    private User user;
    private Map<Dish, Integer> dishesWithQuantities;
    private String address;
    private Double totalPrice;
}
