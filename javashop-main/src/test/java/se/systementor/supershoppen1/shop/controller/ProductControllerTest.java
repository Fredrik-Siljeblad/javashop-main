package se.systementor.supershoppen1.shop.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.model.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mvc;

    static int n = 0;

    private Product generateMovie(){
        n++;
        var product = new Product();
        product.setId(n);
        product.setName("product" + n);
        product.setStockLevel(n);
        product.setCategory(n);
        product.setDescription("description" + n);
        product.setPrice(100 + n);
        product.setCategoryId(n);

        return product;
    }

    @Test
    void getAllMovies() throws Exception {
        ArrayList<Product> products = new ArrayList<>(List.of(
                generateMovie(),
                generateMovie(),
                generateMovie(),
                generateMovie()
        ));

        when(productRepository.findAll()).thenReturn(products);

        this.mvc.perform(get("/products"))
                .andExpect(status().isOk());
    }
}