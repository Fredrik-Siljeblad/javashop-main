package Enhetstester;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import se.systementor.supershoppen1.shop.exception.StockException;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.model.ProductRepository;
import se.systementor.supershoppen1.shop.services.ShoppingCartService;
import se.systementor.supershoppen1.shop.services.WishingListService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WishingListServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private WishingListService wishingListService;
    @Mock
    private Map<Product, Integer> cart;

    private Product product;

    @BeforeEach
    void setUp(){
        productRepository = mock(ProductRepository.class);
        wishingListService = Mockito.spy( new WishingListService(productRepository));
        cart = new HashMap<>();
        product = new Product();
        product.setId(1);
        product.setName("Product");
        product.setPrice(30);
    }


    @Test
    void addProduct() {
        // Act & verify
        wishingListService.addProduct(product);
        verify(wishingListService,times(1)).addProduct(product);

    }

    @Test
    void removeProduct() {
        // Act & verify
        wishingListService.removeProduct(product);
        verify(wishingListService,times(1)).removeProduct(product);
    }

    @Test
    void getProductsInCart() {
        //Arrange
        cart.put(product,1);
        doReturn(cart).when(wishingListService).getProductsInCart();

        // Act
        Map<Product,Integer> productsInCart = wishingListService.getProductsInCart();

        // Assert
        assertEquals(cart,productsInCart);
    }

    @Test
    void checkout() throws StockException {
        // Act & verify
        wishingListService.checkout();
        verify(wishingListService,times(1)).checkout();

    }

    @Test
    void getTotal() {
        // Arrange
        when(wishingListService.getTotal()).thenReturn(product.getPrice());
        int total = wishingListService.getTotal();

        //assert
        assertEquals(30,total);
    }
}