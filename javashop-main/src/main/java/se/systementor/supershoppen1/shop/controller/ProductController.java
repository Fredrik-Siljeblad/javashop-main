package se.systementor.supershoppen1.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.services.ProductService;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public String addProduct(Product product, BindingResult result) {
        if (result.hasErrors())
            return "admin/add-product";

        productService.save(product);
        return "redirect:/admin/products";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") int productId, Product product, BindingResult result) {
        if (result.hasErrors())
            return "admin/update-product";

        productService.updateProduct(productId, product);
        return "redirect:/admin/products";
    }
}