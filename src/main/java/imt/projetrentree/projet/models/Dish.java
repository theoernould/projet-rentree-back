package imt.projetrentree.projet.models;

import imt.projetrentree.projet.models.enums.DishDiet;
import imt.projetrentree.projet.models.enums.DishTag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "dishes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String description;
    String alergens;
    String image;
    List<DishTag> tags;
    DishDiet diet;
    Double price;
}
