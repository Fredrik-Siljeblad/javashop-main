package se.systementor.supershoppen1.shop.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> results = new ArrayList<String>();
        //Get the absolute path of ur image/Categories folder
        File[] files = new File("/Users/williamle/Documents/GitHub/javashop-main/javashop-main/src/main/resources/static/images/Categories").listFiles();
        if (files != null){
            for (File file : files) {
                if (file.isFile()) {
                    results.add(file.getPath().substring(87));
                }
            }
        }
        List<String> sortedResult = results.stream().sorted().toList();
        List<Category> categories = categoryService.getAll();
        List<CategoryAndProducts> list = new ArrayList<>()  ;
        for (int i = 1; i < categories.size() + 1; i++){
            list.add(new CategoryAndProducts(categoryService.get(i),productService.findAllProductsByCategoryId(i), sortedResult.get(i-1)));
        }
        model.addAttribute("categories", list);
        return "admin/categories";
    }





}
