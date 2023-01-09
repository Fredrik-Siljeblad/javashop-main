package Enhetstester;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import se.systementor.supershoppen1.shop.exception.StockException;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.model.ProductRepository;
import se.systementor.supershoppen1.shop.services.ShoppingCartService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShoppingCartServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ShoppingCartService shoppingCartService;
    @Mock
    private Map<Product, Integer> cart;

    private Product product;

    @BeforeEach
    void setUp(){
        productRepository = mock(ProductRepository.class);
        shoppingCartService = Mockito.spy( new ShoppingCartService(productRepository));
        cart = new HashMap<>();
        product = new Product();
        product.setId(1);
        product.setName("Product");
        product.setPrice(30);
    }


    @Test
    void addProduct() {
        // Act & verify
        shoppingCartService.addProduct(product);
        verify(shoppingCartService,times(1)).addProduct(product);

    }

    @Test
    void removeProduct() {
        // Act & verify
        shoppingCartService.removeProduct(product);
        verify(shoppingCartService,times(1)).removeProduct(product);
    }

    @Test
    void getProductsInCart() {
        //Arrange
        cart.put(product,1);
        doReturn(cart).when(shoppingCartService).getProductsInCart();

        // Act
        Map<Product,Integer> productsInCart = shoppingCartService.getProductsInCart();

        // Assert
        assertEquals(cart,productsInCart);
    }

    @Test
    void checkout() throws StockException {
        // Act & verify
        shoppingCartService.checkout();
        verify(shoppingCartService,times(1)).checkout();

    }

    @Test
    void getTotal() {
        // Arrange
        when(shoppingCartService.getTotal()).thenReturn(product.getPrice());
        int total = shoppingCartService.getTotal();

        //assert
        assertEquals(30,total);
    }
}