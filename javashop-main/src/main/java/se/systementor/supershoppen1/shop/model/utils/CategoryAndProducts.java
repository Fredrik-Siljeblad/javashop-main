package se.systementor.supershoppen1.shop.model.utils;

import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.Product;

import java.util.List;

public class CategoryAndProducts {

    private Category category;
    private List<Product> productList;


    public CategoryAndProducts(Category category, List<Product> productList){
        this.category = category;
        this.productList = productList;

    }


    public Category getCategory() {
        return category;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
