package se.systementor.supershoppen1.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.services.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Integer id) {
        return categoryService.get(id);
    }

    @PutMapping ("/create{name}{description}")
    public Category createNewCategory(@PathVariable("name") String name, @PathVariable("description") String description) {
        return categoryService.addCategory(name, description);
    }

    @PutMapping ("/edit{id}{name}{description}")
    public Category editCategory(@PathVariable("id") Integer id, @PathVariable("name") String name, @PathVariable("description") String description) {
        return categoryService.editCategory(id, name, description);
    }



}
