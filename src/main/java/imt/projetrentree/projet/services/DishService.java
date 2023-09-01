package imt.projetrentree.projet.services;

import imt.projetrentree.projet.dto.dish.DishCreationDTO;
import imt.projetrentree.projet.dto.dish.DishFilterDTO;
import imt.projetrentree.projet.exceptions.dish.*;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.DishFilters;
import imt.projetrentree.projet.models.enums.DishDiet;
import imt.projetrentree.projet.models.enums.DishSortingMethod;
import imt.projetrentree.projet.models.enums.DishTag;
import imt.projetrentree.projet.models.enums.SortingOrder;
import imt.projetrentree.projet.repositories.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
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

    public List<Dish> getDishes(DishFilterDTO dishFilterDTO) {
        List<Dish> dishes = dishRepository.findAll();
        return Objects.isNull(dishFilterDTO) ? dishes : filterDishes(dishFilterDTO);
    }

    private List<Dish> filterDishes(DishFilterDTO dishFilterDTO) {
        DishFilters dishFilters = dishFilterDTO.toDishFilters();
        verifyFilters(dishFilters);
        return dishRepository.findAll().stream()
                .filter(dish -> dish.getPrice() >= dishFilters.getLowerPrice())
                .filter(dish -> dish.getPrice() <= dishFilters.getUpperPrice())
                .filter(dish -> dishFilters.getDiets().contains(dish.getDiet()))
                .filter(dish -> dishFilters.getTags().stream().anyMatch(dish.getTags()::contains))
                .filter(dish -> dish.getName().toLowerCase().contains(dishFilters.getSearchText().toLowerCase()))
                .sorted(getComparator(dishFilters.getSortBy(), dishFilters.getSortOrder()))
                .toList();
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
        Comparator<Dish> comparator;
        switch (sortBy) {
            case NAME:
                comparator = Comparator.comparing(Dish::getName, String.CASE_INSENSITIVE_ORDER);
                break;
            case PRICE:
                comparator = Comparator.comparing(Dish::getPrice);
                break;
            default:
                throw new InvalidSortByException(sortBy.getLabel());
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
