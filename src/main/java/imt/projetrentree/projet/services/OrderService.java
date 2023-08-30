package imt.projetrentree.projet.services;

import imt.projetrentree.projet.dto.order.OrderCreationDTO;
import imt.projetrentree.projet.exceptions.order.DishDoesNotExistException;
import imt.projetrentree.projet.exceptions.order.NotEnoughMoneyException;
import imt.projetrentree.projet.exceptions.order.OrderNotFoundException;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.Order;
import imt.projetrentree.projet.models.User;
import imt.projetrentree.projet.repositories.DishRepository;
import imt.projetrentree.projet.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private UserService userService;

    public void createOrder(User user, OrderCreationDTO orderCreationDTO) {
        Map<Long, Integer> dishesIdsWithQuantities = orderCreationDTO.getOrderContent();

        Map<Dish, Integer> dishesWithQuantities = new HashMap<>();

        for (Map.Entry<Long, Integer> entry : dishesIdsWithQuantities.entrySet()) {
            Optional<Dish> dishOptional = dishRepository.findById(entry.getKey());
            Dish dish = dishOptional.orElseThrow(() -> new DishDoesNotExistException(entry.getKey()));
            dishesWithQuantities.put(dish, entry.getValue());
        }

        double orderPrice = getTotalPrice(dishesWithQuantities);

        if(orderPrice > user.getBalance()) {
            throw new NotEnoughMoneyException();
        }

        Order order = new Order();
        order.setUser(user);
        order.setCreationDateTime(LocalDateTime.now());
        order.setDishesWithQuantities(dishesWithQuantities);

        userService.changeBalanceOfUser(user, user.getBalance() - orderPrice);

        orderRepository.save(order);
    }

    private Double getTotalPrice(Map<Dish, Integer> dishesWithQuantities) {
        double totalPrice = 0.0;
        for (Map.Entry<Dish, Integer> entry : dishesWithQuantities.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }

    public List<Order> getOrders(User user) {
        return orderRepository.findAllByUserId(user.getId());
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }
}
