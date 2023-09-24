package imt.projetrentree.projet.models.enums;


import lombok.Getter;

@Getter
public enum DishSortingMethod {
    NAME("Nom"), PRICE("Prix"), ID("Id");

    private final String label;

    DishSortingMethod(String label) {
        this.label = label;
    }
}
