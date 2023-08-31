package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.dto.dish.DishCreationDTO;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.enums.Diet;
import imt.projetrentree.projet.models.enums.DishTag;
import imt.projetrentree.projet.services.DishService;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @GET
    @Produces("application/json")
    public List<Dish> getDishesByIds(@QueryParam("ids") String ids) {
        return dishService.getDishesByIds(ids);
    }

    @GET
    @Produces("application/json")
    @Path("/diets")
    public Map<Diet,String> getDishDiets() {
        Map<Diet,String> map = new HashMap<>();
        for (Diet diet : Diet.values()) {
            map.put(diet, diet.getLabel());
        }
        return map;
    }

    @GET
    @Produces("application/json")
    @Path("/tags")
    public Map<DishTag,String> getDishTags() {
        Map<DishTag,String> map = new HashMap<>();
        for (DishTag dishtag : DishTag.values()) {
            map.put(dishtag, dishtag.getLabel());
        }
        return map;
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
