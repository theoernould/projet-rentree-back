package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.exceptions.dish.DishCategoryDoesNotExistException;
import imt.projetrentree.projet.exceptions.dish.DishNotFoundException;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.DishCategory;
import imt.projetrentree.projet.repositories.DishRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import org.bouncycastle.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Path("dishes")
public class DishController {

    @Autowired
    private DishRepository dishRepository;

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Dish getDishById(@NotNull @PathParam("id") Long id){
        Optional<Dish> d = dishRepository.findById(id);
        if (d.isEmpty()) throw new DishNotFoundException();
        return d.get();
    }

    @GET
    @Produces("application/json")
    public List<Dish> getDishesByIds(@QueryParam("ids") String ids) {
        if (ids == null || ids.isEmpty()){
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

    @POST
    @Consumes("application/json")
    public void createDish(@NotNull @RequestBody Dish dish) {
        categoryNotExistsThenThrowException(dish.getCategory());
        dishRepository.save(dish);
    }

    private void categoryNotExistsThenThrowException(String category) {
    	for(DishCategory c : DishCategory.values()) {
    		if(c.toString().equals(category.toUpperCase())) return;
    	}
        throw new DishCategoryDoesNotExistException(category);
    }

    @PATCH
    @Path("/{id}")
    @Consumes("application/json")
    public void updateDish(@NotNull @PathParam("id") Long id, @RequestBody Dish updatedDish) {
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


    @DELETE
    @Path("/{id}")
    public void deleteDish(@NotNull @PathParam("id") final Long id){
        Optional<Dish> d = dishRepository.findById(id);
        if (d.isEmpty()) throw new DishNotFoundException();
        dishRepository.deleteById(id);
    }
}
