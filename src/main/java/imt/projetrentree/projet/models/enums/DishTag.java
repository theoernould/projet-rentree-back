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
    VEGAN("Vegan"),
    DAIRY("Produits laitiers"),
    POULTRY("Volaille"),
    OTHER("Autre");

    private final String label;
}