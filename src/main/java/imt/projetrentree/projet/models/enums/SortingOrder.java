package imt.projetrentree.projet.models.enums;

import lombok.Getter;

@Getter
public enum SortingOrder {
    ASC("Ascendant"),
    DESC("Descendant");

    private final String label;

    SortingOrder(String label) {
        this.label = label;
    }
}
