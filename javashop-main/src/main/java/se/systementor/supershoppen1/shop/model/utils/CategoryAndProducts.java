package se.systementor.supershoppen1.shop.model.utils;

import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.Product;

import java.util.List;

public class CategoryAndProducts {

    private String imagePath;
    private Category category;
    private List<Product> productList;


    public String getImagePath() {
        return imagePath;
    }

    public CategoryAndProducts(Category category, List<Product> productList, String imagePath){
        this.category = category;
        this.productList = productList;
        this.imagePath = imagePath;

    }

    public Category getCategory() {
        return category;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
