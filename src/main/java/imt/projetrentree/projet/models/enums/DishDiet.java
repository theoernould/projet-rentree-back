package imt.projetrentree.projet.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DishDiet {
    NORMAL("Normal"),
    VEGAN("Vegan"),
    VEGETARIAN("Vegetarian");

    private final String label;
}
