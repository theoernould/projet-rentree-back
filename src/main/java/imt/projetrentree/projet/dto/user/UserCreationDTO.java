package imt.projetrentree.projet.dto.user;

import imt.projetrentree.projet.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
            .isAdmin(false)
            .build();
    }
}
