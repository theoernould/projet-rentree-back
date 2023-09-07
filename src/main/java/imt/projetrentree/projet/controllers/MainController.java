package imt.projetrentree.projet.controllers;


import imt.projetrentree.projet.models.enums.SortingOrder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.HashMap;
import java.util.Map;

import static imt.projetrentree.projet.ProjetApplication.APP_NAME;
import static imt.projetrentree.projet.services.UtilsService.getMapFromEnum;

@Path("")
public class MainController {

    @GET
    public String status() {
        return APP_NAME + " API is running";
    }

    @GET
    @Path("main/sortingOrders")
    @Produces("application/json")
    public Map<SortingOrder, String> getSortingMethods() {
        return getMapFromEnum(SortingOrder.class);
    }
}
