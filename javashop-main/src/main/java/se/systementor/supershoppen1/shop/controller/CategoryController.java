package se.systementor.supershoppen1.shop.controller;

import org.springframework.stereotype.Controller;
import se.systementor.supershoppen1.shop.services.CategoryService;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


}
