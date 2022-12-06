package se.systementor.supershoppen1.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.systementor.supershoppen1.shop.model.Category;

import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.Product;
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
        //Get the absolute path of ur image/Categories folder
        String filePath = "C:/Project/javashop-main/javashop-main/src/main/resources/static/images/Categories";
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
        List<Product> products = productService.getAll();
        List<CategoryAndProducts> list = new ArrayList<>();
        for (Category cat:categories) {
            List<Product> tempProdList = new ArrayList<>();
            for (Product prod:products) {
                if(cat.getId() == prod.getCategory()) {
                    tempProdList.add(prod);

                }
            }
            list.add(new CategoryAndProducts(cat, tempProdList));
        }


        model.addAttribute("categories", list);
        return "admin/categories";
    }


    @GetMapping("/admin/categories/new")
    public String createCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/create_category";
    }

    @PostMapping("/admin/categories/new")
    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        categoryService.saveImage(imageFile);
        return "redirect:/admin/categories";
    };

    @PostMapping("/admin/categories")
    public String saveCategory(@ModelAttribute ("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    };


   /* @PutMapping (path="/admin/categories/edit{id}{name}{description}")
    public Category editCategory(@PathVariable("id") Integer id, @PathVariable("name") String name, @PathVariable("description") String description) {
        return categoryService.editCategory(id, name, description);
    }*/

}
