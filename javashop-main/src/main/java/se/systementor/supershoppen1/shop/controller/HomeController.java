package se.systementor.supershoppen1.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.model.utils.CategoryAndProducts;
import se.systementor.supershoppen1.shop.model.utils.FunctionsUtils;
import se.systementor.supershoppen1.shop.model.utils.LatestProduct;
import se.systementor.supershoppen1.shop.services.CategoryService;
import se.systementor.supershoppen1.shop.services.ProductService;
import se.systementor.supershoppen1.shop.services.SubscriptionsService;

@Controller
public class HomeController {
    private  ProductService productService;
    private SubscriptionsService subscriptionsService;
    private CategoryService categoryService;


    @Autowired
    public HomeController(ProductService productService, SubscriptionsService subscriptionsService, CategoryService categoryService) {
        this.productService = productService;
        this.subscriptionsService = subscriptionsService;
        this.categoryService = categoryService;
    }

    @GetMapping(path="/")
    String empty(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object ud = auth.getPrincipal();
        if (ud instanceof UserDetails) {
            String user = ((UserDetails)ud).getUsername();
            boolean va2 = subscriptionsService.isSubscriber(user);
            model.addAttribute("hideSubscription", va2);
        } else {
            model.addAttribute("hideSubscription", false);
        }

        List<Category> categories = categoryService.getAll();
        List<Product> productList = productService.getAll();
        List<LatestProduct> latestProducts = new ArrayList<>();
        for (Product product: productList) {
            latestProducts.add(new LatestProduct(product,categoryService.get(product.getCategoryId())));
        }
        List<LatestProduct> latestProductsSort = latestProducts.subList(latestProducts.size()-11, latestProducts.size()-1);
        model.addAttribute("categories",categories);

        model.addAttribute("lastTen",latestProductsSort);

        return "home";
    }







}
