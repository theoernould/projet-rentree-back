package imt.projetrentree.projet.controllers;

import imt.projetrentree.projet.config.NeedToBeAuthenticated;
import imt.projetrentree.projet.dto.order.OrderCreationDTO;
import imt.projetrentree.projet.dto.order.OrderDTO;
import imt.projetrentree.projet.dto.order.OrderDetailDTO;
import imt.projetrentree.projet.models.Order;
import imt.projetrentree.projet.models.User;
import imt.projetrentree.projet.services.OrderService;
import imt.projetrentree.projet.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Path("orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GET
    @NeedToBeAuthenticated
    @Produces("application/json")
    @Transactional
    public List<OrderDTO> getOrdersOfUser() {
        User user = userService.getCurrentUser();
        return orderService.getOrders(user).stream().map(Order::toDTO).toList();
    }

    @GET
    @Path("{id}")
    @NeedToBeAuthenticated
    @Produces("application/json")
    @Transactional
    public OrderDetailDTO getOrderById(@PathParam("id") Long id) {
        return orderService.getOrderById(id).toOrderDetailDTO();
    }

    @POST
    @NeedToBeAuthenticated
    public void createOrder(@RequestBody OrderCreationDTO orderCreationDTO) {
        User user = userService.getCurrentUser();
        orderService.createOrder(user, orderCreationDTO);
    }
}
