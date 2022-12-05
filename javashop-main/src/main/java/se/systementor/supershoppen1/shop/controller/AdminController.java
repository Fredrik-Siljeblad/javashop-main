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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import se.systementor.supershoppen1.shop.model.Category;

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

    @GetMapping(path="/admin/category")
    String emptyy(Model model)
    {
        model.addAttribute("category", categoryService.getAll());
        return "admin/category";
    }
    @GetMapping(path="/admin/categories")
    String showAdminCategories(Model model)
    {
        //Get the absolute path of ur image/Categories folder
        String filePath = "C:\\Project\\javashop-main\\javashop-main\\src\\main\\resources\\static\\images\\Categories";
        List<String> results = new ArrayList<String>();
        File[] files = new File(filePath).listFiles();
        if (files != null){
            for (File file : files) {
                if (file.isFile()) {
                    results.add(file.getPath().substring(filePath.length()-18));
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


    @PutMapping(path="/admin/category/create{name}{description}")
    public Category createNewCategory(@PathVariable("name") String name, @PathVariable("description") String description) {
        return categoryService.addCategory(name, description);
    }

    @PutMapping (path="/admin/category/edit{id}{name}{description}")
    public Category editCategory(@PathVariable("id") Integer id, @PathVariable("name") String name, @PathVariable("description") String description) {
        return categoryService.editCategory(id, name, description);
    }

}
