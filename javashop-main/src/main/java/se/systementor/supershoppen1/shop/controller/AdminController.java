package se.systementor.supershoppen1.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.utils.CategoryAndProducts;
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
        List<Category> categories = categoryService.getAll();
        List<CategoryAndProducts> list = new ArrayList<>()  ;
        for (int i = 1; i <= categories.size(); i++){
            list.add(new CategoryAndProducts(categoryService.get(i),productService.findAllProductsByCategoryId(i)));
        }
        model.addAttribute("categories", list);
        return "admin/categories";
    }





}
