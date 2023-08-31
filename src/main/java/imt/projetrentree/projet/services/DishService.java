package imt.projetrentree.projet.services;

import imt.projetrentree.projet.dto.dish.DishCreationDTO;
import imt.projetrentree.projet.exceptions.dish.DishCategoryDoesNotExistException;
import imt.projetrentree.projet.exceptions.dish.DishNotFoundException;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.enums.DishTag;
import imt.projetrentree.projet.repositories.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void createDish(DishCreationDTO dish) {
        dishRepository.save(dish.toDish());
    }

    public void updateDish(Long id, DishCreationDTO updatedDish) {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isEmpty()) {
            throw new DishNotFoundException();
        }

        Dish existingDish = optionalDish.get();

        if (updatedDish.getName() != null) {
            existingDish.setName(updatedDish.getName());
        }
        if (updatedDish.getDescription() != null) {
            existingDish.setDescription(updatedDish.getDescription());
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
