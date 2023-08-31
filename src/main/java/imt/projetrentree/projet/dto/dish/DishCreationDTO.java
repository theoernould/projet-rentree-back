package imt.projetrentree.projet.dto.dish;

import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.enums.Diet;
import imt.projetrentree.projet.models.enums.DishTag;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DishCreationDTO {
    String name;
    String description;
    String image;
    List<DishTag> tags;
    Diet diet;
    Double price;

    public Dish toDish() {
        return Dish.builder()
                .name(this.name)
                .description(this.description)
                .image(this.image)
                .tags(this.tags)
                .diet(this.diet)
                .price(this.price)
                .build();
    }
}
