package imt.projetrentree.projet.services;

import imt.projetrentree.projet.dto.dish.DishCreationDTO;
import imt.projetrentree.projet.exceptions.dish.*;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.enums.DishDiet;
import imt.projetrentree.projet.models.enums.DishSortingMethod;
import imt.projetrentree.projet.models.enums.DishTag;
import imt.projetrentree.projet.models.enums.SortingOrder;
import imt.projetrentree.projet.repositories.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    public Dish getDishById(Long id) {
        Optional<Dish> d = dishRepository.findById(id);
        if (d.isEmpty()) throw new DishNotFoundException();
        return d.get();
    }

    public List<Dish> getDishes(String searchTerm, String lowerPrice, String upperPrice, String diets, String tags, String sortby, String sortorder) {
        Double lowerPriceDouble = parseLowerPrice(lowerPrice);
        Double upperPriceDouble = parseUpperPrice(upperPrice);
        List<DishDiet> dietsList = parseDiets(diets);
        List<DishTag> tagsList = parseTags(tags);
        SortingOrder order = parseSortingOrder(sortorder);
        DishSortingMethod sortByMethod = parseSortBy(sortby);

        return dishRepository.findAll().stream()
                .filter(dish -> searchTerm == null || dish.getName().toUpperCase().contains(searchTerm.toUpperCase()))
                .filter(dish -> dish.getPrice() >= lowerPriceDouble && dish.getPrice() <= upperPriceDouble)
                .filter(dish -> dietsList.contains(dish.getDiet()))
                .filter(dish -> new HashSet<>(dish.getTags()).containsAll(tagsList))
                .sorted(getComparator(sortByMethod, order))
                .toList();
    }

    private Double parseLowerPrice(String lowerPrice) {
        if (lowerPrice == null || lowerPrice.isBlank()) return Double.MIN_VALUE;
        try {
            Double value = Double.parseDouble(lowerPrice);
            if (value < 0) throw new DishNegativePriceException();
            return value;
        } catch (NumberFormatException ex) {
            throw new DishPriceFormatException(lowerPrice);
        }
    }

    private Double parseUpperPrice(String upperPrice) {
        if (upperPrice == null || upperPrice.isBlank()) return Double.MAX_VALUE;
        try {
            Double value = Double.parseDouble(upperPrice);
            if (value < 0) throw new DishNegativePriceException();
            return value;
        } catch (NumberFormatException ex) {
            throw new DishPriceFormatException(upperPrice);
        }
    }

    private List<DishDiet> parseDiets(String diets) {
        if (diets == null || diets.isBlank()) return Arrays.asList(DishDiet.values());
        return Arrays.stream(diets.split(","))
                .map(String::toUpperCase)
                .map(s -> {
                    try {
                        return DishDiet.valueOf(s);
                    } catch (IllegalArgumentException e) {
                        throw new DishDietDoesNotExistException(s);
                    }
                }).collect(Collectors.toList());
    }

    private List<DishTag> parseTags(String tags) {
        if (tags == null || tags.isBlank()) return new ArrayList<>();
        return Arrays.stream(tags.split(","))
                .map(String::toUpperCase)
                .map(s -> {
                    try {
                        return DishTag.valueOf(s);
                    } catch (IllegalArgumentException e) {
                        throw new DishTagDoesNotExistException(s);
                    }
                }).collect(Collectors.toList());
    }

    private DishSortingMethod parseSortBy(String sortby) {
        try {
            return DishSortingMethod.valueOf(sortby.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidSortByException(sortby);
        }
    }

    private SortingOrder parseSortingOrder(String sortorder) {
        try {
            return SortingOrder.valueOf(sortorder.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidSortingOrderException(sortorder);
        }
    }

    private Comparator<Dish> getComparator(DishSortingMethod sortby, SortingOrder order) {
        Comparator<Dish> comparator;
        switch (sortby) {
            case NAME:
                comparator = Comparator.comparing(Dish::getName);
                break;
            case PRICE:
                comparator = Comparator.comparing(Dish::getPrice);
                break;
            default:
                throw new InvalidSortByException(sortby.getLabel());
        }
        return order == SortingOrder.ASC ? comparator : comparator.reversed();
    }

    public Map<DishDiet,String> getDiets(){
        Map<DishDiet,String> map = new HashMap<>();
        for (DishDiet dishDiet : DishDiet.values()) {
            map.put(dishDiet, dishDiet.getLabel());
        }
        return map;
    }

    public Map<DishTag,String> getDishTags(){
        Map<DishTag,String> map = new HashMap<>();
        for (DishTag dishtag : DishTag.values()) {
            map.put(dishtag, dishtag.getLabel());
        }
        return map;
    }

    public Map<DishSortingMethod,String> getSortingMethods(){
        Map<DishSortingMethod,String> map = new HashMap<>();
        for (DishSortingMethod dishSortingMethod : DishSortingMethod.values()) {
            map.put(dishSortingMethod, dishSortingMethod.getLabel());
        }
        return map;
    }

    public void createDish(DishCreationDTO dish) {
        if (dish.getPrice()<0) throw new DishNegativePriceException();
        dishRepository.save(dish.toDish());
    }

    public void updateDish(Long id, DishCreationDTO updatedCreationDish) {
        if (updatedCreationDish.getPrice()<0) throw new DishNegativePriceException();
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
