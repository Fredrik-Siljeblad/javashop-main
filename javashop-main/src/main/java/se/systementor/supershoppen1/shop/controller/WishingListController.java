package se.systementor.supershoppen1.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import se.systementor.supershoppen1.shop.exception.StockException;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.services.ProductService;
import se.systementor.supershoppen1.shop.services.ShoppingCartService;
import se.systementor.supershoppen1.shop.services.WishingListService;

@Controller
public class WishingListController {
    private final WishingListService wishingListService;
    private final ProductService productService;

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public WishingListController(WishingListService wishingListService, ProductService productService, ShoppingCartService shoppingCartService) {
        this.wishingListService = wishingListService;
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/wishingList")
    public ModelAndView wishingList() {
        ModelAndView modelAndView = new ModelAndView("/wishingList");
        modelAndView.addObject("products", wishingListService.getProductsInCart());
        modelAndView.addObject("total", wishingListService.getTotal().toString());
        return modelAndView;
    }

    @GetMapping("/wishingList/addProduct/{productId}")
    public String addProductToList(@PathVariable("productId") int productId) {
        productService.get(productId).ifPresent(wishingListService::addProduct);

        wishingList();

        return "redirect:/";
    }

    @GetMapping("/wishingList/removeProduct/{productId}")
    public String removeProductFromList(@PathVariable("productId") int productId) {
        productService.get(productId).ifPresent(wishingListService::removeProduct);

        wishingList();

        return "redirect:/wishingList";
    }

    @GetMapping("/wishingList/checkout")
    public ModelAndView checkout() {
        try {
            wishingListService.checkout();
        } catch (StockException e) {
            return wishingList().addObject("outOfStockMessage", e.getMessage());
        }

        return wishingList();
    }

    @GetMapping("/wishingList/moveToCart/{id}")
    public String moveToCart(@PathVariable("id") int productId) {
        productService.get(productId).ifPresent(shoppingCartService::addProduct);

        return removeProductFromList(productId);
    }
}
