package se.systementor.supershoppen1.shop.services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import se.systementor.supershoppen1.shop.exception.StockException;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.model.ProductRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartService implements ShoppingListService {

    private final ProductRepository productRepository;

    private Map<Product, Integer> cart = new HashMap<>();

    public ShoppingCartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        if (cart.containsKey(product)) {
            cart.replace(product, cart.get(product) +1);
        } else {
            cart.put(product, 1);
        }
    }

    @Override
    public void removeProduct(Product product) {
        if (cart.containsKey(product)) {
            if (cart.get(product) > 1) {
                cart.replace(product, cart.get(product) - 1);
            } else if (cart.get(product) == 1){
                cart.remove(product);
                System.out.println("deleted product");
            }
        }
    }

    @Override
    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(cart);
    }

    @Override
    public void checkout() throws StockException {
        Product product;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            product = productRepository.findById(entry.getKey().getId()).get();
            if (product.getStockLevel() < entry.getValue()) {
                throw new StockException(product);
            }

            entry.getKey().setStockLevel(product.getStockLevel() - entry.getValue());
        }

        productRepository.saveAllAndFlush(cart.keySet());
        cart.clear();
    }

    @Override
    public Integer getTotal() {
        return cart.entrySet().stream()
                .map(entry -> entry.getKey().getPrice() * entry.getValue())
                .reduce(Integer::sum)
                .orElse(0);
    }
}
