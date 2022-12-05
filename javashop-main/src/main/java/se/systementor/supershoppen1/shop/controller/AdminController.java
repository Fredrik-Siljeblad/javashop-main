package se.systementor.supershoppen1.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.services.CategoryService;
import se.systementor.supershoppen1.shop.services.ProductService;


@Controller
public class AdminController {
    private  ProductService productService;
    private CategoryService categoryService;
    @Autowired
    public AdminController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
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

    @PutMapping(path="/admin/category/create{name}{description}")
    public Category createNewCategory(@PathVariable("name") String name, @PathVariable("description") String description) {
        return categoryService.addCategory(name, description);
    }

    @PutMapping (path="/admin/category/edit{id}{name}{description}")
    public Category editCategory(@PathVariable("id") Integer id, @PathVariable("name") String name, @PathVariable("description") String description) {
        return categoryService.editCategory(id, name, description);
    }

}
