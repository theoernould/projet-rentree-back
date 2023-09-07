package imt.projetrentree.projet.models;

import imt.projetrentree.projet.dto.order.OrderDTO;
import imt.projetrentree.projet.dto.order.OrderDetailDTO;
import imt.projetrentree.projet.dto.order.OrderSummaryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_dishes", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "dish_id")
    @Column(name = "quantity")
    private Map<Dish, Integer> dishesWithQuantities;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDateTime;

    private String address;

    public Double getTotalPrice() {
        return dishesWithQuantities.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public OrderDTO toDTO() {
        return OrderDTO.builder()
                .id(id)
                .totalPrice(getTotalPrice())
                .date(creationDateTime)
                .address(address)
                .build();
    }

    public OrderDetailDTO toOrderDetailDTO() {
        Map<Long, Integer> orderContent = new HashMap<>();
        dishesWithQuantities.forEach((key, value) -> orderContent.put(key.getId(), value));
        return OrderDetailDTO.builder()
                .id(id)
                .totalPrice(getTotalPrice())
                .date(creationDateTime)
                .orderContent(orderContent)
                .address(address)
                .build();
    }

    public OrderSummaryDTO toOrderSummaryDTO() {
        OrderSummaryDTO orderSummaryDTO = new OrderSummaryDTO();
        orderSummaryDTO.setUser(user);
        orderSummaryDTO.setDishesWithQuantities(dishesWithQuantities);
        orderSummaryDTO.setTotalPrice(getTotalPrice());
        orderSummaryDTO.setAddress(address);
        return orderSummaryDTO;
    }

}