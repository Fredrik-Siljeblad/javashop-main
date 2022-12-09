package se.systementor.supershoppen1.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.model.utils.CategoryAndProducts;
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
        List<CategoryAndProducts> list = new ArrayList<>()  ;

        for( Category category: categories){
            List<Product> productList1 = new ArrayList<>();
            for(Product product : productList){
                if (product.getCategory() == category.getId()){
                    productList1.add(product);
                }
            }
            list.add(new CategoryAndProducts(category,productList1,convertImagePath(category.getFilePath(),category.getFileName())));
        }
        model.addAttribute("products", list);

        return "home";
    }

    public String convertImagePath(String filePath,String fileName){
        if(filePath != null){
            return filePath.substring(filePath.length()-18) +"/"+fileName;
        }
        return "File path string is empty";
    }

    @GetMapping(path="/test2")
    List<Product> getAll(){
        return productService.getAll();
    }


}
