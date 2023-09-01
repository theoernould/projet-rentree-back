package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.dto.dish.DishCreationDTO;
import imt.projetrentree.projet.dto.dish.DishFilterDTO;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.enums.DishDiet;
import imt.projetrentree.projet.models.enums.DishSortingMethod;
import imt.projetrentree.projet.models.enums.DishTag;
import imt.projetrentree.projet.services.DishService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Path("dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public List<Dish> getDishesByIds(@Nullable @RequestBody DishFilterDTO dishFilterDTO) {
        return dishService.getDishes(dishFilterDTO);
    }

    @GET
    @Produces("application/json")
    @Path("/sortingmethods")
    public Map<DishSortingMethod, String> getDishSortingMethods() {
        return dishService.getSortingMethods();
    }

    @GET
    @Produces("application/json")
    @Path("/diets")
    public Map<DishDiet, String> getDishDiets() {
        return dishService.getDiets();
    }

    @GET
    @Produces("application/json")
    @Path("/tags")
    public Map<DishTag, String> getDishTags() {
        return dishService.getDishTags();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Dish getDishById(@NotNull @PathParam("id") Long id) {
        return dishService.getDishById(id);
    }


    @POST
    @Consumes("application/json")
    public void createDish(@NotNull @RequestBody DishCreationDTO dish) {
        dishService.createDish(dish);
    }

    @PATCH
    @Path("/{id}")
    @Consumes("application/json")
    public void updateDish(@NotNull @PathParam("id") Long id, @RequestBody DishCreationDTO updatedDish) {
        dishService.updateDish(id, updatedDish);
    }


    @DELETE
    @Path("/{id}")
    public void deleteDish(@NotNull @PathParam("id") final Long id) {
        dishService.deleteDish(id);
    }
}
