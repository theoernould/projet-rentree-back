package imt.projetrentree.projet.dto.order;

import imt.projetrentree.projet.models.OrderFilters;
import imt.projetrentree.projet.models.enums.OrderSortingMethod;
import imt.projetrentree.projet.models.enums.SortingOrder;
import lombok.Data;

import static imt.projetrentree.projet.services.UtilsService.convertToEnum;

@Data
public class OrderFiltersDTO {
    private String sortingMethod = OrderSortingMethod.DATE.name();
    private String sortingOrder = SortingOrder.ASC.name();

    public OrderFilters toOrderFilters() {
        return OrderFilters.builder()
                .sortingMethod(convertToEnum(OrderSortingMethod.class, sortingMethod, "Invalid sorting method"))
                .sortingOrder(convertToEnum(SortingOrder.class, sortingOrder, "Invalid sorting order"))
                .build();
    }
}
