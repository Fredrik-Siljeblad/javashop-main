package se.systementor.supershoppen1.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import se.systementor.supershoppen1.shop.configuration.PasswordEncoderConfiguration;
import se.systementor.supershoppen1.shop.model.*;
import se.systementor.supershoppen1.shop.services.CategoryService;
import se.systementor.supershoppen1.shop.services.ProductService;
import se.systementor.supershoppen1.shop.services.ShopUserDetailsService;
import se.systementor.supershoppen1.shop.services.SubscriptionsService;

@Component
public class SeedData implements CommandLineRunner {
    private final ProductService productService;
    private final SubscriptionsService subscriptionsService;
    private final CategoryService categoryService;
    private final ShopUserDetailsService userDetailsService;
    private final PasswordEncoderConfiguration encoderConfig;

    @Autowired
    public SeedData(ProductService productService, SubscriptionsService subscriptionsService, CategoryService categoryService, ShopUserDetailsService userDetailsService, PasswordEncoderConfiguration encoderConfig) {
        this.productService = productService;
        this.subscriptionsService = subscriptionsService;
        this.categoryService = categoryService;
        this.userDetailsService = userDetailsService;
        this.encoderConfig = encoderConfig;
    }

    @Override
    public void run(String... args) {
        // adminAccount();
        // userAccount();
        // category();


        //exampleCategories();
        //exampleProducts();
        //exampleUsers();
        //exampleSubscriptions();
    }


