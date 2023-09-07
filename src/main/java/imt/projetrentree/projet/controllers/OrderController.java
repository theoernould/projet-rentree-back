package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.annotations.NeedToBeAuthenticated;
import imt.projetrentree.projet.dto.order.OrderCreationDTO;
import imt.projetrentree.projet.dto.order.OrderDTO;
import imt.projetrentree.projet.dto.order.OrderDetailDTO;
import imt.projetrentree.projet.dto.order.OrderFiltersDTO;
import imt.projetrentree.projet.models.Order;
import imt.projetrentree.projet.models.User;
import imt.projetrentree.projet.models.enums.OrderSortingMethod;
import imt.projetrentree.projet.services.OrderService;
import imt.projetrentree.projet.services.UserService;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

import static imt.projetrentree.projet.services.UtilsService.getMapFromEnum;

@Path("orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @NeedToBeAuthenticated
    @Transactional
    public List<OrderDTO> getOrdersOfUser(@Nullable @RequestBody OrderFiltersDTO filtersDTO) {
        User user = userService.getCurrentUser();
        return orderService.getOrders(user, filtersDTO).stream().map(Order::toDTO).toList();
    }

    @GET
    @Path("sortingMethods")
    @Produces("application/json")
    public Map<OrderSortingMethod, String> getOrderSortingMethods() {
        return getMapFromEnum(OrderSortingMethod.class);
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    @NeedToBeAuthenticated
    @Transactional
    public OrderDetailDTO getOrderById(@PathParam("id") Long id) {
        return orderService.getOrderById(id).toOrderDetailDTO();
    }

    @POST
    @Consumes("application/json")
    @NeedToBeAuthenticated
    public void createOrder(@RequestBody OrderCreationDTO orderCreationDTO) {
        User user = userService.getCurrentUser();
        orderService.createOrder(user, orderCreationDTO);
    }
}
