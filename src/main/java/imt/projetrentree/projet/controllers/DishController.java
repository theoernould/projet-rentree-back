package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.dto.dish.DishCreationDTO;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.services.DishService;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Path("dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Dish getDishById(@NotNull @PathParam("id") Long id) {
        return dishService.getDishById(id);
    }

    @GET
    @Produces("application/json")
    public List<Dish> getDishesByIds(@QueryParam("ids") String ids) {
        return dishService.getDishesByIds(ids);
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
