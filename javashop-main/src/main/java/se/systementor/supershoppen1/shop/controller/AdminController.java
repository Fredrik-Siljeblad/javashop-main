package se.systementor.supershoppen1.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se.systementor.supershoppen1.shop.model.Newsletter;
import se.systementor.supershoppen1.shop.services.NewsletterService;
import se.systementor.supershoppen1.shop.services.ProductService;

@Controller
public class AdminController {
    private  ProductService productService;
    private NewsletterService newsletterService;
    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
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


}
