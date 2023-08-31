package imt.projetrentree.projet.models.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Diet {
    NORMAL("Normal"),
    VEGAN("Vegan"),
    VEGETARIAN("Vegetarian");

    private final String label;
}
