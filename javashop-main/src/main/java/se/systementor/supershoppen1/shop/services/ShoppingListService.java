package se.systementor.supershoppen1.shop.services;

import se.systementor.supershoppen1.shop.exception.StockException;
import se.systementor.supershoppen1.shop.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingListService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws StockException;

    Integer getTotal();

}
