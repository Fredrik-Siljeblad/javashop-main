package se.systementor.supershoppen1.shop.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.services.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@WebMvcTest(controllers = ProductController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    static int n = 0;

    private Product generateProduct(){
        n++;
        var product = new Product();
        product.setId(n);
        product.setName("product" + n);
        product.setStockLevel(n);
        product.setCategory(n);
        product.setDescription("description" + n);
        product.setPrice(100 + n);
        product.setCategoryId(n);
        product.setFileName(String.valueOf(n));
        product.setFilePath(String.valueOf(n));
        product.setCategoryName(String.valueOf(n));
        product.setCampaignPercentage(n);

        return product;
    }

    @Test
    void getAllMovies() throws Exception {
        ArrayList<Product> products = new ArrayList<Product>(
        Arrays.asList(
                generateProduct(),
                generateProduct(),
                generateProduct(),
                generateProduct()
        ));

        when(productService.getAllProducts()).thenReturn(products);

        this.mvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(products.size()))
                .andDo(print());
    }

    @Test
    void getProduct() throws Exception {
        Product product = generateProduct();
        when(productService.get(any())).thenReturn(Optional.of(product));

        this.mvc.perform(get("/api/products/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(product.getId())))
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.price", is(product.getPrice())))
                .andDo(print());
    }

    @Test
    void addProduct() throws Exception {
        Product product = generateProduct();

        this.mvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void updateProduct() throws Exception {
        Product product = generateProduct();
        Product updatedProduct = generateProduct();
        updatedProduct.setId(product.getId());

        when(productService.get(product.getId())).thenReturn(Optional.of(product));
        when(productService.save(any(Product.class))).thenReturn(updatedProduct);

        this.mvc.perform(put("/api/products/update/{id}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value(updatedProduct.getName()))
//                .andExpect(jsonPath("$.description").value(updatedProduct.getDescription()))
//                .andExpect(jsonPath("$.price").value(updatedProduct.getPrice()))
                .andDo(print());
    }
}