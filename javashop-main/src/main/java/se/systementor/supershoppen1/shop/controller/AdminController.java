package se.systementor.supershoppen1.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import se.systementor.supershoppen1.shop.services.CategoryService;
import se.systementor.supershoppen1.shop.services.ProductService;

@Controller
public class AdminController {
    private  ProductService productService;
    private CategoryService categoryService;
    @Autowired
    public AdminController(ProductService productService,CategoryService categoryService ) {
        this.productService = productService;
        this.categoryService =categoryService;
    }    

    @GetMapping(path="/admin/products")
    String empty(Model model)
    {
        model.addAttribute("products", productService.getAll());
        return "admin/products";
    }

    @GetMapping(path="/admin/categories")
    String showAdminCategories(Model model)
    {
        model.addAttribute("category1", categoryService.getName(1));
        model.addAttribute("beverages", productService.findAllProductsByCategoryId(1));
        model.addAttribute("category2", categoryService.getName(2));
        model.addAttribute("condiments", productService.findAllProductsByCategoryId(2));
        model.addAttribute("category3", categoryService.getName(3));
        model.addAttribute("confections", productService.findAllProductsByCategoryId(3));
        model.addAttribute("category4", categoryService.getName(4));
        model.addAttribute("dairyproducts", productService.findAllProductsByCategoryId(4));
        model.addAttribute("category5", categoryService.getName(5));
        model.addAttribute("grainscereals", productService.findAllProductsByCategoryId(5));
        model.addAttribute("category6", categoryService.getName(6));
        model.addAttribute("meatpoultry", productService.findAllProductsByCategoryId(6));
        model.addAttribute("category7", categoryService.getName(7));
        model.addAttribute("produce", productService.findAllProductsByCategoryId(7));
        model.addAttribute("category8", categoryService.getName(8));
        model.addAttribute("seafood", productService.findAllProductsByCategoryId(8));
        return "admin/categories";
    }





}
