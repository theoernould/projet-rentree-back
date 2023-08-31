package imt.projetrentree.projet.models.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DishTag {
    MEAT("Meat"),
    FISH("Fish"),
    VEGETABLE("Vegetable"),
    DESSERT("Dessert"),
    DRINK("Drink"),
    HEALTHY("Healthy"),
    OTHER("Other");

    private final String label;
}