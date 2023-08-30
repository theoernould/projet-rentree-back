package imt.projetrentree.projet.models;

import imt.projetrentree.projet.dto.user.UserInfoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;
    String password;

    String firstname;
    String lastname;

    Double balance;

    public UserInfoDTO toUserInfoDTO() {
        return UserInfoDTO.builder()
                .email(email)
                .firstname(firstname)
                .lastname(lastname)
                .balance(balance)
                .build();
    }
}