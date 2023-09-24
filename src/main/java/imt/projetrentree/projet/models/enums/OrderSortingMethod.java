package imt.projetrentree.projet.models.enums;

import lombok.Getter;

@Getter
public enum OrderSortingMethod {
    DATE("Date"), PRICE("Prix");

    private final String label;

    OrderSortingMethod(String label) {
        this.label = label;
    }
}
