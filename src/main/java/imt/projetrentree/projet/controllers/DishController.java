package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.repositories.DishRepository;
import imt.projetrentree.projet.services.DishService;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Path("dishes")
public class DishController {

    @Autowired
    private DishRepository dishRepository;

    @GET
    @Produces("application/json")
    public Dish[] getAllDish(){
        List<Dish> dishes = dishRepository.findAll();
        return dishes.toArray(new Dish[0]);
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Dish getDishById(@NotNull @PathParam("id") final Long id){
        Optional<Dish> l = dishRepository.findById(id);
        return l.orElse(null);
    }

    @POST
    @Consumes("application/json")
    public void createDish(@NotNull @RequestBody Dish dish){
        dishRepository.save(dish);
    }

    /*@PATCH
    @Consumes("application/json")
    @Path("{id}")
    public void updateDish(@NotNull @PathParam("id") Long id, @NotNull @RequestBody Dish dish) throws Exception {
        Optional<Dish> l = dishRepository.findById(id);

        if(l.isEmpty()) {
            throw new Exception("Plat inconnu");
        }
        else {
            Dish dishToUpdate = l.get();
            dishToUpdate.setId(dish.getId());
            dishToUpdate.setName(dish.getName());
            dishToUpdate.setCategory(dish.getCategory());
            dishToUpdate.setDescription(dish.getDescription());
            dishToUpdate.setPrice(dish.getPrice());

            dishRepository.save(dishToUpdate);
        }
    }*/

    @DELETE
    @Path("/{id}")
    public void deleteDish(@NotNull @PathParam("id") final Long id){
        dishRepository.deleteById(id);
    }
}
