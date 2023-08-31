package imt.projetrentree.projet.services;

import imt.projetrentree.projet.dto.dish.DishCreationDTO;
import imt.projetrentree.projet.exceptions.dish.DishCategoryDoesNotExistException;
import imt.projetrentree.projet.exceptions.dish.DishNotFoundException;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.DishCategory;
import imt.projetrentree.projet.repositories.DishRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
        categoryNotExistsThenThrowException(dish.getCategory());
        dishRepository.save(dish.toDish());
    }

    private void categoryNotExistsThenThrowException(String category) {
        for (DishCategory c : DishCategory.values()) {
            if (c.toString().equals(category.toUpperCase())) return;
        }
        throw new DishCategoryDoesNotExistException(category);
    }

    public void updateDish(Long id, DishCreationDTO updatedDish) {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isEmpty()) {
            throw new DishNotFoundException();
        }
        categoryNotExistsThenThrowException(updatedDish.getCategory());

        Dish existingDish = optionalDish.get();

        if (updatedDish.getName() != null) {
            existingDish.setName(updatedDish.getName());
        }
        if (updatedDish.getPrice() != null) {
            existingDish.setPrice(updatedDish.getPrice());
        }
        if (updatedDish.getCategory() != null) {
            existingDish.setCategory(updatedDish.getCategory());
        }
        if (updatedDish.getDescription() != null) {
            existingDish.setDescription(updatedDish.getDescription());
        }
        if (updatedDish.getImage() != null) {
            existingDish.setImage(updatedDish.getImage());
        }

        dishRepository.save(existingDish);
    }

    public void deleteDish(Long id) {
        Optional<Dish> d = dishRepository.findById(id);
        if (d.isEmpty()) throw new DishNotFoundException();
        dishRepository.deleteById(id);
    }
}
