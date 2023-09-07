package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.annotations.AdminOnly;
import imt.projetrentree.projet.annotations.NeedToBeAuthenticated;
import imt.projetrentree.projet.dto.dish.DishCreationDTO;
import imt.projetrentree.projet.dto.dish.DishFiltersDTO;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.enums.DishDiet;
import imt.projetrentree.projet.models.enums.DishSortingMethod;
import imt.projetrentree.projet.models.enums.DishTag;
import imt.projetrentree.projet.services.DishService;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

import static imt.projetrentree.projet.services.UtilsService.getMapFromEnum;

@Path("dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public List<Dish> getDishesByIds(@Nullable @RequestBody DishFiltersDTO dishFiltersDTO) {
        return dishService.getDishes(dishFiltersDTO);
    }

    @GET
    @Produces("application/json")
    @Path("sortingMethods")
    public Map<DishSortingMethod, String> getDishSortingMethods() {
        return getMapFromEnum(DishSortingMethod.class);
    }

    @GET
    @Produces("application/json")
    @Path("diets")
    public Map<DishDiet, String> getDishDiets() {
        return getMapFromEnum(DishDiet.class);
    }

    @GET
    @Produces("application/json")
    @Path("tags")
    public Map<DishTag, String> getDishTags() {
        return getMapFromEnum(DishTag.class);
    }

    @GET
    @Produces("application/json")
    @Path("{id}")
    public Dish getDishById(@NotNull @PathParam("id") Long id) {
        return dishService.getDishById(id);
    }


    @POST
    @NeedToBeAuthenticated
    @AdminOnly
    @Consumes("application/json")
    public void createDish(@NotNull @RequestBody DishCreationDTO dish) {
        dishService.createDish(dish);
    }

    @PATCH
    @Path("{id}")
    @NeedToBeAuthenticated
    @AdminOnly
    @Consumes("application/json")
    public void updateDish(@NotNull @PathParam("id") Long id, @RequestBody DishCreationDTO updatedDish) {
        dishService.updateDish(id, updatedDish);
    }


    @DELETE
    @NeedToBeAuthenticated
    @AdminOnly
    @Path("{id}")
    public void deleteDish(@NotNull @PathParam("id") final Long id) {
        dishService.deleteDish(id);
    }
}
