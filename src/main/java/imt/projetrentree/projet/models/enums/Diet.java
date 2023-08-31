package imt.projetrentree.projet.models.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Diet {
    NORMAL("Normal"),
    VEGAN("Vegan"),
    VEGETARIAN("Vegetarian");

    private final String label;
}
