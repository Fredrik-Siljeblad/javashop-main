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
import se.systementor.supershoppen1.shop.model.Email;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.model.utils.CategoryAndProducts;
import se.systementor.supershoppen1.shop.model.utils.FunctionsUtils;
import se.systementor.supershoppen1.shop.model.utils.LatestProduct;
import se.systementor.supershoppen1.shop.services.CategoryService;
import se.systementor.supershoppen1.shop.services.EMailService.EmailServiceImp;
import se.systementor.supershoppen1.shop.services.ProductService;
import se.systementor.supershoppen1.shop.services.SubscriptionsService;

@Controller
public class HomeController {
    private  ProductService productService;
    private SubscriptionsService subscriptionsService;
    private CategoryService categoryService;
    private EmailServiceImp emailService;


    @Autowired
    public HomeController(ProductService productService, SubscriptionsService subscriptionsService, CategoryService categoryService, EmailServiceImp emailService) {
        this.productService = productService;
        this.subscriptionsService = subscriptionsService;
        this.categoryService = categoryService;
        this.emailService = emailService;
    }

    @GetMapping(path="/")
    String empty(Model model)
    {
        hideSubscription(model);

        List<Category> categories = categoryService.getAll();
        List<Product> productList = productService.getAll();

        productList = getProducts(productList);

        model.addAttribute("categories",categories);
        model.addAttribute("lastTen",productList);

        return "home";
    }

    public void hideSubscription(Model model) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Object ud = auth.getPrincipal();
        if (ud instanceof UserDetails) {
        String user = ((UserDetails)ud).getUsername();
        boolean va2 = subscriptionsService.isSubscriber(user);
        model.addAttribute("hideSubscription", va2);
    } else {
        model.addAttribute("hideSubscription", false);
    }
    }

    private List<Product> getProducts(List<Product> productList) {
        for (Product product : productList) {
            Category category = categoryService.get(product.getCategoryId());
            product.setCategoryName(category.getName());
            if(product.getFileName() == null){
                product.setFilePath(category.getFilePath());
                product.setFileName(category.getFileName());
            }
        }

        if(productList.size() >10){
            productList = productList.subList(productList.size() -10, productList.size());
        }
        return productList;
    }

    @RequestMapping("/products/{id}")
    String homePage(Model model, @PathVariable("id") int id){

        List<Category> categories = categoryService.getAll();
        List<Product> latestProducts = new ArrayList<>();

        if (id != 0){
            latestProducts = productService.findAllProductsByCategoryId(id);
        }else {
            latestProducts = productService.getAll();
        }

        latestProducts = getProducts(latestProducts);
        model.addAttribute("categories",categories);
        model.addAttribute("lastTen",latestProducts);
        return "home";
    }

    @GetMapping(path = "/contact")
    public String contactUsForm (Model model){
        model.addAttribute("email", new Email());
        return "contact";
    }

    @PostMapping(path = "/contact")
    public String sendContactForm(@ModelAttribute Email email, Model model){
        model.addAttribute("email", email);
        emailService.sendSimpleMessage(email);
        return "contact";
    }


}
