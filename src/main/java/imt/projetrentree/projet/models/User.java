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
    private Long id;
    private String email;
    private String password;

    private String firstname;
    private String lastname;

    private String address;

    private Double balance;

    private boolean isAdmin;

    public UserInfoDTO toUserInfoDTO() {
        return UserInfoDTO.builder()
                .email(email)
                .firstname(firstname)
                .lastname(lastname)
                .address(address)
                .balance(balance)
                .build();
    }
}