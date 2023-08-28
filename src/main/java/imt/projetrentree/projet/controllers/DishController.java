package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.services.DishService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping("/{id}")
    public Dish getDishById(@PathParam("id") Long id) {
        return dishService.getDishById(id).orElse(null);
    }
}
