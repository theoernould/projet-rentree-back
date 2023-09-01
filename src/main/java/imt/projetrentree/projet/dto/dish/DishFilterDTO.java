package imt.projetrentree.projet.dto.dish;

import imt.projetrentree.projet.config.EnumValid;
import imt.projetrentree.projet.models.enums.DishDiet;
import imt.projetrentree.projet.models.enums.DishSortingMethod;
import imt.projetrentree.projet.models.enums.DishTag;
import imt.projetrentree.projet.models.enums.SortingOrder;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class DishFilterDTO {

    @PositiveOrZero(message = "Lower price must be positive or zero")
    private Double lowerPrice = 0.0;

    @PositiveOrZero(message = "Upper price must be positive or zero")
    private Double upperPrice = 999.0;

    @EnumValid(enumClass = DishDiet.class, message = "Invalid diet value")
    private List<DishDiet> diets = List.of(DishDiet.values());

    @EnumValid(enumClass = DishTag.class, message = "Invalid tag value")
    private List<DishTag> tags = List.of(DishTag.values());

    @Size(max = 255, message = "Search text length must be less than or equal to 255 characters")
    private String searchText = "";

    @EnumValid(enumClass = DishSortingMethod.class, message = "Invalid sort method")
    private DishSortingMethod sortBy = DishSortingMethod.NAME;

    @EnumValid(enumClass = SortingOrder.class, message = "Invalid sort order")
    private SortingOrder sortOrder = SortingOrder.ASC;
}