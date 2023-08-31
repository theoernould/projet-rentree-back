package imt.projetrentree.projet.services;

import imt.projetrentree.projet.dto.dish.DishCreationDTO;
import imt.projetrentree.projet.exceptions.dish.DishNotFoundException;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.enums.DishDiet;
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

    public List<Dish> getDishesByIds(String ids) {
        if (ids == null || ids.isEmpty()) {
            return dishRepository.findAll();
        }
        List<Long> idsList = new ArrayList<>(List.of(ids.split(","))).stream().map(s -> {
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException e) {
                throw new DishNotFoundException(s);
            }
        }).toList();


        return dishRepository.findAllById(idsList);
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

    public void createDish(DishCreationDTO dish) {
        dishRepository.save(dish.toDish());
    }

    public void updateDish(Long id, DishCreationDTO updatedCreationDish) {
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
