package imt.projetrentree.projet.repositories;

import imt.projetrentree.projet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAndPassword(String email, String password);
    User findByEmailAndPassword(String email, String password);
}
