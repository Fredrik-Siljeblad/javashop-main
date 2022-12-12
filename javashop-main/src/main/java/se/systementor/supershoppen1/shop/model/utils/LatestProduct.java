package se.systementor.supershoppen1.shop.model.utils;

import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.Product;

public class LatestProduct {
    private Product product;
    private Category category;

    public LatestProduct(Product product, Category category){
        this.product = product;
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public Category getCategory() {
        return category;
    }
}
