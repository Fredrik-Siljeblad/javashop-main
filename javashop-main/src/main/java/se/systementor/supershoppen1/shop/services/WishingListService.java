package se.systementor.supershoppen1.shop.services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import se.systementor.supershoppen1.shop.exception.StockException;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.model.ProductRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class WishingListService implements ShoppingListService {

    private ProductRepository productRepository;

    private HashMap<Product, Integer> wishlist= new HashMap<>();

    public WishingListService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        if (wishlist.containsKey(product)) {
            wishlist.replace(product, wishlist.get(product) +1);
        }
        else {
            wishlist.put(product, 1);
        }
    }

    @Override
    public void removeProduct(Product product) {
        if (wishlist.containsKey(product)) {
            if (wishlist.get(product) > 1) {
                wishlist.replace(product, wishlist.get(product) -1);
            } else if (wishlist.get(product) == 1) {
                wishlist.remove(product);
            }
        }
    }

    @Override
    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(wishlist);
    }

    @Override
    public void checkout() throws StockException {
        Product product;
        for (var entry : wishlist.entrySet()) {
            product = productRepository.findById(entry.getKey().getId()).get();
            if (product.getStockLevel() < entry.getValue()) {
                throw new StockException(product);
            }

            entry.getKey().setStockLevel(product.getStockLevel() - entry.getValue());
        }

        productRepository.saveAllAndFlush(wishlist.keySet());
        wishlist.clear();
    }

    @Override
    public Integer getTotal() {
        return wishlist.entrySet().stream()
                .map(entry -> entry.getKey().getPrice() * entry.getValue())
                .reduce(Integer::sum)
                .orElse(0);
    }
}
