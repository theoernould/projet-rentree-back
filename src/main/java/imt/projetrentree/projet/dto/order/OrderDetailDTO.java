package imt.projetrentree.projet.dto.order;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class OrderDetailDTO {
    private Long id;
    private LocalDateTime date;
    private Double totalPrice;
    private Map<Long, Integer> orderContent;
}
