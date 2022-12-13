package se.systementor.supershoppen1.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.services.ShoppingCartService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class AnnotationAdvice {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @ModelAttribute
    public void getCart(Model model) {
        model.addAttribute("shoppingList", shoppingCartService.getProductsInCart());
    }
}
