package imt.projetrentree.projet.dto;

import imt.projetrentree.projet.models.User;
import lombok.Data;

@Data
public class UserCreationDTO {
    String email;
    String password;
    String firstname;
    String lastname;

    public User toUser(Double balance) {
        return User.builder()
            .email(email)
            .password(password)
            .firstname(firstname)
            .lastname(lastname)
            .balance(balance)
            .build();
    }
}
