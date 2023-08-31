package imt.projetrentree.projet.services;

import imt.projetrentree.projet.dto.dish.DishCreationDTO;
import imt.projetrentree.projet.exceptions.dish.*;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.enums.DishDiet;
import imt.projetrentree.projet.models.enums.DishSortingMethod;
import imt.projetrentree.projet.models.enums.DishTag;
import imt.projetrentree.projet.repositories.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Double lowerPriceDouble = null;
        if (lowerPrice == null || lowerPrice.isBlank()){
            lowerPriceDouble = Double.MIN_VALUE;
        }else{
            try{
                lowerPriceDouble = Double.parseDouble(lowerPrice);
                if (lowerPriceDouble < 0) throw new DishNegativePriceException();
            } catch (NumberFormatException ex) {
                throw new DishPriceFormatException(lowerPrice);
            }
        }

        Double upperPriceDouble = null;
        if (upperPrice == null || upperPrice.isBlank()){
            upperPriceDouble=Double.MAX_VALUE;
        }else{
            try{
                upperPriceDouble = Double.parseDouble(upperPrice);
                if (upperPriceDouble < 0) throw new DishNegativePriceException();
            } catch (NumberFormatException ex) {
                throw new DishPriceFormatException(upperPrice);
            }
        }

        List<DishDiet> dietsList;
        if (diets == null || diets.isBlank()){
            dietsList = new ArrayList<>(Arrays.asList(DishDiet.values()));
        }else{
            dietsList = new ArrayList<>(List.of(diets.split(","))).stream().map(s -> {
                try {
                    return DishDiet.valueOf(s.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new DishDietDoesNotExistException(s);
                }
            }).toList();
        }

        List<DishTag> tagsList;
        if (tags == null || tags.isBlank()){
            tagsList = new ArrayList<>();
        }else {
            tagsList = new ArrayList<>(List.of(tags.split(","))).stream().map(s -> {
                try {
                    return DishTag.valueOf(s.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new DishTagDoesNotExistException(s);
                }
            }).toList();
        }

        List<Dish> dishes = dishRepository.findAll();
        if (searchTerm!=null && !searchTerm.isBlank()) dishes.removeIf(dish -> !dish.getName().toUpperCase().contains(searchTerm.toUpperCase()));
        Double finalLowerPriceDouble = lowerPriceDouble;
        dishes.removeIf(dish -> dish.getPrice() < finalLowerPriceDouble);
        Double finalUpperPriceDouble = upperPriceDouble;
        dishes.removeIf(dish -> dish.getPrice() > finalUpperPriceDouble);
        dishes.removeIf(dish -> !dietsList.contains(dish.getDiet()));
        dishes.removeIf(dish -> !new HashSet<>(dish.getTags()).containsAll(tagsList));
        return dishes;
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
