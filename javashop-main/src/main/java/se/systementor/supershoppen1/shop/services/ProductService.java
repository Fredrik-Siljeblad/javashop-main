package se.systementor.supershoppen1.shop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.model.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;

    ProductService(ProductRepository rep) {
        super();
        this.repository = rep;
    }

    public List<Product> getAll(){
        var l = new ArrayList<Product>();
        for(Product r : repository.findAll())
        {
            l.add(r);
        }
        return l;
    }

    public Optional<Product> get(Integer id){
        return repository.findById(id);
    }

    public Product save(Product product1) {
        return repository.save(product1);
    }

    public List<Product> findAllProductsByCategoryId(Integer id){
     return repository.findProductByCategoryId(id);
    }

    public Product updateProduct(int productId, Product updatedProduct) {
        var product = repository.findById(productId);
        if (product == null)
            throw new IllegalArgumentException();
        updatedProduct.setId(productId);
        return repository.save(updatedProduct);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}

