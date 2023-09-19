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
    public List<Dish> getDishesByIds(@QueryParam("search") @DefaultValue("") String searchTerm,
                                     @QueryParam("lowerPrice") @DefaultValue("0.0") String lowerPrice,
                                     @QueryParam("upperPrice") @DefaultValue("999.0") String upperPrice,
                                     @QueryParam("diets") String diets,
                                     @QueryParam("tags") String tags,
                                     @QueryParam("sortBy") @DefaultValue("ID") String sortBy,
                                     @QueryParam("sortOrder") @DefaultValue("ASC") String sortOrder) {
        return dishService.getDishes(searchTerm, lowerPrice, upperPrice, diets, tags, sortBy, sortOrder);
    }

    @GET
    @Path("sortingMethods")
    @Produces("application/json")
    public Map<DishSortingMethod, String> getDishSortingMethods() {
        return getMapFromEnum(DishSortingMethod.class);
    }

    @GET
    @Path("diets")
    @Produces("application/json")
    public Map<DishDiet, String> getDishDiets() {
        return getMapFromEnum(DishDiet.class);
    }

    @GET
    @Path("tags")
    @Produces("application/json")
    public Map<DishTag, String> getDishTags() {
        return getMapFromEnum(DishTag.class);
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Dish getDishById(@NotNull @PathParam("id") Long id) {
        return dishService.getDishById(id);
    }


    @POST
    @Consumes("application/json")
    @NeedToBeAuthenticated
    @AdminOnly
    public void createDish(@NotNull @RequestBody DishCreationDTO dish) {
        dishService.createDish(dish);
    }

    @PATCH
    @Path("{id}")
    @Consumes("application/json")
    @NeedToBeAuthenticated
    @AdminOnly
    public void updateDish(@NotNull @PathParam("id") Long id, @RequestBody DishCreationDTO updatedDish) {
        dishService.updateDish(id, updatedDish);
    }


    @DELETE
    @Path("{id}")
    @NeedToBeAuthenticated
    @AdminOnly
    public void deleteDish(@NotNull @PathParam("id") final Long id) {
        dishService.deleteDish(id);
    }
}
