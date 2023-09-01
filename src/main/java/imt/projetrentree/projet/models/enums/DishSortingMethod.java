package imt.projetrentree.projet.models.enums;


import lombok.Getter;

@Getter
public enum DishSortingMethod {
    NAME("Name"), PRICE("Price");

    private final String label;

    DishSortingMethod(String label) {
        this.label = label;
    }
}
