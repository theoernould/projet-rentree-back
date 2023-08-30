package imt.projetrentree.projet.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDTO {
    private String email;
    private String firstname;
    private String lastname;
    private Double balance;
}
