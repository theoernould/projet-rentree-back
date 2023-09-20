package imt.projetrentree.projet.dto.order;

import imt.projetrentree.projet.models.OrderFilters;
import imt.projetrentree.projet.models.enums.OrderSortingMethod;
import imt.projetrentree.projet.models.enums.SortingOrder;
import lombok.Data;

import static imt.projetrentree.projet.services.UtilsService.convertToEnum;

@Data
public class OrderFiltersDTO {
    private String sortingMethod;
    private String sortingOrder;

    public OrderFiltersDTO(String sortingMethod, String sortingOrder) {
        this.sortingMethod = sortingMethod;
        this.sortingOrder = sortingOrder;
    }

    public OrderFilters toOrderFilters() {
        return OrderFilters.builder()
                .sortingMethod(convertToEnum(OrderSortingMethod.class, sortingMethod, "Invalid sorting method"))
                .sortingOrder(convertToEnum(SortingOrder.class, sortingOrder, "Invalid sorting order"))
                .build();
    }
}
