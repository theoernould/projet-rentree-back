package imt.projetrentree.projet.dto.dish;

import imt.projetrentree.projet.models.Dish;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishCreationDTO {
    String name;
    String description;
    String image;
    String category;
    Double price;

    public Dish toDish() {
        return Dish.builder()
                .name(name)
                .description(description)
                .image(image)
                .category(category)
                .price(price)
                .build();
    }
}
