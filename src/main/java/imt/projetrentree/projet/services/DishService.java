package imt.projetrentree.projet.services;

import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.repositories.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Optional<Dish> getDishById(Long id) {
        return dishRepository.findById(id);
    }
}
