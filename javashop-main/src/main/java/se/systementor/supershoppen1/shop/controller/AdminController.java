package se.systementor.supershoppen1.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.systementor.supershoppen1.shop.model.Category;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se.systementor.supershoppen1.shop.model.Newsletter;
import se.systementor.supershoppen1.shop.services.NewsletterService;
import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.model.utils.CategoryAndProducts;
import se.systementor.supershoppen1.shop.services.CategoryService;
import se.systementor.supershoppen1.shop.services.ProductService;


@Controller
public class AdminController {
    private final  ProductService productService;
    private NewsletterService newsletterService;
    private final CategoryService categoryService;

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


    @GetMapping("/admin/newsletter/all")
    List<Newsletter> getAllNewsletters(){
        return newsletterService.getAll();
    }

    @GetMapping("/admin/newsletter/sent")
    List<Newsletter> getAllSentNewsletters(){
        return newsletterService.getSent();
    }

    @GetMapping("/admin/newsletter/send/{id}")
    String sendNewsLetter(@PathVariable Integer id){
        return newsletterService.send(id);
    }

    @PostMapping("/admin/newsletter/new")
    Newsletter createNewsletter(@RequestBody Newsletter newNewsLetter){
        return newsletterService.create(newNewsLetter);
    }

    @GetMapping(path="/admin/categories")
    String showAdminCategories(Model model)
    {
        Category categoryToEdit = new Category();
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

        model.addAttribute("categories", list);
        model.addAttribute("categoryToEdit",categoryToEdit);

        return "admin/categories";
    }

    @GetMapping(path="/admin/products/edit/{id}")
    String editProduct(@PathVariable("id") int productId, Model model) {
        Product product = productService.get(productId);

        model.addAttribute("product", product);
        return "admin/update-product";
    }

    @GetMapping("/addNewProduct")
    public String addNewProduct() {
        return "admin/add-product";
    }

    @GetMapping("/admin/categories/new")
    public String createCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/create_category";
    }

    @PostMapping("/admin/categories")
    public String saveCategory(@ModelAttribute ("category") Category category, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        categoryService.addCategory(category,multipartFile);

        return "redirect:/admin/categories";


    }

    public String convertImagePath(String filePath,String fileName){
        if(filePath != null){
            return filePath.substring(filePath.length()-18) +"/"+fileName;
        }
        return "File path string is empty";
    }

    @PostMapping (path="/admin/categories/edit/{id}")
    public String editCategory(@ModelAttribute ("category") Category category,@RequestParam("image") MultipartFile multipartFile,@PathVariable Integer id) throws IOException {
        categoryService.editCategory(id,category,multipartFile);
        return "redirect:/admin/categories";
    }
}
