package imt.projetrentree.projet.dto.order;

import lombok.Data;

import java.util.Map;

@Data
public class OrderCreationDTO {
    private Map<Long, Integer> orderContent;
    private String address;
}
