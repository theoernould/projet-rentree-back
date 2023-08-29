-- Table Customer
CREATE TABLE Customers (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    firstname VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    balance DECIMAL(10, 2)
);

-- Table Order
CREATE TABLE Orders (
    id INT PRIMARY KEY,
    customerId INT,
    createdAt TIMESTAMP,
    FOREIGN KEY (customerId) REFERENCES Customers(id)
);

-- Table Dish
CREATE TABLE Dishes (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    image VARCHAR(512),
    category VARCHAR(255),
    info TEXT,
    price DECIMAL(10, 2)
);

-- Table DishOrder
CREATE TABLE DishOrders (
    idOrder INT,
    idDish INT,
    PRIMARY KEY (idOrder, idDish),
    FOREIGN KEY (idOrder) REFERENCES Orders(id),
    FOREIGN KEY (idDish) REFERENCES Dishes(id)
);

INSERT INTO Dishes (id, name, image, category, info, price)
VALUES
    (1, 'Spaghetti Carbonara', 'https://img.cuisineaz.com/660x660/2023/04/11/i192604-pates-a-la-carbonara.jpg', 'Pasta', 'Classic Italian pasta dish with eggs, cheese, pancetta, and black pepper.', 12.99),
    (2, 'Grilled Salmon', 'https://thekitchengirl.com/wp-content/uploads/Grilled-Salmon-a_19-1.jpg', 'Seafood', 'Fresh salmon fillet seasoned and grilled to perfection.', 18.50),
    (3, 'Margherita Pizza', 'https://fr.ooni.com/cdn/shop/articles/Margherita-9920.jpg?crop=center&height=800&v=1644590066&width=800', 'Pizza', 'Traditional pizza topped with fresh tomatoes, mozzarella, basil, and olive oil.', 14.75),
    (4, 'Chicken Stir-Fry', 'https://www.allrecipes.com/thmb/xvlRRhK5ldXuGcXad8XDM5tTAfE=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/223382_chicken-stir-fry_Rita-1x1-1-b6b835ccfc714bb6a8391a7c47a06a84.jpg', 'Asian', 'Sliced chicken and mixed vegetables stir-fried in a savory sauce.', 10.95),
    (5, 'Caesar Salad', 'https://www.allrecipes.com/thmb/mXZ0Tulwn3x9_YB_ZbkiTveDYFE=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/229063-Classic-Restaurant-Caesar-Salad-ddmfs-4x3-231-89bafa5e54dd4a8c933cf2a5f9f12a6f.jpg', 'Salad', 'Crisp romaine lettuce, croutons, Parmesan cheese, and Caesar dressing.', 8.25),
    (6, 'Beef Tacos', 'https://www.saltandlavender.com/wp-content/uploads/2020/11/ground-beef-tacos-recipe-1.jpg', 'Mexican', 'Soft corn tortillas filled with seasoned beef, lettuce, cheese, and salsa.', 9.50),
    (7, 'Mushroom Risotto', 'https://cdn.loveandlemons.com/wp-content/uploads/2023/01/mushroom-risotto.jpg', 'Risotto', 'Creamy risotto cooked with Arborio rice and a medley of mushrooms.', 11.75),
    (8, 'Vegetable Curry', 'https://www.acouplecooks.com/wp-content/uploads/2020/02/Vegetable-Curry-001.jpg', 'Curry', 'Assorted vegetables simmered in a fragrant curry sauce, served with rice.', 13.25),
    (9, 'BBQ Ribs', 'https://tequilasrestaurant.co.uk/wp-content/uploads/2023/07/sauced-pork-ribs-on-a-baoking-sheet.jpg', 'BBQ', 'Slow-cooked pork ribs slathered in smoky barbecue sauce.', 16.99),
    (10, 'Chocolate Lava Cake', 'https://gimmedelicious.com/wp-content/uploads/2020/01/Chocolate-Molten-Lava-Cakes-10.jpg', 'Dessert', 'Warm chocolate cake with a gooey molten center, topped with vanilla ice cream.', 7.50);