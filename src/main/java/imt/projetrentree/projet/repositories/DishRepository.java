package imt.projetrentree.projet.repositories;

import imt.projetrentree.projet.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
