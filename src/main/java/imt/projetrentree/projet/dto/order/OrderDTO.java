package imt.projetrentree.projet.dto.order;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderDTO {
    private Long id;
    private Double totalPrice;
    private LocalDateTime date;
    private String address;
}
