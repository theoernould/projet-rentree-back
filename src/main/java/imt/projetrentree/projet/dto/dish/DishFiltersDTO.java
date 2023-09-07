package imt.projetrentree.projet.dto.dish;

import imt.projetrentree.projet.models.DishFilters;
import imt.projetrentree.projet.models.enums.DishDiet;
import imt.projetrentree.projet.models.enums.DishSortingMethod;
import imt.projetrentree.projet.models.enums.DishTag;
import imt.projetrentree.projet.models.enums.SortingOrder;
import lombok.Data;

import java.util.List;
import java.util.stream.Stream;

import static imt.projetrentree.projet.services.UtilsService.convertToDouble;
import static imt.projetrentree.projet.services.UtilsService.convertToEnum;

@Data
public class DishFiltersDTO {

    private String lowerPrice = "0.0";
    private String upperPrice = "999.0";
    private List<String> diets = Stream.of(DishDiet.values()).map(Enum::name).toList();
    private List<String> tags = Stream.of(DishTag.values()).map(Enum::name).toList();
    private String searchText = "";
    private String sortBy = DishSortingMethod.ID.name();
    private String sortOrder = SortingOrder.ASC.name();

    public DishFilters toDishFilters() {
        return DishFilters.builder()
                .lowerPrice(convertToDouble(lowerPrice, "Invalid lower price format"))
                .upperPrice(convertToDouble(upperPrice, "Invalid upper price format"))
                .diets(diets.stream()
                        .map(diet -> convertToEnum(DishDiet.class, diet, "Invalid diet value"))
                        .toList())
                .tags(tags.stream()
                        .map(tag -> convertToEnum(DishTag.class, tag, "Invalid tag value"))
                        .toList())
                .searchText(searchText.trim())
                .sortBy(convertToEnum(DishSortingMethod.class, sortBy, "Invalid sort method"))
                .sortOrder(convertToEnum(SortingOrder.class, sortOrder, "Invalid sort order"))
                .build();
    }

}