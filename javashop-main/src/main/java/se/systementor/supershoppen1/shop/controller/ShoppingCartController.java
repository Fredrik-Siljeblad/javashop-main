package se.systementor.supershoppen1.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import se.systementor.supershoppen1.shop.exception.StockException;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.services.ProductService;
import se.systementor.supershoppen1.shop.services.ShoppingCartService;

import java.util.Map;

@Controller
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        return modelAndView;
    }

    @GetMapping("/shoppingCart/addProduct/{productId}")
    public String addProductToCart(@PathVariable("productId") int productId) {
        productService.get(productId).ifPresent(shoppingCartService::addProduct);

        shoppingCart();

        return "redirect:/";
    }

    @GetMapping("/shoppingCart/removeProduct/{productId}")
    public String removeProductFromCart(@PathVariable("productId") int productId) {
        productService.get(productId).ifPresent(shoppingCartService::removeProduct);

        shoppingCart();

        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart/checkout")
    public ModelAndView checkout() {
        try {
            shoppingCartService.checkout();
        } catch (StockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }

        return shoppingCart();
    }

    @GetMapping
    public ModelAndView getCart() {
        ModelAndView modelAndView= new ModelAndView("home");
        shoppingCartService.addProduct(new Product());
        modelAndView.addObject("cart", shoppingCartService.getProductsInCart());

        return modelAndView;
    }


}
