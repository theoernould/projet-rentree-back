package imt.projetrentree.projet.models;

import imt.projetrentree.projet.models.enums.OrderSortingMethod;
import imt.projetrentree.projet.models.enums.SortingOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderFilters {
    private OrderSortingMethod sortingMethod;
    private SortingOrder sortingOrder;
}
