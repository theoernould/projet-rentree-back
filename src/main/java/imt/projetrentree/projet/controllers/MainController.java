package imt.projetrentree.projet.controllers;


import imt.projetrentree.projet.models.enums.SortingOrder;
import jakarta.ws.rs.*;
import java.util.HashMap;
import java.util.Map;

@Path("main")
public class MainController {

    @GET
    @Path("welcome")
    public String welcome() {
        return "Bienvenue !";
    }

    @GET
    @Path("sortingorders")
    @Produces("application/json")
    public Map<SortingOrder,String> getSortingMethods(){
        Map<SortingOrder,String> map = new HashMap<>();
        for (SortingOrder sortingOrder : SortingOrder.values()) {
            map.put(sortingOrder, sortingOrder.getLabel());
        }
        return map;
    }
}
