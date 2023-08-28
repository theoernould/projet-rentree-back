package imt.projetrentree.projet.repositories;

import imt.projetrentree.projet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameAndPassword(String username, String password);
    User findByUsernameAndPassword(String username, String password);
}