    private int findCatId(List<Category> categories, String name) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) return category.getId();
        }
        return -1;
    }

    private void exampleCategories() {
        final String FILEPATH = "src/main/resources/static/images/Categories";
        var existing = categoryService.getAll();
        addCategory(existing, "Beverages", "Soft drinks, coffees, teas, beers, and ales", FILEPATH, "1.jpeg");
        addCategory(existing, "Condiments", "Sweet and savory sauces, relishes, spreads, and seasonings", FILEPATH, "2.jpeg");
        addCategory(existing, "Confections", "Desserts, candies, and sweet breads", FILEPATH, "3.jpeg");
        addCategory(existing, "Dairy Products", "Cheeses", FILEPATH, "4.jpeg");
        addCategory(existing, "Grains/Cereals", "Breads, crackers, pasta, and cereal", FILEPATH, "5.jpeg");
        addCategory(existing, "Meat/Poultry", "Prepared meats", FILEPATH, "6.jpeg");
        addCategory(existing, "Produce", "Dried fruit and bean curd", FILEPATH, "7.jpeg");
        addCategory(existing, "Seafood", "Seaweed and fish", FILEPATH, "8.gif");
    }

    private void exampleUsers() {
        var existingUsers = userDetailsService.getAll();
        addUser(existingUsers, "admin@user.se", "ROLE_ADMIN");
        addUser(existingUsers, "user@user.se", "ROLE_USER");

    }

    private void exampleSubscriptions() {
        var existingSubscriptions = subscriptionsService.getAll();
        addSubcription(existingSubscriptions, "admin@user.se", true);
        addSubcription(existingSubscriptions, "user@user.se", true);
    }

    private void addSubcription(List<String> existingSubscriptions, String email, boolean active) {
        for (String sub : existingSubscriptions) {
            if (sub == null) return;
            if (sub.equals(email)) return;
        }
        Subscription newSub = new Subscription(email, active);
        subscriptionsService.save(newSub);
    }


    private void exampleProducts() {
        var existingCats = categoryService.getAll();
        var existingProducts = productService.getAll();

        addProduct(existingProducts, findCatId(existingCats, "Beverages"), "Chai", 18, 39, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Beverages"), "Chang", 19, 17, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Condiments"), "Aniseed Syrup", 10, 13, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Condiments"), "Chef Anton's Cajun Seasoning", 22, 53, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Condiments"), "Chef Anton's Gumbo Mix", 21, 0, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Condiments"), "Grandma's Boysenberry Spread", 25, 120, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Produce"), "Uncle Bob's Organic Dried Pears", 30, 15, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Condiments"), "Northwoods Cranberry Sauce", 40, 6, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Meat/Poultry"), "Mishi Kobe Niku", 97, 29, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Seafood"), "Ikura", 31, 31, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Dairy Products"), "Queso Cabrales", 21, 22, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Dairy Products"), "Queso Manchego La Pastora", 38, 86, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Seafood"), "Konbu", 6, 24, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Produce"), "Tofu", 23, 35, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Condiments"), "Genen Shouyu", 16, 39, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Confections"), "Pavlova", 17, 29, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Meat/Poultry"), "Alice Mutton", 39, 0, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Seafood"), "Carnarvon Tigers", 63, 42, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Confections"), "Teatime Chocolate Biscuits", 9, 25, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Confections"), "Sir Rodney's Marmalade", 81, 40, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Confections"), "Sir Rodney's Scones", 10, 3, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Grains/Cereals"), "Gustaf's Knäckebröd", 21, 104, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Grains/Cereals"), "Tunnbröd", 9, 61, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Beverages"), "Guaraná Fantástica", 5, 20, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Confections"), "NuNuCa Nuß-Nougat-Creme", 14, 76, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Confections"), "Gumbär Gummibärchen", 31, 15, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Confections"), "Schoggi Schokolade", 44, 49, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Produce"), "Rössle Sauerkraut", 46, 26, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Meat/Poultry"), "Thüringer Rostbratwurst", 124, 0, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Seafood"), "Nord-Ost Matjeshering", 26, 10, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Dairy Products"), "Gorgonzola Telino", 13, 0, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Dairy Products"), "Mascarpone Fabioli", 32, 9, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Dairy Products"), "Geitost", 3, 112, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Beverages"), "Sasquatch Ale", 14, 111, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Beverages"), "Steeleye Stout", 18, 20, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Seafood"), "Inlagd Sill", 19, 112, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Seafood"), "Gravad lax", 26, 11, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Beverages"), "Côte de Blaye", 264, 17, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Beverages"), "Chartreuse verte", 18, 69, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Seafood"), "Boston Crab Meat", 18, 123, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Seafood"), "Jack's New England Clam Chowder", 10, 85, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Grains/Cereals"), "Singaporean Hokkien Fried Mee", 14, 26, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Beverages"), "Ipoh Coffee", 46, 17, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Condiments"), "Gula Malacca", 19, 27, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Seafood"), "Rogede sild", 10, 5, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Seafood"), "Spegesild", 12, 95, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Confections"), "Zaanse koeken", 10, 36, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Confections"), "Chocolade", 13, 15, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Confections"), "Maxilaku", 20, 10, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Confections"), "Valkoinen suklaa", 16, 65, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Produce"), "Manjimup Dried Apples", 53, 20, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Grains/Cereals"), "Filo Mix", 7, 38, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Meat/Poultry"), "Perth Pasties", 33, 0, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Meat/Poultry"), "Tourtière", 7, 21, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Meat/Poultry"), "Pâté chinois", 24, 115, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Grains/Cereals"), "Gnocchi di nonna Alice", 38, 21, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Grains/Cereals"), "Ravioli Angelo", 20, 36, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Seafood"), "Escargots de Bourgogne", 13, 62, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Dairy Products"), "Raclette Courdavault", 55, 79, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Dairy Products"), "Camembert Pierrot", 34, 19, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Condiments"), "Sirop d'érable", 29, 113, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Confections"), "Tarte au sucre", 49, 17, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Condiments"), "Vegie-spread", 44, 24, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Grains/Cereals"), "Wimmers gute Semmelknödel", 33, 22, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Condiments"), "Louisiana Fiery Hot Pepper Sauce", 21, 76, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Condiments"), "Louisiana Hot Spiced Okra", 17, 4, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Beverages"), "Laughing Lumberjack Lager", 14, 52, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Confections"), "Scottish Longbreads", 13, 6, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Dairy Products"), "Gudbrandsdalsost", 36, 26, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Beverages"), "Outback Lager", 15, 15, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Dairy Products"), "Flotemysost", 22, 26, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Dairy Products"), "Mozzarella di Giovanni", 35, 14, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Seafood"), "Röd Kaviar", 15, 101, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Produce"), "Longlife Tofu", 10, 4, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Beverages"), "Rhönbräu Klosterbier", 8, 125, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Beverages"), "Lakkalikööri", 18, 57, "Fantastic");
        addProduct(existingProducts, findCatId(existingCats, "Condiments"), "Original Frankfurter grüne Soße", 13, 32, "Fantastic");
    }

    private void addUser(List<UserAccount> existingUsers, String email, String groups) {
        for (UserAccount acc : existingUsers) {
            if (acc.getEmail().equals(email)) return;
        }
        UserAccount acc = new UserAccount(email, encoderConfig.passwordEncoder().encode("stefan123"), groups);
        userDetailsService.save(acc);

    }


    private void addCategory(List<Category> existing, String name, String description, String filePath, String fileName) {
        for (Category cat : existing) {
            if (cat.getName().equals(name)) return;
        }
        Category cat1 = new Category();
        cat1.setName(name);
        cat1.setDescription(description);
        cat1.setFilePath(filePath);
        cat1.setFileName(fileName);
        categoryService.save(cat1);
    }

    private void addProduct(List<Product> existing, int catId, String name, int pris, int stocklevel, String description) {
        for (Product product : existing) {
            if (product.getName().equals(name)) return;
        }
        Product product1 = new Product();
        product1.setName(name);
        product1.setPrice(pris);
        product1.setStockLevel(stocklevel);
        product1.setDescription(description);
        product1.setCategory(catId);
        productService.save(product1);
    }


}