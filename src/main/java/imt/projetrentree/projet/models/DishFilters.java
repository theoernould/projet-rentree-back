package imt.projetrentree.projet.models;

import imt.projetrentree.projet.models.enums.DishDiet;
import imt.projetrentree.projet.models.enums.DishSortingMethod;
import imt.projetrentree.projet.models.enums.DishTag;
import imt.projetrentree.projet.models.enums.SortingOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DishFilters {
    private Double lowerPrice;
    private Double upperPrice;
    private List<DishDiet> diets;
    private List<DishTag> tags;
    private String searchText;
    private DishSortingMethod sortBy;
    private SortingOrder sortOrder;
}
