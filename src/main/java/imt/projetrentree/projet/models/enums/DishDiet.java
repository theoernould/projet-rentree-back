package imt.projetrentree.projet.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DishDiet {
    NORMAL("Normal"),
    VEGAN("Vegan"),
    VEGETARIAN("Végétarien");

    private final String label;
}
