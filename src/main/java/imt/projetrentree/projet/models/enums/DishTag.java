package imt.projetrentree.projet.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DishTag {
    MEAT("Viande"),
    FISH("Poisson"),
    VEGETABLE("Legume"),
    DESSERT("Dessert"),
    DRINK("Boisson"),
    HEALTHY("Healthy"),
    OTHER("Autre");

    private final String label;
}