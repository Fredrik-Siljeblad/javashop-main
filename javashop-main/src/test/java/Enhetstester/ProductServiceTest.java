package Enhetstester;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.parameters.P;
import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.model.ProductRepository;
import se.systementor.supershoppen1.shop.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    @Mock
    ProductService productService;

    Product product1;
    Product product2;
    Category category;

    @BeforeEach
    void setUp(){
        productRepository = mock(ProductRepository.class);
        productService = Mockito.spy( new ProductService(productRepository));

        product1 = new Product();
        product1.setId(1);
        product1.setCategory(1);
        product1.setName("Product1");

        product2 = new Product();
        product1.setId(2);
        product1.setCategory(1);
        product1.setName("Product2");

        category = new Category();
        category.setId(1);
        category.setName("Category1");

    }

    @Test
    void findAllProductsByCategoryId() {

        // Arrange
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        when(productRepository.findProductByCategoryId(1)).thenReturn(productList);


        // Act
        List <Product> productsFound = productService.findAllProductsByCategoryId(1);

        // Assert
        assertEquals(productsFound,productList);
    }

    @Test
    void updateProduct() {

        // Arrange
        Product toUpdate = new Product();
        toUpdate.setId(1);
        toUpdate.setName("updated Name");
        when(productRepository.findById(1)).thenReturn(Optional.ofNullable(product1));
        when(productRepository.save(any(Product.class))).thenReturn(toUpdate);

        // Act
        Product productUpdated = productService.updateProduct(toUpdate.getId(), toUpdate);

        // Assert
        assertEquals(productUpdated,toUpdate);

    }
}