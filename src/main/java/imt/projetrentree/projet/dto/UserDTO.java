package imt.projetrentree.projet.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    String email;
    String username;
    String password;
}