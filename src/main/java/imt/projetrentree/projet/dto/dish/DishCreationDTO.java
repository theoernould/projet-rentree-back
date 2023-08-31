package imt.projetrentree.projet.dto.dish;

import imt.projetrentree.projet.exceptions.dish.DishDietDoesNotExistException;
import imt.projetrentree.projet.exceptions.dish.DishTagDoesNotExistException;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.enums.Diet;
import imt.projetrentree.projet.models.enums.DishTag;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class DishCreationDTO {
    String name;
    String description;
    String alergens;
    String image;
    List<String> tags;
    String diet;
    Double price;

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

    public Diet ifDietNotExistThrow(String diet) {
        if (diet == null || diet.isEmpty()) throw new DishDietDoesNotExistException("null");
        try{
            return Diet.valueOf(diet);
        } catch (IllegalArgumentException e){
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
