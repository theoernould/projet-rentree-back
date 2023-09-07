package imt.projetrentree.projet.dto.user;

import imt.projetrentree.projet.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreationDTO {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String address;

    public User toUser(Double balance) {
        return User.builder()
                .email(email)
                .password(password)
                .firstname(firstname)
                .lastname(lastname)
                .balance(balance)
                .address(address)
                .isAdmin(false)
                .build();
    }
}
