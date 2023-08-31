package imt.projetrentree.projet;

import imt.projetrentree.projet.dto.user.UserCreationDTO;
import imt.projetrentree.projet.models.Dish;
import imt.projetrentree.projet.models.enums.DishDiet;
import imt.projetrentree.projet.models.enums.DishTag;
import imt.projetrentree.projet.repositories.DishRepository;
import imt.projetrentree.projet.repositories.UserRepository;
import imt.projetrentree.projet.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProjetApplication {
	@Autowired
	private DishRepository dishRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetApplication.class, args);
	}

	@PostConstruct
	public void init() {
		saveDishIfNotExists("Spaghetti Carbonara", "https://img.cuisineaz.com/660x660/2023/04/11/i192604-pates-a-la-carbonara.jpg", "Classic Italian pasta dish with eggs, cheese, pancetta, and black pepper.", "Contains: eggs, dairy, pork", 12.99, List.of(DishTag.MEAT), DishDiet.NORMAL);
		saveDishIfNotExists("Grilled Salmon", "https://thekitchengirl.com/wp-content/uploads/Grilled-Salmon-a_19-1.jpg", "Fresh salmon fillet seasoned and grilled to perfection.", "Contains: fish", 18.50, List.of(DishTag.FISH), DishDiet.NORMAL);
		saveDishIfNotExists("Margherita Pizza", "https://fr.ooni.com/cdn/shop/articles/Margherita-9920.jpg?crop=center&height=800&v=1644590066&width=800", "Traditional pizza topped with fresh tomatoes, mozzarella, basil, and olive oil.", "Contains: dairy", 14.75, List.of(DishTag.VEGETABLE), DishDiet.NORMAL);
		saveDishIfNotExists("Chicken Stir-Fry", "https://www.allrecipes.com/thmb/xvlRRhK5ldXuGcXad8XDM5tTAfE=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/223382_chicken-stir-fry_Rita-1x1-1-b6b835ccfc714bb6a8391a7c47a06a84.jpg", "Sliced chicken and mixed vegetables stir-fried in a savory sauce.", "Contains: poultry", 10.95, List.of(DishTag.MEAT, DishTag.VEGETABLE), DishDiet.NORMAL);
		saveDishIfNotExists("Caesar Salad", "https://www.allrecipes.com/thmb/mXZ0Tulwn3x9_YB_ZbkiTveDYFE=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/229063-Classic-Restaurant-Caesar-Salad-ddmfs-4x3-231-89bafa5e54dd4a8c933cf2a5f9f12a6f.jpg", "Crisp romaine lettuce, croutons, Parmesan cheese, and Caesar dressing.", "Contains: dairy", 8.25, List.of(DishTag.VEGETABLE), DishDiet.NORMAL);
		saveDishIfNotExists("Beef Tacos", "https://www.saltandlavender.com/wp-content/uploads/2020/11/ground-beef-tacos-recipe-1.jpg", "Soft corn tortillas filled with seasoned beef, lettuce, cheese, and salsa.", "Contains: beef, dairy", 9.50, List.of(DishTag.MEAT), DishDiet.NORMAL);
		saveDishIfNotExists("Mushroom Risotto", "https://cdn.loveandlemons.com/wp-content/uploads/2023/01/mushroom-risotto.jpg", "Creamy risotto cooked with Arborio rice and a medley of mushrooms.", "Contains: dairy", 11.75, List.of(DishTag.VEGETABLE), DishDiet.NORMAL);
		saveDishIfNotExists("Vegetable Curry", "https://www.acouplecooks.com/wp-content/uploads/2020/02/Vegetable-Curry-001.jpg", "Assorted vegetables simmered in a fragrant curry sauce, served with rice.", "Contains: none", 13.25, List.of(DishTag.VEGETABLE), DishDiet.VEGETARIAN);
		saveDishIfNotExists("BBQ Ribs", "https://tequilasrestaurant.co.uk/wp-content/uploads/2023/07/sauced-pork-ribs-on-a-baoking-sheet.jpg", "Slow-cooked pork ribs slathered in smoky barbecue sauce.", "Contains: pork", 16.99, List.of(DishTag.MEAT), DishDiet.NORMAL);
		saveDishIfNotExists("Chocolate Lava Cake", "https://gimmedelicious.com/wp-content/uploads/2020/01/Chocolate-Molten-Lava-Cakes-10.jpg", "Warm chocolate cake with a gooey molten center, topped with vanilla ice cream.", "Contains: dairy, eggs", 7.50, List.of(DishTag.DESSERT), DishDiet.NORMAL);
		saveDishIfNotExists("Vegan Burger", "https://img.cuisineaz.com/660x660/2016/06/07/i95896-burger-veggie-au-steak-de-pois-chiches.jpg", "Vegan burger with vegan cheese, vegan bacon, vegan mayonnaise, vegan salad, vegan tomato, vegan onion, vegan ketchup, vegan mustard, vegan pickles, vegan bread.", "Contains: none", 15.99, List.of(DishTag.VEGETABLE), DishDiet.VEGAN);

		saveUserIfNotExists("user", "user", "firstname", "lastname");
	}

	private void saveUserIfNotExists(String email, String password, String firstname, String lastname) {
		if (!userRepository.existsByEmail(email)) {
			UserCreationDTO user = UserCreationDTO.builder()
					.email(email)
					.password(password)
					.firstname(firstname)
					.lastname(lastname)
					.build();
			userService.register(user,10000);
		}
	}

	private void saveDishIfNotExists(String name, String image, String description,String alergens, double price, List<DishTag> dishTags, DishDiet dishDiet) {
		if (dishRepository.findByName(name).isEmpty()) {
			Dish dish = Dish.builder()
					.name(name)
					.image(image)
					.description(description)
					.alergens(alergens)
					.price(price)
					.tags(dishTags)
					.diet(dishDiet)
					.build();
			dishRepository.save(dish);
		}
	}

}
