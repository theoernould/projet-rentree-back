package imt.projetrentree.projet.services;

import imt.projetrentree.projet.dto.dish.DishCreationDTO;
import imt.projetrentree.projet.dto.dish.DishFiltersDTO;
import imt.projetrentree.projet.exceptions.dish.*;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.DishFilters;
import imt.projetrentree.projet.models.enums.DishDiet;
import imt.projetrentree.projet.models.enums.DishSortingMethod;
import imt.projetrentree.projet.models.enums.DishTag;
import imt.projetrentree.projet.models.enums.SortingOrder;
import imt.projetrentree.projet.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    public Dish getDishById(Long id) {
        Optional<Dish> d = dishRepository.findById(id);
        if (d.isEmpty()) throw new DishNotFoundException();
        return d.get();
    }

    public List<Dish> getDishes(String searchTerm,
                                String lowerPrice,
                                String upperPrice,
                                String diets,
                                String tags,
                                String sortBy,
                                String sortOrder) {
        DishFiltersDTO dishFilterDTO = getDishFilterDTO(searchTerm, lowerPrice, upperPrice, diets, tags, sortBy, sortOrder);
        return filterDishes(dishFilterDTO);
    }

    private DishFiltersDTO getDishFilterDTO(String searchTerm,
                                           String lowerPrice,
                                           String upperPrice,
                                           String diets,
                                           String tags,
                                           String sortBy,
                                           String sortOrder) {
        List<String> dietsDefault = Stream.of(DishDiet.values()).map(Enum::name).toList();
        List<String> tagsDefault = Stream.of(DishTag.values()).map(Enum::name).toList();
        return DishFiltersDTO.builder()
                .searchText(searchTerm)
                .lowerPrice(lowerPrice)
                .upperPrice(upperPrice)
                .sortBy(sortBy)
                .sortOrder(sortOrder)
                .diets(getStringListFromStrings(diets, dietsDefault, () -> {
                    throw new DishDietDoesNotExistException();
                }))
                .tags(getStringListFromStrings(tags, tagsDefault, () -> {
                    throw new DishTagDoesNotExistException();
                }))
                .build();
    }

    private List<String> getStringListFromStrings(String str, List<String> defaultList, Runnable exception) {
        try {
            return Objects.isNull(str) || str.isEmpty() ? defaultList : Arrays.asList(str.split(","));
        } catch (Exception e) {
            exception.run();
        }
        return defaultList;
    }

    private List<Dish> filterDishes(DishFiltersDTO dishFiltersDTO) {
        DishFilters dishFilters = dishFiltersDTO.toDishFilters();
        verifyFilters(dishFilters);
        List<DishDiet> diets = dishFilters.getDiets();
        List<DishTag> tags = dishFilters.getTags();
        return dishRepository.findAll().stream()
                .filter(dish -> dish.getPrice() >= dishFilters.getLowerPrice())
                .filter(dish -> dish.getPrice() <= dishFilters.getUpperPrice())
                .filter(dish -> diets.isEmpty() || diets.contains(dish.getDiet()))
                .filter(dish -> tags.isEmpty() || tags.stream().anyMatch(dish.getTags()::contains))
                .filter(dish -> searchDish(dish, dishFilters.getSearchText()))
                .sorted(getComparator(dishFilters.getSortBy(), dishFilters.getSortOrder()))
                .toList();
    }

    private boolean searchDish(Dish dish, String searchText) {
        String lowerCaseSearchText = searchText.toLowerCase();
        return dish.getName().toLowerCase().contains(lowerCaseSearchText) || dish.getDescription().toLowerCase().contains(lowerCaseSearchText);
    }

    private void verifyFilters(DishFilters filters) {
        if (filters.getLowerPrice() < 0) {
            throw new DishNegativePriceException();
        }
        if (filters.getUpperPrice() < 0) {
            throw new DishNegativePriceException();
        }
        if (filters.getLowerPrice() > filters.getUpperPrice()) {
            throw new DishInvalidPriceRangeException();
        }
        if (filters.getSearchText().trim().length() > 50) {
            throw new DishInvalidSearchTextException();
        }
    }

    private Comparator<Dish> getComparator(DishSortingMethod sortBy, SortingOrder order) {
        Comparator<Dish> comparator = switch (sortBy) {
            case NAME -> Comparator.comparing(Dish::getName, String.CASE_INSENSITIVE_ORDER);
            case PRICE -> Comparator.comparing(Dish::getPrice);
            case ID -> Comparator.comparing(Dish::getId);
            default -> throw new InvalidSortByException(sortBy.getLabel());
        };
        return order == SortingOrder.ASC ? comparator : comparator.reversed();
    }

    public void createDish(DishCreationDTO dish) {
        if (dish.getPrice() < 0) throw new DishNegativePriceException();
        dishRepository.save(dish.toDish());
    }

    public void updateDish(Long id, DishCreationDTO updatedCreationDish) {
        if (updatedCreationDish.getPrice() < 0) throw new DishNegativePriceException();
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isEmpty()) {
            throw new DishNotFoundException();
        }

        Dish existingDish = optionalDish.get();

        Dish updatedDish = updatedCreationDish.toDish();

        if (updatedDish.getName() != null) {
            existingDish.setName(updatedDish.getName());
        }
        if (updatedDish.getDescription() != null) {
            existingDish.setDescription(updatedDish.getDescription());
        }
        if (updatedDish.getAlergens() != null) {
            existingDish.setAlergens(updatedDish.getAlergens());
        }
        if (updatedDish.getImage() != null) {
            existingDish.setImage(updatedDish.getImage());
        }
        if (updatedDish.getDiet() != null) {
            existingDish.setDiet(updatedDish.getDiet());
        }
        if (updatedDish.getTags() != null) {
            existingDish.setTags(updatedDish.getTags());
        }
        if (updatedDish.getPrice() != null) {
            existingDish.setPrice(updatedDish.getPrice());
        }

        dishRepository.save(existingDish);
    }

    public void deleteDish(Long id) {
        Optional<Dish> d = dishRepository.findById(id);
        if (d.isEmpty()) throw new DishNotFoundException();
        dishRepository.deleteById(id);
    }
}
