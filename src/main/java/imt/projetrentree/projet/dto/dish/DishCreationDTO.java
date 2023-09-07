package imt.projetrentree.projet.dto.dish;

import imt.projetrentree.projet.exceptions.dish.DishDietDoesNotExistException;
import imt.projetrentree.projet.exceptions.dish.DishTagDoesNotExistException;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.enums.DishDiet;
import imt.projetrentree.projet.models.enums.DishTag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class DishCreationDTO {
    private String name;
    private String description;
    private String alergens;
    private String image;
    private List<String> tags;
    private String diet;

    @NotNull(message = "Price is mandatory")
    @PositiveOrZero(message = "Price must be positive or zero")
    private Double price;

    public Dish toDish() {
        return Dish.builder()
                .name(name)
                .description(description)
                .alergens(alergens)
                .image(image)
                .tags(ifDishTagNotExistThrow(tags))
                .diet(ifDietNotExistThrow(diet))
                .price(price)
                .build();
    }

    public DishDiet ifDietNotExistThrow(String diet) {
        if (diet == null || diet.isEmpty()) throw new DishDietDoesNotExistException("null");
        try {
            return DishDiet.valueOf(diet);
        } catch (IllegalArgumentException e) {
            throw new DishDietDoesNotExistException(diet);
        }
    }

    public List<DishTag> ifDishTagNotExistThrow(List<String> tags) {
        if (tags == null || tags.isEmpty()) return new ArrayList<>();
        return tags.stream().map(s -> {
            try {
                return DishTag.valueOf(s);
            } catch (IllegalArgumentException e) {
                throw new DishTagDoesNotExistException(s);
            }
        }).toList();
    }
}
