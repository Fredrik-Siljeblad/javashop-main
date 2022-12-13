package se.systementor.supershoppen1.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.services.CategoryService;


@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path="/category")
    String empty(Model model)
    {
        model.addAttribute("category", categoryService.getAll());
        return "/category";    }


    @GetMapping(path="/category/{id}")
    public Category getCategoryById(@PathVariable("id") Integer id) {
        return categoryService.get(id);
    }





}
