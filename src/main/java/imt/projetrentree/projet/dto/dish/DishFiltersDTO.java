package imt.projetrentree.projet.dto.dish;

import imt.projetrentree.projet.models.DishFilters;
import imt.projetrentree.projet.models.enums.DishDiet;
import imt.projetrentree.projet.models.enums.DishSortingMethod;
import imt.projetrentree.projet.models.enums.DishTag;
import imt.projetrentree.projet.models.enums.SortingOrder;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Stream;

import static imt.projetrentree.projet.services.UtilsService.convertToDouble;
import static imt.projetrentree.projet.services.UtilsService.convertToEnum;

@Data
@Builder
public class DishFiltersDTO {

    private String lowerPrice = "0.0";
    private String upperPrice = "999.0";
    private List<String> diets = Stream.of(DishDiet.values()).map(Enum::name).toList();
    private List<String> tags = Stream.of(DishTag.values()).map(Enum::name).toList();
    private String searchText = "";
    private String sortBy = DishSortingMethod.ID.name();
    private String sortOrder = SortingOrder.ASC.name();

    public DishFilters toDishFilters() {
        return DishFilters.builder()
                .lowerPrice(convertToDouble(lowerPrice, "Le format du prix minimum est invalide"))
                .upperPrice(convertToDouble(upperPrice, "Le format du prix maximum est invalide"))
                .diets(diets.stream()
                        .map(diet -> convertToEnum(DishDiet.class, diet, "Valeur de régime invalide"))
                        .toList())
                .tags(tags.stream()
                        .map(tag -> convertToEnum(DishTag.class, tag, "Valeur de catégorie invalide"))
                        .toList())
                .searchText(searchText.trim())
                .sortBy(convertToEnum(DishSortingMethod.class, sortBy, "Méthode de tri invalide"))
                .sortOrder(convertToEnum(SortingOrder.class, sortOrder, "Ordre de tri invalide"))
                .build();
    }

    private <E extends Enum<E>> E convertToEnum(Class<E> enumClass, String value, String errorMessage) {
        try {
            return Enum.valueOf(enumClass, value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage).build());
        }
    }

    private Double convertToDouble(String value, String errorMessage) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage).build());
        }
    }


}